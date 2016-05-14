<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>

<title><fmt:message key="ui.title" /></title>
</head>
<body class="no-skin">

	<div class="main-container" id="main-container">
				<div class="page-content">
					<div class="row" >
					<div class="col-xs-12">
						<form class="form-inline"  id="queryForm"
							style="border:solid 1px #ddd;padding:3px;margin-bottom:5px;vertical-align:middle;">
							<input id="PROJECT_UID" type="hidden" fieldname="u.PROJECT_UID" operation="=" fieldtype="text"/>
								<div class="form-group">
									<label>姓名</label>
								</div>
								<div class="form-group">
									<input class="form-control"
										style="width: 150px;" name="USER_NAME"
										fieldname="USER_NAME" id="USER_NAME"
										operation="like" logic="and">
								</div>
								&nbsp;&nbsp;
								<div class="form-group">
									<label>登录名 </label>
								</div>
								<div class="form-group">
									<input class="form-control"
										style="width: 150px;" name="LOGON_NAME"
										fieldname="LOGON_NAME" id="LOGON_NAME"
										operation="like" logic="and">
								</div>
								&nbsp;&nbsp;
								<button id="search" class="btn btn-link btn-sm" type="button">
									<i class="#ace-icon glyphicon glyphicon-search  bigger-110"></i>查询
								</button>
								<!-- 
								<button id="Btnclean" class="btn btn-link btn-sm" type="button">
									<i class="#ace-icon glyphicon glyphicon-trash  bigger-110"></i>清空
								</button>
								
								&nbsp;&nbsp;
								 
								<div class="form-group" >
									<a data-target="" id="new" data-toggle="modal">
										<button id="btn_new" class="btn btn-primary btn-sm" type="button"><i class="icon-edit bigger-110"></i>添加用户</button>
									</a>
								</div>
								-->
						</form>
		
						<table  sortname="u.PROJECT_USER_UID" multiselect=false  rownum="10" sortorder="desc"  class="auto_startgrid"  id="grid_table" 
									action="${ctx}/project/buProjectUserController?queryList" >
							<tr>
								<th formatter="jqgridactions"  width="8" align="center" >操作</th>
								<th name="USER_NAME"  width="15" >姓名</th>
								<th name="LOGON_NAME"  width="10" >登录名</th>
								<th name="ENABLED" width="10" align="center" formatter="formatSTATUS">用户状态</th>
								<th name="CREATED_DATE" width="10" align="center" >创建时间</th>
							</tr>
						</table>
						<script type="text/javascript">
							var $path_base = "/";//this will be used in gritter alerts containing images
						</script>
							<!-- PAGE CONTENT ENDS -->
						</div>
					</div>
				</div>
			</div>
				<div id="jldwUser-input" class="modal"></div>
				<div id="jldwUserAuth-input" class="modal"></div>

<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
	<script
		src="${pageContext.request.contextPath }/assets/js/jquery-ui.custom.min.js"></script>
		
<script type="text/javascript">

var scripts =[null];
ace.load_ajax_scripts(scripts, function() {
	$("#PROJECT_UID").val($("#project_uid").val());
	init();
	var gridwidth=$(".page-content").width();
	JqgridManage.initJqgrid(grid_table,queryForm,gridwidth);
	//改变浏览器大小自适应
	$(window).on(
			'resize.jqGrid',
			function() {
				$("#grid_table").jqGrid(    //jqGridtable 自适应width
						'setGridWidth',
						$(".page-content").width());
			});
	$(window).triggerHandler('resize.jqGrid');
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
	
		$.get("${ctx}/jsp/framework/system/user/user-add.jsp",function(data) {
			$("#jldwUser-input").empty();
			$("#jldwUser-input").html("");
			$("#jldwUser-input").html(data);
		});
	});

}


function jqgridactions(cellvalue, opts, rowObject){
	var ryid = rowObject.PROJECT_USER_UID;
	var account = rowObject.LOGON_NAME;
	//var usey = rowObject.USE_Y;
	var showHtml ="<div class='hidden-sm hidden-xs action-buttons'>";	
	
		//showHtml +="<a class=\"blue\" title=\"查看\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		//showHtml +=	" onclick=\"eidtRy('"+ryid+"');\">";
		//showHtml +="<span class=\"glyphicon glyphicon-edit bigger-110\"></i>";
		//showHtml +="</a> &nbsp;";
	        	
		showHtml +="<a class=\"blue\" title=\"授予角色\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		showHtml +=	" onclick=\"awardRole('"+ryid+"','"+account+"');\">";
		showHtml +="<span class=\"ace-icon fa fa-user bigger-110\"></i>";
		showHtml +="</a> &nbsp;";
		
		//showHtml +="<a class=\"blue\" title=\"权限配置\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		//showHtml +=	" onclick=\"awardMenu('"+ryid+"');\">";
		//showHtml +="<span class=\"ace-icon fa fa-cogs bigger-110\"></i>";
		//showHtml +="</a> &nbsp;";
		showHtml +="</div>"
	return 	showHtml;	        	
}

function eidtRy(ryid){
	
	$('#new').attr("data-target","jldwUser-input");
	$('#jldwUser-input').removeData("bs.modal");
	$("#jldwUser-input").empty();
	$('#jldwUser-input').modal({
		backdrop: 'static'
	});

	$.get("${ctx}/jsp/framework/system/user/user-edit.jsp?ryid="+ryid,function(data) {
		$("#jldwUser-input").empty();
		$("#jldwUser-input").html("");
		$("#jldwUser-input").html(data);
	});
}

//授予角色
function awardRole(ryid,account){
	$('#new').attr("data-target","jldwUser-input");
	$('#jldwUser-input').removeData("bs.modal");
	$("#jldwUser-input").empty();
	$('#jldwUser-input').modal({
		backdrop: 'static'
	});

	$.get("${ctx}/jsp/framework/system/user/awardRole.jsp?ryid="+ryid+"&project_uid="+$("#project_uid").val(),function(data) {
		$("#jldwUser-input").empty();
		$("#jldwUser-input").html("");
		$("#jldwUser-input").html(data);
	});
	
}

//权限配置
function awardMenu(ryid){
	$('#new').attr("data-target","jldwUser-input");
	$('#jldwUser-input').removeData("bs.modal");
	$("#jldwUser-input").empty();
	$('#jldwUser-input').modal({
		backdrop: 'static'
	});

	$.get("${ctx}/jsp/framework/system/role/showMenu.jsp?ryid="+ryid+"&type=user",function(data) {
		$("#jldwUser-input").empty();
		$("#jldwUser-input").html("");
		$("#jldwUser-input").html(data);
	});	
}

function formatSTATUS(cellvalue, opts, rowObject){
	if(rowObject.ENABLED=="1"){
		return "有效";
	}else if(rowObject.ENABLED=="0"){
		return "无效";
	}
}


</script>
</body>
</html>