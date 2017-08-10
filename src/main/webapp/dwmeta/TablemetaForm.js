Ext.define('Ems.dwmeta.TablemetaForm',{
	extend:'Ext.form.Panel',
	requires: [
	     'Ems.dwmeta.Tablemeta'
	],
	
    frame: true,
    autoScroll : true,
	buttonAlign : 'center',
    bodyPadding: '5 5 0',


    defaults: {
        msgTarget: 'under',
        labelWidth: 75,
        labelAlign:'right',
        anchor: '90%'
    },
	initComponent: function () {
       var me = this;
       me.items= [
		
	    {
            xtype      : 'fieldcontainer',
            fieldLabel : '表名',
            afterLabelTextTpl: Ext.required,
           // defaultType: 'radiofield',
            defaults: {
                flex: 1
            },
            layout: 'hbox',
            items: [
                {
			        //fieldLabel: '表名',
                	labelWidth:45,
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
			    }
            ]
        },
//	    {
//            xtype      : 'fieldcontainer',
//            fieldLabel : '实体类型',
//            defaultType: 'radiofield',
//            defaults: {
//                width:60
//            },
//            layout: 'hbox',
//            items: [
//                {
//                    boxLabel  : '表',
//                    name      : 'entitytype',
//                    inputValue: 'table'
//                }, {
//                    boxLabel  : '视图',
//                    name      : 'entitytype',
//                    inputValue: 'view'
//                }
//            ]
//        },
		{
	        fieldLabel: '备注',
	        name: 'remark',
            selectOnFocus:true,
	        xtype:'textfield'
	    },
		{
	        fieldLabel: 'id',
	        name: 'id',
            hidden:true,
            selectOnFocus:true,
	        xtype:'textfield'
	    },
		{
	        fieldLabel: '所属分类',
	        name: 'classify_id',
            hidden:true,
            selectOnFocus:true,
	        xtype:'textfield'
	    },
	    {
	        fieldLabel: '所属用户',
	        name: 'dwlayer_id',
            hidden:true,
            selectOnFocus:true,
	        xtype:'textfield'
	    },
	    {
	        fieldLabel: '数据库',
	        name: 'db_id',
	        xtype:'hiddenfield'
	    }
//	    {
//	        fieldLabel: '历史批次',
//	        name: 'history_id',
//	        xtype:'hiddenfield'
//	    }
	  ];   
	  
	  
//	  this.buttons = [];
//	  this.buttonAlign='center';
//		this.buttons.push({
//			text : '保存',
//			itemId : 'save',
//			formBind: true, //only enabled once the form is valid
//       		disabled: true,
//			glyph : 0xf0c7,
//			handler : function(button){
//				var formpanel = button.up('form');
//				formpanel.updateRecord();
//				var record=formpanel.getForm().getRecord();
//				record.set("history_id",window.history_id);
//				record.save({
//					failure: function(record, operation) {
//				    },
//				    success: function(record, operation) {
//				    	formpanel.fireEvent("saved",record);
//				    	formpanel.loadRecord(record);
//						//button.up('window').close();
//				    }
//				});			
//				
//				}
//			},{
//				text : '关闭',
//				itemId : 'close',
//				glyph : 0xf00d,
//				handler : function(button){
//					var aa=button.up('window');
//					if(aa){
//						aa.close();
//					} else {
//						button.up('dwmeta_tablepanel').close();
//					}
//				}
//	    });
      me.callParent();
	}
});
