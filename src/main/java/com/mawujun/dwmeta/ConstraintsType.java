package com.mawujun.dwmeta;

public enum ConstraintsType {
	Primary("主键"),Foreign("外键"),Unique("唯一键");
	
	private String name;
	ConstraintsType(String name){
		this.name=name;
	}
	public String getName() {
		return name;
	}
}
