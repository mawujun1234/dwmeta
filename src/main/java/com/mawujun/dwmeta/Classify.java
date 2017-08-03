package com.mawujun.dwmeta;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.mawujun.annotation.FK;
import com.mawujun.annotation.FieldDefine;
/**
 * 分类，对表进行分类进行查看,在DWLayer下面
 * @author mawujun qq:16064988 mawujun1234@163.com
 *
 */
@Entity
@Table(name="dw_classify")
public class Classify {
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
	@FieldDefine(title="名称",sort=6)
	private String name;
	
	@Column(length=150)
	@FieldDefine(title="备注")
	private String remark;
	
	@FieldDefine(title="dwlayer_id",hidden=true)
	@Column(length=36) 
	@FK(cls=DWLayer.class,column="id")
	private String dwlayer_id;//属于哪一层下面的分类，冗余标识清楚
	@FieldDefine(title="父id",hidden=true)
	@Column(length=36) 
	@FK(cls=Classify.class,column="id")
	private String parent_id;//自己的上下级关系
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getDwlayer_id() {
		return dwlayer_id;
	}
	public void setDwlayer_id(String dwlayer_id) {
		this.dwlayer_id = dwlayer_id;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	
	
	

}
