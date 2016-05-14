package com.xue.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xue.dto.OpenCourseRecodeQuery;
import com.xue.dto.OpenCourseRecodeResult;
import com.xue.dto.PageBean;
import com.xue.dto.StudentAndShop;
import com.xue.model.BsUserInfo;
import com.xue.model.Classes;
import com.xue.model.CourseMajor;
import com.xue.model.CourseMajorSubject;
import com.xue.service.BsUserShopSetService;
import com.xue.service.ClassesService;
import com.xue.service.CourseMajorService;
import com.xue.service.CourseMajorSubjectService;
import com.xue.service.ShopService;
import javacommon.base.CommonController;
import javacommon.base.ResultAction;

@Controller
@RequestMapping("/openRecode")
public class OpenRecodeController extends CommonController {
	
	@Autowired
	private ShopService shopService;
	
	@Autowired
	private BsUserShopSetService bsUserShopSetService;
	  
	@RequestMapping("/openRecode")
	public String openRecode(Model model){ 
		return "/modules/courseOpen/openRecode";
	}
	
	/*获取开通记录*/
	@RequestMapping("/getOpenCourseRecode")
	@ResponseBody
	public ResultAction getOpenCourseRecode(HttpSession session,String currentPage,String studentId,String mobile,String studentName){
		ResultAction result = new ResultAction();
		OpenCourseRecodeQuery openCourseRecode = new OpenCourseRecodeQuery(Integer.parseInt(currentPage));
		openCourseRecode.setStudentId(studentId);
		openCourseRecode.setMobile(mobile);
		BsUserInfo bsUserInfo = getCurrentStudentInfo(session);
		openCourseRecode.setUserId(bsUserInfo.getUserId());
		openCourseRecode.setStudentId(studentId);
		openCourseRecode.setStudentName(studentName);
		List<OpenCourseRecodeResult> openCourseRecodeResults  = bsUserShopSetService.getOpenCourseRecode(openCourseRecode); 
		int count = bsUserShopSetService.getOpenCourseRecodeCount(openCourseRecode);
		PageBean pb = new PageBean();
		pb.setObj(openCourseRecodeResults);
		pb.setAllRecodeNum(count);
		result.setData(pb);
		return result;
	}
}
