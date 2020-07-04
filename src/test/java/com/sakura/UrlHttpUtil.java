package com.sakura;

import com.hqyg.ifs.SkuStoryQuantity;
import com.util.ListUtils;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by River on 2020/7/2
 */

public class UrlHttpUtil extends RealRequest {

    /**
     * get请求，可以传递参数，带请求头
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     */
    public static RealResponse urlHttpGet(String url, Map<String, String> paramsMap, Map<String, String> headerMap) {
        return new RealRequest().getData(getUrl(url, paramsMap), headerMap);

    }

    /**
     * get请求
     *
     * @param url：url
     */
    public static RealResponse urlHttpGet(String url) {
        return urlHttpGet(url, null, null);
    }

    /**
     * get请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     */
    public static RealResponse urlHttpGet(String url, Map<String, String> paramsMap) {
        return urlHttpGet(url, paramsMap, null);
    }


    /**
     * post请求
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param jsonStr：json格式字符串参数
     * @param headerMap：map集合，封装请求头键值对
     * @return RealResponse
     */
    public static RealResponse urlHttpPost(String url, Map<String, String> paramsMap, String jsonStr, Map<String, String> headerMap) {
        return new RealRequest().postData(url, getPostBody(paramsMap, jsonStr), getPostBodyType(paramsMap, jsonStr), headerMap);
    }

    /**
     * post请求
     *
     * @param url：url
     */
    public static RealResponse urlHttpPost(String url) {
        return urlHttpPost(url, null, null, null);
    }

    /**
     * post请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     */
    public static RealResponse urlHttpPost(String url, Map<String, String> paramsMap) {
        return urlHttpPost(url, paramsMap, null, null);
    }

    /**
     * post请求，键值对参数，带请求头
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     */
    public static RealResponse urlHttpPost(String url, Map<String, String> paramsMap, Map<String, String> headerMap) {
        return urlHttpPost(url, paramsMap, null, headerMap);
    }

    /**
     * post请求，键值对参数
     *
     * @param url：url
     * @param jsonStr：json格式字符串参数
     */
    public static RealResponse urlHttpPost(String url, String jsonStr) {
        return urlHttpPost(url, null, jsonStr, null);
    }

    /**
     * post请求，json参数，带请求头
     *
     * @param url：url
     * @param jsonStr：json格式字符串参数
     * @param headerMap：map集合，封装请求头键值对
     */
    public static RealResponse urlHttpPost(String url, String jsonStr, Map<String, String> headerMap) {
        return urlHttpPost(url, null, jsonStr, headerMap);
    }


    /**
     * 获取签名sign
     *
     * @param params    请求头参数map
     * @param appSecret 签名key
     * @return sign
     */
    public static String sign(Map<String, String> params, String appSecret) {

        Map<String, List<String>> signParams = new HashMap<>();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            signParams.put(entry.getKey(), Arrays.asList(entry.getValue()));
        }
        String paramStr = ListUtils.n(signParams.keySet()).order(key -> key).list(key -> {
            String values = ListUtils.n(signParams.get(key)).order(val -> val).join();
            return key + values;
        }).join();

        String signStr = null;
        if ("wetest".equals(appSecret)) {
            signStr = appSecret + paramStr;
        } else if (appSecret.contains("pms")) {
            signStr = paramStr + appSecret;
        }
        String sign = DigestUtils.md5Hex(signStr);
        return sign;
    }
}
