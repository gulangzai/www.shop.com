package com.ccthanking.common.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.coreapp.orgmanage.UserManager;

@Repository
public class EncodingInterceptor extends HandlerInterceptorAdapter {

	private static Logger logger = LoggerFactory.getLogger(EncodingInterceptor.class.getName());

	public static String getRequestPath(HttpServletRequest request) {
		String requestPath = request.getRequestURI() + "?" + request.getQueryString();
		if (requestPath.indexOf("&") > -1) {// 去掉其他参数
			requestPath = requestPath.substring(0, requestPath.indexOf("&"));
		}
		requestPath = requestPath.substring(request.getContextPath().length());// 去掉项目路径
		return requestPath;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {

		logger.debug("Interceptor url ={}", request.getRequestURI());
		Object userId = request.getSession().getAttribute("userId");
		if (userId != null) {
			try {
				User user = UserManager.getInstance().getUserByLoginNameFromNc((String) userId);
				RestContext.setCurrentUserInThread(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return super.preHandle(request, response, object);

	}

}
