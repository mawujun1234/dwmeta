Ext.define("Ems.kpi.Kpi",{
	extend:"Ext.data.Model",
	fields:[
		{name:'code',type:'string'},
		{name:'name',type:'string'},
		{name:'definition',type:'string'},
		{name:'status',type:'string'},
		{name:'remark',type:'string'},
		{name:'kpi_type_id',type:'string'},
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
			read:Ext.ContextPath+'/kpi/load.do',
			//load : Ext.ContextPath+'/kpi/load.do',
			create:Ext.ContextPath+'/kpi/create.do',
			update:Ext.ContextPath+'/kpi/update.do',
			destroy:Ext.ContextPath+'/kpi/destroy.do'
		}
	}
});