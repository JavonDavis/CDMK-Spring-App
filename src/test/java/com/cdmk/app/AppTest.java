package com.cdmk.app;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.Excluder;
import jdk.nashorn.internal.parser.JSONParser;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public void testPoolPartyAuth()
    {
        int responseCode = -1;
        String POOLPARTY_URL = "https://cdmk.poolparty.biz/PoolParty/api/projects";

        URL urlObject = null;
        try {
            urlObject = new URL(POOLPARTY_URL);

            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
            connection.setRequestMethod("GET");

            String b64val = DatatypeConverter.printBase64Binary("apiuser:Msbm2016".getBytes("UTF-8"));
            connection.setRequestProperty("Authorization","Basic "+b64val+"=");

            responseCode = connection.getResponseCode();

        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(200, responseCode);
    }

    public void testTextExtraction()
    {
        String SAMPLE_URL = "https://raw.githubusercontent.com/JA-VON/python-helpers-msbm/master/training/training.txt";
        String sample = "";
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(SAMPLE_URL);

        // add request header
        HttpResponse response = null;
        try {
            response = client.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response != null) {
            System.out.println("Response Code : "
                    + response.getStatusLine().getStatusCode());


            BufferedReader rd = null;
            try {
                rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));
            } catch (IOException e) {
                e.printStackTrace();
            }


            StringBuilder result = new StringBuilder();
            String line = "";
            if (rd != null) {
                try {
                    while ((line = rd.readLine()) != null) {
                        result.append(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            sample = result.toString();
        }
        assertNotNull(sample);

        String projectId = "1DDFC367-E4BD-0001-E4F4-483115961E7F";
        String EXTRACTOR_URL = "https://cdmk.poolparty.biz/extractor/api/extract";
        Concept[] concepts = null;

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(EXTRACTOR_URL);

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("text",sample));
        params.add(new BasicNameValuePair("projectId",projectId));
        params.add(new BasicNameValuePair("language","en"));
        params.add(new BasicNameValuePair("numberOfConcepts",Integer.toString(10)));
        params.add(new BasicNameValuePair("numberOfTerms",Integer.toString(10)));

        String b64val = null;
        try {
            b64val = DatatypeConverter.printBase64Binary("apiuser:Msbm2016".getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        assertNotNull(b64val);
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertNotNull(concepts);
        assertTrue(concepts.length > 0 && concepts.length <11);
    }

    public void testFileExtraction()
    {
        Concept[] concepts = null;
        File sampleFile = new File("sample.docx");

        String projectId = "1DDFC367-E4BD-0001-E4F4-483115961E7F";
        String EXTRACTOR_URL = "https://cdmk.poolparty.biz/extractor/api/extract";

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(EXTRACTOR_URL);

        String b64val = null;
        try {
            b64val = DatatypeConverter.printBase64Binary("apiuser:Msbm2016".getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        assertNotNull(b64val);
        httpPost.setHeader("Authorization","Basic "+b64val+"=");

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        FileBody fileBody = new FileBody(sampleFile);
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
            e.printStackTrace();
        }
        assertNotNull(concepts);
        assertTrue(concepts.length > 0 && concepts.length <11);
    }
}
