Ext.require("Ems.dwmeta.DB");
Ext.require("Ems.dwmeta.DBGrid");
Ext.require("Ems.dwmeta.DBForm");
Ext.require("Ems.dwmeta.DWLayer");
Ext.require("Ems.dwmeta.DWLayerGrid");
Ext.require("Ems.dwmeta.DWLayerForm");
Ext.require('Ems.kpi.ColClassifyGrid');
Ext.require('Ems.kpi.ColClassifyForm');
Ext.onReady(function(){
	//获取数据库的默认链接地址
    Ext.Ajax.request({
    	url:Ext.ContextPath+'/dB/queryJdbc.do',
    	method:'POST',
    	success:function(response){
    		var obj=Ext.decode(response.responseText);
    		window.jdbcs=obj;
    	}
    });
	var dBGrid=Ext.create('Ems.dwmeta.DBGrid',{
		title:'数据库',
		region:'west',
		split:true,
		width:280
	});
	var dWLayerGrid=Ext.create('Ems.dwmeta.DWLayerGrid',{
		//region:'center'
		title:'连接用户管理'
		
	});
	var colClassifyGrid=Ext.create('Ems.kpi.ColClassifyGrid',{
		title:'常用列分类管理'
	});
	dBGrid.on("itemclick",function(view, record, item, index, e, eOpts){
		dWLayerGrid.getStore().getProxy().extraParams=Ext.apply(dWLayerGrid.getStore().getProxy().extraParams,{
			"db_id":record.get("id")
		});
		dWLayerGrid.getStore().reload();
		
		colClassifyGrid.getStore().getProxy().extraParams=Ext.apply(colClassifyGrid.getStore().getProxy().extraParams,{
			"db_id":record.get("id")
		});
		colClassifyGrid.getStore().reload();
		
		tabpanel.unmask();
		window.jdbc=window.jdbcs[record.get("dbtype")];
	});
	
	var tabpanel=Ext.create('Ext.tab.Panel',{
		region:'center',
		items:[dWLayerGrid,colClassifyGrid]
		,listeners:{
			render:function(){
				tabpanel.mask();
			}
		}
	});
	var viewPort=Ext.create('Ext.container.Viewport',{
		layout:'border',
		items:[dBGrid,tabpanel]
	});



});