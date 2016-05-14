<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>

<title><fmt:message key="ui.title" /></title>

<style>
/**设置页面 布局 */
.page-content {
    background-color: #fff;
    position: relative;
    margin: 0;
    padding:3px 0px 0px 3px;
}
</style>
</head>
<body class="no-skin">
	<div class="main-container" id="main-container">
				<div class="page-content">
					 <div class="col-sm-12">
					 <!--   tabs-below-->
					    <div class="tabbable">
							 <ul id="myTab" class="nav nav-tabs">
							   <li class="active">
							      <a href="#home"   onclick="getHref(this)" data-toggle="tab">&nbsp;&nbsp;泵设备管理&nbsp;&nbsp;</a>
							   </li>
							   <li>
							      <a href="#profile"  onclick="getHref(this)" data-toggle="tab">施工应急预案</a>
							   </li>
							  <li>
							      <a href="#messages" onclick="getHref(this)" data-toggle="tab">负责人联络方式</a>
							   </li>
							   <li>
							      <a href="#company" onclick="getHref(this)" data-toggle="tab">抢修单位联系方式</a>
							   </li> 
							 </ul>
							
							 <div class="tab-content">
							   <div class="tab-pane in active" id="home">
							   </div>
							   <div class="tab-pane" id="profile">
							   </div>
							   <div class="tab-pane" id="messages">
							   </div>
							   <div class="tab-pane" id="company">
							   </div>
					    </div>
					</div>
				</div>
			</div>
		</div>

<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
    <script
		src="${pageContext.request.contextPath }/assets/js/jquery-ui.custom.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript">
var scripts =[null];
var gridwidth;
var setHeight;
ace.load_ajax_scripts(scripts, function() {
	//默认预选中 
    $("#home").load("jsp/business/jsjc/jsjc-bsbgl.jsp");
	
	var xmUid = $("#project_uid").val();
	$("#PROJECT_UID").val(xmUid);

	gridwidth=$(".page-content").width();
	gridwidth -=240;
	
	setHeight = $(window).height();
	
	if(navigator.userAgent.indexOf("Firefox") > -1){
	   setHeight = setHeight+50;
	}
	
});

function getHref(obj){
 if(obj.href.indexOf("#home") !=-1){
         $("#profile").html("");
         $("#messages").html("");
         $("#company").html("");
	     $("#home").load("jsp/business/jsjc/jsjc-bsbgl.jsp");
	    }else if(obj.href.indexOf("#profile") !=-1){
	     $("#home").html("");
         $("#messages").html("");
         $("#company").html("");
	     $("#profile").load("jsp/business/jsjc/jsjc-sgyjya.jsp");
	    }else if(obj.href.indexOf("#messages") !=-1){
	     $("#profile").html("");
         $("#home").html("");
         $("#company").html("");
	     $("#messages").load("jsp/business/jsjc/jsjc-fzrllfs.jsp");
	    }else if(obj.href.indexOf("#company") !=-1){
	     $("#profile").html("");
         $("#messages").html("");
         $("#home").html("");
	     $("#company").load("jsp/business/jsjc/jsjc-qxdwlxfs.jsp");
	    }
} 

</script>
	<form id="gdzxtformid" method="post" >
		<input type="hidden" name="gdzxt_gcid" id="gdzxt_gcid" >
	</form>
</body>
</html>