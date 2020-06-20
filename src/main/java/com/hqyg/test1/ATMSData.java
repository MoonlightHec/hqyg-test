package com.hqyg.test1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/** 
* @author 作者 zhangshiping: 
* @version 创建时间：2019年8月28日 下午7:22:36 
* 类说明 
*/
public class ATMSData {
	public static void main(String[] args) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> map2 = new HashMap<String, String>();

		  String dbURL =
		  "jdbc:mysql://111.230.26.36:3306/atms?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true";
		  conn = DriverManager.getConnection(dbURL, "root", "gzm_1993NBA");
// where t.id NOT in ('23','2671','2667')
			String sql = "SELECT  id ,warehouse from mock_rule t ;";
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
