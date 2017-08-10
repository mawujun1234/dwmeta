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



}
