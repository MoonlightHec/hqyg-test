package com.utils;

import org.testng.annotations.Test;

import com.google.common.hash.Hashing;

public class SkuDb {

	
	@Test
	public void test() {
		
		 System.out.println("201911236:" + Hashing.consistentHash("201911236".hashCode(), 32)); 
		 System.out.println("WMS128959O1904222333005:" + Hashing.consistentHash("WMS128959O1904222333005".hashCode(), 32)); 
 

		
	}
	/**
	 * redis 哈希算法
	 * 
	 */ 
	//sku查询前缀    数据库
	@Test
	public void test1() {
		System.out.println("201911236: " + Hashing.consistentHash("201911236".hashCode(), 16384));
	}	
}
