package com.mawujun.dwmeta.loader.compare;
/**
 * 差异的信息
 * @author mawujun qq:16064988 mawujun1234@163.com
 *
 */
public class DiffMsg {
	private String dbname;
	private String layername;
	private String tablename;//
	private DiffMsgType diffMsgType;
	
	private DiffTable diffTable;
	
	public String getDiffMsg() {
		if(diffMsgType==null){
			return "";
		}
		return diffMsgType.getMsg();
	}
	
	
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public DiffMsgType getDiffMsgType() {
		return diffMsgType;
	}
	public void setDiffMsgType(DiffMsgType diffMsgType) {
		this.diffMsgType = diffMsgType;
	}


	public String getDbname() {
		return dbname;
	}


	public void setDbname(String dbname) {
		this.dbname = dbname;
	}


	public String getLayername() {
		return layername;
	}


	public void setLayername(String layername) {
		this.layername = layername;
	}


	public DiffTable getDiffTable() {
		return diffTable;
	}


	public void setDiffTable(DiffTable diffTable) {
		this.diffTable = diffTable;
	}
	
	
}
