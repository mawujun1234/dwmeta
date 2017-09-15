package com.mawujun.dwmeta.loader.compare;

public enum DiffMsgType {
	none("无差异")
	,table_change("表新增")
	,table_more("表新增")//本地比数据库多了
	,table_less("表未登记")//本地比数据库少了
	
	,column_change("列变更")
	,column_more("列新增")
	,column_less("列未登记")
	
	
	,pk_more("主键新增")
	,pk_less("主键未登记")
	//,pk_name("主键名称不一致")
	//,pk_table_name("主键表名不一致")
	,pk_column_diff("主键列不一致")
	
	//,fk_size_diff("外键数量不一致")
	,fk_more("外键新增")
	,fk_less("外键未登记")
	,fk_column_diff("外键列不一致")
	,fk_ref_table_diff("引用表不一致")
	,fk_ref_column_diff("引用列不一致")
	
	,uk_size_diff("唯一键数量不一致")
	,uk_more("唯一键新增")
	,uk_less("唯一键未登记")
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
