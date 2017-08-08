package com.mawujun.dwmeta;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.exception.BusinessException;
import com.mawujun.permission.ShiroUtils;
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
public class ConstraintsService extends AbstractService<Constraints, String>{

	@Autowired
	private ConstraintsRepository constraintsRepository;
	@Autowired
	private ConstraintsColsRepository constraintsColsRepository;
	
	@Override
	public ConstraintsRepository getRepository() {
		return constraintsRepository;
	}
	
	public List<ConstraintsVO> queryAll(String tablemeta_id)  {	
		List<Constraints> constraintses=constraintsRepository.query(Cnd.select()
				.andEquals(M.Constraints.tablemeta_id, tablemeta_id));
		
		List<ConstraintsVO> list=new ArrayList<ConstraintsVO>();
		for(Constraints con:constraintses){	
			list.add(loadtoVO(con));
			
		}
		return list;
	}
	
	public ConstraintsVO loadtoVO(Constraints constraints){
		//获取列的值
//				List<ConstraintsCols> constraintscols=constraintsColsRepository.query(Cnd.select()
//						.andEquals(M.ConstraintsCols.constraints_id, constraints.getId()));
				List<ConstraintsColsVO> constraintscols=constraintsColsRepository.queryVO(constraints.getId());
				
				ConstraintsVO vo=BeanUtils.copyOrCast(constraints, ConstraintsVO.class);
				if(constraints.getType()==ConstraintsType.Foreign){
					ConstraintsColsVO col=constraintscols.get(0);
					vo.setCol_id(col.getCol_id());
					vo.setRef_col_id(col.getRef_col_id());
					vo.setRef_table_id(col.getRef_table_id());
					
					vo.setCol_name(col.getCol_name());
					vo.setRef_col_name(col.getRef_col_name());
					vo.setRef_table_name(col.getRef_table_name());
				} else {	
					String col_id="";
					String col_name="";
					for(int i=0;i<constraintscols.size();i++){
						//vo.setBen_column(null);
						col_id+=","+constraintscols.get(i).getCol_id();
						col_name+=","+constraintscols.get(i).getCol_name();
					}
					col_id=col_id.substring(1);
					vo.setCol_id(col_id);	
					col_name=col_name.substring(1);
					vo.setCol_name(col_name);
				}
				return vo;
	}
	
	public List<Tablemeta> querySameUserTable(String tablemeta_id){
		return constraintsRepository.querySameUserTable(tablemeta_id);
	}
	//@Override
	public ConstraintsVO load(String id) {
		Constraints constraints=constraintsRepository.get(id);
		
		return loadtoVO(constraints);
	}
	
	public String create(ConstraintsVO vo) {
		
		String ben_column=vo.getCol_id();
		if(!StringUtils.hasText(vo.getCol_id())){
			throw new BusinessException("列必须输入");
		}
		String ben_cloumns[]=ben_column.split(",");
		Constraints entity=BeanUtils.copyOrCast(vo, Constraints.class);
		entity.setCreateTime(new Date());
		entity.setCreater(ShiroUtils.getUserId());
		this.getRepository().create(entity);
		
		if(vo.getType()==ConstraintsType.Foreign){
			if(!StringUtils.hasText(vo.getRef_table_id()) || !StringUtils.hasText(vo.getRef_col_id())){
				throw new BusinessException("引用表和引用列不能为空");
			}
			if(ben_cloumns.length>1){
				throw new BusinessException("本表的列只能选一个");
			}
					 
			ConstraintsCols col=new ConstraintsCols();
			col.setRef_table_id(vo.getRef_table_id());
			col.setRef_col_id(vo.getRef_col_id());
			col.setCol_id(vo.getCol_id());
			
			col.setConstraints_id(entity.getId());
			constraintsColsRepository.create(col);
		} else {

			for(int i=0;i<ben_cloumns.length;i++) {
				ConstraintsCols col=new ConstraintsCols();
				//col.(vo.getTablemeta_id());
				col.setCol_id(ben_cloumns[i]);
				col.setConstraints_id(entity.getId());
				constraintsColsRepository.create(col);
			}
			
		}
		
		return entity.getId();
	}

}
