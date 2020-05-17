package com.ithuangyonghua.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ithuangyonghua.entity.Page;
import com.ithuangyonghua.service.FoodService;
import com.ithuangyonghua.service.impl.FoodServiceImpl;
import com.ithuangyonghua.utils.Constants;
import com.ithuangyonghua.utils.WebUtils;

public class ClientIndexServlet  extends BaseServlet{
	private FoodService foodService = new FoodServiceImpl();
	
	//∑÷“≥≤È—Ø
	protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt( req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt( req.getParameter("pageSize"),Constants.PAGE_SIZE);
        Page page = foodService.page(pageNo, pageSize);
        page.setUrl("client/ClientIndexServlet?action=page");
        req.setAttribute("page",page);
        req.getRequestDispatcher("/jsp/client/index.jsp").forward(req,resp);
    }
    
} 
