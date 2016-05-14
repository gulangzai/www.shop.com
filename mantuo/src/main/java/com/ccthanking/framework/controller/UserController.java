/**
 * @author wangzh
 */
package com.ccthanking.framework.controller;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ccthanking.framework.Constants;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.base.BaseVO;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.UserVO;
import com.ccthanking.framework.common.cache.CacheManager;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.http.listener.OnlineUserEventListener;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.model.requestJson;
import com.ccthanking.framework.model.responseJson;
import com.ccthanking.framework.service.UserService;
import com.ccthanking.framework.service.UserServiceCust;
import com.ccthanking.framework.util.Encipher;
import com.copj.modules.utils.event.EventUtil;
import com.copj.modules.utils.exception.SystemException;

/**
 * @author wangzh
 */
@Controller
@RequestMapping("/userController")
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired
	@Qualifier("userServiceCustImpl")
	private UserServiceCust userServiceCust;

	@PostConstruct
	public void init() {
		logger.debug("*******在线用户登录event监听**********");
		OnlineUserEventListener onlineUserEventListener = new OnlineUserEventListener();
		EventUtil.registerEventListener("onlineUserEvent", onlineUserEventListener, true);
	}
	
	@RequestMapping(value = "toRegister", method = RequestMethod.GET)
	public String toRegister(HttpServletRequest request,String inviteCode,Model model){
		ModelAndView modelAndView = new ModelAndView();
	    model.addAttribute("inviteCode", inviteCode);
	    System.out.println("访问成功了"+inviteCode); 
		return "/jsp/business/user/user-add";
	}
	

	/**
	 * @param 用户登录验证
	 * @param username
	 * @param password
	 * @return org.springframework.web.servlet.ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	protected ModelAndView login(final HttpServletRequest request, final ModelAndView mav,
			@RequestParam(value = "username") String username, @RequestParam(value = "password") String password)
			throws Exception {
		User user = this.userService.getUserByUsernameAndPassword(username, password);
		if (user != null) {

			// 设置IP
			user.setIP(getRemoteAddrIp(request));

			// 设置threadLocal信息
			RestContext.setCurrentUserInThread(user);
			RestContext.setCurrentSession(request.getSession());
			System.out.println(RestContext.getCurrentUser());
			request.getSession().setAttribute(Globals.USER_KEY, user);
			request.getSession().setAttribute("userId", user.getAccount());

			// request.getSession().setAttribute("DEPTID",
			// user.getOrgDept().getDeptID());
			// request.getSession().setAttribute("DEPTNAME",
			// user.getOrgDept().getDept_Name());

			LogManager.writeLoginLog(user, LogManager.LOGIN_STATUS_SUCCESS);
			//String pagepath = "/jsp/framework/portal/frame_company";

			//if (User.SUPER_USER.equals(user.getPersonKind())) {
			String	pagepath = "/jsp/framework/portal/frame_admin";
			//}
			//pagepath="/jsp/framework/portal/main";
//			pagepath = "/jsp/business/commons/sxsb-index";
			mav.setViewName(pagepath);
			return mav;
		} else {
			request.setAttribute("error", "用户名不存在或密码不正确");
			mav.setViewName("/index");
			return mav;
		}

	}

	/** 用户登录验证**/
	@RequestMapping(value="loginAjax", method = RequestMethod.POST)
	@ResponseBody
	protected responseJson loginAjax(final HttpServletRequest request, final ModelAndView mav,
			@RequestParam(value = "username") String username, @RequestParam(value = "password") String password)
			throws Exception {
		responseJson j = new responseJson();
		User user = this.userService.getUserByUsernameAndPassword(username, password);
		Map<String, Object> map = new HashMap<String, Object>();
		if (user != null) {

			// 设置IP
			user.setIP(getRemoteAddrIp(request));

			// 设置threadLocal信息
			RestContext.setCurrentUserInThread(user);
			RestContext.setCurrentSession(request.getSession());

			request.getSession().setAttribute(Globals.USER_KEY, user);
			request.getSession().setAttribute("userId", user.getAccount());
			System.out.println(RestContext.getCurrentUser().getAccount());
			// request.getSession().setAttribute("DEPTID",
			// user.getOrgDept().getDeptID());
			// request.getSession().setAttribute("DEPTNAME",
			// user.getOrgDept().getDept_Name());

			//LogManager.writeLoginLog(user, LogManager.LOGIN_STATUS_SUCCESS);
			//String pagepath = "/jsp/framework/portal/main.jsp";
			//2015-11-05 需求变更 直接跳项目列表页面
			String	pagepath = "/jsp/business/project/project-index.jsp";                                        
			
			mav.setViewName(pagepath);
			j.setMsg("{\"url\":\""+pagepath+"\"}");
			map.put("url", pagepath);
			j.setObj(map);
			j.setSuccess(true);
		} else {
			//request.setAttribute("error", "用户名不存在或密码不正确");
			mav.setViewName("/index");
			j.setMsg("{\"url\":\"/index\"}");
			map.put("url", "/index");
			map.put("error", "用户名不存在或密码不正确");
			j.setObj(map);
			j.setSuccess(false);
		}
		
		return j;
	}

	/**
	 * @param 用户登录验证
	 * @param username
	 * @param password
	 * @return org.springframework.web.servlet.ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	protected ModelAndView loginGet(final HttpServletRequest request, final ModelAndView mav) throws Exception {
		User user = RestContext.getCurrentUser();
		if (user != null) {
			String pagepath = "/jsp/framework/portal/main";
//			String pagepath = "/jsp/business/commons/sxsb-index";
			// String pagepath="/jsp/framework/portal/frame";
			mav.setViewName(pagepath);
			return mav;
		} else {
			mav.setViewName("/index");
			return mav;
		}

	}
	
	/**
	 * @param 移动端用户登录验证
	 * @param username
	 * @param password
	 * @return org.springframework.web.servlet.ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "mlogin", method = RequestMethod.POST)
	@ResponseBody
	protected JSONObject mlogin(final HttpServletRequest request) throws Exception {
		User user = RestContext.getCurrentUser();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		JSONObject obj = new JSONObject(); 
		String token = String.valueOf(System.nanoTime());
		if (null != user) {
			obj.put("state", "true");
			obj.put("message", "登录成功");
			obj.put("token", token);
			return obj;
		} else {
			user = this.userServiceCust.getUserByUsernameAndPasswordm(username, password);
			if(null!=user){
				obj.put("state", "true");
				obj.put("message", "登录成功");
				obj.put("token", token);

			}
			return obj;
		}
	}

	@RequestMapping(value = "checkvalid")
	@ResponseBody
	public responseJson checkvalid(final HttpServletRequest request, requestJson js) throws Exception {
		System.out.println(js.getMsg());
		User user = RestContext.getCurrentUser();
		System.out.println(user.getAccount());
		responseJson j = new responseJson();

		// String menuTreeHtml = menuService.getMenuTreeHtml(user);

		// j.setMsg(menuTreeHtml);
		j.setSuccess(true);
		return j;
	}

	/**
	 * @param 保存用户信息
	 * @return org.springframework.web.servlet.ModelAndView
	 */
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	@ResponseBody
	protected Map<String, Object> regPost(final HttpServletRequest reqeuest,
			@RequestParam(value = "username") final String username,
			@RequestParam(value = "password") final String password,
			@RequestParam(value = "realName") final String realName, @RequestParam(value = "email") final String email,
			@RequestParam(value = "sex") final Integer sex) {
		Map<String, Object> model = new HashMap<String, Object>();
		/*
		 * User user = new User();
		 * user.setLastLoginIp(reqeuest.getRemoteAddr()); long now =
		 * System.currentTimeMillis(); user.setLastLoginTime(now);
		 * user.setLoginTimes(0); user.setLogonIp(reqeuest.getRemoteAddr());
		 * user.setPasswrod(password); user.setRealName(realName);
		 * user.setRegTime(now); user.setRole(0); user.setStatus(0);
		 * user.setUserName(username); user.setUserSex(sex); model.put("result",
		 * this.userService.insert(user));
		 */
		return model;
	}

	@RequestMapping(value = "print", method = RequestMethod.POST)
	protected requestJson print(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "tabHtml") final String tabHtml) throws Exception {

		System.out.println(tabHtml);

		requestJson j = new requestJson();
		// String resultVO = "";
		// resultVO = this.userService.updatedemo(json.getMsg(),user);
		// j.setMsg(resultVO);
		return j;
	}

	@RequestMapping(value = "queryUser")
	@ResponseBody
	public requestJson queryUser(HttpServletRequest request, requestJson json) {
		requestJson j = new requestJson();
		try { 
			String domResult = userService.queryUser(json.getMsg());
			j.setMsg(domResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}

	@RequestMapping(params = "awardRoleToUser")
	@ResponseBody
	protected requestJson awardRoleToUser(HttpServletRequest request, requestJson json) throws Exception {
		User user = RestContext.getCurrentUser();
		UserVO vo = new UserVO();

 	    JSONArray jlist = vo.doInitJson(json.getMsg());
 	    JSONObject obj = (JSONObject) jlist.get(0);
 	    
		String account = obj.getString("account");
		String checkValue = obj.getString("checkValue");
		System.out.println("awardRoleToUser : " + checkValue);

		String[] roleNameAndId = "".equals(checkValue) ? new String[0] : checkValue.split(",");
		userService.awardRoleToUser(account, roleNameAndId, user);
		requestJson j = new requestJson();
		j.setMsg("");
		return j;
	}
	

	@RequestMapping(params = "awardRoleToPerson")
	@ResponseBody
	protected requestJson awardRoleToPerson(HttpServletRequest request, requestJson json) throws Exception {
		User user = RestContext.getCurrentUser();
		UserVO vo = new UserVO();

 	    JSONArray jlist = vo.doInitJson(json.getMsg());
 	    JSONObject obj = (JSONObject) jlist.get(0);
		String roleid = obj.getString("roleid");
		String rolename = obj.getString("rolename");
		String checkValue = obj.getString("checkValue");

		String[] accountNameAndId = "".equals(checkValue) ? new String[0] : checkValue.split(",");
		userServiceCust.awardRoleToPerson(roleid, rolename, accountNameAndId, user);
		requestJson j = new requestJson();
		j.setMsg("");

		// String account = request.getParameter("account");
		// String domresult = "";
		// domresult = this.userService.resetPw(account);
		// j.setMsg(domresult);
		return j;

	}
	 
	@RequestMapping(value = "resetPw")
	@ResponseBody
	public requestJson resetPw(HttpServletRequest request, HttpServletResponse response) throws Exception {
		requestJson j = new requestJson();
		String account = request.getParameter("account");
		String domresult = "";
		domresult = this.userService.resetPw(account);
		j.setMsg(domresult);
		return j;

	}

	@RequestMapping(value = "modifyPassword")
	@ResponseBody
	protected requestJson modifyPassword(HttpServletRequest request, requestJson json) throws Exception {

		User user = RestContext.getCurrentUser();
		JSONArray list = new BaseVO().doInitJson(json.getMsg());
		JSONObject jsonObj = (JSONObject) list.get(0);

		String oldPass = jsonObj.getString("oldpassword");
		if (oldPass == null) {
			oldPass = "";
		}
		String newPass = jsonObj.getString("newpassword");
		if (newPass == null)
			newPass = "";

		// 转为大写 密码不区分大小写
		oldPass = oldPass.toUpperCase();

		requestJson j = new requestJson();

		String newPass_md5 = "";
		if (Constants.ENCODER_TYPE.equals("MD5")) {// md5加密
			oldPass = DigestUtils.md5Hex(oldPass);
			newPass_md5 = DigestUtils.md5Hex(newPass.toUpperCase());
		} else {
			oldPass = Encipher.EncodePasswd(oldPass);
			newPass_md5 = Encipher.EncodePasswd(newPass.toUpperCase());
		}

		Connection conn = DBUtil.getConnection();
		try {
			conn.setAutoCommit(false);
			// String sqlQuery =
			// "select t.password from fs_org_person t where t.account='" +
			// user.getAccount() + "'";

			// 增加用户from来源，以决定后续修改哪个表
			String sqlQuery = "SELECT u.pwd FROM \"USERS\" u WHERE u.users_uid=" + user.getAccount();
			String[][] res = DBUtil.query(sqlQuery);
			String password = res[0][0];
			if (password == null)
				password = "";

			if (!oldPass.equals(password)) {
				j.setPrompt("输入的旧密码不正确!");
				SystemException.handleException("输入的旧密码不正确!");
			}
			// newPass = Encipher.EncodePasswd(newPass);

			String sql = "update \"USERS\" t set t.pwd='" + newPass_md5 + "', MIMA='" + newPass
					+ "', updated_date = SYSDATE,updated_by ='" + user.getAccount() + "' where  users_uid="
					+ user.getAccount();

			boolean result = DBUtil.exec(conn, sql);
			conn.commit();
			CacheManager.broadcastChanges(CacheManager.CACHE_USER, user.getAccount(), CacheManager.UPDATE);
			LogManager.writeUserLog("", "", Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS,
					"用户 [" + user.getAccount() + " / " + user.getName() + "] 修改密码成功", user, "ACCOUNT",
					user.getAccount());
			j.setMsg("");

			return j;
		} catch (Exception e) {
			conn.rollback();
			LogManager.writeUserLog("", "", Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_FAILURE,
					"用户 [" + user.getAccount() + " / " + user.getName() + "] 修改密码失败!" + e.getMessage(), user,
					"ACCOUNT", user.getAccount());
			e.printStackTrace(System.out);
		} finally {
			DBUtil.closeConnetion(conn);
			conn = null;
		}
		return j;
	}

	public static String getRemoteAddrIp(HttpServletRequest request) {
		String ipFromNginx = getHeader(request, "X-Real-IP");
		return StringUtils.isEmpty(ipFromNginx) ? request.getRemoteAddr() : ipFromNginx;
	}

	private static String getHeader(HttpServletRequest request, String headName) {
		String value = request.getHeader(headName);
		return !StringUtils.isBlank(value) && !"unknown".equalsIgnoreCase(value) ? value : "";
	}
}
