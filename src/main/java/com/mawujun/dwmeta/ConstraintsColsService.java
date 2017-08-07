package com.mawujun.dwmeta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.mawujun.service.AbstractService;


import com.mawujun.dwmeta.ConstraintsCols;
import com.mawujun.dwmeta.ConstraintsColsRepository;


/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class ConstraintsColsService extends AbstractService<ConstraintsCols, String>{

	@Autowired
	private ConstraintsColsRepository constraintsColsRepository;
	
	@Override
	public ConstraintsColsRepository getRepository() {
		return constraintsColsRepository;
	}

}
