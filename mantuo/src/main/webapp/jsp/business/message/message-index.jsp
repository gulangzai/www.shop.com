<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<%@ page import="com.ccthanking.framework.fileUpload.service.FileUploadUtilService" %>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ page import="com.ccthanking.framework.Constants"%>
<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="ui.title" /></title>

  <link rel="stylesheet" type="text/css" href="${ctx}/assets/sys_resources/plugins/lightbox/themes/default/jquery.lightbox.css" />
  <!--[if IE 6]>
  <link rel="stylesheet" type="text/css" href="${ctx}/assets/sys_resources/plugins/lightbox/themes/default/jquery.lightbox.ie6.css" />
  <![endif]-->
<%
	FileUploadUtilService fus = new FileUploadUtilService();
	String fileRoot =  fus.fileRoot;
	
	String root = request.getScheme()+"://"+request.getServerName()+":8088/file";
	User user = RestContext.getCurrentUser();
	String user_uid = "";
	String username = "";
	if(user!=null){
		user_uid = user.getIdCard();
		username = user.getName();
	}
%>
	<body class="no-skin">
		<div class="main-container" id="main-container">

			<div class="main-content">
				<div class="page-content" style="padding-top: 5px;padding-bottom: 25px;padding-left: 25px;padding-right: 0px;">
			<form action="">
			   <input type="hidden" id="user_uid" value="<%=user_uid%>"/>
			</form>			
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<div class="row">
									<div class="col-xs-12">
										<!-- #section:pages/inbox -->
										<div class="tabbable">
										<!-- style="padding-bottom: 16px;" -->
											<ul id="inbox-tabs" class="inbox-tabs nav nav-tabs tab-size-bigger tab-space-1" >
												<!-- #section:pages/inbox.compose-btn -->
												<li class="li-new-mail pull-right padding-16">
												 <div id="btnAddDiv"  class="form-group" >
													<a data-target="" id="newAdd" data-toggle="modal">
														<button id="btn_new" style="display: none;" class="btn btn-link btn-sm" type="button"><i class="colorshow ace-icon glyphicon glyphicon-plus"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">添加</span></button>
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
														
															</div>

															<div>
														
																<div class="messagebar-item-right" style="right: 70px;font-weight: 900;font-size: 16px;">
																	时间
																</div>
																
																<%--<div class="messagebar-item-right" style="right: 180px;font-weight: 900;font-size: 16px;">
																	发布人/评论数
																</div>--%>

																<!-- #section:pages/inbox.navbar-search -->
																<%--<div class="nav-search minimized" style="left: 40px;font-weight: 900;font-size: 16px;">
																	主题
																</div>--%>
																
																<div class="nav-search minimized" style="left: 200px;font-weight: 900;font-size: 16px;">
																	内容
																</div>

																<!-- /section:pages/inbox.navbar-search -->
															</div>
														</div>

		
														<div id="id-message-new-navbar" class="hide message-navbar clearfix">
															<div class="message-bar"> 
															</div>  
														</div>

														<!-- /section:pages/inbox.navbar -->
														<div class="message-list-container">
															<!-- #section:pages/inbox.message-list -->
															<div class="message-list" id="message-list"> 
																<!-- #section:pages/inbox.message-list.item --> 
															</div> 
															<!-- /section:pages/inbox.message-list -->
														</div> 
														<!-- #section:pages/inbox.message-footer --> 
														<!-- /section:pages/inbox.message-footer -->
													</div>
												</div>
											</div><!-- /.tab-content -->
										</div><!-- /.tabbable -->

										<!-- /section:pages/inbox -->
									</div><!-- /.col -->
								</div><!-- /.row -->

								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						
					<!-- /.page-content-area -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->

		</div><!-- /.main-container -->
<div id="xmgk-input" class="modal"></div>
<div id="see-detail" class="modal"></div>
<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
    <script src="${pageContext.request.contextPath }/assets/js/jquery-ui.custom.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script src="${ctx}/assets/js/bootstrap-wysiwyg.min.js"></script>		
	<script type="text/javascript" src="${ctx}/assets/sys_resources/plugins/lightbox/jquery.min.js"></script>		
	<script type="text/javascript" src="${ctx}/assets/sys_resources/plugins/lightbox/jquery.lightbox.min.js"></script>		
<script type="text/javascript">
var controlletname="${pageContext.request.contextPath }/projectlog/pmProjectTopicController";
var controllerName="${pageContext.request.contextPath}/message/buUserMessageController";
var scripts =[null];
var gridwidth;
var setHeight;
ace.load_ajax_scripts(scripts, function() {
	init();
	//获取 当前的 项目的 主键 uid
	var xmUid = $("#project_uid").val();
	$("#PROJECT_UID").val(xmUid);
	
	//删除多余的 div 避免弹出图片时出现多层
	var div1 = $(".jquery-lightbox-overlay").eq(0);
	var div2 = $(".jquery-lightbox-move").eq(0);

	$(".jquery-lightbox-overlay").remove();
	$(".jquery-lightbox-move").remove();
	$("body").append(div1);
	$("body").append(div2);
	
});

(function () {
    var v17 = $.noConflict(true);
    window.$.v17 = v17;
 })();

(function ($) {
 	$('.lightbox').lightbox();
 })(window.$.v17);

function init(){	 
	$('#newAdd').click(function() {
	$('#newAdd').attr("data-target","xmgk-input");
		$('#xmgk-input').removeData("bs.modal");
		$("#xmgk-input").empty();
		$('#xmgk-input').modal({
			backdrop: 'static'
		});
		
		$.get("${ctx}/jsp/business/wenttl/went-add.jsp",function(data) {
			$("#xmgk-input").empty();
			$("#xmgk-input").html("");
			$("#xmgk-input").html(data);
		});
	
	});
	//权限检查
	//checkBtnAuthority($("#project_uid").val(),$("#projectUserId").val(),"12010001","btn_new"); 
	queryList();
	bindOpen(); 
}
 

	



/**
 * 翻页响应函数
 */
function PreNextFlashRownum(operation, recordsperpage, currentpagenum, totalpage, countrows) {

	var op_page = 1;
	switch (operation) {
		case 'first':
			op_page = 1;
			break;
		case 'pre':
			op_page = currentpagenum - 1;
			break;
		case 'next':
			op_page = currentpagenum + 1;
			break;
		case 'end':
			op_page = totalpage;
			break;
		default:
			op_page = 1;
	}

	var queryData = {"pages": {"recordsperpage": recordsperpage,"currentpagenum": op_page,"totalpage": totalpage,"countrows": countrows}};
	//
	var data = {
		msg: JSON.stringify(queryData)
	};
	var html = "";
	$.ajax({
		url: controllerName + "?query&user_uid="+$("#user_uid").val(),
		data: data,
		cache: false,
		async: true,
		dataType: "json",
		success: function(response) {
			
			var j = "(" + response.msg +")";  // 用括号将json字符串括起来
	    	var obj =  eval(j); // 返回json对象
	    	var jdata = obj.response;
	    	
	    	var recordsperpage = obj.pages.recordsperpage;
    		var currentpagenum = obj.pages.currentpagenum;
    		var totalpage = obj.pages.totalpage;
    		var countrows = obj.pages.countrows;
    		
    		
			$(jdata.data).each(function(index,element){
				html += "<div class='message-item message-unread'>";
				//html += "<span style='display: inline-block;vertical-align: middle;width:165px;margin-left:15px;' title='"+element.SUBJECT+"'>"+element.SUBJECT;
				//html += "</span>";
				html += "<span  style='width:120px;float: right;'>"+element.MESSAGE_TIME+"</span>";
				//html += "<span style='width:150px;float:right;'>"+element.USER_NAME+"/"+element.NUM+"</span>";

				if(element.STATUS=="0"){
					html+="<span class='infobox-icon'><i class='ace-icon fa fa-comments'></i></span>";
					if(element.MESSAGE_CONTENT.length>16){ 
						html += "<span class='text' style='cursor:pointer;'>"+element.MESSAGE_CONTENT.substring(0,16)+"&nbsp;&nbsp;&nbsp;&nbsp;...</span>";
					}else{ 
						html += "<span class='text' style='cursor:pointer;'>"+element.MESSAGE_CONTENT+"&nbsp;&nbsp;&nbsp;&nbsp;</span>";
					}
				}else{
					if(element.MESSAGE_CONTENT.length>16){
						html += "<span class='text' style='cursor:pointer;'>"+element.MESSAGE_CONTENT.substring(0,16)+"&nbsp;&nbsp;&nbsp;&nbsp;[已阅读]...</span>";
					}else{
						html += "<span class='text' style='cursor:pointer;'>"+element.MESSAGE_CONTENT+"&nbsp;&nbsp;&nbsp;&nbsp;[已阅读]</span>";
					}
				}
				 
				html +="<input type='hidden' class='topicUid' value='"+element.USER_MESSAGE_UID+"'>"; 
				html +="<input type='hidden' class='status' value='"+element.STATUS+"'>"; 
				html +="</div>";
			});
			
			//处理分页部分
			html += "<div class='message-footer clearfix'>";
			html += "<div class='pull-right'>";
			html += "<ul class='pagination middle'>";
			if(currentpagenum==1){
				html += "<li class='disabled'><span><i class='ace-icon fa fa-step-backward middle'></i></span></li>";
				html += "<li class='disabled'><span><i class='ace-icon fa fa-caret-left bigger-140 middle'></i></span></li>";
			}else{
				html += "<li ><a href='javascript:void(0);' onclick=\"PreNextFlashRownum('first',"+recordsperpage+","+currentpagenum+","+totalpage+","+countrows+")\"><i class='ace-icon fa fa-step-backward middle'></i></a></li>";
				html += "<li ><a href='javascript:void(0);' onclick=\"PreNextFlashRownum('pre',"+recordsperpage+","+currentpagenum+","+totalpage+","+countrows+")\"><i class='ace-icon fa fa-caret-left bigger-140 middle'></i></a></li>";
			}

			html += "<li><span>["+currentpagenum+"/"+totalpage+"] </span></li>";
			if (currentpagenum==totalpage){
				html += "<li class='disabled'><span><i class='ace-icon fa fa-caret-right bigger-140 middle'></i></span></li>";
				html += "<li class='disabled'><span><i class='ace-icon fa fa-step-forward middle'></i></span></li>";
			}else{
				html += "<li ><a href='javascript:void(0);' onclick=\"PreNextFlashRownum('next',"+recordsperpage+","+currentpagenum+","+totalpage+","+countrows+")\"><i class='ace-icon fa fa-caret-right bigger-140 middle'></i></a></li>";
				html += "<li ><a href='javascript:void(0);' onclick=\"PreNextFlashRownum('end',"+recordsperpage+","+currentpagenum+","+totalpage+","+countrows+")\"><i class='ace-icon fa fa-step-forward middle' ></i></a></li>";
			}
			html += "</ul>&nbsp; &nbsp;<div class='inline middle'>"+recordsperpage+"条/页　共"+countrows+"条记录</div></div></div>";
			
			$('.message-list').empty().html(html);
		}
	});
	setTimeout(function() {
		bindOpen();
	},1000);
};

//查询列表
function queryList(){ 
	var html = "";
		var queryData = {"pages": {"recordsperpage": "10","currentpagenum": "1","totalpage": "","countrows": ""}};
	//
	var data = {
		msg: JSON.stringify(queryData)
	};
	$("#project_uid").val(1);
	$.ajax({
		url: controllerName + "?query&user_uid="+$("#user_uid").val(),
		data:data,                      
		cache: false,
		async: false,
		dataType: "json",
		type: 'post',
		success: function(response) {
			if(response.msg!='0'){
				var j = "(" + response.msg +")";  // 用括号将json字符串括起来
		    	var obj =  eval(j); // 返回json对象
		    	var jdata = obj.response;
		    	
		    	var recordsperpage = obj.pages.recordsperpage;
	    		var currentpagenum = obj.pages.currentpagenum;
	    		var totalpage = obj.pages.totalpage;
	    		var countrows = obj.pages.countrows; 
	    		
	    		console.log("recordsperpage="+recordsperpage+" currentpagenum="+currentpagenum+" totalpage="+totalpage+"countrows="+countrows);

				$(jdata.data).each(function(index,element){
					html += "<div class='message-item message-unread'>"; 
					//html += "<span style='display: inline-block;vertical-align: middle;width:165px;margin-left:15px;' title='"+element.SUBJECT+"'>"+element.SUBJECT; 
					//html += "</span>";
					html += "<span  style='width:120px;float: right;'>"+element.MESSAGE_TIME+"</span>"; 
					
					if(element.STATUS=="0"){
						html+="<span class='infobox-icon'><i class='ace-icon fa fa-comments'></i></span>";
						if(element.MESSAGE_CONTENT.length>16){ 
							html += "<span class='text message_content' style='cursor:pointer;'>"+element.MESSAGE_CONTENT.substring(0,16)+"&nbsp;&nbsp;&nbsp;&nbsp;...</span>";
						}else{ 
							html += "<span class='text message_content'  style='cursor:pointer;'>"+element.MESSAGE_CONTENT+"&nbsp;&nbsp;&nbsp;&nbsp;</span>";
						}
					}else{
						if(element.MESSAGE_CONTENT.length>16){
							html += "<span class='text' style='cursor:pointer;'>"+element.MESSAGE_CONTENT.substring(0,16)+"&nbsp;&nbsp;&nbsp;&nbsp;[已阅读]...</span>";
						}else{
							html += "<span class='text' style='cursor:pointer;'>"+element.MESSAGE_CONTENT+"&nbsp;&nbsp;&nbsp;&nbsp;[已阅读]</span>";
						}
					}
					
					
					html +="<input type='hidden' class='topicUid' value='"+element.USER_MESSAGE_UID+"'>";
					//html +="<input type='hidden' class='project_uid' value='"+element.MESSAGE_PARA1+"'>";
					html +="<input type='hidden' class='status' value='"+element.STATUS+"'>";
					//html +="<input type='hidden' class='role' value='"+element.MESSAGE_PARA2+"'>";
					//html +="<input type='hidden' class='roleName' value='"+element.MESSAGE_PARA3+"'>"; 
					html +="</div>";
				});
				
				//----------------------------------------处理分页部分
				html += "<div class='message-footer clearfix'>";
				html += "<div class='pull-right'>";
				html += "<ul class='pagination middle'>";
				if(currentpagenum==1){
					html += "<li class='disabled'><span><i class='ace-icon fa fa-step-backward middle'></i></span></li>";
					html += "<li class='disabled'><span><i class='ace-icon fa fa-caret-left bigger-140 middle'></i></span></li>";
				}else{
					html += "<li ><a href='javascript:void(0);' onclick=\"PreNextFlashRownum('first',"+recordsperpage+","+currentpagenum+","+totalpage+","+countrows+")\"><i class='ace-icon fa fa-step-backward middle'></i></a></li>";
					html += "<li ><a href='javascript:void(0);' onclick=\"PreNextFlashRownum('pre',"+recordsperpage+","+currentpagenum+","+totalpage+","+countrows+")\"><i class='ace-icon fa fa-caret-left bigger-140 middle'></i></a></li>";
				} 
				html += "<li><span>["+currentpagenum+"/"+totalpage+"] </span></li>";
				if (currentpagenum==totalpage){
					html += "<li class='disabled'><span><i class='ace-icon fa fa-caret-right bigger-140 middle'></i></span></li>";
					html += "<li class='disabled'><span><i class='ace-icon fa fa-step-forward middle'></i></span></li>";
				}else{
					html += "<li ><a href='javascript:void(0);' onclick=\"PreNextFlashRownum('next',"+recordsperpage+","+currentpagenum+","+totalpage+","+countrows+")\"><i class='ace-icon fa fa-caret-right bigger-140 middle'></i></a></li>";
					html += "<li ><a href='javascript:void(0);' onclick=\"PreNextFlashRownum('end',"+recordsperpage+","+currentpagenum+","+totalpage+","+countrows+")\"><i class='ace-icon fa fa-step-forward middle' ></i></a></li>";
				}
				html += "</ul>&nbsp; &nbsp;<div class='inline middle'>"+recordsperpage+"条/页　共"+countrows+"条记录</div></div></div>";
				
				$('.message-list').empty().html(html);
			}

		}
		
	});
}

//查看详细
function bindOpen() {
	$('.message-list .message-item .text').on('click', function() {
		var $this=$(this);
		var message = $(this).closest('.message-item');
		//console.info($(this).siblings('.topicUid'));
		var topicUid = $(this).siblings('.topicUid').val();
		var project_uid = $(this).siblings('.project_uid').val();
		var status = $(this).siblings('.status').val();  
		 //小箭头没有
		 $this.parent().find(".infobox-icon").remove();
		//if message is open, then close it
		if (message.hasClass('message-inline-open')) {
			message.removeClass('message-inline-open').find('.message-content').remove();
			return;
		}
		
		//如果有打开的 先关闭 再把自己打开
		if(message.siblings().hasClass('message-inline-open')){
			message.siblings().removeClass('message-inline-open').find('.message-content').remove();
			//return;
		}

		$('.message-container').append('<div class="message-loading-overlay"><i class="fa-spin ace-icon fa fa-spinner orange2 bigger-160"></i></div>');
		//setTimeout(function() {
			$('.message-container').find('.message-loading-overlay').remove();
			message.addClass('message-inline-open').append('<div class="message-content" style="margin-left: 30px;"/>');
			var html = ""; 
			//设置已阅
			if(status=="0"){
				$this.text($this.text()+"[已阅读]");
			}
			$.ajax({
				url: controllerName + "?queryDetail",
				data:{"USER_MESSAGE_UID":topicUid,"STATUS":status},                      	
				cache: false,
				async: false,
				dataType: "json",
				type: 'post',
				success: function(response) {
					var user = "<%=user_uid%>";
					var j = "(" + response.msg +")";  // 用括号将json字符串括起来
			    	var obj =  eval(j); // 返回json对象
			    	var jdata = obj.response;
					$(jdata.data).each(function(index,element){
						html += "<div class='message-header clearfix'>";
						html += "<div class='pull-left'>";
						html += "<span class='bigger-125'>消息内容</span>";
						html += "<div class='space-4'></div>";  
						html += "<i class='ace-icon fa fa-clock-o bigger-110 orange middle'></i>";
						html += "<span class='time grey'>"+element.MESSAGE_TIME+"</span></div>";
						html += "<div class='pull-right action-buttons'>"; 
						/*if(element.STATUS=="0"){ 
								html += "<a href='javascript:void(0);' onclick='reply("+element.USER_MESSAGE_UID+");'><i class='colorshow fa fa-comment  icon-only bigger-130' title='回复'></i></a>";
		 
								html += "<a href='javascript:void(0);' onclick='shutDown("+element.USER_MESSAGE_UID+");'><i class='colorshow ace-icon glyphicon glyphicon-off bigger-120' title='关闭'></i></a>";
								 
							    html += "<a href='javascript:void(0);' onclick='delTopic("+element.USER_MESSAGE_UID+");'><i class='colorshow1 fa fa-trash-o  icon-only bigger-130' title='删除'></i></a>";	
					 
						}else if(element.STATUS=="1" && element.CREATE_USER == user &&checkTabAuthority($("#project_uid").val(),$("#projectUserId").val(),"12010003")){
					    	html += "<a href='javascript:void(0);' onclick='delTopic("+element.USER_MESSAGE_UID+");'><i class='colorshow1 fa fa-trash-o  icon-only bigger-130' title='删除'></i></a>";
					    }*/
						html += "</div></div>";
						html += "<div class='hr hr-double'></div>";
						html += "<div class='message-body'>";
						html += "<p>"+element.MESSAGE_CONTENT;
						html += "<p>";
						html += "</div>";  
						html += "<div class='hr hr-double'></div>";
						html += "<div class='message-body'>";
						console.info(element.STATUS); 
						if(element.MESSAGE_URL!=null){
							  if(element.MESSAGE_TYPE=='O'){ 
								  //alert(element.MESSAGE_URL);
								  //<button id="addZGDataAdd" onclick="join('yes')" class="btn btn-white btn-default btn-round" type="button">&nbsp;同意&nbsp;</button>  
                                  html+="<input type='button' onclick='openUrl(\""+element.MESSAGE_URL+"\",\""+element.MESSAGE_PARA1+"\",\""+element.MESSAGE_PARA2+"\",\""+element.MESSAGE_PARA3+"\",\""+element.MESSAGE_PARA4+"\");' value='查看具体内容'></input>";
                              }  
						} 
						
					    //html += "<a href='javascript:void(0);' onclick='delTopic("+element.USER_MESSAGE_UID+");'><i class='colorshow1 fa fa-trash-o  icon-only bigger-130' title='删除'></i></a>";	
                        html += "</div>";
					});
					 
					var content = message.find('.message-content:last').empty().html(html); 
					//remove scrollbar elements
					content.find('.scroll-track').remove();
					content.find('.scroll-content').children().unwrap(); 
					content.find('.message-body').ace_scroll({
						size: 150,
						mouseWheelLock: true,
						styleClass: 'scroll-visible'
					});
				}
				
			}); 
		//}, 500 + parseInt(Math.random() * 300));

	});
}




 
//,para1,para2,para3,para4
function openUrl(url,para1,para2,para3,para4){ 
	//para1="?invite_uid=8886497190499";
	//para2="&projectName=测试项目&role=3&userName=周明";
	//para3="&roleName=项目管理员角色建设单位监理单位施工单位";
	//para4="&project_role_uid=16";
	$('#see-detail').removeData("bs.modal");
	$("#see-detail").empty();
	$('#see-detail').modal({
		backdrop: 'static'
	});
	
	$.get("${ctx}/jsp/business"+url+para1+para2+para3+para4+"&user_message_uid=21",function(data) {
		$("#see-detail").empty();
		$("#see-detail").html("");
		$("#see-detail").html(data);
	}); 
}


//根据文件后缀名判断是否是多媒体,
//是返回ture ，不是返回false
function isMedia(fileExtName){
	fileExtName = fileExtName.toLowerCase();
	if(fileExtName=='mp4'||fileExtName=='mp3'||fileExtName=='aac'){
		return true;
	}else{
		return false;
	}
}

//根据文件后缀名判断是否是图片，
//是返回ture ，不是返回false
function isPicture(fileExtName){
	fileExtName = fileExtName.toLowerCase();
	if(fileExtName=='gif'||fileExtName=='jpeg'||fileExtName=='jpg'||fileExtName=='png'){
		return true;
	}else{
		return false;
	}
}
</script>

</body>
</html>