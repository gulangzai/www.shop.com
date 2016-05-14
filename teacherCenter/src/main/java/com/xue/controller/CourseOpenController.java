package com.xue.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xue.dto.CourseOpenResult;
import com.xue.dto.OpenCourseQuery;
import com.xue.dto.OpenCourseRecodeQuery;
import com.xue.dto.PageBean;
import com.xue.dto.StudentAndShop;
import com.xue.model.BsUserInfo;
import com.xue.model.BsUserShopSet;
import com.xue.model.Classes;
import com.xue.model.CourseMajor;
import com.xue.model.CourseMajorSubject;
import com.xue.model.Shop;
import com.xue.model.StudentInfo;
import com.xue.model.StudentInfoDetail;
import com.xue.model.UserInfo;
import com.xue.service.BsUserShopSetService;
import com.xue.service.ClassesService;
import com.xue.service.CourseMajorService;
import com.xue.service.CourseMajorSubjectService;
import com.xue.service.ShopService;
import com.xue.service.StudentInfoService;
import com.xue.service.StudentShopSetService;
import com.xue.vo.query.ShopQuery;

import javacommon.base.CommonController;
import javacommon.base.ResultAction;
import javacommon.util.DateUtils;
import javacommon.util.DbUtil;
import sun.util.logging.resources.logging;

/*
 * 课程开通
 ***/

@Controller
@RequestMapping(value="/courseOpen")
public class CourseOpenController extends CommonController {
    private final Logger log = LoggerFactory.getLogger(CourseOpenController.class);  
	
	@Autowired
	private CourseMajorService courseMajorService;
	@Autowired
	private CourseMajorSubjectService courseMajorSubjectService;
	@Autowired
	private ClassesService classService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private StudentShopSetService studentShopSetService;
	@Autowired
	private StudentInfoService studentInfoService;
	@Autowired
	private BsUserShopSetService bsUserShopSetService;
	
	
	/*
	 * 获取课程开通
	 * */
	@RequestMapping("/courseOpenMain")
	public String courseOpenMain(Model model){
		ResultAction sReturn = new ResultAction();
		List<CourseMajor> courseMajors = courseMajorService.findCourseMajor(); 
		model.addAttribute("courseMajors", courseMajors);
		return "/modules/courseOpen/courseOpenMain";
	}
	/*
	 * 获取所有专业
	 * */
	@RequestMapping(value="/getMajor")
	@ResponseBody
	public ResultAction getMajor(){
		ResultAction sReturn = new ResultAction();
		List<CourseMajor> courseMajors = courseMajorService.findCourseMajor();
		sReturn.setData(courseMajors);
		return sReturn;
	}
	
	/*
	 * 根据专业获取所有科目
	 * */
	@RequestMapping(value="/getSubjectByMajorId")
	@ResponseBody
	public ResultAction getSubjectByMajorId(String majorId){
		ResultAction sReturn = new ResultAction();
		List<CourseMajorSubject> courseMajorSubjects = courseMajorSubjectService.findAllSubjectByMajorId(majorId);
		sReturn.setData(courseMajorSubjects);
		return sReturn;
	}
	
	
	
	@RequestMapping(value="/getClasses")
	@ResponseBody
	public ResultAction getClasses(String subjectId){
		ResultAction sReturn = new ResultAction();
		List<Classes> classess = classService.findAllClassBySubjectId(subjectId);
		sReturn.setData(classess);
		return sReturn;
	}
	
	@RequestMapping(value="/getClassesByMajor")
	@ResponseBody
	public ResultAction getClassesByMajor(String majorId){
		ResultAction sReturn = new ResultAction();
		List<Classes> classess = classService.getClassesByMajor(majorId);
		sReturn.setData(classess);
		return sReturn;
	}
	
	
	/*学年
	类型
	专业
	科目
	班级*/
	@RequestMapping("/getShop")
	@ResponseBody
	public ResultAction getShop(String currentPage,String shopTime,String shopType,String majorId,String subjectId,String classesId){
		ResultAction sResult = new ResultAction();
		System.out.println("currentPage:"+currentPage);
		ShopQuery shopQuery = new ShopQuery(Integer.parseInt(currentPage));
		shopQuery.setShopTime(shopTime);
		shopQuery.setShopType(shopType);
		shopQuery.setMajorId(majorId);
		shopQuery.setSubjectId(subjectId);
		shopQuery.setClassesId(classesId);
		List<Shop> shops = shopService.findShopByQuery(shopQuery);
		for (Shop shop : shops) {
			switch(Integer.parseInt(shop.getShopType())){
				case 0: shop.setShopType("电子书"); break;
				case 1: shop.setShopType("实体书");break;
				case 2: shop.setShopType("课件");break;
				case 3: shop.setShopType("直播");break;
				case 4: shop.setShopType("在线模考商品");break;
				case 9: shop.setShopType("免费课程"); break; 
				default:
					break;
			} 
		}
		int allRecodeNum = shopService.findShopByQueryCount(shopQuery);
		PageBean pb = new PageBean(); 
		pb.setAllRecodeNum(allRecodeNum);
		pb.setObj(shops);
		sResult.setData(pb);
		return sResult;
	}
	
	/*开通课程*/
	@RequestMapping("/openCourse")
	@ResponseBody
	public ResultAction openCourse(HttpSession session,String majorId,String majorName,String shopIds,String userId,String mobile){ 
		//System.out.println("shopIds:"+shopIds+"---mobile"+mobile+"majorId:"+majorId+"majorName:"+majorName);
		ResultAction sReturn = new ResultAction();
		StudentInfo studentInfo =studentInfoService.findByStudentMobile(mobile);
		BsUserInfo userInfo = (BsUserInfo) session.getAttribute("teacher");
		userId = userInfo.getUserId(); 
		try {
				if(studentInfo==null){
					studentInfo = new StudentInfo();
					studentInfo.setMobile(mobile); 
					studentInfo.setStudentName(mobile);
					studentInfo.setCreateTime(DateUtils.getTodayTime());
					studentInfo.setIsDel("0");
					StudentInfoDetail studentInfoDetail = new StudentInfoDetail();  
					studentInfoDetail.setStudentId(DbUtil.getKey());  
					studentInfoDetail.setApplyIntention("无意向");
					studentInfoDetail.setApplyClasses("无意向班");
					studentInfoDetail.setMobilePhone(mobile);
					studentInfoDetail.setIntentionRamark("无意向"); 
					studentInfoDetail.setIntention(1); 
					studentInfoService.inputStudent(studentInfo,studentInfoDetail);  
				}
				if(shopIds!=null&&!"".equals(shopIds)&&shopIds.length()!=0){
				    String[] shopIdss=shopIds.trim().split(",");  
					OpenCourseQuery openCourseQuery = new OpenCourseQuery(); 
				    	for(int i=0;i<shopIdss.length;i++){
				    		Shop shop = shopService.selectByPrimaryKey(shopIdss[i]);
							openCourseQuery.setShopId(shopIdss[i]);
							openCourseQuery.setUserId(studentInfo.getStudentId());
							studentShopSetService.openCourse(openCourseQuery);
							BsUserShopSet bsUserShopSet = new BsUserShopSet(majorId,majorName,userId,shopIdss[i],studentInfo.getStudentId(),shop.getClassesId(),shop.getClassesName(),shop.getShopPrice(),1);
							bsUserShopSetService.openCourse(bsUserShopSet);
						}
				    	List<Shop> shops = shopService.queryShopByStudentId(studentInfo.getStudentId());
				    	CourseOpenResult courseOpenResult = new CourseOpenResult();
				    	courseOpenResult.setShops(shops);
				    	courseOpenResult.setStudentInfo(studentInfo);
				    	sReturn.setData(courseOpenResult);
				    	sReturn.setMessage("OPENSUCCESS");
				    }else{
				    	sReturn.setMessage("NOCHOISE");
				    }
		} catch (Exception e) {
			// TODO Auto-generated catch block 
			log.info(e.getMessage());
			sReturn.setIserror(true);
			e.printStackTrace();
		}  
		return sReturn;
	}
	

}
