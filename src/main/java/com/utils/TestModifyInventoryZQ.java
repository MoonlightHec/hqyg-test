package com.utils;

import com.google.common.hash.Hashing;
import com.util.DBConnection;
import com.util.PublicMethod;
import com.util.SignUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 修改库存信息
 *
 * @author gaozhongming
 * <p>
 * RESERVATION("a", "订货数量"), TRANSPORT("b", "运输在途"), TRANSFER("c",
 * "中转仓库存"), WAIT_CHECK("d", "收货待检"), WAIT_PUTAWAY("e", "待上架"),
 * IN_WAREHOUSE("f", "在库库存"), STOCK_OCCUPY("g", "仓库占用"), FREEZE("h",
 * "冻结库存"), ORDER_OCCUPY("i", "订单占用"), ACTIVITY_OCCUPY("j", "活动占用"),
 * SUPPLIER("k", "供应商库存"), PRESELL("l", "预售库存"), DEFECTIVE("m",
 * "不良品库存"), FAULT("n", "故障品库存");
 */

public class TestModifyInventoryZQ extends PublicMethod {
    // wms - appKey
    public static final String appKey = "wms-test-app-key";
    // wms - app_secrect
    // 时间戳
    public static String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    private static String[] skuList = {"202006070", "202006071", "202006072", "202006073", "202006074", "202006075", "202006076", "202006077", "202006078", "202006079"};
    // 仓库 操作：SZTW 目的：YB
    // private static String stockCode = "ZQ01";
    private static String[] stockCodeList = {"ZQ01"};
    //private static String[] stockCodeList = { "POLANDED" };
    // 货主 DS003 DS004 XW001 FX001 DZ001 DS002 PT001
    public static final String appSecret = "wms-test-app-secret";
    // private static String[] ownerCodeList = {"DS032"};
    private static String[] ownerCodeList = {"DS001", "DS003", "DS002"};
    // 库存类型

    private static String stockTypeList[] = {"IN_WAREHOUSE", "IN_WAREHOUSE_SHARE", "STOCK_OCCUPY", "STOCK_OCCUPY_SHARE", "ORDER_OCCUPY_SHARE", "ORDER_OCCUPY"};
    //private static String stockTypeList[] = { "ORDER_OCCUPY" };

    // 产品数量
    private static String qty = "100";

    /**
     * 修改库存信息(给指定sku加库存)
     */
    @Test
    public static void testChangeStock1() {

        for (String sku : skuList) {
            for (String stockCode : stockCodeList) {
                for (String ownerCode : ownerCodeList) {
                    for (String stockType : stockTypeList) {

                        if (stockType.equals("IN_WAREHOUSE")) {
                            qty = "100";

                        } else if (stockType.equals("IN_WAREHOUSE_SHARE")) {
                            qty = "50";

                        } else if (stockType.equals("STOCK_OCCUPY")) {

                            qty = "40";


                        } else if (stockType.equals("STOCK_OCCUPY_SHARE")) {
                            qty = "20";

                        } else if
                        (stockType.equals("ORDER_OCCUPY")) {
                            qty = "20";
                        } else if
                        (stockType.equals("ORDER_OCCUPY_SHARE")) {
                            qty = "10";

                        }

                        String sql = "select * from iss_" + Hashing.consistentHash(sku.hashCode(), 32)
                                + ".stock_info where sku =" + sku + ";";
                        Map<String, String> data = DBConnection.mySqlConnect(sql,
                                "iss_" + Hashing.consistentHash(sku.hashCode(), 32));
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

    /**
     * 修改库存信息(文件方式)
     *
     * @throws IOException
     * @returnk
     */

    /*
     * @Test public void testChangeStock() throws IOException { for (int j = 0; j <
     * stockCodeList.length; j++) { // 具体库存类型 String stockCode = stockCodeList[j];
     * Map<String, List<String>> signParams = getSignParam(); FileInputStream fis =
     * new FileInputStream("C:\\Users\\Administrator\\Desktop\\ff.txt");
     *
     * @SuppressWarnings("resource") BufferedReader br = new BufferedReader(new
     * InputStreamReader(fis)); String line = null; String textSku = null; while
     * ((line = br.readLine()) != null) { textSku = line; signParams.put("sku",
     * Arrays.asList(textSku)); signParams.put("stockCode",
     * Arrays.asList(stockCode)); signParams.put("ownerCode",
     * Arrays.asList(ownerCode)); signParams.put("stockType",
     * Arrays.asList(stockType)); signParams.put("qty", Arrays.asList(qty)); String
     * sign = SignUtils.sign(signParams, appSecret);
     *
     * @SuppressWarnings("deprecation") String params = "?sign=" + sign + "&appKey="
     * + appKey + "&timestamp=" + URLEncoder.encode(timeStamp) + "&sku=" + textSku +
     * "&stockCode=" + stockCode + "&ownerCode=" + ownerCode + "&stockType=" +
     * stockType + "&qty=" + qty; String requestUrl = domain +
     * "/tool/changeStockInfo" + params; System.out.println(requestUrl); String
     * response = sendGet(requestUrl); System.out.println(response); } } }
     *
     * @Test public static void tool() throws IOException { // 将仓库的数据加载到内存
     * ArrayList<String> stockCodeList = new ArrayList<String>(); ArrayList<String>
     * skuList = new ArrayList<String>(); FileInputStream wareHouse = new
     * FileInputStream("C:\\Users\\Administrator\\Desktop\\warehouse.txt");
     * FileInputStream sku = new
     * FileInputStream("C:\\Users\\Administrator\\Desktop\\skuData.txt");
     *
     * @SuppressWarnings("resource") BufferedReader stockBr = new BufferedReader(new
     * InputStreamReader(wareHouse));
     *
     * @SuppressWarnings("resource") BufferedReader skuBr = new BufferedReader(new
     * InputStreamReader(sku)); String wareHouseline = null; String skuLine = null;
     * while ((wareHouseline = stockBr.readLine()) != null) {
     * stockCodeList.add(wareHouseline); } while ((skuLine = skuBr.readLine()) !=
     * null) { skuList.add(skuLine); } try { File writeName = new
     * File("C:\\Users\\Administrator\\Desktop\\boxData.txt");
     * writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖 try (FileWriter writer = new
     * FileWriter(writeName); BufferedWriter out = new BufferedWriter(writer)) { for
     * (int i = 0; i < 1000000; i++) { String boxSn = "boxSn" + new
     * Random().nextInt(100000000) + 1; String bizSn = "bizSn" + new
     * Random().nextInt(100000000) + 1; out.write(bizSn + "|" + boxSn + "|" +
     * stockCodeList.get(new Random().nextInt(1969) + 1) + "|" + skuList.get(new
     * Random().nextInt(64226) + 1) + "|" + new Random().nextInt(1000) + 1 +
     * "\r\n"); // \r\n即为换行 out.flush(); // 把缓存区内容压入文件 } } } catch (IOException e) {
     * e.printStackTrace();
     *
     * } }
     *
     *//**
     * @throws IOException
     *
     *//*
     * @Test public void test() throws IOException { FileInputStream wms = new
     * FileInputStream("C:\\Users\\Administrator\\Desktop\\test\\wms.txt");
     * FileInputStream iss = new
     * FileInputStream("C:\\Users\\Administrator\\Desktop\\test\\iss.txt");
     *
     * @SuppressWarnings("resource") BufferedReader wmsBr = new BufferedReader(new
     * InputStreamReader(wms));
     *
     * @SuppressWarnings("resource") BufferedReader issBr = new BufferedReader(new
     * InputStreamReader(iss)); String wmsLine = null; String issLine = null;
     * ArrayList<String> list = new ArrayList<String>(); while ((wmsLine =
     * wmsBr.readLine()) != null) { list.add(wmsLine); } while ((issLine =
     * issBr.readLine()) != null) { for (int j = 0; j < list.size(); j++) { if
     * (issLine.equals(list.get(j)) == true) { System.out.println(list.get(j)); } }
     * } }
     */
}
