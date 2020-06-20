package com.hqyg.test1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author 作者 zhangshiping:
 * @version 创建时间：2019年8月28日 上午11:04:13 类说明
 */
public class ToHeavy {

	public static void main(String[] args) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> map2 = new HashMap<String, String>();
		Map<String, String> map3 = new HashMap<String, String>();
		List<String> list1 = new ArrayList<String>();

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

		String dbURL = "jdbc:mysql://111.230.26.36:3306/atms?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true";
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(dbURL, "root", "gzm_1993NBA");
		String sql = "SELECT id,warehouse from mock_rule ;";
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

		Iterator<Map.Entry<String, String>> it = map2.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			String id = entry.getKey();
			String warehouse = entry.getValue();
			if (warehouse.contains(",")) {
				String str2[] = warehouse.split(",");
				for (String string : str2) {
					if (list.contains(string)) {
						if (!id.equals("23") && !id.equals("2671") && !id.equals("2667")) {
							
							System.out.println(id); System.out.println(warehouse); list1.add(id + "=" + warehouse);	
							
						}
						
				}
				}
			} 
			
			else {

				if (list.contains(warehouse)) {

				
					if (!id.equals("23") && !id.equals("2671") && !id.equals("2667")) {
						
						System.out.println(id); System.out.println(warehouse); list1.add(id + "=" + warehouse);	
						
					}

				}
			}
		}

		System.out.println("需要剔除的数据条数为：" + list1.size());
		Set<Integer> set = new TreeSet<Integer>();
		for (String string : list1) {
			String[] str4 = string.split("=");
			set.add(Integer.parseInt(str4[0]));
		}
		System.out.println("需要剔除数据ID大小为"+set.size());
		for (int string2 : set) {
			
			System.out.println("需要剔除数据的ID为"+string2);
		}

	}

}
