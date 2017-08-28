package com.mawujun.dwmeta.history;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.dwmeta.Columnmeta;
import com.mawujun.dwmeta.Constraints;
import com.mawujun.dwmeta.Tablemeta;
import com.mawujun.dwmeta.TablemetaRepository;
import com.mawujun.permission.ShiroUtils;
import com.mawujun.repository.cnd.Cnd;
import com.mawujun.service.AbstractService;
import com.mawujun.utils.M;
import com.mawujun.utils.bean.BeanUtils;


/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class HistoryService extends AbstractService<History, String>{

	@Autowired
	private HistoryRepository historyRepository;
	
	@Autowired
	private HisColmetaRepository hisColmetaRepository;
	@Autowired
	private HisTabmetaService hisTabmetaService;

	@Autowired
	private TablemetaRepository tablemetaRepository;
	
	@Override
	public HistoryRepository getRepository() {
		return historyRepository;
	}
	
	public void createTabmeta(Tablemeta tablemeta,String history_id){
		
		Date createdate=new Date();
	
		//Tablemeta tablemeta=tablemetaRepository.get(columnmeta.getTablemeta_id());
		//创建历史记录
		HisTabmeta historytabmeta=BeanUtils.copyOrCast(tablemeta, HisTabmeta.class);
		historytabmeta.setId(null);
		historytabmeta.setHistory_id(history_id);
		historytabmeta.setTablemeta_id(tablemeta.getId());
		historytabmeta.setHis_createDate(createdate);
		
		hisTabmetaService.create(historytabmeta);
		
	}
	
	public void updateTabmeta(Tablemeta tablemeta,String history_id){
//		String history_id=tablemeta.getHistory_id();
		Date createdate=new Date();
//		
//		History history = this.get(history_id);
//		if (history == null) {
//			history = new History();
//			history.setId(history_id);
//			history.setIntiactive(true);
//			history.setOperater(ShiroUtils.getUserId());
//			history.setOperateTime(createdate);
//			history.setTablemeta_id(tablemeta.getId());
//			history.setOperateType(OperateType.update_table);
//			this.create(history);
//		}
		
		HisTabmeta histabmeta=BeanUtils.copyOrCast(tablemeta, HisTabmeta.class);
		histabmeta.setId(null);
		histabmeta.setHistory_id(history_id);
		histabmeta.setHis_createDate(createdate);
		histabmeta.setTablemeta_id(tablemeta.getId());
		hisTabmetaService.create(histabmeta);
		
	}
	
	public void deleteTabmeta(Tablemeta tablemeta,String history_id){
		//String history_id=tablemeta.getHistory_id();
		Date createdate=new Date();
		
//		History history = this.get(history_id);
//		if (history == null) {
//			history = new History();
//			history.setId(history_id);
//			history.setIntiactive(true);
//			history.setOperater(ShiroUtils.getUserId());
//			history.setOperateTime(createdate);
//			history.setTablemeta_id(tablemeta.getId());
//			history.setOperateType(OperateType.delete_table);
//			this.create(history);
//		}
//		
		HisTabmeta histabmeta=BeanUtils.copyOrCast(tablemeta, HisTabmeta.class);
		histabmeta.setId(null);
		histabmeta.setHistory_id(history_id);
		histabmeta.setHis_createDate(createdate);
		histabmeta.setTablemeta_id(tablemeta.getId());
		hisTabmetaService.create(histabmeta);
		
	}
	
//	public void createColmeta(Columnmeta columnmeta,String history_id) {
//		//String history_id=columnmeta.getHistory_id();
//		//先判断当前列是否是创建的第一个列，如果是就同时建立同个批次的表的记录,表示这个表还每建立过
//		int count=hisColmetaRepository.queryCount(Cnd.count()
//				.andEquals(M.HisColmeta.tablemeta_id, columnmeta.getId()));
//				//.andEquals(M.HisColmeta.history_id, history_id));
//		Date createdate=new Date();
//		boolean iscreateTable=false;
//		if(count==0){
//			//再判断这个表如果是已经建立过了
//			iscreateTable=true;
//			Tablemeta tablemeta=tablemetaRepository.get(columnmeta.getTablemeta_id());
//			//创建历史记录
//			HisTabmeta historytabmeta=BeanUtils.copyOrCast(tablemeta, HisTabmeta.class);
//			historytabmeta.setId(null);
//			historytabmeta.setHistory_id(history_id);
//			historytabmeta.setTablemeta_id(columnmeta.getTablemeta_id());
//			historytabmeta.setHis_createDate(createdate);
//			hisTabmetaService.create(historytabmeta);
//		}
//		
//		HisColmeta hiscolmeta=BeanUtils.copyOrCast(columnmeta, HisColmeta.class);
//		hiscolmeta.setId(null);
//		hiscolmeta.setHistory_id(history_id);
//		hiscolmeta.setHis_createDate(createdate);
//		hiscolmeta.setColumnmeta_id(columnmeta.getId());
//		hiscolmeta.setHis_colOperateType(ColOperateType.create_col);
//		hisColmetaRepository.create(hiscolmeta);
//		
//		//创建历史记录
//		History history=this.get(history_id);
//		if(history==null){
//			history=new History();
//			history.setId(history_id);
//			history.setIntiactive(true);
//			history.setOperater(ShiroUtils.getUserId());
//			history.setOperateTime(createdate);
//			history.setTablemeta_id(columnmeta.getTablemeta_id());
//			history.setOperateType(iscreateTable==true?OperateType.create_table:OperateType.update_table);
//			
//			this.create(history);
//		}
//		
//		//创建sql
//		//history.setSql_content("");
//
//	}
	public void createColmeta(Columnmeta columnmeta,String history_id) {
		HisColmeta hiscolmeta=BeanUtils.copyOrCast(columnmeta, HisColmeta.class);
		hiscolmeta.setId(null);
		hiscolmeta.setHistory_id(history_id);
		hiscolmeta.setHis_createDate(new Date());
		hiscolmeta.setColumnmeta_id(columnmeta.getId());
		hiscolmeta.setHis_colOperateType(ColOperateType.create_col);
		hisColmetaRepository.create(hiscolmeta);
	}
	
	public void updateColmeta(Columnmeta columnmeta,String history_id) {
		Date createdate=new Date();
		//String history_id=columnmeta.getHistory_id();
//		// 创建历史记录
//		History history = this.get(history_id);
//		if (history == null) {
//			history = new History();
//			history.setId(history_id);
//			history.setIntiactive(true);
//			history.setOperater(ShiroUtils.getUserId());
//			history.setOperateTime(createdate);
//			history.setTablemeta_id(columnmeta.getTablemeta_id());
//			history.setOperateType(OperateType.update_table);
//			this.create(history);
//		}
		
		HisColmeta hiscolmeta=BeanUtils.copyOrCast(columnmeta, HisColmeta.class);
		hiscolmeta.setId(null);
		hiscolmeta.setHistory_id(history_id);
		hiscolmeta.setHis_createDate(createdate);
		hiscolmeta.setColumnmeta_id(columnmeta.getId());
		hiscolmeta.setHis_colOperateType(ColOperateType.update_col);
		hisColmetaRepository.create(hiscolmeta);
		
		//创建sql
		//history.setSql_content("");
	}
	public void deleteColmeta(Columnmeta columnmeta,String history_id) {
		Date createdate=new Date();
		//String history_id=columnmeta.getHistory_id();
		// 创建历史记录
//		History history = this.get(history_id);
//		if (history == null) {
//			history = new History();
//			history.setId(history_id);
//			history.setIntiactive(true);
//			history.setOperater(ShiroUtils.getUserId());
//			history.setOperateTime(createdate);
//			history.setTablemeta_id(columnmeta.getTablemeta_id());
//			history.setOperateType(OperateType.update_table);
//			this.create(history);
//		}
		
		HisColmeta hiscolmeta=BeanUtils.copyOrCast(columnmeta, HisColmeta.class);
		hiscolmeta.setId(null);
		hiscolmeta.setHistory_id(history_id);
		hiscolmeta.setHis_createDate(createdate);
		hiscolmeta.setColumnmeta_id(columnmeta.getId());
		hiscolmeta.setHis_colOperateType(ColOperateType.delete_col);
		hisColmetaRepository.create(hiscolmeta);
		
		//创建sql
		//history.setSql_content("");
	}
	
	
	public void updateConstraints(Constraints constraints) {
		Date createdate=new Date();
		String history_id=constraints.getHistory_id();
		// 创建历史记录
		History history = this.get(history_id);
		if (history == null) {
			history = new History();
			history.setId(history_id);
			history.setIntiactive(true);
			history.setOperater(ShiroUtils.getUserId());
			history.setOperateTime(createdate);
			history.setTablemeta_id(constraints.getTablemeta_id());
			//history.setOperateType(OperateType.update_table);
			this.create(history);
		}
		
//		HisColmeta hiscolmeta=BeanUtils.copyOrCast(columnmeta, HisColmeta.class);
//		hiscolmeta.setId(null);
//		hiscolmeta.setHistory_id(history_id);
//		hiscolmeta.setHis_createDate(createdate);
//		hiscolmeta.setColumnmeta_id(columnmeta.getId());
//		hiscolmeta.setHis_colOperateType(ColOperateType.update_col);
//		hisColmetaRepository.create(hiscolmeta);
//		
//		//创建sql
//		//history.setSql_content("");
	}

}
