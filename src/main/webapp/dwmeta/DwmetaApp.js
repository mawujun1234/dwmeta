Ext.require('Ems.dwmeta.DwmetaTree');
Ext.require('Ems.dwmeta.TablePanel');
Ext.require('Ext.ux.TabCloseMenu');
Ext.require('Ems.dwmeta.ConstraintsGrid');
Ext.require('Ems.dwmeta.ConstraintsForm');
Ext.require('Ems.dwmeta.HistoryGrid');
Ext.onReady(function(){
	if(!window.db_id){
		alert("请先设置数据库的id!");
		return;
	}
	//获取数据库类型和该数据库类型下的字段类型
	Ext.Ajax.request({
    	url:Ext.ContextPath+'/dB/getDBVO.do',
    	method:'POST',
    	params:{
    		id:window.db_id
    	},
    	success:function(response){
    		var obj=Ext.decode(response.responseText);
    		window.fieldtypes=obj.fieldtypes;
    		//console.log(window.fieldtypes);
    	}
    });
	
	
	//window.history_id=(new Date()).getTime()
    window.hisobj={
    	tablemeta:{},
		createcoles:[],
		updatecoles:[],
		deletecoles:[],
		createConstraints:[],
		deleteConstraints:[]
	}
	var dwmetaTree=Ext.create('Ems.dwmeta.DwmetaTree',{
		region:'west'
		,width:220
		,split:true
		//title:'XXX表格'
	});
	var tableTabPanel=Ext.create('Ems.dwmeta.TableTabPanel',{
		region:'center'
	});
	window.tableTabPanel=tableTabPanel;
	
	var viewPort=Ext.create('Ext.container.Viewport',{
		layout:'border',
		items:[dwmetaTree,tableTabPanel]
	});



});