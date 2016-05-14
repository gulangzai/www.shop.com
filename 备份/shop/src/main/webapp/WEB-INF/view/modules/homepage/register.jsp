<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/commons/taglib.jsp" %> 
<%@ include file="/WEB-INF/view/commons/jsp/main.jspf"%>
<!DOCTYPE html>
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
    <meta name="viewport" content="width=device-width" /> 
    <link rel="shortcut icon" href="#" /> 
    <meta name="description" content="啾唧网 方便快捷，物美价廉，更快送达，诚信服务" /> 
    <meta name="keywords" content="用户登录" />
<title>啾唧-用户注册</title>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/base.css">
<link rel="stylesheet" href="${ctxStatic}/css/register.css"/>
</head>
 <script type="text/javascript">
    var ctx = '${ctx}'; 
    function yanzhen(){
    	var username=$("input[name='username']").val();
    	if(flag){
	   		  remainTime(); 
	       	  $.ajax({
	       		type:'post',
	       		url:ctx+'/userCtrl/testCode.do',
	       		data:{
	       			mobile:username,   
	   		    },
	   		    dataType: "json", 
	       		async:false,
	       		success:function(sR){ 
	       			if(sR.message=='SEND'){
	       				alert('验证码已发送');
	       			}else if(sR.message=='SENDERROR'){
	       				alert("验证码发送失败");
	       			}else if(sR.message=='SUCCESS'){ 
	       				window.location=ctx+"/homemainCtrl/login.do";
	       			}
	       		}
	       	});
    	}   
    } 
    var i=60;
    var flag = true;
    function remainTime(){ 
    	flag = false;
    	var label = $(".getMobileRand").text();
        if(i==0){  
        	 $(".getMobileRand").text("获取验证码"); 
        	 flag = true; 
        	 return;
        }  
        $(".getMobileRand").text(i+"秒后重试"); 
        i=i-1;
        setTimeout("remainTime()",1000);  
    }  
  </script> 
<body>
<div class="container">  
 <iframe  src="${ctx}/homePageCtrl/toHeader.do" height="81" width="100%" scrolling="no" style="border:0px solid red"></iframe>
<div id="main">
<div class="content">
 <h1 class="title">欢迎注册啾唧通行证</h1>
 <div class="register">
      <form action="" autocomplete="off" name="registerForm" id="registerForm">
            <input type="hidden" name="from" value="0" />
            <div class="reg-tit">
            <div class="reg-tit1"><h4>用户注册</h4></div>
            <div class="reg-tit2"><h4><a href="${ctx}/userCtrl/login.do" target="_self" title="在此登录" >在此登录</a>已有账号</h4></div>
            </div>
            
            <div class="ibox phone">
                <input name="username" class="t_phone" type="text" placeholder="手机号" /> 
                <i></i>  
            </div>
               <span class="ihint ihint_error t_phone_error" style="display: none;margin-top:5px;"></span>
             <div class="cl vbox">
                <div class="fl vboxl">
                    <div class="ibox">
                        <input name="rand" type="text" placeholder="验证码" /> 
                        <i></i>
                    </div>
                </div>
                <div class="fl vboxr">
                    <a class="getMobileRand" href="javascript:yanzhen();">获取验证码</a>
                </div>
                <span class="ihint ihint_error t_rand_error" style="display: none;margin-top:8px;"></span>
            </div>
             
             <div class="ibox pd">
               <!--<label class="pwshow" for="">密码（由6-16位英文字母及数字组成）</label>-->
                <input name="password" type="password"  placeholder="密码（由6-16位英文字母及数字组成）" value="" />
                <i></i> 
            </div>
              <span class="ihint ihint_error t_password_error" style="display: none;"></span>
            <div class="pwlevel">
                <ul class="cl pwLv" style="display: none;">
                    <li class="l cur"><span>低</span></li>
                    <li class="m"><span>中</span></li>
                    <li class="h"><span>高</span></li>
                </ul>
            </div>
            <div class="ibox repeatpw">
                <!-- <label class="pwshow" for="">重复密码</label>-->
                <input name="repeat" type="password"  placeholder="重复密码" value="" /> 
                <i></i> 
            </div>
             <span class="ihint ihint_error t_repeatpw_error" style="display: none;"></span>
             <span class="button-register" id="submit">注册</span>
             </form>
             </div>
</div>
</div>

  <iframe src="${ctx}/homePageCtrl/toFooter.do" height="120px" width="100%"  scrolling="no" style="border:0px solid red"></iframe>  
 </div>
</body> 
  <script src="${ctxStatic}/js/homePage/register.js"></script>
</html>