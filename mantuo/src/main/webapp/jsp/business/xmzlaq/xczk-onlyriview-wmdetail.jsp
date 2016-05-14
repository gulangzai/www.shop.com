<%@ page language="java" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/sys_resources/plugins/lightbox/themes/default/jquery.lightbox.css" />

<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>

<%@ page import="com.ccthanking.framework.Constants"%>
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

 String XC_UID = request.getParameter("XC_UID");
 String XS_UID = request.getParameter("XS_UID");
%>
<!-- Modal -->
<div class="modal-dialog width-60 height-auto">
  <div class="modal-content" >
   <div class="widget-header widget-header-large">
   		<div class="widget-toolbar">
			<a href="javascript:void(0)" onclick="guanbi()" data-action="close" data-dismiss="modal">
				<i class="ace-icon fa fa-times"></i>
				</a>
		</div>
    	<h3 id="myModalLabel" class="blue bigger">查看文明施工</h3>
  </div>
  <div class="modal-body">
    
 	  <form method="post" role="form" class="form-horizontal"  id="executeFrmXcJd" > 
 	   <!-- 获取 项目 的 uid -->
 	          	
      	 <input id="XCJDROJECT_UID" type="hidden" name="ROJECT_UID" fieldname="PROJECT_UID"  />
      	  <input  id="XM_PRJ_STRUC_UID"  type="hidden"  fieldname="PRJ_STRUC_UID" value="" />
      	  <%-- 中间表的主键id --%>
      	   <input  id="XIANCHANG_STRUC_UID"  type="hidden"  fieldname="XIANCHANG_STRUC_UID" value="<%=XS_UID%>" />
      	   <input type="hidden"  fieldname="XIANCHANG_UID" value="<%=XC_UID%>" />
	  	  <input type="hidden" id="target_uid" fieldname="target_uid"  />
	  	  <%--判断是否已读的id --%>
	  	  <input type="hidden" fieldname="USER_UID" value="<%=user_uid%>"/>
	  	  <input type="hidden" fieldname="USER_NAME" value="<%=username%>"/>
	  	  <input type="hidden" id="TARGET_CODE" fieldname="TARGET_CODE" value="XCGK">
	  	  <input type="hidden"  fieldname="TARGET_UID" value="<%=XC_UID%>"/>
	  	  <input type="hidden" id="CREATES_USER" fieldname="CREATE_USER"/>	
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					现场状况类型：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <select  datatype="*" disabled="disabled" style="width: 80%;" nullmsg="请选择类型" fieldname="GONGKUANG_TYPE" class="col-xs-11 col-sm-11" >
			        <option value="WM" >文明</option>
			      </select>
				</div>
				<label class="col-sm-2 control-label no-padding-right" >
					现场状态：
				</label>
				<div class="col-sm-4">
				    &nbsp;<input id="status" type="radio" disabled="disabled" name="STATUS" fieldname="STATUS"  value="-1">&nbsp;草稿
					&nbsp;<input id="jddoing" type="radio" disabled="disabled" name="STATUS" fieldname="STATUS"  value="0">&nbsp;解决中
					
					&nbsp;<input id="jdfinish" type="radio" disabled="disabled" name="STATUS" fieldname="STATUS"  value="1" >&nbsp;已解决
				</div>
			</div>

			<div class="form-group" >
			<!--  
	  			<label class="col-sm-2 control-label no-padding-right" >
					施工部位：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <input  id="getXMJGMC" disabled="disabled" style="width: 80%;" class="col-xs-11 col-sm-11" datatype="*" nullmsg="请选择项目现场的施工部位"  nullmsg="请选择现场施工部位"  placeholder="请选择项目现场的施工部位" type="text"  fieldname="STRUC_NAME" />
				</div>
				-->
				<label class="col-sm-2 control-label no-padding-right" >
					要求解决日期：
				</label>
				<div class="col-sm-4">
				 <input  id="FINISH_DATE" disabled="disabled" style="width: 80%;" class="col-xs-11 col-sm-11" data-date-format="yyyy-mm-dd"   nullmsg="请选择要求解决日期"   nullmsg="请选择要求解决日期"  placeholder="请选择要求解决日期" type="text"  fieldname="FINISH_DATE" />
				</div>
				<label class="col-sm-2 control-label no-padding-right" >
					严重性：
				</label>
				<div class="col-sm-4">
					 <select class="col-sm-4" id="SERIOUSNESS" disabled="disabled"  nullmsg = "请选择严重性"  placeholder="请选择严重性" type="text"  fieldname="SERIOUSNESS">
					 	
					 	<option  value="1">一般</option>
					 	<option value="2">较严重</option>
					 	<option  value="3" >严重</option>
					 </select>
				</div>
			</div>
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					标题：
				</label>
				<div class="col-sm-10">
					 <textarea id="xcjdContent" disabled="disabled" autofocus="autofocus" datatype="*" nullmsg="请输入标题"  placeholder="请输入标题" rows="4" type="text"  fieldname="JINDU" class="col-xs-11 col-sm-11" ></textarea>
				</div>
			</div>
			
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					状况描述：
				</label>
				<div class="col-sm-10">
					 <textarea id="xcjdms" disabled="disabled"  autofocus="autofocus" datatype="*" nullmsg="请输入状况描述"  placeholder="请输入状况描述" rows="4" type="text"  fieldname="DESCRIPTION" class="col-xs-11 col-sm-11" ></textarea>
				</div>
			</div>
            <div class="form-group">
            <label class="col-sm-2 control-label no-padding-right" >
					完成日期：
				</label>
				<div class="col-sm-4">
				 <input  id="CLOSE_DATE" disabled="disabled" style="width: 80%;" class="col-xs-11 col-sm-11" data-date-format="yyyy-mm-dd"   nullmsg="请选择要求完成日期"   nullmsg="请选择完成日期"  placeholder="请选择完成日期" type="text"  fieldname="CLOSE_DATE" />
				</div>
			</div>
     <%--文件类型及显示 上传文件 --%>
		<div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
					现场进度状况：&nbsp;
				</label>
				
				<div class="col-sm-9">
					
					<table  role="presentation" class="table table-striped">
						<tbody onlyView="true" class="files showFileTab" showType="width:120px;height:120px;quanxian:false;heightss:160px;heights:40px;del:false;attr:multi;preview:false;" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="10010" fjlb="media"></tbody>
					</table>
				</div>
		</div>
     </form>
     
	</div>

  <div class="modal-footer">
    <button id="btnClose123"  class="btn btn-2 btn-sms" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
</div>
</div>
<div id="XMZK-XMMC" class="modal"></div>
<jsp:include page="/jsp/file_upload/fileupload_util.jsp" flush="true" />	
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/plugins/lightbox/jquery.min.js"></script>		
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/plugins/lightbox/jquery.lightbox.min.js"></script>
<script type="text/javascript">
var validform;
var controlletnameUrl="${pageContext.request.contextPath }/pmxianchang/pmXianchangController";
var control="${pageContext.request.contextPath }/loguserread/logUserReadController";
//点击保存按钮
$(function() {	
	DatePicker.datepicker("#CLOSE_DATE");
	  initFormValue();
	  select();
	  queryFileData('PM_XIANCHANG','<%=XC_UID%>');
	  $("#btnClose123").click(function(){
		  _reload();
	  });	   
});
function guanbi(){
	$("#btnClose123").click();
	_reload();
}
function select(){
	  var xian_uid="<%=XC_UID%>";
	  var create_uid=$("#CREATES_USER").val();
     //alert(create_uid);	  
	  $('#XCJDROJECT_UID').val($('#project_uid').val());
	  var PROJECT_UID=$("#XCJDROJECT_UID").val();
	  var TARGET_CODE=$("#TARGET_CODE").val();
	  var user_uid="<%=user_uid%>";
	  var xians_uid="";
	  var projects_uid="";
	  var targets_code="";
	  var users_uid="";
	  var date1="";
	  $.ajax({
			url :control+"?queryid",
			data : {"xian_uid":xian_uid,"PROJECT_UID":PROJECT_UID,"TARGET_CODE":TARGET_CODE,"user_uid":user_uid},
			cache : false,
			async :	false,
			dataType : "json",  
			type : 'post',
			success : function(response) {	
				if(response.msg=="0")
				{
					if(create_uid!=user_uid)
					{
						read();
					}
				}
				
				
	}
	});
}

function read(){
	
	$('#XCJDROJECT_UID').val($('#project_uid').val());
	var data = Form2Json.formToJSON(executeFrmXcJd);
	//组成保存json串格式
	var data1 = defaultJson.packSaveJson(data);
	$.ajax({
		url :control+"?insert",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
	});

}
$(function() {	

	//删除多余的 div 避免弹出图片时出现多层
	var div1 = $(".jquery-lightbox-overlay").eq(0);
	var div2 = $(".jquery-lightbox-move").eq(0);

	$(".jquery-lightbox-overlay").remove();
	$(".jquery-lightbox-move").remove();
	$("body").append(div1);
	$("body").append(div2);
});
(function () {
    var v17 = $.noConflict(true);
    window.$.v17 = v17;
 })();

(function ($) {
 	$('.lightbox').lightbox();
 })(window.$.v17);
function initFormValue(){
	  
       
	   var xc_uid = "<%=XC_UID%>";
	   var qystatus = "";
		$.ajax({
				url :controlletnameUrl+"?queryXcQK",
				data : {"XC_UID":xc_uid},
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(result) {
					if(result.obj.listxx!=null && result.obj.listxx!=""){
							$(result.obj.listxx).each(function(index,ele){
								qystatus = ele.STATUS;
								
								//施工部位（结构表中主键uid和 结构的名称）
								$("#XCJDROJECT_UID").val(ele.PROJECT_UID);
								$("#XM_PRJ_STRUC_UID").val(ele.PRJ_STRUC_UID);
								$("#getXMJGMC").val(ele.STRUC_NAME);
								$("#xcjdContent").html(ele.JINDU);
								$("#xcjdms").html(ele.DESCRIPTION);
								$("#FINISH_DATE").val(ele.FINISH_DATE);
								$("#SERIOUSNESS").val(ele.SERIOUSNESS);
								$("#CLOSE_DATE").val(ele.CLOSE_DATE);
								$("#CREATES_USER").val(ele.CREATE_USER);
							});
						}
					
					if(qystatus== "0"){
						$("#jddoing").attr("checked","checked");
					 }else if(qystatus== "1") {
						$("#jdfinish").attr("checked","checked");
					}else if(qystatus=="-1"){
						$("#status").attr("checked","checked");
					}
					if(qystatus=="1"||qystatus=="0"){
						
						$("#executeFrmXcJd span").hide();
					}
					
			      }
			});
	
}
//图片放大
$(document).ready(function(){
	$(".template-download img").click(function(){
	    if($(this).hasClass("fd")){
	        $(this).removeClass("fd");
	    }else{
	        $(this).addClass("fd");
	    }
	})
})
</script>