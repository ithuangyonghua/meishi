package com.ithuangyonghua.dao;

import java.util.List;

import com.ithuangyonghua.entity.Order;

public interface OrderDao {

	void saveOrader(Order order);

	int pageCount(String name);

	List<Order> queryOrderByPage(int begin, int pageSize,String name);

	void updateOrderByOrderId(String orderId);

}
