package com.ithuangyonghua.service;

import java.sql.SQLException;

import com.ithuangyonghua.dao.UserDao;
import com.ithuangyonghua.entity.UserEntity;
import com.ithuangyonghua.utils.DBUtils;

public interface UserService {
	/**
	 * �����û�����ѯ�û��Ƿ����
	 * 
	 * @param userName
	 * @return
	 */
	UserEntity exitsUserByUserName(String userName);

	/**
	 * �����û����������ѯ
	 * 
	 * @param userEntity
	 * @return
	 */
	UserEntity queryUserByUserNameAndPwd(UserEntity userEntity);

	/**
	 * ���һ���û�
	 * 
	 * @param userEntity
	 * @return
	 */
	int insertUser(UserEntity userEntity);
}
