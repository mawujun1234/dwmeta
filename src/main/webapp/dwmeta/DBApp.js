Ext.require("Ems.dwmeta.DB");
Ext.require("Ems.dwmeta.DBGrid");
Ext.require("Ems.dwmeta.DBForm");
Ext.require("Ems.dwmeta.DWLayer");
Ext.require("Ems.dwmeta.DWLayerGrid");
Ext.require("Ems.dwmeta.DWLayerForm");
Ext.onReady(function(){
	var dBGrid=Ext.create('Ems.dwmeta.DBGrid',{
		title:'数据库',
		region:'west',
		split:true,
		width:280
	});
	var dWLayerGrid=Ext.create('Ems.dwmeta.DWLayerGrid',{
		region:'center'
		,title:'连接用户管理'
		,listeners:{
			render:function(){
				dWLayerGrid.mask();
			}
		}
	});
	dBGrid.on("itemclick",function(view, record, item, index, e, eOpts){
		dWLayerGrid.getStore().getProxy().extraParams=Ext.apply(dWLayerGrid.getStore().getProxy().extraParams,{
			"db_id":record.get("id")
		});
		dWLayerGrid.getStore().reload();
		dWLayerGrid.unmask();
	});
	
	var viewPort=Ext.create('Ext.container.Viewport',{
		layout:'border',
		items:[dBGrid,dWLayerGrid]
	});



});