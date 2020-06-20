package com.hqyg.iss;

import com.google.common.hash.Hashing;
import com.util.DBConnection;
import com.util.PublicMethod;
import com.util.SignUtils;
import org.testng.annotations.Test;

import java.net.URLEncoder;
import java.util.*;


/**
 * 初始化库存数量
 *
 * @author lijun
 */

public class InitStock extends PublicMethod {
    // 库存类型
    private static String stockTypeList[] = {"IN_WAREHOUSE", "IN_WAREHOUSE_SHARE", "STOCK_OCCUPY", "STOCK_OCCUPY_SHARE", "ORDER_OCCUPY_SHARE", "ORDER_OCCUPY"};

    /**
     * 添加指定数量sku
     *
     * @param firstSku
     * @param num
     */

    public static void initAddSku(String firstSku, int num) {
        for (int i = 0; i < num; i++) {
            String skuName = "sakura";
            String sku = firstSku + i;
            skuName = skuName + i;
            int hashCode = Hashing.consistentHash(sku.hashCode(), 32);
            int redisCode = Hashing.consistentHash(sku.hashCode(), 16384);
            System.out.println(sku + ":" + skuName + ":" + hashCode + ":" + redisCode);
            Map<String, List<String>> signParams = getSignParam();
            signParams.put("sku", Arrays.asList(sku));
            signParams.put("skuName", Arrays.asList(skuName));
            String sign = SignUtils.sign(signParams, appSecret);
            String params = "?sign=" + sign + "&appKey=" + appKey + "&timestamp=" + URLEncoder.encode(timeStamp) + "&sku=" + sku + "&skuName=" + skuName;
            String requestUrl = domain + "/tool/test/addSku" + params;
            String response = sendGet(requestUrl);

        }
        System.out.println("执行成功");
    }


    /**
     * 添加指定sku
     */
    public static void addThisSku(String sku) {
        String skuName = "sakura";
        int hashCode = Hashing.consistentHash(sku.hashCode(), 32);
        int redisCode = Hashing.consistentHash(sku.hashCode(), 16384);
        System.out.println(sku + ":" + skuName + ":" + hashCode + ":" + redisCode);
        Map<String, List<String>> signParams = getSignParam();
        signParams.put("sku", Arrays.asList(sku));
        signParams.put("skuName", Arrays.asList(skuName));
        String sign = SignUtils.sign(signParams, appSecret);
        String params = "?sign=" + sign + "&appKey=" + appKey + "&timestamp=" + URLEncoder.encode(timeStamp) + "&sku=" + sku + "&skuName=" + skuName;
        String requestUrl = domain + "/tool/test/addSku" + params;
    }


    /**
     * 给sku加库存
     *
     * @param skuList       sku列表
     * @param stockCodeList 仓库
     * @param ownerCodeList 货主
     */

    public static void addSkuQty(ArrayList<String> skuList, String[] stockCodeList, String[] ownerCodeList) {


        // 库存数量
        String qty = "";
        for (String sku : skuList) {
            for (String stockCode : stockCodeList) {
                for (String ownerCode : ownerCodeList) {
                    for (String stockType : stockTypeList) {
                        //在库库存
                        if (stockType.equals("IN_WAREHOUSE")) {
                            qty = "198";
                            //共享在仓库存
                        } else if (stockType.equals("IN_WAREHOUSE_SHARE")) {
                            qty = "98";
                            //仓库占用库存
                        } else if (stockType.equals("STOCK_OCCUPY")) {
                            qty = "37";
                            //共享仓库占用库存
                        } else if (stockType.equals("STOCK_OCCUPY_SHARE")) {
                            qty = "22";
                        } else if
                            //订单占用库存
                        (stockType.equals("ORDER_OCCUPY")) {
                            qty = "30";
                        } else if
                            //共享订单占用库存
                        (stockType.equals("ORDER_OCCUPY_SHARE")) {
                            qty = "20";
                        }
                        int hashCode = Hashing.consistentHash(sku.hashCode(), 32);
                        String sql = "select * from iss_" + hashCode + ".stock_info where sku =" + sku + ";";
                        DBConnection.mySqlConnect(sql, "iss_" + hashCode);
                        Map<String, List<String>> signParams = getSignParam();
                        signParams.put("sku", Arrays.asList(sku));
                        signParams.put("stockCode", Arrays.asList(stockCode));
                        signParams.put("ownerCode", Arrays.asList(ownerCode));
                        signParams.put("stockType", Arrays.asList(stockType));
                        signParams.put("qty", Arrays.asList(qty));
                        String sign = SignUtils.sign(signParams, appSecret);
                        @SuppressWarnings("deprecation")
                        String params = "?sign=" + sign + "&appKey=" + appKey + "&timestamp="
                                + URLEncoder.encode(timeStamp) + "&sku=" + sku + "&stockCode=" + stockCode
                                + "&ownerCode=" + ownerCode + "&stockType=" + stockType + "&qty=" + qty;
                        String requestUrl = domain + "/tool/test/changeStockInfo" + params;
                        System.out.println(requestUrl);
                        String response = sendGet(requestUrl);
                        System.out.println(response);
                    }
                }
            }
        }
    }


    /**
     * 获取sku集合
     *
     * @param firstSku 要添加的第一个sku
     * @param num      要添加的sku数量
     * @return 添加的sku集合
     */
    public static ArrayList<String> getSkuList(String firstSku, int num) {
        ArrayList<String> skuList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            String tempSku = firstSku + i;
            skuList.add(tempSku);
        }
        for (String skuStr : skuList) {
            int hashCode = Hashing.consistentHash(skuStr.hashCode(), 32);
            System.out.println(skuStr + ":" + hashCode);
        }
        return skuList;
    }

    /**
     * 查询sku所属库
     *
     * @return iss数据库名称
     */
    @Test
    public String issBaseName(String sku) {
        int hashCode = Hashing.consistentHash(sku.hashCode(), 32);
        return  "iss_" + hashCode;
    }
}
