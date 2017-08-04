package com.mawujun.dwmeta.history;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mawujun.annotation.FK;
import com.mawujun.annotation.FieldDefine;
import com.mawujun.dwmeta.Tablemeta;

@Entity
@Table(name="dw_history")
public  class History {
	@Id
	@FieldDefine(title="id",sort=7,hidden=true)
	private String id;//以一个操作的批次为id
	
	@FieldDefine(title="tablemeta_id",hidden=true)
	@Column(length=36,nullable=false) 
	@FK(cls=Tablemeta.class,column="id")
	private String tablemeta_id;//
	
	@FieldDefine(title="操作类型",hidden=false)
	@Column(length=36,nullable=false) 
	@Enumerated(EnumType.STRING)
	private OperateType operateType;
	@FieldDefine(title="操作时间",hidden=false)
	private Date operateTime;
	@FieldDefine(title="操作者",hidden=false)
	@Column(length=36,nullable=false) 
	private String operater;
	@FieldDefine(title="是否主动",hidden=false)
	private Boolean intiactive;//主动修改，还是被动修改。如果先在元数据管理修改的话就是主动修改
//	@FieldDefine(title="修改内容",hidden=false)
//	@Column(length=36,nullable=false) 
//	private String content;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTablemeta_id() {
		return tablemeta_id;
	}
	public void setTablemeta_id(String tablemeta_id) {
		this.tablemeta_id = tablemeta_id;
	}
	public OperateType getOperateType() {
		return operateType;
	}
	public void setOperateType(OperateType operateType) {
		this.operateType = operateType;
	}
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	public String getOperater() {
		return operater;
	}
	public void setOperater(String operater) {
		this.operater = operater;
	}
	public Boolean getIntiactive() {
		return intiactive;
	}
	public void setIntiactive(Boolean intiactive) {
		this.intiactive = intiactive;
	}

}
