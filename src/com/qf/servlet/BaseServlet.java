package com.qf.servlet;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

/**
 * base
 * 
 * @author admin
 *
 */
public class BaseServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1.先获取action的名称
		String actionName = req.getParameter("action");

		// 2.找到actionName对应的方法
		Method method = findActionMethodByActionName(actionName);
		if (method != null) {

			// 3.调用action
			executeAction(method, req, resp);
		} else {
			System.err.println("你还没有定义action：" + actionName);
		}

	}

	public void executeAction(Method method, HttpServletRequest req, HttpServletResponse resp) {
		try {
			// 1.定义一个数组用来装方法的参数
			int parameterCount = method.getParameterCount(); // 获取参数个数
			Object[] paramArray = new Object[parameterCount];

			// 2.处理方法的参数
			methodParam(method, paramArray, req, resp);

			// 3.调用action
			Object resultInfo = method.invoke(this, paramArray);

			// 4.处理action的返回值，并相应用户
			if(resultInfo != null){ // 处理方法没有返回值的情况
				responseClinet(resultInfo.toString(), req, resp);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	private void responseClinet(String resultInfo, HttpServletRequest req, HttpServletResponse resp) {

		// 1.解析aciton的返回值
		Map<String, String> resultMap = parseResultInfoToMap(resultInfo);

		// 2.转发到页面
		String type = resultMap.get("type");
		String page = resultMap.get("page");

		if ("forward".equals(type)) {
			try {
				req.getRequestDispatcher(page).forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if ("redirect".equals(type)) {
			try {
				resp.sendRedirect(page);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private Map<String, String> parseResultInfoToMap(String resultInfo) {
		String[] split = resultInfo.split(":");
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", split[0]); // 转发的类型
		map.put("page", split[1]); // 转发页面
		return map;
	}

	private void methodParam(Method method, Object[] paramArray, HttpServletRequest req, HttpServletResponse resp) {

		// 1.获取方法所有的参数
		Parameter[] parameters = method.getParameters();

		// 获取方法的形参名称
		LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
		String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);

		for (int i = 0; i < parameters.length; i++) {

			// 2.获取方法参数的名称
			String paramSimpleName = parameters[i].getType().getSimpleName();

			// 3.处理参数类型是HttpServletRequest
			if (paramSimpleName.equals("HttpServletRequest")) {
				paramArray[i] = req;
			} else if (paramSimpleName.equals("HttpServletResponse")) { // 处理方法形参是HttpServletResponse
				paramArray[i] = resp;
			} else if (paramSimpleName.equals("String")) { // 处理方法形参是字符串
				// 1.获取形参名称
				String paramName = parameterNames[i];

				// 2.根据方法的形参名称从req总取值
				String value = req.getParameter(paramName);

				// 3.把形参的值放入数组中
				paramArray[i] = value;
			} else if (paramSimpleName.equals("Integer")) {
				String value = req.getParameter(parameterNames[i]);
				// 这里这里接收的是int类型，所以需要转一下
				paramArray[i] = Integer.parseInt(value);
			} else {
				// 方法的形参可能是自定义对象
				try {
					// 1.先实例化对象
					Object objectsIns = parameters[i].getType().newInstance();

					// 2.获取对象的属性名称
					Field[] fields = objectsIns.getClass().getDeclaredFields();
					for (Field field : fields) {
						field.setAccessible(true); // 授权
						String fieldName = field.getName(); // 获取对象的属性名称
						String value = req.getParameter(fieldName); // 根据属性名称从req中获取
						
						// 这样做判断的目的是为了不覆盖对象的默认值
						if(value != null && !"".equals(value)){
							BeanUtils.copyProperty(objectsIns, fieldName, value); // 给对象赋值
						}
					}
					// 3.把对象放入方法形参数组
					paramArray[i] = objectsIns;

				} catch (Exception e) {
					System.err.println("实例化对象失败：" + parameters[i].getType());
				}
			}
		}
	}

	/**
	 * 根据action名称找到方法对象
	 * 
	 * @param actionName
	 * @return
	 */
	private Method findActionMethodByActionName(String actionName) {

		// 1.获取当前类中所有的方法
		Method[] methods = this.getClass().getMethods();
		for (Method method : methods) {

			// 2.获取方法名称
			String methodName = method.getName();

			if (methodName.equals(actionName)) {
				return method;
			}
		}
		return null;
	}
}
