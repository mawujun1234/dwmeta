package com.mawujun.dwmeta.loader;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mawujun.dwmeta.loader.schema.SchemaInfo;
import com.mawujun.dwmeta.loader.schema.Table;
import com.mawujun.dwmeta.loader.schema.UniqueKey;
import com.mawujun.exception.BusinessException;

public class OracleMetaLoader  extends AbstractMetaLoader {
	private static Logger logger=LogManager.getLogger(OracleMetaLoader.class);
//	public final static String GET_CONSTRAINT_SQL = "select constraint_name name,constraint_type type,search_condition definition,deferrable from All_Constraints where owner=? "
//			+ "and TABLE_NAME=? and (Constraint_Type='C' or Constraint_Type='U') ";
	public final static String GET_CONSTRAINT_SQL = "select a.constraint_name ,a.constraint_type ,a.search_condition ,a.deferrable,  b.column_name"
			+ " from All_Constraints a"
			+ " inner join all_cons_columns B on a.owner=b.owner and A.constraint_name=B.constraint_name and A.table_name=B.table_name"
			+ " where  lower(a.owner)=lower(?) and lower(a.TABLE_NAME)=lower(?) and   a.Constraint_Type='U' ";
	
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
	
	public Table getTableInfo(String tableName) {
		logger.trace("Get schema name by username");
		String schemaName = null;
		try {
			schemaName = dbm.getUserName();
		} catch (SQLException e) {
			logger.error(e);
			throw new BusinessException("can not get schema name, so see schema as null!");
		}

		Table table = getTableInfo(null, schemaName, tableName);
		return table;
	}

	@Override
	protected Map<String, UniqueKey> getUniqueKeys(String tableName, SchemaInfo schemaInfo) {
		String schema = null;
		if (schemaInfo == null) {
			try {
				schema = dbm.getUserName();
			} catch (SQLException e) {
				logger.error(e);
				throw new BusinessException("Get database(Oracle) user name error!");
			}
		} else {
			schema = schemaInfo.getSchemaName();
		}
		
		String message = "Get database(Oracle) " + tableName + "'s constraint information error!";

		return JDBCUtils.query(dbm, GET_CONSTRAINT_SQL, message, new ResultSetExtractor<Map<String, UniqueKey>>() {

			public Map<String, UniqueKey> extractData(ResultSet rs) throws SQLException {
				Map<String, UniqueKey> constraints = new HashMap<String, UniqueKey>();
				while (rs.next()) {
					String name = rs.getString("CONSTRAINT_NAME");
					String column_name=rs.getString("COLUMN_NAME");
					//String type = rs.getString("CONSTRAINT_TYPE");
					//String definition = rs.getString("definition");
					//String deferrable = rs.getString("deferrable");
					if(!constraints.containsKey(name)){
						UniqueKey c = new UniqueKey();
						c.setTable_name(tableName);
						c.setName(name);
						List<String> columns=new ArrayList<String>();
						columns.add(column_name);
						c.setColumns(columns);
						
						constraints.put(name, c);
					} else {
						UniqueKey c =constraints.get(name);
						c.getColumns().add(column_name);
					}
					
				}
				return constraints;
			}
		}, schema, tableName);
	}



}
