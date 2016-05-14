/*
 * PageController.java  v1.00  2014-4-26
 * Peoject	wndjssg
 * Copyright (c) 2014 Wisdragon
 *
 * Filename	:	PageController.java  v1.00 2014-4-26
 * Project	: 	wndjssg
 * Copyight	:	Copyright (c) 2014 Wisdragon
 */
package com.ccthanking.business.commons;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ccthanking.framework.Constants;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.coreapp.orgmanage.UserManager;

/**
 * 页面跳转.<br>
 * 
 * 屏蔽Url 统一以管理.
 * 
 * @author <a href="mailto:genliang.jiang@wisdragon.com">蒋根亮</a>
 * @version v1.00
 * @since 1.00 2014-4-26
 * 
 */
@Controller
public class PageController {

	
	//需权限的页面
	@RequestMapping(value = "/page/{bse}/{pkg}/{filename:[\\w.\\-]+}")
	public String pageview_auth(HttpServletRequest request, @PathVariable String bse, @PathVariable String pkg,
			@PathVariable String filename) {

		String fileName = "index";
		Object userid = request.getSession().getAttribute("userId");
		if (userid != null) {
			try {
				User user = UserManager.getInstance().getUserByLoginNameFromNc((String) userid);
				RestContext.setCurrentUserInThread(user);
				
				fileName = "jsp/" + bse + File.separator + pkg + File.separator + filename;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return fileName;
	}
	
	//需权限的页面
	@RequestMapping(value = "/page/{bse}/{pkg}/{spkg}/{filename:[\\w.\\-]+}")
	public String pageview_auth1(HttpServletRequest request, @PathVariable String bse, @PathVariable String pkg,@PathVariable String spkg,
			@PathVariable String filename) {
		
		String fileName = "index";
		Object userid = request.getSession().getAttribute("userId");
		if (userid != null) {
			try {
				User user = UserManager.getInstance().getUserByLoginNameFromNc((String) userid);
				RestContext.setCurrentUserInThread(user);
				
				fileName = "jsp/" + bse + File.separator + pkg + File.separator + spkg+File.separator+filename;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return fileName;
	}

	//无需权限的页面
	@RequestMapping(value = "/pagena/{bse}/{pkg}/{filename:[\\w.\\-]+}")
	public String pageview_guest(HttpServletRequest request, @PathVariable String bse, @PathVariable String pkg,
			@PathVariable String filename) {

		Object userid = request.getSession().getAttribute("userId");
		if (userid == null) {
			request.getSession().setAttribute("userId", Constants.GUEST_ID);
		}
		String fileName = "jsp/" + bse + File.separator + pkg + File.separator + filename;

		return fileName;
	}
	
	
	
	/**
	 * 工地子系统跳转使用
	 * */
	@RequestMapping(value = "/pageproject/{bse}/{pkg}/{filename:[\\w.\\-]+}")
	public String pageview_auth_project(HttpServletRequest request,ModelMap modeDriven, @PathVariable String bse, @PathVariable String pkg,
			@PathVariable String filename, @RequestParam(value = "project_uid") String project_uid , @RequestParam(value = "projectUserId") String projectUserId 
			,@RequestParam(value = "root_uid") String root_uid ) {
		String fileName = "index";
		Object userid = request.getSession().getAttribute("userId"); 
		if (userid != null) {
			try {
				User user = UserManager.getInstance().getUserByLoginNameFromNc((String) userid);
				RestContext.setCurrentUserInThread(user);
				modeDriven.addAttribute("project_uid", project_uid);
				modeDriven.addAttribute("projectUserId", projectUserId);
				modeDriven.addAttribute("root_uid", root_uid);
				
				fileName = "jsp/" + bse + File.separator + pkg + File.separator + filename;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return fileName;
	}
	
	@RequestMapping(value = "/openpage/{bse}/{pkg}/{filename:[\\w.\\-]+}")
	public String openpage_auth_project(HttpServletRequest request,ModelMap modeDriven, @PathVariable String bse, @PathVariable String pkg,
			@PathVariable String filename, @RequestParam(value = "PID") String project_uid, @RequestParam(value = "TYPE") String typeId  ) {
		String fileName = "index";
		Object userid = request.getSession().getAttribute("userId"); 
		if (userid != null) {
			try {
				User user = UserManager.getInstance().getUserByLoginNameFromNc((String) userid);
				RestContext.setCurrentUserInThread(user);
				modeDriven.addAttribute("project_uid", project_uid);
				modeDriven.addAttribute("typeId", typeId);
				
				fileName = "jsp/" + bse + File.separator + pkg + File.separator + filename;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return fileName;
	}

	

}
