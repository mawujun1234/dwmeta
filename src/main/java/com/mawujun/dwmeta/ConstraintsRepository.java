package com.mawujun.dwmeta;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mawujun.repository.IRepository;
/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface ConstraintsRepository extends IRepository<Constraints, String>{

	public List<Tablemeta> querySameUserTable(@Param("dwlayer_id")String dwlayer_id);
	
	public List<ConstraintsVO1> queryVO1(@Param("tablemeta_id")String tablemeta_id);
}
