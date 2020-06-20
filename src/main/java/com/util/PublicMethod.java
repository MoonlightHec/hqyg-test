package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.net.ssl.SSLContext;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

/**
 * 接口公共方法(发送请求)
 *
 */
public class PublicMethod {
	// 测试环境地址
//	 public static String domain = "http://10.60.46.86:17004";//不需要签名
	// 一期测试环境地址   需要签名 
//	 public static String domain = "http://10.60.46.88:17000/api/iss";
//	 二期测试环境地址   需要签名 
//	public static String domain = "http://10.60.46.88:27000/api/iss";
	//public static String domain = "http://10.60.46.88:27000/api/iss-dev/";开发环境走网关
//	public static String domain = "http://10.60.46.88:27000/api/rmp";
	 public static String domain = "http://10.60.46.86:27004/";   // http://10.60.46.86:7004/tool/stockShareCut //开发环境不走网关
	
	//压测环境
//	public static String domain = "http://10.60.41.105/api/iss2";

	// wms - appKey
	public static final String appKey = "wms-test-app-key";
	public static final String appSecret = "wms-test-app-secret";
	// 时间戳
	public static String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

	/**
	 * post请求，表单提交格式
	 * 
	 * @param url
	 * @param parameters
	 * @return
	 */
	public static String sendRequest(String url, List<NameValuePair> parameters) {
		CloseableHttpClient httpClient = null;
		try {
			httpClient = getIgnoeSSLClient();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpPost httpPost = new HttpPost(url);
		HttpEntity postParams = null;
		try {
			postParams = new UrlEncodedFormEntity(parameters, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		httpPost.setEntity(postParams);        
		CloseableHttpResponse httpResponse = null;
		try {
			httpResponse = httpClient.execute(httpPost);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "utf-8"));
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String inputLine;
		StringBuffer response = new StringBuffer();

		try {
			while ((inputLine = reader.readLine()) != null) {
				response.append(inputLine);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			httpClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.toString();

	}

	/**
	 * 发送post请求(JSON格式)
	 * 
	 */
	public static String sendPost(String url, String data) {
		String result = "";
		// 创建client
		CloseableHttpClient httpclient = null;

		try {
			httpclient = getIgnoeSSLClient();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			// 创建post请求
			HttpPost httpPost = new HttpPost(url);
			httpPost.addHeader("Accept", "application/json");
//			httpPost.addHeader("client-origin", "oms");
			// JSON
			StringEntity entity = new StringEntity(data, ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			// 执行post请求
			CloseableHttpResponse response = null;
			try {
				response = httpclient.execute(httpPost);

			} catch (ClientProtocolException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// 响应实体
			HttpEntity resEntity = response.getEntity();
			try {

				if (resEntity != null) {
					try {
						result = EntityUtils.toString(resEntity);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// 关闭HttpEntity流
				try {
					EntityUtils.consume(resEntity);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} finally {
				try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;

	}
	
	/**
	 * 发送get请求
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String sendGet(String url) {

		CloseableHttpClient httpClient = null;
		try {
			httpClient = getIgnoeSSLClient();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		HttpGet httpGet = new HttpGet(url);
		String result = "";
		CloseableHttpResponse httpResponse = null;
		try {
			httpResponse = httpClient.execute(httpGet);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "utf-8"));
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String inputLine;
		StringBuffer response = new StringBuffer();

		try {
			while ((inputLine = reader.readLine()) != null) {
				response.append(inputLine);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		result = response.toString();
		try {
			httpClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 忽略证书
	 * 
	 * @return
	 * @throws Exception
	 */
	public static CloseableHttpClient getIgnoeSSLClient() throws Exception {
		SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
			// @Override
			public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
				return true;
			}
		}).build();
		// 创建httpClient
		CloseableHttpClient client = HttpClients.custom().setSSLContext(sslContext).setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
		return client;
	}
	
	/**
	 * 得到返回结果的状态码
	 * 
	 * @throws Exception
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static String getCode(String url, List<NameValuePair> parameters) {
		String result = sendRequest(url, parameters);
		JSONObject json = JSONObject.fromObject(result);	
		String code = json.getString("code");	
		return code;
	}

	/**
	 * 得到返回结果的状态码(JSON)
	 * 
	 * @throws Exception
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static String getStatus(String url, String authToken, String json) {
		String result = sendPost(url, json);
		JSONObject response = JSONObject.fromObject(result);		
		String code = response.getString("code");
		return code;
	}


	/**
	 * 签名公共参数
	 * @return
	 */
	public static Map<String, List<String>> getSignParam() {
		Map<String, List<String>> signParams = new HashMap<>();
		signParams.put("appKey", Arrays.asList(appKey));
		signParams.put("timestamp", Arrays.asList(timeStamp));
		return signParams;
	}

}
