package com.qf.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qf.entity.Page;
import com.qf.entity.User;
import com.qf.service.IUserService;
import com.qf.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends BaseServlet {

	private IUserService userService = new UserServiceImpl();

	public String getUserList(HttpServletRequest request) {
		// 1.先调用service层获取数据
		List<User> userList = userService.getUserList();

		// 2.把userList放入到req作用域中
		request.setAttribute("userList", userList);

		// 3.跳转到页面显示
		return "forward:back/user/userinfo.jsp";
	}

	public String addUser(User user) {
		userService.addUser(user);
		return "redirect:UserServlet?action=getUserPage";
	}

	public String getUserById(HttpServletRequest request, Integer id) {

		// 2.调用service根据id查询对象
		User user = userService.getUserById(id);

		// 3.把user放入到req作用域
		request.setAttribute("user", user);

		return "forward:back/user/updateuser.jsp";
	}

	public String updateUser(User user) {
		// 2.更新用户
		userService.updateUser(user);
		return "redirect:UserServlet?action=getUserPage";
	}

	public String deleteUser(Integer id) {

		// 2.删除
		userService.deleteUser(id);

		return "redirect:UserServlet?action=getUserPage";
	}

	public String getUserPage(HttpServletRequest request, Page<User> page) {

		userService.getUserPage(page);

		// 3.把page对象放入req作用域中
		page.setUrl("UserServlet?action=getUserPage");

		request.setAttribute("page", page);

		// 4.跳转到页面显示
		return "forward:back/user/userinfo.jsp";
	}

	public void batchDel(HttpServletRequest request) {
		String id = request.getParameter("ids[]");
		String[] ids = request.getParameterValues("ids[]");
		userService.batchDel(ids);
	}

	public void backLogin(String username, String password, HttpServletRequest request,HttpServletResponse response) {

		// 1.判断用户是否存在
		User user = userService.backLogin(username, password);
		if (user != null) {

			// 2.判断用户是否是管理员
			if ("1".equals(user.getRole().toString())) {
				request.getSession().setAttribute("user", user);
				try {
					// 如果是管理员跳转到后台的首页
					response.sendRedirect("back/main.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				try {
					response.getWriter().write("<script>alert('你不是管理员，请联系管理员授权！！');location.href='backLogin.jsp'</script>");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} else {
			try {
				response.getWriter().write("<script>alert('用户名或密码错误');location.href='backLogin.jsp'</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
