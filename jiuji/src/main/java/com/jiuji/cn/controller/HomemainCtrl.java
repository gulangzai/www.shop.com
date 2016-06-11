package com.jiuji.cn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javacommon.base.CommonController;

@Controller
@RequestMapping("/homemainCtrl")
public class HomemainCtrl extends CommonController{
	

	@RequestMapping("/toMain")
	public String toMain(){
		return "/modules/homemain/main";
	}  
}
