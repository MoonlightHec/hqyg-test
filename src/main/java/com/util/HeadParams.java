package com.util;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.testng.Assert;

import net.sf.json.JSONObject;

/**
 * @author 作者 zhangshiping:
 * @version 创建时间：2019年9月3日 上午11:01:42 类说明
 */
public class HeadParams {
	/**
	 * 返回请求的地址(post)
	 * 
	 * @return
	 */
	public static String requestUrl(String url) {
		String timeStamp = String.valueOf(System.currentTimeMillis());
		String uniqueId = UUID.randomUUID().toString().replaceAll("-", "");
		Map<String, List<String>> signParams = new HashMap<>();
		signParams.put("appKey", Arrays.asList("pms-test-app-key"));
		signParams.put("timestamp", Arrays.asList(timeStamp));
		signParams.put("uniqueId", Arrays.asList(uniqueId));
		String sign = sign(signParams, "pms-test-app-secret");
		String requestUrl = url + "?" + "appKey=pms-test-app-key" + "&" + "sign=" + sign + "&timestamp="
				+ URLEncoder.encode(timeStamp) + "&uniqueId=" + uniqueId;
		return requestUrl;
	}

	public static String sign(Map<String, List<String>> params, String appSecret) {
		String paramStr = ListUtils.n(params.keySet()).order(key -> key).list(key -> {
			String vals = ListUtils.n(params.get(key)).order(val -> val).join();
			return key + vals;
		}).join();
		String signStr = paramStr + appSecret;
		String sign = DigestUtils.md5Hex(signStr);
		return sign;
	}

	/**
	 * @requestUrl 请求url
	 * @type data 数据类型
	 * @assertionCode 断言码
	 * @param
	 * @return 接口返回码 code
	 * 
	 */

	public static void getResult(String url, String params) {

		String requestUrl = requestUrl(url);
		System.out.println(requestUrl);
		/*
		 * JSONObject data = data(type); System.out.println(data.toString());
		 */
		String response = HttpUtil.sendPost(requestUrl, params);
		System.out.println(response);
		String code = JSONObject.fromObject(response).getString("code");
		String message = JSONObject.fromObject(response).getString("message");
		System.out.println(message);
		/*
		 * if (Integer.valueOf(code).intValue() == 900000006) {
		 * 
		 * } Assert.assertEquals(Integer.valueOf(code).intValue(), "900000006");
		 */
		 
	}

	public static void main(String[] args) {
		getResult("http://10.60.46.88:27000/api/iss-dev/pms/purchase/orderAudit",
				"{\r\n" + "    \"purchaseSn\":\"P0696423311\",\r\n" + "    \"bizSnType\":\"P04211\",\r\n"
						+ "    \"operationTime\":\"1567395643126\",\r\n" + "    \"impo\":1,\r\n"
						+ "    \"goodList\":[\r\n" + "        {\r\n" + "            \"sku\":\"MBC2189\",\r\n"
						+ "            \"qty\":\"20000\",\r\n" + "            \"supplierCode\":\"supplierCode\",\r\n"
						+ "            \"stockCode\":\"PTDE01\"\r\n" + "        },\r\n" + "        {\r\n"
						+ "            \"sku\":\"MBC1131\",\r\n" + "            \"qty\":\"15000\",\r\n"
						+ "            \"supplierCode\":\"supplierCode\",\r\n"
						+ "            \"stockCode\":\"PTDE01\"\r\n" + "        }\r\n" + "    ]\r\n" + "}");

	}

}
