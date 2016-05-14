<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<title><fmt:message key="ui.title" /></title>
<link rel="icon" href="${pageContext.request.contextPath }/favicon.ico" type="image/x-icon" /> 
<link rel="shortcut icon" href="${pageContext.request.contextPath }/favicon.ico" type="image/x-icon" />  
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/ztree_css/zTreeStyle.css" type="text/css"/>
<%@ include file="/jsp/framework/common/head.jsp"%>
<%
	String company_uids = request.getParameter("company_uid");
	
%>
<style>



</style>
</head>
<body class="no-skin">
<div   id="_widgeBox">
						<div class="modal-body">									    
					 	   <form method="post"  class="form-horizontal"   id="queryForm">
						  		<div class="form-group" >
									<input class="form-control"
										style="width: 150px; float:left;margin-left:2%;" name="COMPANY_NAME"
										fieldname="COMPANY_NAME" id="COMPANY_NAME"
										operation="like" logic="and">
									<button id="search" class="btn btn-link btn-sm" type="button" style="float:left;">
										<i class="#ace-icon glyphicon glyphicon-search  bigger-110"></i>查询
									</button>
									<button id="finishBtn" style=" float:left;margin-left:25%;" type="button"  class="btn btn-1 btn-sms">确定</button>&nbsp;&nbsp;
							    	<button id="closeBtn" type="button" class="btn btn-2 btn-sms">关闭</button>
								</div>
							
								
						  	</form>	
								<table  sortname="COMPANY_UID" multiselect=false  rownum="10000" sortorder="ASC" class="auto_startgrid" id="grid_table" 
									action="${pageContext.request.contextPath }/bucompany/buCompanyController?query">
									<tr>
										 <th name="COMPANY_UID"  align="center" width="1" formatter="formatSelect"></th> 
										
										<th name="COMPANY_NAME"  width="12" align="center">单位名称</th>
										<th name="COMPANY_CODE"  width="2" align="center">单位编号</th>
										<th name="LIANXI_REN" width="2" align="center" >联系人</th>
										
								
									</tr >
								</table>			  		
							
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
var controllername="${pageContext.request.contextPath }/pmjiancha/pmBzgfController";
var scripts =[null];

ace.load_ajax_scripts(scripts, function() {
	 JqgridManage.initJqgrid(grid_table,queryForm,700);
	 //$("#grid_table").setGridHeight($(window).height()-550);
	 init();
	
});
function init(){
	
	$('#search').click(function() {
		jQuery("#grid_table").jqGrid().trigger("reloadGrid");
	});
	$("#finishBtn").click(function(){
		var s = getRadioV("guifan");
		if(s==''||s==undefined){
			alert('请选择一条内容!');
		}else{
		
				window.opener.doCallback(s);
				window.close();
			
			
		}
	});
}

function formatSelect(cellvalue, opts, rowObject){
	//var guifan_uid = rowObject.BZGF_UID;
	//var guifan_name = rowObject.NODE_CONTENT;
	//var v = guifan_uid+","+guifan_name;
	var company_uid= rowObject.COMPANY_UID;
	var company_name= rowObject.COMPANY_NAME;
	var company_code = rowObject.COMPANY_CODE;
	var lianxi_ren= rowObject.LIANXI_REN;
	var lianxi_tel=rowObject.LIANXI_TEL;
	var m= company_uid+","+company_name+","+company_code+","+lianxi_ren+","+lianxi_tel ;
	 var company_uids="<%=company_uids%>";
	
	var showHtml ="<div class='hidden-sm hidden-xs action-buttons'>";
       if(company_uids==company_uid){
     	   showHtml +="<input type=\"radio\"   name=\"guifan\" checked=\"checked\" value ='"+m+"'>";
       }else{
    	   showHtml +="<input type=\"radio\"   name=\"guifan\"  value ='"+m+"'>";
       }
	showHtml +="</div>"

	return 	showHtml;		
}
$(function(){
	//关闭按钮
	$("#closeBtn").click(function(){
		window.close();
	});
});


	









</script>
</body>
</html>