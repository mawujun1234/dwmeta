package com.mawujun.dwmeta.loader;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetExtractor<T> {
	
	T extractData(ResultSet rs) throws SQLException;
}
