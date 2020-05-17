package com.ithuangyonghua.dao.impl;

import com.ithuangyonghua.dao.UserDao;
import com.ithuangyonghua.entity.UserEntity;

public class UserDaoImpl extends BaseDaoImpl<UserEntity> implements UserDao{
    
	public UserEntity exitsUserByUserName(String userName) {
		UserEntity userEntity = null;
		String sql = "select * from user where name=?";
		userEntity = this.get( sql, userName);
		return userEntity;
	}

	public UserEntity queryUserByUserNameAndPwd(UserEntity userEntity) {
		UserEntity resultUserEntity = null;
		String sql = "select * from user where name=? and password = ?";
		resultUserEntity = this.get(sql, userEntity.getName(),userEntity.getPassword());
		return resultUserEntity;
	}

	public int insertUser(UserEntity userEntity) {
		String sql="insert into user(name,password,sex,address) values(?,?,?,?)";
		return this.update(sql, userEntity.getName(),userEntity.getPassword(),userEntity.getSex(),userEntity.getAddress());
	}

}
