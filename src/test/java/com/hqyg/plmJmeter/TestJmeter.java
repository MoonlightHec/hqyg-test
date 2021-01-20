package com.hqyg.plmJmeter;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * jmeter临时测试类
 */

public class TestJmeter {

    private static final Logger logger = Logger.getLogger(TestJmeter.class);

    public static void main(String[] args) {
        //System.out.println(getRanddomNumber(10, 100));
        int proofingFrom = (int) (0 + Math.random() * 4);
        System.out.println(proofingFrom + ":" + getProofingType(proofingFrom));
    }

    /**
     * 获取随机个不重复随机整数
     *
     * @param allTimes 总随机次数
     * @param allIds   随机数最大值
     * @return partsIdsStr 结果String
     */
    public static String getRanddomNumber(int allTimes, int allIds) {
        List<Integer> randList = new ArrayList<>();

        int times = (int) (1 + Math.random() * allTimes);
        // 获取100以内的times个随机数
        for (int i = 0; i < times; i++) {
            int id = (int) (1 + Math.random() * allIds);
            boolean isContains = randList.contains(id);
            if (isContains != true) {
                randList.add(id);
            }
            if (randList.size() == times - 1) {
                break;
            }
        }
        //使用逗号拼接获取的随机数
        String partsIdsStr = "";
        for (int i = 0; i < randList.size(); i++) {
            partsIdsStr += randList.get(i);
            if (i != randList.size() - 1) {
                partsIdsStr += ",";
            }
        }
        return partsIdsStr;
    }

    /**
     * 获取打版方式
     *
     * @param proofingFrom 版单来源
     * @return proofingType 打版方式
     */
    public static int getProofingType(int proofingFrom) {

        int proofingType = 0;
        switch (proofingFrom) {
            case 0:
            case 1:
                proofingType = 0;
                break;
            case 2:
                proofingType = 1;
                break;
            case 3:
                proofingType = 2;
                break;
        }
        return proofingType;
    }
}
