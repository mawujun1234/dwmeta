Ext.define('Ems.dwmeta.TablePanel',{
	extend:'Ext.panel.Panel',
	xtype:'dwmeta_tablepanel',
	requires: [
	     'Ems.dwmeta.TablemetaForm',
	     'Ems.dwmeta.ColumnmetaGrid'
	],
	layout:'border',
	
	classify_id:null,//当新建的时候
	tablemeta_id:null,//当新建的时候
	dwlayer_id:null,//主要用在新建的时候

	
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
		
		
	   this.buttons = [];
	   this.buttonAlign='center';
		this.buttons.push({
			text : '保存',
			itemId : 'save',
			formBind: true, //only enabled once the form is valid
       		//disabled: true,
			glyph : 0xf0c7,
			handler : function(button){
				var formpanel = me.tablemetaForm;//button.up('form');
				formpanel.updateRecord();
				var record=formpanel.getForm().getRecord();
				 window.hisobj.tablemeta=record.getData();
				 Ext.Ajax.request({
				 	headers :{ 'Accept':'application/json;'},
				 	url:Ext.ContextPath+"/tablemeta/createorupdate.do",
				 	jsonData:window.hisobj,
				 	method:'POST',
				 	success:function(response){
				 		window.hisobj={
					    	tablemeta:{},
							createcoles:[],
							updatecoles:[],
							deletecoles:[],
							createConstraints:[],
							deleteConstraints:[]
						}
				 		var obj=Ext.decode(response.responseText);
				 		me.loadTablemeta(obj.tablemeta_id);
				 		//me.columnmetaGrid.reloadByTablemeta_id(obj.tablemeta_id);
				 		me.closeThis();
				 		alert("成功");
				 		
				 		
				 	}
				 	
				 });
//				//record.set("history_id",window.history_id);
//				record.save({
//					failure: function(record, operation) {
//				    },
//				    success: function(record, operation) {
//				    	formpanel.fireEvent("saved",record);
//				    	formpanel.loadRecord(record);
//						//button.up('window').close();
//				    }
//				});	
				
				
				}
			},{
				text : '关闭',
				itemId : 'close',
				glyph : 0xf00d,
				handler : function(button){
					me.closeThis();
				}
	    });
		
		me.callParent();
	},
	
	closeThis:function(){
		var aa=me.up('window');
		if(aa){
			aa.close();
		} else {
			button.up('dwmeta_tablepanel').close();
		}
	},
	loadTablemeta:function(id){
		var me=this;
		if(!id){
			var child=Ext.create('Ems.dwmeta.Tablemeta',{
				classify_id:me.classify_id
				,dwlayer_id:me.dwlayer_id
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
			me.constraintsGrid.reloadByTablemeta_id(id);
			
		}
		
	}
});