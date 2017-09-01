package com.mawujun.dwmeta.loader.schema;

import java.util.ArrayList;
import java.util.List;

public abstract class Constraint {
	private String name;
	private List<String> columns = new ArrayList<String>();
	private String table_name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getColumns() {
		return columns;
	}
	public void setColumns(List<String> columns) {
		this.columns = columns;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
}
