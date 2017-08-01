package com.mawujun.dwmeta.kpi;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.controller.spring.mvc.MapParams;
import com.mawujun.service.AbstractService;


/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class KpiService extends AbstractService<Kpi, String>{

	@Autowired
	private KpiRepository kpiRepository;
	
	@Override
	public KpiRepository getRepository() {
		return kpiRepository;
	}
	public List<Kpi> queryAll(MapParams params) {
		return kpiRepository.queryAll(params.getParams());
	}

}
