<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>

<!-- Modal -->
<div class="modal-dialog width-70 height-auto">
  <div class="modal-content" >
   <div class="widget-header widget-header-large">
   		<div class="widget-toolbar">
			<a href="#" data-action="close" data-dismiss="modal">
				<i class="ace-icon fa fa-times"></i>
				</a>
		</div>
    	<h3 id="myModalLabel" class="blue bigger">添加进度工况</h3>
  </div>
  <div class="modal-body">
 	  <form method="post" role="form" class="form-horizontal"  id="executeFrmXcAddXcAdd" > 
 	   <!-- 获取 项目 的 uid -->    	
      	 <input id="XCROJECT_UID" type="hidden" name="ROJECT_UID" fieldname="PROJECT_UID"  />
      	  <input  id="XM_PRJ_STRUC_UID"  type="hidden"  fieldname="PRJ_STRUC_UID" value="" />
	  	  <input type="hidden" id="target_uid" fieldname="target_uid"  />
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					现场状况类型：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <select  datatype="*" style="width: 80%;" nullmsg="请选择类型" fieldname="GONGKUANG_TYPE" class="col-xs-11 col-sm-11" >
			        <option value="JD" >进度</option>
			      </select>
				</div>
				<!--  
				<label class="col-sm-2 control-label no-padding-right" >
					现场状态：
				</label>
				<div class="col-sm-4">
					&nbsp;<input type="radio" name="STATUS" fieldname="STATUS" checked value="0">&nbsp;进行中
					
					&nbsp;<input type="radio" name="STATUS" fieldname="STATUS"  value="1" >&nbsp;已完成
				</div>
				-->
			</div>
<!-- 
			<div class="form-group" >
	  			
	  			<label class="col-sm-2 control-label no-padding-right" >
					施工部位：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <input  id="getXMJGMC" style="width: 80%;" class="col-xs-11 col-sm-11" datatype="*" nullmsg="请选择项目现场的施工部位"  nullmsg="请选择现场施工部位"  placeholder="请选择项目现场的施工部位" type="text"  fieldname="STRUC_NAME" />
				</div>
				
				<label class="col-sm-2 control-label no-padding-right" >
					要求完成日期：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <input  id="FINISH_DATE" style="width: 80%;" class="col-xs-11 col-sm-11" data-date-format="yyyy-mm-dd"  datatype="*" nullmsg="请选择要求完成日期"   nullmsg="请选择要求完成日期"  placeholder="请选择要求完成日期" type="text"  fieldname="FINISH_DATE" />
				</div>
			</div>
 -->
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					标题：
				</label>
				<div class="col-sm-10">
					 <textarea autofocus="autofocus"  nullmsg="请输入标题"  placeholder="请输入标题" rows="4" type="text"  fieldname="JINDU" class="col-xs-11 col-sm-11" ></textarea>
				</div>
			</div>
			
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					状况描述：
				</label>
				<div class="col-sm-10">
					 <textarea autofocus="autofocus"  nullmsg="请输入状况描述"  placeholder="请输入状况描述" rows="4" type="text"  fieldname="DESCRIPTION" class="col-xs-11 col-sm-11" ></textarea>
				</div>
			</div>
	  
            
            <div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
					现场进度状况：&nbsp;
				</label>
				
				<div class="col-sm-10">
					<span style="margin:4px auto;" class="btn  btn-white btn-default btn-round " onclick="selectFile(this);" file_type="10009" >
						<i class="ace-icon fa fa-cloud-upload bigger-100"></i>
							现场进度状况
					</span>
					<table  role="presentation" class="table table-striped">
						<tbody class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="10009" fjlb="image"></tbody>
					</table>
				</div>
			</div>
     </form>
     
	</div>

  <div class="modal-footer">
    <button id="addXcZkData" class="btn btn-1 btn-sms" type="button">保存</button>
    <button id="btnCloseXcJd"  class="btn btn-2 btn-sms pull-right" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
</div>
</div>
<div id="XMZK-XMMC" class="modal"></div>
<jsp:include page="/jsp/file_upload/fileupload_xcgk.jsp" flush="true" />	
<script type="text/javascript">
var validform;
var controlletnameUrl="${pageContext.request.contextPath }/pmxianchang/pmXianchangController";
//点击保存按钮
$(function() {	
	DatePicker.datepicker("#FINISH_DATE"); 
	$(".modal-backdrop").attr("class","");
	//DatePicker.datepicker("#SLOG_DATE");
	validform=FormValid.validbyformid(executeFrmXcAddXcAdd);
	//alert($('#project_uid').val());
	$('#XCROJECT_UID').val($('#project_uid').val());
	$("#addXcZkData").click(function() {
	  if(validform.check()){
		if($("#executeFrmXcAddXcAdd").validationButton()) {
			if($(".showFileTab tr").length>=3){
				//生成json串
				var data = Form2Json.formToJSON(executeFrmXcAddXcAdd);
				//组成保存json串格式
				var data1 = defaultJson.packSaveJson(data);
				$.ajax({
					url :controlletnameUrl+"?insert",
					data : data1,
					cache : false,
					async :	false,
					dataType : "json",  
					type : 'post',
					success : function(response) {
						xAlert("信息提示","现场状况信息发布成功",1);
						$("#btnCloseXcJd").click();
						_reload();
				      }
				});
			}else{
				alert("至少上传3张照片！");
			}

			
		 }
	   }else {
		   return;
	   }
	});
	
	$("#getXMJGMC").click(function(){
		$('#newAdd').attr("data-target","XMZK-XMMC");
		$('#XMZK-XMMC').removeData("bs.modal");
		$("#XMZK-XMMC").empty();
		$('#XMZK-XMMC').modal({
			backdrop: 'static'
		});
		
		$.get("${ctx}/jsp/business/xmzlaq/select-xmmc.jsp",function(data) {
			$("#XMZK-XMMC").empty();
			$("#XMZK-XMMC").html("");
			$("#XMZK-XMMC").html(data);
		});
	
	});
});

function selectFile(obj){
	var targetUid = $('#target_uid').val();
	
	var file_type = $(obj).attr('file_type');
	setFileData("PM_XIANCHANG","XIANCHANG_UID",targetUid,file_type);
    document.getElementById("fileupload_btn").click();	
	
}
</script>