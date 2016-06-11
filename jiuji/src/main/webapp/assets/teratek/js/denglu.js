/**
 * Created by Administrator on 2016/5/3.
 */

$(document).ready(function(){

    //var wHeight = $(window).height();
    //console.log(wHeight);
    //$("#bjt").height(wHeight*0.7);
    //console.log($("#bjt").height());
    ////$(window).resize(function(){
    //    console.log(wHeight);
    //});
    $("#username").focus(function(){
        if($(this).val()=="请输入用户名"){
            $(this).val("");
        }
    }).blur(function(){
        if(this.value==''){
            this.value="请输入用户名";
        }
    });
    $("#pwd").focus(function(){
        if(this.value=="请输入用户密码"){
            this.value="";
            $("#pwd").attr("type","password");
        }
    }).blur(function(){
       if(this.value=="") {
           $("#pwd").attr("type", "text");
           this.value = "请输入用户密码";
       }
    });
    $("#android").mouseover(function(){
        console.log(22);
        $("#android_code").css("visibility","visible");
    }).mouseout(function(){
        $("#android_code").css("visibility","hidden");
    })
});