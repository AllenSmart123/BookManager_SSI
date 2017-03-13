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
		var flag;	  //判断新增和修改方法
		
		$(function(){
			$('#t_reader').datagrid({
				idField:'id' ,
				title:'书籍信息' , 
				url:'reader/getAll.do',
				fitColumns:true ,
				height:500,
				striped:true,
				rownumbers:true,
				frozenColumns:[[
					{
						field:'ck',
						width:50,
						checkbox:true
					}
				]],
				columns:[[
					{
						field:'readerId' ,
						title:'借阅者编号' ,
						width:80,
						align:'center'
					},{
						field:'readerName' ,
						title:'姓名' ,
						width:100,
						align:'center',
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'必填!'
							}
						}
					},{
						field:'sex' ,
						title:'性别'  ,
						width:80,
						align:'center',
						//hidden:true,
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'必填!'
							}
						}

					},{
						field:'birthday' ,
						title:'出生日期'  ,
						width:100,
						align:'center',
						editor:{
							type:'datebox' ,
							options:{
								required:true , 
								missingMessage:'必填!'
							}
						}
					},{
						field:'identityNum' ,
						title:'身份证号'  ,
						width:130,
						align:'center',
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'必填!'
							}
						}

					},{
						field:'readerType' ,
						title:'类型'  ,
						width:100,
						align:'center',
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'必填!'
							}
						}
					},{
						field:'canBorrowNum' ,
						title:'可借几本？' ,
						width:100,
						align:'center',
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'必填!'
							}
						}
					},{
						field:'phone' ,
						title:'联系方式' ,
						width:100,
						align:'center',
						editor:{
							type:'validatebox' ,
							options:{
								required:true, 
								missingMessage:'出版时间!',
								editable:true
							}
						}
					},{
						field:'email' ,
						title:'邮件' ,
						width:130,
						align:'center',
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'必填!'
							}
						}
					},{
						field:'registerDate' ,
						title:'注册日期' ,
						width:100,
						align:'center',
						editor:{
							type:'datebox' ,
							options:{
								required:true , 
								missingMessage:'必填!'
							}
						}
					},{
						field:'remark' ,
						title:'备注' ,
						width:100,
						align:'center',
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'必填!'
							}
						}
					} 
				]] ,
				
				pagination: true , 
				pageSize: 10 ,
				pageList:[10,15,20,30],
				toolbar:[
							{
								text:'新增用户' ,
								iconCls:'icon-add' , 
								
								handler:function(){
								
								if(editing == undefined){
										flag = 'add';
										//1 先取消所有的选中状态
										$('#t_reader').datagrid('unselectAll');
										//2追加一行
										$('#t_reader').datagrid('appendRow',{appropriation:''});
										//3获取当前页的行号
										editing = $('#t_reader').datagrid('getRows').length -1;
										//4开启编辑状态								
										$('#t_reader').datagrid('beginEdit', editing);
									}
								}
							},	{
								text:'修改用户' ,
								iconCls:'icon-edit' , 
								handler:function(){
								var arr = $('#t_reader').datagrid('getSelections');
									if(arr.length != 1){
										$.messager.show({
											title:'提示信息',
											msg:'只能选择一条记录进行修改!' 
										});
									} else {
										if(editing == undefined){
											flag = 'edit';
											//根据行记录对象获取该行的索引位置
											editing = $('#t_reader').datagrid('getRowIndex' , arr[0]);
											//开启编辑状态
											$('#t_reader').datagrid('beginEdit',editing);
											//ids1 += arr[0].branchNo;
											//alert("我是修改");							
										}
									}
								
								}
							},{
								text:'保存',
								iconCls:'icon-save' , 
								handler:function(){
									//保存之前进行数据的校验 , 然后结束编辑并师傅编辑状态字段 
									if(flag == 'add'){
										if($('#t_reader').datagrid('validateRow',editing)){
											$('#t_reader').datagrid('endEdit', editing);
											editing = undefined;
										}
									}else{
										if($('#t_reader').datagrid('validateRow',editing)){
											$('#t_reader').datagrid('endEdit', editing);
											editing = undefined;
										}
									}
								}
							},{
								text:'删除用户' ,
								iconCls:'icon-remove' , 
								handler:function(){
								 var arr = $('#t_reader').datagrid('getSelections');
									if(arr.length <= 0 ){
										$.messager.show({
											title:'提示信息',
											msg:'请选择进行删除操作!'
										});											
									} else {
										$.messager.confirm('提示信息' , '确认删除?' , function(r){
											if(r){
												var ids = '';
												for(var i = 0 ; i < arr.length ; i++){
													ids += arr[i].readerId + ',';
												}
												ids = ids.substring(0,ids.length-1);
												
												$.post('reader/deleteReader.do' , {ids:ids},function(result){
													    $('#t_reader').datagrid('reload');
														$.messager.show({
															title:'提示信息',
															msg:'操作成功!'
														});
												});
												
											} else {
												 return ;
											}
										});
									}
								}
							},	{
								text:'查询用户' ,
								iconCls:'icon-search' , 
								handler:function(){
								$('#lay').layout('expand','south');
								}
							},{
								text:'取消' ,
								iconCls:'icon-cancel' , 
								handler:function(){
								$('#t_reader').datagrid('rejectChanges');
									editing = undefined;
								}
							}
			],
			onAfterEdit:function(index , record){
				$.post(flag=='add'?'reader/saveReader.do':'reader/updateReader.do' , record , function(result){
					if(result.status == 'ok'){
						$.messager.show({
							title:'提示信息',
							msg:'操作成功!'
						});
					}	
				});
				$('#t_reader').datagrid('reload');
			}		
		});
			
		flag = $('#flag').val();
		if(flag == 2){
			$('div.datagrid-toolbar a').eq(0).hide();
			$('div.datagrid-toolbar a').eq(1).hide();
			$('div.datagrid-toolbar a').eq(2).hide();
			$('div.datagrid-toolbar a').eq(3).hide();
			$('div.datagrid-toolbar a').eq(5).hide();
		}
		
			
			
			
		$('#searchbtn').click(function(){
			$('#t_reader').datagrid('load' ,serializeForm($('#mysearch')));
		});
		$('#clearbtn').click(function(){
			$('#keyWord').val("");
			$('#t_reader').datagrid('load' ,{});
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
					<select id="cc" name="selectName"  style="width:100px;">
						<option value="readerId">借阅者编号</option>
						<option value="readerName">借阅者名字</option>
					</select>
			</form>
		</div>
		<div region="center">
			<table id="t_reader"></table>
			<input id="flag" type="hidden" name="flag" value="${manager.grade }"/>
		</div>
		
	  </div> 

	</body>
</html>
