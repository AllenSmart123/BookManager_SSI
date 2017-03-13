<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'branchStaff.jsp' starting page</title>
    
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
	<script type="text/javascript" src="js/jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/commons.js"></script>
	
	<script type="text/javascript"> 
		var editing ; //判断用户是否处于编辑状态 
		var flag  ;	  //判断新增和修改方法
		
		$(function(){
			$('#t_giveBack').datagrid({
				idField:'id' ,
				title:'书籍信息' , 
				url:'giveBack/getAll.do',
				fitColumns:true ,
				height:500,
				striped:true,
				rownumbers:true,
				remoteSort:false,
				frozenColumns:[[{field:'ck',width:50,checkbox:true}]],
				columns:[[
					{field:'readerId' ,title:'借阅者编号' ,width:80,align:'center'},
					{field:'readerName' ,title:'借阅者' ,width:80,align:'center'},
					{field:'bookId' ,title:'书籍编号' ,width:80,align:'center'},
					{field:'bookName' ,title:'书名' ,width:100,align:'center'},
					{field:'backTime' ,title:'归还时间'  ,sortable:true,width:130,align:'center'},
					{field:'operator' ,title:'操作人'  ,width:100,align:'center'}
				]] ,
				pagination: true, 
				pageSize: 10 ,
				pageList:[10,15,20,30],
				toolbar:[
					{
						text:'查询用户' ,
						iconCls:'icon-search' , 
						handler:function(){
						$('#lay').layout('expand','south');
						}
					}
				],
				onAfterEdit:function(index , record){
					$.post(flag=='add'?'book/saveBook.do':'book/updateBook.do' , record , function(result){
						if(result.status == 'ok'){
							$.messager.show({
								title:'提示信息',
								msg:'操作成功!'
							});
						}	
					});
				}
				
		});
			
		
			
		$('#searchbtn').click(function(){
			$('#t_giveBack').datagrid('load' ,serializeForm($('#mysearch')));
		});
		$('#clearbtn').click(function(){
			$('#keyWord').val("");
			$('#t_giveBack').datagrid('load' ,{});
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
	  <div id="lay" class="easyui-layout" style="width: 100%;height:100%" >
		
		<div region="south" title="用户查询" collapsed=true style="height:100px;" >
			<form id="mysearch" method="post">
					请输入关键字：<input name="selectValue"  id="keyWord"/>
					<select id="cc" name="selectName"  style="width:80px;">
						<option value="bookId">书籍编号</option>
						<option value="bookName">书籍名称</option>
					</select>
					<a id="searchbtn" class="easyui-linkbutton">查询</a> <a id="clearbtn" class="easyui-linkbutton">清空</a>
			</form>
		</div>
		<div region="center">
			<table id="t_giveBack"></table>
		</div>
		
	  </div> 
	</body>
</html>
