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
    	<h3 id="myModalLabel" class="blue bigger">添加仓库备品</h3>
  </div>
  <div class="modal-body">
 	  <form method="post" role="form" class="form-horizontal"  id="executeFrm" > 
 	   <!-- 获取 项目 的 uid -->    	
      	 <input id="PROJECT_UID" type="hidden" name="PROJECT_UID" fieldname="PROJECT_UID" value="<%=PROJECT_UID%>"/>
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
				    物品名称：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input datatype="*" nullmsg = "请输入物品名称"  placeholder="请输入物品名称" type="text" name="TOOLS_NAME" id="TOOLS_NAME" fieldname="TOOLS_NAME" class="col-xs-11 col-sm-11" />
				</div>
	  		    <label class="col-sm-2 control-label no-padding-right" >
					物品型号：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input datatype="*" nullmsg="请输如物品型号"  placeholder="请输入物品型号" type="text" name="TOOLS_MODEL" id="TOOLS_MODEL" fieldname="TOOLS_MODEL" class="col-xs-11 col-sm-11" />
				</div>
			</div>
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					预案类型：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <select datatype="*" nullmsg="请选择类型"  placeholder="请选择预案类型" type="text" name="TOOLS_TYPE" id="TOOLS_TYPE" fieldname="TOOLS_TYPE" class="col-xs-11 col-sm-11" >
					   <option value="JK">基坑</option>
					   <option value="JS">降水</option>
					 </select>
				</div>
				<label class="col-sm-2 control-label no-padding-right">
				    物品单位：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input datatype="*" nullmsg = "请输入物品单位"  placeholder="请输入物品单位" type="text" name="TOOLS_UNIT" id="TOOLS_UNIT" fieldname="TOOLS_UNIT" class="col-xs-11 col-sm-11" />
				</div>
			</div>
			
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
				 物品总数量：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input datatype="*" nullmsg="请输入物品总数量"  placeholder="请输入物品总数量" type="text" name="TOOLS_NUMS" id="TOOLS_NUMS" fieldname="TOOLS_NUMS" class="col-xs-11 col-sm-11" />
				</div>
				<label class="col-sm-2 control-label no-padding-right">
				    正在用的物品量：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input datatype="*" nullmsg = "请输入正在使用的物品数量"  placeholder="请输入正在使用的物品数量" type="text" name="IN_USE_NUMS" id="IN_USE_NUMS" fieldname="IN_USE_NUMS" class="col-xs-11 col-sm-11" />
				</div>
			</div>
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					轻微损坏的数量：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input datatype="*" nullmsg="请输入轻微损坏的物品数量"  placeholder="请输入轻微损坏的物品数量" type="text" name="SLIGHT_DAMAGE_NUMS" id="SLIGHT_DAMAGE_NUMS" fieldname="SLIGHT_DAMAGE_NUMS" class="col-xs-11 col-sm-11" />
				</div>
				<label class="col-sm-2 control-label no-padding-right" >
					轻微损坏的数量：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input datatype="*" nullmsg="请输入损坏的物品数量"  placeholder="请输入损坏的物品数量" type="text" name="DAMAGE_NUMS" id="DAMAGE_NUMS" fieldname="DAMAGE_NUMS" class="col-xs-11 col-sm-11" />
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
var controlletname="${pageContext.request.contextPath }/JcTools/jcckController";
//点击保存按钮
$(function() {	
var xmUid = "<%=PROJECT_UID%>";
$("#PROJECT_UID").val(xmUid);
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
		   return;
	   }
	});
});

</script>
		