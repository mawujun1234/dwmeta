Ext.define('Ems.dwmeta.TablePanel',{
	extend:'Ext.panel.Panel',
	xtype:'dwmeta_tablepanel',
	requires: [
	     'Ems.dwmeta.TablemetaForm',
	     'Ems.dwmeta.ColumnmetaGrid',
	     'Ems.dwmeta.TablemetaGridQuery'
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
		
		me.historyGrid=Ext.create("Ems.dwmeta.HistoryGrid",{
			title:'变更历史'
		});
		
		var tabpanel=Ext.create('Ext.tab.Panel',{
			region:'center',
			items:[me.columnmetaGrid,me.constraintsGrid,me.historyGrid]
		});
		me.items=[me.tablemetaForm,tabpanel];
		
		
	   this.buttons = [];
	   this.buttonAlign='center';
		this.buttons.push({
			text : '保存',
			//itemId : 'save',
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
				 		//me.closeThis();
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
				text : '删除',
				//itemId : 'delete',
				iconCls: 'icon-trash',
				handler : function(button){
					Ext.Msg.confirm("消息","删除会状态会变成不可用!",function(btn){
						if(btn=='yes'){
							Ext.Ajax.request({
								url:Ext.ContextPath+'/tablemeta/deleteById.do',
								params:{
									id:me.tablemeta_id
								},
								success:function(response){
									//me.closeThis();
									me.loadTablemeta(me.tablemeta_id);
								}
							});
						}
					});
					
				}
	    },{
				text : "<span style='color:red;'>强制删除</span>",
				//itemId : 'delete',
				iconCls: 'icon-trash',
	
				handler : function(button){
					Ext.Msg.confirm("消息","删除后将<span style='color:red;'>不可恢复</span>!",function(btn){
						if(btn=='yes'){
							Ext.Ajax.request({
								url:Ext.ContextPath+'/tablemeta/deleteFocus.do',
								params:{
									id:me.tablemeta_id
								},
								success:function(response){
									me.closeThis();
								}
							});
						}
					});
					
				}
	    },{
	    	text : '恢复',
			itemId : 'replyById',
			iconCls: 'icon-reply',
			handler : function(button){
				Ext.Ajax.request({
								url:Ext.ContextPath+'/tablemeta/replyById.do',
								params:{
									id:me.tablemeta_id
								},
								success:function(response){
									//me.closeThis();
									me.toggleDisable(false);
									alert("恢复成功!");
								}
							});
			}
	    },{
	    	text : '关闭',
			//itemId : 'close',
			glyph : 0xf00d,
			handler : function(button){
				me.closeThis();
			}
	    });
		
		me.callParent();
	},
	
	closeThis:function(){
		var me=this;
		var aa=me.up('window');
		if(aa){
			aa.close();
		} else {
			//button.up('dwmeta_tablepanel').close();
			me.close();;
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
			        record.set("db_id",window.db_id);
					formpanel.loadRecord(record);
					
					//if(record.get("status")=="false"){
						me.toggleDisable(!record.get("status"));
					//}
			    },
			    callback: function(record, operation, success) {
			        // do something whether the load succeeded or failed
			    }
			});
			me.tablemeta_id=id;
			me.columnmetaGrid.reloadByTablemeta_id(id);
			me.constraintsGrid.reloadByTablemeta_id(id);
			me.historyGrid.reloadByTablemeta_id(id);
		}
		
	},
	toggleDisable:function(isdisable){
		//净值任何操作
		var dock=this.getDockedComponent(0);
		var btns=dock.query("button");
		for(var i=0;i<btns.length;i++){
			
			if(btns[i].getItemId()!="replyById"){
				if(isdisable){
					btns[i].disable();
				} else {
					btns[i].enable();
				}
				
			}
		}
	}
});