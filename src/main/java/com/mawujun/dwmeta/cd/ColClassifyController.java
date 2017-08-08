package com.mawujun.dwmeta.cd;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.dwmeta.cd.ColClassify;
import com.mawujun.dwmeta.cd.ColClassifyService;
import com.mawujun.repository.cnd.Cnd;
import com.mawujun.utils.M;
import com.mawujun.utils.page.Pager;
/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/colClassify")
public class ColClassifyController {

	@Resource
	private ColClassifyService colClassifyService;


//	/**
//	 * 这是基于分页的几种写法,的例子，请按自己的需求修改
//	 * @author mawujun email:16064988@163.com qq:16064988
//	 * @param start
//	 * @param limit
//	 * @param userName
//	 * @return
//	 */
//	@RequestMapping("/colClassify/queryPager.do")
//	@ResponseBody
//	public Pager<ColClassify> queryPager(Pager<ColClassify> pager){
//		
//		return colClassifyService.queryPage(pager);
//	}

	@RequestMapping("/colClassify/queryAll.do")
	@ResponseBody
	public List<ColClassify> queryAll(String db_id) {	
		List<ColClassify> colClassifyes=colClassifyService.query(Cnd.select().andEquals(M.ColClassify.db_id, db_id));
		return colClassifyes;
	}
	

	@RequestMapping("/colClassify/load.do")
	@ResponseBody
	public ColClassify load(String id) {
		return colClassifyService.get(id);
	}
	
	@RequestMapping("/colClassify/create.do")
	@ResponseBody
	public ColClassify create(@RequestBody ColClassify colClassify) {
		colClassifyService.create(colClassify);
		return colClassify;
	}
	
	@RequestMapping("/colClassify/update.do")
	@ResponseBody
	public  ColClassify update(@RequestBody ColClassify colClassify) {
		colClassifyService.update(colClassify);
		return colClassify;
	}
	
//	@RequestMapping("/colClassify/deleteById.do")
//	@ResponseBody
//	public String deleteById(String id) {
//		colClassifyService.deleteById(id);
//		return id;
//	}
	
	@RequestMapping("/colClassify/destroy.do")
	@ResponseBody
	public ColClassify destroy(@RequestBody ColClassify colClassify) {
		//colClassifyService.delete(colClassify);
		colClassify.setDeleted(true);
		return colClassify;
	}
	
	
}
