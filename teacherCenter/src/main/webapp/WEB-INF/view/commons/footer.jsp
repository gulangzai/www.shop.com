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
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/base.css">
</head>

<body>
<strong><div class="footer">
<div class="nk">
<div class="left">
<h3>考建造师，读学尔森</h3>
<p>学员须知  |  关于我们  |  联系方式  |  网校招聘  |  团队介绍  |  师资介绍  |  学员风采  |  全国校区  |  院校合作  |  合作代理  |  友情链接<br>
客服电话：400-166-1186    投诉建议邮箱：hangkongmeng@hotmail.com</p>
</div>
<div class="right">
<img src="${ctxStatic}/images/ewm.png" alt="扫一扫关注建造师考试微信"><br>
扫一扫关注建造师考试微信
</div>
</div>
</div>
</strong>
</body>
</html>
