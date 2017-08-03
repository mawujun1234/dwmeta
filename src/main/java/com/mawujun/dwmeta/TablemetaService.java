package com.mawujun.dwmeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.exception.BusinessException;
import com.mawujun.service.AbstractService;


/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class TablemetaService extends AbstractService<Tablemeta, String>{

	@Autowired
	private TablemetaRepository tablemetaRepository;
	
	@Override
	public TablemetaRepository getRepository() {
		return tablemetaRepository;
	}
	
	public String create(Tablemeta entity) {
		//判断表名是否重复
		int count=tablemetaRepository.exists_tablename(entity.getDb_id(), entity.getTablename());
		if(count>0){
			throw new BusinessException("在当前数据库表名已经存在，不准添加，请修改!");
		}
		return this.getRepository().create(entity);
	}

}
