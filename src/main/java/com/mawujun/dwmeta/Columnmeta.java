package com.mawujun.dwmeta;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.mawujun.annotation.FK;
import com.mawujun.annotation.FieldDefine;

/**
 * 列的元数据结构
 * @author mawujun qq:16064988 mawujun1234@163.com
 *
 */
@Entity
@Table(name="dw_columnmeta")
public class Columnmeta {
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
	@FieldDefine(title="中文名称",sort=6)
	private String name;//中文名称，易于理解的名称
	
	@Column(length=30,nullable=false,unique=false)
	@FieldDefine(title="类型",sort=6)
	private String coltype;
//	@Column(length=30,nullable=true,unique=false)
//	@FieldDefine(title="长度",sort=6)
//	private String collen;
	@FieldDefine(title="是否主键",sort=6)
	private Boolean ispk;
	@FieldDefine(title="可否为空",sort=6)
	private Boolean nullable;
	@Column(length=50,nullable=true,unique=false)
	@FieldDefine(title="默认值",sort=6)
	private String defaultvalue;
	@Column(length=100,nullable=true,unique=false)
	@FieldDefine(title="注释",sort=6)
	private String comments;//写入到数据库的字段的注释
	@FieldDefine(title="排序",sort=6)
	private Integer sorted;//排序
	@Column(length=200,nullable=true,unique=false)
	@FieldDefine(title="修改原因",sort=6)
	private String reasons;
	
	
	@FieldDefine(title="所属表",hidden=true)
	@Column(length=36) 
	@FK(cls=Tablemeta.class,column="id")
	private String tablemeta_id;//
	
	@Transient
	private String history_id;

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



	public String getTablemeta_id() {
		return tablemeta_id;
	}

	public void setTablemeta_id(String tablemeta_id) {
		this.tablemeta_id = tablemeta_id;
	}

	public Integer getSorted() {
		return sorted;
	}

	public void setSorted(Integer sorted) {
		this.sorted = sorted;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getReasons() {
		return reasons;
	}

	public void setReasons(String reasons) {
		this.reasons = reasons;
	}

	public String getHistory_id() {
		return history_id;
	}

	public void setHistory_id(String history_id) {
		this.history_id = history_id;
	}


	

}
