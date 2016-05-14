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
								<div class="row">
									<div class="col-xs-12">
										<!-- #section:pages/inbox -->
										<div class="tabbable">
										<!-- style="padding-bottom: 16px;" -->
											<ul id="inbox-tabs" class="inbox-tabs nav nav-tabs tab-size-bigger tab-space-1" style="padding-bottom: 16px;">
												<!-- #section:pages/inbox.compose-btn -->
												<li class="li-new-mail pull-right padding-16">
												 <div id="btnAddDiv"  class="form-group" >
													<a data-target="" id="newAdd" data-toggle="modal">
														<button id="btn_new" class="btn btn-link btn-sm" type="button"><i class="ace-icon glyphicon glyphicon-plus"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">添加</span></button>
													</a>
													 </div>
												</li><!-- /.li-new-mail -->
											</ul>

											<div class="tab-content no-border no-padding">
												<div id="inbox" class="tab-pane in active">
													<div class="message-container">
														<!-- #section:pages/inbox.navbar -->
														<div id="id-message-list-navbar" class="message-navbar clearfix">
															<div class="message-bar">
																<div class="message-infobar" id="id-message-infobar">
																	<span class="blue bigger-150">Inbox</span>
																	<span class="grey bigger-110">(2 unread messages)</span>
																</div>

																
															</div>

															<div>
														

																<div class="messagebar-item-right">
																	<div class="inline position-relative">
																		<a href="#" data-toggle="dropdown" class="dropdown-toggle">
																			Sort &nbsp;
																			<i class="ace-icon fa fa-caret-down bigger-125"></i>
																		</a>

																		<ul class="dropdown-menu dropdown-lighter dropdown-menu-right dropdown-100">
																			<li>
																				<a href="#">
																					<i class="ace-icon fa fa-check green"></i>
																					Date
																				</a>
																			</li>

																			<li>
																				<a href="#">
																					<i class="ace-icon fa fa-check invisible"></i>
																					From
																				</a>
																			</li>

																			<li>
																				<a href="#">
																					<i class="ace-icon fa fa-check invisible"></i>
																					Subject
																				</a>
																			</li>
																		</ul>
																	</div>
																</div>

																<!-- #section:pages/inbox.navbar-search -->
																<div class="nav-search minimized">
																	<form class="form-search">
																		<span class="input-icon">
																			<input type="text" autocomplete="off" class="input-small nav-search-input" placeholder="Search inbox ..." />
																			<i class="ace-icon fa fa-search nav-search-icon"></i>
																		</span>
																	</form>
																</div>

																<!-- /section:pages/inbox.navbar-search -->
															</div>
														</div>

														<div id="id-message-item-navbar" class="hide message-navbar clearfix">
															<div class="message-bar">
																
															</div>

															<div>
																<div class="messagebar-item-left">
																	<a href="#" class="btn-back-message-list">
																		<i class="ace-icon fa fa-arrow-left blue bigger-110 middle"></i>
																		<b class="bigger-110 middle">返回</b>
																	</a>
																</div>

																<div class="messagebar-item-right">
																	<i class="ace-icon fa fa-clock-o bigger-110 orange"></i>
																	<span class="grey">Today, 7:15 pm</span>
																</div>
															</div>
														</div>

														<div id="id-message-new-navbar" class="hide message-navbar clearfix">
															<div class="message-bar">

															</div>

															<div>
																<div class="messagebar-item-left">
																	<a href="#" class="btn-back-message-list">
																		<i class="ace-icon fa fa-arrow-left bigger-110 middle blue"></i>
																		<b class="middle bigger-110">返回</b>
																	</a>
																</div>

																<div class="messagebar-item-right">
																	<span class="inline btn-send-message">
																		<button type="button" class="btn btn-sm btn-primary no-border btn-white btn-round">
																			<span class="bigger-110">提交</span>

																			<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
																		</button>
																	</span>
																</div>
															</div>
														</div>

														<!-- /section:pages/inbox.navbar -->
														<div class="message-list-container">
															<!-- #section:pages/inbox.message-list -->
															<div class="message-list" id="message-list">

																<!-- #section:pages/inbox.message-list.item -->
																<div class="message-item message-unread">

																	<i class="message-star ace-icon fa fa-star-o light-grey"></i>

																	<span class="sender" title="John Doe">
																		John Doe
																		<span class="light-grey">(4)</span>
																	</span>
																	<span class="time">7:15 pm</span>

																	<span class="attachment">
																		<i class="ace-icon fa fa-paperclip"></i>
																	</span>

																	<span class="summary">
																		<span class="badge badge-pink mail-tag"></span>
																		<span class="text">
																			Clik to open this message right here
																		</span>
																	</span>
																</div>
																<div class="message-item message-unread">

																	<i class="message-star ace-icon fa fa-star-o light-grey"></i>

																	<span class="sender" title="John Doe">
																		John Doe
																		<span class="light-grey">(4)</span>
																	</span>
																	<span class="time">7:15 pm</span>

																	<span class="attachment">
																		<i class="ace-icon fa fa-paperclip"></i>
																	</span>

																	<span class="summary">
																		<span class="badge badge-pink mail-tag"></span>
																		<span class="text">
																			Clik to open this message right here
																		</span>
																	</span>
																</div>
																<div class="message-item message-unread">

																	<i class="message-star ace-icon fa fa-star-o light-grey"></i>

																	<span class="sender" title="John Doe">
																		John Doe
																		<span class="light-grey">(4)</span>
																	</span>
																	<span class="time">7:15 pm</span>

																	<span class="attachment">
																		<i class="ace-icon fa fa-paperclip"></i>
																	</span>

																	<span class="summary">
																		<span class="badge badge-pink mail-tag"></span>
																		<span class="text">
																			Clik to open this message right here
																		</span>
																	</span>
																</div>

															</div>

															<!-- /section:pages/inbox.message-list -->
														</div>

														<!-- #section:pages/inbox.message-footer -->
														<div class="message-footer clearfix">
															<div class="pull-left"> 151 messages total </div>

															<div class="pull-right">
																<div class="inline middle"> page 1 of 16 </div>

																&nbsp; &nbsp;
																<ul class="pagination middle">
																	<li class="disabled">
																		<span>
																			<i class="ace-icon fa fa-step-backward middle"></i>
																		</span>
																	</li>

																	<li class="disabled">
																		<span>
																			<i class="ace-icon fa fa-caret-left bigger-140 middle"></i>
																		</span>
																	</li>

																	<li>
																		<span>
																			<input value="1" maxlength="3" type="text" />
																		</span>
																	</li>

																	<li>
																		<a href="#">
																			<i class="ace-icon fa fa-caret-right bigger-140 middle"></i>
																		</a>
																	</li>

																	<li>
																		<a href="#">
																			<i class="ace-icon fa fa-step-forward middle"></i>
																		</a>
																	</li>
																</ul>
															</div>
														</div>

														<div class="hide message-footer message-footer-style2 clearfix">
															<div class="pull-left"> simpler footer </div>

															<div class="pull-right">
																<div class="inline middle"> message 1 of 151 </div>

																&nbsp; &nbsp;
																<ul class="pagination middle">
																	<li class="disabled">
																		<span>
																			<i class="ace-icon fa fa-angle-left bigger-150"></i>
																		</span>
																	</li>

																	<li>
																		<a href="#">
																			<i class="ace-icon fa fa-angle-right bigger-150"></i>
																		</a>
																	</li>
																</ul>
															</div>
														</div>

														<!-- /section:pages/inbox.message-footer -->
													</div>
												</div>
											</div><!-- /.tab-content -->
										</div><!-- /.tabbable -->

										<!-- /section:pages/inbox -->
									</div><!-- /.col -->
								</div><!-- /.row -->

								

								<div class="hide message-content" id="id-message-content">
									<!-- #section:pages/inbox.message-header -->
									<div class="message-header clearfix">
										<div class="pull-left">
											<span class="blue bigger-125">问题信息</span>

											<div class="space-4"></div>

											<i class="ace-icon fa fa-star orange2"></i>

											&nbsp;
											<img class="middle" alt="John's Avatar" src="${ctx}/assets/avatars/avatar.png" width="32" />
											&nbsp;
											<a href="#" class="sender">张三</a>

											&nbsp;
											<i class="ace-icon fa fa-clock-o bigger-110 orange middle"></i>
											<span class="time grey">7:15</span>
										</div>

										<div class="pull-right action-buttons">
											<a href="#">
												<i class="ace-icon fa fa-reply green icon-only bigger-130"></i>
											</a>

											<a href="#">
												<i class="ace-icon fa fa-mail-forward blue icon-only bigger-130"></i>
											</a>

											<a href="#">
												<i class="ace-icon fa fa-trash-o red icon-only bigger-130"></i>
											</a>
										</div>
									</div>

									<!-- /section:pages/inbox.message-header -->
									<div class="hr hr-double"></div>

									<!-- #section:pages/inbox.message-body -->
									<div class="message-body">
										<p>
											Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
										</p>

										<p>
											Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
										</p>

									</div>

									<!-- /section:pages/inbox.message-body -->
									<div class="hr hr-double"></div>

									<!-- #section:pages/inbox.message-attachment -->
									<div class="message-attachment clearfix">
										<div class="attachment-title">
											<span class="blue bolder bigger-110">附件信息</span>
											&nbsp;
											<span class="grey">(2 files, 4.5 MB)</span>

											<div class="inline position-relative">
												<a href="#" data-toggle="dropdown" class="dropdown-toggle">
													&nbsp;
													<i class="ace-icon fa fa-caret-down bigger-125 middle"></i>
												</a>

												<ul class="dropdown-menu dropdown-lighter">
													<li>
														<a href="#">Download all as zip</a>
													</li>

													<li>
														<a href="#">Display in slideshow</a>
													</li>
												</ul>
											</div>
										</div>

										&nbsp;
										<ul class="attachment-list pull-left list-unstyled">
											<li>
												<a href="#" class="attached-file">
													<i class="ace-icon fa fa-file-o bigger-110"></i>
													<span class="attached-name">Document1.pdf</span>
												</a>

												<span class="action-buttons">
													<a href="#">
														<i class="ace-icon fa fa-download bigger-125 blue"></i>
													</a>

													<a href="#">
														<i class="ace-icon fa fa-trash-o bigger-125 red"></i>
													</a>
												</span>
											</li>

											<li>
												<a href="#" class="attached-file">
													<i class="ace-icon fa fa-film bigger-110"></i>
													<a class='vimeo' href="${pageContext.request.contextPath }/assets/video/1_Oracle学习前奏.mp4" title="R&ouml;yksopp: Remind Me"><span class="attached-name">Sample.mp4</span></a>
												</a>

												<span class="action-buttons">
													<a href="#">
														<i class="ace-icon fa fa-download bigger-125 blue"></i>
													</a>

													<a href="#">
														<i class="ace-icon fa fa-trash-o bigger-125 red"></i>
													</a>
												</span>
											</li>
										</ul>

										<div class="attachment-images pull-right">
											<div class="vspace-4-sm"></div>

											<div>
												<a class="pic1" href="${ctx}/assets/images/gallery/image-4.jpg"><img width="36" alt="image 4" src="${ctx}/assets/images/gallery/thumb-4.jpg" /></a>
												<a class="pic2" href="${ctx}/assets/images/gallery/image-3.jpg"><img width="36" alt="image 3" src="${ctx}/assets/images/gallery/thumb-3.jpg" /></a>
												<a class="pic3" href="${ctx}/assets/images/gallery/image-2.jpg"><img width="36" alt="image 2" src="${ctx}/assets/images/gallery/thumb-2.jpg" /></a>
												<a class="pic4" href="${ctx}/assets/images/gallery/image-1.jpg"><img width="36" alt="image 1" src="${ctx}/assets/images/gallery/thumb-1.jpg" /></a>
											</div>
										</div>
									</div>
									
									<div class="hr hr-double"></div>
									
									<div class="message-header clearfix">
										<div class="pull-left">
											<span class="blue bigger-125">问题回复</span>

											<div class="space-4"></div>

											<i class="ace-icon fa fa-star orange2"></i>

											&nbsp;
											<img class="middle" alt="John's Avatar" src="${ctx}/assets/avatars/avatar.png" width="32" />
											&nbsp;
											<a href="#" class="sender">李四</a>

											&nbsp;
											<i class="ace-icon fa fa-clock-o bigger-110 orange middle"></i>
											<span class="time grey">7:30</span>
										</div>

										<div class="pull-right action-buttons">

											<a href="#">
												<i class="ace-icon fa fa-trash-o red icon-only bigger-130"></i>
											</a>
										</div>
									</div>

									<!-- /section:pages/inbox.message-header -->
									<div class="hr hr-double"></div>

									<!-- #section:pages/inbox.message-body -->
									<div class="message-body">
										<p>
											Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
										</p>

										<p>
											Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
										</p>
									</div>

									<!-- /section:pages/inbox.message-body -->
									<div class="hr hr-double"></div>

									<!-- #section:pages/inbox.message-attachment -->
									<div class="message-attachment clearfix">
										<div class="attachment-title">
											<span class="blue bolder bigger-110">附件信息</span>
											&nbsp;
											<span class="grey">(2 files, 4.5 MB)</span>

											<div class="inline position-relative">
												<a href="#" data-toggle="dropdown" class="dropdown-toggle">
													&nbsp;
													<i class="ace-icon fa fa-caret-down bigger-125 middle"></i>
												</a>

												<ul class="dropdown-menu dropdown-lighter">
													<li>
														<a href="#">Download all as zip</a>
													</li>

													<li>
														<a href="#">Display in slideshow</a>
													</li>
												</ul>
											</div>
										</div>

										&nbsp;
										<ul class="attachment-list pull-left list-unstyled">
											<li>
												<a href="#" class="attached-file">
													<i class="ace-icon fa fa-file-o bigger-110"></i>
													<span class="attached-name">Document1.pdf</span>
												</a>

												<span class="action-buttons">
													<a href="#">
														<i class="ace-icon fa fa-download bigger-125 blue"></i>
													</a>

													<a href="#">
														<i class="ace-icon fa fa-trash-o bigger-125 red"></i>
													</a>
												</span>
											</li>

											<li>
												<a href="#" class="attached-file">
													<i class="ace-icon fa fa-film bigger-110"></i>
													<a class='vimeo' href="${pageContext.request.contextPath }/assets/video/1_Oracle学习前奏.mp4" title="R&ouml;yksopp: Remind Me"><span class="attached-name">Sample.mp4</span></a>
												</a>

												<span class="action-buttons">
													<a href="#">
														<i class="ace-icon fa fa-download bigger-125 blue"></i>
													</a>

													<a href="#">
														<i class="ace-icon fa fa-trash-o bigger-125 red"></i>
													</a>
												</span>
											</li>
										</ul>

										<div class="attachment-images pull-right">
											<div class="vspace-4-sm"></div>

											<div>
												<a class="pic1" href="${ctx}/assets/images/gallery/image-4.jpg"><img width="36" alt="image 4" src="${ctx}/assets/images/gallery/thumb-4.jpg" /></a>
												<a class="pic2" href="${ctx}/assets/images/gallery/image-3.jpg"><img width="36" alt="image 3" src="${ctx}/assets/images/gallery/thumb-3.jpg" /></a>
												<a class="pic3" href="${ctx}/assets/images/gallery/image-2.jpg"><img width="36" alt="image 2" src="${ctx}/assets/images/gallery/thumb-2.jpg" /></a>
												<a class="pic4" href="${ctx}/assets/images/gallery/image-1.jpg"><img width="36" alt="image 1" src="${ctx}/assets/images/gallery/thumb-1.jpg" /></a>
											</div>
										</div>
									</div>

									<!-- /section:pages/inbox.message-attachment -->
								</div><!-- /.message-content -->

								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content-area -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->

		</div><!-- /.main-container -->
<div id="xmgk-input" class="modal"></div>
<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
    <script src="${pageContext.request.contextPath }/assets/js/jquery-ui.custom.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
	<script src="${ctx}/assets/js/bootstrap-wysiwyg.min.js"></script>		
			
<script type="text/javascript">
var controlletname="${pageContext.request.contextPath }/xmgk/pmGongkuangController";
var scripts =[null];
var gridwidth;
var setHeight;
ace.load_ajax_scripts(scripts, function() {
	init();
	//$("#pic1").imgbox();
	$(".pic1").colorbox({rel:'colorbox'});
	$(".pic2").colorbox({rel:'colorbox'});
	$(".pic3").colorbox({rel:'colorbox'});
	$(".pic4").colorbox({rel:'colorbox'});
	$(".vimeo").colorbox({iframe:true, innerWidth:500, innerHeight:409});
	//获取 当前的 项目的 主键 uid
	var xmUid = $("#project_uid").val();
	$("#PROJECT_UID").val(xmUid);
	
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
		alert("xx");
	$('#newAdd').attr("data-target","xmgk-input");
		$('#xmgk-input').removeData("bs.modal");
		$("#xmgk-input").empty();
		$('#xmgk-input').modal({
			backdrop: 'static'
		});
		
		$.get("${ctx}/jsp/business/xiangmrz/riz-add.jsp",function(data) {
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

	$.get("${ctx}/jsp/business/xiangmrz/riz-add.jsp",function(data) {
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
		<script type="text/javascript">
			jQuery(function($){
			
				//handling tabs and loading/displaying relevant messages and forms
				//not needed if using the alternative view, as described in docs
				$('#inbox-tabs a[data-toggle="tab"]').on('show.bs.tab', function (e) {
					var currentTab = $(e.target).data('target');
					if(currentTab == 'write') {
						Inbox.show_form();
					}
					else if(currentTab == 'inbox') {
						Inbox.show_list();
					}
				})
			
			
				//display second message right inside the message list
				$('.message-list .message-item .text').on('click', function(){
					var message = $(this).closest('.message-item');
			
					//if message is open, then close it
					if(message.hasClass('message-inline-open')) {
						message.removeClass('message-inline-open').find('.message-content').remove();
						return;
					}
			
					$('.message-container').append('<div class="message-loading-overlay"><i class="fa-spin ace-icon fa fa-spinner orange2 bigger-160"></i></div>');
					setTimeout(function() {
						$('.message-container').find('.message-loading-overlay').remove();
						message
							.addClass('message-inline-open')
							.append('<div class="message-content" />')
						var content = message.find('.message-content:last').html( $('#id-message-content').html() );
			
						//remove scrollbar elements
						content.find('.scroll-track').remove();
						content.find('.scroll-content').children().unwrap();
						
						content.find('.message-body').ace_scroll({
							size: 150,
							mouseWheelLock: true,
							styleClass: 'scroll-visible'
						});
				
					}, 500 + parseInt(Math.random() * 500));
					
				});
			
			
			
			
				var Inbox = {
					//displays a toolbar according to the number of selected messages
					display_bar : function (count) {
						if(count == 0) {
							$('#id-toggle-all').removeAttr('checked');
							$('#id-message-list-navbar .message-toolbar').addClass('hide');
							$('#id-message-list-navbar .message-infobar').removeClass('hide');
						}
						else {
							$('#id-message-list-navbar .message-infobar').addClass('hide');
							$('#id-message-list-navbar .message-toolbar').removeClass('hide');
						}
					}
					,
					select_all : function() {
						var count = 0;
						$('.message-item input[type=checkbox]').each(function(){
							this.checked = true;
							$(this).closest('.message-item').addClass('selected');
							count++;
						});
						
						$('#id-toggle-all').get(0).checked = true;
						
						Inbox.display_bar(count);
					}
					,
					select_none : function() {
						$('.message-item input[type=checkbox]').removeAttr('checked').closest('.message-item').removeClass('selected');
						$('#id-toggle-all').get(0).checked = false;
						
						Inbox.display_bar(0);
					}
					,
					select_read : function() {
						$('.message-unread input[type=checkbox]').removeAttr('checked').closest('.message-item').removeClass('selected');
						
						var count = 0;
						$('.message-item:not(.message-unread) input[type=checkbox]').each(function(){
							this.checked = true;
							$(this).closest('.message-item').addClass('selected');
							count++;
						});
						Inbox.display_bar(count);
					}
					,
					select_unread : function() {
						$('.message-item:not(.message-unread) input[type=checkbox]').removeAttr('checked').closest('.message-item').removeClass('selected');
						
						var count = 0;
						$('.message-unread input[type=checkbox]').each(function(){
							this.checked = true;
							$(this).closest('.message-item').addClass('selected');
							count++;
						});
						
						Inbox.display_bar(count);
					}
				}
			
				//show message list (back from writing mail or reading a message)
				Inbox.show_list = function() {
					$('.message-navbar').addClass('hide');
					$('#id-message-list-navbar').removeClass('hide');
			
					$('.message-footer').addClass('hide');
					$('.message-footer:not(.message-footer-style2)').removeClass('hide');
			
					$('.message-list').removeClass('hide').next().addClass('hide');
					//hide the message item / new message window and go back to list
				}
			
				//show write mail form
				Inbox.show_form = function() {
					if($('.message-form').is(':visible')) return;
					if(!form_initialized) {
						initialize_form();
					}
					
					
					var message = $('.message-list');
					$('.message-container').append('<div class="message-loading-overlay"><i class="fa-spin ace-icon fa fa-spinner orange2 bigger-160"></i></div>');
					
					setTimeout(function() {
						message.next().addClass('hide');
						
						$('.message-container').find('.message-loading-overlay').remove();
						
						$('.message-list').addClass('hide');
						$('.message-footer').addClass('hide');
						$('.message-form').removeClass('hide').insertAfter('.message-list');
						
						$('.message-navbar').addClass('hide');
						$('#id-message-new-navbar').removeClass('hide');
						
						
						//reset form??
						$('.message-form .wysiwyg-editor').empty();
					
						$('.message-form .ace-file-input').closest('.file-input-container:not(:first-child)').remove();
						$('.message-form input[type=file]').ace_file_input('reset_input');
						
						$('.message-form').get(0).reset();
						
					}, 300 + parseInt(Math.random() * 300));
				}
			
			
			
			
				var form_initialized = false;
				function initialize_form() {
					if(form_initialized) return;
					form_initialized = true;
					
					//intialize wysiwyg editor
					$('.message-form .wysiwyg-editor').ace_wysiwyg({
						toolbar:
						[
							'bold',
							'italic',
							'strikethrough',
							'underline',
							null,
							'justifyleft',
							'justifycenter',
							'justifyright',
							null,
							'createLink',
							'unlink',
							null,
							'undo',
							'redo'
						]
					}).prev().addClass('wysiwyg-style1');
			
			
			
					//file input
					$('.message-form input[type=file]').ace_file_input()
					.closest('.ace-file-input')
					.addClass('width-90 inline')
					.wrap('<div class="form-group file-input-container"><div class="col-sm-7"></div></div>');
			
					//Add Attachment
					//the button to add a new file input
					$('#id-add-attachment')
					.on('click', function(){
						var file = $('<input type="file" name="attachment[]" />').appendTo('#form-attachments');
						file.ace_file_input();
						
						file.closest('.ace-file-input')
						.addClass('width-90 inline')
						.wrap('<div class="form-group file-input-container"><div class="col-sm-7"></div></div>')
						.parent().append('<div class="action-buttons pull-right col-xs-1">\
							<a href="#" data-action="delete" class="middle">\
								<i class="ace-icon fa fa-trash-o red bigger-130 middle"></i>\
							</a>\
						</div>')
						.find('a[data-action=delete]').on('click', function(e){
							//the button that removes the newly inserted file input
							e.preventDefault();
							$(this).closest('.file-input-container').hide(300, function(){ $(this).remove() });
						});
					});
				}//initialize_form
			
			
			});
		</script>
</body>
</html>