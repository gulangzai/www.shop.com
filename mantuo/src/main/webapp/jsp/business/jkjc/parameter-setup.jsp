<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<!-- Modal -->
<%
  String pointitemId = request.getParameter("POINT_ITEM_UID");
  String JC_PRJ_ITEM_UID = request.getParameter("JC_PRJ_ITEM_UID");
 %>
<div class="modal-dialog width-35 height-auto">
  <div class="modal-content" >
   		<div class="widget-header widget-header-large">
   		<div class="widget-toolbar">
			<a href="#" data-action="close" data-dismiss="modal">
				<i class="ace-icon fa fa-times"></i>
				</a>
		</div>
    	<h3 id="myModalLabel" class="blue bigger">设置</h3>
  </div>
  <div class="modal-body">

 	  <form method="post" role="form" class="form-horizontal"  id="executeFrm" > 
	 	  <input type="hidden" id="PRJ_POINT_ITEM_UID" name="PRJ_POINT_ITEM_UID" fieldname="PRJ_POINT_ITEM_UID"  value="<%=pointitemId%>"/>     	
	 	  <input type="hidden" id="jcitemUid" value="<%=JC_PRJ_ITEM_UID%>"/>     	
	  		<div class="form-group" >
		  		<label class="col-sm-6 control-label no-padding-right">
					<span id="SINGLE_WARN_TXT">警戒线：</span><span class="required-indicator">*</span>
				</label>
				<div class="col-sm-6">
					 <input datatype="/^[\+]{0,1}(\d{0,6})\.{0,1}(\d{1,4})$/" nullmsg = "单次警戒线" errormsg="数字非负，长度最大是10位，小数点最多保留4位！"  placeholder="单次警戒线" type="text" name="SINGLE_WARN" id="SINGLE_WARN" fieldname="SINGLE_WARN"  />
				</div>
	  		
			</div>
			<div class="form-group" >
	  			<label class="col-sm-6 control-label no-padding-right" for="MIMA">
					<span id="TOTAL_WARN1_TXT" ></span><span class="required-indicator">*</span>
				</label>
				<div class="col-sm-6">
					 <input datatype="/^[\+-]{0,1}(\d{0,6})\.{0,1}(\d{1,4})$/" nullmsg="最小累计值"  errormsg="数字的长度最大是10位，小数点最多保留4位！" placeholder="最小累计值" type="text" name="TOTAL_WARN1" id="TOTAL_WARN1" fieldname="TOTAL_WARN1"  />
				</div>
	  	
			</div>
	  		<div class="form-group" >
	  			<label class="col-sm-6 control-label no-padding-right" for="MIMA">
					<span id="TOTAL_WARN2_TXT" ></span><span class="required-indicator">*</span>
				</label>
				<div class="col-sm-6">
					 <input datatype="/^[\+-]{0,1}(\d{0,6})\.{0,1}(\d{1,4})$/" nullmsg="最大累计值"  errormsg="数字的长度最大是10位，小数点最多保留4位！" placeholder="最大累计值" type="text" name="TOTAL_WARN2" id="TOTAL_WARN2" fieldname="TOTAL_WARN2"  />
				</div>
	  	
			</div>
			<div class="form-group">
				<label class="col-sm-6 control-label no-padding-right" for="MIMA">
					<span>作用范围：</span>
				</label>
				<div class="col-sm-6">
					 <input id="BTN_ALL" type="radio" name="zyy" value="all" checked="checked">本类型全部测点
					 <input id="BTN_WARN" type="radio" name="zyy" value="cd">仅该测点
				</div>
			</div>
			
	  		<!--<div class="form-group" >
		  		<label class="col-sm-4 control-label no-padding-right">
					上包络线：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-7">
					 <input datatype="n" nullmsg = "上包络线"  placeholder="上包络线" type="text" name="PROJECT_NAME" id="PROJECT_NAME" fieldname="PROJECT_NAME"   />
				</div>
	  		
			</div>
	  		<div class="form-group" >
	  			<label class="col-sm-4 control-label no-padding-right" for="MIMA">
					下包络线：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-7">
					 <input datatype="n" nullmsg="下包络线"  placeholder="下包络线" type="text" name="PROJECT_ADDRESS" id="PROJECT_ADDRESS" fieldname="PROJECT_ADDRESS"/>
				</div>
	  	
			</div>
	  		<div class="form-group" >
		  		<label class="col-sm-4 control-label no-padding-right">
					
				</label>
				<div class="col-sm-7">
					<input name="" type="radio" value="1" checked="checked">全部监测点
					<input name="" type="radio" value="2">仅本测点
				</div>
	  		
			</div>

     --></form>
     
	</div>

  <div class="modal-footer">
    <button class="btn btn-success btn-sm" id="addsaveUserInfo">保存</button>
    <button class="btn btn-danger btn-sm pull-right" id="btnClose" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
</div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/convertJson.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/combineQuery.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/default.js?version=20131206"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/bootstrap-validation.js"> </script>
<script type="text/javascript">
var validform;
//点击保存按钮
$(function() {	
    setFormValues();
	validform=FormValid.validbyformid(executeFrm);
	
	$("#addsaveUserInfo").click(function() {
	var minValue = parseFloat($("#TOTAL_WARN1").val());
	var maxValue = parseFloat($("#TOTAL_WARN2").val());
	if(minValue < maxValue){
	  if(validform.check()){
		if($("#executeFrm").validationButton()) {
			//生成json串
			//var data = Form2Json.formToJSON(executeFrm);
			//组成保存json串格式
			//var data1 = defaultJson.packSaveJson(data);
			$.ajax({
				url :'${ctx}/jkjc/jcPointPropertyController?update',
				//data : data1,
				data:{
					'PRJ_POINT_ITEM_UID':$('#PRJ_POINT_ITEM_UID').val(),
					'SINGLE_WARN':$('#SINGLE_WARN').val(),
					'TOTAL_WARN1':$("#TOTAL_WARN1").val(),
					'TOTAL_WARN2':$("#TOTAL_WARN2").val(),
					'zyfw':getRadioV('zyy'),
					'jcitemUid':$('#jcitemUid').val(),
					'project_uid':$('#project_uid').val()
					}, 
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
					window.parent.updateJcdTable($('#PRJ_POINT_ITEM_UID').val(), $('#SINGLE_WARN').val(), $("#TOTAL_WARN1").val(), $("#TOTAL_WARN2").val());
				    $("#btnClose").click();
					
			      }
			});
			
		 }
	   }else {
		   return;
	   }
	   }else{
	    xAlert("累计最小警戒线值不能超过累计最大警戒线值！请重新输入！");
	   }
	});
});


function setFormValues(){
	$.ajax({
		url : '${ctx}/jkjc/jcPointPropertyController?querybyPoint_Item_Id',
		data : {"POINT_ITEM_ID":'<%=pointitemId%>'},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var resultobj = defaultJson.dealResultJson(response.msg);
			//console.log(resultobj);
			$("#executeFrm").setFormValues(resultobj);	
		}
	});

		//console.log($("#typename").val());
    $("#SINGLE_WARN_TXT").text("单次"+$("#typename").val()+"变化警戒线：");
    $("#TOTAL_WARN1_TXT").text("累计最小"+$("#typename").val()+"警戒线：");
    $("#TOTAL_WARN2_TXT").text("累计最大"+$("#typename").val()+"警戒线：");

}

</script>
		