package com.mawujun.dwmeta.history;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.mawujun.service.AbstractService;


import com.mawujun.dwmeta.history.HistoryColmeta;
import com.mawujun.dwmeta.history.HistoryColmetaRepository;


/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class HistoryColmetaService extends AbstractService<HistoryColmeta, String>{

	@Autowired
	private HistoryColmetaRepository historyColmetaRepository;
	
	@Override
	public HistoryColmetaRepository getRepository() {
		return historyColmetaRepository;
	}

}
