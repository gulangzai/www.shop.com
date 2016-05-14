<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/mycss2(table).css" type="text/css"></link>

<%@ page language="java" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/sys_resources/plugins/lightbox/themes/default/jquery.lightbox.css" />

<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%
    String PROJECT_UID = request.getParameter("PROJECT_UID");
	String log_uid = request.getParameter("log_uid");
%>
<style type="text/css">

</style>
<!-- Modal -->
<div class="modal-dialog width-60 height-auto">
  <div class="modal-content" >
   		<div class="widget-header widget-header-large">
   		<div class="widget-toolbar">
			<a href="#" data-action="close" data-dismiss="modal">
				<i class="ace-icon fa fa-times"></i>
				</a>
		</div>
    	<h3 id="myModalLabel" class="blue bigger">查看设备材料信息</h3>
  </div>
  
  
  
  <div class="modal-body">
       <form method="post" role="form" class="form-horizontal"  id="executeFrm" > 
 	   <!-- 获取 项目 的 uid -->    	
      	 <input id="PROJECT_UID" type="hidden" name="PROJECT_UID" fieldname="PROJECT_UID" value="<%=PROJECT_UID%>" />
      	  <input id="CAILIAO_UID" type="hidden" name="CAILIAO_UID" fieldname="CAILIAO_UID" value="<%=log_uid%>" />
      	  <input type="hidden" id="target_uid" fieldname="target_uid"  />
	  		  	 	
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					设备材料名称：
				</label>
				<div class="col-sm-10">
					 <input disabled="disabled"   type="text" name="CAILIAO_NAME" id="CAILIAO_NAME" fieldname="CAILIAO_NAME" class="col-xs-11 col-sm-11"/>
				</div>	  		
			</div>
            
	  		<div class="form-group" >	
		  		<label class="col-sm-2 control-label no-padding-right">
					品牌：
				</label>
				<div class="col-sm-4">
					 <input class="col-xs-11 col-sm-10" disabled="disabled"   type="text"  id="CAILIAO_PINPAI" name="CAILIAO_PINPAI" fieldname="CAILIAO_PINPAI"/>
				</div>
	  		    <label class="col-sm-2 control-label no-padding-right">
					生产商：
				</label>
				<div class="col-sm-4">
					 <input class="col-xs-11 col-sm-10" disabled="disabled"    type="text"  id="CAILIAO_CHANGSHANG" name= "CAILIAO_CHANGSHANG" fieldname="CAILIAO_CHANGSHANG"/>
				</div>
			</div>
			     <div class="form-group" >	
		  		<label class="col-sm-2 control-label no-padding-right">
					产地：
				</label>
				<div class="col-sm-4">
					 <input class="col-xs-11 col-sm-10" disabled="disabled"    type="text"  id="CAILIAO_CHANDI" name="CAILIAO_CHANDI" fieldname="CAILIAO_CHANDI"/>
				</div>
	  		    <label class="col-sm-2 control-label no-padding-right">
					规格型号：
				</label>
				<div class="col-sm-4">
					 <input class="col-xs-11 col-sm-10" disabled="disabled"   type="text"  id="CAILIAO_XINGHAO" name="CAILIAO_UNIT" fieldname="CAILIAO_XINGHAO"/>
				</div>
			</div>
			<div class="form-group" >	
		  		<label class="col-sm-2 control-label no-padding-right">
					单位：
				</label>
				<div class="col-sm-4">
					 <input class="col-xs-11 col-sm-10" disabled="disabled"   type="text"  id="CAILIAO_UNIT" name="CAILIAO_UNIT" fieldname="CAILIAO_UNIT" />
				</div>
				<label class="col-sm-2 control-label no-padding-right">
					数量：
				</label>
				<div class="col-sm-4">
					 <input class="col-xs-11 col-sm-10" disabled="disabled"   type="text"  id="CAILIAO_NUMS" name="CAILIAO_NUMS" fieldname="CAILIAO_NUMS"/>
				</div>

			</div>
            <div class="form-group">
             	<label class="col-sm-2 control-label no-padding-right">
					价格：
				</label>
				<div class="col-sm-4">
					 <input class="col-xs-11 col-sm-10" disabled="disabled"   type="text"  id="CAILIAO_PRICE" name="CAILIAO_PRICE" fieldname="CAILIAO_PRICE"/>
				</div>
            </div>
	  	
	  		
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right">
					进场日期：
				</label>
				<div class="col-sm-4">
					 <input class="col-xs-11 col-sm-10" disabled="disabled"  data-date-format="yyyy-mm-dd"   type="text" id="CAILIAO_JCRQ" name="CAILIAO_JCRQ" fieldname="CAILIAO_JCRQ" />
				</div>
				<label class="col-sm-2 control-label no-padding-right">
					验收人：
				</label>
				<div class="col-sm-4">
					 <input class="col-xs-11 col-sm-10" disabled="disabled"   type="text"  id="YANSHOU_REN" name="YANSHOU_REN" fieldname="YANSHOU_REN"/>
				</div>

			</div>
				
			
	  		<div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
					附件：&nbsp;
				</label>
				<div class="col-sm-10">
					<!-- 
					<span class="btn  btn-white btn-default btn-round" id="addFileSpan" onclick="selectFile(this);" file_type="10501" >
						<i class="ace-icon fa fa-upload"></i>
						<span>附件上传</span>
					</span>

					<div calss="col-sm-5">
					<img width="150" height="120" src="" id="image" >
					</div>
-->
					<table  role="presentation" class="table table-striped">
						<tbody onlyView="true" class="files showFileTab" showType="width:120px;height:120px;quanxian:false;heightss:160px;heights:40px;del:false;attr:multi;preview:false;" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="10501"></tbody>
					</table>
                    
				</div>
			</div>	
			
		
     </form>
   
	</div>
  <div class="modal-footer">
    <!--<button class="btn btn-1 btn-sms" id="addsaveUserInfo">保存</button>-->
    <button class="btn btn-2 btn-sms pull-right" id="btnClose" data-dismiss="modal" aria-hidden="true">关闭</button>
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
var controlletname="${pageContext.request.contextPath}/pmcailiao/pmCailiaoController";


$(function() {	
	var pId="<%=PROJECT_UID%>";
	setFormValues(pId);
	//删除多余的 div 避免弹出图片时出现多层
	var div1 = $(".jquery-lightbox-overlay").eq(0);
	var div2 = $(".jquery-lightbox-move").eq(0);

	$(".jquery-lightbox-overlay").remove();
	$(".jquery-lightbox-move").remove();
	$("body").append(div1);
	$("body").append(div2);
});
//点击保存按钮
$(function() {	
init();
DatePicker.datepickerid("#CAILIAO_JCRQ");
var xmUid = "<%=PROJECT_UID%>";
$("#PROJECT_UID").val(xmUid);
	validform=FormValid.validbyformid(executeFrm);
	$("#addsaveUserInfo").click(function() {
	  if(validform.check()){
		if($("#executeFrm").validationButton()) {
			//生成json串
			var data = Form2Json.formToJSON(executeFrm);
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
					xAlert("信息提示","更新成功",1);
					$("#btnClose").click();
					jQuery("#grid_table").jqGrid().trigger("reloadGrid");					
			      }
			});		
		 }
	   }else {
		   return;
	   }
	});
});

(function () {
    var v17 = $.noConflict(true);
    window.$.v17 = v17;
 })();

(function ($) {
 	$('.lightbox').lightbox();
 })(window.$.v17);


function init(){
	
	setFormValues();
	
	
}

	


function setFormValues(){
	
	var data = combineQuery.getQueryCombineData(queryFormById,null,null);
	var data1 = {
		msg : data
	};
	$.ajax({
		url : controlletname+"?query",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var resultobj = defaultJson.dealResultJson(response.msg);
			$("#executeFrm").setFormValues(resultobj);	
			
			queryFileData('PM_CAILIAO',resultobj.CAILIAO_UID);
		}
	});
	
}

//附件上传
function selectFile(obj){
	
	var targetUid = $('#target_uid').val();
	var file_type = $(obj).attr('file_type');
	setFileData("PM_CAILIAO","CAILIAO_UID",targetUid,file_type);
    document.getElementById("fileupload_btn").click();	
	
}


</script>
<form method="post" id="queryFormById"  >
   <table class="B-table" width="100%">
   <!--可以再此处加入hidden域作为过滤条件 -->
   	<TR  style="display:none;">
     	<TD class="right-border bottom-border">
      	<INPUT type="text"  kind="text" id="CAILIAO_UID"  fieldname="CAILIAO_UID" value="<%=log_uid%>" operation="="/>
      	</TD>
		<TD class="right-border bottom-border">
		</TD>
     </TR>
   </table>
</form>	
		
		