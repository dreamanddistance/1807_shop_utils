package com.qf.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

	// ctrl+shift+x:大写
	// ctrl+shift+y:小写
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	private static final String URL = "jdbc:mysql://localhost:3306/1807_shop";
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";

	public static Connection getConnection() {

		try {
			// 1.加载驱动
			Class.forName(DRIVER_NAME);

			// 2.连接数据库
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void close(AutoCloseable... autoCloseable) {
		for (AutoCloseable auto : autoCloseable) {
			if (auto != null) {
				try {
					auto.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
