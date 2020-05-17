package com.ithuangyonghua.service.impl;

import java.util.List;

import com.ithuangyonghua.dao.FoodDao;
import com.ithuangyonghua.dao.impl.FoodDaoImpl;
import com.ithuangyonghua.entity.FoodEntity;
import com.ithuangyonghua.entity.Page;
import com.ithuangyonghua.service.FoodService;

public class FoodServiceImpl implements FoodService {
	
    private FoodDao foodDao = new FoodDaoImpl();
	
    public Page<FoodEntity> page(int pageNo, int pageSize) {
		Page<FoodEntity> page = new Page<FoodEntity>();
        //����ÿҳ��ʾ����
        page.setPageSize(pageSize);
        //�����ܼ�¼��
        int PageTotalCount = foodDao.pageCount();
        page.setPageTotalCount(PageTotalCount);
        //������ҳ��
        int pageCount= PageTotalCount / pageSize;
        if(PageTotalCount%pageSize!=0){
            pageCount+=1;
        }
        page.setPageTotal(pageCount);
        //���õ�ǰҳ��
        page.setPageNo(pageNo);
        int begin = (page.getPageNo()-1) * pageSize;
        //���õ�ǰҳ����
        List<FoodEntity> items = foodDao.queryFoodByPage(begin, pageSize);
        page.setItems(items);
        return page;
	}

	public void addFood(FoodEntity foodEntity) {
		// TODO Auto-generated method stub
		foodEntity.setStock(0);
		foodDao.addFood(foodEntity);
	}

	public void deleteFoodById(String id) {
		// TODO Auto-generated method stub
		foodDao.deleteFoodById(id);
	}

	public FoodEntity QueryFoodById(String id) {
		// TODO Auto-generated method stub
		return foodDao.QueryFoodById(id);
	}

	public void updateFoodById(FoodEntity foodEntity) {
		// TODO Auto-generated method stub
		foodDao.updateFoodById(foodEntity);
	}

}
