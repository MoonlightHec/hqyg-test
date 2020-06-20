package com.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		ArrayList<Map<String, String>> arrayList = new ArrayList<Map<String, String>>();
	
		Map<String, String>  map = new HashMap<String, String>();
		
			map.put("1", "ddd");
			map.put("2", "ddd");
			map.put("3", "ddd");
			
			arrayList.add(map);
			
			for (Map<String, String> map1 : arrayList) {
				
				System.out.println(map1);
				
			}
			
			
			
			
			
		
		
	}
	
	
}
