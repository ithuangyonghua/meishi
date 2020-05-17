package com.ithuangyonghua.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ithuangyonghua.entity.OrderItem;
import com.ithuangyonghua.entity.Page;
import com.ithuangyonghua.entity.UserEntity;
import com.ithuangyonghua.service.OrderItemService;
import com.ithuangyonghua.service.OrderService;
import com.ithuangyonghua.service.impl.OrderItemServiceImpl;
import com.ithuangyonghua.service.impl.OrderServiceImpl;
import com.ithuangyonghua.utils.Constants;
import com.ithuangyonghua.utils.WebUtils;

public class ManagerOrderServlet extends BaseServlet {
	private OrderService orderService = new OrderServiceImpl();
	private OrderItemService orderItemService = new OrderItemServiceImpl();

	protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserEntity userEntity = (UserEntity) req.getSession().getAttribute("user");
		if (userEntity == null) {
			// 跳转到登陆页面
			resp.sendRedirect(req.getContextPath() + "/jsp/user/login.jsp");
			return;
		}
		int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
		int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Constants.PAGE_SIZE);
		Page page = orderService.page(pageNo, pageSize, null);
		page.setUrl("manager/ManagerOrderServlet?action=page");
		req.setAttribute("page", page);
		req.getRequestDispatcher("/jsp/manager/order_manager.jsp").forward(req, resp);
	}

	// 订单详情 itempage查询
	protected void itempage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserEntity userEntity = (UserEntity) req.getSession().getAttribute("user");
		if (userEntity == null) {
			// 跳转到登陆页面
			resp.sendRedirect(req.getContextPath() + "/jsp/user/login.jsp");
			return;
		}
		String id = req.getParameter("id");
		List<OrderItem> orderItemList = orderItemService.queryItemListByOrderId(id);
		req.setAttribute("itempage", orderItemList);
		req.getRequestDispatcher("/jsp/manager/orderitem_manager.jsp").forward(req, resp);
	}

	/**
	 * 审核通过
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateStatus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String orderId = req.getParameter("orderId");

		Gson gson = new Gson();
		PrintWriter writer = resp.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			orderService.updateOrderByOrderId(orderId);
			map.put("code", true);
		} catch (Exception e) {
			map.put("code", false);
			e.printStackTrace();
		}
		String json = gson.toJson(map);
		writer.write(json);
	}

}
