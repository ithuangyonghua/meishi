package com.ithuangyonghua.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ithuangyonghua.entity.UserEntity;

public class KeepLoginFilter implements Filter{

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		 HttpServletRequest request = (HttpServletRequest) servletRequest;
	        HttpServletResponse response =  (HttpServletResponse) servletResponse;
	        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("user");
	        if(userEntity==null){//没有登陆
	            //跳转到登陆页面
	        	response.sendRedirect(request.getContextPath() + "/jsp/user/login.jsp"); 
	        }else{
	        	chain.doFilter(servletRequest,servletResponse);
	        }
		
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
