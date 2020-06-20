package com.hqyg.test;

import java.util.ArrayList;
import java.util.List;

/** 
* @author 作者 zhangshiping: 
* @version 创建时间：2019年8月29日 上午10:07:01 
* 类说明 
*/
public class ListTest {
public static void main(String[] args) {
	
	List<String> list = new ArrayList<String>();
	
	
	list.add("a");
	list.add("b");
	list.add("c");
	list.add("d");



		  for (String object : list) {

		  list.remove(1);
		  
		  }
	for (String str: list) {
		System.out.println(str);
	}
		 
	
		/*
		 * for(int i=0;i<list.size();i++) {
		 * 
		 * 
		 * list.remove(list.get(i));
		 * 
		 * }
		 */
	
		
}
}
