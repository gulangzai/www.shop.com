
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
    	<h3 id="myModalLabel" class="blue bigger">添加现场签证</h3>
  </div>
  <div class="modal-body">

 	  <form method="post" role="form" class="form-horizontal"  id="executeFrm" > 
 	   <!-- 获取 项目 的 uid -->    	
      	 <input id="SPROJECT_UID" type="hidden" name="SPROJECT_UID" fieldname="PROJECT_UID"  />
      	 <input  type="hidden"  fieldname="TAGS" operation="=" value="QZ"/> 
	  	 <input type="hidden" id="target_uid" fieldname="target_uid"  />	  	
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					签证日期：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input id="biangengDate2" class="col-xs-11 col-sm-11"  data-date-format="yyyy-mm-dd" datatype="*" nullmsg = "请输入签证日期"  placeholder="请输入签证日期" type="text"  fieldname="BIANGENG_DATE"  />
				</div>
				
				<input type="hidden" fieldname="BIANGENG_TYPE" value="XCQZ" />
				<!-- 让用户自主选择 -->
				<!-- 
		  		<label class="col-sm-2 control-label no-padding-right">
					签证类型：
				</label>
				<div class="col-sm-4">
					 <select class="col-xs-11 col-sm-10"  type="text"  >
					 	<option value="HTBCXY">合同补充协议</option>
					 	<option value="GCBGZL">工程变更指令</option>
					 	<option value="HTBWL">合同备忘录</option>
					 	<option value="XCQZ">现场签证</option>
					 	<option value="SJBG">设计变更</option>
					 	<option value="HYJY">会议纪要</option>
					 	<option value="XH">信函</option>
					 	<option value="CLCJ">材料差价</option>
					 	<option value="JSBG">结算变更</option>
					 </select>
				</div>
	  		  -->
			</div>	
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					签证发起单位：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					<input class="col-xs-11 col-sm-11"  datatype="*"  type="hidden" id="BIANGENG_COMPANYS" fieldname="BIANGENG_COMPANY" disabled="disabled" />
					 <input class="col-xs-11 col-sm-11" datatype="*"  type="text" nullmsg = "请选择签证发起单位"  placeholder="请选择签证发起单位" id="BIANGENG_NAME"  disabled="disabled" />
				     <i class="ace-icon glyphicon glyphicon-list bigger-110" onclick="selectGuifan();" style="cursor:pointer" title="选择项目参建单位"></i>
				</div>
			</div>
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					签证理由：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <textarea autofocus="autofocus" nullmsg = "请输入签证理由"  placeholder="请输入签证理由" datatype="*" type="text"  fieldname="REASON" class="col-xs-11 col-sm-11" ></textarea>					
				</div>
			</div>
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					签证金额：
				</label>
				<div class="col-sm-4">
					 <input class="col-xs-11 col-sm-11"  type="text" nullmsg = "请输入签证金额"  placeholder="请输入签证金额"  fieldname="AMOUNT" datatype="/^\d{0,16}\.(\d{1,2})$|^\d{0,18}$/" errormsg="金额格式不对，此处最多保留2位小数" ignore="ignore"  />元
				</div>				
			</div>	
	  		
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					重大变更：
				</label>
				<div class="col-sm-4">
					 <label class="col-sm-5 center"><input id="important_Y" type="radio" name="important" checked="checked" />是</label>
					 <label class="col-sm-5 center"><input id="important_N" type="radio" name="important"  />否</label>
					 <input id="_important" type="hidden" value="Y" fieldname="IMPORTANT"/>
				</div>				
			</div>	
	  		
			<div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
					附件：&nbsp;
				</label>
				<div class="col-sm-10">
					<span class="btn  btn-white btn-default btn-round" id="addFileSpan" onclick="selectFile(this);" file_type="10401" >
						<i class="ace-icon fa fa-upload"></i>
						<span>上传</span>
					</span>
					<table  role="presentation" class="table table-striped">
						<tbody class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="10401"></tbody>
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
var controlletname="${pageContext.request.contextPath }/biangeng/biangengController";
//点击保存按钮
function selectGuifan(){
	
	window.open("${pageContext.request.contextPath }/jsp/business/danwei/xmdanwei-select.jsp");
}
$(function() {	
	$("#important_Y").change(function(){	
		if($("#important_Y").is(":checked")){
			$("#_important").val("Y");			
		}		
	}); 
	
	$("#important_N").change(function(){	
		if($("#important_N").is(":checked")){
			$("#_important").val("N");			
		}		
	}); 
	
	DatePicker.datepicker("#biangengDate2");
	validform=FormValid.validbyformid(executeFrm);
	$('#SPROJECT_UID').val($('#project_uid').val());
	$("#addsaveUserInfo").click(function(){
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
	setFileData("PM_BIANGENG","BIANGENG_UID",targetUid,file_type);
    document.getElementById("fileupload_btn").click();	
	
}
//回调
function doCallback(s){
	var strs= new Array(); //定义一数组 
	strs=s.split(","); //字符分割 
	$("#BIANGENG_COMPANYS").val(strs[0]);
	$("#BIANGENG_NAME").val(strs[1]);
	
}
</script>
		