package com.jiuji.cn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javacommon.base.CommonController;

@Controller
@RequestMapping("/homemainCtrl")
public class HomemainCtrl extends CommonController{
	
	@RequestMapping("/login")
	public String login(){ 
		String sssssss="";
		return "/modules/homepage/login";
	}
	@RequestMapping("/toMain")
	public String toMain(){
		return "/modules/homemain/main";
	}  
}
