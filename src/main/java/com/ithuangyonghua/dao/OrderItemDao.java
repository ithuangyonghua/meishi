package com.ithuangyonghua.dao;

import java.util.List;

import com.ithuangyonghua.entity.OrderItem;

public interface OrderItemDao {

	void saveOrderItem(OrderItem orderItem);

	List<OrderItem> queryItemListByOrderId(String id);

}
