package com.mawujun.dwmeta.loader.compare;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MetaCompareController {
	@Autowired
	private MetaCompareService metaCompareService;

	@RequestMapping("/metacompare/getTableNames.do")
	@ResponseBody
	public Set<String> getTableNames(String dwlayer_id) {
		return metaCompareService.getTableNames(dwlayer_id);
	}
	
	@RequestMapping("/metacompare/checkChayi.do")
	@ResponseBody
	public Collection<DiffMsg> checkChayi(String dwlayer_id) throws IllegalAccessException, InvocationTargetException {
		 Map<String,DiffMsg> result= metaCompareService.checkChayi(dwlayer_id);
		 return result.values();
	}
}
