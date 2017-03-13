// 自定义的校验器
$.extend($.fn.validatebox.defaults.rules, {   
	login : {
			validator: function(value, param){
				//alert(param[0]);
				var result = $.ajax({
	   				url:'StaffServlet.action?method=getStaffNo',
	   				data:{staffNo:value},
	   				type:'post',
	   				dataType:'json',
	   				async:false,
	   				cache:false			   				
	   			}).responseText;
	   			json = eval("(" + result + ")");
	   			if(json.list == undefined){
	   				if(param[0] != value){
	   					$.fn.validatebox.defaults.rules.login.message = '用户不存在';
	   					return false;
	   				}else{
	   					return true;
	   				}	
	   			}else{
	   				return true;
	   			}  			
			},   
			message: ''   
	},
	verify:{
		validator: function(value, param){
			alert(param[0]);
			if(value != param[0]){
   				$.fn.validatebox.defaults.rules.verify.message = '请输入正确验证码';
   				return false;
   			}else{
   				return true;
   			}		
		},   
		message: ''  
	},
	adsNo : {
 			validator: function(value, param){
				if(value.length != param[0]){
					$.fn.validatebox.defaults.rules.adsNo.message = '用户名必须在'+param[0]+'位';
				}else{
					var result = $.ajax({
		   				url:'AdsServlet.action?method=getAdsNo',
		   				data:{adsNo:value},
		   				type:'post',
		   				dataType:'json',
		   				async:false,
		   				cache:false			   				
		   			}).responseText;
		   			json = eval("(" + result + ")");
		   			if(json.list != undefined){
		   				$.fn.validatebox.defaults.rules.adsNo.message = '用户名已存在';
		   				return false;
		   			}else{
		   				return true;
		   			}
				}
		
			},   
			message: ''  
	},
  branchNo : {
	 			validator: function(value, param){
					if(value.length != param[0]){
						$.fn.validatebox.defaults.rules.branchNo.message = '用户名必须在'+param[0]+'位';
					}else{
						var result = $.ajax({
			   				url:'BranchServlet.action?method=getBranchNo',
			   				data:{branchNo:value},
			   				type:'post',
			   				dataType:'json',
			   				async:false,
			   				cache:false			   				
			   			}).responseText;
			   			json = eval("(" + result + ")");
			   			if(json.list != undefined){
			   				$.fn.validatebox.defaults.rules.branchNo.message = '用户名已存在';
			   				return false;
			   			}else{
			   				return true;
			   			}
					}
			
				},   
				message: ''  
		},
	educateNo : {
 			validator: function(value, param){
			if(value.length != param[0]){
				$.fn.validatebox.defaults.rules.educateNo.message = '用户名必须在'+param[0]+'位';
			}else{
				var result = $.ajax({
	   				url:'EducateServlet.action?method=getEducateNo',
	   				data:{educateNo:value},
	   				type:'post',
	   				dataType:'json',
	   				async:false,
	   				cache:false			   				
	   			}).responseText;
	   			json = eval("(" + result + ")");
	   			if(json.list != undefined){
	   				$.fn.validatebox.defaults.rules.educateNo.message = '用户名已存在';
	   				return false;
	   			}else{
	   				return true;
	   			}
			}
	
		},   
		message: ''  
},
   leaveNo : {
		validator: function(value, param){
		if(value.length != param[0]){
			$.fn.validatebox.defaults.rules.leaveNo.message = '用户名必须在'+param[0]+'位';
		}else{
			var result = $.ajax({
   				url:'LeaveServlet.action?method=getLeaveNo',
   				data:{leaveNo:value},
   				type:'post',
   				dataType:'json',
   				async:false,
   				cache:false			   				
   			}).responseText;
   			json = eval("(" + result + ")");
   			if(json.list != undefined){
   				$.fn.validatebox.defaults.rules.leaveNo.message = '已存在';
   				return false;
   			}else{
   				return true;
   			}
		}

	},   
	message: ''  
},
  personalScheduleNo : {
		validator: function(value, param){
		if(value.length != param[0]){
			$.fn.validatebox.defaults.rules.personalScheduleNo.message = '用户名必须在'+param[0]+'位';
		}else{
			var result = $.ajax({
   				url:'PersonalScheduleServlet.action?method=getPersonalScheduleNo',
   				data:{personalScheduleNo:value},
   				type:'post',
   				dataType:'json',
   				async:false,
   				cache:false			   				
   			}).responseText;
   			json = eval("(" + result + ")");
   			if(json.list != undefined){
   				$.fn.validatebox.defaults.rules.personalScheduleNo.message = '用户名已存在';
   				return false;
   			}else{
   				return true;
   			}
		}

	},   
	message: ''  
},
   projectNo : {
		validator: function(value, param){
		if(value.length != param[0]){
			$.fn.validatebox.defaults.rules.projectNo.message = '用户名必须在'+param[0]+'位';
		}else{
			var result = $.ajax({
   				url:'ProjectServlet.action?method=getProjectNo',
   				data:{projectNo:value},
   				type:'post',
   				dataType:'json',
   				async:false,
   				cache:false			   				
   			}).responseText;
   			json = eval("(" + result + ")");
   			if(json.list != undefined){
   				$.fn.validatebox.defaults.rules.projectNo.message = '用户名已存在';
   				return false;
   			}else{
   				return true;
   			}
		}

	},   
	message: ''  
},
   salaryNo : {
		validator: function(value, param){
		if(value.length != param[0]){
			$.fn.validatebox.defaults.rules.salaryNo.message = '用户名必须在'+param[0]+'位';
		}else{
			var result = $.ajax({
   				url:'SalaryServlet.action?method=getSalaryNo',
   				data:{salaryNo:value},
   				type:'post',
   				dataType:'json',
   				async:false,
   				cache:false			   				
   			}).responseText;
   			json = eval("(" + result + ")");
   			if(json.list != undefined){
   				$.fn.validatebox.defaults.rules.salaryNo.message = '用户名已存在';
   				return false;
   			}else{
   				return true;
   			}
		}

	},   
	message: ''  
},
   purchaseNo : {
	validator: function(value, param){
	if(value.length != param[0]){
		$.fn.validatebox.defaults.rules.purchaseNo.message = '用户名必须在'+param[0]+'位';
	}else{
		var result = $.ajax({
				url:'PurchaseServlet.action?method=getPurchaseNo',
				data:{purchaseNo:value},
				type:'post',
				dataType:'json',
				async:false,
				cache:false			   				
			}).responseText;
			json = eval("(" + result + ")");
			if(json.list != undefined){
				$.fn.validatebox.defaults.rules.purchaseNo.message = '用户名已存在';
				return false;
			}else{
				return true;
			}
	}

},   
message: ''  
}
		
		
}); 

