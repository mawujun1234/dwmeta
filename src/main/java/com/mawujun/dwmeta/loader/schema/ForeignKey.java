package com.mawujun.dwmeta.loader.schema;

import java.util.ArrayList;
import java.util.List;

public class ForeignKey {
	private String name;//外键的名称
	
	private List<ForeignKeyColumn> columns;
	public void addColumn(ForeignKeyColumn column) {
		if(this.columns==null){
			this.columns=new ArrayList<ForeignKeyColumn>();
		}
		this.columns.add(column);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ForeignKeyColumn> getColumns() {
		return columns;
	}
	public void setColumns(List<ForeignKeyColumn> columns) {
		this.columns = columns;
	}
	
}
