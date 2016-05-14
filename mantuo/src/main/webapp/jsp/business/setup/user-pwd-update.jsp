<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>

<%
 String USER_UID =request.getParameter("USER_UID");
 %>
<!-- Modal -->
<div class="modal-dialog width-40 height-auto">
  <div class="modal-content" >
   		<div class="widget-header widget-header-large">
   		<div class="widget-toolbar">
			<a href="#" data-action="close" data-dismiss="modal">
				<i class="ace-icon fa fa-times"></i>
				</a>
		</div>
    	<h3 id="myModalLabel" class="blue bigger">修改密码</h3>
  </div>
  <div class="modal-body">
 	  <form method="post" role="form" class="form-horizontal"  id="executePwd" > 
      	 <input type="hidden" name="USER_UID" fieldname="USER_UID" value="<%=USER_UID %>" /> 
      	  <input type="hidden" id="mimaUpdate" fieldname="XGMIMA" value="YES" />  
	  		 <div class="form-group" >
		  		<label class="col-sm-4 control-label no-padding-right">
				         原密码：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-6">
					<label class="block clearfix">
				     <span class="block input-icon input-icon-right">
					   <input id="oldPwd" type="password"  fieldname="OLDPWD"  ajaxurl="${pageContext.request.contextPath}/commons/SysUserController?checkPwd&USER_UID='<%=USER_UID %>'" 
					   class="form-control" datatype="/^[A-Za-z0-9]+$/" errorMsg="密码只能是数字和字母组成" nullmsg="请输入原密码"  placeholder="" />
					   <i class="ace-icon fa fa-lock"></i>
				    </span>
			        </label>
				</div>
			  </div>
			  <div class ="form-group">
	  		    <label class="col-sm-4 control-label no-padding-right">
				         新密码：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-6">
				<label class="block clearfix">
			     <span class="block input-icon input-icon-right">
				   <input id="userPwd" type="password" class="form-control" fieldname="PWD" datatype="/^[A-Za-z0-9]+$/" errorMsg="密码只能是数字和字母组成" nullmsg="请输入新密码"  placeholder="" />
				   <i class="ace-icon fa fa-lock"></i>
			    </span>
		       </label>
				</div>
			</div>
	  		
			<div class="form-group" >
		  		<label class="col-sm-4 control-label no-padding-right">
				         确定密码：<span class="required-indicator">*</span><font id="showError"  style="color:red;display:none;" >两次输入的密码不一致</font>
				</label>
				<div class="col-sm-6">
				<label class="block clearfix">
			     <span class="block input-icon input-icon-right">
				   <input id="confirmPwd" type="password" class="form-control"  datatype="/^[A-Za-z0-9]+$/" errorMsg="密码只能是数字和字母组成"  nullmsg="请再次输入新密码"  placeholder="" />
				   <i class="ace-icon fa fa-lock"></i>
			    </span>
		       </label>
				</div>
			</div>
     </form>
	</div>
  <div class="modal-footer">
    <button class="btn btn-1 btn-sms" id="updateUserPwd">保存</button>
    <button class="btn btn-2 btn-sms pull-right" id="userPwdClose" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
</div>
</div>

<script 
src="${pageContext.request.contextPath }/assets/sys_resources/js/common/default.js"></script>

<script 
src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"></script>
<script type="text/javascript">
var validform;
var controllername = "${pageContext.request.contextPath }/commons/SysUserController";
//点击保存按钮
$(function() {	
	validform=FormValid.validbyformid(executePwd);
	
	$("#updateUserPwd").click(function(){
	   
		if($("#confirmPwd").val()==$("#userPwd").val()){
		   
	         if(validform.check()){
	         
		        if($("#executePwd").validationButton()) {
		        
		          	//生成json串
		          	var data = Form2Json.formToJSON(executePwd);
		         	//组成保存json串格式
		        	 var data1 = defaultJson.packSaveJson(data);
		        	$.ajax({
			         	url :controllername+"?update",
			          	data : data1,
			        	cache : false,
			        	async :	false,
		           		dataType : "json",  
		        		type : 'post',
			        	success : function(response){
				    	   xAlert("信息提示","修改密码成功",1);
				       	   setTimeout(function(){
				       	   
				      		   window.location.href="${pageContext.request.contextPath }/logout";
				      	   },1000);
					    
			           }
			       });
			
		       }
	         }else {
	           
		      return;
	         }
	    }else{
	    
	    	xAlert("信息提示：","两次输入的密码不一致",1);
	    	return;
	    }
	});
});

</script>
		
		