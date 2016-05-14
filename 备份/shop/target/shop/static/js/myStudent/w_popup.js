$(function(){
	var oBtn = $('#daolu');
	var popWindow = $('.dltwo');
	
	var oBtn2 = $('#lulu');
	var popWindow2 = $('.lu');
	
	var oBtn3 = $('.yhj');
	var popWindow3 = $('.youhui');

	var oClose = $('.close,.ok');
	
	var inputStudents = $('.inputStudents');

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
		popWindow.show().animate({
					'left':positionLeft+'px',
					'top':positionTop+'px'
		},500);

		$('body').append(oMask);
		$('.mask').width(maskWidth).height(maskHeight);

	});
	
	
	oBtn2.click(function(){
		popWindow2.show().animate({
					'left':positionLeft+'px',
					'top':positionTop+'px'
		},500);

		$('body').append(oMask);
		$('.mask').width(maskWidth).height(maskHeight);

	});
	
	
	/*oBtn3.click(function(){  
		alert('---');
		var studentId = $(this).children("input").val(); 
		popWindow3.children("input").val(studentId);
		popWindow3.show().animate({
					'left':positionLeft+'px',
					'top':positionTop+'px'
		},500);

		$('body').append(oMask);
		$('.mask').width(maskWidth).height(maskHeight); 

	});*/
	 
	//录入学员
	inputStudents.click(function(){
		var studentName = $("#inputStudentName").val();
		var mobile = $("#inputMobile").val();
		var applyIntention = $("input[name='applyIntention']:checked").val();
        var applyClasses = $("input[name='applyClasses']:checked").val();
        var intentionRamark =$("input[name='intentionRamark']").val();
        var intention = $("input[name='intention']").val();
       /* alert("studentName:"+studentName+
    			"mobile:"+mobile+
    			"applyIntention:"+applyIntention+
    			"applyClasses:"+applyClasses+
    			"intentionRamark:"+intentionRamark+
    			"intention:"+intention);*/
        $.ajax({
    		type:'post',
    		url:ctx+'/myStudent/inputStudent.do',
    		async:false,
    		data:{ 
    			studentName:studentName,
    			mobile:mobile,
    			applyIntention:applyIntention,
    			applyClasses:applyClasses,
    			intentionRamark:intentionRamark,
    			intention:intention
    		},
    		success:function(sR){
    			var dd=sR.message; 
    			if(dd=='SUCCESS'){ 
    				luClose();
    				alert('录入学生成功');
    				return;
    			}else if(dd=='ERROR'){
    				alert('录入学生出错');
    			}else if(dd=='YETEXIST'){
    				alert('已存在该用户无需录入');
    			}
    		}
    	}); 
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

	function luClose(){
		popWindow2.hide();
		$('.mask').remove();
	}

	oClose.click(function(){
		popWindow.hide();
		$('.mask').remove();
	});
	
	
	oClose.click(function(){
		popWindow2.hide();
		$('.mask').remove();
	});
	
	
	oClose.click(function(){
		popWindow3.hide();
		$('.mask').remove();
	});
});