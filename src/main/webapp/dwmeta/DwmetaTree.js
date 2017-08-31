/**
 * 功能的扩展，添加自定义的怎，删，改
 * 添加右键菜单，增，删，改，并且增加工具栏，增，删，改。
 * 后台的类最好继承TreeNode类，这样就可以少写很多代码
 */
Ext.define('Ems.dwmeta.DwmetaTree', {
    extend: 'Ext.tree.Panel',
    requires:[
    	'Ems.dwmeta.Classify',
    	'Ems.dwmeta.TableTabPanel'
    	,'Ems.dwmeta.TablePanel'
    ],
    rootVisible: false,
    initComponent: function () {
		var me = this;

        me.store = Ext.create('Ext.data.TreeStore', {
	       	autoLoad:true,
	       	nodeParam :'parent_id',//传递到后台的数据，默认是node
	       	fields:[],
	       	proxy:{
				type: 'ajax',
			    url : Ext.ContextPath+'/classify/query4tree.do',
			    headers:{ 'Accept':'application/json;'},
			    actionMethods: { read: 'POST' },
			    reader:{
					type:'json'		
				},
				extraParams:{
					db_id:window.db_id,
					show_deleted:false
				}
			},
			root: {
			    expanded: true,
			    text:"根节点" ,
			    dwlayer_id:null,
			    type:null
			}
		});
		me.on("beforeitemexpand",function(node){
			//alert(1);
			me.store.getProxy().extraParams=Ext.apply(me.store.getProxy().extraParams,{
				db_id:window.db_id,
				type:node.get("type"),
				//show_deleted:false,
				dwlayer_id:node.get("dwlayer_id")
			});
			
		});
		me.on("itemclick",function( view, record, item, index, e, eOpts ){
			window.tableTabPanel.dwlayer_id=record.get("dwlayer_id");
			window.click_node=record;
			if(record.get("type")=="tablemeta"){
				window.tableTabPanel.createTablemeta(record.get("id"),record.get("text"));
			}
			if(node.get("type")=='dwmeta'){
				//开始获取这个数据库支持的数据类型
				Ext.Ajax.request({
			    	url:Ext.ContextPath+'/dB/getColumnTypes.do',
			    	method:'POST',
			    	params:{
			    		dwlayer_id:node.get("dwlayer_id")
			    	},
			    	success:function(response){
			    		var obj=Ext.decode(response.responseText);
			    		window.fieldtypes=obj.fieldtypes;
			    		//console.log(window.fieldtypes);
			    	}
			    });
			}
			
			
		});
		me.initAction();
		
		 me.dockedItems=[];
		 me.dockedItems.push({
	  		xtype: 'toolbar',
	  		dock:'top',
		  	items:[
		  	{
		  		xtype:'checkboxfield',
		  		fieldLabel:'显示已删除',
		  		labelWidth:70,
		  		itemId:'show_deleted',
		  		listeners:{
		  			 change:function( checkbox, newValue, oldValue, eOpts ) {
		  			 	var tree=me;	
		  			 	tree.getStore().getProxy().extraParams={
							db_id:window.db_id,
							show_deleted:newValue
						}
	
						me.getStore().reload({
							callback:function(records,operation ,success ){
								if(success ==true){
									for(var i=0;i<records.length;i++){
										records[i].collapse();
										records[i].expand();
									}
								}
							}
						});	
		  			 }
		  		}
		  	}
		  	,{
		  		text: '手工搜索',
		  		//iconCls: 'icon-search',
		  		handler:function(btn){
		  			me.showSearchTablemetaGrid();
		  		}
		  	}
//			,{
//				text: '查询',
//				itemId:'reload',
//				//disabled:me.disabledAction,
//				handler: function(btn){
//					var show_deleted=btn.previousSibling("#show_deleted");
//					var tree=me;//btn.up("tree");
//					tree.getStore().getProxy().extraParams={
//						db_id:window.db_id,
//						show_deleted:show_deleted.getValue()
//					}
//
//					me.getStore().reload({
//						callback:function(records,operation ,success ){
//							if(success ==true){
//								for(var i=0;i<records.length;i++){
//									records[i].collapse();
//									records[i].expand();
//								}
//							}
//						}
//					});	
//				},
//				iconCls: 'icon-refresh'
//			}
			]
		});
       
		me.callParent(arguments);
    },

    initAction:function(){
     	var me = this;
     	var actions=[];
     	
       var create = new Ext.Action({
		    text: '新建分类',
		    itemId:'create',
		    handler: function(b){
		    	me.onCreate(null,b);
		    },
		    iconCls: 'icon-plus'
		});
		//me.addAction(create);
		actions.push(create);
		
		var update = new Ext.Action({
		    text: '更新分类',
		    itemId:'update',
		    handler: function(){
		    	me.onUpdate();
				
		    },
		    iconCls: 'icon-edit'
		});
		actions.push(update);
		
		var destroy = new Ext.Action({
		    text: '删除分类',
		    itemId:'destroy',
		    handler: function(){
		    	me.onDelete();    
		    },
		    iconCls: 'icon-trash'
		});
		//me.addAction(destroy);
		actions.push(destroy)
		
		var createTable = new Ext.Action({
		    text: '新建表',
		    itemId:'createTable',
		    handler: function(b){
		    	me.onCreateTable(null,b);
		    },
		    iconCls: 'icon-plus'
		});
		actions.push(createTable);
		var importTable = new Ext.Action({
		    text: '导入表',
		    itemId:'importTable',
		    handler: function(b){
		    	me.onImportTable(null,b);
		    },
		    iconCls: 'icon-plus'
		});
		actions.push(importTable);
		
		
		var reload = new Ext.Action({
		    text: '刷新',
		    itemId:'reload',
		    handler: function(){
		    	me.onReload();
		    },
		    iconCls: 'icon-refresh'
		});
		//me.addAction(reload);
		actions.push(reload);

		var menu=Ext.create('Ext.menu.Menu', {
			items: actions
		});	
		me.on('itemcontextmenu',function(tree,record,item,index,e){
			menu.showAt(e.getXY());
			e.stopEvent();
		});
		me.contextMenu=menu;
		
    },
    onCreate:function(){
    	var me=this;

    	var parent=me.getSelectionModel( ).getLastSelected( )||me.getRootNode( );    
    	var type=parent.get("type");
    	var parent_id=parent.get("id");
    	var dwlayer_id=parent.get("dwlayer_id");
    	
    	//创建数据库连接
    	if(!type){
    		return;
    	} else if("dwmeta"==type){//创建分类
    		var title="新建分类";
    		var child=Ext.create('Ems.dwmeta.Classify',{
			    //'parent_id':null,
			    dwlayer_id:dwlayer_id,
			    type:'classify'
			});
			child.set("id",null);
			
			var formpanel=Ext.create('Ems.dwmeta.ClassifyForm',{});
			formpanel.loadRecord(child);
    	} else if("classify"==type){
    		var title="新建分类";
    		var child=Ext.create('Ems.dwmeta.Classify',{
			    'parent_id':parent_id,
			    dwlayer_id:dwlayer_id,
			    type:'classify'
			});
			child.set("id",null);
			
			var formpanel=Ext.create('Ems.dwmeta.ClassifyForm',{});
			formpanel.loadRecord(child);
    	}
		
		
		
    	var win=Ext.create('Ext.window.Window',{
    		layout:'fit',
    		title:title,
    		modal:true,
    		width:400,
    		height:300,
    		closeAction:'hide',
    		items:[formpanel],
    		listeners:{
    			close:function(){
    				me.onReload(parent);
    			}
    		}
    	});
    	win.show();
    },
    
     onUpdate:function(){
    	var me=this;

    	var node=me.getSelectionModel( ).getLastSelected();
    	if(node==null || node.isRoot()){
    		Ext.Msg.alert("提醒","请选择一个不是根节点的节点!");
    		return;
    	}
    	var id=node.get("id");
    	var type=node.get("type");
    	var dwlayer_id=node.get("dwlayer_id");
    	//创建数据库连接
    	if(!type){
    		return;
    	} else if("dwmeta"==type){//创建分类
    		return;
    	} else if("classify"==type){
			Ems.dwmeta.Classify.load(id, {
			    success: function(child) {
			        formpanel.loadRecord(child);
			    }
			});
			var formpanel=Ext.create('Ems.dwmeta.ClassifyForm',{
				listeners:{
					saved:function(record){
						node.set("text",record.get("name"))
					}
				}
			});
			
			
    	}
		
    	var win=Ext.create('Ext.window.Window',{
    		layout:'fit',
    		title:'更新分类',
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
    	var node=me.getSelectionModel( ).getLastSelected( );

		if(!node){
		    Ext.Msg.alert("消息","请先选择节点");	
			return;
		}
		if(node.isRoot()){
			Ext.Msg.alert("消息","根节点不能删除!");	
			return;
		}
		var parent=node.parentNode;
		var id=node.get("id");
		var type=node.get("type");
    	var dwlayer_id=node.get("dwlayer_id");
    	if(type=="dwmeta" || type=="tablemeta"){
    		Ext.Msg.alert("消息","不是分类，不能删除!");
    		return;
    	}
		Ext.Msg.confirm("删除",'确定要删除吗?', function(btn, text){
			if (btn == 'yes'){
				Ext.Ajax.request({
					url:Ext.ContextPath+'/classify/deleteById.do',
					method:'POST',
					params:{
						id:id,
						type:type,
						dwlayer_id:dwlayer_id
					},
					success:function(response){
						me.onReload(parent);
					}
				});
			}
		});
    },
    onCreateTable:function(){
    	var me=this;
    	var node=node||me.getSelectionModel( ).getLastSelected( );
    	var id=node.get("id");
		var type=node.get("type");
    	var dwlayer_id=node.get("dwlayer_id");
    	if(type=="dwmeta" || type=="tablemeta"){
    		Ext.Msg.alert("消息","不是‘分类’，不能在这下面创建表!");
    		return;
    	}
    	
    	var tablePanel=Ext.create('Ems.dwmeta.TablePanel',{
    		classify_id:id,
    		dwlayer_id:dwlayer_id
    		//,db_id:window.db_id
    	});
    	tablePanel.loadTablemeta();
    	var win=Ext.create('Ext.window.Window',{
    		layout:'fit',
    		title:'新建-表元数据',
    		modal:true,
    		maximized:true,
    		//width:400,
    		//height:300,
    		//closeAction:'hide',
    		items:[tablePanel]
    	});
    	win.show();
    },
    onImportTable:function(){
    	var me=this;
    	var node=node||me.getSelectionModel( ).getLastSelected( );
    	var id=node.get("id");
		var type=node.get("type");
    	var dwlayer_id=node.get("dwlayer_id");
    	if(type=="dwmeta" || type=="tablemeta"){
    		Ext.Msg.alert("消息","不是‘分类’，不能在这下面创建表!");
    		return;
    	}
    	
    	
    },
    onReload:function(node){
    	var me=this;
    	var parent=node||me.getSelectionModel( ).getLastSelected( );
		if(parent){
		    me.getStore().load({node:parent});
		} else {
		    me.getStore().reload();	
		}      
    },
   
    
    getContextMenu:function(){
    	return this.contextMenu;
    },
    getLastSelected:function(){
    	return this.getSelectionModel( ).getLastSelected( );
    },
    showSearchTablemetaGrid:function(){
    	var grid=Ext.create('Ems.dwmeta.TablemetaGridQuery',{
    		listeners:{
    			itemclick:function(view, record, item, index, e, eOpts){
    				window.tableTabPanel.createTablemeta(record.get("id"),record.get("tablename"));
    				//grid.getStore().remove(record);
    			}
    		}
    	});
    	var win=Ext.create('Ext.window.Window',{
    		layout:'fit',
    		title:'搜索-选择表(双击选择)',
    		modal:true,
    		//maximized:true,
    		width:650,
    		height:350,
    		//closeAction:'hide',
    		items:[grid]
    	});
    	win.show();
    }
    
});
