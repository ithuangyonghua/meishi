package com.ithuangyonghua.dao;

import com.ithuangyonghua.entity.UserEntity;

public interface UserDao {
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
