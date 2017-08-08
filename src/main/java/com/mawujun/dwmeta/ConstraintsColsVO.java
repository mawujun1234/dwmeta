package com.mawujun.dwmeta;

public class ConstraintsColsVO extends ConstraintsCols {
	private String col_name;
	private String ref_col_name;
	private String ref_table_name;
	
	public String getCol_name() {
		return col_name;
	}
	public void setCol_name(String col_name) {
		this.col_name = col_name;
	}
	public String getRef_col_name() {
		return ref_col_name;
	}
	public void setRef_col_name(String ref_col_name) {
		this.ref_col_name = ref_col_name;
	}
	public String getRef_table_name() {
		return ref_table_name;
	}
	public void setRef_table_name(String ref_table_name) {
		this.ref_table_name = ref_table_name;
	}

}
