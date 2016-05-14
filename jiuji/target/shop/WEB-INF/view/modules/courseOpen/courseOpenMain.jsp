<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/commons/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>建工网校学员管理系统</title>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/homemain/side_right.css">
<link rel="stylesheet" href="${ctxStatic}/css/homemain/popup.css">
<script src="${ctxStatic}/js/jquery.js"/></script>
<script src="${ctxStatic}/js/jquery.page.js"/></script> 
<script src="${ctxStatic}/js/popup.js"></script>
<style type="text/css"> 
ul.pages {display:block;border:1px solid white;text-transform:uppercase;font-size:12px;margin:2px 0 50px;padding:0;}  
ul.pages li {list-style:none;float:left;border:1px solid #b36d41;text-decoration:none;margin:0 5px 0 0;padding:5px;}  
ul.pages li:hover {border:1px solid #afdfe4;background-color:#afdfe4;}  
ul.pages li.pgEmpty {border:1px solid #f0f0f0;color:#d9d6c3;background-color:#f0f0f0;}  
ul.pages li.thpoint {border:none;cursor:auto; color:#000;background-color:#fff;}  
ul.pages li.pgCurrent {padding: 4px 4px;border: 3px double #fff; + border-color: #afdfe4;color:#FFF;font-weight:700;background-color:#afdfe4;}  
 </style>
</head>

<body>

<!-- 开通成功 -->
      <div class="prompt_wrap hide open_success" style="display:none;">
            <h3 class="title_top">
            <span class="tit" style="font-size: 1.5em;">恭喜！操作成功！</span> 
            </h3>  
            <div class="pop_content y_scroll"> 
              <h3 class="headline">具体内容如下：</h3>
               <ul class="conter_bag" id="conter_bag_success_result"> 
                 <li class="title_list" style=" color:#F8FE60;">学员账号  xxx</li>
                 <li class="title_list" style=" color:#F8FE60;">学年2015•一级建造师•神押题班</li>
                 <li class="title_list">建设工程项目管理</li>
                 <li class="title_list">水利水电工程管理实务</li>
                 <li class="title_list">水利水电工程管理实务</li> 
               </ul>
               <div class="open_class" id="cc">完成</div>
            </div>
          </div> 
    <!-- 开通失败 -->    
      <div class="prompt_wrap hide open_fail" style=" display:none;">
            <h3 class="title_top">
            <span class="tit" style="font-size: 1.5em;">抱歉，操作失败</span> 
            </h3> 
            <div class="pop_content">
              <form action="" method="post" id="register"></form>
               <ul class="conter_bag"  id="conter_bag_fail_result">
                <h3 class="headline">以下课程已开通</h3>
                 <li class="title_list">水利水电工程管理实务</li>
                 <li class="title_list">水利水电工程管理实务水利水电工程管理实务</li>
                 <li class="title_list">水利水电工程管理实务</li>
               </ul>
               
               <div class="open_class" id="cc2">完成</div>
            </div>
          </div>   
          
    <div class="side_main">
      <ul class="side_nav">
        <li class="side_list currents"><a href="${ctx}/courseOpen/courseOpenMain.do" target="right">课程开通</a></li>
        <li class="side_list"><a href="${ctx}/openRecode/openRecode.do" target="right">开通记录</a></li>
      </ul>
      <div>
      <div class="side_wrap">
        <form action="" method="post" id="register"></form>
        <ul class="wrap_nav">
          <li class="nav_list">
            <span class="nav_title">学年</span>
            <select name="year" form="register"   id="shopTime" class="change">
                    <option value="2015">2015</option>
                    <option value="2016">2016</option>
                    <option value="2017">2017</option>
                    <option value="2018">2018</option>
                </select>
          </li>
          <li class="nav_list">
            <span class="nav_title">类型</span>
            <select name="type" form="register"   id="shopType" class="change">
                    <option value="请选择">请选择</option>
                    <option value="0">电子书</option>
                    <option value="1">实体书</option>
                    <option value="2">课件</option>
                    <option value="3">直播视频</option>
                    <option value="4">在线模考商品</option>
                    <option value="9">免费教程</option>
                </select>
          </li>
          <li class="nav_list">
            <span class="nav_title">专业</span>
            <select name="courseMajor" id="courseMajor" form="register"   class="change">
            <option value="请选择">请选择</option>
            <c:forEach var="courseMajor" items="${courseMajors}">
               <option value="${courseMajor.majorId}">${courseMajor.majorName}</option>
            </c:forEach> 
                </select>
          </li>
          
        <!--   <li class="nav_list">
            <span class="nav_title">科目</span>
            <select name="subject" id="subject">  
             <option value="请选择">请选择</option>
            </select>
          </li> -->
          
          <li class="nav_list">
            <span class="nav_title">班级</span>
            <select name="classes" id="classes" class="change">
              <option value="请选择">请选择</option>  
            </select>
          </li>
          
          <li  class="nav_list"><Button id="search">查询</Button></li>
        </ul>
       <div class="all">
	        <table class="table table_style" border="1">
	        </table> 
	     </div>  
	 
     <!--  <table class="table_style" border="1">
            <tr>
             <th class="table_th"><input type="checkbox" name="" form="register" /></th>
             <th class="table_th">序号</th>
             <th class="table_th">名称</th>
             <th class="table_th">属性</th>
             <th class="table_th">班级</th>
             <th class="table_th">科目</th>
             <th class="table_th">专业</th>
            </tr>
            <tr>
             <td><input type="checkbox" name="" form="register" /></td>
             <td>1</td>
             <td>2016年一级建造师法律法规</td>
             <td>直播网课</td>
             <td>直通取证班</td>
             <td>法律法规</td>
             <td>一级建造师</td>
            </tr> 
          </table>  
         -->  
          <div class="side_open" id="shows">开通</div> 
          <!-- <div class="tcdPageCode"></div> -->
          
          
      </div>
      
<!--       <div class="side_wrap" style="display:none;">
        <form action="" method="post" id="register"></form>
        <div class="side_search">
           <input type="search" name="search" placeholder="学员姓名/用户名/手机号"/>
            <span class="search_icon"></span>
        </div>
        <table class="table_style" border="1">
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
        
          <div class="tcdPageCode"></div>
      </div> -->
      
      
      </div>
    </div>
  <div style="width:2000px">
     <div class="prompt_wrap hide">
      <h3 class="title_top">
      <span class="tit">已选择的商品如下:</span>
      <span class="close_icon" id="aa"><img src="${ctxStatic}/images/homemain/pop_icon.png"/></span>
      </h3>
      
      <div class="pop_content">
        <form action="" method="post" id="register"></form>
         <ul class="conter_bag" id="conter_bag">
           <li class="title_list"><input type="checkbox" name="" form="register"/>一级工程经济</li>
           <li class="title_list"><input type="checkbox" name="" form="register"/>建设工程法规及相关知识</li>
           <li class="title_list"><input type="checkbox" name="" form="register"/>建设工程项目管理</li>
           <li class="title_list"><input type="checkbox" name="" form="register"/>水利水电工程管理实务</li>
         </ul> 
         <div class="phone_no">请输入学员手机号<input type="tel" name="tel" placeholder="手机号"/></div>
         <div class="open_class open_class_action">开通</div>
      </div>
    </div>
    
           <!--<div class="prompt_wrap hide" style="display:none;">
            <h3 class="title_top">
            <span class="tit" style="font-size: 1.5em;">恭喜！操作成功！</span>
            
            </h3>
            
            <div class="pop_content y_scroll">
              <form action="" method="post" id="register"></form>
               <ul class="conter_bag">
                <h3 class="headline">具体内容如下：</h3>
                 <li class="title_list" style=" color:#F8FE60;">学员账号  xxx</li>
                 <li class="title_list" style=" color:#F8FE60;">学年2015•一级建造师•神押题班</li>
                 <li class="title_list">建设工程项目管理</li>
                 <li class="title_list">水利水电工程管理实务</li>
                 <li class="title_list">水利水电工程管理实务</li>
                 <li class="title_list">水利水电工程管理实务</li>
                  
               </ul>
               <div class="open_class" id="cc">完成</div>
            </div>
          </div>-->
          
           <!--<div class="prompt_wrap hide" style="display:none;">
            <h3 class="title_top">
            <span class="tit" style="font-size: 1.5em;">恭喜！操作成功！</span>
            
            </h3>
            
            <div class="pop_content y_scroll">
              <form action="" method="post" id="register"></form>
               <ul class="conter_bag">
                <h3 class="headline">具体内容如下：</h3>
                 <li class="title_list" style=" color:#F8FE60;">学员账号  xxx</li>
                 <li class="title_list" style=" color:#F8FE60;">学年2015•一级建造师•神押题班</li>
                 <li class="title_list" style="border-bottom:1px solid #fff;">建设工程项目管理七步通关班</li>
               </ul>
               <ul class="conter_bag_one">
                <h3 class="headline">以下课程已开通</h3>
                 <li class="title_list">水利水电工程管理实务</li>
                 <li class="title_list">水利水电工程管理实务水利水电工程管理实务</li>
                 <li class="title_list">水利水电工程管理实务</li>
                 <li class="title_list">水利水电工程管理实务</li>
                 <li class="title_list">水利水电工程管理实务水利水电工程管理实务</li>
                 <li class="title_list">水利水电工程管理实务</li>
               </ul>
               <div class="open_class" id="cc1">完成</div>
            </div>
          </div>-->
          
           <!--<div class="prompt_wrap hide" style=" display:none;">
            <h3 class="title_top">
            <span class="tit" style="font-size: 1.5em;">抱歉，操作失败</span>
            
            </h3>
            
            <div class="pop_content">
              <form action="" method="post" id="register"></form>
               <ul class="conter_bag">
                <h3 class="headline">以下课程已开通</h3>
                 <li class="title_list">水利水电工程管理实务</li>
                 <li class="title_list">水利水电工程管理实务水利水电工程管理实务</li>
                 <li class="title_list">水利水电工程管理实务</li>
               </ul>
               
               <div class="open_class" id="cc2">完成</div>
            </div>
          </div>-->
          
          </div>
</body>
<script type="text/javascript">
   var shops=new Array(); 
	//我选中的列表
	var choiceShops = new Array(); 
</script>
<script type="text/javascript" src="${ctxStatic}/js/courseOpen/courseOpenList.js"></script>
 <script>
    var ctx = '${ctx}';
	var table = $(".table").table({
	 	   columns:[{field:'id',title:'序号'},
	 	            {field:'majorName',title:'专业'},
	 	            {field:'subjectName',title:'科目'},
	 	            {field:'classesName',title:'班级'},
	 	            {field:'shopType',title:'类型'},
	 	            {field:'shopName',title:'商品名称'}]
	}); 
	
    $(".tcdPageCode").createPage({
        pageCount:11,
        current:1,
        backFn:function(p){
            console.log(p);
        }
    });
     
</script>

</html>