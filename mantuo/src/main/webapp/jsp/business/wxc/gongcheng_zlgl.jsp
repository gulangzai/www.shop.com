<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<title><fmt:message key="ui.title" /></title> 

<style>
.tab-content{
height:780px;
}
.page-content {
    background-color: #fff;
    position: relative;
    margin: 0;
    padding:3px 0px 0px 3px;
}
/**.btn-purple001, .btn-purple001:focus {
    border-color: #F5F5F5;
    background-color: #F5F5F5!important;
}**/
</style>
</head>
<body>
	<div class="main-container" id="main-container">
            <div class="tab-content">
				<div class="page-content">
                <form method="post"  role="form" class="form-inline"  id="executeForm" width="100%" sftyle="padding-top:-5px;margin-top:-15px;"> 
                    <input id="PROJECT_UID" type="hidden" name="PROJECT_UID" fieldname="PROJECT_UID" operation="="/>  
                     <input id="" type="hidden" name="DISTRICT" fieldname="DISTRICT" value="no" />
                    <div class="col-xs-12 align-right" style="margin-top:-15px;">
						<button id="btnSaveFile" class="btn btn-white btn-default btn-round" type="button">保存</button>
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
																<span style="margin:4px auto;" class="btn  btn-white btn-default btn-round " onclick="selectFile(this);" file_type="10202" >
																	<i class="ace-icon fa fa-cloud-upload bigger-100"></i>
																	工程资料管理
																</span>
																<table id="tableXuhuan" class="table table-striped"  >
																      <thead align="center">
																	     <tr><td>文件名</td><td>大小</td><td  >编辑</td></tr>
																	  </thead>
																	<tbody align="center" style="cursor: pointer;" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" file_type="10202">
																	  
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
    
  /** $("td:odd").style={"border":"1px solid red"};
   
   $(".showFileTab").each(function(i){
			var tempFjlb = $(this).attr("fjlb");
			if(tempFjlb==fjlb){
				$(this).find("tr[class='template-download']").each(function(j){
					counts++;
				});
			}
		});
   
   **/

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