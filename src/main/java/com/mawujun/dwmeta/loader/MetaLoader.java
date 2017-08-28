package com.mawujun.dwmeta.loader;

import java.util.Set;

import com.mawujun.dwmeta.loader.schema.Table;

public interface MetaLoader {
	Set<String> getTableNames();
	Table getTable(String tableName);
}
