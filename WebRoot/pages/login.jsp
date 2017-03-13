<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

	<link rel="stylesheet" type="text/css" href="css/common.css" />
	<script type="text/javascript" src="js/jquery-easyui-1.2.6/jquery-1.7.2.min.js"></script>
	<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.2.6/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.2.6/themes/icon.css" />
	<script type="text/javascript" src="js/jquery-easyui-1.2.6/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/commons.js"></script>
	<script type="text/javascript" src="js/com.js"></script>
	
	<LINK href="images/Default.css" type=text/css rel=stylesheet>
	<LINK href="images/xtree.css" type=text/css rel=stylesheet>
	<LINK href="images/User_Login.css" type=text/css rel=stylesheet>
	<style type="text/css">
		body {
			background:#3c7fb5 url(images/bg_login.jpg) repeat-x left top;
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
		}
		body,table,td,div {
			font-size: 12px;
			line-height: 24px;
		}
		.textfile {background:url(images/bg_login_textfile.gif)  no-repeat left top; padding: 0px 2px; height: 29px; width: 143px; border: 0; }
		.textfile1 {background:url(images/2121.jpg) no-repeat left top; padding: 0px 2px; height: 29px; width: 83px; border: 0; }
	</style>

</head>
<body>
	<table width="95" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td><img src="images/www.jpg" width="596" height="309" /></td>
		</tr>
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="99"><img src="images/login_06.jpg" width="99"
							height="139" /></td>
						<td background="images/bg_form.jpg">
							<form name="loginForm" id="myform1" method="post" action="manager/login.do">
								<table width="250" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td height="35" align="right">用户名：</td>
										<td><label> <input id="noId" name="name" type="text" class="textfile" />
										</label></td>
									</tr>
									<tr>
										<td height="35" align="right">密&nbsp;&nbsp;码：</td>
										<td><label> <input id="pswId" name="password"
												type="password" class="textfile" />
										</label></td>
									</tr>
									<tr>
										<td height="35" align="right">验证码：</td>
										<td>
											<table border="0">
												<tr>
													<td><label> <input id="verId" name="verify"
															type="text" class="textfile1" />
													</label></td>
													<td style="padding-left: 3px"><a
														href="javascript:refresh();"><img src="auth.jpg"
															width="55px" height="25px" id="authImg" /></a></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td height="35">&nbsp;</td>
										<td><label> <input type="submit" value="登录">
												<input type="reset" name="Submit2" value="重置" />
										</label></td>
									</tr>
									<tr>
										<td height="30">&nbsp;</td>
										<td><label> <input type="checkbox"
												name="autoLogin" value="auto" />
										</label> 记住密码</td>
									</tr>
								</table>
							</form>
						</td>
						<td width="98" align="right"><img src="images/login_08.jpg"
							width="98" height="139" /></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td><img src="images/bottom_login.jpg" width="596" height="39" />
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td align="center">版权所有：上海上讯信息有限公司</td>
		</tr>
	</table>
</body>
</html>