
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
    	<h3 id="myModalLabel" class="blue bigger">添加周计划管理</h3>
  </div>
  <div class="modal-body">

 	  <form method="post" role="form" class="form-horizontal"  id="executeFrm" > 
 	   <!-- 获取 项目 的 uid -->    	
      	 <input id="LPROJECT_UID" type="hidden" name="LPROJECT_UID" fieldname="PROJECT_UID" />
      	 
      	 <input type="hidden" id="target_uid" fieldname="target_uid"  />	
      	 	
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					楼号：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input class="col-xs-11 col-sm-10"   datatype="*" nullmsg = "请输入楼号"  placeholder="请输入楼号" type="text" id="BUILDING_NUM" fieldname="BUILDING_NUM"  />
				</div>
				<label class="col-sm-2 control-label no-padding-right">
					户号：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input class="col-xs-11 col-sm-10"   datatype="*" nullmsg = "请输入户号"  placeholder="请输入户号" type="text" id="ROOM_NUM" fieldname="ROOM_NUM"  />
				</div>
				
			</div>
           <div class="form-group" >
		  		
				<label class="col-sm-2 control-label no-padding-right">
					计划检查周的周一：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input class="col-xs-11 col-sm-10"  data-date-format="yyyy-mm-dd"   datatype="*" nullmsg = "请输入计划检查周的周一"  placeholder="请输入计划检查周的周一" type="text" id="PLAN_WEEK" fieldname="PLAN_WEEK"  />
				</div>
				
			</div>

	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					检查内容：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <textarea  datatype="*" nullmsg="请输入检查内容"  placeholder="请输入检查内容" type="text" name="JC_NEIRONG" id="JC_NEIRONG" fieldname="JC_NEIRONG" class="col-xs-11 col-sm-11" ></textarea>
					
				</div>
			</div>
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
				检查要求：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <textarea  datatype="*" nullmsg="请输入检查要求"  placeholder="请输入检查要求" type="text" name="JC_YAOQIU" id="JC_YAOQIU" fieldname="JC_YAOQIU" class="col-xs-11 col-sm-11" ></textarea>
					
				</div>
			</div>
						 
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					检查标准：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <textarea autofocus="autofocus" datatype="*" nullmsg="请输入检查标准"  placeholder="请输入检查标准" type="text"  fieldname="JC_BIAOZHUN" class="col-xs-11 col-sm-11" ></textarea>
					
				</div>
			</div>
	  		<!--  
			<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					实际检查日期：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					<input autofocus="autofocus" datatype="*" nullmsg="请输入实际检查日期"  placeholder="请输入实际检查日期" type="text"  fieldname="JC_END_DATE" class="col-xs-11 col-sm-10" ></textarea>
				</div>
				<label class="col-sm-2 control-label no-padding-right">
					检查人员：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input autofocus="autofocus"    placeholder="请输入检查人员" type="text" id="JC_USER" fieldname="JC_USER" class="col-xs-11 col-sm-10" ></textarea>
				</div>
				
			</div>
			-->
			
			
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
var controlletname="${pageContext.request.contextPath }/pmscslweekplan/pmScslWeekPlanController";
//点击保存按钮
$(function() {	
	DatePicker.datepicker("#PLAN_WEEK");
	validform=FormValid.validbyformid(executeFrm);
	$('#LPROJECT_UID').val($('#project_uid').val());
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
		