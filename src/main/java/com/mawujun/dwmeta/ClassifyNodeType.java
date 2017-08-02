package com.mawujun.dwmeta;

public enum ClassifyNodeType {
	
	dwmeta("DW分层"),classify("自定义分类"),tablemeta("表");
	
	private String name;
	ClassifyNodeType(String name){
		this.name=name;
	}
	public String getName() {
		return name;
	}

}
