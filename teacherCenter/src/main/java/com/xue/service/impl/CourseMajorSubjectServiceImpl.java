package com.xue.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xue.dao.CourseMajorSubjectMapper;
import com.xue.model.CourseMajorSubject; 
import com.xue.service.CourseMajorSubjectService;
import com.xue.vo.query.CourseMajorSubjectQuery;
@Service
public class CourseMajorSubjectServiceImpl implements CourseMajorSubjectService {
	@Autowired
	private CourseMajorSubjectMapper courseMajorSubjectMapper;
 
	@Override
	public CourseMajorSubject selectByPrimaryKey(String subjectId){
		return courseMajorSubjectMapper.selectByPrimaryKey(subjectId);
	}

	@Override
	public List<CourseMajorSubject> findAllSubjectByMajorId(String majorId) {
		// TODO Auto-generated method stub 
		CourseMajorSubjectQuery courseMajorSubjectQuery = new CourseMajorSubjectQuery();
		courseMajorSubjectQuery.setMajorId(majorId); 
		return courseMajorSubjectMapper.findAllByMajorQuery(courseMajorSubjectQuery);
	}
	
	/*
	 * 获取此学生报考的，专业.科目
	 * */
	@Override
	public List<CourseMajorSubject> findCourseMajorSubjectListByStudentId(String studentId) {
		// TODO Auto-generated method stub
		return courseMajorSubjectMapper.findCourseMajorSubjectListByStudentId(studentId);
	} 
}
