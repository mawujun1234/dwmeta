package com.mawujun.dwmeta;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.mawujun.generator.model.FK;
import com.mawujun.generator.model.FieldDefine;

/**
 * 列的元数据结构
 * @author mawujun qq:16064988 mawujun1234@163.com
 *
 */
@Entity
@Table(name="dw_colmeta")
public class Colmeta {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(
	        name = "uuid",
	        strategy = "org.hibernate.id.UUIDGenerator"
	    )
	@FieldDefine(title="id",sort=7,hidden=true)
	@Column(length=36,nullable=false)
	private String id;
	@Column(length=30,nullable=false,unique=false)
	@FieldDefine(title="列名",sort=6)
	private String colname;
	@Column(length=30,nullable=true,unique=false)
	@FieldDefine(title="名称",sort=6)
	private String name;//中文名称，易于理解的名称
	
	@Column(length=30,nullable=false,unique=false)
	@FieldDefine(title="类型",sort=6)
	private String coltype;
	@Column(length=30,nullable=true,unique=false)
	@FieldDefine(title="长度",sort=6)
	private String collen;
	@FieldDefine(title="是否主键",sort=6)
	private Boolean ispk;
	@FieldDefine(title="可否为空",sort=6)
	private Boolean nullable;
	@Column(length=50,nullable=true,unique=false)
	@FieldDefine(title="默认值",sort=6)
	private String defaultvalue;
	@Column(length=100,nullable=true,unique=false)
	@FieldDefine(title="注释",sort=6)
	private String desc;//写入到数据库中的值
	
	
	@FieldDefine(title="所属表",hidden=true)
	@Column(length=36) 
	@FK(cls=Tablemeta.class,column="id")
	private String tablemeta_id;//

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getColtype() {
		return coltype;
	}

	public void setColtype(String coltype) {
		this.coltype = coltype;
	}

	public String getCollen() {
		return collen;
	}

	public void setCollen(String collen) {
		this.collen = collen;
	}

	public Boolean getIspk() {
		return ispk;
	}

	public void setIspk(Boolean ispk) {
		this.ispk = ispk;
	}

	public Boolean getNullable() {
		return nullable;
	}

	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}

	public String getDefaultvalue() {
		return defaultvalue;
	}

	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTablemeta_id() {
		return tablemeta_id;
	}

	public void setTablemeta_id(String tablemeta_id) {
		this.tablemeta_id = tablemeta_id;
	}
	

}
