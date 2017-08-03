package com.mawujun.dwmeta.history;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.mawujun.service.AbstractService;


import com.mawujun.dwmeta.history.HistoryTabmeta;
import com.mawujun.dwmeta.history.HistoryTabmetaRepository;


/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class HistoryTabmetaService extends AbstractService<HistoryTabmeta, String>{

	@Autowired
	private HistoryTabmetaRepository historyTabmetaRepository;
	
	@Override
	public HistoryTabmetaRepository getRepository() {
		return historyTabmetaRepository;
	}

}
