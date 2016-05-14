<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ page import="com.ccthanking.framework.Constants"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/sys_resources/plugins/lightbox/themes/default/jquery.lightbox.css" />
<%
String id =request.getParameter("TYPE_UID");
String name =request.getParameter("TYPE");
%>
<!-- Modal -->
<div class="modal-dialog width-85 height-auto">
	<div class="modal-content" style="border: 0px;">
		

             
						<div   class="modal-body" class="col-xs-12" id="contentdivid2" style="padding: 0px;">
							<!-- PAGE CONTENT BEGINS -->
							<form  role="form" id="sbForm2" style="display: none;">
							
						 </form>
						 <div id="tableY" class="widget-box widget-color-blue">
						 <div class="widget-header">
												<h5 class="widget-title bigger lighter">
													<i class="ace-icon fa fa-table"></i>
													<%=name %>
												</h5>

											</div>
							<table  sortname="xuhao" multiselect=false  rownum="10"
									sortorder="asc" 
									class="auto_startgrid"   
									id="content_grid_table_view" 
									action="${pageContext.request.contextPath }/jcgl/sysBzjcController?queryTree&typeId=1" >
								<tr>
								    <th name="BZJC_NAME" width="120" align=center >检查指标</th>
									<th name="JC_ZHIBIAO" width="120" align=center >检查指标</th>
									<th name="JC_SSRY" width="120" align=center >实施人员</th>
									<th name="JC_BIAOZHUN" width="120" align=center >检查标准</th>
									<th name="JC_DZMS" width="120" align=center >检查动作描述</th>
									<th name="JC_CHENGGUO" width="120" align=center >检查成果</th>
								</tr>
							</table>
						</div>
							
						</div>


	</div>
</div>

<jsp:include page="/jsp/file_upload/fileupload_util.jsp" flush="true" />
<script type="text/javascript">

var scripts =[null];// ["jsp/business/project/JsProject.js"];
ace.load_ajax_scripts(scripts, function() {
		var gridwidth2=$("#contentdivid2").width();
		JqgridManage.initJqgridTree(content_grid_table_view,sbForm2,gridwidth2);
		 
		$("#content_grid_table_view").setGridHeight($(window).height()/2);
		$("#content_grid_table_view").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
		 //改变浏览器大小自适应
		 /*
		$(window).on('resize.jqGrid',function() {
						$("#grid_table").jqGrid('content_grid_table',$("#contentdivid").width());
					});
		$(window).triggerHandler('resize.jqGrid');*/
		
	});
</script>