package com.ithuangyonghua.entity;

public class FoodEntity {
	private String name;
	private String photo;
	private String sort;
	private String restaurant;
	private Double privce;
	private Integer stock;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	public Double getPrivce() {
		return privce;
	}

	public void setPrivce(Double privce) {
		this.privce = privce;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "FoodEntity [name=" + name + ", photo=" + photo + ", sort=" + sort + ", restaurant=" + restaurant
				+ ", privce=" + privce + ", stock=" + stock + "]";
	}

	

	
}
