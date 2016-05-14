package com.xue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/homeMain")
public class HomeMainController {
	

	@RequestMapping("/toMain")
	public String main(){
		return "/modules/homemain/main";
	}
	
	@RequestMapping("/toTop")
	public String top(){
		return "/modules/homemain/top";
	}
	
	@RequestMapping("/toLeft")
	public String left(){
		return "/modules/homemain/left";
	}
	
	@RequestMapping("/toRight")
	public String right(){
		return "/modules/homemain/right";
	}
	
	@RequestMapping("/toBottom")
	public String bottom(){
		return "/modules/homemain/bottom";
	}
	
}
