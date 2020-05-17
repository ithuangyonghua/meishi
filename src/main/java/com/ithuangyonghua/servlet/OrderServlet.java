package com.ithuangyonghua.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ithuangyonghua.entity.Cart;
import com.ithuangyonghua.entity.OrderItem;
import com.ithuangyonghua.entity.Page;
import com.ithuangyonghua.entity.UserEntity;
import com.ithuangyonghua.service.OrderItemService;
import com.ithuangyonghua.service.OrderService;
import com.ithuangyonghua.service.impl.OrderItemServiceImpl;
import com.ithuangyonghua.service.impl.OrderServiceImpl;
import com.ithuangyonghua.utils.Constants;
import com.ithuangyonghua.utils.WebUtils;

public class OrderServlet extends BaseServlet {

	private OrderService orderService = new OrderServiceImpl();
	private OrderItemService orderItemService = new OrderItemServiceImpl();

	// 订单分页查询
	protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserEntity userEntity = (UserEntity) req.getSession().getAttribute("user");
		if (userEntity == null) {
			// 跳转到登陆页面
			resp.sendRedirect(req.getContextPath() + "/jsp/user/login.jsp");
			return;
		}
		int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
		int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Constants.PAGE_SIZE);
		Page page = orderService.page(pageNo, pageSize,userEntity.getName());
		page.setUrl("client/OrderServlet?action=page");
		req.setAttribute("page", page);
		req.getRequestDispatcher("/jsp/order/order.jsp").forward(req, resp);
	}
    //订单详情 itempage查询
	protected void itempage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserEntity userEntity = (UserEntity) req.getSession().getAttribute("user");
		if (userEntity == null) {
			// 跳转到登陆页面
			resp.sendRedirect(req.getContextPath() + "/jsp/user/login.jsp");
			return;
		}
		String id = req.getParameter("id");
		List<OrderItem> orderItemList =orderItemService.queryItemListByOrderId(id);
		req.setAttribute("itempage", orderItemList);
		req.getRequestDispatcher("/jsp/order/orderitem.jsp").forward(req, resp);
	}
	/**
	 * 创建订单
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 预定时间
		String createtime = req.getParameter("createtime");
		System.out.println(createtime);
		// 获取购物车
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		// 获取用户ID
		UserEntity userEntity = (UserEntity) req.getSession().getAttribute("user");
		if (userEntity == null) {// 未登录
			resp.sendRedirect(req.getContextPath() + "/jsp/user/login.jsp");
			return;
		}
		// 创建订单
		String orderId = orderService.createOrder(cart, createtime, userEntity.getName());
		req.getSession().setAttribute("orderId", orderId);
		resp.sendRedirect(req.getContextPath() + "/jsp/cart/checkout.jsp");

	}

}
