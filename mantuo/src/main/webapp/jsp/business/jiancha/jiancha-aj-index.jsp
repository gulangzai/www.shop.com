<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<title><fmt:message key="ui.title" /></title>


<body class="no-skin">
	<div class="main-container" id="main-container">
			<div class="page-content" id="inside">
				<div class="col-sm-12">
		    <!-- form 开始 位置 -->
					<form class="form-inline"  id="queryForm" width="100%"
					   role="form" style="border:solid 1px #ddd;height:50px;line-height:50px;vertical-align:middle;">
						 <input type="hidden" id="JIANCHA_TYPE" value="AJ" logic="and" fieldname="t.JIANCHA_TYPE" operation="=" fieldtype="text">
						 <input type="hidden" id="gproject_uid"  logic="and" fieldname="t.project_uid" operation="=" fieldtype="text"> 
						<div style="float: right;">
							 <div  class="form-group" >
									<button class="btn btn-link btn-sm" type="button" onclick="goAdd();">
										<i class="colorshow ace-icon glyphicon glyphicon-plus"></i>
										<span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">添加</span>
									</button>
							 </div>
						</div>
					</form>
					    <table   sortname="t.JIANCHA_BZ_UID" multiselect=false  rownum="10000" sortorder="asc"   id="grid_table" 
									action="${ctx}/pmjiancha/pmJianchaBzController?query" >
							<tr>
								<th name="LEVEL_NAME"  width="3" maxlength="100" align="center">检查级别</th>
								<!-- <th name="BZGF_UID"  width="5" maxlength="100" align="center">对应标准规范</th>  -->
								<th name="CONTENT"  width="6" align="center">检查内容</th>
								<th name="USER_NAME"  width="2" align="center">备注</th>
								<th name="JIANCHA_BZ_UID"  width="2" align=center formatter="operate">操作</th>
							</tr> 
						</table> 
						<script type="text/javascript">
							var $path_base = "/";
						</script>
				</div>
			</div>
		</div>
		<div id="jiancha-input" class="modal"></div>

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/convertJson.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/default.js?version=20131206"> </script>
	
<script type="text/javascript">
var controllername="${pageContext.request.contextPath }/pmjiancha/pmJianchaBzController";
var scripts =[null];

ace.load_ajax_scripts(scripts, function() {
	$("#gproject_uid").val($("#project_uid").val());
	var gridwidth=$("#queryForm").width()+1;
	 JqgridManage.initJqgrid(grid_table,queryForm,gridwidth);
	 $("#grid_table").setGridHeight($(window).height()-250);
	//改变浏览器大小自适应
		$(window).on(
				'resize.jqGrid',
				function() {
					$("#grid_table").jqGrid(    //jqGridtable 自适应width
							'setGridWidth',
							$("#queryForm").width()+1);
				});
		$(window).triggerHandler('resize.jqGrid');
	//权限检查
	checkBtnAuthority($("#project_uid").val(),$("#projectUserId").val(),"12050301","btn_new"); 
});



function reloadGrid(){
	jQuery("#grid_table").jqGrid().trigger("reloadGrid");
}


function operate (cellvalue, opts, rowObject){
	var id = rowObject.JIANCHA_BZ_UID;
	var returnHTML  = "<div class=\"action-buttons\" style=\"cursor: pointer;\">";
		returnHTML +=	"<a class=\"blue\" title=\"查看\" style=\"background: none;\" onclick=\"seeXx('"+id+"')\" data-target=\"updateclxx-input\">";
		returnHTML +=		"&nbsp;<i class=\"glyphicon glyphicon-file bigger-110\"></i>&nbsp;";
		returnHTML +=	"</a> ";	
		if(checkTabAuthority($("#project_uid").val(),$("#projectUserId").val(),"12050302")){
			returnHTML +="<a class=\"\" title=\"修改\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
			returnHTML +="onclick=\"updateXx('"+id+"')\">" ;
			returnHTML +="<span class=\"glyphicon glyphicon-edit bigger-110\"></span>" ;
			returnHTML +="</a>&nbsp;";
		}
		if(checkTabAuthority($("#project_uid").val(),$("#projectUserId").val(),"12050303")){
			returnHTML +=	"<a class=\"\" title=\"删除\" style=\"background: none;\" onclick=\"deleteXx('"+id+"')\" data-target=\"updateclxx-input\">";
			returnHTML +=		"&nbsp;<i class=\"glyphicon colorshow1 glyphicon-trash bigger-110\"></i>&nbsp;";
			returnHTML +=	"</a>";
		}

		returnHTML +=	"</div>";
	return returnHTML;
}

//删除
function deleteXx(id){
	bootbox.confirm("确认删除吗？", function(result) {
		if(result){
			$.ajax({
				url : controllername+"?deleteById",
				cache : false,
				async :	false,
				data : {"JIANCHA_BZ_UID":id},
				dataType : "json",  
				type : 'post',
				success : function(response) {
					if(response.success){
						xAlert("信息提示","删除成功！");
						reloadGrid();
					}else{
						xAlert("信息提示","更新失败，请联系管理员！");
					}
				}
			});
			
		}else{
			return;
		}
	});	
}

function updateXx(id){
	$("#jiancha-input").removeData("bs.modal");
	$("#jiancha-input").empty();
	$("#jiancha-input").modal({
		backdrop: 'static'
	});

	$.get("${ctx}/jsp/business/jiancha/jiancha-aj-update.jsp",{
		"jc_uid":id
		},function(data){
		$("#jiancha-input").empty();
		$("#jiancha-input").html("");
		$("#jiancha-input").html(data);
	});
}

function seeXx(id){
	$("#jiancha-input").removeData("bs.modal");
	$("#jiancha-input").empty();
	$("#jiancha-input").modal({
		backdrop: 'static'
	});

	$.get("${ctx}/jsp/business/jiancha/jiancha-aj-view.jsp",{
		"jc_uid":id
		},function(data){
		$("#jiancha-input").empty();
		$("#jiancha-input").html("");
		$("#jiancha-input").html(data);
	});
}

//添加
function goAdd(){
	
	$('#jiancha-input').removeData("bs.modal");
	$("#jiancha-input").empty();
	$('#jiancha-input').modal({
		backdrop: 'static'
	});
		
	$.get("${ctx}/jsp/business/jiancha/jiancha-aj-add.jsp",
		{
		"PROJECT_UID":$("#project_uid").val(),
		"p_uid":$('#p_uid').val(),
		"p_name":$('#p_name').val()
		},function(data) {
		$("#jiancha-input").empty();
		$("#jiancha-input").html("");
		$("#jiancha-input").html(data);
	});
	
}

</script>
</body>
</html>