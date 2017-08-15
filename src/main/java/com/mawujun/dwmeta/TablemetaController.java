package com.mawujun.dwmeta;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.dwmeta.history.HistoryVO;
import com.mawujun.repository.cnd.Cnd;
import com.mawujun.utils.M;
import com.mawujun.utils.page.Pager;
/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/tablemeta")
public class TablemetaController {

	@Resource
	private TablemetaService tablemetaService;


//	/**
//	 * 这是基于分页的几种写法,的例子，请按自己的需求修改
//	 * @author mawujun email:16064988@163.com qq:16064988
//	 * @param start
//	 * @param limit
//	 * @param userName
//	 * @return
//	 */
//	@RequestMapping("/tablemeta/queryPager.do")
//	@ResponseBody
//	public Pager<Tablemeta> queryPager(Pager<Tablemeta> pager){
//		
//		return tablemetaService.queryPage(pager);
//	}

//	@RequestMapping("/tablemeta/queryAll.do")
//	@ResponseBody
//	public List<Tablemeta> queryAll() {	
//		List<Tablemeta> tablemetaes=tablemetaService.queryAll();
//		return tablemetaes;
//	}
	
	@RequestMapping("/tablemeta/queryHistory.do")
	@ResponseBody
	public List<HistoryVO> queryHistory(String tablemeta_id) {	
		List<HistoryVO> tablemetaes=tablemetaService.queryHistory(tablemeta_id);
		return tablemetaes;
	}

	@RequestMapping("/tablemeta/load.do")
	@ResponseBody
	public Tablemeta load(String id) {
		return tablemetaService.get(id);
	}
	
	@RequestMapping("/tablemeta/create.do")
	@ResponseBody
	public Tablemeta create(@RequestBody Tablemeta tablemeta) {
		
		tablemetaService.create(tablemeta);
		return tablemeta;
		//return ResultModel.getInstance().setRoot(tablemeta);
	}
	
	@RequestMapping("/tablemeta/createorupdate.do")
	@ResponseBody
	public String createAll(@RequestBody TablemetaDTO tablemetaDTO ) {
		
		tablemetaService.createorupdate(tablemetaDTO);
		return "{success:true,tablemeta_id:'"+tablemetaDTO.getTablemeta().getId()+"'}";
		//return ResultModel.getInstance().setRoot(tablemeta);
	}
	
	@RequestMapping("/tablemeta/update.do")
	@ResponseBody
	public  Tablemeta update(@RequestBody Tablemeta tablemeta) {
		tablemetaService.update(tablemeta);
		return tablemeta;
	}
	
	@RequestMapping("/tablemeta/deleteById.do")
	@ResponseBody
	public String deleteById(String id) {
		//tablemetaService.deleteById(id);
		tablemetaService.deleteById(id);
		return id;
	}
	
	@RequestMapping("/tablemeta/replyById.do")
	@ResponseBody
	public String replyById(String id) {
		//tablemetaService.deleteById(id);
		tablemetaService.update(Cnd.update().set(M.Tablemeta.status, true).andEquals(M.Tablemeta.id, id));
		return id;
	}
	
	@RequestMapping("/tablemeta/deleteFocus.do")
	@ResponseBody
	public String deleteFocus(String id) {
		tablemetaService.deleteFocus(id);
		return id;
	}
	
	
}
