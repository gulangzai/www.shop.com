<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/commons/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>建工网校学员管理系统</title>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/homemain/wdxy_sy.css"> 
<script type="text/javascript" src="${ctxStatic}/js/jquery.page.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/m.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/jquery-1.11.3.min.js"></script> 
<script src="${ctxStatic}/js/echarts.min.js"></script>
</head> 
<body>
   <div class="top">
           <ul class="nav">
                   <li class="xz"><a href="${ctx}/myStudent/myStudentMain.do">首页</li>
                   <li><a href="${ctx}/myStudent/myStudentList.do">学员列表</a></li>
           </ul>    
   </div>

   <h2 class="one">个人信息</h2>
   

   <div width="400" border="0" class="gerenxinxi">
    <ul>
      <li width="147" class="ydq">姓名</li>
      <li width="196"><input type="text" value="张云"></li>
      <li width="43" class="zdq">修改</li>
    </ul>
    <ul>
      <li class="ydq">用户名</li>
      <li><input type="text" value="zhangyun"></li>
      <li class="zdq">修改</li>
    </ul>
    <ul>
      <li class="ydq">密码</li>
      <li><input type="password" value="zhangyun"></li>
      <li class="zdq">修改</li>
    </ul>
    <ul>
      <li class="ydq">手机号</li>
      <li><input type="text"></li>
      <li class="zdq">修改</li>
    </ul>
    <ul>
      <li class="ydq">机构名称</li>
      <li><input type="text"></li>
      <li class="zdq">修改</li>
    </ul>

   </div>

      <div class="bian"></div>
      
      <div width="400" border="0" class="gerenxinxi">

    <ul>
      <li class="ydq">账户余额</li>
      <li class="wdq">10000</li>
    </ul>
    <ul>
      <li class="ydq">上次充值金额</li>
      <li class="wdq">10000</li>
    </ul>
    <ul>
      <li class="ydq">上次充值时间</li>
      <li class="wdq">2016年1月7号14:26:20</li>
    </ul>
    <ul>
      <li class="ydq">年度充值金额</li>
      <li class="wdq">5000000</li>
    </ul>
    <ul>
      <li class="ydq">年度消耗金额</li>
      <li class="wdq">100000</li>
    </ul>

   </div>
     <div class="zxcz">在线充值</div>

   
 


   <h2>开课情况</h2>
 <!--   <iframe  width="944" height="420"  src="pie.html" frameborder="no"></iframe> -->
   <h2>业绩情况</h2>
<!--    <iframe  width="944" height="400"  src="zzt_yue.html" frameborder="no"></iframe> -->
   
   
   
   
   
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
  <tbody>
    <tr>
      <td width="768">11111111号外号外，为了迎接新年的到来，学尔森全线产品9折辣，活动时间2016年1月1日至1月15日.</td>
      <td width="132" class="wxtz_time">2016年1月1日</td>
    </tr>
    <tr>
      <td>号外号外，为了迎接新年的到来，学尔森全线产品9折辣，活动时间2016年1月1日至1月15日.</td>
      <td class="wxtz_time">2016年1月1日</td>
    </tr>
    <tr>
      <td>号外号外，为了迎接新年的到来，学尔森全线产品9折辣，活动时间2016年1月1日至1月15日.</td>
      <td class="wxtz_time">2016年1月1日</td>
    </tr>
  </tbody>
</table>
                </div>
            </div>
        </div>
        
        
        <div style="display: none;" class="box-txt-bottom" id="mzl_list_2">
            <div class="boxlf">
                <div class="boxword">
                <table width="880" border="0" class="wxtz" cellPadding="0" cellSpacing="0">
  <tbody>
    <tr>
      <td width="768">222222号外号外，为了迎接新年的到来，学尔森全线产品9折辣，活动时间2016年1月1日至1月15日.</td>
      <td width="132" class="wxtz_time">2016年1月1日</td>
    </tr>
    <tr>
      <td>号外号外，为了迎接新年的到来，学尔森全线产品9折辣，活动时间2016年1月1日至1月15日.</td>
      <td class="wxtz_time">2016年1月1日</td>
    </tr>
    <tr>
      <td>号外号外，为了迎接新年的到来，学尔森全线产品9折辣，活动时间2016年1月1日至1月15日.</td>
      <td class="wxtz_time">2016年1月1日</td>
    </tr>
  </tbody>
</table>
                
                </div>
            </div>
        </div>
        
        <div style="display: none;" class="box-txt-bottom" id="mzl_list_3">
            <div class="boxlf">
                <div class="boxword">
                <table width="880" border="0" class="wxtz" cellPadding="0" cellSpacing="0">
  <tbody>
    <tr>
      <td width="768">3333333号外号外，为了迎接新年的到来，学尔森全线产品9折辣，活动时间2016年1月1日至1月15日.</td>
      <td width="132" class="wxtz_time">2016年1月1日</td>
    </tr>
    <tr>
      <td>号外号外，为了迎接新年的到来，学尔森全线产品9折辣，活动时间2016年1月1日至1月15日.</td>
      <td class="wxtz_time">2016年1月1日</td>
    </tr>
    <tr>
      <td>号外号外，为了迎接新年的到来，学尔森全线产品9折辣，活动时间2016年1月1日至1月15日.</td>
      <td class="wxtz_time">2016年1月1日</td>
    </tr>
    <tr>
      <td width="768">3333333号外号外，为了迎接新年的到来，学尔森全线产品9折辣，活动时间2016年1月1日至1月15日.</td>
      <td width="132" class="wxtz_time">2016年1月1日</td>
    </tr>
    <tr>
      <td>号外号外，为了迎接新年的到来，学尔森全线产品9折辣，活动时间2016年1月1日至1月15日.</td>
      <td class="wxtz_time">2016年1月1日</td>
    </tr>
    <tr>
      <td>号外号外，为了迎接新年的到来，学尔森全线产品9折辣，活动时间2016年1月1日至1月15日.</td>
      <td class="wxtz_time">2016年1月1日</td>
    </tr>
  </tbody>
</table>
                
                </div>
            </div>
        </div>
        
        
    <!-----分页----->
    <div class="tcdPageCode">
          
          </div>
          
</div>
   
    

<h2>意见反馈</h2>
   <table width="880" border="0" class="yjfk" cellPadding="0" cellSpacing="0" >
  <tbody>
    <tr>
      <td width="47" align="center" valign="middle"><img src="${ctxStatic}/images/user_icon.png" width="37" height="37" alt=""/></td>
      <td width="695">学员报名了，开课速度有点慢</td>
      <td width="132" align="center"><span>回复</span></td>
    </tr>
    <tr>
      <td align="center" valign="middle"><img src="${ctxStatic}/images/user_icon2.png" width="37" height="37" alt=""/></td>
      <td>技术调整，13号之前能开。</td>
      <td align="center" ></td>
    </tr>
    <tr>
      <td align="center" valign="middle"><img src="${ctxStatic}/images/user_icon.png" width="37" height="37" alt=""/></td>
      <td>学员报名了，开课速度有点慢</td>
      <td align="center" ><span>回复</span></td>
    </tr>
    <tr>
      <td align="center" valign="middle"><img src="${ctxStatic}/images/user_icon.png" width="37" height="37" alt=""/></td>
      <td>学员报名了，开课速度有点慢</td>
      <td align="center" ><span>回复</span></td>
    </tr>
    <tr>
      <td align="center" valign="middle"><img src="${ctxStatic}/images/user_icon.png" width="37" height="37" alt=""/></td>
      <td>学员报名了，开课速度有点慢</td>
      <td align="center" ><span>回复</span></td>
    </tr>
    <tr>
      <td colspan="2"  valign="middle" class="hf"><input type="text"  placeholder="回复意见反馈" ></td>
      <td align="center"><input type="submit" value="发送"  class="fs"></td>
    </tr>
    
  </tbody>
     
</table>

<div class="kb"></div>

    

</body>

<script>
    $(".tcdPageCode").createPage({
        pageCount:11,
        current:1,
        backFn:function(p){
            console.log(p);
        }
    });
</script>
</html>