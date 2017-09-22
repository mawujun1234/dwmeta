package com.mawujun.dwmeta.loader.compare;

import java.util.ArrayList;
import java.util.List;

public class DiffPrimaryKey {// extends PrimaryKey {
	private DiffMsgType diffMsgType;
	
	private String name;
	private List<String> columns = new ArrayList<String>();
	private String table_name;
	

	//private String diff_name;
	private List<String> diff_columns = new ArrayList<String>();
	
	public void addDiff_column(String column) {
		if(diff_columns!=null){
			diff_columns= new ArrayList<String>();
		}
		diff_columns.add(column);
	}
	
	public String getDiffMsgType_name() {
		if(diffMsgType==null){
			return "";
		}
		return diffMsgType.getMsg();
	}
	public void addColumn(String name) {
		if(this.columns==null){
			this.columns=new ArrayList<String>();
		}
		this.columns.add(name);
	}
	
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

	public DiffMsgType getDiffMsgType() {
		return diffMsgType;
	}

	public void setDiffMsgType(DiffMsgType diffMsgType) {
		this.diffMsgType = diffMsgType;
	}

	public List<String> getDiff_columns() {
		return diff_columns;
	}

	public void setDiff_columns(List<String> diff_columns) {
		this.diff_columns = diff_columns;
	}

}
