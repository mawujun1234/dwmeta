package com.mawujun.dwmeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.dwmeta.history.HisColmetaService;
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
	@Autowired
	private HisColmetaService historyColmetaService;
	
	@Override
	public ColumnmetaRepository getRepository() {
		return columnmetaRepository;
	}
	@Override
	public String create(Columnmeta entity) {
		this.getRepository().create(entity);
		//先判断当前列是否是创建的第一个列，如果是就同时建立同个批次的表的记录
		historyColmetaService.logCreate(entity);

		return entity.getId();
	}

}
