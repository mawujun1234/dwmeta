package com.mawujun.dwmeta.kpi;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.controller.spring.mvc.MapParams;
/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/kpi")
public class KpiController {

	@Resource
	private KpiService kpiService;


//	/**
//	 * 这是基于分页的几种写法,的例子，请按自己的需求修改
//	 * @author mawujun email:16064988@163.com qq:16064988
//	 * @param start
//	 * @param limit
//	 * @param userName
//	 * @return
//	 */
//	@RequestMapping("/kpi/queryPager.do")
//	@ResponseBody
//	public Pager<Kpi> queryPager(Pager<Kpi> pager){
//		
//		return kpiService.queryPage(pager);
//	}

	@RequestMapping("/kpi/queryAll.do")
	@ResponseBody
	public List<Kpi> queryAll(MapParams params) {	
		List<Kpi> kpies=kpiService.queryAll(params);
		return kpies;
	}
	

	@RequestMapping("/kpi/load.do")
	@ResponseBody
	public Kpi load(String id) {
		return kpiService.get(id);
	}
	
	@RequestMapping("/kpi/create.do")
	@ResponseBody
	public Kpi create(@RequestBody Kpi kpi) {
		kpiService.create(kpi);
		return kpi;
	}
	
	@RequestMapping("/kpi/update.do")
	@ResponseBody
	public  Kpi update(@RequestBody Kpi kpi) {
		kpiService.update(kpi);
		return kpi;
	}
	
	@RequestMapping("/kpi/deleteById.do")
	@ResponseBody
	public String deleteById(String id) {
		kpiService.deleteById(id);
		return id;
	}
	
	@RequestMapping("/kpi/destroy.do")
	@ResponseBody
	public Kpi destroy(@RequestBody Kpi kpi) {
		kpiService.delete(kpi);
		return kpi;
	}
	
	
}
