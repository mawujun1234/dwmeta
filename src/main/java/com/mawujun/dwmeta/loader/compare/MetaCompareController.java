package com.mawujun.dwmeta.loader.compare;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MetaCompareController {
	@Autowired
	private MetaCompareService metaCompareService;

//	@RequestMapping("/metacompare/getTableNames.do")
//	@ResponseBody
//	public Set<String> getTableNames(String dwlayer_id) {
//		return metaCompareService.getTableNames(dwlayer_id);
//	}
	
	private Map<String,Map<String,DiffMsg>> db_diffMsgs=new HashMap<String,Map<String,DiffMsg>>();
	
	@RequestMapping("/metacompare/checkChayiByDB.do")
	@ResponseBody
	public String checkChayiByDB(String db_id) throws IllegalAccessException, InvocationTargetException {
		Map<String,DiffMsg> diffMsgs= metaCompareService.checkChayiByDB(db_id);
		db_diffMsgs.put(db_id, diffMsgs);
		 
		return "{success:true}";
	}
	
//	@RequestMapping("/metacompare/checkChayi.do")
//	@ResponseBody
//	public Collection<DiffMsg> checkChayi(String dwlayer_id) throws IllegalAccessException, InvocationTargetException {
//		
//		Map<String,DiffMsg> diffMsgs= metaCompareService.checkChayi(dwlayer_id);
//		 
//		return diffMsgs.values();
//	}
	
	@RequestMapping("/metacompare/queryDiffMsg.do")
	@ResponseBody
	public List<DiffMsg> queryDiffMsg(String db_id) throws IllegalAccessException, InvocationTargetException {
		Map<String,DiffMsg> diffMsgs=db_diffMsgs.get(db_id);
		if(diffMsgs==null) {
			return new ArrayList<DiffMsg>();
		}
		List<DiffMsg> list=new ArrayList<DiffMsg>();
		for(Entry<String,DiffMsg> entry:diffMsgs.entrySet()) {
			DiffMsg aaa=new DiffMsg();
			aaa.setDbname(entry.getValue().getDbname());
			aaa.setDiffMsgType(entry.getValue().getDiffMsgType());
			aaa.setLayername(entry.getValue().getLayername());
			aaa.setTablename(entry.getValue().getTablename());
			list.add(aaa);
		}
		return list;
	}
	
	@RequestMapping("/metacompare/queryDiffTable.do")
	@ResponseBody
	public List<DiffMsg> queryDiffTable(String db_id,String table_name)  {
		
		DiffMsg diffMsg=db_diffMsgs.get(db_id).get(table_name);
		DiffTable diffTable=diffMsg.getDiffTable();
		Map<String,Object> table=new HashMap<String,Object>();
		//table.put("name", diffTable.getName());
		table.put("tablename", diffTable.getName());
		List<DiffColumn> columns=new ArrayList<DiffColumn>();
		for(Entry<String,DiffColumn> entry:diffTable.getColumns().entrySet()) {
			columns.add(entry.getValue());
		}
		table.put("columns", columns);
		
		
		if(diffTable.getPrimaryKeyes()!=null) {
			for(DiffPrimaryKey pk:diffTable.getPrimaryKeyes()) {
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("diffMsgType_name", pk.getDiffMsgType_name());
				map.put("diffMsgType", pk.getDiffMsgType());
				map.put("type", "主键");
				map.put("name", pk.getName());
				map.put("col_name", StringUtils.toString(pk.getColumns().toArray()));
				//map.put("ref_table_name", );
				//map.put("ref_col_name", );
			}
		}
		if(diffTable.getForeignkeys()!=null) {
			for(Entry<String,DiffForeignKey> entry:diffTable.getForeignkeys().entrySet()) {
				DiffForeignKey fk=entry.getValue();
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("diffMsgType_name", fk.getDiffMsgType_name());
				map.put("diffMsgType", fk.getDiffMsgType());
				map.put("type", "外键");
				map.put("name", fk.getName());
				map.put("col_name", );
				map.put("ref_table_name", );
				map.put("ref_col_name", );
			}
		}
		
		
	}
	
	
}
