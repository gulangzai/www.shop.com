<!DOCTYPE html>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>

<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<title>用户信息修改</title>
<%
 User user = RestContext.getCurrentUser();
%>
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
									<i class="ace-icon fa fa-leaf green"></i>
									<span class="red">cpmi</span>
									<span class="grey" id="id-text2">用户信息修改</span>
								</h1>
							</div>
							<div class="space-6"></div>
							<div class="position-relative">
								<div id="signup-box" class="signup-box widget-box no-border visible">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header green lighter bigger">
												<i class="ace-icon fa fa-users blue"></i>
												用户信息修改
											</h4>
											<div class="space-6">
											   
											</div>
											<form method="post" role="form" class="form-horizontal"  id="executeFrm" > 
											 <input type="hidden" id="target_uid" fieldname="target_uid"/>  
											  <input type="hidden" value="1" id="xmShowTP"/>
											  <input type="hidden" fieldname="USER_UID" value="<%=user.getIdCard() %>"/>
											    <input type="hidden" id="mimaUpdate" fieldname="XGMIMA" value="NO" />  
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
															<input id="LOGON_NAME" type="text" class="form-control" fieldname="LOGON_NAME" datatype="*" nullmsg="请输入登录名"
															  ajaxurl="${pageContext.request.contextPath}/commons/SysUserController?checkName&USER_UID='<%=user.getIdCard() %>'" placeholder="登录名" />
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" fieldname="USER_NAME" datatype="*" nullmsg="请输入用户名" placeholder="用户名" readonly="readonly" />
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>
													
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input id="XZEMAIL" type="text" class="form-control" fieldname="EMAIL" datatype="e" errormsg="您输入的邮箱格式不正确" nullmsg="请输入邮箱"
															 ajaxurl="${pageContext.request.contextPath}/commons/SysUserController?check&USER_UID='<%=user.getIdCard() %>'" placeholder="邮箱" />
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
													<div class="clearfix"><%--
                                                        <button type="reset" class="width-30 pull-left btn btn-sm">
															<i class="ace-icon fa fa-refresh btn-red"></i>
															<span class="bigger-110">刷新</span>
														</button>
														--%>
														 <button type="button" id="updatePwd" class="width-30 pull-left btn btn-sm btn-success">
															<span class="bigger-110">修改密码</span>
														</button>
														
														<button type="button" id="saveBtn" class="width-30 pull-right btn btn-sm btn-success">
															<span class="bigger-110">保存</span>

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
<div id="user-pwdupdate" class="modal"></div>   
</body>
<jsp:include page="/jsp/file_upload/showPictureorUser.jsp" flush="true" />	
<script type="text/javascript">
		var validform;
		var scripts = [null];
		//对应后台Controller
		var controllername = "${pageContext.request.contextPath }/commons/SysUserController";
		//页面加载自动执行此函数 并获取当前用户的id
		ace.load_ajax_scripts(scripts, function() {
			var userId = "<%= user.getIdCard()%>";
			initUserInfo();
			init();
			queryFileData('SYS_USER',userId);
		});
	
		function init(){
			validform = FormValid.validbyformid(executeFrm);
			/**点击 注册按钮 再次判断 之前的登录名及邮箱是否验证通过**/
			$('#saveBtn').click(function() {
				 if (validform.check() && $("#LOGON_NAME").attr("class").indexOf("Validform_error") == -1 
						&& $("#XZEMAIL").attr("class").indexOf("Validform_error") == -1){
					bootbox.confirm("确认保存数据吗？", function(result) {
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
			
			$("#updatePwd").click(function (){
			   $('#updatePwd').attr("data-target","user-pwdupdate");
					$('#user-pwdupdate').removeData("bs.modal");
					$("#user-pwdupdate").empty();
					$('#user-pwdupdate').modal({
						backdrop: 'static'
					});
					$.get("${ctx}/jsp/business/setup/user-pwd-update.jsp?USER_UID="+'<%= user.getIdCard()%>',function(data) {
						$("#user-pwdupdate").empty();
						$("#user-pwdupdate").html("");
						$("#user-pwdupdate").html(data);
					});
				
			});
			
		}

		function addUser() {
			//生成json串 executeFrm 为表单id
			var data = Form2Json.formToJSON(executeFrm);
			//组成保存json串格式
			var data1 = defaultJson.packSaveJson(data);
			$.ajax({
				url: controllername+'?update',
				data: data1,
				cache: false,
				async: false,
				dataType: "json",
				type: 'post',
				success: function(response) {
					xAlert("信息提示", "修改用户信息成功", 1);
					setTimeout(function() {
						initUserInfo();
		             }, 1000);
				}
			});
		}

		function initUserInfo()	{
			var userId =<%= user.getIdCard()%>;
			$.ajax({
					url : controllername+"?query",
					data : {"userId":userId},
					cache : false,
					async :	false,
					dataType : "json",  
					type : 'post',
					success : function(response) {
						var resultobj = defaultJson.dealResultJson(response.msg);
						//alert(resultobj.PWD)
						queryFileData('SYS_USER',resultobj.USER_UID);
						$("#executeFrm").setFormValues(resultobj);
						//$("#userPwd").val(resultobj.PWD);
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