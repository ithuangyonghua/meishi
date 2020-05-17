package com.ithuangyonghua.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.ithuangyonghua.dao.BaseDao;
import com.ithuangyonghua.utils.DBUtils;
import com.ithuangyonghua.utils.ReflectionUtils;

public class BaseDaoImpl<T> implements BaseDao<T> {
	private QueryRunner queryRunner = null;
	private Class<T> type;

	public BaseDaoImpl() {
		queryRunner = new QueryRunner();
		type = ReflectionUtils.getSuperGenericType(getClass());
	}

	public void batch(String sql, Object[]... args) {
		Connection connection = DBUtils.getConnection();
		try {
			queryRunner.batch(connection, sql, args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public <E> E getForValue(String sql, Object... args) {
		Connection connection = DBUtils.getConnection();
		try {
			return (E) queryRunner.query(connection, sql, new ScalarHandler<Object>(), args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public List<T> getList(String sql, Object... args) {
		Connection connection = DBUtils.getConnection();
		try {
			return queryRunner.query(connection, sql, new BeanListHandler<T>(type), args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public T get(String sql, Object... args)  {
		Connection connection = DBUtils.getConnection();
		try {
			return queryRunner.query(connection, sql, new BeanHandler<T>(type), args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public int update( String sql, Object... args) {
		Connection connection = DBUtils.getConnection();
		int update = -1;
		try {
			update = queryRunner.update(connection, sql, args);
			return update;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
