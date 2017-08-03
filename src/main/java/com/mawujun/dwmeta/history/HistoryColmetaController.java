package com.mawujun.dwmeta.history;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mawujun.repository.cnd.Cnd;
import com.mawujun.utils.M;
import com.mawujun.utils.page.Pager;

import com.mawujun.dwmeta.history.HistoryColmeta;
import com.mawujun.dwmeta.history.HistoryColmetaService;
/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/historyColmeta")
public class HistoryColmetaController {

	@Resource
	private HistoryColmetaService historyColmetaService;


	/**
	 * 这是基于分页的几种写法,的例子，请按自己的需求修改
	 * @author mawujun email:16064988@163.com qq:16064988
	 * @param start
	 * @param limit
	 * @param userName
	 * @return
	 */
	@RequestMapping("/historyColmeta/queryPager.do")
	@ResponseBody
	public Pager<HistoryColmeta> queryPager(Pager<HistoryColmeta> pager){
		
		return historyColmetaService.queryPage(pager);
	}

	@RequestMapping("/historyColmeta/queryAll.do")
	@ResponseBody
	public List<HistoryColmeta> queryAll() {	
		List<HistoryColmeta> historyColmetaes=historyColmetaService.queryAll();
		return historyColmetaes;
	}
	

	@RequestMapping("/historyColmeta/load.do")
	@ResponseBody
	public HistoryColmeta load(String id) {
		return historyColmetaService.get(id);
	}
	
	@RequestMapping("/historyColmeta/create.do")
	@ResponseBody
	public HistoryColmeta create(@RequestBody HistoryColmeta historyColmeta) {
		historyColmetaService.create(historyColmeta);
		return historyColmeta;
	}
	
	@RequestMapping("/historyColmeta/update.do")
	@ResponseBody
	public  HistoryColmeta update(@RequestBody HistoryColmeta historyColmeta) {
		historyColmetaService.update(historyColmeta);
		return historyColmeta;
	}
	
	@RequestMapping("/historyColmeta/deleteById.do")
	@ResponseBody
	public String deleteById(String id) {
		historyColmetaService.deleteById(id);
		return id;
	}
	
	@RequestMapping("/historyColmeta/destroy.do")
	@ResponseBody
	public HistoryColmeta destroy(@RequestBody HistoryColmeta historyColmeta) {
		historyColmetaService.delete(historyColmeta);
		return historyColmeta;
	}
	
	
}
