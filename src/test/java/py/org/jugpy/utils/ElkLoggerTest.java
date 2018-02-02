/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.org.jugpy.utils;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import org.elasticsearch.client.RestClient;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cbustamante
 */
public class ElkLoggerTest {

    public ElkLoggerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {

    }

    /**
     * Test of info method, of class ElkLogger.
     */
//    @Test
    public void testingMapInput() throws Exception {

        Map<String, String> mapa = new TreeMap();
        mapa.put("user", "cbm");
        mapa.put("postDate", "2013-01-30");
        mapa.put("message", "testing from mapa " + String.valueOf(new Date().getTime()));
        String location = "twitter";
        assertTrue("Error al registrar el log", new ElkLogger().info("twitter", "tweet", null, mapa));
        // TODO review the generated test code and remove the default call to fail.
    }
    
    public void testingJsonInput() throws Exception{
         String json = "{" + "\"user\":\"kimchy\"," + "\"postDate\":\"2013-01-30\","
                + "\"message\":\"trying out Elasticsearch from JAVA\"" + "}";
            assertTrue("Error al registrar el log", new ElkLogger().info("twitter", "tweet", null, json));
    }

}
