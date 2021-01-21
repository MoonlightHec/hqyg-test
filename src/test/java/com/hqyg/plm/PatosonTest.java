package com.hqyg.plm;

import com.hqyg.util.SignUtil;
import com.sakura.RealResponse;
import com.sakura.UrlHttpUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * 接收帕拓逊延改数据
 */


public class PatosonTest {
    private static final Logger logger = Logger.getLogger(PatosonTest.class);

    String PTS_KEY = "patozontest";

    @Test
    public void runNumber() {
        int num = 1;
        for (int i = 0; i < num; i++) {
            int sku = (int) ((Math.random() * 9 + 1) * 100000000);
            postPatoson(i + 1, sku);
        }
    }

    @Test
    public void runThisSku() {
        postPatoson(1, 765590182);
    }

    public void postPatoson(int i, int sku) {
        String interfaceStr = "http://plm.hqygou.com:8088/api/change/recommend/info";
        String productMd5Code = DigestUtils.md5Hex(sku + "");
        String requestBody = "{\"recommendInfoList\":[{\"productMd5Code\":\"" + productMd5Code + "\",\"productCode\":\"" + sku + "\"," +
                "\"productName\":\"产品名称" + sku + "\"," +
                "\"ownerSite\":\"plm.com\",\"goodsUrl\":\"https://us.romwe.com/1box-Travel-Soap-Paper-p-752429-cat-841.html\"," +
                "\"goodsImgUrl\":\"http://img.ltwebstatic.com/images3_pi/2020/05/18/15897740988114c3c8ea67f40eda251cfd9f001d82_thumbnail_405x552" +
                ".jpg\",\"cateLabel\":\"Daily Home Essentials\",\"ownerPage\":\"bestseller\",\"isNewTop\":\"1\",\"firstTopTime\":\"2020-10-13 " +
                "15:11:51\",\"currencyCode\":\"$\",\"salesPrice\":\"199\",\"score\":\"4.8\",\"commentsTimes\":\"102654\",\"prePageRank\":\"7\"," +
                "\"curPageRank\":\"17\",\"pageIncreaseTimes\":\"4\",\"pageGoodsNum\":\"77\",\"collectTime\":\"2020-11-15 01:17:28\"," +
                "\"recommendUser\":\"Zhengjiangyan\",\"serialVersionUID\":0}],\"serialVersionUID\":0}";

        logger.info("第" + i + "个sku：" + sku);
        logger.info("第" + i + "个sku请求参数：" + requestBody);
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
