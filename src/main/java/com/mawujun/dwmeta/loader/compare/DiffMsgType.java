package com.mawujun.dwmeta.loader.compare;

public enum DiffMsgType {
	table_more("表多了")//本地比数据库多了
	,table_less("表少了")//本地比数据库少了
	;
	
	private String msg;
	
	DiffMsgType(String msg){
		this.msg=msg;
	}

	public String getMsg() {
		return msg;
	}

}
