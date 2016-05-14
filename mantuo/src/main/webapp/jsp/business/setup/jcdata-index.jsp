<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ page import="com.ccthanking.framework.Constants"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<%
User user = RestContext.getCurrentUser();
String user_uid = "";
if(user!=null){
	user_uid = user.getIdCard();
}else{
	response.sendRedirect("/"+Constants.APP_NAME);
	return;
}
%>
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
					<div class="col-xs-12">
						<form class="form-inline"  id="queryForm"
							style="border:solid 1px #ddd;padding:3px;margin-bottom:5px;vertical-align:middle;">
						 <!-- <input id="PROJECT_UID" type="hidden" name="p.PROJECT_UID" fieldname="p.PROJECT_UID" operation="="/>  
								<div class="form-group">
									<label>设备名称</label>
								</div>
								<div class="form-group">
									<input class="form-control"
										style="width: 150px;" name="EQUIPMENT_NAME"
										fieldname="EQUIPMENT_NAME" id="EQUIPMENT_NAME"
										operation="like" logic="and">
								</div>
								&nbsp;&nbsp;
								<button id="search" class="btn btn-link btn-sm" type="button">
									<i class="#ace-icon glyphicon glyphicon-search  bigger-110"></i>查询
								</button>
								&nbsp;&nbsp;
								-->
								<button id="new" class="btn btn-link btn-sm red" type="button">
									<i class="#ace-icon glyphicon glyphicon-plus  bigger-110"></i>数据导入
								</button>
								
								<div class="form-group" style="float: right;">
									<label><h5>数据模板下载:</h5></label>
									<a href="template/基坑施工测量2011-12-10_垂直位移_已整理.xls">垂直位移</a>&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="template/基坑施工测量2011-12-10_水平位移_已整理.xls">水平位移</a>&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="template/基坑施工测斜12-10_已整理.xls">测斜</a>&nbsp;&nbsp;
								</div>
								
						  </form>
						   <table   sortname="JC_DATA_UID" multiselect=false  rownum="1000000" sortorder="asc"  class=""  id="grid_table" 
									action="${ctx}/jkjc/jcJcDataController?query" >
								<tr>
								   
								   <!--   <th name="JC_DATA_UID" formatter="formatEdit" width="1" align="center">操作</th> -->
									<th name="REPORT_CODE"  width="2" align="center">文件名称</th>
									<th name="VERTICAL_VALUE"  width="2" align="center">上传人</th>
									<th name="HORIZONTAL_VALUE" width="2" align="center" >上传时间</th>
									<!-- 
									<th name="PUMP_STATUS" width="2" align="center" >水泵运行状态</th>
									<th name="WATER_YIELD" width="2" align="center" >出水量(立方米/秒)</th>
									<th name="ELECTRICITY" width="2" align="center" >耗电(KW)</th>
									 -->
								</tr> 
							</table> 
						
						<script type="text/javascript">
							var $path_base = "/";
						</script>
			</div>
		</div>
	</div>
		<div id="jldwUser-input" class="modal"></div>

<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
	<script
		src="${pageContext.request.contextPath }/assets/js/jquery-ui.custom.min.js"></script>
		
<script type="text/javascript">

var scripts =[null];
ace.load_ajax_scripts(scripts, function() {
	var xmUid = $("#project_uid").val();
	$("#PROJECT_UID").val(xmUid);
	init();
	var gridwidth=$(".page-content").width();
	JqgridManage.initJqgrid(grid_table,queryForm,gridwidth);
	//$("#grid_table").setGridHeight($(window).height() - 320);
	$('.ajax-loading-overlay').remove();
});

function init(){
	$('#search').click(function() {
		jQuery("#grid_table").jqGrid().trigger("reloadGrid");
	});

	$("#Btnclean").click(function(){
		 $("#queryForm").clearFormResult();
	     $('#search').click();
	});
	
	$('#new').click(function() {
		$('#new').attr("data-target","jldwUser-input");
		$('#jldwUser-input').removeData("bs.modal");
		$("#jldwUser-input").empty();
		$('#jldwUser-input').modal({
			backdrop: 'static'
		});

		$.get("${ctx}/jsp/business/setup/jcimport.jsp",function(data) {
			$("#jldwUser-input").empty();
			$("#jldwUser-input").html("");
			$("#jldwUser-input").html(data);
		});
	});

}


function formatEdit(cellvalue, opts, rowObject){
//获取 设备信息的uid及设备euid
	var jceid = rowObject.JK_EQUIP_INFO_UID;
	var euid = rowObject.EQUIPMENT_UID;
	var showHtml ="<div class='hidden-sm hidden-xs action-buttons'>";			        	
		showHtml +="<a class=\"blue\" title=\"查看\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		showHtml +=	" onclick=\"eidtRy('"+jceid+"','"+euid+"');\">";
		showHtml +="<span class=\"glyphicon glyphicon-edit bigger-110\"></i>";
		showHtml +="</a> &nbsp;";
	return 	showHtml;	
}

function eidtRy(jceuid,euid){
$('#new').attr("data-target","jldwUser-input");
	$('#jldwUser-input').removeData("bs.modal");
	$("#jldwUser-input").empty();
	$('#jldwUser-input').modal({
		backdrop: 'static'
	});
	$.get("${ctx}/jsp/business/setup/projectJcsb-update.jsp?JCUID="+jceuid+"&PROJECT_UID="+$("#PROJECT_UID").val()+"&EUID="+euid,function(data) {
		$("#jldwUser-input").empty();
		$("#jldwUser-input").html("");
		$("#jldwUser-input").html(data);
	});
}
</script>
	<form id="gdzxtformid" method="post" >
		<input type="hidden" name="PROJECT_UID" id="PROJECT_UID" >
	</form>
</body>
</html>