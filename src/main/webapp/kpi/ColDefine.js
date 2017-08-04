Ext.define("Ems.kpi.ColDefine",{
	extend:"Ext.data.Model",
	fields:[
		{name:'colname',type:'string'},
		{name:'name',type:'string'},
		{name:'coltype',type:'string'},
		{name:'definition',type:'string'},
		{name:'status',type:'string'},
		{name:'remark',type:'string'},
		{name:'colclassify_id',type:'string'},
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
			read:Ext.ContextPath+'/colDefine/load.do',
			//load : Ext.ContextPath+'/colDefine/load.do',
			create:Ext.ContextPath+'/colDefine/create.do',
			update:Ext.ContextPath+'/colDefine/update.do',
			destroy:Ext.ContextPath+'/colDefine/destroy.do'
		}
	}
});