package com.mawujun.dwmeta.history;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.dwmeta.Columnmeta;
import com.mawujun.dwmeta.Tablemeta;
import com.mawujun.dwmeta.TablemetaRepository;
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
public class HisColmetaService extends AbstractService<HisColmeta, String>{

	@Autowired
	private HisColmetaRepository historyColmetaRepository;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private TablemetaRepository tablemetaRepository;
//	@Autowired
//	private ColumnmetaRepository columnmetaRepository;
	
	@Override
	public HisColmetaRepository getRepository() {
		return historyColmetaRepository;
	}

	public String logCreate(Columnmeta columnmeta) {
//		//先判断当前列是否是创建的第一个列，如果是就同时建立同个批次的表的记录,表示这个表还每建立过
//		int count=historyColmetaRepository.queryCount(Cnd.count()
//				.andEquals(M.HistoryColmeta.tablemeta_id, columnmeta.getId())
//				.andEquals(M.HistoryColmeta.history_id, columnmeta.getHistory_id()));
//		Date createdate=new Date();
//		if(count==0){
//			Tablemeta tablemeta=tablemetaRepository.get(columnmeta.getTablemeta_id());
//			//创建历史记录
//			HisTabmeta historytabmeta=BeanUtils.copyOrCast(tablemeta, HisTabmeta.class);
//			historytabmeta.setId(null);
//			historytabmeta.setHis_createDate(createdate);
//			historytabmeta.setHis_content(his_content);
//			historyTabmetaService.create(historytabmeta);
//		}
//		
//		HisColmeta entity=BeanUtils.copyOrCast(columnmeta, HisColmeta.class);
//		entity.setId(null);
//		entity.setHis_createDate(createdate);
//		entity.setColumnmeta_id(columnmeta.getId());
//		historyColmetaRepository.create(entity);
//		
//		History history=new History();
//		history.setId(columnmeta.getHistory_id());
//		history.setIntiactive(true);
//		history.setOperater(operater);
//		history.setOperateTime(createdate);
//		history.setTablemeta_id(tablemeta_id);
//		history.setOperateType(operateType);
//		historyService.create(history);
//		
//		return this.getRepository().create(entity);
		return null;
	}

}
