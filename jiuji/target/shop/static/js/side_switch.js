// JavaScript Document
$(function(){
	var ali = $('.side_list');
	
	var aDiv = $('.side_wrap');
	
	ali.click(function(){
	     $(this).addClass('currents').siblings().removeClass('currents');
	    	aDiv.eq($(this).index()).show().siblings().hide(); 
	});
	
		
});