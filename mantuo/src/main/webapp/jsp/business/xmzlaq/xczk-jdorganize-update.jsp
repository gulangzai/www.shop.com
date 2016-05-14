<%@ page language="java" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/sys_resources/plugins/lightbox/themes/default/jquery.lightbox.css" />

<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%
 String XC_UID = request.getParameter("XC_UID");
 String XS_UID = request.getParameter("XS_UID");
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
    	<h3 id="myModalLabel" class="blue bigger">修改进度工况</h3>
  </div>
  <div class="modal-body">
 	  <form method="post" role="form" class="form-horizontal"  id="executeFrmXcJd" > 
 	   <!-- 获取 项目 的 uid -->    	
      	 <input id="XCJDROJECT_UID" type="hidden" name="ROJECT_UID" fieldname="PROJECT_UID"  />
      	  <input  id="XM_PRJ_STRUC_UID"  type="hidden"  fieldname="PRJ_STRUC_UID" value="" />
      	  <input type="hidden" fieldname="STATUS" value="0"/>
      	  <%-- 中间表的主键id --%>
      	   <input  id="XIANCHANG_STRUC_UID"  type="hidden"  fieldname="XIANCHANG_STRUC_UID" value="<%=XS_UID%>" />
      	   <input type="hidden"  fieldname="XIANCHANG_UID" value="<%=XC_UID%>" />
	  	  <input type="hidden" id="target_uid" fieldname="target_uid"  />
	  	  
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					现场状况类型：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <select  datatype="*" style="width: 80%;" nullmsg="请选择类型" fieldname="GONGKUANG_TYPE" class="col-xs-11 col-sm-11" >
			        <option value="JD" >进度</option>
			      </select>
				</div>
				<!-- 
				<label class="col-sm-2 control-label no-padding-right" >
					现场状态：
				</label>
				<div class="col-sm-4">
					&nbsp;<input id="jddoing" type="radio" name="STATUS" fieldname="STATUS"  value="0">&nbsp;进行中
					
					&nbsp;<input id="jdfinish" type="radio" name="STATUS" fieldname="STATUS"  value="1" >&nbsp;已完成
				</div>
				 -->
			</div>
<!-- 
			<div class="form-group" >
			 
	  			<label class="col-sm-2 control-label no-padding-right" >
					施工部位：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <input  id="getXMJGMC" style="width: 80%;" class="col-xs-11 col-sm-11" datatype="*" nullmsg="请选择项目现场的施工部位"  nullmsg="请选择现场施工部位"  placeholder="请选择项目现场的施工部位" type="text"  fieldname="STRUC_NAME" />
				</div>
				
				<label class="col-sm-2 control-label no-padding-right" >
					要求完成日期：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <input  id="FINISH_DATE" style="width: 80%;" class="col-xs-11 col-sm-11" data-date-format="yyyy-mm-dd"  datatype="*" nullmsg="请选择要求完成日期"   nullmsg="请选择要求完成日期"  placeholder="请选择要求完成日期" type="text"  fieldname="FINISH_DATE" />
				</div>
			</div>
			-->
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					标题：
				</label>
				<div class="col-sm-10">
					 <textarea id="xcjdContent" autofocus="autofocus"  nullmsg="请输入标题"  placeholder="请输入标题" rows="4" type="text"  fieldname="JINDU" class="col-xs-11 col-sm-11" ></textarea>
				</div>
			</div>
			
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					状况描述：
				</label>
				<div class="col-sm-10">
					 <textarea id="xcjdms" autofocus="autofocus"  nullmsg="请输入状况描述"  placeholder="请输入状况描述" rows="4" type="text"  fieldname="DESCRIPTION" class="col-xs-11 col-sm-11" ></textarea>
				</div>
			</div>

     <%--文件类型及显示 上传文件 --%>
            <div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
					现场进度状况：&nbsp;
				</label>
				<div class="col-sm-9">
					<span id="xiugaFT" style="margin:4px auto;" class="btn  btn-white btn-default btn-round " onclick="selectFile(this);" file_type="10009" >
						<i class="ace-icon fa fa-cloud-upload bigger-100"></i>
							现场进度状况
					</span>
					<table  role="presentation" class="table table-striped">
						<tbody class="files showFileTab" showType="width:120px;height:120px;quanxian:true;heightss:160px;heights:40px;del:false;attr:multi;preview:false;" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="10009" fjlb="media"></tbody>
					</table>
				</div>
		</div>
     </form>
     
	</div>

  <div class="modal-footer">
    <button id="updateXcJd" class="btn btn-1 btn-sms" type="button">保存</button>
    <button id="btnClose123"  class="btn btn-2 btn-sms pull-right" data-dismiss="modal" aria-hidden="true">关闭</button>
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

//点击保存按钮
$(function() {	
	  initFormValue();
		DatePicker.datepicker("#FINISH_DATE");
	  queryFileData('PM_XIANCHANG','<%=XC_UID%>');
	validform=FormValid.validbyformid(executeFrmXcJd);
	//获取 到 当前的项目主键UID
	$('#XCJDROJECT_UID').val($('#project_uid').val());
	$("#updateXcJd").click(function() {
	  if(validform.check()){
		if($("#executeFrmXcJd").validationButton()) {
			var data = Form2Json.formToJSON(executeFrmXcJd);
			var data1 = defaultJson.packSaveJson(data);
			$.ajax({
				url :controlletnameUrl+"?update",
				data : data1,
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
					xAlert("信息提示","现场状况信息修改成功",1);
					$("#btnClose123").click();
				     _reload();
			      }
			});
			
		 }
	   }else {
		   return;
	   }
	});
	
	//选择项目的结构名称
	$("#getXMJGMC").click(function(){
		$('#newAdd').attr("data-target","XMZK-XMMC");
		$('#XMZK-XMMC').removeData("bs.modal");
		$("#XMZK-XMMC").empty();
		$('#XMZK-XMMC').modal({
			backdrop: 'static'
		});
		
		$.get("${ctx}/jsp/business/xmzlaq/select-xmmc.jsp",function(data) {
			$("#XMZK-XMMC").empty();
			$("#XMZK-XMMC").html("");
			$("#XMZK-XMMC").html(data);
		});
	
	});
});

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
					if(result.obj!=null && result.obj!=""){
							$(result.obj.listxx).each(function(index,ele){
								//qystatus =  ele.STATUS;
								//施工部位（结构表中主键uid和 结构的名称）
								$("#XM_PRJ_STRUC_UID").val(ele.PRJ_STRUC_UID);
								$("#getXMJGMC").val(ele.STRUC_NAME);
								$("#xcjdContent").html(ele.JINDU);
								$("#xcjdms").html(ele.DESCRIPTION);
								$("#FINISH_DATE").val(ele.FINISH_DATE);
							});
						}
					/*
					if(qystatus == "0"){
						$("#jddoing").attr("checked","checked");
					 }else{
						$("#jdfinish").attr("checked","checked");
					}
					*/
			      }
			});
	
	
	
	
	
	
	
	
	
	
}
//图片放大
/*
$(document).ready(function(){
	$(".template-download img").click(function(){
	    if($(this).hasClass("fd")){
	        $(this).removeClass("fd");
	    }else{
	        $(this).addClass("fd");
	    }
	})
})
*/
function selectFile(obj){
	var targetUid = $('#target_uid').val();
	
	var file_type = $(obj).attr('file_type');
	setFileData("PM_XIANCHANG","XIANCHANG_UID",targetUid,file_type);
    document.getElementById("fileupload_btn").click();	
	
}
</script>