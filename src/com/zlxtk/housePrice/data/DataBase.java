package com.zlxtk.housePrice.data;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBase {
	// 驱动程序名
	private static String driver;

	// 要查询的数据库名
	private static String dataBaseName;
	// 要查询的数据库名
	private static String url;
	// 数据库用户名
	private static String user;
	// 密码
	private static String password;

	static {
		InputStream in = null;
		try {
			Properties prop = new Properties();
			// 获取项目路径
			File directory = new File("");// 参数为空
			String courseFile = directory.getCanonicalPath() + File.separator + "db.properties";
			// 读取属性文件
			FileInputStream fi = new FileInputStream(courseFile);
			in = new BufferedInputStream(fi);
			prop.load(in); /// 加载属性列表
			driver = prop.getProperty("driver");
			dataBaseName = prop.getProperty("dataBaseName");
			url = prop.getProperty("url") + "/" + dataBaseName + "?characterEncoding=UTF-8&useUnicode=true";
			user = prop.getProperty("user");
			password = prop.getProperty("password");
		} catch (Exception e) {
			System.out.println("数据库配置文件加载失败！！！");
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				// nothing
			}
		}
	}

	/**
	 * 获取连接数据库的链接
	 * 
	 * @return
	 */
	protected Connection getConnection() throws Exception {
		Connection conn = null;
		// 加载驱动程序
		Class.forName(driver);
		// 连续数据库
		conn = DriverManager.getConnection(url, user, password);
		return conn;
	}

	/**
	 * 关闭链接
	 * 
	 * @param conn
	 * @param statement
	 * @param rs
	 * @throws SQLException
	 */
	protected void closeConnection(Connection conn) throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DataBase ds = new DataBase();
			System.out.println(ds.dataBaseName);
			System.out.println(ds.user);
			System.out.println(ds.password);
			Connection conn = ds.getConnection();
			ds.closeConnection(conn);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
