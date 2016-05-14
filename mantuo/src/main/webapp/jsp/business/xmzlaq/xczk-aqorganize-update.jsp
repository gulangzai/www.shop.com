<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/sys_resources/plugins/lightbox/themes/default/jquery.lightbox.css" />
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
    	<h3 id="myModalLabel" class="blue bigger">修改安全工况</h3>
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
	  	  <input type="hidden" fieldname="f_DESCRIPTION"  />
	  	  <!-- 
	  	  <input type="hidden" id="CLOSE_DATE" fieldname="CLOSE_DATE" data-date-format="yyyy-mm-dd" />
	  	   -->
	  	  <input type="hidden" id="STATUS" fieldname="STATUS"  value=""/>
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					现场状况类型：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <select  datatype="*" style="width: 80%;" nullmsg="请选择类型" fieldname="GONGKUANG_TYPE" class="col-xs-11 col-sm-11" >
			        <option value="AQ" >安全</option>
			      </select>
				</div>
				<!-- 
				<label class="col-sm-2 control-label no-padding-right" >
					现场状态：
				</label>
				<div class="col-sm-4">
					&nbsp;<input id="jddoing" type="radio" name="STATUS" fieldname="STATUS"  value="0">&nbsp;解决中
					
					&nbsp;<input id="jdfinish" type="radio" name="STATUS" fieldname="STATUS"  value="1" >&nbsp;已解决
				</div>
				 -->
			</div>

			<div class="form-group" >
			<!-- 
	  			<label class="col-sm-2 control-label no-padding-right" >
					施工部位：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <input  id="getXMJGMC" style="width: 80%;" class="col-xs-11 col-sm-11" datatype="*" nullmsg="请选择项目现场的施工部位"  nullmsg="请选择现场施工部位"  placeholder="请选择项目现场的施工部位" type="text"  fieldname="STRUC_NAME" />
				</div>
				 -->
				<label class="col-sm-2 control-label no-padding-right" >
					要求解决日期：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <input  id="FINISH_DATE" style="width: 80%;" class="col-xs-11 col-sm-11" data-date-format="yyyy-mm-dd"  datatype="*" nullmsg="请选择要求解决日期"   nullmsg="请选择要求解决日期"  placeholder="请选择要求完成日期" type="text"  fieldname="FINISH_DATE" />
				</div>
				<label class="col-sm-2 control-label no-padding-right" >
					严重性：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <select class="col-sm-4" id="SERIOUSNESS" datatype="*" nullmsg = "请选择严重性"  placeholder="请选择严重性" type="text"  fieldname="SERIOUSNESS">
					 	
					 	<option value="1">一般</option>
					 	<option value="2">较严重</option>
					 	<option value="3">严重</option>
					 	
					 </select>
				</div>
			</div>
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					标题：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <textarea id="xcjdContent" autofocus="autofocus" datatype="*" nullmsg="请输入标题"  placeholder="请输入标题" rows="4" type="text"  fieldname="JINDU" class="col-xs-11 col-sm-11" ></textarea>
				</div>
			</div>
			
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					状况描述：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <textarea id="xcjdms" autofocus="autofocus" datatype="*" nullmsg="请输入状况描述"  placeholder="请输入状况描述" rows="4" type="text"  fieldname="DESCRIPTION" class="col-xs-11 col-sm-11" ></textarea>
				</div>
			</div>
         <%--文件类型及显示 上传文件 --%>
        <div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
					现场进度状况：&nbsp;
				</label>
				
				<div class="col-sm-9">
					<span style="margin:4px auto;" class="btn  btn-white btn-default btn-round " onclick="selectFile(this);" file_type="10011" >
						<i class="ace-icon fa fa-cloud-upload bigger-100"></i>
							现场进度状况
					</span>
					<table  role="presentation" class="table table-striped">
						<tbody class="files showFileTab" showType="width:120px;height:120px;quanxian:true;heightss:160px;heights:40px;del:false;attr:multi;preview:false;" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="10011" fjlb="media"></tbody>
					</table>
				</div>
		</div>
     </form>
     
	</div>

  <div class="modal-footer">
      
      <button id="tijiao"  class="btn btn-1 btn-sms" type="button">解决</button>
    <button id="fabu"  class="btn btn-1 btn-sms" type="button">发布</button>
    <button id="updateXcJd" class="btn btn-1 btn-sms" type="button">&nbsp;保存&nbsp;</button>
    <button id="btnCloseAq"  class="btn btn-2 btn-sms pull-right" data-dismiss="modal" aria-hidden="true">关闭</button>
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
//点击保存按钮
$(function() {	
	  initFormValue();
	  var tody = new Date(); 
		var nian = tody.getFullYear();
		var youe = tody.getMonth() + 1; 
		var day = tody.getDate(); 
		var dates=nian+"-"+youe+"-"+day;//当前日期
	  DatePicker.datepicker("#FINISH_DATE"); 
	  queryFileData('PM_XIANCHANG','<%=XC_UID%>');
	validform=FormValid.validbyformid(executeFrmXcJd);
	//获取 到 当前的项目主键UID
	$('#XCJDROJECT_UID').val($('#project_uid').val());
	
	$("#updateXcJd").click(function() {
		var date =$("#FINISH_DATE").val();
		var d1 = new Date(date.replace(/\-/g, "\/")); 
		 var d2 = new Date(dates.replace(/\-/g, "\/"));
		$("#STATUS").val("-1");
	  if(validform.check()&&d1>=d2){
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
					xAlert("信息提示","现场状况已关闭，不能修改");
					$("#btnCloseAq").click();
					 jQuery("#grid_table").jqGrid().trigger("reloadGrid");
			      }
			});
			
		 }
	   }else {
		   xAlert("信息提示","要求完成日期必须大于当前日期");
		   return;
	   }
	});
	//发布
	$("#fabu").click(function() {
		var date =$("#FINISH_DATE").val();
		var d1 = new Date(date.replace(/\-/g, "\/")); 
		 var d2 = new Date(dates.replace(/\-/g, "\/"));
		$("#STATUS").val("0");
	  if(validform.check()&&d1>=d2){
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
					$("#btnCloseAq").click();
					 _reload();
			      }
			});
			
		 }
	   }else {
		   xAlert("信息提示","要求完成日期必须大于当前日期");
		   return;
	   }
	
	});
	$("#tijiao").click(function() {
	bootbox.confirm("解决后不可修改工况信息，您确定要解决工况问题吗？", function(result) {
		if (result) {						
			// $("#CLOSE_DATE").val(dates);
				  $("#STATUS").val("1");
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
								xAlert("信息提示","现场状态已关闭，不能再修改",1);
								$("#btnCloseAq").click();
								jQuery("#grid_table").jqGrid().trigger("reloadGrid");	
						      }
						}); 
					 }
				   }else {
					   return;
				   }
		} else {
			return;
		}
	});
	});	
	//提交
	/*$("#tijiao").click(function() {
		  $("#STATUS").val("1");
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
						xAlert("信息提示","现场状态已关闭，不能再修改",1);
						$("#btnCloseAq").click();
						jQuery("#grid_table").jqGrid().trigger("reloadGrid");	
				      }
				}); 
			 }
		   }else {
			   return;
		   }
		});
	*/
	
     
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
								qystatus =  ele.STATUS;
								//施工部位（结构表中主键uid和 结构的名称）
								$("#XM_PRJ_STRUC_UID").val(ele.PRJ_STRUC_UID);
								$("#getXMJGMC").val(ele.STRUC_NAME);
								$("#xcjdContent").html(ele.JINDU);
								$("#xcjdms").html(ele.DESCRIPTION);
								$("#FINISH_DATE").val(ele.FINISH_DATE);
								$("#SERIOUSNESS").val(ele.SERIOUSNESS);
							});
						}
					if(qystatus == "0"){
						$("#jddoing").attr("checked","checked");
					 }else{
						$("#jdfinish").attr("checked","checked");
					}
					if(qystatus == "0"){
						$("select").attr("disabled","disabled");
						$("#updateXcJd").hide();
						$("#fabu").hide();
						$("textarea").attr("disabled","disabled");
						$("#executeFrmXcJd input").attr("disabled","disabled");
					}else if(qystatus == "-1"){
						
					    $("#tijiao").hide();
					}
					
			      }
			});
	
}
(function () {
    var v17 = $.noConflict(true);
    window.$.v17 = v17;
 })();

(function ($) {
 	$('.lightbox').lightbox();
 })(window.$.v17);

function selectFile(obj){
	var targetUid = $('#target_uid').val();
	var file_type = $(obj).attr('file_type');
	setFileData("PM_XIANCHANG","XIANCHANG_UID",targetUid,file_type);
    document.getElementById("fileupload_btn").click();	
	
}
</script>