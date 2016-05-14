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
</head>
<body >
	<div class="main-container" id="main-container">
			<div class="page-content" id="inside">
				<div class="col-sm-12">
		    <!-- form 开始 位置 -->
					<form class="form-inline"  id="queryForm" width="100%"
					   role="form" style="border:solid 1px #ddd;height:50px;line-height:50px;vertical-align:middle;">
						  
						
						<div style="float: right;">
							 <div class="form-group" >
									<button class="btn btn-link btn-sm" type="button" onclick="importGf();">
										<i class="ace-icon glyphicon glyphicon-plus"></i>
										<span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">导入</span>
									</button>
							 </div>
							 &nbsp; 
							 <div  class="form-group" >
									<button class="btn btn-link btn-sm" type="button" onclick="goAdd('GF');">
										<i class="ace-icon glyphicon glyphicon-plus"></i>
										<span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">添加规范</span>
									</button>
							 </div>
							 &nbsp; 
							 <div   class="form-group" >
									<button  class="btn btn-link btn-sm" type="button" onclick="goAdd('FL');">
										<i class="ace-icon glyphicon glyphicon-plus"></i>
										<span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">添加分类</span>
									</button>
							 </div>
							 &nbsp; 
							 <div   class="form-group" >
									<button class="btn btn-link btn-sm" type="button" onclick="goAdd('SJ');">
										<i class="ace-icon glyphicon glyphicon-plus"></i>
										<span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">添加事件</span>
									</button>
							 </div>
							 &nbsp; 
							
							
						</div>
					</form>
					    <table   sortname="t.BZGF_UID" multiselect=false  rownum="10000" sortorder="asc"   id="grid_table" 
									action="${ctx}/pmjiancha/pmBzgfController?query" >
							<tr>
								<th name="NODE_TYPE"  width="6" maxlength="100" align="center">分类</th>
								<th name="NODE_CONTENT"  width="1" align="center">内容</th>
								<th name="USER_NAME"  width="2" align="center">更新人</th>
								<th name="UPDATE_DATE" width="1" align="center" >更新时间</th>
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
var controlletname="${pageContext.request.contextPath }/pmjiancha/pmBzgfController";
var scripts =[null];
var gridwidth;
var setHeight;
ace.load_ajax_scripts(scripts, function() {
	init();
    DatePicker.datepicker("#REPORT_DATE1");
    DatePicker.datepicker("#REPORT_DATE_ONE"); 
	//获取 当前的 项目的 主键 uid
	var xmUid = $("#project_uid").val();
	$("#PROJECT_UID").val(xmUid);
	
	gridwidth=$("#inside").width();
	JqgridManage.initJqgrid(grid_table,queryForm,gridwidth); 
	setHeight = $(window).height();
	
	if(navigator.userAgent.indexOf("Firefox") > -1){
	   setHeight = setHeight+50;
	}
	
	 $("#grid_table").setGridHeight(setHeight - 320); 
});

/* function select(){
 document.getElementById("selectId").focus();
} */
function init(){	
    //查询按钮 (报表)
	$("#searchForPlan").click(function(){
	  jQuery("#grid_table").jqGrid().trigger("reloadGrid");
	}); 
	
	
	//删除 
	$('#newDel').click(function (){
		//获取 选择的列
		   var v="";
		   $("#grid_table input:checkbox[name='GONGKUANG_UID']:checked").each(function() {
				v += $(this).val() + "," ;
			});
			if (v.length != 0) {
			v = v.substring(0, v.length - 1);
			}
		  $("#strUid").val(v);
		  if($("#strUid").val() !="" ){
			bootbox.confirm("确认删除吗？", function(result) {
				if(result){
				    var data = Form2Json.formToJSON(dataCheckBox03);
					//组成保存json串格式
					var data1 = defaultJson.packSaveJson(data);
					$.ajax({
						url : controlletname+"?delete",
						cache : false,
						async :	false,
						data : data1,
						dataType : "json",  
						type : 'post',
						success : function(response) {
							if(response !=null && response !=""){
								xAlert("信息提示","删除成功！");
							    $("#searchForPlan").click();
							}else{
								xAlert("信息提示","更新失败，请联系管理员！");
							}
						}
					});
					
				}else{
					return;
				}
			});	
			
			}else{ //说明 没有 选择 任何数据（复选框没有选择）
			xAlert("信息提示","请选择要删除的行！");
			  return;
			}
		});

}

//导入规范、分类、事件
function importGf(){
	$('#xmgk-input').removeData("bs.modal");
	$("#xmgk-input").empty();
	$('#xmgk-input').modal({
		backdrop: 'static'
	});
		
	$.get("${ctx}/jsp/business/jiancha/jiancha-import.jsp?PROJECT_UID="+$("#project_uid").val(),function(data) {
		$("#xmgk-input").empty();
		$("#xmgk-input").html("");
		$("#xmgk-input").html(data);
	});
}

//添加规范、分类、事件
function goAdd(type){
	$('#xmgk-input').removeData("bs.modal");
	$("#xmgk-input").empty();
	$('#xmgk-input').modal({
		backdrop: 'static'
	});
		
	$.get("${ctx}/jsp/business/jiancha/jiancha-add.jsp?PROJECT_UID="+$("#project_uid").val()+"&NODE_TYPE="+type,function(data) {
		$("#xmgk-input").empty();
		$("#xmgk-input").html("");
		$("#xmgk-input").html(data);
	});
	
}
//选择 按钮 
function formatEdit(cellvalue, opts, rowObject){
	var jceid = rowObject.GONGKUANG_UID;
	var current_y = rowObject.CURRENT_Y;
	var showHtml ="<div class='hidden-sm hidden-xs action-buttons' id='getValue'>";	
		if(current_y=='Y'){
			showHtml +="<input id=\"jcEuid"+jceid+"\" type=\"radio\" name=\"GONGKUANG_UID\" value =\""+jceid+"\" checked=\"checked\"";
		}else{
			showHtml +="<input id=\"jcEuid"+jceid+"\" type=\"radio\" name=\"GONGKUANG_UID\" value =\""+jceid+"\" ";
		}
		showHtml +="</div>";
	return 	showHtml;	
	
}

//查看
function doUpdate(cellvalue, opts, rowObject){
	var gkuid = rowObject.GONGKUANG_UID;
	var current_y = rowObject.CURRENT_Y;
	var showHtml ="<div class='hidden-sm hidden-xs action-buttons'>";
		if(current_y=='Y'){
		showHtml +="<a class=\"red\" title=\"当前工况\" data-target=\"content_view\"  href=\"javascript:void(0);\">";
		}else{
			showHtml +="<a class=\"blue\" title=\"标记为当前工况\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
			showHtml +=	" onclick=\"pmGongkuangSet('"+gkuid+"');\">";
		}

		showHtml +="<span class=\"glyphicon glyphicon-bookmark bigger-110\"></i>";
		showHtml +="</a> &nbsp;";
	        	
	return 	showHtml;
}

function eidtXmgk(gkuid){
$('#newAdd').attr("data-target","xmgk-input");
	$('#xmgk-input').removeData("bs.modal");
	$("#xmgk-input").empty();
	$('#xmgk-input').modal({
		backdrop: 'static'
	});

	$.get("${ctx}/jsp/business/jiancha/jiancha-update.jsp?PROJECT_UID="+$("#project_uid").val()+"&GKUID="+gkuid,function(data) {
		$("#xmgk-input").empty();
		$("#xmgk-input").html("");
		$("#xmgk-input").html(data);
	});

}


function pmGongkuangSet(gongkuang_uid){
	$.ajax({
		url: controlletname + "?pmGongkuangSet",
		cache: false,
		async: false,
		data: {"gongkuang_uid":gongkuang_uid},
		dataType: "json",
		type: 'post',
		success: function(response) {
			if (response.success) {
				xAlert("信息提示", "标记成功！");
			} else {
				xAlert("信息提示", "标记失败，请联系管理员！");
			}
			$("#searchForPlan").click();
		}
	});
	
}



</script>

</body>
</html>