package com.mawujun.dwmeta;

public enum Dbtype {
	
	oracle("oracle","oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@<host>:<port>:<SID>"
			,new String[]{"CHAR(N)","NCHAR(N)","VARCHAR2(N CHAR)","NVARCHAR2(N)","NUMBER(P,S)","INTEGER","FLOAT(N)","DATE","TIMESTAMP"})
	,mysql("mysql","com.mysql.jdbc.Driver","jdbc:mysql://<host>:<port>/<database_name>?useUnicode=true&characterEncoding=utf8"
			,new String[]{"CHAR(N)","VARCHAR(N)","TINYINT","SMALLINT","MEDIUMINT","INTEGER","BIGINT","FLOAT","DOUBLE","DECIMAL(P,S)","DATE","DATETIME","TIMESTAMP"})
	,sqlserver("sqlserver","com.microsoft.jdbc.sqlserver.SQLServerDriver","jdbc:microsoft:sqlserver://<server_name>:<port>",
			new String[]{"char(n)","varchar(n)","nchar(n)","nvarchar(n)","tinyint","smallint","int","decimal(P,S)","numeric(P,S)","float[(n)]","datetime","timestamp"});
	
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
