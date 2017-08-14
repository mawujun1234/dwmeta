Ext.define("Ems.dwmeta.History",{
	extend:"Ext.data.Model",
	fields:[
		{name:'operateTime',type:'string'},
		{name:'operater',type:'string'},
		{name:'intiactive',type:'bool'},
		{name:'sql_content',type:'string'},
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
			read:Ext.ContextPath+'/history/load.do',
			//load : Ext.ContextPath+'/history/load.do',
			create:Ext.ContextPath+'/history/create.do',
			update:Ext.ContextPath+'/history/update.do',
			destroy:Ext.ContextPath+'/history/destroy.do'
		}
	}
});