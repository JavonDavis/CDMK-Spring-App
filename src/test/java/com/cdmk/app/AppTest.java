package com.cdmk.app;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import javax.print.Doc;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase
{

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }


    private DocumentItem findHeader(String needle, String haystack)
    {

        // Recommended preliminaries for a DRP
        String headerRegex = DocumentItem.getSectionRegularExpression(needle);

        Pattern pattern = Pattern.compile(headerRegex);
        Matcher matcher = pattern.matcher(haystack);

        if (matcher.find()) {
            DocumentItem item = new DocumentItem(needle,matcher.start(),matcher.end(),matcher.group());
            System.out.println(item);
            return item;
        }
        return new DocumentItem(needle);
    }

    private ArrayList<DocumentItem> getMissingItems(List<DocumentItem> items){
        ArrayList<DocumentItem> missingItems = new ArrayList<>();

        for(DocumentItem item: items)
        {
            if(!item.isPresentInDocument()) {
                System.out.print("Not Present:"+item.getTag()+"\n");
                missingItems.add(item);
            }
        }

        return missingItems;
    }

    private ArrayList<DocumentItem> getMisplacedItems(List<DocumentItem> items){
        ArrayList<DocumentItem> misplacedItems = new ArrayList<>();

        ArrayList<String> tags = DocumentItem.getTags();

        for(int i = 0; i < tags.size() ; i ++)
        {
            String tag = tags.get(i);
            int itemIndex = getItemIndexByTag(tag, items);

            if(itemIndex > -1) {
                DocumentItem item = items.get(itemIndex);
                if (item.isPresentInDocument() && !inValidPosition(i, item, items,tags)) {
                    System.out.println("Misplaced:"+item.getTag());
                    misplacedItems.add(item);
                }
            }
        }

        return misplacedItems;
    }

    private int getItemIndexByTag(String tag, List<DocumentItem> items)
    {
        for(int i = 0; i< items.size(); i++)
        {
            DocumentItem item = items.get(i);
            if(item.getTag().equals(tag))
                return i;
        }
        return -1;
    }

    private boolean inValidPosition(int tagIndex, DocumentItem item, List<DocumentItem> items, List<String> tags)
    {
        for(int i = 0; i < tags.size(); i ++)
        {
            String tag = tags.get(i);
            int itemIndex = getItemIndexByTag(tag, items);

            if(itemIndex > -1) {
                DocumentItem itemToCheck = items.get(itemIndex);
                if(itemToCheck.isPresentInDocument()) {
                    if (i < tagIndex && item.getStartIndex() < itemToCheck.getEndIndex())
                        return false;
                    else if (i > tagIndex && item.getEndIndex() > itemToCheck.getStartIndex())
                        return false;
                }
            }
        }
        return true;
    }

    public void testDocumentStructure() throws IOException, RuntimeException, InterruptedException {
        String command = "pandoc \"structure.docx\" --from=docx --to=markdown  --output=\"demo.md\"";
        Process p = Runtime.getRuntime().exec(new String[]{"sh","-c",command});
        p.waitFor();

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(p.getInputStream()));

        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine())!= null) {
            stringBuilder.append(line).append("\n");
        }
        System.out.print(stringBuilder);
        assertTrue(new File("demo.md").exists());

        BufferedReader br = null;

        StringBuilder result = new StringBuilder();
        try {

            br = new BufferedReader(new FileReader("demo.md"));

            while ((line = br.readLine()) != null) {
                result.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        String resultString = result.toString();

        ArrayList<String> headerExpressions = DocumentItem.getTags();

        ArrayList<DocumentItem> documentItems = new ArrayList<>();
        for(String header: headerExpressions)
        {
            documentItems.add(findHeader(header, resultString));
        }

        assertTrue(getMissingItems(documentItems).size() == 0);
        assertTrue(getMisplacedItems(documentItems).size() == 0);
        //assertTrue(new File("demo.md").delete());
    }

//    public void testPoolPartyAuth()
//    {
//        int responseCode = -1;
//        String POOLPARTY_URL = "https://cdmk.poolparty.biz/PoolParty/api/projects";
//
//        URL urlObject = null;
//        try {
//            urlObject = new URL(POOLPARTY_URL);
//
//            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
//            connection.setRequestMethod("GET");
//
//            String b64val = DatatypeConverter.printBase64Binary("apiuser:Msbm2016".getBytes("UTF-8"));
//            connection.setRequestProperty("Authorization","Basic "+b64val+"=");
//
//            responseCode = connection.getResponseCode();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        assertEquals(200, responseCode);
//    }
//
//    public void testTextExtraction()
//    {
//        String SAMPLE_URL = "https://raw.githubusercontent.com/JA-VON/python-helpers-msbm/master/training/training.txt";
//        String sample = "";
//        HttpClient client = HttpClientBuilder.create().build();
//        HttpGet request = new HttpGet(SAMPLE_URL);
//
//        // add request header
//        HttpResponse response = null;
//        try {
//            response = client.execute(request);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if (response != null) {
//            System.out.println("Response Code : "
//                    + response.getStatusLine().getStatusCode());
//
//
//            BufferedReader rd = null;
//            try {
//                rd = new BufferedReader(
//                        new InputStreamReader(response.getEntity().getContent()));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//            StringBuilder result = new StringBuilder();
//            String line = "";
//            if (rd != null) {
//                try {
//                    while ((line = rd.readLine()) != null) {
//                        result.append(line);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            sample = result.toString();
//        }
//        assertNotNull(sample);
//
//        String projectId = "1DDFC367-E4BD-0001-E4F4-483115961E7F";
//        String EXTRACTOR_URL = "https://cdmk.poolparty.biz/extractor/api/extract";
//        Concept[] concepts = null;
//
//        HttpClient httpClient = HttpClientBuilder.create().build();
//        HttpPost httpPost = new HttpPost(EXTRACTOR_URL);
//
//        List<NameValuePair> params = new ArrayList<>();
//        params.add(new BasicNameValuePair("text",sample));
//        params.add(new BasicNameValuePair("projectId",projectId));
//        params.add(new BasicNameValuePair("language","en"));
//        params.add(new BasicNameValuePair("numberOfConcepts",Integer.toString(10)));
//        params.add(new BasicNameValuePair("numberOfTerms",Integer.toString(10)));
//
//        String b64val = null;
//        try {
//            b64val = DatatypeConverter.printBase64Binary("apiuser:Msbm2016".getBytes("UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        assertNotNull(b64val);
//        httpPost.setHeader("Authorization","Basic "+b64val+"=");
//
//        try {
//            httpPost.setEntity(new UrlEncodedFormEntity(params));
//
//            HttpResponse httpResponse = httpClient.execute(httpPost);
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
//            String line;
//            StringBuilder result = new StringBuilder();
//            while ((line = reader.readLine()) != null)
//            {
//                result.append(line);
//            }
//            reader.close();
//
//            System.out.println("Successfully retrieved concepts from url");
//
//            JsonParser parser = new JsonParser();
//            JsonObject conceptResult = (JsonObject) parser.parse(result.toString());
//
//            JsonElement conceptData = conceptResult.get("concepts");
//
//            Gson gson = new Gson();
//            concepts = gson.fromJson(conceptData, Concept[].class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        assertNotNull(concepts);
//        assertTrue(concepts.length > 0 && concepts.length <11);
//    }
//
//    public void testFileExtraction()
//    {
//        Concept[] concepts = null;
//        File sampleFile = new File("sample.docx");
//
//        String projectId = "1DDFC367-E4BD-0001-E4F4-483115961E7F";
//        String EXTRACTOR_URL = "https://cdmk.poolparty.biz/extractor/api/extract";
//
//        HttpClient httpClient = HttpClientBuilder.create().build();
//        HttpPost httpPost = new HttpPost(EXTRACTOR_URL);
//
//        String b64val = null;
//        try {
//            b64val = DatatypeConverter.printBase64Binary("apiuser:Msbm2016".getBytes("UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        assertNotNull(b64val);
//        httpPost.setHeader("Authorization","Basic "+b64val+"=");
//
//        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//        FileBody fileBody = new FileBody(sampleFile);
//        builder.addPart("file", fileBody);
//        builder.addPart("projectId",new StringBody(projectId, ContentType.TEXT_PLAIN));
//        builder.addPart("language",new StringBody("en", ContentType.TEXT_PLAIN));
//        builder.addPart("numberOfConcepts",new StringBody(Integer.toString(10), ContentType.TEXT_PLAIN));
//        builder.addPart("numberOfTerms",new StringBody(Integer.toString(10), ContentType.TEXT_PLAIN));
//        try {
//            httpPost.setEntity(builder.build());
//
//            HttpResponse httpResponse = httpClient.execute(httpPost);
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
//            String line;
//            StringBuilder result = new StringBuilder();
//            while ((line = reader.readLine()) != null)
//            {
//                result.append(line);
//            }
//            reader.close();
//
//            System.out.println("Successfully retrieved concepts from url");
//
//            JsonParser parser = new JsonParser();
//            JsonObject conceptResult = (JsonObject) parser.parse(result.toString());
//
//            JsonElement conceptData = conceptResult.getAsJsonObject("document").get("concepts");
//
//            Gson gson = new Gson();
//            concepts = gson.fromJson(conceptData, Concept[].class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        assertNotNull(concepts);
//        assertTrue(concepts.length > 0 && concepts.length <11);
//    }
}
