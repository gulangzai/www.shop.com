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
	String JC_TYPE = request.getParameter("JC_TYPE");
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
    	<h3 id="myModalLabel" class="blue bigger">修改飞行检查</h3>
  </div>
  <div class="modal-body">
     <form id="queryFormById">
     	   <input  type="hidden" name="BZJC_UID" operation="=" fieldname="BZJC_UID" value="<%=BZJC_UID%>"/>    
           <input  type="hidden" name="PROJECT_UID" operation="=" fieldname="PROJECT_UID" value="<%=PROJECT_UID%>"/>    
	  </form>
 	  <form method="post" role="form" class="form-horizontal"  id="zhenggaiDetailForm" > 
 	   <!-- 获取 项目 的 uid -->    
 	      <input  type="hidden" name="BZJC_UID" operation="=" fieldname="BZJC_UID" value="<%=BZJC_UID%>"/>    	
      	  <input id="ZLROJECT_UID" type="hidden" name="ROJECT_UID" fieldname="PROJECT_UID"  />
      	  <input type="hidden" id="target_uid" fieldname="target_uid"  />
	  	  <input type="hidden" id="STATUS" fieldname="STATUS"  value="0"/>
	  		
	  	  <div class="form-group" > 
	  		   <label class="col-sm-2 control-label no-padding-right" >
					检查名称： 
				</label>
				<div class="col-sm-4">
				  <input  class="col-xs-11 col-sm-10" id="ZG_CODE"   datatype="*" nullmsg="请填写检查名称"    placeholder="请填写检查名称" type="text"  fieldname="BZJC_NAME" />
				</div>
			</div>
			<div class="form-group" >	
				<label class="col-sm-2 control-label no-padding-right" >
					检查指标：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				    <input  class="col-xs-11 col-sm-10" id="JC_ZHIBIAO"   datatype="*" nullmsg="请填写检查指标"    placeholder="请填写检查指标" type="text"  fieldname="JC_ZHIBIAO" />
				</div>  
			</div>
			
			<div class="form-group" >	
				<label class="col-sm-2 control-label no-padding-right" >
					实施人员：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				    <input  class="col-xs-11 col-sm-10" id="JC_SSRY"   datatype="*" nullmsg="请填写实施人员"    placeholder="请填写实施人员" type="text"  fieldname="JC_SSRY" />
				</div>  
			</div>
			
		    <div class="form-group" >	
				<label class="col-sm-2 control-label no-padding-right" >
					检查标准：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				    <input  class="col-xs-11 col-sm-10" id="JC_BIAOZHUN"   datatype="*" nullmsg="请填写检查标准"    placeholder="请填写检查标准" type="text"  fieldname="JC_BIAOZHUN" />
				</div>  
			</div>
			
			<div class="form-group" >	
				<label class="col-sm-2 control-label no-padding-right" >
					动作描述：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <textarea id="JC_DZMS"   class="col-xs-11 col-sm-11"  type="text"  nullmsg="请填写动作描述"   placeholder="请填写动作描述" fieldname="JC_DZMS" datatype="*" errormsg="" ignore="ignore"  ></textarea>
				 </div>  
			</div>
			
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					计划完成时间：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				    <input  class="col-xs-11 col-sm-10" id="PLAN_JC_DATE"  datatype="*" nullmsg="请填写计划完成时间"    placeholder="请填写计划完成时间" type="text"  fieldname="PLAN_JC_DATE" />
				</div> 
			</div>	
		
			
			
			
			<div class="form-group" >	
				<label class="col-sm-2 control-label no-padding-right" >
					成果输出：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				    <input  class="col-xs-11 col-sm-10" id="JC_CHENGGUO"   datatype="*" nullmsg="请填写检查成果"    placeholder="请填写检查成果" type="text"  fieldname="JC_CHENGGUO" />
				</div>  
			</div>
			 
	      <div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
				        附件：&nbsp;
				</label> 
				<div class="col-sm-9">
					<span style="margin:4px auto;" class="btn  btn-white btn-default btn-round " onclick="selectFile(this);" file_type="10901" >
						<i class="ace-icon fa fa-cloud-upload bigger-100"></i>
							附件上传
					</span>
						<form class="form-inline"  id="queryForm1" width="100%"
					   role="form" style="border:solid 1px #ddd;height:50px;line-height:50px;vertical-align:middle;"></form>
					<table  role="presentation" class="table table-striped">
						<tbody onlyView="true" showType="width:120px;height:120px;quanxian:true;heightss:160px;heights:40px;del:true;attr:multi;preview:false" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="10901"></tbody>
					</table>
				</div>
		 </div>
			
     </form>
	</div>
  <div class="modal-footer"> 
     <button id="addZGDataAdd" class="btn btn-1 btn-sms btn-round" type="button">&nbsp;保存&nbsp;</button>
 <!--<button id="addZGDataCommit" class="btn btn-1 btn-sms btn-round" type="button">&nbsp;提交&nbsp;</button>  --> 
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
var controlletnameUrlZgAdd="${pageContext.request.contextPath }/pmbzjc/pmBzjcController";
var scripts=[null];
ace.load_ajax_scripts(scripts,function(){
	
});
function selectGuifan1(){
	var project_company_uid=$("#SGDW").val();
	window.open("${pageContext.request.contextPath }/jsp/business/danwei/xmdanwei-select.jsp?project_company_uid="+project_company_uid+"&uid=1");
}
function selectGuifan2(){
	var project_company_uid=$("#JLDW").val();
	window.open("${pageContext.request.contextPath }/jsp/business/danwei/xmdanwei-select.jsp?project_company_uid="+project_company_uid+"&uid=2");
}
function selectGuifan3(){
	var project_company_uid=$("#FAFANG_CORP").val();
	window.open("${pageContext.request.contextPath }/jsp/business/danwei/xmdanwei-select.jsp?project_company_uid="+project_company_uid+"&uid=3");
}
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
function selectcompany1(){
	var project_company_uid = $("#SGDW").val();
	var company_name="";
	$.ajax({
		url : "${pageContext.request.contextPath }/buprojectcompany/buProjectCompanyController?queryid",
		data : {"project_company_uid":project_company_uid},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			 msg = eval("("+response.msg+")");
			 company_name = msg.response.data[0].COMPANY_NAME;
			 $("#SGDWS").val(company_name);
		}
	})
	
}
function selectcompany2(){
	var project_company_uid = $("#JLDW").val();
	var company_name="";
	$.ajax({
		url : "${pageContext.request.contextPath }/buprojectcompany/buProjectCompanyController?queryid",
		data : {"project_company_uid":project_company_uid},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			 msg = eval("("+response.msg+")");
			 company_name = msg.response.data[0].COMPANY_NAME;
			 $("#JLDWS").val(company_name);
		}
	})
	
}
function selectcompany3(){
	var project_company_uid = $("#FAFANG_CORP").val();
	var company_name="";
	$.ajax({
		url : "${pageContext.request.contextPath }/buprojectcompany/buProjectCompanyController?queryid",
		data : {"project_company_uid":project_company_uid},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			 msg = eval("("+response.msg+")");
			 company_name = msg.response.data[0].COMPANY_NAME;
			 $("#FAFANG_CORPS").val(company_name);
		}
	}) 
}
//点击保存按钮
$(function() {	
	init(); 
	//selectcompany1();
	//selectcompany2();
	//selectcompany3();
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
 
	 //保存
	$("#addZGDataAdd").click(function() { 
	  if(validform.check()){
		if($("#zhenggaiDetailForm").validationButton()) {
			var data = Form2Json.formToJSON(zhenggaiDetailForm);
			var data1 = defaultJson.packSaveJson(data);
			$.ajax({
				url :controlletnameUrlZgAdd+"?update",
				data : data1,
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
					xAlert("信息提示","飞行检查保存成功",1);
					$("#closeZGPageAdd").click();
					 jQuery("#grid_table").jqGrid().trigger("reloadGrid");
			      }
			});
			
		 }
	   }else {
		   return;
	   }
	});
	
	//提交
	$("#addZGDataCommit").click(function() {
		  $("#STATUS").val("0");
		  if(validform.check()){
			if($("#zhenggaiDetailForm").validationButton()) {
				var data = Form2Json.formToJSON(zhenggaiDetailForm);
				var data1 = defaultJson.packSaveJson(data);
				$.ajax({
					url :controlletnameUrlZgAdd+"?update",
					data : data1,
					cache : false,
					async :	false,
					dataType : "json",  
					type : 'post',
					success : function(response) {
						xAlert("信息提示","整改信息发布成功",1);
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


function gradeChange(resultobj){
	var STATUS=resultobj.STATUS;
	var one = $("<option value='1' >限期整改</option>");
    var two = $("<option value='2' >局部停工</option>"); 
    var three  = $("<option value='3' >全面停工</option>"); 
    var $selector = $(".selector");
	//如果是草稿状态可修改
	if(STATUS!=-1){
		//select不能改
		var sel = '<%=JC_TYPE%>'; 
	    $selector.empty();
		if(sel=='1'){ 
			$selector.append(one);
		}else if(sel=='2'){
			$selector.append(two);
		}else if(sel=='3'){
			$selector.append(three);
		} 
		//form 下面的所有input，textarea,select禁用
		$("#zhenggaiDetailForm input").attr("disabled","disabled");
		$("textarea").attr("disabled","disabled");
		$("#zhenggaiDetailForm select").attr("disabled","disabled");
		
		//隐藏保存和提交
		 $("#addZGDataAdd").hide(); 
		 $("#addZGDataCommit").hide();   
	} else{
		$selector.append(one).append(two).append(three);
	} 
	 
}

function init(){
	setFormValues(); 
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
			 
		/*    if(resultobj.FUCHA_JIEGUO==0){
				$("#important_Y2")[0].checked = true;
			}else{
				$("#important_N2")[0].checked = true;
			} 
		   
		    if(resultobj.ZG_DAFU_UID==''){
		    	  $(".dafuModel").hide();
		    }else{
		    	 if(resultobj.FUCHA_USER==''){
		    	  $(".fuchaModel").hide();
		    	 }
		    }*/
		   
			$("#zhenggaiDetailForm").setFormValues(resultobj);	 
			queryFileData('PM_BZJC',resultobj.BZJC_UID); 
		}
	});  
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

function welguiLevelStatus(str){ 
	if(str.WEIGUI_LEVEL==1){
		return "一般";
	}else if(str.WEIGUI_LEVEL==2){
		return "较严重";
	}else if(str.WEIGUI_LEVEL==3){
		return "严重";
	} 
}

(function () {
    var v17 = $.noConflict(true);
    window.$.v17 = v17;
})();

(function ($) {
 	$('.lightbox').lightbox();
})(window.$.v17);
//回调
function doCallback(s){
	var strs= new Array(); //定义一数组 
	strs=s.split(","); //字符分割 
	$("#uid1").val(strs[3]);
	var uid=$("#uid1").val();
	if(uid=="1"){
	$("#SGDW").val(strs[0]);
	$("#SGDWS").val(strs[1]);
	$("#XMJL").val(strs[4]);
	}else if(uid=="2"){
	$("#JLDW").val(strs[0]);
	$("#JLDWS").val(strs[1]);
	$("#ZJ").val(strs[4]);
	}else if(uid=="3"){
	$("#FAFANG_CORP").val(strs[0]);
	$("#FAFANG_CORPS").val(strs[1]);
	}
	
}
</script>