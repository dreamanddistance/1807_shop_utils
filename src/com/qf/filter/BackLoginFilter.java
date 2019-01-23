package com.qf.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class BackLoginFilter
 */
public class BackLoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public BackLoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse)response;
		
		// 判断用户是否登录
		String action = req.getParameter("action");
		Object user = req.getSession().getAttribute("user");
		if(user != null || "backLogin".equals(action)){
			chain.doFilter(request, response);
		}else{
			// 不管是重定向还是转发包括open都会参考当前路径
//			当前路径:http://localhost:8080/1807_shop_utils/UserServlet?action=getUserPage
//			转发后的路径：http://localhost:8080/1807_shop_utils/backLogin.jsp
			
//			当前路径:http://localhost:8080/1807_shop_utils/back/main.jsp
//			转发后的路径：http://localhost:8080/1807_shop_utils/login.jsp
			
//			req.getRequestDispatcher("backLogin.jsp").forward(req, response);
			
			// http://localhost:8080/1807_shop_utils/UserServlet?action=getUserPage
			// http://localhost:8080/1807_shop_utils/back/main.jsp
			
//			resp.getWriter().write("<script>alert('你还没有登录，请先登录。。。');window.open('../backLogin.jsp','_parent');</script>");
			String contextPath = req.getServletContext().getContextPath(); // 得到上下文目录(项目的目录)
			System.out.println(contextPath); // /xxxxx
			resp.getWriter().write("<script>alert('你还没有登录，请先登录。。。');window.open('"+contextPath+"/backLogin.jsp','_parent');</script>");
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
