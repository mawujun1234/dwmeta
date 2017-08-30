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

import com.alibaba.druid.pool.DruidDataSource;
import com.mawujun.dwmeta.DWLayer;
import com.mawujun.dwmeta.DWLayerService;
import com.mawujun.dwmeta.loader.DefaultMetaLoaderFactory;
import com.mawujun.dwmeta.loader.JDBCUtils;
import com.mawujun.dwmeta.loader.MetaLoader;
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

	public DataSource getDataSource(String dwlayer_id) {
		if(dataSources.get(dwlayer_id)!=null){
			return dataSources.get(dwlayer_id);
		}
		DWLayer dwlayer=dWLayerService.get(dwlayer_id);
		DruidDataSource datasource = new DruidDataSource();
		// BasicDataSource datasource=new BasicDataSource();
		datasource.setDriverClassName(dwlayer.getJdbc_driver());
		datasource.setUrl(dwlayer.getJdbc_url());
		datasource.setUsername(dwlayer.getJdbc_username());
		datasource.setPassword(dwlayer.getJdbc_password());

		datasource.setDefaultAutoCommit(true);

		// 通常来说，只�?要修改initialSize、minIdle、maxActive�?
		datasource.setInitialSize(2);
		datasource.setMinIdle(2);
		datasource.setMaxActive(5);
		// datasource.setMaxWait(600000);

		// 如果用Oracle，则把poolPreparedStatements配置为true，mysql可以配置为false。分库分表较多的数据库，建议配置为false�?
		datasource.setPoolPreparedStatements(false);
		
		dataSources.put(dwlayer.getId(), datasource);
		return datasource;
	}
	
	public Set<String> getTableNames(String dwlayer_id) {		
		Connection con = JDBCUtils.getConnection(getDataSource(dwlayer_id));
		MetaLoader metaloader=null;
		try{
			metaloader=factory.newInstance(con);
			return metaloader.getTableNames();
		} finally {
			JDBCUtils.closeConnection(con);
		}
	}
}
