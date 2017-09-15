package com.mawujun.dwmeta.loader.compare;

import java.util.ArrayList;
import java.util.List;

import com.mawujun.dwmeta.loader.schema.PrimaryKey;

public class DiffPrimaryKey extends PrimaryKey {
	private DiffMsgType diffMsgType;
	
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
