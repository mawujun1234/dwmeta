package com.mawujun.dwmeta;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
	@FieldDefine(title="列",hidden=false)
	@Column(length=36) 
	private String col_id;
	
	@FieldDefine(title="引用的表",hidden=true)
	@Column(length=36) 
	@FK(cls=Tablemeta.class,column="id")
	private String ref_table_id;
	@FieldDefine(title="引用的列",hidden=true)
	@Column(length=36) 
	@FK(cls=Columnmeta.class,column="id")
	private String ref_col_id;
	
	@FieldDefine(title="约束主表",hidden=true)
	@Column(length=36) 
	@FK(cls=Constraints.class,column="id")
	private String constraints_id;
	


	public String getConstraints_id() {
		return constraints_id;
	}

	public void setConstraints_id(String constraints_id) {
		this.constraints_id = constraints_id;
	}



	public String getCol_id() {
		return col_id;
	}

	public void setCol_id(String col_id) {
		this.col_id = col_id;
	}

	public String getRef_table_id() {
		return ref_table_id;
	}

	public void setRef_table_id(String ref_table_id) {
		this.ref_table_id = ref_table_id;
	}

	public String getRef_col_id() {
		return ref_col_id;
	}

	public void setRef_col_id(String ref_col_id) {
		this.ref_col_id = ref_col_id;
	}

}
