package com.hqyg.test1;

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

/**
 * @author 作者 zhangshiping:
 * @version 创建时间：2019年8月28日 下午3:39:36 类说明
 */
public class Include {
	public static void main(String[] args) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> map2 = new HashMap<String, String>();
		Map<String, String> map3 = new HashMap<String, String>();
		List<String> list1 = new ArrayList<String>();
		List<String> list3 = null;

		String[] newstr = new String[] { "ESFBAWHE", "UKFBAWHE", "FRFBAWHE", "DEFBAWHE", "USFBAWHO", "USFBAWHS",
				"UKFBAWHD", "CAFBAWHS", "ITFBAWHK", "FRFBAWHK", "ESFBAWHK", "WZDFWH", "FXQHBSWH", "USFBAWHG",
				"USFBAWHB", "JPFBAWHE", "JPFBAWHD", "JPFBAWHC", "JPFBAWHB", "ITFBAWHB", "FRFBAWHB", "ESFBAWHB",
				"DEFBAWHB", "EBQHBSWH", "QHZZWH", "QHBSWH", "VGWH", "JPFBAWH", "DEFBAWH", "UKFBAWH", "MXWH", "PCWH",
				"GBRUW", "UK", "USFBAWHR", "USFBAWHK", "CAFBAWHK", "MXFBAWHK", "YBTB", "DEFBAWHM", "ESFBAWHM",
				"UKFBAWHG", "ESFBAWHG", "FRFBAWHG", "UKFBAWHJ", "DEFBAWHI", "ESFBAWHI", "UKFBAWHK", "DEFBAWHK",
				"UKFBAWHH", "ESFBAWHH", "FRFBAWHH", "ITFBAWHH", "JPFBAWHG", "JPFBAWHF", "ESFBAWHL", "UKFBAWHO",
				"UKFBAWHR", "UKTJWH", "DEFBAW", "ITFBASL", "ESTJWH", "FXRUWJ", "FXLAWH2", "DEFNTG", "IDJDYJD",
				"FXJFKGC", "SHOUHOUBEIPINWH", "DGXWFN", "ESWXDZ", "IDXNSP", "FXDHQD", "DZXNQD", "GBFBAEL", "DEFBAEL",
				"FRFBAEL", "ITFBAEL", "ESFBAEL", "GBYKDFX", "GBYKDDZ", "HKB2BC", "DSFHQB", "DZYJDFC", "HZSMTFX2",
				"HZSMTFX4", "ITFBAELE", "CZ4PXFX", "FRFBARD", "GBYKDFX2", "GBYKDDZ2", "DEDFCK", "PTDFCK", "USDFCK",
				"MYDFCK", "GBBMHTJ2", "AUDFCK", "PLDFCK", "BEDFCK", "DZCZBLG", "DZCZBLG1", "DEWHDSF", "CNSMTDFC",
				"ELSSMTDFC", "SZLIUJIUC", "GBFBAFOR", "DEFBAFOR", "ITFBAFOR", "ESFBAFOR", "USFBAM", "AEFBSSVT",
				"DEHAIER", "ITHAIER", "ESHAIER", "ESTJWH2", "SUMAITAE", "USFBAZHE", "CNGYSTO", "USFBAOA", "GBFBAOA",
				"DEFBAOA", "FRFBAOA", "ITFBAOA", "ESFBAOA", "DS", "ITGYSDF", "DETUICANGG", "UKFBAAZON", "ITFBAAZON",
				"ESFBAAZON", "DEFBAAZON", "FRFBAAZON", "UKAMAZON", "FRAMAZON", "DEAMAZON", "ESAMAZON", "USLATJC",
				"USZYCC", "DZTS", "FRFBADE", "CNHYCSDD", "VIDAXLL", "AEFBNNO", "JPFBAFX", "NGJUMIAZA", "JPCHCGC" };

		List<String> list = Arrays.asList(newstr);
		Class.forName("com.mysql.jdbc.Driver");
//		String dbURL = "jdbc:mysql://192.168.7.165:3306/ifs?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true";
//		conn = DriverManager.getConnection(dbURL, "ifs_bus_acc", "ifs_bus_acc123");
		
		String dbURL = "jdbc:mysql://192.168.0.202:3306/plm?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true";
		conn = DriverManager.getConnection(dbURL, "plm_all", "plm_all_pw");
		
		String sql = "SELECT  id ,warehouse from mock_rule t where t.id in (5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,24,25,26,27,28,29,30,31,32,49,282,283,284,285,286,287,288,289,2588,2589,2642,2670,2678,2726,2727,2728,2732,2733,2734,2735,2736,2737,2738,2739,2742,2751);\r\n"
				+ ";";
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
		System.out.println(map2.toString());
		Iterator<Map.Entry<String, String>> it = map2.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			String id = entry.getKey();
			String warehouse = entry.getValue();
			if (warehouse.contains(",")) {
				String str2[] = warehouse.split(",");
				for (int i = 0; i < str2.length; i++) {
					if (list.contains(str2[i])) {
						System.out.println("还是有重复数据");
						System.out.println(id + "=" + str2[i]);
					}
					else {
						System.out.println("没有重复数据");
					}

				}

			}

		}
	}
}
