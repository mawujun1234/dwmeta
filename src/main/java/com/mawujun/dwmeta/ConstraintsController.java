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

import com.mawujun.dwmeta.Constraints;
import com.mawujun.dwmeta.ConstraintsService;
/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/constraints")
public class ConstraintsController {

	@Resource
	private ConstraintsService constraintsService;


//	/**
//	 * 这是基于分页的几种写法,的例子，请按自己的需求修改
//	 * @author mawujun email:16064988@163.com qq:16064988
//	 * @param start
//	 * @param limit
//	 * @param userName
//	 * @return
//	 */
//	@RequestMapping("/constraints/queryPager.do")
//	@ResponseBody
//	public Pager<Constraints> queryPager(Pager<Constraints> pager){
//		
//		return constraintsService.queryPage(pager);
//	}

	@RequestMapping("/constraints/queryAll.do")
	@ResponseBody
	public List<ConstraintsVO> queryAll(String tablemeta_id) {	
//		List<Constraints> constraintses=constraintsService.query(Cnd.select()
//				.andEquals(M.Constraints.tablemeta_id, tablemeta_id));
//		return constraintses;
		return constraintsService.queryAll(tablemeta_id);
	}
	/**
	 * 获取同个用户下面的表
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 * @param tablemeta_id
	 * @return
	 */
	@RequestMapping("/constraints/querySameUserTable.do")
	@ResponseBody
	public List<Tablemeta> querySameUserTable(String tablemeta_id) {	
		
		return constraintsService.querySameUserTable(tablemeta_id);
	}
	

	@RequestMapping("/constraints/load.do")
	@ResponseBody
	public Constraints load(String id) {
		return constraintsService.get(id);
	}
	
	@RequestMapping("/constraints/create.do")
	@ResponseBody
	public Constraints create(@RequestBody ConstraintsVO constraints) {
		constraintsService.create(constraints);
		return constraints;
	}
	
	@RequestMapping("/constraints/update.do")
	@ResponseBody
	public  Constraints update(@RequestBody Constraints constraints) {
		constraintsService.update(constraints);
		return constraints;
	}
	
	@RequestMapping("/constraints/deleteById.do")
	@ResponseBody
	public String deleteById(String id) {
		constraintsService.deleteById(id);
		return id;
	}
	
	@RequestMapping("/constraints/destroy.do")
	@ResponseBody
	public Constraints destroy(@RequestBody Constraints constraints) {
		constraintsService.delete(constraints);
		return constraints;
	}
	
	
}
