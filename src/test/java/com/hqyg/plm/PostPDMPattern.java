package com.hqyg.plm;

import com.hqyg.util.SignUtil;
import com.sakura.RealResponse;
import com.sakura.UrlHttpUtil;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class PostPDMPattern {

    String PDM_KEY = "pdmtest";

    @Test
    public void postPattern() {
        String interfaceStr = "http://plm.hqygou.com:8088/api/pdm/pattern/query";
        String requestBody = "{\"patternCode\":\"DSWLYA213001\"}";

        Map<String, String> headerMap = new HashMap<>();
        String timeStamp = String.valueOf(System.currentTimeMillis());

        headerMap.put("timestamp", timeStamp);
        headerMap.put("apiVersion", "12");
        headerMap.put("appKey", PDM_KEY);
        headerMap.put("uniqueId", "777777");
        headerMap.put("sign", SignUtil.sign(headerMap));

        RealResponse response = UrlHttpUtil.urlHttpPost(interfaceStr, requestBody, headerMap);
        System.out.println(response.is2String(response.getInputStream()));
    }
}
