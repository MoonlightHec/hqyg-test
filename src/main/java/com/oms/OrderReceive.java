package com.oms;

import com.util.Data;
import com.util.HeadParams;
import com.util.HttpUtil;

public class OrderReceive {
	/**
	 * 订单接收
	 * @param args
	 */
	public static void main(String[] args) {
		String url=Data.url+"oms/order/receive";
		String requestUrl = HeadParams.requestUrl(url);

	    String	params="{\r\n" + 
	    		"	\"platform\": \"pt7892008971\",\r\n" + 
	    		"	\"siteCode\": \"S3089315341\",\r\n" + 
	    		"	\"orderSn\": \"WMS128959O1904222333588\",\r\n" + 
	    		"	\"operationTime\": \"2019-10-21 10:07:48\",\r\n" + 
	    		"	\"ownerCode\": \"DS002\",\r\n" + 
	    		"	\"goodList\": [\r\n" + 
	    		
	    		"		{\r\n" + 
	    		"			\"sku\": \"191023001\",\r\n" + 
	    		"			\"qty\": \"10\",\r\n" + 
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
