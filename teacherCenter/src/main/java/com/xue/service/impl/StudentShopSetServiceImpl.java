package com.xue.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xue.dao.StudentShopSetMapper;
import com.xue.dto.OpenCourseQuery;
import com.xue.model.StudentShopSet;
import com.xue.service.StudentShopSetService;

@Service
public class StudentShopSetServiceImpl implements StudentShopSetService {
	
	@Autowired
	StudentShopSetMapper studentShopSetMapper;
	
	@Override
	public List<StudentShopSet> queryStudentShopSetList(StudentShopSet studentShopSet){
		return studentShopSetMapper.queryStudentShopSetList(studentShopSet);
	}

	/*
	 * 开通课程
	 * */
	@Override
	public void openCourse(OpenCourseQuery openCourseQuery) {
		// TODO Auto-generated method stub
		StudentShopSet studentShopSet = new StudentShopSet();
		studentShopSet.setStudentId(openCourseQuery.getUserId());
		studentShopSet.setShopId(openCourseQuery.getShopId()); 
		List<StudentShopSet> studentShopSets = studentShopSetMapper.queryStudentShopSetList(studentShopSet);
		//如果存在已经开通的课，将不开通课
		if(studentShopSets.size()==0){
			studentShopSetMapper.insert(studentShopSet);
		}
		
	}
}
