package com.ithuangyonghua.entity;

public class UserEntity {
	String name;// 用户名
	String password;// 密码
	String sex;// 性别
	String address;// 地址

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "UserEntity [name=" + name + ", password=" + password + ", sex=" + sex + ", address=" + address + "]";
	}

}
