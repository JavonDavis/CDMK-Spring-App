package com.cdmk.app;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.text.DecimalFormat;

import java.net.MalformedURLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.MalformedJsonException;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.core.io.ClassPathResource;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.entopix.maui.filters.MauiFilter.MauiFilterException;
import com.entopix.maui.main.MauiModelBuilder;
import com.entopix.maui.main.MauiTopicExtractor;
import com.entopix.maui.main.MauiWrapper;
import com.entopix.maui.stemmers.PorterStemmer;
import com.entopix.maui.util.Topic;

@Controller
public class CdmkController implements ServletContextAware {

	protected ServletContext context;
   // private static String SOLR_URL = "http://localhost:8983/solr/cdmk-test";
    private static String SOLR_URL = "http://cdmk-caribbean.net:8983/solr/cdmk";

	private Logger log = LoggerFactory.getLogger(this.getClass().getName());

	public void setServletContext(ServletContext servletContext) {
		this.context = servletContext;
	}

	@RequestMapping("/home")
	public String home(HttpServletRequest request) {
		return "home";
	}

	@RequestMapping(value = "/share", method = RequestMethod.GET)
	public String shareForm(HttpServletRequest request) {
		return "share";
	}

	@RequestMapping(value = "/api", method = RequestMethod.GET)
    public String apiPage(HttpServletRequest request) {
        return "api";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView searchPage(@RequestParam(value="text", required=false) String text,
                             HttpServletRequest request,
                             HttpServletResponse response) {
        List<Item> results = new ArrayList<>();
        try {
            SolrServer server = new CommonsHttpSolrServer( SOLR_URL );

            SolrQuery query = new SolrQuery();
            query.setQuery(text);

            QueryResponse rsp = server.query( query );

            ArrayList<Item> queryResult = (ArrayList<Item>) rsp.getBeans(Item.class);
            for(Item item: queryResult)
            {
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

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }

        Set<Item> resultSet = new TreeSet<>(new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                if (item1.equals(item2)) {
                    return 0;
                }
                return 1;
            }
        });
        resultSet.addAll(results);
        request.setAttribute("items", resultSet);
        return new ModelAndView("results");
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(HttpServletRequest request) {
        return "search";
    }

	@RequestMapping(value = "/share", method = RequestMethod.POST) 
	public ModelAndView share(
			@RequestParam(value="text", required=false) String text,
			@RequestParam(value="file", required = false) MultipartFile file,
			@RequestParam(value="url", required = false) String url,
			HttpServletRequest request,
			HttpServletResponse response
		) {

		//request.setAttribute("concepts", mauiExtractor(text));
		request.setAttribute("concepts", poolPartyExtractor(text.trim(), file, url));
		return new ModelAndView("extraction");
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

	private Concept[] poolPartyExtractor(String text, MultipartFile file, String urlToExtract)
	{
		String projectId = "1DDFC367-E4BD-0001-E4F4-483115961E7F";
		String url = "https://cdmk.poolparty.biz/extractor/api/extract";

		if(text != null && !text.isEmpty())
			return poolPartyExtractFromText(text,projectId, url, false);
		else if (urlToExtract != null && !urlToExtract.isEmpty())
			return poolPartyExtractFromText(urlToExtract,projectId, url, true);
		else
			return poolPartyExtractFromFile(file, projectId, url);
	}

	private Concept[] poolPartyExtractFromText(String text, String projectId, String url, boolean isURL)
	{
		Concept[] concepts = null;

		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(url);

		List<NameValuePair> params = new ArrayList<>();
		if(isURL) {
			params.add(new BasicNameValuePair("url",text));
		} else {
			params.add(new BasicNameValuePair("text",text));
		}

		params.add(new BasicNameValuePair("projectId",projectId));
		params.add(new BasicNameValuePair("language","en"));
		params.add(new BasicNameValuePair("numberOfConcepts",Integer.toString(10)));
		params.add(new BasicNameValuePair("numberOfTerms",Integer.toString(10)));

		String b64val = null;
		try {
			b64val = DatatypeConverter.printBase64Binary("apiuser:Msbm2016".getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			log.error(e.getClass().getName() + ": " + e.getMessage());
		}

		if(b64val != null)
			httpPost.setHeader("Authorization","Basic "+b64val+"=");

		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params));

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

			JsonElement conceptData = conceptResult.get("concepts");

			Gson gson = new Gson();
			concepts = gson.fromJson(conceptData, Concept[].class);
		} catch (MalformedJsonException e)
        {
            log.error(e.getClass().getName() + ": " + e.getMessage());
        } catch (IOException e) {
			log.error(e.getClass().getName() + ": " + e.getMessage());
		}
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

}
