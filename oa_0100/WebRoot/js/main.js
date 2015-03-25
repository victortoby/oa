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

/**
 * 保存弹出窗口中Form表单数据，并刷新列表
 * 
 * @param saveFromObj
 *            表单Form对象
 * @param gridObj
 *            要刷新的表格对象
 * @param winObj
 *            窗口对象
 * @param isTreeGrid
 *            是否为树表操作。true-treegrid；null/false/不填写-datagrid
 * @param submitSuccessProcess
 *            表单提交成功后的自定义处理方法
 * @param selfValidateMethod
 *            自定义验证处理方法，在表单项验证之后执行;此方法需要返回boolean类型值
 */
function saveWithWin(saveFromObj, gridObj, winObj, isTreeGrid, submitSuccessProcess, selfValidateMethod, maxFileSize) {
	var fileSize = 50;
	if (maxFileSize != undefined && maxFileSize != null && maxFileSize != '') {
		fileSize = maxFileSize;
	}
	saveFromObj.form('submit', {
		onSubmit: function(){
			
			//验证上传文件类型
			var retFlag = validateUploadFileExt($("input[type='file']"));
			if(!retFlag){
				return false;
			}
			
			$.messager.progress(); 
			var isValid = $(this).form('validate');
			if (isValid && selfValidateMethod != undefined && selfValidateMethod != null && selfValidateMethod != '') {
				isValid = eval(selfValidateMethod());
			}
			if (!isValid){
				$.messager.progress('close');	// hide progress bar while the form is invalid
			}
			return isValid;	// return false will stop the form submission
		},
		success : function(data) {
			
			//当存在上传文件并遇到异常时
			if(data.indexOf("FILEUPLOADERROR") != -1){
				$.messager.progress('close');
				$.messager.alert('操作提示信息', "文件不能超过50M，请重新选择！", 'warning');
				return ;
			}else if (data.indexOf("SINGLEFILEERROR") != -1){
				$.messager.progress('close');
				var retMsg ='';
				if(data.indexOf("ISDBSUPLOADFILETYPEERROR") !=-1) {
					retMsg = data.substring(data.indexOf("ISDBSUPLOADFILETYPEERROR")+24,data.length);
					$.messager.alert('操作提示信息', retMsg, 'warning');
				} else {
					retMsg = data.substring(data.indexOf("ISDBSUPLOADERROR")+16,data.length);
					$.messager.alert('操作提示信息', "单个文件大小不能超过"+fileSize+"M！\n" + retMsg, 'warning');
				}
				return ;				
			}
			
			var returnData = eval('(' + data + ')');
			$.messager.progress('close');
			$.messager.alert('操作提示信息', returnData.message, 'info');
			if (returnData.success) {
				// 采用弹出窗口方式
				winObj.dialog('close');
				if (submitSuccessProcess != undefined && submitSuccessProcess != null && submitSuccessProcess != '') {
					eval(submitSuccessProcess());
				}
				if (gridObj != null) {
					if (isTreeGrid != undefined && isTreeGrid == true) {
						gridObj.treegrid('reload');
					} else {
						gridObj.datagrid('load');
					}
				}
			}
		}
	});
};

//允许上传的文件类型（全部用小写）
var allowExt = "doc,docx,rar,zip,xls,xlsx,xml,png,jpg,jpeg,gif";
function validateUploadFileExt(fileArr){
	var allNullFlag = true;
	var retFlag = false;
	var errExtMsg = "";
	if(fileArr == null){
		return true;
	}
	if(fileArr != null){
		for(var index = 0; index < fileArr.length; index++){
			if(fileArr[index].value != null && fileArr[index].value != ""){
				allNullFlag = false;
				break;
			}
		}
		//当所有文件元素为空时，则返回true
		if(allNullFlag){
			return true;
		}	
		
		var alwExtArr = allowExt.split(",");
		for(var index = 0; index < fileArr.length; index++){
			if(fileArr[index].value != null && fileArr[index].value != ""){
				var fileVal = fileArr[index].value;
				var ext = fileVal.substring(fileVal.lastIndexOf(".")+1,fileVal.length);
				for(var ind = 0; ind < alwExtArr.length; ind++){
					//小写
					if(ext.toLowerCase() == alwExtArr[ind]){
						retFlag = true;
						break;
					}
				}
				if(!retFlag){
					errExtMsg = "不允许上传"+ext+"类型的文件！";
					break;
				}
			}
		}
		if(errExtMsg != ""){
			$.messager.alert('操作提示信息', errExtMsg, 'warning');
		}
	}
	return retFlag;
}