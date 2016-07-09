package com.cdmk.app;

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
public class AppTest 
    extends TestCase
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

            String b64val = DatatypeConverter.printBase64Binary("mona-superadmin:rashoB4o".getBytes("UTF-8"));
            connection.setRequestProperty("Authorization","Basic "+b64val+"=");

            responseCode = connection.getResponseCode();

        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(200, responseCode);
    }
}
