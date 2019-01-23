package com.qf.servlet;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import com.qf.entity.User;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;

/**
 * Servlet implementation class DemoServlet
 */
public class DemoServlet extends BaseServlet {

	public String getUserList() {
		System.out.println("DemoServlet.getUserList()");
		return "";
	}
	
	// 自动注入进来
	public String getUserList1(HttpServletRequest request,HttpServletRequest resp) {
		System.out.println("DemoServlet.getUserList1()");
		System.out.println("req:"+request);
		System.out.println("resp:"+resp);
		return "";
	}
	
	public String getUserById(Integer id){
		System.out.println("DemoServlet.getUserById() id:"+id);
		return "";
	}
	
	public String login(String username,String password){
		System.out.println("DemoServlet.lgoin() username:"+username);
		System.out.println("DemoServlet.lgoin() password:"+password);
		return "";
	}
	
	public String addUser(User user){
		System.out.println("DemoServlet.addUser()");
		System.out.println(user);
//		return "forward:ok.jsp";
		return "redirect:ok.jsp";
	}
	
	
	public static void main(String[] args) throws Exception, SecurityException {
		
		Method method = DemoServlet.class.getMethod("login",String.class,String.class);
		
		Parameter[] parameters = method.getParameters();
		for (Parameter parameter : parameters) {
			System.out.println(parameter.getName());
		}
		
		LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
		String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
		for (String string : parameterNames) {
			System.out.println(string);
		}
	}
	
}

