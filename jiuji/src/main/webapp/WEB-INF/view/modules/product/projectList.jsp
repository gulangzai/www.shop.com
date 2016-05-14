<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/commons/taglib.jsp" %>   
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
<link rel="stylesheet" href="${ctxStatic}/css/top.css" type="text/css" /> 
<link rel="stylesheet" href="${ctxStatic}/css/goods.css" type="text/css" />
<link rel="stylesheet" href="${ctxStatic}/css/homepage/base.css" type="text/css" />
<link rel="stylesheet" href="${ctxStatic}/css/head.css" type="text/css" />


<link rel="stylesheet" href="${ctxStatic}/css/product/productList.css" type="text/css" />
<script src="${ctxStatic}/js/jquery_1.8.3.min.js" type="text/javascript"></script>  
<script type="text/javascript" src="${ctxStatic}/js/responsive.tabs.js"></script>
<script src="${ctxStatic}/js/head.js" type="text/javascript"></script>
<script type="text/javascript">
var ctx ='${ctx}';
$(document).ready(function(){ 
	$('.tabs').respTabs({
		responsive : false
	});
	
});
     $(function(){
         //通用头部搜索切换
         $('#search-hd .search-input').on('input propertychange',function(){
             var val = $(this).val();
             if(val.length > 0){
                 $('#search-hd .pholder').hide(0);
             }else{
                 var index = $('#search-bd li.selected').index();
                 $('#search-hd .pholder').eq(index).show().siblings('.pholder').hide(0);
             }
         })  
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
					<li><a href="#">个人中心</a></li>
					<li><a href="#">收藏夹</a></li>
					<li><a href="#">购物车</a></li>
					<li><a href="#">网站导航</a></li>
				</ul>
			</div>
		</div>
	</div> 
	
	  <div class="search">
		<div class="search-con" style="height:100px;">
			<div class="logo">
               <a href="${ctx}/homePageCtrl/toHomePage.do"><img src="${ctxStatic}/images/homepage/logo2.png" width="145" alt="" /></a>			</div>
			<div class="search-form">
				<div id="search-bd" class="search-bd">
					<ul>
						<li class="selected">找商品</li>
						<li>找商家</li>
					</ul>
				</div>
				<div id="search-hd" class="search-hd">
					<div class="search-bg"></div>
					<input type="text" id="autocomplete-ajax" class="search-input"> 
					<input type="text" id="s2" class="search-input"> 
					<span class="s1 pholder">生鲜水果半价抢疯</span> 
						<span class="s2 pholder" style="display:none">搜商家名称</span>
					<button id="submit" class="btn-search" value="搜索">搜索</button>
				</div> 
				 <div id="selction-ajax"></div> 
			</div>
		</div>
	</div>
	 
   <div align="center">
            <div id="goodAll">  
               <c:forEach items="${projectPictureResults}" var="projectPictureResult">
                <div id="good">
                <div id="singleGood" onmouseover="onMouseOver(this)" onmouseout="onMouseOut(this)">
                 <div style="display:none" id="${projectPictureResult.tproduct.FProductId}"></div> 
                          <a href="${ctx}/productCtrl/toSingleProduct.do?f_ProductId=${projectPictureResult.tproduct.FProductId}">
                          <img id="img" src="${ctxStatic}/images/product/mid/${projectPictureResult.tPictures[0].fMidPic}"></a>  
                          <div id="money">¥:${projectPictureResult.tproduct.FPrice}</div>  
                          <div id="商品名">${projectPictureResult.tproduct.FProductName}</div> 
                          <div id="action" style="height: 25px;"> 
                          <button id="fenxiang"  onclick="window.open('http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url='+encodeURIComponent('${ctx}/productCtrl.toSingleProduct?f_ProductId=${projectPictureResult.tproduct.FProductId}')+
                          '&title=校园网购平台,一切为了学生服务! &desc=我在校园通上卖${projectPictureResult.tproduct.FProductName}, ${projectPictureResult.tproduct.FProductDesc} ,有人想要吗&pics='+encodeURIComponent('${ctxStatic}/images/product/mid/${projectPictureResult.tPictures[0].fMidPic}')+'&style=203&width=98&height=22')">
                                                                     分享</button>                                        
                          <button id="zhan" onclick="zan(this)">赞</button>
                           </div>
                  </div>
                  </div>
               </c:forEach> 
            </div> 
            <form action="${ctx}/productCtrl.toSingleProduct.do">
            <input type="hidden" name="goodType" value=""/>
            <div>
<%--                 <span><a href="${ctx}/web/querySingleGoodBase.goodBaseCtrl?goodType=${goodType}&currentPage=${pageBean.currentPage-1}"> 上一页</a></span>
                <span><a href="${ctx}/web/querySingleGoodBase.goodBaseCtrl?goodType=${goodType}&currentPage=${pageBean.currentPage+1}">下一页</a></span>
                <span>共 ${pageBean.counts} 页,到<input type="text" name="currentPage" size="3">页</span><input  type="button" value="确定" onclick="submit()"/>   --%>   
            </div>
            </form> 
    </div> 
  
  
  <!-- ------------------------------------------------------------------------------------ -->
  	<div class="footer">
		<div class="footer-content">
			<div class="cooperation">
				<ul>
					<li><a href="#">商家入驻</a></li>
					<li><a href="#">销售联盟</a></li>
					<li><a href="#">关于我们</a></li>
					<li><a href="#">商品专题</a></li>
					<li><a href="#">热门搜索</a></li>
					<li><a href="#">服务热线</a></li>
				</ul>
			</div>
			<div class="copy">&copy;2000-2016&nbsp;&nbsp;啾唧在线电子商务有限公司&nbsp;&nbsp;&nbsp;版权所有 皖ICP备16007801号</div>
		</div>
	</div>
	
  
  </body>
  
  <script src="${ctxStatic}/js/header.js" type="text/javascript"></script>
<script type="text/javascript">
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

</html>
