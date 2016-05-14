<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Invite</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <script src="<%=basePath%>assets/sys_resources/js/jquery.js"></script>
	<%
		String uuid = request.getParameter("uuid");
	%>
		<link href="<%=basePath%>assets/sys_resources/lightbox.css">
		<link rel="stylesheet" href="<%=basePath%>assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="<%=basePath%>assets/css/font-awesome.min.css" />
		<!-- text fonts -->
		<link rel="stylesheet" href="<%=basePath%>assets/css/ace-fonts.css" />
		<!-- table CSS样式  begin-->
		<link rel="stylesheet" href="<%=basePath%>assets/css/ui.jqgrid.css" />
		<link rel="stylesheet" href="<%=basePath%>assets/css/jquery-ui.min.css" />
		<!-- table CSS样式  end-->	
		<link rel="stylesheet" href="<%=basePath%>assets/css/jquery.gritter.css" />
		<!-- ace styles -->
		<link rel="stylesheet" href="<%=basePath%>assets/css/ace.min.css" id="main-ace-style" />
<style type="text/css">

.main{width:1150px;margin:auto;}
.main ul{*overflow:hidden;_height:1%;}
.main ul li{border:1px solid #CCC;border-bottom:2px solid #CCC;border-top:1px solid #DBDBDB;float:left;display:inline;list-style:none;margin:5px;background:#FFF;width:356px;}
.main ul li.hover{border:1px solid #fa3241;border-bottom:2px solid #fa3241;}
.main ul li img{width:335px;display:block;}
.main ul li img.hover{filter:Alpha(opacity=90);-moz-opacity:.9;opacity:0.9;}
.main ul li h3,.main ul li p{padding:0 10px 0 10px;font-weight:normal;}
.main ul li p{background:#F3F0FF;line-height:25px;}

.img_block{position:relative;padding:10px;}

.zoom,.ilike{position:absolute;line-height:25px;padding:0 0 2px 18px;text-align:center;top:14px;display:block;text-decoration:none;}
.zoom{left:14px;background-position:0 -71px;}
.zoom:hover{background-position:0 -99px;}
.ilike{right:14px;width:57px;background-position:0 -140px;}
.ilike:hover{background-position:0 -167px;}


</style>
  
<body class="no-skin">

	<div class="main-container" id="main-container">
		<div class="page-content">
				<div  style="width: 83%; margin: 0px auto;" >
					
						<form class="form-inline"  id="queryForm"
							style="padding:3px;">

								<div class="form-group" >
									<a data-target="" id="new" data-toggle="modal" >
										<button id="btn_new" class="btn btn-primary btn-sm" type="button" onclick="inviteConfirm();"><i class="icon-edit bigger-110"></i>确认邀请</button>
									</a>
								</div>
							
							</form>
						
					</div>
					<div  class="row">
						<div class="col-xs-12">
							<div class="main">
															
							</div>

						</div>
					</div>
				</div>
			</div>
			

	<script
		src="<%=basePath%>assets/js/jquery-ui.custom.min.js"></script>
		
<script type="text/javascript">

var scripts =[null];
$(function() {
	queryProject();
	show();
});

function show(){
	$(".waterfall li").mouseover(function() {
		$(this).addClass("hover");
		//$(this).find(".zoom").show();
	});

	$(".waterfall li").mouseout(function() {
		$(this).removeClass("hover");
		//$(this).find(".zoom").hide();
	});
	
}

function queryProject(){

	$.ajax({
		url :'<%=basePath%>project/buProjectController?queryProjectByid',
		data : {"uuid":"<%=uuid%>","projectName":""},
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
					html +='<ul class="waterfall">';
				}
				html +='<li>';
				html +='<div class="img_block">';
				html +='<img src=\''+url+'\' /> </div>';
				html +='<h3>'+element.PROJECT_NAME+'</h3>';
				html +='<p>'+element.PROJECT_DESC+'</p></li>';
				if(cols==num){
					html +="</ul>";
				}
				cols++;
				if(cols>num){
					cols = 1;
					line++;
				}
				$('#project_uid').val(element.PROJECT_UID);
			});
			$('.main').empty();
			$('.main').html(html);
			
	    }
	});
}

function inviteConfirm(){
		$.ajax({
			url: '${pageContext.request.contextPath }/project/buProjectUserController?userConfirm',
			data: {"uuid":"<%=uuid%>"},
			cache: false,
			async: false,
			dataType: "json",
			type: 'post',
			success: function(response) {
				if(response.obj=='true'){
					alert(response.msg);
					window.location = <%=basePath%>;
					//openProject();
				}else{
					alert(response.msg);
				}
				
			}
	});
}

function openProject(){
	//$("#project_uid").val(projectUid);
	//$("#project_name").val(projectName);
    var f =document.getElementById('gdzxtformid');
    var url='${pageContext.request.contextPath }/jsp/framework/project/myproject_index.jsp?project_uid=1&projectUserId=1';
    //var url='${pageContext.request.contextPath }/jsp/framework/project/project_main.jsp';
	f.action=url;
	f.target="_blank"; 
	f.submit();
}

</script>
	<form id="gdzxtformid" method="post" >
		<input type="hidden" name="project_uid" id="project_uid" >
	</form>
</body>

</html>
