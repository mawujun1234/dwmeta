package com.mawujun.dwmeta.loader;

import java.sql.Connection;
import java.util.Set;

import com.mawujun.dwmeta.loader.schema.ColumnType;
import com.mawujun.dwmeta.loader.schema.SchemaInfo;
import com.mawujun.dwmeta.loader.schema.Table;
/**
 * 获取本机登记的表结构
 * @author mawujun
 *
 */
public class DbmmMetaLoader implements MetaLoader {

//	@Override
//	public Connection getConnection() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public Set<ColumnType> getColumnTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getTableNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getTableNames(SchemaInfo schemaInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table getTable(String tableName) {
		// TODO Auto-generated method stub
		return null;
	}

}
