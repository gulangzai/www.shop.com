<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<title><fmt:message key="ui.title" /></title> 

<style>
</style>
</head>
<body>
	<div class="main-container" id="main-container">
				<div class="page-content">
                <form method="post"  role="form" class="form-inline"  id="executeForm" width="100%"> 
                    <input id="PROJECT_UID" type="hidden" name="PROJECT_UID" fieldname="PROJECT_UID" operation="="/>  
                     <input id="" type="hidden" name="DISTRICT" fieldname="DISTRICT" value="no" />
                    <div class="col-xs-12 align-right">
						<!-- <button id="close" class="btn btn-primary btn-sm" onclick="window.close();" type="button">关闭</button> -->
						<button id="btnSaveFile" class="btn btn-primary btn-sm" type="button">保存</button>
					</div>     	
					    <div class="col-xs-12 col-sm-12 widget-container-col ui-sortable" id="FABAOFANGAN">
						  <div class="widget-box ui-sortable-handle" id="setShouSuo">
							<div class="widget-header" style="margin:0px">
								<h5 class="widget-title"><i class="upload-icon ace-icon fa fa-cloud-upload blue fa-1x"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">附件上传</span></h5>
								<div class="widget-toolbar">
								<a href="#" data-action="collapse"> <i
									class="1 ace-icon fa fa-chevron-up bigger-125"></i> </a>
							    </div>
					        </div>
							<div class="widget-body" id="showWJ">
								<div class="widget-main">
											<div class="row">
												  <div class="col-xs-12">
														<input type="hidden" id="target_uid" fieldname="target_uid" >
															<div class="">
																<div class="col-sm-8">
																<span class="btn btn-primary btn-sm" onclick="uploadFileData(this);" file_type="10004">
																	<i class="ace-icon fa fa-upload"></i> <span>&nbsp;应急预案&nbsp;</span>
																</span>
																<table id="loadyjya" role="presentation" class="table table-striped">
																	<tbody style="cursor: pointer;" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" file_type="10004">
																	</tbody>
																</table>
																</div>
															</div>	
															<div class="">
															 <div class="col-sm-8">
																<span class="btn btn-primary btn-sm" onclick="selectFile(this);" file_type="10002">
																	<i class="ace-icon fa fa-upload"></i><span>流程图</span>
																</span>
																<table id="loadpc" role="presentation" class="table table-striped">
																	<tbody style="cursor: pointer;" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" file_type="10002">
																	</tbody>
																</table>
																</div>
															</div>	
													<script type="text/javascript">
														var $path_base = "/";
													</script>
												</div>
								         </div>
									   </div>
									</div>
								</div> 
						    </div> 
					</form>
				</div>
		</div>
		<jsp:include page="/jsp/file_upload/fileupload_util.jsp" flush="true" />
<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
 <script
		src="${pageContext.request.contextPath }/assets/js/jquery-ui.custom.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
</body>
<script type="text/javascript">
var scripts =[null];
var xmUid ="";
var controllername ="${pageContext.request.contextPath}/project/buProjectController";
ace.load_ajax_scripts(scripts, function() {

	xmUid = $("#project_uid").val();
    queryFileData('BU_PROJECT',xmUid);
	$("#PROJECT_UID").val(xmUid);
	$("#btnSaveFile").click(function(){
	    var tb = $("#loadpc tr > td:eq(0)").text();
	    if(tb !="" && tb !=null){
		    //图片上传 正则使用 验证图片上传的 格式
			var content = new RegExp(/\.jpg$|\.png$|\.gif$/i);
			var result = content.test(tb);
			if(!result){
			  alert("您上传的图片格式不正确,请您删除后继续上传!");
			  //$("#btnSaveFile").attr("disabled","disabled");
			  return;
			}
		}
	    // $("#btnSaveFile").removeAttr("disabled");
	    var data = Form2Json.formToJSON(executeForm);
		var data1 = defaultJson.packSaveJson(data);
		defaultJson.doInsertJson(controllername + "?update", data1); 
	
	});
});

function uploadFileData(obj){
      var targetUid = $('#target_uid').val();
	  var file_type = $(obj).attr('file_type');
	  setFileData("BU_PROJECT","PROJECT_UID",targetUid,file_type);
      document.getElementById("fileupload_btn").click();
}
//附件上传	
function selectFile(obj){
	  var targetUid = $('#target_uid').val();
	  var file_type = $(obj).attr('file_type');
	 // var loadPicturus = document.getElementById("loadpc");
	 // var loadyuan = document.getElementById("loadyjya");
	 // var pclength = loadPicturus.rows.length;
	 // var loadContent = loadyuan.rows.length;
	 // alert(loadContent+"===="+pclength);
	  //if(pclength != 0  && file_type=="10002"){
	    deleteFile("BU_PROJECT",targetUid,file_type);
	 // }
	  setFileData("BU_PROJECT","PROJECT_UID",targetUid,file_type);
      document.getElementById("fileupload_btn").click();
	}

function deleteFile(table,targetUid,file_type){
  $.ajax({
		url : controllername+"?deleteFile",
		data : {"target_table":table,"target_uid":targetUid,"file_type":file_type},
		cache : false,
		async :	false,
		dataType : "json",
		success : function(result){
		  queryFileData('BU_PROJECT',xmUid);
		}
	});
}



</script>
</html>