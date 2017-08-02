Ext.require('Ems.dwmeta.DwmetaTree');
Ext.require('Ems.dwmeta.TablePanel');
Ext.require('Ext.ux.TabCloseMenu');
Ext.onReady(function(){
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