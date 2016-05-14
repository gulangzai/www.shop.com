<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%
	String PRJ_POINTS_UID = request.getParameter("PRJ_POINTS_UID");
%>
<!-- Modal -->
<div class="modal-dialog width-40 height-auto">
  <div class="modal-content" >
   		<div class="widget-header widget-header-large">
   		<div class="widget-toolbar">
			<a href="#" data-action="close" data-dismiss="modal">
				<i class="ace-icon fa fa-times"></i>
				</a>
		</div>
    	<h3 id="myModalLabel" class="blue bigger">修改降水设置</h3>
  </div>
  <div class="modal-body">

 	  <form method="post" role="form" class="form-horizontal"  id="executeFrm" >      	
      		<input type="hidden" fieldname="PRJ_POINTS_UID">
	  		<div class="form-group" >
		  		<label class="col-sm-3 control-label no-padding-right">
					测点编号：
				</label>
				<div class="col-sm-8">
					 <input type="text"  fieldname="POINT_CODE" disabled="disabled"  class="col-xs-12 col-sm-12"/>
				</div>
	  		
			</div>
	  		<div class="form-group" >
	  			<label class="col-sm-3 control-label no-padding-right" >
					孔口高程(m)：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-8">
					 <input datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/" nullmsg="不能为空" errormsg="必须为数字，可以有小数" type="text"  fieldname="INIT_HEIGHT"  class="col-xs-12 col-sm-12"/>
				</div>
	  	
			</div>
			<!-- 
	  		<div class="form-group" >
		  		<label class="col-sm-3 control-label no-padding-right">
					泵设备类型：
				</label>
				<div class="col-sm-8">
					 <select id="EQUIPMENT_UID" fieldname="EQUIPMENT_UID" class="col-xs-12 col-sm-12">
					 	<option value="28">-1-</option>
					 	<option value="36">=2=</option>
					 </select>
				</div>
	  		
			</div>			
	  		 -->
	  		
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
var PRJ_POINTS_UID = '<%=PRJ_POINTS_UID%>';
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
				url :'${ctx}/jkjc/jcPrjPointsController?update',
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
					//window.close();
			      }
			});
			
		 }
	   }else {
		   return;
	   }
	});
});

function setFormValues(){
	$.ajax({
		url : '${ctx}/jkjc/jcPrjPointsController?queryById',
		data : {"PRJ_POINTS_UID":PRJ_POINTS_UID},
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
		