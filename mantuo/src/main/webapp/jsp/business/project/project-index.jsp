<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ page import="com.ccthanking.framework.Constants"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<link rel="icon" href="${pageContext.request.contextPath }/favicon.ico" type="image/x-icon" /> 
<link rel="shortcut icon" href="${pageContext.request.contextPath }/favicon.ico" type="image/x-icon" />  
<%
User user = RestContext.getCurrentUser();
String user_uid = "";
String username = "";
if(user!=null){
	user_uid = user.getIdCard();
	username = user.getName();
}else{
	response.sendRedirect("/"+Constants.APP_NAME);
	return;
}
%>
<title><fmt:message key="ui.title" /></title>
<%@ include file="/jsp/framework/common/head.jsp"%>
		
<style type="text/css">

@charset "utf-8";
*{ margin:0;padding:0;}
body{ background-color:#FFFFFF; font-family:"Microsoft YaHei";font-size:14px;color:#202932;}
ul,ol{ list-style:none;}
select,option,input,img{ border:none;}
em{ font-style:normal;}
.clearfix:after{ content:"/0020"; display:block; clear:both; height:0; visibility:hidden;}
.clearfix{ clear:both; zoom:1;}
a{color:#4e4c4c; text-decoration:none;font-weight:normal; }
a:hover{ color:#F60;}
a img { /* 此选择器将删除某些浏览器中显示在图像周围的默认蓝色边框（当该图像包含在链接中时） */
	border: none;
}

/* ~~ 站点链接的样式必须保持此顺序，包括用于创建悬停效果的选择器组在内。 ~~ */
a:link {
	
	text-decoration: underline; /* 除非将链接设置成极为独特的外观样式，否则最好提供下划线，以便可从视觉上快速识别 */
}
/*顶部导航开始--------------start*/
.top_navi{height:50px; width:100%; background-color:#4d4848;color:#ffffff;}
.top_navi .navi{ height:100%; width:100%; margin:0 auto;}
.navi h3{ height:50px;line-height:50px;float:left; font-size:20px;margin-left: 20px;margin-top:0px !important;}
.navi .navi_txt{ float:right;height:50px;line-height:50px;margin-right: 20px;}
.navi_txt>li{ float:left;}
.navi_txt li a{color:#ffffff; text-decoration:none;}
.navi_txt li em{padding-left:10px; padding-right:10px;}
/*顶部导航结束--------------end*/

.container{ width:1000px; height:100%; margin:0 auto;}
.container .content1{ width:100%; height:350px; float:left;}
.cont{ width:30%; height:80%; float:left; margin-top:50px; margin-left:26px;}
.cont_article1{}
/*.cont_article1 p h3{ font-size:18px; font-weight:normal; border:1px solid red;margin-bottom:15px;}*/
.cont_txt{ text-indent:2em; width:310px; margin-top:15px; }
.cont p a{ text-decoration:none;}
.cont p a em{font-size:12px; color:#F30;}
.cont h3{margin-top:0px !important;}
.container .content2{ width:100%; height:350px;  float:left;}
.content2 .cont2{ margin-top:15px;}	

.gn-down{
	position:absolute;
	top:4.3%;
	right:3%;
	}
	.gn-down>li>a{
		color:black;
	}
</style>

</head>

<body >
	<!--顶部导航开始-----------start-->
	<div class="top_navi">
	<div class="navi">
	<!-- 
	<div style="float: left;">
		
		<img src="${ctx}/assets/images/logo/logo_01.png" height="45px;"/>
	</div>
	-->
	<h3>漫拓云工程平台</h3>
	<ul class="navi_txt">
		<li><a href="#" id="newproject"><img src="${ctx}/assets/images/icon.png" />添加项目</a><em>|</em></li>
		<li>

			

			<a href="javascript:void(0);" id="user_name"  class="dropdown-toggle" data-toggle="dropdown" style="cursor:default; position:relative;">
				<small>欢迎您：</small><i class="ace-icon fa fa-user"></i>  <%=username%>  
				<i class="ace-icon fa fa-caret-down"></i></a>
				<span class="badge badge-warning"></span><em>|</em>

			<ul class="user-menu dropdown-menu-right dropdown-menu gn-down ">
				<li><a id="updatePwd" href="#" ><i class="ace-icon fa fa-cog"></i>修改密码</a></li>
                <li><a href="javascript:void(0)" onclick="openMessage(1)" id="updatePwd"><i class="ace-icon fa fa-envelope"></i>消息盒子</a></li>
			</ul>
			

			<a href="javascript:void(0)" onclick="dologout();"><i class="ace-icon fa fa-power-off"></i> 退出 </a>
		</li>
	</ul>
	</div>
	</div>

	<!--顶部导航结束-----------end-->
	<div class="container">
															
	</div>

	<div id="jldwUser-input" class="modal"></div>
	<div id="jkjcProjct-input" class="modal"></div>
	<div id="user-pwdupdate" class="modal"></div> 

<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
<script src="${pageContext.request.contextPath }/assets/js/jquery-ui.custom.min.js"></script>

<%--<script src="${pageContext.request.contextPath }/assets/js/jquery-easyui/jquery.min.js"></script>
--%><script src="${pageContext.request.contextPath }/assets/js/jquery-easyui/jquery.easyui.min.js"></script> 
<script src="${pageContext.request.contextPath }/assets/js/jquery-easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath }/assets/js/jquery-easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/assets/js/jquery-easyui/themes/icon.css" rel="stylesheet" type="text/css" />

<script type="text/javascript"> 


var scripts =[null]; 
ace.load_ajax_scripts(scripts, function() {
	init();
	queryProject();  
	queryMessageCount();
});

var messageCount=0;
//询问未读消息数量
function queryMessageCount(){
	$.ajax({
		url :'${ctx}/project/buProjectController?queryMessageCount', 
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			$(response.obj).each(function(index,element){  
				messageCount=element;
				$(".badge").text(element);
			});
	    }
	});
	   
	if(messageCount!=0){
		$.messager.show({  
	         title: "系统消息提示",  
	         showSpeed:600,
	         height:80,
	         msg: '<font color=green style="font-size:14px;font-weight:bold;">你有'+$(".badge").text()+'消息未读</font>',  
	         showType: 'slide',  
	         timeout: 5000  
	     }); 
	} 
}




function init(){

	$('#newproject').click(function() {
		$('#newproject').attr("data-target","jldwUser-input");
		$('#jldwUser-input').removeData("bs.modal");
		$("#jldwUser-input").empty();
		$('#jldwUser-input').modal({
			backdrop: 'static'
		});

		$.get("${ctx}/jsp/business/project/project-add.jsp?userUid="+<%=user_uid%>,function(data) {
			$("#jldwUser-input").empty();
			$("#jldwUser-input").html("");
			$("#jldwUser-input").html(data);
		});
	});
			
	$('#updatePwd').click(function (){
		   $('#updatePwd').attr("data-target","user-pwdupdate");
				$('#user-pwdupdate').removeData("bs.modal");
				$("#user-pwdupdate").empty();
				$('#user-pwdupdate').modal({
					backdrop: 'static'
				});
				$.get("${ctx}/jsp/business/setup/user-pwd-update.jsp?USER_UID="+'<%= user_uid%>',function(data) {
					$("#user-pwdupdate").empty();
					$("#user-pwdupdate").html("");
					$("#user-pwdupdate").html(data);
				});
			
		}); 
}

function queryProject(){
	var projecName = $('#PROJECT_NAME').val();
	$.ajax({
		url :'${ctx}/project/buProjectController?queryProjectList',
		data : {"userUid":<%=user_uid%>,"projectName":projecName},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {

			var num = 3;
			var line = 0;
			var cols = 1;
			var html = "";
			var url = "";
			$(response.obj).each(function(index,element){
				$.ajax({
					url : "${pageContext.request.contextPath }/fileUploadUtilController?queryFileByType&targetUid="+element.PROJECT_UID+"&file_type=10001",
					cache : false,
					async :	false,
					dataType : "json",
					success : function(result) {
						if(result.obj!=null&&result.obj!=""){
							$.each(result.obj,function(){
								url =  this.url;
							});
						}

					}
				});
				
				if(cols==1){
					html +='<div class="content1">';
				}
				//html +='<li onclick="openProject(\''+element.PROJECT_UID+'\',\''+element.PROJECT_NAME+'\');">';
				//html +='<div class="img_block">';
				//html +='<img src=\''+url+'\' /> </div>';
				//html +='<h3>'+element.PROJECT_NAME+'</h3>';
				//html +='<p>'+element.PROJECT_DESC+'</p></li>';
				
				html +='<div class="cont_article1 cont">';
				html +='<p><h3>'+element.PROJECT_NAME+'</h3></p>';
				html +='<p><a style="text-decorationnone;cursor:hand;" href="javascript:void(0);"><img  height="120" width="300" src="'+url+'" onclick="goMyProject(\''+element.PROJECT_UID+'\',\''+element.PROJECT_NAME+'\',\''+element.PROJECT_USER_UID+'\');"/></a></p>';
				if(element.PROJECT_DESC.length>100){
				       html +='<p class="cont_txt">'+element.PROJECT_DESC.substring(0,100)+'...</p>';
				}else
				{
					html +='<p class="cont_txt">'+element.PROJECT_DESC+'</p>';
				}
				html +='<p><a href="#" id="more" onclick="showDetail(\''+element.PROJECT_UID+'\',\''+element.PROJECT_NAME+'\');"><img src="${pageContext.request.contextPath }/assets/images/icon1.png" /><em>MORE</em></a></p>';
				html += '</div>';
				if(cols==num){
					html +="</div>";
				}
				cols++;
				if(cols>num){
					cols = 1;
					line++;
				}
			});
			
			$('.container').empty();
			$('.container').html(html);
	    }
	});
}

$('#more').tooltip({
 'container': 'body'
});


//点击 more显示 详细信息
function showDetail(pId,name){
  $('#more').attr("data-target","jkjcProjct-input");
		$('#jkjcProjct-input').removeData("bs.modal");
		$("#jkjcProjct-input").empty();
		$('#jkjcProjct-input').modal({
			backdrop: 'static'
		});

		$.get("${ctx}/jsp/business/project/project_detail.jsp?PROJECT_UID="+pId+"&PROJECT_NAME="+name,function(data) {
			$("#jkjcProjct-input").empty();
			$("#jkjcProjct-input").html("");
			$("#jkjcProjct-input").html(data);
		});
}

function openMessage(x){   
	window.location.href = '${pageContext.request.contextPath}/jsp/framework/project/frame_project_message_main.jsp'; 
}

function goMyProject(projectUid,projectName,projectUserId){ 
	$("#project_uid").val(projectUid);
	$("#project_name").val(projectName);
	$("#projectUserId").val(projectUserId); 
	window.location.href = '${pageContext.request.contextPath }/jsp/framework/project/myproject_index.jsp?project_uid='+projectUid+'&projectUserId='+projectUserId;
}


function openProject(projectUid,projectName,projectUserId){
	$("#project_uid").val(projectUid);
	$("#project_name").val(projectName);
	$("#projectUserId").val(projectUserId);
    var f =document.getElementById('gdzxtformid');
    var url='${pageContext.request.contextPath }/pageproject/framework/project/frame_project_main';
    //var url='${pageContext.request.contextPath }/jsp/framework/project/project_main.jsp';
	f.action=url;
	f.target="_blank"; 
	f.submit();
}

	    //显示注销
	    function showLogout(){
	    	if($("#transparentOpen").hasClass("open")){
	    		$("#transparentOpen").removeClass("open");
	    	}else{
	    		$("#transparentOpen").addClass("open");
	    	}
	    	
	    }
	    
	    //注销按钮响应函数
	    function dologout(){
	    	//document.location.href="${pageContext.request.contextPath }/userController/logout";
	    	bootbox.confirm("确认退出系统吗？", function(result) {
				if (result) {
					window.location.href="${pageContext.request.contextPath }/logout";
				} else {
					return;
				}
			});
	    	
	    }
</script>
	<form id="gdzxtformid" method="post" >
		<input type="hidden" name="project_uid" id="project_uid" >
		<input type="hidden" name="projectUserId" id="projectUserId" >
	</form>
</body>
</html>