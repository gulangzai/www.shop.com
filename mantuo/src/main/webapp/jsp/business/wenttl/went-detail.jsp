<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>

<%
 String PROJECT_UID =request.getParameter("PROJECT_UID");
  String GKUID = request.getParameter("GKUID");
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
    	<h3 id="myModalLabel" class="blue bigger">修改工况信息</h3>
  </div>
  <div class="modal-body">

 	  <form method="post" role="form" class="form-horizontal"  id="executeFrm" > 
 	   <!-- 获取 项目 的 uid -->    	
      	 <input id="PROJECT_UID" type="hidden" name="PROJECT_UID" fieldname="PROJECT_UID" value="<%=PROJECT_UID%>" />
      	  <input id="GONGKUANG_UID01" type="hidden" name="GONGKUANG_UID" fieldname="GONGKUANG_UID" value="<%=GKUID%>" />
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					报告日期：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <input data-date-format="yyyy-mm-dd" datatype="*" nullmsg = "请输入工况的报告日期"  placeholder="请输入工况的报告日期" type="text"  id="REPORT_DATE" fieldname="REPORT_DATE" class="col-xs-11 col-sm-11" />
				</div>
	  		
			</div>
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					工况：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <textarea  autofocus="autofocus" datatype="*" nullmsg="请输入工况的描述内容"  placeholder="请输入工况的描述内容" type="text"  id="JINDU" fieldname="JINDU" class="col-xs-11 col-sm-11" /></textarea>
					 
				</div>
			</div>
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					创建人：<span class="required-indicator"></span>
				</label>
				<div class="col-sm-10">
					 <input type="text" name="USER_NAME" id="USER_NAME" fieldname="USER_NAME" class="col-xs-11 col-sm-11" readonly="readonly"/>
				</div>
			</div>
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					创建日期：<span class="required-indicator"></span>
				</label>
				<div class="col-sm-10">
					 <input  type="text" name="CREATE_DATE" id="CREATE_DATE" fieldname="CREATE_DATE" class="col-xs-11 col-sm-11"  readonly="readonly"/>
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
var controllername="${pageContext.request.contextPath }/xmgk/pmGongkuangController";
//点击保存按钮
$(function() {	
init();
DatePicker.datepickerid("#REPORT_DATE");
var xmUid = "<%=PROJECT_UID%>";
$("#PROJECT_UID").val(xmUid);
	validform=FormValid.validbyformid(executeFrm);
	$("#addsaveUserInfo").click(function() {
	  if(validform.check()){
		if($("#executeFrm").validationButton()) {
			//生成json串
			var data = Form2Json.formToJSON(executeFrm);
			//组成保存json串格式
			var data1 = defaultJson.packSaveJson(data);
			$.ajax({
				url :controllername+"?update",
				data : data1,
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
					xAlert("信息提示","更新成功",1);
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
      	<INPUT type="text"  kind="text" id="GONGKUANG_UID"  fieldname="GONGKUANG_UID" value="<%=GKUID%>" operation="="/>
      	</TD>
		<TD class="right-border bottom-border">
		</TD>
     </TR>
   </table>
</form>	
		