package com.mawujun.dwmeta;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.repository.cnd.Cnd;
import com.mawujun.utils.M;
/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/columnmeta")
public class ColumnmetaController {

	@Resource
	private ColumnmetaService columnmetaService;


//	/**
//	 * 这是基于分页的几种写法,的例子，请按自己的需求修改
//	 * @author mawujun email:16064988@163.com qq:16064988
//	 * @param start
//	 * @param limit
//	 * @param userName
//	 * @return
//	 */
//	@RequestMapping("/columnmeta/queryPager.do")
//	@ResponseBody
//	public Pager<Columnmeta> queryPager(Pager<Columnmeta> pager){
//		
//		return columnmetaService.queryPage(pager);
//	}

	@RequestMapping("/columnmeta/queryAll.do")
	@ResponseBody
	public List<Columnmeta> queryAll(String tablemeta_id) {	
		List<Columnmeta> columnmetaes=columnmetaService.query(Cnd.select()
				.andEquals(M.Columnmeta.tablemeta_id, tablemeta_id)
				.asc(M.Columnmeta.sorted));
		return columnmetaes;
	}
	@RequestMapping("/columnmeta/query4combo.do")
	@ResponseBody
	public List<Columnmeta> query4combo(String tablemeta_id) {	
		List<Columnmeta> columnmetaes=columnmetaService.query(Cnd.select()
				.andEquals(M.Columnmeta.tablemeta_id, tablemeta_id)
				.asc(M.Columnmeta.sorted));
		return columnmetaes;
	}
	

	@RequestMapping("/columnmeta/load.do")
	@ResponseBody
	public Columnmeta load(String id) {
		return columnmetaService.get(id);
	}
	
	@RequestMapping("/columnmeta/create.do")
	@ResponseBody
	public Columnmeta create(@RequestBody Columnmeta columnmeta) {
		columnmetaService.create(columnmeta);
		return columnmeta;
	}
	
	@RequestMapping("/columnmeta/update.do")
	@ResponseBody
	public  Columnmeta update(@RequestBody Columnmeta columnmeta) {
		columnmetaService.update(columnmeta);
		return columnmeta;
	}
	
	@RequestMapping("/columnmeta/deleteById.do")
	@ResponseBody
	public String deleteById(String id) {
		columnmetaService.deleteById(id);
		return id;
	}
	
	@RequestMapping("/columnmeta/destroy.do")
	@ResponseBody
	public Columnmeta destroy(@RequestBody Columnmeta columnmeta) {
		columnmetaService.delete(columnmeta);
		return columnmeta;
	}
	
	
}
