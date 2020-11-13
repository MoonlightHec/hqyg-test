package com.hqyg.plm;

import com.sakura.RealResponse;
import com.sakura.UrlHttpUtil;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class PatternLib {

    private static final String TOKEN = "46009C83F7384B07BD6B0F7AC101C4F2";


    /**
     * 新增零部件分类
     */
    @Test
    public void f1() {
        String interfaceStr = "http://plm.hqygou.com:8088/parts/category/add";
        String requestBody = "{\"id\":\"\",\"parentId\":\"0\",\"partsNameCn\":\"我\",\"partsNickName\":\"bd\",\"twoPartsNameCn\":\"1\"," +
                "\"isDelete\":\"1\"}";

        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("PLM-TOKEN", TOKEN);

        RealResponse response = UrlHttpUtil.urlHttpPost(interfaceStr, requestBody, headerMap);
        System.out.println(response.is2String(response.getInputStream()));
    }
}
