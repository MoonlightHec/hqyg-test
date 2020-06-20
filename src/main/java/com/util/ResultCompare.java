package com.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import net.sf.json.JSONObject;

/** 
* @author 作者 zhangshiping: 
* @version 创建时间：2019年9月3日 下午5:11:30 
* 类说明 
*/
public class ResultCompare {

	private static Logger logger = LoggerFactory.getLogger(ResultCompare.class);

	public static boolean verifyReturnInfo(String result, String checkparams) {

		Boolean flag = false;

		String actualCode = JSONObject.fromObject(result).get("code").toString();
		String actualMessage = JSONObject.fromObject(result).get("message").toString();

		String expectCode = JSONObject.fromObject(checkparams).get("code").toString();
		String expectMessage = JSONObject.fromObject(checkparams).get("message").toString();

		if (!actualCode.equals(expectCode)) {

			logger.info(String.format("期望的返回码是：%s, 实际的返回码是：%s", actualCode, expectCode));

		} else if (!actualMessage.equals(expectMessage)) {

			logger.info(String.format("期望的返回message是：%s, 实际的返回message是：%s", actualMessage, expectMessage));

		} else {

			flag = true;

		}

		return flag;
	}
}
