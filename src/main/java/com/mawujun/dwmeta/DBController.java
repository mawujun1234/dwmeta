package com.mawujun.dwmeta;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.dwmeta.kpi.ColClassify;
import com.mawujun.dwmeta.kpi.ColClassifyService;
import com.mawujun.permission.Menu;
import com.mawujun.permission.MenuService;
import com.mawujun.permission.MenuType;
import com.mawujun.utils.bean.BeanUtils;
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
	
	@RequestMapping("/dB/queryJdbc.do")
	@ResponseBody
	public Map<String,Object> queryJdbc() {	
		Map<String,Object> result=new HashMap<String,Object>();
		for(Dbtype type:Dbtype.values()){
			Map<String,String> aaa=new HashMap<String,String>();
			aaa.put("jdbc_driver", type.getJdbc_driver());
			aaa.put("jdbc_url", type.getJdbc_url());
			result.put(type.getName(), aaa);
		}
		return result;
	}
	

	@RequestMapping("/dB/load.do")
	@ResponseBody
	public DB load(String id) {
		return dBService.get(id);
	}
	
	@RequestMapping("/dB/getDBVO.do")
	@ResponseBody
	public DBVO getDBVO(String id) {
		DB db= dBService.get(id);
		DBVO vo=BeanUtils.copyOrCast(db, DBVO.class);

		List<String[]> aaa=new ArrayList<String[]>();
		String[] bbb=db.getDbtype().getFieldtypes();
		for(String b:bbb){
			aaa.add(new String[]{b});
		}
		vo.setFieldtypes(aaa);
		return vo;
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
		
		//创建默认的分类
		ColClassify col=new ColClassify();
		col.setDb_id(dB.getId());
		col.setName("默认分类");
		colClassifyService.create(col);
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
