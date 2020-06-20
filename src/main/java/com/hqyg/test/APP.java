package com.hqyg.test;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.github.crab2died.ExcelUtils;
import com.github.crab2died.exceptions.Excel4JException;




/** 

* @author 作者 zhangshiping: 

* @version 创建时间：2019年7月15日 下午5:19:56 

* 类说明 

*/
public class APP {
	  public static void main(String[] args) throws InvalidFormatException, Excel4JException, IOException {
	    	String path="C:\\Users\\Administrator\\Desktop\\test.xls";
	    	List<TestCase> list = ExcelUtils.getInstance().readExcel2Objects(path, TestCase.class);
	   	 
	   	 System.out.println(list);
		
	    
	    }
}
