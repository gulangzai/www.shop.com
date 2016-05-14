package javacommon.base;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javacommon.util.CipherUtil;
import javacommon.util.CookieUtil;
import javacommon.util.PropertiesContent;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.xue.model.BsUserInfo;
import com.xue.model.StudentInfo;
import com.xue.model.User;
public class CommonController {
	protected static final String chars = "vwxBtuFpqEJKLM6eGZPH3AdU7NYDmnQ2rsfghRS8VCjkT5bcW49Xayz";
	protected static final int WIDTH = 150;
	protected static final int HEIGHT = 50;
	protected static final String ERROR_MSG_KEY = "errorMsg";
	public static final String Operate_Add = "建立计划";
	public static final String Operate_Modify = "修改每天学习时长";
	public static final String Operate_ModifyPre = "修改学习天数";
	public static final String LOGIN_YES ="LOGIN_YES";
	public static final String LOGIN_FAIL ="LOGIN_FAIL";
	
	public static final String COMMON_ERROR_JSP ="/errorpage/500";
	
	CookieUtil tmpcookieutil = new CookieUtil();
	//public HttpServletResponse response;
	/** yyyy-MM-dd HH:mm:ss 日期时间 */
	public static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	/**yyyy-MM-dd 日期*/
	public static final SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	public Logger logger = LoggerFactory.getLogger(this.getClass());
	/** 获取当前系统操作人信息
	 * @param response 
	 * @param request2 */
	public BsUserInfo getCurrentStudentInfo(HttpSession session) {
		return   (BsUserInfo) session.getAttribute("teacher");
	}
	/** 获取当前系统操作人岗位ID */
	public String getPositionId(HttpSession session){
		return getUserMap(session).get("position_id");
	}
	@SuppressWarnings("unchecked")
	public Map<String, String> getUserMap(HttpSession session) {
		Map<String, String> userMap = new HashMap<String, String>();
		String cookieVal=(String) session.getAttribute(PropertiesContent
				.get("cookie_field_key"));
		if (StringUtils.isNotEmpty(cookieVal)) {
			cookieVal = CipherUtil.decryptData(cookieVal);
			userMap = (Map<String, String>) JSON.parse(cookieVal);
		}
		return userMap;
	}
	/**
	 * 绘制背景
	 * 
	 * @param g
	 */
	protected void drawBackground(Graphics g) {
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		Random random = new Random();
		int len = 0;
		while (len <= 5) {
			len = random.nextInt(15);
		}
		for (int i = 0; i < len; i++) {
			int x = (int) (random.nextInt(WIDTH));
			int y = (int) (random.nextInt(HEIGHT));
			int red = (int) (255 - random.nextInt(200));
			int green = (int) (255 - random.nextInt(200));
			int blue = (int) (255 - random.nextInt(200));
			g.setColor(new Color(red, green, blue));
			// g.drawLine(x, y, random.nextInt(WIDTH)-x,
			// random.nextInt(HEIGHT)-y);
			g.drawArc(x, y, random.nextInt(WIDTH) - x, random.nextInt(HEIGHT)
					- y, random.nextInt(WIDTH) - x, random.nextInt(HEIGHT) - y);
			g.drawOval(x, y, 2, 2);
		}
	}
	/**
	 * 绘制验证码
	 * 
	 * @param g
	 * @param rands
	 */
	protected void drawRands(Graphics g, char[] rands) {
		Random random = new Random();
		g.setFont(new Font("黑体", Font.ITALIC | Font.BOLD, 45));
		for (int i = 0; i < rands.length; i++) {
			int red = (int) (random.nextInt(255));
			int green = (int) (random.nextInt(255));
			int blue = (int) (random.nextInt(255));
			g.setColor(new Color(red, green, blue));
			g.drawString("" + rands[i], i * (random.nextInt(4) * 5 + 25), 40);
		}
	}
	/**
	 * 产生随机数
	 * 
	 * @return
	 */
	protected char[] getCode(int length) {
		char[] rands = new char[length];
		for (int i = 0; i < length; i++) {
			int rand = (int) (Math.random() * chars.length());
			rands[i] = chars.charAt(rand);
		}
		return rands;
	}
	protected boolean getCookieByVoild(String stuVolidCode, HttpServletRequest request) {
		String volidCode=tmpcookieutil.getCookie(request, "ValidCode");
		if(volidCode.equalsIgnoreCase(stuVolidCode.trim())){
			return true;
		}
		return false;
		
	}
}