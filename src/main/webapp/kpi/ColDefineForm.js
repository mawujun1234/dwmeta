Ext.define('Ems.kpi.ColDefineForm',{
	extend:'Ext.form.Panel',
	requires: [
	     'Ems.kpi.ColDefine'
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
	        fieldLabel: '列名',
	        name: 'colname',
            allowBlank: false,
            afterLabelTextTpl: Ext.required,
            blankText:"列名不允许为空",
            selectOnFocus:true,
	        xtype:'textfield'
	    },
		{
	        fieldLabel: '中文名称',
	        name: 'name',
            allowBlank: false,
            afterLabelTextTpl: Ext.required,
            blankText:"中文名称不允许为空",
            selectOnFocus:true,
	        xtype:'textfield'
	    },
//		{
//	        fieldLabel: '类型',
//	        name: 'coltype',
//            allowBlank: false,
//            afterLabelTextTpl: Ext.required,
//            blankText:"类型不允许为空",
//            selectOnFocus:true,
//	        xtype:'textfield'
//	    },
	    Ext.create('Ext.form.ComboBox', {
		    fieldLabel: '列类型',
		    name: 'coltype',
		    allowBlank: false,
            afterLabelTextTpl: Ext.required,
            blankText:"类型不允许为空",
		    store: Ext.create('Ext.data.ArrayStore', {
				fields: ['name'],
				data : window.fieldtypes
			}),
		    queryMode: 'local',
		    displayField: 'name',
		    valueField: 'name'
		}),
		{
	        fieldLabel: '定义',
	        name: 'definition',
            selectOnFocus:true,
	        xtype:'textarea'
	    },
        {
            xtype      : 'fieldcontainer',
            fieldLabel : '可用',
            defaultType: 'radiofield',
            defaults: {
                flex: 1
            },
            layout: 'hbox',
            items: [
           		{
                    boxLabel  : '可用',
                    name: 'status',
                    inputValue: 'true'
                },{
                    boxLabel  : '禁用',
                    name: 'status',
                    inputValue: 'false'
                }
            ]
        },
		
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
				formpanel.getForm().getRecord().save({
					failure: function(record, operation) {
				    },
				    success: function(record, operation) {
				    	formpanel.fireEvent("saved",record);
						button.up('window').close();
				    }
				});			
				
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
