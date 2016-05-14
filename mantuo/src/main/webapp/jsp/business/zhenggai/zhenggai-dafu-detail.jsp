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
    	<h3 id="myModalLabel" class="blue bigger">查看答复详情</h3>
  </div>
  <div class="modal-body">
	  <form id="queryFormById">
	       <input  type="hidden" name="ZG_DAFU_UID" operation="=" fieldname="ZG_DAFU_UID" value="<%=ZG_DAFU_UID%>"/>    
	  </form>
 	  <form method="post" role="form" class="form-horizontal"  id="executeFrm5" > 
 	   <!-- 获取 项目 的 uid -->
 	   	 <input  type="hidden" name="ZG_DAFU_UID" operation="=" fieldname="ZG_DAFU_UID" value="<%=ZG_DAFU_UID%>"/>    	
      	 <input id="SPROJECT_UID2" type="hidden" name="SPROJECT_UID2" fieldname="PROJECT_UID"  />
      	 <input  type="hidden"  fieldname="TAGS" operation="=" value="BG"/> 
	  	 <input type="hidden" id="target_uid" fieldname="target_uid"  />
	  	  
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					答复编号：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input id="DAFU_CODE_ID" disabled="disabled" readonly="readonly" 	 datatype="*" nullmsg = "请输入日期"  placeholder="请输入日期" type="text"  fieldname="DAFU_CODE"  class="col-xs-11 col-sm-10"  />
				</div>
				
		  		<label class="col-sm-2 control-label no-padding-right">
					答复日期：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				    <input id="DAFU_DATE_ID" disabled="disabled" readonly="readonly" type="text" name="DAFU_DATE" class="REPORT_DATE1"  data-date-format="yyyy-mm-dd" datatype="*" nullmsg = "请输入日期"  placeholder="请输入日期"   fieldname="DAFU_DATE"   style="height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;" class="col-xs-11 col-sm-10 "/> 	  
				</div> 
			</div>	
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					整改完成日期：
				</label>
				<div class="col-sm-4">
					 <input id="ZG_DATE_ID" disabled="disabled" readonly="readonly" type="text" class="REPORT_DATE2"     fieldname="DAFU_ZG_DATE"  style="height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;" data-date-format="yyyy-mm-dd" class="col-xs-11 col-sm-10"/>
				</div> 
				<label class="col-sm-2 control-label no-padding-right" >
					答复人：
				</label>
				<div class="col-sm-4">
					 <input id="DAFU_USER_ID" disabled="disabled" readonly="readonly" type="text"  fieldname="DAFU_USER" class="col-xs-11 col-sm-10" ></input>					
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
						    <th class="col-sm-2" name="XUHAO" align="left">序号</th>
							<th class="col-sm-2" name="LEVEL_NAME"   maxlength="50" align="center">对应标准规范</th>
							<th class="col-sm-2" name="WEIGUI_LEVEL"  maxlength="50" align="center">违规等级</th>
							<th class="col-sm-2" name="CONTENT"  align="center">违规内容</th>
							<th class="col-sm-2" name="DESCRIPTION"   align="center">详细描述</th>
							<th class="col-sm-2" name="DAFU_CONTENT"  align="center">答复内容</th>
						</tr> 
					</thead>
					  <tbody id="zhenggai-dafu-table"></tbody>
					</table>  
			  </div>
			</div>
		
			
			<div id="fuchaModel" style="display:none">
			
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
							 <input id="FUCHA_DATE_ID" disabled="disabled" type="text" class="REPORT_DATE3"   data-date-format="yyyy-mm-dd" fieldname="FUCHA_DATE" style="height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;" class="col-xs-11 col-sm-10"/>
						</div> 
					 </div>	
			   
			        <div class="form-group" >
				  		<label class="col-sm-2 control-label no-padding-right">
							复查描述：
						</label>
						<div class="col-sm-4">
							 <textarea id="FUCHA_DESC_ID" disabled="disabled" readonly="readonly" class="col-xs-11 col-sm-11"  type="text"  fieldname="FUCHA_DESC" datatype="*" errormsg="" ignore="ignore"  class="col-sm-11 col-xs-11"></textarea>
						</div>				
					</div>	 
			</div>
			
			
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					复查结果：
				</label>
				<div class="col-sm-4"> 
				     <label class="col-sm-5 center"><input id="important_Y2" type="radio" name="important" disabled="disabled"/>重新整改</label>
					 <label class="col-sm-5 center"><input id="important_N2" type="radio" name="important" disabled="disabled"/>整改完成关闭</label> 
					 <input id="FUCHA_JIEGUO_ID" disabled="disabled" type="hidden"   fieldname="FUCHA_JIEGUO"/>
				</div>				
			</div>	
			<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					重新整改应完成的日期：
				</label>
				<div class="col-sm-4">
					 <input id="CXZG_DONE_DATE_ID" disabled="disabled" type="text" class="REPORT_DATE4"  value="" fieldname="CXZG_DONE_DATE" style="height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;" class="col-sm-10 col-xs-11"/>
				</div>				
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
					附件：&nbsp;
				</label>
				<div class="col-sm-10">
					<span style="margin:4px auto;display:none"  class="btn  btn-white btn-default btn-round" id="addFileSpan" onclick="selectFile(this);" file_type="10107" >
						<i class="ace-icon fa fa-upload"></i>
						<span>上传</span>
					</span>
					<table  role="presentation" class="table table-striped">
						<tbody onlyView="true" showType="width:120px;height:120px;quanxian:false;heightss:160px;heights:40px;attr:multi;preview:false" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="10107"></tbody>
					</table>
				</div>
			</div>
     </form>
     
	</div>

  <div class="modal-footer"> 
    <button class="btn btn-2 btn-sms pull-right btn-round" id="btnClose" data-dismiss="modal" aria-hidden="true">关闭</button>
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
var controlletname="${pageContext.request.contextPath }/biangeng/biangengController";
//点击保存按钮
$(function() {	
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
	DatePicker.datepicker(".REPORT_DATE3");
	DatePicker.datepicker(".REPORT_DATE4");
	
	$("#important_Y2").change(function(){	
		if($("#important_Y2").is(":checked")){
			$("#_important2").val("Y");			
		}		
	}); 
	
	$("#important_N2").change(function(){	
		if($("#important_N2").is(":checked")){
			$("#_important2").val("N");			
		}		
	}); 
	
	validform=FormValid.validbyformid(executeFrm5);
	$('#SPROJECT_UID2').val($('#project_uid').val());
	$("#addsaveUserInfo2").click(function(){
	  if(validform.check()){
		if($("#executeFrm5").validationButton()) {
			//生成json串
			var data = Form2Json.formToJSON(executeFrm5);
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

function setFormValues(){
	var data = combineQuery.getQueryCombineData(queryFormById,null,null);
	var data1 = {
		msg : data
	};
	$.ajax({
		url : "${pageContext.request.contextPath }/zhenggai/pmZgDafuController?query",
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
		     
			$("#executeFrm5").setFormValues(resultobj);	
			queryFileData('PM_ZG_DAFU',resultobj.ZG_DAFU_UID);
			 
			$.ajax({
				url : "${pageContext.request.contextPath }/zhenggai/pmZgDafuContentController?querySon",
				data : {ZG_DAFU_UID:resultobj.ZG_DAFU_UID},
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
					//var resultobj = defaultJson.dealResultJson(response.msg);
					//console.info(response.obj);
					var obj = response.obj;
					
					obj.forEach(function(obj){
						console.info(obj);
						var $tr = $("<tr style='height:auto'></tr>");
						$tr
						.append($("<td class='col-sm-2' align='center' style='padding:26px 10px;'></td>").append($("<input type='text' size='10' class='col-sm-12 col-xs-12' style='display:block' readOnly='readOnly' name='XUHAO' fieldname='XUHAO' value="+obj.XUHAO+"></input>")))
						.append($("<td class='col-sm-2' align='center' style='padding:26px 10px;'></td>").append($("<input type='text' size='20' maxlength='30'  class='col-sm-12 col-xs-12'  readOnly='readOnly' name='DETAIL_NAME' fieldname='DETAIL_NAME' value="+obj.NODE_CONTENT+"></input>")))
						.append($("<td class='col-sm-2' align='center' style='padding:26px 10px;'></td>").append($("<input type='text' size='20' maxlength='30' class='col-sm-12 col-xs-12' readOnly='readOnly' datatype='/^[0-9]*[1-9][0-9]*$/' errormsg='只能输入正整数' name='DETAIL_NUMS' fieldname='DETAIL_NUMS' value="+welguiLevelStatus(obj)+"></input>")))
						.append($("<td class='col-sm-2' align='center'></td>").append($("<textarea rows='2'   class='col-sm-12 col-xs-12'   multiline='true'  name='DETAIL_JCJG' fieldname='DETAIL_JCJG' readOnly='readOnly'>"+obj.CONTENT+"</textarea>")))
						.append($("<td class='col-sm-2' align='center'></td>").append($("<textarea rows='2'   class='col-sm-12 col-xs-12'   multiline='true'  name='DETAIL_JCJG' fieldname='DETAIL_JCJG' readOnly='readOnly'>"+obj.DESCRIPTION+"</textarea>")))
						.append($("<td class='col-sm-2' align='center'></td>").append($("<textarea  rows='2'  class='col-sm-12 col-xs-12'  multiline='true'    name='DETAIL_YSJL' fieldname='DETAIL_YSJL' readOnly='readOnly'>"+obj.DAFU_CONTENT+"</textarea>")));
						$("#zhenggai-dafu-table").append($tr);
					}); 
					//console.info($(response.msg).response);
					<%--var resultobj = defaultJson.dealResultJson(response.msg);
				    if(resultobj.FUCHA_JIEGUO==0){
						$("#important_Y2")[0].checked = true;
					}else{
						$("#important_N2")[0].checked = true;
					}
					$("#executeFrm5").setFormValues(resultobj);	 --%>
					//console.info(response);
				}
			});
		}
	}); 
	
	//复查信息（有对应的复查信息，此区域显示，否则不显示）
    initFuCha();
	
}

//复查信息（有对应的复查信息，此区域显示，否则不显示）
function initFuCha(){  
	if($("#FUCHA_USER_ID").val()!='')
		$("#fuchaModel").show();
}

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
 
function welguiLevelStatus(str){ 
	if(str.WEIGUI_LEVEL==1){
		return "一般";
	}else if(str.WEIGUI_LEVEL==2){
		return "较严重";
	}else if(str.WEIGUI_LEVEL==3){
		return "严重";
	} 
}
</script>
		