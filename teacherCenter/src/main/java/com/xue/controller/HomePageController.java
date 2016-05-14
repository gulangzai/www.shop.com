package com.xue.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xue.dto.BsUserInfoDetail;
import com.xue.interceptor.CommonInterceptor;
import com.xue.listener.SessionListener; 
import com.xue.model.BsConsumeInfo;
import com.xue.model.BsUserInfo; 
import com.xue.model.BsCallbackInfo;
import com.xue.model.BsNotice; 
import com.xue.model.BsUserInfoDetails;
import com.xue.model.BsUserPayInfo;
import com.xue.model.BsUserShopSet;
import com.xue.model.BsuserOpinion;
import com.xue.model.KnowledgePoint;
import com.xue.model.StudentHighScore;
import com.xue.model.StudentInfo;
import com.xue.model.StudentScore;
import com.xue.model.User;
import com.xue.service.BsUserInfoService;
import com.xue.service.BsUserShopSetService;
import com.xue.service.StudentInfoService;
import com.xue.service.UserService;
import com.xue.vo.query.BsNoticeQuery;

import javacommon.base.CommonController;
import javacommon.base.ResultAction;
import javacommon.util.CookieUtil;
import javacommon.util.DateUtils;
import javacommon.util.MD5Utils;
import javacommon.util.SafeUtils;

@Controller
@RequestMapping("/homePage")
public class HomePageController extends CommonController{
    private final Logger log = LoggerFactory.getLogger(HomePageController.class);  

	@Autowired
	StudentInfoService studentInfoService;
	@Autowired
	UserService userService;
	@Autowired
	private BsUserInfoService bsUserInfoService;
	@Autowired
	private BsUserShopSetService bsUserShopSetService;
	
	@RequestMapping("/toIndex")
	public String toIndex(){ 
		return "/modules/homepage/login";
	}
	 
	@RequestMapping("/toLogin") 
	public String login(String userName,String password,String checkboxmark,Model model,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		System.out.println("userName:"+userName+"password:"+password+"checkboxmark:"+checkboxmark);
		String fromUrl = (String)request.getParameter("fromUrl");
		try {
			if(userName ==null||userName.equals("") || password== null||password.equals("")){
				model.addAttribute("message","用户名和密码不能为空!");
				return "/modules/homepage/login";
			}
			String newpassword = MD5Utils.createMD5(password);
			BsUserInfoDetail teacher = bsUserInfoService.login(userName,newpassword);  
			  CookieUtil cookieUtil = new CookieUtil();
			if(teacher != null&&teacher.getPassword().equals(newpassword)){
				session.setAttribute("teacher", teacher);
				session.setAttribute("loginname", teacher.getUserName()); 
				teacher.setPassword(newpassword); 
				if(checkboxmark=="1"||checkboxmark.equals("1")){
					cookieUtil.addCookie(response, "userName",userName, 7*24*60*60); 
					cookieUtil.addCookie(response,"password",password,7*24*60*60);
					cookieUtil.addCookie(response,"checkboxmark",checkboxmark,7*24*60*60); 
				} else{
					cookieUtil.delCookie(request, "userName", response);
					cookieUtil.delCookie(request, "password", response);
					cookieUtil.delCookie(request, "checkboxmark", response);
				}
				SessionListener.replaceUserSession(session, teacher.getUserId());
			/*	teacher.setLastLoginTime(teacher.getCurrentLoginTime());
				teacher.setCurrentLoginTime(DateUtils.getTodayTime());
				studentInfoService.updateByPrimaryKeySelective(teacher);*/
			    log.info("-------登录成功");
				return "redirect:/homeMain/toMain.do"; 	 
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
	
	@RequestMapping("/toExit") 
	public String exit(String userId,String password,String checkboxmark,Model model,HttpServletRequest request,HttpServletResponse response,HttpSession session){
	    try {
			session.removeAttribute("teacher");
			session.removeAttribute("loginname");  
			SessionListener.removeUserSession(userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			e.printStackTrace(); 
		}
		return "/modules/homepage/login";
	}

	private StudentInfoService updateByPrimaryKeySelective(StudentInfo teacher) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @Description:展示首页
	 * @param model
	 * @return
	 * String
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2016年1月17日 下午2:11:16
	 */
	@RequestMapping("/showHome") 
	public String showHome(Model model,HttpSession session){
		try {
			BsUserInfoDetail bsUserInfoDetails=(BsUserInfoDetail) session.getAttribute("teacher");
			BsUserPayInfo bsUserPayInfo = new BsUserPayInfo();
			BsUserInfoDetail bsUserInfoDetail=bsUserInfoService.findByUserId(bsUserInfoDetails.getUserId());
			List<BsUserPayInfo> bsUserPayInfos=bsUserInfoService.findUserPayInfo(bsUserInfoDetails.getUserId());
			Integer bsNotices=bsUserInfoService.findNoticeCount();
			Integer useMoneys=bsUserShopSetService.findAllPayInfo(bsUserInfoDetails.getUserId());
			List<BsuserOpinion> bsuserOpinions=bsUserInfoService.findOpinion(bsUserInfoDetails.getUserId());
			Integer payMoneys=bsUserInfoService.findAnnualConsumption(bsUserInfoDetails.getUserId());
			if(bsUserPayInfos.size()>0){
				bsUserPayInfo = bsUserPayInfos.get(0);
			}
			model.addAttribute("payMoneys", payMoneys);
			model.addAttribute("bsNotices", bsNotices);
			model.addAttribute("bsUserInfoDetail", bsUserInfoDetail);
			model.addAttribute("bsUserPayInfo", bsUserPayInfo);
			model.addAttribute("useMoneys", useMoneys);
			model.addAttribute("bsuserOpinions", bsuserOpinions);
			
			//model.addAttribute("bsCallbackInfos", bsCallbackInfos);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			logger.error(e.getMessage());
		}
		
		return "/modules/homepage/showHome";
	}
	/**
	 * @Description:寻找所有的网校通知
	 * @return
	 * String
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2016年1月18日 下午2:13:20
	 */
	@RequestMapping("/fullNotice")
	@ResponseBody
	public ResultAction<List<BsNotice>> fullNotice(int pageIndex,int pageSize){
		ResultAction<List<BsNotice>> resultAction = new ResultAction<List<BsNotice>>();
		try {
			BsNoticeQuery bsNoticeQuery = new BsNoticeQuery();
			bsNoticeQuery.setPageNumber(pageIndex);
			bsNoticeQuery.setPageSize(pageSize);
			List<BsNotice> bsNotices=bsUserInfoService.findNotice(bsNoticeQuery);
			if(bsNotices.size()>0){
				resultAction.setData(bsNotices);
				resultAction.setIserror(false);
			}else{
				resultAction.setIserror(true);
				resultAction.setMessage("没有知识点");
			}
		} catch (Exception e) {
			resultAction.setIserror(true);
			resultAction.setMessage("系统错误");
			logger.error(e.getLocalizedMessage());
		}
		return resultAction;
	}
	/**
	 * @Description:寻找所有的网校通知
	 * @return
	 * String
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2016年1月18日 下午2:13:20
	 */
	@RequestMapping("/submitFeedback")
	@ResponseBody
	public ResultAction<String> submitFeedback(String feedback,HttpSession session){
		ResultAction<String> resultAction = new ResultAction<String>();
		try {
			BsUserInfoDetail bsUserInfoDetail=(BsUserInfoDetail) session.getAttribute("teacher");
			int result=bsUserInfoService.insertBsuserOpinion(feedback,bsUserInfoDetail);
			if(result>0){
				resultAction.setIserror(false);
				resultAction.setMessage("反馈成功");
			}else{
				resultAction.setIserror(true);
				resultAction.setMessage("反馈失败,请重新反馈");
			}
		} catch (Exception e) {
			resultAction.setIserror(true);
			resultAction.setMessage("系统错误");
			logger.error(e.getMessage());
		}
		return resultAction;
	}
	
	/**
	 * @Description:展示开课情况
	 * @return
	 * String
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2016年1月19日 下午1:16:05
	 */
	@RequestMapping("/showClassInfo")
	public String showClassInfo(){
		return "/modules/homepage/showClassInfo";
	}
	/**
	 * @Description:展示开课情况
	 * @return
	 * String
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2016年1月19日 下午1:16:05
	 */
	@RequestMapping("/showAchivementInfo")
	public String showAchivementInfo(Model model){
		try {
			List<BsUserShopSet> bsUserShopSets=bsUserShopSetService.findAllMajor("teacher");
			if(bsUserShopSets.size()>0){
				model.addAttribute("bsUserShop", bsUserShopSets.get(0));
			}
			model.addAttribute("bsUserShopSets", bsUserShopSets);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}
		return "/modules/homepage/showAchivementInfo";
	}
	/**
	 * @Description:TODO
	 * @return
	 * ResultAction<List<StudentScore<List<Float>>>>
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2016年1月25日 下午1:20:16
	 */
	@RequestMapping("/getMounthAchivementInfo")
	@ResponseBody
	public ResultAction<List<StudentScore<List<Float>>>>   getMounthAchivementInfo(Integer mouth,String marjorId,HttpSession session){
		ResultAction<List<StudentScore<List<Float>>>> resultAction = new ResultAction<List<StudentScore<List<Float>>>>();
		try {
			BsUserInfoDetail bsUserInfoDetail=(BsUserInfoDetail) session.getAttribute("teacher");
			List<StudentHighScore> studentHighScores=bsUserInfoService.getMouthAchivementInfo(bsUserInfoDetail.getUserId(),marjorId,mouth);//班级日期
			List<StudentHighScore> highScores=bsUserInfoService.getMouthAchivementInfoByClassId(bsUserInfoDetail.getUserId(),marjorId,mouth);//班级分组
			List<List<StudentHighScore>> lists = new ArrayList<List<StudentHighScore>>();
			List<StudentScore<List<Float>>> studentScores = new ArrayList<StudentScore<List<Float>>>();
			for (int i = 0; i < highScores.size(); i++) {
				List<StudentHighScore> scores = new ArrayList<StudentHighScore>();
				for (int j = 0; j < studentHighScores.size(); j++) {
					if(highScores.get(i).getClassId().equalsIgnoreCase(studentHighScores.get(j).getClassId())){
						scores.add(studentHighScores.get(j));
					}
				}
				lists.add(scores);
			}
			for (int i = 0; i < lists.size(); i++) {
				StudentScore<List<Float>> stu = new StudentScore<List<Float>>();
				stu.setName(lists.get(i).get(0).getClassName());
				studentScores.add(stu);
			}
			for (int i = 0; i < studentScores.size(); i++) {
				List<Float> allgrade = new ArrayList<Float>();
				for (int z = 0; z < studentHighScores.size(); z++) {
					if(studentScores.get(i).getName().equalsIgnoreCase(studentHighScores.get(z).getClassName())){
						allgrade.add(studentHighScores.get(z).getScore());
					}
				}
				List<Float> allgrade1 = new ArrayList<Float>();
				allgrade1.addAll(allgrade);
				studentScores.get(i).setData(allgrade1);
				allgrade.removeAll(allgrade);
			}
			resultAction.setData(studentScores);
			resultAction.setIserror(false);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			resultAction.setIserror(true);
		}
		return resultAction;
	}
	/**
	 * @Description:展示开课情况
	 * @return
	 * ResultAction<List<StudentScore<List<Float>>>>
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2016年1月21日 下午4:53:15
	 */
	@RequestMapping("/getAchivementInfo")
	@ResponseBody
	public ResultAction<List<StudentScore<List<Float>>>>  getAchivementInfo(String marjorId,HttpSession session){
		ResultAction<List<StudentScore<List<Float>>>> resultAction = new ResultAction<List<StudentScore<List<Float>>>>();
		try {
			BsUserInfoDetail bsUserInfoDetail=(BsUserInfoDetail) session.getAttribute("teacher");
			List<StudentScore<List<Float>>> studentScores = new ArrayList<StudentScore<List<Float>>>();
			List<StudentHighScore> studentHighScores=bsUserInfoService.getAchivementInfo(bsUserInfoDetail.getUserId(),marjorId);//班级日期先写死
			List<StudentHighScore> highScores=bsUserInfoService.getAchivementInfoByClassId(bsUserInfoDetail.getUserId(),marjorId);//班级分组
			//List<StudentHighScore> monthScores=bsUserInfoService.getAchivementInfoByMonth();//日期
			List<List<StudentHighScore>> lists = new ArrayList<List<StudentHighScore>>();
			for (int i = 0; i < highScores.size(); i++) {
				List<StudentHighScore> scores = new ArrayList<StudentHighScore>();
				for (int j = 0; j < studentHighScores.size(); j++) {
					if(highScores.get(i).getClassId().equalsIgnoreCase(studentHighScores.get(j).getClassId())){
						scores.add(studentHighScores.get(j));
					}
				}
				lists.add(scores);
			}
			for (int i = 0; i < lists.size(); i++) {
				StudentScore<List<Float>> stu = new StudentScore<List<Float>>();
				stu.setName(lists.get(i).get(0).getClassName());
				studentScores.add(stu);
			}
			for (int i = 0; i < studentScores.size(); i++) {
				List<Float> allgrade = new ArrayList<Float>();
				for (int j = 1; j <= 12; j++) {
					boolean isTrue = false;
					for (int z = 0; z < studentHighScores.size(); z++) {
						if(SafeUtils.getInt(studentHighScores.get(z).getMonths())==j&&studentScores.get(i).getName().equalsIgnoreCase(studentHighScores.get(z).getClassName())){
							allgrade.add(studentHighScores.get(z).getScore());
							isTrue =true;
							break;
						}
					}
					if(!isTrue)
						allgrade.add(0f);
				}
				List<Float> allgrade1 = new ArrayList<Float>();
				allgrade1.addAll(allgrade);
				studentScores.get(i).setData(allgrade1);
				allgrade.removeAll(allgrade);
			}
			resultAction.setData(studentScores);
			resultAction.setIserror(false);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			resultAction.setIserror(true);
		}
		return resultAction;
	}
	/**
	 * @Description:更新用户信息
	 * @param bsUserInfo
	 * @return
	 * ResultAction<String>
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2016年1月20日 下午2:32:32
	 */
	@RequestMapping("/updateUserInfo")
	@ResponseBody
	public ResultAction<String> updateUserInfo(BsUserInfoDetails bsUserInfo){
		ResultAction<String> resultAction = new ResultAction<String>();
		try {
			bsUserInfo.setUserId("teacher");
			bsUserInfoService.updateBsUserInfo(bsUserInfo);
			resultAction.setIserror(false);
			resultAction.setMessage("更新成功");
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultAction.setIserror(true);
		}
		return resultAction;
	}
	
	/**
	 * @Description:TODO
	 * @param model
	 * @param session
	 * @param majorId
	 * @param subjectId
	 * @return
	 * ResultAction<List<StudentScore<List<Float>>>>
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2016年1月19日 下午3:03:15
	 */
	@RequestMapping("/getMajorCount")
	@ResponseBody
	public ResultAction<List<StudentScore>> getMajorCount(Model model,HttpSession session,String majorId,String subjectId){
		ResultAction<List<StudentScore>> resultAction = new ResultAction<List<StudentScore>>();
		try {
			List<StudentScore> dataList = new ArrayList<StudentScore>();
			BsUserInfoDetail bsUserInfoDetail=(BsUserInfoDetail) session.getAttribute("teacher");
			List<BsUserShopSet> bsUserShopSets=bsUserInfoService.findAllMajor(bsUserInfoDetail.getUserId());
			if(bsUserShopSets.size()>0){
				List<BsUserShopSet> bsMajorUserShopSets=bsUserInfoService.findMajor(bsUserInfoDetail.getUserId());
				for (int i = 0; i < bsMajorUserShopSets.size(); i++) {
					StudentScore stu = new StudentScore();
					Integer count = 0;
					stu.setName(bsMajorUserShopSets.get(i).getMajorName());
					for (int j = 0; j < bsUserShopSets.size(); j++) {
						if(bsMajorUserShopSets.get(i).getMajorId().equalsIgnoreCase(bsUserShopSets.get(j).getMajorId())){
							count++;
							stu.setData(count);
						}
					}
					dataList.add(stu);
				}
			}
			resultAction.setData(dataList);
			resultAction.setIserror(false);
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultAction.setIserror(true);
		}
		return resultAction;
	}
	/**
	 * @Description:读取用户已经读过的通知
	 * @param bsUserInfo
	 * @return
	 * ResultAction<String>
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2016年1月20日 下午2:32:32
	 */
	@RequestMapping("/readNotice")
	@ResponseBody
	public ResultAction<Integer> readNotice(){
		ResultAction<Integer> resultAction = new ResultAction<Integer>();
		try {
			int readNoticeCount=bsUserInfoService.readNotice();
			resultAction.setIserror(false);
			resultAction.setMessage("更新成功");
			resultAction.setData(readNoticeCount);
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultAction.setIserror(true);
		}
		return resultAction;
	}
	/**
	 * @Description:读取用户已经读过的通知
	 * @param bsUserInfo
	 * @return
	 * ResultAction<String>
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2016年1月20日 下午2:32:32
	 */
	@RequestMapping("/readNoNotice")
	@ResponseBody
	public ResultAction<Integer> readNoNotice(){
		ResultAction<Integer> resultAction = new ResultAction<Integer>();
		try {
			int readNoticeCount=bsUserInfoService.readNoNotice();
			resultAction.setIserror(false);
			resultAction.setMessage("更新成功");
			resultAction.setData(readNoticeCount);
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultAction.setIserror(true);
		}
		return resultAction;
	}
	/**
	 * @Description:读取用户未读的通知分页
	 * @return
	 * ResultAction<List<BsNotice>>
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2016年1月21日 上午11:19:00
	 */
	@RequestMapping("/readNoPageNotice")
	@ResponseBody
	public ResultAction<List<BsNotice>> readNoPageNotice(int pageIndex,int pageSize){
		ResultAction<List<BsNotice>> resultAction = new ResultAction<List<BsNotice>>();
		try {
			BsNoticeQuery bsNoticeQuery = new BsNoticeQuery();
			bsNoticeQuery.setPageNumber(pageIndex);
			bsNoticeQuery.setPageSize(pageSize);
			List<BsNotice> bsNotices=bsUserInfoService.readNoPageNotice(bsNoticeQuery);
			if(bsNotices.size()>0){
				resultAction.setData(bsNotices);
				resultAction.setIserror(false);
			}else{
				resultAction.setIserror(true);
				resultAction.setMessage("没有知识点");
			}
		} catch (Exception e) {
			resultAction.setIserror(true);
			resultAction.setMessage("系统错误");
			logger.error(e.getMessage());
		}
		return resultAction;
	}
	/**
	 * @Description:读取用户已经读过的通知分页
	 * @return
	 * ResultAction<List<BsNotice>>
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2016年1月21日 上午11:19:00
	 */
	@RequestMapping("/readPageNotice")
	@ResponseBody
	public ResultAction<List<BsNotice>> readPageNotice(int pageIndex,int pageSize){
		ResultAction<List<BsNotice>> resultAction = new ResultAction<List<BsNotice>>();
		try {
			BsNoticeQuery bsNoticeQuery = new BsNoticeQuery();
			bsNoticeQuery.setPageNumber(pageIndex);
			bsNoticeQuery.setPageSize(pageSize);
			List<BsNotice> bsNotices=bsUserInfoService.readPageNotice(bsNoticeQuery);
			if(bsNotices.size()>0){
				resultAction.setData(bsNotices);
				resultAction.setIserror(false);
			}else{
				resultAction.setIserror(true);
				resultAction.setMessage("没有知识点");
			}
		} catch (Exception e) {
			resultAction.setIserror(true);
			resultAction.setMessage("系统错误");
			logger.error(e.getMessage());
		}
		return resultAction;
	}
}
