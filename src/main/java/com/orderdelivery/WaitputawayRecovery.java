package com.orderdelivery;

import com.util.Data;
import com.util.HeadParams;
import com.util.HttpUtil;

public class WaitputawayRecovery {
	
	
	/**
	 * 回收入库
	 * @param args
	 */
public static void main(String[] args) {
	
     String url=Data.url+"/wms/waitputaway/recovery";
	String requestUrl = HeadParams.requestUrl(url);

    String	params="	{\r\n" + 
    		"		\"bizSn\": \"WMS128959O1904222333599p2\",\r\n" + 
    		"		\"bizSnType\": \"W061\",\r\n" + 
    		"		\"stockCode\": \"ITED\",\r\n" + 
    		"	\"operationTime\"" + ":" +"\"" +Data.dateString +"\"" +",\r\n" + 
    		"		\"shareStatus\": 1,\r\n" + 
    		"		\"bizSnRelate\": \"WMS128959O1904222333599\",\r\n" + 
    		"		\"goodList\": [\r\n" + 
    		"			{\r\n" + 
    		"				\"sku\": \"103508603\",\r\n" + 
    		"				\"qty\": 60,\r\n" + 
    		"				\"orderOwnerCode\": \"DS024\",\r\n" + 
    		"				\"pickingOwnerCode\": \"DS024\",\r\n" + 
    		"				\"goodQty\": 60,\r\n" + 
    		"				\"badQty\": 0\r\n" + 
    		"			},\r\n" + 
    		"			{\r\n" + 
    		"				\"sku\": \"103508604\",\r\n" + 
    		"				\"qty\": 60,\r\n" + 
    		"				\"orderOwnerCode\": \"DS024\",\r\n" + 
    		"				\"pickingOwnerCode\": \"DS024\",\r\n" + 
    		"				\"goodQty\": 60,\r\n" + 
    		"				\"badQty\": 0\r\n" + 
    		"			}\r\n" + 
    		"		]\r\n" + 
    		"	}";
    		
	
	System.out.println("地址为：" + requestUrl);
	System.out.println("参数为：" + params);
	String responseresults = HttpUtil.sendPost(requestUrl,params);
	System.out.println(responseresults);
}
	
	
	
}
