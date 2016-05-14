<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ page import="com.ccthanking.framework.fileUpload.service.FileUploadUtilService" %>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<title><fmt:message key="ui.title" /></title>

<%
    User user = RestContext.getCurrentUser();
    String XC_UID = request.getParameter("XC_UID");
    String fileType = request.getParameter("FILE_TYPE");
	FileUploadUtilService fus = new FileUploadUtilService();
	String fileRoot =  fus.fileRoot;
  
 %>
<link rel="stylesheet" type="text/css" href="${ctx}/assets/sys_resources/plugins/lightbox/themes/default/jquery.lightbox.css" />
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

.message-attachment {
   padding-right: 0px;
   padding-left: 0px; 
}

/**设置 附件信息 样式 */
.setMargin{
margin:5px;
}

/**设置 删除按钮 的 样式 */
.colorshow{
color:#4E4C4C;
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
  	<div class="message-item message-unread message-inline-open" id="id-message-content" style="margin-left: 5px;" >
									
									
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
									<span id="getType" style="margin:4px auto;" class="btn  btn-white btn-default btn-round " onclick="selectFile(this);" file_type="10012" >
										<i class="ace-icon fa fa-cloud-upload bigger-100"></i>
											现场进度状况
									</span>
									<table  role="presentation" class="table table-striped">
										<tbody class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="10012" fjlb="media"></tbody>
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
<script type="text/javascript" src="${ctx}/assets/sys_resources/plugins/lightbox/jquery.min.js"></script>		
<script type="text/javascript" src="${ctx}/assets/sys_resources/plugins/lightbox/jquery.lightbox.min.js"></script>		
<script type="text/javascript">
var controllernameJD= "${pageContext.request.contextPath }/pmxianchang/pmXianchangController";
var controllernameResponse = "${pageContext.request.contextPath }/pmxianchang/pmXianchangDafuController";
var scripts =[null];
var validform;
var userId = '<%=user.getIdCard()%>';
var xc_uid = '<%=XC_UID%>';
//获取当前的文件类型 +3
var fileType = '<%=fileType%>';
var DF_TYPE = 0;
ace.load_ajax_scripts(scripts, function() {
	var df_type = parseInt(fileType,10)+ 3;
	DF_TYPE = df_type+ "";
	
	/** 修改当前附件信息的 类型**/
	$("#getType").attr("file_type",DF_TYPE);
	$(".showFileTab").attr("file_type",DF_TYPE);
	
	successAnswer(DF_TYPE,fileType);
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
				       theNewAnswer(DF_TYPE);
				          queryFileData('XIANCHANG_UID',"0000");
				       
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

/**解决jQuery 版本问题**/
(function () {
    var v17 = $.noConflict(true);
    window.$.v17 = v17;
 })();

(function ($) {
 	$('.lightbox').lightbox();
 })(window.$.v17);


/**刷新父页面 **/
function closeWindow(){
	_reload();
}

/** 最新的回复消息 */
function theNewAnswer(DF_TYPE){
	    var xc_uid = '<%= XC_UID%>';
	    var html="";
		$.ajax({
				url: controllernameResponse + "?queryNewAnswer",
				data:{"XC_UID":xc_uid,"FILE_TYPE":DF_TYPE},                      	
				cache: false,
				async: false,
				dataType: "json",
				type: 'post',
				success: function(response) {
					$(response.obj.listxx).each(function(index,element){
						html +="<div class='hr hr-double'></div>";
						html += "<div class='message-header clearfix'>";
						html += "<div class='pull-left'>";
						html += "<span class='blue bigger-125'>现场回复</span>";
						html += "<div class='space-4'></div>";
						html += "<i class='ace-icon fa fa-star orange2'></i> &nbsp;";
						html += "<span class='profile-picture padding' >";
						if(response.obj.userxx != null  && response.obj.userxx != ""){
								$(response.obj.userxx).each(function(index,p){
									if(p.FILE_EXT_NAME=='gif'||p.FILE_EXT_NAME=='jpeg'||p.FILE_EXT_NAME=='jpg'||p.FILE_EXT_NAME=='png'){
										html += "<img class='middle' alt='John's Avatar' src='"+p.IMG_PATH+"' width='32' />";
									   }
						         });
								
							}else{
								html += "<img class='middle' alt='John's Avatar' src='${ctx}/assets/avatars/avatar.png' width='32' />";
							}
						//html += "<img class='middle' alt='John's Avatar' src='${ctx}/assets/avatars/avatar.png' width='32' />";
						
						html += "</span>&nbsp;"
						html += "<span style='margin-left:15px;color: #585858;font-weight: bolder;'>"+element.USER_NAME+"</span> &nbsp;";
						html += "<i class='ace-icon fa fa-clock-o bigger-110 orange middle'></i>";
						html += "<span class='middle grey'>"+element.CREATE_DATE+"</span></div>";
						html += "<div class='pull-right action-buttons'>";
						html += "<a onclick='deleteInfo("+element.XIANCHANG_DAFU_UID+")' ><i title='删除' class='colorshow ace-icon fa fa-trash-o  icon-only bigger-130'></i></a>";
						html += "</div></div>";
						html += "<div class='hr hr-double'></div>";
						html += "<div class='message-body'>";
						html += "<p>"+element.CONTENT+"<br/><p>";
						html += "</div>";
							//附件信息
						html += "<div class='hr hr-double'></div>";
						html+="<div class='message-attachment clearfix'>";
						html+="<div class='attachment-title clearfix'>";
						html+="<span class='setMargin bolder bigger-110'>附件信息</span> </div>&nbsp;";
						//html+="<span class='grey'>(2 files, 4.5 MB)</span>&nbsp;";
						html+="<ul class='attachment-list pull-left list-unstyled'>";
						$(response.obj.queryFileList).each(function(index,f){
							if(f.FILE_EXT_NAME=='mp4'||f.FILE_EXT_NAME=='mp3'){
								html+="<li><a href='${ctx}/assets/sys_resources/plugins/lightbox/player.swf?file="+f.FILE_PATH+"&lightbox[width]=600&lightbox[height]=400' class='lightbox'>";
								html+="<i class='ace-icon fa fa-file-o bigger-110'></i>";
								html+="<span class='attached-name'>"+f.FILE_NAME+"</span></a>";
								html+="<span class='action-buttons'>";
								html+="<a href='${ctx}/UploadUtilServlet?getfile="+f.FILE_ROOT_PATH+"&fileDir=<%=fileRoot%>' title='下载'><i class='ace-icon fa fa-download bigger-125 blue'></i></a>";
								html+="</span></li>";
							}

						});
						html+="</ul>";
						html+="<div class='attachment-images pull-right'>";
						html+="<div class='vspace-4-sm'></div>";
						html+="<div>";	
						$(response.obj.queryFileList).each(function(index,f){
							if(f.FILE_EXT_NAME=='gif'||f.FILE_EXT_NAME=='jpeg'||f.FILE_EXT_NAME=='jpg'||f.FILE_EXT_NAME=='png'){
								html+="    <a class='lightbox'  href='"+f.FILE_PATH+"'><img width='36' height='36' alt='image' src='"+f.FILE_PATH+"' /></a>";
							}
							
						});
						
						html+="	</div>";
						html+="	</div>";
						html+="</div>";
							
					    
					});
					
		             $("#id-message-content").append(html);
		             document.getElementById("textContent").value = "";
				}
				
			});
	
}


/**初始化发布的现场状况信息  并初始化回复的现场状况的信息**/
function successAnswer(DF_TYPE,fileType){
	    var xc_uid = '<%= XC_UID%>';
	    var useUid = "";
	    var html = "";
		$.ajax({
			url: controllernameJD + "?queryXcQK",
			data:{"XC_UID":xc_uid,"FILE_TYPE":fileType},
			cache: false,
			async: false,
			dataType: "json",
			type: 'post',
			success: function(response) {
				$(response.obj.listxx).each(function(index,ele){
					    useUid = ele.USER_UID;
						html += "<div class='message-header clearfix'>";
						html += "<div class='pull-left'>";
						html += "<span class='blue bigger-125'>现场信息</span>";
						html += "<div class='space-4'></div>";
						html += "<i class='ace-icon fa fa-star orange2'></i> &nbsp;";
						html += "<span class='profile-picture padding' >";
						if(response.obj.userPic != null  && response.obj.userPic != ""){
							$(response.obj.userPic).each(function(index,p){
								if(p.FILE_EXT_NAME=='gif'||p.FILE_EXT_NAME=='jpeg'||p.FILE_EXT_NAME=='jpg'||p.FILE_EXT_NAME=='png'){
									html += "<img class='middle' alt='John's Avatar' src='"+p.IMG_PATH+"' width='32' />";
								  }
										
						       });
								
							}else{
								 html += "<img class='middle' alt='John's Avatar' src='${ctx}/assets/avatars/avatar.png' width='32' />";
						}
						
						html += "</span>&nbsp;"
						html += "<span style='margin-left:15px;color: #585858;font-weight: bolder;'>"+ele.USER_NAME+"</span> &nbsp;";
						html += "<i class='ace-icon fa fa-clock-o bigger-110 orange middle'></i>";
						html += "<span class='middle grey'>"+ele.FB_DATE+"</span></div>";
						html += "</div>";
						html += "<div class='hr hr-double'></div>";
						html += "<div class='message-body'>";
						html += "<p>"+ele.DESCRIPTION+"<br/><p>";
						html += "</div>&nbsp";
						html += "<div class='hr hr-double'></div>";
						html +="<div class='message-attachment clearfix'>";
						html +="<div class='attachment-title clearfix'>";
						html +="<span class='bolder bigger-110'>附件信息</span>&nbsp;";
						html +="</div>&nbsp;";
						html +="<ul class='attachment-list pull-left list-unstyled'>";
							$(response.obj.allFile).each(function(index,m){
								if(m.FILE_EXT_NAME != '' ){
									html+="<li>";
									html+="<i class='ace-icon fa fa-file-o bigger-110' style='color:#2a6496;'></i>";
									html+="<span class='attached-name' style='color:#2a6496;'>"+m.FILE_NAME+"</span>";
									html+="<span class='action-buttons'>";
									html+="<a href='${ctx}/UploadUtilServlet?getfile="+m.FILE_ROOT_PATH+"&fileDir=<%=fileRoot%>' title='下载' ><i class='ace-icon fa fa-download bigger-125 blue'></i></a>";
									
									html+="</span></li>";
								}
	                           //html+="<a href='#'><i class='ace-icon fa fa-trash-o bigger-125 red'></i></a>";删除
							});
							html+="</ul>";
							html+="<div class='attachment-images pull-right'>";
							html+="<div class='vspace-4-sm'></div>";
							html+="<div>";	
							$(response.obj.allFile).each(function(index,f){
								if(f.FILE_EXT_NAME=='gif' || f.FILE_EXT_NAME=='jpeg' || f.FILE_EXT_NAME=='jpg' || f.FILE_EXT_NAME=='png'){
									html+="     <a class='lightbox'  href='"+f.FILE_PATH+"'><img width='36' height='36' alt='image' src='"+f.FILE_PATH+"' /></a>";
								}
								
							});
							
							html+="	</div>";
							html+="	</div>";
							html+="</div>";
							
						//所有的 回复信息结束点
				});
				
				    /** 以上是 当前类型的 现场信息（最初发布的信息） **/
					if(userId === useUid){//是发布人的 回复
						$("#confirmType").val("0");
					}else{
						$("#confirmType").val("1");
					}
				
		           /**以下是对当前类型的现场状况信息 回复的附件信息及用户及现场信息 **/
		            
		            $.ajax({
					url: controllernameResponse + "?queryDetails",
					data:{"XC_UID":xc_uid,"FILE_TYPE":DF_TYPE},                      	
					cache: false,
					async: false,
					dataType: "json",
					type: 'post',
					success: function(response) {
						if(response.obj !=null && response.obj != ""){
						$(response.obj.allAnswer).each(function(index,element){
							html +="<div class=\"hr hr-double\"></div>";
							html += "<div class='message-header clearfix'>";
							html += "<div class='pull-left'>";
							html += "<span class='blue bigger-125'>现场回复</span>";
							html += "<div class='space-4'></div>";
							html += "<i class='ace-icon fa fa-star orange2'></i> &nbsp;";
							html += "<span class='profile-picture padding' >";
							html += "<img class='middle' alt='John's Avatar' src='"+element.IMG_PATH+"' width='32' />";							
							html += "</span>&nbsp;"
							html += "<span style='margin-left:15px;color:#585858;font-weight: bolder;'>"+element.USER_NAME+"</span> &nbsp;";
							html += "<i class='ace-icon fa fa-clock-o bigger-110 orange middle'></i>";
							html += "<span class='middle grey'>"+element.CREATE_DATE+"</span></div>";
							html += "<div class='pull-right action-buttons'>";
							html += "<a onclick='deleteInfo("+element.XIANCHANG_DAFU_UID+")' ><i title='删除' class='colorshow ace-icon fa fa-trash-o  icon-only bigger-130'></i></a>";
							html += "</div></div>";
							html += "<div class='hr hr-double'></div>";
							html += "<div class='message-body'>";
							html += "<p>"+element.CONTENT+"<br/><p>";
							html += "</div>&nbsp;";
							html += "<div class='hr hr-double'></div>";
							html+="<div class='message-attachment clearfix'>";
							html+="<div class='attachment-title clearfix'>";
							html+="<span class='setMargin bolder  bigger-110'>附件信息</span> </div>&nbsp;";
							//html+="<span class='grey'>(2 files, 4.5 MB)</span>&nbsp;";
							html+="<ul class='attachment-list pull-left list-unstyled'>";
							$(element.allFileList).each(function(index,f){
								if(f.FILE_EXT_NAME=='mp4'||f.FILE_EXT_NAME=='mp3'){
									html+="<li><a href='${ctx}/assets/sys_resources/plugins/lightbox/player.swf?file="+f.FILE_PATH+"&lightbox[width]=600&lightbox[height]=400' class='lightbox'>";
									html+="<i class='ace-icon fa fa-file-o bigger-110'></i>";
									html+="<span class='attached-name'>"+f.FILE_NAME+"</span></a>";
									html+="<span class='action-buttons'>";
									html+="<a href='${ctx}/UploadUtilServlet?getfile="+f.FILE_ROOT_PATH+"&fileDir=<%=fileRoot%>' title='下载'><i class='ace-icon fa fa-download bigger-125 blue'></i></a>";
									html+="</span></li>";
								}
	
							});
							html+="</ul>";
							html+="<div class='attachment-images pull-right'>";
							html+="<div class='vspace-4-sm'></div>";
							html+="<div>";	
							$(element.allFileList).each(function(index,f){
								if(f.FILE_EXT_NAME=='gif'||f.FILE_EXT_NAME=='jpeg'||f.FILE_EXT_NAME=='jpg'||f.FILE_EXT_NAME=='png'){
									html+="     <a class='lightbox'  href='"+f.FILE_PATH+"'><img width='36' height='36' alt='image' src='"+f.FILE_PATH+"' /></a>";
								}
								
							});
							
							html+="	</div>";
							html+="	</div>";
							html+="</div>";
							
						//所有的 回复信息结束点
						    
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
				//刷新当前页面数据
				successAnswer(DF_TYPE,fileType);
				
			}else{
				xAlert("信息提示","删除当前回复失败",1);
			}
			
		}
		
	});
	
}

//附件上传	
function selectFile(obj){
	  var targetUid = $('#target_uid').val();
	  var file_type = $(obj).attr('file_type');

	  setFileData("PM_XIANCHANG_DAFU","XIANCHANG_DAFU_UID",targetUid,file_type);
      document.getElementById("fileupload_btn").click();
	}

</script>

</body>
</html>