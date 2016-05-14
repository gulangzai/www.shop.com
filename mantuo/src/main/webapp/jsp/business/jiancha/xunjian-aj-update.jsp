<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>

<%
 String NODE_TYPE = request.getParameter("NODE_TYPE");
 String p_uid = request.getParameter("p_uid");
 String p_name = request.getParameter("p_name");
 String bzgf_uid = request.getParameter("bzgf_uid");
 String xunjian_uid = request.getParameter("xunjian_uid");
 %>
<!-- Modal -->
<div class="modal-dialog width-80 height-auto">
  <div class="modal-content" >
   		<div class="widget-header widget-header-large">
   		<div class="widget-toolbar">
			<a href="#" data-action="close" data-dismiss="modal">
				<i class="ace-icon fa fa-times"></i>
				</a>
		</div>
    	<h3 id="myModalLabel" class="blue bigger">修改安全检查</h3>
  </div>
  <div class="modal-body">

 	  <form method="post" role="form" class="form-horizontal"  id="executeFrm" > 
 	   <!-- 获取 项目 的 uid -->    	
      	 <input type="hidden" id="guifanuidstr" />
      	 <input type="hidden" id="XUNJIAN_UID" fieldname="XUNJIAN_UID"/>
      	 <input type="hidden" id="target_uid" fieldname="target_uid"  />
      	 
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					项目名称：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					<input type="text" id="pName" datatype="*" disabled="disabled" class="col-xs-11 col-sm-11" >
				</div>	 
	  		
			</div>
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					检查级别：
				</label>
				<div class="col-sm-4" id="jcjb">
					
				</div>	 
	  			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					检查日期：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					<input data-date-format="yyyy-mm-dd" datatype="*" nullmsg = "请输入检查日期"  placeholder="请输入检查日期" type="text" name="XUNJIAN_DATE" id="XUNJIAN_DATE" fieldname="XUNJIAN_DATE" />
				</div>
			</div>
			</div>

	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					违规内容：
				</label>
				<div class="col-sm-10">
					<span class="col-xs-11 col-sm-11">
						
							<!-- #section:custom/widget-box.options -->
							<div style="float: right;">
								<button type="button" class="btn btn-link btn-sm" onclick="addWgnr();" title="添加违规内容"><i class="ace-icon glyphicon glyphicon-plus blud">添加违规内容</i></button>
							</div>
						
							<!-- /section:custom/widget-box.options -->
							
							<table class="table table-striped table-bordered table-hover" >
								<thead class="thin-border-bottom">
									<tr>
										<th>序号</th>
										<th>违规等级</th>
										<th>违规事件</th>
										<th>对应标准规范</th>
										<th>详细描述</th>
									</tr>
								</thead>
								<tbody id="t"></tbody>
							</table>
						
						
					</span>
				</div>
			</div>
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					检查结论：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <textarea id="JIERUN"  datatype="*" type="text"  fieldname="JIERUN" class="col-xs-11 col-sm-11" rows="5"></textarea>
					
				</div>
			</div>
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					参检人员：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <input id="CANJIAN"  datatype="*" type="text"  fieldname="CANJIAN" class="col-xs-11 col-sm-11" />
					
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
					附件：&nbsp;
				</label>
				<div class="col-sm-10">
					<span class="btn  btn-white btn-default btn-round" id="addFileSpan" onclick="selectFile(this);" file_type="10601" >
						<i class="ace-icon fa fa-upload"></i>
						<span>附件上传</span>
					</span>
					<table  role="presentation" class="table table-striped">
						<tbody class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="10601"></tbody>
					</table>
				</div>
			</div>	
     </form>
     
	</div>

  <div class="modal-footer">
    <button class="btn btn-1 btn-sms" id="addsaveUserInfo">保存</button>
    <button class="btn btn-2 btn-sms pull-right" id="btnClose" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
</div>
</div>
<form method="post" id="queryFormById"  >
   <table class="B-table" width="100%">
   <!--可以再此处加入hidden域作为过滤条件 -->
   	<TR  style="display:none;">
     	<TD class="right-border bottom-border">
      	<INPUT type="text"  kind="text" id="XUNJIAN_UID"  fieldname="XUNJIAN_UID" value="<%=xunjian_uid%>" operation="="/>
      	</TD>
		<TD class="right-border bottom-border">
		</TD>
     </TR>
   </table>
</form>	
<jsp:include page="/jsp/file_upload/fileupload_util.jsp" flush="true" />
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/convertJson.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/combineQuery.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/default.js?version=20131206"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/bootstrap-validation.js"> </script>
<script type="text/javascript">
var validform;
var controlletname="${pageContext.request.contextPath }/pmjiancha/pmJianchaBzController";
var xunjiancontrollet = "${pageContext.request.contextPath }/pmjiancha/pmXunjianController";
var xjContentcontrollet = "${pageContext.request.contextPath }/pmjiancha/pmXunjianContentController";
//点击保存按钮
$(function() {	

	init();
	
	validform=FormValid.validbyformid(executeFrm);
	
	$("#addsaveUserInfo").click(function() {
	  if(validform.check()){
		if($("#executeFrm").validationButton()) {
			//生成json串
			var data = Form2Json.formToJSON(executeFrm);
			//组成保存json串格式
			var data1 = defaultJson.packSaveJson(data);
			
			$.ajax({
				url :xunjiancontrollet+"?update",
				data : data1,
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
					xAlert("信息提示","修改成功",1);
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

function init(){
	$("#pName").val($("#project_name").val());
	$("#PROJECT_UID").val($("#project_uid").val());
    setFormValues();
    getJcjb();
}
function setFormValues(){
	var data = combineQuery.getQueryCombineData(queryFormById,null,null);
	var data1 = {
		msg : data
	};
	$.ajax({
		url : xunjiancontrollet+"?query",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var resultobj = defaultJson.dealResultJson(response.msg);
			setTab(resultobj.XUNJIAN_UID);
			$("#executeFrm").setFormValues(resultobj);	
			queryFileData('PM_XUNJIAN',resultobj.XUNJIAN_UID);
		}
	});
}

function getJcjb(){
	$.ajax({
		url :controlletname+"?queryJcjb",
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var html = "";
			$(response.obj).each(function(index,element){
				if(index==0){
					html += "<input type='radio'  checked='checked' name='JIANCHA_LEVEL' fieldname='JIANCHA_LEVEL_UID' value='"+element.JIANCHA_LEVEL_UID+"'>"+element.LEVEL_NAME;
				}else{
					html += "<input type='radio' style='margin-left:8px;' name='JIANCHA_LEVEL' fieldname='JIANCHA_LEVEL_UID' value='"+element.JIANCHA_LEVEL_UID+"'>"+element.LEVEL_NAME;
				}
				
			});
			$('#jcjb').html(html);
	      }
	});
}

//设置表格
function setTab(xunjian_uid){
	$.ajax({
		url :xjContentcontrollet+"?queryByXunjianUid",
		cache : false,
		async :	false,
		data:{"xunjianUid":xunjian_uid},
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var html = "";
			var guifanuidstr = $('#guifanuidstr').val();

			$(response.obj).each(function(index,element){
				var num = index+1;
				var sjuid = element.BZGF_UID;
				guifanuidstr = sjuid+","+guifanuidstr;
				$('#guifanuidstr').val(guifanuidstr);
				html +="<tr>";
				html +="<td>"+num+"</td>";
				html +="<td><input type='hidden' fieldname='BZGF_UID' value='"+element.BZGF_UID+"'>";
				html +="<input type='hidden' fieldname='WEIGUI_LEVEL' value='"+element.WEIGUI_LEVEL+"'>";
				html +="<input style='width: 55px;' type='text' disabled='disabled' fieldname='WEIGUI_LEVEL_NAME' value='"+element.WEIGUI_LEVEL_NAME+"'></td>";
				html +="<td><input type='text' fieldname='CONTENT' value='"+element.CONTENT+"'></td>";
				html +="<td><input type='text' fieldname='P_BZGF' value='"+element.BZGF+"'></td>";
				html +="<td><input type='text' fieldname='DESCRIPTION' value='"+element.DESCRIPTION+"' ></td>";
				html +="</tr>";
				
			});
			$('#t').html(html);
	      
	      }
	});
}

function addWgnr(){
	var guifanuidstr = $("#guifanuidstr").val(); 
	var project_uid = $("#project_uid").val();
	window.open("${pageContext.request.contextPath }/jsp/business/jiancha/guifan-select2.jsp?guifanuidstr="+guifanuidstr+"&project_uid="+project_uid);
}

//设置回调
function doCallback(str){
	
	//原来拥有的规范 (去了逗号)
	var _str = $("#guifanuidstr").val().replace(",","");
	/**
	 * 这里有三种情况需要处理     
	 * 第一只叠加(str里面在_str基础上添加了新的内容) 直接append在后面即可
	 * 第二只删除(str里面在_str基础上减少了) 直接remove
	 * 第三有删除(str里面在_str基础上有减少有增加)有叠 先remove再append
	*/
	//alert();

	var strs = new Array(); //定义一数组 
	strs = str.split(",,"); //字符分割 
	//长度为1说明没有添加 长度为2说明有添加
	if(strs.length == 1){
		//有删除，处理删除
		if(strs[0]!=_str){
			delTab(strs[0]);
		}
		
	}else if(strs.length == 2){
		//有删除，处理删除
		if(strs[0]!=_str){
			delTab(strs[0]);
		}
		$.ajax({
			url :controlletname+"?queryWgnr",
			cache : false,
			async :	false,
			data :{"guifan_uid":strs[1]},
			dataType : "json",  
			type : 'post',
			success : function(response) {
				var html = "";
				$(response.obj).each(function(index,element){
					var num =  $("#t tr").length+index+1;
					html +="<tr>";
					html +="<td>"+num+"</td>";
					html +="<td><input type='hidden' fieldname='BZGF_UID' value='"+element.BZGF_UID+"'>";
					html +="<input type='hidden' fieldname='WEIGUI_LEVEL' value='"+element.WEIGUI_LEVEL+"'>";
					html +="<input type='text' disabled='disabled' fieldname='WEIGUI_LEVEL_NAME' value='"+element.WEIGUI_LEVEL_NAME+"'></td>";
					html +="<td><input type='text' fieldname='CONTENT' value='"+element.NODE_CONTENT+"'></td>";
					html +="<td><input type='text' fieldname='P_BZGF' value='"+element.P_BZGF+"'></td>";
					html +="<td><input type='text' fieldname='DESCRIPTION' ></td>";
					html +="</tr>";
					
				});
				$('#t').append(html);
		      }
		});
	}
	
}

//处理表格删除
function delTab(str){
	var v = str.split(",");
	$("#t input[name='BZGF_UID']").each(function(){
		var flag = true; 
		for(var i = 0;i<v.length;i++){
			if($(this).val()== v[i]){
				flag = false; 
			}
		}
		 
		if(flag){
			$(this).parent().parent().remove();
		}
	});
}


//附件上传	
function selectFile(obj){
	var targetUid = $('#target_uid').val();
	var file_type = $(obj).attr('file_type');
	setFileData("PM_XUNJIAN","XUNJIAN_UID",targetUid,file_type);
    document.getElementById("fileupload_btn").click();	
	
}

</script>
		