<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>

<%
 String PROJECT_UID = request.getParameter("PROJECT_UID");
 String NODE_TYPE = request.getParameter("NODE_TYPE");
 String p_uid = request.getParameter("p_uid");
 String p_name = request.getParameter("p_name");
		 
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
    	<h3 id="myModalLabel" class="blue bigger"><p id="title"><p></h3>
  </div>
  <div class="modal-body">

 	  <form method="post" role="form" class="form-horizontal"  id="executeFrm" > 
 	   <!-- 获取 项目 的 uid -->    	
      	 <input id="PROJECT_UID" type="hidden" fieldname="PROJECT_UID" value="<%=PROJECT_UID%>" />
      	 <input id="NODE_TYPE" type="hidden" fieldname="NODE_TYPE" value="<%=NODE_TYPE%>" />
      	 
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					标准级别：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					<input type="radio" name="BZ_LEVEL" checked="checked" value="GJ" fieldname="BZ_LEVEL">国家标准
					<input type="radio" name="BZ_LEVEL" value="HY" fieldname="BZ_LEVEL">行业标准
				</div>
				
				<label class="col-sm-2 control-label no-padding-right">
					等级：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">

					<select fieldname="WEIGUI_LEVEL">
						<option value="1">一般</option>
						<option value="2">较严重</option>
						<option value="3">严重</option>						
					</select>
				</div>		 
	  		
			</div>
			
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					上层节点：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <input id="P_BZGF" datatype="*" type="text" value="<%=p_name%>" disabled="disabled" class="col-xs-11 col-sm-11" >
					 <input  type="hidden" datatype="*"  fieldname="P_BZGF_UID" value="<%=p_uid%>" class="col-xs-11 col-sm-11" >
					
				</div>
			</div>
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					<p id="labelName"></p><span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <textarea id="NODE_CONTENT"  datatype="*" type="text"  fieldname="NODE_CONTENT" class="col-xs-11 col-sm-11" ></textarea>
					
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

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/convertJson.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/combineQuery.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/default.js?version=20131206"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/bootstrap-validation.js"> </script>
<script type="text/javascript">
var validform;
var controlletname="${pageContext.request.contextPath }/pmjiancha/pmBzgfController";
//点击保存按钮
$(function() {	
	var xmUid = "<%=PROJECT_UID%>";
	var nodeType = "<%=NODE_TYPE%>";
	if(nodeType=='FL'){
		$('#title').text('添加分类');
		$('#labelName').text('分类名称：');
		$('#NODE_CONTENT').attr('nullmsg','请输入分类名称').attr('placeholder','请输入分类名称');
	}else if(nodeType=='SJ'){
		$('#title').text('添加事件');
		$('#labelName').text('事件内容：');
		$('#NODE_CONTENT').attr('nullmsg','请输入事件内容').attr('placeholder','请输入事件内容');
	}else{
		$('#title').text('添加规范');
		$('#labelName').text('规范名称：');
		$('#NODE_CONTENT').attr('nullmsg','请输入规范名称').attr('placeholder','请输入规范名称');
	}

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
					reloadIndex();
			      }
			});
			
		 }
	   }else {
		   return;
	   }
	});
});

</script>
		