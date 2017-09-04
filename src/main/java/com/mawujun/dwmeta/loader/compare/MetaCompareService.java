package com.mawujun.dwmeta.loader.compare;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.druid.pool.DruidDataSource;
import com.mawujun.dwmeta.DWLayer;
import com.mawujun.dwmeta.DWLayerService;
import com.mawujun.dwmeta.Tablemeta;
import com.mawujun.dwmeta.TablemetaService;
import com.mawujun.dwmeta.loader.DefaultMetaLoaderFactory;
import com.mawujun.dwmeta.loader.JDBCUtils;
import com.mawujun.dwmeta.loader.MetaLoader;
import com.mawujun.dwmeta.loader.schema.ColumnType;
import com.mawujun.dwmeta.loader.schema.SchemaInfo;
import com.mawujun.dwmeta.loader.schema.Table;
import com.mawujun.repository.cnd.Cnd;
import com.mawujun.utils.M;
@Service
public class MetaCompareService {
	private static Logger logger=LogManager.getLogger(MetaCompareService.class);
	@Autowired
	private DWLayerService dWLayerService;
	@Autowired
	private TablemetaService tablemetaService;
	
	private Map<String,DataSource> dataSources=new HashMap<String,DataSource>();
	
	private DefaultMetaLoaderFactory factory=new DefaultMetaLoaderFactory();

	public void setFactory(DefaultMetaLoaderFactory factory) {
		this.factory = factory;
	}

	public DataSource initDataSource(String jdbc_driver,String jdbc_url,String jdbc_username,String jdbc_password) {
		
		
		DruidDataSource datasource = new DruidDataSource();
		// BasicDataSource datasource=new BasicDataSource();
		datasource.setDriverClassName(jdbc_driver);
		datasource.setUrl(jdbc_url);
		datasource.setUsername(jdbc_username);
		datasource.setPassword(jdbc_password);

		datasource.setDefaultAutoCommit(true);

		// 通常来说，只�?要修改initialSize、minIdle、maxActive�?
		datasource.setInitialSize(2);
		datasource.setMinIdle(2);
		datasource.setMaxActive(5);
		// datasource.setMaxWait(600000);

		// 如果用Oracle，则把poolPreparedStatements配置为true，mysql可以配置为false。分库分表较多的数据库，建议配置为false�?
		datasource.setPoolPreparedStatements(false);
		
		
		return datasource;
	}
	public DataSource getDataSource(String dwlayer_id,String jdbc_driver,String jdbc_url,String jdbc_username,String jdbc_password){
		DataSource datasource=dataSources.get(dwlayer_id);
		if(datasource==null){
			datasource=initDataSource( jdbc_driver, jdbc_url, jdbc_username, jdbc_password);
			dataSources.put(dwlayer_id, datasource);
		}
		return datasource;
	}
//	public MetaLoader getMetaLoader(String dwlayer_id,String jdbc_driver,String jdbc_url,String jdbc_username,String jdbc_password){
//		DataSource datasource=dataSources.get(dwlayer_id);
//		if(datasource==null){
//			datasource=initDataSource( jdbc_driver, jdbc_url, jdbc_username, jdbc_password);
//			dataSources.put(dwlayer_id, datasource);
//		}
//		
//		
//		MetaLoader metaloader=factory.newInstance(con);
//		return metaloader;
//	}
	
	public Set<ColumnType> getColumnTypes(String dwlayer_id) {
		Connection con =null;
		try{
			DWLayer dwlayer=dWLayerService.get(dwlayer_id);
			con = JDBCUtils.getConnection(getDataSource(dwlayer_id,dwlayer.getJdbc_driver(),dwlayer.getJdbc_url(),dwlayer.getJdbc_username(),dwlayer.getJdbc_password()));
			MetaLoader metaloader=factory.newInstance(con);
			//MetaLoader metaloader=getMetaLoader(dwlayer_id,dwlayer.getJdbc_driver(),dwlayer.getJdbc_url(),dwlayer.getJdbc_username(),dwlayer.getJdbc_password());
			//con =metaloader.getConnection();
			
			Set<ColumnType> bbb=metaloader.getColumnTypes();
//			int i=0;
//			 for(ColumnType ct:bbb){
//				 System.out.println(i+"------类型");i++;
//				 System.out.println(ct.getType_name()+"---data_type:"+ct.getData_type());
//				 System.out.println(ct.getLocal_type_name());
//				 System.out.println(ct.getPrecision());
//				 System.out.println(ct.getCanprecision());
//				 System.out.println(ct.getMinimum_scale()+"-----"+ct.getMaximum_scale());
//			 }
			return bbb;
		} finally {
			JDBCUtils.closeConnection(con);
		}
	}
	
	public List<DiffMsg> checkChayi(String dwlayer_id){
		Set<String> db_tablemetaes=getTableNames(dwlayer_id);
		
		List<Tablemeta> tablemetaes=tablemetaService.query(Cnd.select().andEquals(M.Tablemeta.dwlayer_id,dwlayer_id));
		
		Set<String> sametablename=new HashSet<String>();
		//比较获取出两个数据库之间的差异
		List<DiffMsg> list=new ArrayList<DiffMsg>();
		//本地比数据库多的时候
		for(Tablemeta tt:tablemetaes){
			DiffMsg chayi=new DiffMsg();
			if(db_tablemetaes.contains(tt.getTablename())){
				sametablename.add(tt.getTablename());
				continue;
			} else {
				chayi.setTablename(tt.getTablename());
				chayi.setDiffMsgType(DiffMsgType.table_more);
			}
			list.add(chayi);
		}
		for(String tablename:db_tablemetaes){
			DiffMsg chayi=new DiffMsg();
			if(tablemetaes.contains(tablename)){
				continue;
			} else {
				chayi.setTablename(tablename);
				chayi.setDiffMsgType(DiffMsgType.table_less);
			}
			list.add(chayi);
		}
		
		
		for(String tablename:sametablename){
			Table db_table=this.getTable(dwlayer_id, tablename);
			Table table=tablemetaService.getTable(dwlayer_id,tablename);
			
		}
		
		//检查表的注释是否一致
		//简化表的列信息是否一致
		//检查表的约束是否一致
		
		return list;
	}
	
	public Set<String> getTableNames(String dwlayer_id) {	
	
		DWLayer dwlayer=dWLayerService.get(dwlayer_id);
		Connection con = JDBCUtils.getConnection(getDataSource(dwlayer_id,dwlayer.getJdbc_driver(),dwlayer.getJdbc_url(),dwlayer.getJdbc_username(),dwlayer.getJdbc_password()));
		MetaLoader metaloader=factory.newInstance(con);
		//MetaLoader metaloader=getMetaLoader(dwlayer_id,dwlayer.getJdbc_driver(),dwlayer.getJdbc_url(),dwlayer.getJdbc_username(),dwlayer.getJdbc_password());

//		MetaLoader metaloader=getMetaLoader(dwlayer_id,dwlayer.getJdbc_driver(),dwlayer.getJdbc_url(),dwlayer.getJdbc_username(),dwlayer.getJdbc_password());
//		Connection con =metaloader.getConnection();
		Set<String> db_tablemetaes=new HashSet<String>();
		try{
			metaloader=factory.newInstance(con);

			if(StringUtils.hasText(dwlayer.getCatalogName()) || StringUtils.hasText(dwlayer.getSchemaName())) {
				SchemaInfo aaa=new SchemaInfo();
				aaa.setCatalogName(dwlayer.getCatalogName());
				aaa.setSchemaName(dwlayer.getSchemaName());
				db_tablemetaes= metaloader.getTableNames(aaa);
			} else {
				db_tablemetaes= metaloader.getTableNames();
			}

		} finally {
			JDBCUtils.closeConnection(con);
		}
		
		return db_tablemetaes;			
	}
	
	
	public Table getTable(String dwlayer_id,String tableName){	
		DWLayer dwlayer=dWLayerService.get(dwlayer_id);
		Connection con = JDBCUtils.getConnection(getDataSource(dwlayer_id,dwlayer.getJdbc_driver(),dwlayer.getJdbc_url(),dwlayer.getJdbc_username(),dwlayer.getJdbc_password()));
		MetaLoader metaloader=factory.newInstance(con);
		
		try{
			metaloader=factory.newInstance(con);

			return metaloader.getTable(tableName);

		} finally {
			JDBCUtils.closeConnection(con);
		}
	}
}
