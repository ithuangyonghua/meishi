package com.ithuangyonghua.entity;

import java.math.BigDecimal;

public class CartItem {
    private String name;//名字
    private Integer count;//数量
    private BigDecimal price;//价格
    private BigDecimal totalPrice;//总价

    public CartItem() {
    }

    public CartItem( String name, Integer count, BigDecimal price, BigDecimal totalPrice) {
       
        this.name = name;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
