package com.hqyg.iss;

import com.google.common.hash.Hashing;
import com.util.PublicMethod;

import java.util.ArrayList;

public class TemTest extends PublicMethod {


    private static String firstSku = "20206070";
    String[] sku = new String[10];
    ArrayList<String> skuList = new ArrayList<>();

    public void dateTest() {
        for (int i = 0; i < 10; i++) {
            String tempSku = firstSku + i;
            skuList.add(tempSku);
        }
        for (String skuStr : skuList) {
            int hashCode = Hashing.consistentHash(skuStr.hashCode(), 32);
            System.out.println(skuStr+":"+hashCode);
        }

    }


    public void baseNumber() {
        String sku = "202060901";

        int hashCode = Hashing.consistentHash(sku.hashCode(), 32);
        System.out.println(hashCode);
    }


}
