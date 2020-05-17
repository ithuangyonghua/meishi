package com.ithuangyonghua.service;

import java.util.List;

import com.ithuangyonghua.entity.OrderItem;

public interface OrderItemService {

	List<OrderItem> queryItemListByOrderId(String id);

}
