<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/commons/taglib.jsp" %>
<%@ include file="/WEB-INF/view/commons/jsp/main.jspf"%> 
<!doctype html>
<html>
<head>
<meta charset="utf-8"> 
<title>学尔森建工网校学员管理系统</title>
<link href="${ctxStatic}/css/myStudent/xylb_xyxq.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctxStatic}/js/myStudent/m.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/myStudent/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/myStudent/jquery.page.js"></script>
<script src="${ctxStatic}/js/myStudent/echarts.min.js"></script>
</head>

<body>
   <div class="top">
           <ul class="nav">
                   <li><a href="${ctx}/homePage/showHome.do" target="right">首页</a></li>
                   <li class="xz">学员详情</li>
           </ul>      
   </div>

   <h2 class="one">报名意向</h2>
   


   <div class="baoming">
                 <label>
                   <input type="radio" name="RadioGroup5" value="3" id="RadioGroup5_0" <c:if test="${studentDetailInfo.intention==3}">checked</c:if>>
                   高</label>
                 <label>
                   <input type="radio" name="RadioGroup5" value="2" id="RadioGroup5_1" <c:if test="${studentDetailInfo.intention==2}">checked</c:if>>
                   中</label>
                 <label>
                   <input type="radio" name="RadioGroup5" value="1" id="RadioGroup5_2" <c:if test="${studentDetailInfo.intention==1}">checked</c:if>>
                   低</label>
    <ul>
      <li >报名意向：</li>
      <li>${studentDetailInfo.applyIntention}</li>
    </ul>

   </div>



   <h2>学习情况</h2> 
   <c:choose>
       <c:when test="${empty studentPlanLearnResults}">
           <div class="xxqk">该学员没有任何学习计划</div> 
       </c:when>
       <c:otherwise>
       <c:forEach var="studentPlanLearnResult" items="${studentPlanLearnResults}" varStatus="status"> 
       <c:set var="studentPlanLearn" value="${studentPlanLearnResult.studentPlanLearn}"></c:set>
       <c:set var="studentPlan" value="${studentPlanLearnResult.studentPlan}"></c:set>
       <c:if test="${studentPlanLearn!=null}">
       <h2>学习计划${status.count}</h2> 
       <br>
   <iframe  width="370" height="330"  src="${ctx}/myStudent/myStudentStatusPie.do?pie=${studentPlanLearn.pie}" style="border:none; float:left"></iframe>
   <div class="xxqk">
   <ul>
      <li class="zuti">学习时长</li>
      <li>总学习时长：${fn:substring(studentPlanLearn.oneLearnTime,0,4)+fn:substring(studentPlanLearn.twoLearnTime,0,4)}小时</li>
      <li>一轮学习时长：${studentPlanLearn.oneLearnTime}小时</li>
      <li>二轮及以上学习时长：${studentPlanLearn.twoLearnTime}小时</li>
      <li>复习率：${studentPlanLearn.reviewRate}%</li>
   </ul>
   <div class="bian"></div>
   <ul>
      <li class="zuti">做题练习</li>
      <li>总答题量：${studentPlanLearn.alwaysATopicQuantity}道</li>
      <li>
      <c:choose>
	 	<c:when test="${studentPlanLearn.correct>0&&studentPlanLearn.alwaysATopicQuantity>0}">
	 		正确率：${fn:substring(studentPlanLearn.correct/studentPlanLearn.alwaysATopicQuantity*100,0,4)}%
	 	</c:when>
	 	<c:otherwise>
	 		正确率：0%
	 	</c:otherwise>
 	</c:choose>
 	  </li>
      <li>知识点覆盖：${fn:substring(studentPlanLearn.coverage,0,4)}%</li>
      <li>二次以上练习率：${studentPlanLearn.twoPracticeRate}%</li>
   </ul> 
   </div> 
       </c:if>
       
       <c:if test="${studentPlan!=null}">
	   <br>
	   <h2>学员学习计划完成情况${status.count}</h2>
	   <div class="xxxx">
	   <ul>
	      <li>每天学习时长：${studentPlan.dayTime}小时</li>
	      <li>计划学习天数：${studentPlan.totalDay }天</li>
	      <li>预计每天学习课时数：<fmt:formatNumber type="number" value="${studentPlan.videoTotal/studentPlan.totalVideoTime}" maxFractionDigits="0"/>个</li>
	      <li>预计完成学习时间：${studentPlan.endDate}</li>
	      <li>开始学习时间：${studentPlan.startDate}</li>
	   </ul>
	   <div class="bian"></div>
	   <ul>
	      <li>达标率：${fn:substring(learningTimeRate,0,4)} % </li>
	      <li>剩余学习天数：${residueLearnDay}天 </li> 
	   </ul>
	   </c:if>
	   	
	   <div class="bian"></div>
	   <ul>
	      <li class="zuti">学员计划修改日志</li>
	      <!-- <li>2016-01-05建立计划</li> -->
	      <c:forEach var="studentPlanLog" items="${studentPlanLearnResult.studentPlanLogs}">
		     <li><p><spring:eval expression="studentPlanLog.createTime"></spring:eval>${studentPlanLog.actionName}</p></li>
	      </c:forEach> 
	   </ul> 
	   </div>  
    </c:forEach>
    
    
       </c:otherwise>
   </c:choose>

    

   <h2>学员考试成绩变化</h2>
   
      <div class="xuan"> 
      <c:choose>
         <c:when test="${courseMajorSubjects.size()==0}">
            <li>该学生没有学习成绩</li> 
         </c:when>
         <c:otherwise>
             <c:set  var="index" value="0"></c:set>
             <c:forEach var="courseMajorSubject" items="${courseMajorSubjects}"  varStatus="status"> 
                <c:if test="${status.count%3==1}">
                  <c:set  var="index" value="${index+1}"></c:set> 
                  <ul class="studentGrade">
                </c:if>
                   <li><input type="hidden" name="majorId" value="${courseMajorSubject.majorId}">${step}
		           <input type="hidden" name="subjectId" value="${courseMajorSubject.subjectId}">
		           <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>${courseMajorSubject.subjectName}</li> 
		        <c:if test="${status.count==index*3}"></ul></c:if> 
             </c:forEach>
              <div style="clear:both;margin-top:10px;"></div> 
      </c:otherwise>
      </c:choose>  
     </div>
    <c:if test="${courseMajorSubjects.size()!=0}">
       <iframe  width="920" height="460"  src="${ctx}/myStudent/myStudentScoreLine.do?studentId=${studentId}" frameborder="no" style="margin-left:20px;"></iframe>  
    </c:if>
   <h2>回访记录</h2>
    
    <div class="box-txt">
       
<table border="0" cellspacing="0" cellpadding="0">  
  <tbody class="content">
  <c:forEach var="bsCallbackInfo" items="${bsCallbackInfos}">
   <tr>
      <td width="700">${bsCallbackInfo.callbackContent}</td>
      <td width="200" class="wxtz_time">${bsCallbackInfo.createTimeLabel}</td>
    </tr>
  </c:forEach> 
  </tbody>
</table> 
    <!-----分页----->
    <div class="pagingWrap"></div>
          
     <ul class="huifang">
      <li><input type="text" name="callbackContent" placeholder="输入新的回访方式、回访结果"  class="hfk"></li>
      <li><input type="submit" value="提交"  class="hfanniu"></li>
    </ul>  
     
</div>
    

<div class="kb"></div>

    

</body>
<script type="text/javascript" src="${ctxStatic}/js/myStudent/jqPaginator.js"></script>
<script>
   /*  $(".tcdPageCode").createPage({
        pageCount:11,
        current:1,
        backFn:function(p){
            console.log(p);
        }
    }); */
    
    var totalCount=${bsNotices}; 
    var pSize = 5;
    var studentId = '${studentId}';
    
    
    function openWrap(){ 
        $('.pagingWrap').jqPaginator({
      	    totalCounts : totalCount,
            currentPage : 1,
            pageSize : pSize, 
            totalPages:1,
      	    //first: '<li class="first"><a href="javascript:;">首页</a></li>',
      	    prev: '<li class="prev paging_one"><a href="javascript:;">前一页</a></li>',
            next: '<li class="next paging_one"><a href="javascript:;">后一页</a></li>',
            page: '<li class="page paging_one"><a href="javascript:;">{{page}}</a></li>',
            //last: '<li class="page"><a href="javascript:;">尾页</a></li>',
            disableClass:'disabled',
     	    onPageChange: function (num, type) {
    	       	     $.ajax({
    	                 url : ctx+'/myStudent/queryCallbackInfo.do',
    	                 dataType : 'json',
    	                 data : {
    	                     pageIndex : num,
    	                     pageSize : pSize,
    	                     totalCounts:totalCount,
    	                     studentId:studentId
    	                 },
    	                 cache : false,
    	                 success : function(sR) {
    	                	 var results = sR.data;
    	                	 //$("#thisKnowledge li:not(:first)").remove();
    	                	 var content = $(".content");
    	                	 content.empty();
    	                	 for (var i = 0; i < results.length; i++) {
    	                		 content.append('<tr><td width="700">'+sR.data[i].callbackContent+'</td><td width="200" class="wxtz_time">'+sR.data[i].createTimeLabel+'</td></tr>');
    	                	 }
    	                	 $("input[name='callbackContent']").val('');
    	                 },
    	                 error : function(html) {
    	                     return;
    	                 }
    	       	   	 });
     	    }
      	});
    } 
    
    openWrap();
    
    $(".hfanniu").click(function(){
    	var callbackContent = $("input[name='callbackContent']").val(); 
    	if(callbackContent==''){
    		alert('请填写相关内容');
    		return;
    	}else{ 
    		$.ajax({
        		type:'post',
        		url:ctx+'/myStudent/addCallbackInfo.do',
        		data:{ 
        			callbackContent:callbackContent,
        			studentId:studentId
    		    },
        		async:true,
        		success:function(sR){ 
        			if(sR!=null){
        				totalCount = sR.data;
            			openWrap(); 
        			}else{
        				totalCount=0;
        				openWrap();
        			}
        		
        		}
        	});   
    	}
    });
     
</script>
</html>