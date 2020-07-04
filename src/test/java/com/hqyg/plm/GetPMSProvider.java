package com.hqyg.plm;

import com.sakura.RealResponse;
import com.sakura.UrlHttpUtil;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class GetPMSProvider {

    private static final String TOKEN = "789B39D89604450CAAA48B126991DFB7";
    private static final String PMS_SECRET = "pms-test-app-secret";
    private static final String WE_SECRET = "wetest";

    @Test
    public void getProviderCon() {
        String interfaceStr = "http://plm.hqygou.com:8088/sample/develop/pms/suppliers/query";
        String requestBody = "{\"providerName\": \"锴慧通讯\"}";

        Map<String, String> headerMap = new HashMap<>();
        String timeStamp = String.valueOf(System.currentTimeMillis());

        headerMap.put("PLM-TOKEN", TOKEN);
        headerMap.put("timeStamp", timeStamp);
        headerMap.put("sign", UrlHttpUtil.sign(headerMap, PMS_SECRET));

        RealResponse response = UrlHttpUtil.urlHttpPost(interfaceStr, requestBody, headerMap);
        System.out.println(response.is2String(response.getInputStream()));
    }

    @Test
    public void getWeBorrowSample() {
        String interfaceStr = "http://plm.hqygou.com:8088/api/we/borrowsample/update";
        String requestBody = "{\"operateDate\":\"1585118896\",\"operateStatus\":\"6\",\"operateUser\":25121,\"plmSerialNumber\":725,\"remark\":\"we111\"}";

        Map<String, String> headerMap = new HashMap<>();
        String timeStamp = String.valueOf(System.currentTimeMillis());

        headerMap.put("apiVersion", "12");
        headerMap.put("appkey", "wetest");
        headerMap.put("Content-Type", "application/json");
        headerMap.put("timeStamp", "1585118858");
        headerMap.put("uniqueId", "77777");
        headerMap.put("sign", UrlHttpUtil.sign(headerMap, WE_SECRET));

        RealResponse response = UrlHttpUtil.urlHttpPost(interfaceStr, requestBody, headerMap);
        System.out.println(response.is2String(response.getInputStream()));
    }

}
