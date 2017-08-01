Ext.require("Ems.kpi.Kpi");
Ext.require("Ems.kpi.KpiGrid");
Ext.require("Ems.kpi.KpiForm");
Ext.onReady(function(){
	var grid=Ext.create('Ems.kpi.KpiGrid',{
		region:'center'
		//title:'XXX表格'
	});
	
	var viewPort=Ext.create('Ext.container.Viewport',{
		layout:'border',
		items:[grid]
	});



});