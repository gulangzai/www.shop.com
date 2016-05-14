package com.xue.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xue.dao.StudentInfoDetailMapper;
import com.xue.model.StudentInfoDetail;
import com.xue.service.StudentInfoDetailService;

@Service
public class StudentInfoDetailServiceImpl implements StudentInfoDetailService {

	@Autowired
	StudentInfoDetailMapper studentInfoDetailMapper;
	@Override
	public StudentInfoDetail findByStudentId(String studentId) {
		// TODO Auto-generated method stub
		return studentInfoDetailMapper.findByStudentId(studentId);
	}
	
	@Override
	public void save(StudentInfoDetail studentInfoDetail) {
		// TODO Auto-generated method stub
		studentInfoDetailMapper.insert(studentInfoDetail);
	}

}
