package com.ithuangyonghua.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface BaseDao<T> {
	/**
	 * 批量处理
	 * @param connection
	 * @param sql
	 * @param args
	 * @throws SQLException
	 */
	void batch(String sql, Object[]... args) throws SQLException;

	/**
	 * 
	 * @param connection
	 * @param sql
	 * @param args
	 * @return
	 */
	<E> E getForValue( String sql, Object... args) throws SQLException;

	/**
	 * 
	 * @param connection
	 * @param sql
	 * @param args
	 * @return
	 */
	<T> List<T> getList(String sql, Object... args) throws SQLException;

	/**
	 * 
	 * @param connection
	 * @param sql
	 * @param args
	 * @return
	 */
	<T> T get(String sql, Object... args) throws SQLException;

	/**
	 * 
	 * @param connection
	 * @param sql
	 * @param args
	 * @return
	 */
	int update( String sql, Object... args) throws SQLException;

}
