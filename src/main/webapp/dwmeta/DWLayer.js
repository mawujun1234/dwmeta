Ext.define("Ems.dwmeta.DWLayer",{
	extend:"Ext.data.Model",
	fields:[
		{name:'name',type:'string'},
		{name:'jdbc_driver',type:'string'},
		{name:'jdbc_url',type:'string'},
		{name:'jdbc_username',type:'string'},
		{name:'jdbc_password',type:'string'},
		{name:'remark',type:'string'},
		{name:'id',type:'string'}
	],
	proxy:{
		type:'ajax',
		actionMethods: { read: 'POST' },
		timeout :600000,
		headers:{ 'Accept':'application/json;'},
		writer:{
			type:'json',
			writeRecordId:true,
			writeAllFields:true
		},
		reader:{
			type:'json'
			///rootProperty:'root',
			//successProperty:'success',
			//totalProperty:'total'		
		},
		api:{
			read:Ext.ContextPath+'/dWLayer/load.do',
			//load : Ext.ContextPath+'/dWLayer/load.do',
			create:Ext.ContextPath+'/dWLayer/create.do',
			update:Ext.ContextPath+'/dWLayer/update.do',
			destroy:Ext.ContextPath+'/dWLayer/destroy.do'
		}
	}
});