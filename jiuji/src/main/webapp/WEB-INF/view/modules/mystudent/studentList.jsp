<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/commons/taglib.jsp" %>
<%@ include file="/WEB-INF/view/commons/jsp/main.jspf"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<style type="text/css">
body,table{
font-size:12px;
} 
table{
table-layout:fixed;
empty-cells:show;
border-collapse: collapse; 
margin:0 auto;
}
td{
   /* height:30px;  */
    border-width: 1px;
}
h1,h2,h3{
font-size:12px;
margin:0;
padding:0;
}
.table{
/* border:1px solid #cad9ea; */
color:#666;
}
 .table tr th {
 background-repeat:repeat-x;   
/*  padding:1px 5px;  */
 /* height:30px; */ 
}  

.table tr td{ 
padding:0 1em 0;
text-align:center;
width:350px;
height:30px;
border:1px solid #cad9ea;  
background:#dcddc0 url('${ctxStatic}/images/studentList/cell-grey.jpg');
}
.table tr th{ 
  border:1px solid #666666;  
 /*  background-color: #dedede; */
}

.table tr.alter{
background-color:#f5fafe;
} 

ul.pages {display:block;border:1px solid white;text-transform:uppercase;font-size:12px;margin:2px 0 50px;padding:0;}  
ul.pages li {list-style:none;float:left;border:1px solid #b36d41;text-decoration:none;margin:0 5px 0 0;padding:5px;}  
ul.pages li:hover {border:1px solid #afdfe4;background-color:#afdfe4;}  
ul.pages li.pgEmpty {border:1px solid #f0f0f0;color:#d9d6c3;background-color:#f0f0f0;}  
ul.pages li.thpoint {border:none;cursor:auto; color:#000;background-color:#fff;}  
ul.pages li.pgCurrent {padding: 4px 4px;border: 3px double #fff; + border-color: #afdfe4;color:#FFF;font-weight:700;background-color:#afdfe4;}  

</style>
<title>Insert title here</title>
</head>
<body style="width:1080px;margin:0px;padding:0px;overflow-x:hidden;">
    <div style="margin:0px;padding:0px;">
    
       <div style="margin-top:0px;padding:0px;margin-bottom:0px;width:100px;height:20px;background:url('${ctxStatic}/images/transparent.gif')" class="ax_文本" >
	        <span>学</span><span>员列表</span> 
	    </div>
	     
        <!-- Unnamed (水平线) -->
      <div style="margin-top:0px;padding:0px;display:block;"  class="ax_水平线">
        <img  class="img " src="${ctxStatic}/images/studentList/u11_line.png" alt="u11_line"/> 
      </div>
      
      <div style="width:80px;height:6px;margin-top:-3px;padding:0px;display:block;background:url('${ctxStatic}/images/studentList/u14.png')"> 
      </div>
	 
	   <div style="margin-top:20px;width:800px;">
		     <div id="all" style="float:left;cursor:pointer;width:100px;height:30px;border:1px solid black;border-radius:15px;text-align:center;" class="colorOpe" data="#dedede">
					<%--   <img  class="img " src="${ctxStatic}/images/studentList/u840.png" tabindex="0">  --%>
					<div  class="text" style="transform-origin: 48px 9px 0px;padding-top:5px;">
					       全部 
					 </div>
			  </div>
			  
			  <div id="red" style=" float:left;margin-left:20px;cursor:pointer;width:100px;height:30px;border:1px solid black;border-radius:15px;text-align:center;" class="colorOpe" data="red">
		       <%--   <img class="img " src="${ctxStatic}/images/studentList/u16.png" tabindex="0"> --%>
		        <div  class="text" style="transform-origin: 48px 9px 0px;cursor:pointer;padding-top:5px">
		         <span>红</span> <span>色</span>
		      </div>
		      </div>
		      
		      <div id="yellow" style="float:left;margin-left:20px;cursor:pointer;width:100px;height:30px;border:1px solid black;border-radius:15px;text-align:center;" class="colorOpe" data="yellow">
		         <%--  <img   class="img " src="${ctxStatic}/images/studentList/u16.png" tabindex="0"> --%>
		         <div  class="text" style="transform-origin: 48px 9px 0px;padding-top:5px">
		           <span>黄</span>  <span>色</span>  
		       </div>
		      </div>
		      
		      <div id="orange" style="float:left;margin-left:20px;cursor: pointer;width:100px;height:30px;border:1px solid black;border-radius:15px;text-align:center;" class="colorOpe" data="orange">
				<%-- <img class="img " src="${ctxStatic}/images/studentList/u16.png" tabindex="0"> --%>
				<div   class="text" style="transform-origin: 48px 9px 0px;padding-top:5px">
				   <span>橙</span> <span>色</span>  
				</div>
			  </div> 
			  
			   <div style="float:right;" class="ax_文本">
				<img   class="img " src="${ctxStatic}/images/transparent.gif">
				<div   class="text" style="font-size:12px;">
				<p><span>注：1、最后登录时间超过一星期的显示红色</span></p>
				<p><span>   2、每天学习时长达标率低于40%显示黄色</span></p>
				<p><span>   3、答题正确率低于50%显示橙色</span></p>
				</div>
              </div>
      </div> 
      
	 <div style="clear:both"></div>
     <div style="margin-top:0px;background-Color:red;">
		       <div   style="float:left;" class="ax_文本框_单行_">
		           <input  type="text" name="search" value="" style="color: rgb(153, 153, 153); opacity: 1;">
		       </div>
		       
		       <div  id="search" style="float:left;cursor:pointer" class="ax_形状">
					<img   class="img " src="${ctxStatic}/images/studentList/u877.png"> '
					<div  style="margin-top:-38px;"><p> <span>查找</span> </p></div>
			  </div>
				
			 <div  id="exportExcel" style="float:right;margin-left:150px;cursor:pointer" class="ax_形状">
	         	<img  class="img " src="${ctxStatic}/images/studentList/u16.png">
		        <div  class="text" style="margin-top: -38px; transform-origin: 48px 9px 0px;">
		        <p> <span>导出Excel</span> </p>
		       </div>
		    </div>
      </div>
 <div style="clear:both"></div>
     
     <div class="all">
	     <table class="table"></table> 
	 </div> 
	   
	 <div id="p" class="easyui-progressbar" style="width:400px;display:none"></div>
	 
    </div>  
</body>
<script type="text/javascript" src="${ctxStatic}/js/studentList.js"></script>
<script type="text/javascript">
   var ctx = '${ctx}';
    var table = $(".table").table({
	   columns:[{field:'id',title:'序号'},
	            {field:'name',title:'姓名'},
	            {field:'usename',title:'用户名'},
	            {field:'tele',title:'手机号码'},
	            {field:'loginLastTime',title:'最后登录的时间'},
	            {field:'studyTime',title:'第一轮学习时长'},
	            {field:'studyTime',title:'答题正确率'},
	            {field:'studentAttr',title:'学员属性'},
	            {field:'studentSource',title:'学员来源'},
	            {field:'operator',title:'操作'}],
	    headColor:'#dedede',
	    majorId:'8Z5FqMVTQ4QZtJMQfbtp'
   }); 
    
    //查看详情
    function studentDetail(studentId){
    	//window.location.href=ctx+"/myStudent/studentDetail.do?studentId="+studentId;
    	window.open(ctx+"/myStudent/studentDetail.do?studentId="+studentId+"&majorId=8Z5FqMVTQ4QZtJMQfbtp","right");
    }
</script>

</html>