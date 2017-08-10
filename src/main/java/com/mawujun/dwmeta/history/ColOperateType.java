package com.mawujun.dwmeta.history;

public enum ColOperateType {

	create_col("创建列"),update_col("更新列"),delete_col("删除列");
	
	private String name;
	ColOperateType(String name){
		this.name=name;
	}
	public String getName() {
		return name;
	}

}
