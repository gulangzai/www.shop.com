<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%> 
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<style>
.page-content {
    background-color: #fff;
    position: relative;
    margin: 0;
    padding:3px 0px 0px 3px;
}
</style>
<!-- Modal -->
<div class="modal-dialog width-60 height-auto">
  <div class="modal-content" >
   <div class="widget-header widget-header-large">
   		<div class="widget-toolbar">
			<a href="#" data-action="close" data-dismiss="modal">
				<i class="ace-icon fa fa-times"></i>
				</a>
		</div>
    	<h3 id="myModalLabel" class="blue bigger">添加${table.desc}</h3>
  </div>
  <div class="modal-body">
 	  <form method="post" role="form" class="form-horizontal"  id="execute${table.name}Add" > 
 	   <!-- 获取 项目 的 uid -->    	
      	  <input id="XCROJECT_UID" type="hidden" name="ROJECT_UID" fieldname="PROJECT_UID"  /> 
	  	  <input type="hidden" id="target_uid" fieldname="target_uid"  /> 
				
		  <#if  fields?exists>
		    <#list fields  as field>
		        <#if  field.comment !=""  && field.column_key!="PRI">
		       <div class="form-group" >
		  			<label class="col-sm-2 control-label no-padding-right" >
						${field.comment}：<span class="required-indicator">*</span>
					</label>
					<div class="col-sm-10">
					 <input value="" id="${field.name}" datatype="*" nullmsg="请填写${field.comment}"    placeholder="请填写${field.comment}" type="text"  fieldname="${field.name}" class="col-xs-11 col-sm-11 <#if field.data_type =="datetime">dataPicker</#if>"/>
					</div>
			   </div>
			   </#if>
		    </#list>
		  </#if>
			
		  
            <div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
				        附件：&nbsp;
				</label> 
				<div class="col-sm-9">
					<span style="margin:4px auto;" class="btn  btn-white btn-default btn-round " onclick="selectFile(this);" file_type="${file_type}" >
						<i class="ace-icon fa fa-cloud-upload bigger-100"></i>
							附件上传
					</span>
						<form class="form-inline"  id="queryForm1" width="100%"
					   role="form" style="border:solid 1px #ddd;height:50px;line-height:50px;vertical-align:middle;"></form>
					<table  role="presentation" class="table table-striped">
						<tbody onlyView="true" showType="width:120px;height:120px;quanxian:true;heightss:160px;heights:40px;del:true;attr:multi;preview:false" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="${file_type}" fjlb="media"></tbody>
					</table>
				</div>
			</div>
     </form>
     
	</div>

  <div class="modal-footer">
    <button id="btnAdd${table.name}" class="btn btn-1 btn-sms btn-round" type="button">&nbsp;保存&nbsp;</button>
    <button id="btnClose${table.name}add"  class="btn btn-2 btn-sms pull-right  btn-round" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
</div>
</div>
<div id="XMZK-XMMC" class="modal"></div>
<#noparse>
<jsp:include page="/jsp/file_upload/fileupload_util.jsp" flush="true" />
<%@ include file="/jsp/framework/common/basic_scripts.jsp"%> 
<script src="${pageContext.request.contextPath }/assets/js/jquery-ui.custom.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript"> 
var validform;
var controllernameUrl="${pageContext.request.contextPath}/";
</#noparse>	
 controllernameUrl=controllernameUrl+'${controllerName}';
 
var scripts =[null];
ace.load_ajax_scripts(scripts, function() { 
	 DatePicker.datepicker(".REPORT_DATE1"); 
     DatePicker.datepicker(".REPORT_DATE2");   
}); 


//点击保存按钮
$(function() {	 
	$(".modal-backdrop").attr("class","");
	DatePicker.datepicker(".dataPicker");
	validform=FormValid.validbyformid(execute${table.name}Add); 
	$('#XCROJECT_UID').val($('#project_uid').val());
	$("#btnAdd${table.name}").click(function() {
	  if(validform.check()){
		if($("#execute${table.name}Add").validationButton()) {
			//生成json串
			var data = Form2Json.formToJSON(execute${table.name}Add);
			//组成保存json串格式
			var data1 = defaultJson.packSaveJson(data);
			$.ajax({
				url :controllernameUrl+"?insert",
				data : data1,
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
					xAlert("信息提示","${table.name}添加成功",1);
					$("#btnClose${table.name}add").click();
					_reload();
			      }
			});
			
		 }
	   }else {
		   return;
	   }
	}); 
	
});

function selectFile(obj){
	var targetUid = $('#target_uid').val();
	var file_type = $(obj).attr('file_type');
	setFileData("${table.name}","${tableKey}",targetUid,file_type);
    document.getElementById("fileupload_btn").click();	 
}
function _reload(){
	jQuery("#grid_table").jqGrid().trigger("reloadGrid");
}
</script>