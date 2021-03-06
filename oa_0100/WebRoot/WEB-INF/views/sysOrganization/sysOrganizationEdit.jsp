<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.bjgydx.graduate.model.SysOrganization"%>

<form id="sysOrganizationForm" method="post" action="${ctx}/sysOrganization/saveOrUpdate">
	<table>
		<tr>
			<th><label><%=SysOrganization.PARENT_ORG_GUID_VO%>:</label></th>
			<td><input name="parentOrgGuid" class="easyui-validatebox" data-options="required : true"></td>
		</tr>
		<tr>
			<th><label><%=SysOrganization.ORG_NAME_VO%>:</label></th>
			<td><input name="orgName" class="easyui-validatebox" data-options="required : true, validType : ['length[0,20]']"></td>
		</tr>
	</table>
</form>
