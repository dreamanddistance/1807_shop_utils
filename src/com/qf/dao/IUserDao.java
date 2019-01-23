package com.qf.dao;

import java.util.List;

import com.qf.entity.User;

public interface IUserDao {

	/**
	 * 添加用户
	 * @param user 添加的用户对象
	 * @return 影响的行数
	 */
	public int addUser(User user);
	

	/**
	 * 修改用户
	 * @param user 修改的用户对象
	 * @return 影响的行数
	 */
	public int updateUser(User user);
	
	
	/**
	 * 删除用户
	 * @param id 用户的di
	 * @return 影响的行数
	 */
	public int deleteUser(Integer id);
	
	/**
	 * 查询用户
	 * @param id 用户的id
	 * @return 对应的用户对象
	 */
	public User getUserById(Integer id);
	
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<User> getUserList();


	/**
	 * 获取总条数
	 * @return
	 */
	public Integer getUserTotalCount();


	/**
	 * 查询数据
	 * @param index 起始的行数
	 * @param size 偏移量
	 * @return 集合
	 */
	public List<User> getUserList(Integer index, Integer size);


	public void batchDel(String[] ids);


	public User login(String username, String password);
}
