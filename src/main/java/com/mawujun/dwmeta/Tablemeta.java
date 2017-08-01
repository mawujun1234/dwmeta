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
	@FieldDefine(title="名称",sort=6)
	private String name;//中文名称，易于理解的名称
	@Column(length=150)
	@FieldDefine(title="备注")
	private String remark;
	
	

	@FieldDefine(title="所属分类",hidden=true)
	@Column(length=36) 
	@FK(cls=Classify.class,column="id")
	private String classify_id;//



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
	

}
