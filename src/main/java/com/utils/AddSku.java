package com.utils;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.testng.annotations.Test;

import com.google.common.hash.Hashing;
import com.util.DBConnection;
import com.util.PublicMethod;
import com.util.SignUtils;

public class AddSku extends PublicMethod {
    // wms - appKey
    public static final String appKey = "wms-test-app-key";
    // wms - app_secrect
    // 时间戳
    public static String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    public static final String appSecret = "wms-test-app-secret";

    // 产品数量
//	private static String qty = "5";

    /**
     * 修改库存信息(给指定sku加库存)
     */
    @Test
    public static void testChangeStock1() {


        String date = "20200607";
        for (int i = 0; i < 10; i++) {
            String skuName = "zsp";
            String sku = "";
            sku = date + i;
            skuName = skuName + i;
            int hashCode = Hashing.consistentHash(sku.hashCode(), 32);
            int redisCode = Hashing.consistentHash(sku.hashCode(), 16384);
            System.out.println(sku + ":" + skuName + ":" + hashCode + ":" + redisCode);
            String sql = "select * from iss_" + hashCode + ".stock_info where sku =" + sku
                    + ";";
            Map<String, String> data = DBConnection.mySqlConnect(sql, "iss_" + Hashing.consistentHash(sku.hashCode(), 32));
            Map<String, List<String>> signParams = getSignParam();
            signParams.put("sku", Arrays.asList(sku));
            signParams.put("skuName", Arrays.asList(skuName));
            String sign = SignUtils.sign(signParams, appSecret);
            String params = "?sign=" + sign + "&appKey=" + appKey + "&timestamp=" + URLEncoder.encode(timeStamp) + "&sku=" + sku + "&skuName=" + skuName;
            String requestUrl = domain + "/tool/test/addSku" + params;
            //System.out.println(requestUrl);
            String response = sendGet(requestUrl);

        }
        System.out.println("执行成功");
    }


    /**
     * 签名公共参数
     *
     * @return
     */

    public static Map<String, List<String>> getSignParam() {

        Map<String, List<String>> signParams = new HashMap<>();
        signParams.put("appKey", Arrays.asList(appKey));
        signParams.put("timestamp", Arrays.asList(timeStamp));
        return signParams;

    }


    @Test
    public static void testChangeStock2() {

        String sku2 = "}";
        String sku1 = "{";
        String date = "20200607";
        int k = 10;
        for (int i = 0; i < k; i++) {

            if (i == k - 1) {

                String sku = "\"" + date + i + "\"";
                sku1 = sku1 + sku;
            } else {

                String sku = "\"" + date + i + "\"" + ",";
                sku1 = sku1 + sku;

            }


        }

        String sku3 = sku1 + sku2;
        System.out.println(sku3);
    }
}