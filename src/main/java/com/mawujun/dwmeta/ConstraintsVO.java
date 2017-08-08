package com.mawujun.dwmeta;

public class ConstraintsVO extends Constraints {
	private String col_id;
	private String ref_table_id;
	private String ref_col_id;
	
	private String col_name;
	private String ref_table_name;
	private String ref_col_name;
	

	public String getRef_table_id() {
		return ref_table_id;
	}
	public void setRef_table_id(String ref_table_id) {
		this.ref_table_id = ref_table_id;
	}

	public String getCol_id() {
		return col_id;
	}
	public void setCol_id(String col_id) {
		this.col_id = col_id;
	}
	public String getRef_col_id() {
		return ref_col_id;
	}
	public void setRef_col_id(String ref_col_id) {
		this.ref_col_id = ref_col_id;
	}
	public String getCol_name() {
		return col_name;
	}
	public void setCol_name(String col_name) {
		this.col_name = col_name;
	}
	public String getRef_table_name() {
		return ref_table_name;
	}
	public void setRef_table_name(String ref_table_name) {
		this.ref_table_name = ref_table_name;
	}
	public String getRef_col_name() {
		return ref_col_name;
	}
	public void setRef_col_name(String ref_col_name) {
		this.ref_col_name = ref_col_name;
	}

}
