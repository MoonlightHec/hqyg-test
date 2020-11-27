package com.hqyg.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import java.util.*;

public class SignUtil {


    private static final String PMS_KEY = "pms-test-app-secret";
    private static final String WE_KEY = "wetest";
    private static final String PDM_KEY = "wetest";
    private static final Logger logger = Logger.getLogger(SignUtil.class);

    /**
     * 获取签名sign
     *
     * @param params 请求头参数map
     * @return sign
     */
    public static String sign(Map<String, String> params) {

        Map<String, List<String>> signParams = new HashMap<>();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String jsonStr;
            Object obj = entry.getValue();
            if (obj instanceof String) {
                jsonStr = entry.getValue();
            } else {
                jsonStr = String.valueOf(obj);
            }
            signParams.put(entry.getKey(), Collections.singletonList(jsonStr));
        }
        String paramStr = ListUtil.n(signParams.keySet()).order(key -> key).list(key -> {
            String values = ListUtil.n(signParams.get(key)).order(val -> val).join();
            return key + values;
        }).join();

        paramStr += "secret12345678";
        //logger.info(paramStr);
        String sign = DigestUtils.md5Hex(paramStr);
        //logger.info(sign);
        return sign;
    }

    public static String sign(String json) {

        Map map = JSON.parseObject(json);
        return sign(map);
    }

    public static void main(String[] args) {
        System.out.println(DigestUtils.md5Hex("777777777"));
    }

}
