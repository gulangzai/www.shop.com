<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>

<title>insertDemo</title>

<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/sys_resources/plugins/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/sys_resources/plugins/zTree/css/demo.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/plugins/zTree/js/jquery.ztree.all-3.5.min.js"></script>
<style type="text/css">
	div#rMenu {position:absolute; visibility:hidden; top:0; background-color: #555;text-align: left;padding: 2px;}
	div#rMenu ul li{
		margin: 1px 0;
		padding: 0 5px;
		cursor: pointer;
		list-style: none outside none;
		background-color: #DFDFDF;
	}
	th {
	font-weight: bold;
}
th {
	display: table-cell;
	vertical-align: inherit;
}
table {
	border-collapse: collapse;
	border-spacing: 0;
}
table {
	border-spacing: 2px;
	border-color: gray;
}
</style>
</head>
<body>
<app:dialogs/>
	  
<div class="container-fluid">
  <div class="row-fluid">
    <div class="span3">
    <p></p>
	  <div class="zTreeDemoBackground left">
	    <ul id="menuTree" class="ztree">
	    	<img alt="请稍后，正在加载数据……" src="${pageContext.request.contextPath }/assets/img/loading.gif" />
	    </ul>
	  </div>
	  
	  <!-- 右键菜单 -->
	  <div id="rMenu">
		<ul>
			<li id="m_add" onclick="addMenu();">增加菜单</li>
			<li id="m_fresh" onclick="freshMenu()">刷新</li>
			<li id="m_reset" onclick="updateMenu();">修改菜单</li>
			<li id="m_del" onclick="deleteMenu();">删除节点</li>
			<li id="m_addPage" onclick="addPageResource();">增加按钮节点</li>
		</ul>
	  </div>
    </div>
    <div class="span9">
		<div class="B-small-from-table-autoConcise">
			<h4 class="title" id="h4">菜单管理
		    <span class="pull-right">
		    	<input type="hidden" id="valueType" />
		    	<button class="btn" id="saveUserInfo">保存</button>
		    </span>
			</h4>
			
		<form method="post" id="queryForm"  >
		<table style="width: 100%" class="B-table">
		<!--可以再此处加入hidden域作为过滤条件 -->
			<TR  style="display:none;">
				<TD class="right-border bottom-border"></TD>
				<TD class="right-border bottom-border">
					<input type="text" fieldname="LEVELNO" id="LEVELNO" name="LEVELNO" />
				</TD>
			</TR>
		<!--可以再此处加入hidden域作为过滤条件 -->
			<tr>
				<th width="5%">所属应用系统</th>
				<td width="10%">
					<select type="text" placeholder="必填" check-type="required" id="APP_NAME" name="APP_NAME" fieldname="APP_NAME" kind="dic" src="APP_NAME"></select>
				</td>
			</tr>
			<tr>
				<th width="5%">菜单别名（英文、数字）</th>
				<td width="10%">
					<input type="text" placeholder="必填" check-type="required" id="NAME" name="NAME" fieldname="NAME" ></td>
			</tr>
			<tr>
				<th width="5%">菜单标题（汉字）</th>
				<td width="10%">
					<input type="text" placeholder="必填" check-type="required" id="TITLE" name="TITLE" fieldname="TITLE" />
				</td>
			</tr>
			<tr>
				<th width="5%">父级菜单编码</th>
				<td width="10%">
					<input type="text" id="PARENT" name="PARENT" fieldname="PARENT" ></td>
			</tr>
			<tr>
				<th width="5%">显示顺序号</th>
				<td width="10%">
					<input type="number" placeholder="" check-type="" id="ORDERNO" name="ORDERNO" fieldname="ORDERNO" ></td>
			</tr>
			<tr>
				<th width="5%">页面显示区域</th>
				<td width="10%">
					<select type="text" placeholder="必填" check-type="required" id="TARGET" name="TARGET" fieldname="TARGET" kind="dic" src="YMXSQY"></select></td>
			</tr>
			<tr>
				<th width="5%">页面web路径</th>
				<td width="10%">
					<input type="text" placeholder="必填" check-type="required" id="LOCATION" name="LOCATION" fieldname="LOCATION" ></td>
			</tr>
		</table>
		</form>
		<form method="post" id="queryPageResourceForm"  style="display :none;">
			<table class="B-table" width="100%" >
	      		<input type="hidden" id="PAGE_ID" fieldname="FS_PAGE_RESOURCE_ID" name = "FS_PAGE_RESOURCE_ID"/>
		  	
		        <tr>
					<th width="8%" class="right-border bottom-border text-right">资源名称</th>
		       	 	<td class="bottom-border right-border" width="23%">
		         		<input class="span12" style="width:85%" id="PAGE_NAME" type="text" placeholder="必填" check-type="required" fieldname="NAME" name = "NAME" />
		       	 	</td>
		         	<th width="8%" class="right-border bottom-border text-right">资源定位符</th>
		       		<td class="bottom-border right-border"width="15%">
		         		<input class="span12" style="width:100%" id="PAGE_URL" type="text" fieldname="URL" name = "URL"  />
		         	</td>
		        </tr>
		        <tr>
					<th width="8%" class="right-border bottom-border text-right disabledTh">父级对象</th>
					<td width="17%" class="right-border bottom-border">
						<input class="span12" style="width:100%" id="PAGE_PARENT" type="text" fieldname="PARENT" name = "PARENT" disabled/>
					</td>
				</tr>
				<tr>
					<th width="8%" class="right-border bottom-border text-right">资源描述</th>
					<td width="17%" class="right-border bottom-border" colspan="3" id="showMO">
						<textarea class="span12" rows="2" id="PAGE_MEMO" check-type="maxlength" maxlength="4000" fieldname="MEMO" name="MEMO"></textarea>
					</td>
				</tr>
			</table>
		</form>
	
		</div>
		
	
		  选中要操作的节点右键，即可操作菜单
		  <form method="post" id="queryUserForm">
		  	<table class="B-table" width="100%">
				<!--可以再此处加入hidden域作为过滤条件 -->
				<TR  style="display:none;">
					<TD>
						<input type="text" class="span12" kind="text"  fieldname="rownum" value="1000" operation="<=" >
					</TD>
				</TR>
			</table>
		  </form>
		  
		  <div class="B-small-from-table-autoConcise" id="userList">
				<h4 class="title" id="h4">功能按钮
					<span class="pull-right">
						<input type="hidden" id="valueType" />
					</span>
				</h4>
			  <div class="overFlowX">
		            <table width="100%" class="table-hover table-activeTd B-table" id="DT2" type="single"  noPage=true>
		                <thead>
		                	<tr>
		                		<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
								<th fieldname="NAME" colindex=1 tdalign="center" maxlength="30" >&nbsp;资源名称&nbsp;</th>
								<th fieldname="MEMO" colindex=2 tdalign="center" maxlength="30" >&nbsp;资源描述&nbsp;</th>
								<th fieldname="URL" colindex=3 tdalign="center" maxlength="30" >&nbsp;资源定位符&nbsp;</th>
								<th fieldname="SFYX" colindex=5 tdalign="center" >&nbsp;是否有效&nbsp;</th>
								<th fieldname="SSFL" colindex=6 tdalign="center" >&nbsp;资源分类&nbsp;</th>
								<th fieldname="SSFL" colindex=6 tdalign="center"  CustomFunction="caozuoFun">&nbsp;操作&nbsp;</th>
		                	</tr>
		                </thead>
		              	<tbody></tbody>
		           </table>
		       </div>
	       </div>
		  
		<div class="B-small-from-table-autoConcise" id="userList">
			<h4 class="title" id="h4">具有权限用户列表
				<span class="pull-right">
					<input type="hidden" id="valueType" />
				</span>
			</h4>
			
			<div class="overFlowX">
	            <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single">
					<thead>
						<tr>
							<th name="XH" id="_XH" width="3%">#</th>
							<th fieldname="ACCOUNT" >&nbsp;登录名&nbsp;</th>
							<th fieldname="NAME" >&nbsp;用户姓名&nbsp;</th>
							<th fieldname="SEX" tdalign="center" width="5%">&nbsp;性别&nbsp;</th>
							<th fieldname="DEPARTMENT" >&nbsp;所在单位&nbsp;</th>
							<th fieldname="PERSON_KIND" >&nbsp;用户类别&nbsp;</th>
							<th fieldname="FLAG" >&nbsp;是否有效&nbsp;</th>
						</tr>
					</thead>
				    <tbody></tbody>
				</table>
	       </div>
		</div>
	</div>
  </div>
</div>
<FORM name="frmPost" method="post" style="display:none" target="_blank" id ="frmPost">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML" id = "queryXML">
		<input type="hidden" name="txtXML" id = "txtXML">
		<input type="hidden" name="txtFilter"  order="asc" fieldname = "to_number(sort)"	id = "txtFilter">
		<input type="hidden" name="resultXML" id = "resultXML">
		<input type="hidden" name="queryResult" id = "queryResult">
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData">
	</FORM>
<script type="text/javascript">

function caozuoFun(obj){
	var status=obj.SFYX;
	var showHtml="";
	if(status=='1'){
		showHtml +="<span class='btn btn-link' id='addSpan' onclick='addRoles(this);'  >";
		showHtml +="角色 ";
		showHtml +="</span>&nbsp;&nbsp;";
		showHtml +="<span class='btn btn-link' id='addSpan' onclick='addUsers(this);'  >";
		showHtml +="用户 ";
		showHtml +="</span>";
	}
	
	return showHtml;
}

function addRoles(obj){
	while(obj.tagName.toLowerCase() != "tr"){
	    obj = obj.parentNode;
	    if(obj.tagName.toLowerCase() == "table")return null;
	   }
	obj.click();
	var data = $("#DT2").getSelectedRow();
	var tempJson = convertJson.string2json1(data);
	var fs_page_resource_id=tempJson.FS_PAGE_RESOURCE_ID;
	$(window).manhuaDialog({"title":"按钮菜单>添加到角色","type":"text","content":"${pageContext.request.contextPath }/jsp/framework/system/menu/pageResourceToRoles.jsp?fs_page_resource_id="+fs_page_resource_id,"modal":"4"});
}

function addUsers(obj){
	while(obj.tagName.toLowerCase() != "tr"){
	    obj = obj.parentNode;
	    if(obj.tagName.toLowerCase() == "table")return null;
	   }
	obj.click();
	var data = $("#DT2").getSelectedRow();
	var tempJson = convertJson.string2json1(data);	
	var fs_page_resource_id=tempJson.FS_PAGE_RESOURCE_ID;							 
	$(window).manhuaDialog({"title":"按钮菜单>添加到用户","type":"text","content":"${pageContext.request.contextPath }/jsp/framework/system/menu/pageResourceToUsers.jsp?fs_page_resource_id="+fs_page_resource_id,"modal":"4"});
}

var controllername= "${pageContext.request.contextPath }/menuController";
var controllernamePage= "${pageContext.request.contextPath }/pageResourceController";
var setting = {
	async: {
		enable: true,
		url: controllername + "?getAllMenu",
		autoParam: ["id"],
		dataFilter: function (treeId, parentNode, responseData) {
            return responseData;
        }
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
	},
	callback: {
		onRightClick  : showMenuValue,
		onClick: showMenuInfo,
		onDblClick: showUserList,
		onAsyncSuccess: function(){zTree.expandAll(true);}
	}
};

function showMenuInfo(event, treeId, treeNode) {
    menuTreeJson = treeNode;
    $("#NAME").val(menuTreeJson.id);
	$("#TITLE").val(menuTreeJson.name);
	$("#PARENT").val(menuTreeJson.parentId);
	$("#TARGET").val(menuTreeJson.target);
	$("#LOCATION").val(menuTreeJson.location);
	$("#ORDERNO").val(menuTreeJson.orderno);
	$("#LEVELNO").val(menuTreeJson.levelno);
	
	$("#APP_NAME").val(menuTreeJson.app_name);
	$("#NAME").attr("readonly", "readonly");
	$("#PARENT").attr("readonly", "readonly");
	
    $("#queryForm").setFormValues(treeNode);
    
    showResouceURL(menuTreeJson.id);
    
    $("#DT1").clearResult();
};

function getQueryDateXw(querydata){
	var json = "{querycondition: {conditions: [" +querydata+" ]},pages:{recordsperpage:10, currentpagenum:1, totalpage:0, countrows:0},orders:[{\"fieldname\":\"parent\",\"order\":\"desc\"}]}";
	return json;
}
function showResouceURL(menuid){
	var querycondition = "{\"logic\":\"and\",\"fieldname\":\"PARENT\",\"operation\":\"=\",\"value\":\""+menuid+"\",\"fieldtype\":'',\"fieldformat\":''}";
	var data=getQueryDateXw(querycondition);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllernamePage+"?query", data, DT2);
}


/**
 * 点击右键时，获取的菜单信息
 */
function showMenuValue(event, treeId, treeNode) {
	menuTreeJson = treeNode;
	if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
		// 取消选中节点
		zTree.cancelSelectedNode();
		showRMenu("root", event.clientX, event.clientY);
	} else if (treeNode && !treeNode.noR) {
		// 设置选中节点
		zTree.selectNode(treeNode);
		showRMenu("node", event.clientX, event.clientY);
	}
}
function showUserList(event, treeId, treeNode) {
	var data = combineQuery.getQueryCombineData(queryUserForm,frmPost, DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllernamePage+"?queryPageMappingUser&menuKey="+treeNode.id, data, DT1);
}
/**
 * 显示右键按钮
 * type	 
 * x	右键时所在的x坐标
 * y	右键时所在的y坐标
 */
function showRMenu(type, x, y) {
	$("#rMenu ul").show();
	if (type=="root") {
		$("#m_add").hide();
		$("#m_fresh").hide();
		$("#m_reset").hide();
		$("#m_del").hide();
	} else {
		var levelno = menuTreeJson.levelno;

		if(levelno == 'final'){
			$("#m_add").hide();
			$('#m_addPage').hide();
		}else{
			$('#m_addPage').show();
		}
		
		// 根节点只能添加
		if(levelno == 0) {
			$("#m_add").show();
			$("#m_fresh").show();
			$("#m_reset").hide();
			$("#m_del").hide();
			$("#m_resource").hide();
			$('#m_addPage').hide();
		// 第三层节点不允许再添加
		} else if(levelno == 3) {
			$("#m_add").hide();
			$("#m_fresh").hide();
			$("#m_reset").show();
			$("#m_del").show();
			$("#m_resource").show();
			$('#m_addPage').show();
		// 其他正常
		} else {
			$("#m_add").show();
			$("#m_fresh").hide();
			$("#m_reset").show();
			$("#m_del").hide();
			$("#m_resource").show();
			$('#m_addPage').show();
		}
		if(levelno=='final'){
			$("#m_add").hide();
			$("#m_addPage").hide();
		}
		
	}
	rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

	$("body").bind("mousedown", onBodyMouseDown);
}

/**
 * 隐藏右键按钮
 */
function hideRMenu() {
	if (rMenu) rMenu.css({"visibility": "hidden"});
	$("body").unbind("mousedown", onBodyMouseDown);
}

function onBodyMouseDown(event) {
	if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
		rMenu.css({"visibility" : "hidden"});
	}
}

/**
 * 添加按钮方法
 */
function addMenu() {
//	$("#h4").html("添加菜单");
	hideRMenu();
	$('#valueType').val('MENU');
	$("#queryPageResourceForm").hide();
	$("#queryForm").show();
	$("#PARENT").val(menuTreeJson.id);
	var thisLevel = menuTreeJson.levelno;
	var nextLevel = parseInt(thisLevel)+1;
	$("#LEVELNO").val(nextLevel);

	$("#NAME").removeAttr("readonly");
	$("#PARENT").attr("readonly", "readonly");

	$("#NAME").val("");
	$("#TITLE").val("");
	$("#TARGET").val("");
	$("#LOCATION").val("");
	
	// 设置操作标识符，1表示添加 
	operatorSign = 1;
}
function addPageResource(){
	hideRMenu();

	$("#queryPageResourceForm").show();
	$("#queryForm").hide();
	$('#valueType').val('PAGE');
	$("#queryPageResourceForm").show();
	$("#queryForm").hide();

	$("#PAGE_PARENT").val(menuTreeJson.id);


	//$("#NAME").attr("readonly", "readonly");
	$("#PAGE_PARENT").attr("readonly", "readonly");
	operatorSign = 1;
}
/**
 * 修改按钮方法
 */
function updateMenu() {
//	$("#h4").html("修改菜单");
	hideRMenu();
	if(menuTreeJson.levelno=='final'){
		$('#valueType').val('PAGE');
		$("#queryPageResourceForm").show();
		$("#queryForm").hide();
		$("#PAGE_ID").val(menuTreeJson.id);
		$("#PAGE_NAME").val(menuTreeJson.name);
		$("#PAGE_PARENT").val(menuTreeJson.parentId);
		//$("#TARGET").val(menuTreeJson.target);
		$("#PAGE_URL").val(menuTreeJson.location);
		//$("#ORDERNO").val(menuTreeJson.orderno);
		$("#showMO").html('<textarea class="span12" rows="2" id="PAGE_MEMO" check-type="maxlength" maxlength="4000" fieldname="MEMO" name="MEMO">'+menuTreeJson.memo+'</textarea>');
	
		//$("#NAME").attr("readonly", "readonly");
		$("#PAGE_PARENT").attr("readonly", "readonly");
	}else{
		$('#valueType').val('MENU');
		$("#queryPageResourceForm").hide();
		$("#queryForm").show();
		
		$("#NAME").val(menuTreeJson.id);
		$("#TITLE").val(menuTreeJson.name);
		$("#PARENT").val(menuTreeJson.parentId);
		$("#TARGET").val(menuTreeJson.target);
		$("#LOCATION").val(menuTreeJson.location);
		$("#ORDERNO").val(menuTreeJson.orderno);
		$("#LEVELNO").val(menuTreeJson.levelno);
	
		$("#NAME").attr("readonly", "readonly");
		$("#PARENT").attr("readonly", "readonly");
	}
	
	// 设置操作标识符，2表示修改
	operatorSign = 2;
}


function freshMenu() {
	$.fn.zTree.init($("#menuTree"), setting);
	hideRMenu();
}

//点击保存按钮
$(function() {
	var saveUserInfoBtn = $("#saveUserInfo");
	saveUserInfoBtn.click(function() {
		if(!operatorSign) {
			//xAlert("提示信息","请选择一个父节点进行添加");
			xInfoMsg("请选择一个父节点进行添加","");
			return;
		}
 		if(sign == 1) {
			 //xAlert("提示信息","登录用户名重复，请重新填写");
			 xInfoMsg("登录用户名重复，请重新填写","");
			 return;
		}
		var type =  $('#valueType').val();

 		if(type=='PAGE'){
 			if($("#queryPageResourceForm").validationButton()) {
 				//生成json串
 				var data = Form2Json.formToJSON(queryPageResourceForm);
 				//组成保存json串格式
 				var data1 = defaultJson.packSaveJson(data);
 				//通过判断id是否为空来判断是插入还是修改
 				
 				var success = false;
 				if(operatorSign == 1) {
 					success = defaultJson.doInsertJson(controllernamePage + "?insert", data1, null);
 				} else if(operatorSign == 2) {
 					success = defaultJson.doUpdateJson(controllernamePage + "?update", data1, null);
 				}
 				$.fn.zTree.init($("#menuTree"), setting);
 			}
 	 	}else if(type=='MENU'){
 	 		if($("#queryForm").validationButton()) {
 				//生成json串
 				var data = Form2Json.formToJSON(queryForm);
 				//组成保存json串格式
 				var data1 = defaultJson.packSaveJson(data);
 				//通过判断id是否为空来判断是插入还是修改
 				
 				var success = false;
 				if(operatorSign == 1) {
 					success = defaultJson.doInsertJson(controllername + "?executeMenu&operatorSign=" + operatorSign, data1, null);
 				} else if(operatorSign == 2) {
 					success = defaultJson.doUpdateJson(controllername + "?executeMenu&operatorSign=" + operatorSign, data1, null);
 				}
 				$.fn.zTree.init($("#menuTree"), setting);
 			}
 	 	}
	});
});

// 检测菜单信息是否重复
var sign;
$(function() {
	var menuName = $("#NAME");
	menuName.blur(function() {
		$.ajax({
			url : controllername+"?queryUnique&menuName="+menuName.val(),
			cache : false,
			async : false,
			dataType : 'json',
			success : function(response) {
				var result = eval("(" + response + ")");
				// 设置添加时得校验标识，1表示数据重复
				sign = result.sign;
				//xAlert("提示信息", result.isUnique);
				xInfoMsg(result.isUnique,"");
			}
		});
	});
	var pageMenuURL = $("#PAGE_URL");
	pageMenuURL.blur(function() {
		$.ajax({
			url : controllernamePage+"?queryUnique&menuName="+pageMenuURL.val()+":"+$("#PAGE_ID").val(),
			cache : false,
			async : false,
			dataType : 'json',
			success : function(response) {
				var result = eval("(" + response + ")");
				// 设置添加时得校验标识，1表示数据重复
				sign = result.sign;
				//xAlert("提示信息", result.isUnique);
				xInfoMsg(result.isUnique,"");
			}
		});
	});
});

var zTree,rMenu,menuTreeJson,operatorSign;
$(document).ready(function() {
	$.fn.zTree.init($("#menuTree"), setting);
	zTree = $.fn.zTree.getZTreeObj("menuTree");
	$("#menuTree").css("height",document.documentElement.clientHeight-50);

	rMenu = $("#rMenu");
});
</script>

</body>
</html>