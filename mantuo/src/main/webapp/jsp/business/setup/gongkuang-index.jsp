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
					<form class="form-inline"  id="queryForm" width="100%"
					   role="form" style="border:solid 1px #ddd;height:50px;line-height:50px;vertical-align:middle;">
							<input id="PROJECT_UID" type="hidden" name="p.PROJECT_UID" fieldname="p.PROJECT_UID" operation="="/> 
								&nbsp;&nbsp;
								<div class="form-group" style="float: right; ">
									<a data-target="" id="new" data-toggle="modal" >
										<button id="btn_new" class="btn btn-link btn-sm" type="button"><i class="ace-icon glyphicon glyphicon-plus"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">添加</span></button>
									</a>
								</div>
							
						</form>
		
					    <table   sortname="g.GONGKUANG_UID" multiselect=false  rownum="1000000" sortorder="asc"  class=""  id="grid_table" 
									action="${ctx}/xmgk/pmGongkuangController?query" >
							<tr>
								<th name="JINDU"  maxlength="100" align="center">工况</th>
								<th name=""   align="center" formatter="formatFj">对应模型</th>
								<th name="CREATE_DATE"   align="center">最近操作时间</th>
								<th name="GONGKUANG_UID"  align="center" formatter="doUpdate">操作</th>
								
							</tr> 
						</table> 
						<script type="text/javascript">
							var $path_base = "/";//this will be used in gritter alerts containing images
						</script>
							<!-- PAGE CONTENT ENDS -->
						</div>
				    </div>
				</div>
				<div id="xmgk-input" class="modal"></div>

<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
	<script
		src="${pageContext.request.contextPath }/assets/js/jquery-ui.custom.min.js"></script>
		
<script type="text/javascript">

var scripts =[null];
ace.load_ajax_scripts(scripts, function() {
	init();
	$("#PROJECT_UID").val($("#project_uid").val());
	var gridwidth=$(".page-content").width();
	JqgridManage.initJqgrid(grid_table,queryForm,gridwidth);
	$('.ajax-loading-overlay').remove();

});

function init(){
	$('#search').click(function() {
		 _reload();
	});

	
	$('#new').click(function() {
		$('#new').attr("data-target","xmgk-input");
		$('#xmgk-input').removeData("bs.modal");
		$("#xmgk-input").empty();
		$('#xmgk-input').modal({
			backdrop: 'static'
		});

		$.get("${ctx}/jsp/business/setup/gongkuang-add.jsp?PROJECT_UID="+$("#project_uid").val(),function(data) {
			$("#xmgk-input").empty();
			$("#xmgk-input").html("");
			$("#xmgk-input").html(data);
		});
	});

}


function _reload(){
	jQuery("#grid_table").jqGrid().trigger("reloadGrid");
}

function removeData(gkid){
	bootbox.confirm("确认删除吗？", function(result) {
		if (result) {						
			$.ajax({
				url :contextPath+'/xmgk/pmGongkuangController?deleteByid',
				data : {"GONGKUANG_UID":gkid},
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
					if(response.success){
						xAlert("信息提示","删除成功",1);
					}else{
						xAlert("信息提示","删除失败,请联系管理员",1);
					}
					_reload();

			      }
			});
						
		} else {
			return;
		}
	});
}

//显示附件
function formatFj(cellvalue, opts, rowObject){
	var showHtml = "";
	$.ajax({
		url: "${pageContext.request.contextPath }/xmgk/pmGongkuangController?queryFileByType&targetUid=" + rowObject.GONGKUANG_UID + "&file_type=10201",
		cache: false,
		async: false,
		dataType: "json",
		success: function(result) {
			if (result.obj != null && result.obj != "") {
				$.each(result.obj, function() {
					if(this.FILE_NAME.length>100){
						showHtml +="<span>"+this.FILE_NAME.substring(0,100)+"...</span>";
					}else{
						showHtml +="<span>"+this.FILE_NAME+"</span>";
					}
				});
			}
	
		}
	});
	return 	showHtml;
}

//查看
function doUpdate(cellvalue, opts, rowObject){
	var gkuid = rowObject.GONGKUANG_UID;
    var jindu = rowObject.JINDU;
	var showHtml ="<div class='hidden-sm hidden-xs action-buttons'>";			        	
		showHtml +="<a class=\"blue\" title=\"查看\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		showHtml +=	" onclick=\"eidtXmgk('"+gkuid+"');\">";
		showHtml +="<span class=\"glyphicon glyphicon-edit bigger-110\"></i>";
		showHtml +="</a> &nbsp;";
		showHtml +="<a class=\"red\" title=\"删除\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		showHtml +=	" onclick=\"removeData('"+gkuid+"');\">";
		showHtml +="<span class=\"glyphicon  glyphicon-trash bigger-110\"></i>";
		showHtml +="</a>&nbsp;";
		showHtml +="<a class=\"blue\" title=\"查看附件历史\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		showHtml +=	" onclick=\"showFileList('"+gkuid+"','"+jindu+"');\">";
		showHtml +="<span class=\"glyphicon glyphicon-file bigger-110\"></i>";
		showHtml +="</a>&nbsp;";
	        	
	return 	showHtml;
}

function eidtXmgk(gkuid){
$('#new').attr("data-target","xmgk-input");
	$('#xmgk-input').removeData("bs.modal");
	$("#xmgk-input").empty();
	$('#xmgk-input').modal({
		backdrop: 'static'
	});

	$.get("${ctx}/jsp/business/setup/gongkuang-update.jsp?PROJECT_UID="+$("#project_uid").val()+"&GKUID="+gkuid,function(data) {
		$("#xmgk-input").empty();
		$("#xmgk-input").html("");
		$("#xmgk-input").html(data);
	});

}

function showFileList(gongkuang_uid,jindu){
$('#new').attr("data-target","xmgk-input");
	$('#xmgk-input').removeData("bs.modal");
	$("#xmgk-input").empty();
	$('#xmgk-input').modal({
		backdrop: 'static'
	});

	$.get("${ctx}/jsp/business/setup/gongkuang-fileList.jsp?gongkuang_uid="+gongkuang_uid+"&jindu="+jindu,function(data) {
		$("#xmgk-input").empty();
		$("#xmgk-input").html("");
		$("#xmgk-input").html(data);
	});

}




</script>
</body>
</html>