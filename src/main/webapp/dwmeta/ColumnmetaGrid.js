Ext.define('Ems.dwmeta.ColumnmetaGrid',{
	extend:'Ext.grid.Panel',
	requires: [
	     'Ems.dwmeta.Columnmeta'
		,'Ems.dwmeta.ColumnmetaForm'
	],
	columnLines :true,
	stripeRows:true,
	viewConfig:{
		enableTextSelection:true
	},
	selModel: {
          selType: 'checkboxmodel'
      },
	initComponent: function () {
      var me = this;
      me.columns=[
      	{xtype: 'rownumberer'},
		{dataIndex:'colname',header:'列名'
        },
		{dataIndex:'name',header:'中文名称'
        },
		{dataIndex:'coltype',header:'类型'
        },
		{dataIndex:'collen',header:'长度',hidden:true
        },
//		{dataIndex:'ispk',header:'是否主键',xtype: 'checkcolumn'	
//            ,stopSelection :false,
//			processEvent : function(type) {  
//            	if (type == 'click')  
//                   return false;  
//            }
//		},
		{dataIndex:'nullable',header:'可否为空',xtype: 'checkcolumn'	
            ,stopSelection :false,
			processEvent : function(type) {  
            	if (type == 'click')  
                   return false;  
            }
		},
		{dataIndex:'defaultvalue',header:'默认值'
        },
		{dataIndex:'comments',header:'注释'
        },
        {dataIndex:'reasons',header:'修改原因'
        }
      ];
      

	  me.store=Ext.create('Ext.data.Store',{
			autoSync:false,
			pageSize:50,
			autoLoad:false,
			model: 'Ems.dwmeta.Columnmeta',
			proxy:{
				type: 'ajax',
			    url : Ext.ContextPath+'/columnmeta/queryAll.do',
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
    	var tablepanel=me.up("dwmeta_tablepanel");
    	var tablemeta_id=tablepanel.tablemetaForm.getForm().findField("id").getValue();
    	if(!tablemeta_id){
    		Ext.Msg.alert("消息","请先输入并保存表!");
    		return;
    	}
		var child=Ext.create('Ems.dwmeta.Columnmeta',{
			tablemeta_id:tablemeta_id
			,sorted:tablepanel.columnmetaGrid.getStore().getCount()+1
		});
		child.set("id",null);
		
		var formpanel=Ext.create('Ems.dwmeta.ColumnmetaForm',{
			listeners:{
    			saved:function(record){
    				me.getStore().add(record);
    			}
    		}
		});
		formpanel.loadRecord(child);
		
    	var win=Ext.create('Ext.window.Window',{
    		layout:'fit',
    		title:'新增列',
    		modal:true,
    		width:400,
    		height:420,
    		closeAction:'hide',
    		items:[formpanel]
//    		listeners:{
//    			close:function(){
//    				me.getStore().reload();
//    			}
//    		}
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

		var formpanel=Ext.create('Ems.dwmeta.ColumnmetaForm',{});
		formpanel.loadRecord(record);
		
    	var win=Ext.create('Ext.window.Window',{
    		layout:'fit',
    		title:'更新',
    		modal:true,
    		width:400,
    		height:420,
    		closeAction:'hide',
    		items:[formpanel]
    	});
    	win.show();
    },
    
    onDelete:function(){
    	var me=this;
    	//var record=me.getSelectionModel( ).getLastSelected( );
		var records=me.getSelectionModel( ).getSelection( );
		if(!records){
		    Ext.Msg.alert("消息","请先选择一行数据");	
			return;
		}
		//var parent=record.parentNode;
		
		Ext.Msg.confirm("删除",'确定要删除吗?', function(btn, text){
			if (btn == 'yes'){
				for(var i=0;i<records.length;i++){
					records[i].erase({
					    failure: function(record, operation) {
			            	//me.getStore().reload();
					    	alert("删除出错，请点击‘刷新’");
					    },
					    success:function(){
					    	//me.getStore().reload();
					    }
					});
				}
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
