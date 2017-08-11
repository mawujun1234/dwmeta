package com.mawujun.dwmeta;

public enum Dbtype {
	
	oracle("oracle","oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@<host>:<port>:<SID>"
			,new String[]{"CHAR(36)","NCHAR(36)","VARCHAR2(36 CHAR)","NVARCHAR2(36)","NUMBER(12,2)","INTEGER","FLOAT(15)","DATE","TIMESTAMP"})
	,mysql("mysql","com.mysql.jdbc.Driver","jdbc:mysql://<host>:<port>/<database_name>?useUnicode=true&characterEncoding=utf8"
			,new String[]{"CHAR(36)","VARCHAR(36)","TINYINT","SMALLINT","MEDIUMINT","INTEGER","BIGINT","FLOAT","DOUBLE","DECIMAL(12,2)","DATE","DATETIME","TIMESTAMP"})
	,sqlserver("sqlserver","com.microsoft.jdbc.sqlserver.SQLServerDriver","jdbc:microsoft:sqlserver://<server_name>:<port>",
			new String[]{"char(36)","varchar(36)","nchar(36)","nvarchar(36)","tinyint","smallint","int","decimal(12,2)","numeric(12,2)","float(15)","datetime","timestamp"});
	
	private String name;
	private String jdbc_url;
	private String jdbc_driver;
	private String[] fieldtypes;
	Dbtype(String name,String jdbc_driver,String jdbc_url,String[] fieldtypes){
		this.name=name;
		this.jdbc_url=jdbc_url;
		this.jdbc_driver=jdbc_driver;
		this.fieldtypes=fieldtypes;
	}
	public String getName() {
		return name;
	}
	public String getJdbc_url() {
		return jdbc_url;
	}
	public String getJdbc_driver() {
		return jdbc_driver;
	}
	public String[] getFieldtypes() {
		return fieldtypes;
	}

}
