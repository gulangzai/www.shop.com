package com.jiuji.cn.utils.log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint; 
 
public class LoginLog {
	 
	public void beforeLogin(JoinPoint jp){
		//Liyi.p("-----���˿�ʼ��½");
	}
	 
	public void afterLogin(JoinPoint jp){
		//Liyi.p("-----���˿�ʼ�ǳ�");
	}
}
