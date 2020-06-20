package com.orderdelivery;

import com.util.Data;
import com.util.HeadParams;
import com.util.HttpUtil;

public class PickingCreate {

public static void main(String[] args) {
	//订单配送
	 String url=Data.url+"/oms/order/picking/create";
		String requestUrl = HeadParams.requestUrl(url);

	    String	params="{\r\n" + 
	    		"	\"platform\": \"pt5102243331\",\r\n" + 
	    		"	\"siteCode\": \"S3264735981\",\r\n" + 
	    		"	\"orderSn\": \"WMS128959O1904222333588\",\r\n" + 
	    		"	\"operationTime\"" + ":" +"\"" +Data.dateString +"\"" +",\r\n" + 
	    		"	\"ownerCode\": \"DS002\",\r\n" + 
	    		"	\"pickingSn\": \"WMS128959O1904222333598p2\",\r\n" + 
	    		"	\"pickingGoodList\": [\r\n" + 
	    		"		{\r\n" + 
	    		"			\"sku\": \"103508603\",\r\n" + 
	    		"			\"qty\": \"18\",\r\n" + 
	    		"			\"stockCode\": \"ZQ01\"\r\n" + 
	    		"		}\r\n" + 
	    		"	],\r\n" + 
	    		"	\"orderGoodDetails\": [\r\n" + 
	    		"		{\r\n" + 
	    		"			\"sku\": \"103508603\",\r\n" + 
	    		"			\"qty\": \"0\",\r\n" + 
	    		"			\"stockCode\": \"ZQ01\"\r\n" + 
	    		"		}\r\n" + 
	    		"	]\r\n" + 
	    		"}";
	    		
		
		System.out.println("地址为：" + requestUrl);
		System.out.println("参数为：" + params);
		String responseresults = HttpUtil.sendPost(requestUrl,params);
		System.out.println(responseresults);
	
	
}
}
