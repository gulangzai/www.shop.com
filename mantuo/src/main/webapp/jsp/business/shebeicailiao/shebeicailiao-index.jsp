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

.col-xs-6, .col-sm-10 {
    position: relative;
    min-height: 1px;
    padding-left: 3px;
    padding-right: 3px;
} 


</style>
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/mycss2(table).css" type="text/css"></link></head>
<body >
	<div class="main-container" id="main-container">
			<div class="page-content" >
				<div class="col-sm-12" id="inside">
		    <!-- form 开始 位置 -->
					<form class="form-inline"  id="queryForm" width="100%"
					   role="form" style="border:solid 1px #ddd;line-height:50px;vertical-align:middle;">
						 <input id="CAILIAO_UID" type="hidden" fieldname="t.CAILIAO_UID" operation="="/> 
						  <input id="PROJECT_UID" type="hidden" name="t.PROJECT_UID" fieldname="t.PROJECT_UID" operation="="/> 
						<div class="form-group"><!-- style="width: 130px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;" -->
						 设备材料名称：				
						 <input id="selectId" placeholder="设备材料名称" class="form-control" onclick="select();" style="width: 150px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;
							  background-color: #ffffff; background-image: url(./assets/images/search_icon.png);background-position: right center; background-repeat: no-repeat;" name="t.CAILIAO_NAME" fieldname="t.CAILIAO_NAME" operation="like" logic="and">  
						  进场日期：
						 <input class="form-control" data-date-format="yyyy-mm-dd"
										style="width: 130px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"
										fieldname="t.CAILIAO_JCRQ" id="REPORT_DATE_ONE"
										operation=">=" logic="and" />
								~
						 <input class="form-control" data-date-format="yyyy-mm-dd"
								style="width: 130px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"		
										fieldname="t.CAILIAO_JCRQ" id="REPORT_DATE1"
										operation="<=" logic="and" />
										&nbsp;&nbsp; 
						  <button id="searchForPlan" class="btn btn-link btn-sm" type="button">
								<i class="colorshow ace-icon glyphicon glyphicon-search"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">查询</span>
						  </button>	
						
						
						<div style="float: right;">
							 <div id="btnAddDiv"  class="form-group" >
								<a data-target="" id="newAdd" data-toggle="modal">
									<button id="btn_new" style="display: none;" class="btn btn-link btn-sm" type="button"><i class="colorshow ace-icon glyphicon glyphicon-plus"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">添加</span></button>
								</a>
							 </div>
							 &nbsp;  
						</div>
						</div>
					</form>
					    <table   sortname="t.CAILIAO_UID" multiselect=false  rownum="1000000" sortorder="asc"  class=""  id="grid_table" 
									action="${ctx}/pmcailiao/pmCailiaoController?query" >
							<tr>
								<!-- <th name="CAILIAO_UID"   align="center">序号</th> -->
								<th name="CAILIAO_NAME"   align="center">设备材料名称</th>
								<th name="CAILIAO_PINPAI"  align="center">品牌</th>
								<th name="CAILIAO_CHANDI"  align="center">产地</th>
								<th name="CAILIAO_XINGHAO"   align="center">规格型号</th>
							    <th name="CAILIAO_NUMS"   align="center">数量</th>
							    <th name="CAILIAO_UNIT"   align="center">单位</th>
							    <th name="CAILIAO_JCRQ"   align="center">进场日期</th>
							    <th name="YANSHOU_REN"   align="center">验收人</th>
							    <th name="CAILIAO_UID" align="center" formatter="doUpdate">操作</th>
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
var controlletname="${pageContext.request.contextPath }/pmcailiao/pmCailiaoController";
var scripts =[null];
var gridwidth;
var setHeight;
ace.load_ajax_scripts(scripts, function() {
	init();

     DatePicker.datepicker("#REPORT_DATE1");
     DatePicker.datepicker("#REPORT_DATE_ONE"); 
	$("#CAILIAO_UID").val($("#cailiao_uid").val());
	var xmUid = $("#project_uid").val();
	
	$("#PROJECT_UID").val(xmUid);
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
		checkBtnAuthority($("#project_uid").val(),$("#projectUserId").val(),"12010001","btn_new");
	 
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
		
		$.get("${ctx}/jsp/business/shebeicailiao/shebeicailiao-add.jsp?PROJECT_UID="+$("#project_uid").val(),function(data) {
			$("#xmgk-input").empty();
			$("#xmgk-input").html("");
			$("#xmgk-input").html(data);
		});
	
	});
	

}
function doUpdate(cellvalue, opts, rowObject){
	var log_uid = rowObject.CAILIAO_UID;
	var showHtml ="<div class='hidden-sm hidden-xs action-buttons'>";	
	    showHtml +="<a class=\"blue\" title=\"修改\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
	    showHtml +=	" onclick=\"showeidtLlog('"+log_uid+"');\">";
	    showHtml +="<span class=\"glyphicon  glyphicon-file bigger-110\"></i>";
	    showHtml +="</a> &nbsp;";
	    if(checkTabAuthority($("#project_uid").val(),$("#projectUserId").val(),"12060102")){
		     showHtml +="<a class=\"blue\" title=\"修改\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		     showHtml +=	" onclick=\"eidtLlog('"+log_uid+"');\">";
		     showHtml +="<span class=\"glyphicon  glyphicon-edit bigger-110\"></i>";
		     showHtml +="</a> &nbsp;";
	    }
	    if(checkTabAuthority($("#project_uid").val(),$("#projectUserId").val(),"12060103")){
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
		
		$.get("${ctx}/jsp/business/shebeicailiao/shebeicailiao-update.jsp?PROJECT_UID="+$("#project_uid").val()+"&log_uid="+log_uid,function(data) {
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
	
	$.get("${ctx}/jsp/business/shebeicailiao/shebeicailiao-show.jsp?PROJECT_UID="+$("#project_uid").val()+"&log_uid="+log_uid,function(data) {
		$("#xmgk-input").empty();
		$("#xmgk-input").html("");
		$("#xmgk-input").html(data);
	});
}
function removeData(log_uid){
		bootbox.confirm("确认删除吗？", function(result) {
		if (result) {						
			$.ajax({
				url :'${ctx}/pmcailiao/pmCailiaoController?deleteId',
				data : {"CAILIAO_UID":log_uid},
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