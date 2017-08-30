Ext.define('Ems.dwmeta.DWLayerGrid',{
	extend:'Ext.grid.Panel',
	requires: [
	     'Ems.dwmeta.DWLayer'
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
		{dataIndex:'name',header:'名称'
        },
		{dataIndex:'jdbc_driver',header:'驱动程序',width:200
        },
		{dataIndex:'jdbc_url',header:'连接地址',width:280
        },
		{dataIndex:'jdbc_username',header:'账号'
        },
		{dataIndex:'jdbc_password',header:'密码'
        },
		{dataIndex:'remark',header:'备注'
        }
      ];
      

	  me.store=Ext.create('Ext.data.Store',{
			autoSync:false,
			pageSize:50,
			autoLoad:false,
			model: 'Ems.dwmeta.DWLayer',
			proxy:{
				type: 'ajax',
			    url : Ext.ContextPath+'/dWLayer/queryAll.do',
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
	  me.dockedItems.push({
	  	xtype: 'toolbar',
	  	dock:'top',
	  	//enableOverflow:true,
		items:[
			{
                xtype: 'textfield',
				itemId:'name',
                fieldLabel: '名称',
                labelWidth:60,
                width:150,
                selectOnFocus:true 
            },
	    	{
            	text:'查询',
            	iconCls:'icon-search',
            	handler:function(btn){
            		
            		var grid=btn.up("grid");
					
					var params=grid.getParams();
					grid.getStore().getProxy().extraParams=params;
					grid.getStore().reload();
            	}
            }
	  	]
	  });
	  
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
			},{
				text: '测试连接',
				
				handler: function(btn){
					var grid=btn.up("grid");
					grid.testConn();
				},
				iconCls:'icon-bolt'
			}]
		});

       
      me.callParent();
	},
	getParams:function(){
		var grid=this;//Ext.getCmp("sampleDesignGrid");
		var toolbars=grid.getDockedItems('toolbar[dock="top"]');

    	var params={
    		//"params['ormtno']":toolbars[0].down("#ordmtcombo").getValue(),
			"params['name']":grid.down("#name").getValue()
    					
    	};
    	return params;
	},
	onCreate:function(){
    	var me=this;
		var child=Ext.create('Ems.dwmeta.DWLayer',{
			db_id:me.getStore().getProxy().extraParams.db_id
			,jdbc_url:window.jdbc.jdbc_url
			,jdbc_driver:window.jdbc.jdbc_driver
		});
		child.set("id",null);
		
		var formpanel=Ext.create('Ems.dwmeta.DWLayerForm',{});
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
    			close:function(){
    				me.getStore().reload();
    			}
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

		var formpanel=Ext.create('Ems.dwmeta.DWLayerForm',{});
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
    testConn:function(){
		var me=this;
		var record=me.getSelectionModel( ).getLastSelected( );

		if(!record){
		    Ext.Msg.alert("消息","请先选择一行数据");	
			return;
		}
		Ext.Ajax.request({
			headers:{ 'Accept':'application/json;'},
			url:Ext.ContextPath+'/dWLayer/testConn.do',
			params:{
				jdbc_driver:record.get("jdbc_driver"),
				jdbc_url:record.get("jdbc_url"),
				jdbc_username:record.get("jdbc_username"),
				jdbc_password:record.get("jdbc_password")
			},
			method:'POST',
			success:function(response){
				var obj=Ext.decode(response.responseText);
				//alert(obj.success);
				if(obj.success==false){
					Ext.Msg.alert("消息",obj.msg);
				} else {
					Ext.Msg.alert("消息","测试连接成功!");
				}
			}
		
		});
	}
});
