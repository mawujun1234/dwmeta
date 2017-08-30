package com.mawujun.dwmeta.loader;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mawujun.dwmeta.loader.schema.Table;
import com.mawujun.exception.BusinessException;

public class AbstractMetaLoader implements MetaLoader{
	private static Logger logger=LogManager.getLogger(AbstractMetaLoader.class);

	protected DatabaseMetaData dbm;

	public void setDbm(DatabaseMetaData dbm) {
		this.dbm = dbm;
	}

	public AbstractMetaLoader(DatabaseMetaData dbm) {
		this.dbm = dbm;
	}

	public AbstractMetaLoader() {

	}

	@Override
	public Set<String> getTableNames() {
		Set<String> tables = new HashSet<String>();
		ResultSet rs;
		try {
			rs = dbm.getTables(null, null, null, new String[] { "TABLE" });

			while (rs.next()) {
				String tableName = rs.getString("TABLE_NAME");
				tables.add(tableName);
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new BusinessException("getTableNames():获取表名失败!");
		}

		return tables;
	}

	@Override
	public Table getTable(String tableName) {
		// TODO Auto-generated method stub
		return null;
	}
}
