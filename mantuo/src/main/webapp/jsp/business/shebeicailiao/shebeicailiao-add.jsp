
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%
 String PROJECT_UID =request.getParameter("PROJECT_UID");

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
    	<h3 id="myModalLabel" class="blue bigger">添加设备材料信息</h3>
  </div>
  <div class="modal-body">

 	  <form method="post" role="form" class="form-horizontal"  id="executeFrm" > 
 	   <!-- 获取 项目 的 uid -->    	
      	 <input id="PROJECT_UID" type="hidden" name="PROJECT_UID" fieldname="PROJECT_UID" value="<%=PROJECT_UID%>"/>
      	
      	 <input type="hidden" id="target_uid" fieldname="target_uid"  />	
      	 	
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					设备材料名称：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <input datatype="*" nullmsg = "请输入设备材料名称"  placeholder="请输入设备材料名称" type="text" name="CAILIAO_NAME" id="REPORT_DATE_TWO" fieldname="CAILIAO_NAME" class="col-xs-11 col-sm-11" />
				</div>	  		
			</div>

	  		<div class="form-group" >	
		  		<label class="col-sm-2 control-label no-padding-right">
					品牌：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input class="col-xs-11 col-sm-10"  datatype="*" nullmsg = "请输入品牌"  placeholder="请输入品牌" type="text"  id="CAILIAO_PINPAI" fieldname="CAILIAO_PINPAI" />
				</div>
	  		    <label class="col-sm-2 control-label no-padding-right">
					生产商：
				</label>
				<div class="col-sm-4">
					 <input class="col-xs-11 col-sm-10"  nullmsg = "请输入生产商"  placeholder="请输入生产商" type="text"  id="CAILIAO_CHANGSHANG" fieldname="CAILIAO_CHANGSHANG"/>
				</div>
			</div>
			     <div class="form-group" >	
		  		<label class="col-sm-2 control-label no-padding-right">
					产地：
				</label>
				<div class="col-sm-4">
					 <input class="col-xs-11 col-sm-10"  nullmsg = "请输入产地"  placeholder="请输入产地" type="text"  id="CAILIAO_CHANDI" fieldname="CAILIAO_CHANDI"/>
				</div>
	  		    <label class="col-sm-2 control-label no-padding-right">
					规格型号：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input class="col-xs-11 col-sm-10"  datatype="*" nullmsg = "请输入规格型号"  placeholder="请输入规格型号" type="text"  id="CAILIAO_XINGHAO" fieldname="CAILIAO_XINGHAO" />
				</div>
			</div>
			<div class="form-group" >	
		  		<label class="col-sm-2 control-label no-padding-right">
					单位：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input class="col-xs-11 col-sm-10"  datatype="*" nullmsg = "请输入单位"  placeholder="请输入单位" type="text"  id="CAILIAO_UNIT" fieldname="CAILIAO_UNIT" />
				</div>
				<label class="col-sm-2 control-label no-padding-right">
					数量：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input class="col-xs-11 col-sm-10"  datatype="/^\d{0,16}\.(\d{1,2})$|^\d{0,18}$/" errormsg="数量格式不对"  nullmsg = "请输入数量"  placeholder="请输入数量" type="text"  id="CAILIAO_NUMS" fieldname="CAILIAO_NUMS" />
				</div>
                
			
	  	     
	  		</div >
	  		  <div class="form-group">
	  		    <label class="col-sm-2 control-label no-padding-right">
					价格：
				</label>
				<div class="col-sm-4">
					 <input class="col-xs-11 col-sm-10"  datatype="/^\d{0,16}\.(\d{1,2})$|^\d{0,18}$/" errormsg="价格格式不对，此处最多保留2位小数"  nullmsg = "请输入价格"  placeholder="请输入价格" type="text"  id="CAILIAO_PRICE" fieldname="CAILIAO_PRICE"/>
				</div>
	  		</div>
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right">
					进场日期：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input class="col-xs-11 col-sm-10"  data-date-format="yyyy-mm-dd" datatype="*" nullmsg = "请输入进场日期"  placeholder="请输入进场日期" type="text" id="CAILIAO_JCRQ" fieldname="CAILIAO_JCRQ"  />
				</div>
				<label class="col-sm-2 control-label no-padding-right">
					验收人：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input class="col-xs-11 col-sm-10"  datatype="*" nullmsg = "请输入验收人"  placeholder="请输入验收人" type="text"  id="YANSHOU_REN" fieldname="YANSHOU_REN"/>
				</div>
			</div>
	  		<div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
					附件：&nbsp;
				</label>
				<div class="col-sm-10">
					<span class="btn  btn-white btn-default btn-round" id="addFileSpan" onclick="selectFile(this);" file_type="10501" >
						<i class="ace-icon fa fa-upload"></i>
						<span>附件上传</span>
					</span>
					<table  role="presentation" class="table table-striped">
						<tbody class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="10501"></tbody>
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
var controlletname="${pageContext.request.contextPath }/pmcailiao/pmCailiaoController";
//点击保存按钮
$(function() {	
    var xmUid = "<%=PROJECT_UID%>";

     $("#PROJECT_UID").val(xmUid);
	 DatePicker.datepicker("#CAILIAO_JCRQ");
	validform=FormValid.validbyformid(executeFrm);
	
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
	setFileData("PM_CAILIAO","CAILIAO_UID",targetUid,file_type);
    document.getElementById("fileupload_btn").click();	
	
}
</script>
		