<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/commons/taglib.jsp" %>
<%@ include file="/WEB-INF/view/commons/jsp/main.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/homemain/left_panel.css">  
<!-- <style>
body{
margin:0px;padding:0px;
border-right:1px solid rgb(153,153,153);
}
ul{
   margin:0px; 
   padding:0px;
   width:180px;
   border:1px solid red;
}

div{
  margin:0px;
  padding:0px;
}
ul li{
     list-style:none; 
     width:220px;
     margin:0px;
     padding:0px;
     margin-left:-40px;
     font-size:16px;
     background:rgb(153,153,153);
}
ul li div{
   width:200px;
   height:30px;
   margin:0px 0px;
   padding-top:5px;
   padding-left:20px;
   display:block; 
  /*   url('${ctxStatic}/images/homemain/u831.png') */
 /*  background:red; */
}
ul li a{  
  TEXT-DECORATION:none;
  font-size:16px;
  color:white; 
  
}
</style> -->
<html> 
<body>
<div class="left-panel">
  <ul class="left_nav">
     <a href="${ctx}/homePage/showHome.do" target="right"><li class="nav_list">我的学员</li></a>
     <a href="${ctx}/courseOpen/courseOpenMain.do" target="right"><li class="nav_list">课程开通</li></a>
  </ul>
</div>
</body> 
</html>