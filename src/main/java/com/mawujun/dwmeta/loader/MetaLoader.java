package com.mawujun.dwmeta.loader;

import java.sql.Connection;
import java.util.Set;

import com.mawujun.dwmeta.loader.schema.ColumnType;
import com.mawujun.dwmeta.loader.schema.SchemaInfo;
import com.mawujun.dwmeta.loader.schema.Table;

public interface MetaLoader {
	public Connection getConnection();
	public  Set<ColumnType> getColumnTypes();
	/**
	 * 默认使用用户名作为schema
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 * @return
	 */
	public Set<String> getTableNames();
	/**
	 * 可以使用指定的catlog和schema过滤出表
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 * @param schemaInfo
	 * @return
	 */
	public Set<String> getTableNames(SchemaInfo schemaInfo);
	public Table getTable(String tableName);
}
