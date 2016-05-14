<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%
	String userUid = request.getParameter("userUid");
%>
<!-- Modal -->
<div class="modal-dialog width-70 height-auto">
  <div class="modal-content" >
   		<div class="widget-header widget-header-large">
   		<div class="widget-toolbar">
			<a href="#" data-action="close" data-dismiss="modal">
				<i class="ace-icon fa fa-times"></i>
				</a>
		</div>
    	<h3 id="myModalLabel" class="blue bigger">添加项目信息</h3>
  </div>
  <div class="modal-body">

 	  <form method="post" role="form" class="form-horizontal"  id="executeFrm" >      	
      	<input type="hidden" id="target_uid" fieldname="target_uid"  />
      	<input type="hidden" id="CREATED_USER" fieldname="CREATED_USER" value="<%=userUid %>" />
      	
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					项目名称：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <input datatype="*" nullmsg = "请输入项目名称"  placeholder="请输入项目名称" type="text" name="PROJECT_NAME" id="PROJECT_NAME" fieldname="PROJECT_NAME"  style="width: 97%;" />
				</div>
	  		
			</div>
			
			<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					项目所在城市：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-2" >
					 <select  datatype="*" nullmsg = "请选择项目所在的省！"  style="width: 150px;border:1px solid #ddd;overflow-y:scroll;" name="PROVINCE" id="PROVINCE" fieldname="PROVINCE"  class="col-xs-11 col-sm-11" onchange="checkProvince(this.id)" >
				     </select>
				</div>
				<div class="col-sm-2"  >
					 <select  datatype="*" nullmsg = "请选择项目所在的地级市！"  style="width: 150px;border:1px solid #ddd;overflow-y:scroll;" name="CITY" id="CITY"  fieldname="CITY"  class="col-xs-11 col-sm-11" onchange="checkCity(this.id);">
					   <option>&nbsp;&nbsp;地级市&nbsp;&nbsp;</option>
					 </select>
				</div>
				<div class="col-sm-2"  >
					 <select  datatype="*" nullmsg = "请选择项目所在的市、县、区！"  style="width: 150px;border:1px solid #ddd;overflow-y:scroll;"  name="DISTRICT" id="CONTRY" fieldname="DISTRICT" onchange="checkContry(this.id);" class="col-xs-11 col-sm-11" >
					  <option value="" >&nbsp;&nbsp;市、县、区&nbsp;&nbsp;</option>
					 </select>
				</div>
			</div>
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" for="MIMA">
					项目地址：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <input datatype="*" nullmsg="请输入项目地址"  placeholder="请输入项目地址" type="text" name="PROJECT_ADDRESS" id="PROJECT_ADDRESS" fieldname="PROJECT_ADDRESS" style="width: 97%;" />
				</div>
	  	
			</div>
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					开工日期：
				</label>
				<div class="col-sm-4">
					 <input data-date-format="yyyy-mm-dd"  placeholder="请输入开工日期" type="text" name="BEGIN_DATE" id="BEGIN_DATE" fieldname="BEGIN_DATE" class="col-xs-11 col-sm-11"  />
				</div>
		  		<label class="col-sm-2 control-label no-padding-right">
					完工日期：
				</label>
				<div class="col-sm-4">
					 <input  data-date-format="yyyy-mm-dd" placeholder="请输入完工日期" type="text" name="END_DATE" id="END_DATE" fieldname="END_DATE" class="col-xs-11 col-sm-11"  />
				</div>
	  		
			</div>			
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					占地面积(平方米)：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input datatype="*" nullmsg="请输入占地面积" placeholder="请输入占地面积" type="text" name="ZHANDI_MIANJI" id="ZHANDI_MIANJI" fieldname="ZHANDI_MIANJI" class="col-xs-11 col-sm-11"  />
				</div>
		  		<label class="col-sm-2 control-label no-padding-right">
					建筑面积(平方米)：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input datatype="*" nullmsg="请输入建筑面积"  placeholder="请输入建筑面积" type="text" name="JIANZHU_MIANJI" id="JIANZHU_MIANJI" fieldname="JIANZHU_MIANJI" class="col-xs-11 col-sm-11"  />
				</div>
	  		
			</div>			
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					总投资(元)：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input datatype="*" nullmsg="请输入总投资"  placeholder="请输入总投资" type="text" name="ZONG_TOUZI" id="ZONG_TOUZI" fieldname="ZONG_TOUZI" class="col-xs-11 col-sm-11"  />
				</div>
	  		
			</div>			
	  		<div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
					项目简介：&nbsp;
				</label>
				<div class="col-sm-10">
					<textarea style="width: 97%;"  rows="5" id="PROJECT_DESC" fieldname="PROJECT_DESC"></textarea>
				</div>
			</div>
	  		<div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
					项目缩略图：&nbsp;
				</label>
				<div class="col-sm-10">
					<span class="btn btn-primary btn-sm col-sm-3" id="addFileSpan" onclick="selectFile(this);" file_type="10001" >
						<i class="ace-icon fa fa-upload"></i>
						<span>附件上传</span>
					</span>
					<table  role="presentation" class="table table-striped">
						<tbody class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="10001"></tbody>
					</table>
				</div>
			</div>
			
	  </form>
     
	</div>

  <div class="modal-footer">
    <button class="btn btn-success btn-sm" id="addsaveUserInfo">保存</button>
    <button class="btn btn-danger btn-sm pull-right" id="btnClose" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
</div>
</div>

<jsp:include page="/jsp/file_upload/fileupload_util.jsp" flush="true" />	
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
	DatePicker.datepickerid("#BEGIN_DATE");
	DatePicker.datepickerid("#END_DATE");
	validform=FormValid.validbyformid(executeFrm);
	
	$("#addsaveUserInfo").click(function() {
	if($("#CONTRY").val() !="" && $("#CITY").val() !="" && $("#PROVINCE").val() !=""){
	  if(validform.check()){
		if($("#executeFrm").validationButton()) {
			//生成json串
			var data = Form2Json.formToJSON(executeFrm);
			//组成保存json串格式
			var data1 = defaultJson.packSaveJson(data);
			
			$.ajax({
				url :'${ctx}/project/buProjectController?insert',
				data : data1,
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
					xAlert("信息提示","新增成功",1);
					var a =window.parent;
					a.location.reload();
					window.close();
			      }
			});
			
		 }
	   }else {
		   return;
	   }
	   
	   }else{
	   	xAlert("信息提示","项目所在的城市必须都不能为空!",1);
	     return;
	   }
	});
});

function selectFile(obj){
	var targetUid = $('#target_uid').val();
	var file_type = $(obj).attr('file_type');
	setFileData("BU_PROJECT","PROJECT_UID",targetUid,file_type);
    document.getElementById("fileupload_btn").click();	
	
}

/**初始化 下拉框值（省）**/
function setFormValues(){
 $.ajax({
    url:'${ctx}/project/buProjectController?queryCity',
    cache:false,
    async:false,
    dataType:"json",
    type:'post',
    success:function(result){
    var thtml ="" ;
    var html = "";
      if(result.obj!=null&&result.obj!=""){
		$.each(result.obj,function(){
		    html ="<option value=\"\" >&nbsp;&nbsp;省份&nbsp;&nbsp;</option>";
			thtml +="<option value=\""+this.REGION_CODE+"\" >&nbsp;&nbsp;"+this.REGION_NAME+"</option>";
							
			});
							
		}
		$("#PROVINCE").append(html);
		$("#PROVINCE").append(thtml);
    
    }
 
 });
 

}
/**选择的省**/
function checkProvince(id){
   var region_code = ($("#"+id).val());
   $("#PROVINCE").val(region_code);
   queryCity(region_code);
   queryContry(region_code+"11111111");
  
}

/** 查询省下的 城市 **/
function queryCity(code){
  if(code !="" && code != null){
	    $.ajax({
          url:'${ctx}/project/buProjectController?queryCity',
          data:{"REGION_CODE":code},
          cache:false,
          async:false,
          dataType:'json',
          type:'post',
          success:function(result){
         // alert(result.obj+"ssssss");
              var  thtml ="" ;
		      if(result.obj!=null&&result.obj!=""){
				$.each(result.obj,function(){
					thtml +="<option value=\""+this.REGION_CODE+"\" >&nbsp;&nbsp;"+this.REGION_NAME+"</option>";
									
					});
									
		         }
		         
		         var html = "<option value=\" \">&nbsp;&nbsp;地级市&nbsp;&nbsp;</option>"; 
		         $("#CITY").empty();	
		         $("#CITY").append(html);			
		         $("#CITY").append(thtml);
          }
       
       });
       
      }else{
                 var html = "<option value=\" \">&nbsp;&nbsp;地级市&nbsp;&nbsp;</option>"; 
                 $("#CITY").empty();		
		         $("#CITY").append(html);			
        return;
      }
      
}

/**选择城市获取选择的 省的代码REGION_CODE**/
function checkCity(id){
	    var code = ($("#"+id).val());
	    $("#CITY").val(code);
        queryContry(code);
      
     
}
/**查询 市 县 区 **/
function queryContry(code){
if(code !="" && code != null){
 $.ajax({
          url:'${ctx}/project/buProjectController?queryCity',
          data:{"REGION_CODE":code},
          cache:false,
          async:false,
          dataType:'json',
          type:'post',
          success:function(result){
              var  thtml ="" ;
		      if(result.obj!=null&&result.obj!=""){
				$.each(result.obj,function(){
					thtml +="<option value=\""+this.REGION_CODE+"\" >&nbsp;&nbsp;"+this.REGION_NAME+"</option>";
									
					});
									
		         }
			         var html = "<option value=\" \">&nbsp;&nbsp;市、县、区&nbsp;&nbsp;</option>";
			         $("#CONTRY").empty();
			         $("#CONTRY").append(html);			
			         $("#CONTRY").append(thtml);
			     
		         
          }
       
       });
       
      }else{
           var html = "<option value=\" \">&nbsp;&nbsp;市、县、区&nbsp;&nbsp;</option>";
		   $("#CONTRY").empty();
		   $("#CONTRY").append(html);	
           return;
      }
      
}

function checkContry(id){
       var code = ($("#"+id).val());
	    $("#CONTRY").val(code);
}

</script>
		