package com.jiuji.cn.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiuji.cn.service.UserService;

import javacommon.base.ResultAction;
import javacommon.base.Suggestion;
import javacommon.base.Vo;

@Controller
@RequestMapping("/userCtrl")
public class UserCtrl {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/login") 
	public String login(String username,String password,String checkboxmark,Model model,HttpServletRequest request,HttpServletResponse response,HttpSession session){  
		String message =  userService.login(username,password,checkboxmark,model,request,response,session);  
		return message;
	}
	
	@RequestMapping("/register")
	@ResponseBody
	public ResultAction register(String mobile,String password,String checkCode){
		ResultAction ra = userService.register(mobile,password,checkCode);
		return ra;
	}
	@RequestMapping("/testCode")
	@ResponseBody
    public Vo testCode(){ 
		 Vo ra = new Vo(); 
		List suggestions = new ArrayList();
		//Map suggestions = new HashMap();
		Suggestion sug1= new Suggestion("好好学习1",null);
		Suggestion sug2= new Suggestion("好好学习2",null);
		suggestions.add(sug1);
		suggestions.add(sug2);
		ra.setSuggestions(suggestions);
		return ra;
	}
	 
	
	@RequestMapping("/toLwj") 
    public String toLwj(){ 
		return "/modules/person/lwj";
	}
}
