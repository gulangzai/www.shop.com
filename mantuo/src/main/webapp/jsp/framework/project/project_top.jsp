<%@page import="com.ccthanking.framework.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<link rel="icon" href="${pageContext.request.contextPath }/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="${pageContext.request.contextPath }/favicon.ico" type="image/x-icon" />

<script language="javascript">
	var project_uid = "";
	function closeGdzxt() {
		window.openen = null;
		window.open('', '_self');
		window.close();
	}
		/*鼠标移入图片*/

	jQuery(function($) {
		init();
		//dopage('6');
		 var root_uid = "<%=root_uid%>";
		 if(root_uid=='111'){
			 $("#modelName").text("基坑");
		 }else if(root_uid=='15'){
			 $("#modelName").text("微现场");
		 }else if(root_uid=='108'){
			 $("#modelName").text("BIM");
		 }else if(root_uid=='1'){
			 $("#modelName").text("设置"); 
		 }
		 
		project_uid = $("#qproject_uid").val();
		projectUserId=$("#projectUserId").val();
		
		
		if (project_uid == null || project_uid == '') {
			return false;
		} else {
			$
					.ajax({
						url : '${pageContext.request.contextPath}/project/buProjectController?queryProject',
						data : "project_uid=" + project_uid,
						cache : false,
						async : false,
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

	function init() {
		var project_uid = $("#qproject_uid").val();
		$
				.ajax({
					url : '${pageContext.request.contextPath}/project/buProjectController?queryWeather',
					data : {
						"project_uid" : project_uid
					},
					cache : false,
					async : false,
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
						if (result.obj != null && result.obj != "") {
							$.each(result.obj, function() {
								CITY_NAME = this.CITY_NAME;
								WEATHER_DESC = this.WEATHER_DESC;
								MAX_TEMPERATURE = this.MAX_TEMPERATURE;
								MIN_TEMPERATURE = this.MIN_TEMPERATURE;
								WIND = this.WIND;
								WIND_DIRECTION = this.WIND_DIRECTION;
								REPORT_DATE = this.GET_TIME;
								WEATHER_PIC = "./" + this.WEATHER_PIC;

							});
							//获取 当前项目所在的城市
							//$("#CITY_NAME").html(thtml);
							//星期几 处理
							var date = new Date();
							var day = date.getDay();//返回的是一周中 的某一天
							if (day === 0) {
								day = '日';
							} else if (day === 1) {
								day = "一";
							} else if (day === 2) {
								day = "二";
							} else if (day === 3) {
								day = "三";
							} else if (day === 4) {
								day = "四";
							} else if (day === 5) {
								day = "五";
							} else if (day === 6) {
								day = "六";
							}

							//显示天气的图标
							var tianqitb = "<i class=\"ace-icon\"><img width=\"36\" height=\"36\" src=\""+WEATHER_PIC+"\"></img></i>";
							//var tianqitb = "<i class=\"ace-icon\"><img width=\"45\" height=\"45\" src=\"./assets/images/weather/icon/day/yu.png\"></img></i>";
							$("#addTQTB").html(tianqitb);
							$("#someDay").html("周" + day + "&nbsp;");
							var reportyear = date.getFullYear();
							var reportmonth = date.getMonth() + 1;
							var getDay = date.getDate();
							//日期处理 显示为年月 日 的 格式
							$("#REPORT_DATE").html(
									reportmonth + "月" + getDay + "日"
											+ "&nbsp;&nbsp;");

							if (MIN_TEMPERATURE == "" || MAX_TEMPERATURE == "") {
								$("#WEATHER_DESC").html(
										"&nbsp;&nbsp;" + WEATHER_DESC
												+ "&nbsp;");
							} else {
								//天气现象描述及气温处理
								$("#WEATHER_DESC").html(
										"&nbsp;&nbsp;" + WEATHER_DESC
												+ "&nbsp;" + MIN_TEMPERATURE
												+ "~" + MAX_TEMPERATURE + "℃"
												+ "&nbsp;&nbsp;&nbsp;&nbsp;");
							}

							$("#WIND_DIRECTION").html(
									"&nbsp;&nbsp;" + WIND_DIRECTION + "&nbsp;"
											+ WIND);
							//$("#WIND").html(WIND+"&nbsp;");

							//成功 获取天气状况 再 去查询工况信息(最新)
							$
									.ajax({
										url : '${pageContext.request.contextPath}/project/buProjectController?queryGKdetail',
										data : "project_uid=" + project_uid,
										cache : false,
										async : false,
										dataType : "json",
										type : 'post',
										success : function(response) {
											//var obj = convertJson.string2json1(response.msg);
											var resultobj = JSON
													.parse(response.obj);
											var PROJECT_ADDRESS = resultobj.PROJECT_ADDRESS;
											//获取 当前的农历日期
											var sysNlDate = resultobj.NLDATE
													.toString();
											var num = sysNlDate.indexOf('年');
											num++;
											var strDate = sysNlDate
													.substring(num);
											var dizhitb = "<img src=\"assets/images/login/address.png\" title=\""+PROJECT_ADDRESS+"\" width=\"26px;\" height=\"26px;\"/>";
											$("#showDIZHI").html(dizhitb);
											$("#xmdzname")
													.html(PROJECT_ADDRESS);
											$("#addGKTB").html(tianqitb);
											$("#getNlDate")
													.html("农历" + strDate);
											var gkDescriprion = resultobj.DESCRIPTION
													.toString();
											if (gkDescriprion.length > 10) {
												var Descriprion = gkDescriprion
														.substr(0, 12);
												$("#GKDETAIL")
														.html(Descriprion);
											} else {
												$("#GKDETAIL").html(
														gkDescriprion);
											}

										}
									});

						} else {
							// $("#showTianqi").css("display","none");
							return;
						}
					}
				});

		$("#xianshi").mouseover(function(){

	if($("#xianshi tr td:hidden").is(":hidden")){
		$("#xianshi tr td:hidden").show();

	}

});
/*鼠标移出图片*/
$("#xianshi").mouseout(function(){
	if($("#xianshi tr td:visible").is(":visible")){
		$("#xianshi tr td:visible").hide();
		$("#addTQTB").show();

	}
});



	}

	function dopage(puid) {
		$.each($("li"), function() {
			if ($(this).attr("appName") != null) {
				if ($(this).attr("appName") == puid) {
					$(this).show();
				} else {
					$(this).hide();
				}
			}
		});

		if (puid == '6') {
			window.location.href = 'pageproject/framework/project/frame_project_main#page/framework/project/project_index';
		}

		if (puid == '12') {
			window.location.href = 'pageproject/framework/project/frame_project_main#page/framework/project/weixc_index';
		}

		if (puid == '41') {
			window.location.href = 'pageproject/framework/project/frame_project_main#page/business/Viewer/webgl-Viewer';
		}
	}
function dofanhui(project_uid,projectUserId){
	var project_uid = $("#qproject_uid").val();
	var projectUserId=$("#projectUserId").val();
	// document.location.href="${pageContext.request.contextPath }";
	 window.location.href = '${pageContext.request.contextPath }/jsp/framework/project/myproject_index.jsp?project_uid='+project_uid+'&projectUserId='+projectUserId+'';
}
	
</script>


<div class="navbar navbar-bjt" id="navbar" style="height:70px;">
	<input type="hidden" id="qproject_uid" value="${project_uid}">
	<input type="hidden" id="project_uid"> 
	<input type="hidden" id="project_name">
	<div class="navbar-container navbar-padding" id="navbar-container">
		<%--LOGO --%>

		<div style="float: left;margin-left:30px;margin-top:5px;">
			<img src="${ctx}/assets/img/web2--logo.png" height="40px;" width="70px" />
			<span id="modelName" style="display:block; font-size:12px;text-align:center;margin-left:-7px;"></span>
		</div>
		<div class="bjt1">
			<%-- 返回项目列表 页面--%>
			<div class="navbar-content">
			<div id="fanhui"
				style="height:45px;cursor:pointer; margin:0px;"
				class="pull-right">
				<ul class="nav ace-nav">
				<!-- 
				window.location.href = '${pageContext.request.contextPath }/jsp/framework/project/myproject_index.jsp?project_uid=1&projectUserId=1;
				 -->
				<!-- javascript:window.opener=null;window.open('','_self');window.close();-->
					<li><a href="javascript:void(0)"onclick="dofanhui();"> <i
							class="ace-icon fa fa-reply icon-only" style="font-size:20px;"
							title="返回项目列表"></i> </a>
					</li>
				</ul>
			</div>

			<div class="navbar-header pull-right" id="showTianqi">
				<ul class="nav">
					<li>
						<table id="xianshi">
							<tr
								style="font-family:'Microsoft YaHei';font-size:15px;color:#DDD">
								<td id="addTQTB" colspan="1" rowspan="2"></td>
								<td id="someDay" style="display:none"></td>
								<td id="REPORT_DATE" style="display:none"></td>
								<td id="WEATHER_DESC" style="display:none"></td>
								<td id="GKDETAIL" style="display:none"></td>

							</tr>
							<tr
								style="font-family:'Microsoft YaHei';font-size:15px;color:#DDD">
								<td colspan="2" id="getNlDate" style="display:none"></td>
								<td id="WIND_DIRECTION" style="display:none"></td>
								<td id="WIND" style="display:none"></td>
							</tr>
						</table></li>
				</ul>
			</div>

		<div class="navbar-content-3">
			<span class="navbar-brand" style="font-size: 20px;"><small><b
					id="pname"></b>
			</small>
			</span>
		</div>
	    </div>
	</div>
	<!-- /.navbar-header -->
</div>
<!-- /.container -->
<input type="hidden" id="app_code" />
<input type="hidden" id="projectUserId" value="${projectUserId}" />

</div>