Ext.define("Ems.dwmeta.Columnmeta",{
	extend:"Ext.data.Model",
	fields:[
		{name:'colname',type:'string'},
		{name:'name',type:'string'},
		{name:'coltype',type:'string'},
		{name:'coltype1',type:'string'},
		{name:'collen',type:'string'},
		{name:'ispk',type:'bool'},
		{name:'nullable',type:'bool'},
		{name:'defaultvalue',type:'string'},
		{name:'comments',type:'string'},
		{name:'id',type:'string'},
		{name:'sorted',type:'int'},
		{name:'tablemeta_id',type:'string'},
		{name:'history_id',type:'string'},
		{name:'reasons',type:'string'}
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
			read:Ext.ContextPath+'/columnmeta/load.do',
			//load : Ext.ContextPath+'/columnmeta/load.do',
			create:Ext.ContextPath+'/columnmeta/create.do',
			update:Ext.ContextPath+'/columnmeta/update.do',
			destroy:Ext.ContextPath+'/columnmeta/destroy.do'
		}
	}
});