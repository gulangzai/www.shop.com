<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>

<%
 String NODE_TYPE = request.getParameter("NODE_TYPE");
 String p_uid = request.getParameter("p_uid");
 String p_name = request.getParameter("p_name");
 String bzgf_uid = request.getParameter("bzgf_uid");
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
      	 <input id="NODE_TYPE" type="hidden" fieldname="NODE_TYPE" value="<%=NODE_TYPE%>" />
      	 <input id="BZGF_UID" type="hidden" fieldname="BZGF_UID" />
      	 
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					标准级别：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					<input type="radio" name="BZ_LEVEL" checked="checked" value="GJ" fieldname="BZ_LEVEL">国家标准
					<input type="radio" name="BZ_LEVEL" value="HY" fieldname="BZ_LEVEL">行业标准
				</div>	 
	  		
			</div>
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					上层节点：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <input id="p_bzgf" type="text"  class="col-xs-11 col-sm-11" value="<%=p_name %>" >
					 <input  type="hidden" id="p_id" datatype="*"  fieldname="P_BZGF_UID" class="col-xs-11 col-sm-11" value="<%=p_uid %>">
					
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
<form method="post" id="queryFormById"  >
   <table class="B-table" width="100%">
   <!--可以再此处加入hidden域作为过滤条件 -->
   	<TR  style="display:none;">
     	<TD class="right-border bottom-border">
      	<INPUT type="text"  kind="text" id="BZGF_UID"  fieldname="BZGF_UID" value="<%=bzgf_uid%>" operation="="/>
      	</TD>
		<TD class="right-border bottom-border">
		</TD>
     </TR>
   </table>
</form>	
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
	var nodeType = "<%=NODE_TYPE%>";
	init();
	if(nodeType=='FL'){
		$('#title').text('修改分类');
		$('#labelName').text('分类名称：');
		$('#NODE_CONTENT').attr('nullmsg','请输入分类名称').attr('placeholder','请输入分类名称');
	}else if(nodeType=='SJ'){
		$('#title').text('修改事件');
		$('#labelName').text('事件内容：');
		$('#NODE_CONTENT').attr('nullmsg','请输入事件内容').attr('placeholder','请输入事件内容');
	}else{
		$('#title').text('修改规范');
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
				url :controlletname+"?update",
				data : data1,
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
					xAlert("信息提示","修改成功",1);
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
		success : function(response) {
			var resultobj = defaultJson.dealResultJson(response.msg);
			var p_bzgf_uid = resultobj.P_BZGF_UID;
			var node_type = resultobj.NODE_TYPE;
			if(p_bzgf_uid==""){
				if(node_type=="GF"){
					$('#p_bzgf').val('规范');
				}else if(node_type=="FL"){
					$('#p_bzgf').val('分类');
				}else if(node_type=="SJ"){
					$('#p_bzgf').val('事件');
				}
			}
			
			$("#executeFrm").setFormValues(resultobj);	
		
		}
	});
}
</script>
		