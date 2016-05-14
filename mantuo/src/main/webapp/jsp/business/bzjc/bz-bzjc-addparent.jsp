<%@ page language="java" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/sys_resources/plugins/lightbox/themes/default/jquery.lightbox.css" />
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>

<!-- Modal -->
<div class="modal-dialog width-60 height-auto">
  <div class="modal-content" >
   <div class="widget-header widget-header-large">
   		<div class="widget-toolbar">
			<a href="#" data-action="close" data-dismiss="modal">
				<i class="ace-icon fa fa-times"></i>
				</a>
		</div>
    	<h3 id="myModalLabel" class="blue bigger">添加飞行标准检查父节点</h3>
  </div>
  <div class="modal-body">
 	  <form method="post" role="form" class="form-horizontal"  id="executeFrmZL" > 
 	   <!-- 获取 项目 的 uid -->    	
      	  <input id="ZLROJECT_UID" type="hidden" name="ROJECT_UID" fieldname="PROJECT_UID"  />
      	  <input type="hidden" id="target_uid" fieldname="target_uid"  />
	  	  <input type="hidden" id="STATUS" fieldname="STATUS"  value="0"/>
	  	  <input type="hidden" id="LEVEL" fieldname="LEVEL"  value="1"/> 
	  	  <input type="hidden" id="JC_TYPE" fieldname="JC_TYPE"  value="FX"/> 
	  	  
	  	  
	  	  <input type="hidden" id="guifanuidstr" />
	  	   
	  	   <div class="form-group" > 
	  		   <label class="col-sm-2 control-label no-padding-right" >
					检查名称： 
				</label>
				<div class="col-sm-4">
				  <input  class="col-xs-11 col-sm-10" id="ZG_CODE"   datatype="*" nullmsg="请填写检查名称"    placeholder="请填写检查名称" type="text"  fieldname="BZJC_NAME" />
				</div>
			</div>
		
		<!-- <div class="form-group" >	
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
				    <input    data-date-format="yyyy-mm-dd"  class="col-xs-11 col-sm-10 REPORT_DATE1" id="PLAN_JC_DATE"  datatype="*" nullmsg="请填写计划完成时间"    placeholder="请填写计划完成时间" type="text"  fieldname="PLAN_JC_DATE" />
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
			 
			  -->	
			  
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
 <!--<button id="addZGDataCommit" class="btn btn-1 btn-sms btn-round" type="button">&nbsp;提交&nbsp;</button> -->  
     <button id="closeZGPageAdd"  class="btn btn-2 btn-sms pull-right btn-round" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
</div>
</div>
<div id="XMZK-XMMC" class="modal"></div>
<jsp:include page="/jsp/file_upload/fileupload_util.jsp" flush="true" />
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/plugins/lightbox/jquery.min.js"></script>		
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/plugins/lightbox/jquery.lightbox.min.js"></script>
	
<script type="text/javascript">
var validform;
var controlletnameAdd="${pageContext.request.contextPath }/pmjiancha/pmJianchaBzController";
var controlletnameUrlZgAdd="${pageContext.request.contextPath }/pmbzjc/pmBzjcController";
var scripts=[null];
function selectGuifan1(){ 
	window.open("${pageContext.request.contextPath }/jsp/business/danwei/xmdanwei-select.jsp?uid=1");
}
function selectGuifan2(){
	
	window.open("${pageContext.request.contextPath }/jsp/business/danwei/xmdanwei-select.jsp?uid=2");
}
function selectGuifan3(){ 
	window.open("${pageContext.request.contextPath }/jsp/business/danwei/xmdanwei-select.jsp?uid=3");
}

ace.load_ajax_scripts(scripts,function(){
	DatePicker.datepicker(".REPORT_DATE1"); 
    DatePicker.datepicker(".REPORT_DATE2");   
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
	
	
	validform=FormValid.validbyformid(executeFrmZL);
	$('#ZLROJECT_UID').val($('#project_uid').val());  
	var date = new Date().Format("yyyy-MM-dd");
	//console.info(date); 
	$("#FAFANG_DATE").val(date);  
	
    //新建
	$("#addZGDataAdd").click(function() {
	   //$("#STATUS").val("-1");
	   //  var finish_date =$("#zg_date").val();//获取完成日期;
	   //  var d1 = new Date(finish_date.replace(/\-/g, "\/")); 
	   //  var d2 = new Date(date.replace(/\-/g, "\/"));
	  if(validform.check()){
		if($("#executeFrmZL").validationButton()) {
			var data = Form2Json.formToJSON(executeFrmZL);
			var data1 = defaultJson.packSaveJson(data);
			$.ajax({
				url :controlletnameUrlZgAdd+"?insert",
				data : data1,
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
					xAlert("信息提示","整改信息新建成功",1);
					$("#closeZGPageAdd").click();
					 jQuery("#grid_table").jqGrid().trigger("reloadGrid");
			      }
			});
			
		 }
	   }else {
		   xAlert("信息提示","要求完成日期必须大于当前日期",1);
		   return;
	   }
	});
	//提交
	$("#addZGDataCommit").click(function() {
		 $("#STATUS").val("0");
		 //var finish_date =$("#zg_date").val();//获取完成日期;
		 //var d1 = new Date(finish_date.replace(/\-/g, "\/")); 
		 //var d2 = new Date(date.replace(/\-/g, "\/"));
		  if(validform.check()){
			if($("#executeFrmZL").validationButton()) {
				var data = Form2Json.formToJSON(executeFrmZL);
				var data1 = defaultJson.packSaveJson(data);
				$.ajax({
					url :controlletnameUrlZgAdd+"?insert",
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
			   xAlert("信息提示","要求完成日期必须大于当前日期",1);
		
			   return;
		   }
		});
	
	
	
	$("#addZhenggaiCotent").click(function(){
		<%--$('#newAddZhengGaiContent').attr("data-target","XMZK-XMMC");
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
		}); --%>
		addWgnr();
	}); 
});

//打开添加规范网页
function addWgnr(){ 
	var guifanuidstr = $("#guifanuidstr").val(); 
	window.open("${pageContext.request.contextPath }/jsp/business/jiancha/guifan-select2.jsp?guifanuidstr="+guifanuidstr+"&project_uid="+$("#project_uid").val());
} 

//设置回调
function doCallback(str){ 
	if(str==""){
		$('#guifanuidstr').val(str);
	}else{
		$('#guifanuidstr').val(str);
	}
	
	$.ajax({
		url :controlletnameAdd+"?queryWgnr",
		cache : false,
		async :	false,
		data :{"guifan_uid":str},
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var html = "";
			$("#t").empty();
			$(response.obj).each(function(index,element){
				var num = index+1; 
			    // num = $("#t tr").length+1;
				$("#t").append(
						$("<tr style='height:auto'></tr>")
						.append($("<td class='col-sm-2 col-xs-2' style='padding:26px;'><input type='text' class='col-sm-12 col-xs-12' readonly='readonly' fieldname='XUHAO' value='"+num+"'></td>"))
						.append($("<td class='col-sm-2 col-xs-2' style='padding:26px;'><input type='hidden' fieldname='BZGF_UID' value='"+element.BZGF_UID+"'><input type='text' readonly='readonly' class='col-sm-12 col-xs-12' value='"+element.BZGF+"'></td>"))
						.append($("<td class='col-sm-2 col-xs-2' style='padding:26px;'><input type='hidden' fieldname='WEIGUI_LEVEL' value='"+element.WEIGUI_LEVEL+"'><input type='text' readonly='readonly' class='col-sm-12 col-xs-12' value='"+element.WEIGUI_LEVEL_NAME+"'></td>"))
						.append($("<td class='col-sm-2 col-xs-2'><textarea rows='2'  readonly='readonly' class='col-sm-12 col-xs-12'   multiline='true' fieldname='CONTENT'>"+element.NODE_CONTENT+"</textarea></td>"))
						.append($("<td class='col-sm-2 col-xs-2'><textarea rows='2'   class='col-sm-12 col-xs-12'   multiline='true' fieldname='DESCRIPTION'></textarea></td>"))
						);
			}); 
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