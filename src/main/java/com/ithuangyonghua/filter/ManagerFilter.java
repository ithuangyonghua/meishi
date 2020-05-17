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

/**
 * 后台管理拦截器
 *
 */
public class ManagerFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response =  (HttpServletResponse) servletResponse;
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("user");
        if(userEntity==null){//没有登陆
            //跳转到登陆页面
            request.getRequestDispatcher("/jsp/user/login.jsp").forward(servletRequest,servletResponse);
        }else{//登陆了
        	//判断是管理员还是用户
        	if(!"hyh19981".equals(userEntity.getName())){
        		request.getSession().setAttribute("qxmsg", "权限不够");
        		 request.getRequestDispatcher("/client/ClientIndexServlet?action=page").forward(servletRequest,servletResponse);
        		return;
        	}
        	filterChain.doFilter(servletRequest,servletResponse);
            
        }
    }

    public void destroy() {

    }
}
