<%@page import="com.ccthanking.framework.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>

<script language="javascript">
var project_uid = "";
function closeGdzxt(){
	window.openen=null;
	window.open('','_self');
	window.close();
}
/*鼠标移入图片*/
$("#addTQTB").onmouseover(function(){
	$("#addTQTB~td").show();
});
/*鼠标移出图片*/
$("#addTQTB").onmouseover(function(){
	$("#addTQTB~td").hide();
});
jQuery(function($){
    init();
	project_uid=$("#qproject_uid").val();
	if(project_uid==null||project_uid==''){
		return false;
	}else{
		$.ajax({
			url :'${pageContext.request.contextPath}/project/buProjectController?queryProject',
			data : "project_uid="+project_uid,
			cache : false,
			async :	false,
			dataType : "json",  
			type : 'post',
			success : function(response) { 
				//var obj = convertJson.string2json1(response.msg);
				var resultobj = JSON.parse(response.obj);

				$('#project_name').val(resultobj.projectName);
				$('#project_uid').val(resultobj.projectUid);
				$("#pname").html(resultobj.projectName);
			}	
		});
	}
})

	
function init(){
      var project_uid = $("#qproject_uid").val();
      $.ajax({
			url :'${pageContext.request.contextPath}/project/buProjectController?queryWeather',
			data : {"project_uid":project_uid},
			cache : false,
			async :	false,
			dataType : "json",  
			type : 'post',
			success : function(result) { 
				var CITY_NAME = "";
				var WEATHER_DESC = "";//天气现象描述
				var MAX_TEMPERATURE = "";
				var MIN_TEMPERATURE = "";
				var WIND = "";//风力
				var WIND_DIRECTION = "";//风向
				var REPORT_DATE = "";//预报日期
				var WEATHER_PIC = "";//天气图标
				if(result.obj !=null && result.obj != ""){
							$.each(result.obj,function(){
							    CITY_NAME = this.CITY_NAME;
							    WEATHER_DESC  = this.WEATHER_DESC;
							    MAX_TEMPERATURE = this.MAX_TEMPERATURE;
							    MIN_TEMPERATURE = this.MIN_TEMPERATURE;
							    WIND = this.WIND;
								WIND_DIRECTION = this.WIND_DIRECTION;
								REPORT_DATE = this.GET_TIME;
								WEATHER_PIC = "./"+this.WEATHER_PIC;
								
							});
							//获取 当前项目所在的城市
							//$("#CITY_NAME").html(thtml);
							//星期几 处理
							var date = new Date();
							var day = date.getDay();//返回的是一周中 的某一天
							if(day === 0){
							   day = '日';
							}else if(day === 1){
							   day = "一";
							}else if(day === 2){
							   day = "二";
							}else if(day === 3){
							   day = "三";
							}else if(day === 4){
							   day = "四";
							}else if(day === 5){
							   day = "五";
							}else if(day === 6){
							   day = "六";
							}
							
							//显示天气的图标
							var tianqitb = "<i class=\"ace-icon\"><img width=\"36\" height=\"36\" src=\""+WEATHER_PIC+"\"></img></i>";
							//var tianqitb = "<i class=\"ace-icon\"><img width=\"45\" height=\"45\" src=\"./assets/images/weather/icon/day/yu.png\"></img></i>";
							$("#addTQTB").html(tianqitb);
							$("#someDay").html("周"+day+"&nbsp;");
							var reportyear = date.getFullYear();
 	                        var reportmonth = date.getMonth()+1;
 	                        var getDay = date.getDate();
							//日期处理 显示为年月 日 的 格式
							$("#REPORT_DATE").html(reportmonth+"月"+getDay+"日"+"&nbsp;&nbsp;");
							
							if(MIN_TEMPERATURE =="" || MAX_TEMPERATURE == "" ){
								$("#WEATHER_DESC").html("&nbsp;&nbsp;"+WEATHER_DESC+"&nbsp;");
							}else{
							//天气现象描述及气温处理
							$("#WEATHER_DESC").html("&nbsp;&nbsp;"+WEATHER_DESC+"&nbsp;"+MIN_TEMPERATURE+"~"+MAX_TEMPERATURE+"℃"+"&nbsp;&nbsp;&nbsp;&nbsp;");
							}
							
							$("#WIND_DIRECTION").html("&nbsp;&nbsp;"+WIND_DIRECTION+"&nbsp;"+WIND);
							//$("#WIND").html(WIND+"&nbsp;");
							
				
				//成功 获取天气状况 再 去查询工况信息(最新)
				$.ajax({
						url :'${pageContext.request.contextPath}/project/buProjectController?queryGKdetail',
						data : "project_uid="+project_uid,
						cache : false,
						async :	false,
						dataType : "json",  
						type : 'post',
						success : function(response) { 
							//var obj = convertJson.string2json1(response.msg);
							var resultobj = JSON.parse(response.obj);
							var PROJECT_ADDRESS = resultobj.PROJECT_ADDRESS;
							//获取 当前的农历日期
							var sysNlDate = resultobj.NLDATE.toString();
							var num = sysNlDate.indexOf('年');
							num++;
							var strDate = sysNlDate.substring(num);
							var dizhitb = "<img src=\"assets/images/login/address.png\" title=\""+PROJECT_ADDRESS+"\" width=\"26px;\" height=\"26px;\"/>";
							$("#showDIZHI").html(dizhitb);
							$("#xmdzname").html(PROJECT_ADDRESS);
							$("#addGKTB").html(tianqitb);
							$("#getNlDate").html("农历"+strDate);
			                var gkDescriprion = resultobj.DESCRIPTION.toString();
			                if(gkDescriprion.length > 10){
			                   var Descriprion = gkDescriprion.substr(0,12);
			                   $("#GKDETAIL").html(Descriprion);
			                }else{
			                   $("#GKDETAIL").html(gkDescriprion);
			                }
			               
			               
						}	
					});
							
				}else{
				   // $("#showTianqi").css("display","none");
				  return;
				}
			}	
		});

}

	  	function doPage(url,aid,app_code){

    		///$("#"+aid).css("border-top","2px #FFB752 solid");
 			//$("#"+aid).css("background-color","#0354a7");
 			//$("#"+aid).css("color","orange");
 			//$("#"+aid).find("i").addClass("orange"); 

 			//$("#"+aid).parent().siblings().find("i").removeClass("orange");	
 			//$("#"+aid).parent().siblings().find("a").css("border-top","#FFFFFF");	
 			//$("#"+aid).parent().siblings().find("a").css("background-color","#0866C6");	
 			//$("#"+aid).parent().siblings().find("a").css("color","white");
 			
 			$("#app_code").val(app_code);
			//$('#main-content').empty();
			//$('#main-content').load(url);
			window.location=url;
		}

</script>


<div class="navbar" id="navbar">
<input type="hidden" id="qproject_uid" value="${project_uid}" >
	<input type="hidden" id="project_uid"  >
	<input type="hidden"  id="project_name">
	<div class="navbar-container" id="navbar-container">
	    <div style="float: left;">
		   <img src="${ctx}/assets/images/logo/logo_01.png" height="45px;"/>
	    </div>
		<div style="float: left;margin:0px;background-color:transparent;">
		   <span class="navbar-brand" style="font-size: 18px;"><small><b id="pname"></b></small></span>
		</div>
		<div id="showDIZHI" style="width:36px;height:36px;cursor:pointer; padding-top:10px; padding-bottom:10px;" class="pull-left" >

		</div>
		<div class="navbar-header pull-left">
			<ul class="nav ace-nav">
				<app:menutop />
			</ul>
		</div>
		<div class="navbar-header pull-right" id="showTianqi">
			<ul class="nav">
			   <li >
			    <table >
			        <tr style="font-family:'Microsoft YaHei';font-size:15px;color:#DDD">
			          <td id="addTQTB" colspan="1" rowspan="2"></td>
			          <td id="someDay" style="display:none"></td>
			          <td id="REPORT_DATE" style="display:none"></td>
			          <td id="WEATHER_DESC" style="display:none"></td>
			          <td id="GKDETAIL"  style="display:none"></td>
			         
			        </tr>
		            <tr style="font-family:'Microsoft YaHei';font-size:15px;color:#DDD">
			            <td colspan="2" id="getNlDate" ></td>
			            <td id="WIND_DIRECTION"></td>
			            <td id="WIND"></td>
		            </tr>
			     </table>
			   </li>
			</ul>
		 </div>
	 </div>		
		<!-- /.navbar-header -->
	</div>
	<!-- /.container -->
	<input type="hidden" id="app_code">
</div>