/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.org.jugpy.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;


/***
 * ElasticSearch Logger 
 * @author cbustamante
 */
public class ElkLogger {

    /**
     * @Description: connect Establece la conexión con ElasticSearch
     * @return
     */
    public RestClient connect() {
        try {

            RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200, "http"));
            builder.setFailureListener(new RestClient.FailureListener() {
                @Override
                public void onFailure(HttpHost host) {
                    System.out.println("Connection Failure! " + host);
                }
            });
            return builder.build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /***
     *  Registra mensaje en ElasticSearch
     * @param index
     * @param type
     * @param id
     * @param params
     * @throws IOException 
     */
    public boolean info(String index, String type, String id, Map<String, String> params) throws IOException {
        try (RestClient client = connect()) {
           String json = convertMapToJson(params);
           Map<String, String> params2 = Collections.emptyMap();
            System.out.println("JsonRequest:" + json);
            HttpEntity entity = new NStringEntity(json, ContentType.APPLICATION_JSON);
            Response response = client.performRequest("POST",
                    (id != null)
                            ? String.format("/%s/%s/%s", index, type, id)
                            : String.format("/%s/%s", index, type),
                    params2, entity);
            System.out.println("Respuesta: " + response);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
            
        }

    }

    /***
     * Registra mensaje en ElasticSearch 
     * @param index
     * @param type
     * @param id
     * @param json
     * @throws IOException 
     */
    public boolean info(String index, String  type, String id, String json) throws IOException {
        try (RestClient client = connect()) {

            Map<String, String> params = Collections.emptyMap();
            System.out.println("JsonRequest:" + json);
            HttpEntity entity = new NStringEntity(json, ContentType.APPLICATION_JSON);
            Response response = client.performRequest("POST",
                    (id != null)
                            ? String.format("/%s/%s/%s", index, type, id)
                            : String.format("/%s/%s", index, type),
                    params, entity);
            System.out.println("Respuesta: " + response);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    //--------------------------------------------------------
    /*
     * @Description: Method to convert Map to JSON String
     * @param: map Map<String, String> 
     * @return: json String
     */
    private String convertMapToJson(Map<String, String> map) {
        Gson gson = new Gson();
        String json = gson.toJson(map);
        return json;
    }

    /*
     * @Description: Method to convert JSON String to Map
     * @param: json String 
     * @return: map Map<String, String> 
     */
    private Map<String, String> revertJsonToMap(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> map = gson.fromJson(json, type);
        return map;
    }

    /*
     * @Description: Method to print elements in the Map
     * @param: map Map<String, String> 
     * @return: void 
     */
    private void printMap(Map<String, String> map) {
        for (String key : map.keySet()) {
            System.out.println("map.get(\"" + key + "\") = " + map.get(key));
        }
    }

    /*
     * @Description: Method to print the JSON String
     * @param: json String 
     * @return: void 
     */
    public static void printJson(String json) {
        System.out.println("json = " + json);
    }

}
