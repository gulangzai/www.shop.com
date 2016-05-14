package com.xue.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xue.dao.BsUserStuFavorableMapper;
import com.xue.model.BsUserStuFavorable;
import com.xue.model.StudentInfo;
import com.xue.service.BsUserStuFavorableService;
import com.xue.service.StudentInfoService;

@Service
public class BsUserStuFavorableServiceImpl implements BsUserStuFavorableService{

	@Autowired
	BsUserStuFavorableMapper bsUserStuFavorableMapper;
	@Autowired
	StudentInfoService studentInfoServiceMapper;
	
	@Override
	public void addCoupon(BsUserStuFavorable bsUserStuFavorable) throws Exception{
		// TODO Auto-generated method stub
		bsUserStuFavorableMapper.insert(bsUserStuFavorable);
		StudentInfo studentInfo = studentInfoServiceMapper.findByUserId(bsUserStuFavorable.getStudentId());
	    studentInfo.setBalance(studentInfo.getBalance()+bsUserStuFavorable.getFavorableBalance());
	    studentInfoServiceMapper.updateByPrimaryKeySelective(studentInfo);
		
	}
	
}
