package com.mawujun.dwmeta.loader.compare;

import java.util.ArrayList;
import java.util.List;

import com.mawujun.dwmeta.loader.schema.Table;

public class DiffTable extends Table{
	private List<DiffPrimaryKey> primaryKeyes;
	
	private DiffMsgType diffMsgType;
	
	public void addDiffPrimaryKey(DiffPrimaryKey diffprimarykey){
		if(primaryKeyes==null){
			primaryKeyes=new ArrayList<DiffPrimaryKey>();
		}
		primaryKeyes.add(diffprimarykey);
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
