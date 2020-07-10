package com.hqyg.plm;

import com.sakura.RealResponse;
import com.sakura.UrlHttpUtil;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class GetPDMSample {

    private static final String TOKEN = "6EDC80EB811545B79F1E6F1F138AE704";


    @Test
    public void getProviderCon() {
        String interfaceStr = "http://pdm.hqygou.com/interface/sample-generate/get-sample-by-sku";
        String requestBody = "{\"goods_sn\":\"206076401\",\"signature\":\"018f4bea4297618dda3ebabb39bc0f12\",\"timestamp\":1594030158}";

        Map<String, String> headerMap = new HashMap<>();

        RealResponse response = UrlHttpUtil.urlHttpPost(interfaceStr, requestBody, headerMap);
        System.out.println(response.is2String(response.getInputStream()));
    }

}
