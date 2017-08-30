package com.mawujun.dwmeta;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.controller.spring.mvc.ResultModel;
import com.mawujun.repository.cnd.Cnd;
import com.mawujun.utils.M;
/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/dWLayer")
public class DWLayerController {

	@Resource
	private DWLayerService dWLayerService;


//	/**
//	 * 这是基于分页的几种写法,的例子，请按自己的需求修改
//	 * @author mawujun email:16064988@163.com qq:16064988
//	 * @param start
//	 * @param limit
//	 * @param userName
//	 * @return
//	 */
//	@RequestMapping("/dWLayer/queryPager.do")
//	@ResponseBody
//	public Pager<DWLayer> queryPager(Pager<DWLayer> pager){
//		
//		return dWLayerService.queryPage(pager);
//	}

	@RequestMapping("/dWLayer/queryAll.do")
	@ResponseBody
	public List<DWLayer> queryAll(String db_id) {	
		List<DWLayer> dWLayeres=dWLayerService.query(Cnd.select().andEquals(M.DWLayer.db_id, db_id));
		return dWLayeres;
	}
	

	@RequestMapping("/dWLayer/testConn.do")
	@ResponseBody
	public ResultModel testConn(String jdbc_driver,String jdbc_url,String jdbc_username,String jdbc_password) {
		 dWLayerService.testConn(jdbc_driver, jdbc_url, jdbc_username, jdbc_password);
		 return ResultModel.getInstance();
	}
	
	@RequestMapping("/dWLayer/load.do")
	@ResponseBody
	public DWLayer load(String id) {
		return dWLayerService.get(id);
	}
	
	@RequestMapping("/dWLayer/create.do")
	@ResponseBody
	public DWLayer create(@RequestBody DWLayer dWLayer) {
		dWLayerService.create(dWLayer);
		return dWLayer;
	}
	
	@RequestMapping("/dWLayer/update.do")
	@ResponseBody
	public  DWLayer update(@RequestBody DWLayer dWLayer) {
		dWLayerService.update(dWLayer);
		return dWLayer;
	}
	
	@RequestMapping("/dWLayer/deleteById.do")
	@ResponseBody
	public String deleteById(String id) {
		dWLayerService.deleteById(id);
		return id;
	}
	
	@RequestMapping("/dWLayer/destroy.do")
	@ResponseBody
	public DWLayer destroy(@RequestBody DWLayer dWLayer) {
		dWLayerService.delete(dWLayer);
		return dWLayer;
	}
	
	
}
