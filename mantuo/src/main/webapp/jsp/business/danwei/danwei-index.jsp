<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>

<title><fmt:message key="ui.title" /></title>
<style>
/**设置页面 布局 */
.page-content {
    background-color: #fff;
    position: relative;
    margin: 0;
    padding:3px 0px 0px 3px;
}
</style>
</head>
<body class="no-skin">

<div class="main-container" id="main-container">
				<div class="page-content" id="inside">
					
						<form class="form-inline"  id="queryForm"
							style="border:solid 1px #ddd;padding:3px;margin-bottom:5px;vertical-align:middle;">
								
						 <div class="form-group">
									<label>单位名称</label>
								</div>
								<div class="form-group">
									<input class="form-control"
										style="width: 150px;" name="COMPANY_NAME"
										fieldname="COMPANY_NAME" id="COMPANY_NAME"
										operation="like" logic="and">
								</div>
								&nbsp;&nbsp;
								<button id="search" class="btn btn-link btn-sm" type="button">
									<i class="#ace-icon glyphicon glyphicon-search  bigger-110"></i>查询
								</button>
							
							
							 <div class="form-group" >
								<a data-target="" id="new" data-toggle="modal">
									<button id="btn_new"  class="btn btn-link btn-sm" type="button"><i class="colorshow ace-icon glyphicon glyphicon-plus"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">添加</span></button>
								</a>
							
							</div>
						</form>
		
						<table sortname="COMPANY_UID" multiselect=false  rownum="10" sortorder="desc"   class="auto_startgrid"  id="grid_table" 
									action="${ctx}/bucompany/buCompanyController?query ">
							<tr>
								<!-- <th name="PROJECT_NAME"  width="8" formatter="jqgridactions" align="center">操作</th>  -->
								<th name="COMPANY_NAME" width="10" align="center" >单位名称</th>
								<th name="COMPANY_CODE" width="10" align="center" >单位编号</th>
								<th name="LIANXI_REN" width="10" align="center" >联系人</th>
								<th name="LIANXI_TEL" width="10" align="center" >联系电话</th>
								<th name="ADDRESS" width="10" align="center" >单位地址</th>
								<th name="URL" width="10" align="center" >单位网址</th>
								<th name="COMPANY_UID" width="10" align="center" formatter="doUpdate" >操作</th>
							</tr>
						</table>
						<script type="text/javascript">
							var $path_base = "/";//this will be used in gritter alerts containing images
						</script>
							<!-- PAGE CONTENT ENDS -->
					
		        </div>
		      </div>
			<div id="jldwUser-input" class="modal"></div>

<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
	<script
		src="${pageContext.request.contextPath }/assets/js/jquery-ui.custom.min.js"></script>
		
<script type="text/javascript">
var scripts =[null];
var gridwidth;
var setHeight;
ace.load_ajax_scripts(scripts, function() {
	init();
	//$("#PROJECT_UID").val($("#project_uid").val());
	gridwidth = $("#inside").width();
	JqgridManage.initJqgrid(grid_table,queryForm,gridwidth);
		setHeight = $(window).height();
	
	if(navigator.userAgent.indexOf("Firefox") > -1){
	   setHeight = setHeight+50;
	}
	
	 $("#grid_table").setGridHeight(setHeight - 320); 
	//改变浏览器大小自适应
		$(window).on(
				'resize.jqGrid',
				function() {
					$("#grid_table").jqGrid(    //jqGridtable 自适应width
							'setGridWidth',
							$("#inside").width());
				});
		$(window).triggerHandler('resize.jqGrid');
});

function init(){
	
	$('#search').click(function() {
		jQuery("#grid_table").jqGrid().trigger("reloadGrid");
	});

	$("#Btnclean").click(function(){
		 $("#queryForm").clearFormResult();
	     $('#search').click();
	});
	
	
	$('#new').click(function() {
		$('#new').attr("data-target","jldwUser-input");
		$('#jldwUser-input').removeData("bs.modal");
		$("#jldwUser-input").empty();
		$('#jldwUser-input').modal({
			backdrop: 'static'
		});

		$.get("${ctx}/jsp/business/danwei/danwei-add.jsp",function(data) {
			$("#jldwUser-input").empty();
			$("#jldwUser-input").html("");
			$("#jldwUser-input").html(data);
		});
	});

}

function doUpdate(cellvalue, opts, rowObject){
	var company_uid = rowObject.COMPANY_UID;
	   /* showHtml ="<div class='hidden-sm hidden-xs action-buttons'>";	
	    showHtml +="<a class=\"blue\" title=\"查看\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
	    showHtml +=	" onclick=\"showeidtLlog('"+company_uid+"');\">";
	    showHtml +="<span class=\"glyphicon  glyphicon-file bigger-110\"></i>";
	    showHtml +="</a> &nbsp;";
	    */
	    //if(checkTabAuthority($("#project_uid").val(),$("#projectUserId").val(),"12060102")){
		var  showHtml ="<a class=\"blue\" title=\"修改\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		     showHtml +=	" onclick=\"eidtLlog('"+company_uid+"');\">";
		     showHtml +="<span class=\"glyphicon  glyphicon-edit bigger-110\"></i>";
		     showHtml +="</a> &nbsp;";
	   // }
	   // if(checkTabAuthority($("#project_uid").val(),$("#projectUserId").val(),"12060103")){
		    showHtml +="<a class=\"\" title=\"删除\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		    showHtml +=	" onclick=\"removeData('"+company_uid+"');\">";
		    showHtml +="<span class=\"glyphicon colorshow1 glyphicon-trash bigger-110\"></i>";
		    showHtml +="</a>&nbsp;";
	    //}
	        	
	return 	showHtml;
}
//修改单位
function eidtLlog(company_uid)
{
	
		$('#new').attr("data-target","jldwUser-input");
		$('#jldwUser-input').removeData("bs.modal");
		$("#jldwUser-input").empty();
		$('#jldwUser-input').modal({
			backdrop: 'static'
		});

		$.get("${ctx}/jsp/business/danwei/danwei-update.jsp?COMPANY_UID="+company_uid,function(data) {
			$("#jldwUser-input").empty();
			$("#jldwUser-input").html("");
			$("#jldwUser-input").html(data);
		});
	

}
//查看单位
function showeidtLlog(company_uid)
{
	
		$('#new').attr("data-target","jldwUser-input");
		$('#jldwUser-input').removeData("bs.modal");
		$("#jldwUser-input").empty();
		$('#jldwUser-input').modal({
			backdrop: 'static'
		});

		$.get("${ctx}/jsp/business/danwei/danwei-show.jsp?COMPANY_UID="+company_uid,function(data) {
			$("#jldwUser-input").empty();
			$("#jldwUser-input").html("");
			$("#jldwUser-input").html(data);
		});
	

}
function removeData(company_uid){
	bootbox.confirm("确认删除吗？", function(result) {
	if (result) {						
		$.ajax({
			url :'${ctx}/bucompany/buCompanyController?deleteId',
			data : {"company_uid":company_uid},
			cache : false,
			async :	false,
			dataType : "json",  
			type : 'post',
			success : function(response) {
				if(response.success){
					xAlert("信息提示","删除成功",1);
				}else{
					xAlert("信息提示","删除失败,请联系管理员",1);
				}
				jQuery("#grid_table").jqGrid().trigger("reloadGrid");

		      }
		});
					
	} else {
		return;
	}
});
}

</script>

</body>
</html>