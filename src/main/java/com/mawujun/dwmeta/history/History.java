package com.mawujun.dwmeta.history;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.mawujun.annotation.FK;
import com.mawujun.annotation.FieldDefine;
import com.mawujun.dwmeta.Tablemeta;

@MappedSuperclass
public abstract class History {
	@Id
	@FieldDefine(title="id",sort=7,hidden=true)
	private Long id;//以一个操作的批次为id
	
	@FieldDefine(title="tablemeta_id",hidden=true)
	@Column(length=36,nullable=false) 
	@FK(cls=Tablemeta.class,column="id")
	private String tablemeta_id;//
	
	@FieldDefine(title="操作类型",hidden=false)
	@Column(length=36,nullable=false) 
	@Enumerated(EnumType.STRING)
	private OperateType operateType;
	@FieldDefine(title="操作时间",hidden=false)
	private Date operateTime;
	@FieldDefine(title="操作者",hidden=false)
	@Column(length=36,nullable=false) 
	private String operater;
	@FieldDefine(title="是否主动",hidden=false)
	private Boolean intiactive;//主动修改，还是被动修改。如果先在元数据管理修改的话就是主动修改
//	@FieldDefine(title="修改内容",hidden=false)
//	@Column(length=36,nullable=false) 
//	private String content;

}
