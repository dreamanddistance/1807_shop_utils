package com.qf.utils;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import com.qf.entity.User;

public class DBManagerTest extends DBManager<User> {

	@Test
	public void test() {
		Connection connection = DBUtils.getConnection();
		System.out.println(connection);
	}

	@Test
	public void test1() {

		User user = new User();
		user.setId(10);
		System.out.println(user.getId());

		// 利用反射实现

		try {
			// 实例化对象
			User userIns = User.class.newInstance(); // new User();

			// 找到setId那个方法
			Method method = userIns.getClass().getMethod("setId", Integer.class);

			// 调用setId方法 -->setId(100);
			method.invoke(userIns, 100);

			// 找到getId方法
			Method method2 = userIns.getClass().getMethod("getId");

			Object result = method2.invoke(userIns);
			System.out.println("resultInfo:" + result);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void test2() {
		System.out.println(DBManagerTest.class.getName());
		System.out.println(DBManagerTest.class.getSimpleName());

		User user = new User();

		try {
			BeanUtils.copyProperty(user, "id", "12");
			System.out.println(user.getId());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		;
	}

}
