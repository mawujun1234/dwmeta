Ext.define('Ems.dwmeta.TableTabPanel',{
	extend:'Ext.tab.Panel',
	requires: [

	],
	
	
	autoDestroy : false,
	tabPosition : 'top',
	plugins : [{

		ptype : 'tabclosemenu',
		closeAllTabsText : '关闭所有',
		closeOthersTabsText : '关闭其他',
		closeTabText : '关闭'
	}],
	classify_id:null,
	initComponent: function(){
		var me = this;

//		me.items=[{
//            title: 'Home',
//            id:'aaaaa',
//            iconCls: 'icon-table',
//            closable:true,
//            closeAction:'destroy',
//            html: 'Home Screen'
//        }];
		

		me.callParent();
	},
	createTablemeta:function(id,text){
		var me=this;
		var tabs=me.items;
	    		
	    for(var i=0;i<tabs.length;i++){
	    	var tab=tabs.items[i];
	    	if(tab.tablemeta_id==id){
	    		me.setActiveTab(tab);
	    		return;
	    	}
	    }

	    var tablemetaPanel=Ext.create('Ems.dwmeta.TablePanel',{
	    	title: text,
            tablemeta_id:id,
            iconCls: 'icon-table',
            //layout:'fit',
            closable:true
	    });
	    tablemetaPanel.loadTablemeta(id);
	    me.setActiveTab(me.add(tablemetaPanel));
//		me.setActiveTab(me.add({
//            title: text,
//            itemId:id,
//            iconCls: 'icon-table',
//            layout:'fit',
//            closable:true,
//            //closeAction:'hide',
//            items:[tablemetaPanel]
//        }));
				

	}
});