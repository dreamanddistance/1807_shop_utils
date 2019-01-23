package com.qf.service.impl;

import java.util.List;

import org.apache.tomcat.util.http.fileupload.util.LimitedInputStream;

import com.qf.dao.IUserDao;
import com.qf.dao.impl.UserDaoImpl;
import com.qf.entity.Page;
import com.qf.entity.User;
import com.qf.service.IUserService;

public class UserServiceImpl implements IUserService {

	private IUserDao userDao = new UserDaoImpl();

	@Override
	public int addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public int updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public int deleteUser(Integer id) {
		return userDao.deleteUser(id);
	}

	@Override
	public User getUserById(Integer id) {
		return userDao.getUserById(id);
	}

	@Override
	public List<User> getUserList() {
		return userDao.getUserList();
	}

	@Override
	public void getUserPage(Page<User> page) {

		Integer currentPage = page.getCurrentPage();
		Integer pageSize = page.getPageSize();

		// 1.算出总条数
		Integer totalCount = userDao.getUserTotalCount();

		// 2.算出当前页要展示的数据
		List<User> userList = userDao.getUserList((currentPage - 1) * pageSize, pageSize);

		// 3.算出总页数
		Integer totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;

		// 4.封装到page中
		page.setTotalCount(totalCount);
		page.setTotalPage(totalPage);
		page.setList(userList);

	}

	@Override
	public void batchDel(String[] ids) {
		// 1.性能:delete t_user where id in (1,2,3,34,4,5)
		// 2.安全：尽量的不要在循环中范文数据库
//		for(int i =0;i<ids.length;i++){
//			userDao.deleteUser(Integer.parseInt(ids[i]));
//		}
		userDao.batchDel(ids);
		
	}

	@Override
	public User backLogin(String username, String password) {
		return userDao.login(username,password);
	}

}
