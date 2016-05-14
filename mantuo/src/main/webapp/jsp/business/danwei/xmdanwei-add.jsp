<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<!-- Modal -->
<%
 String project=request.getParameter("project_uid");
%>
<div class="modal-dialog width-40 height-auto">
  <div class="modal-content" >
   		<div class="widget-header widget-header-large">
   		<div class="widget-toolbar">
			<a href="#" data-action="close" data-dismiss="modal">
				<i class="ace-icon fa fa-times"></i>
				</a>
		</div>
    	<h3 id="myModalLabel" class="blue bigger">添加项目单位</h3>
  </div>
  <div class="modal-body">
 	 <form method="post" role="form" class="form-horizontal"  id="executeFrms" >      	
		<input type="hidden" id="PROJECT_UID" fieldname="PROJECT_UID" value="<%=project %>"/>
		<div class="form-group" >
			 <label class="col-sm-3 control-label no-padding-right">
				单位名称：<span class="required-indicator">*</span>
			</label>
			<div class="col-sm-7" style="display:inline-block;">
				<input class="form-control"  placeholder="请选择单位名称"  id="COMPANY_NAME" fieldname="COMPANY_NAME" datatype="*" nullmsg="请选择单位名称" disabled="disabled">
			   
			</div>
		  		<i class="ace-icon glyphicon glyphicon-list bigger-110" onclick="selectGuifan();" style="cursor:pointer;margin-left:-2%;" title="选择单位"></i>
		</div>
		<div class="form-group" >
			 <label class="col-sm-3 control-label no-padding-right">
				单位编号：<span class="required-indicator">*</span>
			</label>
			<div class="col-sm-7">
			<input class="form-control"   id="COMPANY_UID" fieldname="COMPANY_UID" placeholder="请输入单位编号" datatype="*" nullmsg="请输入单位编号" disabled="disabled">
		     </div>
		</div>
		<div class="form-group" >
			 <label class="col-sm-3 control-label no-padding-right">
				单位类型：<span class="required-indicator">*</span>
			</label>
			<div class="col-sm-5">
					 <select class="col-xs-11 col-sm-10" datatype="*" nullmsg = "请选择单位类型"  placeholder="请选择单位类型" type="text"  fieldname="COMPANY_TYPE">
					 	<option value="SJ">设计单位</option>
					 	<option value="SG">施工总承包</option>
					 	<option value="JL">监理单位</option>
					 	<option value="JS">建设单位</option>
					 	<option value="DJ">代建单位</option>
					 </select>
				</div>
		  		
		  		
		</div>
		
		<div class="form-group" >
			 <label class="col-sm-3 control-label no-padding-right">
				项目负责人：<span class="required-indicator">*</span>
			</label>
			<div class="col-sm-7">
				<input class="form-control"  placeholder="请输入项目负责人" id="PROJECT_FUZEREN" fieldname="PROJECT_FUZEREN" datatype="*" nullmsg="请输入项目负责人">
			</div>
		  		
		</div>
		<div class="form-group" >
			 <label class="col-sm-3 control-label no-padding-right">
				联系电话：<span class="required-indicator">*</span>
			</label>
			<div class="col-sm-7">
				<input class="form-control" placeholder="请输入联系电话"  id="PROJCET_FUZEREN_TEL" fieldname="PROJCET_FUZEREN_TEL" datatype="/^[1][358][0-9]{9}$/" nullmsg="请输入联系电话">
			</div>
		  		
		</div>
     </form>
  </div>

  <div class="modal-footer">
    <button class="btn btn-1 btn-sms " id="addsave">确认</button>
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

function selectGuifan(){
	
	window.open("${pageContext.request.contextPath }/jsp/business/danwei/danwei-select.jsp");
}

$(function() {	
	///$("#PROJECT_UID").val($("#project_uid").val());
	validform=FormValid.validbyformid(executeFrms);
	
	$("#addsave").click(function() {
	  if(validform.check()){
		if($("#executeFrms").validationButton()) {
			//生成json串
			var data = Form2Json.formToJSON(executeFrms);
			//组成保存json串格式
			var data1 = defaultJson.packSaveJson(data);
			
			
			 $.ajax({
				url: '${ctx}/buprojectcompany/buProjectCompanyController?insert',
				data: data1,
				cache: false,
				async: false,
				dataType: "json",
				type: 'post',
				success: function(response) {
					xAlert("信息提示","新增成功",1);
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


//回调
function doCallback(s){
	var strs= new Array(); //定义一数组 
	strs=s.split(","); //字符分割 
	$("#COMPANY_NAME").val(strs[1]);
	$("#COMPANY_UID").val(strs[0]);
	$("#PROJECT_FUZEREN").val(strs[3]);//联系人
	$("#PROJCET_FUZEREN_TEL").val(strs[4]);//联系电话
}
</script>
		