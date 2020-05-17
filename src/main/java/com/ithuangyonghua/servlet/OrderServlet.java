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

	// ������ҳ��ѯ
	protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserEntity userEntity = (UserEntity) req.getSession().getAttribute("user");
		if (userEntity == null) {
			// ��ת����½ҳ��
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
    //�������� itempage��ѯ
	protected void itempage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserEntity userEntity = (UserEntity) req.getSession().getAttribute("user");
		if (userEntity == null) {
			// ��ת����½ҳ��
			resp.sendRedirect(req.getContextPath() + "/jsp/user/login.jsp");
			return;
		}
		String id = req.getParameter("id");
		List<OrderItem> orderItemList =orderItemService.queryItemListByOrderId(id);
		req.setAttribute("itempage", orderItemList);
		req.getRequestDispatcher("/jsp/order/orderitem.jsp").forward(req, resp);
	}
	/**
	 * ��������
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Ԥ��ʱ��
		String createtime = req.getParameter("createtime");
		System.out.println(createtime);
		// ��ȡ���ﳵ
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		// ��ȡ�û�ID
		UserEntity userEntity = (UserEntity) req.getSession().getAttribute("user");
		if (userEntity == null) {// δ��¼
			resp.sendRedirect(req.getContextPath() + "/jsp/user/login.jsp");
			return;
		}
		// ��������
		String orderId = orderService.createOrder(cart, createtime, userEntity.getName());
		req.getSession().setAttribute("orderId", orderId);
		resp.sendRedirect(req.getContextPath() + "/jsp/cart/checkout.jsp");

	}

}
