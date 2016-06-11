<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>

<title><fmt:message key="ui.title" /></title>
<#noparse>
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jkjc-btnStyle.css" type="text/css"></link>
</#noparse>
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
    padding-left: 2px;
    padding-right: 3px;
} 
</style>
</head>
<body >
	<div class="main-container" id="main-container">
			<div class="page-content" >
				 <div class="col-sm-12" id="inside">
		    <!-- form 开始 位置 -->
					<form class="form-inline"  id="queryForm" width="100%"
					   role="form" style="border:solid 1px #ddd;line-height:50px;vertical-align:middle;">
				<!--  <input id="PROJECT_UID" type="hidden" fieldname="t.PROJECT_UID" operation="="/>  -->
				 
					 <div class="form-group">
					   <div class="checkbox form-group" >
							    <label class="checkbox inline" style="">
									<input name="form-field-checkbox"  checked="checked" type="checkbox"  class="ace"  value="FB" >
									<span class="lbl"> 分部工程</span>
								</label>	
							   <label>
									<input name="form-field-checkbox" checked="checked"  type="checkbox"  class="ace"  value="FX">
									<span class="lbl"> 分项工程</span>
								</label>
						 
								<label>
									<input name="form-field-checkbox" checked="checked"  type="checkbox"  class="ace"   value="ZX">
									<span class="lbl"> 专项工程</span>
								</label>
								 <label>
									<input name="form-field-checkbox" checked="checked"  type="checkbox"  class="ace"  value="YB">
									<span class="lbl"> 隐蔽工程</span>
								</label>
							 	<label>
									<input name="form-field-checkbox" checked="checked"  type="checkbox"  class="ace"   value="JG">
									<span class="lbl"> 工程总竣工</span>
								</label>
						 </div>    
						 
							 &nbsp;&nbsp;&nbsp; 			
							 <input id="selectId" placeholder="分部工程名称" class="form-control" onclick="select();" style="width: 140px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;
								  background-color: #ffffff;background-position: right center; background-repeat: no-repeat;" name="t.FBFXGC_NAME" fieldname="t.FBFXGC_NAME" operation="like" logic="and">  
						 &nbsp;&nbsp;
						    验收日期：
						 <input class="form-control" data-date-format="yyyy-mm-dd"
										style="width: 100px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"
										fieldname="t.YSRQ" id="REPORT_DATE_ONE"
										operation="&gt;" logic="and" />
								~
						 <input class="form-control" data-date-format="yyyy-mm-dd"
								style="width: 100px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"		
											fieldname="t.YSRQ" id="REPORT_DATE1"
										operation="&lt;" logic="and" />
										&nbsp;  
						 
						<button id="${table.name}_searchForPlan" class="btn btn-link btn-sm" type="button">
								<i class="colorshow ace-icon glyphicon glyphicon-search"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">查询</span>
						</button>	
						 
						<div style="float: right;">
							 <div id="btnAddDiv"  class="form-group" >
								<a data-target="" id="newAdd_${table.name}" data-toggle="modal">
									<button id="btn_new" style="display:none" class="btn btn-link btn-sm" type="button"><i class="colorshow ace-icon glyphicon glyphicon-plus"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">添加</span></button>
								</a>
							 </div>
							 &nbsp;  
						</div> 
					 </div> 
					  </form>
					  
					  <!--表格区--->
					    <table   sortname="t.${tableKey}" multiselect=false  rownum="1000000" sortorder="asc"  class=""  id="grid_table" 
							    action="<#noparse>${ctx}</#noparse>/${controllerName}?query">
							<tr>
							
							  <#if  fields?exists>
							    <#list fields  as field>
							        <#if  field.comment !=""  && field.column_key!="PRI">  
									    <th name="${field.name}"   align="center" formatter="">${field.comment}</th> 
								   </#if>
							    </#list>
							   </#if>
						 
								<th name="XIANCHANG_UID"  align="center" formatter="doUpdate">操作</th>
							</tr> 
						</table> 
						
						<script type="text/javascript">
							var $path_base = "/";
						</script>
				</div>
			</div>
		</div>
		<div id="${table.name}-input" class="modal"></div>
<#noparse>
<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
<script src="${pageContext.request.contextPath }/assets/js/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript"> 
var controllernameUrl="${pageContext.request.contextPath}/";
</#noparse>	
controllernameUrl = controllernameUrl+"${controllerName}";
var scripts =[null];
var gridwidth;
var setHeight;

<!--------------------------------------初始化区----------------------------------------->

ace.load_ajax_scripts(scripts, function() {
     init(); 
	$("#jcjbUid").val(getCheckboxV('form-field-checkbox')); 
     DatePicker.datepicker(".REPORT_DATE1");
     DatePicker.datepicker(".REPORT_DATE2"); 
	$("#PROJECT_UID").val($("#project_uid").val());
	
	gridwidth=$("#inside").width();
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
	 
    //查询按钮 (报表)
	$("#${table.name}_searchForPlan").click(function(){
		$("#jcjbUid").val(getCheckboxV('form-field-checkbox'));
	    jQuery("#grid_table").jqGrid().trigger("reloadGrid");
	}); 
	
	
	<!---------------------------添加按钮监听事件区--------------------------->
	$('#newAdd_${table.name}').click(function() {
	$('#newAdd_${table.name}').attr("data-target","${table.name}-input");
		$('#${table.name}-input').removeData("bs.modal");
		$("#${table.name}-input").empty();
		$('#${table.name}-input').modal({
			backdrop: 'static'
		});
		
		$.get("<#noparse>${ctx}</#noparse>/jsp/business/${table.name}/${addOutFile}",function(data) {
			$("#${table.name}-input").empty();
			$("#${table.name}-input").html("");
			$("#${table.name}-input").html(data);
		}); 
	});
	 
	checkBtnAuthority($("#project_uid").val(),$("#projectUserId").val(),"12090001","btn_new");

}

function doUpdate(cellvalue, opts, rowObject){
	var log_uid = rowObject.PROJECT_LOG_UID;
	var PROJECT_UID = rowObject.PROJECT_UID; 
	var ${tableKey} = rowObject.${tableKey};
	var showHtml ="<div class='hidden-sm hidden-xs action-buttons' style='padding-top:15px;'>";	
    showHtml +="<a class=\"blue\" title=\"查看现场信息\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
	showHtml +="onclick=\"showFileList('"+${tableKey}+"','"+PROJECT_UID+"');\">" ;
	showHtml +="<span class=\"glyphicon glyphicon-file bigger-110\"></span>" ;
	showHtml +="</a>&nbsp;"; 	
	
	if(checkTabAuthority($("#project_uid").val(),$("#projectUserId").val(),"12090002")){
		showHtml +="<a class=\"blue\" title=\"修改现场信息\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		showHtml +=	" onclick=\"eidtLlog('"+${tableKey}+"','"+PROJECT_UID+"');\">";
		showHtml +="<span class=\"glyphicon glyphicon-edit bigger-110\"></i>";
		showHtml +="</a> &nbsp;";
	}
	if(checkTabAuthority($("#project_uid").val(),$("#projectUserId").val(),"12090003")){
		showHtml +="<a class=\"red\" title=\"删除\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		showHtml +=	" onclick=\"removeData('"+${tableKey}+"');\">";
		showHtml +="<span class=\"glyphicon colorshow1 glyphicon-trash bigger-110\"></i>";
		showHtml +="</a>&nbsp;";
	}  
	return 	showHtml;
}

<!--------------------------------------监听事件区----------------------------------------->

function showFileList(${tableKey},project_uid){
	$('#newAdd').attr("data-target","${table.name}-input");
	$('#${table.name}-input').removeData("bs.modal");
	$("#${table.name}-input").empty();
	$('#${table.name}-input').modal({
		backdrop: 'static'
	});
	
	$.get("<#noparse>${ctx}</#noparse>/jsp/business/${table.name}/${detailOutFile}?${tableKey}="+${tableKey}+"&PROJECT_UID="+project_uid,function(data) {
		$("#${table.name}-input").empty();
		$("#${table.name}-input").html("");
		$("#${table.name}-input").html(data);
	});
}
function eidtLlog(${tableKey},project_uid,yanshou_type){
		$('#newAdd').attr("data-target","${table.name}-input");
		$('#${table.name}-input').removeData("bs.modal");
		$("#${table.name}-input").empty();
		$('#${table.name}-input').modal({
			backdrop: 'static'
		});
		
		$.get("<#noparse>${ctx}</#noparse>/jsp/business/${table.name}/${editOutFile}?${tableKey}="+${tableKey}+"&PROJECT_UID="+project_uid,function(data) {
			$("#${table.name}-input").empty();
			$("#${table.name}-input").html("");
			$("#${table.name}-input").html(data);
		});
}

function removeData(${tableKey}){
		bootbox.confirm("确认删除吗？", function(result) {
		
		if (result) {
		 var data="{'response':{'data':[{'${tableKey}':'"+${tableKey}+"'}]}}";						
			$.ajax({
				url :controllernameUrl+'?delete',
				data : {"msg":data},
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
						xAlert("信息提示","删除成功",1);
					_reload(); 
			      }
			}); 	
		} else {
			return;
		}
	});
}

function _reload(){
	jQuery("#grid_table").jqGrid().trigger("reloadGrid");
}

<!--------------------------------------------------------------------------------->
function doYanshouTypeStatus(cellvalue, opts, rowObject){
	var status = rowObject.YANSHOU_TYPE;
	var repStatus = "";
	if(status.indexOf("FB") != -1){//说明是已经完成
		repStatus="分部工程验收";
	}else if(status.indexOf("ZX")!=-1){
		repStatus="专项工程验收"; 
	}else if(status.indexOf("YB")!=-1){ 
		repStatus="隐蔽工程验收";
	}else if(status.indexOf("JG")!=-1){
		repStatus="竣工总验收";
	}
	var thtml ="<div class=\"hidden-sm hidden-xs btn-group\" >";
	    thtml +="<span class=\"bigger-100\">"+repStatus+"</span>";
	    thtml +="</div>";
	return thtml; 
}


</script>

</body>
</html>