package com.jiuji.cn.service;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

public interface HomePageService {

	void queryInfomation(Model model, HttpSession session);

}
