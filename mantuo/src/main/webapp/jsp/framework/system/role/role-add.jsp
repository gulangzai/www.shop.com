<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%
String ROLE_ID = request.getParameter("ryid");
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
    	<h3 id="myModalLabel" class="blue bigger">添加角色管理<span id="title_sp"></span></h3>
  </div>
  <div class="modal-body">

 	  <form method="post" role="form" class="form-horizontal"  id="executeFrm" >      	
      	<input type="hidden" id="PROJECT_ROLE_UID" fieldname="PROJECT_ROLE_UID">
      	<input type="hidden" id="P_UID" fieldname="PROJECT_UID">

	  		<div class="form-group" >
		  		<label class="col-sm-4 control-label no-padding-right">
					角色名称：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-6">
					 <input  nullmsg="请输入角色名称！"type="text" name="NAME" id="LOGON_NAME" fieldname="ROLE_NAME" class="col-xs-11 col-sm-11"  ajaxurl="${ctx}/xtgl/usersController?check"/>
					 <div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
					
				</div>

			</div>
			<!-- 
	  		<div class="form-group" >
			 <label class="col-sm-4 control-label no-padding-right" >
				角色类别：<span class="required-indicator">*</span>
			 </label>
				<div class="col-sm-6">
					<select name="ROLETYPE" fieldname="ROLETYPE" id="ROLETYPE" check-type="required" kind="dic" src="JSLB" defaultmemo="请选择"  class="col-xs-11 col-sm-11">

          			</select>
					 <div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
			-->
	  		<div class="form-group" >
			 <label class="col-sm-4 control-label no-padding-right" >
				显示序号：
			 </label>
				<div class="col-sm-6">
					 <input type="text" check-type="n" name="XUHAO" id="XUHAO" fieldname="XUHAO" class="col-xs-11 col-sm-11"  />
					 <div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
			 
	  		<div class="form-group">
				<label class="col-sm-4 control-label no-padding-right" for="form-field-1">
					汉字描述：&nbsp;
				</label>
				<div class="col-sm-6">
					<input type="text"  fieldname="DESCRIPTION" name="S_MEMO" id="S_MEMO"  class="col-xs-11 col-sm-11">
				</div>
			</div>
	  		
     </form>
     
	</div>

  <div class="modal-footer">
    <button class="btn btn-success btn-sm" id="saveRoleInfo">保存</button>
    <button class="btn btn-danger btn-sm pull-right" id="btnClose" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
</div>
</div>
<form method="post" id="queryFormById"  >
   <table class="B-table" width="100%">
   <!--可以再此处加入hidden域作为过滤条件 -->
   	<TR  style="display:none;">
     	<TD class="right-border bottom-border">
      	<INPUT type="text"  kind="text" id="QROLE_ID"  fieldname="ROLE_ID" value="<%=ROLE_ID %>" operation="="/>
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
<script src="${ctx}/assets/sys_resources/js/common/loadFields.js?version=20131206"></script>
<script type="text/javascript">
var controllername= "${pageContext.request.contextPath }/roleController";
var validform;
//点击保存按钮
$(function() {	
	validform=FormValid.validbyformid(executeFrm);
	
	$("#saveRoleInfo").click(function() {
	  if(validform.check()){
		if($("#executeFrm").validationButton()) {
			$("#P_UID").val($("#project_uid").val());
			//生成json串
			var data = Form2Json.formToJSON(executeFrm);
			//组成保存json串格式
			var data1 = defaultJson.packSaveJson(data);
			
			var ROLE_ID = $('#PROJECT_ROLE_UID').val();
			
			if(ROLE_ID==""){
				defaultJson.doInsertJson(controllername + "?executeRole&operatorSign=1", data1);
			}else{
				defaultJson.doInsertJson(controllername + "?executeRole&operatorSign=2", data1);
			}
			jQuery("#grid_table").jqGrid().trigger("reloadGrid");
			$('#btnClose').click();
			//var a =window.parent;
			//a.location.reload();
			//window.close();
		 }
	   }else {
		   return;
	   }
	});
	init();
});


function init(){
	var ROLE_ID = $('#QROLE_ID').val();
	if(ROLE_ID!=""){
		setFormValues();
	}
}

function setFormValues(){
	var data = combineQuery.getQueryCombineData(queryFormById,null,null);
	var data1 = {
		msg : data
	};
	$.ajax({
		url : contextPath+'/roleController?queryRole',
		data : data1,
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
		