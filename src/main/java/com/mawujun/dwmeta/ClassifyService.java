package com.mawujun.dwmeta;
import java.util.ArrayList;
import java.util.List;

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
public class ClassifyService extends AbstractService<Classify, String>{

	@Autowired
	private ClassifyRepository classifyRepository;
	@Autowired
	private DWLayerRepository dWLayerRepository;
	@Autowired
	private TablemetaRepository tablemetaRepository;
	
	@Override
	public ClassifyRepository getRepository() {
		return classifyRepository;
	}
	/**
	 * 创建一个默认的分类
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 */
	public void createDefault(String dwlayer_id){
		Classify a=new Classify();
		a.setDwlayer_id(dwlayer_id);
		a.setName("默认分类");
		classifyRepository.create(a);
	}
	
	public List<ClassifyNode> query4tree(String db_id,String parent_id,ClassifyNodeType type,String dwlayer_id,Boolean show_deleted ) {
		if(type==null || "root".equals(parent_id)){
			return dWLayerRepository.query4tree(db_id);
		} else if(type==ClassifyNodeType.dwmeta){
			return classifyRepository.query4tree(null, dwlayer_id);
		} else if(type==ClassifyNodeType.classify){
			List<ClassifyNode> list= classifyRepository.query4tree(parent_id, dwlayer_id);
			List<ClassifyNode> tables=tablemetaRepository.query4tree(parent_id, dwlayer_id,show_deleted);
			list.addAll(tables);
			return list;
			
		}
		return new ArrayList<ClassifyNode>();
	}

}
