<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%
	String topic_uid = request.getParameter("topic_uid");
%>
<!-- Modal -->
<div class="modal-dialog width-60 height-auto">
  <div class="modal-content" >
   		<div class="widget-header widget-header-large">
   		<div class="widget-toolbar">
			<a href="#" data-action="close" data-dismiss="modal">
				<i class="ace-icon fa fa-times"></i>
				</a>
		</div>
    	<h3 id="myModalLabel" class="blue bigger">添加回复</h3>
  </div>
<div class="modal-body">

	<form method="post" role="form" class="form-horizontal"  id="executeFrm" > 
	     <input id="PROJECT_TOPIC_UID" type="hidden"  fieldname="PROJECT_TOPIC_UID"  />
	  	 <input type="hidden" id="target_uid" fieldname="target_uid"  />
	  	 <!-- 
		<div class="form-group" >
	  		<label class="col-sm-2 control-label no-padding-right" >
				主题：<span class="required-indicator">*</span>
			</label>
			<div class="col-sm-10">
				<textarea autofocus="autofocus" datatype="*" nullmsg="请输入主题"  placeholder="请输入主题" type="text"  fieldname="SUBJECT" class="col-xs-11 col-sm-11" ></textarea>
			</div>
		</div>
		 -->
	  	<div class="form-group" >
	  		<label class="col-sm-2 control-label no-padding-right" >
				内容：<span class="required-indicator">*</span>
			</label>
			<div class="col-sm-10">
				<textarea autofocus="autofocus" datatype="*" nullmsg="请输入内容"  placeholder="请输入内容" type="text"  fieldname="CONTENT" class="col-xs-11 col-sm-11" ></textarea>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
					附件上传：&nbsp;
			</label>
			<div class="col-sm-10">
				<span class="btn  btn-white btn-default btn-round" id="addFileSpan" onclick="selectFile(this);" file_type="10302" >
					<i class="ace-icon fa fa-upload"></i>
					<span>附件上传</span>
				</span>
				<table  role="presentation" class="table table-striped">
					<tbody class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="10302" fjlb="media"></tbody>
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
<jsp:include page="/jsp/file_upload/fileupload_util.jsp" flush="true"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/convertJson.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/combineQuery.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/default.js?version=20131206"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/bootstrap-validation.js"> </script>
<script type="text/javascript">
var validform;
//点击保存按钮
$(function() {
	var topic_uid = '<%=topic_uid%>';
	$('#PROJECT_TOPIC_UID').val(topic_uid);
	validform=FormValid.validbyformid(executeFrm);
	$("#addsaveUserInfo").click(function() {
	  if(validform.check()){
		if($("#executeFrm").validationButton()) {
			//生成json串
			var data = Form2Json.formToJSON(executeFrm);
			//组成保存json串格式
			var data1 = defaultJson.packSaveJson(data);
			
			$.ajax({
				url :"${pageContext.request.contextPath }/projectlog/pmProjectTopicReplyController?insert",
				data : data1,
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
					xAlert("信息提示","回复成功",1);
					$("#btnClose").click();
					 init();
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
	setFileData("PM_PROJECT_TOPIC_REPLY","PROJECT_TOPIC_REPLY_UID",targetUid,file_type);
    document.getElementById("fileupload_btn").click();	
	
}
</script>
		