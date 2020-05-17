package com.ithuangyonghua.service.impl;

import java.util.List;

import com.ithuangyonghua.dao.OrderItemDao;
import com.ithuangyonghua.dao.impl.OrderItemDaoImpl;
import com.ithuangyonghua.entity.OrderItem;
import com.ithuangyonghua.service.OrderItemService;

public class OrderItemServiceImpl implements OrderItemService {
     private OrderItemDao orderItemDao = new OrderItemDaoImpl();

	public List<OrderItem> queryItemListByOrderId(String id) {
		return orderItemDao.queryItemListByOrderId(id);
	}
}
