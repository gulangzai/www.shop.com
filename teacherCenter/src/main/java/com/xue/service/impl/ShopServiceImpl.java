package com.xue.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.xue.dao.ShopMapper;
import com.xue.dao.StudentInfoMapper;
import com.xue.dto.OpenCourseQuery;
import com.xue.dto.OpenCourseRecodeQuery;
import com.xue.dto.OpenCourseRecodeResult;
import com.xue.dto.StudentAndShop;
import com.xue.model.MyCollect;
import com.xue.model.MyCollectDetailPointSet;
import com.xue.model.Shop; 
import com.xue.model.StudentInfo;
import com.xue.model.StudentMyCollectSet;
import com.xue.model.SysCollect;
import com.xue.service.ShopService;
import com.xue.service.StudentInfoService; 
import com.xue.vo.query.ShopQuery;

@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopMapper shopMapper;
  
	@Override
	public List<Shop> findSubClassByDetailId(String majorId, String subjectId) {
		ShopQuery shopQuery = new ShopQuery();
		shopQuery.setMajorId(majorId);
		shopQuery.setSubjectId(subjectId);
		return shopMapper.findSubClassByDetailId(shopQuery);
		//return shopMapper.findSubClassByDetailId(shopDetailId);
	}
	
	@Override
	public List<Shop> findSubClassByDetailId(String majorId, String subjectId,String shopType) {
		ShopQuery shopQuery = new ShopQuery();
		shopQuery.setMajorId(majorId);
		shopQuery.setSubjectId(subjectId);
		shopQuery.setShopType(shopType);
		return shopMapper.findSubClassByDetailId(shopQuery);
		//return shopMapper.findSubClassByDetailId(shopDetailId);
	}
	
	
	
	@Override
	public Shop selectByPrimaryKey(String id){
		return shopMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<Shop> findByShopQuery(ShopQuery shopQuery){
		return shopMapper.findSubClassByDetailId(shopQuery);
	}

	@Override
	public List<Shop> findShopByQuery(ShopQuery shopQuery) {
		// TODO Auto-generated method stub
		return   shopMapper.findShopByQuery(shopQuery);
	}
 
	/*
	 * 获取开通记录
	 * */
	@Override
	public List<OpenCourseRecodeResult> getOpenCourseRecode(OpenCourseRecodeQuery openCourseRecode) {
		// TODO Auto-generated method stub
		List<OpenCourseRecodeResult> openCourseRecodeResults = new ArrayList<OpenCourseRecodeResult>();
		/*List<StudentAndShop> studentAndShops = shopMapper.queryAllStudentShop(openCourseRecode);
	    for (StudentAndShop studentAndShop : studentAndShops) {
			OpenCourseRecodeResult openCourseRecodeResult = new OpenCourseRecodeResult();
			openCourseRecodeResult.setStudentId(studentAndShop.getStudentId());
			openCourseRecodeResult.setStudentName(studentAndShop.getStudentName());
			openCourseRecodeResult.setMobile(studentAndShop.getMobile());
			openCourseRecodeResult.setOpenProject(studentAndShop.getShopName());
			openCourseRecodeResult.setOpenTime(String.valueOf(studentAndShop.getCreateTime()));
		}*/
		return openCourseRecodeResults;
	}

	@Override
	public int findShopByQueryCount(ShopQuery shopQuery) {
		// TODO Auto-generated method stub
		return shopMapper.findShopByQueryCount(shopQuery);
	}

	@Override
	public List<Shop> queryShopByStudentId(String studentId) {
		// TODO Auto-generated method stub
		return shopMapper.queryShopByStudentId(studentId);
	}

 
}
