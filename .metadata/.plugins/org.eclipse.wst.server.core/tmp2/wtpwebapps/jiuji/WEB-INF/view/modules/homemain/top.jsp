<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/commons/taglib.jsp" %> 
<%@ include file="/WEB-INF/view/commons/jsp/main.jspf"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1" /> 
<!--<script type="text/javascript" src="${ctxStatic}/js/jquery-1.8.2.min.js"></script>	
<script src="js/jquery.js"></script>
<script src="js/menu.js"></script>-->
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/homemain/top.css">
<title>header</title>
<script type="text/javascript">

var rootPath = '${ctx}';	

function searchQuestion(){
	var title = $("#title").val();
	if(null == title || title.trim() == ""){
		alert("请填写想问的内容");
		$("#title").focus();
		return;
	}
	/* $.ajax({type:"post",
	    url:rootPath+"/stuPersonalCenter/enterMyAnswer.do",
	    dataType: "json",
	    data:{
	    	  title:title
	    },
	    success:function(data){
	    }
	});  */ 
	parent.location.href= rootPath+"/stuPersonalCenter/enterMyAnswerList.do?title="+encodeURI(encodeURI(title));
}
</script>
<style type="text/css">
html{
  margin:0;
  padding:0; 
}
  body{
     margin:0px;
     padding:0px;
     width:100%;
     height:85px;
     display:block;  
    /*  background-width:1px;   */
     border-bottom-width:1px;
     border-bottom-style:solid;
     border-bottom-color:blue;  
  }
  
  .top{
     width:100%;
     height:88px;
     display:block; 
     border:1px solid blue; 
  }
  
  div{
     margin:0px;
     padding:0px; 
  }
   
</style>
</head> 
<body>
 <div class="wrapper">
     <header>
     <div class="top_left">
       <img src="${ctxStatic}/images/homemain/logo.jpg"/>
       <p class="top_title">建工网校班主任管理后台</p>
     </div>
     
       <ul class="top_right">
         <li class="user_list list_one">
         <img src="${ctxStatic}/images/homemain/user_icon.png"/>
         <span class="user_name">${teacher.userName}</span>
         </li>
         <li class="user_list"><a href="${ctx}/homePage/toExit.do" target="main"><img src="${ctxStatic}/images/homemain/tuichu.png"/></a></li>
       </ul>
    
   </header>
 </div>
</body>

<%-- <body>  
  <div class="top">   
      
       <div  style="margin-left:0px;margin-top:0px;"  class="ax_图片">
        <img id="u3_img" class="img " src="${ctxStatic}/images/homemain/u3.png"/>
        <!-- Unnamed () -->
        <div   class="text">
          <p><span>&nbsp;</span></p>
        </div>
      </div>  
      
      <!-- Unnamed (形状) -->
        <div  style="margin-left:400px;" class="ax_h2"> 
       <div  style="color:blue;font-size:18px;" class="text">
          <p> <span>班主任工作</span><span>管理后台</span></p>
          </div>
      </div> 
  </div>     
</body> --%>
</html>
