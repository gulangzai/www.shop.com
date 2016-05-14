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
    	<h3 id="myModalLabel" class="blue bigger">添加工况信息</h3>
  </div>
  <div class="modal-body">

 	  <form method="post" role="form" class="form-horizontal"  id="executeFrm" > 
 	   <!-- 获取 项目 的 uid -->    	
      	 <input id="PROJECT_UID" type="hidden" name="PROJECT_UID" fieldname="PROJECT_UID" value="<%=PROJECT_UID%>" />
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					报告日期：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <input data-date-format="yyyy-mm-dd" datatype="*" nullmsg = "请输入工况的报告日期"  placeholder="请输入工况的报告日期" type="text" name="REPORT_DATE" id="REPORT_DATE_TWO" fieldname="REPORT_DATE" class="col-xs-11 col-sm-11" />
				</div>
	  		
			</div>
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					工况：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <textarea autofocus="autofocus" datatype="*" nullmsg="请输入工况的描述内容"  placeholder="请输入工况的描述内容" type="text" name="JINDU" id="JINDU" fieldname="JINDU" class="col-xs-11 col-sm-11" ></textarea>
					
				</div>
			</div>
			<!-- <div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					预案类型：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <select datatype="*" nullmsg="请输入类型"  placeholder="请输入预案类型" type="text" name="PLAN_TYPE" id="PLAN_TYPE" fieldname="PLAN_TYPE" class="col-xs-11 col-sm-11" >
					   <option value="JK">基坑</option>
					   <option value="JS">降水</option>
					 </select>
				</div>
			</div> -->
     </form>
     
	</div>

  <div class="modal-footer">
    <button class="btn btn-success btn-sm" id="addsaveUserInfo">保存</button>
    <button class="btn btn-danger btn-sm pull-right" id="btnClose" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
</div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/convertJson.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/combineQuery.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/default.js?version=20131206"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/bootstrap-validation.js"> </script>
<script type="text/javascript">
var validform;
var controlletname="${pageContext.request.contextPath }/xmgk/pmGongkuangController";
//点击保存按钮
$(function() {	
var xmUid = "<%=PROJECT_UID%>";
$("#PROJECT_UID").val(xmUid);
	 DatePicker.datepicker("#REPORT_DATE_TWO");
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

</script>
		