package com.ithuangyonghua.service;

import com.ithuangyonghua.entity.Cart;
import com.ithuangyonghua.entity.Page;

public interface OrderService {

	String createOrder(Cart cart,String createtime,String username);

	Page page(int pageNo, int pageSize,String name);

	void updateOrderByOrderId(String orderId);

}
