<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<title><fmt:message key="ui.title" /></title>
<%
  User user = RestContext.getCurrentUser();
  
  String XC_UID = request.getParameter("XC_UID");
 %>

<style>
div{
cursor: pointer;
}
/**设置页面 布局 */
.page-content {
    background-color: #fff;
    position: relative;
    margin: 0;
    padding:3px 0px 0px 3px;
}

.col-xs-6, .col-sm-10 {
    position: relative;
    min-height: 1px;
    padding-left: 3px;
    padding-right: 3px;
} 

.setposition{
position: absolute;
left:800px;
top:100px;
}

.col-sm-11{
padding-right:0px;
}

</style>
</head>
<body >
<div class="modal-dialog width-60 height-auto ">
  <div class="modal-content" >
   <div class="widget-header widget-header-large">
         <div class="widget-toolbar">
			<a onclick="closeWindow();" data-action="close" data-dismiss="modal">
				<i class="ace-icon fa fa-times"></i>
				</a>
		</div>
  </div>
  <!-- 设置 滚动条 自动显示 -->
  <div class="modal-body" style="overflow: auto;" >
  
  <%-- 页面内容 开始 的位置 只需在当前DIV下追加append div 即可表示回复人的 楼层 --%>
  	<div class="message-item message-unread message-inline-open" id="id-message-content">
									<!-- #section:pages/inbox.message-header -->
									<div class="message-header clearfix">
										<div class="pull-left">
											<span class="blue bigger-125">现场状况信息</span>
											<div class="space-4"></div>
											<i class="ace-icon fa fa-star orange2"></i>
											&nbsp;
											<span class="profile-picture padding"  file_type="10008">
											  <img id="showUserPictures" class="middle" alt="John's Avatar" src="${ctx}/assets/avatars/avatar.png" width="32" />
											</span>
											<a  id="showReportName" class="sender">张三</a>
											<i class="ace-icon fa fa-clock-o bigger-110 orange middle"></i>
											<span id="showReportDate" class="middle grey">7:15</span>
										</div>
										
										 <div>
										   <table  id="sfshowTb" role="presentation" class="table table-striped" style="display: none;">
			                                 <tbody class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="10008"></tbody>
		                                   </table>
										 </div>
										 
										<div class="pull-right action-buttons">
										</div>
									</div>

									<!-- /section:pages/inbox.message-header -->
									<div class="hr hr-double"></div>

									<!-- #section:pages/inbox.message-body -->
									<div class="message-body" id="showContent">
										<p>
	1、你若安好，便是晴天。
　　2、人生没有彩排，每一天都是现场直播。
　　3、怕爹是孝顺，怕老婆是爱情。1、你若安好，便是晴天。
　　2、人生没有彩排，每一天都是现场直播。
　　3、怕爹是孝顺，怕老婆是爱情。
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
									
			</div>
			
			<div class="hr hr-double"></div>
		 <form method="post" role="form" class="form-horizontal"  id="executeFrmresponse" > 
			        <input id="confirmType" type="hidden" fieldname="DAFU_TYPE" value="" />
			          <input type="hidden" fieldname="XIANCHANG_UID" value="<%=XC_UID %>" />
			           <input type="hidden" id="target_uid" fieldname="target_uid"  />										
							<div class="form-group clearfix" >
					  			<span class="col-sm-2" style="margin:0px;padding-right:0px;padding-left:12px;text-align:right;"  >
									答复内容：
								</span>
								<div class="col-sm-10" >
									 <textarea id="textContent" dataType="*" rows="4" type="text" nullmsg="回复的内容不可为空"  fieldname="CONTENT" class="col-xs-11 col-sm-11" ></textarea>
								</div>
							</div>
						    <div class="hr hr-double"></div>					
							<div class="form-group">
								<span class="col-sm-2" style="margin:0px;padding-right:0px;padding-left:12px;text-align:right;"  >
									附件信息：
								</span>
								<div class="col-sm-10">
									<span style="margin:4px auto;" class="btn  btn-white btn-default btn-round " onclick="selectFile(this);" file_type="10014" >
										<i class="ace-icon fa fa-cloud-upload bigger-100"></i>
											现场安全状况
									</span>
									<table  role="presentation" class="table table-striped">
										<tbody class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="10014" fjlb="media"></tbody>
									</table>
								</div>
							</div>
			  </form>
			<div class="message-footer clearfix" >
			  <button id="responseAnswer" type="button"  ><strong>发表回复</strong></button>
		   </div>
  
 </div>
</div>
</div>
<jsp:include page="/jsp/file_upload/fileupload_util.jsp" flush="true" />		
<script type="text/javascript">
var controllernameJD= "${pageContext.request.contextPath }/pmxianchang/pmXianchangController";
var controllernameResponse = "${pageContext.request.contextPath }/pmxianchang/pmXianchangDafuController";
var scripts =[null];
var validform;
var userId = '<%=user.getIdCard()%>';
var xc_uid = '<%=XC_UID%>';
ace.load_ajax_scripts(scripts, function() {
	
	$(".pic1").colorbox({rel:'colorbox'});
	$(".pic2").colorbox({rel:'colorbox'});
	$(".pic3").colorbox({rel:'colorbox'});
	$(".pic4").colorbox({rel:'colorbox'});
	
	successAnswer();
	/////queryFileData('XIANCHANG_UID',xc_uid);
	//获取 当前的 项目的 主键 uid
	var xmUid = $("#project_uid").val();
	$("#XMMC_PROJECT_UID").val(xmUid);
	
	$("#responseAnswer").click(function() {
		var textContent = document.getElementById("textContent").value;
		if(textContent != "" && textContent != null){
			var data = Form2Json.formToJSON(executeFrmresponse);
			var data1 = defaultJson.packSaveJson(data);
			$.ajax({
				url :controllernameResponse+"?insert",
				data : data1,
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
				   if(response){
					   xAlert("信息提示","现场进度状况回复成功",1);
				       theNewAnswer();
				       
				   }else{
					   xAlert("信息提示","现场进度状况回复失败",1);
				   }
					
					 
			      }
			});
			
		}else{
			xAlert("信息提示","请输入回复的内容",1);
			return;
		}
	  
	});

	//删除多余的 div 避免弹出图片时出现多层
	var div1 = $(".jquery-lightbox-overlay").eq(0);
	var div2 = $(".jquery-lightbox-move").eq(0);

	$(".jquery-lightbox-overlay").remove();
	$(".jquery-lightbox-move").remove();
	$("body").append(div1);
	$("body").append(div2);
	
});

/**刷新父页面 **/
function closeWindow(){
	_reload();
}

/** 最新的回复消息 */
function theNewAnswer(){
	    var xc_uid = '<%= XC_UID%>';
	    var html="";
		$.ajax({
				url: controllernameResponse + "?queryNewAnswer",
				data:{"XC_UID":xc_uid},                      	
				cache: false,
				async: false,
				dataType: "json",
				type: 'post',
				success: function(response) {
					$(response.obj).each(function(index,element){
						html +="<div class=\"hr hr-double\"></div>";
						html += "<div class='message-header clearfix'>";
						html += "<div class='pull-left'>";
						html += "<span class='blue bigger-125'></span>";
						html += "<div class='space-4'></div>";
						html += "<i class='ace-icon fa fa-star orange2'></i> &nbsp;";
						html += "<span class='profile-picture padding'  file_type='10008'>";
						html += "<img class='middle' alt='John's Avatar' src='${ctx}/assets/avatars/avatar.png' width='32' />";
						html += "</span>&nbsp;"
						html += "<span class='sender'>"+element.USER_NAME+"</span> &nbsp;";
						html += "<i class='ace-icon fa fa-clock-o bigger-110 orange middle'></i>";
						html += "<span class='middle grey'>"+element.CREATE_DATE+"</span></div>";
						html += "<div class='pull-right action-buttons'>";
						html += "<a onclick='deleteInfo("+element.XIANCHANG_DAFU_UID+")'><i class='ace-icon fa fa-trash-o red icon-only bigger-130'></i></a>";
						html += "</div></div>";
						html += "<div class='hr hr-double'></div>";
						html += "<div class='message-body'>";
						html += "<p>"+element.CONTENT+"<br/><p>";
						html += "</div>";
						//附件信息
						html += "<div class='hr hr-double'></div>";
						html+="<div class='message-attachment clearfix'>";
						html+="<div class='attachment-title'>";
						html+="<span class='blue bolder bigger-110'>附件信息</span> &nbsp;";
						html+="<span class='grey'>(2 files, 4.5 MB)</span></div>&nbsp;";
						html+="<ul class='attachment-list pull-left list-unstyled'>";
						html+="<li><a href='#' class='attached-file'>";
						html+="<i class='ace-icon fa fa-file-o bigger-110'></i>";
						html+="<span class='attached-name'>Document1.pdf</span></a>";
						html+="<span class='action-buttons'>";
						html+="<a href='#'><i class='ace-icon fa fa-download bigger-125 blue'></i></a>";
						html+="<a href='#'><i class='ace-icon fa fa-trash-o bigger-125 red'></i></a>";
						html+="</span></li>";
						html+="<li>";
						html+="<a href='#' class='attached-file'><i class='ace-icon fa fa-film bigger-110'></i>";
						html+="<span class='attached-name'>Sample.mp4</span></a>";
						html+="<span class='action-buttons'>";
						html+="<a href='#'><i class='ace-icon fa fa-download bigger-125 blue'></i></a>";
						html+="<a href='#'><i class='ace-icon fa fa-trash-o bigger-125 red'></i></a>";
						html+="</span></li></ul>";
						html+="<div class='attachment-images pull-right'>";
						html+="<div class='vspace-4-sm'></div>";
						html+="<div>";
						html+="<a class='pic1' href='${ctx}/assets/images/gallery/image-4.jpg'><img width='36' alt='image 4' src='${ctx}/assets/images/gallery/thumb-4.jpg' /></a>";
						html+="<a class='pic2' href='${ctx}/assets/images/gallery/image-3.jpg'><img width='36' alt='image 3' src='${ctx}/assets/images/gallery/thumb-3.jpg' /></a>";
						html+="<a class='pic3' href='${ctx}/assets/images/gallery/image-2.jpg'><img width='36' alt='image 2' src='${ctx}/assets/images/gallery/thumb-2.jpg' /></a>";
						html+="<a class='pic4' href='${ctx}/assets/images/gallery/image-1.jpg'><img width='36' alt='image 1' src='${ctx}/assets/images/gallery/thumb-1.jpg' /></a>";
						html+="</div></div></div>";
					    html+="<div class='hr hr-double'></div>";
					   
					    
					});
					
		             $("#id-message-content").append(html);
		             document.getElementById("textContent").value = "";
				}
				
			});
	
}


/**  查询出所有的回复 页面初始化数据 刷新当前页面 追加DIV*/
function successAnswer(){
	    var xc_uid = '<%= XC_UID%>';
	    var xcUid='<%=XC_UID%>';
	    var useUid = "";
	    var html = "";
		$.ajax({
			url: controllernameJD + "?queryXcQK",
			data:{"XC_UID":xcUid},
			cache: false,
			async: false,
			dataType: "json",
			type: 'post',
			success: function(response) {
				$(response.obj).each(function(index,ele){
					//html="<p>"+ele.DESCRIPTION+"</p>";
					//$("#showReportName").html(ele.USER_NAME);
					//$("#showReportDate").html(ele.FB_DATE);
					    useUid = ele.USER_UID;
						html += "<div class='message-header clearfix'>";
						html += "<div class='pull-left'>";
						html += "<span class='blue bigger-125'>现场状况信息</span>";
						html += "<div class='space-4'></div>";
						html += "<i class='ace-icon fa fa-star orange2'></i> &nbsp;";
						html += "<span class='profile-picture padding'  file_type='10008'>";
						html += "<img class='middle' alt='John's Avatar' src='${ctx}/assets/avatars/avatar.png' width='32' />";
						html += "</span>&nbsp;"
						html += "<span class='sender'>"+ele.USER_NAME+"</span> &nbsp;";
						html += "<i class='ace-icon fa fa-clock-o bigger-110 orange middle'></i>";
						html += "<span class='middle grey'>"+ele.FB_DATE+"</span></div>";
						html += "</div>";
						html += "<div class='hr hr-double'></div>";
						html += "<div class='message-body'>";
						html += "<p>"+ele.DESCRIPTION+"<br/><p>";
						html += "</div>";
						//附件信息
						html += "<div class='hr hr-double'></div>";
						html+="<div class='message-attachment clearfix'>";
						html+="<div class='attachment-title'>";
						html+="<span class='blue bolder bigger-110'>附件信息</span> &nbsp;";
						html+="<span class='grey'>(2 files, 4.5 MB)</span></div>&nbsp;";
						html+="<ul class='attachment-list pull-left list-unstyled'>";
						html+="<li><a href='#' class='attached-file'>";
						html+="<i class='ace-icon fa fa-file-o bigger-110'></i>";
						html+="<span class='attached-name'>Document1.pdf</span></a>";
						html+="<span class='action-buttons'>";
						html+="<a href='#'><i class='ace-icon fa fa-download bigger-125 blue'></i></a>";
						html+="<a href='#'><i class='ace-icon fa fa-trash-o bigger-125 red'></i></a>";
						html+="</span></li>";
						html+="<li>";
						html+="<a href='#' class='attached-file'><i class='ace-icon fa fa-film bigger-110'></i>";
						html+="<span class='attached-name'>Sample.mp4</span></a>";
						html+="<span class='action-buttons'>";
						html+="<a href='#'><i class='ace-icon fa fa-download bigger-125 blue'></i></a>";
						html+="<a href='#'><i class='ace-icon fa fa-trash-o bigger-125 red'></i></a>";
						html+="</span></li></ul>";
						html+="<div class='attachment-images pull-right'>";
						html+="<div class='vspace-4-sm'></div>";
						html+="<div>";
						html+="<a class='pic1' href='${ctx}/assets/images/gallery/image-4.jpg'><img width='36' alt='image 4' src='${ctx}/assets/images/gallery/thumb-4.jpg' /></a>&nbsp;";
						html+="  <a class='pic2' href='${ctx}/assets/images/gallery/image-3.jpg'><img width='36' alt='image 3' src='${ctx}/assets/images/gallery/thumb-3.jpg' /></a>&nbsp;";
						html+="  <a class='pic3' href='${ctx}/assets/images/gallery/image-2.jpg'><img width='36' alt='image 2' src='${ctx}/assets/images/gallery/thumb-2.jpg' /></a>&nbsp;";
						html+="  <a class='pic4' href='${ctx}/assets/images/gallery/image-1.jpg'><img width='36' alt='image 1' src='${ctx}/assets/images/gallery/thumb-1.jpg' /></a>&nbsp;";
						html+="</div></div></div>";
					    html+="<div class='hr hr-double'></div>";
					   //现场状况 基本信息  结束
				});
				
					if(userId === useUid){//是发布人的 回复
						$("#confirmType").val("0");
					}else{
						$("#confirmType").val("1");
					}
					
		            queryFileData('SYS_USER',useUid);
					
		            $.ajax({
					url: controllernameResponse + "?queryDetails",
					data:{"XC_UID":xc_uid},                      	
					cache: false,
					async: false,
					dataType: "json",
					type: 'post',
					success: function(response) {
						if(response.obj !=null && response.obj != ""){
						$(response.obj).each(function(index,element){
							html +="<div class=\"hr hr-double\"></div>";
							html += "<div class='message-header clearfix'>";
							html += "<div class='pull-left'>";
							html += "<span class='blue bigger-125'></span>";
							html += "<div class='space-4'></div>";
							html += "<i class='ace-icon fa fa-star orange2'></i> &nbsp;";
							html += "<span class='profile-picture padding'  file_type='10008'>";
							html += "<img class='middle' alt='John's Avatar' src='${ctx}/assets/avatars/avatar.png' width='32' />";
							html += "</span>&nbsp;"
							html += "<span class='sender'>"+element.USER_NAME+"</span> &nbsp;";
							html += "<i class='ace-icon fa fa-clock-o bigger-110 orange middle'></i>";
							html += "<span class='middle grey'>"+element.CREATE_DATE+"</span></div>";
							html += "<div class='pull-right action-buttons'>";
							html += "<a onclick='deleteInfo("+element.XIANCHANG_DAFU_UID+")'><i class='ace-icon fa fa-trash-o red icon-only bigger-130'></i></a>";
							html += "</div></div>";
							html += "<div class='hr hr-double'></div>";
							html += "<div class='message-body'>";
						    html += "<p>"+element.CONTENT+"<br/><p>";
							html += "</div>";
							//附件信息
							html += "<div class='hr hr-double'></div>";
							html+="<div class='message-attachment clearfix'>";
							html+="<div class='attachment-title'>";
							html+="<span class='blue bolder bigger-110'>附件信息</span> &nbsp;";
							html+="<span class='grey'>(2 files, 4.5 MB)</span></div>&nbsp;";
							html+="<ul class='attachment-list pull-left list-unstyled'>";
							html+="<li><a href='#' class='attached-file'>";
							html+="<i class='ace-icon fa fa-file-o bigger-110'></i>";
							html+="<span class='attached-name'>Document1.pdf</span></a>";
							html+="<span class='action-buttons'>";
							html+="<a href='#'><i class='ace-icon fa fa-download bigger-125 blue'></i></a>";
							html+="<a href='#'><i class='ace-icon fa fa-trash-o bigger-125 red'></i></a>";
							html+="</span></li>";
							html+="<li>";
							html+="<a href='#' class='attached-file'><i class='ace-icon fa fa-film bigger-110'></i>";
							html+="<span class='attached-name'>Sample.mp4</span></a>";
							html+="<span class='action-buttons'>";
							html+="<a href='#'><i class='ace-icon fa fa-download bigger-125 blue'></i></a>";
							html+="<a href='#'><i class='ace-icon fa fa-trash-o bigger-125 red'></i></a>";
							html+="</span></li></ul>";
							html+="<div class='attachment-images pull-right'>";
							html+="<div class='vspace-4-sm'></div>";
							html+="<div>";
							html+="<a class='pic1' href='${ctx}/assets/images/gallery/image-4.jpg'><img width='36' alt='image 4' src='${ctx}/assets/images/gallery/thumb-4.jpg' /></a>&nbsp;";
							html+="  <a class='pic2' href='${ctx}/assets/images/gallery/image-3.jpg'><img width='36' alt='image 3' src='${ctx}/assets/images/gallery/thumb-3.jpg' /></a>&nbsp;";
							html+="  <a class='pic3' href='${ctx}/assets/images/gallery/image-2.jpg'><img width='36' alt='image 2' src='${ctx}/assets/images/gallery/thumb-2.jpg' /></a>&nbsp;";
							html+="  <a class='pic4' href='${ctx}/assets/images/gallery/image-1.jpg'><img width='36' alt='image 1' src='${ctx}/assets/images/gallery/thumb-1.jpg' /></a>&nbsp;";
							html+="</div></div></div>";
						    html+="<div class='hr hr-double'></div>";
						    //回复信息 结束
						    
						});
			            
			             }else{
			               html += "";
			            	 
			             }
					}
					
				});
		
			}
			
		});
		                 
		                $("#id-message-content").html(html);
		    
		
}

//删除 内容 
function deleteInfo(xcdf_uid){
	$.ajax({
		url: controllernameResponse + "?delete",
		data:{"XCDF_UID":xcdf_uid},
		cache: false,
		async: false,
		dataType: "json",
		type: 'post',
		success: function(response) {
			if(response !=null && response != ""){
				xAlert("信息提示","删除当前回复成功",1);
				//$("#id-message-content").html("");
				successAnswer();
				
			}else{
				xAlert("信息提示","删除当前回复失败",1);
			}
			
		}
		
	});
	
}

//初始化 现场进度的的情况  同一面上 
function initXcJd(){
	var xcUid='<%=XC_UID%>';
	var html = "";
	var useUid = "";
	$.ajax({
		url: controllernameJD + "?queryXcQK",
		data:{"XC_UID":xcUid},
		cache: false,
		async: false,
		dataType: "json",
		type: 'post',
		success: function(response) {
			$(response.obj).each(function(index,ele){
				html="<p>"+ele.DESCRIPTION+"</p>";
				$("#showReportName").html(ele.USER_NAME);
				$("#showReportDate").html(ele.FB_DATE);
				useUid = ele.USER_UID;
			});
			
			if(userId === useUid){//是发布人的 回复
				$("#confirmType").val("0");
			}else{
				$("#confirmType").val("1");
			}
            queryFileData('SYS_USER',useUid);
			$('#showContent').html(html);
		}
		
	});
	
	
}
//附件上传	
function selectFile(obj){
	  var targetUid = $('#target_uid').val();
	  var file_type = $(obj).attr('file_type');

	  setFileData("PM_XIANCHANG","XIANCHANG_UID",targetUid,file_type);
      document.getElementById("fileupload_btn").click();
	}

</script>

</body>
</html>