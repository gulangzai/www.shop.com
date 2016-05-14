<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%> 
<!-- Modal -->
<div class="modal-dialog width-40 height-auto">
  <div class="modal-content" >
   <div class="widget-header widget-header-large">
   		<div class="widget-toolbar">
			<a href="#" data-action="close"  onclick="m_close()">
				<i class="ace-icon fa fa-times"></i>
				</a>
		</div>
    	<h3 id="myModalLabel" class="blue bigger">添加工程整改内容</h3>
  </div>
  <div class="modal-body">
 	  <form method="post" role="form" class="form-horizontal"  id="zhenggai_executeFrmZL" > 
 	   <!-- 获取 项目 的 uid -->    	
      	  <input id="ZLCONTENTROJECT_UID" type="hidden" name="ROJECT_UID" fieldname="PROJECT_UID"  />
      	  <input  id="XM_PRJ_STRUC_UID"  type="hidden"  fieldname="PRJ_STRUC_UID" value="" />  
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
				     对应标准规范：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-9">
				 <input  id="BZGF_UID" type="hidden" name="BZGF_UID" nullmsg="请填写对应标准规范"  style="width:440px;" placeholder="请填写对应标准规范" type="text"  fieldname="BZGF_UID" />
				 <input  id="NODE_CONTENT" datatype="*"  name="NODE_CONTENT" nullmsg="请填写对应标准规范"  style="width:440px;" placeholder="请填写对应标准规范" type="text"  fieldname="NODE_CONTENT" />
				</div>
				<div class="col-sm-1">
				<button type="button" class="btn btn-link btn-sm" onclick="addWgnr()" title="添加违规内容"><i class="ace-icon glyphicon glyphicon-plus blud"></i></button>
				</div>
			</div>
			
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					违规等级：<span class="required-indicator">*</span>
				</label>
				 <select  id="WEIGUI_LEVEL" style="width:220px;"  datatype="*" nullmsg="请选择类型" fieldname="WEIGUI_LEVEL" class="col-xs-11 col-sm-11" >
			        <option value="1" >一般</option>
			        <option value="2" >较为严重</option>
			        <option value="3" >严重</option> 
			      </select>
			</div>
			
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
				       违规内容：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
				   <textarea id="CONTENT"  datatype="*" nullmsg="请填写 违规内容"  placeholder="请输入违规内容" name="CONTENT"  type="text"  fieldname="CONTENT" class="col-xs-11 col-sm-11" ></textarea>
				 </div> 
			</div>
			
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
				       备注：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
				   <textarea id="DESCRIPTION" datatype="*" nullmsg="请填写 备注"  placeholder="请输入备注"  name="DESCRIPTION"  type="text"  fieldname="DESCRIPTION" class="col-xs-11 col-sm-11" ></textarea>
				 </div> 
			</div> 
     </form>
	</div>
   <div class="modal-footer">
     <button id="addZhenggaiContentAdd" class="btn btn-1 btn-sms btn-round" type="button">&nbsp;保存&nbsp;</button>
     <button id="closeZhenggaiContentAdd"  class="btn btn-2 btn-sms pull-right btn-round"  onclick="m_close();" aria-hidden="true">关闭</button>
   </div>
</div>
</div> 
<script type="text/javascript">
var validform1;
var controlletnameUrl="${pageContext.request.contextPath }/pmxianchang/pmXianchangController";
//点击保存按钮
$(function() {	
	validform1=FormValid.validbyformid(zhenggai_executeFrmZL);
	$('#ZLCONTENTROJECT_UID').val($('#project_uid').val());
	$("#addZhenggaiContentAdd").click(function() { 
	   if(validform1.check()){
		 var xuhao=$(".table-striped tr").length;
		 var BZGF_UID = $("input[name='BZGF_UID']").val();
		 var WEIGUI_LEVEL = $("#WEIGUI_LEVEL").val();
		 var WEIGUI_LEVEL_text = $("#WEIGUI_LEVEL").text();
		 var NODE_CONTENT = $("input[name='NODE_CONTENT']").val();
		 var CONTENT = $("#CONTENT").val();
		 var DESCRIPTION = $("#DESCRIPTION").val();
		//alert(BZGF_UID+'--'+CONTENT);
		 var $tr =  $("<tr></tr>");
		 $tr.append($("<td></td>").append($("<input type='text' style='width:30px;'  fieldname='XUHAO' value="+xuhao+">")))
		 .append($("<td style='display:none'></td>").append($("<input type='hidden'  fieldname='BZGF_UID' value="+BZGF_UID+">")).append($("<input type='hidden'  fieldname='WEIGUI_LEVEL' value="+WEIGUI_LEVEL+">")))
		 
		 .append($("<td></td>").append($("<input type='text' style='width:120px;' fieldname='' value="+NODE_CONTENT+">")))
		 .append($("<td></td>").append($("<input type='text' style='width:120px;' fieldname='' value="+WEIGUI_LEVEL_text+">")))
		 .append($("<td></td>").append($("<input type='text' style='width:120px;' fieldname='CONTENT' value="+CONTENT+">")))
		 .append($("<td></td>").append($("<input type='text' style='width:120px;' fieldname='DESCRIPTION' value="+DESCRIPTION+">")));
		 $("#t").append($tr);
		 m_close();
	   }else {
		   return;
	   }
	}); 
});

//关闭窗口
function m_close(){
	    $("#XMZK-XMMC").css("display","none");
		$("#XMZK-XMMC").attr("aria-hidden","true");
		$("#XMZK-XMMC").attr("class","modal");
		/**页面框的背景 样式去除**/
		$(".modal-backdrop").attr("class",""); 
};
 
function addWgnr(){
	window.open("${pageContext.request.contextPath }/jsp/business/zhenggai/guifan-select2.jsp?guifan_uid=");
}

//设置回调
function doCallback(str){ 
	var html = "";
	var strs= new Array(); //定义一数组 
	strs=str.split(","); //字符分割 
	console.info(str);
	 for(var i=0;i<strs.length;i++){
		//BZGF_UID
		$("#BZGF_UID").val(strs[0]); 
		$("#NODE_CONTENT").val(strs[1]);  
	 } 
}
</script>