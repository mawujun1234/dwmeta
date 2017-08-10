Ext.define('Ems.dwmeta.ConstraintsGrid',{
	extend:'Ext.grid.Panel',
	requires: [
	     'Ems.dwmeta.Constraints'
	],
	columnLines :true,
	stripeRows:true,
	viewConfig:{
		enableTextSelection:true
	},
	initComponent: function () {
      var me = this;
      me.columns=[
      	{xtype: 'rownumberer'},
		{dataIndex:'type',header:'约束类型'
        },
		
		{dataIndex:'name',header:'约束名称'
        },
        {dataIndex:'col_name',header:'列',flex:1
        }
        ,{dataIndex:'ref_table_name',header:'引用表'
        }
        ,{dataIndex:'ref_col_name',header:'引用列'
        }
      ];
      

	  me.store=Ext.create('Ext.data.Store',{
			autoSync:false,
			pageSize:50,
			autoLoad:false,
			model: 'Ems.dwmeta.Constraints',
			proxy:{
				type: 'ajax',
			    url : Ext.ContextPath+'/constraints/queryAll.do',
			    headers:{ 'Accept':'application/json;'},
			    actionMethods: { read: 'POST' },
			    extraParams:{limit:50},
			    reader:{
					type:'json',//如果没有分页，那么可以把后面三行去掉，而且后台只需要返回一个数组就行了
					rootProperty:'root',
					successProperty:'success',
					totalProperty:'total'		
				}
			}
	  });

	  me.dockedItems=[];
//      me.dockedItems.push({
//	        xtype: 'pagingtoolbar',
//	        store: me.store,  
//	        dock: 'bottom',
//	        displayInfo: true
//	  });
//	  
	  me.dockedItems.push({
	  		xtype: 'toolbar',
	  		dock:'top',
		  	items:[{
				text: '新增',
				itemId:'create',
				handler: function(btn){
					me.onCreate();
				},
				iconCls: 'icon-plus'
			},{
			    text: '更新',
			    itemId:'update',
			    hidden:true,
			    handler: function(){
			    	me.onUpdate();
					
			    },
			    iconCls: 'icon-edit'
			},{
			    text: '删除',
			    itemId:'destroy',
			    handler: function(){
			    	me.onDelete();    
			    },
			    iconCls: 'icon-trash'
			},{
				text: '刷新',
				itemId:'reload',
				disabled:me.disabledAction,
				handler: function(btn){
					var grid=btn.up("grid");
					grid.getStore().reload();
				},
				iconCls: 'icon-refresh'
			}]
		});

       
      me.callParent();
	},
	getParams:function(){
		var grid=this;//Ext.getCmp("sampleDesignGrid");
		var toolbars=grid.getDockedItems('toolbar[dock="top"]');

    	var params={
    		//"params['ormtno']":toolbars[0].down("#ordmtcombo").getValue(),
    					
    	};
    	return params;
	},
	onCreate:function(){
    	var me=this;
    	//var tablepanel=me.up("dwmeta_tablepanel");
    	var tablemeta_id=me.getStore().getProxy().extraParams.tablemeta_id;//tablepanel.tablemetaForm.getForm().findField("id").getValue();
    	if(!tablemeta_id){
    		Ext.Msg.alert("消息","请先输入并保存表!");
    		return;
    	}

		var child=Ext.create('Ems.dwmeta.Constraints',{
			tablemeta_id:tablemeta_id
		});
		child.set("id",null);
		
		var formpanel=Ext.create('Ems.dwmeta.ConstraintsForm',{
			tablemeta_id:tablemeta_id,
			listeners:{
    			saved:function(record){
    				me.getStore().reload();
    			}
    		}
		});
		formpanel.loadRecord(child);
		
    	var win=Ext.create('Ext.window.Window',{
    		layout:'fit',
    		title:'新增',
    		modal:true,
    		width:400,
    		height:300,
    		closeAction:'hide',
    		items:[formpanel],
    		listeners:{
    			//close:function(){
    			//	me.getStore().reload();
    			//}
    		}
    	});
    	win.show();
    },
    
     onUpdate:function(){
    	var me=this;

    	var record=me.getSelectionModel( ).getLastSelected();
    	if(record==null){
    		Ext.Msg.alert("提醒","请选择一行数据!");
    		return;
    	}
    	//var tablemeta_id=me.getStore().getProxy().extraParams.tablemeta_id;

		var formpanel=Ext.create('Ems.dwmeta.ConstraintsForm',{
			tablemeta_id:record.get("tablemeta_id")
		});
		formpanel.loadRecord(record);
		var field_col_id=formpanel.getForm().findField("col_id");
		field_col_id.getStore().load(function(records, operation, success) {
		    field_col_id.setValue(record.get("col_id"));
		});
		if(record.get("type")=="Foreign"){
			var field_ref_table_id=formpanel.getForm().findField("ref_table_id");
			field_ref_table_id.setDisabled(false);
			field_ref_table_id.getStore().load(function(records, operation, success) {
			    field_ref_table_id.setValue(record.get("ref_table_id"));
			    
			    var field_ref_col_id=formpanel.getForm().findField("ref_col_id");
			    field_ref_col_id.setDisabled(false);
			    field_ref_col_id.getStore().getProxy().extraParams={
					tablemeta_id:record.get("ref_table_id")
				}
				field_ref_col_id.getStore().load(function(records, operation, success) {
				    field_ref_col_id.setValue(record.get("ref_col_id"));
				});
			});
		}

		
		
		
    	var win=Ext.create('Ext.window.Window',{
    		layout:'fit',
    		title:'更新',
    		modal:true,
    		width:400,
    		height:300,
    		closeAction:'hide',
    		items:[formpanel]
    	});
    	win.show();
    	setTimeout(function(){
    		    

    	},2000);
    },
    
    onDelete:function(){
    	var me=this;
    	var record=me.getSelectionModel( ).getLastSelected( );

		if(!record){
		    Ext.Msg.alert("消息","请先选择一行数据");	
			return;
		}
		var parent=record.parentNode;
		Ext.Msg.confirm("删除",'确定要删除吗?', function(btn, text){
				if (btn == 'yes'){
					record.erase({
					    failure: function(record, operation) {
			            	me.getStore().reload();
					    },
					    success:function(){
					    	me.getStore().reload();
					    }
				});
			}
		});
    },
    reloadByTablemeta_id:function(tablemeta_id){
    	this.getStore().getProxy().extraParams={
    		tablemeta_id:tablemeta_id
    	}
    	this.getStore().reload();
    }
});
