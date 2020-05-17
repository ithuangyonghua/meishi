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
 * �û�ģ���Servlet
 * @author Hyh
 *
 */
public class UserServlet extends BaseServlet{
	 private UserService userService = new  UserServiceImpl();
	/**
	 * �û����Ƿ����
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void exitsUserName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          //��ȡ�û���
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
     * ע��
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
	protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          //��ȡ��֤��
		  String yzm = (String) req.getSession().getAttribute("KAPTCHA_SESSION_KEY");
		  //���session�е���֤��
		  req.getSession().removeAttribute("KAPTCHA_SESSION_KEY");
		  System.out.println("��ȡ����֤��" + yzm);
		  //
		  UserEntity UserBean = WebUtils.copyParamToBean(new UserEntity(), req.getParameterMap());
          String reyzm = req.getParameter("yzm");
          if(reyzm.equals(yzm)){//������ȷ
        	  //����Ҫ�ж��û����Ƿ����
        	  UserEntity exitsUserByUserName = userService.exitsUserByUserName(UserBean.getName());
        	  if(exitsUserByUserName!=null){
        		  req.setAttribute("msg","�û����Ѵ���");
        		  req.getRequestDispatcher("/jsp/user/regist.jsp").forward(req,resp);
        		  
        	  }else{//ע��-->����ע��ɹ�ҳ��  ֻ�����ض���ķ���(��ֹ���ظ��ύ)
                  userService.insertUser(UserBean);
                  req.getRequestDispatcher("/jsp/user/regist_success.jsp").forward(req,resp);
              }
          }else{//����
        	  req.setAttribute("msg","��֤�����");
              req.setAttribute("username",UserBean.getName());
              System.out.println("��֤��[" + reyzm + "]����");
              req.getRequestDispatcher("/jsp/user/regist.jsp").forward(req,resp);
          }
		  
	}
	/**
	 * ��½
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 UserEntity UserBean = WebUtils.copyParamToBean(new UserEntity(), req.getParameterMap());
		 UserEntity loginUserEntity = userService.queryUserByUserNameAndPwd(UserBean);
		 if(loginUserEntity!=null){
      			//��½�ɹ�
	            req.getSession().setAttribute("user",loginUserEntity);
	            req.getRequestDispatcher("/jsp/user/login_success.jsp").forward(req,resp);
	        }else{
	            //��½ʧ��
	            req.setAttribute("msg","�û������������");
	            req.setAttribute("username",UserBean.getName());
	            req.getRequestDispatcher("/jsp/user/login.jsp").forward(req,resp);
	        }
	}	
	/**
	 * ע��
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
