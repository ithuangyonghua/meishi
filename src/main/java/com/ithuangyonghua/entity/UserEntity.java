package com.ithuangyonghua.entity;

public class UserEntity {
	String name;// �û���
	String password;// ����
	String sex;// �Ա�
	String address;// ��ַ

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
