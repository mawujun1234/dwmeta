package com.mawujun.dwmeta.loader;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import com.mawujun.exception.BusinessException;

public class DefaultMetaLoaderFactory {
	public static final int MYSQL = 1;
	public static final int SQL_SERVER = 2;
	public static final int ORACLE = 3;

	public MetaLoader newInstance(Connection con) {
		String product=getProductName(con);
		
		DatabaseMetaData dbm=getDatabaseMetaData(con);
		if (product.equals("MySQL")) {
			return new MySqlMetaLoader(dbm);
		} else if (product.equals("Oracle")) {
			return new OracleMetaLoader(dbm);
		} else if (product.equals("Microsoft SQL Server")) {
			return new SqlServerMetaLoader(dbm);
		} else {
			return null;
		}
	}

	protected String getProductName(Connection conn) {
		String product = null;
		try {
			DatabaseMetaData dbm = conn.getMetaData();
			product = dbm.getDatabaseProductName();
			return product;

		} catch (SQLException e) {
			throw new BusinessException("不能获取数据库类型的信息");
		}
	}
	
	protected DatabaseMetaData getDatabaseMetaData(Connection connection) {
		DatabaseMetaData dbm;
		try {
			dbm = connection.getMetaData();
		} catch (SQLException e) {
			JDBCUtils.closeConnection(connection);
			throw new BusinessException("不能获取数据库的元数据");
		}
		return dbm;
	}
}
