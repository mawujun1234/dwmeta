package com.mawujun.utils;
public final class M {
public static final class Constant {
	public static final String id="id";
	public static final String name="name";
	public static final String sort="sort";
}
public static final class ConstantItem {
	public static final String id="id";
	public static final String name="name";
	public static final String sort="sort";
	public static final String status="status";
	public static final String constant_id="constant_id";
}
public static final class ColClassify {
	public static final String name="name";
	public static final String remark="remark";
	public static final String deleted="deleted";
	public static final String db_id="db_id";
	public static final String id="id";
}
public static final class ColDefine {
	public static final String colname="colname";
	public static final String name="name";
	public static final String definition="definition";
	public static final String status="status";
	public static final String remark="remark";
	public static final String coltype="coltype";
	public static final String colclassify_id="colclassify_id";
	public static final String id="id";
}
public static final class Kpi {
	public static final String id="id";
	public static final String code="code";
	public static final String name="name";
	public static final String definition="definition";
	public static final String status="status";
	public static final String remark="remark";
	public static final String kpi_type_id="kpi_type_id";
}
public static final class Classify {
	public static final String id="id";
	public static final String name="name";
	public static final String remark="remark";
	public static final String dwlayer_id="dwlayer_id";
	public static final String parent_id="parent_id";
}
public static final class Columnmeta {
	public static final String id="id";
	public static final String colname="colname";
	public static final String name="name";
	public static final String coltype="coltype";
	public static final String nullable="nullable";
	public static final String defaultvalue="defaultvalue";
	public static final String comments="comments";
	public static final String sorted="sorted";
	public static final String reasons="reasons";
	public static final String tablemeta_id="tablemeta_id";
}
public static final class Constraints {
	public static final String name="name";
	public static final String type="type";
	public static final String tablemeta_id="tablemeta_id";
	public static final String createTime="createTime";
	public static final String creater="creater";
	public static final String history_id="history_id";
	public static final String id="id";
}
public static final class ConstraintsCols {
	public static final String col_id="col_id";
	public static final String ref_table_id="ref_table_id";
	public static final String ref_col_id="ref_col_id";
	public static final String constraints_id="constraints_id";
	public static final String id="id";
}
public static final class DB {
	public static final String name="name";
	public static final String dbtype="dbtype";
	public static final String remark="remark";
	public static final String id="id";
}
public static final class DWLayer {
	public static final String id="id";
	public static final String name="name";
	public static final String jdbc_driver="jdbc_driver";
	public static final String jdbc_url="jdbc_url";
	public static final String jdbc_username="jdbc_username";
	public static final String jdbc_password="jdbc_password";
	public static final String remark="remark";
	public static final String db_id="db_id";
}
public static final class HisColmeta {
	public static final String id="id";
	public static final String colname="colname";
	public static final String name="name";
	public static final String coltype="coltype";
	public static final String nullable="nullable";
	public static final String defaultvalue="defaultvalue";
	public static final String comments="comments";
	public static final String sorted="sorted";
	public static final String reasons="reasons";
	public static final String tablemeta_id="tablemeta_id";
	public static final String history_id="history_id";
	public static final String his_colOperateType="his_colOperateType";
	public static final String his_createDate="his_createDate";
	public static final String columnmeta_id="columnmeta_id";
}
public static final class HisTabmeta {
	public static final String id="id";
	public static final String tablename="tablename";
	public static final String name="name";
	public static final String remark="remark";
	public static final String dwlayer_id="dwlayer_id";
	public static final String classify_id="classify_id";
	public static final String history_id="history_id";
	public static final String his_createDate="his_createDate";
	public static final String tablemeta_id="tablemeta_id";
}
public static final class History {
	public static final String id="id";
	public static final String tablemeta_id="tablemeta_id";
	public static final String operateTime="operateTime";
	public static final String operater="operater";
	public static final String intiactive="intiactive";
	public static final String sql_content="sql_content";
}
public static final class Tablemeta {
	public static final String id="id";
	public static final String tablename="tablename";
	public static final String name="name";
	public static final String status="status";
	public static final String remark="remark";
	public static final String dwlayer_id="dwlayer_id";
	public static final String classify_id="classify_id";
	public static final String db_id="db_id";
}
public static final class Org {
	public static final String id="id";
	public static final String name="name";
	public static final String code="code";
	public static final String isroot="isroot";
	public static final String layer="layer";
	public static final String status="status";
	public static final String sort="sort";
	public static final String phonenumber="phonenumber";
	public static final String fax="fax";
	public static final String address="address";
	public static final String postalcode="postalcode";
	public static final String email="email";
	public static final String web="web";
	public static final String introduction="introduction";
	public static final String isdel="isdel";
	public static final String operator_id="operator_id";
	public static final String operatetime="operatetime";
	public static final String orgtype_id="orgtype_id";
	public static final String parent_id="parent_id";
	public static final String createdate="createdate";
	public static final String enddate="enddate";
}
public static final class Position {
	public static final String id="id";
	public static final String name="name";
	public static final String remark="remark";
	public static final String org_id="org_id";
	public static final String positiontype_id="positiontype_id";
}
public static final class Menu {
	public static final String id="id";
	public static final String code="code";
	public static final String name="name";
	public static final String url="url";
	public static final String leaf="leaf";
	public static final String menuType="menuType";
	public static final String parent_id="parent_id";
	public static final String sort="sort";
	public static final String remark="remark";
}
public static final class Role {
	public static final String id="id";
	public static final String name="name";
	public static final String roleType="roleType";
	public static final String remark="remark";
	public static final String parent_id="parent_id";
}
public static final class RoleMenu {
	 /**
	 * 返回关联对象的属性，，以对象关联的方式(a.b这种形式)，只有一些基本属性，层级不再往下了
	 */
	public static final class menu {
		public static final String id="menu.id";
		public static final String code="menu.code";
		public static final String name="menu.name";
		public static final String url="menu.url";
		public static final String leaf="menu.leaf";
		public static final String menuType="menu.menuType";
		public static final String parent_id="menu.parent_id";
		public static final String sort="menu.sort";
		public static final String remark="menu.remark";
			
	    /**
	    * 返回的是关联类的属性名称，主要用于属性过滤的时候
	    */
	    public static String name(){ 
		    return "menu";
	    }
	}
	 /**
	 * 返回关联对象的属性，，以对象关联的方式(a.b这种形式)，只有一些基本属性，层级不再往下了
	 */
	public static final class role {
		public static final String id="role.id";
		public static final String name="role.name";
		public static final String roleType="role.roleType";
		public static final String remark="role.remark";
		public static final String parent_id="role.parent_id";
			
	    /**
	    * 返回的是关联类的属性名称，主要用于属性过滤的时候
	    */
	    public static String name(){ 
		    return "role";
	    }
	}
}
public static final class RoleUser {
	 /**
	 * 返回关联对象的属性，，以对象关联的方式(a.b这种形式)，只有一些基本属性，层级不再往下了
	 */
	public static final class user {
		public static final String id="user.id";
		public static final String name="user.name";
		public static final String loginName="user.loginName";
		public static final String pwd="user.pwd";
		public static final String phone="user.phone";
		public static final String mobile="user.mobile";
		public static final String email="user.email";
		public static final String remark="user.remark";
		public static final String state="user.state";
		public static final String candel="user.candel";
		public static final String lastlogintime="user.lastlogintime";
			
	    /**
	    * 返回的是关联类的属性名称，主要用于属性过滤的时候
	    */
	    public static String name(){ 
		    return "user";
	    }
	}
	 /**
	 * 返回关联对象的属性，，以对象关联的方式(a.b这种形式)，只有一些基本属性，层级不再往下了
	 */
	public static final class role {
		public static final String id="role.id";
		public static final String name="role.name";
		public static final String roleType="role.roleType";
		public static final String remark="role.remark";
		public static final String parent_id="role.parent_id";
			
	    /**
	    * 返回的是关联类的属性名称，主要用于属性过滤的时候
	    */
	    public static String name(){ 
		    return "role";
	    }
	}
}
public static final class User {
	public static final String id="id";
	public static final String name="name";
	public static final String loginName="loginName";
	public static final String pwd="pwd";
	public static final String phone="phone";
	public static final String mobile="mobile";
	public static final String email="email";
	public static final String remark="remark";
	public static final String state="state";
	public static final String candel="candel";
	public static final String lastlogintime="lastlogintime";
}
}
