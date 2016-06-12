<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/commons/taglib.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
 <link rel="shortcut icon" href="image/ico.png" />
  <meta name="description" content="啾唧网 方便快捷，物美价廉，更快送达，诚信服务" />
  <meta name="keywords" content="啾唧商品页" />
<title>啾唧-商品页</title> 
<link rel="stylesheet" href="${ctxStatic}/css/base.css" type="text/css" />  
<link rel="stylesheet" href="${ctxStatic}/css/homepage/base.css" type="text/css" />
<link rel="stylesheet" href="${ctxStatic}/css/goods.css" type="text/css" /> 
<link rel="stylesheet" href="${ctxStatic}/css/head.css" type="text/css" />


<script src="${ctxStatic}/js/jquery-1.11.3.min.js" type="text/javascript"></script>  
<script src="${ctxStatic}/js/browser.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctxStatic}/js/responsive.tabs.js"></script>
 
<script src="${ctx}/assets/js/bootstrap.min.js" type="text/javascript"></script> 

<%
String rootFile = request.getScheme()+"://"+request.getServerName()+":8088/file";
%>

 
<script type="text/javascript">
var ctx ='${ctx}';
$(document).ready(function(){ 
	$('.tabs').respTabs({
		responsive : false
	});
	
});
$(function() {
	//通用头部搜索切换
	$('#search-hd .search-input').on(
			'input propertychange',
			function() {
				var val = $(this).val();
				if (val.length > 0) {
					$('#search-hd .pholder').hide(0);
				} else {
					var index = $('#search-bd li.selected').index();
					$('#search-hd .pholder').eq(index).show().siblings(
							'.pholder').hide(0);
				}
			})
	$('#search-bd li').click(
			function() {
				var index = $(this).index();
				$('#search-hd .pholder').eq(index).show().siblings(
						'.pholder').hide(0);
				$('#search-hd .search-input').eq(index).show().siblings(
						'.search-input').hide(0);
				$(this).addClass('selected').siblings().removeClass(
						'selected');
				$('#search-hd .search-input').val('');
			});
})
</script>
</head>
<body>
	<div class="top">
		<div class="title">
			<div class="wel">
				<ul>
					<li>欢迎来到啾唧网</li>
					<li><a href="${ctx}/userCtrl/login.do" target="_self">请登录</a></li>
					<li><a href="${ctx}/homePageCtrl/toRegister.do" target="_self">免费注册</a></li>
				</ul>
			</div>
			<div class="personal">
				<ul>
					<li><a href="javascript:alert('功能暂未开放')">个人中心</a></li>
					<li><a href="javascript:alert('功能暂未开放')">收藏夹</a></li>
					<li><a href="javascript:alert('功能暂未开放')">购物车</a></li>
					<li><a href="javascript:alert('功能暂未开放')">网站导航</a></li>
				</ul>
			</div>
		</div>
	</div> 

   <div class="search">
		<div class="search-con">
			<div class="logo">
				<a href="${ctx}/homePageCtrl/toHomePage.do"><img src="${ctxStatic}/images/homepage/logo2.png" width="145" alt="" /></a>
			</div>
			<div class="search-form">
				<div id="search-bd" class="search-bd">
					<ul>
						<li class="selected">找商品</li>
						<li>找商家</li>
					</ul>
				</div>
				<div id="search-hd" class="search-hd" style="height:40px;padding:10px 0px;">
					<div class="search-bg"></div>
					<input type="text" id="autocomplete-ajax" class="search-input"> 
					<input type="text" id="s2" class="search-input"> <span
						class="s1 pholder">生鲜水果半价抢疯</span> <span class="s2 pholder">搜商家名称</span>
					<button id="submit" class="btn-search" value="搜索">搜索</button>
				</div> 
				 <div id="selction-ajax"></div> 
			</div>
		</div>
	</div>
	
     <div class="content">
        	<div class="headNav"> 
			<div class="navCon w1020"> 
				<div class="navCon-cate fl navCon_on"> 
					<div class="navCon-cate-title">
						<a href="#">全部商品分类</a>
					</div> 
					<div class="cateMenu hide"> 
						<ul> 
						<c:forEach var="tClass" items="${tclasses}">
					        <li style="border-top: none;"> 
								<div class="cate-tag">
									<strong><a href="">${tClass.FClsName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&gt;</a></strong>
								</div> 
								<div class="list-item hide"> 
									<ul class="itemleft"> 
										<dl> 
											<!-- <dt>水果/蔬菜</dt> -->
											<dd>
											        <c:forEach var="sonTClass" items="${tClass.sonTClasses}">
													    <a href="#">${sonTClass.FClsName}</a>  
													</c:forEach>
											</dd> 
										</dl>  
									</ul>  
								</div> 
							</li>
					    </c:forEach>   
						</ul> 
					</div> 
				</div>

				<div class="navCon-menu fl"> 
					<ul> 
						<li><a class="curMenu" href="${ctx}/homePageCtrl/toHomePage.do">首页</a></li>
                        <li><a href="javascript:alert('功能暂未开放')">团购场</a></li> 
						<li><a href="javascript:alert('功能暂未开放')">自营超市</a></li> 
						<li><a href="javascript:alert('功能暂未开放')">名家鉴赏</a></li> 
						<li><a href="javascript:alert('功能暂未开放')">闪购</a></li> 
						<li><a href="javascript:alert('功能暂未开放')">VIP专场</a></li> 
					</ul>  
			</div>  
		</div>
     </div>

	<div class="goods">
		<div class="preview-left">
			<div id="vertical" class="bigImg">
			     <img src="<%=rootFile%>/${singleProduct.path}" width="400" height="400" alt="" id="midimg" />
			       <!--<c:forEach var="t_picture" items="${t_pictures}"> 
                   </c:forEach>  -->
				<div style="display: none;" id="winSelector"></div>
			</div>
			<!--bigImg end-->
			<div class="smallImg">
				<div class="scrollbutton smallImgUp disabled"></div>
				<div id="imageMenu">
					<ul> 
					<li  id="onlickImg"><img src="<%=rootFile%>/${singleProduct.path}" width="68"
							height="68" alt="水果" /></li>
					<!--<c:forEach var="t_picture" items="${t_pictures}"> 
					</c:forEach>  -->
					</ul>
				</div>
				<div class="scrollbutton smallImgDown"></div>
			</div>
			<!--smallImg end-->
			<div id="bigView" style="display: none;">
				<img width="800" height="800" alt="" src="" />
			</div>
		</div>

		<div class="preview-right">
		    <span class="productId" style="display:none">${singleProduct.FProductId}</span>
			<h1 class="goods-title">
				<span itemprop="name" class="productName">${singleProduct.FProductName}</span>
			</h1>
			<div class="goods-main">
				<div class="main-box">
					<div class="property-box">
						<span class="property-type">价格：</span> <span class="origin-price">¥${singleProduct.FPrice}</span>
					</div> 
				</div>
				 
					
				<div class="property-extra" >  
				 
				   <c:forEach var="tbStandard" items="${tbStandards}">
					   	&nbsp; 
                       <div style="margin:5px 0;padding:5px;display:block;font-size:16px;color:#A5A5A5">${tbStandard.f_standardName}:<span>&nbsp${tbStandard.f_standardValue}</span></div>
                    </c:forEach>  
                    
				</div>
			</div>
		
			 
			 
			 
			 
			<!-- <div class="">地址</div> -->
			<div class="buy">
				<div class="buy-btn">
					<a  id="submitRecordInfo" data-toggle="modal" data-target="#recordContact">提交订单信息</a>
				</div>
				<!-- <div class="shop">
					<a href="#">加入购物车</a>
				   </div> -->
			</div>
		</div>

       <div style="clear:both"></div>
		<div class="tabs">
			<ul class="tabs-list">
				<li class="active"><a href="javascript:;">产品详情</a></li>
	   	 <!--  <li><a href="javascript:;">累计评价</a></li>  -->  
			</ul>
			<div class="tabs-container">
				<div class="tab-content">
					<!-- <h4>品牌名称：大红枣</h4> -->
					<p>
						<span> ${singleProduct.FProductDesc}</span>
					</p>
				</div>
				<div class="tab-content">
					 <p><span>暂无评论</span></p>
				</div>
			</div>
		  </div>
		</div>
	</div>


<!-- ------------------------------------------------------- -->
	<div class="footer">
		<div class="footer-content">
			<div class="cooperation">
				<ul>
					<li><a href="javascript:alert('功能暂未开放')">商家入驻</a></li>
					<li><a href="javascript:alert('功能暂未开放')">销售联盟</a></li>
					<li><a href="javascript:alert('功能暂未开放')">关于我们</a></li>
					<li><a href="javascript:alert('功能暂未开放')">商品专题</a></li>
					<li><a href="javascript:alert('功能暂未开放')">热门搜索</a></li>
					<li><a href="javascript:alert('功能暂未开放')">服务热线</a></li>
				</ul>
			</div>
			<div class="copy">&copy;2000-2016&nbsp;&nbsp;啾唧在线电子商务有限公司&nbsp;&nbsp;&nbsp;版权所有 皖ICP备16007801号</div>
		</div>
	</div>
	
	
<!-- ---------------- -->
<div id="recordContact" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
 
</div><!-- /.modal -->  
 

<script src="${ctxStatic}/js/header.js" type="text/javascript"></script>  

<!-- ---------------------------ace -----> 
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.css" type="text/css" />  


<!--preview end-->
<script type="text/javascript">

(function(){
	//提交订单信息
	$("#submitRecordInfo").click(function(){
		//$('#newAdd').attr("data-target","xmgk-input");
		//$("#submitRecordInfo").attr("href","#recordContact");  
		var productName = $(".productName").text();
		var productId = $(".productId").text();
		$("#recordContact").empty(); 
		$.get("${ctx}/homePageCtrl/toRecordContact.do?productName="+productName+"&productId="+productId,function(data) {
			$("#recordContact").empty();
			$("#recordContact").html("");
			$("#recordContact").html(data);
		}); 
	});
	
})();
$(document).ready(function(){
	// 图片上下滚动
	var count = $("#imageMenu li").length - 4; /* 显示 5 个 li标签内容 */
	var interval = $("#imageMenu li:first").width();
	var curIndex = 0;
	
	$('.scrollbutton').click(function(){
		if( $(this).hasClass('disabled') ) return false;
		
		if ($(this).hasClass('smallImgUp')) --curIndex;
		else ++curIndex;
		
		$('.scrollbutton').removeClass('disabled');
		if (curIndex == 0) $('.smallImgUp').addClass('disabled');
		if (curIndex == count-1) $('.smallImgDown').addClass('disabled');
		
		$("#imageMenu ul").stop(false, true).animate({"marginLeft" : -curIndex*interval + "px"}, 600);
	});	
	// 解决 ie6 select框 问题
	$.fn.decorateIframe = function(options) {
        if ($.browser.msie && $.browser.version < 7) {
            var opts = $.extend({}, $.fn.decorateIframe.defaults, options);
            $(this).each(function() {
                var $myThis = $(this);
                //创建一个IFRAME
                var divIframe = $("<iframe />");
                divIframe.attr("id", opts.iframeId);
                divIframe.css("position", "absolute");
                divIframe.css("display", "none");
                divIframe.css("display", "block");
                divIframe.css("z-index", opts.iframeZIndex);
                divIframe.css("border");
                divIframe.css("top", "0");
                divIframe.css("left", "0");
                if (opts.width == 0) {
                    divIframe.css("width", $myThis.width() + parseInt($myThis.css("padding")) * 2 + "px");
                }
                if (opts.height == 0) {
                    divIframe.css("height", $myThis.height() + parseInt($myThis.css("padding")) * 2 + "px");
                }
                divIframe.css("filter", "mask(color=#fff)");
                $myThis.append(divIframe);
            });
        }
    }
    $.fn.decorateIframe.defaults = {
        iframeId: "decorateIframe1",
        iframeZIndex: -1,
        width: 0,
        height: 0
    }
    //放大镜视窗
    $("#bigView").decorateIframe();
    //点击到中图
    var midChangeHandler = null;
	
    $("#imageMenu li img").bind("click", function(){
		if ($(this).attr("id") != "onlickImg") {
			midChange($(this).attr("src").replace("small", "mid"));
			$("#imageMenu li").removeAttr("id");
			$(this).parent().attr("id", "onlickImg");
		}
	}).bind("mouseover", function(){
		if ($(this).attr("id") != "onlickImg") {
			window.clearTimeout(midChangeHandler);
			midChange($(this).attr("src").replace("small", "mid"));
			$(this).css({ "border": "3px solid #959595" });
		}
	}).bind("mouseout", function(){
		if($(this).attr("id") != "onlickImg"){
			$(this).removeAttr("style");
			midChangeHandler = window.setTimeout(function(){
				midChange($("#onlickImg img").attr("src").replace("small", "mid"));
			}, 1000);
		}
	});
    function midChange(src) {
        $(".midimg").attr("src", src).load(function() {
            changeViewImg();
        });
    }
    //大视窗看图
    function mouseover(e) {
        if ($("#winSelector").css("display") == "none") {
            $("#winSelector,#bigView").show();
        }
        $("#winSelector").css(fixedPosition(e));
        e.stopPropagation();
    }
    function mouseOut(e) {
        if ($("#winSelector").css("display") != "none") {
            $("#winSelector,#bigView").hide();
        }
        e.stopPropagation();
    }
    $("#midimg").mouseover(mouseover); //中图事件
    $("#midimg,#winSelector").mousemove(mouseover).mouseout(mouseOut); //选择器事件

    var $divWidth = $("#winSelector").width(); //选择器宽度
    var $divHeight = $("#winSelector").height(); //选择器高度
    var $imgWidth = $("#midimg").width(); //中图宽度
    var $imgHeight = $("#midimg").height(); //中图高度
    var $viewImgWidth = $viewImgHeight = $height = null; //IE加载后才能得到 大图宽度 大图高度 大图视窗高度

    function changeViewImg() {
        $("#bigView img").attr("src", $("#midimg").attr("src").replace("mid", "big"));
    }
    changeViewImg();
    $("#bigView").scrollLeft(0).scrollTop(0);
    function fixedPosition(e) {
        if (e == null) {
            return;
        }
        var $imgLeft = $("#midimg").offset().left; //中图左边距
        var $imgTop = $("#midimg").offset().top; //中图上边距
        X = e.pageX - $imgLeft - $divWidth / 2; //selector顶点坐标 X
        Y = e.pageY - $imgTop - $divHeight / 2; //selector顶点坐标 Y
        X = X < 0 ? 0 : X;
        Y = Y < 0 ? 0 : Y;
        X = X + $divWidth > $imgWidth ? $imgWidth - $divWidth : X;
        Y = Y + $divHeight > $imgHeight ? $imgHeight - $divHeight : Y;

        if ($viewImgWidth == null) {
            $viewImgWidth = $("#bigView img").outerWidth();
            $viewImgHeight = $("#bigView img").height();
            if ($viewImgWidth < 200 || $viewImgHeight < 200) {
                $viewImgWidth = $viewImgHeight = 800;
            }
            $height = $divHeight * $viewImgHeight / $imgHeight;
            $("#bigView").width($divWidth * $viewImgWidth / $imgWidth);
            $("#bigView").height($height);
        }
        var scrollX = X * $viewImgWidth / $imgWidth;
        var scrollY = Y * $viewImgHeight / $imgHeight;
        $("#bigView img").css({ "left": scrollX * -1, "top": scrollY * -1 });
        $("#bigView").css({ "top": 220, "left": $(".preview-left").offset().left + $(".preview-left").width() + 15 });

        return { left: X, top: Y };
    }
});
</script>
</body>
</html>