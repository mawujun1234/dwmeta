package com.mawujun.dwmeta;
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

import com.mawujun.dwmeta.Classify;
import com.mawujun.dwmeta.ClassifyService;
/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/classify")
public class ClassifyController {

	@Resource
	private ClassifyService classifyService;


//	/**
//	 * 这是基于分页的几种写法,的例子，请按自己的需求修改
//	 * @author mawujun email:16064988@163.com qq:16064988
//	 * @param start
//	 * @param limit
//	 * @param userName
//	 * @return
//	 */
//	@RequestMapping("/classify/queryPager.do")
//	@ResponseBody
//	public Pager<Classify> queryPager(Pager<Classify> pager){
//		
//		return classifyService.queryPage(pager);
//	}
//
//	@RequestMapping("/classify/queryAll.do")
//	@ResponseBody
//	public List<Classify> queryAll() {	
//		List<Classify> classifyes=classifyService.queryAll();
//		return classifyes;
//	}
	
	@RequestMapping("/classify/query4tree.do")
	@ResponseBody
	public List<Classify> query4tree() {	
		List<Classify> classifyes=classifyService.query4tree();
		return classifyes;
	}
	

	@RequestMapping("/classify/load.do")
	@ResponseBody
	public Classify load(String id) {
		return classifyService.get(id);
	}
	
	@RequestMapping("/classify/create.do")
	@ResponseBody
	public Classify create(@RequestBody Classify classify) {
		classifyService.create(classify);
		return classify;
	}
	
	@RequestMapping("/classify/update.do")
	@ResponseBody
	public  Classify update(@RequestBody Classify classify) {
		classifyService.update(classify);
		return classify;
	}
	
	@RequestMapping("/classify/deleteById.do")
	@ResponseBody
	public String deleteById(String id) {
		classifyService.deleteById(id);
		return id;
	}
	
	@RequestMapping("/classify/destroy.do")
	@ResponseBody
	public Classify destroy(@RequestBody Classify classify) {
		classifyService.delete(classify);
		return classify;
	}
	
	
}
