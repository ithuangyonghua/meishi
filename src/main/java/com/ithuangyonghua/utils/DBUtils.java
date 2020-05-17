package com.ithuangyonghua.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.PreparedStatement;

public class DBUtils {

	private static DataSource dataSource = null;
	public static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();// 保存连接

	static {
		dataSource = new ComboPooledDataSource("helloc3p0");
	}
    /**
     * 获取数据库连接
     * @return
     */
	public static Connection getConnection() {
		Connection conn = conns.get();
		if (conn == null) {
			try {
				conn = dataSource.getConnection();
				conns.set(conn);// 将conn保存到ThreadLocal
				conn.setAutoCommit(false);// 设置手动管理事务
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	/**
	 * 提交事务并关闭连接
	 */
	public static void commitAndCloseConnection() {
		Connection connection = conns.get();
		if (connection != null) {// 说明之前使用过并操作过数据库
			try {
				connection.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		conns.remove();// 移除此线程局部变量的值。
	}

	/**
	 * 回滚事务并关闭连接
	 */
	public static void rollbackAndCloseConnection() {
		Connection connection = conns.get();
		if (connection != null) {// 说明之前使用过并操作过数据库
			try {
				connection.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		conns.remove();// 移除此线程局部变量的值。
	}
}
