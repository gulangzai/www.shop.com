<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" type="text/css" href="${ctxStatic }/css/base.css">
<link rel="stylesheet" href="${ctxStatic }/css/menu.css">
<script type="text/javascript" src="${ctxStatic}/js/jquery-1.8.2.min.js"></script>	
<!--<script src="js/jquery.js"></script>
<script src="js/menu.js"></script>-->
<title>header</title>
<script type="text/javascript">

var rootPath = '${ctx}';	

function searchQuestion(){
	var title = $("#title").val();
	if(null == title || title.trim() == ""){
		alert("请填写想问的内容");
		$("#title").focus();
		return;
	}
	/* $.ajax({type:"post",
	    url:rootPath+"/stuPersonalCenter/enterMyAnswer.do",
	    dataType: "json",
	    data:{
	    	  title:title
	    },
	    success:function(data){
	    }
	});  */
	
	parent.location.href= rootPath+"/stuPersonalCenter/enterMyAnswerList.do?title="+encodeURI(encodeURI(title));
	
}

</script>
</head>

<body>
<div class="header">
<div class="header_wrap" style="display:block;">
<div class="logo_lie">
 <a href="${ctx}/homePage/toIndex.do" target="_parent"><img src="${ctxStatic}/images/logo.png" alt="学尔森建工网校"></a>
<span class="text_tit">测试版</span>
</div>
<ul class="nav">
    <li class="nav_text"><a href="${ctx}/homePage/toIndex.do" target="_parent">首页</a></li>
    <li class="nav_text"><a href="${ctx}/examCourse/getZhinengCourse.do" target="_parent">考试课程</a></li> 
    <li class="nav_text"><a href="${ctx}/homePage/skipTiku.do" target="_parent">考试题库</a></li>
    <li class="nav_text"><a href="${ctx}/stuPersonalCenter/enterMyAskExam.do" target="_parent">考试问答</a></li>
    <li class="nav_text nav_select" style="border:none;">
    </li>
</ul> 
<div class="select">
    <input name="title" id="title" type="text" size="17" class="sr" placeholder="搜你想问">
   <%-- <a href="${ctx}/stuPersonalCenter/enterMyAnswer.do" target="_parent">
    	<input type="button" value="百问搜索" class="h-btn">
    </a> --%>
   <input type="button" value="百问搜索" onclick="searchQuestion();" class="h-btn">
    </div>
<%-- <div class="we_ask">
<a href="${ctx}/stuPersonalCenter/enterMyQuestions.do" target="_parent">我要提问</a>
</div> --%>

<c:if test="${user.studentName!=''&&user.studentName!=null}">
<div class="welcome_login">
  <div class="login_bag">
   <span class="welcome_icon"><img src="${ctxStatic }/images/icon3.png"></span>
    <div class="welcome_text">
    <div class="wel_name">
     <span class="wel_names">Hi,&nbsp;${user.studentName}</span>
      <div class="wel_down">
         <a href="${ctx}/stuPersonalCenter/getCenterMain.do" target="_parent"> <span class="wel_ge">个人中心</span></a><a href="${ctx}/homePage/exit.do" target="_parent"><em class="wel_exit">退出</em></a>
     <%--  <a href="${ctx}/myTest/mydetail.do" target="_parent"> <span class="wel_ge">个人中心</span></a><a href="${ctx}/homePage/exit.do" target="_parent"><em class="wel_exit">退出</em></a> --%>
     </div>
    </div>
    </div>
  </div>
 
      <!--<ul class="menu_down">
		<li class="menu_lie"><a href="#">
        <em class="menu_tit">一级建造师</em><br/>
        <span>法律法规 第3节</span>
        <span class="menu_go_on">继续</span>
        </a>
        </li>
	    <li class="menu_lie" style="line-height:32px;">
        <a><em class="menu_per">完善个人资料</em></a>
       
        <em class="menu_out">退出</em></li>
        
		
	 </ul>-->

  
</div>
<%-- <div class="welcome_login" style="display:block;">
  <div class="login_bag">
   <span class="welcome_icon"><img src="${ctxStatic }/images/u28.png"></span>
    <div class="welcome_text">
    <div class="wel_name">
     <span class="wel_names">${user.studentName},</span>
    <a href="${ctx}/homePage/exit.do" target="_parent"><em class="wel_exit">退出</em></a></br>
     <a href="${ctx}/stuPersonalCenter/getMyCourse.do" target="_parent"><span class="wel_ge">个人中心</span></a>
    </div>
    </div>
  </div>
</div> --%>
</c:if>
<c:if test="${user.studentName==''||user.studentName==null}">
<div class="dlzc">
    <a href="${ctx}/homePage/toLogin.do?type=1" target="_parent">登录
    </a>
    <a href="${ctx}/homePage/toLogin.do?type=2" target="_parent">注册
    </a>
</div>
</c:if>
</div>
</div>

</body>
</html>
