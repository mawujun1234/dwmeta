package com.mawujun.dwmeta.loader;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mawujun.dwmeta.loader.schema.Column;
import com.mawujun.dwmeta.loader.schema.ColumnType;
import com.mawujun.dwmeta.loader.schema.ForeignKey;
import com.mawujun.dwmeta.loader.schema.PrimaryKey;
import com.mawujun.dwmeta.loader.schema.SchemaInfo;
import com.mawujun.dwmeta.loader.schema.Table;
import com.mawujun.dwmeta.loader.schema.UniqueKey;
import com.mawujun.exception.BusinessException;
import com.mawujun.utils.Assert;

public abstract class AbstractMetaLoader implements MetaLoader{
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
//	@Override
//	public Connection getConnection(){
//		try {
//			return dbm.getConnection();
//		} catch (SQLException e) {
//			logger.error(e);
//			e.printStackTrace();
//			throw new BusinessException("getConnection():获取数据库连接失败!");
//		}
//	}
	
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
	@Override
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
		return getTable(tableName,null);
	}
	public abstract Table getTableInfo(String tableName);
	public Table getTable(String tableName, SchemaInfo schemaInfo) {
		logger.debug("crawl table information,table name is " + tableName + " ;use standard schemaInfoLevel");

		Assert.notNull(tableName, "tableName must not be null");
		//Assert.notEmpty(tableName, "tableName must not be an empty string");
		// Get table base information

		Table table;

		if (schemaInfo == null) {
			table = getTableInfo(tableName);
		} else {
			table = getTableInfo(schemaInfo.getCatalogName(), schemaInfo.getSchemaName(), tableName);
		}

		// crawl column information
		//if (level.isRetrieveTableColumns()) {
			Map<String, Column> columns = getColumnInfo(tableName, schemaInfo);
			table.setColumns(columns);
		//}

		// crawl primary key
		//if (level.isRetrievePrimaryKey()) {
			PrimaryKey pk = crawlPrimaryKey(tableName, schemaInfo);
			table.setPrimaryKey(pk);
		//}

		// crawl foreign
		//if (level.isRetrieveForeignKeys()) {
			Map<String, ForeignKey> foreignKeys = crawlForeignKey(tableName, schemaInfo);
			table.setForeignkeys(foreignKeys);
		//}


		
		//crawl constraint
		//if(level.isRetrieveTableConstraintInformation()){
			Map<String, UniqueKey> uniqueKeys=getUniqueKeys(tableName, schemaInfo);
			table.setUniqueKeys(uniqueKeys);
		//}

		return table;
	}
	protected abstract Map<String, UniqueKey> getUniqueKeys(String tableName, SchemaInfo schemaInfo);
	protected Table getTableInfo(String catalog, String schema, String tableName) {
		List<String> types = new ArrayList<String>();
		types.add("TABLE");
//		if (level.isRetrieveTable()) {
//			types.add("TABLE");
//		}
//		if (level.isRetrieveViewInformation()) {
//			types.add("VIEW");
//		}
//		if (types.size() == 0) {
//			throw new SchemaInfoLevelException(level.getTag() + " Schema level ,can not get Table information");
//		}
		String[] typeStr = new String[types.size()];
		for (int i = 0; i < typeStr.length; ++i) {
			typeStr[i] = types.get(i);
		}
		Table table = new Table();
		try {
			ResultSet rs = dbm.getTables(catalog, schema, tableName, typeStr);
			while (rs.next()) {
				String tableN=rs.getString("TABLE_NAME");
				if(!tableN.equals(tableName)) continue;
				table.setName(tableN);
				table.setComment(rs.getString("REMARKS"));
				//table.setTableType(TableType.fromString(rs.getString("TABLE_TYPE")));
			}

		} catch (SQLException e) {
			throw new BusinessException("Table " + tableName + " information get error!");
		}

		return table;
	}
	
	/**
	 * getting the table's column
	 * 
	 * @param tableName
	 * @return
	 */
	protected Map<String, Column> getColumnInfo(String tableName, SchemaInfo schemaInfo) {
		Map<String, Column> columns = new HashMap<String, Column>();
		ResultSet rs = null;
		try {
			if (schemaInfo == null) {
				rs = dbm.getColumns(null, null, tableName, null);
			} else {
				rs = dbm.getColumns(schemaInfo.getCatalogName(), schemaInfo.getSchemaName(), tableName, null);
			}
			while (rs.next()) {
				if(!rs.getString("TABLE_NAME").equals(tableName)) continue;
				Column column = packColumn(rs);
				columns.put(column.getName(), column);
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new BusinessException("Table " + tableName + " get column information error!");
		} finally {
			JDBCUtils.closeResultSet(rs);
		}
		return columns;
	}
	protected Column packColumn(ResultSet rs) throws SQLException {
		String name = rs.getString("COLUMN_NAME");
		Column column = new Column();
		column.setName(name);
		column.setComment(rs.getString(12));// Oracle and Mssql can not get
											// comment information
		column.setDefaultValue(rs.getString("COLUMN_DEF"));
		column.setPrecision(rs.getInt("CHAR_OCTET_LENGTH"));
		column.setNullable(rs.getInt("NULLABLE") == 1 ? true : false);
		column.setPrecision(rs.getInt("COLUMN_SIZE"));
		column.setScale(rs.getInt("DECIMAL_DIGITS"));
		//column.setType(rs.getInt("DATA_TYPE"));
		column.setType_name(rs.getString("TYPE_NAME"));
		// column.setUnique(unique);
		return column;
	}
	
	protected PrimaryKey crawlPrimaryKey(String tableName, SchemaInfo schemaInfo) {
		List<String> columns = new ArrayList<String>();
		TreeMap<Integer, String> columnMaps = new TreeMap<Integer, String>();
		PrimaryKey pk = new PrimaryKey();
		ResultSet rs = null;
		try {
			if (schemaInfo == null) {
				rs = dbm.getPrimaryKeys(null, null, tableName);
			} else {
				rs = dbm.getPrimaryKeys(schemaInfo.getCatalogName(), schemaInfo.getSchemaName(), tableName);
			}
			while (rs.next()) {
				String pkName = rs.getString("PK_NAME");
				pk.setName(pkName);
				int seq = rs.getInt("KEY_SEQ");
				String columnName = rs.getString("COLUMN_NAME");
				columnMaps.put(seq, columnName);
			}

			Set<Integer> keys = columnMaps.keySet();
			for (Integer integer : keys) {
				String columnName = columnMaps.get(integer);
				columns.add(columnName);
			}
			pk.setColumns(columns);
		} catch (SQLException e) {
			logger.error(e);
			throw new BusinessException("Table " + tableName + " get primary key information error!");
		} finally {
			JDBCUtils.closeResultSet(rs);
		}
		return pk;
	}
	
	protected Map<String, ForeignKey> crawlForeignKey(String tableName, SchemaInfo schemaInfo) {
		Map<String, ForeignKey> foreignKeys = new HashMap<String, ForeignKey>();
		ResultSet rs = null;
		try {
			if (schemaInfo == null) {
				rs = dbm.getImportedKeys(null, null, tableName);
			} else {
				rs = dbm.getImportedKeys(schemaInfo.getCatalogName(), schemaInfo.getSchemaName(), tableName);
			}
			while (rs.next()) {
				String fk_name = rs.getString("FK_NAME");
				ForeignKey key;
				if (!foreignKeys.containsKey(fk_name)) {
					key = new ForeignKey();
					key.setName(rs.getString("FK_NAME"));
					//key.setUpdateRule(ForeignKeyUpdateRule.valueOf(rs.getInt("UPDATE_RULE")));
					//key.setDeleteRule(ForeignKeyUpdateRule.valueOf(rs.getInt("DELETE_RULE")));
					//key.setDeferrability(ForeignKeyDeferrability.valueOf(rs.getInt("DEFERRABILITY")));
					foreignKeys.put(fk_name, key);
				} else {
					key = foreignKeys.get(fk_name);
				}
				//ForeignKeyColumnReference fcr = packForeignKeyColumnReference(rs);
				//key.getColumnReferences().add(fcr);
				key.setTable_name(rs.getString("PKTABLE_NAME"));
				key.setColumn_name(rs.getString("PKCOLUMN_NAME"));
				key.setRef_table_name(rs.getString("FKTABLE_NAME"));
				key.setRef_column_name(rs.getString("FKCOLUMN_NAME"));
				key.setKeySeq( rs.getInt("KEY_SEQ"));
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new BusinessException("Table " + tableName + " get foreign key information error!");
		} finally {
			JDBCUtils.closeResultSet(rs);
		}

		return foreignKeys;
	}
//	protected ForeignKeyColumnReference packForeignKeyColumnReference(ResultSet rs) throws SQLException {
//		//String pk_cat = rs.getString("PKTABLE_CAT");
//		//String pk_schema = rs.getString("PKTABLE_SCHEM");
//		String pk_table = rs.getString("PKTABLE_NAME");
//		String pk_column = rs.getString("PKCOLUMN_NAME");
//
//		//String fk_cat = rs.getString("FKTABLE_CAT");
//		//String fk_schema = rs.getString("FKTABLE_SCHEM");
//		String fk_table = rs.getString("FKTABLE_NAME");
//		String fk_column = rs.getString("FKCOLUMN_NAME");
//
//		int keySeq = rs.getInt("KEY_SEQ");
//
////		ForeignKeyColumnReference foreignKeyColumnReference = new ForeignKeyColumnReference();
////		foreignKeyColumnReference.setKeySequence(keySeq);
////		foreignKeyColumnReference.setForeignColumn(new ForeignKeyColumnReference.ColumnReference(fk_cat, fk_schema, fk_table, fk_column));
////		foreignKeyColumnReference.setPrimaryColumn(new ForeignKeyColumnReference.ColumnReference(pk_cat, pk_schema, pk_table, pk_column));
//
//		return foreignKeyColumnReference;
//	}
	
	
}
