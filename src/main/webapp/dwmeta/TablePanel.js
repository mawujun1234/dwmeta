Ext.define('Ems.dwmeta.TablePanel',{
	extend:'Ext.panel.Panel',
	xtype:'dwmeta_tablepanel',
	requires: [
	     'Ems.dwmeta.TablemetaForm',
	     'Ems.dwmeta.ColumnmetaGrid'
	],
	layout:'border',
	
	classify_id:null,//当新建的时候
	tablemeta_id:null,//当更新的时候
	
	initComponent: function(){
		var me = this;
		
		me.tablemetaForm=Ext.create('Ems.dwmeta.TablemetaForm',{
			region:'north',
			height:130,
			listeners:{
				saved:function(record){
					me.tablemeta_id=record.get("id");
				}
			}
		});
		me.columnmetaGrid=Ext.create('Ems.dwmeta.ColumnmetaGrid',{
			region:'center'
		});
		me.items=[me.tablemetaForm,me.columnmetaGrid];
		
		me.callParent();
	},
	loadTablemeta:function(id){
		var me=this;
		if(!id){
			var child=Ext.create('Ems.dwmeta.Tablemeta',{
				classify_id:me.classify_id
			});
			child.set("id",null);
			
			var formpanel=me.tablemetaForm;
			formpanel.loadRecord(child);
			
		} else {
			Ems.dwmeta.Tablemeta.load(id,{
			    scope: this,
			    failure: function(record, operation) {
			        // do something if the load failed
			    },
			    success: function(record, operation) {
			        var formpanel=me.tablemetaForm;
					formpanel.loadRecord(record);
			    },
			    callback: function(record, operation, success) {
			        // do something whether the load succeeded or failed
			    }
			});
			me.tablemeta_id=id;
			me.columnmetaGrid.reloadByTablemeta_id(id);
		}
		
	}
});