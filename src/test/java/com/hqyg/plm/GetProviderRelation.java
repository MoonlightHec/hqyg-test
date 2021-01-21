package com.hqyg.plm;


import com.alibaba.fastjson.JSON;
import com.sakura.RealResponse;
import com.sakura.UrlHttpUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取pms供货关系接口
 */
public class GetProviderRelation {
    public static void main(String[] args) {
        String key = "afasf*^d#&^h213sa152";
        String provider_sn = "WGC1748";
        String sku = "206061407";

        Map<String, String> keyMap = new HashMap<String, String>();
        keyMap.put("provider_sn", provider_sn);
        keyMap.put("sku", sku);

        //拼装data
        String data = JSON.toJSONString(keyMap);
        String signStr = key + data;
        String keyStr = DigestUtils.md5Hex(signStr);
        System.out.println("data:" + data);
        System.out.println("key:" + keyStr);

        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("data", data);
        paramsMap.put("key", keyStr);
        //发起请求
        RealResponse response = UrlHttpUtil.urlHttpPost("http://pms.hqygou.com/interface/plm/get-sku-provider", paramsMap);
        String result = response.is2String(response.getInputStream());
        System.out.println(StringEscapeUtils.unescapeJava(result));
    }
}
