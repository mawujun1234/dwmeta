package com.mawujun.dwmeta.kpi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.service.AbstractService;


/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class ColClassifyService extends AbstractService<ColClassify, String>{

	@Autowired
	private ColClassifyRepository colClassifyRepository;
	
	@Override
	public ColClassifyRepository getRepository() {
		return colClassifyRepository;
	}

}
