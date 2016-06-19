<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8" />
		<title></title>
		<meta name="description" content="overview & stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link href="static/css/bootstrap.min.css" rel="stylesheet" />
		<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/css/font-awesome.min.css" />
		<!-- 下拉框 -->
		<link rel="stylesheet" href="static/css/chosen.css" />
		
		<link rel="stylesheet" href="static/css/ace.min.css" />
		<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
		<link rel="stylesheet" href="static/css/ace-skins.min.css" />
		
		<link rel="stylesheet" href="static/css/datepicker.css" /><!-- 日期框 -->
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		
		<link rel="stylesheet" type="text/css" href="${ctx}/plugins/webuploader/webuploader.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/plugins/webuploader/style.css" />
		
		
<script type="text/javascript"> 
	//保存
	function save(){
			if($("#F_PRODUCTNAME").val()==""){
			$("#F_PRODUCTNAME").tips({
				side:3,
	            msg:'请输入商品名',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#F_PRODUCTNAME").focus();
			return false;
		}
		if($("#F_PRODUCTDESC").val()==""){
			$("#F_PRODUCTDESC").tips({
				side:3,
	            msg:'请输入商品描述',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#F_PRODUCTDESC").focus();
			return false;
		}
		if($("#F_PRICE").val()==""){
			$("#F_PRICE").tips({
				side:3,
	            msg:'请输入商品价格',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#F_PRICE").focus();
			return false;
		}
		if($("#F_CLSID").val()==""){
			$("#F_CLSID").tips({
				side:3,
	            msg:'请输入商品类型',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#F_CLSID").focus();
			return false;
		}
		if($("#F_ISSPECIAL").val()==""){
			$("#F_ISSPECIAL").tips({
				side:3,
	            msg:'请输入是否特卖',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#F_ISSPECIAL").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body> 
	<form action="tproductController/${msg}.do" name="Form" id="Form" method="post">
		<input type="hidden" name="F_ProductId" id="F_ProductId" value="${pd.F_ProductId}"/>
		<input type="hidden" name="F_PRODUCTPIC" id="PICTURES_ID">
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">商品名:</td>
				<td><input type="text" name="F_PRODUCTNAME" id="F_PRODUCTNAME" value="${pd.F_PRODUCTNAME}" maxlength="32" placeholder="这里输入商品名" title="商品名"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">商品描述:</td>
				<td><input type="text" name="F_PRODUCTDESC" id="F_PRODUCTDESC" value="${pd.F_PRODUCTDESC}" maxlength="32" placeholder="这里输入商品描述" title="商品描述"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">商品价格:</td>
				<td><input type="number" name="F_PRICE" id="F_PRICE" value="${pd.F_PRICE}" maxlength="32" placeholder="这里输入商品价格" title="商品价格"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">商品类型:</td>
				<td><input type="text" name="F_CLSNAME" id="F_CLSNAME" value="${pd.F_CLSNAME}" maxlength="32" placeholder="这里输入商品类型" title="商品类型"/><span onclick="classTree()">选择</span></td>
			    <td><input type="hidden" name="F_CLSID" id="F_CLSID" value="${pd.F_CLSID}" maxlength="32" placeholder="这里输入商品类型" title="商品类型"/></td>
			    
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">是否特卖:</td>
				<td><input type="number" name="F_ISSPECIAL" id="F_ISSPECIAL" value="${pd.F_ISSPECIAL}" maxlength="32" placeholder="这里输入是否特卖" title="是否特卖"/></td>
			</tr>
			
			<tr>
			   <td><span class="primary btn" onclick="addStandard()">+</span></td>
			</tr>
			<tr>
			    <td colspan="2">
			        <table class="">
			              <th>序号</th>
			              <th>规格名</th>
			              <th>规格值</th> 
			              <tbody class="guige"></tbody>
			        </table>
			    </td>
			</tr>
			
			<tr>
			  <td colspan="2">
		     	<div id="zhongxin"> 
						<div id="wrapper">
							<div id="container">
								<!--头部，相册选择和格式选择--> 
								<div id="uploader">
									<div class="queueList">
										<div id="dndArea" class="placeholder">
											<div id="filePicker"></div>
											<p>或将照片拖到这里，单次最多可选300张</p>
										</div>
									</div>
									<div class="statusBar" style="display: none;">
										<div class="progress">
											<span class="text">0%</span> <span class="percentage"></span>
										</div>
										<div class="info"></div>
										<div class="btns">
											<div id="filePicker2"></div>
											<div class="uploadBtn">开始上传</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
			  </td>
			</tr>
			<tr>
				<td style="text-align: center;" colspan="10">
					<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
				</td>
			</tr>
		</table>
		</div>
		
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
		
	</form>
	

	
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		
			
    	<script type="text/javascript" src="${ctx}/plugins/webuploader/webuploader.js"></script>
    	<script type="text/javascript" src="${ctx}/plugins/webuploader/upload.js"></script>
	
		
		<script type="text/javascript">
		$(top.hangge());
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
		});
		
		//选择类型树
		function classTree(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag = true;
			 diag.Title = "菜单权限";
			 diag.URL = "${ctx}/goodClass/classTree.do";
			 diag.Width = 280;
			 diag.Height = 370;
			 diag.CancelEvent = function(){ //关闭事件
				diag.close();
			 };
			 diag.OKEvent = function(){  
				 var fClsId =  diag.innerFrame.contentWindow.document.getElementById('choseClassId').value;
				 var choseClassName =  diag.innerFrame.contentWindow.document.getElementById('choseClassName').value;

				 if(fClsId==''){
					alert("请选择一个类型");
				 } else{ 
					$("#F_CLSID").val(fClsId); 
					$("#F_CLSNAME").val(choseClassName);
					diag.close();
				 }  
			 };
			 //点击确定后调用的方法
			 diag.show();
		}
		
		//添加规格
		function addStandard(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag = true;
			 diag.Title = "添加规格";
			 diag.URL = "${ctx}/standard/goAdd.do";
			 diag.Width = 480;
			 diag.Height = 370;
			 diag.CancelEvent = function(){ //关闭事件
				diag.close();
			 };
			 diag.OKEvent = function(){  
				 var nameObj = $(diag.innerFrame.contentWindow.document.getElementById('F_STANDARDNAME'));
				 var standardName =  diag.innerFrame.contentWindow.document.getElementById('F_STANDARDNAME').value;
				 var standardValue =  diag.innerFrame.contentWindow.document.getElementById('F_STANDARDVALUE').value;
				 var xuhao =  diag.innerFrame.contentWindow.document.getElementById('F_XUHAO').value;

				 if(nameObj.val()==""){
						nameObj.tips({
							side:3,
				            msg:'请输入规格名',
				            bg:'#AE81FF',
				            time:2
				        });
						nameObj.focus();
						return false;
					}
				 
				 if(standardName!=''){ 
					var $tr= $("<tr></tr");
					$tr
					.append($("<td></td>").append($("<input type='text' name='F_XUHAO[]'>").val(xuhao)))
					.append($("<td></td>").append($("<input type='text' name='F_StandardName[]'>").val(standardName)))
					.append($("<td></td>").append($("<input type='text' name='F_StandardValue[]'>").val(standardValue))); 
					diag.close();
					$(".guige").append($tr);
				 }  
			 };
			 //点击确定后调用的方法
			 diag.show();
		}
		
		</script>
</body>
</html>