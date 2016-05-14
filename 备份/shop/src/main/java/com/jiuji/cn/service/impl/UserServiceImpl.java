package com.jiuji.cn.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.jiuji.cn.dao.TSendMessageMapper;
import com.jiuji.cn.dao.UserMapper;
import com.jiuji.cn.exception.MsmException;
import com.jiuji.cn.model.TSendMessage;
import com.jiuji.cn.model.TUser;
import com.jiuji.cn.service.UserService;

import javacommon.base.ResultAction;
import javacommon.util.IDGenertor;
import javacommon.util.MD5Utils;
import javacommon.util.SendMsg_webchinese;
import javacommon.util.StringUtil;

@Service
public class UserServiceImpl implements UserService {
	
	private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	UserMapper userMapper;
	@Autowired
	TSendMessageMapper tsendMessageMapper;
	
	@Override
	public String login(String username,String password,String checkboxmark,Model model,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		System.out.println("userName:"+username+"password:"+password+"checkboxmark:"+checkboxmark);
		//String fromUrl = (String)request.getParameter("fromUrl");
		try {
			if(username ==null||username.equals("") || password== null||password.equals("")){
				model.addAttribute("message","用户名和密码不能为空!");
				return "/modules/homepage/login";
			}
			String newpassword = MD5Utils.createMD5(password);
			TUser t_user = new TUser();
			t_user.setFUserName(username);
			t_user.setFPassword(newpassword);
			//判断是否是手机号
			 Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
			 Matcher m = p.matcher(username);  
			 if(m.matches()){
				 t_user.setFUserName(null);
				 t_user.setFMobile(username);
				 t_user.setFPassword(newpassword);
			 }  
			 //判断是否是邮箱
			 p = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
			 m=p.matcher(username);
			 if(m.matches()){
				 t_user.setFUserName(null);
				 t_user.setFEmail(username);
				 t_user.setFPassword(newpassword);
			 }
			t_user = userMapper.selectOnlyone(t_user);  
			javacommon.util.CookieUtil cookieUtil = new javacommon.util.CookieUtil();
			if(t_user != null&&t_user.getFPassword().equals(newpassword)){
				session.setAttribute("t_user", t_user);
				session.setAttribute("loginname", t_user); 
				t_user.setFPassword(newpassword); 
				if(checkboxmark=="1"||checkboxmark.equals("1")){
					cookieUtil.addCookie(response, "userName",username, 7*24*60*60); 
					cookieUtil.addCookie(response,"password",password,7*24*60*60);
					cookieUtil.addCookie(response,"checkboxmark",checkboxmark,7*24*60*60); 
				} else{
					cookieUtil.delCookie(request, "userName", response);
					cookieUtil.delCookie(request, "password", response);
					cookieUtil.delCookie(request, "checkboxmark", response);
				}
				/* SessionListener.replaceUserSession(session, teacher.getUserId());
			    teacher.setLastLoginTime(teacher.getCurrentLoginTime());
				teacher.setCurrentLoginTime(DateUtils.getTodayTime());
				studentInfoService.updateByPrimaryKeySelective(teacher);*/
			    logger.info("-------登录成功");
				return "redirect:/homePageCtrl/toHomePage.do"; 	 
			}else{
				model.addAttribute("message","用户名和密码不匹配!");
				return "/modules/homepage/login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return "/modules/homepage/login"; 
	}

	/**
	 * 注册接口
	 */
	@Override
	public ResultAction register(String mobile, String password,String checkCode) {
		// TODO Auto-generated method stub
		System.out.println(mobile+"---"+password);
		ResultAction ra = new ResultAction();
		try{ 
			password = MD5Utils.createMD5(password);	
			TUser tuser = new TUser();  
			tuser.setFMobile(mobile);
			List<TUser> tusers = userMapper.select(tuser);
			if(tusers!=null&&tusers.size()>0){
				ra.setMessage("EXIST");
			}else{
				TSendMessage tsendMessage = new TSendMessage(mobile,checkCode);
				tsendMessage = tsendMessageMapper.selectOnlyone(tsendMessage);
				if(tsendMessage!=null){
					tuser = new TUser(mobile,password);
					tuser.setFUserId(StringUtil.getKey());
					tuser.setFDeleted(0);
					tuser.setFPassword(password);
					System.out.println(tuser);
					userMapper.insert(tuser);
					ra.setMessage("SUCCESS");
				}else{
					ra.setMessage("ERRORCODE");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			ra.setMessage("FAIL");
		}
	     return ra; 
	}
	
	public ResultAction testCode(String mobile){
		ResultAction ra = new ResultAction();
		String checkcode = IDGenertor.randomCode(); 
		TSendMessage tsendMessage = new TSendMessage();
		tsendMessage.setFMobile(mobile); 
		try {
			List<TSendMessage> tsendMessages = tsendMessageMapper.select(tsendMessage);
			tsendMessage.setFCheckcode(checkcode);
			if(tsendMessages.size()>=1){
				tsendMessageMapper.update(tsendMessage);
			}else{ 
				tsendMessageMapper.insert(tsendMessage);
			}
			SendMsg_webchinese.sendMessage(mobile,checkcode); 
			ra.setMessage("SEND");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ra.setMessage("SENDERROR");
		} catch (MsmException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			ra.setMessage("SENDERROR");
		} 
		return ra;
	}
}
