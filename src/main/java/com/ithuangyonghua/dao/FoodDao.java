package com.ithuangyonghua.dao;

import java.util.List;

import com.ithuangyonghua.entity.FoodEntity;

public interface FoodDao{

	int pageCount();

	List<FoodEntity> queryFoodByPage(int begin, int pageSize);

	void addFood(FoodEntity foodEntity);

	void deleteFoodById(String id);

	FoodEntity QueryFoodById(String id);

	void updateFoodById(FoodEntity foodEntity);

}
