package com.ithuangyonghua.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private String orderId;
    private String createTime;//Ԥ��ʱ��
    private BigDecimal price;
    private String datetime;//����ʱ��
    // 0 δ������1 �ѷ�����2 ��ʾ��ǩ��
    private Integer  status= 0;
    private String userId;

    public Order() {
    }

	public Order(String orderId, String createTime, BigDecimal price, String datetime, Integer status, String userId) {
		super();
		this.orderId = orderId;
		this.createTime = createTime;
		this.price = price;
		this.datetime = datetime;
		this.status = status;
		this.userId = userId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
 
   
}
