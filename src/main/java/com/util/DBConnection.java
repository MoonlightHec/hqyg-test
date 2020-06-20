package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.testng.annotations.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import redis.clients.jedis.Jedis;

/**
 * 数据库连接
 * 
 * @author gaozhongming
 *
 */
public class DBConnection {

	/**
	 * mysql数据库连接
	 * 
	 * @param sql
	 * @return
	 */
	public static Map<String, String> mySqlConnect(String sql, String dataBaseName) {

		// 声明Connection对象
		Connection conn;
		// 驱动程序名
		String driver = "com.mysql.jdbc.Driver";
		// ISS数据库配置
		String url = "jdbc:mysql://10.60.46.82:3306/" + dataBaseName;
		String user = "liaoweizhi";
		String password = "E4568DDA7";
		// 库存数据
		Map<String, String> inventoryData = new HashMap<>();

		// 遍历查询结果集
		try {
			// 加载驱动程序
			Class.forName(driver);
			// getConnection()方法，连接MySQL数据库！！
			conn = DriverManager.getConnection(url, user, password);
			// 创建statement类对象，用来执行SQL语句！！
			Statement statement = conn.createStatement();
			// ResultSet类，用来存放获取的结果集！！
			ResultSet rs = statement.executeQuery(sql);
			ResultSetMetaData data = rs.getMetaData();
			int columnCount = rs.getMetaData().getColumnCount();
			int i = 0;
			while (rs.next()) {				
				while (i < columnCount) {
					i++;
					inventoryData.put(data.getColumnName(i), rs.getString(i));
				}
			}
			rs.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// 数据库驱动类异常处理
			System.out.println("Sorry,can't find the Driver!");
			e.printStackTrace();
		} catch (SQLException e) {
			// 数据库连接失败异常处理
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return inventoryData;
	}

	/**
	 * mongodb 连接
	 * 
	 * @return
	 * @return
	 * @return
	 */

	public static MongoClient mongoDBConnection(String ip, int port) {
		MongoClient mongoClient = null;
		try {
			// 连接到 mongodb 服务
			mongoClient = new MongoClient(ip, port);
			// 连接到数据库
			System.out.println("Connect to database successfully！！！");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return mongoClient;
	}	

	// 插入文档
	/**
	 * 1.创建文档 org.bson.Document 参数为key-value的格式   2. 创建文档集合List<Document> 
	 * 3.将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用
	 * mongoCollection.insertOne(Document)
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public static void insertedDocument() throws InterruptedException {
		for (int i = 200000; i < 300000; i++) {
			Thread.sleep(10);
			MongoClient mongoClient = mongoDBConnection("localhost", 27017);
			MongoDatabase mongoDatabase = mongoClient.getDatabase("mongo_test");
			MongoCollection<Document> collection = mongoDatabase.getCollection("col");
			// 插入
			Document document = new Document("title", "MongoDB" + i).append("description", "database").append("likes", 100).append("by", "Fly");
			List<Document> documents = new ArrayList<Document>();
			documents.add(document);
			collection.insertMany(documents);
			System.out.println("文档插入成功");
		}
	}

	// 检索所有文档
	/**
	 * 1. 获取迭代器FindIterable<Document> 2. 获取游标MongoCursor<Document> 3. 通过游标遍历检索出的文档集合
	 * 
	 */

	@Test
	public static void queryDocument() {

		MongoClient mongoClient = mongoDBConnection("localhost", 27017);
		MongoDatabase mongoDatabase = mongoClient.getDatabase("mongo_test");
		MongoCollection<Document> collection = mongoDatabase.getCollection("col");
		// 检索
		FindIterable<Document> findIterable = collection.find();
		MongoCursor<Document> mongoCursor = findIterable.iterator();
		while (mongoCursor.hasNext()) {
			System.out.println(mongoCursor.next());
		}
	}

	// 更新文档

	@Test
	public static void updateDocument() {

		MongoClient mongoClient = mongoDBConnection("localhost", 27017);
		MongoDatabase mongoDatabase = mongoClient.getDatabase("mongo_test");
		MongoCollection<Document> collection = mongoDatabase.getCollection("col");
		// 更新文档 将文档中likes=100的文档修改为likes=200
		collection.updateMany(Filters.eq("likes", 100), new Document("$set", new Document("likes", 200)));
	}

	// 删除文档
	@Test
	public static void deleteDocument() {
		
		MongoClient mongoClient = mongoDBConnection("localhost", 27017);
		MongoDatabase mongoDatabase = mongoClient.getDatabase("mongo_test");
		MongoCollection<Document> collection = mongoDatabase.getCollection("col");	
		// 删除所有符合条件的文档
		collection.deleteMany(Filters.eq("likes", 200));
	}
	
	/**
	 * java 操作redis
	 */
	@Test 
	public static void redisConnecTion() {
		// 连接本地的 Redis 服务
		@SuppressWarnings("resource")
		Jedis jedis = new Jedis("localhost");
		System.out.println("连接成功");
		//设置 redis 字符串数据
        jedis.set("runoobkey", "www.runoob");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));
	}
}
