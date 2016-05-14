<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>

<title><fmt:message key="ui.title" /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jkjc-btnStyle.css" type="text/css"></link>
<style>

</style>
</head>
<body >
	<div class="main-container" id="main-container">
			<div class="page-content" id="inside">
		    <!-- form 开始 位置 -->
					<form class="form-inline"  id="queryForm01" width="100%"
					   role="form" style="border:solid 1px #ddd;height:50px;line-height:50px;vertical-align:middle;">
						 <input id="PROJECT_UID" type="hidden" name="t.PROJECT_UID" fieldname="t.PROJECT_UID" operation="="/> 
						<div class="form-group">
						  <span id="getSelect" style="font-size:15px;line-height: 50px;">&nbsp;设施设备</span>
						  <input id="ClickType" type="hidden" value=""/>
						</div>
						<div style="float:right">
						 <div id="btnAddDiv"  class="form-group " >
						 <!-- <span style="font-family:'Microsoft YaHei';font-size:13px;color:#4e4c4c;">添加</span> -->
							<a data-target="" id="newAdd" data-toggle="modal">
								<button id="btnAdd" class="btn btn-link btn-sm" type="button"><i class="ace-icon glyphicon glyphicon-plus"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">添加</span></button>
							</a>
						 </div>
						 &nbsp;
						<!--  <button class="btn btn-white btn-default btn-round"><i class="ace-icon fa fa-times red2"></i>删除</button> -->
						 <div id="btnDelDiv"class="form-group" >
							<a data-target="" id="newDel" data-toggle="modal">
								<button id="btnDel" class="btn btn-link btn-sm" type="button"><i class="ace-icon glyphicon glyphicon-remove red bigger-110"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">删除</span></button>
							</a>
						 </div> 
						 &nbsp;
						<div class="form-group right" >
						  <input class="searchContent form-control" style="width: 150px;border-radius: 8px 8px 8px 8px;"  fieldname="j.EQUIPMENT_NAME" id="" operation="like" logic="and">
						  <button id="searchBox" class="btn btn-link btn-sm" type="button">
								<i class="#ace-icon glyphicon   bigger-110"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">查询</span>
						  </button>
						</div>
						</div>
					</form>
				   <form id="dataCheckBox01">
				      <!-- 当前 选择的 checkbox -->
				       <input class="hidden" id="strUid" type="hidden" fieldname="EQUIPMENT_UID"/>
				    </form>
					    <table   sortname="j.EQUIPMENT_UID" multiselect=false  rownum="1000000" sortorder="asc"  class=""  id="grid_table" 
									action="${ctx}/jcsb/jcJkEquipInfoController?query" >
							<tr>
							    <th name="EQUIPMENT_UID" formatter="formatEdit" width="1" align="center">选择</th>
								<th name="EQUIPMENT_UID"  width="2" align="center">设备ID</th>
								<th name="EQUIPMENT_NAME"  width="2" align="center">设备名称</th>
								<th name="EQUIPMENT_UNIT"  width="2" align="center">设备单位</th>
								<th name="EQUIPMENT_MODEL" width="2" align="center" >设备型号</th>
								<th name="WATER_YIELD" width="2" align="center" >出水量（立方米/秒）</th>
								<th name="ELECTRICITY" width="2" align="center" >耗电（KW）</th>
							</tr> 
						</table> 
						<script type="text/javascript">
							var $path_base = "/";
						</script>
				</div>
			</div>
		<div id="jldwUser-input" class="modal"></div>

<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
    <script
		src="${pageContext.request.contextPath }/assets/js/jquery-ui.custom.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript">
var controlletname="${pageContext.request.contextPath }/jcsb/jcJkEquipInfoController";
var scripts =[null];
var gridwidth;
var setHeight;
ace.load_ajax_scripts(scripts, function() {
	init();
	var xmUid = $("#project_uid").val();
	$("#PROJECT_UID").val(xmUid);
	
	gridwidth=$("#inside").width();
	JqgridManage.initJqgrid(grid_table,queryForm01,gridwidth); 
	setHeight = $(window).height();
	
	if(navigator.userAgent.indexOf("Firefox") > -1){
	   setHeight = setHeight+50;
	}
	
	 $("#grid_table").setGridHeight(setHeight - 320); 
});

function init(){	
    //查询按钮 (报表)
	$("#searchBox").click(function(){
	  jQuery("#grid_table").jqGrid().trigger("reloadGrid");
	}); 
	
	
	$('#newAdd').click(function() {
	$('#newAdd').attr("data-target","jldwUser-input");
		$('#jldwUser-input').removeData("bs.modal");
		$("#jldwUser-input").empty();
		$('#jldwUser-input').modal({
			backdrop: 'static'
		});
         
       
		$.get("${ctx}/jsp/business/jkjc/jkjcJcsb-add.jsp?PROJECT_UID="+$("#project_uid").val(),function(data) {
			$("#jldwUser-input").empty();
			$("#jldwUser-input").html("");
			$("#jldwUser-input").html(data);
		});
	
	});
	
	//删除 
	$('#newDel').click(function (){
		//获取 选择的列
		   var v="";
		   $("#grid_table input:checkbox[name='EQUIPMENT_UID']:checked").each(function() {
				v += $(this).val() + "," ;
			});
			if (v.length != 0) {
			v = v.substring(0, v.length - 1);
			}
		  $("#strUid").val(v);
		  if($("#strUid").val() !="" ){
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
								xAlert("信息提示","删除成功！");
							     refresh();
							}else{
								xAlert("信息提示","删除失败，请联系管理员！");
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
	var jceid = rowObject.EQUIPMENT_UID;
	var showHtml ="<div class='hidden-sm hidden-xs action-buttons' id='getValue'>";			        	
		showHtml +="<input id=\"jcEuid"+jceid+"\" type=\"checkbox\" name=\"EQUIPMENT_UID\" value =\""+jceid+"\" ";
		showHtml +="</div>";
	return 	showHtml;	
	
}

//刷新 页面数据
function refresh() {
		jQuery("#grid_table").jqGrid().trigger("reloadGrid");
		
	}
</script>
	<form id="gdzxtformid" method="post" >
		<input type="hidden" name="gdzxt_gcid" id="gdzxt_gcid" >
	</form>
</body>
</html>