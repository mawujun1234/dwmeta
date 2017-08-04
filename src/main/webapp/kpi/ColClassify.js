Ext.define("Ems.kpi.ColClassify",{
	extend:"Ext.data.Model",
	fields:[
		{name:'name',type:'string'},
		{name:'remark',type:'string'},
		{name:'id',type:'string'},
		{name:'deleted',type:'bool'},
		{name:'db_id',type:'string'}
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
			read:Ext.ContextPath+'/colClassify/load.do',
			//load : Ext.ContextPath+'/colClassify/load.do',
			create:Ext.ContextPath+'/colClassify/create.do',
			update:Ext.ContextPath+'/colClassify/update.do',
			destroy:Ext.ContextPath+'/colClassify/destroy.do'
		}
	}
});