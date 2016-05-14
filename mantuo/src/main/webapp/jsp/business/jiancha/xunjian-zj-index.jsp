<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<title><fmt:message key="ui.title" /></title>



<body class="no-skin">
	<div class="main-container" id="main-container">
			<div class="page-content" id="inside">
				<div class="col-sm-12">
		    <!-- form 开始 位置 -->
					<form class="form-inline"  id="queryForm" width="100%"
					   role="form" style="border:solid 1px #ddd;height:50px;line-height:50px;vertical-align:middle;">
					   <input type="hidden" id="jcjbUid" fieldname='x.JIANCHA_LEVEL_UID' fieldtype='text' operation='in'>
					   <input type="hidden"  fieldname='x.XUNJIAN_TYPE' fieldtype='text' operation='=' value="ZJ">
					   <input type="hidden" id="gproject_uid"  logic="and" fieldname="x.project_uid" operation="=" fieldtype="text">
					   <div class="form-group" id="qjcjb">
					   		
					   </div>
						<div class="form-group" style="margin-left: 15px;">
						 检查日期：
						 <input class="form-control" data-date-format="yyyy-mm-dd"
										style="width: 130px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"
										fieldname="x.XUNJIAN_DATE" id="XUNJIAN_DATE_B"
										operation="&gt;" logic="and" />
								—
						 <input class="form-control" data-date-format="yyyy-mm-dd"
								style="width: 130px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"		
										fieldname="x.XUNJIAN_DATE" id="XUNJIAN_DATE_E"
										operation="&lt;" logic="and" />
										&nbsp;&nbsp; 
						  <button id="searchForPlan" class="btn btn-link btn-sm" type="button">
								<i class="colorshow ace-icon glyphicon glyphicon-search"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">查询</span>
						  </button>	 				
						</div> 

						<div style="float: right;">
							 <div  class="form-group" >
									<button class="btn btn-link btn-sm" type="button" onclick="goAdd();">
										<i class="colorshow ace-icon glyphicon glyphicon-plus"></i>
										<span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">添加</span>
									</button>
							 </div>
						</div>
					</form>
					    <table   sortname="x.XUNJIAN_UID" multiselect=false  rownum="10000" sortorder="asc"   id="grid_table" 
									action="${ctx}/pmjiancha/pmXunjianController?query" >
							<tr>
								<th name="LEVEL_NAME"  width="6" maxlength="100" align="center">检查级别</th>
								<th name="XUNJIAN_DATE"  width="2" maxlength="100" align="center">检查日期</th>
								<th name="JIERUN"  width="6" align="center">检查结论</th>
								<th name="CANJIAN"  width="2" align="center">参检人员</th>
								<th name="XUNJIAN_UID"  width="2" align=center formatter="operate">操作</th>
							</tr> 
						</table> 
						<script type="text/javascript">
							var $path_base = "/";
						</script>
				</div>
			</div>
		</div>
		<div id="xunjian-input" class="modal"></div>

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/convertJson.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/loadFields.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/default.js?version=20131206"> </script>
		
<script type="text/javascript">
var controllername="${pageContext.request.contextPath }/pmjiancha/pmXunjianController";
var controlletnamejc="${pageContext.request.contextPath }/pmjiancha/pmJianchaBzController";
var scripts =[null];

ace.load_ajax_scripts(scripts, function() {
	$("#gproject_uid").val($("#project_uid").val());
	getJcjb();
	DatePicker.datepicker("#XUNJIAN_DATE_B");
	DatePicker.datepicker("#XUNJIAN_DATE_E");
	$('#jcjbUid').val(getCheckboxV('JIANCHA_LEVEL'));
	var gridwidth=$("#queryForm").width()+1;
	 JqgridManage.initJqgrid(grid_table,queryForm,gridwidth);
	 $("#grid_table").setGridHeight($(window).height()-250);
	 
	 $("#searchForPlan").click(function(){
		 $('#jcjbUid').val(getCheckboxV('JIANCHA_LEVEL'));
		 reloadGrid();
	 });
	//改变浏览器大小自适应
		$(window).on(
				'resize.jqGrid',
				function() {
					$("#grid_table").jqGrid(    //jqGridtable 自适应width
							'setGridWidth',
							$("#queryForm").width()+1);
				});
		$(window).triggerHandler('resize.jqGrid');
	 
		//权限检查
		checkBtnAuthority($("#project_uid").val(),$("#projectUserId").val(),"12050401","btn_new"); 
});



function reloadGrid(){
	jQuery("#grid_table").jqGrid().trigger("reloadGrid");
}


function operate (cellvalue, opts, rowObject){
	var id = rowObject.XUNJIAN_UID;
	var returnHTML  = "<div class=\"action-buttons\" style=\"cursor: pointer;\">";
	returnHTML +=	"<a title=\"查看\" class=\"blue\" style=\"background: none;\" onclick=\"seeXx('"+id+"')\" data-target=\"updateclxx-input\">";
	returnHTML +=		"&nbsp;<i class=\"glyphicon glyphicon-file bigger-110\"></i>&nbsp;";
	returnHTML +=	"</a> ";
	if(checkTabAuthority($("#project_uid").val(),$("#projectUserId").val(),"12050402")){
		returnHTML +="<a class=\"\" title=\"修改\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		returnHTML +="onclick=\"updateXx('"+id+"')\">" ;
		returnHTML +="<span class=\"glyphicon glyphicon-edit bigger-110\"></span>" ;
		returnHTML +="</a>&nbsp;";
	}
	if(checkTabAuthority($("#project_uid").val(),$("#projectUserId").val(),"12050403")){
		returnHTML +=	"<a class=\"\" title=\"删除\" style=\"background: none;\" onclick=\"deleteXx('"+id+"')\" data-target=\"updateclxx-input\">";
		returnHTML +=		"&nbsp;<i class=\"glyphicon colorshow1 glyphicon-trash bigger-110\"></i>&nbsp;";
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
				data : {"XUNJIAN_UID":id},
				dataType : "json",  
				type : 'post',
				success : function(response) {
					if(response.success){
						xAlert("信息提示","删除成功！");
						reloadGrid();
					}else{
						xAlert("信息提示","更新失败，请联系管理员！");
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
	$("#xunjian-input").removeData("bs.modal");
	$("#xunjian-input").empty();
	$("#xunjian-input").modal({
		backdrop: 'static'
	});

	$.get("${ctx}/jsp/business/jiancha/xunjian-zj-update.jsp",{
		"xunjian_uid":id
		},function(data){
		$("#xunjian-input").empty();
		$("#xunjian-input").html("");
		$("#xunjian-input").html(data);
	});
}

//查看
function seeXx(id){
	$("#xunjian-input").removeData("bs.modal");
	$("#xunjian-input").empty();
	$("#xunjian-input").modal({
		backdrop: 'static'
	});

	$.get("${ctx}/jsp/business/jiancha/xunjian-zj-view.jsp",{
		"xunjian_uid":id
		},function(data){
		$("#xunjian-input").empty();
		$("#xunjian-input").html("");
		$("#xunjian-input").html(data);
	});
}

//添加
function goAdd(){
	
	$('#xunjian-input').removeData("bs.modal");
	$("#xunjian-input").empty();
	$('#xunjian-input').modal({
		backdrop: 'static'
	});
		
	$.get("${ctx}/jsp/business/jiancha/xunjian-zj-add.jsp",
		{
		"PROJECT_UID":$("#project_uid").val(),
		"p_uid":$('#p_uid').val(),
		"p_name":$('#p_name').val()
		},function(data) {
		$("#xunjian-input").empty();
		$("#xunjian-input").html("");
		$("#xunjian-input").html(data);
	});
	
}


function getJcjb(){
	$.ajax({
		url :controlletnamejc+"?queryJcjb",
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var html = "";
			$(response.obj).each(function(index,element){
				//if(index==0){
				//	html += "<input type='checkbox'  checked='checked' name='JIANCHA_LEVEL' fieldname='JIANCHA_LEVEL_UID' value='"+element.JIANCHA_LEVEL_UID+"'>"+element.LEVEL_NAME;
				//}else{
					html += "<input type='checkbox' checked='checked' style='margin-left:8px;' name='JIANCHA_LEVEL'  value='"+element.JIANCHA_LEVEL_UID+"'>"+element.LEVEL_NAME;
				//}
				
			});
			$('#qjcjb').html(html);
	      }
	});
}
</script>
</body>
</html>