<!DOCTYPE html>
<%@page import="com.copj.modules.utils.spring.SpringContextHolder"%>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<%
	String type = request.getParameter("type");
	String id = request.getParameter("uid");
 %>
<title>实例页面-查询</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/sys_resources/plugins/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/plugins/zTree/js/jquery.ztree.all-3.5.min.js"></script>
</head>
<body>
<div class="modal-dialog width-30 height-auto">
	<div class="modal-content">
		<div class="widget-header widget-header-large">
			<div class="widget-toolbar">
				<a href="#" data-action="close" data-dismiss="modal">
					<i class="ace-icon fa fa-times"></i>
				</a>
			</div>
			<h3 id="myModalLabel" class="blue bigger" style="margin-top: 15px;">菜单信息<span id="title_sp"></span></h3>
		</div>
		<div class="modal-body" style="overflow: scroll;overflow-x:hidden;">
		  <div class="zTreeDemoBackground left" style="height: 100%;margin-right:100px;">
		    <ul id="menuTree" class="ztree" style="height: 400px;width: 350px;">
		    	<img alt="请稍后，正在加载数据……" src="${pageContext.request.contextPath }/assets/sys_resources/plugins/zTree/css/zTreeStyle/img/loading.gif" />
		    </ul>
		  </div>


		</div>

		<div class="modal-footer">
			<button class="btn btn-success btn-sm" id="saveRoleInfo">保存</button>
			<button class="btn btn-danger btn-sm pull-right" id="btnClose" data-dismiss="modal" aria-hidden="true">关闭</button>
		</div>
	</div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/convertJson.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/combineQuery.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/default.js?version=20131206"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/bootstrap-validation.js"> </script>
<script type="text/javascript">
var controllername= "${pageContext.request.contextPath }/sjMenuController";
var type = "<%=type %>"; 
var id = "<%=id %>";
//设置角色权限
var setting = {
	check: {
		enable: true,
		chkStyle : "checkbox",
		chkboxType : {"Y":"ps","N":"s"}
	},
	async: {
		enable: true,
		url: controllername + "?loadAllMenu&type="+type+"&ryid="+id+"&project_uid="+$('#project_uid').val(),
		autoParam: ["id"]
	},
	view: {
		dblClickExpand: true
	},
	data: {
		simpleData: {
			enable: true,
			idKey: "id",
			pIdKey: "parentId"
		}
	}
};

	
//点击保存按钮
$(function() {
	var saveUserInfoBtn = $("#saveRoleInfo");
	saveUserInfoBtn.click(function() {
		var zTree = $.fn.zTree.getZTreeObj("menuTree");
		// 获取输入框被勾选的节点集合
		var nodes = zTree.getCheckedNodes(true);
		//var val = "";
		var codes = "";
		//alert(nodes);
		for(var i = 0; i < nodes.length; i++) {
			//val += nodes[i].id + ",";
			codes += nodes[i].code +",";
		}

	
		if(type=="role"){
			var data = "{\"roleId\":\""+id+"\",\"codes\":\""+codes+"\"}";
			//组成保存json串格式frmPost9
			var data1 = defaultJson.packSaveJson(data);
			//保存角色分配的权限
			defaultJson.doInsertJson(controllername + "?awardMenuToRole", data1);
		}else if(type=="user"){
			var data = "{\"userId\":\""+id+"\",\"project_uid\":\""+$("#project_uid").val()+"\",\"codes\":\""+codes+"\"}";
			//组成保存json串格式frmPost9
			var data1 = defaultJson.packSaveJson(data);
			//保存用户分配的权限
			defaultJson.doInsertJson(controllername+"?awardMenuToUser", data1);
		}
		jQuery("#grid_table").jqGrid().trigger("reloadGrid");
		$('#btnClose').click();
	});
	
	$("#btnCancel").click(function(){
		parent.$(window).manhuaDialog.close();
	});
});

$(document).ready(function() {
	$.fn.zTree.init($("#menuTree"),setting)
});
</script>
</body>
</html>