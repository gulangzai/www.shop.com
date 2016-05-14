<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%
	String JC_PRJ_ITEM_UID = request.getParameter("JC_PRJ_ITEM_UID");
%>
<!-- Modal -->
<div class="modal-dialog width-50 height-auto">
  <div class="modal-content" >
   		<div class="widget-header widget-header-large">
   		<div class="widget-toolbar">
			<a href="#" data-action="close" data-dismiss="modal">
				<i class="ace-icon fa fa-times"></i>
				</a>
		</div>
    	<h3 id="myModalLabel" class="blue bigger">修改监测项维护</h3>
  </div>
  <div class="modal-body">

 	  <form method="post" role="form" class="form-horizontal"  id="executeFrm" >      	
      		<input type="hidden" fieldname="JC_PRJ_ITEM_UID">
	  		<div class="form-group" >
		  		<label class="col-sm-4 control-label no-padding-right">
					监测对象：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-7">
					 <select  fieldname="JC_OBJECT_UID" id="JC_OBJECT_UID" class="col-xs-12 col-sm-12">
					 	
					 </select>
				</div>
	  		
			</div>
	  		<div class="form-group" >
	  			<label class="col-sm-4 control-label no-padding-right" >
					检测值类型：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-7">
					<select fieldname="JC_TYPE_UID" id="JC_TYPE_UID" class="col-xs-12 col-sm-12">
						
					</select>
				</div>
	  	
			</div>
	  		<div class="form-group" >
		  		<label class="col-sm-4 control-label no-padding-right">
					监测项目名称：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-7">
					<input type="text" datatype="*" fieldname="JC_NAME" class="col-xs-12 col-sm-12">
				</div>
			</div>			
	  		<div class="form-group" >
		  		<label class="col-sm-4 control-label no-padding-right">
					监测项短名称：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-7">
					<input type="text" datatype="*" fieldname="SHORT_NAME" class="col-xs-12 col-sm-12">
				</div>
			</div>			
	  		<div class="form-group" >
		  		<label class="col-sm-4 control-label no-padding-right">
					前缀：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-2">
					<input type="text" datatype="*" fieldname="PRE_CODE" class="col-xs-12 col-sm-12">
				</div>
				<label class="col-sm-3 control-label no-padding-right">
					单位：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-2">
					<input type="text" datatype="*" fieldname="UNIT" class="col-xs-12 col-sm-12">
				</div>
			</div>			
	  		
	  		<div class="form-group" >
		  		<label class="col-sm-4 control-label no-padding-right">
					单次变化值上限：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-7">
					<input type="text" datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/" nullmsg="不能为空" errormsg="必须为数字，可以有小数" fieldname="SINGLE_WARN" class="col-xs-12 col-sm-12">
				</div>
			</div>			
	  		<div class="form-group" >
		  		<label class="col-sm-4 control-label no-padding-right">
					累计值下限：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-2">
					<input type="text" datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/" nullmsg="不能为空" errormsg="必须为数字，可以有小数" fieldname="TOTAL_WARN1" class="col-xs-12 col-sm-12">
				</div>
		  		<label class="col-sm-3 control-label no-padding-right">
					累计值上限：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-2">
					<input type="text" datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/" nullmsg="不能为空" errormsg="必须为数字，可以有小数" fieldname="TOTAL_WARN2" class="col-xs-12 col-sm-12">
				</div>				
			</div>			
	  		
	  		<div class="form-group" >
		  		<label class="col-sm-4 control-label no-padding-right">
					监测项所属的二级菜单：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-7">
					<select fieldname="ITEM_TYPE"  class="col-xs-12 col-sm-12">
						<option value="基坑">基坑</option>
						<option value="降水">降水</option>
						<option value="视频监控">视频监控</option>
						<option value="门禁">门禁</option>
					</select>
				</div>
			</div>			
	  		
     </form>
     
	</div>

  <div class="modal-footer">
    <button class="btn btn-success btn-sm" id="saveBtn">保存</button>
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
var JC_PRJ_ITEM_UID = '<%=JC_PRJ_ITEM_UID%>';
//点击保存按钮
$(function() {	
	validform=FormValid.validbyformid(executeFrm);
	
	setFormValues();
	$("#saveBtn").click(function() {
	  if(validform.check()){
		if($("#executeFrm").validationButton()) {
			//生成json串
			var data = Form2Json.formToJSON(executeFrm);
			//组成保存json串格式
			var data1 = defaultJson.packSaveJson(data);
			
			$.ajax({
				url :'${ctx}/jkjc/jcPrjJcItemController?update',
				data : data1,
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
					xAlert("信息提示","设置成功",1);
					var a = window.parent;
					a._reload();
					$('#btnClose').click();
			      }
			});
			
		 }
	   }else {
		   return;
	   }
	});
});

function setFormValues(){
	
	//查询监测对象
	$.ajax({
		url: '${ctx}/jkjc/jcPrjJcItemController?queryJcObject',
		data: {},
		cache: false,
		async: false,
		dataType: "json",
		type: 'post',
		success: function(response) {
			var showHtml = "";
			$(response.obj).each(function() {
				showHtml += "<option value='" + this.JC_OBJECT_UID + "'";
				showHtml += ">" + this.OBJECT_NAME + "</option>";
			});
			$("#JC_OBJECT_UID").empty();
			$("#JC_OBJECT_UID").append(showHtml);
		}
	});
	
	//查询检测值类型
	$.ajax({
		url: '${ctx}/jkjc/jcPrjJcItemController?queryJcType',
		data: {},
		cache: false,
		async: false,
		dataType: "json",
		type: 'post',
		success: function(response) {
			var showHtml = "";
			$(response.obj).each(function() {
				showHtml += "<option value='" + this.JC_TYPE_UID+ "'";
				showHtml += ">" + this.JC_TYPE_NAME + "</option>";
			});
			$("#JC_TYPE_UID").empty();
			$("#JC_TYPE_UID").append(showHtml);
	
		}
	});
	
	
	$.ajax({
		url : '${ctx}/jkjc/jcPrjJcItemController?queryById',
		data : {"JC_PRJ_ITEM_UID":JC_PRJ_ITEM_UID},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var resultobj = defaultJson.dealResultJson(response.msg);
			$("#executeFrm").setFormValues(resultobj);	

		}
	});
}


</script>
		