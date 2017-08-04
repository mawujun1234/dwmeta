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

import com.mawujun.dwmeta.history.HisTabmeta;
import com.mawujun.dwmeta.history.HisTabmetaService;
/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/historyTabmeta")
public class HisTabmetaController {

	@Resource
	private HisTabmetaService historyTabmetaService;


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
	public Pager<HisTabmeta> queryPager(Pager<HisTabmeta> pager){
		
		return historyTabmetaService.queryPage(pager);
	}

	@RequestMapping("/historyTabmeta/queryAll.do")
	@ResponseBody
	public List<HisTabmeta> queryAll() {	
		List<HisTabmeta> historyTabmetaes=historyTabmetaService.queryAll();
		return historyTabmetaes;
	}
	

	@RequestMapping("/historyTabmeta/load.do")
	@ResponseBody
	public HisTabmeta load(String id) {
		return historyTabmetaService.get(id);
	}
	
	@RequestMapping("/historyTabmeta/create.do")
	@ResponseBody
	public HisTabmeta create(@RequestBody HisTabmeta historyTabmeta) {
		historyTabmetaService.create(historyTabmeta);
		return historyTabmeta;
	}
	
	@RequestMapping("/historyTabmeta/update.do")
	@ResponseBody
	public  HisTabmeta update(@RequestBody HisTabmeta historyTabmeta) {
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
	public HisTabmeta destroy(@RequestBody HisTabmeta historyTabmeta) {
		historyTabmetaService.delete(historyTabmeta);
		return historyTabmeta;
	}
	
	
}
