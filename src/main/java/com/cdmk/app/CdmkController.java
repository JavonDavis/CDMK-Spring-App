package com.cdmk.app;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.*;
import java.text.DecimalFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.NamedList;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.context.ServletContextAware;
import org.springframework.stereotype.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.entopix.maui.filters.MauiFilter.MauiFilterException;
import com.entopix.maui.main.MauiWrapper;
import com.entopix.maui.stemmers.PorterStemmer;
import com.entopix.maui.util.Topic;

@Controller
public class CdmkController implements ServletContextAware {

	protected ServletContext context;
    private static String SOLR_URL = "http://localhost:8983/solr/cdmk-test";
//    private static String SOLR_URL = "http://cdmk-caribbean.net:8983/solr/cdmk";
    private static SolrServer server;
    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    private String searchConcept;
    // Search variables
    private List<Concept> filterConcepts = new ArrayList<>();
    private List<Item> searchResults = new ArrayList<>();

    static {
        try {
            server = new CommonsHttpSolrServer(SOLR_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void setServletContext(ServletContext servletContext) {
		this.context = servletContext;
	}

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView search(HttpServletRequest request) {
        if(request.getParameterMap().containsKey("q"))
        {
            String concept = request.getParameter("q");
            searchConcept = concept;
            request.setAttribute("concept", concept);

            Set<Item> results = searchForConcept(new String[]{concept});

            populateSearchResults(results);
            populateFilterConcepts(results);

            request.setAttribute("filters", filterConcepts);
            request.setAttribute("items", searchResults);
            return new ModelAndView("results");
        }
        else if(request.getParameterMap().containsKey("filter") || request.getParameterMap().containsKey("expand"))
        {
            String filterConcept;
            boolean check = request.getParameterMap().containsKey("filter");
            if(request.getParameterMap().containsKey("filter"))
                filterConcept = request.getParameter("filter");
            else
                filterConcept = request.getParameter("expand");
            List<Item> filteredSearchResults = new ArrayList<>();

            filteredSearchResults.addAll(searchResults);
            boolean conceptChecked = false;
            for (Concept concept : filterConcepts) {
                if (concept.equals(new Concept(filterConcept))) {
                    concept.setChecked(check);
                }
                if(concept.isChecked())
                    conceptChecked = true;
            }

            // The following operation could become very timely if concepts in vocabulary becomes > 10000 but not likely
            // at the time of writing
            for(Item item: searchResults)
            {
                if(conceptChecked) {
                    if (item.getConcepts() != null) {
                        for (Concept concept : filterConcepts) {

                            if (concept.isChecked()) {
                                if (!item.getConcepts().contains(concept)) {
                                    filteredSearchResults.remove(item);
                                }
                            }
                        }
                    } else {
                        filteredSearchResults.remove(item);
                    }
                }
            }

            request.setAttribute("concept", searchConcept);
            request.setAttribute("filters", filterConcepts);
            request.setAttribute("items", filteredSearchResults);
            return new ModelAndView("results");
        }
        return new ModelAndView("home");
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public ModelAndView searchPage(@RequestParam(value="text", required=false) String text,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {

        String[] concepts = text.split(",");
        if(concepts.length > 0)
            searchConcept = concepts[0];

        Set<Item> results = searchForConcept(concepts);

        populateFilterConcepts(results);
        populateSearchResults(results);

        request.setAttribute("filters", filterConcepts);
        request.setAttribute("items", searchResults);

        request.setAttribute("concept", text);
        return new ModelAndView("results");
    }

	@RequestMapping(value = "/share", method = RequestMethod.GET)
	public String shareForm(HttpServletRequest request) {
		return "share";
	}

	@RequestMapping(value = "/api", method = RequestMethod.GET)
    public String apiPage(HttpServletRequest request) {
        return "api";
    }


	@RequestMapping(value = "/share", method = RequestMethod.POST) 
	public ModelAndView share(
	        @RequestParam(value="title") String title,
			@RequestParam(value="file", required = false) MultipartFile file,
			@RequestParam(value="url", required = false) String url,
			HttpServletRequest request,
			HttpServletResponse response
		) {

		//request.setAttribute("concepts", mauiExtractor(text));
        Concept[] concepts = poolPartyExtractor(file, url);

        String filePath = "";

        if(file.getSize() <= 0 && url.isEmpty())
        {
            return new ModelAndView("error");
        }
        if(file.getSize() > 0) {
            filePath = "/cdmk/" + file.getOriginalFilename();
            final String finalFilePath = filePath;

            Runnable documentUploaderTask = new Runnable() {
                public void run() {
                    ContentStreamUpdateRequest req = new ContentStreamUpdateRequest("/update/extract");
                    try {
                        req.addFile(new File(finalFilePath));
                        req.setParam("literal.id", finalFilePath);
                        NamedList<Object> result = server.request(req);
                        System.out.println("Result: " + result);
                    } catch (IOException | SolrServerException e) {
                        e.printStackTrace();
                    }
                }
            };

            ExecutorService executor = Executors.newCachedThreadPool();
            executor.submit(documentUploaderTask);
        }
        if(concepts.length > 0)
            addToIndex(title, filePath, url, concepts);

		request.setAttribute("concepts", concepts);
		return new ModelAndView("extraction");
	}

    @RequestMapping(value="/cdmk/{file:.*}", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response, @PathVariable("file") String fileName) throws IOException {

        File file = new File("/cdmk/"+fileName);

        if(!file.exists()){
            String errorMessage = "Sorry. The file you are looking for does not exist in the index";
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;
        }

        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType==null){
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }

        System.out.println("mimetype : "+mimeType);

        response.setContentType(mimeType);

        /* "Content-Disposition : inline" will show viewable types [like images/text/pdf/anything viewable by browser] right on browser
            while others(zip e.g) will be directly downloaded [may provide save as popup, based on your browser setting.]*/
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));


        /* "Content-Disposition : attachment" will be directly download, may provide save as popup, based on your browser setting*/
        //response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));

        response.setContentLength((int)file.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

        //Copy bytes from source to destination(outputstream in this example), closes both streams.
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }

	private void addToIndex(String title, String filePath, String url, Concept[] concepts)
    {
        SolrInputDocument document = new SolrInputDocument();
        document.addField( "title", title );
        document.addField( "url", url );
        document.addField( "file", filePath );

        String tags = "";

        for(Concept concept: concepts)
        {
            String conceptStr = concept.getPrefLabel()+"-"+concept.getScore()+",";
            tags += conceptStr;
        }

        tags = tags.substring(0, tags.length() -1);

        document.addField("tags", tags);
        try {
            server.add(document);
            server.commit();
        } catch (SolrServerException e) {
            log.error(e.getClass().getName() + ": " + e.getMessage());
        } catch (IOException e) {
            log.error(e.getClass().getName() + ": " + e.getMessage());
        }
    }

	private Concept[] mauiExtractor(String text)
	{
		ArrayList<Concept> concepts = new ArrayList<>();
		String modelName = this.context.getRealPath("/WEB-INF/classes/output_model");

		String vocabularyName = this.context.getRealPath("/WEB-INF/classes/drp.rdf");

		String vocabularyFormat = "skos";

		int topicsPerDocument = 10;

		MauiWrapper mauiWrapper = null;

		try {
			// Use default stemmer, stopwords and language
			// MauiWrapper also can be initalized with a pre-loaded vocabulary
			// and a pre-loaded MauiFilter (model) objects
			mauiWrapper = new MauiWrapper(modelName, vocabularyName, "skos");

			// the last three items should match what was used in the wrapper constructor
			// i.e. null if the defaults were used
			mauiWrapper.setModelParameters(vocabularyName, new PorterStemmer(), null, null);

		} catch (Exception e) {
			log.error(e.getClass().getName() + ": " + e.getMessage());
		}
		ArrayList<Topic> keywords = new ArrayList<Topic>();

		try {
			keywords = mauiWrapper.extractTopicsFromText(text, topicsPerDocument);
			log.info("KEYWORDS: " + keywords.toString());
		}
		catch (MauiFilterException | NullPointerException e) {
			log.error(e.getClass().getName() + ": " + e.getMessage());
		}
		DecimalFormat df = new DecimalFormat("0.##");

		for (Topic keyword : keywords) {
			String probability = df.format(keyword.getProbability() * 100);
			concepts.add(new Concept(keyword.getTitle(), Integer.parseInt(probability)));
		}
		return (Concept[]) concepts.toArray();
	}

	private Concept[] poolPartyExtractor(MultipartFile file, String urlToExtract)
	{
		String projectId = "1DDFC367-E4BD-0001-E4F4-483115961E7F";
		String url = "https://cdmk.poolparty.biz/extractor/api/extract";

		if (urlToExtract != null && !urlToExtract.isEmpty())
			return poolPartyExtractFromURL(urlToExtract,projectId, url);
		else
			return poolPartyExtractFromFile(file, projectId, url);
	}

	private Concept[] poolPartyExtractFromURL(String url, String projectId, String extractorURL)
	{
		Concept[] concepts = null;

		HttpClient httpClient = HttpClientBuilder.create().build();

        try {
            URIBuilder builder = new URIBuilder(extractorURL);
            builder.addParameter("projectId",projectId);

            builder.addParameter("url",url);
            builder.addParameter("numberOfConcepts",Integer.toString(10));
            builder.addParameter("language","en");
            builder.addParameter("numberOfTerms",Integer.toString(10));


            HttpGet httpGet = new HttpGet(String.valueOf(builder.build().toURL()));

            String b64val;

            b64val = DatatypeConverter.printBase64Binary("apiuser:Msbm2016".getBytes("UTF-8"));


            if (b64val != null)
                httpGet.setHeader("Authorization", "Basic " + b64val + "=");

            HttpResponse httpResponse = httpClient.execute(httpGet);

            BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();

            System.out.println("Successfully retrieved concepts from url");

            JsonParser parser = new JsonParser();
            JsonObject conceptResult = (JsonObject) parser.parse(result.toString());

            JsonElement conceptData = conceptResult.get("concepts");

            Gson gson = new Gson();
            concepts = gson.fromJson(conceptData, Concept[].class);
        } catch (URISyntaxException | IOException e)
        {
            log.error(e.getClass().getName() + ": " + e.getMessage());
        }
        if (concepts == null)
            return new Concept[]{};
        return concepts;
	}

	private Concept[] poolPartyExtractFromFile(MultipartFile multipartFile, String projectId, String url)
	{
		Concept[] concepts = null;

		File file = new File("/cdmk/"+multipartFile.getOriginalFilename());

		try {
			InputStream inputStream = multipartFile.getInputStream();

			OutputStream outputStream = new FileOutputStream(file);

			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1)
			{
				outputStream.write(bytes, 0, read);
			}
		} catch (IOException e) {
			log.error(e.getClass().getName() + ": " + e.getMessage());
		}


		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(url);

		String b64val = null;
		try {
			b64val = DatatypeConverter.printBase64Binary("apiuser:Msbm2016".getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			log.error(e.getClass().getName() + ": " + e.getMessage());
		}

		if(b64val != null)
			httpPost.setHeader("Authorization","Basic "+b64val+"=");

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		FileBody fileBody = new FileBody(file);
		builder.addPart("file", fileBody);
		builder.addPart("projectId",new StringBody(projectId, ContentType.TEXT_PLAIN));
		builder.addPart("language",new StringBody("en", ContentType.TEXT_PLAIN));
		builder.addPart("numberOfConcepts",new StringBody(Integer.toString(10), ContentType.TEXT_PLAIN));
		builder.addPart("numberOfTerms",new StringBody(Integer.toString(10), ContentType.TEXT_PLAIN));
		try {
			httpPost.setEntity(builder.build());

			HttpResponse httpResponse = httpClient.execute(httpPost);

			BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			String line;
			StringBuilder result = new StringBuilder();
			while ((line = reader.readLine()) != null)
			{
				result.append(line);
			}
			reader.close();

			System.out.println("Successfully retrieved concepts from url");

			JsonParser parser = new JsonParser();
			JsonObject conceptResult = (JsonObject) parser.parse(result.toString());

			JsonElement conceptData = conceptResult.getAsJsonObject("document").get("concepts");

			Gson gson = new Gson();
			concepts = gson.fromJson(conceptData, Concept[].class);
		} catch (IOException e) {
			log.error(e.getClass().getName() + ": " + e.getMessage());
		}
		return concepts;
	}

    private Set<Item> searchForConcept(String[] conceptQueries)
    {
        Set<Item> resultSet = new TreeSet<>(new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                if (item1.equals(item2)) {
                    return 0;
                }
                return 1;
            }
        });
        List<Item> results = new ArrayList<>();

        try {

            String queryString = "";

            if(conceptQueries.length == 1)
            {
                if(!conceptQueries[0].contains("file"))
                    queryString = "\""+conceptQueries[0]+"\"";
                else
                    queryString = conceptQueries[0];
            }
            else if(conceptQueries.length >1) {
                queryString = "\""+conceptQueries[0]+"\"";
                for (int i =1; i<conceptQueries.length; i++) {
                    String conceptString = conceptQueries[i];
                    String conceptStringComponent = "OR \"" + conceptString + "\"";
                    queryString += conceptStringComponent;
                }
            }
            SolrQuery query = new SolrQuery();
            query.setQuery(queryString);

            QueryResponse rsp = server.query( query );

            ArrayList<Item> queryResult = (ArrayList<Item>) rsp.getBeans(Item.class);
            for(Item item: queryResult)
            {

                // under the premise that anything in the index with a file tag must have concepts in the tags field!
                if(item.tags == null)
                {
                    String filePath = item.getId();
                    Set<Item> unTaggedSearchItems = searchForConcept(new String[]{"file:(\""+filePath+"\")"});
                    if(unTaggedSearchItems.isEmpty())
                    {
                        item.file = new ArrayList<>();
                        item.fileName = getFileNameFromPath(filePath);
                        item.file.add(filePath);
                        results.add(item);
                        System.out.print(item);
                    }
                    else {
                        resultSet.addAll(unTaggedSearchItems);
                    }
                    continue;
                }

            	if(item.file != null && !item.file.isEmpty())
				{
					String filePath = item.file.get(0);
					item.fileName = getFileNameFromPath(filePath);
				}

                item.concepts = new ArrayList<>();
                String tags = item.tags.get(0);
                String[] conceptStrings = tags.split(",");

                ArrayList<Concept> concepts = new ArrayList<>();
                for(String conceptAndValueString: conceptStrings)
                {
                    String[] conceptAndValue = conceptAndValueString.split("-");
                    concepts.add(new Concept(conceptAndValue[0],Integer.parseInt(conceptAndValue[1])));
                }
                item.concepts.addAll(concepts);
                results.add(item);
            }

        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        resultSet.addAll(results);
        return resultSet;
    }

    private String getFileNameFromPath(String filePath)
    {
        String[] components = filePath.split("/");
        return components[components.length -1];
    }

    private void populateFilterConcepts(Set<Item> items)
    {
        filterConcepts.clear();
        if(items.size() >1) {
            for (Item item : items) {
                if (item.getConcepts() != null) {
                    for (Concept mConcept : item.getConcepts()) {
                        boolean add = true;
                        for (Concept nConcept : filterConcepts) {
                            if (mConcept.equals(nConcept)) {
                                add = false;
                                break;
                            }
                        }
                        if (add)
                            filterConcepts.add(mConcept);
                    }
                }
            }
        }
    }

    /**
     * Search results populated in the following order.
     * Results with the concept searched for ordered by the value of the strength first
     * Results that have concepts i.e have been tagged but showed up as a result of a full text search shown next
     * Results that came from full text search but don't have concepts are shown next
     * @param results
     */
    private void populateSearchResults(Set<Item> results)
    {
        searchResults.clear();

        List<Item> resultsList = new ArrayList<>();
        resultsList.addAll(results);

        // sort results by concept strength/existence/existence
        for (Item item : resultsList) {
            if (searchResults.isEmpty()) {
                searchResults.add(item);
            } else {
                if (item.getConcepts() == null) {
                    searchResults.add(item);
                } else {
                    int resultsSize = searchResults.size();
                    for (int j = 0; j < resultsSize; j++) {
                        Item mItem = searchResults.get(j);

                        Concept searchConceptObject = new Concept(searchConcept);
                        // null checks and full text result case checks
                        if (mItem.getConcepts() == null) {
                            searchResults.add(j, item);
                            break;
                        } else if (item.getConcepts().contains(searchConceptObject) && !mItem.getConcepts().contains(searchConceptObject)) {
                            searchResults.add(j, item);
                            break;
                        } else if (!item.getConcepts().contains(searchConceptObject) && !mItem.getConcepts().contains(searchConceptObject)) {
                            searchResults.add(j, item);
                            break;
                        } else if (!item.getConcepts().contains(searchConceptObject) && (mItem.getConcepts() == null || j == resultsSize - 1)) {
                            searchResults.add(j, item);
                            break;
                        } else if ((!item.getConcepts().contains(searchConceptObject) && mItem.getConcepts().contains(searchConceptObject))) {
                            continue;
                        }


                        int index = item.getConcepts().indexOf(searchConceptObject);
                        int mIndex = mItem.getConcepts().indexOf(searchConceptObject);

                        Concept concept = item.getConcepts().get(index);
                        Concept mConcept = mItem.getConcepts().get(mIndex);

                        if (concept.getScore() > mConcept.getScore()) {
                            searchResults.add(j, item);
                            break;
                        } else if (j == resultsSize - 1) {
                            searchResults.add(item);
                        }
                    }
                }
            }
        }

        // Remove duplicates
        for(int i = 0; i<searchResults.size(); i++)
        {
            for(int j = 0; j<searchResults.size(); j++)
            {
                if(i != j && searchResults.get(i).equals(searchResults.get(j)))
                    searchResults.remove(j);
            }
        }
    }

}
