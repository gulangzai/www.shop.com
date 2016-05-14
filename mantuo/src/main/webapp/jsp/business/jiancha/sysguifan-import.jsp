<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<title><fmt:message key="ui.title" /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/ztree_css/zTreeStyle.css" type="text/css"/>
<%@ include file="/jsp/framework/common/head.jsp"%>
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
</head>
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
									<button id="finishBtn" type="button" class="btn btn-1 btn-sms">确定</button>&nbsp;&nbsp;
							    	<button id="closeBtn" type="button" class="btn btn-2 btn-sms">关闭</button>
						    </div> 
						   
						<div class="modal-body">									    
					 	   <form method="post"  class="form-horizontal"  id="queryForm">
						  		<input type="hidden" id="NODE_TYPE" fieldname="t.NODE_TYPE" operation="=" logic="and">
						  		<input type="hidden" id="P_BZGF_UID"  logic="and" fieldname="t.P_BZGF_UID" fieldtype="text">
						  		<input type="hidden" id="sjuidstr" value="11">
								<table  sortname="t.BZGF_UID" multiselect=false  rownum="10000" sortorder="ASC" class="auto_startgrid" id="grid_table" 
									action="${pageContext.request.contextPath }/pmjiancha/sysBzgfController?query" page="false">
									<tr>
										<th name="BZGF_UID"  align="center" width="1" formatter="formatSelect"></th>
										<th name="NODE_TYPE_NAME"  width="2"  align="center">类型</th>
										<th name="NODE_CONTENT"  width="10" align="left">内容</th>
										<!--  <th name="USER_NAME"  width="2" align="center">更新人</th> -->
										<th name="UPDATE_DATE" width="2" align="center" >更新时间</th>
								
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
<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>												
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/plugins/zTree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/plugins/zTree/js/jquery.ztree.exedit-3.5.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/convertJson.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/default.js?version=20131206"> </script>
		
<script type="text/javascript">
var controllername="${pageContext.request.contextPath }/pmjiancha/sysBzgfController";
var scripts =[null];
var selectContent = new Array();
ace.load_ajax_scripts(scripts, function() {
	 $.fn.zTree.init($("#resourceTree"), getSettings());
	 
	 
		//给表格添加查询条件 ="="
	 //$('#P_BZGF_UID').attr("fieldname","t.P_BZGF_UID");
	 $('#P_BZGF_UID').attr("operation","is");
	 $('#P_BZGF_UID').val('null');
	 var gridwidth=$("#_widgeBox").width()-29;
	 JqgridManage.initJqgrid(grid_table,queryForm,gridwidth);
	 $("#grid_table").setGridHeight($(window).height()-250);
	 init();

});

//菜单树 设置 并获取到后端组织的菜单树数据结构
function getSettings(){
	 var setting = {
				async: {
					enable: true,
					url: controllername+"?creatTree"							
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
	}else{
		$('#P_BZGF_UID').attr("operation","=");
	}
	reloadGrid();
	
	//var sjuidstr1 = $("#sjuidstr").val();
	
	//if(sjuidstr1!=""){
	//	selectContent = sjuidstr1.split(","); //字符分割 
	//}
}

function init(){
	$("#treeDiv").css("height",$(window).height()-120);

	//确定按钮
	$("#finishBtn").click(function(){
		var str = selectContent.join(",");
		if(str==''||str==undefined){
			alert('请至少选择一条内容!');
		}else{
			if(window.opener){
				//执行父窗口方法
				window.opener.doImport(str);
			}
			window.close();
		}
	});
	
	//关闭按钮
	$("#closeBtn").click(function(){
		window.close();
	});

}


function reloadGrid(){
	jQuery("#grid_table").jqGrid().trigger("reloadGrid");
}

function reloadIndex(){
	$.fn.zTree.init($("#resourceTree"), getSettings());
	reloadGrid();
}

//表格第一列格式化
function formatSelect(cellvalue, opts, rowObject){
	var guifan_uid = rowObject.BZGF_UID;
	var guifan_name = rowObject.NODE_CONTENT;
	var node_type = rowObject.NODE_TYPE;
	var v = "";
	var showHtml ="<div class='hidden-sm hidden-xs action-buttons'>";
	if(node_type!='GF'){
		showHtml += "";
	}else{
		if(contains(selectContent,guifan_uid)){
			showHtml +="<input type=\"checkbox\" onclick='checkboxChange(this)' name=\"guifan\" checked=\"checked\" value ='"+guifan_uid+"'>";
		}else{
			showHtml +="<input type=\"checkbox\" onclick='checkboxChange(this)' name=\"guifan\" value ='"+guifan_uid+"' >";
		}	
	}
		        	
	
	showHtml +="</div>"

	return 	showHtml;		
}

//违规事件框 选中/取消选中 时触发
function checkboxChange(obj){
	
	var wgsjUid = $(obj).val();
	if($(obj).prop("checked")){
		selectContent.push(wgsjUid);
	}else{
		for(var i = 0 ; i < selectContent.length ; i++){//移除掉已经保存的违规事件编号
			if(selectContent[i] == wgsjUid){
				selectContent.splice(i,1);
			}
		}
	}
}

//判断某个元素是否在指定的数组中存在
function contains (array ,element){
	for (var i = 0; i < array.length; i++) { 
    	if (array[i] == element) { 
    		return true; 
    	} 
    } 
}


</script>
</body>
</html>