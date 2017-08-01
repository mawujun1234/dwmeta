package com.mawujun.dwmeta;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.mawujun.generator.model.FieldDefine;

/**
 * 数据仓库的分层
 * @author mawujun qq:16064988 mawujun1234@163.com
 *
 */
@Entity
@Table(name="dw_dwlayer")
public class DWLayer {
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
	@FieldDefine(title="名称",sort=6,genQuery=true)
	private String name;
	
	@Column(length=50,nullable=false,unique=false)
	@FieldDefine(title="驱动程序",sort=6)
	private String jdbc_driver;
	@Column(length=50,nullable=false,unique=false)
	@FieldDefine(title="连接地址",sort=6)
	private String jdbc_url;
	@Column(length=50,nullable=false,unique=false)
	@FieldDefine(title="账号",sort=6)
	private String jdbc_username;
	@Column(length=50,nullable=false,unique=false)
	@FieldDefine(title="密码",sort=6)
	private String jdbc_password;
	
	@Column(length=150)
	@FieldDefine(title="备注")
	private String remark;

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

	public String getJdbc_driver() {
		return jdbc_driver;
	}

	public void setJdbc_driver(String jdbc_driver) {
		this.jdbc_driver = jdbc_driver;
	}

	public String getJdbc_url() {
		return jdbc_url;
	}

	public void setJdbc_url(String jdbc_url) {
		this.jdbc_url = jdbc_url;
	}

	public String getJdbc_username() {
		return jdbc_username;
	}

	public void setJdbc_username(String jdbc_username) {
		this.jdbc_username = jdbc_username;
	}

	public String getJdbc_password() {
		return jdbc_password;
	}

	public void setJdbc_password(String jdbc_password) {
		this.jdbc_password = jdbc_password;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
