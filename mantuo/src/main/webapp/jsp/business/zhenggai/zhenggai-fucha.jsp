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
	String ZHENGGAI_UID = request.getParameter("zhenggai_uid");
	String ZG_DAFU_UID = request.getParameter("zg_dafu_uid");
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
    	<h3 id="myModalLabel" class="blue bigger">复查信息</h3>
  </div>
  <div class="modal-body">
	<!--  <form id="queryFormById">
	       <input  type="hidden" name="ZG_DAFU_UID" operation="=" fieldname="ZG_DAFU_UID" value="<%=ZG_DAFU_UID%>"/>    
	       <input type="hidden" name="NEW_Y" operation="=" fieldname="NEW_Y" value="Y">
	  </form> --> 
 	  <form method="post" role="form" class="form-horizontal"  id="executeFrm1" > 
 	   <!-- 获取 项目 的 uid -->
 	     <input  type="hidden" name="ZHENGGAI_UID" operation="=" fieldname="ZHENGGAI_UID" value="<%=ZHENGGAI_UID%>"/>    	
 	     <input  type="hidden" name="ZG_DAFU_UID" operation="=" fieldname="ZG_DAFU_UID" value="<%=ZG_DAFU_UID%>"/>    	
      	<input  type="hidden" id="STATUS" name="STATUS" operation="=" fieldname="STATUS" value="0"/>  
      	 <input id="SPROJECT_UID2" type="hidden" name="SPROJECT_UID2" fieldname="PROJECT_UID"  />
      	 <input  type="hidden"  fieldname="TAGS" operation="=" value="BG"/> 
	  	 <input type="hidden" id="target_uid" fieldname="target_uid"  />
	  	    
			<div id="fuchaModel">
			
			  		<div class="form-group" >
				  		<label class="col-sm-2 control-label no-padding-right">
							复查人：
						</label>
						<div class="col-sm-4">
							 <input id="FUCHA_USER_ID"  type="text" disabled="disabled" readonly="readonly" value="<%=username%>"  fieldname="FUCHA_USER"  class="col-xs-11 col-sm-10"  />
						</div>
				  		<label class="col-sm-2 control-label no-padding-right">
							复查日期：
						</label>
						<div class="col-sm-4">
							 <input id="FUCHA_DATE_ID"  value=""   type="text" class="REPORT_DATE3"   data-date-format="yyyy-mm-dd" fieldname="FUCHA_DATE" style="height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;" class="col-xs-11 col-sm-10"/>
						</div> 
					 </div>	
			    
			        <div class="form-group" >
				  		<label class="col-sm-2 control-label no-padding-right">
							复查描述：<span class="required-indicator">*</span>
						</label>
						<div class="col-sm-10">
							 <textarea id="FUCHA_DESC_ID"  nullmsg="请填写复查描述"  placeholder="请输入复查内容"   class="col-xs-11 col-sm-11"  type="text"  fieldname="FUCHA_DESC" datatype="*" errormsg="" class="col-xs-11 col-sm-10"></textarea>
						</div>				
					</div>	 
			</div>
			
			
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					复查结果：
				</label>
				<div class="col-sm-4"> 
				     <label class="col-sm-5 center"><input id="important_Y2" type="radio" name="important"/>重新整改</label>
					 <label class="col-sm-5 center"><input id="important_N2" type="radio" name="important"  checked="checked"/>整改完成关闭</label> 
					 <input id="FUCHA_JIEGUO_ID" datatype="*" type="hidden"   fieldname="FUCHA_JIEGUO" value="0"/>
				</div>				
			</div>	
			<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					重新整改应完成的日期：
				</label>
				<div class="col-sm-4">
					 <input id="CXZG_DONE_DATE_ID" nullmsg="请填写重新整改应完成的日期"  data-date-format="yyyy-mm-dd"  type="text" class="REPORT_DATE4"  value="" fieldname="CXZG_DONE_DATE" style="height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;" class="col-xs-11 col-sm-11"/>
				</div>				
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
					附件：&nbsp;
				</label>
				<div class="col-sm-10">
					<span class="btn  btn-white btn-default btn-round" id="addFileSpan" onclick="selectFile(this);" file_type="10107" >
						<i class="ace-icon fa fa-upload"></i>
						<span>上传</span>
					</span>
					<table  role="presentation" class="table table-striped">
						<tbody onlyView="true" showType="width:120px;height:120px;quanxian:true;heightss:160px;heights:40px;attr:multi;preview:false" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="10107"></tbody>
					</table>
				</div>
			</div>
     </form>
     
	</div>

  <div class="modal-footer"> 
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
var controlletname="${pageContext.request.contextPath }/zhenggai/pmZgDafuController";

ace.load_ajax_scripts(scripts,function(){  
	var date = new Date().Format("yyyy-MM-dd"); 
	$("#FUCHA_DATE_ID").val(date); 
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
	
	$("#FUCHA_JIEGUO_ID").val("1");
	$("#CXZG_DONE_DATE_ID").attr("disabled",true);
	$("#important_Y2").change(function(){	
		if($("#important_Y2").is(":checked")){
			$("#FUCHA_JIEGUO_ID").val("0");	
			$("#CXZG_DONE_DATE_ID").attr("disabled",false);
			$("#CXZG_DONE_DATE_ID").attr("datatype","*");
		}		
	});  
	$("#important_N2").change(function(){	
		if($("#important_N2").is(":checked")){
			$("#FUCHA_JIEGUO_ID").val("1");	
			$("#CXZG_DONE_DATE_ID").attr("disabled",true);
			$("#CXZG_DONE_DATE_ID").removeAttr("datatype");
			
		}		
	}); 
	 
	$('#SPROJECT_UID2').val($('#project_uid').val()); 
	
	$("#addFuchaDataSubmit").click(function(){ 
		  validform=FormValid.validbyformid(executeFrm1);
		  if(validform.check()){
			var FUCHA_JIEGUO_ID = $("#FUCHA_JIEGUO_ID").val();
			
			if(FUCHA_JIEGUO_ID=='1'){ 
				$("#STATUS").val("1");
			}else{
				$("#STATUS").val("0");
			}
			if($("#executeFrm1").validationButton()) {
				//生成json串
				var data = Form2Json.formToJSON(executeFrm1);
				//组成保存json串格式
				var data1 = defaultJson.packSaveJson(data); 
				$.ajax({
					url :controlletname+"?update",
					data : data1,
					cache : false,
					async :	false,
					dataType : "json",  
					type : 'post',
					success : function(response) {
						xAlert("信息提示","提交成功",1);
						$("#closeFuchaPageSubmit").click();
						 jQuery("#grid_table").jqGrid().trigger("reloadGrid");
				      }
				}); 
			 }
		   }else {
			   return;
		   }
		});
});


function selectFile(obj){
	var targetUid = $('#target_uid').val();
	var file_type = $(obj).attr('file_type');
	setFileData("PM_ZG_DAFU","ZG_DAFU_UID",targetUid,file_type);
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
		