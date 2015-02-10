/**
 * 创建数据表格对象，默认自动分页加载数据
 * 
 * @param gridObj
 *            页面中定义的表格对象
 * @param gridParamsObj
 *            创建表格参数，必须包括url，columns参数
 * @returns
 */
function createDataGrid(gridObj,gridParamsObj) {
	var dataGridObj = gridObj.datagrid({
		url : gridParamsObj.url,
		fit : gridParamsObj.height != undefined ? false: true,
		height : gridParamsObj.height != undefined ?  gridParamsObj.height : 'auto',
		striped : true,
		singleSelect : gridParamsObj.singleSelect != undefined ? gridParamsObj.singleSelect : false,//默认为多选
		pagination : gridParamsObj.pagination != undefined ? gridParamsObj.pagination : false,//默认不显示底部分页栏
		pageSize : 20,
		queryParams : gridParamsObj.queryParams != undefined ? gridParamsObj.queryParams : {},
		rownumbers : true,
		fitColumns : true,
		remoteSort : false,
		columns : gridParamsObj.columns,
		idField : gridParamsObj.idField != undefined ? gridParamsObj.idField : null,
		toolbar : gridParamsObj.toolbar != undefined ? gridParamsObj.toolbar : null,
		
	});
	return dataGridObj;
}

function createDialog(optFlag, dialogObj, dialogParamsObj) {
	//操作窗口标题前缀
	var _titlePre = '';
	if (optFlag == 'edit') {
		_titlePre = '编辑';
	} else if (optFlag == 'view') {
		_titlePre = '查看';
	} else if (optFlag == 'add') {
		_titlePre = '新增';
	};
	var _url = dialogParamsObj.url;
	var _closeBtn = {
		id : 'main_button_id',
		iconCls : 'icon-cancel',
		text : '关闭',
		handler : function() {
			_selfDialog.dialog('close');
		}
	};
	var _buttons = [];
	if (dialogParamsObj.buttons != null && dialogParamsObj.buttons != undefined
			&& dialogParamsObj.buttons.length > 0) {
		$.each(dialogParamsObj.buttons, function(i, btnObj) {
			_buttons.push(btnObj);
		});
	}
	_buttons.push(_closeBtn);
	var _selfDialog = dialogObj.dialog({    
	    title : _titlePre + dialogParamsObj.title,    
	    width : dialogParamsObj.width != undefined ? dialogParamsObj.width :  600,    
	    height : dialogParamsObj.height != undefined ? dialogParamsObj.height : 400,
		maximizable : false,
		draggable : true,
	    cache : false,    
	    href :  _url,    
	    modal: true,
	    toolbar : dialogParamsObj.toolbar != undefined ? dialogParamsObj.toolbar : null,
		buttons: _buttons,
	});
	console.info(_selfDialog);
	return _selfDialog;
}