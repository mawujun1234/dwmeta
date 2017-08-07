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
 * 表的元数据结构
 * @author mawujun qq:16064988 mawujun1234@163.com
 *
 */
@Entity
@Table(name="dw_tablemeta")
public class Tablemeta {
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
	@FieldDefine(title="表名",sort=6)
	private String tablename;
	@Column(length=30,nullable=true,unique=false)
	@FieldDefine(title="中文名称",sort=6)
	private String name;//中文名称，易于理解的名称
//	@Column(length=20)
//	@FieldDefine(title="实体类型")
//	private String entitytype;//实体类型，表还是视图
	@Column(length=150)
	@FieldDefine(title="备注")
	private String remark;
	
	

	@FieldDefine(title="所属分类",hidden=true)
	@Column(length=36,nullable=false) 
	@FK(cls=Classify.class,column="id")
	private String classify_id;//

	@Transient
	private String db_id;
	@Transient
	private String history_id;

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getTablename() {
		return tablename;
	}



	public void setTablename(String tablename) {
		this.tablename = tablename;
	}



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



	public String getClassify_id() {
		return classify_id;
	}



	public void setClassify_id(String classify_id) {
		this.classify_id = classify_id;
	}



	public String getDb_id() {
		return db_id;
	}



	public void setDb_id(String db_id) {
		this.db_id = db_id;
	}



//	public String getEntitytype() {
//		return entitytype;
//	}
//
//
//
//	public void setEntitytype(String entitytype) {
//		this.entitytype = entitytype;
//	}



	public String getHistory_id() {
		return history_id;
	}



	public void setHistory_id(String history_id) {
		this.history_id = history_id;
	}



}
