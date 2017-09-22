Ext.define('Ems.dwmeta.diff.DiffTablePanel',{
	extend:'Ext.panel.Panel',
	//xtype:'dwmeta_tablepanel',
	requires: [

	],
	layout:'border',
	

	
	initComponent: function(){
		var me = this;
		
		me.tablemetaForm=Ext.create('Ext.form.Panel',{
			region:'north',
			height:100,
			defaults: {
		        msgTarget: 'under',
		        labelWidth: 75,
		        labelAlign:'right',
		        anchor: '90%'
		    },
			items:[{
				        //fieldLabel: '表名',
	                	fieldLabel: '表名',
	                	labelWidth:60,
				        name: 'tablename',
			            allowBlank: false,
			            afterLabelTextTpl: Ext.required,
			            blankText:"表名不允许为空",
			            selectOnFocus:true,
				        xtype:'textfield'
				    },
					{
				        fieldLabel: '中文名称',
				        labelWidth:60,
				        name: 'name',
			            selectOnFocus:true,
				        xtype:'textfield'
				    }]
		});
		me.columnmetaGrid=Ext.create('Ext.grid.Panel',{
			//region:'center'
			title:'列差异情况',
			columnLines :true,
			stripeRows:true,
			viewConfig:{
				enableTextSelection:true
			},
			columns:[
		      	{xtype: 'rownumberer'},
				{dataIndex:'colname',header:'列名'
		        },
				{dataIndex:'name',header:'中文名称'
		        },
				{dataIndex:'coltype',header:'类型'
		        },
				{dataIndex:'colprecision',header:'长度',width:60
		        },
		        {dataIndex:'colscale',header:'小数位数',width:80
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
		      ],
		      store:Ext.create('Ext.data.Store', {
				    fields: ['abbr', 'name'],
				    data : [
				        {"abbr":"AL", "name":"Alabama"},
				        {"abbr":"AK", "name":"Alaska"},
				        {"abbr":"AZ", "name":"Arizona"}
				    ]
				})
		});
		
		me.constraintsGrid=Ext.create('Ext.grid.Panel',{
			title:'约束差异情况',
			columnLines :true,
			stripeRows:true,
			viewConfig:{
				enableTextSelection:true
			},
			columns:[
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
		    ],
		    store:Ext.create('Ext.data.Store', {
				    fields: ['abbr', 'name'],
				    data : [
				        {"abbr":"AL", "name":"Alabama"},
				        {"abbr":"AK", "name":"Alaska"},
				        {"abbr":"AZ", "name":"Arizona"}
				    ]
				})
		});
		
		var tabpanel=Ext.create('Ext.tab.Panel',{
			region:'center',
			items:[me.columnmetaGrid,me.constraintsGrid]
		});
		me.items=[me.tablemetaForm,tabpanel];
		
		
		
		me.callParent();
	}

});