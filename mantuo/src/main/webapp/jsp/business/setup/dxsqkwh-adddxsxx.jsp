<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>

<%
 String PROJECT_UID =request.getParameter("PROJECT_UID");
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
    	<h3 id="myModalLabel" class="blue bigger">添加地下水情况</h3>
  </div>
  <div class="modal-body">

 	  <form method="post" role="form" class="form-horizontal"  id="executeFrm" > 
 	   <!-- 获取 项目 的 uid -->    	
      	 <input id="PROJECT_UID" type="hidden" name="PROJECT_UID" fieldname="PROJECT_UID" value="<%=PROJECT_UID%>" />
	  		<div class="form-group" >
		  		<label class="col-sm-4 control-label no-padding-right">
					水头埋深：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-6">
					 <input datatype="/^[\+-]{0,1}(\d{0,4})\.(\d{1,2})$|^[\+-]{0,1}(\d{0,4})$/" errormsg="输入的数字格式不对，小数最大值为9999.99，小数点后只能保留2位小数" 
					 nullmsg = "水头埋深不可为空"  placeholder="请输入水头埋深" type="text" name="DEEP" id="DEEP_ONE" fieldname="DEEP"  class="col-xs-12 col-sm-12" />
				</div>
	  		
			</div>
	  		
			<div class="form-group" >
		  		<label class="col-sm-4 control-label no-padding-right">
					水位标高：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-6">
					 <input datatype="/^[\+-]{0,1}(\d{0,4})\.{1}(\d{1,2})$|^[\+-]{0,1}(\d{0,4})$/" errormsg="输入的数字格式不对，小数最大值为9999.99，小数点后只能保留2位小数" 
					  nullmsg = "水位标高不可为空"  placeholder="请输入水位标高" type="text" name="HEIGHT" id="HEIGHT_ONE" fieldname="HEIGHT" class="col-xs-12 col-sm-12" />
				</div>
	  		
			</div>
			
     </form>
     
	</div>

  <div class="modal-footer">
    <button class="btn btn-success btn-sm" id="adddxsInfo">保存</button>
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
var controlletname="${pageContext.request.contextPath }/dizhi/pmDxsController";
//点击保存按钮
$(function() {	
var xmUid = "<%=PROJECT_UID%>";
   $("#PROJECT_UID").val(xmUid);
	validform=FormValid.validbyformid(executeFrm);
	
	$("#adddxsInfo").click(function() {
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
					//关闭页面 刷新 数据
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
		