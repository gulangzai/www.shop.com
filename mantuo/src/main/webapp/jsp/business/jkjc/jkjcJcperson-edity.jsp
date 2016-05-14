<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>

<%
 String PROJECT_UID =request.getParameter("PROJECT_UID");
  String JCSB_UID = request.getParameter("JCUID");
 %>
<!-- Modal -->
<div class="modal-dialog width-70 height-auto">
  <div class="modal-content" >
   		<div class="widget-header widget-header-large">
   		
    	<h3 id="myModalLabel" class="blue bigger">修改负责人信息</h3>
  </div>
  <div class="modal-body">

 	  <form method="post" role="form" class="form-horizontal"  id="executeFrm" > 
 	   <!-- 获取 项目 的 uid -->    	
      	 <input id="PROJECT_UID" type="hidden" name="PROJECT_UID" fieldname="PROJECT_UID" value="<%=PROJECT_UID%>"/>
      	  <input id="PERSON_INFO_UID" type="hidden" name="PERSON_INFO_UID" fieldname="PERSON_INFO_UID" value="<%=JCSB_UID%>"/>
	  	  <input id="REPAIR_Y" type="hidden" name="REPAIR_Y" fieldname="REPAIR_Y" operation="=" value="N"/> 
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					姓名：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input datatype="*" nullmsg = "请输入联系人姓名"  placeholder="请输入联系人姓名" type="text" name="PERSON_NAME" id="PERSON_NAME" fieldname="PERSON_NAME"  class="col-xs-11 col-sm-11" />
				</div>
	  		
			</div>
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					职务：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input datatype="*" nullmsg="请输入联系人职务"  placeholder="请输入联系人姓名" type="text" name="PERSON_POST",id="PERSON_POST" fieldname="PERSON_POST" class="col-xs-11 col-sm-11" />
				</div>
			</div>
			
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					邮箱：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input datatype="*" nullmsg="请输入联系人邮箱"  placeholder="请输入联系人邮箱" type="text" name="PERSON_EMAIL",id="PERSON_EMAIL" fieldname="PERSON_EMAIL" class="col-xs-11 col-sm-11" />
				</div>
			</div>
			
			<div class="form-group">
			    <label class="col-sm-2 control-label no-padding-right">
					公司：
				</label>
				<div class="col-sm-4">
					 <input   placeholder="请输入联系人公司名称" type="text" name="COMPANY_NAME" id="COMPANY_NAME" fieldname="COMPANY_NAME" class="col-xs-11 col-sm-11""/>
				</div>
			
			</div>
			
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					联络类型：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <select datatype="*" nullmsg="请输入联络类型" placeholder="请输入联络类型" type="text" name="CONTACT_TYPE" id="CONTACT_TYPE" fieldname="CONTACT_TYPE" class="col-xs-11 col-sm-11"  >
					   <option value="JK" selected="selected">基坑</option>
					   <option value="JS">降水</option>
					 </select>
				</div>
			</div>	
			
			<!-- <div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					是否为抢修单位联系人：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <select datatype="*" nullmsg="请选择是否为抢修单位联系人" placeholder="请选择是否为抢修单位联系人" type="text" name="REPAIR_Y" id="REPAIR_Y" fieldname="REPAIR_Y" class="col-xs-11 col-sm-11"  >
					  <option value="N" >否</option>
					  <option value="Y">是</option>
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
var controlletname="${pageContext.request.contextPath}/JcPerson/jcPersonInfoController";
//点击保存按钮
$(function() {	
var xmUid = "<%=PROJECT_UID%>";
$("#PROJECT_UID").val(xmUid);
    init();
	DatePicker.datepickerid("#BEGIN_TIME");
	DatePicker.datepickerid("#END_TIME");
	validform=FormValid.validbyformid(executeFrm);
	
	$("#addsaveUserInfo").click(function() {
	  if(validform.check()){
		if($("#executeFrm").validationButton()) {
			//生成json串
			var data = Form2Json.formToJSON(executeFrm);
			//组成保存json串格式
			var data1 = defaultJson.packSaveJson(data);
			
			$.ajax({
				url :controlletname+"?update",
				data : data1,
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
					xAlert("信息提示","修改成功",1);
					$("#btnClose").click();
			      }
			});
			
		 }
	   }else {
		   return;
	   }
	});
});


function init(){
    setFormValues();
}
function setFormValues(){
	var data = combineQuery.getQueryCombineData(queryFormById,null,null);
	var data1 = {
		msg : data
	};
	$.ajax({
		url : controllername+"?query",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response){
			var resultobj = defaultJson.dealResultJson(response.msg);
			$("#executeFrm").setFormValues(resultobj);	
		
		}
	});
}

</script>
<form method="post" id="queryFormById"  >
   <table class="B-table" width="100%">
   <!--可以再此处加入hidden域作为过滤条件 -->
   	<TR  style="display:none;">
     	<TD class="right-border bottom-border">
      	<INPUT type="text"  kind="text" id="PERSON_INFO_UID"  fieldname="PERSON_INFO_UID" value="<%=JCSB_UID%>" operation="="/>
      	<!-- <INPUT type="text"  kind="text" id="REPAIR_Y"  fieldname="REPAIR_Y" value="N" operation="="/> -->
      	</TD>
		<TD class="right-border bottom-border">
		</TD>
     </TR>
   </table>
</form>	

</script>
		