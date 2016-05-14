<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctx_site" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>
<c:set var="datePattern"><fmt:message key="date.format"/></c:set>


<div class="modal-dialog width-auto height-auto">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="MessageModalTitle">提示信息</h3>
  </div>
  <div class="modal-body" id="MessageModalContent"> 
                               请填写用户名：<input type="text" name="username_reset" value="">  
                               <br>
         <button onclick="sendResetMail()">点击发送验证到邮箱</button>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
</div>


<script>
function sendResetMail(){
	var username = $("input[name='username_reset']").val();
	
	if(username==''){ 
		//xAlert("信息提示：","请填写用户名！",1);
		alert(username);
		return;
	}
	$.ajax({
		type: "POST",
		cache:false,
		async:false,
		ifModified:true,
		url: '${pageContext.request.contextPath}/commons/SysUserController?sendResetPasswordMail',
		data: {"username":username},
		success: function(msg){
			var result = eval("(" + msg + ")");
			if(result.success){
				$(".btn").click();
				//xAlert("信息提示",result.msg,1); 
				alert(result.msg);
				return;
			    //windows.location.href="/url" 当前页面打开URL页面
				//window.location.href='${pageContext.request.contextPath }'+result.obj.url;
	   		}else{
	   			alert(result.msg); 
		   	}
			return false;
	   }
	});
	$("input[name='username_reset']").val("");
}
</script>