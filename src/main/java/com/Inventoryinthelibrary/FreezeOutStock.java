package com.Inventoryinthelibrary;

import com.util.Data;
import com.util.HeadParams;
import com.util.HttpUtil;

public class FreezeOutStock {
	
	public static void main(String[] args) {
		//冻结出库
		String url=Data.url+"/wms/inWarehouse/freezeOutStock";
		String requestUrl = HeadParams.requestUrl(url);

	    String	params="{\r\n" + 
	    		"	\"bizSn\": \"gzm339622374\",\r\n" + 
	    		"	\"bizSnType\": \"W061\",\r\n" + 
	    		"	\"stockCode\": \"ZQ01\",\r\n" + 
	    		"	\"operationTime\"" + ":" +"\"" +Data.dateString +"\"" +",\r\n" + 
	    		"	\"goodList\": [\r\n" + 
	    		"		{\r\n" + 
	    		"			\"ownerShip\": \"HQYG\",\r\n" + 
	    		"			\"sku\": \"116024612\",\r\n" + 
	    		"			\"qty\": \"2\"\r\n" + 
	    		"		}\r\n" + 
	    		"	]\r\n" + 
	    		"}";
		
		System.out.println("地址为：" + requestUrl);
		System.out.println("参数为：" + params);
		String responseresults = HttpUtil.sendPost(requestUrl,params);
		System.out.println(responseresults);	
	}
	}
	
