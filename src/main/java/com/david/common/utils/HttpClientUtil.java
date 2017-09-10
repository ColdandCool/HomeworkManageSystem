package com.david.common.utils;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by david on 2017/9/10.
 */
public class HttpClientUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    private static CloseableHttpClient httpClient = HttpClients.createDefault();

    public static String doGet(String url){

        HttpGet request = new HttpGet(url);
        String result = null ;
        try {
            HttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result= EntityUtils.toString(response.getEntity(),"utf-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  result;
    }

    public static void main(String[] args) {
        String get = doGet("http://api.openweathermap.org/data/2.5/weather?id=2147714&appid=43aacfb6fa831b24200a39d2d4351449");
        System.out.println(get);
    }
}
