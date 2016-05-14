package com.xue.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xue.dao.BsCallbackInfoMapper;
import com.xue.model.BsCallbackInfo;
import com.xue.service.BsCallbackInfoService;
import com.xue.vo.query.BsCallbackInfoQuery;

@Service
public class BsCallbackInfoServiceImpl implements BsCallbackInfoService {
	
	@Autowired
	public BsCallbackInfoMapper bsCallbackInfoMapper;

	@Override
	public List<BsCallbackInfo> selectByTeacherAndStudent(BsCallbackInfoQuery bsCallbackInfoQuery) {
		// TODO Auto-generated method stub
		return bsCallbackInfoMapper.selectByTeacherAndStudent(bsCallbackInfoQuery);
	}

	@Override
	public void addCallbackInfo(BsCallbackInfo bsCallbackInfo) {
		// TODO Auto-generated method stub
		bsCallbackInfoMapper.insert(bsCallbackInfo);
	}

	@Override
	public int findNoticeCount(BsCallbackInfoQuery bsCallbackInfoQuery) {
		// TODO Auto-generated method stub
		return bsCallbackInfoMapper.findNoticeCount(bsCallbackInfoQuery);
	}
	
	
}
