
// 自定义的校验器
$.extend($.fn.validatebox.defaults.rules, {   
		midLength: {   
	 			validator: function(value, param){   
					return value.length >= param[0] && value.length <= param[1];    
				},   
				message: ''  
		} ,
		equalLength : {
	 			validator: function(value, param){   
					return value.length == param[0];    
				},   
				message: ''  
		} ,
		loginNo : {
 			validator: function(value, param){
				if(value.length != param[0]){
					$.fn.validatebox.defaults.rules.loginNo.message = '用户名必须在'+param[0]+'位';
				}else{
					var result = $.ajax({
		   				url:'StaffServlet.action?method=getStaffNo',
		   				data:{staffNo:value},
		   				type:'post',
		   				dataType:'json',
		   				async:false,
		   				cache:false			   				
		   			}).responseText;
		   			json = eval("(" + result + ")");
		   			if(json.list != undefined){
		   				$.fn.validatebox.defaults.rules.loginNo.message = '用户名已存在';
		   				return false;
		   			}else{
		   				return true;
		   			}
				}
		   			
			},   
			message: ''  
	},
	updatePassword:{
			validator: function(value, param){
	   			if(value != param[0]){
	   				$.fn.validatebox.defaults.rules.updatePassword.message = '请输入正确密码';
	   				return false;
	   			}else{
	   				return true;
	   			}		
			},   
			message: ''  
		},
	CHS : {
			validator: function(value, param){
				return /^[\u0391-\uFFE5]{2,5}$/.test(value);
			}, 
			message: '输入必须2到5个汉字' 
		},
	reason : {
			validator: function(value, param){
				return /^[\u0391-\uFFE5]{5,100}$/.test(value);
			}, 
			message: '输入必须5到100个汉字' 
		},	
	equalTo : {
			validator: function(value, param){
				return value == $(param[0]).val();
			}, 
			message: '两次输入密码不一致' 
		}
	
	
	
	
	
	
	
}); 



$.extend($.fn.datagrid.defaults.editors, {   
    datetimebox: {   
        init: function(container, options){   
            var box = $('<input />').appendTo(container);   
            box.datetimebox(options);
            return box;   
        },   
        getValue: function(target){   
            return $(target).datetimebox('getValue');   
        },   
        setValue: function(target, value){   
            $(target).datetimebox('setValue',value);
        },   
        resize: function(target, width){   
            var box = $(target);   
			box.datetimebox('resize' , width);
        } ,
        destroy:function(target){
        	$(target).datetimebox('destroy');
        }
    }   
});  







