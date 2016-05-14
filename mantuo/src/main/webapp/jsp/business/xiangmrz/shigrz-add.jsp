<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>

<!-- Modal -->
<div class="modal-dialog width-60 height-auto">
  <div class="modal-content" >
   		<div class="widget-header widget-header-large">
   		<div class="widget-toolbar">
			<a href="#" data-action="close" data-dismiss="modal">
				<i class="ace-icon fa fa-times"></i>
				</a>
		</div>
    	<h3 id="myModalLabel" class="blue bigger">添加施工日志</h3>
  </div>
  <div class="modal-body">

 	  <form method="post" role="form" class="form-horizontal"  id="executeFrm" > 
 	   <!-- 获取 项目 的 uid -->    	
      	 <input id="SPROJECT_UID" type="hidden" name="SPROJECT_UID" fieldname="PROJECT_UID"  />
      	 <input id="LOG_TYPE" type="hidden" name="LOG_TYPE" fieldname="LOG_TYPE" value="SG" />
	  	 <input type="hidden" id="target_uid" fieldname="target_uid"  />
	  		<!-- 
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					单位工程名称：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <input datatype="*" nullmsg = "请输入单位工程名称"  placeholder="请输入单位工程名称" type="text"  fieldname="REPORT_DATE" class="col-xs-11 col-sm-11" />
				</div>
	  		
			</div>
			 -->
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					日期：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <input class="col-xs-11 col-sm-11"  data-date-format="yyyy-mm-dd" datatype="*" nullmsg = "请输入日期"  placeholder="请输入日期" type="text" id="SLOG_DATE" fieldname="LOG_DATE"  />
				</div>
				<!-- 
		  		<label class="col-sm-2 control-label no-padding-right">
					填写人：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input class="col-xs-11 col-sm-10"  datatype="*" nullmsg = "请输入填写人"  placeholder="请输入填写人" type="text"  id="CREATE_USER" fieldname="CREATE_USER" value="3" />
				</div>
	  		 -->
			</div>
			<!-- 
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					出勤人数：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <textarea autofocus="autofocus" datatype="*" nullmsg="请输入出勤人数"  placeholder="请输入出勤人数" type="text" name="JINDU" id="JINDU" fieldname="JINDU" class="col-xs-11 col-sm-11" ></textarea>
					
				</div>
			</div>
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					施工部位：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <textarea autofocus="autofocus" datatype="*" nullmsg="请输入施工部位"  placeholder="请输入施工部位" type="text"  fieldname="JINDU" class="col-xs-11 col-sm-11" ></textarea>
					
				</div>
			</div>
			 -->
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					生产情况记录：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <textarea autofocus="autofocus" datatype="*" nullmsg="请输入生产情况记录"  placeholder="请输入生产情况记录" type="text"  fieldname="SHENGCHAN_INFO" class="col-xs-11 col-sm-11" ></textarea>
					
				</div>
			</div>
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					质量安全情况记录：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <textarea autofocus="autofocus" datatype="*" nullmsg="请输入质量安全情况记录"  placeholder="请输入质量安全情况记录" type="text"  fieldname="ZLAQ_INFO" class="col-xs-11 col-sm-11" ></textarea>
					
				</div>
			</div>
	  		
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					突发事件：
				</label>
				<div class="col-sm-10">
					 <textarea autofocus="autofocus"   placeholder="请输入突发事件" type="text"  fieldname="TUFA" class="col-xs-11 col-sm-11" ></textarea>
					
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
					照片上传：&nbsp;
				</label>
				<div class="col-sm-10">
					<span class="btn  btn-white btn-default btn-round" id="addFileSpan" onclick="selectFile(this);" file_type="10109" >
						<i class="ace-icon fa fa-upload"></i>
						<span>附件上传</span>
					</span>
					<table  role="presentation" class="table table-striped">
						<tbody class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="10109"></tbody>
					</table>
				</div>
			</div>
     </form>
     
	</div>

  <div class="modal-footer">
    <button class="btn btn-1 btn-sms" id="addsaveUserInfo">保存</button>
    <button class="btn btn-2 btn-sms pull-right" id="btnClose" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
</div>
</div>
<jsp:include page="/jsp/file_upload/fileupload_util.jsp" flush="true" />
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/convertJson.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/combineQuery.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/default.js?version=20131206"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/bootstrap-validation.js"> </script>
<script type="text/javascript">
var validform;
var controlletname="${pageContext.request.contextPath }/projectlog/pmProjectLogController";
//点击保存按钮
$(function() {	
	
	DatePicker.datepicker("#SLOG_DATE");
	validform=FormValid.validbyformid(executeFrm);
	//alert($('#project_uid').val());
	$('#SPROJECT_UID').val($('#project_uid').val());
	$("#addsaveUserInfo").click(function() {
	  if(validform.check()){
		if($("#executeFrm").validationButton()) {
			//生成json串
			var data = Form2Json.formToJSON(executeFrm);
			//组成保存json串格式
			var data1 = defaultJson.packSaveJson(data);
			
			$.ajax({
				url :controlletname+"?insert",
				data : data1,
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
					xAlert("信息提示","新增成功",1);
					$("#btnClose").click();
					 jQuery("#grid_table").jqGrid().trigger("reloadGrid");
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
	setFileData("PM_PROJECT_LOG","PROJECT_LOG_UID",targetUid,file_type);
    document.getElementById("fileupload_btn").click();	
	
}
</script>
		