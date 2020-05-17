package com.ithuangyonghua.dao.impl;

import java.util.List;

import com.ithuangyonghua.dao.OrderItemDao;
import com.ithuangyonghua.entity.OrderItem;

public class OrderItemDaoImpl extends BaseDaoImpl<OrderItem> implements OrderItemDao {
    
	public void saveOrderItem(OrderItem orderItem) {
		// TODO Auto-generated method stub
		 String sql = "insert into orderitem(`name`,`count`,`price`,`totalprice`,`orderId`) values(?,?,?,?,?)";
	     update(sql, orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
	}

	public List<OrderItem> queryItemListByOrderId(String id) {
		String sql ="select * from orderitem where orderId = ?";
		return this.getList(sql, id);
	}

}
