package com.mawujun.dwmeta.loader.compare;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.druid.pool.DruidDataSource;
import com.mawujun.dwmeta.DB;
import com.mawujun.dwmeta.DBService;
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
	private DBService dbService;
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
		difftable.setDiffMsgType(DiffMsgType.none);
		
		try {
			//复制列
			Map<String,Column> columns=table.getColumns();
			for(Entry<String,Column> entry:columns.entrySet()){
				DiffColumn diffcol=new DiffColumn();
				BeanUtils.copyProperties(diffcol, entry.getValue());
				diffcol.setDiffMsgType(DiffMsgType.none);
				difftable.addColumn(diffcol);
			}
			//复制主键
			PrimaryKey pk=table.getPrimaryKey();
			if(pk!=null){
				DiffPrimaryKey diffpk=new DiffPrimaryKey();
				//BeanUtils.copyProperties(diffpk, pk);
				diffpk.setName(pk.getName());
				diffpk.setTable_name(pk.getTable_name());
				for(String aa:pk.getColumns()){
					diffpk.addColumn(aa);
				}
				difftable.setPrimaryKey(diffpk);
			}
			//复制外键
			Map<String,ForeignKey> foreignkeys=table.getForeignkeys();
			if(foreignkeys!=null){
				for(Entry<String,ForeignKey> entry:foreignkeys.entrySet()){
					DiffForeignKey diffcol=new DiffForeignKey();
					//BeanUtils.copyProperties(diffcol, entry.getValue());
					diffcol.setName(entry.getValue().getName());
					for(ForeignKeyColumn fkc:entry.getValue().getColumns()){
						ForeignKeyColumn aa=new ForeignKeyColumn();
						BeanUtils.copyProperties(aa, fkc);
						diffcol.addColumn(aa);
					}
					difftable.addForeignKey(diffcol);
				}
			}
			
			//复制唯一键约束
			Map<String,UniqueKey> uniqueKeys=table.getUniqueKeys();
			if(uniqueKeys!=null){
				for(Entry<String,UniqueKey> entry:uniqueKeys.entrySet()){
					DiffUniqueKey diffcol=new DiffUniqueKey();
					//BeanUtils.copyProperties(diffcol, entry.getValue());
					diffcol.setName(entry.getValue().getName());
					diffcol.setTable_name(entry.getValue().getTable_name());
					for(String aa:pk.getColumns()){
						diffcol.addColumn(aa);
					}
					difftable.addUniqueKey( diffcol);
				}
			}
			
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return difftable;
		
	}
	public  Map<String,DiffMsg> checkChayiByDB(String db_id) throws IllegalAccessException, InvocationTargetException {
		List<DWLayer> list=dWLayerService.query(Cnd.select().andEquals(M.DWLayer.db_id, db_id));
		Map<String,DiffMsg> result=new LinkedHashMap<String,DiffMsg>();
		for(DWLayer dwlayer:list) {
			Map<String,DiffMsg>  aa=checkChayi(dwlayer);
			result.putAll(aa);
		}
		return result;
	}
	
	public Map<String,DiffMsg> checkChayi(String dwlayer_id) throws IllegalAccessException, InvocationTargetException{
		DWLayer dwlayer=dWLayerService.get(dwlayer_id);
		return checkChayi(dwlayer);
	}
	public Map<String,DiffMsg> checkChayi(DWLayer dwlayer) throws IllegalAccessException, InvocationTargetException{
		//DWLayer dwlayer=dWLayerService.get(dwlayer_id);
		String dwlayer_id=dwlayer.getId();
		DB db=dbService.get(dwlayer.getDb_id());
		
		Set<String> db_tablemetaes=getTableNames(dwlayer);
		
		List<Tablemeta> tablemetaes=tablemetaService.query(Cnd.select().andEquals(M.Tablemeta.dwlayer_id,dwlayer_id));
		
		Set<String> sametablename=new HashSet<String>();
		//比较获取出两个数据库之间的差异
		Map<String,DiffMsg> list=new LinkedHashMap<String,DiffMsg>();
		//本地比数据库多的时候，在这里登记了，但还未在系统中建立的表结构
		for(Tablemeta tt:tablemetaes){
			DiffMsg chayi=new DiffMsg();
			chayi.setLayername(dwlayer.getName());
			chayi.setDbname(db.getName());
			if(db_tablemetaes.contains(tt.getTablename())){
				sametablename.add(tt.getTablename());
				continue;
			} else {
				chayi.setTablename(tt.getTablename());
				chayi.setDiffMsgType(DiffMsgType.table_more);
			}
			//list.add(chayi);
			Table table=tablemetaService.getTable(dwlayer_id,tt.getTablename());
			DiffTable diffTable=copy2DiffTable(table);
			diffTable.setDiffMsgType(DiffMsgType.table_more);
			chayi.setDiffTable(diffTable);
			
			//列设置为多了	
			for(Entry<String,Column> entry:diffTable.getColumns().entrySet()){
				((DiffColumn)entry.getValue()).setDiffMsgType(DiffMsgType.column_more);
			}
			//主键设置为多了
			if(diffTable.getPrimaryKey()!=null) {
				((DiffPrimaryKey)diffTable.getPrimaryKey()).setDiffMsgType(DiffMsgType.pk_more);
			}
			//外键设置为多了
			if(diffTable.getForeignkeys()!=null) {
				for(Entry<String,ForeignKey> entry:diffTable.getForeignkeys().entrySet()){
					((DiffForeignKey)entry.getValue()).setDiffMsgType(DiffMsgType.fk_more);
				}
			}
			
			//唯一键设置为多了
			if(diffTable.getUniqueKeys()!=null) {
				for(Entry<String,UniqueKey> entry:diffTable.getUniqueKeys().entrySet()){
					((DiffUniqueKey)entry.getValue()).setDiffMsgType(DiffMsgType.uk_more);
				}
			}
			
			
			list.put(tt.getTablename(), chayi);
		}
		//未在本系统中登记的表结构
		for(String tablename:db_tablemetaes){
			
			boolean bool=false;
			for(Tablemeta tt:tablemetaes) {
				if(tt.getTablename().equals(tablename)) {
					bool=true;
					break;
				}
			}
			
			DiffMsg chayi=new DiffMsg();
			if(bool){
				continue;
			} else {
				chayi.setLayername(dwlayer.getName());
				chayi.setDbname(db.getName());
				chayi.setTablename(tablename);
				chayi.setDiffMsgType(DiffMsgType.table_less);
			}
			Table table=this.getTable(dwlayer_id, tablename);
			DiffTable diffTable=copy2DiffTable(table);
			diffTable.setDiffMsgType(DiffMsgType.table_less);
			chayi.setDiffTable(diffTable);
			
			//列设置为多了	
			for(Entry<String,Column> entry:diffTable.getColumns().entrySet()){
				((DiffColumn)entry.getValue()).setDiffMsgType(DiffMsgType.column_less);
			}
			//主键设置为多了
			if(diffTable.getPrimaryKey()!=null) {
				((DiffPrimaryKey)diffTable.getPrimaryKey()).setDiffMsgType(DiffMsgType.pk_less);
			}
			//外键设置为多了
			if(diffTable.getForeignkeys()!=null) {
				for(Entry<String,ForeignKey> entry:diffTable.getForeignkeys().entrySet()){
					((DiffForeignKey)entry.getValue()).setDiffMsgType(DiffMsgType.fk_less);
				}
			}
			
			//唯一键设置为多了
			if(diffTable.getUniqueKeys()!=null) {
				for(Entry<String,UniqueKey> entry:diffTable.getUniqueKeys().entrySet()){
					((DiffUniqueKey)entry.getValue()).setDiffMsgType(DiffMsgType.uk_less);
				}
			}
			
			list.put(tablename, chayi);
		}
		//
		
		
		for(String tablename:sametablename){
			Table db_table=this.getTable(dwlayer_id, tablename);
			Table table=tablemetaService.getTable(dwlayer_id,tablename);
			//检查表的注释是否一致
			//检查表的列信息是否一致
			DiffTable difftable=copy2DiffTable(table);
			boolean bool=this.checkColumn(db_table, difftable);
			if(!bool){
				DiffMsg chayi=new DiffMsg();
				chayi.setLayername(dwlayer.getName());
				chayi.setDbname(db.getName());
				chayi.setTablename(tablename);
				chayi.setDiffMsgType(DiffMsgType.table_change);
				chayi.setDiffTable(difftable);
				list.put(tablename, chayi);
			}
			//检查表的约束是否一致
			boolean bbb=this.checkConstraints(db_table, difftable);
			if(bool && !bbb){
				DiffMsg chayi=new DiffMsg();
				chayi.setLayername(dwlayer.getName());
				chayi.setDbname(db.getName());
				chayi.setTablename(tablename);
				chayi.setDiffMsgType(DiffMsgType.table_change);
				chayi.setDiffTable(difftable);
				list.put(tablename, chayi);
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
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	private boolean checkColumn(Table db_table,DiffTable difftable) throws IllegalAccessException, InvocationTargetException {
		
		 Map<String, Column> db_columns=db_table.getColumns();
		 Map<String, Column> columns=difftable.getColumns();
		 
//		 if(db_columns.size()!=columns.size()){
//			 return DiffMsgType.column_change;
//		 }
		 Boolean issame=true;
		 for(Entry<String,Column> entry:db_columns.entrySet()) {
			 Column db_column=entry.getValue();
			 Column column=columns.get(db_column.getName());
			 DiffColumn diffcol=new DiffColumn();
			 
			 //列不存在
			 if (column==null) {
				 //return DiffMsgType.column_change;
				 //把数据库中的列拷贝过来，并且设置为less
				 BeanUtils.copyProperties(diffcol,db_column);
				 diffcol.setDiffMsgType(DiffMsgType.column_less);
				 difftable.addColumn(diffcol);
				 continue;
			 } else {
				 BeanUtils.copyProperties(diffcol,column);
				 difftable.addColumn(diffcol);
			 }
			 
			 //比较列的信息是不是一致
			 if(db_column.getType_name()!=null && !db_column.getType_name().equals(column.getType_name())) {
				//return DiffMsgType.column_change;
				 issame=false;
				 diffcol.setDiffMsgType(DiffMsgType.column_change);
				 diffcol.setDiff_type_name(db_column.getType_name());
				 
			 }
			 if( db_column.getPrecision()!=column.getPrecision()) {
				 //return DiffMsgType.column_change;
				 issame=false;
				 diffcol.setDiffMsgType(DiffMsgType.column_change);
				 diffcol.setPrecision(db_column.getPrecision());
			 }
			 if( db_column.getScale()!=column.getScale()) {
				 //return DiffMsgType.column_change;
				 issame=false;
				 diffcol.setDiffMsgType(DiffMsgType.column_change);
				 diffcol.setScale(db_column.getScale());
			 }
			 if(db_column.getNullable()!=null && !db_column.getNullable().equals(column.getNullable())) {
				 //return DiffMsgType.column_change;
				 issame=false;
				 diffcol.setDiffMsgType(DiffMsgType.column_change);
				 diffcol.setNullable(db_column.getNullable());
			 }
			 if(db_column.getDefaultValue()!=null && !db_column.getDefaultValue().equals(column.getDefaultValue())) {
				// return DiffMsgType.column_change;
				 issame=false;
				 diffcol.setDiffMsgType(DiffMsgType.column_change);
				 diffcol.setDefaultValue(db_column.getDefaultValue());
			 }
			 if(db_column.getComment()!=null && !db_column.getComment().equals(column.getComment())) {
				 //return DiffMsgType.column_change;
				 issame=false;
				 diffcol.setDiffMsgType(DiffMsgType.column_change);
				 diffcol.setComment(db_column.getComment());
			 }
		 }
		 return issame;
		 
		 //return null;
	}
	
	private boolean checkConstraints(Table db_table,DiffTable difftable) throws IllegalAccessException, InvocationTargetException {
		boolean issame=true;
		PrimaryKey db_pk=db_table.getPrimaryKey();
		PrimaryKey pk=difftable.getPrimaryKey();
		if(db_pk!=null && pk==null){
			issame=false;
			DiffPrimaryKey db_diffpk=new DiffPrimaryKey();
			BeanUtils.copyProperties(db_diffpk,db_pk );
			db_diffpk.setDiffMsgType(DiffMsgType.pk_less);
			difftable.addDiffPrimaryKey(db_diffpk);
		} else if(db_pk==null && pk!=null){
			issame=false;
			((DiffPrimaryKey)pk).setDiffMsgType(DiffMsgType.pk_more);
			difftable.addDiffPrimaryKey((DiffPrimaryKey)pk);
		} else if(db_pk!=null && pk!=null && !db_pk.getName().equals(pk.getName())) {
			issame=false;
			DiffPrimaryKey db_diffpk=new DiffPrimaryKey();
			BeanUtils.copyProperties(db_diffpk,db_pk);
			db_diffpk.setDiffMsgType(DiffMsgType.pk_less);
			
			db_diffpk.setDiffMsgType(DiffMsgType.pk_more);
			difftable.addDiffPrimaryKey(db_diffpk);	
		} else {
			List<String> db_columns=db_pk.getColumns();
			List<String> columns=pk.getColumns();
			if(db_columns.size()!=columns.size()){
				//return DiffMsgType.pk_column_diff;
				issame=false;
				DiffPrimaryKey diffpk=((DiffPrimaryKey)pk);
				diffpk.setDiffMsgType(DiffMsgType.pk_column_diff);
				
				for(String aa:db_columns){
					diffpk.addColumn(aa);
				}
				
				difftable.addDiffPrimaryKey(diffpk);	
			} else {
				for(String db_col:db_columns) {
					if(!columns.contains(db_col)) {
						issame=false;
						DiffPrimaryKey diffpk=((DiffPrimaryKey)pk);
						diffpk.setDiffMsgType(DiffMsgType.pk_column_diff);
						
						for(String aa:db_columns){
							diffpk.addColumn(aa);
						}
						difftable.addDiffPrimaryKey(diffpk);	
					}
				}
			}
			
		}
		
		
		
		//比较外键
		Map<String,ForeignKey> db_fk=db_table.getForeignkeys();
		Map<String,ForeignKey> fk=difftable.getForeignkeys();
//		if(db_fk.size()!=fk.size()){
//			return DiffMsgType.fk_size_diff;
//		}
		if(db_fk==null){
			db_fk=new HashMap<String,ForeignKey>();
		}
		if(fk==null){
			fk=new HashMap<String,ForeignKey>();
		}
		for(String db_key:db_fk.keySet()){
			if(!fk.containsKey(db_key)) {
				issame=false;
				ForeignKey fk11=db_fk.get(db_key);
				DiffForeignKey diff_fk=new DiffForeignKey();
				BeanUtils.copyProperties(diff_fk, fk11);
				diff_fk.setDiffMsgType(DiffMsgType.fk_less);
				//return DiffMsgType.fk_less;
				difftable.addForeignKey(diff_fk);
			}
		}
		for(String key:fk.keySet()) {
			if(!db_fk.containsKey(key)) {
				issame=false;
				//return DiffMsgType.fk_more;
				DiffForeignKey diff_fk=(DiffForeignKey)fk.get(key);
				diff_fk.setDiffMsgType(DiffMsgType.fk_more);
			}
		}
		for(Entry<String,ForeignKey> entry:db_fk.entrySet()) {
			DiffForeignKey diff_fk=(DiffForeignKey)fk.get(entry.getKey());
			if(diff_fk!=null){
				List<ForeignKeyColumn> db_coles=entry.getValue().getColumns();
				List<ForeignKeyColumn> coles=fk.get(entry.getKey()).getColumns();
				//判断同名的外键的列是否一致
				if(db_coles.size()!=coles.size()){
					issame=false;
					//return DiffMsgType.fk_column_diff;
					diff_fk.setDiffMsgType(DiffMsgType.fk_column_diff);
					for(ForeignKeyColumn fkc:db_coles) {
						ForeignKeyColumn diff_fkc=new ForeignKeyColumn();
						BeanUtils.copyProperties(diff_fkc, fkc);
						diff_fk.addDiff_columns(diff_fkc);
					}
					
				} else {
					boolean bool=true;
					for(ForeignKeyColumn db_fkc:db_coles) {
						bool=true;
						for(ForeignKeyColumn fkc:coles) {					
							if(db_fkc.getColumn_name().equals(fkc.getColumn_name())){
								bool=false;
								if(!db_fkc.getRef_table_name().equals(fkc.getRef_table_name())){
									bool=true;
								} else if(!db_fkc.getRef_column_name().equals(fkc.getRef_column_name())){
									//return DiffMsgType.fk_ref_column_diff;
									bool=true;
								}
							}
						}
					}
					//列名不一致,不是所有的列名都一样
					if(!bool) {
						issame=false;
						//return DiffMsgType.fk_column_diff;
						diff_fk.setDiffMsgType(DiffMsgType.fk_column_diff);
						for(ForeignKeyColumn fkc:db_coles) {
							ForeignKeyColumn diff_fkc=new ForeignKeyColumn();
							BeanUtils.copyProperties(diff_fkc, fkc);
							diff_fk.addDiff_columns(diff_fkc);
						}
					}
				}	
			}//if(fk.containsKey(entry.getKey())){	
		}
		
		
		//比较唯一键
		Map<String,UniqueKey> db_uk=db_table.getUniqueKeys();
		Map<String,UniqueKey> uk=difftable.getUniqueKeys();
		if(db_uk==null){
			db_uk=new HashMap<String,UniqueKey>();
		}
		if(uk==null){
			uk=new HashMap<String,UniqueKey>();
		}

		for(String db_key:db_uk.keySet()){
			if(!uk.containsKey(db_key)) {
				issame=false;
				//return DiffMsgType.uk_less;
				UniqueKey uk11=db_uk.get(db_key);
				DiffUniqueKey diff_uk=new DiffUniqueKey();
				BeanUtils.copyProperties(diff_uk, uk11);
				diff_uk.setDiffMsgType(DiffMsgType.uk_less);
				//return DiffMsgType.fk_less;
				difftable.addUniqueKey(diff_uk);
			}
		}
		for(String key:uk.keySet()) {
			if(!db_uk.containsKey(key)) {
				issame=false;
				//return DiffMsgType.uk_more;
				DiffUniqueKey diff_uk=(DiffUniqueKey)uk.get(key);
				diff_uk.setDiffMsgType(DiffMsgType.uk_more);
			}
		}
		for(Entry<String,UniqueKey> entry:db_uk.entrySet()){
			DiffUniqueKey ukaa=(DiffUniqueKey)uk.get(entry.getKey());
			if(ukaa!=null){
				List<String> db_coles=entry.getValue().getColumns();
				List<String> coles=uk.get(entry.getKey()).getColumns();
				boolean bool=true;
				for(String db_col:db_coles){
					bool=true;
					if(!coles.contains(db_col)){
						bool=false;
						break;
					}
//					for(String col:coles){
//						bool=false;
//						if(db_col.equals(col)){
//							bool=true;
//							break;
//						}
//					}
				}
				if(!bool){
					issame=false;
					for(String db_col:db_coles){
						ukaa.addDiff_columns(db_col);
					}
				}
			}
		}
		
		
		return issame;
	}
	
	public Set<String> getTableNames(DWLayer dwlayer) {	
	
		
		Connection con = JDBCUtils.getConnection(getDataSource(dwlayer.getId(),dwlayer.getJdbc_driver(),dwlayer.getJdbc_url(),dwlayer.getJdbc_username(),dwlayer.getJdbc_password()));
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
