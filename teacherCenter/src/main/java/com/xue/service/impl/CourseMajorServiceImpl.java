package com.xue.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xue.dao.CourseMajorMapper; 
import com.xue.dao.StudentInfoMapper; 
import com.xue.dto.CourseMajorShopToday;
import com.xue.model.CourseMajor;
import com.xue.model.CourseMajorShop; 
import com.xue.model.ExperienceMoive;
import com.xue.model.StudentInfo;
import com.xue.model.StudentPlan;
import com.xue.model.SysCollect;
import com.xue.service.CourseMajorService;
import com.xue.service.StudentInfoService; 
import com.xue.vo.query.ExperienceMoiveQuery;

@Service
public class CourseMajorServiceImpl implements CourseMajorService {
	@Autowired
	private CourseMajorMapper courseMajorMapper;
	/*@Autowired
	private ExperienceMoiveMapper experienceMoiveMapper;*/
	
	@Override
	public List<CourseMajorShop> findMajor(String studentId) {
		return courseMajorMapper.findMajor(studentId);
	}

	@Override
	public List<CourseMajor> findCourseMajor() {
		return courseMajorMapper.findCourseMajor();
	}

	@Override
	public CourseMajor selectByPrimaryKey(String majorId) {
		return courseMajorMapper.selectByPrimaryKey(majorId);
	}

 	@Override
	public ExperienceMoive findExperienceMoive(String majorId, Integer experiencePhase, Integer experienceOrder) {
	/*	ExperienceMoiveQuery experienceMoiveQuery = new ExperienceMoiveQuery();
		experienceMoiveQuery.setExperienceMajorId(majorId);
		experienceMoiveQuery.setExperienceOrder(experienceOrder);
		experienceMoiveQuery.setExperiencePhase(experiencePhase);
		return experienceMoiveMapper.findExperienceMoive(experienceMoiveQuery);*/
 		return null;
	}
	@Override
	public void insert(ExperienceMoive experienceMoive){
		/* experienceMoiveMapper.insert(experienceMoive);*/ 
	}

	@Override
	public List<ExperienceMoive> findAllByMajorId(String majorId) {
		/*return experienceMoiveMapper.findAllByMajorId(majorId);*/
		return null;
	}

	@Override
	public List<CourseMajorShop> findByMajorId(StudentPlan studentPlan) {
		return courseMajorMapper.findByMajorIdForSubject(studentPlan);
	}

	@Override
	public List<CourseMajor> findBySubjectType(String subjectType) {
		return courseMajorMapper.findBySubjectType(subjectType);
	}

	@Override
	public List<CourseMajorShop> findFreeLesson() {
		return courseMajorMapper.findFreeLesson();
	}

	@Override
	public List<CourseMajorShop> findByFreeMajorId(StudentPlan studentPlan) {
		return courseMajorMapper.findByFreeMajorId(studentPlan);
	}

	@Override
	public List<CourseMajorShopToday> findZhiboCourse(String studentId) {
		// TODO Auto-generated method stub
		return courseMajorMapper.findZhiboCourse(studentId);
	}
 
	
}
