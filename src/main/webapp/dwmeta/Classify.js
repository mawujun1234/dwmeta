Ext.define("Ems.dwmeta.Classify",{
	extend:"Ext.data.Model",
	fields:[
		{name:'name',type:'string'},
		{name:'remark',type:'string'},
		{name:'id',type:'string'},
		{name:'dwlayer_id',type:'string'},
		{name:'parent_id',type:'string'}
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
			read:Ext.ContextPath+'/classify/load.do',
			//load : Ext.ContextPath+'/classify/load.do',
			create:Ext.ContextPath+'/classify/create.do',
			update:Ext.ContextPath+'/classify/update.do',
			destroy:Ext.ContextPath+'/classify/destroy.do'
		}
	}
});