<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>

<%
 String PROJECT_UID =request.getParameter("PROJECT_UID");
 %>
<!-- Modal -->
<div class="modal-dialog width-70 height-auto">
  <div class="modal-content" >
   		<div class="widget-header widget-header-large">
   		<div class="widget-toolbar">
			<a href="#" data-action="close" data-dismiss="modal">
				<i class="ace-icon fa fa-times"></i>
				</a>
		</div>
    	<h3 id="myModalLabel" class="blue bigger">添加设施设备</h3>
  </div>
  <div class="modal-body">

 	  <form method="post" role="form" class="form-horizontal"  id="executeFrm" > 
 	   <!-- 获取 项目 的 uid -->    	
      	 <input id="PROJECT_UID" type="hidden" name="PROJECT_UID" fieldname="PROJECT_UID" value="<%=PROJECT_UID%>" />
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					设备名称：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <input datatype="*" nullmsg = "请输入设备名称"  placeholder="请输入设备名称" type="text" name="EQUIPMENT_NAME" id="EQUIPMENT_NAME" fieldname="EQUIPMENT_NAME"  style="width: 97%;" />
				</div>
	  		
			</div>
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					设备单位（个丶台）：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input datatype="*" nullmsg="请输入设备的单位"  placeholder="请输入设备的单位" type="text" name="EQUIPMENT_UNIT" id="EQUIPMENT_UNIT" fieldname="EQUIPMENT_UNIT" class="col-xs-11 col-sm-11" />
				</div>
			</div>
			
			<div class="form-group">
			    <label class="col-sm-2 control-label no-padding-right">
					设备型号：
				</label>
				<div class="col-sm-4">
					 <input    placeholder="请输入设备的型号 " type="text" name="EQUIPMENT_MODEL" id="EQUIPMENT_MODEL" fieldname="EQUIPMENT_MODEL" class="col-xs-11 col-sm-11""/>
				</div>
			
			</div>
			
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					出水量（立方米/秒）：
				</label>
				<div class="col-sm-4">
					 <input   placeholder="请输入出水量丶范围是：0.0~10000.0" type="text" name="WATER_YIELD" id="WATER_YIELD" fieldname="WATER_YIELD" class="col-xs-11 col-sm-11"  />
				</div>
			</div>	
			<div class="form-group">
			  <label class="col-sm-2 control-label no-padding-right">
					耗电（KW）：
				</label>
				<div class="col-sm-4">
					 <input   placeholder="请输入设备的耗电量" type="text" name="ELECTRICITY" id="ELECTRICITY" fieldname="ELECTRICITY" class="col-xs-11 col-sm-11"  />
				</div>
			</div>		
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					设备类型：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <select datatype="*" nullmsg="请输入设备类型" placeholder="请输入设备类型" type="text" name="EQUIPMENT_TYPE" id="EQUIPMENT_TYPE" fieldname="EQUIPMENT_TYPE" class="col-xs-11 col-sm-11"  >
					   <option value="JK" selected="selected">基坑</option>
					   <option value="JS">降水</option>
					 </select>
				</div>
	  		
			</div>			
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
var controlletname="${pageContext.request.contextPath }/jcsb/jcJkEquipInfoController";
//点击保存按钮
$(function() {	
var xmUid = "<%=PROJECT_UID%>";
$("#PROJECT_UID").val(xmUid);
	//DatePicker.datepickerid("#BEGIN_TIME");
	//DatePicker.datepickerid("#END_TIME");
	validform=FormValid.validbyformid(executeFrm);
	
	$("#addsaveUserInfo").click(function() {
	 var chushui = $("#WATER_YIELD").val();
	  if(validform.check()  && chushui <1000){
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
			      }
			});
			
		 }
	   }else {
	   	xAlert("信息提示","出水量范围超出或输入有空缺，请重新确认！",1);
	 // alert("出水量范围超出或输入有空缺,请重新确认!");
		   return;
	   }
	   
	});
});

</script>
		