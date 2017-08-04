package com.mawujun.dwmeta.history;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.mawujun.annotation.FK;
import com.mawujun.annotation.FieldDefine;
import com.mawujun.dwmeta.Columnmeta;
import com.mawujun.dwmeta.Tablemeta;

@Entity
@Table(name="dw_his_coleta")
public class HisColmeta {
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
	
	
	//=================================================历史要新增的内容
	@FieldDefine(title="history_id",hidden=true)
	@Column(length=36,nullable=false) 
	@FK(cls=History.class,column="id")
	private Long history_id;//也就是修改批次的id
	@FieldDefine(title="修改内容",hidden=false)
	@Column(length=36,nullable=false) 
	private String his_content;
	@FieldDefine(title="创建时间",hidden=false)
	private Date his_createDate;
	@FieldDefine(title="columnmeta_id",hidden=true)
	@Column(length=36,nullable=false) 
	@FK(cls=Columnmeta.class,column="id")
	private String columnmeta_id;//
	
	
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
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Integer getSorted() {
		return sorted;
	}
	public void setSorted(Integer sorted) {
		this.sorted = sorted;
	}
	public String getReasons() {
		return reasons;
	}
	public void setReasons(String reasons) {
		this.reasons = reasons;
	}
	public String getTablemeta_id() {
		return tablemeta_id;
	}
	public void setTablemeta_id(String tablemeta_id) {
		this.tablemeta_id = tablemeta_id;
	}
	public Long getHistory_id() {
		return history_id;
	}
	public void setHistory_id(Long history_id) {
		this.history_id = history_id;
	}
	public String getHis_content() {
		return his_content;
	}
	public void setHis_content(String his_content) {
		this.his_content = his_content;
	}

	public Date getHis_createDate() {
		return his_createDate;
	}
	public void setHis_createDate(Date his_createDate) {
		this.his_createDate = his_createDate;
	}
	public String getColumnmeta_id() {
		return columnmeta_id;
	}
	public void setColumnmeta_id(String columnmeta_id) {
		this.columnmeta_id = columnmeta_id;
	}

	
//	@FieldDefine(title="操作类型",hidden=false)
//	@Column(length=36,nullable=false) 
//	@Enumerated(EnumType.STRING)
//	private OperateType operateType;
//	@FieldDefine(title="操作时间",hidden=false)
//	private Date operateTime;
////	@FieldDefine(title="操作者",hidden=false)
////	@Column(length=36,nullable=false) 
////	private String operater;
////	@FieldDefine(title="是否主动",hidden=false)
////	private Boolean intiactive;//主动修改，还是被动修改。如果先在元数据管理修改的话就是主动修改
//	@FieldDefine(title="修改内容",hidden=false)
//	@Column(length=36,nullable=false) 
//	private String content;
}
