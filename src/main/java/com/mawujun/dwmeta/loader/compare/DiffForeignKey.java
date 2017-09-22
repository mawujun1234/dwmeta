package com.mawujun.dwmeta.loader.compare;

import java.util.ArrayList;
import java.util.List;

import com.mawujun.dwmeta.loader.schema.ForeignKeyColumn;

public class DiffForeignKey {//extends ForeignKey {
	private String name;//外键的名称
	
	private List<ForeignKeyColumn> columns;
	
	private DiffMsgType diffMsgType;
	
	private List<ForeignKeyColumn> diff_columns;

	public void addColumn(ForeignKeyColumn column) {
		if(this.columns==null){
			this.columns=new ArrayList<ForeignKeyColumn>();
		}
		this.columns.add(column);
	}
	
	
	
	public void addDiff_columns(ForeignKeyColumn fkc){
		if(this.diff_columns==null){
			this.diff_columns=new ArrayList<ForeignKeyColumn>();
		}
		this.diff_columns.add(fkc);
	}
	
	public String getDiffMsgType_name() {
		if(diffMsgType==null){
			return "";
		}
		return diffMsgType.getMsg();
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
	public DiffMsgType getDiffMsgType() {
		return diffMsgType;
	}

	public void setDiffMsgType(DiffMsgType diffMsgType) {
		this.diffMsgType = diffMsgType;
	}

	public List<ForeignKeyColumn> getDiff_columns() {
		return diff_columns;
	}

	public void setDiff_columns(List<ForeignKeyColumn> diff_columns) {
		this.diff_columns = diff_columns;
	}
}
