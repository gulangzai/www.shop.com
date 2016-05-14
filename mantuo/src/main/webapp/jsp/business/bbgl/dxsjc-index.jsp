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
						 <input id="PROJECT_UID" type="hidden" name="p.PROJECT_UID" fieldname="p.PROJECT_UID" operation="="/> 
						<div class="form-group"><!-- style="width: 130px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;" -->
						  日期：
						 <input class="form-control" data-date-format="yyyy-mm-dd"
										style="width: 130px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"
						                name="g.REPORT_DATE"
										fieldname="g.REPORT_DATE" id="REPORT_DATE_ONE"
										operation="&gt;" logic="and" />
								—
						 <input class="form-control" data-date-format="yyyy-mm-dd"
								style="width: 130px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"		
							   			name="g.REPORT_DATE"
										fieldname="g.REPORT_DATE" id="REPORT_DATE1"
										operation="&lt;" logic="and" />
										&nbsp;&nbsp; 
						    工况：				
						 <input id="selectId" placeholder="请输入工况描述" class="form-control" onclick="select();" style="width: 150px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;
							  background-color: #ffffff; background-image: url(./assets/images/search_icon.png);background-position: right center; background-repeat: no-repeat;" name="g.DESCRIPTION" fieldname="g.DESCRIPTION" operation="like" logic="and">
						  <button id="searchForPlan" class="btn btn-link btn-sm" type="button">
								<i class="#ace-icon glyphicon   bigger-110"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">查询</span>
						  </button>	
						  <!-- 
						  <button id="searchForClean" class="btn btn-link btn-sm" type="button">
								<i class="#ace-icon glyphicon   bigger-110"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">清空</span>
						  </button>	
						   -->		
						</div>
						<!--  
						<div style="float: right;">
							 <div id="btnAddDiv"  class="form-group" >
								<a data-target="" id="newAdd" data-toggle="modal">
									<button id="btn_new" class="btn btn-link btn-sm" type="button"><i class="ace-icon glyphicon glyphicon-plus"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">添加</span></button>
								</a>
							 </div>
							 &nbsp; 
							 <div id="btnDelDiv"class="form-group" >
								<a data-target="" id="newDel" data-toggle="modal">
									<button id="btnDel" class="btn btn-link btn-sm" type="button"><i class="ace-icon glyphicon glyphicon-remove red bigger-110"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">删除</span></button>
								</a>
							 </div> 
							 &nbsp;
							
						</div>
						-->
					</form>
				   <form id="dataCheckBox03">
				      <!-- 当前 选择的 checkbox进行 删除操作-->
				       <input class="hidden" id="strUid" type="hidden" fieldname="GONGKUANG_UID"/>
				    </form>
					    <table   sortname="g.GONGKUANG_UID" multiselect=false  rownum="1000000" sortorder="asc"  class=""  id="grid_table" 
									action="${ctx}/xmgk/pmGongkuangController?query" >
							<tr>
								<!-- 
							    <th name="GONGKUANG_UID" formatter="formatEdit" width="1" align="center">选择</th>
							     -->
								<th name="JINDU"  width="6" maxlength="100" align="center">工况</th>
								<th name="USER_NAME"  width="1" align="center">创建人</th>
								<th name="CREATE_DATE"  width="2" align="center">创建日期</th>
								<th name="GONGKUANG_UID" width="1" align="center" formatter="doUpdate">操作</th>
								
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
var controlletname="${pageContext.request.contextPath }/xmgk/pmGongkuangController";
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
	
	//清空
	$("#searchForClean").click(function(){
	     $("#queryForm").clearFormResult();
	     $('#searchForPlan').click();
	}); 
	
	$('#newAdd').click(function() {
	$('#newAdd').attr("data-target","xmgk-input");
		$('#xmgk-input').removeData("bs.modal");
		$("#xmgk-input").empty();
		$('#xmgk-input').modal({
			backdrop: 'static'
		});
		
		$.get("${ctx}/jsp/business/xmgk/project-gk-add.jsp?PROJECT_UID="+$("#project_uid").val(),function(data) {
			$("#xmgk-input").empty();
			$("#xmgk-input").html("");
			$("#xmgk-input").html(data);
		});
	
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

	$.get("${ctx}/jsp/business/xmgk/project-gk-update.jsp?PROJECT_UID="+$("#project_uid").val()+"&GKUID="+gkuid,function(data) {
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