package com.mawujun.dwmeta.loader.compare;

import java.sql.Connection;
import java.util.HashMap;
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
import com.mawujun.dwmeta.loader.DefaultMetaLoaderFactory;
import com.mawujun.dwmeta.loader.JDBCUtils;
import com.mawujun.dwmeta.loader.MetaLoader;
import com.mawujun.dwmeta.loader.schema.ColumnType;
import com.mawujun.dwmeta.loader.schema.SchemaInfo;
@Service
public class MetaCompareService {
	private static Logger logger=LogManager.getLogger(MetaCompareService.class);
	@Autowired
	private DWLayerService dWLayerService;
	
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
	public MetaLoader getMetaLoader(String dwlayer_id,String jdbc_driver,String jdbc_url,String jdbc_username,String jdbc_password){
		DataSource datasource=dataSources.get(dwlayer_id);
		if(datasource==null){
			datasource=initDataSource( jdbc_driver, jdbc_url, jdbc_username, jdbc_password);
			dataSources.put(dwlayer_id, datasource);
		}
		
		Connection con = JDBCUtils.getConnection(datasource);
		MetaLoader metaloader=factory.newInstance(con);
		return metaloader;
	}
	
	public Set<ColumnType> getColumnTypes(String dwlayer_id) {
		Connection con =null;
		try{
			DWLayer dwlayer=dWLayerService.get(dwlayer_id);
			MetaLoader metaloader=getMetaLoader(dwlayer_id,dwlayer.getJdbc_driver(),dwlayer.getJdbc_url(),dwlayer.getJdbc_username(),dwlayer.getJdbc_password());
			con =metaloader.getConnection();
			
			Set<ColumnType> bbb=metaloader.getColumnTypes();
			
			return bbb;
		} finally {
			JDBCUtils.closeConnection(con);
		}
	}
	
	public Set<String> getTableNames(String dwlayer_id) {	
//		DataSource datasource=dataSources.get(dwlayer_id);
//		DWLayer dwlayer=dWLayerService.get(dwlayer_id);
//		if(dataSources.get(dwlayer_id)==null){
//			datasource=initDataSource(dwlayer);
//		}
//		Connection con = JDBCUtils.getConnection(datasource);
//		
		DWLayer dwlayer=dWLayerService.get(dwlayer_id);
		MetaLoader metaloader=getMetaLoader(dwlayer_id,dwlayer.getJdbc_driver(),dwlayer.getJdbc_url(),dwlayer.getJdbc_username(),dwlayer.getJdbc_password());
		Connection con =metaloader.getConnection();
		try{
			metaloader=factory.newInstance(con);
			 Set<ColumnType> bbb=metaloader.getColumnTypes();
			 int i=0;
			 for(ColumnType ct:bbb){
				 System.out.println(i+"------类型");i++;
				 System.out.println(ct.getType_name()+"---data_type:"+ct.getData_type());
				 System.out.println(ct.getLocal_type_name());
				 System.out.println(ct.getPrecision());
				 System.out.println(ct.getMinimum_scale()+"-----"+ct.getMaximum_scale());
			 }
			if(StringUtils.hasText(dwlayer.getCatalogName()) || StringUtils.hasText(dwlayer.getSchemaName())) {
				SchemaInfo aaa=new SchemaInfo();
				aaa.setCatalogName(dwlayer.getCatalogName());
				aaa.setSchemaName(dwlayer.getSchemaName());
				return metaloader.getTableNames(aaa);
			} else {
				return metaloader.getTableNames();
			}

		} finally {
			JDBCUtils.closeConnection(con);
		}
	}
}
