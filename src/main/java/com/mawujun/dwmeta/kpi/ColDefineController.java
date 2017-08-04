package com.mawujun.dwmeta.kpi;
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

import com.mawujun.dwmeta.kpi.ColDefine;
import com.mawujun.dwmeta.kpi.ColDefineService;
/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/colDefine")
public class ColDefineController {

	@Resource
	private ColDefineService colDefineService;


//	/**
//	 * 这是基于分页的几种写法,的例子，请按自己的需求修改
//	 * @author mawujun email:16064988@163.com qq:16064988
//	 * @param start
//	 * @param limit
//	 * @param userName
//	 * @return
//	 */
//	@RequestMapping("/colDefine/queryPager.do")
//	@ResponseBody
//	public Pager<ColDefine> queryPager(Pager<ColDefine> pager){
//		
//		return colDefineService.queryPage(pager);
//	}

	@RequestMapping("/colDefine/queryAll.do")
	@ResponseBody
	public List<ColDefine> queryAll(String colclassify_id,String colname,String name, Boolean status) {	
		List<ColDefine> colDefinees=colDefineService.query(Cnd.select()
				.andEquals(M.ColDefine.colclassify_id, colclassify_id)
				.andLikeIf(M.ColDefine.colname, colname,true)
				.andLikeIf(M.ColDefine.name, name,true)
				.andEqualsIf(M.ColDefine.status, status));
		return colDefinees;
	}
	

	@RequestMapping("/colDefine/load.do")
	@ResponseBody
	public ColDefine load(String id) {
		return colDefineService.get(id);
	}
	
	@RequestMapping("/colDefine/create.do")
	@ResponseBody
	public ColDefine create(@RequestBody ColDefine colDefine) {
		colDefineService.create(colDefine);
		return colDefine;
	}
	
	@RequestMapping("/colDefine/update.do")
	@ResponseBody
	public  ColDefine update(@RequestBody ColDefine colDefine) {
		colDefineService.update(colDefine);
		return colDefine;
	}
	
//	@RequestMapping("/colDefine/deleteById.do")
//	@ResponseBody
//	public String deleteById(String id) {
//		colDefineService.deleteById(id);
//		return id;
//	}
	
	@RequestMapping("/colDefine/destroy.do")
	@ResponseBody
	public ColDefine destroy(@RequestBody ColDefine colDefine) {
		//colDefineService.delete(colDefine);
		colDefine.setStatus(false);
		colDefineService.update(colDefine);
		return colDefine;
	}
	
	
}
