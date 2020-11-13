package com.hqyg.plm;

import com.hqyg.util.SignUtil;
import com.sakura.RealResponse;
import com.sakura.UrlHttpUtil;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class PatosonTest {

    String PTS_KEY = "patozontest";

    @Test
    public void postPattern() {
        String interfaceStr = "http://plm.hqygou.com:8088/api/change/recommend/info";
        String requestBody = "{\"recommendInfoList\":[{\"productMd5Code\":\"e1a965214158edaee9c4af63996d95c8\",\"productCode\":\"475072702\"," +
                "\"productName\":\"帕拓逊第一个产品\",\"ownerSite\":\"\",\"goodsUrl\":\"http://pdm.hqygou" +
                ".com/product/product/view-detail-api?token=eyJnb29kc19zbiI6IjIwNjExMDYxMSIsImtleSI6IjU2ZjhjN2IyMDZiYTUwYzA1NTRlZjExYmNhMTFiNjYxIn0" +
                "%3D\",\"goodsImgUrl\":\"http://pdm.hqygou.com/uploads/PLM/2020/11/thumb-img/1605146404014081997.jpg\",\"cateLabel\":\"\"," +
                "\"ownerPage\":\"\",\"isNewTop\":\"1\",\"firstTopTime\":\"\",\"currencyCode\":\"\",\"salesPrice\":\"\",\"score\":\"99.87\"," +
                "\"commentsTimes\":\"\",\"prePageRank\":\"\",\"curPageRank\":\"\",\"pageIncreaseTimes\":\"\",\"pageGoodsNum\":\"\"," +
                "\"collectTime\":\"\",\"recommendUser\":\"\",\"serialVersionUID\":0}],\"serialVersionUID\":0}";

        Map<String, String> headerMap = new HashMap<>();
        String timeStamp = String.valueOf(System.currentTimeMillis());

        headerMap.put("timestamp", timeStamp);
        headerMap.put("apiVersion", "12");
        headerMap.put("appKey", PTS_KEY);
        headerMap.put("uniqueId", "777777");
        headerMap.put("sign", SignUtil.sign(headerMap));

        //String json = "{\"timestamp\":\"" + timeStamp + "\",\"apiVersion\":\"12\",\"appKey\":\"patozontest\",\"uniqueId\":\"777777\"}";
        //headerMap.put("sign", SignUtil.sign(json));

        RealResponse response = UrlHttpUtil.urlHttpPost(interfaceStr, requestBody, headerMap);
        System.out.println(response.is2String(response.getInputStream()));
    }
}
