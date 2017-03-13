<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统登录</title>
<link href="css/login.css" rel="stylesheet" rev="stylesheet" type="text/css" media="all" />
<link href="css/demo.css" rel="stylesheet" rev="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/jquery1.42.min.js"></script>
<script type="text/javascript" src="js/jquery.SuperSlide.js"></script>
<script type="text/javascript" src="js/Validform_v5.3.2_min.js"></script>

<script>
$(function(){

$(".i-text").focus(function(){
$(this).addClass('h-light');
});

$(".i-text").focusout(function(){
$(this).removeClass('h-light');
});

$("#username").focus(function(){
 var username = $(this).val();
 if(username=='输入账号'){
 $(this).val('');
 }
});

$("#username").focusout(function(){
 var username = $(this).val();
 if(username==''){
 $(this).val('输入账号');
 }
});


$("#password").focus(function(){
 var username = $(this).val();
 if(username=='输入密码'){
 $(this).val('');
 }
});


$("#yzm").focus(function(){
 var username = $(this).val();
 if(username=='输入验证码'){
 $(this).val('');
 }
});

$("#yzm").focusout(function(){
 var username = $(this).val();
 if(username==''){
 $(this).val('输入验证码');
 }
});



$(".registerform").Validform({
	tiptype:function(msg,o,cssctl){
		var objtip=$(".error-box");
		cssctl(objtip,o.type);
		objtip.text(msg);
	},
	ajaxPost:true
});

});




</script>


</head>

<body>


<div class="header">
  <h1 class="headerLogo"><a title="后台管理系统" target="_blank" href="#"><img alt="logo" src="images/logo3.png"></a></h1>
	<div class="headerNav">
		<a target="_blank" href="http://www.suninfo.com/">上讯官网</a>
		<a target="_blank" href="http://www.suninfo.com/list-25.html">关于上讯</a>
		<a target="_blank" href="#">开发团队</a>
		<a target="_blank" href="#">意见反馈</a>
		<a target="_blank" href="#">帮助</a>	
	</div>
</div>

<div class="banner">

<div class="login-aside">
  <div id="o-box-up"></div>
  <div id="o-box-down"  style="table-layout:fixed;">
   <div class="error-box"></div>
   
   <form name="loginForm" id="myform1" method="post" action="manager/login.do">
	  <div class="fm-item">
		   <label for="logonId" class="form-label">上讯图书系统登陆：</label>
		   <input type="text" value="输入账号" maxlength="100" name="name" id="username" class="i-text" ajaxurl="demo/valid.jsp"  datatype="s6-18" errormsg="用户名至少6个字符,最多18个字符！"  >    
	       <div class="ui-form-explain"></div>
	  </div>
  
	  <div class="fm-item">
		   <label for="logonId" class="form-label">登陆密码：</label>
		   <input type="password" value="" maxlength="100" name="password" id="password" class="i-text" datatype="*6-16" nullmsg="请设置密码！" errormsg="密码范围在6~16位之间！">    
	       <div class="ui-form-explain"></div>
	  </div>
  
	  <div class="fm-item pos-r">
		   <label for="logonId" class="form-label">验证码</label>
		   <input type="text" value="输入验证码" maxlength="100" id="yzm" class="i-text yzm" nullmsg="请输入验证码！" >    
	       <div class="ui-form-explain"><img src="images/yzm.jpg" class="yzm-img" /></div>
	  </div>
  
	  <div class="fm-item">
		   <label for="logonId" class="form-label"></label>
		   <input type="submit" value="" tabindex="4" id="send-btn" class="btn-login"> 
	       <div class="ui-form-explain"></div>
	  </div>
  
  </form>
  
  </div>

</div>

	<div class="bd">
		<ul>
			<li style="background:url(themes/theme-pic1.jpg) #CCE1F3 center 0 no-repeat;"><a target="_blank" href="#"></a></li>
			<li style="background:url(themes/theme-pic2.jpg) #BCE0FF center 0 no-repeat;"><a target="_blank" href="#"></a></li>
		</ul>
	</div>

	<div class="hd"><ul></ul></div>
</div>
<script type="text/javascript">jQuery(".banner").slide({ titCell:".hd ul", mainCell:".bd ul", effect:"fold",  autoPlay:true, autoPage:true, trigger:"click" });</script>


<div class="banner-shadow"></div>

<div class="footer">
   <p>版权所有：上海上讯信息有限公司    Copyright 2016-2017 HUARUAN Corporation, All Rights Reserved</p>
</div>

</body>
</html>
