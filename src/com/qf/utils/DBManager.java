package com.qf.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBManager<T> {

	// DBManager.commonUpdate("insert into
	// t_user(username,password,sex,role,email)
	// values(?,?,?,?,?)",user.getName(),user.getPassword()..);
	/**
	 * 公用的更新操作
	 * 
	 * @param sql
	 *            执行的sql
	 * @param args
	 *            参数
	 * @return 响应的行数
	 */
	public static int commonUpdate(String sql, Object... args) {

		// 1.连接数据库
		Connection connection = DBUtils.getConnection();

		// 2.获取预处理对象
		PreparedStatement prst = null;
		try {
			prst = connection.prepareStatement(sql);

			// 3.给占位符赋值
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					prst.setObject(i + 1, args[i]);
				}
			}

			// 4.执行sql
			return prst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5.关闭资源
			DBUtils.close(connection, prst);
		}
		return 0;
	}
	
	
	/**
	 * 插入操作，返回主键
	 * @param sql 
	 * @param args
	 * @return
	 */
	public static int commonInsert(String sql, Object... args) {
		
		// 1.连接数据库
		Connection connection = DBUtils.getConnection();
		
		// 2.获取预处理对象
		PreparedStatement prst = null;
		try {
			
			prst = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			// 3.给占位符赋值
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					prst.setObject(i + 1, args[i]);
				}
			}
			
			// 4.执行sql
			prst.executeUpdate();
			
			// 5.获取主键
			ResultSet resultSet = prst.getGeneratedKeys();
			resultSet.next();
			return resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5.关闭资源
			DBUtils.close(connection, prst);
		}
		return 0;
	}

	// T/?
	// sql :select * from t_user
	// ssql :select * from t_user where id = ?
	/**
	 * 公共的查询操作
	 * 
	 * @param sql
	 *            sql语句
	 * @param args
	 *            参数
	 * @param cls
	 *            list中装的对象
	 * @return 集合
	 */
	public static <T> List<T> commonQuery(String sql, Class<T> cls, Object... args) {

		// 1.获取连接对象
		Connection connection = DBUtils.getConnection();

		// 2.获取预处理对象
		PreparedStatement prst = null;
		ResultSet resultSet = null;
		List<T> list = new ArrayList<T>();
		try {
			prst = connection.prepareStatement(sql);

			// 3.给占位符赋值
			if (args != null && args.length > 0) {
				for (int i = 0; i < args.length; i++) {
					prst.setObject(i + 1, args[i]);
				}
			}

			// 4.执行sql
			resultSet = prst.executeQuery();

			while (resultSet.next()) {

				// 5.创建一个对象
				T ins = cls.newInstance();

				// 6.获取对象中的所有的属性(因为对象中的属性和表中宏的字段一一对应的)
				Field[] fields = ins.getClass().getDeclaredFields();
				for (int i = 0; i < fields.length; i++) {

					fields[i].setAccessible(true); // 给字段授权

					// 7.获取属性名称
					String name = fields[i].getName();

					// 8.根据对象的属性名称从结果集中获取value值
					Object value = null;
					try {
						value = resultSet.getObject(name);
					} catch (Exception e) {
						System.err.println("字段没有找到:"+name);
						// 读取属性文件
//						Properties properties = new Properties();
//						try {
//							// 加载属性文件
//							properties.load(DBManager.class.getClassLoader().getResourceAsStream("db_user.properties"));
//							
//							// 根据对象的属性名称获取表中的字段名称
//							Object columnName = properties.get(name);
//							
//							// 获取到表中字段的名称再一次取值
//							value = resultSet.getObject(columnName.toString());
//						} catch (IOException e1) {
//							e1.printStackTrace();
//						}
					}

					// 9.给属性赋值
					fields[i].set(ins, value);
				}

				// 10.把对象添加到集合中
				list.add(ins);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(resultSet, prst, connection);
		}

		return list;
	}

	public static int commonCount(String sql) {
		// 1.获取连接对象
		Connection connection = DBUtils.getConnection();

		PreparedStatement prst = null;
		ResultSet resultSet = null;
		try {
			// 2.得到预处理对象
			prst = connection.prepareStatement(sql);

			// 4.执行sql
			resultSet = prst.executeQuery();

			resultSet.next();

			// 5.获取总条数
			return resultSet.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6.关闭资源
			DBUtils.close(connection, prst, resultSet);
		}
		return 0;
	}

}
