<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/commons/commons.jspf"%> 
<%
   String fClsId = request.getParameter("fClsId");
%>
<script type="text/javascript"> 
function flush(fClsId){ 
	$("#FCLSID").val(fClsId);
	$("#userForm").submit();
}
</script>
<!DOCTYPE html>
<html lang="en">
	<head>  
	
	</head>
<body>
 <div class="container-fluid" id="main-container">  
				<div id="page-content" class="clearfix"> 
				  <div class="row-fluid"> 
					<div class="row-fluid"> 
							<!-- 检索  -->
							<form action="${ctx}/goodClass/listClass.do" method="post" name="userForm" id="userForm">
							<input type="hidden" name="FCLSID" id="FCLSID" value="">
					    <!--<table border='0'>
								<tr> 
									<td>
										<span class="input-icon">
											<input autocomplete="off" id="nav-search-input" type="text" name="USERNAME" value=" " placeholder="这里输入关键词" />
											<i id="nav-search-icon" class="icon-search"></i>
										</span> 
									</td>
									
									<td><input class="span10 date-picker" name="lastLoginStart" id="lastLoginStart" value="" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="开始日期"/></td>
									<td><input class="span10 date-picker" name="lastLoginEnd" id="lastLoginEnd" value="" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="到期日期"/></td>
									<td style="vertical-align:top;"> 
									 	<select class="chzn-select" name="ROLE_ID" id="role_id" data-placeholder="请选择等级" style="vertical-align:top;width: 120px;">
										<option value=""></option> 
									  	</select>
									</td>
									<td style="vertical-align:top;"> 
									 	<select class="chzn-select" name="STATUS" id="STATUS" data-placeholder="状态" style="vertical-align:top;width: 79px;">
										<option value=""></option>
										<option value="">全部</option>
										<option value="1" <c:if test="">selected</c:if>可用</option>
										<option value="0" <c:if test="">selected</c:if>停用</option>
										</select>
									</td>
									<td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();"  title="检索"><i id="nav-search-icon" class="icon-search"></i></button></td>
								    <td style="vertical-align:top;"><a class="btn btn-mini btn-light" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="icon-download-alt"></i></a></td>
							    </tr>
							</table>-->
							
							<div><a class="btn btn-big btn-success" onclick="add();">新增商品类别</a></div>
							<!-- 检索  -->
							 
							<table id="table_report" class="table table-striped table-bordered table-hover"> 
								<thead>
									<tr>
										<th class="center">
										<label><input type="checkbox" id="zcheckbox" /><span class="lbl"></span></label>
										</th>
										<th>序号</th>
										<th>商品类型名</th> 
										<th class="center">状态</th>
										<th class="center">操作</th>
									</tr>
								</thead> 
								<tbody>
									
								<!-- 开始循环 -->	 
										<c:forEach items="${classList}" var="tclass" varStatus="vs"> 
											<tr>
												<td class='center' style="width: 30px;">
													<label><input type='checkbox' name='ids' value="${tclass.fClsId}" id="${tclass.fClsId }"  /><span class="lbl"></span></label>
												</td>
												<td class='center' style="width: 30px;">${vs.index+1}</td>
												<td>${tclass.fClsName}</td> 
												<td style="width: 60px;" class="center">
												     <c:if test="${tclass.fStatus==0}">
													 <span class="label label-important arrowed-in">可用</span>
													 </c:if> 
													 <c:if test="${tclass.fStatus==1}">
													 <span class="label label-success arrowed">停用</span> 
													 </c:if>
												</td>
												<td style="width: 30px;" class="center">
													<div class='hidden-phone visible-desktop btn-group'>   
														<div class="inline position-relative">
														<button class="btn btn-mini btn-info" data-toggle="dropdown"><i class="icon-cog icon-only"></i></button>
														<ul class="dropdown-menu dropdown-icon-only dropdown-light pull-right dropdown-caret dropdown-close">
														     <li><a style="cursor:pointer;" title="编辑" onclick="editUser('');" class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></li>
															 <li><a style="cursor:pointer;" title="删除" onclick="delClass('${tclass.fClsId}','商品类型:${tclass.fClsName}');" class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a></li>
												        </ul> 
														</div> 
													</div>
												</td>
											</tr>
										
										</c:forEach>   
								</tbody>
							</table> 
						</form>
					</div>
				  
					<!-- PAGE CONTENT ENDS HERE -->
				  </div><!--/row--> 
				</div><!--/#page-content-->
				</div><!--/.fluid-container#main-container-->
		
		<!-- 返回顶部  -->
		<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse">
			<i class="icon-double-angle-up icon-only"></i>
		</a> 
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='${ctxStatic}/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="${ctxStatic}/js/bootstrap.min.js"></script>
		<script src="${ctxStatic}/js/ace-elements.min.js"></script>
		<script src="${ctxStatic}/js/ace.min.js"></script>
		
		<script type="text/javascript" src="${ctxStatic}/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="${ctxStatic}/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="${ctxStatic}/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		<script type="text/javascript" src="${ctxStatic}/js/jquery.tips.js"></script><!--提示框-->
		<script type="text/javascript">
 
		$(top.hangge());
		
		//检索
		function search(){
			top.jzts();
			$("#userForm").submit();
		}
		
		//去发送电子邮件页面
		function sendEmail(EMAIL){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="发送电子邮件";
			 diag.URL = '${ctx}/head/goSendEmail.do?EMAIL='+EMAIL+'&msg=appuser';
			 diag.Width = 660;
			 diag.Height = 470;
			 diag.CancelEvent = function(){ //关闭事件
				diag.close();
			 };
			 diag.show();
		}
		
		//去发送短信页面
		function sendSms(phone){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="发送短信";
			 diag.URL = '${ctx}/head/goSendSms.do?PHONE='+phone+'&msg=appuser';
			 diag.Width = 600;
			 diag.Height = 265;
			 diag.CancelEvent = function(){ //关闭事件
				diag.close();
			 };
			 diag.show();
		}
		
		//新增
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增商品类别";
			 diag.URL = '${ctx}/happuser/goAddU.do';
			 diag.Width = 450;
			 diag.Height = 355;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 top.jzts();
						 setTimeout("self.location=self.location",100);
					 }else{
						 nextPage(${page.currentPage});
					 }
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//修改
		function editUser(user_id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="会员资料";
			 diag.URL = '${ctx}/happuser/goEditU.do?USER_ID='+user_id;
			 diag.Width = 450;
			 diag.Height = 355;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 nextPage(${page.currentPage});
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//删除
		function delClass(fClsId,msg){
			bootbox.confirm("确定要删除["+msg+"]吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "${ctx}/goodClass/deleteClass.do?fClsId="+fClsId+"&tm="+new Date().getTime();
					$.get(url,function(data){
						nextPage(${page.currentPage});
					});
				}
			});
		} 
		</script>
		
		<script type="text/javascript">
		
		$(function() {
			
			//下拉框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
			//复选框
			$('table th input:checkbox').on('click' , function(){
				var that = this;
				$(this).closest('table').find('tr > td:first-child input:checkbox')
				.each(function(){
					this.checked = that.checked;
					$(this).closest('tr').toggleClass('selected');
				});
					
			});
			
		});
		
		
		//批量操作
		function makeAll(msg){
			bootbox.confirm(msg, function(result) {
				if(result) {
					var str = '';
					var emstr = '';
					var phones = '';
					for(var i=0;i < document.getElementsByName('ids').length;i++)
					{
						  if(document.getElementsByName('ids')[i].checked){
						  	if(str=='') str += document.getElementsByName('ids')[i].value;
						  	else str += ',' + document.getElementsByName('ids')[i].value;
						  	
						  	if(emstr=='') emstr += document.getElementsByName('ids')[i].id;
						  	else emstr += ';' + document.getElementsByName('ids')[i].id;
						  	
						  	if(phones=='') phones += document.getElementsByName('ids')[i].alt;
						  	else phones += ';' + document.getElementsByName('ids')[i].alt;
						  }
					}
					if(str==''){
						bootbox.dialog("您没有选择任何内容!", 
							[
							  {
								"label" : "关闭",
								"class" : "btn-small btn-success",
								"callback": function() {
									//Example.show("great success");
									}
								}
							 ]
						);
						
						$("#zcheckbox").tips({
							side:3,
				            msg:'点这里全选',
				            bg:'#AE81FF',
				            time:8
				        });
						
						return;
					}else{
						if(msg == '确定要删除选中的数据吗?'){
							top.jzts();
							$.ajax({
								type: "POST",
								url: '${ctx}/happuser/deleteAllU.do?tm='+new Date().getTime(),
						    	data: {USER_IDS:str},
								dataType:'json',
								//beforeSend: validateData,
								cache: false,
								success: function(data){
									 $.each(data.list, function(i, list){
											nextPage(${page.currentPage});
									 });
								}
							});
						}else if(msg == '确定要给选中的用户发送邮件吗?'){
							sendEmail(emstr);
						}else if(msg == '确定要给选中的用户发送短信吗?'){
							sendSms(phones);
						}
						
					}
				}
			});
		}
		
		//导出excel
		function toExcel(){
			var USERNAME = $("#nav-search-input").val();
			var lastLoginStart = $("#lastLoginStart").val();
			var lastLoginEnd = $("#lastLoginEnd").val();
			var ROLE_ID = $("#role_id").val();
			var STATUS = $("#STATUS").val();
			window.location.href='${ctx}/happuser/excel.do?USERNAME='+USERNAME+'&lastLoginStart='+lastLoginStart+'&lastLoginEnd='+lastLoginEnd+'&ROLE_ID='+ROLE_ID+'&STATUS='+STATUS;
		}
		</script>
		
	</body>
</html>

