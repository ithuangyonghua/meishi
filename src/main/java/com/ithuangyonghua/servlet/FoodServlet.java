package com.ithuangyonghua.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ithuangyonghua.entity.FoodEntity;
import com.ithuangyonghua.entity.Page;
import com.ithuangyonghua.service.FoodService;
import com.ithuangyonghua.service.impl.FoodServiceImpl;
import com.ithuangyonghua.utils.Constants;
import com.ithuangyonghua.utils.WebUtils;

public class FoodServlet extends BaseServlet{
	private FoodService foodService = new FoodServiceImpl();
    //分页查询
	protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        int pageNo = WebUtils.parseInt( req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt( req.getParameter("pageSize"),Constants.PAGE_SIZE);
        Page page = foodService.page(pageNo, pageSize);
        page.setUrl("manager/FoodServlet?action=page");
        req.setAttribute("page",page);
        req.getRequestDispatcher("/jsp/manager/food_manager.jsp").forward(req,resp);
    }
	//添加
	protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取pageNo
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        pageNo +=1;
        req.setCharacterEncoding("UTF-8");
        FoodEntity foodEntity = WebUtils.copyParamToBean(new FoodEntity(), req.getParameterMap());
        foodService.addFood(foodEntity);
        resp.sendRedirect(req.getContextPath() + "/manager/FoodServlet?action=page&pageNo="+pageNo);
    }
	
	protected void getFood(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		id=new String(id.getBytes("iso-8859-1"), "UTF-8");
		FoodEntity foodEntity = foodService.QueryFoodById(id);
        req.setAttribute("foodEntity",foodEntity);
        req.getRequestDispatcher("/jsp/manager/food_edit.jsp").forward(req,resp);
    }
	 //修改
	 protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        req.setCharacterEncoding("UTF-8");
	        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
	        FoodEntity foodEntity = WebUtils.copyParamToBean(new FoodEntity(), req.getParameterMap());
	        foodService.updateFoodById(foodEntity);
	        resp.sendRedirect(req.getContextPath() + "/manager/FoodServlet?action=page&pageNo="+pageNo);
	    }
	 //删除
	  protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
	        String id = req.getParameter("id");
	        id=new String(id.getBytes("iso-8859-1"), "UTF-8");
	        foodService.deleteFoodById(id);
	        resp.sendRedirect(req.getContextPath() + "/manager/FoodServlet?action=page&pageNo="+pageNo);
	    }
   
}
