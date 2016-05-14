<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>

<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jkjc-btnStyle.css" type="text/css"></link>

</head>
<body >
	<div class="main-container" id="main-container">
			<div class="page-content" id="inside">
		    <!-- form 开始 位置 -->
					<form class="form-inline"  id="queryForm01" width="100%"
					   role="form" style="border:solid 1px #ddd;height:50px;line-height:50px;vertical-align:middle;">
						 <input id="PROJECT_UID" type="hidden" name="t.PROJECT_UID" fieldname="t.PROJECT_UID" operation="="/> 
						 <input id="PLAN_TYPE" type="hidden" name="t.PLAN_TYPE" fieldname="t.PLAN_TYPE" operation="=" value="JS"/>
						
						<div class="form-group">
						  <span id="getSelect" style="font-size:15px;line-height: 50px;">&nbsp;施工应急方案</span>
						</div>
						<div style="float: right;">
							 <div  class="form-group" >
								<a data-target="" id="new01" data-toggle="modal">
									<button id="btn_new01" class="btn btn-link btn-sm" type="button"><i class="ace-icon glyphicon glyphicon-plus"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">添加</span></button>
								</a>
							 </div>
							 &nbsp;
							<!--  <button class="btn btn-white btn-default btn-round"><i class="ace-icon fa fa-times red2"></i>删除</button> -->
							 <div  class="form-group" >
								<a data-target="" id="new01Del" data-toggle="modal">
									<button id="btnDel01" class="btn btn-link btn-sm" type="button"><i class="ace-icon glyphicon glyphicon-remove red bigger-110"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">删除</span></button>
								</a>
							 </div> 
							 &nbsp; 
							<div class="form-group">
							  <input class="searchContent form-control" style="width: 150px;" name="" fieldname="PLAN_NAME" id="" operation="like" logic="and">
							  <button id="searchForPlan01" class="btn btn-link btn-sm" type="button">
									<i class="#ace-icon glyphicon   bigger-110"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">查询</span>
							  </button>
							</div>
						</div>
					</form>
				   <form id="dataCheckBox01">
				      <!-- 当前 选择的 checkbox -->
				       <input class="hidden" id="strUid01" type="hidden" fieldname="EMERGENCY_PLAN_UID"/>
				    </form>
					    <table   sortname="EMERGENCY_PLAN_UID" multiselect=false  rownum="1000000" sortorder="asc"  class=""  id="grid_table1" 
									action="${ctx}/JcPlan/jcEmergencyPlanController?query" >
							<tr>
							     <th name="EMERGENCY_PLAN_UID" formatter="formatEdit" width="1" align="center">选择</th>
								<th name="PLAN_NAME"  width="2" align="center">名称</th>
								<th name="PLAN_CONTENT"  width="2" align="center">简介</th>
								<th name="EMERGENCY_PLAN_UID" width="1" align="center" formatter="doUpdate">操作</th>
								
							</tr> 
						</table> 
						<script type="text/javascript">
							var $path_base = "/";//this will be used in gritter alerts containing images
						</script>
				</div>
			</div>
		<div id="jsjc-sgyjya-input" class="modal"></div>

<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
    <script
		src="${pageContext.request.contextPath }/assets/js/jquery-ui.custom.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript">
var controlletname="${pageContext.request.contextPath }/JcPlan/jcEmergencyPlanController";
var scripts =[null];
var gridwidth;
var setHeight;
ace.load_ajax_scripts(scripts, function() {
	init();
	//默认点击 首行 模拟点击应急预案
    $("#firstTR").addClass("new01Class");
	var xmUid = $("#project_uid").val();
	$("#PROJECT_UID").val(xmUid);
	
	gridwidth=$("#inside").width();
	//gridwidth -=250;
	//设置 放置 表格的 div的宽度
	//$("#tbDiv").css("width",gridwidth);
	JqgridManage.initJqgrid(grid_table1,queryForm01,gridwidth); 
	setHeight = $(window).height();
	
	if(navigator.userAgent.indexOf("Firefox") > -1){
	   setHeight = setHeight+50;
	}
	
	 $("#grid_table1").setGridHeight(setHeight - 320); 
});

function init(){	
    //查询按钮 (报表)
	$("#searchForPlan01").click(function(){
	  jQuery("#grid_table1").jqGrid().trigger("reloadGrid");
	}); 
	
	
	$('#btn_new01').click(function() {
	$('#new01').attr("data-target","jsjc-sgyjya-input");
		$('#jsjc-sgyjya-input').removeData("bs.modal");
		$("#jsjc-sgyjya-input").empty();
		$('#jsjc-sgyjya-input').modal({
			backdrop: 'static'
		});
       
		$.get("${ctx}/jsp/business/jsjc/jsjc-sgyjya-add.jsp?PROJECT_UID="+$("#project_uid").val(),function(data) {
			alert("nihao 1111111");
			$("#jsjc-sgyjya-input").empty();
			$("#jsjc-sgyjya-input").html("");
			$("#jsjc-sgyjya-input").html(data);
		});
	
	});
	
	//删除 
	$('#new01Del').click(function (){
		//获取 选择的列
		   var v="";
		   $("#grid_table1 input:checkbox[name='EMERGENCY_PLAN_UID']:checked").each(function() {
				v += $(this).val() + "," ;
			});
			if (v.length != 0) {
			v = v.substring(0, v.length - 1);
			}
		  $("#strUid01").val(v);
		  if($("#strUid01").val() !="" ){
			bootbox.confirm("确认删除吗？", function(result) {
				if(result){
				    var data = Form2Json.formToJSON(dataCheckBox01);
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
								xAlert("信息提示","更新成功！");
							    $("#searchForPlan01").click();
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

//选择 按钮 
function formatEdit(cellvalue, opts, rowObject){
	var jceid = rowObject.EMERGENCY_PLAN_UID;
	var showHtml ="<div class='hidden-sm hidden-xs action-buttons' id='getValue'>";			        	
		showHtml +="<input id=\"jcEuid"+jceid+"\" type=\"checkbox\" name=\"EMERGENCY_PLAN_UID\" value =\""+jceid+"\" ";
		showHtml +="</div>";
	return 	showHtml;	
	
}

//操作
function doUpdate(cellvalue, opts, rowObject){
	var yjid = rowObject.EMERGENCY_PLAN_UID;

	var showHtml ="<div class='hidden-sm hidden-xs action-buttons'>";			        	
		showHtml +="<a class=\"blue\" title=\"查看\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		showHtml +=	" onclick=\"eidtSgya('"+yjid+"');\">";
		showHtml +="<span class=\"glyphicon glyphicon-edit bigger-110\"></i>";
		showHtml +="</a> &nbsp;";
	        	
	return 	showHtml;
}
function eidtSgya(yjid){
$('#new01').attr("data-target","jsjc-sgyjya-input");
	$('#jsjc-sgyjya-input').removeData("bs.modal");
	$("#jsjc-sgyjya-input").empty();
	$('#jsjc-sgyjya-input').modal({
		backdrop: 'static'
	});

	$.get("${ctx}/jsp/business/jsjc/jsjc-sgyjya-edit.jsp?PROJECT_UID="+$("#project_uid").val()+"&JCUID="+yjid,function(data) {
		$("#jsjc-sgyjya-input").empty();
		$("#jsjc-sgyjya-input").html("");
		$("#jsjc-sgyjya-input").html(data);
	});

}




</script>
	<form id="gdzxtformid" method="post" >
		<input type="hidden" name="gdzxt_gcid" id="gdzxt_gcid" >
	</form>
</body>
</html>