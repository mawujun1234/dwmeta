package com.mawujun.dwmeta.loader.compare;

public enum DiffMsgType {
	none("无差异")
	,table_more("表未建立")//本地比数据库多了
	,table_less("表未登记")//本地比数据库少了
	,column_change("列变动")
	,pk_name("主键名称不一致")
	,pk_table_name("主键表名不一致")
	,pk_column_diff("主键列不一致")
	
	//,fk_size_diff("外键数量不一致")
	,fk_more("未建立外键")
	,fk_less("外键为登记")
	,fk_column_diff("外键列不一致")
	,fk_ref_table_diff("引用表不一致")
	,fk_ref_column_diff("引用列不一致")
	
	,uk_size_diff("唯一键数量不一致")
	,uk_more("未建立外键")
	,uk_less("外键为登记")
	,uk_column_diff("唯一键列不一致")
	;
	
	private String msg;
	
	DiffMsgType(String msg){
		this.msg=msg;
	}

	public String getMsg() {
		return msg;
	}

}
