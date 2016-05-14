<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ page import="com.ccthanking.framework.Constants"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>

<title><fmt:message key="ui.title" /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jkjc-btnStyle.css" type="text/css"></link>
<%
User user = RestContext.getCurrentUser();
String user_uid = "";
String username = "";
if(user!=null){
	user_uid = user.getIdCard();
	username = user.getName();
}else{
	response.sendRedirect("/"+Constants.APP_NAME);
	return;
}
%>
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

.chosen-select{
	height: 30px!important;
	line-height: 38px;
	overflow-y:auto;
}
.chosen-choices .search-field{
	height: 30px!important;
	line-height: 30px;
	overflow-y:auto;
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
						  <input id="" type="hidden" fieldname="xc.GONGKUANG_TYPE" operation="=" value="WM"/> 
						<input id="TARGET_CODE" type="hidden" filedname="TARGET_CODE" value="XCGK">
						<div class="checkbox" >
							<label>
									<input name="form-field-checkbox" type="checkbox" fieldname="STATUS" class="ace" operation="=" checked value="0" >
									<span class="lbl"> 解决中</span>
								</label>
								<label>
									<input name="form-field-checkbox" type="checkbox"  class="ace" operation="=" checked value="1">
									<span class="lbl"> 已解决</span>
								</label>
								<!-- 
								<label>
									<input name="form-field-checkbox" type="checkbox"  class="ace" operation="=" checked value="-1">
									<span class="lbl"> 草稿</span>
								</label>
								 -->
						</div>
						 &nbsp;&nbsp;&nbsp;&nbsp;
						<div class="form-group"><!-- style="width: 130px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;" -->
						  发布时间：
						 <input class="form-control" data-date-format="yyyy-mm-dd"
										style="width: 130px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"
										fieldname="xc.CREATE_DATE" id="REPORT_DATE_ONE1"
										 operation=">=" logic="and"/>
								~
						 <input class="form-control" data-date-format="yyyy-mm-dd"
								style="width: 130px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"		
										fieldname="xc.CREATE_DATE" id="REPORT_DATE11" 
										 operation="<=" logic="and"/>
										&nbsp;&nbsp; 
						
						  <button id="searchForPlan" class="btn btn-link btn-sm" type="button">
								<i class="colorshow ace-icon glyphicon glyphicon-search"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">查询</span>
						  </button>	
						</div>
						<div style="float: right;">
							 <div id="btnAddDiv"  class="form-group" >
								<a data-target="" id="newAdd" data-toggle="modal">
									<button  id="btn_new" class="btn btn-link btn-sm" type="button"><i class="colorshow ace-icon glyphicon glyphicon-plus"></i>
									  <span  style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">导入</span>
									</button>
								</a>
							 </div>
							 &nbsp; 
						</div>
						
					</form>
					 <div id="tableY" class="widget-box">
							<table  sortname="BZJC_TYPE_UID" multiselect=false  rownum="10"
									sortorder="desc"   ExpandColumn="BZJC_NAME"
									class="auto_startgrid" 
									id="content_grid_table" 
									action="${pageContext.request.contextPath }/jcgl/sysBzjcController?queryTree&typeId=1" >
								<tr> 
								    <th name="BZJC_NAME" width="120" align=center >检查指标</th>
									<th name="JC_ZHIBIAO" width="120" align=center >检查指标</th>
									<th name="JC_SSRY" width="120" align=center >实施人员</th>
									<th name="JC_BIAOZHUN" width="120" align=center >检查标准</th>
									<th name="JC_DZMS" width="120" align=center >检查动作描述</th>
									<th name="JC_CHENGGUO" width="120" align=center >检查成果</th>
								</tr>
							</table>
							</div>
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
var controlletname="${pageContext.request.contextPath }/pmxianchang/pmXianchangController";
var controll="${pageContext.request.contextPath }/loguserread/logUserReadController";
var scripts =[null];
var gridwidth;
var setHeight;
ace.load_ajax_scripts(scripts, function() {
	init();
    $(".footer-content").remove();//去除页脚下的版本符号
	JqgridManage.initJqgridTree(content_grid_table,queryForm,$("#queryForm").width());
    $("#content_grid_table").setGridHeight($(window).height()-250);
    
    //改变浏览器大小自适应
		$(window).on(
				'resize.jqGrid',
				function() {
					$("#content_grid_table").jqGrid(    //jqGridtable 自适应width
							'setGridWidth',
							$("#queryForm").width());
				});
		$(window).triggerHandler('resize.jqGrid');
});

function init(){	
	$('#newAdd').click(function() {
			$("#pId").val($("#project_uid").val());
	        var f =document.getElementById('Pfrom');
	        var url='${pageContext.request.contextPath }/openpage/business/jcgl/check-jcbz';
			f.action=url;
			f.target="_blank"; 
			f.submit();
	});
	
	
}

</script>
<form id="Pfrom" method="post" >
		<input type="hidden" name="PID" id="pId" >
		<input type="hidden" name="TYPE" id="typeId" value="1" >
	</form>
</body>
</html>