package com.mawujun.dwmeta;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.mawujun.annotation.FieldDefine;
import com.mawujun.repository.idEntity.UUIDEntity;

@Entity
@Table(name="dw_db")
public class DB extends UUIDEntity {
	/**
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 */
	private static final long serialVersionUID = 1L;
	@Column(length=50)
	@FieldDefine(title="数据库")
	private String name;
	@Column(length=50)
	@FieldDefine(title="数据库类型")
	@Enumerated(EnumType.STRING)
	private Dbtype dbtype;
	@Column(length=150)
	@FieldDefine(title="备注")
	private String remark;
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
	public Dbtype getDbtype() {
		return dbtype;
	}
	public void setDbtype(Dbtype dbtype) {
		this.dbtype = dbtype;
	}

}
