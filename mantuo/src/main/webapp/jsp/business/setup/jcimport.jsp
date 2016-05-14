<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<!-- Modal -->
<div class="modal-dialog width-50 height-auto">
  <div class="modal-content" >
   		<div class="widget-header widget-header-large">
   		<div class="widget-toolbar">
			<a href="#" data-action="close" data-dismiss="modal">
				<i class="ace-icon fa fa-times"></i>
				</a>
		</div>
    	<h3 id="myModalLabel" class="blue bigger">检测数据导入</h3>
  </div>
  <div class="modal-body">
 	 <form method="post" role="form" class="form-horizontal"  id="executeFrm" >      	

		<div class="form-group" >
			 <label class="col-sm-3 control-label no-padding-right">
				项目名称：<span class="required-indicator"></span>
			</label>
			<div class="col-sm-8">
				<input class="form-control" type="text" id="projctname" disabled="disabled">
			</div>
		  		
		</div>
		<div class="form-group" >
			 <label class="col-sm-3 control-label no-padding-right">
				测点类型：<span class="required-indicator">*</span>
			</label>
			<div class="col-sm-8">
				<select class="form-control">
					<option value="">沉降</option>
					<option value="">水平位移</option>
					<option value="">测斜</option>
					
				</select>
			</div>
		  		
		</div>
		<div class="form-group" >
			 <label class="col-sm-3 control-label no-padding-right">
				设备型号：<span class="required-indicator">*</span>
			</label>
			<div class="col-sm-8">
				<select class="form-control">
					<option value="">YL98220</option>
					
				</select>
			</div>
		  		
		</div>
		<!-- 
		<div class="form-group" >
			 <label class="col-sm-2 control-label no-padding-right">
				数据类型：<span class="required-indicator">*</span>
			</label>
			<div class="col-sm-8">
				<select class="form-control">
					<option value="">沉降</option>
					<option value="">降水</option>
					<option value="">轴力</option>
					<option value="">测斜</option>
				</select>
			</div>
		  		
		</div>
		 -->
		<div class="form-group" >
			 <label class="col-sm-3 control-label no-padding-right">
				导入文件：<span class="required-indicator">*</span>
			</label>
			<div class="col-sm-8">
				<input class="form-control"  type="file" datatype="*"   nullmsg="请选择文件">
			</div>
		</div>
		
		<!-- 
		<div class="form-group" >
			 <label class="col-sm-3 control-label no-padding-right">
				模板下载：<span class="required-indicator">*</span>
			</label>
			<div class="col-sm-8">
				<a href="template/基坑施工测量2011-12-10_垂直位移_已整理.xls">垂直位移</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="template/基坑施工测量2011-12-10_水平位移_已整理.xls">水平位移</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="template/基坑施工测斜12-10_已整理.xls">测斜</a>
			</div>
		</div>
		 -->
		<div class="form-group" >
			 <label class="col-sm-3 control-label no-padding-right">
				是否覆盖原数据：<span class="required-indicator">*</span>
			</label>
			<div class="col-sm-8">
				 &nbsp;<input  name="isCover" type="radio" value="1">是 &nbsp; &nbsp;
				<input  name="isCover" type="radio" value="0" checked="checked">否
			</div>
		  		
		</div>
     </form>
  </div>

  <div class="modal-footer">
    <button class="btn btn-success btn-sm" id="addsaveUserInfo">导入</button>
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
var pname = $('#project_name').val();
$('#projctname').val(pname);
var validform;
//点击保存按钮
$(function() {	
	//validform=FormValid.validbyformid(executeFrm);
	
	$("#addsaveUserInfo").click(function() {
	  if(validform.check()){
		if($("#executeFrm").validationButton()) {
			var project_uid = $('#project_uid').val();
			var email = $('#email').val();
			var role = $('#role').val();
			$.ajax({
				url: '${ctx}/project/buProjectUserController?checkEmail',
				data: {"email":email,"project_uid":project_uid},
				cache: false,
				async: false,
				dataType: "json",
				type: 'post',
				success: function(response) {
					if(response.success){
						$.ajax({
							url: '${ctx}/project/buProjectUserController?sendInvite',
							data: {"project_uid":project_uid,"email":email,"project_name":$('#project_name').val(),"role":role},
							cache: false,
							async: false,
							dataType: "json",
							type: 'post',
							success: function(response) {
								if(response.success){
									xAlert("信息提示", "邀请成功，请等待用户确认！", 1);
								}else{
									xAlert("信息提示", "邀请失败，请检查邮箱是否存在！", 1);
								}
							}
						});
					}else{
						xAlert("信息提示", response.msg, 1);
					}
				}
			});
			
		 }
	   }else {
		   return;
	   }
	});
});

</script>
		