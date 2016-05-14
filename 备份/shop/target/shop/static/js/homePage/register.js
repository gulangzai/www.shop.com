(function(){
	var username=$("input[name='username']").val();
	var password = $("input[name='password']").val();
	var repeat = $("input[name='repeat']").val(); 
	var checkCode = $("input[name='rand']").val();
	var randFlag = false;
	var mobileFlag = false;
	var passwordFlag = false;
	  var regBox = {
		        regEmail : /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/,//邮箱
		        regName : /^[a-z0-9_-]{3,16}$/,//用户名
		        regMobile : /^0?1[3|4|5|8][0-9]\d{8}$/,//手机
		        regTel : /^0[\d]{2,3}-[\d]{7,8}$/,
		        regPass: /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$/
		  };
	$("input[name='password']").blur(function(){
		password = $("input[name='password']").val();
	    var t_password_error = $(".t_password_error");  
		if(password==''){
			t_password_error.show(); 
			t_password_error.text("密码不能为空");
			passwordFlag=false;
			return false;
		}
		if(password.length<6){
			t_password_error.show(); 
			t_password_error.text("密码长度过短"); 
			passwordFlag=false;
			return false;
		}
		if(password.length>13){
			t_password_error.show(); 
			t_password_error.text("密码长度过长"); 
			passwordFlag=false;
			return false;
		}
		if(!regBox.regPass.test(password)){ 
			t_password_error.show(); 
			t_password_error.text("密码由6至16位字母及数字组成");  
			passwordFlag=false;
			return false;
		} 
		t_password_error.hide(); 
		passwordFlag=true;
	});
	
	$("input[name='repeat']").blur(function(){
		var t_repeatpw_error = $(".t_repeatpw_error");
		password = $("input[name='password']").val();
		repeat = $("input[name='repeat']").val(); 
		if(!regBox.regPass.test(repeat)){ 
			t_repeatpw_error.show(); 
			t_repeatpw_error.text("确定密码:密码由6至16位字母及数字组成");   
			passwordFlag=false;
			return false;
		}
		if(password!=repeat){ 
			t_repeatpw_error.show(); 
			t_repeatpw_error.text("两次密码验证相等");   
			passwordFlag=false;
			return false;
		} 
		t_repeatpw_error.hide();
		passwordFlag=true;
	});
	$("input[name='rand']").blur(function(){
		  var t_rand_error = $(".t_rand_error");
		  if($(this).val()==''){
			  t_rand_error.show();
			  t_rand_error.text('验证码不能为空'); 
			  randFlag=false; 
		  }else{
			  randFlag=true;
			  t_rand_error.hide();
		  }
		  return;	  
	});
	$("input[name='username']").blur(function(){ 
		var t_phone_error = $(".t_phone_error");
		username=$("input[name='username']").val(); 
		if(username==''){
			t_phone_error.show();
			t_phone_error.text('手机号为空'); 
			mobileFlag=false;
			return false;
		}  
		var tflag =  regBox.regMobile.test(username); 
		if(!tflag){ 
			t_phone_error.show();
			t_phone_error.text('手机格式不对'); 
			mobileFlag=false;
			return false;
		} 
		t_phone_error.hide();
		mobileFlag=true;
	});
	$("#submit").click(function(){  
		if(randFlag&&mobileFlag&&passwordFlag){
			$.ajax({
	    		type:'post',
	    		url:ctx+'/userCtrl/register.do',
	    		data:{
	    			mobile:username,  
	    			password:password,
	    			checkCode:checkCode
			    },
			    dataType: "json", 
	    		async:false,
	    		success:function(sR){ 
	    			if(sR.message=='FAIL'){
	    				alert('注册失败');
	    			}else if(sR.message=='EXIST'){
	    				alert("用户已经存在");
	    			}else if(sR.message=='ERRORCODE'){
	    				alert("验证码错误");
	    			}else if(sR.message=='SUCCESS'){ 
	    				window.location=ctx+"/homemainCtrl/login.do";
	    			}
	    		}
	    	});  
		}else{
			alert('请按要求填写');
		}
	}); 
})(jQuery);