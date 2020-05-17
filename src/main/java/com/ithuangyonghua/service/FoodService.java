package com.ithuangyonghua.service;

import com.ithuangyonghua.entity.FoodEntity;
import com.ithuangyonghua.entity.Page;

public interface FoodService {

	Page page(int pageNo, int pageSize);

	void addFood(FoodEntity foodEntity);

	void deleteFoodById(String id);

	FoodEntity QueryFoodById(String id);

	void updateFoodById(FoodEntity foodEntity);

}
