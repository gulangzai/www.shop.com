<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/commons/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<title>班主任工作管理系统</title>
</head>

<frameset rows="88px,50%" frameborder="0" framespacing="0" name="main"> 
   <frame   src="${ctx}/homeMain/toTop.do" name="top">
        <frameset cols="220px,100%" style="border:1px solid red;">
            <frame src="${ctx}/homeMain/toLeft.do" name="left">
            <frame src="${ctx}/homePage/showHome.do" name="right">  
        </frameset>
</frameset>
<noframes>
<body>
</body>
</noframes>

 
<%-- 	<frameset rows="90,*" framespacing=0 border=0 frameborder="0">
	    <frame noresize name="TopMenu" scrolling="no" src="${ctx}/homeMain/toTop.do"> 
		<frameset cols="45,250" id="resize" scrolling="yes">
			<frame noresize name="menu" scrolling="no"  src="${ctx}/homeMain/toLeft.do">
			<frame noresize name="right" scrolling="yes" src="${ctx}/homeMain/toRight.do">
		</frameset>
	  <frame noresize name="status_bar" scrolling="no" src="${ctx}/homeMain/toBottom.do">  
	</frameset> --%>
 
 
</html>