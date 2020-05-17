package com.ithuangyonghua.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private String orderId;
    private String createTime;//预定时间
    private BigDecimal price;
    private String datetime;//创建时间
    // 0 未发货，1 已发货，2 表示已签收
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
