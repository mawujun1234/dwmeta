Ext.define('Ems.dwmeta.DWLayerForm',{
	extend:'Ext.form.Panel',
	requires: [
	     'Ems.dwmeta.DWLayer'
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
	        fieldLabel: '名称',
	        name: 'name',
            allowBlank: false,
            afterLabelTextTpl: Ext.required,
            blankText:"名称不允许为空",
            selectOnFocus:true,
	        xtype:'textfield'
	    },
		{
	        fieldLabel: '驱动程序',
	        name: 'jdbc_driver',
            allowBlank: false,
            afterLabelTextTpl: Ext.required,
            blankText:"驱动程序不允许为空",
            selectOnFocus:true,
	        xtype:'textfield'
	    },
		{
	        fieldLabel: '连接地址',
	        name: 'jdbc_url',
            allowBlank: false,
            afterLabelTextTpl: Ext.required,
            blankText:"连接地址不允许为空",
            selectOnFocus:true,
	        xtype:'textfield'
	    },
		{
	        fieldLabel: '账号',
	        name: 'jdbc_username',
            allowBlank: false,
            afterLabelTextTpl: Ext.required,
            blankText:"账号不允许为空",
            selectOnFocus:true,
	        xtype:'textfield'
	    },
		{
	        fieldLabel: '密码',
	        name: 'jdbc_password',
            allowBlank: false,
            afterLabelTextTpl: Ext.required,
            blankText:"密码不允许为空",
            selectOnFocus:true,
	        xtype:'textfield'
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
	    },
	    {
	        fieldLabel: 'db_id',
	        name: 'db_id',
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
						button.up('window').close();
				    }
				});			
				
				}
			},{
				text : '测试连接',
				iconCls:'icon-bolt',
				handler : function(button){
					var formpanel = button.up('form');
					formpanel.testConn();
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
	},
	testConn:function(){
		var me=this;
		me.updateRecord();
		var record=me.getForm().getRecord();
		Ext.Ajax.request({
			headers:{ 'Accept':'application/json;'},
			url:Ext.ContextPath+'/dWLayer/testConn.do',
			params:{
				jdbc_driver:record.get("jdbc_driver"),
				jdbc_url:record.get("jdbc_url"),
				jdbc_username:record.get("jdbc_username"),
				jdbc_password:record.get("jdbc_password")
			},
			method:'POST',
			success:function(response){
				var obj=Ext.decode(response.responseText);
				//alert(obj.success);
				if(obj.success==false){
					Ext.Msg.alert("消息",obj.msg);
				} else {
					Ext.Msg.alert("消息","测试连接成功!");
				}
			}
		
		});
	}
});
