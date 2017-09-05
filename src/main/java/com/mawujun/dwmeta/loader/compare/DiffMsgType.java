package com.mawujun.dwmeta.loader.compare;

public enum DiffMsgType {
	none("无差异")
	,table_more("表未建立")//本地比数据库多了
	,table_less("表未登记")//本地比数据库少了
	,column_change("列变动")
	,pk_name("主键名称不一致")
	,pk_table_name("主键表名不一致")
	,pk_column_diff("主键列不一致")
	;
	
	private String msg;
	
	DiffMsgType(String msg){
		this.msg=msg;
	}

	public String getMsg() {
		return msg;
	}

}
