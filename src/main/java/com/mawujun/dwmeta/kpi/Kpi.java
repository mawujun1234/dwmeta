package com.mawujun.dwmeta.kpi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.mawujun.annotation.FK;
import com.mawujun.annotation.FieldDefine;
import com.mawujun.annotation.ShowType;
import com.mawujun.constant.ConstantItem;

@Entity
@Table(name="dw_kpi")
public class Kpi {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(
	        name = "uuid",
	        strategy = "org.hibernate.id.UUIDGenerator"
	    )
	@FieldDefine(title="id",sort=7,hidden=true)
	@Column(length=36,nullable=false)
	private String id;
	@Column(length=50,nullable=false)
	@FieldDefine(title="编码",genQuery=true)
	private String code;
	@Column(length=50,nullable=false)
	@FieldDefine(title="名称",genQuery=true)
	private String name;
	@Column(length=150)
	@FieldDefine(title="定义")
	private String definition;
	@FieldDefine(title="可用",showType=ShowType.radio)
	private Boolean status;
	@Column(length=150)
	@FieldDefine(title="备注")
	private String remark;
	
	@Column(length=36)
	@FieldDefine(title="指标类型")
	@FK(cls=ConstantItem.class,column="id")
	private String kpi_type_id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getKpi_type_id() {
		return kpi_type_id;
	}
	public void setKpi_type_id(String kpi_type_id) {
		this.kpi_type_id = kpi_type_id;
	}

}
