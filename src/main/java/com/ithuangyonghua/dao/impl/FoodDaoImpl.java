package com.ithuangyonghua.dao.impl;

import java.util.List;

import com.ithuangyonghua.dao.FoodDao;
import com.ithuangyonghua.entity.FoodEntity;

public class FoodDaoImpl extends BaseDaoImpl<FoodEntity> implements FoodDao{
    //获取总条数
	public int pageCount() {
		 String sql="select count(*) from food";
	        Number pageCount = this.getForValue(sql);
	        return pageCount.intValue();
	}
    //分页后的数据
	public List<FoodEntity> queryFoodByPage(int begin, int pageSize) {
		String sql = "select `name`,`photo`,sort,restaurant,privce,stock from food limit ?,?";
		return getList(sql, begin,pageSize);
	}
	//添加
	public void addFood(FoodEntity foodEntity) {
		String sql = "insert into food(`name`,photo,sort,restaurant,privce,stock) values (?,?,?,?,?,?)";
		update(sql, foodEntity.getName(),foodEntity.getPhoto(),foodEntity.getSort(),
				foodEntity.getRestaurant(),foodEntity.getPrivce(),foodEntity.getStock());
	}
	//删除
	public void deleteFoodById(String id) {
		String sql ="delete from food where name=?";
		update(sql,id);
	}
	//获取
	public FoodEntity QueryFoodById(String id) {
		String sql = "select `name`,`photo`,sort,restaurant,privce,stock from food where `name` = ?";
		return get(sql, id);
	}
	//修改
	public void updateFoodById(FoodEntity foodEntity) {
		String sql = "update food set `photo`=?, sort=?,restaurant=?,privce=?,stock=? where `name`=?";
		update(sql, foodEntity.getPhoto(),foodEntity.getSort(),
				foodEntity.getRestaurant(),foodEntity.getPrivce(),foodEntity.getStock(),foodEntity.getName());
	}

}
