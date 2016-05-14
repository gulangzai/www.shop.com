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
			<div class="page-content" id="inside">
		    <!-- form 开始 位置 -->
					<form class="form-inline"  id="queryForm02" width="100%"
					   role="form" style="border:solid 1px #ddd;height:50px;line-height:50px;vertical-align:middle;">
						 <input id="PROJECT_UID" type="hidden" name="t.PROJECT_UID" fieldname="bp.PROJECT_UID" operation="="/> 
						<div class="form-group">
						  <span id="getSelect" style="font-size:15px;line-height: 50px;">&nbsp;</span>
						  <input id="ClickType" type="hidden" value=""/>
						</div>
						<div style="float: right;">
						     <div style="display:none;"  class="form-group" >
								<a data-target="" id="newAdd" data-toggle="modal">
									<button id="btnAdd" class="btn btn-link btn-sm" type="button"><i class="ace-icon glyphicon glyphicon-plus"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">添加</span></button>
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
					    <table   sortname="PROJECT_UID" multiselect=false  rownum="1000000" sortorder="asc"  class=""  id="grid_table02" 
									action="${ctx}/project/buProjectController?queryDataJSON" >
							<tr>
								<th name="PROJECT_NAME"  width="2" align="center">项目名称</th>
								<th name="PROJECT_DESC"  width="3" align="center">项目简介</th>
								<th name="PROJECT_ADDRESS" width="3" align="center" >项目地址</th>
								<th name="BEGIN_DATE" width="2" align="center" >开工日期</th>
								<th name="END_DATE" width="2" align="center" >完工日期</th>
								<th name="ZHANDI_MIANJI" width="2" align="center" >占地面积</th>
								<th name="JIANZHU_MIANJI" width="2" align="center" >建筑面积</th>
								<th name="ZONG_TOUZI" width="2" align="center" >总投资</th>
							    <th name="USER_NAME" width="2" align="center" >创建人</th>
								<th name="CREATED_DATE" width="2" align="center" formatter ="createDate()">创建日期</th>
								
								<th name="PROJECT_UID" width="2" align="center" formatter="doProjectUpdate" >编辑</th>
							</tr> 
						</table> 
						<script type="text/javascript">
							var $path_base = "/";
						</script>
				</div>
			</div>
		<div id="project-xinxi" class="modal"></div>

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
	$("#PROJECT_UID").val(xmUid);
	//$("#getSelect".val();
	gridwidth=$("#inside").width();
	//gridwidth -=250;
	//设置 放置 表格的 div的宽度
	//$("#tbDiv").css("width",gridwidth);
	JqgridManage.initJqgrid(grid_table02,queryForm02,gridwidth); 
	setHeight = $(window).height();
	
	if(navigator.userAgent.indexOf("Firefox") > -1){
	   setHeight = setHeight+50;
	}
	
	 $("#grid_table02").setGridHeight(setHeight - 320); 
});

function init(){	
    //查询按钮 (报表)
	$("#searchBox").click(function(){
	  jQuery("#grid_table02").jqGrid().trigger("reloadGrid");
	}); 

}

//创建 日期
function createDate(){

}
function doProjectUpdate(cellvalue, opts, rowObject){
	var tid = rowObject.PROJECT_UID;
	var showHtml ="<div class='hidden-sm hidden-xs action-buttons'>";			        	
		showHtml +="<a class=\"blue\" title=\"查看\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		showHtml +=	" onclick=\"eidtTid();\">";
		showHtml +="<span class=\"glyphicon glyphicon-edit bigger-110\"></i>";
		showHtml +="</a> &nbsp;";
	        	
	return 	showHtml;
}

function eidtTid(){
$('#newAdd').attr("data-target","project-xinxi");
	$('#project-xinxi').removeData("bs.modal");
	$("#project-xinxi").empty();
	$('#project-xinxi').modal({
		backdrop: 'static'
	});

	$.get("${ctx}/jsp/business/setup/project-xinxi-update.jsp?PROJECT_UID="+$("#project_uid").val(),function(data) {
		$("#project-xinxi").empty();
		$("#project-xinxi").html("");
		$("#project-xinxi").html(data);
	});

}



</script>
	<form id="gdzxtformid" method="post" >
		<input type="hidden" name="gdzxt_gcid" id="gdzxt_gcid" >
	</form>
</body>
</html>