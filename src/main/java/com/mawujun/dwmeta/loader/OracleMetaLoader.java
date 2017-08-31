package com.mawujun.dwmeta.loader;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mawujun.dwmeta.loader.schema.SchemaInfo;
import com.mawujun.exception.BusinessException;

public class OracleMetaLoader  extends AbstractMetaLoader {
	private static Logger logger=LogManager.getLogger(OracleMetaLoader.class);
	public OracleMetaLoader() {

	}
	
	public OracleMetaLoader(DatabaseMetaData dbm) {
		this.dbm = dbm;
	}
	
	private boolean isRubbishTable(String tableName) {
		if (tableName == null || tableName.length() > 30) {
			return true;
		}
		String rex = "[a-zA-Z_0-9$#]+";
		return !tableName.matches(rex);
	}
	/**
	 * 默认以用户名为schema
	 */
	public Set<String> getTableNames() {
		Set<String> tables = new LinkedHashSet<String>();
		try {

			String userName = dbm.getUserName();
			ResultSet rs = dbm.getTables(null, userName, null, new String[] { "TABLE" });

			while (rs.next()) {
				String tableName = rs.getString("TABLE_NAME");
				if (!isRubbishTable(tableName)) {
					tables.add(tableName);
				}
			}

		} catch (SQLException e) {
			logger.error(e);
			throw new BusinessException("getTableNames():获取表失败!");
		}
		return tables;
	}
	
	public Set<String> getTableNames(SchemaInfo schemaInfo) {
		Set<String> tables = new LinkedHashSet<String>();
		ResultSet rs;
		try {
			rs = dbm.getTables(schemaInfo.getCatalogName(), schemaInfo.getSchemaName(), null, new String[] { "TABLE" });

			while (rs.next()) {
				String tableName = rs.getString("TABLE_NAME");
				if (!isRubbishTable(tableName)) {
					tables.add(tableName);
				}
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new BusinessException("getTableNames():获取表失败!");
		}

		return tables;
	}


}
