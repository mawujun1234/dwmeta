Ext.define('Ems.dwmeta.TablemetaGridQuery',{
	extend:'Ext.grid.Panel',
	requires: [
	     'Ems.dwmeta.Tablemeta'
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
		{dataIndex:'tablename',header:'表名',width:220
        },
		{dataIndex:'name',header:'中文名称',width:220
        },
		{dataIndex:'status',header:'状态',renderer:function(value){
				if(value){
					return "正常";
				} else {
					return "<span style='color:red;'>已删除</span>";
				
				}
			}
		},
		{dataIndex:'remark',header:'备注',flex:1
        }
      ];
      

	  me.store=Ext.create('Ext.data.Store',{
			autoSync:false,
			pageSize:50,
			autoLoad:false,
			model: 'Ems.dwmeta.Tablemeta',
			proxy:{
				type: 'ajax',
			    url : Ext.ContextPath+'/tablemeta/queryByName.do',
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
		  	items:[{
		  		xtype:'textfield',
		  		itemId:'name',
		  		emptyText:'输入表名或中文名'
		  	},{
				text: '查询',
				itemId:'reload',
				disabled:me.disabledAction,
				handler: function(btn){
					var grid=btn.up("grid");
					var params=grid.getParams();
					grid.getStore().getProxy().extraParams=Ext.apply(grid.getStore().getProxy().extraParams,params);
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
    		//"params['name']":toolbars[0].down("#name").getValue()
    		"name":toolbars[0].down("#name").getValue()
    					
    	};
    	return params;
	},
	onCreate:function(){
    	var me=this;
		var child=Ext.create('Ems.dwmeta.Tablemeta',{

		});
		child.set("id",null);
		
		var formpanel=Ext.create('Ems.dwmeta.TablemetaForm',{
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
    }
    
});
