package com.jiuji.cn.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import javacommon.base.ResultAction;

public interface UserService {

	String login(String username, String password, String checkboxmark, Model model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session);

	ResultAction register(String mobile, String password, String checkCode);

}
