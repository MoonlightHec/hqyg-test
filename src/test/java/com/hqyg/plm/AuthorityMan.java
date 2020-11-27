package com.hqyg.plm;

import com.sakura.RealResponse;
import com.sakura.UrlHttpUtil;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class AuthorityMan {
    private static final String TOKEN = "8F3C754F367449DDAAD08F6A8F0355E1";

    @Test
    private void meauQurry() {
        String interfaceStr = "http://plm.hqygou.com:8088/menu/query";
        String requestBody = "{\"buttonCode\":\"\",\"menuName\":\"\",\"menuType\":\"\",\"pageTableName\":\"\",\"parentId\":\"\",\"status\":\"\"," +
                "\"url\":\"\"}";

        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("PLM-TOKEN", TOKEN);

        RealResponse response = UrlHttpUtil.urlHttpPost(interfaceStr, requestBody, headerMap);
        System.out.println(response.is2String(response.getInputStream()));
    }
}
