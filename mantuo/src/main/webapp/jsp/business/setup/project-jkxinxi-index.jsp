<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>

<title><fmt:message key="ui.title" /></title>

<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jkjc-btnStyle.css" type="text/css"></link>
<style>
</style>
</head>
<body >
	<div class="main-container" id="main-container">
			<div class="page-content" id="insideJKXX">
		    <!-- form 开始 位置 -->
					<form class="form-inline"  id="queryFormJKXX" width="100%"
					   role="form" style="border:solid 1px #ddd;height:50px;line-height:50px;vertical-align:middle;">
						 <input id="PROJECT_UID_JKXX" type="hidden" name="PROJECT_UID" fieldname="PROJECT_UID" operation="="/> 
						<div class="form-group">
						  <span id="getSelect00000" style="font-size:15px;line-height: 50px;">&nbsp;</span>
						</div>
						<div style="float: right;">
						     <div style="display:none;"  class="form-group" >
								<a data-target="" id="jkxinxiAdd" data-toggle="modal">
									<button id="jkAdd" class="btn btn-link btn-sm" type="button"><i class="ace-icon glyphicon glyphicon-plus"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">添加</span></button>
								</a>
							 </div>
							 &nbsp; 
							<div class="form-group">
							  <input class="searchContent form-control" placeholder="请输入项目名称"style="width: 150px;height:28px;" name="" fieldname="PROJECT_NAME" id="" operation="like" logic="and">
							  <button id="searchBox" class="btn btn-link btn-sm" type="button">
									<i class="#ace-icon glyphicon  bigger-110"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">查询</span>
							  </button>
							</div>
						</div>	
					 </form>
					    <table   sortname="PROJECT_UID" multiselect=false  rownum="1000000" sortorder="asc"  class=""  id="grid_table_jkxinxi" 
									action="${ctx}/project/buProjectController?queryDataJSON" >
							<tr>
								<th name="PROJECT_NAME"  width="2" align="center">项目名称</th>
								<th name="JK_DEEP" width="3" align="center" >基坑的深度（米）</th>
								<th name="JK_LEVEL" width="2" align="center" formatter="doJkdengji">基坑的等级</th>
								<th name="JKZH_STRUC_TYPE" width="2" align="center" >支护结构类型</th>
								<th name="PROJECT_UID" width="2" align="center" formatter="doJkUpdate" >编辑</th>
							</tr> 
						</table> 
						<script type="text/javascript">
							var $path_base = "/";
						</script>
				</div>
			</div>
		<div id="project-jkxinxi" class="modal"></div>

<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
    <script
		src="${pageContext.request.contextPath }/assets/js/jquery-ui.custom.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript">
var controlletname="${pageContext.request.contextPath }/project/buProjectController";
var scripts =[null];
var gridwidth;
var setHeight;
ace.load_ajax_scripts(scripts, function() {
	init();
	var xmUid = $("#project_uid").val();
	$("#PROJECT_UID_JKXX").val(xmUid);
	//$("#getSelect".val();
	gridwidth=$("#insideJKXX").width();
	//gridwidth -=250;
	//设置 放置 表格的 div的宽度
	//$("#tbDiv").css("width",gridwidth);
	JqgridManage.initJqgrid(grid_table_jkxinxi,queryFormJKXX,gridwidth); 
	setHeight = $(window).height();
	
	if(navigator.userAgent.indexOf("Firefox") > -1){
	   setHeight = setHeight+50;
	}
	
	 $("#grid_table_jkxinxi").setGridHeight(setHeight - 320); 
});

function init(){	
    //查询按钮 (报表)
	$("#searchBox").click(function(){
	  jQuery("#grid_table_jkxinxi").jqGrid().trigger("reloadGrid");
	}); 

}

function doJkdengji(cellvalue, opts, rowObject){
	var jkdengji = rowObject.JK_LEVEL;
	var jkdj = jkdengji;
	if(jkdengji == "2"){
	jkdj = "二级";
	}else if(jkdengji == "1"){
	jkdj = "一级";
	}else if(jkdengji == "3"){
	jkdj = "三级";
	}
    return jkdj;
}

function doJkUpdate(cellvalue, opts, rowObject){
	var tid = rowObject.PROJECT_UID;

	var showHtml ="<div class='hidden-sm hidden-xs action-buttons'>";			        	
		showHtml +="<a class=\"blue\" title=\"查看\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		showHtml +=	" onclick=\"eidtTid('"+tid+"');\">";
		showHtml +="<span class=\"glyphicon glyphicon-edit bigger-110\"></i>";
		showHtml +="</a> &nbsp;";
	        	
	return 	showHtml;
}

function eidtTid(tid){
$('#jkxinxiAdd').attr("data-target","project-jkxinxi");
	$('#project-jkxinxi').removeData("bs.modal");
	$("#project-jkxinxi").empty();
	$('#project-jkxinxi').modal({
		backdrop: 'static'
	});

	$.get("${ctx}/jsp/business/setup/project-jkxinxi-edity.jsp?PROJECT_UID="+$("#project_uid").val(),function(data) {
		$("#project-jkxinxi").empty();
		$("#project-jkxinxi").html("");
		$("#project-jkxinxi").html(data);
	});

}



</script>
	<form id="gdzxtformid" method="post" >
		<input type="hidden" name="gdzxt_gcid" id="gdzxt_gcid" >
	</form>
</body>
</html>