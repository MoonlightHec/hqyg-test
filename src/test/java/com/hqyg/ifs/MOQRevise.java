package com.hqyg.ifs;

import com.hqyg.util.ExcelUtil;

import java.util.*;


public class MOQRevise {


    public static void main(String[] args) {
        Map<String, SkuStoryQuantity> skuStoryQuantityMap = new HashMap<String, SkuStoryQuantity>();
        ArrayList<String> skuList = new ArrayList<>();
        skuStoryQuantityMap = getTestDatas();

        //获取所有sku集合
        for (String key : skuStoryQuantityMap.keySet()) {
            skuList.add(key);
        }
        //将sku从小到大排序
        Collections.sort(skuList);

        //进行第一次分摊
        //计算销量占比


        //整款色最小起订量M
        int allMOQ = 0;
        //整款色库存数量A
        int allStockQuantity = 0;
        //总缺货需求数量Q
        int allAQuantity = 0;
        //整款色7天销量N
        int allWeekSales = 0;
        //遍历skuStoryQuantityMap获取以上数据
        for (Map.Entry<String, SkuStoryQuantity> entry : skuStoryQuantityMap.entrySet()) {
            allMOQ += entry.getValue().getMOQ();
            if (entry.getValue().getStockQuantity() > entry.getValue().getOrderQuantity()) {
                allStockQuantity += (entry.getValue().getStockQuantity() - entry.getValue().getOrderQuantity());
            }
            allAQuantity += entry.getValue().getAStoryQuantity();
            allWeekSales += entry.getValue().getWeekSales();
        }
        System.out.println("【M:" + allMOQ + "】【A:" + allStockQuantity + "】【Q:" + allAQuantity + "】【N:" + allWeekSales + "】");

        //获得每个sku的7天销量占比
        System.out.println("----------------每个sku的7天销量占比-----------------");
        for (Map.Entry<String, SkuStoryQuantity> entry : skuStoryQuantityMap.entrySet()) {
            ArrayList<Float> SalesTempList = new ArrayList<>();
            SalesTempList.add(((float) entry.getValue().getWeekSales() / allWeekSales));
            entry.getValue().setSalesProportionList(SalesTempList);
            System.out.println(SalesTempList);
        }


        for (Map.Entry<String, SkuStoryQuantity> entry : skuStoryQuantityMap.entrySet()) {
            ArrayList<Float> SalesTempList = entry.getValue().getSalesProportionList();
            for (Float sales : SalesTempList) {
                if (sales <= 0) {

                }
            }
        }
    }

    /**
     * 从excel获取初始数据
     *
     * @return SkuStoryQuantity对象
     */

    public static Map<String, SkuStoryQuantity> getTestDatas() {

        String sku = "";
        //A需求数量
        int aStoryQuantity = 0;
        //B需求数量
        int bStoryQuantity = 0;
        //C需求数量
        int cStoryQuantity = 0;
        //单个sku的MOQ
        int MOQ = 0;
        //库存
        int stockQuantity = 0;
        //90天订单占用量
        int orderQuantity = 0;
        //前7天真实销量
        int weekSales = 0;

        Map<String, SkuStoryQuantity> skuTestDatasMap = new HashMap<>();

        //获取excel数据
        String path = "/数据准备.xlsx";
        Object[][] datas = ExcelUtil.readExcel(path, "0", "2", "7", "2", "9");

        //将数据装进map
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                switch (j) {
                    case 0:
                        sku = datas[i][j].toString();
                        break;
                    case 1:
                        aStoryQuantity = Integer.parseInt(datas[i][j].toString());
                        break;
                    case 2:
                        bStoryQuantity = Integer.parseInt(datas[i][j].toString());
                        break;
                    case 3:
                        cStoryQuantity = Integer.parseInt(datas[i][j].toString());
                        break;
                    case 4:
                        MOQ = Integer.parseInt(datas[i][j].toString());
                        break;
                    case 5:
                        stockQuantity = Integer.parseInt(datas[i][j].toString());
                        break;
                    case 6:
                        orderQuantity = Integer.parseInt(datas[i][j].toString());
                        break;
                    case 7:
                        weekSales = Integer.parseInt(datas[i][j].toString());
                        break;
                }
            }
            SkuStoryQuantity skuStoryQuantity = new SkuStoryQuantity();
            skuStoryQuantity.setSku(sku);
            skuStoryQuantity.setAStoryQuantity(aStoryQuantity);
            skuStoryQuantity.setBStoryQuantity(bStoryQuantity);
            skuStoryQuantity.setCStoryQuantity(cStoryQuantity);
            skuStoryQuantity.setMOQ(MOQ);
            skuStoryQuantity.setStockQuantity(stockQuantity);
            skuStoryQuantity.setOrderQuantity(orderQuantity);
            skuStoryQuantity.setWeekSales(weekSales);
            skuTestDatasMap.put(sku, skuStoryQuantity);
        }
        return skuTestDatasMap;
    }
}
