<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctx_site" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>
<c:set var="datePattern"><fmt:message key="date.format"/></c:set>

<%@ include file="/jsp/framework/common/head.jsp"%> 
<%@ taglib uri= "/tld/base.tld" prefix="app" %>

<%
   String USER_UID = request.getParameter("user_uid");
   String timestamp = request.getParameter("timestamp");
%>
<title>用户重置密码</title>
<style>
.padding{
margin:1px 1px 1px 1px ;
cursor: pointer;
}
</style>
 </head>
<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
<app:base/>
<body class="login-layout light-login">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center"> 
							</div>
							<div class="space-6"></div>
							<div class="position-relative">
								<div id="signup-box" class="signup-box widget-box no-border visible">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header green lighter bigger">
												<i class="ace-icon fa fa-users blue"></i>
												用户重置密码
											</h4>
											<div class="space-6">
											   
											</div>
											<form method="post" role="form" class="form-horizontal"  id="executeFrm" > 
											   <input type="hidden"   id="xmShowTP" fieldname="USER_UID" value="<%=USER_UID%>" />
											   <input type="hidden" id="timestamp" fieldname="timestamp" value="<%=timestamp%>"/>
												<fieldset>  
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input id="password1" type="password" class="form-control" fieldname="PWD1" datatype="/^[A-Za-z0-9]+$/" errorMsg="密码只能是数字和字母组成" nullmsg="请输入密码"  placeholder="请输入密码" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input id="password2" type="password" class="form-control" fieldname="PWD" datatype="/^[A-Za-z0-9]+$/" errorMsg="密码只能是数字和字母组成" nullmsg="请再次输入密码"  placeholder="请再次输入密码" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>
													<div class="space-24"></div>
													<div class="clearfix">
                                                        <button type="reset" class="width-30 pull-left btn btn-sm">
															<i class="ace-icon fa fa-refresh"></i>
															<span class="bigger-110">重置</span>
														</button>
														<button type="button" id="saveBtn" class="width-30 pull-right btn btn-sm btn-success">
															<span class="bigger-110">确定</span>

															<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
														</button>
													</div>
												</fieldset>
											</form>
										</div>

									</div><!-- /.widget-body -->
								</div><!-- /.signup-box -->
							</div><!-- /.position-relative -->
 
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->
	
</body>
<jsp:include page="/jsp/file_upload/showPictureorUser.jsp" flush="true" />	
<script type="text/javascript">
		var validform;
		var scripts = [null];
		//对应后台Controller
		var controllername = "${pageContext.request.contextPath }/commons/SysUserController";
		//页面加载自动执行此函数
		ace.load_ajax_scripts(scripts, function() {
			init();
		});
	
		function init(){
			
			
			/**点击 注册按钮 再次判断 之前的登录名及邮箱是否验证通过**/
			$('#saveBtn').click(function() { 
				validform = FormValid.validbyformid(executeFrm); 
				var password1 = $("#password1").val();
				var password2 = $("#password2").val();
				if(password1!=password2){
					xAlert("信息提示：","两次密码填写信息不一致，请检查！",1);
					return;
				}
				 if (validform.check()) {
					bootbox.confirm("确认重置密码吗？", function(result) {
						if (result) {
							resetPassword();
						} else {
							return;
						}
					});
				 }else {
					xAlert("信息提示：","填写信息有误，请检查！",1);
					return;
				} 
			}); 
		}

		function resetPassword() {
			//生成json串 executeFrm 为表单id
			var data = Form2Json.formToJSON(executeFrm);
			//组成保存json串格式
			var data1 = defaultJson.packSaveJson(data);
			$.ajax({
				url: controllername+'?resetPassword',
				data: data1,
				cache: false,
				async: false,
				dataType: "json",
				type: 'post',
				success: function(response) {
					if(response.success){
						xAlert("信息提示", response.msg, 1);
						setTimeout(function() {
							window.location.href="${pageContext.request.contextPath}/index.jsp";  
			             }, 1000);
					}else{
						if(response.msg!=''){
						   xAlert("信息提示",response.msg, 1);
						}else{
						   xAlert("信息提示", "密码重置失败", 1);
						} 
					}
				
				}
			});
		} 
</script>	
</html>