<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.visural.common.web.client.WebClient"%>
<%@ page import="com.ccthanking.framework.Constants"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", -10);

//设置客户端信息
WebClient webclient = WebClient.detect(request);
Object objuser = session.getAttribute("userId");
if(objuser!=null){
	  String pagepath = "jsp/business/project/project-index.jsp";
	  response.sendRedirect(pagepath);
}

String root = request.getScheme()+"://"+request.getServerName()+":8088/file";
String roots = request.getContextPath();

%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="ui.title"/></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=mode" />
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<link href="${pageContext.request.contextPath }/assets/css/ace.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/assets/css/font-awesome.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/assets/sys_resources/css/bootstrap.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath }/assets/sys_resources/css/base.css" rel="stylesheet" media="screen">
<%-- <link href="${pageContext.request.contextPath }/assets/css/jquery.gritter.css " rel="stylesheet" media="screen">--%>  
<link rel="icon" href="${pageContext.request.contextPath }/favicon.ico" type="image/x-icon" /> 
<link rel="shortcut icon" href="${pageContext.request.contextPath }/favicon.ico" type="image/x-icon" /> 


<script src="${pageContext.request.contextPath }/assets/sys_resources/js/jquery.js"></script>
<script src="${pageContext.request.contextPath }/assets/sys_resources/plugins/cookie/jquery.cookie.js"></script>
<script src="${pageContext.request.contextPath }/assets/sys_resources/js/bootstrap.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/default.js?version=20131206"> </script>

<style type="text/css">
/* 最初的页面背景色:#6A5B50**/
*{ margin:0;padding:0;}
ul{ list-style:none;}  
input,img{ border:none;}
input:-webkit-autofill { 
	-webkit-box-shadow: 0 0 0px 1000px #425677 inset; 
	border: none!important; 
	color:white;
}

body{background-color:#425677;font-family:"Microsoft YaHei";color:#white;background-image:url(${pageContext.request.contextPath }/assets/images/login/login_background.jpg);background-position:center;background-repeat:no-repeat;background-size:100% 100%;}
.top{height:50px; width:100%; background-color:#4d4848;}
.top p{ height:50px; line-height:50px;color:#ffffff; font-size:22px;width:400px;margin-left:50px;}

.content{ width:1000px; height:500px; margin:0 auto; }
.content .con_div2{ height:300px; width:360px;float:left;margin-left:650px; margin-top:100px;border:1px #00FFFF solid;-moz-border-radius: 15px;-webkit-border-radius: 15px;border-radius:15px;background-color:#425677;}
.con_div2 .loginStyle{ width:340px;height:250px;margin-left:10px;margin-top:50px;}
.con_div2 .login .text-form {width:80%;margin-left:10%;border-bottom:1px #00FFFF solid;}
.con_div2 .login .text-form2 {margin-top:30px;width:80%;margin-left:10%;color:white;}
.con_div2 .login .text-form .ace-icon-style{color:white; height:30px;margin-top:15px;padding-top:20px;margin-left:10px;margin-right:10px;}
.con_div2 .login .text-form input{color:white; height:30px;width:200px;margin-bottom:5px;padding:0px; border:none;background-color:#425677 !important;} 
.warningInfo{width:600px; float:left; margin-left:420px;margin-top:50px;}
/* ie 不支持  inline-block属性  解决用 *display:inline;zoom:1;**/
</style>
</head>
<%
 String error = (String)request.getAttribute("error");
  if(error==null)
  {
	  error = "";
  }
%>

<body >
   <div class="top">
     <p>漫拓云工程平台</p>
  </div>
<script src="${pageContext.request.contextPath }/assets/js/jquery.gritter.min.js"></script>
<div id="MessageModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="MessageModalTitle">提示信息</h3>
  </div>
  <div class="modal-body" id="MessageModalContent">
    <%=error %>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
</div>


<div>
<div id="browser_msg" style="color:red;font-weight: bold;text-align: right">
    		
</div>
<!-- <form method="post"  >   style="float:left" style="line-height: 50px;margin-right:10px; "-->
<div class="content">
<div class="con_div2">
  <form action="#"method="post" class="login loginStyle" id="loginForm" autocomplete="off">
  <p class="text-form">
  <i class="ace-icon fa fa-user ace-icon-style"></i>
  <input type="text"  id="username"  name="username" placeholder="请输入用户名" value="" autocomplete="off" />
  </p>
  <p class="text-form">
  <i class="ace-icon fa fa-lock ace-icon-style"></i>
  <input type="password" name="password" id="password" placeholder="请输入密码" value=""  autocomplete="off"/>
  </p>
  <p class="text-form2">
	<span class="remem_username" style="line-height:25px;"><input type="checkbox" id="remem_username" name="remem_username" value="1" style="margin-bottom:5px;"/>记住账号</span>
	<span class="remem_resetpassword" style="line-height:25px;"><input type="button" id="remem_resetpassword" name="remem_resetpassword" value="忘记密码" style="margin-bottom:5px;"/></span>
	<button style="float:right;width:50px;height:25px;color:#22677C;margin-right:20px;" id="login"  type="button">登陆</button>
	<a style="float:right;width:50px;height:25px;line-height:25px;font-size:15px;color:red" type="button" onclick="userReg();">注册</a>
  </p>
  </form> 
   <br/>
</div>
<div class="warningInfo"> <%= Constants.getString("BROWSER_ALTER","") %></div> 
</div>
   <!--  </form> -->
   
    <div>
    	<% 
    	//WebClient webclient =  (WebClient)session.getAttribute(Constants.WEB_CLIENT);
    	
    	if("IE".equals(webclient.getUserAgent().toString())&&webclient.getMajorVersion()<9){
    		%>
    			<script type="text/javascript">
    				$("#browser_msg").text("<%=Constants.getString("BROWSER_ERROR","")%>");
    				$("#username").attr("disabled",true);
    				$("#password").attr("disabled",true);
    				alert("<%=Constants.getString("BROWSER_ERROR","")%>");
    			</script>
    		<%
    		
    	}
    %>
    </div>    
</div>  
<div id="code" class="code" >
<h4 style="margin-left:63%;">android用户下载</h4>
<p><img style="margin-left:65%;" height="100"  width="100" src="<%=roots%>/assets/img/index/erweimas.png"/></p>
</div>
<div id="resetPassword" class="modal hide"></div>
</body>

<script  type="text/javascript">
function userReg(){
	//window.open("${pageContext.request.contextPath }/pagena/business/user/user-add", "用户注册");
    window.location.href="${pageContext.request.contextPath }/pagena/business/user/user-add";
}

function setAlertHTML()
{
	var alertHTML  = "<div id=\"pubAlert\" class=\"alert alert-block block-b-open\">";
	    alertHTML += "<button type=\"button\" class=\"close\" data-dismiss=\"alert\"></button>";
        alertHTML += "<h4>提示信息</h4>";
        alertHTML += "<span class=\"alertContent\"></span >";
        alertHTML += "</div>";
    $("body").prepend(alertHTML);
}

 

$(document).ready(function(){
	
var sa='<%= Constants.getString("BROWSER_ALTER","") %>'; 
	  setAlertHTML();
	  $("#username").focus();
	var path='${pageContext.request.contextPath }';
	
	$("#username").on("keydown",function(event){
		if(event.keyCode==13){ 
			$("#username").focus();
		}
	});
	
	/** 对密码框进行Enter键 操作 相当于点击了登录按钮 若输入了用户名
	 *  而未输入密码就可获取到用户名去数据库查询*/
	$("#password").keydown(function(event){
		if(event.keyCode==13){
			$("#login").click();
			return false;
		}
	});
	

	var uname = $.cookie("<%=Constants.APP_COOKIE_NAME_REMBERUSERNAME%>");
	if(uname==undefined){
		$("#remem_username").attr('checked',false);
	}else{
		$("#remem_username").attr('checked',true);
		$("#username").val(uname);
	}
	
	//弹出提示框
	$("#remem_resetpassword").click(function(){
		   $("#remem_resetpassword").attr("data-target","resetPassword");
		   $("#resetPassword").removeData("bs.modal");
		   $("#resetPassword").empty();
		   $("#resetPassword").modal({
		 	 backdrop:'static'
		 });
		 
		 $.get("${ctx}/jsp/business/user/user-reset-dialog.jsp",function(data){
			 $("#resetPassword").empty();
			 $("#resetPassword").html("");
			 $("#resetPassword").html(data);
		 });
	});
	
	/**直接点击 登录按钮 未输入用户名和密码的时提示**/
	$("#login").click(function() {
		var username = $("#username").val();
		if(username=="")
		{
			$("#pubAlert").find("span").html("请输入用户名！");
			$("#pubAlert").slideDown('fast').delay(2000).slideUp('fast');
			$("#username").focus();
			return false;
		}
		var password = $("#password").val();
		if($("#remem_username")[0].checked){
			var date = new Date(); 
			date.setTime(date.getTime() + (14 * 24 * 60 * 60 * 1000)); 
			$.cookie('<%=Constants.APP_COOKIE_NAME_REMBERUSERNAME%>',username, {expires:date});
		}else{
			$.removeCookie('<%=Constants.APP_COOKIE_NAME_REMBERUSERNAME%>');
		}
		
		var fields = $("#loginForm").serialize();
		$.ajax({
			type: "POST",
			cache:false,
			async:false,
			ifModified:true,
			url: '${pageContext.request.contextPath }/userController/loginAjax',
			data: fields,
			success: function(msg){
				var result = eval("(" + msg + ")");
				if(result.success){
				//windows.location.href="/url" 当前页面打开URL页面
					window.location.href='${pageContext.request.contextPath }'+result.obj.url;
		   		}else{
		   			$("#MessageModalContent").html(result.obj.error);
		   			$("#MessageModal").modal("show");
			   	}
				return false;
		   }
		});
		
		return false;
	});  
});




</script>

</html>
