package com.mawujun.dwmeta.loader.compare;
/**
 * 差异的信息
 * @author mawujun qq:16064988 mawujun1234@163.com
 *
 */
public class DiffMsg {
	private String tablename;//
	private DiffMsgType diffMsgType;
	
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
	
	
}
