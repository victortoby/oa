<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理系统</title>
<link rel="stylesheet" type="text/css"
	href="js/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="js/themes/icon.css">
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/locale/easyui-lang-zh_CN.js"></script>
<style type="text/css">
a {
	text-decoration: none;
	color: black;
}

body {
	background-color: #DDD;
}
</style>
<script>
	function check_f() {
		var name = document.login_f.name.value;
		var pwd = document.login_f.password.value;
		var code = document.login_f.ckcode.value;
		if (!name || !pwd || !code) {
			alert('请如入完整信息！');
			return false;
		}
		document.login_f.submit();
	}
</script>
</head>
<body>
	<div style="margin: 0 auto; width: 800px;">
		<div id="win" class="easyui-window" minimizable="false"
			maximizable="false" collapsible="false" title="用户登录"
			style="width: 300px; height: 220px;">
			<form action="${ctx}/main/login" method="post" style="padding: 10px 20px 10px 40px;" name="login_f">
				<p>
					用户名: <input class="easyui-validatebox" type="text" id="n"
						name="name" required="true" />
				</p>
				<p>
					密&nbsp;&nbsp;码: <input class="easyui-validatebox" id="pwd"
						type="password" name="password" required="true">
				</p>
				<p>
					验证码: <input class="easyui-validatebox" type="text" id="n"
						name="ckcode" required="true" style="width: 80px;" /><img
						src="images/logo.gif" width="70" height="20" />
				</p>
				<div style="padding: 5px; text-align: center;">
					<a href="#" class="easyui-linkbutton" icon="icon-ok"
						onclick="check_f()">登录</a> <a href="#" class="easyui-linkbutton"
						icon="icon-cancel" onclick="document.login_f.reset()">重置</a>
				</div>
			</form>
		</div>
	</div>

</body>
</html>
