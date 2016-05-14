<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/view/commons/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width" />
<link rel="shortcut icon" href="#" />
<meta name="description" content="啾唧网 方便快捷，物美价廉，更快送达，诚信服务" />
<meta name="keywords" content="啾唧首页" />
<title>啾唧-主页</title> 
<link rel="stylesheet" href="${ctxStatic}/css/main.css" type="text/css" />
<link rel="stylesheet" href="${ctxStatic}/css/homepage/base.css" type="text/css" />
<link rel="stylesheet" href="${ctxStatic}/css/head.css" type="text/css" />
<link rel="stylesheet" href="${ctxStatic}/css/homepage/carousel.css" />
<script src="${ctxStatic}/js/jquery-1.7.1.min.js" type="text/javascript"></script>  
<script src="${ctxStatic}/js/head.js" type="text/javascript"></script>
<script> 
var ctx = '${ctx}';
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
					<li><a href="#">个人中心</a></li>
					<li><a href="#">收藏夹</a></li>
					<li><a href="#">购物车</a></li>
					<li><a href="#">网站导航</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="tit-banner">
		<div class="tit-img">
			<img src="${ctxStatic}/images/homepage/tit1.jpg" width="1000px" height="80px" alt="" />
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
				<div id="search-hd" class="search-hd">
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
					<div class="cateMenu"> 
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
													    <a href="${ctx}/productCtrl/toProjectListPage.do?f_clsId=${sonTClass.FClsId}">${sonTClass.FClsName}</a>  
													</c:forEach>
											</dd> 
										</dl> 
									<!--<div class="fn-clear"></div> 
										<dl> 
											<dt>香型</dt> 
											<dd>
												<a href="">酱香型</a> <a href="">浓香型</a> <a href="">清香型</a> <a
													href="">凤香型</a> <a href="">馥郁香</a> <a href="">董香型</a> <a
													href="">特香型</a> <a href="">芝麻香</a> <a href="">兼香型</a> <a
													href="">金门香型</a> <a href="">老干白</a> <a href="">绵柔型</a> <a
													href="">柔和型</a> <a href="">小曲型</a> <a href="">生态竹香型</a>
											</dd> 
										</dl> 
										<div class="fn-clear"></div>   -->	
									</ul>  
								</div> 
							</li>
					    </c:forEach>   
						</ul> 
					</div>

				</div>

				<div class="navCon-menu fl"> 
					<ul>

						<li><a class="curMenu" href="#">首页</a></li>

						<li><a href="#">团购场</a></li>

						<li><a href="#">自营超市</a></li>

						<li><a href="#">名家鉴赏</a></li>

						<li><a href="#">闪购</a></li>

						<li><a href="#">VIP专场</a></li>

					</ul>

				</div> 
			</div>


			<div id="focus">
				<ul> 
					<c:forEach var="tCarousel" items="${tCarousels}">
					  <li><div class="img">
									 <img src="${ctxStatic}/images/${tCarousel.FAddress}" height="400" width="800" alt="" />
										 </div></li>
					</c:forEach> 
				</ul>
			</div>
		</div>
	</div>
	
	<div class="goods-img">
		<div class="arrival-con">
			<div class="tit-arrival">
				<a href="#"><img src="${ctxStatic}/images/homepage/u53.png" alt="" /></a>
			</div>
			<div class="arrival">
				<ul class="goods">
				<c:forEach var="tproduct" items="${tproducts}">
					<li><a href="${ctx}/productCtrl/toSingleProduct.do?f_ProductId=${tproduct.FProductId}" target="_blank"><img src="${ctxStatic}/images/${tproduct.FProductPic}"
							alt="${tproduct.FProductName}" /></a> <span class="txt">${tproduct.FPrice}RMB</span></li> 
			    </c:forEach>  
				</ul>
			</div>
			<div class="tit-arrival1">
				<a href="#">热门商品</a>
			</div>
			<div class="arrival">
				<ul class="goods"> 
				<c:forEach var="tproductHot" items="${tproductHots}">
					<li><a href="${ctx}/productCtrl/toSingleProduct.do?f_ProductId=${tproductHot.FProductId}" target="_blank"><img src="${ctxStatic}/images/${tproductHot.FProductPic}"
							alt="${tproductHot.FProductName}" /></a> <span class="txt">${tproductHot.FPrice}RMB</span></li>
			    </c:forEach>  
				</ul>
			</div>
			<div class="tit-arrival1">
				<a href="#">产品特卖</a>
			</div>
			<div class="arrival">
				<ul class="goods">
			  	 <c:forEach var="tproductSpecial" items="${tproductSpecials}">
					<li><a href="${ctx}/productCtrl/toSingleProduct.do?f_ProductId=${tproductSpecial.FProductId}" target="_blank"><img src="${ctxStatic}/images/${tproductSpecial.FProductPic}"
							alt="${tproductSpecial.FProductName}" /></a> <span class="txt">${tproductSpecial.FPrice}RMB</span></li>
			     </c:forEach> 
				</ul>
			</div>
		</div>
	</div>

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


	<script type="text/javascript">
		$(function() {
			var sWidth = $("#focus").width(); //获取焦点图的宽度（显示面积）
			var len = $("#focus ul li").length; //获取焦点图个数
			var index = 0;
			var picTimer;

			//以下代码添加数字按钮和按钮后的半透明条，还有上一页、下一页两个按钮
			var btn = "<div class='btnBg'></div><div class='btn'>";
			for (var i = 0; i < len; i++) {
				btn += "<span></span>";
			}
			btn += "</div><div class='preNext pre'></div><div class='preNext next'></div>";
			$("#focus").append(btn);
			$("#focus .btnBg").css("opacity", 0.5);

			//为小按钮添加鼠标滑入事件，以显示相应的内容
			$("#focus .btn span").css("opacity", 0.4).mouseover(function() {
				index = $("#focus .btn span").index(this);
				showPics(index);
			}).eq(0).trigger("mouseover");

			//上一页、下一页按钮透明度处理
			$("#focus .preNext").css("opacity", 0.2).hover(function() {
				$(this).stop(true, false).animate({
					"opacity" : "0.5"
				}, 300);
			}, function() {
				$(this).stop(true, false).animate({
					"opacity" : "0.2"
				}, 300);
			});

			//上一页按钮
			$("#focus .pre").click(function() {
				index -= 1;
				if (index == -1) {
					index = len - 1;
				}
				showPics(index);
			});

			//下一页按钮
			$("#focus .next").click(function() {
				index += 1;
				if (index == len) {
					index = 0;
				}
				showPics(index);
			});

			$("#focus ul").css("width", sWidth * (len));

			//鼠标滑上焦点图时停止自动播放，滑出时开始自动播放
			$("#focus").hover(function() {
				clearInterval(picTimer);
			}, function() {
				picTimer = setInterval(function() {
					showPics(index);
					index++;
					if (index == len) {
						index = 0;
					}
				}, 4000); //此4000代表自动播放的间隔，单位：毫秒
			}).trigger("mouseleave");

			//显示图片函数，根据接收的index值显示相应的内容
			function showPics(index) { //普通切换
				var nowLeft = -index * sWidth; //根据index值计算ul元素的left值
				$("#focus ul").stop(true, false).animate({
					"left" : nowLeft
				}, 300); //通过animate()调整ul元素滚动到计算出的position
				//$("#focus .btn span").removeClass("on").eq(index).addClass("on"); //为当前的按钮切换到选中的效果
				$("#focus .btn span").stop(true, false).animate({
					"opacity" : "0.4"
				}, 300).eq(index).stop(true, false).animate({
					"opacity" : "1"
				}, 300); //为当前的按钮切换到选中的效果
			}
		});
	</script>
</body>
<script src="${ctxStatic}/plugin/jquery/demo.js" type="text/javascript"></script>  
<script src="${ctxStatic}/plugin/jquery/jquery.autocomplete.js" type="text/javascript"></script>

</html>