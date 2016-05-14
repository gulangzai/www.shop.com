<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<!-- Modal -->
<%
  
	String company_uid = request.getParameter("COMPANY_UID");
%>
<div class="modal-dialog width-40 height-auto">
  <div class="modal-content" >
   		<div class="widget-header widget-header-large">
   		<div class="widget-toolbar">
			<a href="#" data-action="close" data-dismiss="modal">
				<i class="ace-icon fa fa-times"></i>
				</a>
		</div>
    	<h3 id="myModalLabel" class="blue bigger">修改单位</h3>
  </div>
  <div class="modal-body">
 	 <form method="post" role="form" class="form-horizontal"  id="executeFrm" >      	
		  <input type="hidden" id="COMPANY_UID" name="COMPANY_UID" fieldname="COMPANY_UID" value="<%=company_uid%>"/>
		<div class="form-group" >
			 <label class="col-sm-2 control-label no-padding-right">
				单位名称：<span class="required-indicator">*</span>
			</label>
			<div class="col-sm-8">
				<input class="form-control" type="text"   id="COMPANY_NAMES" fieldname="COMPANY_NAME" datatype="*" nullmsg="请输入单位名称">
			</div>
		  		
		</div>
		<div class="form-group" >
			 <label class="col-sm-2 control-label no-padding-right">
				单位编号：<span class="required-indicator">*</span>
			</label>
			<div class="col-sm-8">
				<input class="form-control"  type="text"  id="COMPANY_CODES" fieldname="COMPANY_CODE" datatype="*" nullmsg="请输入单位编号">
			</div>
		  		
		</div>
		<div class="form-group" >
			 <label class="col-sm-2 control-label no-padding-right">
				联系人：<span class="required-indicator">*</span>
			</label>
			<div class="col-sm-8">
				<input class="form-control" type="text"  id="LIANXI_RENS" fieldname="LIANXI_REN" datatype="*" nullmsg="请输入联系人">
			</div>
		  		
		</div>
		<div class="form-group" >
			 <label class="col-sm-2 control-label no-padding-right">
				联系电话：<span class="required-indicator">*</span>
			</label>
			<div class="col-sm-8">
				<input class="form-control" type="text"  id="LIANXI_TELS" fieldname="LIANXI_TEL" datatype="/^[1][358][0-9]{9}$/" nullmsg="请输入联系电话">
			</div>
		  		
		</div>
		<div class="form-group" >
			 <label class="col-sm-2 control-label no-padding-right">
				单位地址：<span class="required-indicator">*</span>
			</label>
			<div class="col-sm-8">
				<input class="form-control" type="text"  id="ADDRESSS" fieldname="ADDRESS" datatype="*" nullmsg="请输入单位地址">
			</div>
		  		
		</div>
		<div class="form-group" >
			 <label class="col-sm-2 control-label no-padding-right">
				单位网址：<span class="required-indicator">*</span>
			</label>
			<div class="col-sm-8">
				<input class="form-control" type="text"  id="URLS" fieldname="URL" datatype="*" nullmsg="请输入单位网址">
			</div>
		  		
		</div>
     </form>
  </div>

  <div class="modal-footer">
    <button class="btn btn-1 btn-sms" id="addsaveUserInfo">确认</button>
    <button class="btn btn-2 btn-sms pull-right" id="btnClose" data-dismiss="modal" aria-hidden="true">关闭</button>
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
var conn="${ctx}/bucompany/buCompanyController";

$(function() {	
	
	setFormValues();
	
});
function setFormValues(){
	var msg="";
	var company_name="";
	 var  company_code="";
	 var  address = "";
    var  url = "";
	var  lianxi_ren = "";
	var  lianxi_tel = "";
   var company_uid ="<%=company_uid%>";
	$.ajax({
		url :conn+"?queryid",
		data : {"company_uid":company_uid},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			
			  msg = eval("("+response.msg+")");
			  company_name = msg.response.data[0].COMPANY_NAME;
			  company_code = msg.response.data[0].COMPANY_CODE;
			  address = msg.response.data[0].ADDRESS;
			  url = msg.response.data[0].URL;
			  lianxi_ren = msg.response.data[0].LIANXI_REN;
			  lianxi_tel = msg.response.data[0].LIANXI_TEL;
			 
			 $("#COMPANY_NAMES").val(company_name);
			 $("#COMPANY_CODES").val(company_code);
			 $("#ADDRESSS").val(address);
			 $("#URLS").val(url);
			 $("#LIANXI_RENS").val(lianxi_ren);
			 $("#LIANXI_TELS").val(lianxi_tel);
		}
	});
}
//点击保存按钮
$(function() {	
	validform=FormValid.validbyformid(executeFrm);
	
	$("#addsaveUserInfo").click(function() {
	  if(validform.check()){
		if($("#executeFrm").validationButton()) {
			//生成json串
			var data = Form2Json.formToJSON(executeFrm);
			//组成保存json串格式
			var data1 = defaultJson.packSaveJson(data);
			$.ajax({
				url :conn+"?update",
				data : data1,
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
					xAlert("信息提示","更新成功",1);
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

	