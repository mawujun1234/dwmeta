package com.mawujun.dwmeta;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.permission.Menu;
import com.mawujun.permission.MenuService;
import com.mawujun.permission.MenuType;
/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/dB")
public class DBController {

	@Resource
	private DBService dBService;
	@Resource
	private MenuService menuService;


//	/**
//	 * 这是基于分页的几种写法,的例子，请按自己的需求修改
//	 * @author mawujun email:16064988@163.com qq:16064988
//	 * @param start
//	 * @param limit
//	 * @param userName
//	 * @return
//	 */
//	@RequestMapping("/dB/queryPager.do")
//	@ResponseBody
//	public Pager<DB> queryPager(Pager<DB> pager){
//		
//		return dBService.queryPage(pager);
//	}

	@RequestMapping("/dB/queryAll.do")
	@ResponseBody
	public List<DB> queryAll() {	
		List<DB> dBes=dBService.queryAll();
		return dBes;
	}
	

	@RequestMapping("/dB/load.do")
	@ResponseBody
	public DB load(String id) {
		return dBService.get(id);
	}
	
	@RequestMapping("/dB/create.do")
	@ResponseBody
	public DB create(@RequestBody DB dB) {
		dBService.create(dB);
		//创建菜单
		Menu menu=new Menu();
		menu.setLeaf(true);
		menu.setMenuType(MenuType.menu);
		menu.setName(dB.getName());
		menu.setUrl("/dwmeta/DwmetaApp.jsp?db_id="+dB.getId());
		menuService.create(menu);
		return dB;
	}
	
	@RequestMapping("/dB/update.do")
	@ResponseBody
	public  DB update(@RequestBody DB dB) {
		dBService.update(dB);
		return dB;
	}
	
	@RequestMapping("/dB/deleteById.do")
	@ResponseBody
	public String deleteById(String id) {
		dBService.deleteById(id);
		return id;
	}
	
	@RequestMapping("/dB/destroy.do")
	@ResponseBody
	public DB destroy(@RequestBody DB dB) {
		dBService.delete(dB);
		return dB;
	}
	
	
}
