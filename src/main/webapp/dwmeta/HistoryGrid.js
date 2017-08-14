Ext.define('Ems.dwmeta.HistoryGrid',{
	extend:'Ext.grid.Panel',
	requires: [
	     'Ems.dwmeta.History'
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
		
		{dataIndex:'operater_name',header:'操作者'
        },
		{dataIndex:'intiactive',header:'是否主动',renderer:function(value){
			if(value==true){
				return "主动";
			} else {
				return "<span style='color:red;'>被动</span>";
			}
		}
		},
		{dataIndex:'operateTime',header:'操作时间',width:150
		},
		{
            //xtype:'actioncolumn',
			dataIndex:'operater',
			header:'查看sql',
            width:70,
            renderer:function(value){
            	return "<a href='javascript:void(0);'>查看</a>";
            }
        }
//		{dataIndex:'sql_content',header:'修改内容',flex:1
//        }
      ];
      me.on("cellclick",function(view, td, cellIndex, record, tr, rowIndex, e, eOpts){
      	if(cellIndex==4){
      		var win=Ext.create('Ext.window.Window',{
      			layout:'fit',
      			modal:true,
      			width:680,
      			height:350,
      			items: [{
      				title:'sql内容',
			        xtype     : 'textareafield',
			        grow      : true,
			        name      : 'message',
			        anchor    : '100%',
			        value:record.get("sql_content")
			    }]
      		});
      		win.show();
      	}
      });
      

	  me.store=Ext.create('Ext.data.Store',{
			autoSync:false,
			pageSize:50,
			autoLoad:true,
			model: 'Ems.dwmeta.History',
			proxy:{
				type: 'ajax',
			    url : Ext.ContextPath+'/tablemeta/queryHistory.do',
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
		  	items:[
//		  		{
//				text: '新增',
//				itemId:'create',
//				handler: function(btn){
//					me.onCreate();
//				},
//				iconCls: 'icon-plus'
//			},{
//			    text: '更新',
//			    itemId:'update',
//			    handler: function(){
//			    	me.onUpdate();
//					
//			    },
//			    iconCls: 'icon-edit'
//			},{
//			    text: '删除',
//			    itemId:'destroy',
//			    handler: function(){
//			    	me.onDelete();    
//			    },
//			    iconCls: 'icon-trash'
//			},
			{
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
		var child=Ext.create('Ems.dwmeta.History',{

		});
		child.set("id",null);
		
		var formpanel=Ext.create('Ems.dwmeta.HistoryForm',{
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

		var formpanel=Ext.create('Ems.dwmeta.HistoryForm',{});
		formpanel.loadRecord(record);
		
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
