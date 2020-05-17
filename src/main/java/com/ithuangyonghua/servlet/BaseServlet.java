package com.ithuangyonghua.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//解决POST乱码问题
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		try {
			Method declaredMethod = this.getClass().getDeclaredMethod(action, HttpServletRequest.class,HttpServletResponse.class);
			declaredMethod.invoke(this, req,resp);//执行方法
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();//抛出异常给Filter
		} 
	}
   
}
