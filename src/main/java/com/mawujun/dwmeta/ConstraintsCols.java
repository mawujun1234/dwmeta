package com.mawujun.dwmeta;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.tool.hbm2ddl.TableMetadata;

import com.mawujun.annotation.FK;
import com.mawujun.annotation.FieldDefine;
import com.mawujun.repository.idEntity.UUIDEntity;

/**
 * 约束组成的列
 * @author mawujun qq:16064988 mawujun1234@163.com
 *
 */
@Entity
@Table(name="dw_cons_cols")
public class ConstraintsCols extends UUIDEntity{
	@FieldDefine(title="约束的列",hidden=false)
	@Column(length=36) 
	private String column;
	
	@FieldDefine(title="引用的表",hidden=true)
	@Column(length=36) 
	@FK(cls=Tablemeta.class,column="id")
	private String ref_table_id;
	
	@FieldDefine(title="约束主表",hidden=true)
	@Column(length=36) 
	@FK(cls=Constraints.class,column="id")
	private String constraints_id;
	

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getConstraints_id() {
		return constraints_id;
	}

	public void setConstraints_id(String constraints_id) {
		this.constraints_id = constraints_id;
	}

}
