Ext.define("Ems.dwmeta.DB",{
	extend:"Ext.data.Model",
	fields:[
		{name:'name',type:'string'},
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
			read:Ext.ContextPath+'/dB/load.do',
			//load : Ext.ContextPath+'/dB/load.do',
			create:Ext.ContextPath+'/dB/create.do',
			update:Ext.ContextPath+'/dB/update.do',
			destroy:Ext.ContextPath+'/dB/destroy.do'
		}
	}
});