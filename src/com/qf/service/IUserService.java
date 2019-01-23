package com.qf.service;

import java.util.List;

import com.qf.entity.Page;
import com.qf.entity.User;

public interface IUserService {

	/**
	 * 添加用户
	 * 
	 * @param user
	 *            添加的用户对象
	 * @return 影响的行数
	 */
	public int addUser(User user);

	/**
	 * 修改用户
	 * 
	 * @param user
	 *            修改的用户对象
	 * @return 影响的行数
	 */
	public int updateUser(User user);

	/**
	 * 删除用户
	 * 
	 * @param id
	 *            用户的di
	 * @return 影响的行数
	 */
	public int deleteUser(Integer id);

	/**
	 * 查询用户
	 * 
	 * @param id
	 *            用户的id
	 * @return 对应的用户对象
	 */
	public User getUserById(Integer id);

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	public List<User> getUserList();
	
	
	public void getUserPage(Page<User> page);

	
	public void batchDel(String[] ids);

	public User backLogin(String username, String password);
	
}
