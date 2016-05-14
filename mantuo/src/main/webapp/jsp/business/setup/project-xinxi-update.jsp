<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>

<div class=" width-90 height-auto">
  <div class="" >
  <div class="modal-body">
 	  <form method="post" role="form" class="form-horizontal"  id="executeFrmData" > 
      	 <input id="PROJECT_UID" type="hidden" name="PROJECT_UID" fieldname="PROJECT_UID" value="" />
      	 <input type="hidden" id="target_uid" fieldname="target_uid"  />
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					项目名称：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <input datatype="*" nullmsg = "请输入项目名称！"  placeholder="请输入项目名称！" type="text" name="PROJECT_NAME" id="PROJECT_NAME" fieldname="PROJECT_NAME"  style="width: 97%;" />
				</div>
	  		
			</div>
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					项目简介：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <textarea datatype="*" nullmsg="请输入项目的简介！"  placeholder="请输入项目的简介！" type="text"  
					 name="PROJECT_DESC" id="PROJECT_DESC" fieldname="PROJECT_DESC" style="width: 97%;"  rows="4" class="col-xs-11 col-sm-11" ></textarea>
				</div>
			</div>
			
			<div class="form-group" >
			  <label class="col-sm-2 control-label no-padding-right">
					项目所在城市：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-2" >
					 <select  datatype="*" nullmsg = "请选择项目所在的省！"  style="width: 150px;border:1px solid #ddd;" name="PROVINCE" id="PROVINCE" fieldname="PROVINCE"  class="col-xs-11 col-sm-11" onchange="checkProvince(this.id)" >
					 <!-- <option>&nbsp;&nbsp;选择省&nbsp;&nbsp;</option>  -->
					 </select>
				</div>
				<div class="col-sm-2"  >
					 <select  datatype="*" nullmsg = "请选择项目所在的地级市！"  style="width: 150px;border:1px solid #ddd;overflow-y:scroll;" name="CITY" id="CITY"  fieldname="CITY"  class="col-xs-11 col-sm-11" onchange="checkCity(this.id);">
					   <option  >&nbsp;&nbsp;&nbsp;&nbsp;地级市&nbsp;&nbsp;</option>
					 </select>
				</div>
				<div class="col-sm-2"  >
					 <select  datatype="*" nullmsg = "请选择项目所在的市、县、区！"  style="width: 150px;border:1px solid #ddd;overflow-y:scroll;"  name="DISTRICT" id="CONTRY" fieldname="DISTRICT" onchange="checkContry(this.id);" class="col-xs-11 col-sm-11" >
					  <option value="" >&nbsp;&nbsp;&nbsp;&nbsp;市、县、区&nbsp;&nbsp;</option>
					 </select>
				</div>
		    </div>
		    
		    <div class="form-group">
			    <label class="col-sm-2 control-label no-padding-right">
					项目地址：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <input  datatype="*" nullmsg="请输入项目的地址！"  placeholder="请输入项目的地址！ " type="text" name="PROJECT_ADDRESS"
					  id="PROJECT_ADDRESS01" fieldname="PROJECT_ADDRESS" style="width: 97%;" />
				</div>
			</div>
			
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
				     开工日期：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input   data-date-format="yyyy-mm-dd"  nullmsg="请输入开工日期!" 
					 placeholder="请输入开工的日期!" type="text" name="BEGIN_DATE" id="BEGIN_DATE_ONE" fieldname="BEGIN_DATE" class="col-xs-11 col-sm-11"  />
				</div>
			    <label class="col-sm-2 control-label no-padding-right">
				   完工日期：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input  data-date-format="yyyy-mm-dd"  nullmsg="请输入完工日期!"  
					 placeholder="请输入完工的日期!" type="text" name="END_DATE" id="END_DATE_TWO" fieldname="END_DATE" class="col-xs-11 col-sm-11"  />
				</div>
			</div>
			
			<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
				     占地面积：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input   datatype="/^\d{0,11}\.(\d{1,2})$|^\d{0,11}$/" errormsg="填写的数字格式不对，此处最多保留2位小数"   nullmsg="请输入项目的 占地面积!!" placeholder="请输入项目的占地面积!" type="text" name="ZHANDI_MIANJI" 
					 id="ZHANDI_MIANJI" fieldname="ZHANDI_MIANJI" class="col-xs-11 col-sm-11"  />
				</div>
			    <label class="col-sm-2 control-label no-padding-right">
				   建筑面积：<span class="required-indicator">*</span>
			    </label>
				<div class="col-sm-4">
					 <input   datatype="/^\d{0,11}\.(\d{1,2})$|^\d{0,11}$/" errormsg="填写的数字格式不对，此处最多保留2位小数"  nullmsg="请输入项目的建筑面积!"  placeholder="请输入项目的建筑面积!" type="text" name="JIANZHU_MIANJI" 
					 id="JIANZHU_MIANJI" fieldname="JIANZHU_MIANJI" class="col-xs-11 col-sm-11"  />
				</div>
			</div>				
			
			<div class="form-group" >
			    <label class="col-sm-2 control-label no-padding-right">
					总投资：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input  datatype="/^\d{0,14}\.(\d{1,2})$|^\d{0,14}$/" errormsg="金额格式不对，此处最多保留2位小数"  nullmsg="金额格式不对，此处最多保留2位小数"  placeholder="请输入项目的总投资金!" 
					 type="text" name="ZONG_TOUZI" id="ZONG_TOUZI" fieldname="ZONG_TOUZI" class="col-xs-11 col-sm-11"  />
				</div>
				
		  		<label class="col-sm-2 control-label no-padding-right">
					创建日期：<span class="required-indicator">*</span>
				</label>
				
				<div class="col-sm-4">
					 <input   datatype="*"   data-date-format="yyyy-mm-dd" nullmsg="请输入项目的创建日期!"  
					 placeholder="请输入项目的创建日期!" type="text" name="CREATED_DATE" id="CREATED_DATE_THREE" fieldname="CREATED_DATE" class="col-xs-11 col-sm-11"  />
				</div>
			</div>	
		    
		   <div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
					项目缩略图：&nbsp;
				</label>
				
				<div class="col-sm-9">
					<span class="btn btn-primary btn-sm col-sm-3" id="addFileSpan" onclick="selectFile(this);" file_type="10001" >
						<i class="ace-icon fa fa-upload"></i>
						<span>附件上传</span>
					</span>
					<table  role="presentation" class="table table-striped">
						<tbody class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="10001"></tbody>
					</table>
				</div>
			</div>
		    <div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					图片预览：
				</label>
				<div class="col-sm-9" id="xmShowPictures"  >
				  <input type="hidden" value="1" id="xmShowTP"  />
				  <img id="showPICTURES"  src=""  height="200" width="400"  />
			    </div>
		   </div>
		<div class="form-group" >
		<div class=" widget-footer">
   		<div class="" style="float:right;padding-right:60px;padding-bottom: 30px;" >
		    <button class="btn btn-success btn-sm" id="addsaveProjectInfo">保存</button>
		</div>
        </div> 
	</div>	   
     </form>
        <form method="post" role="form" class="form-horizontal"  id="executeFrm" >
		     <input id="PROJECT_UIDS" type="hidden" name="PROJECT_UID" fieldname="PROJECT_UID" operation="=" value="" />
		     <div class="form-group" >
		     
	  			<label class="col-sm-2 control-label no-padding-right" >
					项目参建单位列表：
				</label>
		  
								<a data-target="" id="new" data-toggle="modal" style="margin-left:50%;">
									<button id="btn_new"  class="btn btn-link btn-sm" type="button"><i class="colorshow ace-icon glyphicon glyphicon-plus"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">添加</span></button>
								</a>
							
							</div>
		    </form> 
		    <div class="form-group" >
		    
		        
	  			<table sortname="PROJECT_COMPANY_UID" multiselect=false  rownum="10" sortorder="desc"   class="auto_startgrid"  id="grid_table" 
									action="${ctx}/buprojectcompany/buProjectCompanyController?query ">
							<tr>
								<!-- <th name="PROJECT_NAME"  width="8" formatter="jqgridactions" align="center">操作</th>  -->
								<th name="COMPANY_TYPE" width="10" align="center" formatter="showtype" >单位类型</th>
								<th name="COMPANY_NAME" width="10" align="center" >单位名称</th>
								<th name="PROJECT_FUZEREN" width="10" align="center" >项目联系人</th>
								<th name="PROJCET_FUZEREN_TEL" width="10" align="center" >电话</th>
								<th name="PROJECT_COMPANY_UID" width="10" align="center" formatter="doUpdate" >操作</th>
							</tr>
						</table>
				 	
		   </div>
	</div>
      
</div>
</div>
<div id="jldwUser-input" class="modal"></div>
<jsp:include page="/jsp/file_upload/fileupload_util.jsp" flush="true" />	
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/convertJson.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/combineQuery.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/default.js?version=20131206"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/bootstrap-validation.js"> </script>
<script type="text/javascript">
var validform;
var controlletname="${pageContext.request.contextPath }/project/buProjectController";
//点击保存按钮
var flag = true;
var scripts =[null];

var setHeight;
ace.load_ajax_scripts(scripts, function() {
	setFormValues();
	$("#PROJECT_UIDS").val($("#project_uid").val());
	gridwidth = $("#executeFrmData").width();
	JqgridManage.initJqgrid(grid_table,executeFrm,gridwidth);
		setHeight = $(window).height();
		 
			//改变浏览器大小自适应
				$(window).on(
						'resize.jqGrid',
						function() {
							$("#grid_table").jqGrid(    //jqGridtable 自适应width
									'setGridWidth',
									$("#inside").width());
						});
				$(window).triggerHandler('resize.jqGrid');
	// $("#grid_table").setGridHeight(setHeight - 200); 

});
//添加
$(function(){
	
	
	$('#new').click(function() {
		$('#new').attr("data-target","jldwUser-input");
		$('#jldwUser-input').removeData("bs.modal");
		$("#jldwUser-input").empty();
		$('#jldwUser-input').modal({
			backdrop: 'static'
		});

		$.get("${ctx}/jsp/business/danwei/xmdanwei-add.jsp?project_uid="+$("#project_uid").val(),function(data) {
			$("#jldwUser-input").empty();
			$("#jldwUser-input").html("");
			$("#jldwUser-input").html(data);
		});
	});
});

//单位类型
function showtype(cellvalue, opts, rowObject){
	var company_type= rowObject.COMPANY_TYPE;
	var thtml ="<div class=\"hidden-sm hidden-xs btn-group\" >";
	if(company_type=="JS"){
		thtml +="<span class=\" bigger-100\">建设单位</span>";
	}else if(company_type=="DJ"){
		thtml +="<span class=\" bigger-100\">代建单位</span>";
	}else if(company_type=="JL"){
		thtml +="<span class=\" bigger-100\">监理单位</span>";
	}else if(company_type=="SG"){
		thtml +="<span class=\" bigger-100\">施工总承包</span>";
	}else if(company_type=="SJ"){
		thtml +="<span class=\" bigger-100\">设计单位</span>";
	}
	
    
    thtml +="</div>";
return thtml;
}
//操作
function doUpdate(cellvalue, opts, rowObject){
	var project_company_uid = rowObject.PROJECT_COMPANY_UID;
	   /* showHtml ="<div class='hidden-sm hidden-xs action-buttons'>";	
	    showHtml +="<a class=\"blue\" title=\"查看\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
	    showHtml +=	" onclick=\"showeidtLlog('"+project_company_uid+"');\">";
	    showHtml +="<span class=\"glyphicon  glyphicon-file bigger-110\"></i>";
	    showHtml +="</a> &nbsp;";
	    */
	    //if(checkTabAuthority($("#project_uid").val(),$("#projectUserId").val(),"12060102")){
		var  showHtml ="<a class=\"blue\" title=\"修改\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		     showHtml +=	" onclick=\"eidtLlog('"+project_company_uid+"');\">";
		     showHtml +="<span class=\"glyphicon  glyphicon-edit bigger-110\"></i>";
		     showHtml +="</a> &nbsp;";
	   // }
	   // if(checkTabAuthority($("#project_uid").val(),$("#projectUserId").val(),"12060103")){
		    showHtml +="<a class=\"\" title=\"删除\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		    showHtml +=	" onclick=\"removeData('"+project_company_uid+"');\">";
		    showHtml +="<span class=\"glyphicon colorshow1 glyphicon-trash bigger-110\"></i>";
		    showHtml +="</a>&nbsp;";
	    //}
	        	
	return 	showHtml;
}
//修改单位
function eidtLlog(project_company_uid)
{
	
		$('#new').attr("data-target","jldwUser-input");
		$('#jldwUser-input').removeData("bs.modal");
		$("#jldwUser-input").empty();
		$('#jldwUser-input').modal({
			backdrop: 'static'
		});

		$.get("${ctx}/jsp/business/danwei/xmdanwei-update.jsp?PROJECT_COMPANY_UID="+project_company_uid+"&project_id="+$("#project_uid").val(),function(data) {
			$("#jldwUser-input").empty();
			$("#jldwUser-input").html("");
			$("#jldwUser-input").html(data);
		});
	

}
function removeData(project_company_uid){
	bootbox.confirm("确认删除吗？", function(result) {
	if (result) {						
		$.ajax({
			url :'${ctx}/buprojectcompany/buProjectCompanyController?deleteId',
			data : {"project_company_uid":project_company_uid},
			cache : false,
			async :	false,
			dataType : "json",  
			type : 'post',
			success : function(response) {
				if(response.success){
					xAlert("信息提示","删除成功",1);
				}else{
					xAlert("信息提示","删除失败,请联系管理员",1);
				}
				jQuery("#grid_table").jqGrid().trigger("reloadGrid");

		      }
		});
					
	} else {
		return;
	}
});
}

$(function() {	
	  $(".footer-content").remove();//去除页脚下的版本符号
      DatePicker.datepicker("#BEGIN_DATE_ONE");
      DatePicker.datepicker("#END_DATE_TWO");
      DatePicker.datepicker("#CREATED_DATE_THREE");
      var xmUid = $("#project_uid").val();
      /**查询 修改之前的 文件地址 及文件名*/
      queryFileData('BU_PROJECT',xmUid);

      $("#PROJECT_UID").val(xmUid);
      setFormValuesForSheng();
      
	validform=FormValid.validbyformid(executeFrmData);
	$("#addsaveProjectInfo").click(function() {
	  if(validform.check()){
		if($("#executeFrmData").validationButton()) {
			//生成json串
			var data = Form2Json.formToJSON(executeFrmData);
			//组成保存json串格式
			var data1 = defaultJson.packSaveJson(data);
			$.ajax({
				url :controlletname+"?update",
				data : data1,
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {//考虑到城市code不存在的情况 给予提示
				if(response.msg == null){
					  xAlert("信息提示","修改信息成功。",1);
					  //说明当前城市的code不存在。
					 }else{
					   xAlert("信息提示","修改信息成功",1);
					 }
			      }
			});
		 }
	   }else {
	   	xAlert("信息提示","填写的数字格式不正确,请重新确认！",1);
		   return;
	   }
	});
});

/**附件上传选择 文件**/
function selectFile(obj){
	var targetUid = $('#target_uid').val();
	
	var file_type = $(obj).attr('file_type');
	setFileData("BU_PROJECT","PROJECT_UID",targetUid,file_type);

    document.getElementById("fileupload_btn").click();
    }

/**项目图片显示 预览**/

function queryxmShowPictyres(xmUid){
          $.ajax({
					url : "${pageContext.request.contextPath }/fileUploadUtilController?queryFileByType&targetUid="+xmUid+"&file_type=10001",
					cache : false,
					async :	false,
					dataType : "json",
					success : function(result) {
					    var html = "";
					    var pcurl = "";
						if(result.obj!=null&&result.obj!=""){
							$.each(result.obj,function(){
								pcurl =  this.url;
								if(pcurl.indexOf(".jpg") || pcurl.indexOf(".png") || pcurl.indexOf(".gif")){
								 html +="<img id=\"showPICTURES\" height=\"200\" width=\"400\" src="+pcurl+" />";
								}else{
								 html="";
								}
							 
							});
						}
						$("#xmShowPictures").append(html);

					}
				});
}

function setFormValues(){
 	      $.ajax({
				url :'${ctx}/project/buProjectController?queryDetailById&PROJECT_UID='+$("#project_uid").val(),
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(result) {
					
					if(result.obj!=null&&result.obj!=""){
							$.each(result.obj,function(){
							    $("#PROJECT_NAME").val(this.PROJECT_NAME);
							    $("#PROJECT_DESC").html(this.PROJECT_DESC);
							    $("#PROJECT_ADDRESS01").val(this.PROJECT_ADDRESS);
							    $("#BEGIN_DATE_ONE").val(this.BEGIN_DATE);
							    $("#END_DATE_TWO").val(this.END_DATE);
							    $("#ZHANDI_MIANJI").val(this.ZHANDI_MIANJI);
							    $("#JIANZHU_MIANJI").val(this.JIANZHU_MIANJI);
							    $("#ZONG_TOUZI").val(this.ZONG_TOUZI);
							    /**初始化 省市区  查询出的是 省市区的（sys_region）的主键id REGION_CODE**/
							    if(this.PROVINCE !="" && this.CITY !="" && this.DISTRICT !=""){
							      queryCity(this.PROVINCE);
							      queryContry(this.CITY);
							    }
							    $("#PROVINCE").val(this.PROVINCE);
							    $("#CITY").val(this.CITY);
							    $("#CONTRY").val(this.DISTRICT);
							    var re = /[\u4000-\uFFFF]/g;
					            var periods = this.CREATED_DATE.replace(re, '');
					            var str = periods.substr(0,10);
							    $("#CREATED_DATE_THREE").val(str);
						});

			      }
			   }
			});

}

/**初始化 下拉框值（省）**/
function setFormValuesForSheng(){
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
<form method="post" id="queryFormById"  >
   <table class="B-table" width="100%">
   <!--可以再此处加入hidden域作为过滤条件 -->
   	<TR  style="display:none;">
     	<TD class="right-border bottom-border">
      	<INPUT type="text"  kind="text" id="XMPROJECT_UID"  fieldname="PROJECT_UID" value="" operation="="/>
      	</TD>
		<TD class="right-border bottom-border">
		</TD>
     </TR>
   </table>
</form>	
		