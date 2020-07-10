package com.hqyg.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignUtil {


    private static final String PMS_KEY = "pms-test-app-secret";
    private static final String WE_KEY = "wetest";
    private static final String PDM_KEY = "wetest";

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
            String jsonStr = null;
            Object obj = entry.getValue();
            if (obj instanceof Integer) {
                jsonStr = String.valueOf(obj);
            } else {
                jsonStr = entry.getValue();
            }
            signParams.put(entry.getKey(), Arrays.asList(jsonStr));
        }
        String paramStr = ListUtil.n(signParams.keySet()).order(key -> key).list(key -> {
            String values = ListUtil.n(signParams.get(key)).order(val -> val).join();
            return key + values;
        }).join();

        String signStr = null;
        if (PDM_KEY.equals(appSecret)) {
            signStr = paramStr + "secret12345678";
        } else if (PMS_KEY.equals(appSecret)) {
            signStr = paramStr + appSecret;
        }
        String sign = DigestUtils.md5Hex(signStr);
        return sign;
    }

    public static String sign(String json, String appSecret) {

        Map map = JSON.parseObject(json);
//        for (Object mapData : map.entrySet()) {
//            Map.Entry<String, String> entry = (Map.Entry<String, String>) mapData;
//        }
        return sign(map, appSecret);
    }

    public static void main(String[] args) {
        String json = "{\"colorChangeId\":1,\"logCate\":2,\"bizCode\":1,\"listType\":1}";
        System.out.println(sign(json, PDM_KEY));
    }

}
