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
							
								<div class="form-group">
									<label>用户名</label>
								</div>
								<div class="form-group">
									<input class="form-control"
										style="width: 150px;" name="PROJECT_NAME"
										fieldname="PROJECT_NAME" id="PROJECT_NAME"
										operation="like" logic="and">
								</div>
								&nbsp;&nbsp;
								<button id="search" class="btn btn-link btn-sm" type="button">
									<i class="#ace-icon glyphicon glyphicon-search  bigger-110"></i>查询
								</button>
								<button id="Btnclean" class="btn btn-link btn-sm" type="button">
									<i class="#ace-icon glyphicon glyphicon-trash  bigger-110"></i>清空
								</button>
								
								&nbsp;&nbsp;
								<div class="form-group" >
									<a data-target="" id="new" data-toggle="modal">
										<button id="btn_new" class="btn btn-primary btn-sm" type="button"><i class="icon-edit bigger-110"></i>添加用户</button>
									</a>
								</div>
							
						</form>
		
						<table  sortname="PROJECT_UID" multiselect=false  rownum="10" sortorder="desc"  class="auto_startgrid"  id="grid_table" 
									action="${ctx}/project/buProjectUserController?query" >
							<tr>
								<th name="PROJECT_NAME"  width="8" formatter="jqgridactions" align="center">操作</th>
								<th name="USER_NAME"  width="15" >用户名</th>
								<th name="LOGON_NAME" width="10" align="center" >登录名</th>
								<th name="EMAIL" width="10" align="center" >邮箱</th>
								<th name="MOBILE" width="10" align="right" >电话</th>
								<th name="ENABLED" width="10" align="right" formatter="jqgridstatus">是否有效</th>
								
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
	var gridwidth=$(".page-content").width();
	JqgridManage.initJqgrid(grid_table,queryForm,gridwidth);

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

		$.get("${ctx}/jsp/business/setup/projectUser-add.jsp",function(data) {
			$("#jldwUser-input").empty();
			$("#jldwUser-input").html("");
			$("#jldwUser-input").html(data);
		});
	});

}


function jqgridactions(cellvalue, opts, rowObject){
	var puid = rowObject.PROJECT_USER_UID;
	var name = rowObject.USER_NAME;
	var	showHtml ="<a class=\"blue\" title=\"查看\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		showHtml +=	" onclick=\"eidtRy('"+puid+"');\"><i><span class=\"glyphicon glyphicon-edit bigger-110\"></i></a> &nbsp;";
	return 	showHtml;	        	
}

function eidtRy(puid){
	$("#gdzxt_gcid").val(puid);
    var f =document.getElementById('gdzxtformid');
    var url='${pageContext.request.contextPath }/jsp/framework/gdzxt/frame_gdzxt_main.jsp';
    //var url='${pageContext.request.contextPath }/jsp/framework/project/project_main.jsp';
	f.action=url;
	//f.target="_blank"; 
	f.submit();
}
</script>
	<form id="gdzxtformid" method="post" >
		<input type="hidden" name="gdzxt_gcid" id="gdzxt_gcid" >
	</form>
</body>
</html>