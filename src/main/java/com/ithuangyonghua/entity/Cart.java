package com.ithuangyonghua.entity;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    // private Integer totalCount;
    // private BigDecimal totalPrice;
    /*** key 是商品编号， * value，是商品信息 */
    private Map<String, CartItem> items = new LinkedHashMap<String, CartItem>();

    /*** 添加商品项 ** @param cartItem */
    public void addItem(CartItem cartItem) {
        // 先查看购物车中是否已经添加过此商品，如果已添加，则数量累加，总金额更新，如果没有添加过，直接放到 集合中即可
        CartItem item = items.get(cartItem.getName());
        if (item == null) { // 之前没添加过此商品
            items.put(cartItem.getName(), cartItem);
        } else { // 已经 添加过的情况
            item.setCount(item.getCount() + 1); // 数量 累加
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount()))); // 更 新总金额
        }
    }

    /*** 删除商品项 */
    public void deleteItem(String name) {
        items.remove(name);
    }

    /**
     * 清空购物车
     */

    public void clear() {
        items.clear();
    }

    /*** 修改商品数量 */
    public void updateCount(String  name, Integer count) {
        // 先查看购物车中是否有此商品。如果有，修改商品数量，更新总金额
        CartItem cartItem = items.get(name);
        if (cartItem != null) {
            cartItem.setCount(count);// 修改商品数量
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount()))); // 更新总金额
        }
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (Map.Entry<String, CartItem> entry : items.entrySet()) {
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<String, CartItem> entry : items.entrySet()) {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    public Map<String, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<String, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" + "totalCount=" + getTotalCount() + ", totalPrice=" + getTotalPrice() + ", items=" + items + '}';
    }
}
