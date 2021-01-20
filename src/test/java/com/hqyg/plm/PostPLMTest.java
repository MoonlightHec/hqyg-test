package com.hqyg.plm;

import com.sakura.RealResponse;
import com.sakura.UrlHttpUtil;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * PLM临时测试
 */
public class PostPLMTest {


    private static final String TOKEN = "53DD257A220348EDAE35355C42E533CC";

    @Test
    private void meauQurry() {
        String interfaceStr = "http://plm.hqygou.com:8088/proofing/query";
        String requestBody = "{\"limit\":20,\"offset\":1,\"condition\":{\"proofingSn\":\"\",\"sku\":\"\",\"styleName\":\"\",\"designerList\":[]," +
                "\"applyBeginDate\":\"2021-01-01\",\"applyEndDate\":\"2021-01-30\",\"proofingCreateUserList\":[],\"proofingVersionList\":[]," +
                "\"currentStageList\":[],\"currentOperationUserList\":[],\"bizSnStatusList\":[],\"proofingFinishDate\":[]," +
                "\"sourceOfProofingList\":[],\"proofingStatusList\":[]}}";

        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("PLM-TOKEN", TOKEN);

        RealResponse response = UrlHttpUtil.urlHttpPost(interfaceStr, requestBody, headerMap);
        System.out.println(response.is2String(response.getInputStream()));
    }
}
