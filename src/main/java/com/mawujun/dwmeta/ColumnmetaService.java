package com.mawujun.dwmeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.dwmeta.history.HistoryService;
import com.mawujun.service.AbstractService;


/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class ColumnmetaService extends AbstractService<Columnmeta, String>{

	@Autowired
	private ColumnmetaRepository columnmetaRepository;
//	@Autowired
//	private HistoryService historyService;
	
	@Override
	public ColumnmetaRepository getRepository() {
		return columnmetaRepository;
	}
//	@Override
//	public String create(Columnmeta entity) {
//		this.getRepository().create(entity);
//		//
//		historyService.createColmeta(entity);
//
//		return entity.getId();
//	}
//	
//	@Override
//	public void update(Columnmeta entity) {
//		this.getRepository().update(entity);
//		//
//		historyService.updateColmeta(entity);
//	}
//	@Override
//	public void delete(Columnmeta entity) {
//		this.getRepository().delete(entity);
//		//
//		historyService.deleteColmeta(entity);
//	}

}
