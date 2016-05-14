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
					 <div class="col-xs-12">
						<form class="form-inline"  id="queryForm"
							style="border:solid 1px #ddd;padding:3px;margin-bottom:5px;vertical-align:middle;">
							<input fieldname="ji.ITEM_TYPE" value="降水" type="hidden" operation="=" logic="and">
							<input fieldname="pp.PROJECT_UID" id="projectUid" type="hidden" operation="=" logic="and">
								<div class="form-group">
									<label>测点编号</label>
								</div>
								<div class="form-group">
									<input class="form-control"
										style="width: 150px;" name="POINT_CODE"
										fieldname="POINT_CODE" id="POINT_CODE"
										operation="=" logic="and">
								</div>
								&nbsp;&nbsp;
								<button id="search" class="btn btn-link btn-sm" type="button">
									<i class="#ace-icon glyphicon glyphicon-search  bigger-110"></i>查询
								</button>
								
								&nbsp;&nbsp;
								<div class="form-group" >
									<a data-target="" id="new" data-toggle="modal">
										
									</a>
								</div>
							
						</form>
		
						<table  sortname="pp.POINT_CODE" multiselect=false  rownum="10" sortorder="asc"  class="auto_startgrid"  id="grid_table" 
									action="${ctx}/jkjc/jcPrjPointsController?query" jqgridheight="327">
							<tr>
								
								<th name="POINT_CODE"  width="15" align="center" >测点编号</th>
								<th name="INIT_HEIGHT" width="10" align="center" >孔口高程(m)</th>
								<th name="EQUIPMENT_UID" width="10" align="center" >泵设备类型</th>
								<th name="PRJ_POINTS_UID"  width="8" formatter="jqgridactions" align="center">操作</th>
								
							</tr>
						</table>
						<script type="text/javascript">
							var $path_base = "/";//this will be used in gritter alerts containing images
						</script>
							<!-- PAGE CONTENT ENDS -->
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
	init();
	$('#projectUid').val($('#project_uid').val());
	var gridwidth=$(".page-content").width();
	JqgridManage.initJqgrid(grid_table,queryForm,gridwidth);
	$('.ajax-loading-overlay').remove();

});

function init(){
	
	$('#search').click(function() {
		_reload();
	});

}

function _reload(){
	jQuery("#grid_table").jqGrid().trigger("reloadGrid");
}


function jqgridactions(cellvalue, opts, rowObject){
	var puid = rowObject.PRJ_POINTS_UID;
	var	showHtml ="<a class=\"blue\" title=\"修改\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		showHtml +=	" onclick=\"modifyData('"+puid+"');\"><i><span class=\"glyphicon glyphicon-edit bigger-110\"></i></a> &nbsp;";
	return 	showHtml;	        	
}

function modifyData(puid){
	$('#new').attr("data-target","jldwUser-input");
	$('#jldwUser-input').removeData("bs.modal");
	$("#jldwUser-input").empty();
	$('#jldwUser-input').modal({
		backdrop: 'static'
	});

	$.get("${ctx}/jsp/business/setup/jsSetup-modify.jsp?PRJ_POINTS_UID="+puid,function(data) {
		$("#jldwUser-input").empty();
		$("#jldwUser-input").html("");
		$("#jldwUser-input").html(data);
	});
}
</script>
	<form id="gdzxtformid" method="post" >
		<input type="hidden" name="gdzxt_gcid" id="gdzxt_gcid" >
	</form>
</body>
</html>