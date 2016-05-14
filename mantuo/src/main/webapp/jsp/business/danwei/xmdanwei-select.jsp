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
	String project_company_uid = request.getParameter("project_company_uid");
	String  uid = request.getParameter("uid");
%>
<style>



</style>
</head>
<body class="no-skin">
<div   id="_widgeBox">
						<div class="modal-body">									    
					 	   <form method="post"  class="form-horizontal"   id="queryForm">
						  		<div class="form-group">
									<input class="form-control"
										style="width: 150px;float:left;margin-left:2%;" name="COMPANY_NAME"
										fieldname="COMPANY_NAME" id="COMPANY_NAME"
										operation="like" logic="and">
							      <button id="search" class="btn btn-link btn-sm" type="button"style="float:left;">
									<i class="#ace-icon glyphicon glyphicon-search  bigger-110"></i>查询
								</button>
									<button id="finishBtn" type="button"style=" float:left;margin-left:25%;" class="btn btn-1 btn-sms">确定</button>&nbsp;&nbsp;
							    	<button id="closeBtn" type="button" class="btn btn-2 btn-sms">关闭</button>
								</div>
								
								
						  	</form>	
								<table  sortname="PROJECT_COMPANY_UID" multiselect=false  rownum="10000" sortorder="ASC" class="auto_startgrid" id="grid_table" 
									action="${pageContext.request.contextPath }/buprojectcompany/buProjectCompanyController?query">
									<tr>
										 <th name="PROJECT_COMPANY_UID"  align="center" width="1" formatter="formatSelect"></th> 
										<th name="COMPANY_TYPE"  width="5" align="center" formatter="showtype">单位类型</th>
										<th name="COMPANY_NAME"  width="5" align="center">单位名称</th>
										<th name="PROJECT_FUZEREN"  width="5" align="center">项目联系人</th>
										<th name="PROJCET_FUZEREN_TEL"  width="5" align="center">电话</th>
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

var scripts =[null];

ace.load_ajax_scripts(scripts, function() {
	 JqgridManage.initJqgrid(grid_table,queryForm,700);
	// $("#grid_table").setGridHeight($(window).height()-250);
	 init();
	
});
//单位类型
function showtype(cellvalue, opts, rowObject){
	var company_type= rowObject.COMPANY_TYPE;
	var thtml ="<div class=\"hidden-sm hidden-xs btn-group\" >";
	if(company_type=="JS"){
		thtml +="<span class=\" bigger-100\">建设单位</span>";
	}else if(company_type=="DJ"){
		thtml +="<span class=\" bigger-100\">代建单位</span>";
	}else if(company_type=="JL"){
		thtml +="<span class=\" bigger-100\">监理单位</span>";
	}else if(company_type=="SG"){
		thtml +="<span class=\" bigger-100\">施工总承包</span>";
	}else if(company_type=="SJ"){
		thtml +="<span class=\" bigger-100\">设计单位</span>";
	}
	
    
    thtml +="</div>";
return thtml;
}
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
	var project_company_uid= rowObject.PROJECT_COMPANY_UID;
	var company_name= rowObject.COMPANY_NAME;
	var company_type = rowObject.COMPANY_TYPE;
	var project_fuzeren=rowObject.PROJECT_FUZEREN;
	var uids = "<%=uid%>";
	
	var m= project_company_uid+","+company_name+","+company_type+","+uids+","+project_fuzeren;
    var project_company_uids="<%=project_company_uid%>"; 
	
	var showHtml ="<div class='hidden-sm hidden-xs action-buttons'>";
     if(project_company_uids==project_company_uid){
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