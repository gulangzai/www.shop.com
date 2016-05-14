<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/commons/taglib.jsp" %>
<%@ include file="/WEB-INF/view/commons/jsp/main.jspf"%>
<!DOCTYPE html>
<html> 
  <head>
  <style type="text/css">
  </style> 
  <link href="${ctxStatic}/css/login/main.css" rel="stylesheet" type="text/css">
   <title>学尔森建工网校学员管理系统</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/> 
  </head>
  <body>
  
      <img src="${ctxStatic}/images/login/beijing.jpg" alt=""/ class="beij">
      
      <div class="dl">
           <div class="wenzi">学尔森建工网校</div>
           <div class="wenzix">—&nbsp;学员管理系统&nbsp;—</div>
           <div class="dlkuang">
              <c:if test="${message!=''}">
	              <div class="bao" style="color:red;text-align:center;">${message}</div>
	         </c:if>
             <form action="${ctx}/homePage/toLogin.do" name="loginForm" id="loginForm" method="post">
             <label for="textfield">用户名</label><br>
             <input type="text" name="userName" id="userName" class="biank" value='${cookie.userName.value}'><br>
             
             <label for="password">密码</label><br>
             <input type="password" name="password" id="password" class="biank" value="${cookie.password.value}"><br>
           
             <input type="checkbox" name="checkbox" id="checkbox" onChange="change()">
             <input type="hidden" name="checkboxmark" value="${cookie.checkboxmark.value}">
             <label for="checkbox">记住密码 </label> <br> 
             <input type="submit" name="submit" id="submit" value="登&nbsp;录">
             </form>
           
           </div> 
      </div> 
  </body>
  
  <script type="text/javascript">
    var ctx = '${ctx}';
    function init(){ 
    	
    	if(loginForm.checkboxmark.value=='1'){
    		loginForm.checkbox.checked=true; 
    	}else{
    		loginForm.checkbox.checked=false; 
    	}
    }
    init();
    function change(){ 
    	if(loginForm.checkbox.checked){ 
    		loginForm.checkboxmark.value=1;
    	}else{ 
    		loginForm.checkboxmark.value=0;
    	}
    }
  </script>
  <script src="${ctxStatic}/js/login/login.js"></script>
</html>