package com.mawujun.dwmeta;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.mawujun.service.AbstractService;


import com.mawujun.dwmeta.Columnmeta;
import com.mawujun.dwmeta.ColumnmetaRepository;


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
	
	@Override
	public ColumnmetaRepository getRepository() {
		return columnmetaRepository;
	}

}
