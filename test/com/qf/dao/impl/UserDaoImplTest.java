package com.qf.dao.impl;

import java.util.List;

import org.junit.Test;

import com.qf.dao.IUserDao;
import com.qf.entity.User;

public class UserDaoImplTest {

	private IUserDao userDao = new UserDaoImpl();

	@Test
	public void testAddUser() {
		for (int i = 0; i < 1; i++) {

			User user = new User();
			user.setName("qf_" + i);
			user.setPassword("pw_" + i);
			user.setEmail("qf_" + i + "@1000phone.com");
			user.setRole(i % 2 == 0 ? 1 : 0); // 1:管理员，0：普通用户
			user.setSex(i % 2 == 0 ? 1 : 0); // 1:男，0：女
			System.out.println(userDao.addUser(user));
		}
	}

	@Test
	public void testUpdateUser() {
		User user = new User();
		user.setName("qf");
		user.setPassword("qf");
		user.setEmail("qf@1000phone.com");
		user.setRole(0); // 1:管理员，0：普通用户
		user.setSex(0); // 1:男，0：女
		user.setId(1);
		System.out.println(userDao.updateUser(user));
	}

	@Test
	public void testDeleteUser() {
		System.out.println(userDao.deleteUser(1));
	}

	@Test
	public void testGetUserById() {
		System.out.println(userDao.getUserById(211));
	}

	@Test
	public void testGetUserList() {
		List<User> users = userDao.getUserList();
		for (User user : users) {
			System.out.println(user);
		}
	}
	
	@Test
	public void testGetUserList1() {
		List<User> users = userDao.getUserList(0,5);
		for (User user : users) {
			System.out.println(user);
		}
	}
	
	@Test
	public void testGetUserTotalCount() {
		System.out.println(userDao.getUserTotalCount());
	}

}
