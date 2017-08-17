Ext.define('Ems.dwmeta.ConstraintsForm',{
	extend:'Ext.form.Panel',
	requires: [
	     'Ems.dwmeta.Constraints'
	],
	
    frame: true,
    autoScroll : true,
	buttonAlign : 'center',
    bodyPadding: '5 5 0',

    tablemeta_id:null,

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
	        fieldLabel: '约束名称',
	        name: 'name',
	        allowBlank: false,
            afterLabelTextTpl: Ext.required,
            blankText:"约束名不允许为空",
            selectOnFocus:true,
	        xtype:'textfield'
	    },{
			fieldLabel: '约束类型',
			name: 'type',
			allowBlank: false,
            afterLabelTextTpl: Ext.required,
            blankText:"约束类型不允许为空",
			queryMode: 'local',
			editable:false,
			forceSelection:true,
		    displayField: 'name',
		    valueField: 'key',
		    store: {
			    fields: ['key', 'name'],
			    data : [
			    	{"key":"Foreign", "name":"外键"},
			    	{"key":"Unique", "name":"唯一键"},
			    	{"key":"Primary", "name":"主键"}
			    ]
			},
			xtype:'combobox',
			listeners:{
				select:function(combo, record, eOpts){
					if(record.get("key")=="Foreign"){
						combo.nextSibling("#ref_col_id").setDisabled(false);
						combo.nextSibling("#ref_table_id").setDisabled(false);
					} else {
						combo.nextSibling("#ref_col_id").setDisabled(true);
						combo.nextSibling("#ref_table_id").setDisabled(true);
						combo.nextSibling("#ref_col_id"). clearValue();
						combo.nextSibling("#ref_table_id"). clearValue();
					}
					
				}
			
			}
		},
        {
			xtype : 'tagfield',
			fieldLabel : '列',
			name:'col_id',//本主表的列
			allowBlank: false,
            afterLabelTextTpl: Ext.required,
            blankText:"列不允许为空",
			displayField : 'colname',
			valueField : 'id',
			queryMode : 'local',
			filterPickList : true,
			store:Ext.create('Ext.data.ArrayStore',{
				fields:["id","colname"],
				data:[]
			})
//			store : Ext.create('Ext.data.Store',{
//				fields:["id","colname"],
//				proxy:{
//					type: 'ajax',
//				    url : Ext.ContextPath+'/columnmeta/query4combo.do',
//				    headers:{ 'Accept':'application/json;'},
//				    actionMethods: { read: 'POST' },
//				    extraParams:{
//				    	tablemeta_id:me.tablemeta_id
//				    },
//				    reader:{
//						type:'json'//,//如果没有分页，那么可以把后面三行去掉，而且后台只需要返回一个数组就行了
////						rootProperty:'root',
////						successProperty:'success',
////						totalProperty:'total'		
//					}
//					
//				}
//			})
		},
		{
			xtype : 'combobox',
			fieldLabel : '引用主表',
			name:'ref_table_id',
			itemId:'ref_table_id',
			//allowBlank: false,
            //afterLabelTextTpl: Ext.required,
            //blankText:"列不允许为空",
			displayField : 'tablename',
			valueField : 'id',
			queryMode : 'remote',
			//filterPickList : true,
			disabled:true,
			store : Ext.create('Ext.data.Store',{
				fields:["id","tablename"],
				proxy:{
					type: 'ajax',
				    url : Ext.ContextPath+'/constraints/querySameUserTable.do',
				    headers:{ 'Accept':'application/json;'},
				    actionMethods: { read: 'POST' },
				    extraParams:{
				    	dwlayer_id:window.tableTabPanel.dwlayer_id
				    },
				    reader:{
						type:'json'//,//如果没有分页，那么可以把后面三行去掉，而且后台只需要返回一个数组就行了		
					}
					
				}
			}),
			listeners:{
				select:function(combo, record, eOpts ){
					var ref_column_combo=combo.nextSibling("#ref_col_id");
					ref_column_combo.getStore().removeAll();
					ref_column_combo.clearValue();

					ref_column_combo.getStore().getProxy().extraParams={
						tablemeta_id:record.get("id")
					}
					ref_column_combo.getStore().reload();
				}
			}
		},
		{
			xtype : 'combobox',
			fieldLabel : '引用的列',
			name:'ref_col_id',
			itemId:'ref_col_id',
			//allowBlank: false,
            //afterLabelTextTpl: Ext.required,
            //blankText:"列不允许为空",
			displayField : 'colname',
			valueField : 'id',
			queryMode : 'remote',
			disabled:true,
			editable:false,
			store : Ext.create('Ext.data.Store',{
				fields:["id","tablename"],
				autoLoad:false,
				proxy:{
					type: 'ajax',
				    url : Ext.ContextPath+'/columnmeta/query4combo.do',
				    headers:{ 'Accept':'application/json;'},
				    actionMethods: { read: 'POST' },
				    extraParams:{
				    	//tablemeta_id:me.tablemeta_id
				    },
				    reader:{
						type:'json'//,//如果没有分页，那么可以把后面三行去掉，而且后台只需要返回一个数组就行了		
					}
					
				}
			})
		},
//        {
//	        fieldLabel: '引用主表',
//	        name: 'ref_table_id',
//            hidden:false,
//            selectOnFocus:true,
//	        xtype:'textfield'
//	    },
//		{
//	        fieldLabel: '引用的列',
//	        name: 'column',
//            selectOnFocus:true,
//	        xtype:'textfield'
//	    },
//	    {
//        	fieldLabel: '是否可用',
//            name:'status',
//            xtype: 'checkbox',
//            cls: 'x-grid-checkheader-editor'
//        },
		{
	        fieldLabel: 'id',
	        name: 'id',
            hidden:true,
            selectOnFocus:true,
	        xtype:'textfield'
	    },
		{
	        fieldLabel: '所属表',
	        name: 'tablemeta_id',
            hidden:true,
            selectOnFocus:true,
	        xtype:'textfield'
	    }
	  ];   
	  
	  
	  this.buttons = [];
		this.buttons.push({
			text : '保存',
			itemId : 'save',
			formBind: true, //only enabled once the form is valid
       		disabled: true,
			glyph : 0xf0c7,
			handler : function(button){
				var formpanel = button.up('form');
				formpanel.updateRecord();
				var record=formpanel.getForm().getRecord();
				record.set("col_name",formpanel.getForm().findField("col_id").getRawValue());
				record.set("ref_table_name",formpanel.getForm().findField("ref_table_id").getRawValue());
				record.set("ref_col_name",formpanel.getForm().findField("ref_col_id").getRawValue());
				formpanel.fireEvent("saved",record);
				button.up('window').close();
//				formpanel.getForm().getRecord().save({
//					failure: function(record, operation) {
//				    },
//				    success: function(record, operation) {
//				    	formpanel.fireEvent("saved",record);
//						button.up('window').close();
//				    }
//				});			
				
				}
			},{
				text : '关闭',
				itemId : 'close',
				glyph : 0xf00d,
				handler : function(button){
					button.up('window').close();
				}
	    });
      me.callParent();
	}
});
