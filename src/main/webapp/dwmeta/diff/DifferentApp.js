Ext.require('Ems.dwmeta.diff.DiffTablePanel');
Ext.onReady(function(){
	
	var diffmsg_store=Ext.create('Ext.data.Store', {
		autoLoad:false,
	    fields:[ 'dbname', 'layername', 'layername','diffMsg'],
	    proxy:{
	    	type: 'ajax',
			url : Ext.ContextPath+'/metacompare/queryDiffMsg.do',
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
	var db_combobox=Ext.create('Ext.form.field.ComboBox', {
	    fieldLabel: '数据库',
	    labelWidth:60,
	    width:160,
	    displayField: 'name',
	    valueField: 'id',
	    editable:false,
	    store: Ext.create('Ext.data.Store',{
	    	fields: ['id', 'name'],
	    	autoLoad:true,
	    	proxy:{
		    	type: 'ajax',
				url : Ext.ContextPath+'/dB/queryAll.do',
				//headers:{ 'Accept':'application/json;'},
				//actionMethods: { read: 'POST' },
				extraParams:{limit:50},
				reader:{
					type:'json',//如果没有分页，那么可以把后面三行去掉，而且后台只需要返回一个数组就行了
					rootProperty:'root',
					successProperty:'success',
					totalProperty:'total'		
				}
	    	}
	    })
	});
	//db_combobox.getStore().reload();
	var diffmsg_grid=Ext.create('Ext.grid.Panel',{
		store:diffmsg_store,
		region:'west',
		columnLines:true,
		split:true,
		columns: [
			{ text: '差异类型', dataIndex: 'diffMsg' },
			{ text: '表名', dataIndex: 'tablename', minWidth:120 },
			{ text: 'schema', dataIndex: 'layername',width:80 },
	        { text: '数据库', dataIndex: 'dbname',width:80 }
	    ],
	    width: 400
	    ,dockedItems:[{
	    	xtype: 'toolbar',
	    	dock:'top',
	    	items:[db_combobox,{
	    		text:'重新检查',
	    		handler:function(){
	    			Ext.getBody().mask("正在检查,请稍候.....");
	    			var db_id=db_combobox.getValue();
	    			Ext.Ajax.request({
	    				url:Ext.ContextPath+"/metacompare/checkChayiByDB.do",
	    				method:'POST',
	    				timeout:6000000,
	    				params:{
	    					db_id:db_id
	    				},
	    				success:function(response){
	    					diffmsg_grid.getStore().reload({params:{db_id:db_id}});
	    					Ext.getBody().unmask();
	    				}
	    			});
	    		}
	    	},{
	    		text:'查询',
	    		handler:function(){
	    			var db_id=db_combobox.getValue();
	    			diffmsg_grid.getStore().reload({params:{db_id:db_id}})
	    		}
	    	}]
	    }]
	});
	
	var diffTablePanel=Ext.create('Ems.dwmeta.diff.DiffTablePanel',{
		region:'center'
	});
	var viewPort=Ext.create('Ext.container.Viewport',{
		layout:'border',
		items:[diffmsg_grid,diffTablePanel]
	});



});