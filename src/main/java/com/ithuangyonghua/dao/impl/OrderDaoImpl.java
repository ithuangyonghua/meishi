package com.ithuangyonghua.dao.impl;

import java.util.List;

import com.ithuangyonghua.dao.OrderDao;
import com.ithuangyonghua.entity.Order;

public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDao {

	public void saveOrader(Order order) {
		// TODO Auto-generated method stub
		String sql = "insert into  `order`(orderId,createTime,price,datetime,status,userid) values (?,?,?,?,?,?)";
		update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getDatetime(), order.getStatus(),
				order.getUserId());
	}

	public int pageCount(String name) {
		String sql = null;
		if(name==null){
			sql =  "select count(*) from `order`";
			Number pageCount = this.getForValue(sql);
			return pageCount.intValue();
		}else{
			sql =  "select count(*) from `order` where userid = ?";
			Number pageCount = this.getForValue(sql,name);
			return pageCount.intValue();
		}
		
		
	}

	public List<Order> queryOrderByPage(int begin, int pageSize,String name) {
		String sql = null;
		if(name==null){
			sql = "select `orderId`,`createTime`,price,datetime,status,userid from `order` limit ?,?";
			return getList(sql, begin,pageSize);
		}else{
			sql = "select `orderId`,`createTime`,price,datetime,status,userid from `order` where userid = ? limit ?,?";
			return getList(sql,name,begin,pageSize);
		}
		
	}

	public void updateOrderByOrderId(String orderId) {
		// TODO Auto-generated method stub
		String sql ="update `order` set status = 1 where `orderId` = ?";
		update(sql, orderId);
	}

}
