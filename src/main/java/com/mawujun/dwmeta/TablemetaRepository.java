package com.mawujun.dwmeta;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mawujun.dwmeta.history.HistoryVO;
import com.mawujun.repository.IRepository;
/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface TablemetaRepository extends IRepository<Tablemeta, String>{
	public List<ClassifyNode> query4tree(@Param("classify_id")String classify_id,@Param("dwlayer_id")String dwlayer_id,@Param("show_deleted")Boolean show_deleted);
	public int exists_tablename(@Param("db_id")String db_id,@Param("tablename")String tablename);
	
	public List<HistoryVO> queryHistory(@Param("tablemeta_id")String tablemeta_id);
}
