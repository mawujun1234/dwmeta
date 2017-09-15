package com.mawujun.dwmeta.loader.compare;

import java.util.ArrayList;
import java.util.List;

import com.mawujun.dwmeta.loader.schema.UniqueKey;

public class DiffUniqueKey extends UniqueKey {
	private DiffMsgType diffMsgType;
	private List<String> diff_columns = new ArrayList<String>();
	
	public void addDiff_columns(String name) {
		if(this.diff_columns==null){
			this.diff_columns=new ArrayList<String>();
		}
		this.diff_columns.add(name);
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
}
