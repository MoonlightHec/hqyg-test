package com.orderdelivery;

import com.util.Data;
import com.util.HeadParams;
import com.util.HttpUtil;

public class OrderCancel {
	 public static void main(String[] args) {
		   
		  
//			订单作废
				String url =Data.url+"/oms/order/cancel";
				String requestUrl = HeadParams.requestUrl(url);
				String params = "{\r\n" + 
						"	\"platform\": \"pt7892008971\",\r\n" + 
						"	\"siteCode\": \"S3089315341\",\r\n" + 
						"	\"orderSn\": \"WMS128959O1904222333599\",\r\n" + 
						"	\"operationTime\"" + ":" +"\"" +Data.dateString +"\"" +",\r\n" + 
						"	\"ownerCode\": \"DS002\",\r\n" + 
						"	\"goodList\": [\r\n" + 
						"		{\r\n" + 
						"			\"sku\": \"103508603\",\r\n" + 
						"			\"qty\": \"80\",\r\n" + 
						"			\"stockCode\": \"ZQ01\"\r\n" + 
						"		}\r\n" + 
						"	]\r\n" + 
						"}";

				System.out.println("地址为：" + requestUrl);
				System.out.println("参数为：" + params);
				String responseresults = HttpUtil.sendPost(requestUrl, params);
				System.out.println(responseresults);
			}
}
