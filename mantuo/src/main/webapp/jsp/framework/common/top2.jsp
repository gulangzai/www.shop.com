<%@page import="com.ccthanking.framework.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>

<%
String permissions_code =(String)session.getAttribute("permissionsCode");

User user = RestContext.getCurrentUser();

String userid = "";
String username = "";
String uuid = ""; 
String dp = "";
String qyid = "";
String orgtype = "";
if(user!=null){
	userid = user.getAccount();
	username = user.getName();
	uuid = user.getUserSN();
	dp = user.getDepartment();
	qyid = user.getIdCard();
	orgtype = user.getAjzt();
}else{
	response.sendRedirect("/"+Constants.APP_NAME);
	return;
}
%>

