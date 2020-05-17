package com.ithuangyonghua.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.PreparedStatement;

public class DBUtils {

	private static DataSource dataSource = null;
	public static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();// ��������

	static {
		dataSource = new ComboPooledDataSource("helloc3p0");
	}
    /**
     * ��ȡ���ݿ�����
     * @return
     */
	public static Connection getConnection() {
		Connection conn = conns.get();
		if (conn == null) {
			try {
				conn = dataSource.getConnection();
				conns.set(conn);// ��conn���浽ThreadLocal
				conn.setAutoCommit(false);// �����ֶ���������
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	/**
	 * �ύ���񲢹ر�����
	 */
	public static void commitAndCloseConnection() {
		Connection connection = conns.get();
		if (connection != null) {// ˵��֮ǰʹ�ù������������ݿ�
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
		conns.remove();// �Ƴ����ֲ߳̾�������ֵ��
	}

	/**
	 * �ع����񲢹ر�����
	 */
	public static void rollbackAndCloseConnection() {
		Connection connection = conns.get();
		if (connection != null) {// ˵��֮ǰʹ�ù������������ݿ�
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
		conns.remove();// �Ƴ����ֲ߳̾�������ֵ��
	}
}
