Ext.require('Ems.dwmeta.DwmetaTree');
Ext.onReady(function(){
	var dwmetaTree=Ext.create('Ems.dwmeta.DwmetaTree',{
		region:'west'
		,width:220
		,split:true
		//title:'XXX表格'
	});
	
	var viewPort=Ext.create('Ext.container.Viewport',{
		layout:'border',
		items:[dwmetaTree,{region:'center',html:'11111'}]
	});



});