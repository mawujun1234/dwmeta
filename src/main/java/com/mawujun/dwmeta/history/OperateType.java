package com.mawujun.dwmeta.history;

public enum OperateType {
	create_table("创建表"),update_table("更改表"),delete_table("删除表")
	,update_classify("更改表分类")
	,create_col("创建列"),update_col("更新列"),delete_col("删除列")
	//表示既有更新列，也有删除列的具有多种操作类型的时候
	,mixaction_col("混合列操作");
	
	private String name;
	OperateType(String name){
		this.name=name;
	}
	public String getName() {
		return name;
	}
}
