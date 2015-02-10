<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.bjgydx.graduate.model.SysOrganization"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>组织机构</title>
<jsp:include page="../resource.jsp"></jsp:include>
<script type="text/javascript">
	$(function() {
			
	});
</script>
</head>
<body>
	<form id="sysOrganizationForm" method="post">
		<div class="fitem">
			<label><%=SysOrganization.PARENT_ORG_GUID_VO%>:</label>
			<input name="parentOrgGuid" class="easyui-validatebox" required="true">
		</div>
		<div class="fitem">
			<label><%=SysOrganization.ORG_NAME_VO%>:</label>
			<input name="orgName" class="easyui-validatebox" required="true">
		</div>
	</form>
</body>
</html>