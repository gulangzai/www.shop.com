/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    commons.service.SysUserController.java
 * 创建日期： 2015-10-26 下午 10:31:55
 * 功能：    服务控制类：用户信息
 * 所含类:   SysUserService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-26 下午 10:31:55  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.commons;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.ccthanking.business.commons.service.MobileService;
import com.ccthanking.business.loguserread.vo.LogUserReadVO;
import com.ccthanking.business.pmcailiao.vo.PmCailiaoVO;
import com.ccthanking.business.pmjiancha.vo.PmXunjianStrucVO;
import com.ccthanking.business.pmjiancha.vo.PmXunjianVO;
import com.ccthanking.business.pmxianchang.vo.PmXianchangDafuVO;
import com.ccthanking.business.pmxianchang.vo.PmXianchangVO;
import com.ccthanking.business.pmyanshou.vo.PmYanshouVO;
import com.ccthanking.business.project.vo.PmBiangengVO;
import com.ccthanking.business.projectlog.vo.PmProjectLogStrucVO;
import com.ccthanking.business.projectlog.vo.PmProjectLogVO;
import com.ccthanking.business.projectlog.vo.PmProjectTopicReplyVO;
import com.ccthanking.business.projectlog.vo.PmProjectTopicVO;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.service.UserServiceCust;
import com.ccthanking.framework.util.DateUtil;
import com.ccthanking.framework.util.MobileFileUpload;
import com.ccthanking.framework.util.MobileUtil;
import com.ccthanking.framework.util.PushMessage;

/**
 * <p>
 * MobileController.java
 * </p>
 * <p>
 * 功能：移动端接口
 * </p>
 *
 * <p>
 * <a href="MobileController.java.html"><i>查看源代码</i></a>
 * </p>
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-26
 * 
 */

@Controller
@RequestMapping("/commons/mobileController")
public class MobileController {

	private static Logger logger = LoggerFactory.getLogger(MobileController.class);

	@Autowired
	private MobileService mobileService;

	@Autowired
	@Qualifier("userServiceCustImpl")
	private UserServiceCust userServiceCust;

	/**
	 * 移动端 密码修改
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "login", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject login(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		JSONObject obj = new JSONObject();
		User user = this.userServiceCust.getUserByUsernameAndPasswordm(username, password);
		if (null != user) {
			obj.put("state", "true");
			obj.put("message", "登录成功");
			obj.put("userId", user.getIdCard());
		} else {
			obj.put("state", "false");
			obj.put("message", "用户名不存在或密码不正确");
		}
		return obj;

	}

	/**
	 * 移动端登录 微现场 登录 判断
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "wxcLogin", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject wxcLogin(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String token = request.getParameter("token");
		JSONObject obj = new JSONObject();
		if (MobileUtil.WXCTOKEN.equalsIgnoreCase(token)) {// 移动端 应用程序标识
			/** 返回 判断 的结果：存在user就返回,否则 返回null **/
			User user = this.userServiceCust.getUserByUsernameAndPassword(username, password);
			if (null != user) {
				obj.put("state", "true");
				obj.put("message", "登录成功");
				obj.put("userId", user.getIdCard());
			} else {
				obj.put("state", "false");
				obj.put("message", "用户名不存在或密码不正确");
			}

		} else {
			obj.put("state", false);
			obj.put("message", "查询结果失败,token异常..");
		}
		return obj;

	}

	/**
	 * 移动端 修改密码
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "updatePassWord", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updatePassWord(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		String userId = request.getParameter("userId");
		String oldPwd = request.getParameter("oldPassword");
		String newPwd = request.getParameter("newPassword");
		String token = request.getParameter("token");
		JSONObject obj = new JSONObject();
		if (MobileUtil.WXCTOKEN.equalsIgnoreCase(token)) {// 移动端 应用程序标识
			/** 返回 判断 的结果：存在user就返回,否则 返回null **/
			String message = this.userServiceCust.updatePassWord(userId, oldPwd, newPwd);

			if ("no".equalsIgnoreCase(message)) {
				MobileUtil.getPwdInputError(obj);
			} else if ("true".equalsIgnoreCase(message)) {
				MobileUtil.getUpdateMessage(obj, true);
			} else {
				MobileUtil.getUpdateMessage(obj, false);
			}

		} else {
			MobileUtil.getErrorToken(obj);
		}
		return obj;

	}

	/**
	 * 移动端获取 用户信息
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "getUserInfo", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getUserXinxi(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		String userId = request.getParameter("userId");
		String token = request.getParameter("token");
		JSONObject obj = new JSONObject();
		if (MobileUtil.WXCTOKEN.equalsIgnoreCase(token)) {// 移动端 应用程序标识
			/** 返回 判断 的结果： **/
			obj = this.userServiceCust.getUserXinXi(userId);
			String image = obj.getString("imageUrl");
			obj.put("image", request.getScheme() + "://" + request.getServerName() + ":8088/file" + image);

		} else {
			MobileUtil.getErrorToken(obj);
		}
		return obj;

	}
	
	/**
	 * 移动端获取 用户信息
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "queryUserInfo", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject queryUserInfo(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		String userId = request.getParameter("userId");
		String token = request.getParameter("token");
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", token);
		JSONObject obj = this.mobileService.queryUserInfo(map);
		
		return obj;
		
	}

	/**
	 * 修改头像
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "modifyImage", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject modifyImage(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		// 先判断是否有文件
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// 如果有文件则处理文件
		if (isMultipart) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 先处理附件信息
			MobileFileUpload mfu = new MobileFileUpload();
			JSONArray json = mfu.packFile(multipartRequest);
			if (null != json) {
				map.put("json", json);
			}
		}
		String userId = request.getParameter("create_user");
		String token = request.getParameter("token");
		map.put("userId", userId);
		map.put("token", token);
		JSONObject obj = this.mobileService.modifyImage(map);
		List<Map> list = (List<Map>) obj.get("imageUrl");
		if (list.size() > 0) {
			Map imap = list.get(0);
			String image = imap.get("FILE_PATH").toString();
			obj.put("imageUrl", request.getScheme() + "://" + request.getServerName() + ":8088/file" + image);
		}

		return obj;

	}

	/**
	 * 添加变更签证
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "insertChange")
	@ResponseBody
	public JSONObject insertChange(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		// 先判断是否有文件
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// 如果有文件则处理文件
		if (isMultipart) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 先处理附件信息
			MobileFileUpload mfu = new MobileFileUpload();
			JSONArray json = mfu.packFile(multipartRequest);
			if (null != json) {
				map.put("json", json);
			}
		}

		String token = request.getParameter("token");
		String userId = request.getParameter("userId");
		String projectId = request.getParameter("projectId");
		String date = request.getParameter("time");
		String changeType = request.getParameter("changeType");
		String type = request.getParameter("type");
		String unit = request.getParameter("unit");
		String reason = request.getParameter("reason");
		String amount = request.getParameter("amount");
		String major = request.getParameter("major");
		String state = request.getParameter("state");
		PmBiangengVO vo = new PmBiangengVO();
		vo.setProject_uid(projectId);
		vo.setCreate_user(userId);
		vo.setBiangeng_date(DateUtil.formatDate(date));
		vo.setCreate_date(new Date());
		vo.setTags(changeType);
		vo.setBiangeng_type(type);
		vo.setBiangeng_company(unit);
		vo.setReason(reason);
		vo.setAmount(amount);
		vo.setImportant(major);
		vo.setStatus(state);

		map.put("token", token);
		map.put("changevo", vo);
		JSONObject obj = this.mobileService.insertChange(map);

		return obj;
	}
	
	/**
	 * 修改变更
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "updateChange")
	@ResponseBody
	public JSONObject updateChange(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		JSONObject obj = new JSONObject();
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> map2 = new HashMap<String, Object>();
		String token = request.getParameter("token");
		String userId = request.getParameter("userId");
		String projectId = request.getParameter("projectId");
		String date = request.getParameter("time");
		String changeType = request.getParameter("changeType");
		String type = request.getParameter("type");
		String unit = request.getParameter("unit");
		String reason = request.getParameter("reason");
		String amount = request.getParameter("amount");
		String major = request.getParameter("major");
		String state = request.getParameter("state");
		String target_table = request.getParameter("target_table");
		String changeId = request.getParameter("changeId");
		
		map2.put("token", token);
		map2.put("target_uid", changeId);
		map2.put("table_name", target_table);
		// 先判断是否有文件
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// 如果有文件则处理文件
		if (isMultipart) {
			//obj = this.mobileService.deleteFiles(map2);
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 先处理附件信息
			MobileFileUpload mfu = new MobileFileUpload();
			JSONArray json = mfu.packFile(multipartRequest);
			if (null != json) {
				map.put("json", json);
			}
		}
		
		PmBiangengVO vo = new PmBiangengVO();
		vo.setProject_uid(projectId);
		vo.setBiangeng_date(DateUtil.formatDate(date));
		vo.setTags(changeType);
		vo.setBiangeng_type(type);
		vo.setBiangeng_company(unit);
		vo.setReason(reason);
		vo.setAmount(amount);
		vo.setImportant(major);
		vo.setStatus(state);
		vo.setUpdate_date(new Date());
		vo.setUpdate_user(userId);
		vo.setBiangeng_uid(changeId);

		map.put("token", token);
		map.put("changevo", vo);
		obj = this.mobileService.updateChange(map);
		
		return obj;
	}
	/**
	 * 删除问题讨论
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "deleteChange")
	@ResponseBody
	public JSONObject deleteChange(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		
		HashMap<String, Object> map = new HashMap<String, Object>();

		String token = request.getParameter("token");
		String changeId = request.getParameter("changeId");
		
		map.put("token", token);
		map.put("changeId", changeId);
		JSONObject obj = this.mobileService.deleteChange(map);
		
		return obj;
	}

	/**
	 * 获取变更签证
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "getChange")
	@ResponseBody
	public JSONObject getChange(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		// User user = RestContext.getCurrentUser();
		// logger.info("<{}>执行操作【获取项目列表】",user.getName());
		String token = request.getParameter("token");
		String projectId = request.getParameter("projectId");
		String userId = request.getParameter("userId");
		String type = request.getParameter("type");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", token);
		map.put("projectId", projectId);
		map.put("userId", userId);
		map.put("type", type);

		JSONObject obj = this.mobileService.getChange(map);
		List<Map> list = (List<Map>) obj.get("changeList");
		for (int i = 0; i < list.size(); i++) {
			Map changemap = list.get(i);
			changemap.put("userImage",
					request.getScheme() + "://" + request.getServerName() + ":8088/file" + changemap.get("userImage"));
			List<Map> flist = (List<Map>) changemap.get("appendix");
			for (int j = 0; j < flist.size(); j++) {
				Map fmap = flist.get(j);
				fmap.put("fileUrl",
						request.getScheme() + "://" + request.getServerName() + ":8088/file" + fmap.get("FILE_PATH"));
				fmap.put("fileName", fmap.get("FILE_NAME"));
			}
		}
		return obj;
	}

	/**
	 * 添加设备材料进场
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "insertMaterial")
	@ResponseBody
	public JSONObject insertMaterial(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		// 先判断是否有文件
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// 如果有文件则处理文件
		if (isMultipart) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 先处理附件信息
			MobileFileUpload mfu = new MobileFileUpload();
			JSONArray json = mfu.packFile(multipartRequest);
			if (null != json) {
				map.put("json", json);
			}
		}

		String token = request.getParameter("token");
		String userId = request.getParameter("userId");
		String projectId = request.getParameter("projectId");
		String name = request.getParameter("name");
		String date = request.getParameter("time");
		String brand = request.getParameter("brand");
		String producer = request.getParameter("producer");
		String originPlace = request.getParameter("originPlace");
		String model = request.getParameter("model");
		String unit = request.getParameter("unit");
		String number = request.getParameter("numbers");
		String price = request.getParameter("price");
		String acceptor = request.getParameter("acceptor");
		String note = request.getParameter("note");
		PmCailiaoVO vo = new PmCailiaoVO();
		vo.setProject_uid(projectId);
		vo.setCreate_user(userId);
		vo.setCailiao_jcrq(DateUtil.formatDate(date));
		vo.setCreate_date(new Date());
		vo.setCailiao_name(name);
		vo.setCailiao_pinpai(brand);
		vo.setCailiao_chandi(originPlace);
		vo.setCailiao_changshang(producer);
		vo.setCailiao_xinghao(model);
		vo.setCailiao_unit(unit);
		vo.setCailiao_nums(number);
		vo.setCailiao_price(price);
		vo.setYanshou_ren(acceptor);
		vo.setDescription(note);

		map.put("token", token);
		map.put("materialvo", vo);
		JSONObject obj = this.mobileService.insertMaterial(map);

		return obj;
	}
	/**
	 * 修改设备材料进场
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "updateMaterial")
	@ResponseBody
	public JSONObject updateMaterial(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		JSONObject obj = new JSONObject();
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> map2 = new HashMap<String, Object>();
		
		String token = request.getParameter("token");
		String userId = request.getParameter("userId");
		String projectId = request.getParameter("projectId");
		String name = request.getParameter("name");
		String date = request.getParameter("time");
		String brand = request.getParameter("brand");
		String producer = request.getParameter("producer");
		String originPlace = request.getParameter("originPlace");
		String model = request.getParameter("model");
		String unit = request.getParameter("unit");
		String number = request.getParameter("numbers");
		String price = request.getParameter("price");
		String acceptor = request.getParameter("acceptor");
		String note = request.getParameter("note");
		
		String materialId = request.getParameter("materialId");
		String target_table = request.getParameter("target_table");
		// 先判断是否有文件
		map2.put("token", token);
		map2.put("target_uid", materialId);
		map2.put("table_name", target_table);
		// 先判断是否有文件
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// 如果有文件则处理文件
		if (isMultipart) {
			//obj = this.mobileService.deleteFiles(map2);
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 先处理附件信息
			MobileFileUpload mfu = new MobileFileUpload();
			JSONArray json = mfu.packFile(multipartRequest);
			if (null != json) {
				map.put("json", json);
			}
		}
		

		PmCailiaoVO vo = new PmCailiaoVO();
		vo.setProject_uid(projectId);
		vo.setCailiao_jcrq(DateUtil.formatDate(date));
		vo.setCailiao_name(name);
		vo.setCailiao_pinpai(brand);
		vo.setCailiao_chandi(originPlace);
		vo.setCailiao_changshang(producer);
		vo.setCailiao_xinghao(model);
		vo.setCailiao_unit(unit);
		vo.setCailiao_nums(number);
		vo.setCailiao_price(price);
		vo.setYanshou_ren(acceptor);
		vo.setDescription(note);
		vo.setUpdate_date(new Date());
		vo.setUpdate_user(userId);
		vo.setCailiao_uid(materialId);
		
		map.put("token", token);
		map.put("materialvo", vo);
		obj = this.mobileService.updateMaterial(map);
		
		return obj;
	}
	/**
	 * 删除设备材料进场
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "deleteMaterial")
	@ResponseBody
	public JSONObject deleteMaterial(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		String token = request.getParameter("token");
		String materialId = request.getParameter("materialId");
		
		map.put("token", token);
		map.put("materialId", materialId);
		JSONObject obj = this.mobileService.deleteMaterial(map);
		
		return obj;
	}

	/**
	 * 获取设备材料进场
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "getMaterial")
	@ResponseBody
	public JSONObject getMaterial(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		// User user = RestContext.getCurrentUser();
		// logger.info("<{}>执行操作【获取项目列表】",user.getName());
		String token = request.getParameter("token");
		String projectId = request.getParameter("projectId");
		String userId = request.getParameter("userId");
		String type = request.getParameter("type");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", token);
		map.put("projectId", projectId);
		map.put("userId", userId);
		map.put("type", type);
		JSONObject obj = this.mobileService.getMaterial(map);
		List<Map> list = (List<Map>) obj.get("materialList");
		for (int i = 0; i < list.size(); i++) {
			Map materialmap = list.get(i);
			materialmap.put("userImage", request.getScheme() + "://" + request.getServerName() + ":8088/file"
					+ materialmap.get("userImage"));
			List<Map> flist = (List<Map>) materialmap.get("appendix");
			for (int j = 0; j < flist.size(); j++) {
				Map fmap = flist.get(j);
				fmap.put("fileUrl",
						request.getScheme() + "://" + request.getServerName() + ":8088/file" + fmap.get("FILE_PATH"));
				fmap.put("fileName", fmap.get("FILE_NAME"));
			}
		}
		return obj;
	}

	/**
	 * 添加违规内容
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "insertContent")
	@ResponseBody
	public JSONObject insertContent(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();

		String token = request.getParameter("token");
		String userId = request.getParameter("userId");
		String projectId = request.getParameter("projectId");
		String name = request.getParameter("name");
		String date = request.getParameter("time");
		String level = request.getParameter("level");
		String conclusion = request.getParameter("conclusion");
		String person = request.getParameter("person");

		PmXunjianVO vo = new PmXunjianVO();
		vo.setProject_uid(projectId);
		vo.setCreate_user(userId);
		vo.setXunjian_date(DateUtil.formatDate(date));
		vo.setCreate_date(new Date());
		vo.setProject_uid(projectId);
		vo.setJiancha_level_uid(level);
		vo.setJierun(conclusion);
		vo.setCanjian(person);

		map.put("token", token);
		map.put("checkvo", vo);
		JSONObject obj = this.mobileService.insertCheck(map);

		return obj;
	}

	/**
	 * 添加现场检查
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "insertCheck")
	@ResponseBody
	public JSONObject insertCheck(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		// 先判断是否有文件
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// 如果有文件则处理文件
		if (isMultipart) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 先处理附件信息
			MobileFileUpload mfu = new MobileFileUpload();
			JSONArray json = mfu.packFile(multipartRequest);
			if (null != json) {
				map.put("json", json);
			}
		}
		String token = request.getParameter("token");
		String userId = request.getParameter("userId");
		String projectId = request.getParameter("projectId");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String date = request.getParameter("time");
		String level = request.getParameter("level");
		String conclusion = request.getParameter("conclusion");
		String person = request.getParameter("person");
		String state = request.getParameter("state");

		PmXunjianVO vo = new PmXunjianVO();
		vo.setProject_uid(projectId);
		vo.setCreate_user(userId);
		vo.setXunjian_date(DateUtil.formatDate(date));
		vo.setCreate_date(new Date());
		vo.setProject_uid(projectId);
		vo.setXunjian_type(type);
		vo.setJiancha_level_uid(level);
		vo.setJierun(conclusion);
		vo.setCanjian(person);
		vo.setStatus(state);

		PmXunjianStrucVO svo = new PmXunjianStrucVO();
		svo.setPrj_struc_uid("1");

		map.put("token", token);
		map.put("checkvo", vo);
		map.put("svo", svo);
		JSONObject obj = this.mobileService.insertCheck(map);

		return obj;
	}
	/**
	 * 修改现场检查
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "updateCheck")
	@ResponseBody
	public JSONObject updateCheck(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		
		JSONObject obj = new JSONObject();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> map2 = new HashMap<String, Object>();
		
		String token = request.getParameter("token");
		String userId = request.getParameter("userId");
		String projectId = request.getParameter("projectId");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String date = request.getParameter("time");
		String level = request.getParameter("level");
		String conclusion = request.getParameter("conclusion");
		String person = request.getParameter("person");
		String state = request.getParameter("state");
		
		String checkId = request.getParameter("checkId");
		String target_table = request.getParameter("target_table");
		// 先判断是否有文件
		map2.put("token", token);
		map2.put("target_uid", checkId);
		map2.put("table_name", target_table);
		// 先判断是否有文件
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// 如果有文件则处理文件
		if (isMultipart) {
			//obj = this.mobileService.deleteFiles(map2);
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 先处理附件信息
			MobileFileUpload mfu = new MobileFileUpload();
			JSONArray json = mfu.packFile(multipartRequest);
			if (null != json) {
				map.put("json", json);
			}
		}

		
		PmXunjianVO vo = new PmXunjianVO();
		vo.setProject_uid(projectId);
		vo.setXunjian_date(DateUtil.formatDate(date));
		vo.setProject_uid(projectId);
		vo.setXunjian_type(type);
		vo.setJiancha_level_uid(level);
		vo.setJierun(conclusion);
		vo.setCanjian(person);
		vo.setStatus(state);
		vo.setUpdate_date(new Date());
		vo.setUpdate_user(userId);
		vo.setXunjian_uid(checkId);
		
		
		map.put("token", token);
		map.put("checkvo", vo);
		obj = this.mobileService.updateCheck(map);
		
		return obj;
	}
	/**
	 * 添加现场检查
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "deleteCheck")
	@ResponseBody
	public JSONObject deleteCheck(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String token = request.getParameter("token");
		String checkId = request.getParameter("checkId");
		
		map.put("token", token);
		map.put("checkId", checkId);

		JSONObject obj = this.mobileService.deleteCheck(map);
		
		return obj;
	}

	/**
	 * 获取现场检查
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "getCheck")
	@ResponseBody
	public JSONObject getCheck(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		// User user = RestContext.getCurrentUser();
		// logger.info("<{}>执行操作【获取项目列表】",user.getName());
		String token = request.getParameter("token");
		String projectId = request.getParameter("projectId");
		String userId = request.getParameter("userId");
		String type = request.getParameter("type");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", token);
		map.put("projectId", projectId);
		map.put("userId", userId);
		map.put("type", type);

		JSONObject obj = this.mobileService.getCheck(map);
		List<Map> list = (List<Map>) obj.get("checkList");
		for (int i = 0; i < list.size(); i++) {
			Map materialmap = list.get(i);
			materialmap.put("userImage", request.getScheme() + "://" + request.getServerName() + ":8088/file"
					+ materialmap.get("userImage"));
			List<Map> flist = (List<Map>) materialmap.get("appendix");
			for (int j = 0; j < flist.size(); j++) {
				Map fmap = flist.get(j);
				fmap.put("fileUrl",
						request.getScheme() + "://" + request.getServerName() + ":8088/file" + fmap.get("FILE_PATH"));
				fmap.put("fileName", fmap.get("FILE_NAME"));
			}
		}
		return obj;
	}

	/**
	 * 添加工程验收
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "insertAcceptance")
	@ResponseBody
	public JSONObject insertAcceptance(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		// 先判断是否有文件
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// 如果有文件则处理文件
		if (isMultipart) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 先处理附件信息
			MobileFileUpload mfu = new MobileFileUpload();
			JSONArray json = mfu.packFile(multipartRequest);
			if (null != json) {
				map.put("json", json);
			}
		}

		String token = request.getParameter("token");
		String userId = request.getParameter("userId");
		String projectId = request.getParameter("projectId");
		String date = request.getParameter("time");
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		String constructionUnit = request.getParameter("constructionUnit");
		String constructionUnitPerson = request.getParameter("constructionUnitPerson");
		String constructionUnitQualityPerson = request.getParameter("constructionUnitQualityPerson");
		String subpackageUnit = request.getParameter("subpackageUnit");
		String subpackageUnitPerson = request.getParameter("subpackageUnitPerson");
		String subpackageUnitQualityPerson = request.getParameter("subpackageUnitQualityPerson");
		String concealedAcceptanceLocation = request.getParameter("concealedAcceptanceLocation");
		String concealedAcceptanceBasis = request.getParameter("concealedAcceptanceBasis");
		String concealedAcceptanceContent = request.getParameter("concealedAcceptanceContent");
		String specialProjectStart = request.getParameter("specialProjectStart");
		String specialProjectEnd = request.getParameter("specialProjectEnd");
		String specialProjectAcceptanceContent = request.getParameter("specialProjectAcceptanceContent");
		String specialProjectAcceptanceData = request.getParameter("specialProjectAcceptanceData");
		String constructionUnitResult = request.getParameter("constructionUnitResult");
		String conclusion = request.getParameter("conclusion");
		PmYanshouVO vo = new PmYanshouVO();
		vo.setProject_uid(projectId);
		vo.setYsrq(DateUtil.formatDate(date));
		vo.setCreate_user(userId);
		vo.setCreate_date(new Date());
		vo.setYanshou_type(type);
		vo.setFbfxgc_name(name);
		vo.setSgdw(constructionUnit);
		vo.setSgdw_jsfzr(constructionUnitPerson);
		vo.setSgdw_zlfzr(constructionUnitQualityPerson);
		vo.setFbdw(subpackageUnit);
		vo.setFbdw_fzr(subpackageUnitPerson);
		vo.setFbdw_jsfzr(subpackageUnitQualityPerson);
		vo.setYb_ysbw(concealedAcceptanceLocation);
		vo.setYb_ysyj(concealedAcceptanceBasis);
		vo.setYb_ysnr(concealedAcceptanceContent);
		vo.setZx_kgrq(DateUtil.formatDate(specialProjectStart));
		vo.setZx_wcrq(DateUtil.formatDate(specialProjectEnd));
		vo.setZx_gcnr(specialProjectAcceptanceContent);
		vo.setZx_yszl(specialProjectAcceptanceData);
		vo.setJcjg(constructionUnitResult);
		vo.setYsjl(conclusion);

		map.put("token", token);
		map.put("acceptancevo", vo);
		JSONObject obj = this.mobileService.insertAcceptance(map);

		return obj;
	}
	/**
	 * 修改工程验收
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "updateAcceptance")
	@ResponseBody
	public JSONObject updateAcceptance(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		JSONObject obj = new JSONObject();
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> map2 = new HashMap<String, Object>();
		
		String token = request.getParameter("token");
		String userId = request.getParameter("userId");
		String projectId = request.getParameter("projectId");
		String date = request.getParameter("time");
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		String constructionUnit = request.getParameter("constructionUnit");
		String constructionUnitPerson = request.getParameter("constructionUnitPerson");
		String constructionUnitQualityPerson = request.getParameter("constructionUnitQualityPerson");
		String subpackageUnit = request.getParameter("subpackageUnit");
		String subpackageUnitPerson = request.getParameter("subpackageUnitPerson");
		String subpackageUnitQualityPerson = request.getParameter("subpackageUnitQualityPerson");
		String concealedAcceptanceLocation = request.getParameter("concealedAcceptanceLocation");
		String concealedAcceptanceBasis = request.getParameter("concealedAcceptanceBasis");
		String concealedAcceptanceContent = request.getParameter("concealedAcceptanceContent");
		String specialProjectStart = request.getParameter("specialProjectStart");
		String specialProjectEnd = request.getParameter("specialProjectEnd");
		String specialProjectAcceptanceContent = request.getParameter("specialProjectAcceptanceContent");
		String specialProjectAcceptanceData = request.getParameter("specialProjectAcceptanceData");
		String constructionUnitResult = request.getParameter("constructionUnitResult");
		String conclusion = request.getParameter("conclusion");
		String acceptanceId = request.getParameter("acceptanceId");
		String target_table = request.getParameter("target_table");

		map2.put("token", token);
		map2.put("target_uid", acceptanceId);
		map2.put("table_name", target_table);
		// 先判断是否有文件
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// 如果有文件则处理文件
		if (isMultipart) {
			//obj = this.mobileService.deleteFiles(map2);
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 先处理附件信息
			MobileFileUpload mfu = new MobileFileUpload();
			JSONArray json = mfu.packFile(multipartRequest);
			if (null != json) {
				map.put("json", json);
			}
		}
		

		PmYanshouVO vo = new PmYanshouVO();
		vo.setProject_uid(projectId);
		vo.setYsrq(DateUtil.formatDate(date));

		vo.setYanshou_type(type);
		vo.setFbfxgc_name(name);
		vo.setSgdw(constructionUnit);
		vo.setSgdw_jsfzr(constructionUnitPerson);
		vo.setSgdw_zlfzr(constructionUnitQualityPerson);
		vo.setFbdw(subpackageUnit);
		vo.setFbdw_fzr(subpackageUnitPerson);
		vo.setFbdw_jsfzr(subpackageUnitQualityPerson);
		vo.setYb_ysbw(concealedAcceptanceLocation);
		vo.setYb_ysyj(concealedAcceptanceBasis);
		vo.setYb_ysnr(concealedAcceptanceContent);
		vo.setZx_kgrq(DateUtil.formatDate(specialProjectStart));
		vo.setZx_wcrq(DateUtil.formatDate(specialProjectEnd));
		vo.setZx_gcnr(specialProjectAcceptanceContent);
		vo.setZx_yszl(specialProjectAcceptanceData);
		vo.setJcjg(constructionUnitResult);
		vo.setYsjl(conclusion);
		vo.setUpdate_date(new Date());
		vo.setUpdate_user(userId);
		vo.setYanshou_uid(acceptanceId);
		
		map.put("token", token);
		map.put("acceptancevo", vo);
		obj = this.mobileService.updateAcceptance(map);
		
		return obj;
	}
	/**
	 * 添加工程验收
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "deleteAcceptance")
	@ResponseBody
	public JSONObject deleteAcceptance(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();

		String token = request.getParameter("token");
		String acceptanceId = request.getParameter("acceptanceId");

		
		map.put("token", token);
		map.put("acceptanceId", acceptanceId);
		JSONObject obj = this.mobileService.deleteAcceptance(map);
		
		return obj;
	}

	/**
	 * 获取工程验收列表
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "getAcceptance")
	@ResponseBody
	public JSONObject getAcceptance(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		// User user = RestContext.getCurrentUser();
		// logger.info("<{}>执行操作【获取项目列表】",user.getName());
		String token = request.getParameter("token");
		String projectId = request.getParameter("projectId");
		String userId = request.getParameter("userId");
		String type = request.getParameter("type");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", token);
		map.put("projectId", projectId);
		map.put("userId", userId);
		map.put("type", type);

		JSONObject obj = this.mobileService.getAcceptance(map);
		List<Map> list = (List<Map>) obj.get("acceptanceList");
		for (int i = 0; i < list.size(); i++) {
			Map acceptancemap = list.get(i);
			acceptancemap.put("userImage", request.getScheme() + "://" + request.getServerName() + ":8088/file"
					+ acceptancemap.get("userImage"));
			List<Map> flist = (List<Map>) acceptancemap.get("appendix");
			for (int j = 0; j < flist.size(); j++) {
				Map fmap = flist.get(j);
				fmap.put("fileUrl",
						request.getScheme() + "://" + request.getServerName() + ":8088/file" + fmap.get("FILE_PATH"));
				fmap.put("fileName", fmap.get("FILE_NAME"));
			}
		}
		return obj;
	}

	/**
	 * 添加项目日志
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "insertProjectLog")
	@ResponseBody
	public JSONObject insertProjetLog(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		// 先判断是否有文件
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// 如果有文件则处理文件
		if (isMultipart) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 先处理附件信息
			MobileFileUpload mfu = new MobileFileUpload();
			JSONArray json = mfu.packFile(multipartRequest);
			if (null != json) {
				map.put("json", json);
			}
		}

		String token = request.getParameter("token");
		String userId = request.getParameter("userId");
		String projectId = request.getParameter("projectId");
		String date = request.getParameter("date");
		String location = request.getParameter("location");
		String event = request.getParameter("event");
		String production = request.getParameter("production");
		String safety = request.getParameter("safety");
		String logType = request.getParameter("logType");
		String work = request.getParameter("work");
		String problem = request.getParameter("problem");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		PmProjectLogVO vo = new PmProjectLogVO();
		PmProjectLogStrucVO svo = new PmProjectLogStrucVO();
		vo.setProject_uid(projectId);
		vo.setLog_date(DateUtil.formatDate(date));
		vo.setShengchan_info(production);
		vo.setZlaq_info(safety);
		vo.setLog_type(logType);
		vo.setTufa(event);
		vo.setCreate_user(userId);
		vo.setGongzuo_info(work);
		vo.setCqqk_info(problem);
		vo.setCreate_date(new Date());
		vo.setCreate_user(userId);
		vo.setYzlog_title(title);
		vo.setYzlog_content(content);

		svo.setPrj_struc_uid(location);
		map.put("token", token);
		map.put("logvo", vo);
		map.put("svo", svo);
		JSONObject obj = this.mobileService.insertProjetLog(map);

		return obj;
	}
	/**
	 * 修改项目日志
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "updateProjectLog")
	@ResponseBody
	public JSONObject updateProjectLog(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		JSONObject obj = new JSONObject();
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> map2 = new HashMap<String, Object>();
		
		String token = request.getParameter("token");
		String userId = request.getParameter("userId");
		String projectId = request.getParameter("projectId");
		String date = request.getParameter("date");
		String location = request.getParameter("location");
		String event = request.getParameter("event");
		String production = request.getParameter("production");
		String safety = request.getParameter("safety");
		String logType = request.getParameter("logType");
		String work = request.getParameter("work");
		String problem = request.getParameter("problem");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String logId = request.getParameter("logId");
		String target_table = request.getParameter("target_table");

		map2.put("token", token);
		map2.put("target_uid", logId);
		map2.put("table_name", target_table);
		// 先判断是否有文件
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// 如果有文件则处理文件
		if (isMultipart) {
			//obj = this.mobileService.deleteFiles(map2);
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 先处理附件信息
			MobileFileUpload mfu = new MobileFileUpload();
			JSONArray json = mfu.packFile(multipartRequest);
			if (null != json) {
				map.put("json", json);
			}
		}
		

		PmProjectLogVO vo = new PmProjectLogVO();
		//PmProjectLogStrucVO svo = new PmProjectLogStrucVO();
		vo.setProject_uid(projectId);
		vo.setLog_date(DateUtil.formatDate(date));
		vo.setShengchan_info(production);
		vo.setZlaq_info(safety);
		vo.setLog_type(logType);
		vo.setTufa(event);
		vo.setCreate_user(userId);
		vo.setGongzuo_info(work);
		vo.setCqqk_info(problem);
		vo.setUpdate_date(new Date());
		vo.setUpdate_user(userId);
		vo.setProject_log_uid(logId);
		vo.setYzlog_title(title);
		vo.setYzlog_content(content);
		
		//svo.setPrj_struc_uid(location);
		map.put("token", token);
		map.put("logvo", vo);
		//map.put("svo", svo);
		obj = this.mobileService.updateProjectLog(map);
		
		return obj;
	}
	/**
	 * 添加项目日志
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "deleteProjectLog")
	@ResponseBody
	public JSONObject deleteProjectLog(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		String token = request.getParameter("token");
		String logId = request.getParameter("logId");
		map.put("token", token);
		map.put("logId", logId);
		JSONObject obj = this.mobileService.deleteProjectLog(map);
		
		return obj;
	}

	/**
	 * 获取日志列表
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "getProjectLogs")
	@ResponseBody
	public JSONObject getProjetLogs(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		// User user = RestContext.getCurrentUser();
		// logger.info("<{}>执行操作【获取项目列表】",user.getName());
		String token = request.getParameter("token");
		String projectId = request.getParameter("projectId");
		String userId = request.getParameter("userId");
		String type = request.getParameter("type");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", token);
		map.put("projectId", projectId);
		map.put("userId", userId);
		map.put("type", type);
		JSONObject obj = this.mobileService.getProjetLogs(map);
		List<Map> list = (List<Map>) obj.get("logList");
		for (int i = 0; i < list.size(); i++) {
			Map logmap = list.get(i);
			logmap.put("userImage",
					request.getScheme() + "://" + request.getServerName() + ":8088/file" + logmap.get("userImage"));
			List<Map> flist = (List<Map>) logmap.get("appendix");
			for (int j = 0; j < flist.size(); j++) {
				Map fmap = flist.get(j);
				fmap.put("fileUrl",
						request.getScheme() + "://" + request.getServerName() + ":8088/file" + fmap.get("FILE_PATH"));
				fmap.put("fileName", fmap.get("FILE_NAME"));
			}
		}
		return obj;
	}

	/**
	 * 添加问题讨论
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "insertTopic")
	@ResponseBody
	public JSONObject insertTopic(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		HashMap<String, Object> map = new HashMap<String, Object>();
		// 先判断是否有文件
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// 如果有文件则处理文件
		if (isMultipart) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 先处理附件信息
			MobileFileUpload mfu = new MobileFileUpload();
			JSONArray json = mfu.packFile(multipartRequest);
			if (null != json) {
				map.put("json", json);
			}
		}

		String token = request.getParameter("token");
		String userId = request.getParameter("userId");
		String projectId = request.getParameter("projectId");
		String date = request.getParameter("date");
		String state = request.getParameter("state");
		String content = request.getParameter("content");
		String subject = request.getParameter("subject");

		PmProjectTopicVO vo = new PmProjectTopicVO();

		vo.setProject_uid(projectId);
		vo.setCreate_date(new Date());
		vo.setCreate_user(userId);
		vo.setStatus(state);
		vo.setSubject(subject);
		vo.setContent(content);

		map.put("token", token);
		map.put("topic", vo);
		JSONObject obj = this.mobileService.insertTopic(map);

		return obj;
	}
	/**
	 * 修改问题讨论
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "updateTopic")
	@ResponseBody
	public JSONObject updateTopic(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		JSONObject obj = new JSONObject();
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> map2 = new HashMap<String, Object>();
		String token = request.getParameter("token");
		String userId = request.getParameter("userId");
		String projectId = request.getParameter("projectId");
		String date = request.getParameter("date");
		String state = request.getParameter("state");
		String content = request.getParameter("content");
		String subject = request.getParameter("subject");
		String target_table = request.getParameter("target_table");
		String topicId = request.getParameter("topicId");
		
		map2.put("token", token);
		map2.put("target_uid", topicId);
		map2.put("table_name", target_table);
		// 先判断是否有文件
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// 如果有文件则处理文件
		if (isMultipart) {
			//obj = this.mobileService.deleteFiles(map2);
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 先处理附件信息
			MobileFileUpload mfu = new MobileFileUpload();
			JSONArray json = mfu.packFile(multipartRequest);
			if (null != json) {
				map.put("json", json);
			}
		}
		

		PmProjectTopicVO vo = new PmProjectTopicVO();
		
		vo.setProject_uid(projectId);
		//vo.setCreate_date(new Date());
		//vo.setCreate_user(userId);
		vo.setUpdate_date(new Date());
		vo.setStatus(state);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setProject_topic_uid(topicId);
		
		map.put("token", token);
		map.put("topic", vo);
		obj = this.mobileService.updateTopic(map);
		
		return obj;
	}
	/**
	 * 删除问题讨论
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "deleteTopic")
	@ResponseBody
	public JSONObject deleteTopic(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		
		HashMap<String, Object> map = new HashMap<String, Object>();

		String token = request.getParameter("token");
		String topicUid = request.getParameter("topicId");
		
		map.put("token", token);
		map.put("topicUid", topicUid);
		JSONObject obj = this.mobileService.deleteTopic(map);
		
		return obj;
	}
	/**
	 * 删除问题讨论回复
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "deleteReply")
	@ResponseBody
	public JSONObject deleteReply(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		String token = request.getParameter("token");
		String commentId = request.getParameter("commentId");
		
		map.put("token", token);
		map.put("commentId", commentId);
		JSONObject obj = this.mobileService.deleteReply(map);
		
		return obj;
	}
	/**
	 * 删除现场工况回复
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "deleteXianchang")
	@ResponseBody
	public JSONObject deleteXianchang(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		String token = request.getParameter("token");
		String commentId = request.getParameter("commentId");
		
		map.put("token", token);
		map.put("commentId", commentId);
		JSONObject obj = this.mobileService.deleteXianchang(map);
		
		return obj;
	}

	/**
	 * 获取问题讨论列表
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "getTopics")
	@ResponseBody
	public JSONObject getTopics(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		String token = request.getParameter("token");
		String projectId = request.getParameter("projectId");
		String userId = request.getParameter("userId");
		String type = request.getParameter("type");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", token);
		map.put("projectId", projectId);
		map.put("userId", userId);
		map.put("type", type);
		JSONObject obj = this.mobileService.getTopics(map);
		List<Map> list = (List<Map>) obj.get("topicList");
		for (int i = 0; i < list.size(); i++) {
			Map logmap = list.get(i);
			logmap.put("userImage",
					request.getScheme() + "://" + request.getServerName() + ":8088/file" + logmap.get("userImage"));
			List<Map> flist = (List<Map>) logmap.get("appendix");
			for (int j = 0; j < flist.size(); j++) {
				Map fmap = flist.get(j);
				fmap.put("fileUrl",
						request.getScheme() + "://" + request.getServerName() + ":8088/file" + fmap.get("FILE_PATH"));
				fmap.put("fileName", fmap.get("FILE_NAME"));
			}
		}

		return obj;
	}
	
	
	/**
	 * 获取单条问题讨论
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "getTopicById")
	@ResponseBody
	public JSONObject getTopicById(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		String token = request.getParameter("token");
		String topicId = request.getParameter("topicId");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", token);
		map.put("topicId", topicId);
		JSONObject obj = this.mobileService.getTopicById(map);
		List<Map> list = (List<Map>) obj.get("topicList");
		for (int i = 0; i < list.size(); i++) {
			Map logmap = list.get(i);
			logmap.put("userImage",
					request.getScheme() + "://" + request.getServerName() + ":8088/file" + logmap.get("userImage"));
			List<Map> flist = (List<Map>) logmap.get("appendix");
			for (int j = 0; j < flist.size(); j++) {
				Map fmap = flist.get(j);
				fmap.put("fileUrl",
						request.getScheme() + "://" + request.getServerName() + ":8088/file" + fmap.get("FILE_PATH"));
				fmap.put("fileName", fmap.get("FILE_NAME"));
			}
		}
		
		return obj;
	}

	/**
	 * 添加问题讨论回复
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "insertReply")
	@ResponseBody
	public JSONObject insertReply(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, String> messageMap = new HashMap<String, String>();
		// 先判断是否有文件
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// 如果有文件则处理文件
		if (isMultipart) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 先处理附件信息
			MobileFileUpload mfu = new MobileFileUpload();
			JSONArray json = mfu.packFile(multipartRequest);
			if (null != json) {
				map.put("json", json);
			}
		}

		String token = request.getParameter("token");
		String userId = request.getParameter("userId");
		String topicUid = request.getParameter("topicId");
		String time = request.getParameter("time");
		String content = request.getParameter("content");
		String account = request.getParameter("account");
		String className = request.getParameter("class");

		PmProjectTopicReplyVO vo = new PmProjectTopicReplyVO();

		vo.setCreate_date(new Date());
		vo.setCreate_user(userId);
		vo.setProject_topic_uid(topicUid);
		vo.setContent(content);

		map.put("token", token);
		map.put("reply", vo);
		
		messageMap.put("title", account+"给你回复了...");
		messageMap.put("content", content);
		messageMap.put("account", account);
		messageMap.put("id", topicUid);
		messageMap.put("className", className);
		
		JSONObject obj = this.mobileService.insertReply(map);
		PushMessage.messagePush(messageMap);
		return obj;
	}

	/**
	 * pm_xianchang 添加现场讨论回复
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "insertXianchang")
	@ResponseBody
	public JSONObject insertXianchang(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, String> messageMap = new HashMap<String, String>();
		// 先判断是否有文件
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// 如果有文件则处理文件
		if (isMultipart) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 先处理附件信息
			MobileFileUpload mfu = new MobileFileUpload();
			JSONArray json = mfu.packFile(multipartRequest);
			if (null != json) {
				map.put("json", json);
			}
		}
		String token = request.getParameter("token");
		String userId = request.getParameter("userId");
		String xc_uid = request.getParameter("xc_uid");
		String time = request.getParameter("time");
		String content = request.getParameter("content");
		String account = request.getParameter("account");
		String className = request.getParameter("class");
		PmXianchangDafuVO vo = new PmXianchangDafuVO();
		vo.setCreate_date(new Date());
		vo.setCreate_user(userId);
		vo.setXianchang_uid(xc_uid);
		vo.setContent(content);

		map.put("token", token);
		map.put("xianchangReply", vo);
		
		messageMap.put("title", account+"给你回复了...");
		messageMap.put("content", content);
		messageMap.put("account", account);
		messageMap.put("id", xc_uid);
		messageMap.put("className", className);
		
		JSONObject obj = this.mobileService.insertXianchang(map);
		//推送消息
		PushMessage.messagePush(messageMap);
		return obj;
	}

	/**
	 * 获取问题讨论回复列表
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "getReplyByTopicUid")
	@ResponseBody
	public JSONObject getReplyByTopicUid(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		String token = request.getParameter("token");
		String topicUid = request.getParameter("topicId");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", token);
		map.put("topicUid", topicUid);
		JSONObject obj = this.mobileService.getReplyByTopicUid(map);
		List<Map> list = (List<Map>) obj.get("commentList");
		// 组织图像及附件
		for (Map vmap : list) {
			vmap.put("userImage",
					request.getScheme() + "://" + request.getServerName() + ":8088/file" + vmap.get("userImage"));
			for (int i = 0; i < ((List) vmap.get("appendix")).size(); i++) {
				Map rmap = (Map) ((List) vmap.get("appendix")).get(i);
				rmap.put("fileUrl",
						request.getScheme() + "://" + request.getServerName() + ":8088/file" + rmap.get("FILE_PATH"));
				rmap.put("fileName", rmap.get("FILE_NAME"));
			}
		}

		return obj;
	}

	/**
	 * 发布现场状况信息
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "insertXcZkInfo")
	@ResponseBody
	public JSONObject insertXcZkInfo(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		HashMap<String, Object> map = new HashMap<String, Object>();
		// 先判断是否有文件
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// 如果有文件则处理文件
		if (isMultipart) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 先处理附件信息
			MobileFileUpload mfu = new MobileFileUpload();
			JSONArray json = mfu.packFile(multipartRequest);
			if (null != json) {
				map.put("json", json);
			}
		}
		String token = request.getParameter("token");
		String userId = request.getParameter("userId");
		String projectId = request.getParameter("projectId");
		String type = request.getParameter("type");// 类型
		String date = request.getParameter("date");
		String status = request.getParameter("state");
		String location = request.getParameter("location");
		String condition = request.getParameter("condition");
		String progress = request.getParameter("progress");
		String severity = request.getParameter("severity");
		String finishDate = request.getParameter("finishDate");

		PmXianchangVO vo = new PmXianchangVO();
		vo.setCreate_date(new Date());
		vo.setCreate_user(userId);
		vo.setProject_uid(projectId);
		vo.setGongkuang_type(type);// 类型
		vo.setStatus(status);// 状态 0 或 1
		vo.setJindu(progress);// 进度
		vo.setDescription(condition);// 现场状况描述
		vo.setFinish_date(DateUtil.formatDate2(finishDate));
		vo.setSeriousness(severity);

		map.put("token", token);
		map.put("locationId", location);
		JSONObject obj = this.mobileService.insertXczkInfo(map, vo);

		return obj;
	}
	/**
	 * 修改现场状况信息
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "updateXcZkInfo")
	@ResponseBody
	public JSONObject updateXcZkInfo(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		JSONObject obj = new JSONObject();
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> map2 = new HashMap<String, Object>();
		
		String token = request.getParameter("token");
		String userId = request.getParameter("userId");
		String projectId = request.getParameter("projectId");
		String type = request.getParameter("type");// 类型
		String date = request.getParameter("date");
		String status = request.getParameter("state");
		String location = request.getParameter("location");
		String condition = request.getParameter("condition");
		String progress = request.getParameter("progress");
		String severity = request.getParameter("severity");
		String finishDate = request.getParameter("finishDate");
		
		String xc_uid = request.getParameter("xc_uid");
		
		// 先判断是否有文件
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// 如果有文件则处理文件
		if (isMultipart) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 先处理附件信息
			MobileFileUpload mfu = new MobileFileUpload();
			JSONArray json = mfu.packFile(multipartRequest);
			if (null != json) {
				map.put("json", json);
			}
		}

		
		PmXianchangVO vo = new PmXianchangVO();
		vo.setProject_uid(projectId);
		vo.setGongkuang_type(type);// 类型
		vo.setStatus(status);// 状态 0 或 1
		vo.setJindu(progress);// 进度
		vo.setDescription(condition);// 现场状况描述
		vo.setXianchang_uid(xc_uid);
		vo.setFinish_date(DateUtil.formatDate2(finishDate));
		vo.setSeriousness(severity);
		
		map.put("token", token);
		map.put("locationId", location);
		obj = this.mobileService.updateXcZkInfo(map, vo);

		
		return obj;
	}
	/**
	 * 删除现场状况信息
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "deleteXcZkInfo")
	@ResponseBody
	public JSONObject deleteXcZkInfo(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		HashMap<String, Object> map = new HashMap<String, Object>();

		String token = request.getParameter("token");
		String xc_uid = request.getParameter("xc_uid");

		
		map.put("token", token);
		map.put("xc_uid", xc_uid);
		JSONObject obj = this.mobileService.deleteXcZkInfo(map);
		return obj;
	}

	/**
	 * 获取现场状况信息列表
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "getXcInfos")
	@ResponseBody
	public JSONObject getXcInfos(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		String token = request.getParameter("token");
		String projectId = request.getParameter("projectId");
		String userId = request.getParameter("userId");
		String type = request.getParameter("type");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", token);
		map.put("projectId", projectId);
		map.put("userId", userId);
		map.put("type", type);
		JSONObject obj = this.mobileService.getXcInfos(map);
		List<Map> list = (List<Map>) obj.get("conditionList");
		// 组织图像及附件
		for (Map vmap : list) {
			vmap.put("userImage",
					request.getScheme() + "://" + request.getServerName() + ":8088/file" + vmap.get("userImage"));
			for (int i = 0; i < ((List) vmap.get("appendix")).size(); i++) {
				Map rmap = (Map) ((List) vmap.get("appendix")).get(i);
				rmap.put("fileUrl",
						request.getScheme() + "://" + request.getServerName() + ":8088/file" + rmap.get("FILE_PATH"));
				rmap.put("fileName", rmap.get("FILE_NAME"));
				rmap.put("fileDescribe", rmap.get("DESCRIPTION"));
			}
		}

		return obj;
	}
	
	/**
	 * 获取现场状况信息列表
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "getXcInfoById")
	@ResponseBody
	public JSONObject getXcInfoById(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		String token = request.getParameter("token");
		String xc_uid = request.getParameter("xc_uid");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", token);
		map.put("xc_uid", xc_uid);
		JSONObject obj = this.mobileService.getXcInfoById(map);
		List<Map> list = (List<Map>) obj.get("conditionList");
		// 组织图像及附件
		for (Map vmap : list) {
			vmap.put("userImage",
					request.getScheme() + "://" + request.getServerName() + ":8088/file" + vmap.get("userImage"));
			for (int i = 0; i < ((List) vmap.get("appendix")).size(); i++) {
				Map rmap = (Map) ((List) vmap.get("appendix")).get(i);
				rmap.put("fileUrl",
						request.getScheme() + "://" + request.getServerName() + ":8088/file" + rmap.get("FILE_PATH"));
				rmap.put("fileName", rmap.get("FILE_NAME"));
				rmap.put("fileDescribe", rmap.get("DESCRIPTION"));
			}
		}
		
		return obj;
	}

	/**
	 * 获取现场状况信息回复列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "getXcResponse")
	@ResponseBody
	public JSONObject getXcResponse(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		String token = request.getParameter("token");
		String xc_uid = request.getParameter("xc_uid");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", token);
		map.put("xc_uid", xc_uid);
		JSONObject obj = this.mobileService.getXcResponse(map);
		List<Map> list = (List<Map>) obj.get("commentList");
		// 组织图像及附件
		for (Map vmap : list) {
			vmap.put("userImage",
					request.getScheme() + "://" + request.getServerName() + ":8088/file" + vmap.get("userImage"));
			for (int i = 0; i < ((List) vmap.get("appendix")).size(); i++) {
				Map rmap = (Map) ((List) vmap.get("appendix")).get(i);
				rmap.put("fileUrl",
						request.getScheme() + "://" + request.getServerName() + ":8088/file" + rmap.get("FILE_PATH"));
				rmap.put("fileName", rmap.get("FILE_NAME"));
			}
		}

		return obj;
	}

	/**
	 * 移动端获取项目列表
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "getProject")
	@ResponseBody
	public JSONObject getProject(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		//User user = RestContext.getCurrentUser();
		//logger.info("<{}>执行操作【获取项目列表】", user.getName());
		String token = request.getParameter("token");
		String userId = request.getParameter("userId");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", token);
		map.put("userId", userId);
		JSONObject obj = this.mobileService.queryProjectList(map);
		List<Map<String, String>> list = (List<Map<String, String>>) obj.get("project");
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> map0 = list.get(i);
				// map0.put("photoUrl",
				// "http://"+request.getServerName()+":"+request.getServerPort()+"/file"+map0.get("photoUrl"));
				map0.put("photoUrl",
						request.getScheme() + "://" + request.getServerName() + ":8088/file" + map0.get("photoUrl"));
			}
		}

		return obj;
	}

	/**
	 * 移动端数据查询工况和天气
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "queryJcWorkAndWeather")
	@ResponseBody
	public JSONObject queryJcWorkAndWeather(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		//User user = RestContext.getCurrentUser();
		//logger.info("<{}>执行操作【工况和天气查询】", user.getName());
		String token = request.getParameter("token");
		String projectid = request.getParameter("projectId");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", token);
		map.put("projectid", projectid);
		JSONObject obj = this.mobileService.queryJcWorkAndWeather(map);

		return obj;
	}

	/**
	 * 移动端数据查询正常点
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "queryJcGetPoint")
	@ResponseBody
	public JSONObject queryJcNormalPoint(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		//User user = RestContext.getCurrentUser();
		//logger.info("<{}>执行操作【监测数据查询】", user.getName());
		String token = request.getParameter("token");
		String project_uid = request.getParameter("projectId");
		String type = request.getParameter("type");
		String userId = request.getParameter("userId");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", token);
		map.put("project_uid", project_uid);
		map.put("type", type);
		map.put("userId", userId);
		JSONObject obj = this.mobileService.queryJcNormalPoint(map);

		return obj;
	}

	/**
	 * 移动端数据查询测点数据
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "queryJcPointData")
	@ResponseBody
	public JSONObject queryJcPointData(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		//User user = RestContext.getCurrentUser();
		//logger.info("<{}>执行操作【监测数据查询】", user.getName());
		String token = request.getParameter("token");
		String number = request.getParameter("number");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String type = request.getParameter("type");
		String project_uid = request.getParameter("projectId");
		String pointId = request.getParameter("pointId");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", token);
		map.put("number", number);
		map.put("type", type);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("projectId", project_uid);
		map.put("pointId", pointId);
		JSONObject obj = this.mobileService.queryJcPointData(map);

		return obj;
	}

	/**
	 * 移动端数据查询测点折线图和柱状图数据
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "queryJcCurveData")
	@ResponseBody
	public JSONObject queryJcCurveData(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		//User user = RestContext.getCurrentUser();
		//logger.info("<{}>执行操作【监测数据查询】", user.getName());
		String token = request.getParameter("token");
		String number = request.getParameter("number");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String type = request.getParameter("type");
		String project_uid = request.getParameter("projectId");
		String pointId = request.getParameter("pointId");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", token);
		map.put("number", number);
		map.put("type", type);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("projectId", project_uid);
		map.put("pointId", pointId);
		JSONObject obj = this.mobileService.queryJcCurveData(map);

		return obj;
	}

	/**
	 * 移动端数据查询测点信息
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "queryJcPointInfo")
	@ResponseBody
	public JSONObject queryJcPointInfo(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		//User user = RestContext.getCurrentUser();
		//logger.info("<{}>执行操作【监测数据查询】", user.getName());
		String token = request.getParameter("token");
		String number = request.getParameter("number");
		String typeUid = request.getParameter("type");
		String project_uid = request.getParameter("projectId");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", token);
		map.put("number", number);
		map.put("typeUid", typeUid);
		map.put("projectId", project_uid);
		JSONObject obj = this.mobileService.queryJcPointInfo(map);

		return obj;
	}

	/**
	 * 移动端查询测斜类数据
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "querySurveyDataList")
	@ResponseBody
	public JSONObject querySurveyDataList(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		//User user = RestContext.getCurrentUser();
		//logger.info("<{}>执行操作【监测数据查询】", user.getName());
		String token = request.getParameter("token");
		String code = request.getParameter("pointId");
		String time1 = request.getParameter("startDate");
		String time2 = request.getParameter("endDate");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", token);
		map.put("code", code);
		map.put("time1", time1);
		map.put("time2", time2);
		JSONObject obj = this.mobileService.querySurveyDataList(map);

		return obj;
	}
	
	/**
	 * 删除附件列表
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "deleteFile")
	@ResponseBody
	public JSONObject deleteFile(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		//User user = RestContext.getCurrentUser();
		//logger.info("<{}>执行操作【监测数据查询】", user.getName());
		String token = request.getParameter("token");
		String file_uid = request.getParameter("file_uid");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("token", token);
		map.put("file_uid", file_uid);
		JSONObject obj = this.mobileService.deleteFile(map);
		
		return obj;
	}
	
	
	/**
	 * 获取问题讨论列表
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "getFiles")
	@ResponseBody
	public JSONObject getFiles(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		String token = request.getParameter("token");
		String projectId = request.getParameter("projectId");
		String P_DOCS_UID =  request.getParameter("P_DOCS_UID");

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", token);
		map.put("projectId", projectId);
		map.put("P_DOCS_UID", P_DOCS_UID);
		JSONObject obj = this.mobileService.getFiles(map);
		List<Map> list = (List<Map>) obj.get("dataList");
		for (int i = 0; i < list.size(); i++) {
			Map logmap = list.get(i);
			logmap.put("FILE_PATH",
					request.getScheme() + "://" + request.getServerName() + ":8088/file" + logmap.get("FILE_PATH"));
			
		}

		return obj;
	}
	
	/**
	 * 插入阅读信息
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "insertReadInfo")
	@ResponseBody
	public JSONObject insertReadInfo(final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		HashMap<String, Object> map = new HashMap<String, Object>();

		String token = request.getParameter("token");
		String userId = request.getParameter("userId");
		String userName = request.getParameter("name");
		String projectId = request.getParameter("projectId");
		String targetCode = request.getParameter("targetCode");
		String targetUid = request.getParameter("targetUid");


		LogUserReadVO vo = new LogUserReadVO();
		vo.setLogin_session(request.getSession().getId());
		vo.setUser_uid(userId);
		vo.setUser_name(userName);
		vo.setProject_uid(projectId);
		vo.setTarget_code(targetCode);
		vo.setTarget_uid(targetUid);
		vo.setRead_time(new Date());


		map.put("token", token);
		map.put("vo", vo);
		JSONObject obj = this.mobileService.insertReadInfo(map);

		return obj;
	}
	
	
	/**
	 * 权限检测
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "checkBtnAuthority")
	@ResponseBody
	public JSONObject checkBtnAuthority(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		String token = request.getParameter("token");
		String projectId = request.getParameter("projectId");
		String projectUserId =  request.getParameter("projectUserId");
		String authorityCode =  request.getParameter("authorityCode");

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", token);
		map.put("projectId", projectId);
		map.put("projectUserId", projectUserId);
		map.put("authorityCode", authorityCode);
		JSONObject obj = this.mobileService.checkBtnAuthority(map);
		
		return obj;
	}
	
	/**
	 * 获取版本号
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "getVersion")
	@ResponseBody
	public JSONObject getVersion(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		String token = request.getParameter("token");
		JSONObject obj = new JSONObject();
		String version = "0.7.6.9";
		obj.put("version", version);
		return obj;
	}

}
