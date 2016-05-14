package com.ccthanking.framework.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.RoleVO;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.log.log;
import com.ccthanking.framework.model.requestJson;
import com.ccthanking.framework.model.responseJson;
import com.ccthanking.framework.service.SjMenuService;

/**
 * @author wangzh
 */
@Controller
@RequestMapping("/sjMenuController")
public class SjMenuController {
	
	@Autowired
	private SjMenuService menuService;
	
	@RequestMapping(params = "menutree")
	@ResponseBody
	public responseJson menutree(final HttpServletRequest request,requestJson js) throws Exception {
		System.out.println(js.getMsg());
		User user = (User)  request.getSession().getAttribute(Globals.USER_KEY);
		System.out.println(user.getAccount());
		responseJson j = new responseJson();
		
		String menuTreeHtml ="";// menuService.getMenuTreeHtml(user);
		
		j.setMsg(menuTreeHtml);
		j.setSuccess(true);
		return j;
	}

	@ResponseBody
	@RequestMapping(params="getAllMenu")
	public void getAllMenu(HttpServletRequest request, HttpServletResponse response) {
		String menuJson = menuService.getAllMenu();
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().print(menuJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@RequestMapping(params = "executeMenu")
	@ResponseBody
	protected requestJson executeMenu(HttpServletRequest request,requestJson json) throws Exception {
		User user = (User)  request.getSession().getAttribute(Globals.USER_KEY);
		requestJson j = new requestJson();
		String operatorSign = request.getParameter("operatorSign");
		try {
			String domResult = menuService.executeMenu(json.getMsg(), user, operatorSign);
			j.setMsg(domResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}
	
	@RequestMapping(params = "queryUnique")
	@ResponseBody
	protected String queryUnique(HttpServletRequest request,requestJson json) throws Exception {
		User user = (User)  request.getSession().getAttribute(Globals.USER_KEY);
		String menuName = request.getParameter("menuName");
		String domResult = menuService.queryUnique(menuName, user);
		return domResult;
	}
	
	@ResponseBody
	@RequestMapping(params="loadAllMenu")
	public void loadAllMenu(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		String ryid = request.getParameter("ryid");
		String project_uid = request.getParameter("project_uid");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", type);
		map.put("ryid", ryid);
		map.put("project_uid", project_uid);
		String menuJson = menuService.loadAllMenu(map);
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().print(menuJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	@RequestMapping(params = "awardMenuToRole")
	@ResponseBody
	protected requestJson awardMenuToRole(HttpServletRequest request,requestJson json) throws Exception {
		User user = RestContext.getCurrentUser();
		
		RoleVO vo = new RoleVO();

 	    JSONArray jlist = vo.doInitJson(json.getMsg());
 	    JSONObject obj = (JSONObject) jlist.get(0);
 	    
		String roleId = obj.getString("roleId");
		String codes = obj.getString("codes");
		
				
		String[] menuName = "".equals(codes) ? new String[0] : codes.split(",");
		menuService.awardMenuToRole(roleId, menuName, user);
		requestJson j = new requestJson();
		j.setMsg("");
		return j;
	}
	
	@RequestMapping(params = "awardMenuToUser")
	@ResponseBody
	protected requestJson awardMenuToUser(HttpServletRequest request,requestJson json) throws Exception {
		User user = RestContext.getCurrentUser();
		RoleVO vo = new RoleVO();

 	    JSONArray jlist = vo.doInitJson(json.getMsg());
 	    JSONObject obj = (JSONObject) jlist.get(0);
		String userId = obj.getString("userId");
		String codesVal = obj.getString("codes");
		String val = obj.getString("val");
		System.out.println("awardRoleToUser | " + val);
				
		String[] menuName = "".equals(val) ? new String[0] : val.split(",");
		String[] codes = "".equals("codes") ? new String[0] : codesVal.split(",");
		menuService.awardMenuToUser(userId, menuName,codes, user);
		requestJson j = new requestJson();
		j.setMsg("");
		return j;
	}
	
	/**
	 * 根据用户编号和菜单code判断是否有权显示操作按钮
	 * @param request
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "getByMenuCodeAndUserId")
	@ResponseBody
	protected requestJson getByMenuCodeAndUserId(HttpServletRequest request,requestJson json) throws Exception {
		User user = RestContext.getCurrentUser();
		String code = request.getParameter("code");
		String result = menuService.getByMenuCodeAndUserId(user.getUserSN(),code);
		requestJson j = new requestJson();
		j.setMsg("");
		if(!"".equals(result) && result != null){
			j.setMsg(result);
		}
		return j;
	}
	
	public void refreshMenuCache() {
		Logger lg = log.getLogger(SjMenuController.class);
		lg.info("重新载入菜单信息...");
		try {
			com.ccthanking.framework.coreapp.orgmanage.MenuManager.getInstance().reBuildMemory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
