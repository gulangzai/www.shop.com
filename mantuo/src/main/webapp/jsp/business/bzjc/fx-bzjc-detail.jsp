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
	String PROJECT_UID = request.getParameter("PROJECT_UID");
	String BZJC_UID = request.getParameter("BZJC_UID"); 
	String ZG_TYPE = request.getParameter("JC_TYPE");
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
    	<h3 id="myModalLabel" class="blue bigger">查看飞行检查</h3>
  </div>
  <div class="modal-body">
     <form id="queryFormById">
     	   <input  type="hidden" name="BZJC_UID" operation="=" fieldname="BZJC_UID" value="<%=BZJC_UID%>"/>    
           <input  type="hidden" name="PROJECT_UID" operation="=" fieldname="PROJECT_UID" value="<%=PROJECT_UID%>"/>    
	  </form>
 	  <form method="post" role="form" class="form-horizontal"  id="zhenggaiDetailForm" > 
 	   <!-- 获取 项目 的 uid -->    	
      	 <input id="ZLROJECT_UID" type="hidden" name="ROJECT_UID" fieldname="PROJECT_UID"  />
      	  <input type="hidden" id="target_uid" fieldname="target_uid"  />
	  	  <input type="hidden" id="STATUS" fieldname="STATUS"  value="0"/>
	  		
	        <div class="form-group" > 
	  		   <label class="col-sm-2 control-label no-padding-right" >
					检查名称： 
				</label>
				<div class="col-sm-4">
				  <input  class="col-xs-11 col-sm-10" id="ZG_CODE" disabled="disabled" datatype="*" nullmsg="请填写检查名称"    placeholder="请填写检查名称" type="text"  fieldname="BZJC_NAME" />
				</div>
			</div>
			<div class="form-group" >	
				<label class="col-sm-2 control-label no-padding-right" >
					检查指标：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				    <input  class="col-xs-11 col-sm-10" id="JC_ZHIBIAO" disabled="disabled" datatype="*" nullmsg="请填写检查指标"    placeholder="请填写检查指标" type="text"  fieldname="JC_ZHIBIAO" />
				</div>  
			</div>
			
			<div class="form-group" >	
				<label class="col-sm-2 control-label no-padding-right" >
					实施人员：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				    <input  class="col-xs-11 col-sm-10" id="JC_SSRY" disabled="disabled" datatype="*" nullmsg="请填写实施人员"    placeholder="请填写实施人员" type="text"  fieldname="JC_SSRY" />
				</div>  
			</div>
			
		    <div class="form-group" >	
				<label class="col-sm-2 control-label no-padding-right" >
					检查标准：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				    <input  class="col-xs-11 col-sm-10" id="JC_BIAOZHUN" disabled="disabled" datatype="*" nullmsg="请填写检查标准"    placeholder="请填写检查标准" type="text"  fieldname="JC_BIAOZHUN" />
				</div>  
			</div>
			
			<div class="form-group" >	
				<label class="col-sm-2 control-label no-padding-right" >
					动作描述：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				    <input  class="col-xs-11 col-sm-10" id="JC_DZMS" disabled="disabled" datatype="*" nullmsg="请填写动作描述"    placeholder="请填写动作描述" type="text"  fieldname="JC_DZMS" />
				</div>  
			</div>
			
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					计划完成时间：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				    <input  class="col-xs-11 col-sm-10" id="PLAN_JC_DATE" disabled="disabled" datatype="*" nullmsg="请填写计划完成时间"    placeholder="请填写计划完成时间" type="text"  fieldname="PLAN_JC_DATE" />
				</div> 
			</div>	
		
			
			
			
			<div class="form-group" >	
				<label class="col-sm-2 control-label no-padding-right" >
					成果输出：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				    <input  class="col-xs-11 col-sm-10" id="JC_CHENGGUO" disabled="disabled" datatype="*" nullmsg="请填写检查成果"    placeholder="请填写检查成果" type="text"  fieldname="JC_CHENGGUO" />
				</div>  
			</div>
			
			<div class="form-group" >	
				<label class="col-sm-2 control-label no-padding-right" >
					实际完成时间：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				    <input  class="col-xs-11 col-sm-10" id="JC_END_DATE" disabled="disabled" datatype="*" nullmsg="请填写实际完成时间"    placeholder="请填写实际完成时间" type="text"  fieldname="JC_END_DATE" />
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
					 <textarea id="DESCRIPTION" disabled="disabled" readonly="readonly" class="col-xs-11 col-sm-11"  type="text"  fieldname="DESCRIPTION" datatype="*" errormsg="" ignore="ignore"  ></textarea>
				 </div>
			</div>
			  
	  		<%-- 
	     	<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" > 
				</label>
				<div class="col-sm-10" >
					<span class="col-xs-12 col-sm-12" > 
							<!-- #section:custom/widget-box.options -->
					 <!--  <div style="float: right;">
							 <a data-target="" id="newAddZhengGaiContent" data-toggle="modal">
								<button type="button" class="btn btn-link btn-sm" id="addZhenggaiCotent"  title="添加违规内容"><i class="ace-icon glyphicon glyphicon-plus blud"></i></button>
							 </a>
							</div>   -->	
							<!-- /section:custom/widget-box.options -->
							
						  <table class="table table-striped table-bordered table-hover" >
								<thead class="thin-border-bottom">
									<tr>
										<th id="XH">序号</th>
										<th fieldname="WG_DENGJI2">对应标准规范</th>
										<th fieldname="WG_DENGJI3" formatter="welguiLevelStatus">违规等级</th>
										<th fieldname="WG_DENGJI3" formatter="formatWgsj">违规内容</th>
										<th fieldname="WG_DENGJI4" formatter="formatWgsj">备注</th>
									</tr>
								</thead>
								<tbody id="t1"></tbody>
							</table>  
					 
					</span>
				</div>
			</div>
			
		 --%>	 
	 
	    
	      <div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
				        附件：&nbsp;
				</label> 
				<div class="col-sm-9">
					<span style="margin:4px auto;display:none" class="btn  btn-white btn-default btn-round " onclick="selectFile(this);" file_type="10901" >
						<i class="ace-icon fa fa-cloud-upload bigger-100"></i>
							附件上传
					</span>
						<form class="form-inline"  id="queryForm1" width="100%"
					   role="form" style="border:solid 1px #ddd;height:50px;line-height:50px;vertical-align:middle;"></form>
					<table  role="presentation" class="table table-striped">
						<tbody onlyView="true" showType="width:120px;height:120px;quanxian:false;heightss:160px;heights:40px;attr:multi;preview:false" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="10901"></tbody>
					</table>
				</div>
		 </div>
			
     </form>
	</div>
  <div class="modal-footer">
 <!--<button id="addZGDataAdd" class="btn btn-white btn-default btn-round" type="button">&nbsp;保存&nbsp;</button>  --> 
    <button id="closeZGPageAdd"  class="btn btn-2 btn-sms pull-right btn-round" data-dismiss="modal" aria-hidden="true">关闭</button>
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
	init();
	setFormValues();  
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
 
	$("#addZGDataAdd").click(function() {
	  if(validform.check()){
		if($("#executeFrmZL").validationButton()) {
			var data = Form2Json.formToJSON(zhenggaiDetailForm);
			var data1 = defaultJson.packSaveJson(data);
			$.ajax({
				url :controlletnameUrlZgAdd+"?insert",
				data : data1,
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
					xAlert("信息提示","现场状况信息发布成功",1);
					$("#closeZGPageAdd").click();
					 jQuery("#grid_table").jqGrid().trigger("reloadGrid");
			      }
			});
			
		 }
	   }else {
		   return;
	   }
	});
	
	
	$("#addZhenggaiCotent").click(function(){
		$('#newAddZhengGaiContent').attr("data-target","XMZK-XMMC");
		$('#XMZK-XMMC').removeData("bs.modal");
		$("#XMZK-XMMC").empty();
		$('#XMZK-XMMC').modal({
			backdrop: 'static'
		});
		
		$.get("${ctx}/jsp/business/zhenggai/zhenggai_content_add.jsp",function(data) {
			console.info(data);
			$("#XMZK-XMMC").empty();
			$("#XMZK-XMMC").html("");
			$("#XMZK-XMMC").html(data);
		}); 
	}); 
	
});
 

 
function init(){
	//form 下面的所有input，textarea,select禁用
	$("#zhenggaiDetailForm input").attr("disabled","disabled");
	$("textarea").attr("disabled","disabled");
	$("#zhenggaiDetailForm select").attr("disabled","disabled");
}
function setFormValues(){
	var data = combineQuery.getQueryCombineData(queryFormById,null,null);
	var data1={PROJECT_UID:'<%=PROJECT_UID%>',BZJC_UID:'<%=BZJC_UID%>'};
	$.ajax({
		url : "${pageContext.request.contextPath }/pmbzjc/pmBzjcController?queryNewContent",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var resultobj = defaultJson.dealResultJson(response.msg);
		  
			$("#zhenggaiDetailForm").setFormValues(resultobj);	  
			queryFileData('PM_BZJC',resultobj.BZJC_UID);
			 
	<%--    $.ajax({
				url : "${pageContext.request.contextPath }/zhenggai/pmZhengGaiContentController?queryContentList",
				data : {BZJC_UID:resultobj.BZJC_UID},
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) { 
					var obj = response.obj; 
					obj.forEach(function(obj){ 
						
						$("#t1").append(
								$("<tr style='height:auto'></tr>")
								.append($("<td class='col-sm-2' style='padding:26px;'><input type='text' class='col-sm-12 col-xs-12' readOnly='readOnly' style='display:block' value="+obj.XUHAO+"></td>"))
								.append($("<td class='col-sm-2' style='padding:26px;'><input type='text' class='col-sm-12 col-xs-12' readOnly='readOnly' style='display:block'  value="+obj.P_BZGF+"></td>"))
								.append($("<td class='col-sm-2' style='padding:26px;'><input type='text' class='col-sm-12 col-xs-12' readOnly='readOnly' style='display:block'  value='"+welguiLevelStatus(obj)+"'></td>"))
								.append($("<td class='col-sm-2'><textarea rows='2'  class='col-sm-12 col-xs-12'  multiline='true'  readOnly='readOnly' style='display:block'>"+obj.CONTENT+"</textarea></td>"))
								.append($("<td class='col-sm-2'><textarea rows='2'  class='col-sm-12 col-xs-12'  multiline='true'  readOnly='readOnly'>"+obj.DESCRIPTION+"</textarea></td>"))
								); 
					});   
				}
			}); 
		    
			$.ajax({
				url : "${pageContext.request.contextPath }/zhenggai/pmZgDafuContentController?querySon",
				data : {ZG_DAFU_UID:resultobj.ZG_DAFU_UID},
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) { 
				
					var objs = response.obj;  
					 objs.forEach(function(obj){  
						var $tr = $("<tr></tr>"); 
						$tr
						.append($("<td></td>").append(obj.XUHAO))
						.append($("<td></td>").append(obj.NODE_CONTENT))
						.append($("<td></td>").append(welguiLevelStatus(obj)))
						.append($("<td></td>").append(obj.CONTENT))
						.append($("<td></td>").append(obj.DESCRIPTION))
						.append($("<td></td>").append(obj.DAFU_CONTENT)); 
						 $("#t2").append($tr);
					});  
				}
			});
		--%>	
		}
	});  
}

function welguiLevelStatus(str){ 
	if(str.WEIGUI_LEVEL==1){
		return "一般";
	}else if(str.WEIGUI_LEVEL==2){
		return "较严重";
	}else if(str.WEIGUI_LEVEL==3){
		return "严重";
	} 
}
function selectFile(obj){
	var targetUid = $('#target_uid').val();
	var file_type = $(obj).attr('file_type');
	setFileData("PM_BZJC","BZJC_UID",targetUid,file_type);
    document.getElementById("fileupload_btn").click();	 
}

function generateMixed(n) {
	var chars = ['0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];
    var res = "";
     for(var i = 0; i < n ; i ++) {
         var id = Math.ceil(Math.random()*35);
         res += chars[id];
     }
     return res;
}

(function () {
    var v17 = $.noConflict(true);
    window.$.v17 = v17;
 })();

(function ($) {
 	$('.lightbox').lightbox();
 })(window.$.v17);
 
</script>