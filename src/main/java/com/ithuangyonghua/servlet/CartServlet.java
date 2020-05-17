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
	 * 添加条目
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 首先判断是否登陆,
		UserEntity userEntity = (UserEntity) req.getSession().getAttribute("user");
		if (userEntity == null) {
			// 跳转到登陆页面
			resp.sendRedirect(req.getContextPath() + "/jsp/user/login.jsp");
			return;
		}
		// 商品ID
		String id = req.getParameter("id");
		id = new String(id.getBytes("ISO-8859-1"), "utf-8");
		FoodEntity food = foodService.QueryFoodById(id);
		// 将FoodEntity转换为CartItem
		CartItem cartItem = new CartItem(food.getName(), 1, new BigDecimal(food.getPrivce()),
				new BigDecimal(food.getPrivce()));
		// 创建Cart
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			req.getSession().setAttribute("cart", cart);
		}
		cart.addItem(cartItem);
		req.getSession().setAttribute("lastName", cartItem.getName());
		System.out.println(cart);
		// 重定向到首页
		// resp.sendRedirect(req.getContextPath());
		resp.sendRedirect(req.getHeader("Referer"));
	}

	/**
	 * 删除条目
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
	 * 清空购物车
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
	 * 修改数量
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取数量和商品id
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
