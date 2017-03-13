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
			$('#t_book').datagrid({
				idField:'id' ,
				title:'书籍信息' , 
				url:'book/getAll.do',
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
						field:'bookId' ,
						title:'书籍编号' ,
						width:80,
						align:'center'
					},{
						field:'bookName' ,
						title:'名称' ,
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
						field:'barcode' ,
						title:'条形码'  ,
						width:130,
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
						field:'author' ,
						title:'作者'  ,
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
						field:'ISBN' ,
						title:'出版社'  ,
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
						field:'price' ,
						title:'价格(元)'  ,
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
						field:'page' ,
						title:'页数' ,
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
						field:'publishTime' ,
						title:'出版时间' ,
						width:100,
						align:'center',
						editor:{
							type:'datebox' ,
							options:{
								required:true, 
								missingMessage:'出版时间!',
								editable:true
							}
						}
					},{
						field:'bookType' ,
						title:'类型' ,
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
						field:'borrowDays' ,
						title:'可借天数' ,
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
						field:'ownership' ,
						title:'所有者' ,
						width:100,
						align:'center',
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'必填!'
							}
						}
						/* formatter:function(value,data,index){
							if(value != null){
								return '<span style=color:red; >离职</span>';
							}else {
								return '<span style=color:blue; >在职</span>';
							}
						} */
					},{
						field:'bookcaseRow' ,
						title:'第几列？'  ,
						width:50,
						align:'center',
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'必填!'
							}
						}
					},{
						field:'bookcaseColumn' ,
						title:'第几排？'  ,
						width:50,
						align:'center',
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'必填!'
							}
						}
					},{
						field:'bookcaseFloor' ,
						title:'第几层？'  ,
						width:50,
						align:'center',
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'必填!'
							}
						}
					},{
						field:'bookHouse' ,
						title:'现有库存'  ,
						width:50,
						align:'center',
						editor:{
							type:'validatebox' ,
							options:{
								required:true , 
								missingMessage:'必填!'
							}
						}
					},{
						field:'opt',title:'操作',width:50,align:'center',  
			            formatter:function(value,rec){  
			                var btn = '<a class="editcls" onclick="borrowBook(\''+rec.bookId+'\')" href="javascript:void(0)">编辑</a>';  
			                return btn;  
			            }  
			        }  
				]] ,
				onLoadSuccess:function(data){  
			        $('.editcls').linkbutton({text:'借阅',plain:true,iconCls:'icon-redo'});
			    },
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
										$('#t_book').datagrid('unselectAll');
										//2追加一行
										$('#t_book').datagrid('appendRow',{appropriation:''});
										//3获取当前页的行号
										editing = $('#t_book').datagrid('getRows').length -1;
										//4开启编辑状态								
										$('#t_book').datagrid('beginEdit', editing);
									}
								}
							},	{
								text:'修改用户' ,
								iconCls:'icon-edit' , 
								handler:function(){
								var arr = $('#t_book').datagrid('getSelections');
									if(arr.length != 1){
										$.messager.show({
											title:'提示信息',
											msg:'只能选择一条记录进行修改!' 
										});
									} else {
										if(editing == undefined){
											flag = 'edit';
											//根据行记录对象获取该行的索引位置
											editing = $('#t_book').datagrid('getRowIndex' , arr[0]);
											//开启编辑状态
											$('#t_book').datagrid('beginEdit',editing);
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
										if($('#t_book').datagrid('validateRow',editing)){
											$('#t_book').datagrid('endEdit', editing);
											editing = undefined;
										}
									}else{
										if($('#t_book').datagrid('validateRow',editing)){
											$('#t_book').datagrid('endEdit', editing);
											editing = undefined;
										}
									}
								}
							},{
								text:'删除用户' ,
								iconCls:'icon-remove' , 
								handler:function(){
								 var arr = $('#t_book').datagrid('getSelections');
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
													ids += arr[i].bookId + ',';
												}
												ids = ids.substring(0,ids.length-1);
												
												$.post('book/deleteBook.do' , {ids:ids},function(result){
													    $('#t_book').datagrid('reload');
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
								$('#t_book').datagrid('rejectChanges');
									editing = undefined;
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
				$('#t_book').datagrid('reload');
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
			$('#t_book').datagrid('load' ,serializeForm($('#mysearch')));
		});
		$('#clearbtn').click(function(){
			$('#keyWord').val("");
			$('#t_user').datagrid('load' ,{});
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
	
	//借阅书籍
	function borrowBook(bookId){
		
		$.messager.confirm("操作提示", "您确定要借阅此书吗？", function (data) {  
	           if (data) {  
	           	$.ajax({
	       			type : "post",
	       			dataType : "json",
	       			url : "borrow/borrowBook.do",
	       			data : {'bookId':bookId},
	       			async:true,
	       			success : function(data) {
	       				$.messager.show({
	       					title:'提示信息',
	       					msg:data.flag
	       				});
	       				$('#t_book').datagrid('reload');
	       			}
	       		});  
	           }else{
	        	   return;
	           }  
	       });  
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
			<table id="t_book"></table>
			<input id="flag" type="hidden" name="flag" value="${manager.grade }"/>
		</div>
		
	  </div> 

	</body>
</html>
