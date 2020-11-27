package com.hqyg.plm;

import com.sakura.RealResponse;
import com.sakura.UrlHttpUtil;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class GetPDMSample {

    private static final String TOKEN = "D9D181499CD4446D89632F9037A86F1E";


    /**
     * 获取pdm样品详细信息
     */
    @Test
    public void getProviderCon() {
        String interfaceStr = "http://pdm.hqygou.com/interface/sample-generate/get-sample-by-sku";
        String requestBody = "{\"goods_sn\":\"206076401\",\"signature\":\"018f4bea4297618dda3ebabb39bc0f12\",\"timestamp\":1594030158}";


        RealResponse response = UrlHttpUtil.urlHttpPost(interfaceStr, requestBody);
        System.out.println(response.is2String(response.getInputStream()));
    }

    @Test
    public void getApm() {
        String interfaceStr = "http://api-plm.gw-ec.com/sample/base/add";
        String requestBody = "{\"typeId\":\"0\",\"warehouseId\":\"1\",\"sampleNumInfoList\":[{\"sampleNum\":\"470101001\",\"sku\":\"470101001\"," +
                "\"num\":1,\"result\":\"\"}],\"operateContent\":\"批量还样/还样\"}";

        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("PLM-TOKEN",TOKEN);

        RealResponse response = UrlHttpUtil.urlHttpPost(interfaceStr, requestBody, headerMap);
        System.out.println(response.is2String(response.getInputStream()));
    }

}
