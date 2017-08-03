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

import com.mawujun.dwmeta.history.HistoryTabmeta;
import com.mawujun.dwmeta.history.HistoryTabmetaService;
/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/historyTabmeta")
public class HistoryTabmetaController {

	@Resource
	private HistoryTabmetaService historyTabmetaService;


	/**
	 * 这是基于分页的几种写法,的例子，请按自己的需求修改
	 * @author mawujun email:16064988@163.com qq:16064988
	 * @param start
	 * @param limit
	 * @param userName
	 * @return
	 */
	@RequestMapping("/historyTabmeta/queryPager.do")
	@ResponseBody
	public Pager<HistoryTabmeta> queryPager(Pager<HistoryTabmeta> pager){
		
		return historyTabmetaService.queryPage(pager);
	}

	@RequestMapping("/historyTabmeta/queryAll.do")
	@ResponseBody
	public List<HistoryTabmeta> queryAll() {	
		List<HistoryTabmeta> historyTabmetaes=historyTabmetaService.queryAll();
		return historyTabmetaes;
	}
	

	@RequestMapping("/historyTabmeta/load.do")
	@ResponseBody
	public HistoryTabmeta load(String id) {
		return historyTabmetaService.get(id);
	}
	
	@RequestMapping("/historyTabmeta/create.do")
	@ResponseBody
	public HistoryTabmeta create(@RequestBody HistoryTabmeta historyTabmeta) {
		historyTabmetaService.create(historyTabmeta);
		return historyTabmeta;
	}
	
	@RequestMapping("/historyTabmeta/update.do")
	@ResponseBody
	public  HistoryTabmeta update(@RequestBody HistoryTabmeta historyTabmeta) {
		historyTabmetaService.update(historyTabmeta);
		return historyTabmeta;
	}
	
	@RequestMapping("/historyTabmeta/deleteById.do")
	@ResponseBody
	public String deleteById(String id) {
		historyTabmetaService.deleteById(id);
		return id;
	}
	
	@RequestMapping("/historyTabmeta/destroy.do")
	@ResponseBody
	public HistoryTabmeta destroy(@RequestBody HistoryTabmeta historyTabmeta) {
		historyTabmetaService.delete(historyTabmeta);
		return historyTabmeta;
	}
	
	
}
