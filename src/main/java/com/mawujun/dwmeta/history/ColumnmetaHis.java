package com.mawujun.dwmeta.history;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.mawujun.annotation.FK;
import com.mawujun.annotation.FieldDefine;
import com.mawujun.dwmeta.Tablemeta;

@Entity
@Table(name="dw_tablemetahis")
public class ColumnmetaHis {
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
	private String content;
	
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
