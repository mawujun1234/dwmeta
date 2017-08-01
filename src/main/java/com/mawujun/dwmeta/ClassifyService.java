package com.mawujun.dwmeta;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.mawujun.service.AbstractService;


import com.mawujun.dwmeta.Classify;
import com.mawujun.dwmeta.ClassifyRepository;


/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class ClassifyService extends AbstractService<Classify, String>{

	@Autowired
	private ClassifyRepository classifyRepository;
	
	@Override
	public ClassifyRepository getRepository() {
		return classifyRepository;
	}
	
	public List<Classify> query4tree() {
		return this.queryAll();
	}

}
