Ext.define('Ems.dwmeta.ColumnmetaForm',{
	extend:'Ext.form.Panel',
	requires: [
	     'Ems.dwmeta.Columnmeta'
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
		    fieldLabel: '类型',
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
	        fieldLabel: '长度',
	        name: 'collen',
	        hidden:true,
            selectOnFocus:true,
	        xtype:'textfield'
	    },
//        {
//        	fieldLabel: '是否主键',
//            name:'ispk',
//            xtype: 'checkbox',
//            cls: 'x-grid-checkheader-editor'
//        },
        {
        	fieldLabel: '可否为空',
            name:'nullable',
            xtype: 'checkbox',
            cls: 'x-grid-checkheader-editor'
        },
		{
	        fieldLabel: '默认值',
	        name: 'defaultvalue',
            selectOnFocus:true,
	        xtype:'textfield'
	    },
	   
		{
	        fieldLabel: '注释',
	        name: 'comments',
            selectOnFocus:true,
	        xtype:'textfield'
	    },
	    {
	        fieldLabel: '排序',
	        name: 'sorted',
            selectOnFocus:true,
	        xtype:'numberfield'
	    },
	    {
	        fieldLabel: '修改原因',
	        name: 'reasons',
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
	        fieldLabel: '所属表',
	        name: 'tablemeta_id',
            hidden:true,
            selectOnFocus:true,
	        xtype:'textfield'
	    }
//	    {
//	        fieldLabel: '历史批次',
//	        name: 'history_id',
//	        xtype:'hiddenfield'
//	    }
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
				//var aa=formpanel.getForm().findField("ispk").getValue();
				//alert(aa);
				
				formpanel.updateRecord();
				//alert(formpanel.getForm().getRecord().get("ispk"));
				//return;
				var record=formpanel.getForm().getRecord();
				//record.set("ispk",formpanel.getForm().findField("ispk").getValue());
				record.set("nullable",formpanel.getForm().findField("nullable").getValue());
				//record.set("history_id",window.history_id);
				
				formpanel.fireEvent("saved",record);
				button.up('window').close();
//				record.save({
//					failure: function(record, operation) {
//				    },
//				    success: function(record, operation) {
//				    	
//				    	formpanel.fireEvent("saved",record);
//						button.up('window').close();
//						
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
