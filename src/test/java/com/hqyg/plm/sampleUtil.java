package com.hqyg.plm;

import java.util.ArrayList;

public class sampleUtil {

    /**
     * 生成样品管理入库登记入参json
     *
     * @param sku
     * @param num
     */
    public static void sampleParams(String sku, int num) {
        String jsonPart1 = "{\"sampleNum\":\"";
        String jsonPart2 = "\",\"sku\":\"";
        String jsonPart3 = "\",\"num\":1,\"result\":\"\"}";
        ArrayList<String> skuJsonList = new ArrayList<>();
        int skuInt = Integer.parseInt(sku);
        for (int i = 0; i < num; i++) {
            String skuJsonStr = jsonPart1 + (skuInt + i) + jsonPart2 + (skuInt + i) + jsonPart3;
            skuJsonList.add(skuJsonStr);
            System.out.println(skuJsonStr);
        }
        System.out.println(skuJsonList.toString());
    }

    public static void main(String[] args) {
        String sku = "202040000";
        sampleParams(sku, 10000);
    }
}
