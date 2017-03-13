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
	<script type="text/javascript">

        function showSubMenu(url, title, menuCategory, defaultIcon) {
            if (defaultIcon == null || defaultIcon == "") {
                defaultIcon = "icon-table";
            }
            addTab(title, url, "icon " + defaultIcon);
            Clearnav();
            if (menuCategory != "") {
                addNav(_menus[menuCategory]);
            }
        }

		function freshAD(){
			var AdHtml="<font color='red'>现在时间："+new Date().format("hh:mm:ss")+"&nbsp;&nbsp;今天："+getChineseCalendar()+"</font>";
			$("#opt_info").panel({title:AdHtml});
	        setTimeout("freshAD()",1000);
	     }
	
		$(function(){
			freshAD();
			
			//修改密码
			$('#updatePsw').click(function(){
				$('#updatePswWin').dialog('open');
				$('#myform').get(0).reset();
			});
			//查看个人信息
			$('#scanInfo').click(function(){
				$('#ScanPersonalWin').dialog('open');
				$('#myform').get(0).reset();
			});
			
			//退出系统登录
			$('#loginOut').click(function() {
                $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(result) {
                    if (result) {
	                   	$.ajax({
	                   		type: 'post' ,
	   						url:'manager/clear.do',
	   						dataType:'json',
	   						success:function(data){
	   							if(data.status == 'ok'){
	   								location.href = '/BookManager_V1/login.jsp';
	   							}
	   						} 
	                   	});
                    }
                });
            })
			
			//提交表单方法
			$('#btn1').click(function(){
				if($('#myform').form('validate')){
					$.ajax({
						type: 'post' ,
						url:'StaffServlet.action?method=updatePassword',
						cache:false ,
						data:$('#myform').serialize() ,
						success:function(result){
							$('#mydialog').dialog('close');
							$.messager.show({
								title:'提示信息!' ,
								msg:'数据验证成功!'
							});
						} 
					});
				} else {
					$.messager.show({
						title:'提示信息!' ,
						msg:'数据验证不通过,不能修改!'
					});
				}
			});
	
			$('a[title]').click(function(){
					var src = $(this).attr('title');
					var title = $(this).html();
					if($('#tt').tabs('exists' ,title)){
						$('#tt').tabs('select',title);
					} else {
						$('#tt').tabs('add',{   
						    title:title,   
						    content:'<iframe frameborder=0 style=width:100%;height:100% src='+ src +' ></iframe>',   
						    closable:true  
						});  
					}
				});
			});
			
	
		//js方法：序列化表单 			
		function serializeForm(form){
			var obj = {};
			$.each(form.serializeArray(),function(index){
				if(obj[this['name']]){
					obj[this['name']] = obj[this['name']] + ','+this['value'];
				} else {
					obj[this['name']] =this['value'];
				}
			});
			return obj;
		}
	</script>
 
  </head>
  
  <body>
		<div id="cc" class="easyui-layout" fit=true style="width:100%;height:100%;">  
		    <!-- <div region="north" title="上讯信息图书管理系统"  split="false" style="height:100px;">
			</div>  --> 
			<div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
		        background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
		        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
		        <span style="float:right; padding-right:20px;" class="head">欢迎 :<a id="scanInfo">${manager.name }(${ip})</a> <a id="updatePsw">修改密码</a> <a id="loginOut">安全退出</a></span>
		        <span style="padding-left:10px; font-size: 16px; float:left;"><img width="20" height="20" align="absmiddle" />上讯信息图书管理系统</span>
		    </div>
			
		   <div region="west"  iconCls="icon-ok" split="true" title="导航菜单管理" style="width:200px;">
				<div id="aa" class="easyui-accordion" style="position: absolute; top: 27px; left: 0px; right: 0px; bottom: 0px;" >  				 
				    <div title="书籍管理" selected="true" style="overflow:auto;padding:10px;">
				    	<ul>
				    		<li><a title="pages/bookInfo.jsp" >书籍管理</a></li>
				    		<li><a title="pages/borrowInfo.jsp" >借阅排行榜</a></li>
				    		<li><a title="pages/borrowDetailInfo.jsp" >书籍借阅</a></li>
				    		<li><a title="pages/giveBackInfo.jsp" >书籍归还</a></li>
				    	</ul>
				    </div>
					<div title="读者管理" style="padding:10px;">
				    	<ul>
				    		<li><a title="pages/readerInfo.jsp" >读者管理</a></li>
				    		<li><a title="pages/testDemo.jsp" >借阅排行榜</a></li>
				    		<li><a title="pages/bookInfo.jsp" >书籍借阅</a></li>
				    		<li><a title="pages/bookInfo.jsp" >书籍归还</a></li>
				    	</ul>
				    </div>  
				</div>  
		    </div>
		    
		    <div id="opt_info" border="false" region="center" title="小广告" >
				<div id="tt" class="easyui-tabs" fit=true style="width:500px;height:250px;"></div>  
		    </div>
		     
		    <div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
		        <div class="footer" align="center">版权所有：上海上讯信息有限公司  网址:www.suninfo.com</div>
		    </div>
		    
			<!-- 修改密码 -->
			<div id="updatePswWin" title="修改密码" modal=true  draggable=false class="easyui-dialog" closed=true style="width:300px;">
		     <form id="myform" action="" method="post">	    		
					<table>
						<tr>
							<td>旧密码:</td>
							<td><input id="old" type="password" name="oldPassword" value="" /></td>
						</tr>
						<tr>
							<td>新密码:</td>
							<td><input id="new" type="password" name="newPassword" value="" /></td>
						</tr>	    		
						<tr>
							<td>确认新密码:</td>
							<td><input id="psw" type="password"  name="password" value="" /></td>
						</tr>
				
						<tr align="center">
							<td colspan="2">
								<a id="btn1" class="easyui-linkbutton">确定</a>
								<a id="btn2" class="easyui-linkbutton">关闭</a>
							</td>
						</tr>   					 					    					    					    					    					    					    					    					
					</table>
			  </form>
			</div> 
			
			<!-- 查看个人休息 -->
			<div id="ScanPersonalWin" title="个人信息" modal=true  draggable=false class="easyui-dialog" closed=true style="width:300px;">
		     <form id="myform2" action="" method="post">	    		
					<TABLE>
						<tr>
							<td>员工编号:</td>
							<td><input  TYPE="text" NAME="staffNo" VALUE="" readonly="readonly" /></td>
						</tr>
						<tr>
							<td>员工姓名:</td>
							<td><input TYPE="text" NAME="staffName" VALUE="" readonly="readonly"/></td>
						</tr>
						<tr align="center">
							<td colspan="2">
								<a id="btn22" class="easyui-linkbutton">关闭</a>
							</td>
						</tr>   					 					    					    					    					    					    					    					    					
					</TABLE>
			  </form>
			</div>   
		    
		</div>  

  </body>
</html>
