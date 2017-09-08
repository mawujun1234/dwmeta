package com.mawujun.dwmeta.loader.compare;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
import com.mawujun.dwmeta.loader.schema.Column;
import com.mawujun.dwmeta.loader.schema.ColumnType;
import com.mawujun.dwmeta.loader.schema.ForeignKey;
import com.mawujun.dwmeta.loader.schema.ForeignKeyColumn;
import com.mawujun.dwmeta.loader.schema.PrimaryKey;
import com.mawujun.dwmeta.loader.schema.SchemaInfo;
import com.mawujun.dwmeta.loader.schema.Table;
import com.mawujun.dwmeta.loader.schema.UniqueKey;
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
	
	public DiffTable copy2DiffTable(Table table){
		DiffTable difftable=new DiffTable();
		difftable.setName(table.getName());
		difftable.setComment(table.getComment());
		Map<String,Column> columns=table.getColumns();
		for(Entry<String,Column> entry:columns.entrySet()){
			
		}
		
	}
	public List<DiffMsg> checkChayi(String dwlayer_id){
		Set<String> db_tablemetaes=getTableNames(dwlayer_id);
		
		List<Tablemeta> tablemetaes=tablemetaService.query(Cnd.select().andEquals(M.Tablemeta.dwlayer_id,dwlayer_id));
		
		Set<String> sametablename=new HashSet<String>();
		//比较获取出两个数据库之间的差异
		Map<String,DiffMsg> list=new HashMap<String,DiffMsg>();
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
			//list.add(chayi);
			Table table=tablemetaService.getTable(dwlayer_id,tt.getTablename());
			DiffTable diffTable=Bean
			chayi.setDiffTable(table);
			list.put(tt.getTablename(), );
		}
		for(String tablename:db_tablemetaes){
			DiffMsg chayi=new DiffMsg();
			if(tablemetaes.contains(tablename)){
				continue;
			} else {
				chayi.setTablename(tablename);
				chayi.setDiffMsgType(DiffMsgType.table_less);
			}
			DiffTable diffTable=Bean
			chayi.setDiffTable(table);
			//list.add(chayi);
		}
		//
		
		
		for(String tablename:sametablename){
			Table db_table=this.getTable(dwlayer_id, tablename);
			Table table=tablemetaService.getTable(dwlayer_id,tablename);
			//检查表的注释是否一致
			//检查表的列信息是否一致
			DiffMsgType aaa=this.checkColumn(db_table, table);
			if(aaa!=null){
				DiffMsg chayi=new DiffMsg();
				chayi.setTablename(tablename);
				chayi.setDiffMsgType(aaa);
				list.add(chayi);
			}
			//检查表的约束是否一致
			DiffMsgType bbb=this.checkConstraints(db_table, table);
			if(bbb!=null){
				DiffMsg chayi=new DiffMsg();
				chayi.setTablename(tablename);
				chayi.setDiffMsgType(bbb);
				list.add(chayi);
			}
		}
		
		
		
		return list;
	}
	/**
	 * 判断列是否一致
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 * @param db_table
	 * @param table
	 * @return
	 */
	private DiffMsgType checkColumn(Table db_table,Table table) {
		 Map<String, Column> db_columns=db_table.getColumns();
		 Map<String, Column> columns=table.getColumns();
		 
		 if(db_columns.size()!=columns.size()){
			 return DiffMsgType.column_change;
		 }
		 for(Entry<String,Column> entry:db_columns.entrySet()) {
			 Column db_column=entry.getValue();
			 Column column=columns.get(db_column.getName());
			 //列不存在
			 if(column==null){
				 return DiffMsgType.column_change;
			 }
			 //比较列的信息是不是一致
			 if(db_column.getType_name()!=null && !db_column.getType_name().equals(column.getType_name())) {
				 return DiffMsgType.column_change;
			 }
			 if( db_column.getPrecision()!=column.getPrecision()) {
				 return DiffMsgType.column_change;
			 }
			 if( db_column.getScale()!=column.getScale()) {
				 return DiffMsgType.column_change;
			 }
			 if(db_column.getNullable()!=null && !db_column.getNullable().equals(column.getNullable())) {
				 return DiffMsgType.column_change;
			 }
			 if(db_column.getDefaultValue()!=null && !db_column.getDefaultValue().equals(column.getDefaultValue())) {
				 return DiffMsgType.column_change;
			 }
			 if(db_column.getComment()!=null && !db_column.getComment().equals(column.getComment())) {
				 return DiffMsgType.column_change;
			 }
		 }
		 return null;
	}
	
	private DiffMsgType checkConstraints(Table db_table,Table table) {
		PrimaryKey db_pk=db_table.getPrimaryKey();
		PrimaryKey pk=table.getPrimaryKey();
		if(!db_pk.getName().equals(pk.getName())) {
			return DiffMsgType.pk_name;
		}
//		if(!db_pk.getTable_name().equals(pk.getTable_name())) {
//			return DiffMsgType.pk_table_name;
//		}
		List<String> db_columns=db_pk.getColumns();
		List<String> columns=pk.getColumns();
		if(db_columns.size()!=columns.size()){
			return DiffMsgType.pk_column_diff;
		}
		for(String db_col:db_columns){
			if(!columns.contains(db_col)){
				return DiffMsgType.pk_column_diff;
			}
		}
		
		
		//比较外键
		Map<String,ForeignKey> db_fk=db_table.getForeignkeys();
		Map<String,ForeignKey> fk=table.getForeignkeys();
//		if(db_fk.size()!=fk.size()){
//			return DiffMsgType.fk_size_diff;
//		}
		for(String key:db_fk.keySet()){
			if(!fk.containsKey(key)) {
				return DiffMsgType.fk_less;
			}
		}
		for(String key:fk.keySet()) {
			if(!db_fk.containsKey(key)) {
				return DiffMsgType.fk_more;
			}
		}
		for(Entry<String,ForeignKey> entry:db_fk.entrySet()) {
			if(fk.containsKey(entry.getKey())){
				List<ForeignKeyColumn> db_coles=entry.getValue().getColumns();
				List<ForeignKeyColumn> coles=fk.get(entry.getKey()).getColumns();
				//判断同名的外键的列是否一致
				if(db_coles.size()!=coles.size()){
					return DiffMsgType.fk_column_diff;
				} else {
					for(ForeignKeyColumn db_fkc:db_coles) {
						boolean bool=false;
						for(ForeignKeyColumn fkc:coles) {					
							if(db_fkc.getColumn_name().equals(fkc.getColumn_name())){
								bool=true;
								if(!db_fkc.getRef_table_name().equals(fkc.getRef_table_name())){
									return DiffMsgType.fk_ref_table_diff;
								}
								if(!db_fkc.getRef_column_name().equals(fkc.getRef_column_name())){
									return DiffMsgType.fk_ref_column_diff;
								}
							}
						}
						//列名不一致
						if(!bool) {
							return DiffMsgType.fk_column_diff;
						}
					}
				}
				
				
			}	
		}
		
		
		//比较唯一键
		Map<String,UniqueKey> db_uk=db_table.getUniqueKeys();
		Map<String,UniqueKey> uk=table.getUniqueKeys();
		if(db_uk.size()!=uk.size()){
			return DiffMsgType.uk_more;
		}
		for(String key:db_uk.keySet()){
			if(!uk.containsKey(key)) {
				return DiffMsgType.uk_less;
			}
		}
		for(String key:uk.keySet()) {
			if(!db_uk.containsKey(key)) {
				return DiffMsgType.uk_more;
			}
		}
		for(Entry<String,UniqueKey> entry:db_uk.entrySet()){
			if(uk.containsKey(entry.getKey())){
				List<String> db_coles=entry.getValue().getColumns();
				List<String> coles=uk.get(entry.getKey()).getColumns();
				for(String db_col:db_coles){
					boolean bool=false;
					for(String col:coles){
						if(db_col.equals(col)){
							bool=true;
						}
					}
					if(!bool){
						return  DiffMsgType.uk_column_diff;
					}
				}
			}
		}
		
		
		return null;
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
