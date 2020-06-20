package com.Inventoryinthelibrary;

import com.util.Data;
import com.util.HeadParams;
import com.util.HttpUtil;

public class InWarehouseChangeInStock {
	public static void main(String[] args) {
		//调整入库
		String url=Data.url+"/wms/inWarehouse/changeInStock";
		String requestUrl = HeadParams.requestUrl(url);

	    String	params="{\r\n" + 
	    		"	\"bizSn\": \"gzm339622373\",\r\n" + 
	    		"	\"bizSnType\": \"W061\",\r\n" + 
	    		"	\"stockCode\": \"ZQ01\",\r\n" + 
	    		"	\"operationTime\"" + ":" +"\"" +Data.dateString +"\"" +",\r\n" + 
	    		"	\"goodList\": [\r\n" + 
	    		"		{\r\n" + 
	    		"			\"ownerShip\": \"HQYG\",\r\n" + 
	    		"			\"sku\": \"116024613\",\r\n" + 
	    		"			\"qty\": \"15\"\r\n" + 
	    		"		}\r\n" + 
	    		"	]\r\n" + 
	    		"}";
		
		System.out.println("地址为：" + requestUrl);
		System.out.println("参数为：" + params);
		String responseresults = HttpUtil.sendPost(requestUrl,params);
		System.out.println(responseresults);	
	}
}
