package com.mawujun.dwmeta.loader.compare;

import com.mawujun.dwmeta.loader.schema.Table;

public class DiffTable extends Table{
	private DiffMsgType diffMsgType;
	
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
