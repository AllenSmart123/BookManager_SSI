<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>上讯图书管理系统主界面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/common.css" />
	<script type="text/javascript" src="js/jquery-easyui-1.2.6/jquery-1.7.2.min.js"></script>
	<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.2.6/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.2.6/themes/icon.css" />
	<script type="text/javascript" src="js/jquery-easyui-1.2.6/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/ChineseCalendar.js"></script>
	<script type="text/javascript" src="js/jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/commons.js"></script>
	<script type="text/javascript" src="js/com.js"></script>
	
    <link href="css/default.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src='js/outlook2.js'> </script>

    <script type="text/javascript">
	 var _menus = {"menus":[
					{
						"menus":[{"menuname":"书籍管理","icon":"icon-nav","url":"pages/bookInfo.jsp"},
								{"menuname":"借阅排行榜","icon":"icon-add","url":"pages/borrowInfo.jsp"},
								{"menuname":"书籍借阅","icon":"icon-users","url":"pages/borrowDetailInfo.jsp"},
								{"menuname":"书籍归还","icon":"icon-role","url":"pages/giveBackInfo.jsp"},
								{"menuname":"读者管理","icon":"icon-set","url":"pages/readerInfo.jsp"},
								{"menuname":"系统日志","icon":"icon-log","url":"demo.html"}]
					},{
						"menus":[{"menuname":"员工列表","icon":"icon-nav","url":"demo.html"},
								{"menuname":"视频监控","icon":"icon-nav","url":"demo1.html"}]
					}
			]};

    </script>

</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
    <div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
		        background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
		        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
		        <span style="float:right; padding-right:20px;" class="head">欢迎 :<a id="scanInfo">${manager.name }(${ip})</a> <a id="updatePsw">修改密码</a> <a id="loginOut">安全退出</a></span>
		        <span style="padding-left:10px; font-size: 16px; float:left;"><img width="20" height="20" align="absmiddle" />上讯信息图书管理系统</span>
	</div>

    <div region="west" split="true" title="导航菜单" style="width:180px;" id="west">
		<div class="easyui-accordion" fit="true" border="false">
			<!--  导航内容 -->
					
		</div>
	
	</div>
	
	<div id="opt_info" border="false" region="center" title="小广告" >
		<div id="tt" class="easyui-tabs" fit=true style="width:500px;height:250px;"></div>  
    </div>
	
    <div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
	     <div class="footer" align="center">版权所有：上海上讯信息有限公司  网址:www.suninfo.com</div>
	</div>
	
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
			<div title="欢迎使用" style="padding:20px;overflow:hidden;" id="home">
				
			<h1>Welcome to jQuery UI!</h1>

			</div>
		</div>
    </div>
</body>
</html>