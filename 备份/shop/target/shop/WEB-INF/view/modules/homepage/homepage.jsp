<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/commons/taglib.jsp" %> 

<!DOCTYPE html>
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
    <meta name="viewport" content="width=device-width" /> 
    <link rel="shortcut icon" href="#" /> 
    <meta name="description" content="啾唧网 方便快捷，物美价廉，更快送达，诚信服务" /> 
    <meta name="keywords" content="用户登录" />
<title>啾唧-主页</title>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/base.css">
<link rel="stylesheet" href="${ctxStatic}/css/login/login.css"  />
</head>

<body>
<div class="container">  
 <iframe  src="${ctx}/homePageCtrl/toHeader.do" height="81" width="100%" scrolling="no" style="border:0px solid red"></iframe>
 <div id="main">
<div class="content">
<!--<div class="content-img"><img src="image/content.jpg"  alt=""/></div>-->
<div class="login">
 <c:if test="${message!=''}">
       <div class="bao" style="color:red;text-align:center;">${message}</div>
  </c:if>
 <form method="post" action="${ctx}/userCtrl/login.do" id="loginForm" name="loginForm">
      <h4><a href="#" target="_blank" title="立即注册" tabindex="7">立即注册 &gt;</a>用户登录</h4>
         <p class="logininput">
         <input id="username" name="username" size="15" tabindex="1" maxlength="50" type="text" 
             placeholder="手机号/会员名/邮箱" value="${cookie.userName.value}" /><span class="clearb"></span></p>
             <p class="logininput">
		          <input id="password" name="password" size="15" tabindex="2" maxlength="16" type="password" placeholder="请输入密码" value="${cookie.password.value}" />
		     </p>
               <p class="logincheckbox">
               <label> <input id="check" type="checkbox"  name="checkbox" 	 onChange="change()"/>记住密码</label>
              <input type="hidden" name="checkboxmark" value="${cookie.checkboxmark.value}">
            <a href="#">忘记密码</a> </p> 
         <span class="button-login" id="submit">登录</span> 
         </form>
    </div> 
</div>
</div> 

  <iframe src="${ctx}/homePageCtrl/toFooter.do" height="120px" width="100%"  scrolling="no" style="border:0px solid red"></iframe>  
 </div>
</body>

  <script type="text/javascript">
    var ctx = '${ctx}';
    function init(){  
    	if(loginForm.checkboxmark.value=='1'){
    		loginForm.checkbox.checked=true; 
    		loginForm.checkboxmark.value=1;
    	}else{
    		loginForm.checkbox.checked=false; 
    		loginForm.checkboxmark.value=0;
    	}
    }
    init();
    function change(){ 
    	if(loginForm.checkbox.checked){ 
    		loginForm.checkboxmark.value=1;
    	}else{ 
    		loginForm.checkboxmark.value=0;
    	}
    	console.info(loginForm.checkboxmark.value);
    }
  </script>
  <script src="${ctxStatic}/js/login/login.js"></script>
</html>