<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/sys_resources/plugins/lightbox/themes/default/jquery.lightbox.css" />

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%
	String log_uid = request.getParameter("log_uid");
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
    	<h3 id="myModalLabel" class="blue bigger">修改业主日志</h3>
  </div>
  <div class="modal-body">
	  <form id="queryFormById">
	       <input  type="hidden" name="PROJECT_LOG_UID" operation="=" fieldname="PROJECT_LOG_UID" value="<%=log_uid %>"/>
	  </form>
 	  <form method="post" role="form" class="form-horizontal"  id="executeFrm" > 
 	   <!-- 获取 项目 的 uid -->    	
      	 <input id="PROJECT_LOG_UID" type="hidden" name="PROJECT_LOG_UID" fieldname="PROJECT_LOG_UID" value="<%=log_uid %>"/>
      	 <input id="LOG_TYPE" type="hidden" name="LOG_TYPE" fieldname="LOG_TYPE" value="YZ"/>
      	 <input type="hidden" id="target_uid" fieldname="target_uid"  />	
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					日期：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <input class="col-xs-11 col-sm-11"  data-date-format="yyyy-mm-dd" datatype="*" nullmsg = "请输入日期"  placeholder="请输入日期" type="text" id="LLOG_DATE" fieldname="LOG_DATE"  />
				</div>
			</div>
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					业主日报标题：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <textarea  datatype="*" nullmsg="请输入业主日报标题"  placeholder="请输入业主日报标题" type="text" name="YZLOG_TITLE" id="YZLOG_TITLE" fieldname="YZLOG_TITLE" class="col-xs-11 col-sm-11" ></textarea>
					
				</div>
			</div>
	  		
						 
	  		
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					业主日报内容：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <textarea autofocus="autofocus" datatype="*" nullmsg="请输入业主日报内容"  placeholder="请输入业主日报内容" type="text" id="YZLOG_CONTENT" name="YZLOG_CONTENT" fieldname="YZLOG_CONTENT" class="col-xs-11 col-sm-11" ></textarea>
					
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
						<tbody showType="width:120px;height:120px;quanxian:true;heightss:160px;heights:40px;del:false;attr:multi;preview:false;" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="10109"></tbody>
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
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/plugins/lightbox/jquery.min.js"></script>		
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/plugins/lightbox/jquery.lightbox.min.js"></script>

<script type="text/javascript">
var validform;
var controlletname="${pageContext.request.contextPath }/projectlog/pmProjectLogController";
$(function() {	
	
	//删除多余的 div 避免弹出图片时出现多层
	var div1 = $(".jquery-lightbox-overlay").eq(0);
	var div2 = $(".jquery-lightbox-move").eq(0);

	$(".jquery-lightbox-overlay").remove();
	$(".jquery-lightbox-move").remove();
	$("body").append(div1);
	$("body").append(div2);
});
(function () {
    var v17 = $.noConflict(true);
    window.$.v17 = v17;
 })();

(function ($) {
 	$('.lightbox').lightbox();
 })(window.$.v17);

//点击保存按钮
$(function() {	
	setFormValues();
	 DatePicker.datepicker("#LLOG_DATE");
	validform=FormValid.validbyformid(executeFrm);
	$("#addsaveUserInfo").click(function() {
	  if(validform.check()){
		if($("#executeFrm").validationButton()) {
			//生成json串
			var data = Form2Json.formToJSON(executeFrm);
			//组成保存json串格式
			var data1 = defaultJson.packSaveJson(data);
			
			$.ajax({
				url :"${pageContext.request.contextPath }/projectlog/pmProjectLogController?update",
				data : data1,
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
					xAlert("信息提示","修改成功",1);
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

function setFormValues(){
	var data = combineQuery.getQueryCombineData(queryFormById,null,null);
	var data1 = {
		msg : data
	};
	$.ajax({
		url : "${pageContext.request.contextPath }/projectlog/pmProjectLogController?query",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var resultobj = defaultJson.dealResultJson(response.msg);
			$("#executeFrm").setFormValues(resultobj);	
			queryFileData('PM_PROJECT_LOG',resultobj.PROJECT_LOG_UID);
		}
	});
}
//图片放大

/*
 $(document).ready(function(){
	$(".template-download img").click(function(){
	    if($(this).hasClass("fd")){
	        $(this).removeClass("fd");
	    }else{
	        $(this).addClass("fd");
	    }
	})
})
*/
function selectFile(obj){
	var targetUid = $('#target_uid').val();
	var file_type = $(obj).attr('file_type');
	setFileData("PM_PROJECT_LOG","PROJECT_LOG_UID",targetUid,file_type);
    document.getElementById("fileupload_btn").click();	
	
}
</script>
		