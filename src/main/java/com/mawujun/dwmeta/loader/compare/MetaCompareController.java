package com.mawujun.dwmeta.loader.compare;

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
}
