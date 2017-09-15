package com.mawujun.dwmeta.loader.compare;

import java.util.ArrayList;
import java.util.List;

import com.mawujun.dwmeta.loader.schema.ForeignKey;
import com.mawujun.dwmeta.loader.schema.ForeignKeyColumn;

public class DiffForeignKey extends ForeignKey {
	private DiffMsgType diffMsgType;
	
	private List<ForeignKeyColumn> diff_columns;
	
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
