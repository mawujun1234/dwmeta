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
import com.mawujun.dwmeta.Classify;
import com.mawujun.dwmeta.Tablemeta;
/**
 * 保留修改时的所有原有的信息，这样后续才能进行比对
 * @author mawujun qq:16064988 mawujun1234@163.com
 *
 */
@Entity
@Table(name="dw_history_tabmeta")
public class HistoryTabmeta {
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
	@Column(length=20)
	@FieldDefine(title="表还是视图")
	private String entitytype;//表还是视图
	@Column(length=150)
	@FieldDefine(title="备注")
	private String remark;
	
	@FieldDefine(title="所属分类",hidden=true)
	@Column(length=36,nullable=false) 
	@FK(cls=Classify.class,column="id")
	private String classify_id;//
	
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
	@FieldDefine(title="tablemeta_id",hidden=true)
	@Column(length=36,nullable=false) 
	@FK(cls=Tablemeta.class,column="id")
	private String tablemeta_id;//
	
//	
//	@FieldDefine(title="操作类型",hidden=false)
//	@Column(length=36,nullable=false) 
//	@Enumerated(EnumType.STRING)
//	private OperateType operateType;
//	@FieldDefine(title="操作时间",hidden=false)
//	private Date operateTime;
//	@FieldDefine(title="操作者",hidden=false)
//	@Column(length=36,nullable=false) 
//	private String operater;
//	@FieldDefine(title="是否主动",hidden=false)
//	private Boolean intiactive;//主动修改，还是被动修改。如果先在元数据管理修改的话就是主动修改
//	@FieldDefine(title="修改内容",hidden=false)
//	@Column(length=36,nullable=false) 
//	private String content;
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
	public String getEntitytype() {
		return entitytype;
	}
	public void setEntitytype(String entitytype) {
		this.entitytype = entitytype;
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
	public String getTablemeta_id() {
		return tablemeta_id;
	}
	public void setTablemeta_id(String tablemeta_id) {
		this.tablemeta_id = tablemeta_id;
	}

}
