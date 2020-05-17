package com.ithuangyonghua.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ithuangyonghua.dao.FoodDao;
import com.ithuangyonghua.dao.OrderDao;
import com.ithuangyonghua.dao.OrderItemDao;
import com.ithuangyonghua.dao.impl.FoodDaoImpl;
import com.ithuangyonghua.dao.impl.OrderDaoImpl;
import com.ithuangyonghua.dao.impl.OrderItemDaoImpl;
import com.ithuangyonghua.entity.Cart;
import com.ithuangyonghua.entity.CartItem;
import com.ithuangyonghua.entity.FoodEntity;
import com.ithuangyonghua.entity.Order;
import com.ithuangyonghua.entity.OrderItem;
import com.ithuangyonghua.entity.Page;
import com.ithuangyonghua.service.OrderService;
import com.ithuangyonghua.utils.WebUtils;

public class OrderServiceImpl implements OrderService {
	private OrderDao orderDao = new OrderDaoImpl();
	private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private FoodDao foodDao = new FoodDaoImpl();
	public String createOrder(Cart cart,String createtime,String username) {
		//保存订单表 
		String orderId = System.currentTimeMillis()+"";
		Order order = new Order(orderId, createtime, cart.getTotalPrice(), WebUtils.parseDate(new Date()), 0, username);
		order.setOrderId(orderId);
		orderDao.saveOrader(order);
		//保存订单详情表
		 for(Map.Entry<String,CartItem> item :cart.getItems().entrySet()){
	            CartItem cartItem = item.getValue();
	            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
	            orderItemDao.saveOrderItem(orderItem);
	            FoodEntity foodEntity = foodDao.QueryFoodById(cartItem.getName());
	            //更新销量
	            foodEntity.setStock(foodEntity.getStock()+cartItem.getCount());//销量
	            foodDao.updateFoodById(foodEntity);
	        }
	        //注意我们购买完后,购物车应该被清空
	        cart.clear();
		return orderId;
	}
	public Page page(int pageNo, int pageSize,String name) {
		Page<Order> page = new Page<Order>();
        //设置每页显示数量
        page.setPageSize(pageSize);
        //设置总记录数
        int PageTotalCount = orderDao.pageCount(name);
        page.setPageTotalCount(PageTotalCount);
        //设置总页码
        int pageCount= PageTotalCount / pageSize;
        if(PageTotalCount%pageSize!=0){
            pageCount+=1;
        }
        page.setPageTotal(pageCount);
        //设置当前页码
        page.setPageNo(pageNo);
        int begin = (pageNo-1) * pageSize;
        //设置当前页数据
        List<Order> items = orderDao.queryOrderByPage(begin, pageSize,name);
        page.setItems(items);
        return page;
	}
	public void updateOrderByOrderId(String orderId) {
		// TODO Auto-generated method stub
		orderDao.updateOrderByOrderId(orderId);
	}

}
