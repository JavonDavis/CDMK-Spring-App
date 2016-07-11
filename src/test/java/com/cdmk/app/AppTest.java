package com.cdmk.app;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jdk.nashorn.internal.parser.JSONParser;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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

    public void testExtraction()
    {
        String SAMPLE_URL = "https://raw.githubusercontent.com/JA-VON/python-helpers-msbm/master/training/training.txt";

        URL sampleUrlObject = null;
        String sample = "";
        try {
            sampleUrlObject = new URL(SAMPLE_URL);

            HttpURLConnection conn = (HttpURLConnection) sampleUrlObject.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            sample = response.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        assertNotNull(sample);

        String projectId = "1DDFC367-E4BD-0001-E4F4-483115961E7F";
        String EXTRACTOR_URL = "https://cdmk.poolparty.biz/extractor/api/extract";

        URL urlObject = null;
        try {
            urlObject = new URL(EXTRACTOR_URL);

            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
            connection.setRequestMethod("POST");

            String b64val = DatatypeConverter.printBase64Binary("apiuser:Msbm2016".getBytes("UTF-8"));
            connection.setRequestProperty("Authorization","Basic "+b64val+"=");

            String parameters = "projectId="+projectId+"&text="+sample+"&language=en";

            connection.setDoOutput(true);
            DataOutputStream stream = new DataOutputStream((connection.getOutputStream()));
            stream.writeBytes(parameters);
            stream.flush();
            stream.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null)
            {
                response.append(line);
            }
            reader.close();

            System.out.println("Successfully retrieved concepts from url");

            JsonParser parser = new JsonParser();
            JsonObject result = (JsonObject) parser.parse(response.toString());

            JsonElement conceptData = result.get("concepts");

            Gson gson = new Gson();
            Concept[] concepts = gson.fromJson(conceptData, Concept[].class);
            assertTrue(concepts.length > 0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
