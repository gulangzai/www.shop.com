package com.xue.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xue.dao.BsUserShopSetMapper;
import com.xue.dto.OpenCourseRecodeQuery;
import com.xue.dto.OpenCourseRecodeResult;
import com.xue.model.BsUserShopSet;
import com.xue.service.BsUserShopSetService;

@Service
public class BsUserShopSetServiceImpl implements BsUserShopSetService {

	@Autowired
	BsUserShopSetMapper bsUserShopSetMapper;
	
	@Override
	public List<OpenCourseRecodeResult> getOpenCourseRecode(OpenCourseRecodeQuery openCourseRecode) {
		// TODO Auto-generated method stub
		return bsUserShopSetMapper.queryAllStudentShop(openCourseRecode);
	}

	@Transactional(rollbackFor=Exception.class) 
	@Override
	public void openCourse(BsUserShopSet bsUserShopSet) {
		List<BsUserShopSet> bsUserShopSets = bsUserShopSetMapper.queryBsUserShopSet(bsUserShopSet);
		if(bsUserShopSets.size()==0){
			bsUserShopSetMapper.insert(bsUserShopSet);
		} 
	}

	@Override
	public int getOpenCourseRecodeCount(OpenCourseRecodeQuery openCourseRecode) {
		return bsUserShopSetMapper.getOpenCourseRecodeCount(openCourseRecode);
	}

	@Override
	public List<BsUserShopSet> findAllMajor(String userId) {
		return bsUserShopSetMapper.findShowMajor(userId);
	}

	@Override
	public Integer findAllPayInfo(String userId) {
		return bsUserShopSetMapper.findAllPayInfo(userId);
	}
}
