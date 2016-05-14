<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>

<head>
<%
String XC_UID = request.getParameter("XC_UID");
String File_Type = request.getParameter("TYPE");
%>
</head>
<!-- Modal -->
<div class="modal-dialog width-50 height-auto">
  <div class="modal-content" >
   <div class="widget-header widget-header-large">
   		<div class="widget-toolbar">
			<a href="#" data-action="close" data-dismiss="modal">
				<i class="ace-icon fa fa-times"></i>
				</a>
		</div>
    	<h3 id="myModalLabel" class="blue bigger">附件信息</h3>
  </div>
  <div class="modal-body">
 	  <form method="post" role="form" class="form-horizontal"  id="executeFrmXcAddXcAdd" > 
 	   <!-- 获取 项目 的 uid -->    	
      	 <input id="XCROJECT_UID" type="hidden" name="ROJECT_UID" fieldname="PROJECT_UID"  />
      	  <input  id="XM_PRJ_STRUC_UID"  type="hidden"  fieldname="XIANCHANG_UID" value="" />
	  	  <input type="hidden" id="target_uid" fieldname="target_uid"  />
            <div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
					现场状况：&nbsp;
				</label>
				<div class="col-sm-9">
					<%--<span style="margin:4px auto;" class="btn  btn-white btn-default btn-round "  file_type="10009" >
						<i class="ace-icon fa fa-cloud-upload bigger-100"></i>
							现场进度状况
					</span>
					--%>
					<table  role="presentation" class="table table-striped">
						<tbody onlyView="true"   class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="" fjlb="media"></tbody>
					</table>
				</div>
			</div>
     </form>
     
	</div>

  <div class="modal-footer">
   
  </div>
</div>
</div>
<jsp:include page="/jsp/file_upload/fileupload_util.jsp" flush="true" />	
<script type="text/javascript">
var scripts =[null];
var xc_uid = "<%=XC_UID%>";
var type = "<%=File_Type%>";
ace.load_ajax_scripts(scripts, function() {
	//获取 附件的类别
	 $(".showFileTab").attr("file_type",type);
	 queryFileData('PM_XIANCHANG',xc_uid);
});

function selectFile(obj){
	var targetUid = $('#target_uid').val();
	var file_type = $(obj).attr('file_type');
	setFileData("PM_XIANCHANG","XIANCHANG_UID",targetUid,file_type);
    document.getElementById("fileupload_btn").click();	
	
}
</script>