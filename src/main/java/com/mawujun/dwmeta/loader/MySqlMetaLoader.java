package com.mawujun.dwmeta.loader;

import java.sql.DatabaseMetaData;

public class MySqlMetaLoader extends AbstractMetaLoader {
	public MySqlMetaLoader() {

	}
	
	public MySqlMetaLoader(DatabaseMetaData dbm) {
		this.dbm = dbm;
	}

	

}
