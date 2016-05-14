<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/commons/taglib.jsp" %>
<%@ include file="/WEB-INF/view/commons/jsp/main.jspf"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/homemain/xylb.css"> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学员列表</title> 
</head>
<body>
<!-----------------------优惠券-----------------------------------------------------------------------------> 
 <div class="fudong youhui">
       <input type="hidden" name="studentId" value="">
       <div class="youhuiquan"><img class="close" src="${ctxStatic}/images/homemain/pop_icon.png" width="49" height="47" alt=""/>
             <div class="title">优惠券</div>
             <div class="money">优惠金额<input type="text" class="input" name="favorableBalance">元</div>
             <input type="button" value="确定" class="ok addCoupon">
       </div> 
 </div>  
 
 <!-----------------------第2次导入学员------------------------------------------------------------------>    
     <div class="fudong dltwo">
     <form action="${ctx}/myStudent/batchInputStudent.do" enctype="multipart/form-data" method="post" name="fileForm" id="fileForm">
       <div class="youhuiquan"><img class="close" src="${ctxStatic}/images/homemain/pop_icon.png" width="49" height="47" alt=""/>
             <div class="title">导入学员</div>
             <div class="money">批量导入<input type="file" value="选择文件" name="file" ></div>
             <div class="pldao">还没有标准录入表格，<span><a href="javascript:panduan()">点击下载</a></span></div>
             <input type="button" value="确定" class="ok batchInputStudent"">
       </div> 
      </form>
    </div>
  
  <!-----------------------第一次导入学员--------------- ------------------------------------------------------>   
  <div class="fudong  dlone">
       <div class="youhuiquan">
             <img class="close" src="${ctxStatic}/images/homemain/pop_icon.png" width="49" height="47" alt=""/>
             <div class="title">导入学员</div>
             <div class="money">下载表格<input type="button" class="djxz" value="点击下载"></div>
             <div class="pldao">提示：老师您好，如要使用批量导入功能请先下载数据表格，按表格填写学员信息，方便自动导入数据表格。</div>
       </div> 
  </div> 
  
   <!-----------------------录入学员------------------  ------------------------------------------------------------> 

    <div class="fudong lu">
       <div class="youhuiquan"><img class="close" src="${ctxStatic}/images/homemain/pop_icon.png" width="49" height="47" alt=""/>
             <div class="title">录入学员</div>
         <div class="xinxi"><span>学员姓名</span><input type="text" name="studentName" class="input" id="inputStudentName" ></div>
             <div class="xinxi"><span>手机号码</span><input type="text" name="mobile" class="input" id="inputMobile" ></div>
             <div class="xinxi"><span>报名意向</span>
                 <label>
                   <input type="radio" name="applyIntention" value="一级建造师" id="RadioGroup3_0">
                   一级建造师</label>
                 <label>
                   <input type="radio" name="applyIntention" value="二级建造师" id="RadioGroup3_1">
                   二级建造师</label>
             </div>
             <div class="xinxi"><span>报名班级</span>
                 <label>
                   <input type="radio" name="applyClasses" value="七步通关班" id="RadioGroup4_0">
                   七步通关班</label>
                 <label>
                   <input type="radio" name="applyClasses" value="邱院长保过班" id="RadioGroup4_1">
                   邱院长保过班</label>
             </div>
             <div class="xinxi"><span>意向备注</span><input type="text" name="intentionRamark" class="input" ></div>
             <div class="xinxi"><span>意向程度</span>
                 <label>
                   <input type="radio" name="intention" value="3" id="RadioGroup5_0">
                   高</label>
                 <label>
                   <input type="radio" name="intention" value="2" id="RadioGroup5_1">
                   中</label>
                 <label>
                   <input type="radio" name="intention" value="1" id="RadioGroup5_2">
                   低</label>
             </div>
             <input type="button" value="确定" class="inputStudents ok2">
       </div> 
     </div>  
     
     
    <div class="top">
           <ul class="nav">
                   <li><a href="${ctx}/homePage/showHome.do" target="right">首页</a></li>
                   <li class="xz"><a href="${ctx}/myStudent/myStudentList.do" target="right">学员列表</a></li>
           </ul>    
           <div  class="suosuo">
              <!--  <select id="keyword">
                 <option value="mobile">手机号</option>
                 <option value="studentId">用户名</option> 
                 <option value="studentName">学员姓名</option> 
              </select>  -->
               <input type="text" name="search" value="" placeholder="用户名/手机号" style="width:200px;height:25px;"/>
              <!--  <input type="search" placeholder="用户姓名/用户名/手机号" class="suosuo_left"> -->
              <input type="submit" class="suosuo_right" value="" id="search">
               <!--  <input type="button" value="查询" /> -->
           </div>
   </div>
   
<div class="side_wrap">
        <form action="" method="post" id="register"></form> 
  
   <h3>学习情况</h3>   
      <p class="yanse"> 
          <label title="全部学员">
            <input type="radio" name="RadioGroup1" value="#dedede" id="RadioGroup1_0" checked="checked" >
            全部</label>

          <label title="连续7天未登录学员">
            <input type="radio" name="RadioGroup1" value="red" id="RadioGroup1_1">
            红色</label>

          <label title="每天学习时长达标率＜30%学员">
            <input type="radio" name="RadioGroup1" value="yellow" id="RadioGroup1_2">
            黄色</label>

          <label title="100题＜总答题量 且 答题正确率＜50%学员">
            <input type="radio" name="RadioGroup1" value="orange" id="RadioGroup1_3">
            橙色</label> 
         </p>
  
  <h3>学习意向</h3>   
  <div class="yanse"> 
   <label title="全部学员">
            <input type="radio" name="RadioGroup2" value="0" id="RadioGroup2_0" checked="checked"  >
            全部	</label> 
          <label title="全部学员">
            <input type="radio" name="RadioGroup2" value="3" id="RadioGroup2_0">
            高</label> 
          <label title="连续7天未登录学员">
            <input type="radio" name="RadioGroup2" value="2" id="RadioGroup2_1">
            中</label> 
          <label title="每天学习时长达标率＜30%学员">
            <input type="radio" name="RadioGroup2" value="1" id="RadioGroup2_2">
            低</label>  
        <div class="anniu">
           <input id="lulu" type="button" value="录入学员">
           <input id="daolu" type="button" value="导入学员">
           <input type="button" value="导出excel" id="exportExcel">
        </div>
  </div>
        
       <div class="all">
	        <table class="table table_style" border="1"></table> 
	   </div> 
	 
<!--         <table class="table_style" border="1">
            <tr>
             <th width="2%" class="table_th"><input type="checkbox" name="" /></th>
             <th width="4%" class="table_th">序号</th>
             <th width="4%" class="table_th">姓名</th>
             <th width="6%" class="table_th">用户名</th>
             <th width="9%" class="table_th">手机号</th>
             <th width="10%" class="table_th">最后登录时间</th>
             <th width="9%" class="table_th"><p>第一轮</p>
              <p>学习时长</p></th>
             <th width="6%" class="table_th"><p>答题</p>
              <p>正确率</p></th>
             <th width="8%" class="table_th">课程属性</th>
             <th width="8%" class="table_th">学员来源</th>
             <th width="5%" class="table_th"><p>意向</p>
              <p>程度</p></th>
             <th width="10%" class="table_th">意向备注</th>
             <th width="19%" class="table_th">操作</th>
            </tr>
            <tr>
             <td><input type="checkbox" name="" /></td>
             <td>1</td>
             <td>张云</td>
             <td>张云</td>
             <td>18902847586</td>
             <td><p>2015年12月11号</p>
              <p>12:12:12</p></td>
             <td>12小时17分</td>
             <td>35%</td>
             <td>纯网课</td>
             <td>网盟</td>
             <td>高</td>
             <td>下个星期报名</td>
             <td>
                    <ul>
                         <li>优惠券</li>
                         <li>查看详情</li>
                         <li>删除</li>
                         <li>重置密码</li>
                    </ul>
             </td>
            </tr>
            <tr>
             <td><input type="checkbox" name="" /></td>
             <td>2</td>
             <td>张云</td>
             <td>张云</td>
             <td>18902847586</td>
             <td><p>2015年12月11号</p>
              <p>12:12:12</p></td>
             <td>12小时17分</td>
             <td>35%</td>
             <td>纯网课</td>
             <td>网盟</td>
             <td>高</td>
             <td>下个星期报名</td>
             <td>
                    <ul>
                         <li>优惠券</li>
                         <li>查看详情</li>
                         <li>删除</li>
                         <li>重置密码</li>
                    </ul></td>
            </tr> 
        </table>  -->
      </div> 
        <!--  <div class="tcdPageCode"></div>   -->
</body>

<script type="text/javascript" src="${ctxStatic}/js/myStudent/myStudentList.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/myStudent/w_popup.js"></script>
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
	            {field:'degree',title:'意向程度'},
	            {field:'remark',title:'意向备注'},
	            {field:'operator',title:'操作'}],
	    headColor:'#dedede', 
   });  
    //查看详情
    function studentDetail(studentId){
    	//alert('studentId'+studentId);
    	//window.location.href=ctx+"/myStudent/studentDetail.do?studentId="+studentId;
    	window.open(ctx+"/myStudent/myStudentDetail.do?studentId="+studentId,"right");
    }
    
    //判断模板文件自否存在
    function panduan(){ 
    	$.ajax({
    		type:'post',
    		url:ctx+'/myStudent/templateIsExist.do',
    		async:false,
    		data:{  
    		},
    		success:function(sR){
    			 if(sR.message=='EXIST'){
    				 window.location.href=ctx+"/myStudent/downloadTemplate.do"; 
    			 } else if(sR.message=='NO'){
    			    alert('服务器上不存在模板文件'); 
    			 }
    		}
    	}); 
    } 
    $(".batchInputStudent").click(function(){
    	batchInputStudent();
    });
    //上传批量文件
    function batchInputStudent(){
    	 var formData = new FormData($("#fileForm")[0]);  
    	$.ajax({
    		type:'post',
    		url:ctx+'/myStudent/batchInputStudent.do',
    		async:false,
    		data:formData,
    		cache:false,
    		contentType:false,
    		processData:false,
    		success:function(sR){
    			 if(sR.message=='SUCCESS'){ 
    				 parseExcelInputStudent(sR.data.file); 
    				 return; 
    			 } else if(sR.message=='FAIL'){
    			    alert('上传失败'); 
    			    return;
    			 }
    		},
    		error:function(returndata){
    			alert(returndata);
    			return;
    		}
    	}); 
    }
    
    /**解析excel */
    function parseExcelInputStudent(fileName){
    	$.ajax({
    		type:'post',
    		url:ctx+"/myStudent/parseExcelInputStudent.do",
    		async:false,
    		data:{
    			fileName:fileName
    		},
    		success:function(sR){
	   			 if(sR.message=='SUCCESS'){
	   				 alert("解析成功"); 
	   				 return; 
	   			 } else if(sR.message=='FAIL'){
	   			    alert('解析失败'); 
	   			    return;
	   			 }
   		    }
    	}); 
    } 
    
    //------------------------------------------------------------------------ 
 	function studentYouhuiQuan(studentId){
 	
 		var oBtn3 = $('.yhj');
 		var popWindow3 = $('.youhui');
 		//浏览器可视区域的宽度
 		var browserWidth = $(window).width(); 
 		//浏览器可视区域的高度
 		var browserHeight = $(window).height(); 
 		//浏览器纵向滚动条距离上边界的值
 		var browserScrollTop = $(window).scrollTop(); 
 		//浏览器横向滚动条距离左边界的值
 		var browserScrollLeft = $(window).scrollLeft();
 		//弹出窗口的宽度
 		var popWindowWidth = popWindow3.outerWidth(true);
 		//弹出窗口的高度
 		var popWindowHeight = popWindow3.outerHeight(true);
 		//left的值＝浏览器可视区域的宽度／2－弹出窗口的宽度／2+浏览器横向滚动条距离左边界的值
 		var positionLeft = browserWidth/2 - popWindowWidth/2+browserScrollLeft; 
 		//top的值＝浏览器可视区域的高度／2－弹出窗口的高度／2+浏览器纵向滚动条距离上边界的值
 		var positionTop = browserHeight/2 - popWindowHeight/2+browserScrollTop;  
		popWindow3.children("input").val(studentId);
		popWindow3.show().animate({
					'left':positionLeft+'px',
					'top':positionTop+'px'
		},500); 
		var oMask = '<div class="mask"></div>';
		//遮照层的宽度
		var maskWidth = $(document).width(); 
		//遮照层的高度
		var maskHeight = $(document).height();
		$('body').append(oMask);
		$('.mask').width(maskWidth).height(maskHeight);  
	} 
	
    /*
     *添加优惠券 
     */
     $(".addCoupon").click(function(){
    	 var studentId = $(".youhui").children('input[name="studentId"]').val(); 
    	 var favorableBalance = $("input[name='favorableBalance']").val();
    	 $.ajax({
     		type:'post',
     		url:ctx+"/myStudent/addCoupon.do",
     		async:false,
     		data:{
     			studentId:studentId,
     			favorableBalance:favorableBalance
     		},
     		success:function(sR){
 	   			 if(!sR.iserror){
 	   				 alert(sR.message);  
 	   			 } else {
 	   			    alert(sR.message);  
 	   			 }
 	   			 $('.youhui').children("input").val(''); 
 	   			 $("input[name='favorableBalance']").val('');
 	   			 return;
    		   }
     	}); 
     });
    
    //------------------------------------------------------------------
</script> 
</html>