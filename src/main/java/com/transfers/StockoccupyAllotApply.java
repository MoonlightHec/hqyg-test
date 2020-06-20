package com.transfers;

import com.util.Data;
import com.util.HeadParams;
import com.util.HttpUtil;

public class StockoccupyAllotApply {
	public static void main(String[] args) {
		//调拨申请
		String url=Data.url+"/wms/stockoccupy/allotApply";
		String requestUrl = HeadParams.requestUrl(url);

	    String	params="{\r\n" + 
	    		"	\"bizSn\": \"gzm339622374\",\r\n" + 
	    		"	\"bizSnType\": \"W061\",\r\n" + 
	    		"	\"stockCode\": \"ZQ01\",\r\n" + 
	    		"	\"operationTime\"" + ":" +"\"" +Data.dateString +"\"" +",\r\n" + 
	    		"	\"goodList\": [\r\n" + 
	    		"		{\r\n" + 
	    		"			\"outOwnerCode\": \"DS002\",\r\n" + 
	    		"			\"inOwnerCode\": \"DS037\",\r\n" + 
	    		"			\"sku\": \"116024612\",\r\n" + 
	    		"			\"qty\": \"10\"\r\n" + 
	    		"		}\r\n" + 
	    		"	]\r\n" + 
	    		"}";
		
		System.out.println("地址为：" + requestUrl);
		System.out.println("参数为：" + params);
		String responseresults = HttpUtil.sendPost(requestUrl,params);
		System.out.println(responseresults);	
	}
}
