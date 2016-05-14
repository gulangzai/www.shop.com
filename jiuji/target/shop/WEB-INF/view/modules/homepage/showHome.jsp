<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/commons/taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8"> 
<title>学尔森建工网校学员管理系统</title>
<link href="${ctxStatic}/css/homemain/wdxy_sy.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctxStatic}/js/m.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/jqPaginator.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/highcharts.js"></script>
</head>

<body>
   <div class="top">
           <ul class="nav">
                   <li class="xz">首页</li>
                   <li><a href="${ctx}/myStudent/myStudentList.do" target="right">学员列表</a></li>
           </ul>    
   </div>

   <h2 class="one">个人信息</h2>
   

   <div width="400" border="0" class="gerenxinxi">
    <ul>
      <li width="147" class="ydq">姓名</li>
      <li width="196"><input type="text" value="${bsUserInfoDetail.realName}" name="realName" readonly="readonly" id="realName"></li>
      <li width="43" class="zdq" onclick="change(realName)">修改</li>
    </ul>
   <%--  <ul>
      <li class="ydq">用户名</li>
      <li><input type="text" value="${bsUserInfoDetail.userName}" name="userName"  readonly="readonly" id="userName"></li>
      <li class="zdq" onclick="change(userName)">修改</li>
    </ul>
    <ul>
      <li class="ydq">密码</li>
      <li><input type="password" value="${bsUserInfoDetail.password}" name="password" readonly="readonly" id="password"></li>
      <li class="zdq" onclick="change(password)">修改</li>
    </ul> --%>
    <ul>
      <li class="ydq">手机号</li>
      <li><input type="text" value="${bsUserInfoDetail.telephone}" readonly="readonly" name="telephone" id="telephone"></li>
      <li class="zdq" onclick="change(telephone)">修改</li>
    </ul>
    <ul>
      <li class="ydq">机构名称</li>
      <li><input type="text" value="${bsUserInfoDetail.jigouName}" readonly="readonly" name="jigouName" id="jigouName"></li>
      <li class="zdq" onclick="change(jigouName)">修改</li>
    </ul>
			
   </div>

      <div class="bian"></div>
      
      <div width="400" border="0" class="gerenxinxi">

    <ul>
      <li class="ydq">账户余额</li>
      <li class="wdq">${bsUserInfoDetail.balance }</li>
    </ul>
    <ul>
      <li class="ydq">上次充值金额</li>
      <li class="wdq">${bsUserPayInfo.payMoney}</li>
    </ul>
    <ul>
      <li class="ydq">上次充值时间</li>
      <li class="wdq"><spring:eval expression="bsUserPayInfo.payTime"></spring:eval></li>
    </ul>
    <ul>
      <li class="ydq">年度充值金额</li>
      <li class="wdq">${payMoneys}</li>
    </ul>
    <ul>
      <li class="ydq">年度消耗金额</li>
      <li class="wdq">${useMoneys}</li>
    </ul>

   </div>
     <div class="zxcz">在线充值</div>

   
 


   <h2>开课情况</h2>
  <!--  <id="main" style="width: 800px;height:400px; margin-top:10px;"></div> -->
   <iframe  width="944" height="420"  src="${ctx}/homePage/showClassInfo.do" frameborder="no"></iframe>
   <h2>业绩情况</h2>
   <iframe  width="944" height="650"  src="${ctx}/homePage/showAchivementInfo.do" frameborder="no"></iframe>
   
   
   
   
   
   <h2>网校通知</h2>
    <div class="box-txt">
    
        <div class="box-txt-center">
            <span class="White-bt" id="mzl_1" onclick="switchTag('mzl_','mzl_list_','1',3,'White-bt','blue-bt');">全部</span>
            <span class="blue-bt" id="mzl_2" onclick="switchTag('mzl_','mzl_list_','2',3,'White-bt','blue-bt');">已读</span>
            <span class="blue-bt" id="mzl_3" onclick="switchTag('mzl_','mzl_list_','3',3,'White-bt','blue-bt');">未读</span>
        </div>
        
        <div style="display: block;" class="box-txt-bottom" id="mzl_list_1">
            <div class="boxlf">
                <div class="boxword">
                <table width="880" border="0" class="wxtz" cellPadding="0" cellSpacing="0">
				  <tbody id="thisKnowledge1">
				   
				  </tbody>
				</table>
                </div>
            </div>
        </div>
        <div id="paging1">
		  	<div class="clear"></div>
		   	 <ul class="paging_wrap" id="pagingWrap1">
		    </ul>
	    </div>
        
        
        <div style="display: none;" class="box-txt-bottom" id="mzl_list_2">
            <div class="boxlf">
                <div class="boxword">
                <table width="880" border="0" class="wxtz" cellPadding="0" cellSpacing="0">
				  <tbody id="thisKnowledge2">
				  </tbody>
				</table>
                
                </div>
            </div>
        </div>
        <div id="paging2">
		  	<div class="clear"></div>
		   	 <ul class="paging_wrap" id="pagingWrap2">
		    </ul>
	    </div>
        <div style="display: none;" class="box-txt-bottom" id="mzl_list_3">
            <div class="boxlf">
                <div class="boxword">
                <table width="880" border="0" class="wxtz" cellPadding="0" cellSpacing="0">
				  <tbody id="thisKnowledge3">
				  </tbody>
				</table>
                </div>
            </div>
        </div>
        <div id="paging3">
		  	<div class="clear"></div>
		   	 <ul class="paging_wrap" id="pagingWrap3">
		    </ul>
	    </div>
        
          
</div>
   
    

<h2>意见反馈</h2>
   <table width="880" border="0" class="yjfk" cellPadding="0" cellSpacing="0" >
  <tbody id="callbackInfo">
  	<c:forEach var="bsuserOpinion" items="${bsuserOpinions}" varStatus="bsuserOpinionsIndex">
  		<tr>
	      <td width="47" align="center" valign="middle"><img src="${ctxStatic}/images/homemain/user_icon.png" width="37" height="37" alt=""/></td>
	      <td width="695">${bsuserOpinion.opinionContent}</td>
    	</tr>
  	</c:forEach>
  </tbody>
  <tr>
      <td colspan="2"  valign="middle" class="hf"><input type="text"  placeholder="回复意见反馈" id="feedback"></td>
      <td align="center"><input type="button" value="发送"  class="fs" onclick="submitFeedback()"></td>
  </tr>
</table>

<div class="kb"></div>

    

</body>

<script>
	function submitFeedback(){
		var feedback = $("#feedback").val();
		$.ajax({type:"post",
    	    url:rootPath+"/homePage/submitFeedback.do",
    	    dataType: "json",
    	    data:{feedback:feedback
    	    },
    	    success:function(data){
    	    	 if(data.iserror==true){
    	    		 alert(data.message);
    	    	 }else if(data.iserror==false){
    	    		 alert(data.message);
    	    		 $("#callbackInfo").append('<tr><td width="47" align="center" valign="middle"><img src="${ctxStatic}/images/homemain/user_icon.png" width="37" height="37" alt=""/></td> <td width="695">'+feedback+'</td></tr>');
    	    		 $("#feedback").val("");
    	    	 }
    	    }
    }); 
	}
	var rootPath = "${ctx}";
	var pSize= 5;
	var queryUrl = "/homePage/fullNotice.do";
	var changQueryUrl="/homePage/updateUserInfo.do";
	var totalCount=${bsNotices};
	$(document).ready(function(){  
		initAllKnowLedge();
	}); 
	var format = function(time, format){
	    var t = new Date(time);
	    var tf = function(i){return (i < 10 ? '0' : '') + i};
	    return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
	        switch(a){
	            case 'yyyy':
	                return tf(t.getFullYear());
	                break;
	            case 'MM':
	                return tf(t.getMonth() + 1);
	                break;
	            case 'mm':
	                return tf(t.getMinutes());
	                break;
	            case 'dd':
	                return tf(t.getDate());
	                break;
	            case 'HH':
	                return tf(t.getHours());
	                break;
	            case 'ss':
	                return tf(t.getSeconds());
	                break;
	        }
	    });
	}
	
	function initAllKnowLedge(){
		 $('#pagingWrap1').jqPaginator({
		  	    totalCounts : totalCount,
		        currentPage : 1,
		        pageSize : pSize,
		  	    //first: '<li class="first"><a href="javascript:;">首页</a></li>',
		  	    prev: '<li class="prev paging_one"><a href="javascript:;">前一页</a></li>',
		        next: '<li class="next paging_one"><a href="javascript:;">后一页</a></li>',
		        page: '<li class="page paging_one"><a href="javascript:;">{{page}}</a></li>',
		        //last: '<li class="page"><a href="javascript:;">尾页</a></li>',
		        disableClass:'disabled',
		 	    onPageChange: function (num, type) {
			       	     $.ajax({
			                 url : rootPath+queryUrl,
			                 dataType : 'json',
			                 data : {
			                     pageIndex : num,
			                     pageSize : pSize,
			                 },
			                 cache : false,
			                 success : function(data) {
			                	 var results = data.data;
			                	 //$("#thisKnowledge li:not(:first)").remove();
			                	 $('#thisKnowledge1').empty();
			                	 for (var i = 0; i < results.length; i++) {
			                		 var rowContent ='<tr><td width="768">'+results[i].annunciate+'</td>';
			                		 rowContent+='<td width="132" class="wxtz_time">'+format(new Date(results[i].createTime).getTime(), 'yyyy-MM-dd')+'</td></tr>';
		                			 $('#thisKnowledge1').prepend(rowContent);
			                	 }
			                 },
			                 error : function(html) {
			                     return;
			                 }
			       	   	 });
		 	    }
		  	});
	}
	function change(name){
		var realName=$("#realName").val();
		var userName=$("#userName").val();
		var password=$("#password").val();
		var telephone=$("#telephone").val();
		var jigouName=$("#jigouName").val();
		if($(name).attr("readonly")=="readonly"){
			$(name).removeAttr("readonly");
		}else{
			 $.ajax({
                 url : rootPath+changQueryUrl,
                 dataType : 'json',
                 data : {
                	 realName: realName,
                	 userName : userName,
                	 password : password,
                	 telephone : telephone,
                	 jigouName : jigouName
                 },
                 cache : false,
                 success : function(data) {
                	 if(data.iserror==true){
	    	    		 alert(data.message);
	    	    	 }else if(data.iserror==false){ 
	    	    		 alert(data.message);
	    	    	 }
                 },
                 error : function(html) {
                     return;
                 }
       	   	 });
		}
	}
  /*   $(".tcdPageCode").createPage({
        pageCount:11,
        current:1,
        backFn:function(p){
            console.log(p);
        }
    }); */
</script>
</html>