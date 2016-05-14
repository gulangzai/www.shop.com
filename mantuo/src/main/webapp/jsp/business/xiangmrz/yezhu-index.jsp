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
						 <input id="PROJECT_UID" type="hidden" fieldname="l.PROJECT_UID" operation="="/> 
						 <input  type="hidden" fieldname="l.LOG_TYPE" operation="=" value="YZ"/> 
						<div class="form-group">
						  日期：
						 <input class="form-control" data-date-format="yyyy-mm-dd"
										style="width: 130px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"
										fieldname="l.LOG_DATE" id="REPORT_DATE_ONE"
										operation=">=" logic="and" />
								~
						 <input class="form-control" data-date-format="yyyy-mm-dd"
								style="width: 130px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"		
										fieldname="l.LOG_DATE" id="REPORT_DATE11"
										operation="<=" logic="and" />
										&nbsp;&nbsp; 
						
						  <button id="searchForPlan" class="btn btn-link btn-sm" type="button">
								<i class="colorshow ace-icon glyphicon glyphicon-search"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">查询</span>
						  </button>	
						</div>
						
						<div style="float: right;">
							 <div id="btnAddDiv"  class="form-group" >
								<a data-target="" id="newAdd" data-toggle="modal">
									<button id="btn_new" class="btn btn-link btn-sm" type="button"><i class="colorshow ace-icon glyphicon glyphicon-plus"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">添加</span></button>
								</a>
							 </div>
							 &nbsp; 
							
						</div>
						
					</form>
					    <table   sortname="l.PROJECT_LOG_UID" multiselect=false  rownum="1000000" sortorder="asc"  class=""  id="grid_table" 
									action="${ctx}/projectlog/pmProjectLogController?query" >
							<tr>
								<!-- <th name="PROJECT_NAME"   align="center">项目名称</th> -->
								<th name="LOG_DATE" width="2"  align="center">日期</th>
								<th name="YZLOG_TITLE" width="8"  align="left">业主日报标题</th>
								<th name="YZLOG_CONTENT"  align="left" width="8">业主日报内容</th>
								<th name="USER_NAME"  align="center" width="2">发布人</th>
								<th name="PROJECT_LOG_UID" align="center" width="2" formatter="doUpdate">操作</th>
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
var controlletname="${pageContext.request.contextPath }/projectlog/pmProjectLogController";
var scripts =[null];
var gridwidth;
var setHeight;
ace.load_ajax_scripts(scripts, function() {
	init();

     DatePicker.datepicker("#REPORT_DATE11");
     DatePicker.datepicker("#REPORT_DATE_ONE"); 
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
	checkBtnAuthority($("#project_uid").val(),$("#projectUserId").val(),"12060201","btn_new"); 	 
	 
});

function init(){	
    //查询按钮 (报表)
	$("#searchForPlan").click(function(){
	
	  jQuery("#grid_table").jqGrid().trigger("reloadGrid");
	  
	}); 
	
	$('#newAdd').click(function() {
	$('#newAdd').attr("data-target","xmgk-input");
		$('#xmgk-input').removeData("bs.modal");
		$("#xmgk-input").empty();
		$('#xmgk-input').modal({
			backdrop: 'static'
		});
		
		$.get("${ctx}/jsp/business/xiangmrz/yezhu-add.jsp",function(data) {
			$("#xmgk-input").empty();
			$("#xmgk-input").html("");
			$("#xmgk-input").html(data);
		});
	
	});
	

}


function doUpdate(cellvalue, opts, rowObject){
	var log_uid = rowObject.PROJECT_LOG_UID;
	var showHtml ="<div class='hidden-sm hidden-xs action-buttons'>";			        	
       	showHtml +="<a class=\"blue\" title=\"查看\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
     	showHtml +=	" onclick=\"showeidtLlog('"+log_uid+"');\">";
	    showHtml +="<span class=\"glyphicon glyphicon-file bigger-110\"></i>";
    	showHtml +="</a> &nbsp;";
    	if(checkTabAuthority($("#project_uid").val(),$("#projectUserId").val(),"12060202")){
		    showHtml +="<a class=\"blue\" title=\"修改\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
			showHtml +=	" onclick=\"eidtLlog('"+log_uid+"');\">";
			showHtml +="<span class=\"glyphicon glyphicon-edit bigger-110\"></i>";
			showHtml +="</a> &nbsp;";
    	}
    	if(checkTabAuthority($("#project_uid").val(),$("#projectUserId").val(),"12060203")){
			showHtml +="<a class=\"\" title=\"删除\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
			showHtml +=	" onclick=\"removeData('"+log_uid+"');\">";
			showHtml +="<span class=\"glyphicon colorshow1 glyphicon-trash bigger-110\"></i>";
			showHtml +="</a>&nbsp;";
    	}
	        	
	return 	showHtml;
}

function eidtLlog(log_uid){
		$('#newAdd').attr("data-target","xmgk-input");
		$('#xmgk-input').removeData("bs.modal");
		$("#xmgk-input").empty();
		$('#xmgk-input').modal({
			backdrop: 'static'
		});
		
		$.get("${ctx}/jsp/business/xiangmrz/yezhu-update.jsp?log_uid="+log_uid,function(data) {
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
	
	$.get("${ctx}/jsp/business/xiangmrz/yezhu-show.jsp?log_uid="+log_uid,function(data) {
		$("#xmgk-input").empty();
		$("#xmgk-input").html("");
		$("#xmgk-input").html(data);
	});
}
function removeData(log_uid){
		bootbox.confirm("确认删除吗？", function(result) {
		if (result) {						
			$.ajax({
				url :'${ctx}/projectlog/pmProjectLogController?deleteById',
				data : {"PROJECT_LOG_UID":log_uid},
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