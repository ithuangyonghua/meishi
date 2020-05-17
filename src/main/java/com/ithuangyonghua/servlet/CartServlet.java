package com.ithuangyonghua.servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ithuangyonghua.entity.Cart;
import com.ithuangyonghua.entity.CartItem;
import com.ithuangyonghua.entity.FoodEntity;
import com.ithuangyonghua.entity.UserEntity;
import com.ithuangyonghua.service.FoodService;
import com.ithuangyonghua.service.impl.FoodServiceImpl;
import com.ithuangyonghua.utils.WebUtils;

public class CartServlet extends BaseServlet {
	private FoodService foodService = new FoodServiceImpl();

	/**
	 * �����Ŀ
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// �����ж��Ƿ��½,
		UserEntity userEntity = (UserEntity) req.getSession().getAttribute("user");
		if (userEntity == null) {
			// ��ת����½ҳ��
			resp.sendRedirect(req.getContextPath() + "/jsp/user/login.jsp");
			return;
		}
		// ��ƷID
		String id = req.getParameter("id");
		id = new String(id.getBytes("ISO-8859-1"), "utf-8");
		FoodEntity food = foodService.QueryFoodById(id);
		// ��FoodEntityת��ΪCartItem
		CartItem cartItem = new CartItem(food.getName(), 1, new BigDecimal(food.getPrivce()),
				new BigDecimal(food.getPrivce()));
		// ����Cart
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			req.getSession().setAttribute("cart", cart);
		}
		cart.addItem(cartItem);
		req.getSession().setAttribute("lastName", cartItem.getName());
		System.out.println(cart);
		// �ض�����ҳ
		// resp.sendRedirect(req.getContextPath());
		resp.sendRedirect(req.getHeader("Referer"));
	}

	/**
	 * ɾ����Ŀ
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		String id = req.getParameter("id");
		id = new String(id.getBytes("ISO-8859-1"), "utf-8");
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		if (cart != null) {
			cart.deleteItem(id);
		}
		resp.sendRedirect(req.getHeader("Referer"));
	}

	/**
	 * ��չ��ﳵ
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		if (cart != null) {
			cart.clear();
		}
		resp.sendRedirect(req.getHeader("Referer"));
	}

	/**
	 * �޸�����
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ��ȡ��������Ʒid
		String id = req.getParameter("id");
		id = new String(id.getBytes("ISO-8859-1"), "utf-8");
		int count = WebUtils.parseInt(req.getParameter("count"), 1);
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		if (cart != null) {
			cart.updateCount(id, count);
		}
		resp.sendRedirect(req.getHeader("Referer"));
	}
}
