package com.mawujun.dwmeta.kpi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.mawujun.service.AbstractService;


import com.mawujun.dwmeta.kpi.ColDefine;
import com.mawujun.dwmeta.kpi.ColDefineRepository;


/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class ColDefineService extends AbstractService<ColDefine, String>{

	@Autowired
	private ColDefineRepository colDefineRepository;
	
	@Override
	public ColDefineRepository getRepository() {
		return colDefineRepository;
	}

}
