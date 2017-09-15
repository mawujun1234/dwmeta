package com.mawujun.dwmeta.loader.schema;

import java.util.HashMap;
import java.util.Map;

public class Table {
	private String name;
	private String comment;
	
	private Map<String, Column> columns;
	private PrimaryKey primaryKey;
	private Map<String,ForeignKey> foreignkeys;
	private Map<String,UniqueKey> uniqueKeys;
	public void addColumn(Column col){
		if(columns==null){
			columns=new HashMap<String, Column>();
		}
		this.columns.put(col.getName(), col);
	}
	public void addForeignKey(ForeignKey fk){
		if(foreignkeys==null){
			foreignkeys=new HashMap<String, ForeignKey>();
		}
		this.foreignkeys.put(fk.getName(),  fk);
	}
	public void addUniqueKey(UniqueKey uk){
		if(uniqueKeys==null){
			uniqueKeys=new HashMap<String, UniqueKey>();
		}
		this.uniqueKeys.put(uk.getName(),  uk);
	}

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

	public Map<String, Column> getColumns() {
		return columns;
	}

	public void setColumns(Map<String, Column> columns) {
		this.columns = columns;
	}

	public PrimaryKey getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(PrimaryKey primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Map<String, ForeignKey> getForeignkeys() {
		return foreignkeys;
	}

	public void setForeignkeys(Map<String, ForeignKey> foreignkeys) {
		this.foreignkeys = foreignkeys;
	}

	public Map<String, UniqueKey> getUniqueKeys() {
		return uniqueKeys;
	}

	public void setUniqueKeys(Map<String, UniqueKey> uniqueKeys) {
		this.uniqueKeys = uniqueKeys;
	}
}
