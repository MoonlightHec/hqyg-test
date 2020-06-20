package com.hqyg.test1;
/** 
* @author 作者 zhangshiping: 
* @version 创建时间：2019年8月28日 下午5:25:27 
* 类说明 
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class IFSData {
	public static void main(String[] args) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> map2 = new HashMap<String, String>();

		
		/*
		 * String dbURL =
		 * "jdbc:mysql://111.230.26.36:3306/atms?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true";
		 * conn = DriverManager.getConnection(dbURL, "root", "gzm_1993NBA");
		 */
		 
		
		/*
		 * String dbURL =
		 * "jdbc:mysql://192.168.7.165:3306/ifs?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true";
		 * conn = DriverManager.getConnection(dbURL, "ifs_bus_acc", "ifs_bus_acc123");
		 */
		 

		String sql = "SELECT  id ,warehouse from mock_rule t ;";
		
		
		String dbURL = "jdbc:mysql://192.168.0.202:3306/plm?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true";
		
		conn = DriverManager.getConnection(dbURL, "plm_all", "plm_all_pw");
		pstmt = conn.prepareStatement(sql);
		resultSet = pstmt.executeQuery();// 返回查询结果
		ResultSetMetaData metaData = resultSet.getMetaData();
		int col_len = metaData.getColumnCount();
		while (resultSet.next()) {
			for (int i = 0; i < col_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				String cols_value = resultSet.getObject(cols_name).toString();
				if (cols_value == null) {
					cols_value = "";
				}
				map.put(cols_name, cols_value);

				map2.put(map.get("id"), map.get("warehouse"));

			}

		}
		System.out.println("数据库的条数为：" + map2.size());
		int k = 1;
		Iterator<Map.Entry<String, String>> it = map2.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			String id = entry.getKey();
			String warehouse = entry.getValue();
			if (warehouse.contains(",")) {
				String str2[] = warehouse.split(",");
				for (String string : str2) {

					System.out.println(k++ + "=" + string);

				}

			}

			else {
				System.out.println(k++ + "=" + id);

			}

		}

		System.out.println("总条数为：" + k);

	}

}
