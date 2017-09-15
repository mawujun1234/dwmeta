package com.mawujun.dwmeta.loader.compare;

import com.mawujun.dwmeta.loader.schema.Column;

public class DiffColumn extends Column {
	/**
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 */
	private static final long serialVersionUID = 1L;
	
	private DiffMsgType diffMsgType;
	
	
	private String diff_name;//列名
	private String diff_comment;//列的注释
	private Boolean diff_nullable;//是否可以为null
	//private int type;//just the java.sql.Type
	private String diff_type_name;//column type name
	private int diff_precision;
	private int diff_scale;
	private String diff_defaultValue;
	
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

	public String getDiff_name() {
		return diff_name;
	}

	public void setDiff_name(String diff_name) {
		this.diff_name = diff_name;
	}

	public String getDiff_comment() {
		return diff_comment;
	}

	public void setDiff_comment(String diff_comment) {
		this.diff_comment = diff_comment;
	}

	public Boolean getDiff_nullable() {
		return diff_nullable;
	}

	public void setDiff_nullable(Boolean diff_nullable) {
		this.diff_nullable = diff_nullable;
	}

	public String getDiff_type_name() {
		return diff_type_name;
	}

	public void setDiff_type_name(String diff_type_name) {
		this.diff_type_name = diff_type_name;
	}

	public int getDiff_precision() {
		return diff_precision;
	}

	public void setDiff_precision(int diff_precision) {
		this.diff_precision = diff_precision;
	}

	public int getDiff_scale() {
		return diff_scale;
	}

	public void setDiff_scale(int diff_scale) {
		this.diff_scale = diff_scale;
	}

	public String getDiff_defaultValue() {
		return diff_defaultValue;
	}

	public void setDiff_defaultValue(String diff_defaultValue) {
		this.diff_defaultValue = diff_defaultValue;
	}
}
