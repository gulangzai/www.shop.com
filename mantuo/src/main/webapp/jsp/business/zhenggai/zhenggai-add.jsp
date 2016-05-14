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
    	<h3 id="myModalLabel" class="blue bigger">添加工程整改</h3>
  </div>
  <div class="modal-body">
 	  <form method="post" role="form" class="form-horizontal"  id="executeFrmZL" > 
 	   <!-- 获取 项目 的 uid -->    	
      	 <input id="ZLROJECT_UID" type="hidden" name="ROJECT_UID" fieldname="PROJECT_UID"  />
      	  <input type="hidden" id="target_uid" fieldname="target_uid"  />
	  	  <input type="hidden" id="STATUS" fieldname="STATUS"  value="0"/>
	  		<input type="hidden" id="guifanuidstr" />
	  		<div class="form-group" >
	  		
	  		   <label class="col-sm-2 control-label no-padding-right" >
					整改编号： 
				</label>
				<div class="col-sm-4">
				  <input class="col-xs-11 col-sm-10" id="ZG_CODE" disabled="disabled" datatype="*" nullmsg="请选择项目现场的施工部位"    placeholder="请选择项目现场的施工部位" type="text"  fieldname="ZG_CODE" />
				</div>
				 
	  			<label class="col-sm-2 control-label no-padding-right" >
					整改类型：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <select  style=""  nullmsg="请选择类型" datatype="*" fieldname="ZG_TYPE" class="col-xs-11 col-sm-10" >
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
				   <input  id="getXMJGMC" value="" datatype="*" nullmsg="请输入现场进度"    placeholder="请输入现场进度" type="text"  fieldname="JINDU" class="col-xs-11 col-sm-11"/>
				</div>
			</div>
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					施工单位：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
				 <input id="uid1" value="" type="hidden"/>
				 <input  id="SGDW" value="" datatype="*" disabled="disabled" nullmsg="请选择施工单位"    placeholder="请选择施工单位" type="hidden"  fieldname="SGDW" class="col-xs-11 col-sm-11"/>
				 <input  id="SGDWS" value="" datatype="*" disabled="disabled" nullmsg="请选择施工单位"    placeholder="请选择施工单位" type="text"   class="col-xs-11 col-sm-11"/>
				<i class="ace-icon glyphicon glyphicon-list bigger-110" onclick="selectGuifan1();" style="cursor:pointer" title="选择项目参建单位"></i>
				</div>
			</div>
			
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					监理单位：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
				 <input  id="JLDW" value="" datatype="*" disabled="disabled" nullmsg="请选择监理单位"   placeholder="请选择监理单位" type="hidden"  fieldname="JLDW" class="col-xs-11 col-sm-11"/>
				<input  id="JLDWS" value="" datatype="*" disabled="disabled" nullmsg="请选择监理单位"   placeholder="请选择监理单位" type="text"   class="col-xs-11 col-sm-11"/>
				<i class="ace-icon glyphicon glyphicon-list bigger-110" onclick="selectGuifan2();" style="cursor:pointer" title="选择项目参建单位"></i>
				</div>
			</div>
			
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
				        项目经理：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <input class="col-xs-11 col-sm-10" id="XMJL" value="" datatype="*" nullmsg="请输入项目经理"   placeholder="请输入项目经理" type="text"  fieldname="XMJL" />
				</div>
				
				<label class="col-sm-2 control-label no-padding-right" >
				        总监：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <input class="col-xs-11 col-sm-10"  id="ZJ"  value="" datatype="*" nullmsg="请输入总监"    placeholder="请输入总监" type="text"  fieldname="ZJ" />
				</div> 
			</div>
			
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
				       发放单位：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
				 <input  id="FAFANG_CORP" value="" disabled="disabled" datatype="*" nullmsg="请选择发放单位"    placeholder="请选择发放单位" type="hidden"  fieldname="FAFANG_CORP" class="col-xs-11 col-sm-11"/>
				 <input  id="FAFANG_CORPS" value="" disabled="disabled" datatype="*" nullmsg="请选择发放单位"    placeholder="请选择发放单位" type="text"   class="col-xs-11 col-sm-11"/>
				 <i class="ace-icon glyphicon glyphicon-list bigger-110" onclick="selectGuifan3();" style="cursor:pointer" title="选择项目参建单位"></i>
				</div> 
			</div>
			
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
				        发放人：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <input  class="col-xs-11 col-sm-10" id="getXMJGMC" value="" datatype="*" nullmsg="请输入发放人"    placeholder="请输入发放人" type="text"  fieldname="FAFANG_USER" />
				</div>
				
				<label class="col-sm-2 control-label no-padding-right" >
				        发放日期：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <input   id="FAFANG_DATE" class="REPORT_DATE1" datatype="*" nullmsg="默认当前日期"  style="height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;" placeholder="默认当前日期" data-date-format="yyyy-mm-dd" fieldname="FAFANG_DATE" class="col-xs-11 col-sm-10"/>
				</div> 
			</div>
			 
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					要求整改完成日期：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
				 <input  class="REPORT_DATE2" id="zg_date" datatype="*" nullmsg="请选择要求整改完成日期"    placeholder="请选择要求整改完成日期" style="width: 100px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"   data-date-format="yyyy-mm-dd"  fieldname="zg_date" class="col-xs-11 col-sm-10"/>
				</div>
			</div>
			
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					参检人员：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
				    <input  id="getXMJGMC" value="" datatype="*" nullmsg="请输入参检人员"    placeholder="请输入参检人员" type="text"  fieldname="canjian"  class="col-xs-11 col-sm-11"/>
				</div>
			</div>
	  	
	  	<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" > 
				</label>
				<div class="col-sm-10" >
					<span class="col-xs-11 col-sm-11" > 
							<!-- #section:custom/widget-box.options -->
							<div style="float: right;">
							 <a data-target="" id="newAddZhengGaiContent" data-toggle="modal">
								<button type="button" class="btn btn-link btn-sm" id="addZhenggaiCotent"  title="添加违规内容"><i class="ace-icon glyphicon glyphicon-plus blud"></i></button>
							</a>
							</div> 
							<!-- /section:custom/widget-box.options -->
							<div class="row">
							<div class="col-sm-12">
							 <table class="table table-striped table-bordered table-hover table-condensed" id="zhenggaiContentTable" >
								<thead class="thin-border-bottom">
									<tr>
										<th class="col-sm-2" id="XH">序号</th>
										<th class="col-sm-2" fieldname="WG_DENGJI2">对应标准规范</th>
										<th class="col-sm-2" fieldname="WG_DENGJI3">违规等级</th>
										<th class="col-sm-2" fieldname="WG_DENGJI3" formatter="formatWgsj">违规内容</th>
										<th class="col-sm-2" fieldname="WG_DENGJI4" formatter="formatWgsj">详细描述</th>
									</tr>
								</thead>
								 <tbody id="t"> 
								 </tbody>
							</table>  
							</div>
						 </div>
					</span>
				</div>
			</div>
		 
           <div class="form-group">
					<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
						附件：&nbsp;
					</label>
					
					<div class="col-sm-9">
						<span style="margin:4px auto;" class="btn  btn-white btn-default btn-round " onclick="selectFile(this);" file_type="10105" >
							<i class="ace-icon fa fa-cloud-upload bigger-100"></i>
								上传
						</span>
						<table  role="presentation" class="table table-striped">
							<tbody onlyView="true"  showType="width:120px;height:120px;quanxian:true;heightss:160px;heights:40px;del:true;attr:multi;preview:false" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="10105" fjlb=""></tbody>
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
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/plugins/lightbox/jquery.min.js"></script>		
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/plugins/lightbox/jquery.lightbox.min.js"></script>
	
<script type="text/javascript">
var validform;
var controlletnameAdd="${pageContext.request.contextPath }/pmjiancha/pmJianchaBzController";
var controlletnameUrlZgAdd="${pageContext.request.contextPath }/zhenggai/pmZhengGaiController";
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
	//自动编号
	$("#ZG_CODE").val(generateMixed(12));
	var date = new Date().Format("yyyy-MM-dd");
	//console.info(date); 
	$("#FAFANG_DATE").val(date);  
	
    //新建
	$("#addZGDataAdd").click(function() {
	  $("#STATUS").val("-1");
	  var finish_date =$("#zg_date").val();//获取完成日期;
	   var d1 = new Date(finish_date.replace(/\-/g, "\/")); 
		 var d2 = new Date(date.replace(/\-/g, "\/"));
	  if(validform.check()&&d1>=d2){
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
		 var finish_date =$("#zg_date").val();//获取完成日期;
		   var d1 = new Date(finish_date.replace(/\-/g, "\/")); 
			 var d2 = new Date(date.replace(/\-/g, "\/"));
		  if(validform.check()&&d1>=d2){
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