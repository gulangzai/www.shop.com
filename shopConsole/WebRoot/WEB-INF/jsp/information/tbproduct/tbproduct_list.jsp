<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/commons/commons.jspf"%> 
<!DOCTYPE html>
<html lang="en">
	<head></head>
<body>

<style type="text/css">
	table.imagetable {
		font-family: verdana, arial, sans-serif;
		font-size: 14px;
	}
	
	table.imagetable th {
		padding: 8px;
	}
	
	table.imagetable td {
		padding:  8px;
	}
</style>


	<div class="container-fluid" id="main-container">

<div id="page-content" class="clearfix">
						
  <div class="row-fluid"> 
	<div class="col-sm-12" id="inside">
	
			<!-- 检索  -->
			<form class="form-inline" width="100%" action="${ctx}/tbProductController/list.do" method="post" name="Form" id="Form" style="border:solid 1px #ddd;line-height:50px;vertical-align:middle;">
					
					<div class="form-group">
					                                           日期：
							   	<input class="form-control date-picker" name="lastLoginStart" id="lastLoginStart" value="${pd.lastLoginStart}" type="text" data-date-format="yyyy-mm-dd"  style="width:120px;height:25px;border-radius:8px 8px 8px 8px;border:1px solid #d1d2d9;" placeholder="开始日期"/>
							     	~
					            <input class="form-control date-picker" type="text" data-date-format="yyyy-mm-dd"  style="width:120px;height:25px;border-radius:8px 8px 8px 8px;border:1px solid #d1d2d9;" placeholder="结束日期" name="lastLoginEnd" id="lastLoginEnd" value="${pd.lastLoginEnd}" />
						 
						 		
							  	<button class="btn  btn-link btn-sm" type="button" onclick="search();">
							  	   <i class="colorshow  ace-icon glyphicon glyphicon-search"></i>
							  	   <span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">查询</span>
							  	</button>
							  	<button class="btn btn-link btn-sm" type="button"  onclick="toExcel();"> 
							      	 <i  class="colorshow  ace-icon glyphicon glyphicon-download-alt"></i>
							     	 <span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">下载</span> 
					            </button>
					</div>
		  
			<!-- 检索  --> 
		
			<table id="table_report" class="table table-striped table-bordered table-hover  imagetable">
				
				<thead>
					<tr>
						<th class="center">
						<label><input type="checkbox" id="zcheckbox" /><span class="lbl"></span></label>
						</th>
						<th class="center"  width="5%">序号</th>
						<th class="center"  width="10%">商品名</th>
						<th class="center"  width="50%">商品描述</th>
						<th class="center"  width="10%">商品价格</th>
						<th class="center"  width="10%">商品类型</th>
						<th class="center"  width="10%">是否特卖</th>
						<th class="center"  width="5%">操作</th>
					</tr>
				</thead> 				
				<tbody> 
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty varList}">
						<c:if test="${QX.cha == 1 }">
						<c:forEach items="${varList}" var="var" varStatus="vs">
							<tr>
								<td class='center' style="width: 30px;">
									<label><input type='checkbox' name='ids' value="${var.F_ProductId}" /><span class="lbl"></span></label>
								</td>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
										<td  style="text-align:center">${var.F_PRODUCTNAME}</td>
										<td>${var.F_PRODUCTDESC}</td>
										<td  style="text-align:center">${var.F_PRICE}</td>
										<td  style="text-align:center">${var.F_CLSID}</td>
										<td  style="text-align:center">${var.F_ISSPECIAL}</td>
										
							   <td  class="center" style="width:50px;">   
									 <a style="cursor:pointer;" title="编辑" onclick="edit('${var.F_ProductId}');" class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a> 
								     <a style="cursor:pointer;" title="删除" onclick="del('${var.F_ProductId}');" class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a> 
								</td>  
							</tr>
						
						</c:forEach>
						</c:if>
						<c:if test="${QX.cha == 0 }">
							<tr>
								<td colspan="100" class="center">您无权查看</td>
							</tr>
						</c:if>
					</c:when>
					<c:otherwise>
						<tr class="main_info">
							<td colspan="100" class="center" >没有相关数据</td>
						</tr>
					</c:otherwise>
				</c:choose> 
				</tbody>
				
			</table>
			
	 
			<div class="page-header position-relative"> 
				<table style="width:100%;">
					<tr>
						<td style="vertical-align:top;">
							<c:if test="${QX.add == 1 }">
							<a class="btn btn-small btn-success" onclick="add();">新增</a>
							</c:if>
							<c:if test="${QX.del == 1 }">
							<a class="btn btn-small btn-danger" onclick="makeAll('确定要删除选中的数据吗?');" title="批量删除" ><i class='icon-trash'></i></a>
							</c:if>
						</td>
						<td style="vertical-align:top;">
						<div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
					</tr>
				</table>
			</div>
		</form>
		
		
	</div> <!-- /row --> 
	<!-- PAGE CONTENT ENDS HERE -->
  </div><!--/row-->
	
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
		
		<!-- 返回顶部  -->
		<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse">
			<i class="icon-double-angle-up icon-only"></i>
		</a>
		
       
       <script type="text/javascript">
		
		$(top.hangge());
		
		//检索
		function search(){
			top.jzts();
			$("#Form").submit();
		}
		
		//新增
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '${ctx}/tbProductController/goAdd.do';
			 diag.Width = 650;
			 diag.Height = 455;
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
		
		//删除
		function del(Id){
			bootbox.confirm("确定要删除吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "${ctx}/tbProductController/delete.do?F_ProductId="+Id+"&tm="+new Date().getTime();
					$.get(url,function(data){
						nextPage(${page.currentPage});
					});
				}
			});
		}
		
		//修改
		function edit(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '${ctx}/tproductController/goEdit.do?F_ProductId='+Id;
			 diag.Width = 650;
			 diag.Height = 455;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 nextPage(${page.currentPage});
				}
				diag.close();
			 };
			 diag.show();
		} 
		
		 
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
					for(var i=0;i < document.getElementsByName('ids').length;i++)
					{
						  if(document.getElementsByName('ids')[i].checked){
						  	if(str=='') str += document.getElementsByName('ids')[i].value;
						  	else str += ',' + document.getElementsByName('ids')[i].value;
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
								url: '${ctx}/tproductController/deleteAll.do?tm='+new Date().getTime(),
						    	data: {DATA_IDS:str},
								dataType:'json',
								//beforeSend: validateData,
								cache: false,
								success: function(data){
									 $.each(data.list, function(i, list){
											nextPage(${page.currentPage});
									 });
								}
							});
						}
					}
				}
			});
		}
		
		//导出excel
		function toExcel(){
			window.location.href='${ctx}/tproductController/excel.do';
		}
		</script>
		
	</body>
</html>

