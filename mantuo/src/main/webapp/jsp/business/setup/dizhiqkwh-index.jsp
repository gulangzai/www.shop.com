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
					<form class="form-inline"  id="queryForm02" width="100%"
					   role="form" style="border:solid 1px #ddd;height:50px;line-height:50px;vertical-align:middle;">
						 <input id="PROJECT_UID" type="hidden" name="dz.PROJECT_UID" fieldname="dz.PROJECT_UID" operation="="/> 
						<div style="float: right;">
						     <div  class="form-group" >
								<a data-target="" id="dizhiAdd" data-toggle="modal">
									<button id="btnAddDizhi" class="btn btn-link btn-sm" type="button">
									<i class="ace-icon glyphicon glyphicon-plus"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">添加</span></button>
								</a>
							 </div>
							 &nbsp; 
							<div class="form-group">
							  <input class="searchContent form-control" placeholder="请输入土层名称"style="width: 150px;height:28px;" name="TUCENG_NAME" fieldname="dz.TUCENG_NAME" id="TUCENG_NAME" operation="like" logic="and">
							  <button id="searchBox" class="btn btn-link btn-sm" type="button">
									<i class="#ace-icon glyphicon  bigger-110"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">查询</span>
							  </button>
							</div>
						</div>	
					 </form>
					    <table   sortname="XUHAO" multiselect=false  rownum="1000000" sortorder="asc"  class=""  id="grid_table_dz" 
									action="${ctx}/dizhi/pmDizhiController?query" >
							<tr>
								<th name="TUCENG_NUM"  width="2" align="center">土层层号</th>
								<th name="TUCENG_NAME"  width="2" align="center">土层名称</th>
								<th name="DEEP"  width="2" align="center">层顶深（m）</th>
								<th name="HEIGHT"  width="2" align="center">层顶标高（m）</th>
								<th name="WEIGHT"  width="2" align="center">重度（KN/m3）</th>
							    <th name="USER_NAME" width="2" align="center" >创建人</th>
								<th name="CREATE_DATE" width="2" align="center" >创建日期</th>
								<th name="DIZHI_UID" width="2" align="center" formatter="doDiZhiUpdate" >操作</th>
							</tr> 
						</table> 
						<script type="text/javascript">
							var $path_base = "/";
						</script>
				</div>
			</div>
		<div id="project-dizhi" class="modal"></div>

<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
    <script
		src="${pageContext.request.contextPath }/assets/js/jquery-ui.custom.min.js"></script>
<script type="text/javascript">
var controlletname="${pageContext.request.contextPath }/dizhi/pmDizhiController";
var scripts =[null];
var gridwidth;
var setHeight;
ace.load_ajax_scripts(scripts, function() {
	init();
	var xmUid = $("#project_uid").val();
	$("#PROJECT_UID").val(xmUid);
	gridwidth=$("#inside").width();
	JqgridManage.initJqgrid(grid_table_dz,queryForm02,gridwidth); 
	
});


function init(){	
    //查询按钮 (报表)
	$("#searchBox").click(function(){
	  jQuery("#grid_table_dz").jqGrid().trigger("reloadGrid");
	}); 
    
    $("#dizhiAdd").click(function (){
    $('#dizhiAdd').attr("data-target","project-dizhi");
		$('#project-dizhi').removeData("bs.modal");
		$("#project-dizhi").empty();
		$('#project-dizhi').modal({
			backdrop: 'static'
		});
		$.get("${ctx}/jsp/business/setup/dizhiqkwh-adddzqk.jsp?PROJECT_UID="+$("#project_uid").val(),function(data) {
			$("#project-dizhi").empty();
			$("#project-dizhi").html("");
			$("#project-dizhi").html(data);
		});
    
    });
}

//刷新页面数据
function reload(){
jQuery("#grid_table_dz").jqGrid().trigger("reloadGrid");
}

function doDiZhiUpdate(cellvalue, opts, rowObject){
	var dizhiuid = rowObject.DIZHI_UID;
	var xuhao = rowObject.XUHAO;
	var showHtml ="<div class='hidden-sm hidden-xs action-buttons'>";
	    showHtml ="<a class=\"blue\" title=\"修改\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		showHtml +=	" onclick=\"xiugaiData('"+dizhiuid+"');\"><i><span class=\"ace-icon glyphicon glyphicon-edit\"></i></a> &nbsp;";
        showHtml +="<a class=\"blue\" title=\"上移\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		showHtml +=	" onclick=\"xiugaiUp('"+dizhiuid+"','"+xuhao+"');\"><i><span class=\"ace-icon glyphicon glyphicon-upload\"></i></a> &nbsp;";
		showHtml +="<a class=\"blue\" title=\"下移\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		showHtml +=	" onclick=\"xiugaiLow('"+dizhiuid+"','"+xuhao+"');\"><i><span class=\"ace-icon glyphicon glyphicon-download\"></i></a> &nbsp;";
		showHtml +=	" <a class=\"blue\" title=\"删除\" data-target=\"content_view\"  href=\"javascript:void(0);\"";
		showHtml +=	" onclick=\"DELETEData('"+dizhiuid+"');\"><i><span class=\"glyphicon glyphicon-remove bigger-110\"></i></a> &nbsp;";
        showHtml +="</div>";	

	return 	showHtml;	 
	       	
}

function xiugaiData(dizhiuid){
	$('#dizhiAdd').attr("data-target","project-dizhi");
	$('#project-dizhi').removeData("bs.modal");
	$("#project-dizhi").empty();
	$('#project-dizhi').modal({
		backdrop: 'static'
	});

	$.get("${ctx}/jsp/business/setup/dizhiqkwh-updatedzqk.jsp?DIZHI_UID="+dizhiuid,function(data) {
		$("#project-dizhi").empty();
		$("#project-dizhi").html("");
		$("#project-dizhi").html(data);
	});
}

//向上移动 改变序号的值 +1
function xiugaiUp(dizhiuid,xuhao){
	$.ajax({
		url :controlletname+'?updateForSort',
		data : {"DIZHI_UID":dizhiuid,"XUHAO":xuhao,"MOVE":"up"},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			if(response != null && response !=""){
				xAlert("信息提示",response.msg,1);
			    reload();
			}else{
				xAlert("信息提示","移动失败,请联系管理员",1);
			}

			
	      }
	});
	
}

//向下移动改变序号值 -1
function  xiugaiLow(dizhiuid,xuhao){
		$.ajax({
				url :controlletname+'?updateForSort',
				data : {"DIZHI_UID":dizhiuid,"XUHAO":xuhao,"MOVE":"down"},
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
					if(response != null && response !=""){
					    xAlert("信息提示",response.msg,1);
						reload();
					}else{
						xAlert("信息提示","移动失败,请联系管理员",1);
					}

					
			      }
			});
}

function deleteData(dizhiuid){
	bootbox.confirm("确认删除吗？", function(result) {
		if (result) {						
			$.ajax({
				url :controlletname+'?delete',
				data : {"DIZHI_UID":dizhiuid},
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
					if(response){
						xAlert("信息提示","删除成功",1);
						reload();
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



</script>
	<form id="gdzxtformid" method="post" >
		<input type="hidden" name="gdzxt_gcid" id="gdzxt_gcid" >
	</form>
</body>
</html>