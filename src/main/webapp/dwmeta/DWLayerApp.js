Ext.require("Ems.dwmeta.DWLayer");
Ext.require("Ems.dwmeta.DWLayerGrid");
Ext.require("Ems.dwmeta.DWLayerForm");
Ext.onReady(function(){
	var grid=Ext.create('Ems.dwmeta.DWLayerGrid',{
		region:'center'
		//title:'XXX表格'
	});
	
	var viewPort=Ext.create('Ext.container.Viewport',{
		layout:'border',
		items:[grid]
	});



});