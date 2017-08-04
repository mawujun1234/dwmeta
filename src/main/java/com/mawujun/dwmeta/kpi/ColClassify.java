package com.mawujun.dwmeta.kpi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.mawujun.annotation.FK;
import com.mawujun.annotation.FieldDefine;
import com.mawujun.dwmeta.DB;
import com.mawujun.repository.idEntity.UUIDEntity;

/**
 * 常用字段分类
 * @author mawujun qq:16064988 mawujun1234@163.com
 *
 */
@Entity
@Table(name="dw_colclassify")
public class ColClassify extends UUIDEntity {
	@Column(length=30,nullable=false,unique=false)
	@FieldDefine(title="名称",sort=6)
	private String name;
	
	@Column(length=150)
	@FieldDefine(title="备注")
	private String remark;
	@FieldDefine(title="已删除")
	private Boolean deleted=false;
	
	@FieldDefine(title="所属数据库",hidden=true)
	@Column(length=36,nullable=false) 
	@FK(cls=DB.class,column="id")
	private String db_id;//

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDb_id() {
		return db_id;
	}

	public void setDb_id(String db_id) {
		this.db_id = db_id;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	

}
