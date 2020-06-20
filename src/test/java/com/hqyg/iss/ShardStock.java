package com.hqyg.iss;

import com.google.common.hash.Hashing;
import com.util.DBConnection;
import com.util.SignUtils;
import org.testng.annotations.Test;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ShardStock extends InitStock {


    @Test
    public void addOneSku() {

        String sku = "100010101";
        //添加sku
        addThisSku(sku);

        //sku加库存
        ArrayList<String> skuList = new ArrayList<>();
        skuList.add(sku);
        //仓库
        String[] stockCodeList = {"ZQ01"};
        //货主
        String[] ownerCodeList = {"FZ001"};
        addSkuQty(skuList, stockCodeList, ownerCodeList, 3);
        System.out.println("初始库存数据成功！");
    }

    /**
     * 计算共享库存
     */
    @Test
    public void executiveShardStock() {
        String requestUrl = domain + "/tool/stockShareCut?password=scg.iss";
        String response = sendGet(requestUrl);
        System.out.println(response);
    }

    @Test
    public void getISSBase(){
        System.out.println(issBaseName("100012401"));
    }


    /**
     * sku添加库存
     * @param skuList sku列表
     * @param stockCodeList 仓库列表
     * @param ownerCodeList 货主列表
     * @param flag 对应场景的库存编号（qty序号）
     */
    public static void addSkuQty(ArrayList<String> skuList, String[] stockCodeList, String[] ownerCodeList, int flag) {
        // 库存类型
        String stockTypeList[] = {"IN_WAREHOUSE", "IN_WAREHOUSE_SHARE", "STOCK_OCCUPY", "STOCK_OCCUPY_SHARE", "ORDER_OCCUPY_SHARE", "ORDER_OCCUPY"};

        // 库存数量
        String qty = "";
        for (String sku : skuList) {
            for (String stockCode : stockCodeList) {
                for (String ownerCode : ownerCodeList) {
                    for (String stockType : stockTypeList) {
                        if (stockType.equals("IN_WAREHOUSE")) {
                            qty = qty(flag, "198", "198", "198");
                        }//在库库存

                        else if (stockType.equals("IN_WAREHOUSE_SHARE")) {
                            qty = qty(flag, "48", "98", "98");
                        }//共享在仓库存

                        else if (stockType.equals("STOCK_OCCUPY")) {
                            qty = qty(flag, "65", "65", "37");
                        } //仓库占用库存

                        else if (stockType.equals("STOCK_OCCUPY_SHARE")) {
                            qty = qty(flag, "15", "55", "22");
                        } //共享仓库占用库存

                        else if (stockType.equals("ORDER_OCCUPY")) {
                            qty = qty(flag, "77", "127", "30");
                        } //订单占用库存

                        else if (stockType.equals("ORDER_OCCUPY_SHARE")) {
                            qty = qty(flag, "20", "112", "20");
                        } //共享订单占用库存

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

    public static String qty(int flag, String... qtys) {
        String qty = "100";
        qty = qtys[flag - 1];
        return qty;
    }
}
