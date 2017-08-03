package com.mawujun.dwmeta.history;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.mawujun.annotation.FK;
import com.mawujun.annotation.FieldDefine;
import com.mawujun.dwmeta.Classify;
/**
 * 保留修改时的所有原有的信息，这样后续才能进行比对
 * @author mawujun qq:16064988 mawujun1234@163.com
 *
 */
@Entity
@Table(name="dw_tablemetahis")
public class TablemetaHis {
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
	private String content;
//	@FieldDefine(title="tablemeta_id",hidden=true)
//	@Column(length=36,nullable=false) 
//	@FK(cls=Tablemeta.class,column="id")
//	private String tablemeta_id;//
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
}
