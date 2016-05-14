<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<!-- Modal -->
<div class="modal-dialog width-40 height-auto">
  <div class="modal-content" >
   		<div class="widget-header widget-header-large">
   		<div class="widget-toolbar">
			<a href="#" data-action="close" data-dismiss="modal">
				<i class="ace-icon fa fa-times"></i>
				</a>
		</div>
    	<h3 id="myModalLabel" class="blue bigger">邀请用户</h3>
  </div>
  <div class="modal-body">
 	 <form method="post" role="form" class="form-horizontal"  id="executeFrm" >      	
		<div class="form-group" >
			 <label class="col-sm-2 control-label no-padding-right">
				角色：<span class="required-indicator">*</span>
			</label>
			<div class="col-sm-8">
				<select class="form-control" id="role" datatype="*" nullmsg="请选择角色" >
				<!--<option value="项目管理员" selected="selected">项目管理员</option>
					<option value="施工员">施工员</option>
					<option value="监理员">监理员</option>-->
				</select>
			</div>
		  		
		</div>
		<div class="form-group" >
			 <label class="col-sm-2 control-label no-padding-right">
				邮箱：<span class="required-indicator">*</span>
			</label>
			<div class="col-sm-8">
				<input class="form-control"   id="email" datatype="e" nullmsg="请输入邮箱">
			</div>
		  		
		</div>
     </form>
  </div>

  <div class="modal-footer">
    <button class="btn btn-success btn-sm" id="addsaveUserInfo">邀请</button>
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
//点击保存按钮
$(function() {	
	validform=FormValid.validbyformid(executeFrm);
	
	init();
	
	$("#addsaveUserInfo").click(function() {
	  if(validform.check()){
		if($("#executeFrm").validationButton()) {
			var project_uid = $('#project_uid').val();
			var email = $('#email').val();
			var role = $('#role').find("option:selected").val(); 
			var roleName = $('#role').find("option:selected").text();
			var project_role_uid = $('#role').find("option:selected").attr('project_role_uid');
			  $.ajax({
				url: '${ctx}/project/buProjectUserController?checkProjectEmail',
				data: {"email":email,"project_uid":project_uid},
				cache: false,
				async: false,
				dataType: "json",
				type: 'post',
				success: function(response) {
					if(response.success){
						$.ajax({
							url: '${ctx}/project/buProjectUserController?sendInvite',
							data: {"project_uid":project_uid,"email":email,"project_name":$('#project_name').val(),"role":role,"roleName":roleName,"project_role_uid":project_role_uid},
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
			  $("#btnClose").click();
		 }
	   }else {
		   return;
	   }
	});
	
	function init(){
		var project_uid = $('#project_uid').val(); 
		var role = $('#role').val();
		$.ajax({
			url: '${ctx}/project/buProjectUserController?getRoleByProject',
			data: {"project_uid":project_uid},
			cache: false,
			async: false,
			dataType: "json",
			type: 'post',
			success: function(response) {
				
				response.forEach(function(item){
					$("#role").append($("<option project_role_uid="+item.PROJECT_ROLE_UID+" value="+item.PROJECT_ROLE_UID+">"+item.ROLE_NAME+"</option>"));
					console.info(item.ROLE_NAME);
				});
				//<option value="项目管理员" selected="selected">项目管理员</option>
			}
		});
	}
});

</script>
		