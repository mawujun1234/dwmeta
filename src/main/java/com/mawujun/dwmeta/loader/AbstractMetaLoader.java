package com.mawujun.dwmeta.loader;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mawujun.dwmeta.loader.schema.ColumnType;
import com.mawujun.dwmeta.loader.schema.SchemaInfo;
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
	
	public Connection getConnection(){
		try {
			return dbm.getConnection();
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
			throw new BusinessException("getConnection():获取数据库连接失败!");
		}
	}
	
	@Override
	public Set<ColumnType> getColumnTypes() {
		Set<ColumnType> tables = new LinkedHashSet<ColumnType>();
		ResultSet rs;
		try {
			rs=dbm.getTypeInfo();
			while (rs.next()) {
				//String tableName = rs.getString("TABLE_NAME");
				ColumnType columntype=new ColumnType();
				columntype.setType_name(rs.getString("TYPE_NAME"));
				columntype.setData_type(rs.getInt("DATA_TYPE"));
				columntype.setPrecision(rs.getInt("PRECISION"));
				columntype.setLiteral_prefix(rs.getString("LITERAL_SUFFIX"));
				columntype.setLiteral_suffix(rs.getString("LITERAL_SUFFIX"));
				columntype.setCreate_params(rs.getString("CREATE_PARAMS"));
				columntype.setNullable(rs.getShort("NULLABLE"));
				columntype.setCase_sensitive(rs.getBoolean("CASE_SENSITIVE"));
				columntype.setSearchable(rs.getBoolean("SEARCHABLE"));
				columntype.setUnsigned_attribute(rs.getBoolean("UNSIGNED_ATTRIBUTE"));
				columntype.setFixed_prec_scale(rs.getBoolean("FIXED_PREC_SCALE"));
				columntype.setAuto_increment(rs.getBoolean("AUTO_INCREMENT"));
				columntype.setLocal_type_name(rs.getString("LOCAL_TYPE_NAME"));
				columntype.setMinimum_scale(rs.getShort("MINIMUM_SCALE"));
				columntype.setMaximum_scale(rs.getShort("MAXIMUM_SCALE"));
				tables.add(columntype);
			}
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
			throw new BusinessException("getTypeInfo():获取数据库支持的列失败!");
		}
		return tables;
	}

	@Override
	public Set<String> getTableNames() {
		Set<String> tables = new LinkedHashSet<String>();
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
	public Set<String> getTableNames(SchemaInfo schemaInfo) {
		Set<String> tables = new LinkedHashSet<String>();
		ResultSet rs;
		try {
			rs = dbm.getTables(schemaInfo.getCatalogName(), schemaInfo.getSchemaName(), null, new String[] { "TABLE" });

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
