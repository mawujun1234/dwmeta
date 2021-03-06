Ext.define('Ems.dwmeta.DBForm',{
	extend:'Ext.form.Panel',
	requires: [
	     'Ems.dwmeta.DB'
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
	        fieldLabel: '数据库',
	        name: 'name',
            selectOnFocus:true,
            allowBlank: false,
            afterLabelTextTpl: Ext.required,
            blankText:"名称不允许为空",
	        xtype:'textfield'
	    },
	    {
            xtype      : 'fieldcontainer',
            fieldLabel : '类型',
            defaultType: 'radiofield',
            defaults: {
                width:70
            },
            layout: 'hbox',
            allowBlank: false,
            afterLabelTextTpl: Ext.required,
            blankText:"名称不允许为空",
           
            items: [
                {
                    boxLabel  : 'oracle',
                    name      : 'dbtype',
                    inputValue: 'oracle'
                }, {
                    boxLabel  : 'mysql',
                    name      : 'dbtype',
                    inputValue: 'mysql'
                    
                }, {
                    boxLabel  : 'sqlserver',
                    name      : 'dbtype',
                    inputValue: 'sqlserver'
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
