package com.mawujun.dwmeta.loader;

import java.sql.DatabaseMetaData;
import java.util.Map;

import com.mawujun.dwmeta.loader.schema.SchemaInfo;
import com.mawujun.dwmeta.loader.schema.Table;
import com.mawujun.dwmeta.loader.schema.UniqueKey;

public class MySqlMetaLoader extends AbstractMetaLoader {
	public MySqlMetaLoader() {

	}
	
	public MySqlMetaLoader(DatabaseMetaData dbm) {
		this.dbm = dbm;
	}

	@Override
	public Table getTableInfo(String tableName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map<String, UniqueKey> getUniqueKeys(String tableName, SchemaInfo schemaInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
