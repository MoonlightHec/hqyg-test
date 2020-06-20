package com.caigou;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lee.util.Md5Util;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {
	/*
	 * public static String md5(String plainText) { //定义一个字节数组 byte[] secretBytes =
	 * null; try { // 生成一个MD5加密计算摘要 MessageDigest md =
	 * MessageDigest.getInstance("MD5"); //对字符串进行加密 md.update(plainText.getBytes());
	 * //获得加密后的数据 secretBytes = md.digest(); } catch (NoSuchAlgorithmException e) {
	 * throw new RuntimeException("没有md5这个算法！"); } //将加密后的数据转换为16进制数字 String md5code
	 * = new BigInteger(1, secretBytes).toString(16); // 如果生成数字未满32位，需要前面补0 for (int
	 * i = 0; i < 32 - md5code.length(); i++) { md5code = "0" + md5code; }
	 * System.out.println(md5code); return md5code; }
	 */
	
	//加盐方式
		public static String md5Password(String password) {
	        try {
	            // 得到一个信息摘要器
	            MessageDigest digest = MessageDigest.getInstance("md5");
	            byte[] result = digest.digest(password.getBytes());
	            StringBuffer buffer = new StringBuffer();
	            // 把每一个byte 做一个与运算 0xff;
	            for (byte b : result) {
	                // 与运算
	                int number = b & 0xff;// 加盐
	                String str = Integer.toHexString(number);
	                if (str.length() == 1) {
	                    buffer.append("0");
	                }
	                buffer.append(str);
	            }
	            // 标准的md5加密后的结果
	            
	            System.out.println( buffer.toString());
	            return buffer.toString();
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	            return "";
	        }
	    }


		//普通方式
		public static String MD5(String key) {
	        char hexDigits[] = {
	                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
	        };
	        try {
	            byte[] btInput = key.getBytes();
	            // 获得MD5摘要算法的 MessageDigest 对象
	            MessageDigest mdInst = MessageDigest.getInstance("MD5");
	            // 使用指定的字节更新摘要
	            mdInst.update(btInput);
	            // 获得密文
	            byte[] md = mdInst.digest();
	            // 把密文转换成十六进制的字符串形式
	            int j = md.length;
	            char str[] = new char[j * 2];
	            int k = 0;
	            for (int i = 0; i < j; i++) {
	                byte byte0 = md[i];
	                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
	                str[k++] = hexDigits[byte0 & 0xf];
	            }
	            
	            System.out.println(new String(str));
	            return new String(str);
	        } catch (Exception e) {
	            return null;
	        }
	    }
	
	/*
	 * public String EncoderByMd5(String str) throws NoSuchAlgorithmException,
	 * UnsupportedEncodingException{ //确定计算方法 MessageDigest
	 * md5=MessageDigest.getInstance("MD5"); BASE64Encoder base64en = new
	 * BASE64Encoder(); //加密后的字符串 String
	 * newstr=base64en.encode(md5.digest(str.getBytes("utf-8"))); return newstr; }
	 */


	public static void main(String[] args) {
		JSONObject reqJson = new JSONObject();
		String a="afasf*^d#&^h213sa152";
		String b="[\r\n" + 
				"        {\r\n" + 
				"            \"sku\":\"452278702\"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"sku\":\"449093601\"\r\n" + 
				"        }\r\n" + 
				"]";
		
	//	Map<String, String>>
		
	List<Map<String, Object>> 	list= new ArrayList<Map<String,Object>>();
	 
	Map<String, Object> reqMap = new HashMap<String, Object>(); 
	Map<String, Object> reqMap1 = new HashMap<String, Object>(); 
	Map<String, Object> reqMap2= new HashMap<String, Object>(); 
	reqMap1.put("sku", "452278702");
	reqMap2.put("sku", "449093601");
	list.add(reqMap1);
	list.add(reqMap2);
//	reqMap.put("key", "hello");
	reqMap.put("data_json", list);
	reqJson.putAll(reqMap);
	String json = JSONObject.toJSONString(reqJson, true);
	System.out.println(list);
	System.out.println(a);
	System.out.println(b);
	String md5_opt	=Md5Util.getMd5Hex("afasf*^d#&^h213sa152"+"[{\"sku\":\"452278702\"},{\"sku\":\"449093601\"}]");
		//String md5Text = md5(a, json);
		// String md5_opt1=DigestUtils.md5Hex("afasf*^d#&^h213sa152"+"[{\"sku\":\"452278702\"},{\"sku\":\"449093601\"}]");
		System.out.println(md5_opt);
		//System.out.println(md5_opt1);
		
		//md5(a+b);
		
		//md5Password("afasf*^d#&^h213sa152"+"[{\"sku\":\"452278702\"},{\"sku\":\"449093601\"}]");
		
		//MD5("afasf*^d#&^h213sa152[{\"sku\":\"452278702\"},{\"sku\":\"449093601\"}]");
	}
	
}
