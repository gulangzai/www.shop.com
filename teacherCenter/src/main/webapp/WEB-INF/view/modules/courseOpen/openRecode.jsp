<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/commons/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/homemain/side_right.css">
<link rel="stylesheet" href="${ctxStatic}/css/homemain/popup.css">
<script src="${ctxStatic}/js/jquery.js"/></script>
<script src="${ctxStatic}/js/jquery.page.js"/></script> 
<script src="${ctxStatic}/js/popup.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title> 
</head>
<body>
 <div class="side_main">
  <ul class="side_nav">
        <li class="side_list"><a href="${ctx}/courseOpen/courseOpenMain.do" target="right">课程开通</a></li>
        <li class="side_list currents"><a href="${ctx}/openRecode/openRecode.do" target="right">开通记录</a></li>
  </ul>
 
    <div class="side_wrap" style="display:block;">
       
      
        <div class=""><!-- side_search -->
          <form action="" method="post">
          <select id="keyword">
               <option value="mobile">手机号</option>
               <option value="studentId">用户名</option> 
               <option value="studentName">学员姓名</option> 
          </select>
         <!--    <span class="search_icon"></span>   -->
            <input type="text" name="search" value="" placeholder="学员姓名/用户名/手机号" style="width:200px;height:25px;"/>
            <input type="button" value="查询" id="search"/>
          </form>
        </div>
         
       
        
         <div class="all">
	        <table class="table table_style" border="1"></table> 
	     </div>  
	     
<!--         <table class="table_style" border="1">
            <tr> 
             <th class="table_th">序号</th>
             <th class="table_th">姓名</th>
             <th class="table_th">手机号</th>
             <th class="table_th">开通项目</th>
             <th class="table_th">开通时间</th> 
            </tr>
            <tr>
             <td>1</td>
             <td>张远超</td>
             <td>1305666234</td>
             <td>2016年一级建造师法律法规邱院长保过班,2016年一级建造师法律法规邱院长保过班</td>
             <td>2016年01月14日10:38</td>
            </tr>
            <tr>
             <td>1</td>
             <td>张远超</td>
             <td>1305666234</td>
             <td>2016年一级建造师法律法规邱院长保过班,2016年一级建造师法律法规邱院长保过班</td>
             <td>2016年01月14日10:38</td>
            </tr>
            <tr>
             <td>1</td>
             <td>张远超</td>
             <td>1305666234</td>
             <td>2016年一级建造师法律法规邱院长保过班,2016年一级建造师法律法规邱院长保过班</td>
             <td>2016年01月14日10:38</td>
            </tr>
            <tr>
             <td>1</td>
             <td>张远超</td>
             <td>1305666234</td>
             <td>2016年一级建造师法律法规邱院长保过班,2016年一级建造师法律法规邱院长保过班</td>
             <td>2016年01月14日10:38</td>
            </tr>
            <tr>
              <td>1</td>
             <td>张远超</td>
             <td>1305666234</td>
             <td>2016年一级建造师法律法规邱院长保过班,2016年一级建造师法律法规邱院长保过班</td>
             <td>2016年01月14日10:38</td>
            </tr>
          </table> 
          <div class="tcdPageCode"> 
          </div> -->
      </div>
      </div>
</body>
<script type="text/javascript" src="${ctxStatic}/js/courseOpen/openRecode.js"></script>
<script type="text/javascript">
var ctx = '${ctx}';  
var table = $(".table").table({
 	   columns:[{field:'id',title:'序号'},
 	            {field:'studentName',title:'姓名'},
 	            {field:'mobile',title:'手机号'},
 	            {field:'openProject',title:'开通项目'},
 	            {field:'openTime',title:'开通时间'}]
}); 
</script>
</html>