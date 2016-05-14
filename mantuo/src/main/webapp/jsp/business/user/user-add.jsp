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
<title>用户注册</title>
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
								<h1>
									<!--  
									<i class="ace-icon fa fa-leaf green"></i>
									<span class="red">cpmi</span>
									<span class="grey" id="id-text2">系统注册</span>
								    -->
								</h1>
							</div>
							<div class="space-6"></div>
							<div class="position-relative">
								<div id="signup-box" class="signup-box widget-box no-border visible">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header green lighter bigger">
												<i class="ace-icon fa fa-users blue"></i>
												新用户注册
											</h4>
											<div class="space-6">
											   
											</div>
											<form method="post" role="form" class="form-horizontal"  id="executeFrm" > 
											 <input type="hidden" id="target_uid" fieldname="target_uid"  />  
											 <input type="hidden" id="zcCode" fieldname="ZCCODE" value="${inviteCode}">
											  <input type="hidden" value="1" id="xmShowTP"  />
												<fieldset>
													<div class="block clearfix" style="border:1px solid #D5D5D5;margin:4px auto;" >
													   <span class="profile-picture padding" onclick="selectFile(this);" file_type="10008">
															    <img id="showPICTURES" src="${pageContext.request.contextPath}/assets/images/userImg/profile-pic.jpg" 
															    alt="Alex's Avatar" class="img-responsive " width="60"  height="55"/>
													   </span>
													   <span class="padding" >
															<i class="ace-icon fa  bigger-100"></i>
										                      <font color="red"> 点击右图上传用户头像</font>     
													   </span>
													 </div>
													 <div>
													  <table  id="sfshowTb" role="presentation" class="table table-striped" style="display: none;">
						                                 <tbody class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="10008"></tbody>
					                                  </table>
													 </div>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input id="LOGON_NAME" type="text" class="form-control" fieldname="LOGON_NAME" datatype="/^[a-zA-Z][a-zA-Z0-9]*$/" nullmsg="请输入登录名"
															  ajaxurl="${pageContext.request.contextPath}/commons/SysUserController?checkName"  placeholder="登录名" />
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" fieldname="USER_NAME" datatype="*" nullmsg="请输入用户名" placeholder="用户名" />
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>
													
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" fieldname="PWD" datatype="/^[A-Za-z0-9]+$/" errorMsg="密码只能是数字和字母组成" nullmsg="请输入密码"  placeholder="密码" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input id="XZEMAIL" type="text" class="form-control" fieldname="EMAIL" datatype="e" errormsg="您输入的邮箱格式不正确" nullmsg="请输入邮箱"
															 ajaxurl="${pageContext.request.contextPath}/commons/SysUserController?check" placeholder="邮箱" />
															<i class="ace-icon fa fa-envelope"></i>
														</span>
													</label>
													
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" fieldname="MOBILE" datatype="m" errormsg="您输入的手机号码格式不正确" nullmsg="请输入手机号码" placeholder="手机" />
															<i class="ace-icon fa fa-mobile"></i>
														</span>
													</label>
													<div class="space-24"></div>
													<div class="clearfix">
                                                        <button type="reset" class="width-30 pull-left btn btn-sm">
															<i class="ace-icon fa fa-refresh"></i>
															<span class="bigger-110">重置</span>
														</button>
														<button type="button" id="saveBtn" class="width-30 pull-right btn btn-sm btn-success">
															<span class="bigger-110">注册</span>

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
			validform = FormValid.validbyformid(executeFrm);
			var table = document.getElementById("sfshowTb");
			
			/**点击 注册按钮 再次判断 之前的登录名及邮箱是否验证通过**/
			$('#saveBtn').click(function() {
			
			 
				 if (validform.check() && $("#LOGON_NAME").attr("class").indexOf("Validform_error") == -1 
						&& $("#XZEMAIL").attr("class").indexOf("Validform_error") == -1) {
					bootbox.confirm("确认注册用户吗？", function(result) {
						if (result) {
							addUser();
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

		function addUser() {
			//生成json串 executeFrm 为表单id
			var data = Form2Json.formToJSON(executeFrm);
			//组成保存json串格式
			var data1 = defaultJson.packSaveJson(data);
			$.ajax({
				url: controllername+'?insert',
				data: data1,
				cache: false,
				async: false,
				dataType: "json",
				type: 'post',
				success: function(response) {
					xAlert("信息提示", "注册成功", 1);
					setTimeout(function() {
						window.location.href="${pageContext.request.contextPath}/index.jsp"; 
						//window.location.href="${pageContext.request.contextPath }/jsp/business/project/project-index.jsp";
						//window.close();
		             }, 1000);
				}
			});
		}
		
/**用户头像上传*/
function selectFile(obj){
	var targetUid = $('#target_uid').val();
	var file_type = $(obj).attr('file_type');
	setFileData("SYS_USER","USER_UID",targetUid,file_type);
    document.getElementById("fileupload_btn").click();
    }

</script>	
</html>