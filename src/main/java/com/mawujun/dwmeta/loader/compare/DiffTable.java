package com.mawujun.dwmeta.loader.compare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiffTable {//extends Table{
	
	private String name;
	private String comment;
	
	private Map<String, DiffColumn> columns;
	private DiffPrimaryKey primaryKey;//当前表存在的主键
	private Map<String,DiffForeignKey> foreignkeys;
	private Map<String,DiffUniqueKey> uniqueKeys;
	
	private List<DiffPrimaryKey> primaryKeyes;//只记录差异的主键，包括本地多了的，数据库多了的，或者两者都不一致的
	
	private DiffMsgType diffMsgType;
	
	public void addDiffPrimaryKey(DiffPrimaryKey diffprimarykey){
		if(primaryKeyes==null){
			primaryKeyes=new ArrayList<DiffPrimaryKey>();
		}
		primaryKeyes.add(diffprimarykey);
	}
	
	public void addColumn(DiffColumn col){
		if(columns==null){
			columns=new HashMap<String, DiffColumn>();
		}
		this.columns.put(col.getName(), col);
	}
	public void addForeignKey(DiffForeignKey fk){
		if(foreignkeys==null){
			foreignkeys=new HashMap<String, DiffForeignKey>();
		}
		this.foreignkeys.put(fk.getName(),  fk);
	}
	public void addUniqueKey(DiffUniqueKey uk){
		if(uniqueKeys==null){
			uniqueKeys=new HashMap<String, DiffUniqueKey>();
		}
		this.uniqueKeys.put(uk.getName(),  uk);
	}
	
	
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


	public Map<String, DiffColumn> getColumns() {
		return columns;
	}


	public void setColumns(Map<String, DiffColumn> columns) {
		this.columns = columns;
	}


	public Map<String, DiffForeignKey> getForeignkeys() {
		return foreignkeys;
	}


	public void setForeignkeys(Map<String, DiffForeignKey> foreignkeys) {
		this.foreignkeys = foreignkeys;
	}


	public Map<String, DiffUniqueKey> getUniqueKeys() {
		return uniqueKeys;
	}


	public void setUniqueKeys(Map<String, DiffUniqueKey> uniqueKeys) {
		this.uniqueKeys = uniqueKeys;
	}


	public List<DiffPrimaryKey> getPrimaryKeyes() {
		return primaryKeyes;
	}


	public void setPrimaryKeyes(List<DiffPrimaryKey> primaryKeyes) {
		this.primaryKeyes = primaryKeyes;
	}

	public DiffPrimaryKey getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(DiffPrimaryKey primaryKey) {
		this.primaryKey = primaryKey;
	}

	

}
