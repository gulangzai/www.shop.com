package com.xue.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xue.dto.OpenCourseStatusResult;
import com.xue.dto.SelfNoticeQuery;
import com.xue.dto.StudentAndShop;
import com.xue.model.CourseMajor;
import com.xue.model.CourseMajorSubject;
import com.xue.model.Notice;
import com.xue.model.Suggest;
import com.xue.model.User;
import com.xue.service.CourseMajorService;
import com.xue.service.CourseMajorSubjectService;
import com.xue.service.NoticeService;
import com.xue.service.SuggestService;
import com.xue.service.UserService;

import javacommon.base.CommonController;
import javacommon.base.ResultAction;


@RequestMapping("/firstPage")
@Controller
public class FirstPageController extends CommonController{
	
	@Autowired
	UserService userService;
	@Autowired
	CourseMajorSubjectService courseMajorSubjectService;
	@Autowired
	CourseMajorService courseMajorService;
	@Autowired
	NoticeService noticeService;
	@Autowired
	SuggestService suggestService;
	
	/*获取老师个人信息*/
	@RequestMapping("/getSelfInformation")
	@ResponseBody
	public ResultAction getSelfInformation(HttpSession session){  
		ResultAction resultAction = new ResultAction(); 
	   /* User user = getCurrentStudentInfo(session);
		resultAction.setData(user);*/
		return resultAction;
	}
	
	/*更新个人信息*/
	@RequestMapping("/updateInformation")
	@ResponseBody
	public ResultAction updateInformation(HttpSession session,String userName,String userId,String password,String mobileNumber,String organization){
		ResultAction resultAction = new ResultAction();
	/*	User user = getCurrentStudentInfo(session);
		user.setUserName(userName);
		user.setPassword(password);
		user.setMobileNumber(mobileNumber);
		user.setOrganization(organization);
		userService.updateByPrimaryKeySelective(user);
		resultAction.setData(user);*/
		return resultAction;
	}
	
	/*开课情况*/
	@RequestMapping("/openCourseStatus")
	@ResponseBody
	public ResultAction openCourseStatus(){
		ResultAction resultAction = new ResultAction();
		List<CourseMajor> courseMajors = courseMajorService.findCourseMajor();
		int all = 0;
		OpenCourseStatusResult result = new OpenCourseStatusResult();  
		int a[] = {}; 
		for (CourseMajor courseMajor : courseMajors) {
			List<CourseMajorSubject> couseMajorSubjects = courseMajorSubjectService.findAllSubjectByMajorId(courseMajor.getMajorId());
		    a[a.length+1]=couseMajorSubjects.size();
		    all=all+couseMajorSubjects.size();
		}
		for(int i=0;i<a.length;i++){
			float rate = a[i]/all;
			result.getRateList().add(rate+"%");
		}
		resultAction.setData(result);
		return resultAction;
	}
	
	/*
	 * 业绩统计
	 * */
	
	
	/*
	 * 关于网校通知
	 * */
	@RequestMapping("/getSelfNotice")
	@ResponseBody
	public ResultAction getSelfNotice(HttpSession session){  
		ResultAction resultAction = new ResultAction(); 
	 /*   User user = getCurrentStudentInfo(session);
	    SelfNoticeQuery selfNotice = new SelfNoticeQuery();
	    selfNotice.setUserId(user.getUserId());
	    List<Notice> notices = noticeService.getSelfNotice(selfNotice);
		resultAction.setData(user);*/
		return resultAction;
	}
	
    /*意见反馈*/
	@RequestMapping("/getAllSuggest")
	@ResponseBody
	public ResultAction getAllSuggest(HttpSession session){  
		ResultAction resultAction = new ResultAction(); 
		/*User user = getCurrentStudentInfo(session);  
	    List<Suggest> suggests = suggestService.getAllSuggest();
		resultAction.setData(suggests);*/
		return resultAction;
	} 

}
