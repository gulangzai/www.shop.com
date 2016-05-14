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
    	<h3 id="myModalLabel" class="blue bigger">添加安全检查标准</h3>
  </div>
  <div class="modal-body">

 	  <form method="post" role="form" class="form-horizontal"  id="executeFrm" > 
 	   <!-- 获取 项目 的 uid -->    	
      	 <input id="PROJECT_UID" type="hidden" fieldname="PROJECT_UID" value="<%=PROJECT_UID%>" />
      	 <input id="JIANCHA_TYPE" type="hidden" fieldname="JIANCHA_TYPE" value="AJ" />
      	 
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					检查级别：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10" id="jcjb">
					
				</div>	 
	  		
			</div>
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					对应标准规范：
				</label>
				<div class="col-sm-10">
					 <input id="gfname" type="text"  class="col-xs-11 col-sm-11" nullmsg = "请选择对应标准规范"  placeholder="请选择对应标准规范" disabled="disabled">
					 <input  type="hidden" id="BZGF_UID"   fieldname="BZGF_UID"  class="col-xs-11 col-sm-11" >
					 <i class="ace-icon glyphicon glyphicon-list bigger-110" onclick="selectGuifan();" style="cursor:pointer" title="选择规范"></i>
				</div>
			</div>
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					检查内容：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <textarea id="CONTENT"  datatype="*" type="text" nullmsg = "请输入检查内容"  placeholder="请输入检查内容"  fieldname="CONTENT" class="col-xs-11 col-sm-11" ></textarea>
					
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
var controlletname="${pageContext.request.contextPath }/pmjiancha/pmJianchaBzController";
//点击保存按钮
$(function() {	
	getJcjb();

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
					reloadGrid();
			      }
			});
			
		 }
	   }else {
		   return;
	   }
	});
});

function getJcjb(){
	$.ajax({
		url :controlletname+"?queryJcjb",
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var html = "";
			$(response.obj).each(function(index,element){
				if(index==0){
					html += "<input type='radio' style='margin-left:15px;' checked='checked' name='JIANCHA_LEVEL' fieldname='JIANCHA_LEVEL_UID' value='"+element.JIANCHA_LEVEL_UID+"'>"+element.LEVEL_NAME;
				}else{
					html += "<input type='radio' style='margin-left:15px;' name='JIANCHA_LEVEL' fieldname='JIANCHA_LEVEL_UID' value='"+element.JIANCHA_LEVEL_UID+"'>"+element.LEVEL_NAME;
				}
				
			});
			$('#jcjb').html(html);
	      }
	});
}

//打开选择页
function selectGuifan(){
	var guifan_uid = $("#BZGF_UID").val();
	var project_uid = $("#project_uid").val();
	window.open("${pageContext.request.contextPath }/jsp/business/jiancha/guifan-select.jsp?guifan_uid="+guifan_uid+"&project_uid="+project_uid);
}

//回调
function doCallback(str){
	var strs= new Array(); //定义一数组 
	strs=str.split(","); //字符分割 
	$("#BZGF_UID").val(strs[0]);
	$("#gfname").val(strs[1]);
	
}
</script>
		