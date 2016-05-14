// JavaScript Document
$(function(){
	var Atime = $('#dayTime img');
	var Bsubmit = $('#submit');
	var Cbutton = $('#buttonone');
	
	
	 Atime.click(function(){
        
	   Bsubmit.show().prev().hide();
	 
	 });
	 Cbutton.click(function(){
        
	   Bsubmit.hide().prev().show();
	 
	 });
	
});
$(function(){
	var Atime1 = $('#totalDay img');
	var Bsubmit1 = $('#submit1');
	var Cbutton1 = $('#buttons');
    Atime1.click(function(){
    	Bsubmit1.show().prev().hide();
    });
	Cbutton1.click(function(){
	    Bsubmit1.hide().prev().show();
	});
	
});