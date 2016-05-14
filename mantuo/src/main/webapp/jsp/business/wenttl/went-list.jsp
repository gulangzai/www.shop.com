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
<body class="no-skin">
		<div class="main-container" id="main-container">
			<div class="main-content">
				<div class="page-content">
					<div class="page-content-area">
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<div id="user-profile-1" class="user-profile row">

									<div class="col-xs-12 col-sm-12">

										<div class="widget-box transparent">
											<div class="widget-header widget-header-small">
												<h4 class="widget-title blue smaller">
														<i class="ace-icon fa fa-rss orange"></i>
														Recent Activities
													</h4>

												<div class="widget-toolbar action-buttons">
													<a href="#" data-action="reload">
														<i class="ace-icon fa fa-refresh blue"></i>
													</a>
													&nbsp;
													<a href="#" class="pink">
														<i class="ace-icon fa fa-trash-o"></i>
													</a>
												</div>
											</div>

											<div class="widget-body">
												<div class="widget-main padding-8">
													<!-- #section:pages/profile.feed -->
													<div id="profile-feed-1" class="profile-feed">
														<div class="profile-activity clearfix">
															<div>
																<img class="pull-left" alt="Alex Doe's avatar" src="${ctx}/assets/avatars/avatar5.png" />
																<a class="user" href="#"> Alex Doe </a> changed his profile photo.
																<a href="#">Take a look</a>

																<div class="time">
																	<i class="ace-icon fa fa-clock-o bigger-110"></i> an hour ago
																</div>
															</div>

															<div class="tools action-buttons">
																<a href="#" class="blue">
																	<i class="ace-icon fa fa-pencil bigger-125"></i>
																</a>

																<a href="#" class="red">
																	<i class="ace-icon fa fa-times bigger-125"></i>
																</a>
															</div>
														</div>

														<div class="profile-activity clearfix">
															<div>
																<img class="pull-left" alt="Susan Smith's avatar" src="${ctx}/assets/avatars/avatar1.png" />
																<a class="user" href="#"> Susan Smith </a> is now friends with Alex Doe.
																<div class="time">
																	<i class="ace-icon fa fa-clock-o bigger-110"></i> 2 hours ago
																</div>
															</div>

															<div class="tools action-buttons">
																<a href="#" class="blue">
																	<i class="ace-icon fa fa-pencil bigger-125"></i>
																</a>

																<a href="#" class="red">
																	<i class="ace-icon fa fa-times bigger-125"></i>
																</a>
															</div>
														</div>

														<div class="profile-activity clearfix">
															<div>
																<i class="pull-left thumbicon fa fa-check btn-success no-hover"></i>
																<a class="user" href="#"> Alex Doe </a> joined
																<a href="#">Country Music</a> group.
																<div class="time">
																	<i class="ace-icon fa fa-clock-o bigger-110"></i> 5 hours ago
																</div>
															</div>

															<div class="tools action-buttons">
																<a href="#" class="blue">
																	<i class="ace-icon fa fa-pencil bigger-125"></i>
																</a>

																<a href="#" class="red">
																	<i class="ace-icon fa fa-times bigger-125"></i>
																</a>
															</div>
														</div>

														<div class="profile-activity clearfix">
															<div>
																<i class="pull-left thumbicon fa fa-picture-o btn-info no-hover"></i>
																<a class="user" href="#"> Alex Doe </a> uploaded a new photo.
																<a href="#">Take a look</a>

																<div class="time">
																	<i class="ace-icon fa fa-clock-o bigger-110"></i> 5 hours ago
																</div>
															</div>

															<div class="tools action-buttons">
																<a href="#" class="blue">
																	<i class="ace-icon fa fa-pencil bigger-125"></i>
																</a>

																<a href="#" class="red">
																	<i class="ace-icon fa fa-times bigger-125"></i>
																</a>
															</div>
														</div>

														<div class="profile-activity clearfix">
															<div>
																<img class="pull-left" alt="David Palms's avatar" src="${ctx}/assets/avatars/avatar4.png" />
																<a class="user" href="#"> David Palms </a> left a comment on Alex's wall.
																<div class="time">
																	<i class="ace-icon fa fa-clock-o bigger-110"></i> 8 hours ago
																</div>
															</div>

															<div class="tools action-buttons">
																<a href="#" class="blue">
																	<i class="ace-icon fa fa-pencil bigger-125"></i>
																</a>

																<a href="#" class="red">
																	<i class="ace-icon fa fa-times bigger-125"></i>
																</a>
															</div>
														</div>

														<div class="profile-activity clearfix">
															<div>
																<i class="pull-left thumbicon fa fa-pencil-square-o btn-pink no-hover"></i>
																<a class="user" href="#"> Alex Doe </a> published a new blog post.
																<a href="#">Read now</a>

																<div class="time">
																	<i class="ace-icon fa fa-clock-o bigger-110"></i> 11 hours ago
																</div>
															</div>

															<div class="tools action-buttons">
																<a href="#" class="blue">
																	<i class="ace-icon fa fa-pencil bigger-125"></i>
																</a>

																<a href="#" class="red">
																	<i class="ace-icon fa fa-times bigger-125"></i>
																</a>
															</div>
														</div>

														<div class="profile-activity clearfix">
															<div>
																<img class="pull-left" alt="Alex Doe's avatar" src="${ctx}/assets/avatars/avatar5.png" />
																<a class="user" href="#"> Alex Doe </a> upgraded his skills.
																<div class="time">
																	<i class="ace-icon fa fa-clock-o bigger-110"></i> 12 hours ago
																</div>
															</div>

															<div class="tools action-buttons">
																<a href="#" class="blue">
																	<i class="ace-icon fa fa-pencil bigger-125"></i>
																</a>

																<a href="#" class="red">
																	<i class="ace-icon fa fa-times bigger-125"></i>
																</a>
															</div>
														</div>

														<div class="profile-activity clearfix">
															<div>
																<i class="pull-left thumbicon fa fa-key btn-info no-hover"></i>
																<a class="user" href="#"> Alex Doe </a> logged in.
																<div class="time">
																	<i class="ace-icon fa fa-clock-o bigger-110"></i> 12 hours ago
																</div>
															</div>

															<div class="tools action-buttons">
																<a href="#" class="blue">
																	<i class="ace-icon fa fa-pencil bigger-125"></i>
																</a>

																<a href="#" class="red">
																	<i class="ace-icon fa fa-times bigger-125"></i>
																</a>
															</div>
														</div>

														<div class="profile-activity clearfix">
															<div>
																<i class="pull-left thumbicon fa fa-power-off btn-inverse no-hover"></i>
																<a class="user" href="#"> Alex Doe </a> logged out.
																<div class="time">
																	<i class="ace-icon fa fa-clock-o bigger-110"></i> 16 hours ago
																</div>
															</div>

															<div class="tools action-buttons">
																<a href="#" class="blue">
																	<i class="ace-icon fa fa-pencil bigger-125"></i>
																</a>

																<a href="#" class="red">
																	<i class="ace-icon fa fa-times bigger-125"></i>
																</a>
															</div>
														</div>

														<div class="profile-activity clearfix">
															<div>
																<i class="pull-left thumbicon fa fa-key btn-info no-hover"></i>
																<a class="user" href="#"> Alex Doe </a> logged in.
																<div class="time">
																	<i class="ace-icon fa fa-clock-o bigger-110"></i> 16 hours ago
																</div>
															</div>

															<div class="tools action-buttons">
																<a href="#" class="blue">
																	<i class="ace-icon fa fa-pencil bigger-125"></i>
																</a>

																<a href="#" class="red">
																	<i class="ace-icon fa fa-times bigger-125"></i>
																</a>
															</div>
														</div>
														<div class="profile-activity clearfix">
															<div>
																<i class="pull-left thumbicon fa fa-key btn-info no-hover"></i>
																<a class="user" href="#"> Alex Doe </a> logged in.
																<div class="time">
																	<i class="ace-icon fa fa-clock-o bigger-110"></i> 16 hours ago
																</div>
															</div>

															<div class="tools action-buttons">
																<a href="#" class="blue">
																	<i class="ace-icon fa fa-pencil bigger-125"></i>
																</a>

																<a href="#" class="red">
																	<i class="ace-icon fa fa-times bigger-125"></i>
																</a>
															</div>
														</div>
														<div class="profile-activity clearfix">
															<div>
																<i class="pull-left thumbicon fa fa-key btn-info no-hover"></i>
																<a class="user" href="#"> Alex Doe </a> logged in.
																<div class="time">
																	<i class="ace-icon fa fa-clock-o bigger-110"></i> 16 hours ago
																</div>
															</div>

															<div class="tools action-buttons">
																<a href="#" class="blue">
																	<i class="ace-icon fa fa-pencil bigger-125"></i>
																</a>

																<a href="#" class="red">
																	<i class="ace-icon fa fa-times bigger-125"></i>
																</a>
															</div>
														</div>
														<div class="profile-activity clearfix">
															<div>
																<i class="pull-left thumbicon fa fa-key btn-info no-hover"></i>
																<a class="user" href="#"> Alex Doe </a> logged in.
																<div class="time">
																	<i class="ace-icon fa fa-clock-o bigger-110"></i> 16 hours ago
																</div>
															</div>

															<div class="tools action-buttons">
																<a href="#" class="blue">
																	<i class="ace-icon fa fa-pencil bigger-125"></i>
																</a>

																<a href="#" class="red">
																	<i class="ace-icon fa fa-times bigger-125"></i>
																</a>
															</div>
														</div>
														<div class="profile-activity clearfix">
															<div>
																<i class="pull-left thumbicon fa fa-key btn-info no-hover"></i>
																<a class="user" href="#"> Alex Doe </a> logged in.
																<div class="time">
																	<i class="ace-icon fa fa-clock-o bigger-110"></i> 16 hours ago
																</div>
															</div>

															<div class="tools action-buttons">
																<a href="#" class="blue">
																	<i class="ace-icon fa fa-pencil bigger-125"></i>
																</a>

																<a href="#" class="red">
																	<i class="ace-icon fa fa-times bigger-125"></i>
																</a>
															</div>
														</div>
														<div class="profile-activity clearfix">
															<div>
																<i class="pull-left thumbicon fa fa-key btn-info no-hover"></i>
																<a class="user" href="#"> Alex Doe </a> logged in.
																<div class="time">
																	<i class="ace-icon fa fa-clock-o bigger-110"></i> 16 hours ago
																</div>
															</div>

															<div class="tools action-buttons">
																<a href="#" class="blue">
																	<i class="ace-icon fa fa-pencil bigger-125"></i>
																</a>

																<a href="#" class="red">
																	<i class="ace-icon fa fa-times bigger-125"></i>
																</a>
															</div>
														</div>
														<div class="profile-activity clearfix">
															<div>
																<i class="pull-left thumbicon fa fa-key btn-info no-hover"></i>
																<a class="user" href="#"> Alex Doe </a> logged in.
																<div class="time">
																	<i class="ace-icon fa fa-clock-o bigger-110"></i> 16 hours ago
																</div>
															</div>

															<div class="tools action-buttons">
																<a href="#" class="blue">
																	<i class="ace-icon fa fa-pencil bigger-125"></i>
																</a>

																<a href="#" class="red">
																	<i class="ace-icon fa fa-times bigger-125"></i>
																</a>
															</div>
														</div>
														<div class="profile-activity clearfix">
															<div>
																<i class="pull-left thumbicon fa fa-key btn-info no-hover"></i>
																<a class="user" href="#"> Alex Doe </a> logged in.
																<div class="time">
																	<i class="ace-icon fa fa-clock-o bigger-110"></i> 16 hours ago
																</div>
															</div>

															<div class="tools action-buttons">
																<a href="#" class="blue">
																	<i class="ace-icon fa fa-pencil bigger-125"></i>
																</a>

																<a href="#" class="red">
																	<i class="ace-icon fa fa-times bigger-125"></i>
																</a>
															</div>
														</div>
														<div class="profile-activity clearfix">
															<div>
																<i class="pull-left thumbicon fa fa-key btn-info no-hover"></i>
																<a class="user" href="#"> Alex Doe </a> logged in.
																<div class="time">
																	<i class="ace-icon fa fa-clock-o bigger-110"></i> 16 hours ago
																</div>
															</div>

															<div class="tools action-buttons">
																<a href="#" class="blue">
																	<i class="ace-icon fa fa-pencil bigger-125"></i>
																</a>

																<a href="#" class="red">
																	<i class="ace-icon fa fa-times bigger-125"></i>
																</a>
															</div>
														</div>
														<div class="profile-activity clearfix">
															<div>
																<i class="pull-left thumbicon fa fa-key btn-info no-hover"></i>
																<a class="user" href="#"> Alex Doe </a> logged in.
																<div class="time">
																	<i class="ace-icon fa fa-clock-o bigger-110"></i> 16 hours ago
																</div>
															</div>

															<div class="tools action-buttons">
																<a href="#" class="blue">
																	<i class="ace-icon fa fa-pencil bigger-125"></i>
																</a>

																<a href="#" class="red">
																	<i class="ace-icon fa fa-times bigger-125"></i>
																</a>
															</div>
														</div>
														<div class="profile-activity clearfix">
															<div>
																<i class="pull-left thumbicon fa fa-key btn-info no-hover"></i>
																<a class="user" href="#"> Alex Doe </a> logged in.
																<div class="time">
																	<i class="ace-icon fa fa-clock-o bigger-110"></i> 16 hours ago
																</div>
															</div>

															<div class="tools action-buttons">
																<a href="#" class="blue">
																	<i class="ace-icon fa fa-pencil bigger-125"></i>
																</a>

																<a href="#" class="red">
																	<i class="ace-icon fa fa-times bigger-125"></i>
																</a>
															</div>
														</div>
														<div class="profile-activity clearfix">
															<div>
																<i class="pull-left thumbicon fa fa-key btn-info no-hover"></i>
																<a class="user" href="#"> Alex Doe </a> logged in.
																<div class="time">
																	<i class="ace-icon fa fa-clock-o bigger-110"></i> 16 hours ago
																</div>
															</div>

															<div class="tools action-buttons">
																<a href="#" class="blue">
																	<i class="ace-icon fa fa-pencil bigger-125"></i>
																</a>

																<a href="#" class="red">
																	<i class="ace-icon fa fa-times bigger-125"></i>
																</a>
															</div>
														</div>
														<div class="profile-activity clearfix">
															<div>
																<i class="pull-left thumbicon fa fa-key btn-info no-hover"></i>
																<a class="user" href="#"> Alex Doe </a> logged in.
																<div class="time">
																	<i class="ace-icon fa fa-clock-o bigger-110"></i> 16 hours ago
																</div>
															</div>

															<div class="tools action-buttons">
																<a href="#" class="blue">
																	<i class="ace-icon fa fa-pencil bigger-125"></i>
																</a>

																<a href="#" class="red">
																	<i class="ace-icon fa fa-times bigger-125"></i>
																</a>
															</div>
														</div>
														<div class="profile-activity clearfix">
															<div>
																<i class="pull-left thumbicon fa fa-key btn-info no-hover"></i>
																<a class="user" href="#"> Alex Doe </a> logged in.
																<div class="time">
																	<i class="ace-icon fa fa-clock-o bigger-110"></i> 16 hours ago
																</div>
															</div>

															<div class="tools action-buttons">
																<a href="#" class="blue">
																	<i class="ace-icon fa fa-pencil bigger-125"></i>
																</a>

																<a href="#" class="red">
																	<i class="ace-icon fa fa-times bigger-125"></i>
																</a>
															</div>
														</div>
													</div>

													<!-- /section:pages/profile.feed -->
												</div>
											</div>
										</div>

									</div>
								</div>

								<!-- PAGE CONTENT ENDS -->
							</div>
							<!-- /.col -->
						</div>
						<!-- /.row -->
					</div>
					<!-- /.page-content-area -->
				</div>
				<!-- /.page-content -->
			</div>
			<!-- /.main-content -->
		</div>
		<!-- /.main-container -->

<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
    <script
		src="${pageContext.request.contextPath }/assets/js/jquery-ui.custom.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript">
var controlletname="${pageContext.request.contextPath }/xmgk/pmGongkuangController";
var scripts =[null];
var gridwidth;
var setHeight;
ace.load_ajax_scripts(scripts, function() {
	init();

	//获取 当前的 项目的 主键 uid
	var xmUid = $("#project_uid").val();
	$("#PROJECT_UID").val(xmUid);
	
	$('#profile-feed-1').ace_scroll({
		height: '400px',
		mouseWheelLock: true,
		alwaysVisible : true
	});
	
	if(navigator.userAgent.indexOf("Firefox") > -1){
	   setHeight = setHeight+50;
	}
	
	 $("#grid_table").setGridHeight(setHeight - 320); 
});

/* function select(){
 document.getElementById("selectId").focus();
} */
function init(){	
    //查询按钮 (报表)
	$("#searchForPlan").click(function(){
	  jQuery("#grid_table").jqGrid().trigger("reloadGrid");
	}); 
	
	//清空
	$("#searchForClean").click(function(){
	     $("#queryForm").clearFormResult();
	     $('#searchForPlan').click();
	}); 
	
	$('#newAdd').click(function() {
	$('#newAdd').attr("data-target","xmgk-input");
		$('#xmgk-input').removeData("bs.modal");
		$("#xmgk-input").empty();
		$('#xmgk-input').modal({
			backdrop: 'static'
		});
		
		$.get("${ctx}/jsp/business/xmgk/project-gk-add.jsp?PROJECT_UID="+$("#project_uid").val(),function(data) {
			$("#xmgk-input").empty();
			$("#xmgk-input").html("");
			$("#xmgk-input").html(data);
		});
	
	});
	
	//删除 
	$('#newDel').click(function (){
		//获取 选择的列
		   var v="";
		   $("#grid_table input:checkbox[name='GONGKUANG_UID']:checked").each(function() {
				v += $(this).val() + "," ;
			});
			if (v.length != 0) {
			v = v.substring(0, v.length - 1);
			}
		  $("#strUid").val(v);
		  if($("#strUid").val() !="" ){
			bootbox.confirm("确认删除吗？", function(result) {
				if(result){
				    var data = Form2Json.formToJSON(dataCheckBox03);
					//组成保存json串格式
					var data1 = defaultJson.packSaveJson(data);
					$.ajax({
						url : controlletname+"?delete",
						cache : false,
						async :	false,
						data : data1,
						dataType : "json",  
						type : 'post',
						success : function(response) {
							if(response !=null && response !=""){
								xAlert("信息提示","删除成功！");
							    $("#searchForPlan").click();
							}else{
								xAlert("信息提示","更新失败，请联系管理员！");
							}
						}
					});
					
				}else{
					return;
				}
			});	
			
			}else{ //说明 没有 选择 任何数据（复选框没有选择）
			xAlert("信息提示","请选择要删除的行！");
			  return;
			}
		});

}

//选择 按钮 
function formatEdit(cellvalue, opts, rowObject){
	var jceid = rowObject.GONGKUANG_UID;
	var current_y = rowObject.CURRENT_Y;
	var showHtml ="<div class='hidden-sm hidden-xs action-buttons' id='getValue'>";	
		if(current_y=='Y'){
			showHtml +="<input id=\"jcEuid"+jceid+"\" type=\"radio\" name=\"GONGKUANG_UID\" value =\""+jceid+"\" checked=\"checked\"";
		}else{
			showHtml +="<input id=\"jcEuid"+jceid+"\" type=\"radio\" name=\"GONGKUANG_UID\" value =\""+jceid+"\" ";
		}
		showHtml +="</div>";
	return 	showHtml;	
	
}

//查看
function doUpdate(cellvalue, opts, rowObject){
	var gkuid = rowObject.GONGKUANG_UID;
	var current_y = rowObject.CURRENT_Y;
	var showHtml ="<div class='hidden-sm hidden-xs action-buttons'>";
		if(current_y=='Y'){
		showHtml +="<a class=\"red\" title=\"当前工况\" data-target=\"content_view\"  href=\"javascript:void(0);\">";
		}else{
			showHtml +="<a class=\"blue\" title=\"标记为当前工况\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
			showHtml +=	" onclick=\"pmGongkuangSet('"+gkuid+"');\">";
		}

		showHtml +="<span class=\"glyphicon glyphicon-bookmark bigger-110\"></i>";
		showHtml +="</a> &nbsp;";
	        	
	return 	showHtml;
}

function eidtXmgk(gkuid){
$('#newAdd').attr("data-target","xmgk-input");
	$('#xmgk-input').removeData("bs.modal");
	$("#xmgk-input").empty();
	$('#xmgk-input').modal({
		backdrop: 'static'
	});

	$.get("${ctx}/jsp/business/xmgk/project-gk-update.jsp?PROJECT_UID="+$("#project_uid").val()+"&GKUID="+gkuid,function(data) {
		$("#xmgk-input").empty();
		$("#xmgk-input").html("");
		$("#xmgk-input").html(data);
	});

}


function pmGongkuangSet(gongkuang_uid){
	$.ajax({
		url: controlletname + "?pmGongkuangSet",
		cache: false,
		async: false,
		data: {"gongkuang_uid":gongkuang_uid},
		dataType: "json",
		type: 'post',
		success: function(response) {
			if (response.success) {
				xAlert("信息提示", "标记成功！");
			} else {
				xAlert("信息提示", "标记失败，请联系管理员！");
			}
			$("#searchForPlan").click();
		}
	});
	
}



</script>

</body>
</html>