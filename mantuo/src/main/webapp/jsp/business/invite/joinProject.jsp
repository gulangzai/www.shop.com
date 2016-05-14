<%@ page language="java" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/sys_resources/plugins/lightbox/themes/default/jquery.lightbox.css" />
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.Constants"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%
	User user = RestContext.getCurrentUser();
	String user_uid = "";
	String username = "";
	if(user!=null){
		user_uid = user.getIdCard();
		username = user.getName();
	}else{
		response.sendRedirect("/"+Constants.APP_NAME);
		return;
	}
	String user_message_uid = request.getParameter("user_message_uid");
	String project_role_uid = request.getParameter("project_role_uid");
	String role = request.getParameter("role");
	String invite_uid  = request.getParameter("invite_uid");
	String project_uid = request.getParameter("project_uid");
	String projectName = request.getParameter("projectName");
	String roleName = request.getParameter("roleName"); 
	String userName = request.getParameter("userName");
%>
<!-- Modal -->
<div class="modal-dialog width-60 height-auto">
  <div class="modal-content" >
   <div class="widget-header widget-header-large">
   		<div class="widget-toolbar">
			<a href="#" data-action="close" id="btnClose"  data-dismiss="modal">
				<i class="ace-icon fa fa-times"></i>
				</a>
		</div>
    	<h3 id="myModalLabel" class="blue bigger">项目邀请信息</h3>
  </div>
  <div class="modal-body">
     <form id="queryFormById">
     	   <input  type="hidden" name="ZHENGGAI_UID" operation="=" fieldname="ZHENGGAI_UID" value=""/>    
           <input  type="hidden" name="PROJECT_UID" operation="=" fieldname="PROJECT_UID" value="<%=project_uid%>"/>    
	  </form>
 	  <form method="post" role="form" class="form-horizontal"  id="zhenggaiDetailForm" > 
 	   <!-- 获取 项目 的 uid -->    	
      	 <input id="ZLROJECT_UID" type="hidden" name="ROJECT_UID" fieldname="PROJECT_UID"  />
      	  <input type="hidden" id="target_uid" fieldname="target_uid"  />
	  	  <input type="hidden" id="STATUS" fieldname="STATUS"  value="0"/>
	  		
	  		<div class="form-group" >
	  		
	  		   <label class="col-sm-2 control-label no-padding-right" >
					邀请人： 
				</label>
				<div class="col-sm-4">
				  <input  class="col-xs-11 col-sm-10" id="ZG_CODE" disabled="disabled" datatype="*" nullmsg="请选择项目现场的施工部位"    placeholder="请选择项目现场的施工部位" type="text"  fieldname="ZG_CODE"  value="<%=userName%>"/>
				</div> 
			</div>
			
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					项目名： 
				</label>
			    <div class="col-sm-4">
				   <input  class="col-xs-11 col-sm-10" id="getXMJGMC"  datatype="*" nullmsg="请先写现场进度"    placeholder="请先写现场进度" type="text"  fieldname="JINDU" value="<%=projectName%>"/>
				</div>
			</div>
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					角色名： 
				</label>
				<div class="col-sm-4">
				 <input class="col-xs-11 col-sm-10" id="getXMJGMC"   datatype="*" nullmsg="请填写施工单位"    placeholder="请填写施工单位" type="text"  fieldname="SGDW" value="<%=roleName%>"/>
				</div>
			</div>  
	</div>
  <div class="modal-footer" style="display:none">
    <button id="addZGDataAdd" onclick="join('yes')" class="btn btn-2 btn-white btn-sms  btn-round">&nbsp;同意&nbsp;</button>  
    <button id="closeZGPageAdd" onclick="join('no')" class="btn btn-2 btn-sms pull-right btn-round">不同意</button>
  </div>
</div>
</div>
<div id="XMZK-XMMC" class="modal"></div>
<jsp:include page="/jsp/file_upload/fileupload_util.jsp" flush="true" />	
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/convertJson.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/combineQuery.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/default.js?version=20131206"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/bootstrap-validation.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/plugins/lightbox/jquery.min.js"></script>		
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/plugins/lightbox/jquery.lightbox.min.js"></script>

<script type="text/javascript">
var validform;
var controlletnameUrlZgAdd="${pageContext.request.contextPath }/zhenggai/pmZhengGaiController";
var scripts=[null];
ace.load_ajax_scripts(scripts,function(){ 
});

//点击保存按钮
$(function() {	
	init(); 
	//删除多余的 div 避免弹出图片时出现多层
	var div1 = $(".jquery-lightbox-overlay").eq(0);
	var div2 = $(".jquery-lightbox-move").eq(0); 
	$(".jquery-lightbox-overlay").remove();
	$(".jquery-lightbox-move").remove();
	$("body").append(div1);
	$("body").append(div2);
	
	DatePicker.datepicker(".REPORT_DATE1"); 
    DatePicker.datepicker(".REPORT_DATE2");   
	validform=FormValid.validbyformid(zhenggaiDetailForm);
	$('#ZLROJECT_UID').val($('#project_uid').val());   
});


function init(){
	var invite_uid = "<%=invite_uid%>";
	$.ajax({
		url :'${ctx}/invite/sysInviteController?querySingle',
		data : {"invite_uid":invite_uid},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
		console.info(response); 
		    var obj = response.obj;
			if(obj.STATUS!=0&&obj.STATUS!=1){ 
				$(".modal-footer").show();
	        }
		}
	}); 
	
	
	//form 下面的所有input，textarea,select禁用
	$("#zhenggaiDetailForm input").attr("disabled","disabled");
	$("textarea").attr("disabled","disabled");
	$("#zhenggaiDetailForm select").attr("disabled","disabled");
} 


//加入项目
function join(isJoin){
	invite_uid="<%=invite_uid%>";
	project_uid= "<%=project_uid%>";
	role="<%=role%>";
	project_role_uid="<%=project_role_uid%>";
	user_message_uid="<%=user_message_uid%>";
	console.log(isJoin+""+project_uid+""+role);
	if(isJoin=='yes'){
		bootbox.confirm("确认加入吗？", function(result) {
			if (result) {						
				$.ajax({
					url :'${ctx}/project/buProjectUserController?join',
					data : {"invite_uid":invite_uid,"PROJECT_UID":project_uid,"role":role,"project_role_uid":project_role_uid,"USER_MESSAGE_UID":user_message_uid,"STATUS":0},
					cache : false,
					async :	false,
					dataType : "json",  
					type : 'post',
					success : function(response) {
						if(response.success){
							xAlert("信息提示","加入成功",1);
						}else{ 
							if(response.msg!=null){
								 xAlert("信息提示",response.msg,1);
							}else{
								xAlert("信息提示","加入失败,请联系管理员",1);
							}  
						}
						 init();
				      }
				}); 
			} else {
				return;
			}
		});
	} else{
		bootbox.confirm("确认不加入吗？", function(result) {
			if (result) {						
				$.ajax({
					url :'${ctx}/project/buProjectUserController?noJoin',
					data : {"invite_uid":invite_uid,"PROJECT_UID":project_uid,"role":role,"USER_MESSAGE_UID":user_message_uid,"STATUS":1},
					cache : false,
					async :	false,
					dataType : "json",  
					type : 'post',
					success : function(response) {
						if(response.success){
							xAlert("信息提示","不加入成功",1);
						}else{
							if(response.msg!=null){
								 xAlert("信息提示",response.msg,1);
							}else{
								xAlert("信息提示","加入失败,请联系管理员",1);
							}  
						}
						 init();
				      }
				}); 
			} else {
				return;
			}
		});
	} 
	$("#btnClose").click();
}

 
 

(function () {
    var v17 = $.noConflict(true);
    window.$.v17 = v17;
 })();

(function ($) {
 	$('.lightbox').lightbox();
 })(window.$.v17);
</script>