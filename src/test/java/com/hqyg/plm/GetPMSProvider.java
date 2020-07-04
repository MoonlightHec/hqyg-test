package com.hqyg.plm;

import com.sakura.RealResponse;
import com.sakura.UrlHttpUtil;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class GetPMSProvider {

    private static final String TOKEN = "789B39D89604450CAAA48B126991DFB7";

    @Test
    public void getProviderCon() {
        String interfaceStr = "http://plm.hqygou.com:8088/sample/develop/pms/suppliers/query";
        String requestBody = "{\"providerName\": \"锴慧通讯\"}";
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("PLM-TOKEN", TOKEN);
        RealResponse response = UrlHttpUtil.urlHttpPost(interfaceStr, requestBody, headerMap);
        System.out.println(response.is2String(response.getInputStream()));
    }

}
