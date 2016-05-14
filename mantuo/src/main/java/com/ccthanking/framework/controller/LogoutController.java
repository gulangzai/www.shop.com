/*
 * LogoutController.java  v1.00  2014-8-22
 * Peoject	wndjsjl
 * Copyright (c) 2014 Wisdragon
 *
 * Filename	:	LogoutController.java  v1.00 2014-8-22
 * Project	: 	wndjsjl
 * Copyight	:	Copyright (c) 2014 Wisdragon
 */
package com.ccthanking.framework.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.coreapp.orgmanage.OperatePermManager;

/**
 * 退出.
 * 
 * @author <a href="mailto:genliang.jiang@wisdragon.com">蒋根亮</a>
 * @version v1.00
 * @since 1.00 2014-8-22
 * 
 */
@Controller
public class LogoutController {
	
	
	/**
	 * @param 用户注销
	 * @return org.springframework.web.servlet.ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	protected ModelAndView logout(final HttpServletRequest request, final ModelAndView mav) throws Exception {
		User user = RestContext.getCurrentUser();
		if (user != null) {
			com.ccthanking.framework.log.LogManager.writeLogoutLog(user);
			//去掉缓存中该用户的操作按钮权限数据 20140819
			OperatePermManager.getInstance().removeSetOperatePerm(user);
			RestContext.clearContext();
		}
		request.getSession().invalidate();
		mav.setViewName("index");
		return mav;

	}

}
