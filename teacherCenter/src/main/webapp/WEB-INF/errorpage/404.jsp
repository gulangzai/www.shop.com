<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/commons/taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>404</title>
<link rel="stylesheet" type="text/css" href="${ctxStatic }/css/base.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic }/css/xx.css">
<link rel="stylesheet" href="${ctxStatic }/css/pay.css">
<link rel="stylesheet" href="${ctxStatic }/css/505.css">
<script src="${ctxStatic }/js/jquery-1.8.3.min.js"></script>
<script src="${ctxStatic }/js/jump.js"></script>
	
<!--[if IE 6]>
        <script type="text/javascript" src="js/DD_belatedPNG.js"></script>
        <script>
          DD_belatedPNG.fix('.png_bg');
        </script>
    <![endif]-->
</head>

<body>
<div class="page">
 <iframe scrolling="no" src="${ctx}/homePage/toHeader.do" height="81" style="position:fixed; top:0; z-index:9999;"></iframe>
 <div class="wrap">
  <div class="sorry_one"><img src="${ctxStatic }/images/404_03_03_03_03.png"/>
  </div>
  <div class="five"><span class="sorry_tit">
  很抱歉，该路径不正确或资源不存在!请确认后重新输入地址!!!</span>
  </div>
  <div class="black"><a href="${ctx}/homePage/toIndex.do">返回首页</a></div>
 </div>
<%--  <div class="wrap">
  <div class="sorry_one"><img src="${ctxStatic }/images/icon_03.png"/>
  <span class="sorry_tit">很抱歉，该路径不正确或资源不存在!</span>
  </div>
  <span class="five"><img src="${ctxStatic }/images/404_03.png"/></span>
  <div class="black"><a href="${ctx}/homePage/toIndex.do">首页</a></div>
 </div> --%>
<iframe scrolling="no" src="${ctx}/homePage/toFooter.do" height="161" style="margin-top:20px;"></iframe>
</div>
</body>
</html>
