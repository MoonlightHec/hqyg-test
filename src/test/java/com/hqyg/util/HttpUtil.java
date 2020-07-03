package com.hqyg.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

/**
 * 改写HttpUtil2，将传入参数由第三方的NameValuePair类型改为Java自带的Map
 *
 * @author Administrator<br>
 */
public class HttpUtil {

    /**
     * post请求
     *
     * @param url           url
     * @param parametersMap map集合，封装键值对参数
     * @return response
     */
    public static CloseableHttpResponse post(String url, Map<Object, String> parametersMap) {

        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient client;
        CloseableHttpResponse response = null;
        Set<Object> mapKeySet = parametersMap.keySet();
        List<NameValuePair> parametersList = new ArrayList<>();
        for (Object key : mapKeySet) {
            NameValuePair pair = new BasicNameValuePair((String) key, parametersMap.get(key));
            parametersList.add(pair);
        }
        NameValuePair pair = new BasicNameValuePair("Content-Type", "application/json");
        parametersList.add(pair);

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(parametersList));
            client = HttpClients.createDefault();
            response = client.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * get请求
     *
     * @param url       url
     * @param parameter 参数
     * @return response
     */
    public static CloseableHttpResponse get(String url, String parameter) {
        url += parameter;
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static CloseableHttpResponse getPlus(String url, Map<Object, String> parametersMap) {
        Set<Object> mapKeySet = parametersMap.keySet();
        List<NameValuePair> parametersList = new ArrayList<>();
        for (Object key : mapKeySet) {
            NameValuePair pair = new BasicNameValuePair((String) key, parametersMap.get(key));
            parametersList.add(pair);
        }

        String parametersStr = URLEncodedUtils.format(parametersList, "utf-8");
        url += "?" + parametersStr;

        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder("moonlight");
        stringBuilder.append("hec");
        String result = stringBuilder.toString();
        System.out.println(result);
    }
}
