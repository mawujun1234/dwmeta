package com.mawujun.dwmeta.loader.schema;

import java.io.Serializable;

public class Column implements Serializable{
	private static final long serialVersionUID = 7510687871404901852L;
	
	private String name;//列名
	private String comment;//列的注释
	private boolean nullable;//是否可以为null
	//private int type;//just the java.sql.Type
	private String type_name;//column type name
	private int precision;
	private int scale;
	private String defaultValue;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public boolean isNullable() {
		return nullable;
	}
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public int getPrecision() {
		return precision;
	}
	public void setPrecision(int precision) {
		this.precision = precision;
	}
	public int getScale() {
		return scale;
	}
	public void setScale(int scale) {
		this.scale = scale;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	
	
}
