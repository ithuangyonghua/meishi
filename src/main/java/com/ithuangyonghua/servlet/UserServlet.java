package com.ithuangyonghua.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ithuangyonghua.entity.UserEntity;
import com.ithuangyonghua.service.UserService;
import com.ithuangyonghua.service.impl.UserServiceImpl;
import com.ithuangyonghua.utils.WebUtils;

/**
 * 用户模块的Servlet
 * @author Hyh
 *
 */
public class UserServlet extends BaseServlet{
	 private UserService userService = new  UserServiceImpl();
	/**
	 * 用户名是否存在
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void exitsUserName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          //获取用户名
		  String username = req.getParameter("username");
		  Gson gson = new Gson();
		  PrintWriter writer = resp.getWriter();
		  Map<String,Object> map = new HashMap<String,Object>();
		  UserEntity exitsUserByUserName = userService.exitsUserByUserName(username);
		  if(exitsUserByUserName==null){
			  map.put("code", true);
		  }else{
			  map.put("code", false);
		  }
		  String json = gson.toJson(map);
		  writer.write(json);
	}	
	
    /**
     * 注册
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
	protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          //获取验证码
		  String yzm = (String) req.getSession().getAttribute("KAPTCHA_SESSION_KEY");
		  //清楚session中的验证码
		  req.getSession().removeAttribute("KAPTCHA_SESSION_KEY");
		  System.out.println("获取的验证码" + yzm);
		  //
		  UserEntity UserBean = WebUtils.copyParamToBean(new UserEntity(), req.getParameterMap());
          String reyzm = req.getParameter("yzm");
          if(reyzm.equals(yzm)){//输入正确
        	  //还需要判断用户名是否存在
        	  UserEntity exitsUserByUserName = userService.exitsUserByUserName(UserBean.getName());
        	  if(exitsUserByUserName!=null){
        		  req.setAttribute("msg","用户名已存在");
        		  req.getRequestDispatcher("/jsp/user/regist.jsp").forward(req,resp);
        		  
        	  }else{//注册-->返回注册成功页面  只能用重定向的方法(防止表单重复提交)
                  userService.insertUser(UserBean);
                  req.getRequestDispatcher("/jsp/user/regist_success.jsp").forward(req,resp);
              }
          }else{//返回
        	  req.setAttribute("msg","验证码错误");
              req.setAttribute("username",UserBean.getName());
              System.out.println("验证码[" + reyzm + "]错误");
              req.getRequestDispatcher("/jsp/user/regist.jsp").forward(req,resp);
          }
		  
	}
	/**
	 * 登陆
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 UserEntity UserBean = WebUtils.copyParamToBean(new UserEntity(), req.getParameterMap());
		 UserEntity loginUserEntity = userService.queryUserByUserNameAndPwd(UserBean);
		 if(loginUserEntity!=null){
      			//登陆成功
	            req.getSession().setAttribute("user",loginUserEntity);
	            req.getRequestDispatcher("/jsp/user/login_success.jsp").forward(req,resp);
	        }else{
	            //登陆失败
	            req.setAttribute("msg","用户名或密码错误");
	            req.setAttribute("username",UserBean.getName());
	            req.getRequestDispatcher("/jsp/user/login.jsp").forward(req,resp);
	        }
	}	
	/**
	 * 注销
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
	}
}
