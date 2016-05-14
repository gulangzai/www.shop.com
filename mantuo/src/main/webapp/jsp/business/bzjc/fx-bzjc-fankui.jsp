<%@ page language="java" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/sys_resources/plugins/lightbox/themes/default/jquery.lightbox.css" />
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ page import="com.ccthanking.framework.common.User"%>
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
	String BZJC_UID = request.getParameter("BZJC_UID"); 
%>
<!-- Modal -->
<div class="modal-dialog width-60 height-auto">
  <div class="modal-content" >
   		<div class="widget-header widget-header-large">
   		<div class="widget-toolbar">
			<a href="#" data-action="close" data-dismiss="modal">
				<i class="ace-icon fa fa-times"></i>
				</a>
		</div>
    	<h3 id="myModalLabel" class="blue bigger">反馈信息</h3>
  </div>
  <div class="modal-body">
	<!--  <form id="queryFormById">
	        <input type="hidden" name="NEW_Y" operation="=" fieldname="NEW_Y" value="Y">
	  </form> --> 
 	  <form method="post" role="form" class="form-horizontal"  id="executeFrm1" > 
 	   <!-- 获取 项目 的 uid -->
 	     <input  type="hidden" name="BZJC_UID" operation="=" fieldname="BZJC_UID" value="<%=BZJC_UID%>"/>    	   	
         <input  type="hidden" id="STATUS" name="STATUS" operation="=" fieldname="STATUS" value="0"/>  
      	 <input id="SPROJECT_UID2" type="hidden" name="SPROJECT_UID2" fieldname="PROJECT_UID"  />
      	 <input  type="hidden"  fieldname="TAGS" operation="=" value="BG"/> 
	  	 <input type="hidden" id="target_uid" fieldname="target_uid"  />
	  	     
			<div class="form-group" >	
				<label class="col-sm-2 control-label no-padding-right" >
					实际完成时间：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				    <input   data-date-format="yyyy-mm-dd"  class="col-xs-11 col-sm-10 REPORT_DATE4" id="JC_END_DATE" disabled="disabled" datatype="*" nullmsg="请填写实际完成时间"    placeholder="请填写实际完成时间" type="text"  fieldname="JC_END_DATE" />
				</div>  
			</div>
		
			
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					检查结果：<span class="required-indicator">*</span>
				</label>
			    <div class="col-sm-10">
				   <input  class="col-sm-11 col-xs-11" id="JC_RESULT"  datatype="*" nullmsg="请填写检查结果"    placeholder="请填写检查结果" type="text"  fieldname="JC_RESULT" />
				</div>
			</div>
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					备注：<span class="required-indicator">*</span>
				</label>
				 <div class="col-sm-10">
					 <textarea id="DESCRIPTION"   class="col-xs-11 col-sm-11"  type="text"  fieldname="DESCRIPTION" datatype="*" errormsg="" ignore="ignore"  ></textarea>
				 </div>
			</div>
			 
			<div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
					附件：&nbsp;
				</label>
				<div class="col-sm-10">
					<span class="btn  btn-white btn-default btn-round" id="addFileSpan" onclick="selectFile(this);" file_type="10901" >
						<i class="ace-icon fa fa-upload"></i>
						<span>上传</span>
					</span>
					<table  role="presentation" class="table table-striped">
						<tbody onlyView="true" showType="width:120px;height:120px;quanxian:true;heightss:160px;heights:40px;attr:multi;preview:false" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="10901"></tbody>
					</table>
				</div>
			</div>
     </form>
     
	</div>

  <div class="modal-footer"> 
    <button id="addZGDataAdd" class="btn btn-1 btn-sms btn-round" type="button">&nbsp;保存&nbsp;</button>
    <button id="addFuchaDataSubmit" class="btn btn-1 btn-sms btn-round" type="button">&nbsp;提交&nbsp;</button>
    <button id="closeFuchaPageSubmit"  class="btn btn-2 btn-sms pull-right btn-round" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
</div>
</div>
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
var controlletname="${pageContext.request.contextPath }/pmbzjc/pmBzjcController";

ace.load_ajax_scripts(scripts,function(){  
	var date = new Date().Format("yyyy-MM-dd"); 
	$("#JC_END_DATE").val(date); 
	DatePicker.datepicker(".REPORT_DATE1");
	DatePicker.datepicker(".REPORT_DATE2");
	DatePicker.datepicker(".REPORT_DATE3");
	DatePicker.datepicker(".REPORT_DATE4");
});


Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

//点击保存按钮
$(function() {	 
	//删除多余的 div 避免弹出图片时出现多层
	var div1 = $(".jquery-lightbox-overlay").eq(0);
	var div2 = $(".jquery-lightbox-move").eq(0); 
	$(".jquery-lightbox-overlay").remove();
	$(".jquery-lightbox-move").remove();
	$("body").append(div1);
	$("body").append(div2);
	
	//$("#FUCHA_JIEGUO_ID").val("1");
	$("#CXZG_DONE_DATE_ID").attr("disabled",true);
	    
	$('#SPROJECT_UID2').val($('#project_uid').val()); 
	
	$("#addZGDataAdd").click(function(){ 
		/*if(FUCHA_JIEGUO_ID=='1'){ 
			$("#STATUS").val("1");
		}else{
			$("#STATUS").val("0");
		}*/
		fankui("保存");
	});
	
	$("#addFuchaDataSubmit").click(function(){  
		fankui("提交");
	}); 
	
	function fankui(message){
		 validform=FormValid.validbyformid(executeFrm1);
		  if(validform.check()){
			//var FUCHA_JIEGUO_ID = $("#FUCHA_JIEGUO_ID").val(); 
			if($("#executeFrm1").validationButton()) {
				//生成json串
				var data = Form2Json.formToJSON(executeFrm1);
				//组成保存json串格式
				var data1 = defaultJson.packSaveJson(data); 
				$.ajax({
					url :controlletname+"?fankui",
					data : data1,
					cache : false,
					async :	false,
					dataType : "json",  
					type : 'post',
					success : function(response) {
						xAlert("信息提示",message+"成功",1);
						$("#closeFuchaPageSubmit").click();
						jQuery("#grid_table").jqGrid().trigger("reloadGrid");
				      }
				}); 
			 }
		   }else {
			   return;
		   }
	}
});


function selectFile(obj){
	var targetUid = $('#target_uid').val();
	var file_type = $(obj).attr('file_type');
	setFileData("PM_BZJC","BZJC_UID",targetUid,file_type);
    document.getElementById("fileupload_btn").click();	 
}


(function () {
    var v17 = $.noConflict(true);
    window.$.v17 = v17;
})();

(function ($) {
 	$('.lightbox').lightbox();
 })(window.$.v17);

</script>
		