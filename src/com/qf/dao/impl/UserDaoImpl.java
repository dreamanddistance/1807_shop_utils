package com.qf.dao.impl;

import java.util.List;

import com.qf.dao.IUserDao;
import com.qf.entity.User;
import com.qf.utils.DBManager;

public class UserDaoImpl implements IUserDao {

	@Override
	public int addUser(User user) {
		return DBManager.commonInsert("insert into t_user(name,password,sex,role,email) values(?,?,?,?,?)",
				user.getName(), user.getPassword(), user.getSex(), user.getRole(), user.getEmail());
	}

	@Override
	public int updateUser(User user) {
		return DBManager.commonUpdate(
				"update t_user set name = ?,password = ?,sex = ?,role = ?,email = ? where id = ?", user.getName(),
				user.getPassword(), user.getSex(), user.getRole(), user.getEmail(), user.getId());
	}

	@Override
	public int deleteUser(Integer id) {
		return DBManager.commonUpdate("delete from t_user where id = ?", id);
	}

	@Override
	public User getUserById(Integer id) {
		List<User> list = DBManager.commonQuery("select * from t_user where id = ?", User.class, id);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<User> getUserList() {
		return DBManager.commonQuery("select * from t_user", User.class);
	}

	@Override
	public Integer getUserTotalCount() {
		return DBManager.commonCount("select count(1) from t_user");

	}

	@Override
	public List<User> getUserList(Integer index, Integer size) {
		return DBManager.commonQuery("select * from t_user limit ?,?", User.class, index, size);

	}

	@Override
	public void batchDel(String[] ids) {
		StringBuffer buffer = new StringBuffer();
		// delete t_user where id in (1,2,3,34,4,5)
		buffer.append("delete from t_user where id in (");
		for (int i = 0; i < ids.length; i++) {

			if (i == (ids.length) - 1) {
				// 如果条件成立说明这是最后一个循环
				buffer.append(ids[i]).append(")");
			} else {
				buffer.append(ids[i]).append(",");
			}
		}

		// 执行数据库
		DBManager.commonUpdate(buffer.toString());

	}

	@Override
	public User login(String username, String password) {
		String sql = "select * from t_user where name = ? and password = ?";
		List<User> list = DBManager.commonQuery(sql, User.class, username, password);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

}
