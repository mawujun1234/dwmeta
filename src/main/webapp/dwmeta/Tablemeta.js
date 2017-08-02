Ext.define("Ems.dwmeta.Tablemeta",{
	extend:"Ext.data.Model",
	fields:[
		{name:'tablename',type:'string'},
		{name:'name',type:'string'},
		{name:'remark',type:'string'},
		{name:'id',type:'string'},
		{name:'classify_id',type:'string'}
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
			//,rootProperty:'root'
			//successProperty:'success',
			//totalProperty:'total'		
		},
		api:{
			read:Ext.ContextPath+'/tablemeta/load.do',
			//load : Ext.ContextPath+'/tablemeta/load.do',
			create:Ext.ContextPath+'/tablemeta/create.do',
			update:Ext.ContextPath+'/tablemeta/update.do',
			destroy:Ext.ContextPath+'/tablemeta/destroy.do'
		}
	}
});