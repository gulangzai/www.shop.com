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
								<input type="hidden" id="itemType" fieldname="ji.ITEM_TYPE" fieldtype="text" operation="in" logic="and">
								<input fieldname="ji.PROJECT_UID" id="projectUid" type="hidden" operation="=" logic="and">
								<div class="form-group" style="padding: 5px 4px 6px;">
									<input type="checkbox" name="itemType" value="基坑" checked="checked" onclick="_reload();"> 基坑
								</div>
								<div class="form-group">
									<input type="checkbox" name="itemType" value="降水" checked="checked" onclick="_reload();"> 降水
								</div>
								<div class="form-group">
									<input type="checkbox" name="itemType" value="视频监控" checked="checked" onclick="_reload();"> 视频监控
								</div>
								<div class="form-group">
									<input type="checkbox" name="itemType" value="门禁" checked="checked" onclick="_reload();"> 门禁
								</div>
								<!--  
								&nbsp;&nbsp;
								<button id="search" class="btn btn-link btn-sm" type="button">
									<i class="#ace-icon glyphicon glyphicon-search  bigger-110"></i>查询
								</button>
								-->
								&nbsp;&nbsp;
								<div class="form-group" style="float: right; ">
									<a data-target="" id="new" data-toggle="modal" >
										<button id="btn_new" class="btn btn-link btn-sm" type="button"><i class="ace-icon glyphicon glyphicon-plus"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">添加</span></button>
									</a>
								</div>
							
						</form>
		
						<table  sortname="ji.JC_PRJ_ITEM_UID" multiselect=false  rownum="10" sortorder="asc"  class="auto_startgrid"  id="grid_table" 
									action="${ctx}/jkjc/jcPrjJcItemController?query" jqgridheight="327" >
							<tr>
								<th name="OBJECT_NAME"  width="20" >监测对象</th>
								<th name="JC_TYPE_NAME" width="10" align="center" >监测类型</th>
								<th name="JC_NAME" width="10" align="center" >监测项目名称</th>
								<th name="SHORT_NAME" width="10" align="center" >监测项短名称</th>
								<th name="PRE_CODE" width="8" align="center" >前缀</th>
								<th name="UNIT" width="8" align="center" >单位</th>
								<th name="SINGLE_WARN" width="8" align="center" >单次变化值上限</th>
								<th name="TOTAL_WARN1" width="8" align="center" >累计值下限</th>
								<th name="TOTAL_WARN2" width="8" align="center" >累计值上限</th>
								<th name="ITEM_TYPE" width="10" align="center" >监测项所属的二级菜单</th>
								<th name="JC_PRJ_ITEM_UID"  width="8" formatter="jqgridactions" align="center">操作</th>
								
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
	$('#itemType').val(getTypeV('itemType'));
	$('#search').click(function() {
		 _reload();
	});

	
	$('#new').click(function() {
		$('#new').attr("data-target","jldwUser-input");
		$('#jldwUser-input').removeData("bs.modal");
		$("#jldwUser-input").empty();
		$('#jldwUser-input').modal({
			backdrop: 'static'
		});

		$.get("${ctx}/jsp/business/setup/jcxwh-add.jsp",function(data) {
			$("#jldwUser-input").empty();
			$("#jldwUser-input").html("");
			$("#jldwUser-input").html(data);
		});
	});

}


function _reload(){
	$('#itemType').val(getTypeV('itemType'));
	jQuery("#grid_table").jqGrid().trigger("reloadGrid");
}


function jqgridactions(cellvalue, opts, rowObject){
	var puid = rowObject.JC_PRJ_ITEM_UID;
	var	showHtml ="<a class=\"blue\" title=\"修改\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		showHtml +=	" onclick=\"modifyData('"+puid+"');\"><i><span class=\"glyphicon glyphicon-edit bigger-110\"></i></a> &nbsp;";
		showHtml +=	" <a class=\"blue\" title=\"删除\" data-target=\"content_view\"  href=\"javascript:void(0);\"";
		showHtml +=	" onclick=\"removeData('"+puid+"');\"><i><span class=\"glyphicon glyphicon-remove bigger-110\"></i></a> &nbsp;";
	return 	showHtml;	        	
}

function modifyData(puid){
	$('#new').attr("data-target","jldwUser-input");
	$('#jldwUser-input').removeData("bs.modal");
	$("#jldwUser-input").empty();
	$('#jldwUser-input').modal({
		backdrop: 'static'
	});

	$.get("${ctx}/jsp/business/setup/jcxwh-modify.jsp?JC_PRJ_ITEM_UID="+puid,function(data) {
		$("#jldwUser-input").empty();
		$("#jldwUser-input").html("");
		$("#jldwUser-input").html(data);
	});
}

function removeData(puid){
	bootbox.confirm("确认删除吗？", function(result) {
		if (result) {						
			$.ajax({
				url :'${ctx}/jkjc/jcPrjJcItemController?delete',
				data : {"JC_PRJ_ITEM_UID":puid},
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
					if(response.success){
						xAlert("信息提示","删除成功",1);
						var a = window.parent;
						a._reload();
					}else{
						xAlert("信息提示","删除失败,请联系管理员",1);
					}

					
			      }
			});
						
		} else {
			return;
		}
	});
}

//获取选中值
function getTypeV(lname) {
	var v = "";
	$("input:checkbox[name='" + lname + "']:checked").each(function() {
		v += $(this).val() + ",";
	});
	if (v.length != 0) {
		v = v.substring(0, v.length - 1);
	}
	//不勾选 不显示
	if(v==""){
		v= "00";
	}
	return v;
}
</script>
	<form id="gdzxtformid" method="post" >
		<input type="hidden" name="gdzxt_gcid" id="gdzxt_gcid" >
	</form>
</body>
</html>