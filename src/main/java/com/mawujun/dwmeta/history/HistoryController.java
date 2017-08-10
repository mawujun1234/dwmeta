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

import com.mawujun.dwmeta.history.History;
import com.mawujun.dwmeta.history.HistoryService;
/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/history")
public class HistoryController {

	@Resource
	private HistoryService historyService;


	/**
	 * 这是基于分页的几种写法,的例子，请按自己的需求修改
	 * @author mawujun email:16064988@163.com qq:16064988
	 * @param start
	 * @param limit
	 * @param userName
	 * @return
	 */
	@RequestMapping("/history/queryPager.do")
	@ResponseBody
	public Pager<History> queryPager(Pager<History> pager){
		
		return historyService.queryPage(pager);
	}

	@RequestMapping("/history/queryAll.do")
	@ResponseBody
	public List<History> queryAll() {	
		List<History> historyes=historyService.queryAll();
		return historyes;
	}
	

	@RequestMapping("/history/load.do")
	@ResponseBody
	public History load(String id) {
		return historyService.get(id);
	}
	
	@RequestMapping("/history/create.do")
	@ResponseBody
	public History create(@RequestBody History history) {
		historyService.create(history);
		return history;
	}
	
	@RequestMapping("/history/update.do")
	@ResponseBody
	public  History update(@RequestBody History history) {
		historyService.update(history);
		return history;
	}
	
	@RequestMapping("/history/deleteById.do")
	@ResponseBody
	public String deleteById(String id) {
		historyService.deleteById(id);
		return id;
	}
	
	@RequestMapping("/history/destroy.do")
	@ResponseBody
	public History destroy(@RequestBody History history) {
		historyService.delete(history);
		return history;
	}
	
	
}
