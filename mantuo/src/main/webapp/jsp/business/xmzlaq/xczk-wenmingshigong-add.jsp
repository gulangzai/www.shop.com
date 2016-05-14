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
    	<h3 id="myModalLabel" class="blue bigger">添加文明施工</h3>
  </div>
  <div class="modal-body">
 	  <form method="post" role="form" class="form-horizontal"  id="executeFrmZL" > 
 	   <!-- 获取 项目 的 uid -->    	
      	 <input id="ZLROJECT_UID" type="hidden" name="ROJECT_UID" fieldname="PROJECT_UID"  />
      	  <input  id="XM_PRJ_STRUC_UID"  type="hidden"  fieldname="PRJ_STRUC_UID" value="" />
	  	  <input type="hidden" id="target_uid" fieldname="target_uid"  />
	  		<input type="hidden" fieldname="f_DESCRIPTION"  />
	  		 <input type="hidden" id="STATUS" fieldname="STATUS"  value=""/>
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					现场状况类型：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <select  datatype="*" style="width: 80%;" nullmsg="请选择类型" fieldname="GONGKUANG_TYPE" class="col-xs-11 col-sm-11" >
			        <option value="WM" >文明</option>
			      </select>
				</div>
				<!--  
				<label class="col-sm-2 control-label no-padding-right" >
					现场状态：
				</label>
				<div class="col-sm-4">
					&nbsp;<input type="radio" id="STATUS" name="STATUS" fieldname="STATUS" checked value="0">&nbsp;解决中
					
					&nbsp;<input type="radio" id="STATUS" name="STATUS" fieldname="STATUS"  value="1" >&nbsp;已解决
				</div>
				-->
			</div>

			<div class="form-group" >
			<!-- 
	  			<label class="col-sm-2 control-label no-padding-right" >
					施工部位：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <input  id="getXMJGMC" style="width: 80%;" class="col-xs-11 col-sm-11" datatype="*" nullmsg="请选择项目现场的施工部位"  nullmsg="请选择现场施工部位"  placeholder="请选择项目现场的施工部位" type="text"  fieldname="STRUC_NAME" />
				</div>
				 -->
				<label class="col-sm-2 control-label no-padding-right" >
					要求解决日期：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <input name="FINISH_DATE"  id="FINISH_DATE" style="width: 80%;" class="col-xs-11 col-sm-11" data-date-format="yyyy-mm-dd"  datatype="*" nullmsg="请选择要求解决日期"   nullmsg="请选择要求解决日期"  placeholder="请选择要求解决日期" type="text"  fieldname="FINISH_DATE" />
				</div>
				<label class="col-sm-2 control-label no-padding-right" >
					严重性：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <select class="col-sm-4" datatype="*" nullmsg = "请选择严重性"  placeholder="请选择严重性" type="text"  fieldname="SERIOUSNESS">
					 	
					 	<option value="1">一般</option>
					 	<option value="2">较严重</option>
					 	<option value="3">严重</option>
					 	
					 </select>
				</div>
			</div>
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					标题：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <textarea autofocus="autofocus" datatype="*" nullmsg="请输入标题"  placeholder="请输入标题" rows="4" type="text"  fieldname="JINDU" class="col-xs-11 col-sm-11" ></textarea>
				</div>
			</div>
			
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					状况描述：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <textarea autofocus="autofocus" datatype="*" nullmsg="请输入状况描述"  placeholder="请输入状况描述" rows="4" type="text"  fieldname="DESCRIPTION" class="col-xs-11 col-sm-11" ></textarea>
				</div>
			</div>
	  	
           <div class="form-group">
					<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
						现场质量状况：&nbsp;
					</label>
					
					<div class="col-sm-9">
						<span style="margin:4px auto;" class="btn  btn-white btn-default btn-round " onclick="selectFile(this);" file_type="10010" >
							<i class="ace-icon fa fa-cloud-upload bigger-100"></i>
								现场质量状况
						</span>
						<table  role="presentation" class="table table-striped">
							<tbody class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="10010" fjlb="media"></tbody>
						</table>
					</div>
		   </div>
     </form>
	</div>
  <div class="modal-footer">
    <button id="addXcZlData" class="btn btn-1 btn-sms" type="button">发布</button>
    <button id="baocun" class="btn btn-1 btn-sms" type="button">保存</button>
    <button id="closeZlPage"  class="btn btn-2 btn-sms" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
</div>
</div>
<div id="XMZK-XMMC" class="modal"></div>
<jsp:include page="/jsp/file_upload/fileupload_util.jsp" flush="true" />	
<script type="text/javascript">
var validform;
var controlletnameUrl="${pageContext.request.contextPath }/pmxianchang/pmXianchangController";
//点击保存按钮
$(function() {	
	DatePicker.datepicker("#FINISH_DATE");
	var tody = new Date(); 
	var nian = tody.getFullYear();
	var youe = tody.getMonth() + 1; 
	var day = tody.getDate(); 
	var dates=nian+"-"+youe+"-"+day;//当前日期
	
    // $("input[name=FINISH_DATE]").datepicker({dateFormat:"yy-mm-dd",minDate: new Date()}); 
	validform=FormValid.validbyformid(executeFrmZL);
	$('#ZLROJECT_UID').val($('#project_uid').val());
	$("#addXcZlData").click(function() {
		$("#STATUS").val("0");
		var date=$("#FINISH_DATE").val();
		var d1 = new Date(date.replace(/\-/g, "\/")); 
		 var d2 = new Date(dates.replace(/\-/g, "\/")); 
	  if(validform.check()&&d1>=d2){
		if($("#executeFrmZL").validationButton()) {
			var data = Form2Json.formToJSON(executeFrmZL);
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
					$("#closeZlPage").click();
					 jQuery("#grid_table").jqGrid().trigger("reloadGrid");
			      }
			});
			
		 }
	   }else {
		   xAlert("信息提示","要求完成日期必须大于当前日期",1);
		   return;
	   }
	});
	$("#baocun").click(function() {
		var date=$("#FINISH_DATE").val();
		var d1 = new Date(date.replace(/\-/g, "\/")); 
		 var d2 = new Date(dates.replace(/\-/g, "\/")); 
		$("#STATUS").val("-1");
		  if(validform.check()&&d1>=d2){
			if($("#executeFrmZL").validationButton()) {
				var data = Form2Json.formToJSON(executeFrmZL);
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
						$("#closeZlPage").click();
						 jQuery("#grid_table").jqGrid().trigger("reloadGrid");
				      }
				});
				
			 }
		   }else {
			   xAlert("信息提示","要求完成日期必须大于当前日期",1);
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