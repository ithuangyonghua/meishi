package com.ithuangyonghua.service.impl;

import com.ithuangyonghua.dao.UserDao;
import com.ithuangyonghua.dao.impl.UserDaoImpl;
import com.ithuangyonghua.entity.UserEntity;
import com.ithuangyonghua.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao = new UserDaoImpl();

	public UserEntity exitsUserByUserName(String userName) {
		return userDao.exitsUserByUserName(userName);
	}

	public UserEntity queryUserByUserNameAndPwd(UserEntity userEntity) {
		return userDao.queryUserByUserNameAndPwd(userEntity);
	}

	public int insertUser(UserEntity userEntity) {
		return userDao.insertUser(userEntity);
	}
   
}
