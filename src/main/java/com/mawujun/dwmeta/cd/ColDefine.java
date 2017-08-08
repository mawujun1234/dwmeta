package com.mawujun.dwmeta.cd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.mawujun.annotation.FK;
import com.mawujun.annotation.FieldDefine;
import com.mawujun.annotation.ShowType;
import com.mawujun.repository.idEntity.UUIDEntity;

@Entity
@Table(name="dw_cd_coldefine")
public class ColDefine extends UUIDEntity {
	@Column(length=30,nullable=false,unique=false)
	@FieldDefine(title="列名",sort=6,genQuery=true)
	private String colname;
	@Column(length=30,nullable=false,unique=false)
	@FieldDefine(title="中文名称",sort=6,genQuery=true)
	private String name;//中文名称，易于理解的名称,写入到数据库字段的注释中
	@Column(length=250)
	@FieldDefine(title="定义")
	private String definition;
	@FieldDefine(title="可用",showType=ShowType.radio)
	private Boolean status;
	@Column(length=150)
	@FieldDefine(title="备注")
	private String remark;
	
	@Column(length=30,nullable=false,unique=false)
	@FieldDefine(title="列类型",sort=6)
	private String coltype;//字段类型
	
	@FieldDefine(title="常用字段分类",hidden=true)
	@Column(length=36) 
	@FK(cls=ColClassify.class,column="id")
	private String colclassify_id;//

	public String getColname() {
		return colname;
	}

	public void setColname(String colname) {
		this.colname = colname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getColtype() {
		return coltype;
	}

	public void setColtype(String coltype) {
		this.coltype = coltype;
	}

	public String getColclassify_id() {
		return colclassify_id;
	}

	public void setColclassify_id(String colclassify_id) {
		this.colclassify_id = colclassify_id;
	}
}
