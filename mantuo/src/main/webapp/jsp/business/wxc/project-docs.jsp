<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<%@ page import="com.ccthanking.framework.fileUpload.service.FileUploadUtilService" %>
<title><fmt:message key="ui.title" /></title> 
<%
	FileUploadUtilService fus = new FileUploadUtilService();
	String fileRoot =  fus.fileRoot;
	
	String root = request.getScheme()+"://"+request.getServerName()+":8088/file";
%>

  <link rel="stylesheet" type="text/css" href="${ctx}/assets/sys_resources/plugins/lightbox/themes/default/jquery.lightbox.css" />
  <!--[if IE 6]>
  <link rel="stylesheet" type="text/css" href="${ctx}/assets/sys_resources/plugins/lightbox/themes/default/jquery.lightbox.ie6.css" />
  <![endif]-->
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
#file_bar{
	margin-left: 0px;
}
#file_bar li{
display:inline;
}
#file_search_bar{
	margin-left: 0px;
}
#file_search_bar li{
display:inline;
}
/**.btn-purple001, .btn-purple001:focus {
    border-color: #F5F5F5;
    background-color: #F5F5F5!important;
}**/
</style>
</head>
<body>
	<div class="main-container" id="main-container">
				<div class="page-content">
				<div class="col-sm-12">
	                <form method="post" class="form-inline"  id="queryForm" width="100%"
					   role="form" style="border:solid 1px #ddd;height:50px; line-height:44px;vertical-align:middle;"> 
	                    <input id="PROJECT_UID" type="hidden" name="PROJECT_UID" fieldname="PROJECT_UID" operation="="/>  
	                     <input id="" type="hidden" name="DISTRICT" fieldname="DISTRICT" value="no" />
	                     <input type="hidden" id="p_doc_uid">
	                     <input type="hidden" id="p_doc_name">
						&nbsp;
	                     <div class="form-group">
							<span file_type="10801" onclick="selectFile(this);" id="addFileSpan" class="btn  btn-white btn-default btn-round ">
								<i class="ace-icon fa fa-cloud-upload bigger-100"></i>
								<span>上传</span>
							</span>
							
						</div>
						
	                     <div class="form-group">
	                     	 
		                     <button id="newDir" class="btn  btn-white btn-default btn-round " type="button">
								<i class="ace-icon fa fa-folder   bigger-110"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">新建文件夹</span>
							</button>
						</div>
						
						<div class="form-group right" >
	                     	 &nbsp;&nbsp;
		                     <input class="form-control" type="text" id="keyWords" placeholder="搜索你的文件" >
		                     <button id="searchForPlan" class="btn btn-link btn-sm" type="button">
								<i class="#ace-icon glyphicon   bigger-110"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">搜索</span>
							</button>
						</div>
						
					</form>
					<div style="margin-top: 10px;margin-bottom: 5px;" id="file_tab">
						<span id="qb">全部文件</span>
						<ul id="file_search_bar" >

						</ul>
						<ul id="file_bar" style="display: none;">
							<li data-deep='0'>
								<a href='javascript:void(0);' >返回上一级</a>
								<span>|</span>
								<a href='javascript:void(0);' onclick='queryFileList("","")'>全部文件</a>
							</li>
						</ul>
						
					</div>
					<table class="table table-striped table-bordered table-hover">
						<thead class="thin-border-bottom">
							<tr>
								<th width="70%" id="tab_th"><input type="checkbox" id="all">&nbsp;<span id="txtSpan">文件名</span> <span id="delSpan" style="display: none;"></span></th>
								<th width="10%">大小</th>
								<th class="hidden-480" width="20%">修改时间</th>
								
							</tr>
						</thead>

						<tbody id="fileTbody">
							
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<jsp:include page="/jsp/file_upload/fileupload_gczl.jsp" flush="true" />
<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
 <script
		src="${pageContext.request.contextPath }/assets/js/jquery-ui.custom.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
	<script type="text/javascript" src="${ctx}/assets/sys_resources/plugins/lightbox/jquery.min.js"></script>		
	<script type="text/javascript" src="${ctx}/assets/sys_resources/plugins/lightbox/jquery.lightbox.min.js"></script>			
</body>

<script type="text/javascript">
var scripts =[null];
var xmUid ="";
var controllername ="${pageContext.request.contextPath}/projectdoc/pmProjectDocsController";
ace.load_ajax_scripts(scripts, function() {

	//全选 全不选
	$("#all").click(function(){
		
		if(this.checked){
			　var ids=$("input[name='file_name']");  
			　for(var i=0;i<ids.length;i++){   
			　　　ids[i].checked="checked";  
			　}
			var num = $("#fileTbody tr").length;
			$("#delSpan").empty();
			$("#delSpan").prepend("已选中"+num+"个文件/文件夹 <button onclick='deleteFiles();' class='btn btn-link btn-sm'>删除</button>");
			$("#delSpan").show();
			$("#txtSpan").hide();
			
		}else{
			 $("#fileTbody :checkbox").removeAttr("checked","checked");
			 $("#delSpan").hide();
			 $("#txtSpan").show();
		}
	});
	

	//复选框事件
	$(document).on({ 
	    click: function() { 
	    	//已选中文件
	    	var num = $("input:checkbox[name='file_name']:checked").length;
	    	//所有文件
	    	var num1 = $("#fileTbody tr").length;
	    	var all = document.getElementById("all");
	    	//如果没有选择 恢复默认状态 否则有选择 显示删除
	    	if(num==0){
	    		$("#delSpan").hide();
				$("#txtSpan").show();
				$("#all").removeAttr("checked","checked");
	    	}else{
		    	$("#delSpan").empty();
		    	if(num==1){
		    		$("#delSpan").prepend("已选中"+num+"个文件/文件夹 <button onclick='deleteFiles();' class='btn btn-link btn-sm'>删除</button>&nbsp;<button id='renameBtn' onclick='rename();' class='btn btn-link btn-sm'>重命名</button>");
		    	}else{
		    		$("#delSpan").prepend("已选中"+num+"个文件/文件夹 <button onclick='deleteFiles();' class='btn btn-link btn-sm'>删除</button>");
		    	}
				
		    	$("#delSpan").show();
				$("#txtSpan").hide();
	    	}
	    	
	    	//如果全部都选上了 则把全选勾上 否则去掉勾选
	    	if(num == num1){
	    		all.checked="checked";
	    	}else{
	    		all.checked="";
	    	}

	    } 
	}, "input[name='file_name']");
	
	//新建文件夹
	$("#newDir").click(function(){
		
		var html = "";
			html += "<tr>";
			html += "	<td><input type='checkbox' name='file_name'>&nbsp;";
			html += " <input type='text' id='dirName' value='新建文件夹'>&nbsp;&nbsp;&nbsp;";
			html += "<span style='font:14px;'><a href='javascript:void(0);' onclick='saveDir();'>√</a></span>&nbsp;&nbsp;";
			html += "<span style='font:14px;'><a href='javascript:void(0);' onclick='deleteDir();'>×</a></span></td>";
			html += "	<td></td>";
			html += "	<td></td>";
			html += "</tr>";
		//判断有没有刚新建的文件夹 没有才添加	
		if($("#fileTbody input[id='dirName']").val()==undefined){
			$("#fileTbody").prepend(html);
		};	
		
	});
	
	//模糊查询
	$("#searchForPlan").click(function(){
		var keyWords = $.trim($("#keyWords").val());
		if(keyWords==""){
			queryFileList("","");
		}else{
			$.ajax({
				url : controllername+"?queryFiles",
				cache : false,
				async :	false,
				data : {"keyWords":keyWords,
						"project_uid":$("#project_uid").val()
					   },
				dataType : "json",  
				type : 'post',
				success : function(response) {
					if(response.success){
						$("#qb").hide();
						$("#file_bar").hide();
						$("#file_search_bar").empty();
						var html = "<li>";
							html +="<a href='javascript:void(0);' onclick='queryFileList(\"\",\"\")'>返回上一级</a>";
							html +="<span>|</span>";
							html +="<a href='javascript:void(0);' onclick='queryFileList(\"\",\"\")'>全部文件</a>";
							html +="<span>></span></li>";
							html += "<li><span>搜索:'"+keyWords+"'</span></li>";
						$("#file_search_bar").html(html);
				
						initTable(response.obj);
					}else{
						xAlert("信息提示","查询失败，请联系管理员！");
					}
				}
			});
		}

	});
	
	queryFileList("","");
	
	//删除多余的 div 避免弹出图片时出现多层
	var div1 = $(".jquery-lightbox-overlay").eq(0);
	var div2 = $(".jquery-lightbox-move").eq(0);

	$(".jquery-lightbox-overlay").remove();
	$(".jquery-lightbox-move").remove();
	$("body").append(div1);
	$("body").append(div2);
});
//解决版本冲突问题
(function () {
    var v17 = $.noConflict(true);
    window.$.v17 = v17;
 })();

(function ($) {
 	$('.lightbox').lightbox();
 })(window.$.v17);

//保存文件夹
function saveDir(){
	var dirName = $("#dirName").val();
	if(dirName != ""){
		$.ajax({
			url : controllername+"?saveDir",
			cache : false,
			async :	false,
			data : {"P_DOCS_UID":$("#p_doc_uid").val(),
					"project_uid":$("#project_uid").val(),
					"dirName":dirName
				   },
			dataType : "json",  
			type : 'post',
			success : function(response) {
				if(response.success){
					xAlert("信息提示","文件夹创建成功！");
					queryFileList($("#p_doc_uid").val(),$("#p_doc_name").val(),0);
				}else{
					xAlert("信息提示","新建文件夹失败，请联系管理员！");
				}
			}
		});
	}else{
		xAlert("信息提示","文件夹名称不能为空，请输入文件夹名称！");
		return ;
	}

}

//删除文件夹
function deleteDir(){
	 $("#fileTbody tr:eq(0)").remove();
}

//文件重命名
function rename(){
	//$("input[name='file_name']").unbind("click");
	$("#all").attr("disabled","disabled");
	$("#renameBtn").hide();
	$("#fileTbody input").each(function(){
		$(this).attr("disabled","disabled");
		if($(this).is(":checked")){
			var name = $(this).siblings("a").text(); 
			var id = $(this).val();
			var html = " <input type='text' id='docName' value='"+name+"'><input type='hidden' id='docId' value='"+id+"'>&nbsp;&nbsp;&nbsp;";
				html += "<span style='font:14px;'><a href='javascript:void(0);' onclick='saveName();'>√</a></span>&nbsp;&nbsp;";
				html += "<span style='font:14px;'><a href='javascript:void(0);' onclick='restore();'>×</a></span></td>";
			$(this).siblings("a").replaceWith(html);
			
		}
	});
}

//修改文件名称
function saveName(){
	var docName = $("#docName").val();
	if(docName != ""){
		$.ajax({
			url : controllername+"?updateDir",
			cache : false,
			async :	false,
			data : {"docId":$("#docId").val(),
					"docName":docName
				   },
			dataType : "json",  
			type : 'post',
			success : function(response) {
				if(response.success){
					xAlert("信息提示","重命名成功！");
					queryFileList($("#p_doc_uid").val(),$("#p_doc_name").val(),0);
				}else{
					xAlert("信息提示","重命名失败，请联系管理员！");
				}
			}
		});
	}else{
		xAlert("信息提示","文件(夹)名称不能为空，请输入文件名称！");
		return;
	}

}

//复原
function restore(){
	queryFileList($("#p_doc_uid").val(),$("#p_doc_name").val(),0);
	$("#all").removeAttr("disabled");
}

//查询表格数据
function queryFileList(pUid,name,_deep){
	$("#keyWords").val("");
	$("#file_search_bar").empty();
	$.ajax({
		url : controllername+"?queryFileList",
		cache : false,
		async :	false,
		data : {"P_DOCS_UID":pUid,
				"project_uid":$("#project_uid").val()
			   },
		dataType : "json",  
		type : 'post',
		success : function(response) {
			if(response.success){
				//获取最后一个深度
				var deep = $("#file_bar li:last").attr("data-deep");
				if(pUid!=""){
					var html = "";
					if(_deep<deep){
						var $deep =  _deep-1;
						$("li[data-deep='"+$deep+"']").nextAll().remove();
						deep = _deep;
						//当层级为1时 返回到最高层
						if($deep==0){
							var _a = $("#file_bar li:first").find("a").eq(0).attr("onclick","queryFileList(\"\",\"\")");
						}else{
							var _click = $("li[data-deep='"+$deep+"']").find("span").eq(1).find("a").attr("onclick");
							var _a = $("#file_bar li:first").find("a").eq(0).attr("onclick",_click);
						}
						
						//层级0 不添加
						if(_deep!=0){
							html = "<li class='txt' data-deep='"+deep+"'><span>></span><span>"+name+"</span></li>";
						}
					}else{
						var _span = $("#file_bar li:last").find("span").eq(1);
						_span.html("<a href='javascript:void(0);' onclick=\"queryFileList('"+$("#p_doc_uid").val()+"','"+$("#p_doc_name").val()+"','"+deep+"')\">"+$("#p_doc_name").val()+"</a>");
						var _a = $("#file_bar li:first").find("a").eq(0).attr("onclick","queryFileList('"+$("#p_doc_uid").val()+"','"+$("#p_doc_name").val()+"','"+deep+"')");
						deep++;	
						//层级0 不添加
						if(_deep!=0){
							html = "<li class='txt' data-deep='"+deep+"'><span>></span><span>"+name+"</span></li>";
						}
						
					}
					
					$("#qb").hide();
					$("#file_bar").show();
					
					$("#file_bar").append(html);
					
				}else{
					$("#qb").show();
					$("#file_bar").hide();
					$("li").remove(".txt");
				}
				$("#p_doc_uid").val(pUid);
				$("#p_doc_name").val(name);
				initTable(response.obj);
			}else{
				xAlert("信息提示","查询失败，请联系管理员！");
			}
		}
	});
}

//组织表格数据
function initTable(data){
	var html = "";
	$(data).each(function(index,element){

		html += "<tr>";
		if(checkDirAuthority($("#project_uid").val(),$("#projectUserId").val(),element.PROJECT_DOCS_UID)){
			if(element.NODE_TYPE=="DIR"){
				html += "	<td><input type='checkbox' name='file_name' value='"+element.PROJECT_DOCS_UID+"' >&nbsp;<img src='${ctx}/assets/img/doc/file.png' /><a  href='javascript:void(0);' onclick=\"queryFileList('"+element.PROJECT_DOCS_UID+"','"+element.NODE_NAME+"')\">"+element.NODE_NAME+"</a></td>";
				html += "	<td></td>";
			}else{
				var imageStr = getImage(element.FILE_EXT_NAME);
				if(isMedia(element.FILE_EXT_NAME)){
					html += "	<td><input type='checkbox' name='file_name' value='"+element.PROJECT_DOCS_UID+"'>&nbsp;<img src='${ctx}/assets/img/doc/"+imageStr+"' /><a href='${ctx}/assets/sys_resources/plugins/lightbox/player.swf?file=<%=root%>"+element.FILE_PATH+"&lightbox[width]=600&lightbox[height]=400' class='lightbox'>"+element.NODE_NAME+"</a><span style='float:right'><a href='${ctx}/UploadUtilServlet?getfile="+element.FILE_PATH+"&fileDir=<%=fileRoot%>' title='下载'><i class='ace-icon fa fa-download bigger-125 blue'></i></a></span></td>";
				}else if(isPicture(element.FILE_EXT_NAME)){
					html += "	<td><input type='checkbox' name='file_name' value='"+element.PROJECT_DOCS_UID+"'>&nbsp;<img src='${ctx}/assets/img/doc/"+imageStr+"' /><a class='lightbox'  href='<%=root%>"+element.FILE_PATH+"'>"+element.NODE_NAME+"</a><span style='float:right'><a href='${ctx}/UploadUtilServlet?getfile="+element.FILE_PATH+"&fileDir=<%=fileRoot%>' title='下载'><i class='ace-icon fa fa-download bigger-125 blue'></i></a></span></td>";
				}else{
					html += "	<td><input type='checkbox' name='file_name' value='"+element.PROJECT_DOCS_UID+"'>&nbsp;<img src='${ctx}/assets/img/doc/"+imageStr+"' /><a href='${ctx}/UploadUtilServlet?getfile="+element.FILE_PATH+"&fileDir=<%=fileRoot%>' title='下载'>"+element.NODE_NAME+"</a><span style='float:right'><a href='${ctx}/UploadUtilServlet?getfile="+element.FILE_PATH+"&fileDir=<%=fileRoot%>' title='下载'><i class='ace-icon fa fa-download bigger-125 blue'></i></a></span></td>";
				}
				
				html += "	<td>"+element.FILE_SIZE/1000+"KB</td>";
			}
		
			html += "	<td>"+element.UPDATE_DATE+"</td>";
		}

		html += "</tr>";
		
	});
	//初始化状态
	$("#delSpan").hide();
	$("#txtSpan").show();
	var all = document.getElementById("all");
	all.checked = "";
	
	$("#fileTbody").html(html);
}

//删除文件夹（文件）
function deleteFiles(){
	bootbox.confirm("确认要删除吗？", function(result){
		if(result){
			var doc_uids = getCheckboxV("file_name"); 
			$.ajax({
				url : controllername+"?deleteFiles",
				cache : false,
				async :	false,
				data : {"doc_uids":doc_uids},
				dataType : "json",  
				type : 'post',
				success : function(response) {
					if(response.success){
						xAlert("信息提示","删除成功！");
						queryFileList($("#p_doc_uid").val(),$("#p_doc_name").val(),0);
					}else{
						xAlert("信息提示","删除失败，请联系管理员！");
					}
				}
			});
		}else{
			return;
		}
	});

}


//附件上传	
function selectFile(obj){
	var p_doc_uid = $("#p_doc_uid").val();
	var file_type = $(obj).attr('file_type');

	setFileData("PM_PROJECT_DOCS","PROJECT_DOCS_UID",p_doc_uid,file_type,$("#project_uid").val());
    document.getElementById("fileupload_btn").click();
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

//上传完成回调
function callBack(){
	xAlert("信息提示","上传成功！");
	queryFileList($("#p_doc_uid").val(),$("#p_doc_name").val(),0);
}


function getImage(fileExtName){
	fileExtName = fileExtName.toLowerCase();
	var imageStr = "wd.png";
	if(fileExtName=='gif'||fileExtName=='jpeg'||fileExtName=='jpg'||fileExtName=='png'){
		imageStr = "image.png";
	}else if(fileExtName=='mp4'||fileExtName=='aac'){
		imageStr = "video.png";
	}else if(fileExtName=='mp3'){
		imageStr = "audio.png";
	}else if(fileExtName=='pdf'){
		imageStr = "pdf.png";
	}else if(fileExtName=='zip'||fileExtName=='rar'||fileExtName=='war'){
		imageStr = "zip.png";
	}else if(fileExtName=='doc'||fileExtName=='docx'){
		imageStr = "doc.png";
	}else if(fileExtName=='txt'){
		imageStr = "txt.png";
	}else if(fileExtName=='xls'||fileExtName=='xlsx'){
		imageStr = "xlsx.png";
	}else if(fileExtName=='ppt'||fileExtName=='pptx'){
		imageStr = "pptx.png";
	}
		
	return imageStr;
}
</script>
</html>