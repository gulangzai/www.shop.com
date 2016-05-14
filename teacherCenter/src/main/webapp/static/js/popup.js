$(function(){
	var oBtn = $('#shows');
	//开通
	var open_class_action = $(".open_class_action");
	
	var popWindow = $('.prompt_wrap');
	var oClose = $('#aa');
    var cClose = $('#cc');
	var c1Close = $('#cc1');
	var c2Close = $('#cc2');
	//浏览器可视区域的宽度
	var browserWidth = $(window).width();

	//浏览器可视区域的高度
	var browserHeight = $(window).height();

	//浏览器纵向滚动条距离上边界的值
	var browserScrollTop = $(window).scrollTop();

	//浏览器横向滚动条距离左边界的值
	var browserScrollLeft = $(window).scrollLeft();
	
	
	
	//弹出窗口的宽度
	var popWindowWidth = popWindow.outerWidth(true);
	//弹出窗口的高度
	var popWindowHeight = popWindow.outerHeight(true);

	//left的值＝浏览器可视区域的宽度／2－弹出窗口的宽度／2+浏览器横向滚动条距离左边界的值
	var positionLeft = browserWidth/2 - popWindowWidth/2+browserScrollLeft;

	//top的值＝浏览器可视区域的高度／2－弹出窗口的高度／2+浏览器纵向滚动条距离上边界的值
	var positionTop = browserHeight/2 - popWindowHeight/2+browserScrollTop;


	var oMask = '<div class="mask"></div>'
	//遮照层的宽度
	var maskWidth = $(document).width();

	//遮照层的高度
	var maskHeight = $(document).height();

	oBtn.click(function(){
		//shop集合
		//shops;
		//choiceShops
		popWindow.show().animate({
					'left':positionLeft+'px',
					'top':positionTop+'px'
		},500);
		var achecks = $('input[type=checkbox]:checked');
		$('.conter_bag').empty(); 
		/*for(var i=0;i<achecks.length;i++){
			var shopName = $($(achecks[i]).parent().nextAll()[5]).text();
			var shopId = $(achecks[i]).val(); 
			var majorName = $($(achecks[i]).parent().nextAll()[1]).text();
			var majorId = $($($(achecks[i]).parent().nextAll()[6]).children()[0]).val();
			console.info(majorId);
			if(shopName!=''){ 
				$('#conter_bag').append('<li class="title_list"><input type="hidden" name="choseMajorId" form="register" value="'+majorId+'"/>'+
						'<input type="hidden" name="choseMajorName" form="register" value="'+majorName+'"/><input type="checkbox" name="choseShopId" form="register" value="'+shopId+'"/>'+shopName+'</li>'); 	
			}
		} */ 
		
		for(var i=0;i<choiceShops.length;i++){
			var shop = choiceShops[i];
			if(shop.shopName!=''){ 
				$('#conter_bag').append('<li class="title_list"><input type="hidden" name="choseMajorId" form="register" value="'+shop.majorId+'"/>'+
						'<input type="hidden" name="choseMajorName" form="register" value="'+shop.majorName+'"/><input type="checkbox" name="choseShopId" form="register" value="'+shop.shopId+'"/>'+shop.shopName+'</li>'); 	
			}
		}
		 
		$('body').append(oMask);
		$('.mask').width(maskWidth).height(maskHeight);

	});
	
	//监听开通课程
	open_class_action.click(function(){
		var tel = $("input[name='tel']").val();
		var shopIds = $("input[name='choseShopId']:checked"); 
		var majorId=  $("input[name='choseMajorId']").val(); 
		var majorName=  $("input[name='choseMajorName']").val(); 		
		var shopIDs='';
	    for(var i =0;i<shopIds.length;i++){ 
	    	var shopId = $(shopIds[i]).val();
	    	shopIDs=shopIDs+","+shopId;
	    }
	    shopIDs=shopIDs.substring(1,shopIDs.length);
	    console.info(shopIDs);
	    if(shopIDs==''){
	    	alert('请选择课程');
	    	return;
	    }
	    if(tel==''){
		   alert('请填写手机号');
		   return;
	    }
	    $.ajax({
    		type:'post',
    		url:ctx+'/courseOpen/openCourse.do',
    		async:false,
    		data:{  
    			mobile:tel,
    			shopIds:shopIDs,
    			majorId:majorId,
    			majorName:majorName
    		},  
    		success:function(sR){ 
    			if(sR.message=='NOCHOISE'){
    				alert("请选择课程"); 
    			} else if(sR.message=='OPENSUCCESS'){ 
    				oClose.click();
    				var cbr = $("#conter_bag_success_result");
    				var courseOpenResult = sR.data;
    				cbr.empty().append('<li class="title_list" style="color:#F8FE60;">学员账号  :'+sR.data.studentInfo.studentName+'</li>');
    				for(var i=0;i<sR.data.shops.length;i++){
    					cbr.append('<li class="title_list" style="color:#F8FE60;margin-top:10px;">学年'+sR.data.shops[i].shopTime+'•'+sR.data.shops[i].majorName+'•'+sR.data.shops[i].subjectName+'•'+sR.data.shops[i].classesName+'</li>');
    				} 
    				$(".open_success").show();
    			}else if(sR.message=='NOSTUDENT'){
    				alert('没有该学员');
    			}else if(sR.iserror){
    				alert('系统错误');
    			}else if(sR.message=='ERROR'){
    				oClose.click();
    				var cbr = $("#conter_bag_fail_result");
    				var courseOpenResult = sR.data;
    				cbr.empty().append('<li class="title_list" style=" color:#F8FE60;">学员账号  :'+sR.data.studentInfo.studentName+'</li>');
    				cbr.append('<h3 class="headline">以下课程已开通</h3>');
    				for(var i=0;i<sR.data.shops.length;i++){
    					cbr.append('<li class="title_list" style=" color:#F8FE60;margin-top:10px;">学年'+sR.data.shops[i].shopTime+'•'+sR.data.shops[i].majorName+'•'+sR.data.shops[i].subjectName+'•'+sR.data.shops[i].classesName+'</li>');
    				} 
    				$(".open_fail").show();
    			}
    		}
    	});
		//alert(tel+shopId[0]+'--'+shopId[1]);
	});
	
	


	$(window).resize(function(){	
		if(popWindow.is(':visible')){
			browserWidth = $(window).width();
			browserHeight = $(window).height();
			positionLeft = browserWidth/2 - popWindowWidth/2+browserScrollLeft;
			positionTop = browserHeight/2 - popWindowHeight/2+browserScrollTop;

			popWindow.animate({
						'left':positionLeft+'px',
						'top':positionTop+'px'
			},500);		
		}
	});

	$(window).scroll(function(){
		if(popWindow.is(':visible')){
			browserScrollTop = $(window).scrollTop();
			browserScrollLeft = $(window).scrollLeft();
			positionLeft = browserWidth/2 - popWindowWidth/2+browserScrollLeft;
			positionTop = browserHeight/2 - popWindowHeight/2+browserScrollTop;
			popWindow.animate({
						'left':positionLeft+'px',
						'top':positionTop+'px'
			},500).dequeue();
		}
				
	});

	

	oClose.click(function(){
		popWindow.hide();
		$('.mask').remove();
	});
	cClose.click(function(){
		popWindow.hide();
		$('.mask').remove();
	});
	c1Close.click(function(){
		popWindow.hide();
		$('.mask').remove();
	});
	c2Close.click(function(){
		popWindow.hide();
		$('.mask').remove();
	});
});