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
                     <input type="hidden" name="DISTRICT" fieldname="DISTRICT" value="no" />
                    <div class="col-xs-12 align-right">
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
																<span class="btn btn-primary btn-sm" onclick="selectFile(this);" file_type="10003">
																	<i class="ace-icon fa fa-upload"></i> <span>&nbsp;基坑施工设计&nbsp;</span>
																</span>
																<table   class="table table-striped">
																	<tbody style="cursor: pointer;" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" file_type="10003">
																	</tbody>
																</table>
																</div>
															</div>	
															<div class="">
															 <div class="col-sm-8">
																<span class="btn btn-primary btn-sm" onclick="selectFile(this);" file_type="10005">
																	<i class="ace-icon fa fa-upload"></i><span>&nbsp;水文地质文件&nbsp;</span>
																</span>
																<table  class="table table-striped">
																	<tbody style="cursor: pointer;" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" file_type="10005">
																	</tbody>
																</table>
																</div>
															</div>	
															<div class="">
																<div class="col-sm-8">
																<span class="btn btn-primary btn-sm" onclick="selectFile(this);" file_type="10006">
																	<i class="ace-icon fa fa-upload"></i> <span>&nbsp;其他项目文件&nbsp;</span>
																</span>
																<table   class="table table-striped">
																	<tbody style="cursor: pointer;" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" file_type="10006">
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
	    var data = Form2Json.formToJSON(executeForm);
		var data1 = defaultJson.packSaveJson(data);
		defaultJson.doInsertJson(controllername + "?update", data1); 
	
	});
});

//附件上传	
function selectFile(obj){
	  var targetUid = $('#target_uid').val();
	  var file_type = $(obj).attr('file_type');

	  setFileData("BU_PROJECT","PROJECT_UID",targetUid,file_type);
      document.getElementById("fileupload_btn").click();
	}

</script>
</html>