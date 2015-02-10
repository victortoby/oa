<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.bjgydx.graduate.model.SysOrganization"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>组织机构列表</title>
<jsp:include page="../resource.jsp"></jsp:include>
<script type="text/javascript">
/**
 * 组织机构表格
 */
$(function() {
	
	// 表格列数组
	var gridColumnsArray = [[
			{field: 'orgGuid', title: "<%=SysOrganization.ORG_GUID_VO%>", width:80},
		    {field: 'parentOrgGuid', title: '<%=SysOrganization.PARENT_ORG_GUID_VO%>',width:80},  
		    {field: 'orgName', title: '<%=SysOrganization.ORG_NAME_VO%>', width:100},  
		    {field: 'orgLevel', title: '<%=SysOrganization.ORG_LEVEL_VO%>', width:150}
		]];
	 
	//表格操作按钮
	var gridToolBarBtnArray = [{
			id:'sysOrganization_add',
			text: '新增',
			iconCls: 'icon-add',
			handler: function(){
				addMethod();
				}
		},'-',{
			id:'sysOrganization_edit',
			text:'编辑',
			iconCls: 'icon-edit',
			handler: function(){
				editMethod();
				}
		},'-',{
			id:'sysOrganization_remove',
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				alert('remove')
				}
	}];
	
	//表格参数
	var gridParams = {
			url : '${ctx}/sysOrganization/query' ,
			columns : gridColumnsArray,
			toolbar : gridToolBarBtnArray,
			height:500,
			pagination : true,
	};
	
	//创建表格
	var sysOrganizationDataGrid = createDataGrid($('#sysOrganizationTable'), gridParams);
});
	//新增方法
	function addMethod() {
		$('#sysOrganizationCreateDialog').dialog({    
		    title: '新增组织机构',    
		    width: 400,    
		    height: 200,    
		    closed: false,    
		    cache: false,    
		    href: '${ctx}/sysOrganization/sysOrganizationaddUI',    
		    modal: true,
		    
		});    
	}
	
	//编辑方法
	function editMethod(id) {
		$('#sysOrganizationCreateDialog').dialog({    
		    title: '编辑组织机构',    
		    width: 400,    
		    height: 200,    
		    closed: false,    
		    cache: false,    
		    href: '${ctx}/sysOrganization/sysOrganizationEditUI/'+id,    
		    modal: true,
		    
		});    
	}
</script>
</head>
<body>
    <table id="sysOrganizationTable"></table>
    <div id="sysOrganizationCreateDialog"></div>
</body>
</html>