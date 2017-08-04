Ext.require("Ems.kpi.ColDefine");
Ext.require("Ems.kpi.ColDefineGrid");
Ext.require("Ems.kpi.ColDefineForm");
Ext.require('Ems.dwmeta.DBGridQuery');

Ext.onReady(function(){
//	var grid=Ext.create('Ems.kpi.ColDefineGrid',{
//		region:'center',
//		title:'XXX表格'
//	});
	
	var dBGridQuery=Ext.create('Ems.dwmeta.DBGridQuery',{
		title:'数据库',
		region:'west',
		split:true,
		width:280
	});
	var tabpanel=Ext.create('Ext.tab.Panel',{
		region:'center',
		items:[]
		,listeners:{
			render:function(){
				tabpanel.mask();
			}
		}
	});

	dBGridQuery.on("itemclick",function(view, record, item, index, e, eOpts){

		Ext.Ajax.request({
	    	url:Ext.ContextPath+'/dB/getDBVO.do',
	    	method:'POST',
	    	params:{
	    		id:record.get("id")
	    	},
	    	success:function(response){
	    		var obj=Ext.decode(response.responseText);
	    		window.fieldtypes=obj.fieldtypes;
	    		//window.jdbc=window.jdbcs[record.get("dbtype")];
	    		//console.log(window.fieldtypes);
	    	}
	    });
		 
		//获取字段分类
		 Ext.Ajax.request({
	    	url:Ext.ContextPath+'/colClassify/queryAll.do',
	    	params:{
	    		db_id:record.get("id")
	    	},
	    	method:'POST',
	    	success:function(response){
	    		var objes=Ext.decode(response.responseText);
	    		createTab(objes);
	    		tabpanel.unmask();
	    	}
	    });

	});
	function createTab(arry){
		tabpanel.removeAll ( true, true )
		for(var i=0;i<arry.length;i++){
	    	var aa=Ext.create('Ems.kpi.ColDefineGrid',{
	    		title:arry[i].name
	    	});
	    	aa.getStore().getProxy().extraParams=Ext.apply(aa.getStore().getProxy().extraParams,{
				"colclassify_id":arry[i].id
			});
			tabpanel.add(aa);
	    }
	    tabpanel.setActiveItem(0);
	}
	var viewPort=Ext.create('Ext.container.Viewport',{
		layout:'border',
		items:[dBGridQuery,tabpanel]
	});



});