package com.xue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xue.dto.PageBean;
import com.xue.model.Classes;
import com.xue.model.CourseMajor;
import com.xue.model.CourseMajorSubject;
import com.xue.model.Shop;
import com.xue.service.ClassesService;
import com.xue.service.CourseMajorService;
import com.xue.service.CourseMajorSubjectService;
import com.xue.service.ShopService;
import com.xue.vo.query.ShopQuery;

import javacommon.base.ResultAction;

@Controller
@RequestMapping("/shopManager")
public class ShopManagerController {
	
	@Autowired
	private CourseMajorService courseMajorService;
	
	@Autowired
	private ShopService shopService;
	
	@Autowired
	private ClassesService classService;
	
	@Autowired
	private CourseMajorSubjectService courseMajorSubjectService;
	
	/*
	 * 访问商品管理系统
	 * */
	@RequestMapping(value="/getShopManager") 
	public String shopManager(){
		return "/modules/myShop/shopManager";
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
		System.out.println("---------"+classess);
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
}
