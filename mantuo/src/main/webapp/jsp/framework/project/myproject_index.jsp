<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*" %>
<!DOCTYPE html>
<html>
<head>
<title>菜单导航</title>
    <%@ include file="/jsp/framework/common/taglibs.jsp"%>
    <%@ include file="/jsp/framework/common/head.jsp"%>
    <%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ page import="com.ccthanking.framework.Constants"%>

<link rel="icon" href="${pageContext.request.contextPath }/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="${pageContext.request.contextPath }/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/bootstrap.css">
    <script
    src="${pageContext.request.contextPath }/assets/js/jquery-ui.custom.min.js"></script>
<%
	String project_uid = request.getParameter("project_uid");
	String projectUserId = request.getParameter("projectUserId");
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

<style type="text/css">
*{
    margin:0px;
    padding:0px;
    box-sizing: border-box !important;
    list-style-type: none;
    text-decoration:none !important;
}
.cont_txt{ text-indent:2em; width:30%; margin:7% 4% 0 7%; float:left;}
.main-left-midden{
        width:100%;
        height:80%;
    }
.left{
    float:left;
}
.right{
    float:right;
}

#header{
    width:100%;
    height:70px;
    background-image:url(${pageContext.request.contextPath }/assets/img/topnavi_bg_02.jpg) ;
}
    .white{
        color:#fff;
    }
    .header-right{
       display:inline-block;
        width:15%;
        position:relative;
        left:64%;
        color:#fff;
    }
    .mgr{
    margin-right:2%;
    }
    .mgl{
    margin-left:2%;
    }


#content{
    width:100%;
    height:80%;
    margin-top:30px;
}

#main-right ul li {
    position: relative;
    width:170px;
    height:129px;
    background: #80889B;
    color:#fff;
    margin:0 0 30px 20px;
}
        #main-right ul li:hover{
        background:#45536c;
        }
#main-right ul li span{
    position: absolute;
    margin:10px;
    font-size: 12px;
}
#main-right ul li img{
    position: absolute;
    top:45px;
    left:60px;

}
#main-left{
    border:1px solid #80889B;
    height:445px;
    padding:0px;
    position:relative;
    left:5%;

}
.main-left-header{
    background: #80889B;
    color:#fff;
    width:100%;
    height:28px;
    padding:3px 30px;
}
#main-right ul li{
    cursor: pointer;
    }
#fanhui{
	float:right;
	width:50px;
	height:40px;
	margin-top:15px;
	text-align:center;

}    
#fanhui:hover{
	background:orange;
}

#fanhui i{
	margin-top:25%;
}
    @media screen and (max-device-width:768px){
    .header-right{
            display:inline-block;
            width:18%;
            position:relative;
            left:50%;
            color:#fff;
          }

    }
</style>

</head>
<body>
<div id="header" class="container">
    <%--<ul>--%>
        <%--<li class="left">综合信息监测平台</li>--%>
        <%--<li class="right tc"><a href="">退出</a></li>--%>
        <%--<li class="right yhm_val">33</li><!---先写个值看1下位置~----->--%>
        <%--<li class="right yhm">用户名:</li>--%>
    <%--</ul>--%>
    <div class="col-lg-12">
        <img src="${pageContext.request.contextPath }/assets/img/logo.png">
        <span class="white">漫拓云工程平台</span>
        <div class="header-right">
             <a  data-toggle="dropdown" href="#" class="dropdown-toggle white">
                <small class="mgr">欢迎您：</small><i class="ace-icon fa fa-user mgr"></i ><%=username %><i class="ace-icon fa fa-caret-down mgr mgl"></i>	</a>
                <span class="badge badge-warning"></span><em>|</em>
             </a>
            <ul class="user-menu  dropdown-menu  ">
                 <li><a href="#" id="updatePwd"><i class="ace-icon fa fa-cog"></i>修改密码</a></li>
                 <li><a href="javascript:void(0)" onclick="openMessage(1)" id="updatePwd"><i class="ace-icon fa fa-envelope"></i>消息盒子</a></li>
            </ul>

             <a href="javascript:void(0)" class="white" onclick="dologout();"><i class="ace-icon fa fa-power-off"></i> 退出 </a>
              <!-- javascript:window.history.back() -->
         </div>
         <div id="fanhui">	
         	<a href="javascript:void(0)"  onclick="dohoutui();" > <i
							class="ace-icon fa fa-reply icon-only" style="font-size:20px;color:#fff;"
							title="返回项目列表"></i> </a>
		</div>					
    </div>
</div>
<div id="content">
	<div class="">

        <div id="main-left" class="left col-lg-6 col-sm-6 ">
                 <div class="main-left-header">
                         项目简介
                </div>
                <div class="main-left-midden">
                </div>
          
        </div>

    <div id="main-right" class="right col-lg-5 col-sm-6">
        <ul class="left">
            <li onclick="doPage('111');">
                <span>基坑</span>
                <img src="${pageContext.request.contextPath }/assets/img/index/icon_1.png" alt="" />
            </li>
            <li onclick="doPage('108');">
                <span>BIM</span>
                <img src="${pageContext.request.contextPath }/assets/img/index/icon_3.png" alt="" />
            </li>
            <li>
                <span>投资控制</span>
                <img src="${pageContext.request.contextPath }/assets/img/index/icon_5.png" alt=""/>
            </li>
        </ul>
        <ul class="left">
            <li onclick="doPage('15');">
                <span>微现场</span>
                <img src="${pageContext.request.contextPath }/assets/img/index/icon_2.png" alt="" />
            </li>
            <li>
                <span>合同管理</span>
                <img src="${pageContext.request.contextPath }/assets/img/index/icon_4.png" alt=""/>
            </li>
            <li onclick="doPage('1');">
                <span>设置</span>
                <img src="${pageContext.request.contextPath }/assets/img/set.png" alt="" />
            </li>
        </ul>
    </div>

    </div>
</div>
	<form id="gdzxtformid" method="post" >
		<input type="hidden" name="project_uid" id="project_uid" value="<%=project_uid%>">
		<input type="hidden" name="projectUserId" id="projectUserId" value="<%=projectUserId %>">
		<input type="hidden" name="root_uid" id="root_uid">
	</form>
	<div id="user-pwdupdate" class="modal"></div>
	<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>

<script src="${pageContext.request.contextPath }/assets/js/jquery-easyui/jquery.easyui.min.js"></script> 
<script src="${pageContext.request.contextPath }/assets/js/jquery-easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath }/assets/js/jquery-easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/assets/js/jquery-easyui/themes/icon.css" rel="stylesheet" type="text/css" />


<script type="text/javascript">
var conn="${pageContext.request.contextPath}/project/buProjectController";
$(function(){
	
	var project_uid="";
	var project_name="";
	var project_desc="";
	var projectuid ="<%=project_uid%>";		
		$.ajax({
		url :conn+"?queryid",
		data : {"projectuid":projectuid},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
		
			msg = eval("("+response.obj+")");
		    project_uid =   msg.response.data[0].PROJECT_UID;
		    project_name=	msg.response.data[0].PROJECT_NAME;
		    project_desc=   msg.response.data[0].PROJECT_DESC;
		    $.ajax({
		    	url : "${pageContext.request.contextPath }/fileUploadUtilController?queryFileByType&targetUid="+project_uid+"&file_type=10001",
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

    var	html ='<h3 align="center">'+project_name+'</h3>';
			html +='<img style="width:50%;height:70%;margin-top:3%;" src="'+url+'"/>';
			html +='<span  class="cont_txt">'+project_desc+'</span>';

			$('.main-left-midden').html(html);
			
	    }
		
	});
});

/**
 * 未读消息的数量
 */
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

queryMessageCount();

function doPage(root_uid){
	 $("#root_uid").val(root_uid);
    

    var f =document.getElementById('gdzxtformid');
    var url='${pageContext.request.contextPath }/pageproject/framework/project/frame_project_main';
   
	f.action=url; 
	f.target="_self";
	f.submit();
}

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
 function dohoutui(){
	 document.location.href="${pageContext.request.contextPath }";
 }

 //打开消息窗口
 function openMessage(x){   
		window.location.href = '${pageContext.request.contextPath}/jsp/framework/project/frame_project_message_main.jsp'; 
}
</script>
</body>
</html>

