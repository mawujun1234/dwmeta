package com.mawujun.dwmeta;
/**
 * 
 * @author mawujun qq:16064988 mawujun1234@163.com
 *
 */
public class ClassifyNode {
	private String id;
	private String text;
	private Boolean leaf;
	private ClassifyNodeType type;
	private String dwlayer_id;
	private Boolean expanded;
	
	private Boolean status;
	
	public String getIconCls(){
		if(type==ClassifyNodeType.dwmeta){
			return "icon-hdd";
		} else if(type==ClassifyNodeType.classify){
			return "icon-folder-close-alt";
		} else if(type==ClassifyNodeType.tablemeta){
			return "icon-table";
		} else{
			return "";
		}
	}
	public String getCls() {
		if(status!=null && status==false){
			return "nodedeleted";
		}
		if(type==ClassifyNodeType.dwmeta){
			return null;
		} else if(type==ClassifyNodeType.classify){
			return null;
		} else if(type==ClassifyNodeType.tablemeta){
			return "greennode";
		} else{
			return null;
		}
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public Boolean getLeaf() {
		return leaf;
	}
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}
	public ClassifyNodeType getType() {
		return type;
	}
	public void setType(ClassifyNodeType type) {
		this.type = type;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	public String getDwlayer_id() {
		return dwlayer_id;
	}
	public void setDwlayer_id(String dwlayer_id) {
		this.dwlayer_id = dwlayer_id;
	}
	public Boolean getExpanded() {
		return expanded;
	}
	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}
	public String getText() {
		return text;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}

}
