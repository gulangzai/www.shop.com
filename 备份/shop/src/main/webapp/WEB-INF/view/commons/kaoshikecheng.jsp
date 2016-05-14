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
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/base.css">
<title>考试课程</title>
<style>
* { border:none; padding:0; margin:0; float:none;}
body{font: normal 100% Helvetica, Arial, sans-serif;font-family: "微软雅黑";background: #fff; }
img{border: none;}
ul, ol{list-style: none;}
a{color: #333;text-decoration: none;}
.page {  width:100%;}
.main { width:990px; margin: 80px auto 20px auto; text-align:center; padding:5px;}
.tit { width:960px; margin:auto; height:60px; padding:30px 20px 10px 20px;}
</style>
</head>

<body>
<div class="page">
<iframe scrolling="no" src="${ctx}/homePage/toHeader.do" height="81" style="position:fixed; top:0; z-index:9999;"></iframe>
  <div class="main">
<table width="100%" border="0" cellspacing="4" cellpadding="2">
  <tr>
    <td rowspan="2" align="center" valign="middle"><a href="${ctx}/homePage/toGeneral.do?majorId=kl941809Z9Fz10Xe1Jyj" target="_blank"><img src="${ctxStatic}/image/wk_01.png" alt="二级建造师" width="324" height="332" id="img01"> </a></td>
    <td align="center" valign="middle"><a href="${ctx}/homePage/toGeneral.do?majorId=h5xeu3TIR1gpnUFnmbQ8" target="_blank"><img id="img02" src="${ctxStatic}/image/wk_08.png" width="324" height="162" alt="一级建造师"></a></td>
    <td colspan="2" align="center" valign="middle"><a href="${ctx}/homePage/toGeneral.do?majorId=Q25iX81302XAXp48teRx" target="_blank"><img id="img03" src="${ctxStatic}/image/wk_02.png" width="324" height="162" alt="监理工程师"></a></td>
    </tr>
  <tr>
    <td align="center" valign="middle"><a href="#" target="_blank"><img id="img04" src="${ctxStatic}/image/wk_03.png" width="324" height="162" alt="物业管理师"></a></td>
    <td colspan="2" align="center" valign="middle"><a href="${ctx}/homePage/toGeneral.do?majorId=f0tUN7YChCtVu6H2oio5" target="_blank"><img id="img05" src="${ctxStatic}/image/wk_07.png" width="324" height="162" alt="北京造价员"></a></td>
    </tr>
  <tr>
    <td align="center" valign="middle"><a href="${ctx}/homePage/toGeneral.do?majorId=M4IM860LchY2s17l17iy" target="_blank"><img id="img06" src="${ctxStatic}/image/wk_04.png" width="324" height="162" alt="一级消防工程师"></a></td>
    <td align="center" valign="middle"><a href="${ctx}/homePage/toGeneral.do?majorId=f0tUN7YChCtVu6H2oio5" target="_blank"><img id="img07" src="${ctxStatic}/image/wk_05.png" width="324" height="162" alt="造价工程师"></a></td>
    <td align="center" valign="middle"><a href="${ctx}/homePage/toGeneral.do?majorId=f0tUN7YChCtVu6H2oio5" target="_blank"><img id="img08" src="${ctxStatic}/image/wk_11.png" width="158" height="162" alt="上海造价员"></a></td>
    <td align="center" valign="middle"><a href="#" target="_blank"><img src="${ctxStatic}/image/wk_10.png" id="img09" width="158" height="162" alt="中专/大专/本科"></a></td>
  </tr>
  <tr>
    <td align="center" valign="middle"><a href="${ctx}/homePage/toGeneral.do?majorId=8Z5FqMVTQ4QZtJM4F07d" target="_blank"><img id="img10" src="${ctxStatic}/image/wk_09.png" width="324" height="162" alt="二级消防工程师"></a></td>
    <td align="center" valign="middle"><a href="${ctx}/homePage/toGeneral.do?majorId=5udGuw5bWjfql9wo3I2a" target="_blank"><img  id="img11" src="${ctxStatic}/image/wk_06.png" alt="安全工程师"></a></td>
    <td align="center" valign="middle"><a href="${ctx}/homePage/toLogin.do" target="_blank"><img  id="img12" src="${ctxStatic}/image/wk_13.png" alt="登录"></a></td>
    <td align="center" valign="middle"><a href="${ctx}/homePage/toLogin.do" target="_blank"><img  id="img12" src="${ctxStatic}/image/wk_12.png" alt="注册"></a></td>
  </tr>
</table>
</div>
<iframe scrolling="no" src="${ctx}/homePage/toFooter.do" height="161" style="margin-top:20px;"></iframe>
</div>
</body>
</html>
