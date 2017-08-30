package com.mawujun.dwmeta.loader;

import java.sql.DatabaseMetaData;

public class SqlServerMetaLoader  extends AbstractMetaLoader {

	public SqlServerMetaLoader() {

	}
	
	public SqlServerMetaLoader(DatabaseMetaData dbm) {
		this.dbm = dbm;
	}

}
