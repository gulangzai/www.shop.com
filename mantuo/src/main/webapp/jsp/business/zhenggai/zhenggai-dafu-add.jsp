<%@ page language="java" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/sys_resources/plugins/lightbox/themes/default/jquery.lightbox.css" />
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ page import="com.ccthanking.framework.Constants"%>
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
	String zhenggai_uid = request.getParameter("zhenggai_uid");
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
    	<h3 id="myModalLabel" class="blue bigger">整改答复</h3>
  </div>
  <div class="modal-body">
 	  <form method="post" role="form" class="form-horizontal"  id="executeFrmZL" > 
 	   <!-- 获取 项目 的 uid -->    	
      	 <input id="ZLROJECT_UID" type="hidden" name="ROJECT_UID" fieldname="PROJECT_UID"  />
      	  <input type="hidden" id="target_uid" fieldname="target_uid"  />
	  	  <input type="hidden" id="STATUS" name="STATUS" fieldname="STATUS"/>
	  	  <input type="hidden" id="NEW_Y" name="NEW_Y"  fieldname="NEW_Y" value="Y"/>
	  	  <input type="hidden" id="ZHENGGAI_UID" fieldname="ZHENGGAI_UID" value="<%=zhenggai_uid%>"/>
	  		<div class="form-group" >
	  		
	  		   <label class="col-sm-2 control-label no-padding-right" >
					答复编号： 
				</label>
				<div class="col-sm-4">
				  <input  class="col-xs-11 col-sm-10" id="DAFU_CODE" disabled="disabled"     type="text"  fieldname="DAFU_CODE" />
				</div>
				 
	  			<label class="col-sm-2 control-label no-padding-right" >
					答复日期：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				<input   id="DAFU_DATE"  class="REPORT_DATE1"   nullmsg="请选择答复日期"  style="height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;" placeholder="请选择答复日期"   data-date-format="yyyy-mm-dd"  fieldname="DAFU_DATE" class="col-xs-11 col-sm-10" />
				</div> 
			</div>
			
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					整改完成日期：<span class="required-indicator">*</span>
				</label>
			    <div class="col-sm-4">
				   <input  datatype="*" class="REPORT_DATE2"   nullmsg="请选择整改完成日期"  style="height:25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;" placeholder="请选择整改完成日期"   data-date-format="yyyy-mm-dd" fieldname="DAFU_ZG_DATE"  class="col-xs-11 col-sm-10" />
				</div>
				
				<label class="col-sm-2 control-label no-padding-right" >
					答复人：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				  <input  class="col-xs-11 col-sm-10" id="DAFU_USER" datatype="*"   nullmsg="请填写答复人"    placeholder="请填写答复人" type="text"  fieldname="DAFU_USER" />
				</div>
			</div> 
		  
	  	<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" > 
				</label>
				<div class="col-sm-10" >
					<span class="col-xs-12 col-sm-12" > 
							<table class="table table-striped table-bordered table-hover" >
								<thead class="thin-border-bottom">
									<tr>
										<th id="XH">序号</th>
										<th fieldname="BZGF_UID" align="center">对应标准规范</th>
										<th fieldname="WEIGUI_LEVEL" align="center">违规等级</th>
										<th fieldname="CONTENT" align="center" formatter="formatWgsj">违规内容</th>
										<th fieldname="DESCRIPTION" align="center" formatter="formatWgsj">备注</th>
										<th fieldname="DAFU_CONTENT" align="center" formatter="formatWgsj">答复内容</th>
									</tr>
								</thead>
								<tbody id="t"></tbody>
							</table> 
					</span>
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
    <button id="addZGDataAdd" class="btn btn-1 btn-sms btn-round" type="button">&nbsp;保存&nbsp;</button>
    <button id="addZGDataUpdate" class="btn btn-1 btn-sms btn-round" type="button">&nbsp;提交&nbsp;</button>
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
var controlletnameUrlZgContent="${pageContext.request.contextPath }/zhenggai/pmZhengGaiContentController";
var controlletnameUrldaFu ="${pageContext.request.contextPath }/zhenggai/pmZgDafuController";
var scripts=[null];
var ZHENGGAI_UID = "<%=zhenggai_uid%>";
 
ace.load_ajax_scripts(scripts,function(){  
	$('#ZLROJECT_UID').val($('#project_uid').val()); 
	//自动编号
	$("#DAFU_CODE").val(generateMixed(12));
	$("#DAFU_USER").val('<%=username%>');
	var date = new Date().Format("yyyy-MM-dd"); 
	$("#DAFU_DATE").val(date);  
	DatePicker.datepicker(".REPORT_DATE1"); 
    DatePicker.datepicker(".REPORT_DATE2");   
	init(); 
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
 
$(function() {	 
	
	//删除多余的 div 避免弹出图片时出现多层
	var div1 = $(".jquery-lightbox-overlay").eq(0);
	var div2 = $(".jquery-lightbox-move").eq(0); 
	$(".jquery-lightbox-overlay").remove();
	$(".jquery-lightbox-move").remove();
	$("body").append(div1);
	$("body").append(div2);
	
	validform=FormValid.validbyformid(executeFrmZL);  
	//点击保存按钮  status为-1
	 $("#addZGDataAdd").click(function() { 
	  $("#STATUS").val("-1");
	  if(validform.check()){
		if($("#executeFrmZL").validationButton()) { 
			var data = Form2Json.formToJSON(executeFrmZL); 
			var data1 = defaultJson.packSaveJson(data);
			$.ajax({
				url :controlletnameUrldaFu+"?insert",
				data : data1,
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
					xAlert("信息提示","答复信息保存成功",1);
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
	 $("#addZGDataUpdate").click(function() { 
	  $("#STATUS").val("0");
	  if(validform.check()){
		if($("#executeFrmZL").validationButton()) {
			var data = Form2Json.formToJSON(executeFrmZL); 
			var data1 = defaultJson.packSaveJson(data);
			$.ajax({
				url :controlletnameUrldaFu+"?insert",
				data : data1,
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
					xAlert("信息提示","答复信息保存成功",1);
					 $("#closeZGPageAdd").click();
					 jQuery("#grid_table").jqGrid().trigger("reloadGrid");
			      }
			}); 
		 }
	   }else {
		   return;
	   }
	}); 
});
	
	$("#addZhenggaiCotent").click(function(){
		$('#newAddZhengGaiContent').attr("data-target","XMZK-XMMC");
		$('#XMZK-XMMC').removeData("bs.modal");
		$("#XMZK-XMMC").empty();
		$('#XMZK-XMMC').modal({
			backdrop: 'static'
		});
		
		$.get("${ctx}/jsp/business/zhenggai/zhenggai_content_add.jsp",function(data) {
			//console.info(data);
			$("#XMZK-XMMC").empty();
			$("#XMZK-XMMC").html("");
			$("#XMZK-XMMC").html(data);
		}); 
	});   



function init(){
	 $.ajax({
			url :controlletnameUrlZgContent+"?queryContentList",
			data : {"ZHENGGAI_UID":ZHENGGAI_UID},
			cache : false,
			async :	false,
			dataType : "json",  
			type : 'post',
			success : function(response) { 
				var obj = response.obj; 
				obj.forEach(function(obj){ 
					 var $tr =  $("<tr style='height:auto'></tr>");
					 $tr
					 .append($("<td style='display:none'></td>")
							 .append($("<input type='hidden'  fieldname='BZGF_UID' value="+obj.BZGF_UID+">"))
							 .append($("<input type='hidden'  fieldname='ZHENGGAI_CONTENT_UID' value="+obj.ZHENGGAI_CONTENT_UID+">"))
					         .append($("<input type='hidden' fieldname='WEIGUI_LEVEL' value='"+obj.WEIGUI_LEVEL+"'>")))
                     .append($("<td class='col-sm-2' style='padding:26px;'></td>").append($("<input type='text' size='20' maxlength='30' class='col-sm-12 col-xs-12' readOnly='readOnly' fieldname='XUHAO' value="+obj.XUHAO+">")))
					 .append($("<td class='col-sm-2' style='padding:26px;'></td>").append($("<input type='text' size='20' maxlength='30' class='col-sm-12 col-xs-12' readOnly='true' fieldname='' value="+obj.P_BZGF+">")))
					 .append($("<td class='col-sm-2' style='padding:26px;'></td>").append($("<input type='text' size='20' maxlength='30' class='col-sm-12 col-xs-12' readOnly='true' fieldname='' value="+welguiLevelStatus(obj)+">")))
					 .append($("<td class='col-sm-2'></td>").append($("<textarea rows='2'   class='col-sm-12 col-xs-12'   multiline='true' readOnly='true' fieldname='CONTENT'>"+obj.CONTENT+"</textarea>")))
					 .append($("<td class='col-sm-2'></td>").append($("<textarea rows='2'   class='col-sm-12 col-xs-12'   multiline='true' readOnly='true' fieldname='DESCRIPTION'>"+obj.DESCRIPTION+"</textarea>")))
					 .append($("<td class='col-sm-2'></td>").append($("<textarea rows='2'   class='col-sm-12 col-xs-12'   multiline='true' fieldname='DAFU_CONTENT'></textarea>")));
					 $("#t").append($tr);  
				});  
		      }
		}); 
}

function selectFile(obj){
	var targetUid = $('#target_uid').val();
	var file_type = $(obj).attr('file_type');
	setFileData("PM_ZG_DAFU","ZG_DAFU_UID",targetUid,file_type);
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
</script>