package com.hqyg.plm;

import com.hqyg.util.SignUtil;
import com.sakura.RealResponse;
import com.sakura.UrlHttpUtil;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class GetPMSProvider {

    private static final String TOKEN = "789B39D89604450CAAA48B126991DFB7";
    private static final String PMS_KEY = "pms-test-app-secret";
    private static final String WE_KEY = "wetest";
    private static final String PDM_KEY = "wetest";

    @Test
    public void getProviderCon() {
        String interfaceStr = "http://plm.hqygou.com:8088/sample/develop/pms/suppliers/query";
        String requestBody = "{\"providerName\": \"锴慧通讯\"}";

        Map<String, String> headerMap = new HashMap<>();
        String timeStamp = String.valueOf(System.currentTimeMillis());

        headerMap.put("PLM-TOKEN", TOKEN);
        headerMap.put("timeStamp", timeStamp);
        headerMap.put("sign", SignUtil.sign(headerMap, PMS_KEY));

        RealResponse response = UrlHttpUtil.urlHttpPost(interfaceStr, requestBody, headerMap);
        System.out.println(response.is2String(response.getInputStream()));
    }

    @Test
    public void getWeBorrowSample() {
        String interfaceStr = "http://plm.hqygou.com:8088/api/we/borrowsample/update";
        String requestBody = "{\"operateDate\":\"1585118896\",\"operateStatus\":\"6\",\"operateUser\":25121,\"plmSerialNumber\":725," +
                "\"remark\":\"we111\"}";

        Map<String, String> headerMap = new HashMap<>();
        String timestamp = String.valueOf(System.currentTimeMillis());

        headerMap.put("apiVersion", "12");
        headerMap.put("appkey", "wetest");
        headerMap.put("Content-Type", "application/json");
        headerMap.put("timestamp", "1585118858");
        headerMap.put("uniqueId", "77777");
        headerMap.put("sign", SignUtil.sign(headerMap, WE_KEY));

        RealResponse response = UrlHttpUtil.urlHttpPost(interfaceStr, requestBody, headerMap);
        System.out.println(response.is2String(response.getInputStream()));
    }

    @Test
    public void getSignWE() {
        Map<String, String> headerMap = new HashMap<>();

        headerMap.put("apiVersion", "12");
        headerMap.put("appkey", "wetest");
        headerMap.put("operateDate", "1585118896");
        headerMap.put("operateStatus", "6");
        headerMap.put("remark", "we");
        headerMap.put("operateUser", "25121");
        headerMap.put("plmSerialNumber", "WPSQ20200300028");
        //headerMap.put("Content-Type", "application/json");
        headerMap.put("timestamp", "1585118858");
        headerMap.put("uniqueId", "77777");
        headerMap.put("sign", SignUtil.sign(headerMap, WE_KEY));
    }


}
