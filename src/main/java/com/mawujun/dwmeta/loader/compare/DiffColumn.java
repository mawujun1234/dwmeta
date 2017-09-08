package com.mawujun.dwmeta.loader.compare;

import com.mawujun.dwmeta.loader.schema.Column;

public class DiffColumn extends Column {
	/**
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 */
	private static final long serialVersionUID = 1L;
	
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
