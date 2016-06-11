package com.jiuji.cn.business.spcontact.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jiuji.cn.business.spcontact.model.SpContact;
import com.jiuji.cn.business.spcontact.service.SpContactService;

import javacommon.base.CommonController;

@Controller
@RequestMapping("/spContactController")
public class SpContactController extends CommonController{
	
	@Autowired
	SpContactService spContactService;
	
	@RequestMapping("/insert")
	@ResponseBody
	public String  insert(HttpServletRequest request){
		String F_ProductId = request.getParameter("F_ProductId");
		String CONTACT_NAME = request.getParameter("CONTACT_NAME");
		String CONTACT_TELE = request.getParameter("CONTACT_TELE");
		String CONTACT_ADDRESS = request.getParameter("CONTACT_ADDRESS"); 
		SpContact spContact = new SpContact(CONTACT_NAME,CONTACT_TELE,CONTACT_ADDRESS,Integer.parseInt(F_ProductId));
		spContactService.save(spContact);
		return "success"; 
	}
}
