package com.hqyg.plm;

import com.hqyg.util.SignUtil;
import com.sakura.RealResponse;
import com.sakura.UrlHttpUtil;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class GetPMSProvider {

    private static final String TOKEN = "ADAF9141892648D484A1F5400AE18B93";
    private static final String PMS_KEY = "pms-test-app-secret";
    private static final String WE_KEY = "wetest";
    private static final String PDM_KEY = "pdmtest";


    /**
     * 获取pdm所有供应商
     */
    @Test
    public void getProviderCon() {
        String interfaceStr = "http://plm.hqygou.com:8088/sample/develop/pms/suppliers/query";
        String requestBody = "{\"providerName\": \"锴慧通讯\"}";

        Map<String, String> headerMap = new HashMap<>();
        String timeStamp = String.valueOf(System.currentTimeMillis());

        headerMap.put("PLM-TOKEN", TOKEN);
        headerMap.put("timeStamp", timeStamp);
        headerMap.put("sign", SignUtil.sign(headerMap));

        RealResponse response = UrlHttpUtil.urlHttpPost(interfaceStr, requestBody, headerMap);
        System.out.println(response.is2String(response.getInputStream()));
    }


    /**
     * 获取pms有效供应商个数
     */
    @Test
    public void getEffectProvider(){
        String interfaceStr = "http://10.60.46.88:27000/api/pms/provider/skuByPrefix";
        String requestBody = "{\"prefix\":\"1666182\"}";

        Map<String, String> headerMap = new HashMap<>();
        String timeStamp = String.valueOf(System.currentTimeMillis());

        headerMap.put("timestamp", timeStamp);
        headerMap.put("apiVersion", "12");
        headerMap.put("appKey", PDM_KEY);
        headerMap.put("uniqueId", "77777");
        headerMap.put("sign", SignUtil.sign(headerMap));

        RealResponse response = UrlHttpUtil.urlHttpPost(interfaceStr, requestBody, headerMap);
        System.out.println(response.is2String(response.getInputStream()));
    }



    /**
     * we推送借样结果
     * sign方式还不清楚
     */
    @Test
    public void getWeBorrowSample() {
        String interfaceStr = "http://plm.hqygou.com:8088/api/we/borrowsample/update";
        String requestBody = "{\"operateDate\":\"1585118896\",\"operateStatus\":\"6\",\"operateUser\":25121,\"plmSerialNumber\":725," +
                "\"remark\":\"we111\"}";

        Map<String, String> headerMap = new HashMap<>();
        String timestamp = String.valueOf(System.currentTimeMillis());

        headerMap.put("apiVersion", "12");
        headerMap.put("appkey", "wetest");
        headerMap.put("timestamp", "1585118858");
        headerMap.put("uniqueId", "77777");
        headerMap.put("sign", SignUtil.sign(headerMap));

        RealResponse response = UrlHttpUtil.urlHttpPost(interfaceStr, requestBody, headerMap);
        System.out.println(response.is2String(response.getInputStream()));
    }

}
