package com.ithuangyonghua.dao;

import com.ithuangyonghua.entity.UserEntity;

public interface UserDao {
	/**
	 * 根据用户名查询用户是否存在
	 * 
	 * @param userName
	 * @return
	 */
	UserEntity exitsUserByUserName(String userName);

	/**
	 * 根据用户名与密码查询
	 * 
	 * @param userEntity
	 * @return
	 */
	UserEntity queryUserByUserNameAndPwd(UserEntity userEntity);

	/**
	 * 添加一个用户
	 * 
	 * @param userEntity
	 * @return
	 */
	int insertUser(UserEntity userEntity);
}
