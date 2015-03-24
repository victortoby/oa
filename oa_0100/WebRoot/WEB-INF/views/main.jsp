<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="resource.jsp"></jsp:include>
<title>后台管理系统</title>
		<style type="text/css">
		a{text-decoration:none;color:black;}
		body{background-color:#DDD;}
		</style>
        <script>
        $(function(){
         var _tabPanel=$("#main");
         freshAD();
         $(".easyui-accordion").find("a[link]").each(function(){
                    $(this).click(function(){
                        var url=$(this).attr("link");
                        var title=$(this).html();
				        if(url!=="#"&&url!==""){
					            if(!_tabPanel.tabs('exists',title)){
							        _tabPanel.tabs('add',{
							                               title:title,
							                               content:'<iframe src="'+url+'" style="padding:0;margin:0;border:0;width:100%;height:100%;"></iframe>',
							                               closable:true});
							        bindfresh(title);
						        }else{
							        _tabPanel.tabs('select',title);
						        }
					        }
				        });
                    });//end each
	   window.document.addTab=function(url,title){
	    var _tabPanel=$("#main");
	    if(!_tabPanel.tabs('exists',title)){
	    _tabPanel.tabs('add',{
				title:title,
				content:'<iframe src="'+url+'" style="padding:0;margin:0;border:0;width:100%;height:100%;"></iframe>',
				closable:true});
		bindfresh(title);
	    }else{
		_tabPanel.tabs('select',title);
	    }
            }//end window.document.addTab
        
        });//end ready
        function freshAD(){
        var AdHtml="<font color='red'>现在时间："+new Date().format("hh:mm:ss")+"&nbsp;&nbsp;今天："+getChineseCalendar()+"</font>";
        $("#opt_info").panel({title:AdHtml});
        setTimeout("freshAD()",500);
        }
        function goHome(){$("#main").tabs('select',"后台首页");}
        function wopen(url){window.open (url,'newwindow','height=800,width=1100,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no')}
        function bindfresh(title){
        /*双击刷新TAB选项卡*/
	    $(".tabs-inner").dblclick(function(){
	        var _ctab=$('#main').tabs('getTab',title);
	        var html=_ctab.html();
	        _ctab.html(html);
	    });//end $(".tabs-inner").dblclick
        }
</script>
</head>
<body class="easyui-layout" style="text-align:left">
<div region="north" style="height:52px;">
	<div class="easyui-layout" style="height:50px;">
		<div region="west" id="logo" style="height:48px;width:207px;background:#eee;padding:0;margin:0;" >
		<img style="height:45px;width:195px;padding:0;margin:0;border:0;"  src="images/logo.gif"/>
		</div>
		<div  region="center" style="height:48px;padding:0;margin:0;"  border="false" >
		    <a href="../" class="easyui-linkbutton" plain="true" icon="icon-next" target="_blank">网站首页</a>
		    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-home" onclick="goHome()">后台首页</a>
		    <a href="login.php?act=off" onclick="if(!confirm('你确定退出吗？')){return false;}" class="easyui-linkbutton" plain="true" icon="icon-undo" >退出系统</a>
		    <br/>
		    欢迎使用后台管理系统
		</div>
	</div>
</div>
			
		    <div region="west" split="false" icon="icon-info" title="后台系统菜单" style="width:208px;" >
					    <div class="easyui-accordion"  border="false">
						    <div title="系统管理" iconCls="icon-light" selected="true" style="overflow:auto;padding:10px;">
						    <ul class="easyui-tree">
						        <li state="open">
						            <span >网站文章管理</span>
						            <ul>
						                <li><span><a link="http://www.google.com.hk">添加内容</a></span></li>
						                <li><span><a link="http://www.baidu.com">内容管理</a></span></li>
						            </ul>
						        </li>
				                <li><span><a link="${ctx}/sysOrganization/init">组织机构管理</a></span></li>
						        <li state="closed">
						            <span>配置管理</span>
						            <ul>
						                <li><span><a link="http://www.google.com.hk">系统配置</a></span></li>
						                <li><span><a link="http://www.baidu.com">更新配置</a></span></li>
				                        <li><span><a link="http://www.google.com.hk">清空缓存</a></span></li>
						            </ul>
						        </li>
		                     	<li><span><a link="http://www.google.com.hk">生成视频代码</a></span></li>
						        <li><span><a link="http://www.baidu.com">服务器信息</a></span></li>
						        <li><span><a onclick="goHome()">系统首页</a></span></li>
						    </ul>
						    </div>
						    <div title="文件管理" iconCls="icon-folder" style="padding:10px;">
						    <ul class="easyui-tree">
						        <li><span><a link="http://www.google.com.hk">上传图片</a></span></li>
				                <li><span><a link="http://www.baidu.com">上传附件</a></span></li>
				                <li><span><a link="http://www.google.com.hk">管理上传文件(utf8)</a></span></li>
						        <li><span><a link="http://www.baidu.com">管理上传文件(zh_cn)</a></span></li>
						    </ul>
						    </div>
						</div>
	    </div>
		    
	   <div id="opt_info" border="false" region="center" title="小广告" >
			     <div id="main" class="easyui-tabs"  fit="true" border="false" plain="true">
					    <div title="后台首页" style="padding:10px;">
					      <iframe  frameborder="0" style="width:100%;height:100%;" src="http://www.baidu.com"></iframe>
					    </div>
			     </div>
		</div>
	   <div  region="south" style="text-align:center" >
         狂奔的蜗牛版权所有 技术支持QQ：672308444
		</div>
</body>
</html>
