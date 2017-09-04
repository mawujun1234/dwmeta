package com.mawujun.dwmeta;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.dwmeta.history.History;
import com.mawujun.dwmeta.history.HistoryService;
import com.mawujun.dwmeta.history.HistoryVO;
import com.mawujun.dwmeta.loader.schema.Column;
import com.mawujun.dwmeta.loader.schema.Table;
import com.mawujun.exception.BusinessException;
import com.mawujun.permission.ShiroUtils;
import com.mawujun.repository.cnd.Cnd;
import com.mawujun.service.AbstractService;
import com.mawujun.utils.M;
import com.mawujun.utils.string.StringUtils;


/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class TablemetaService extends AbstractService<Tablemeta, String>{
	@Autowired
	private DBService dbservice;
	@Autowired
	private TablemetaRepository tablemetaRepository;
	@Autowired
	private ColumnmetaService columnmetaService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private ConstraintsService constraintsService;
	SimpleDateFormat yyyyMMddHHmmssSSSS=new SimpleDateFormat("yyyyMMddHHmmssSSSS");
	
	@Override
	public TablemetaRepository getRepository() {
		return tablemetaRepository;
	}
	@Override
	public String create(Tablemeta entity) {
		//判断表名是否重复
		int count=tablemetaRepository.exists_tablename(entity.getDwlayer_id(), entity.getTablename());
		if(count>0){
			throw new BusinessException("在当前数据库表名已经存在，不准添加，请修改!");
		}
		 this.getRepository().create(entity);
		return entity.getId();
	}
	
	public List<HistoryVO> queryHistory(String tablemeta_id) {	
		return tablemetaRepository.queryHistory(tablemeta_id);
	}
	
	public String showCreatetablesql(String db_id,String tablemeta_id) {
		Tablemeta tablemeta=this.get(tablemeta_id);
		tablemeta.setDb_id(db_id);
		List<Columnmeta> createcoles=columnmetaService.query(Cnd.select().andEquals(M.Columnmeta.tablemeta_id, tablemeta_id));
		List<ConstraintsVO> createConstraints=constraintsService.queryAll(tablemeta_id);
		TablemetaDTO tablemetaDTO=new TablemetaDTO();
		tablemetaDTO.setTablemeta(tablemeta);
		tablemetaDTO.setCreatecoles(createcoles);
		tablemetaDTO.setCreateConstraints(createConstraints);
		String sql_content=sql_create(tablemetaDTO);
		return sql_content;
	}
	
	public void createorupdate(TablemetaDTO tablemetaDTO ) {
		String history_id=yyyyMMddHHmmssSSSS.format(new Date());
		String tablemeta_id=null;
		Boolean creteaTable=false;
		if(StringUtils.hasText(tablemetaDTO.getTablemeta().getId())){
			this.update(tablemetaDTO.getTablemeta());
			tablemeta_id=tablemetaDTO.getTablemeta().getId();
		} else {
			//判断该表是否已经创建了
			
			
			tablemeta_id=create(tablemetaDTO.getTablemeta());
			creteaTable=true;
		}
		History history = historyService.get(history_id);
		if (history == null) {
			history = new History();
			history.setId(history_id);
			history.setIntiactive(true);
			history.setOperater(ShiroUtils.getUserId());
			history.setOperateTime(new Date());
			history.setTablemeta_id(tablemeta_id);
			//history.setOperateType(OperateType.create_table);
			historyService.create(history);
		}
		//这里要进行判断，如果tablemeta没有变化，就不进行修改,如果都没有改变就不建立History
		//historyService.createTabmeta(tablemetaDTO.getTablemeta(), history_id);
		
		for(Columnmeta columnmeta:tablemetaDTO.getCreatecoles()){
			columnmeta.setTablemeta_id(tablemeta_id);
			columnmetaService.create(columnmeta);
			//historyService.createColmeta(columnmeta,history_id);
		}
		for(Columnmeta columnmeta:tablemetaDTO.getUpdatecoles()){
			columnmetaService.update(columnmeta);
			//historyService.createColmeta(columnmeta,history_id);
		}
		for(Columnmeta columnmeta:tablemetaDTO.getDeletecoles()){
			columnmetaService.delete(columnmeta);
			//historyService.createColmeta(columnmeta,history_id);
		}
		
		for(ConstraintsVO vo:tablemetaDTO.getCreateConstraints()){
			vo.setTablemeta_id(tablemeta_id);
			constraintsService.create(vo);
		}
		for(Constraints entity:tablemetaDTO.getDeleteConstraints()){
			constraintsService.delete(entity);
		}
		
		//生成sql
		if(creteaTable){
			String sql_content=sql_create(tablemetaDTO);
			history.setSql_content(sql_content);
			historyService.update(history);
		} else {
			//对表格进行修改
			String sql_content=sql_update(tablemetaDTO);
			history.setSql_content(sql_content);
			historyService.update(history);
			
		}
	}
	
	private String sql_create(TablemetaDTO tablemetaDTO) {
		String db_id=tablemetaDTO.getTablemeta().getDb_id();
		DB db=dbservice.get(db_id);
		
		StringBuilder builder=new StringBuilder("create table "+tablemetaDTO.getTablemeta().getTablename()+"(");
		
		int i=0;
		for(Columnmeta columnmeta:tablemetaDTO.getCreatecoles()) {
			i++;
			builder.append(columnmeta.getColname()+" "+columnmeta.getColtype());
			if(StringUtils.hasText(columnmeta.getDefaultvalue())){
				builder.append(" default '"+columnmeta.getDefaultvalue()+"'");
			}
			if(columnmeta.getNullable()){
				builder.append(" not null ");
			}
			
			if(i!=tablemetaDTO.getCreatecoles().size()){
				builder.append(",");
				builder.append("\n");
			}
			
		}
		builder.append(");");
		builder.append("\n");
		
		//为表添加注释//COMMENT ON table t1 IS '个人信息';
		if(tablemetaDTO.getTablemeta().getRemark()!=null){
			builder.append("comment on table "+tablemetaDTO.getTablemeta().getTablename()+" is '"+tablemetaDTO.getTablemeta().getRemark()+"';");
			builder.append("\n");
		}
		
		
		
		for(ConstraintsVO vo:tablemetaDTO.getCreateConstraints()){
			if(vo.getType()==ConstraintsType.Primary){
				builder.append("alter table "+tablemetaDTO.getTablemeta().getTablename()+" add constraint "+vo.getName()+" primary key ("+vo.getCol_name()+");");
			}  else if(vo.getType()==ConstraintsType.Unique){
				builder.append("alter table "+tablemetaDTO.getTablemeta().getTablename()+" add constraint "+vo.getName()+" unique ("+vo.getCol_name()+");");
			} else if(vo.getType()==ConstraintsType.Foreign){
				builder.append("alter table "+tablemetaDTO.getTablemeta().getTablename()+" add constraint "+vo.getName()+" foreign key ("+vo.getCol_name()+") references "+vo.getRef_table_name()+"("+vo.getRef_col_name()+");");
			}
			builder.append("\n");
			
		}
		if(db.getDbtype()==Dbtype.oracle){
			//添加注释--oracle的方法
			for(Columnmeta columnmeta:tablemetaDTO.getCreatecoles()) {
				if(StringUtils.hasText(columnmeta.getName())){
					//oracle的方法
					builder.append("comment on column "+tablemetaDTO.getTablemeta().getTablename()+"."+columnmeta.getColname()+" is '"+columnmeta.getName()+"';");
				}
				builder.append("\n");
			}
		}

		return builder.toString();
		
	}
	private String sql_update(TablemetaDTO tablemetaDTO) {
		String db_id=tablemetaDTO.getTablemeta().getDb_id();
		DB db=dbservice.get(db_id);
		StringBuilder builder=new StringBuilder("");
		
//		//为表添加注释//COMMENT ON table t1 IS '个人信息';
//		if(tablemetaDTO.getTablemeta().getRemark()!=null){
//			builder.append("comment on table "+tablemetaDTO.getTablemeta().getTablename()+" is '"+tablemetaDTO.getTablemeta().getRemark()+"';");
//			builder.append("\n");
//		}
		
		
		for(Columnmeta columnmeta:tablemetaDTO.getDeletecoles()) {
			builder.append("alter table "+tablemetaDTO.getTablemeta().getTablename()+" drop column "+columnmeta.getColname()+";");
			builder.append("\n");
		}
		
		
		int i=0;
		for(Columnmeta columnmeta:tablemetaDTO.getCreatecoles()) {
			i++;
			builder.append("alter table "+tablemetaDTO.getTablemeta().getTablename()+" add (");
			builder.append(columnmeta.getColname()+" "+columnmeta.getColtype()+"");
			if(StringUtils.hasText(columnmeta.getDefaultvalue())){
				builder.append(" default '"+columnmeta.getDefaultvalue()+"'");
			}
			if(columnmeta.getNullable()){
				builder.append(" not null ");
			}
//			if(i!=tablemetaDTO.getCreatecoles().size()){
//				builder.append(",");
//			}
			builder.append(");");
			builder.append("\n");
		}
		
		builder.append("\n");
		
	
		
		i=0;
		for(Columnmeta columnmeta:tablemetaDTO.getCreatecoles()) {
			i++;
			builder.append("alter table "+tablemetaDTO.getTablemeta().getTablename()+" modify  (");
			builder.append(columnmeta.getColname()+" "+columnmeta.getColtype()+"");
			if(StringUtils.hasText(columnmeta.getDefaultvalue())){
				builder.append(" default '"+columnmeta.getDefaultvalue()+"'");
			}
			if(columnmeta.getNullable()){
				builder.append(" not null ");
			}
//			if(i!=tablemetaDTO.getCreatecoles().size()){
//				builder.append(",");
//			}
			builder.append(");");
			builder.append("\n");
		}
		
		builder.append("\n");
		
		//添加注释
		if(db.getDbtype()==Dbtype.oracle){
			//添加注释--oracle的方法
			for(Columnmeta columnmeta:tablemetaDTO.getCreatecoles()) {
				if(StringUtils.hasText(columnmeta.getName())){
					//oracle的方法
					builder.append("comment on column "+tablemetaDTO.getTablemeta().getTablename()+"."+columnmeta.getColname()+" is '"+columnmeta.getName()+"';");
				}
				builder.append("\n");
			}
		}
		//删除约束
		for(Constraints vo:tablemetaDTO.getDeleteConstraints()){
			builder.append("alter table "+tablemetaDTO.getTablemeta().getTablename()+" drop constraint "+vo.getName()+";");
			builder.append("\n");
			
		}
		//新增约束
		for(ConstraintsVO vo:tablemetaDTO.getCreateConstraints()){
			if(vo.getType()==ConstraintsType.Primary){
				builder.append("alter table "+tablemetaDTO.getTablemeta().getTablename()+" add constraint "+vo.getName()+" primary key ("+vo.getCol_name()+");");
			}  else if(vo.getType()==ConstraintsType.Unique){
				builder.append("alter table "+tablemetaDTO.getTablemeta().getTablename()+" add constraint "+vo.getName()+" unique ("+vo.getCol_name()+");");
			} else if(vo.getType()==ConstraintsType.Foreign){
				builder.append("alter table "+tablemetaDTO.getTablemeta().getTablename()+" add constraint "+vo.getName()+" foreign key ("+vo.getCol_name()+") references "+vo.getRef_table_name()+"("+vo.getRef_col_name()+");");
			}
			builder.append("\n");
			
		}
		
		
		return builder.toString();
	}
	@Override
	public void deleteById(String tablemeta_id) {
		//this.update(Cnd.update().set(M.Tablemeta.status, false).andEquals(M.Tablemeta.id, tablemeta_id));
		Tablemeta tablemeta=this.get(tablemeta_id);
		tablemeta.setStatus(false);
		this.update(tablemeta);
		
		String history_id=yyyyMMddHHmmssSSSS.format(new Date());
		
		History history = new History();
		history.setId(history_id);
		history.setIntiactive(true);
		history.setOperater(ShiroUtils.getUserId());
		history.setOperateTime(new Date());
		history.setTablemeta_id(tablemeta_id);
		history.setSql_content("drop table "+tablemeta.getTablename()+";");
		historyService.create(history);
		
	}
	
//	@Override
//	public void update(Tablemeta entity) {
//		this.getRepository().update(entity);
//		//historyService.updateTabmeta(entity);
//	}
//	@Override
//	public void delete(Tablemeta entity) {
//		this.getRepository().delete(entity);
//		//historyService.deleteTabmeta(entity);
//	}
	
	public void deleteFocus(String tablemeta_id) {
		//删除历史记录
		historyService.deleteBatch(Cnd.delete().andEquals(M.History.tablemeta_id, tablemeta_id));
		//删除约束
		constraintsService.deleteByTablemeta_id(tablemeta_id);
		//删除列
		columnmetaService.deleteBatch(Cnd.delete().andEquals(M.Columnmeta.tablemeta_id, tablemeta_id));
		//删除表
		this.deleteBatch(Cnd.delete().andEquals(M.Tablemeta.id, tablemeta_id));
		
	}
	/**
	 * 返回完整的Table对象
	 * @param dwlayer_id
	 * @param tablename
	 * @return
	 */
	public Table getTable(String dwlayer_id,String tablename) {
		List<Tablemeta> tablemetaes=this.query(Cnd.select().andEquals(M.Tablemeta.dwlayer_id, dwlayer_id)
				.andEquals(M.Tablemeta.tablename, tablename));
		if(tablemetaes.size()==0){
			throw new BusinessException("该表名不存在!");
		}
		Tablemeta tablemeta=tablemetaes.get(0);
		Table table=new Table();
		table.setName(tablemeta.getTablename());
		table.setComment(tablemeta.getRemark());
		//获取列
		List<Columnmeta> columnmetaes=columnmetaService.query(Cnd.select().andEquals(M.Columnmeta.tablemeta_id, tablemeta.getId()));
		for(Columnmeta cm:columnmetaes){
			Column column=new Column();
			column.setName(cm.getColname());
			column.setType_name(cm.getColtype());
			column.setPrecision(column.getPrecision());
			column.setScale(column.getScale());
			column.setNullable(column.getNullable());
			column.setDefaultValue(cm.getDefaultvalue());
			column.setComment(column.getComment());
			table.addColumn(column);
		}
		//获取约束
		List<ConstraintsVO> constraintsVOs=constraintsService.queryVOs( tablemeta.getId());
		for(ConstraintsVO vo:constraintsVOs){
			if(vo.getType()==ConstraintsType.Primary){
				
			} else if(vo.getType()==ConstraintsType.Foreign){
				
			} else if(vo.getType()==ConstraintsType.Unique){
				
			}
			
		}
		
		
	}

}
