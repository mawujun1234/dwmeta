package com.mawujun.dwmeta.loader.schema;

public class ForeignKeyColumn {
	private String table_name;
	
	private String column_name;
	
	private String ref_table_name;
	private String ref_column_name;
	
	private int keySeq;

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public String getColumn_name() {
		return column_name;
	}

	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}

	public String getRef_table_name() {
		return ref_table_name;
	}

	public void setRef_table_name(String ref_table_name) {
		this.ref_table_name = ref_table_name;
	}

	public String getRef_column_name() {
		return ref_column_name;
	}

	public void setRef_column_name(String ref_column_name) {
		this.ref_column_name = ref_column_name;
	}

	public int getKeySeq() {
		return keySeq;
	}

	public void setKeySeq(int keySeq) {
		this.keySeq = keySeq;
	}

}
