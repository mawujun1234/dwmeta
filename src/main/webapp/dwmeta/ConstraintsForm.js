Ext.define('Ems.dwmeta.ConstraintsForm',{
	extend:'Ext.form.Panel',
	requires: [
	     'Ems.dwmeta.Constraints'
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
       var shows = Ext.create('Ext.data.Store', { fields: ['id','show'], data: [ {id: 0, show: 'Battlestar Galactica'}, {id: 1, show: 'Doctor Who'}, {id: 2, show: 'Farscape'}, {id: 3, show: 'Firefly'}, {id: 4, show: 'Star Trek'}, {id: 5, show: 'Star Wars: Christmas Special'} ] });
       me.items= [
		{
	        fieldLabel: '约束名称',
	        name: 'name',
            selectOnFocus:true,
	        xtype:'textfield'
	    },{
			fieldLabel: '约束类型',
			name: 'type',
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
			xtype:'combobox'
		},
        {
			xtype : 'tagfield',
			fieldLabel : '列',
			store : shows,
			displayField : 'show',
			valueField : 'id',
			queryMode : 'local',
			filterPickList : true
		},
        {
	        fieldLabel: '引用主表',
	        name: 'ref_table_id',
            hidden:false,
            selectOnFocus:true,
	        xtype:'textfield'
	    },
		{
	        fieldLabel: '引用的列',
	        name: 'column',
            selectOnFocus:true,
	        xtype:'textfield'
	    },
	    {
        	fieldLabel: '是否可用',
            name:'status',
            xtype: 'checkbox',
            cls: 'x-grid-checkheader-editor'
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
