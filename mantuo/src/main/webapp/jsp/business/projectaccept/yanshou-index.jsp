<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>

<title><fmt:message key="ui.title" /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jkjc-btnStyle.css" type="text/css"></link>
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
						 <input id="PROJECT_UID" type="hidden" fieldname="t.PROJECT_UID" operation="="/> 
				 	 <input  type="hidden" id="jcjbUid" fieldname="t.YANSHOU_TYPE" fieldtype="text" operation="in" /> 
				 
					 <div class="form-group">
					   <div class="checkbox form-group" >
							    <label class="checkbox inline" style="">
									<input name="form-field-checkbox"  checked="checked" type="checkbox"  class="ace"  value="FB" >
									<span class="lbl"> 分部工程</span>
								</label>	
							<!-- <label>
									<input name="form-field-checkbox" checked="checked"  type="checkbox"  class="ace"  value="FX">
									<span class="lbl"> 分项工程</span>
								</label>
							 -->	
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
								  background-color: #ffffff; background-image: url(./assets/images/search_icon.png);background-position: right center; background-repeat: no-repeat;" name="t.FBFXGC_NAME" fieldname="t.FBFXGC_NAME" operation="like" logic="and">  
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
						 
						<button id="searchForPlan" class="btn btn-link btn-sm" type="button">
								<i class="colorshow ace-icon glyphicon glyphicon-search"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">查询</span>
						</button>	
						 
						<div style="float: right;">
							 <div id="btnAddDiv"  class="form-group" >
								<a data-target="" id="newAdd" data-toggle="modal">
									<button id="btn_new" style="display:none" class="btn btn-link btn-sm" type="button"><i class="colorshow ace-icon glyphicon glyphicon-plus"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">添加</span></button>
								</a>
							 </div>
							 &nbsp;  
						</div> 
					 </div> 
					  </form>
					    <table   sortname="t.YANSHOU_UID" multiselect=false  rownum="1000000" sortorder="asc"  class=""  id="grid_table" 
							   action="${ctx}/projectaccept/pmYanShouController?query" >
							<tr>
								<th name="YANSHOU_TYPE"   align="center" formatter="doYanshouTypeStatus">验收类型</th>
								<th name="FBFXGC_NAME"   align="center">分部工程名称</th>
								<th name="DWGC_NAME"  align="center">单位工程名称</th>
								<th name="SGDW"  align="center" formatter="showsgdw">施工单位</th>
								<th name="FBDW"   align="center" formatter="showfbdw">分包单位</th>
								<th name="JCJG"   align="center" formatter="doResult">检查结果</th> 
								<th name="YSRQ" align="center"  >验收日期</th> 
								<th name="XIANCHANG_UID"  align="center" formatter="doUpdate">操作</th>
							</tr> 
						</table> 
						<script type="text/javascript">
							var $path_base = "/";
						</script>
				</div>
			</div>
		</div>
		<div id="xmgk-input" class="modal"></div>

<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
<script src="${pageContext.request.contextPath }/assets/js/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript"> 
var controlletname="${pageContext.request.contextPath}/projectaccept/pmYanShouController";
var scripts =[null];
var gridwidth;
var setHeight;
ace.load_ajax_scripts(scripts, function() {
	$("#jcjbUid").val(getCheckboxV('form-field-checkbox'));
	
	init(); 
     DatePicker.datepicker("#REPORT_DATE1");
     DatePicker.datepicker("#REPORT_DATE_ONE"); 
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
	$("#searchForPlan").click(function(){
		$("#jcjbUid").val(getCheckboxV('form-field-checkbox'));
	  jQuery("#grid_table").jqGrid().trigger("reloadGrid");
	}); 
	
	$('#newAdd').click(function() {
	$('#newAdd').attr("data-target","xmgk-input");
		$('#xmgk-input').removeData("bs.modal");
		$("#xmgk-input").empty();
		$('#xmgk-input').modal({
			backdrop: 'static'
		});
		
		$.get("${ctx}/jsp/business/projectaccept/yanshou-add.jsp",function(data) {
			$("#xmgk-input").empty();
			$("#xmgk-input").html("");
			$("#xmgk-input").html(data);
		}); 
	});
	 
	checkBtnAuthority($("#project_uid").val(),$("#projectUserId").val(),"12090001","btn_new");

}
//施工单位
function showsgdw(cellvalue, opts, rowObject){
	var project_company_uid= rowObject.SGDW;
	var thtml ="<div class=\"hidden-sm hidden-xs btn-group\" >";
	$.ajax({
		url : "${pageContext.request.contextPath }/buprojectcompany/buProjectCompanyController?queryid",
		data : {"project_company_uid":project_company_uid},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			 msg = eval("("+response.msg+")");
			 company_name = msg.response.data[0].COMPANY_NAME;
			 thtml +="<span class=\" bigger-100\">"+company_name+"</span>";
		}
	})
    thtml +="</div>";
	return thtml;
}
//分包单位
function showfbdw(cellvalue, opts, rowObject){
	var project_company_uid= rowObject.FBDW;
	var thtml ="<div class=\"hidden-sm hidden-xs btn-group\" >";
	$.ajax({
		url : "${pageContext.request.contextPath }/buprojectcompany/buProjectCompanyController?queryid",
		data : {"project_company_uid":project_company_uid},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			 msg = eval("("+response.msg+")");
			 company_name = msg.response.data[0].COMPANY_NAME;
			 thtml +="<span class=\" bigger-100\">"+company_name+"</span>";
		}
	})
    thtml +="</div>";
	return thtml;
}

function doUpdate(cellvalue, opts, rowObject){
	var log_uid = rowObject.PROJECT_LOG_UID;
	var PROJECT_UID = rowObject.PROJECT_UID;
	var YANSHOU_TYPE = rowObject.YANSHOU_TYPE;
	var YANSHOU_UID = rowObject.YANSHOU_UID;
	var showHtml ="<div class='hidden-sm hidden-xs action-buttons' style='padding-top:15px;'>";	
    showHtml +="<a class=\"blue\" title=\"查看现场信息\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
	showHtml +="onclick=\"showFileList('"+YANSHOU_UID+"','"+PROJECT_UID+"','"+YANSHOU_TYPE+"');\">" ;
	showHtml +="<span class=\"glyphicon glyphicon-file bigger-110\"></span>" ;
	showHtml +="</a>&nbsp;"; 	
	
	if(checkTabAuthority($("#project_uid").val(),$("#projectUserId").val(),"12090002")){
		showHtml +="<a class=\"blue\" title=\"修改现场信息\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		showHtml +=	" onclick=\"eidtLlog('"+YANSHOU_UID+"','"+PROJECT_UID+"','"+YANSHOU_TYPE+"');\">";
		showHtml +="<span class=\"glyphicon glyphicon-edit bigger-110\"></i>";
		showHtml +="</a> &nbsp;";
	}
	if(checkTabAuthority($("#project_uid").val(),$("#projectUserId").val(),"12090003")){
		showHtml +="<a class=\"red\" title=\"删除\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		showHtml +=	" onclick=\"removeData('"+YANSHOU_UID+"');\">";
		showHtml +="<span class=\"glyphicon colorshow1 glyphicon-trash bigger-110\"></i>";
		showHtml +="</a>&nbsp;";
	}  
	return 	showHtml;
}

function showFileList(yanshou_uid,project_uid,YANSHOU_TYPE){
	$('#newAdd').attr("data-target","xmgk-input");
	$('#xmgk-input').removeData("bs.modal");
	$("#xmgk-input").empty();
	$('#xmgk-input').modal({
		backdrop: 'static'
	});
	
	$.get("${ctx}/jsp/business/projectaccept/yanshou-see.jsp?YANSHOU_UID="+yanshou_uid+"&PROJECT_UID="+project_uid+"&YANSHOU_TYPE="+YANSHOU_TYPE,function(data) {
		$("#xmgk-input").empty();
		$("#xmgk-input").html("");
		$("#xmgk-input").html(data);
	});
}
function eidtLlog(yanshou_uid,project_uid,yanshou_type){
		$('#newAdd').attr("data-target","xmgk-input");
		$('#xmgk-input').removeData("bs.modal");
		$("#xmgk-input").empty();
		$('#xmgk-input').modal({
			backdrop: 'static'
		});
		
		$.get("${ctx}/jsp/business/projectaccept/yanshou-edit.jsp?YANSHOU_UID="+yanshou_uid+"&PROJECT_UID="+project_uid+"&YANSHOU_TYPE="+yanshou_type,function(data) {
			$("#xmgk-input").empty();
			$("#xmgk-input").html("");
			$("#xmgk-input").html(data);
		});
}

function removeData(YANSHOU_UID){
		bootbox.confirm("确认删除吗？", function(result) {
		if (result) {						
			$.ajax({
				url :'${ctx}/projectaccept/pmYanShouController?deleteById',
				data : {"YANSHOU_UID":YANSHOU_UID},
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