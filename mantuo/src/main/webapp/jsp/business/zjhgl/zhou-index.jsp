<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>

<title><fmt:message key="ui.title" /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jkjc-btnStyle.css" type="text/css"></link>
<style>
/**设置页面 布局 */
.page-content {
    background-color: #fff;
    position: relative;
    margin: 0;
    padding:3px 0px 0px 3px;
}

.col-xs-6, .col-sm-10 {
    position: relative;
    min-height: 1px;
    padding-left: 3px;
    padding-right: 3px;
} 

</style>
<body >
	<div class="main-container" id="main-container">
			<div class="page-content" >
				<div class="col-sm-12" id="inside">
		    <!-- form 开始 位置 -->
					<form class="form-inline"  id="queryForm" width="100%"
					   role="form" style="border:solid 1px #ddd;height:50px;line-height:50px;vertical-align:middle;">
						 <input id="PROJECT_UID" type="hidden" fieldname="PROJECT_UID" operation="="/> 
						<!--  <input id="sname" type="hidden" fieldname="sname" operation="=" value="0"/> -->
						<input id="date1" type="hidden" fieldname="PLAN_WEEK" operation="="/>
						<input id="date2" type="hidden" fieldname="PLAN_WEEK" operation="="/>
						<div style="float: right;">
							 <div id="btnAddDiv"  class="form-group" >
								<a data-target="" id="newAdd" data-toggle="modal">
									<button id="btn_new" class="btn btn-link btn-sm" type="button"><i class="colorshow ace-icon glyphicon glyphicon-plus"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">添加</span></button>
								</a>
							 </div>
							 &nbsp; 
							
						</div>
						<button id="shangzhou" class="btn btn-link btn-sm" type="button">
								<span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">< 上周</span>
						  </button>	
						 
						  
						  <button id="xiazhou" class="btn btn-link btn-sm" type="button">
								<span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">下周 ></span>
						  </button>	
					</form>
					    <table   sortname="SCSL_WEEK_PLAN_UID" multiselect=false  rownum="1000000" sortorder="asc"  class=""  id="grid_table" 
									action="${ctx}/pmscslweekplan/pmScslWeekPlanController?query" >
							<tr>
								<!-- <th name="PROJECT_NAME"   align="center">项目名称</th> -->
								<th name="ROOM_NUM" width="2"  align="center" >检查房号</th>
								<th name="JC_NEIRONG" width="5"  align="left">检查内容</th>
								<th name="JC_YAOQIU"  align="left" width="5">检查要求</th>
								<th name="JC_BIAOZHUN"  align="left" width="5">检查指标</th>
								<th name="JC_RESULT"  align="center" width="2">检查结果</th>
								<th name="JC_STATUS"  align="center" width="2">检查状态</th>
								<th name="SCSL_WEEK_PLAN_UID" align="center" width="3" formatter="doUpdate">操作</th>
							</tr> 
						</table> 
						<script type="text/javascript">
							var $path_base = "/";
						</script>
				</div>
			</div>
		</div>
		<div id="xmgk-input" class="modal"></div>

<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
    <script
		src="${pageContext.request.contextPath }/assets/js/jquery-ui.custom.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript">
var controlletname="${pageContext.request.contextPath }/pmscslweekplan/pmScslWeekPlanController";
var scripts =[null];
var gridwidth;
var setHeight;
ace.load_ajax_scripts(scripts, function() {
	init();


    $("#PROJECT_UID").val($("#project_uid").val());
	
	gridwidth=$("#inside").width();
	JqgridManage.initJqgrid(grid_table,queryForm,gridwidth); 
	setHeight = $(window).height();
	
	if(navigator.userAgent.indexOf("Firefox") > -1){
	   setHeight = setHeight+50;
	}
	
	 $("#grid_table").setGridHeight(setHeight - 320); 
	//改变浏览器大小自适应
		$(window).on(
				'resize.jqGrid',
				function() {
					$("#grid_table").jqGrid(    //jqGridtable 自适应width
							'setGridWidth',
							$("#inside").width());
				});
		$(window).triggerHandler('resize.jqGrid');
	//权限检查
	//checkBtnAuthority($("#project_uid").val(),$("#projectUserId").val(),"12060201","btn_new"); 	 
	 
});

function init(){
	
	$('#newAdd').click(function() {
	$('#newAdd').attr("data-target","xmgk-input");
		$('#xmgk-input').removeData("bs.modal");
		$("#xmgk-input").empty();
		$('#xmgk-input').modal({
			backdrop: 'static'
		});
		
		$.get("${ctx}/jsp/business/zjhgl/zhou-add.jsp",function(data) {
			$("#xmgk-input").empty();
			$("#xmgk-input").html("");
			$("#xmgk-input").html(data);
		});
	
	});
	

}


function doUpdate(cellvalue, opts, rowObject){
	var log_uid = rowObject.SCSL_WEEK_PLAN_UID;
	
	var showHtml ="<div class='hidden-sm hidden-xs action-buttons'>";			        	
       	//showHtml +="<a class=\"blue\" title=\"查看\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
     	//showHtml +=	" onclick=\"showeidtLlog('"+log_uid+"');\">";
	    //showHtml +="<span class=\"glyphicon glyphicon-file bigger-110\"></i>";
    	//showHtml +="</a> &nbsp;";
    	//if(checkTabAuthority($("#project_uid").val(),$("#projectUserId").val(),"12060202")){
		    showHtml +="<a class=\"blue\" title=\"修改\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
			showHtml +=	" onclick=\"eidtLlog('"+log_uid+"');\">";
			showHtml +="<span class=\"glyphicon glyphicon-edit bigger-110\"></i>";
			showHtml +="</a> &nbsp;";
    	//}
		//if(checkTabAuthority($("#project_uid").val(),$("#projectUserId").val(),"12060203")){
			//showHtml +="<a class=\"\" title=\"反馈\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
			//showHtml +=	" onclick=\"fankui('"+log_uid+"');\">";
			//showHtml +="<span class=\"glyphicon  glyphicon-edit bigger-110\"></i>";
			//showHtml +="</a>&nbsp;";
    	//}
    	//if(checkTabAuthority($("#project_uid").val(),$("#projectUserId").val(),"12060203")){
			showHtml +="<a class=\"\" title=\"删除\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
			showHtml +=	" onclick=\"removeData('"+log_uid+"');\">";
			showHtml +="<span class=\"glyphicon colorshow1 glyphicon-trash bigger-110\"></i>";
			showHtml +="</a>&nbsp;";
    	//}
	        	
	return 	showHtml;
}

function eidtLlog(log_uid){
		$('#newAdd').attr("data-target","xmgk-input");
		$('#xmgk-input').removeData("bs.modal");
		$("#xmgk-input").empty();
		$('#xmgk-input').modal({
			backdrop: 'static'
		});
		
		$.get("${ctx}/jsp/business/zjhgl/zhou-update.jsp?log_uid="+log_uid,function(data) {
			$("#xmgk-input").empty();
			$("#xmgk-input").html("");
			$("#xmgk-input").html(data);
		});
}
/*
function fankui(log_uid){

	$('#newAdd').attr("data-target","xmgk-input");
	$('#xmgk-input').removeData("bs.modal");
	$("#xmgk-input").empty();
	$('#xmgk-input').modal({
		backdrop: 'static'
	});
	
	$.get("${ctx}/jsp/business/zjhgl/zhou-show.jsp?log_uid="+log_uid,function(data) {
		$("#xmgk-input").empty();
		$("#xmgk-input").html("");
		$("#xmgk-input").html(data);
	});
}
function showeidtLlog(log_uid){

	$('#newAdd').attr("data-target","xmgk-input");
	$('#xmgk-input').removeData("bs.modal");
	$("#xmgk-input").empty();
	$('#xmgk-input').modal({
		backdrop: 'static'
	});
	
	$.get("${ctx}/jsp/business/zjhgl/zhou-show.jsp?log_uid="+log_uid,function(data) {
		$("#xmgk-input").empty();
		$("#xmgk-input").html("");
		$("#xmgk-input").html(data);
	});
}
*/
function removeData(log_uid){
		bootbox.confirm("确认删除吗？", function(result) {
		if (result) {						
			$.ajax({
				url :'${ctx}/pmscslweekplan/pmScslWeekPlanController?deleteId',
				data : {"SCSLWEEKPLANUID":log_uid},
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

function _reload(){
	jQuery("#grid_table").jqGrid().trigger("reloadGrid");
}

</script>

</body>
</html>