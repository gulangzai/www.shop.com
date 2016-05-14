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
	String ZHENGGAI_UID = request.getParameter("ZHENGGAI_UID"); 
	String ZG_TYPE = request.getParameter("ZG_TYPE");
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
    	<h3 id="myModalLabel" class="blue bigger">修改工程整改</h3>
  </div>
  <div class="modal-body">
     <form id="queryFormById">
     	   <input  type="hidden" name="ZHENGGAI_UID" operation="=" fieldname="ZHENGGAI_UID" value="<%=ZHENGGAI_UID%>"/>    
           <input  type="hidden" name="PROJECT_UID" operation="=" fieldname="PROJECT_UID" value="<%=PROJECT_UID%>"/>    
	  </form>
 	  <form method="post" role="form" class="form-horizontal"  id="zhenggaiDetailForm" > 
 	   <!-- 获取 项目 的 uid -->    
 	      <input  type="hidden" name="ZHENGGAI_UID" operation="=" fieldname="ZHENGGAI_UID" value="<%=ZHENGGAI_UID%>"/>    	
      	  <input id="ZLROJECT_UID" type="hidden" name="ROJECT_UID" fieldname="PROJECT_UID"  />
      	  <input type="hidden" id="target_uid" fieldname="target_uid"  />
	  	  <input type="hidden" id="STATUS" fieldname="STATUS"  value="0"/>
	  		
	  		<div class="form-group" >
	  		
	  		   <label class="col-sm-2 control-label no-padding-right" >
					整改编号： 
				</label>
				<div class="col-sm-4">
				  <input  class="col-xs-11 col-sm-10" id="ZG_CODE" disabled="disabled" datatype="*" nullmsg="请选择项目现场的施工部位"    placeholder="请选择项目现场的施工部位" type="text"  fieldname="ZG_CODE" />
				</div>
				 
	  			<label class="col-sm-2 control-label no-padding-right" >
					整改类型：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <select   class="col-xs-11 col-sm-10"  nullmsg="请选择类型" datatype="*" fieldname="ZG_TYPE" class="col-xs-11 col-sm-11" >
			        <option value="1" >限期整改</option>
			        <option value="2" >局部停工</option>
			        <option value="3" >全面停工</option>
			        
			      </select>
				</div> 
			</div>
			
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					现场进度：<span class="required-indicator">*</span>
				</label>
			    <div class="col-sm-10">
				   <input  class="col-sm-11 col-xs-11" id="getXMJGMC"  datatype="*" nullmsg="请先写现场进度"    placeholder="请先写现场进度" type="text"  fieldname="JINDU" />
				</div>
			</div>
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					施工单位：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
				 <input id="uid1" value="" type="hidden"/>
				 <input  id="SGDW" value="" datatype="*" disabled="disabled" nullmsg="请填写施工单位"    placeholder="请填写施工单位" type="hidden"  fieldname="SGDW" class="col-xs-11 col-sm-11"/>
				 <input  id="SGDWS" value="" datatype="*" disabled="disabled" nullmsg="请填写施工单位"    placeholder="请填写施工单位" type="text"   class="col-xs-11 col-sm-11"/>
				<i class="ace-icon glyphicon glyphicon-list bigger-110" onclick="selectGuifan1();" style="cursor:pointer" title="选择项目参建单位"></i>
				</div>
			</div>
			
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					监理单位：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
				 <input  id="JLDW" value="" datatype="*" disabled="disabled" nullmsg="请填写监理单位"   placeholder="请填写监理单位" type="hidden"  fieldname="JLDW" class="col-xs-11 col-sm-11"/>
				<input  id="JLDWS" value="" datatype="*" disabled="disabled" nullmsg="请填写监理单位"   placeholder="请填写监理单位" type="text"   class="col-xs-11 col-sm-11"/>
				<i class="ace-icon glyphicon glyphicon-list bigger-110" onclick="selectGuifan2();" style="cursor:pointer" title="选择项目参建单位"></i>
				</div>
			</div>
			
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
				        项目经理：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <input  class="col-xs-11 col-sm-10" id="XMJL"   datatype="*" nullmsg="请填写项目经理"   placeholder="请填写项目经理" type="text"  fieldname="XMJL" />
				</div>
				
				<label class="col-sm-2 control-label no-padding-right" >
				        总监：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <input class="col-xs-11 col-sm-10"  id="ZJ"   datatype="*" nullmsg="请填写总监"    placeholder="请填写总监" type="text"  fieldname="ZJ" />
				</div> 
			</div>
			
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
				       发放单位：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
				 <input  id="FAFANG_CORP" value="" disabled="disabled" datatype="*" nullmsg="请填写发放单位"    placeholder="请填写发放单位" type="hidden"  fieldname="FAFANG_CORP" class="col-xs-11 col-sm-11"/>
				 <input  id="FAFANG_CORPS" value="" disabled="disabled" datatype="*" nullmsg="请填写发放单位"    placeholder="请填写发放单位" type="text"   class="col-xs-11 col-sm-11"/>
				 <i class="ace-icon glyphicon glyphicon-list bigger-110" onclick="selectGuifan3();" style="cursor:pointer" title="选择项目参建单位"></i>
				</div> 
			</div>
			
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
				        发放人：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <input  class="col-xs-11 col-sm-10" id="getXMJGMC"   datatype="*" nullmsg="请填写发放人"   placeholder="请填写项目经理" type="text"  fieldname="FAFANG_USER" />
				</div>
				
				<label class="col-sm-2 control-label no-padding-right" >
				        发放日期：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <input  class="col-xs-11 col-sm-10" id="FAFANG_DATE" type="text" class="REPORT_DATE1" datatype="*" nullmsg="默认当前日期"  style="height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;" placeholder="默认当前日期" data-date-format="yyyy-mm-dd" fieldname="FAFANG_DATE" />
				</div> 
			</div>
			 
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					要求整改完成日期：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
				 <input  class="REPORT_DATE2" type="text" id="zg_date" datatype="*"  style="height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"   data-date-format="yyyy-mm-dd"  fieldname="ZG_DATE"  class="col-xs-11 col-sm-11"/>
				</div>
			</div>
			
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					参检人员：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
				    <input  id="getXMJGMC"   datatype="*" nullmsg="请填写参检人员"   placeholder="请填写参检人员" type="text"  fieldname="canjian" class="col-xs-11 col-sm-11"/>
				</div>
			</div>
	  	
	  	<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" ></label>
				<div class="col-sm-10" >
					<span  class="col-xs-11 col-sm-11" > 
							<!-- #section:custom/widget-box.options -->
					 <!--  <div style="float: right;">
							 <a data-target="" id="newAddZhengGaiContent" data-toggle="modal">
								<button type="button" class="btn btn-link btn-sm" id="addZhenggaiCotent"  title="添加违规内容"><i class="ace-icon glyphicon glyphicon-plus blud"></i></button>
							 </a>
							</div>   -->	
							<!-- /section:custom/widget-box.options -->
							
						 <table class="table table-striped table-bordered table-hover">
								<thead class="thin-border-bottom">
									<tr>
										<th id="XH">序号</th>
										<th fieldname="WG_DENGJI2">对应标准规范</th>
										<th fieldname="WG_DENGJI3">违规等级</th>
										<th fieldname="WG_DENGJI3" formatter="formatWgsj">违规内容</th>
										<th fieldname="WG_DENGJI4" formatter="formatWgsj">详细描述</th>
									</tr>
								</thead>
								<tbody id="t1"></tbody>
							</table>  
							 
					</span>
				</div>
			</div>
			
			
		<!-- -------------------------------------#答复情况#--------------------------------------- -->
		<div class="dafuModel">
			<div class="form-group" > 
	  		   <label class="col-sm-2 control-label no-padding-right" >
					答复编号： 
				</label>
				<div class="col-sm-3">
				  <input  id="DAFU_CODE" disabled="disabled" nullmsg="请填写答复编号"    placeholder="请填写答复编号" type="text"  fieldname="DAFU_CODE" class="col-xs-11 col-sm-10"/>
				</div> 
				
				 <label class="col-sm-2 control-label no-padding-right" >
					答复日期： 
				</label>
				<div class="col-sm-3">
				  <input  id="DAFU_DATE" disabled="disabled"   nullmsg="请填写答复日期"    placeholder="请填写答复日期" type="text"  fieldname="DAFU_DATE" class="col-xs-11 col-sm-10"/>
				</div>  
			</div>
			
			<div class="form-group" > 
	  		   <label class="col-sm-2 control-label no-padding-right" >
					要求整改完成日期： 
				</label>
				<div class="col-sm-3">
				  <input  id="ZG_DATE" disabled="disabled"   nullmsg="请填写答复编号"    placeholder="请填写答复编号" type="text"  fieldname="DAFU_ZG_DATE" class="col-xs-11 col-sm-10"/>
				</div> 
				
				 <label class="col-sm-2 control-label no-padding-right" >
					答复人： 
				</label>
				<div class="col-sm-3">
				  <input  id="DAFU_USER" disabled="disabled"   nullmsg="请填写答复日期"   placeholder="请填写答复日期" type="text"  fieldname="DAFU_USER" class="col-xs-11 col-sm-10" />
				</div>  
			</div>
			
		   <div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" > 
				</label>
				<div class="col-sm-10" >
					<span class="col-xs-11 col-sm-11" > 
							<table class="table table-striped table-bordered table-hover" >
								<thead class="thin-border-bottom">
									<tr>
										<th id="XH">序号</th>
										<th fieldname="BZGF_UID">对应标准规范</th>
										<th fieldname="WEIGUI_LEVEL">违规等级</th>
										<th fieldname="CONTENT" formatter="formatWgsj">违规内容</th>
										<th fieldname="DESCRIPTION" formatter="formatWgsj">备注</th>
										<th fieldname="DAFU_CONTENT" formatter="formatWgsj">答复内容</th>
									</tr>
								</thead>
								<tbody id="t2"></tbody>
							</table> 
					</span>
				</div>
		 </div> 
		<!-- -------------------------------------#复查情况#--------------------------------------- -->
			<div class="fuchaModel"> 
			  		<div class="form-group" >
				  		<label class="col-sm-2 control-label no-padding-right">
							复查人：
						</label>
						<div class="col-sm-4">
							 <input id="FUCHA_USER_ID" disabled="disabled" class="col-xs-11 col-sm-11" type="text"  readonly="readonly" fieldname="FUCHA_USER"/>
						</div>
				  		<label class="col-sm-2 control-label no-padding-right">
							复查日期：
						</label>
						<div class="col-sm-4">
							 <input id="FUCHA_DATE_ID" disabled="disabled" type="text" class="col-xs-11 col-sm-11 REPORT_DATE3"   data-date-format="yyyy-mm-dd" fieldname="FUCHA_DATE" style="width: 220px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"/>
						</div> 
					 </div>	
			   
			        <div class="form-group" >
				  		<label class="col-sm-2 control-label no-padding-right">
							复查描述：
						</label>
						<div class="col-sm-4">
							 <textarea id="FUCHA_DESC_ID" disabled="disabled" readonly="readonly" class="col-xs-11 col-sm-11"  type="text"  fieldname="FUCHA_DESC"  errormsg="" ignore="ignore"  ></textarea>
						</div>				
					</div>	 
			
			<div class="form-group">
		  		<label class="col-sm-2 control-label no-padding-right">
					复查结果：
				</label>
				<div class="col-sm-4"> 
				     <label class="col-sm-5 center"><input id="important_Y2" type="radio" name="important"/>重新整改</label>
					 <label class="col-sm-5 center"><input id="important_N2" type="radio" name="important"/>整改完成关闭</label> 
					 <input id="FUCHA_JIEGUO_ID" disabled="disabled" type="hidden"   fieldname="FUCHA_JIEGUO"/>
				</div>				
			</div>	
			<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					重新整改应完成的日期：
				</label>
				<div class="col-sm-4">
					 <input id="CXZG_DONE_DATE_ID" disabled="disabled" type="text" class="REPORT_DATE4"  value="" fieldname="CXZG_DONE_DATE" style="width: 220px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"/>
				</div>				
			</div>
		   </div>
	    </div>
	    
	      <div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
				        附件：&nbsp;
				</label> 
				<div class="col-sm-9">
					<span style="margin:4px auto;" class="btn  btn-white btn-default btn-round " onclick="selectFile(this);" file_type="10105" >
						<i class="ace-icon fa fa-cloud-upload bigger-100"></i>
							附件上传
					</span>
						<form class="form-inline"  id="queryForm1" width="100%"
					   role="form" style="border:solid 1px #ddd;height:50px;line-height:50px;vertical-align:middle;"></form>
					<table  role="presentation" class="table table-striped">
						<tbody onlyView="true" showType="width:120px;height:120px;quanxian:true;heightss:160px;heights:40px;del:true;attr:multi;preview:false" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="10105"></tbody>
					</table>
				</div>
		 </div>
			
     </form>
	</div>
  <div class="modal-footer"> 
     <button id="addZGDataAdd" class="btn btn-1 btn-sms btn-round" type="button">&nbsp;保存&nbsp;</button>
     <button id="addZGDataCommit" class="btn btn-1 btn-sms btn-round" type="button">&nbsp;提交&nbsp;</button>
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
	setFormValues(); 
	selectcompany1();
	selectcompany2();
	selectcompany3();
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
		var sel = '<%=ZG_TYPE%>'; 
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
	
}
function setFormValues(){
	var data = combineQuery.getQueryCombineData(queryFormById,null,null);
	var data1={PROJECT_UID:'<%=PROJECT_UID%>',ZHENGGAI_UID:'<%=ZHENGGAI_UID%>'};
	$.ajax({
		url : "${pageContext.request.contextPath }/zhenggai/pmZhengGaiContentController?queryNewContent",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var resultobj = defaultJson.dealResultJson(response.msg);
			 
		    if(resultobj.FUCHA_JIEGUO==0){
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
		    }
		   
			$("#zhenggaiDetailForm").setFormValues(resultobj);	 
			queryFileData('PM_ZHENGGAI',resultobj.ZHENGGAI_UID);
			 
		    $.ajax({
				url : "${pageContext.request.contextPath }/zhenggai/pmZhengGaiContentController?queryContentList",
				data : {ZHENGGAI_UID:resultobj.ZHENGGAI_UID},
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) { 
					var obj = response.obj; 
					obj.forEach(function(obj){  
						$("#t1").append(
						$("<tr style='height:auto'></tr>")
						.append($("<td style='display:none'></td>").append($("<input style='width:20px' type='text' fieldname='ZHENGGAI_CONTENT_UID'/>").val(obj.ZHENGGAI_CONTENT_UID)))
						.append($("<td class='col-sm-2' style='padding:26px;'><input type='text' class='col-sm-12 col-xs-12' readOnly='readOnly' fieldname='XUHAO' style='display:block' value="+obj.XUHAO+"></td>"))
						.append($("<td class='col-sm-2' style='padding:26px;'><input type='hidden' fieldname='BZGF_UID' value='"+obj.BZGF_UID+"'><input type='text' size='20' maxlength='30' class='col-sm-12 col-xs-12' readOnly='readOnly' style='display:block'  value="+obj.P_BZGF+"></td>"))
						.append($("<td class='col-sm-2' style='padding:26px;'><input type='hidden' fieldname='WEIGUI_LEVEL' value='"+obj.WEIGUI_LEVEL+"'><input type='text' size='20' maxlength='30' class='col-sm-12 col-xs-12' readOnly='readOnly' style='display:block'  value='"+welguiLevelStatus(obj)+"'></td>"))
						.append($("<td class='col-sm-2'><textarea rows='2'  class='col-sm-12 col-xs-12'  multiline='true' fieldname='CONTENT'  readOnly='readOnly'  style='display:block'>"+obj.CONTENT+"</textarea></td>"))
						.append($("<td class='col-sm-2'><textarea rows='2'  class='col-sm-12 col-xs-12'  multiline='true' fieldname='DESCRIPTION'>"+obj.DESCRIPTION+"</textarea></td>"))
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
					var obj = response.obj; 
					obj.forEach(function(obj){ 
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
			gradeChange(resultobj); 
		}
	});  
}

function selectFile(obj){
	var targetUid = $('#target_uid').val();
	var file_type = $(obj).attr('file_type');
	setFileData("PM_ZHENGGAI","ZHENGGAI_UID",targetUid,file_type);
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