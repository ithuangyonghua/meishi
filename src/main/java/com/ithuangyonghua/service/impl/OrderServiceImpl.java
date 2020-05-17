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
		//���涩���� 
		String orderId = System.currentTimeMillis()+"";
		Order order = new Order(orderId, createtime, cart.getTotalPrice(), WebUtils.parseDate(new Date()), 0, username);
		order.setOrderId(orderId);
		orderDao.saveOrader(order);
		//���涩�������
		 for(Map.Entry<String,CartItem> item :cart.getItems().entrySet()){
	            CartItem cartItem = item.getValue();
	            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
	            orderItemDao.saveOrderItem(orderItem);
	            FoodEntity foodEntity = foodDao.QueryFoodById(cartItem.getName());
	            //��������
	            foodEntity.setStock(foodEntity.getStock()+cartItem.getCount());//����
	            foodDao.updateFoodById(foodEntity);
	        }
	        //ע�����ǹ������,���ﳵӦ�ñ����
	        cart.clear();
		return orderId;
	}
	public Page page(int pageNo, int pageSize,String name) {
		Page<Order> page = new Page<Order>();
        //����ÿҳ��ʾ����
        page.setPageSize(pageSize);
        //�����ܼ�¼��
        int PageTotalCount = orderDao.pageCount(name);
        page.setPageTotalCount(PageTotalCount);
        //������ҳ��
        int pageCount= PageTotalCount / pageSize;
        if(PageTotalCount%pageSize!=0){
            pageCount+=1;
        }
        page.setPageTotal(pageCount);
        //���õ�ǰҳ��
        page.setPageNo(pageNo);
        int begin = (pageNo-1) * pageSize;
        //���õ�ǰҳ����
        List<Order> items = orderDao.queryOrderByPage(begin, pageSize,name);
        page.setItems(items);
        return page;
	}
	public void updateOrderByOrderId(String orderId) {
		// TODO Auto-generated method stub
		orderDao.updateOrderByOrderId(orderId);
	}

}
