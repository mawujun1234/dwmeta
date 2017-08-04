Ext.define('Ems.kpi.ColDefineGrid',{
	extend:'Ext.grid.Panel',
	requires: [
	     'Ems.kpi.ColDefine'
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
		{dataIndex:'colname',header:'列名'
        },
		{dataIndex:'name',header:'中文名称'
        },
		{dataIndex:'coltype',header:'类型'
        },
		{dataIndex:'definition',header:'定义',flex:1,renderer:function(value,metadata,record){
								metadata.tdAttr = "data-qtip='" + value+ "'";
							    return value;
							}
        },
//		{dataIndex:'status',header:'可用',xtype: 'checkcolumn'	
//            ,stopSelection :false,
//			processEvent : function(type) {  
//            	if (type == 'click')  
//                   return false;  
//            }
//		},
        {dataIndex:'status',header:'可用',renderer:function(value){
				if(value=='true'){
					return "可用";
				} else {
					return "<span style='color:red;'>禁用</span>";
				}
			}
        },
		{dataIndex:'remark',header:'备注'
        }
      ];
      

	  me.store=Ext.create('Ext.data.Store',{
			autoSync:false,
			pageSize:50,
			autoLoad:true,
			model: 'Ems.kpi.ColDefine',
			proxy:{
				type: 'ajax',
			    url : Ext.ContextPath+'/colDefine/queryAll.do',
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
 	 me.dockedItems.push({
	  	xtype: 'toolbar',
	  	dock:'top',
	  	//enableOverflow:true,
		items:[
			{
                xtype: 'textfield',
				itemId:'colname',
                fieldLabel: '列名',
                labelWidth:50,
                width:150,
                selectOnFocus:true 
            },
			{
                xtype: 'textfield',
				itemId:'name',
                fieldLabel: '中文名',
                labelWidth:50,
                width:150,
                selectOnFocus:true 
            },
            
		    Ext.create('Ext.form.ComboBox', {
			    fieldLabel: '状态',
			    itemId: 'status',
			    store: Ext.create('Ext.data.Store', {
				    fields: ['key', 'name'],
				    data : [
				    	{"key":"", "name":"所有"},
				        {"key":"true", "name":"可用"},
				        {"key":"false", "name":"禁用"}
				    ]
				}),
				editable:false,
			    queryMode: 'local',
			    //value:'true',
			    displayField: 'name',
			    valueField: 'key',
			    labelWidth:50,
	            width:150
			}),
	    	{
            	text:'查询',
            	iconCls:'icon-search',
            	handler:function(btn){
            		
            		var grid=btn.up("grid");
					
					var params=grid.getParams();
					//grid.getStore().getProxy().extraParams=params;
					grid.getStore().getProxy().extraParams=Ext.apply(grid.getStore().getProxy().extraParams,params);
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
			}]
		});

       
      me.callParent();
	},
	getParams:function(){
		var grid=this;//Ext.getCmp("sampleDesignGrid");
		var toolbars=grid.getDockedItems('toolbar[dock="top"]');

    	var params={
    		colname:toolbars[0].down("#colname").getValue(),
    		name:toolbars[0].down("#name").getValue(),
    		status:toolbars[0].down("#status").getValue()
    					
    	};
    	return params;
	},
	onCreate:function(){
    	var me=this;
		var child=Ext.create('Ems.kpi.ColDefine',{
			status:true,
			colclassify_id:me.getStore().getProxy().extraParams.colclassify_id
		});
		child.set("id",null);
		
		var formpanel=Ext.create('Ems.kpi.ColDefineForm',{
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

		var formpanel=Ext.create('Ems.kpi.ColDefineForm',{});
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
    }
});
