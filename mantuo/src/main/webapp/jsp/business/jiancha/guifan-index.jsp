<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<title><fmt:message key="ui.title" /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/ztree_css/zTreeStyle.css" type="text/css"/>

<style>

ul.ztree {
    margin-top: 0px;
    border: 1px solid #617775;
    background: #f0f6e4;
    width: 250px;
    height: 400px;
    overflow-y: scroll;
    overflow-x: auto;
}

/*去除背景蓝条**/
a:hover
{ 
	background-color:white;
	background-image: none;
}

</style>
<body class="no-skin">

<div class="main-container" id="main-container">
	<div class="page-content">
		<div class="row" >
			<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class="col-sm-3">
						<div class="widget-box" style="height: 100%;">
						    <div class="widget-header header-color-blue2">
								<h4 class="lighter smaller">
									标准规范
								 </h4>								
							</div>
							<div class="widget-body" id="treeDiv" style="overflow: auto;">
							<!-- 设置style 显示 间隔  "-->
						  		<div  class="widget-main padding-8" style="height: 100%;width:100%;">
							  		  <div id="resourceTree" class="ztree">
							    		<img alt="请稍后，正在加载数据……" src="${pageContext.request.contextPath }/assets/img/loading.gif" />
							          </div>
							          
							    </div>
						  	</div>
						</div>
					</div>
					
					<div class="col-sm-9" >
						<div class="widget-box" style="height: 100%;overflow:hidden;padding-bottom: 48px;" id="_widgeBox">
							<div class="" style="padding-top: 15px;padding-right: 15px;text-align: right;">
									<button id="export" type="button" class="btn  btn-white btn-default btn-round" onclick="importGf();" style="display: none;">&nbsp;&nbsp;导入&nbsp;&nbsp;</button>&nbsp;&nbsp;
							    	<button id="newFL" type="button" class="btn  btn-white btn-default btn-round" onclick="goAdd('FL');" style="display: none;">添加分类</button>
							    	<button id="newSJ" type="button" class="btn  btn-white btn-default btn-round" onclick="goAdd('SJ');" style="display: none;">添加事件</button>
							    	<button id="newGF" type="button" class="btn  btn-white btn-default btn-round" onclick="goAdd('GF');" >添加规范</button>
						    </div> 
						   
						<div class="modal-body">									    
					 	   <form method="post"  class="form-horizontal"  id="queryForm">
						  		<input type="hidden" id="NODE_TYPE" fieldname="t.NODE_TYPE" operation="=" logic="and">
						  		<input type="hidden" id="P_BZGF_UID"  logic="and" fieldname="t.P_BZGF_UID" fieldtype="text">
						  		<input type="hidden" id="gproject_uid"  logic="and" fieldname="t.project_uid" operation="=" fieldtype="text">
								<table  sortname="t.BZGF_UID" multiselect=false  rownum="10000" sortorder="ASC" class="auto_startgrid" id="grid_table" 
									action="${pageContext.request.contextPath }/pmjiancha/pmBzgfController?query" page="false">
									<tr>
										<th name="BZGF_UID"  align=center hidden="hidden" ></th>
										<th name="NODE_TYPE_NAME"  width="2"  align="center">类型</th>
										<th name="NODE_CONTENT"  width="9" align="left">内容</th>
										<th name="USER_NAME"  width="2" align="center">更新人</th>
										<th name="UPDATE_DATE" width="2" align="center" >更新时间</th>
										<th name="BZGF_UID"  width="2" align=center formatter="operate">操作</th>
								
									</tr >
								</table>			  		
							</form>
			
						</div>	
						

			  </div>
			</div>
		</div>
	</div>
</div>
</div>
</div>	
<div id="guifan-input" class="modal" ></div>



<input type="hidden" id="p_uid"/>							
<input type="hidden" id="p_name"/>												
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/plugins/zTree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/plugins/zTree/js/jquery.ztree.exedit-3.5.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/convertJson.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/default.js?version=20131206"> </script>
		
<script type="text/javascript">
var controllername="${pageContext.request.contextPath }/pmjiancha/pmBzgfController";
var scripts =[null];

ace.load_ajax_scripts(scripts, function() {
	 $("#gproject_uid").val($("#project_uid").val());
	 $.fn.zTree.init($("#resourceTree"), getSettings());
	 
	 $('#NODE_TYPE').val(getRadioV('NODE_TYPE'));
		//给表格添加查询条件 ="="
	 //$('#P_BZGF_UID').attr("fieldname","t.P_BZGF_UID");
	 $('#P_BZGF_UID').attr("operation","is");
	 $('#P_BZGF_UID').val('null');
	 var gridwidth=$("#_widgeBox").width()-29;
	 JqgridManage.initJqgrid(grid_table,queryForm,gridwidth);
	 $("#grid_table").setGridHeight($(window).height()-250);
	 init();
	//改变浏览器大小自适应
		$(window).on(
				'resize.jqGrid',
				function() {
					$("#grid_table").jqGrid(    //jqGridtable 自适应width
							'setGridWidth',
							$("#_widgeBox").width()-29);
				});
		$(window).triggerHandler('resize.jqGrid');
});

//菜单树 设置 并获取到后端组织的菜单树数据结构
function getSettings(){
	 var setting = {
				async: {
					enable: true,
					url: controllername+"?creatTree&project_uid="+$("#project_uid").val()						
				},
				view: {
					dblClickExpand: false,
					selectedMulti : false
				},
				data: {
					key: {
						title:"",
						name:"name"
					},
				simpleData: {
						enable: true,
						idKey: "id",
					    pIdKey: "pId"							 
					}
				},
				callback: {
					onClick:zTreeOnClick
				}		
			}; 
	 return setting;
}

//单击 节点 获取数据显示
function zTreeOnClick (event, treeId, treeNode){
	
	$("#P_BZGF_UID").val(treeNode.id);
	$("#p_uid").val(treeNode.id);
	$("#p_name").val(treeNode.name);

	//给表格添加查询条件
	if(treeNode.id==""){
		$('#P_BZGF_UID').attr("operation","is");
		$('#P_BZGF_UID').val('null');
		$("#export").show();
	}else{
		
		$('#P_BZGF_UID').attr("operation","=");
		if(treeNode.NODE_TYPE=='GF'){
			$("#newFL").show();
			$("#newGF").hide();
			$("#newSJ").hide();
			$("#export").hide();
		}else if(treeNode.NODE_TYPE=='FL'){
			$("#newSJ").show();
			$("#newFL").hide();
			$("#newGF").hide();
			$("#export").hide();
		}
	}
	reloadGrid();
}

function init(){
	$("#treeDiv").css("height",$(window).height()-120);
	
	$("#deleteCL").click(function(){
		var treeObj = $.fn.zTree.getZTreeObj("resourceTree");
		//获取被选择的节点
		var nodes = treeObj.getSelectedNodes();		
		if(nodes.length==0){
			alert("请选择节点");
			return;
		}
		var id = nodes[0].id;
		bootbox.confirm("确认要删除吗？", function(result){
			if(result){
				defaultJson.doInsertJson(controllername + "?deleteClfl&id="+id, null);
				$.fn.zTree.init($("#resourceTree"), setting);
				jQuery("#grid_table").jqGrid().trigger("reloadGrid");
			}else{
				return;
			}
		});
	});

}


function reloadGrid(){
	jQuery("#grid_table").jqGrid().trigger("reloadGrid");
}

function reloadIndex(){
	$.fn.zTree.init($("#resourceTree"), getSettings());
	reloadGrid();
}


function operate (cellvalue, opts, rowObject){
	var id = rowObject.BZGF_UID;
	var returnHTML  = "<div class=\"action-buttons\" style=\"cursor: pointer;\">";
		if(checkTabAuthority($("#project_uid").val(),$("#projectUserId").val(),"12050102")){
			returnHTML +=	"<a class=\"blue\" title=\"修改\" style=\"background: none;\" onclick=\"updateXx('"+id+"')\" data-target=\"updateclxx-input\">";
			returnHTML +=		"<i class=\"glyphicon glyphicon-edit bigger-110\"></i>";
			returnHTML +=	"</a> ";
		}
		if(checkTabAuthority($("#project_uid").val(),$("#projectUserId").val(),"12050103")){
			returnHTML +=	"<a class=\"\" title=\"删除\" style=\"background: none;\" onclick=\"deleteXx('"+id+"')\" data-target=\"updateclxx-input\">";
			returnHTML +=		"<i class=\"glyphicon colorshow1 glyphicon-trash bigger-110\"></i>";
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
				data : {"BZGF_UID":id},
				dataType : "json",  
				type : 'post',
				success : function(response) {
					if(response.success){
						xAlert("信息提示","删除成功！");
						reloadIndex();
					}else{
						xAlert("信息提示","删除失败，请联系管理员！");
					}
				}
			});
			
		}else{
			return;
		}
	});	
}

//修改
function updateXx(id){
	$("#guifan-input").removeData("bs.modal");
	$("#guifan-input").empty();
	$("#guifan-input").modal({
		backdrop: 'static'
	});

	$.get("${ctx}/jsp/business/jiancha/guifan-update.jsp",{
		"NODE_TYPE":getRadioV('NODE_TYPE'),
		"p_uid":$('#p_uid').val(),
		"p_name":$('#p_name').val(),
		"bzgf_uid":id
		},function(data){
		$("#guifan-input").empty();
		$("#guifan-input").html("");
		$("#guifan-input").html(data);
	});
}

function trend (cellvalue, opts, rowObject){
	var CL_GUIGE = rowObject.CL_GUIGE;
	var CL_FENLEI_UID = rowObject.CL_FENLEI_UID;
	var FENLEI_NAME = rowObject.FENLEI_NAME;
	var returnHTML  = "<div class=\"action-buttons\" style=\"cursor: pointer;\">";
	returnHTML +=	"<a class=\"green\" style=\"background: none;\" onclick=\"viewTend('"+CL_GUIGE+"','"+CL_FENLEI_UID+"','"+FENLEI_NAME+"')\" data-target=\"trendclxx-input\">";
	returnHTML +=		"&nbsp;&nbsp;&nbsp;&nbsp;<i class=\"ace-icon fa fa-bar-chart-o bigger-130\"></i>&nbsp;&nbsp;&nbsp;&nbsp;";
	returnHTML +=	"</a></div>";	
return returnHTML;
}

function viewTend(CL_GUIGE,CL_FENLEI_UID,FENLEI_NAME){
	$("#trendclxx-input").removeData("bs.modal");
	$("#trendclxx-input").empty();
	$("#trendclxx-input").modal({
		backdrop: 'static'
	});

	$.get("${ctx}/jsp/business/shenjzlk/trendCLXX.jsp?CL_GUIGE="+CL_GUIGE+"&CL_FENLEI_UID="+CL_FENLEI_UID+"&FENLEI_NAME="+FENLEI_NAME,function(data){
		$("#trendclxx-input").empty();
		$("#trendclxx-input").html("");
		$("#trendclxx-input").html(data);
	});
}

//导入
function importGf(){
	var treeObj = $.fn.zTree.getZTreeObj("resourceTree");
	//获取被选择的节点
	var nodes = treeObj.getSelectedNodes();		
	if(nodes.length==0){
		xAlert("信息提示","请选择节点！");
		return;
	}

	
	window.open("${pageContext.request.contextPath }/jsp/business/jiancha/sysguifan-import.jsp");
}

//添加规范、分类、事件
function goAdd(type){
	var treeObj = $.fn.zTree.getZTreeObj("resourceTree");
	//获取被选择的节点
	var nodes = treeObj.getSelectedNodes();		
	if(nodes.length==0){
		xAlert("信息提示","请选择节点！");
		return;
	}
	
	$('#guifan-input').removeData("bs.modal");
	$("#guifan-input").empty();
	$('#guifan-input').modal({
		backdrop: 'static'
	});
		
	$.get("${ctx}/jsp/business/jiancha/guifan-add.jsp",
		{
		"PROJECT_UID":$("#project_uid").val(),
		"NODE_TYPE":type,
		"p_uid":$('#p_uid').val(),
		"p_name":$('#p_name').val()
		},function(data) {
		$("#guifan-input").empty();
		$("#guifan-input").html("");
		$("#guifan-input").html(data);
	});
	
}


//选择导入 回调
function doImport(str){

	$.ajax({
		url :controllername+"?importGF",
		cache : false,
		async :	false,
		data:{
			"BZGF_UID":str,
			"PROJECT_UID":$('#project_uid').val()	
		},
		dataType : "json",  
		type : 'post',
		success : function(response) {
			if(response.success){
				xAlert("信息提示","导入成功",1);
				reloadIndex();
			}
	    }
	});
}
</script>
</body>
</html>