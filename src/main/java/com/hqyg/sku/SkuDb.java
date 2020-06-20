package com.hqyg.sku;

import org.testng.annotations.Test;

import com.google.common.hash.Hashing;

public class SkuDb {

	
	@Test
	public void test() {
		
		 System.out.println("gzm339622371:" + Hashing.consistentHash("gzm339622371".hashCode(), 32));     
         System.out.println("MBC2189:" + Hashing.consistentHash("MBC2189".hashCode(), 32));     
		 
		
	}
	/**
	 * redis 哈希算法
	 * 
	 */ 
	//sku查询前缀    数据库
	@Test
	public void test1() {
		System.out.println("202298816: " + Hashing.consistentHash("202298816".hashCode(), 16384));
		System.out.println("QA0000801: " + Hashing.consistentHash("QA0000801".hashCode(), 16384));
	}	
	
	@Test
	public void test3() {
		
		int a=8;
		if(a>9) {
			
		System.out.println("dd");	
			
		}
		
		else {
			
			
			System.out.println("11");	
			
		}
		
	}
	}

