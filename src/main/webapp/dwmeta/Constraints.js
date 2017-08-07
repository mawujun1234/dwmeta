Ext.define("Ems.dwmeta.Constraints",{
	extend:"Ext.data.Model",
	fields:[
		{name:'type',type:'string'},
		{name:'status',type:'bool'},
		{name:'name',type:'string'},
		{name:'id',type:'string'},
		{name:'tablemeta_id',type:'string'}
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
			read:Ext.ContextPath+'/constraints/load.do',
			//load : Ext.ContextPath+'/constraints/load.do',
			create:Ext.ContextPath+'/constraints/create.do',
			update:Ext.ContextPath+'/constraints/update.do',
			destroy:Ext.ContextPath+'/constraints/destroy.do'
		}
	}
});