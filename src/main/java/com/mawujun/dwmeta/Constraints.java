package com.mawujun.dwmeta;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.mawujun.annotation.FK;
import com.mawujun.annotation.FieldDefine;
import com.mawujun.annotation.ShowType;
import com.mawujun.repository.idEntity.UUIDEntity;

/**
 * 约束
 * @author mawujun qq:16064988 mawujun1234@163.com
 *
 */
@Entity
@Table(name="dw_constraints")
public class Constraints extends UUIDEntity {
	
	@FieldDefine(title="约束名称",hidden=false)
	@Column(length=36) 
	private String name;
	@FieldDefine(title="约束类型",sort=6,showType=ShowType.combobox)
	@Enumerated(EnumType.STRING)
	private ConstraintsType type;//是主键(PK)，还是外键(FK),unique
	
	
	
//	@FieldDefine(title="是否可用",sort=6)
//	private Boolean status;
	
	@FieldDefine(title="所属表",hidden=true)
	@Column(length=36) 
	@FK(cls=Tablemeta.class,column="id")
	private String tablemeta_id;
	
	@FieldDefine(title="创建时间",hidden=true)
	@Column(updatable=false) 
	private Date createTime;
	@FieldDefine(title="创建者",hidden=true)
	@Column(length=36,updatable=false) 
	private String creater;
	
	@Transient
	private String history_id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ConstraintsType getType() {
		return type;
	}

	public void setType(ConstraintsType type) {
		this.type = type;
	}

	public String getTablemeta_id() {
		return tablemeta_id;
	}

	public void setTablemeta_id(String tablemeta_id) {
		this.tablemeta_id = tablemeta_id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getHistory_id() {
		return history_id;
	}

	public void setHistory_id(String history_id) {
		this.history_id = history_id;
	}
	

}
