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
			//db_id:window.db_id,
			listeners:{
				saved:function(record){
					me.tablemeta_id=record.get("id");
					//me.columnmetaGrid.reloadByTablemeta_id(record.get("id"));
				}
			}
		});
		me.columnmetaGrid=Ext.create('Ems.dwmeta.ColumnmetaGrid',{
			//region:'center'
			title:'列结构'
		});
		
		me.constraintsGrid=Ext.create('Ems.dwmeta.ConstraintsGrid',{
			title:'约束维护'
		});
		
		var tabpanel=Ext.create('Ext.tab.Panel',{
			region:'center',
			items:[me.columnmetaGrid,me.constraintsGrid,{
				title:'变更历史'
			}]
		});
		me.items=[me.tablemetaForm,tabpanel];
		
		me.callParent();
	},
	loadTablemeta:function(id){
		var me=this;
		if(!id){
			var child=Ext.create('Ems.dwmeta.Tablemeta',{
				classify_id:me.classify_id
				,entitytype:'table'
				,db_id:window.db_id
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