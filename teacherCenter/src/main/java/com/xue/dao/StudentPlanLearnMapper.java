/*
 * StudentPlanLearnMapper.java
 * Copyright(C) 2015-2018 ѧ��ɭ
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.dao;

import java.util.List;

import com.xue.model.StudentPlan;
import com.xue.model.StudentPlanLearn;
import com.xue.vo.query.StudentPlanQuery;

public interface StudentPlanLearnMapper {
    int deleteByPrimaryKey(Integer planLearnId);

    int insert(StudentPlanLearn record);

    int insertSelective(StudentPlanLearn record);

    StudentPlanLearn selectByPrimaryKey(Integer planLearnId);

    int updateByPrimaryKeySelective(StudentPlanLearn record);

    int updateByPrimaryKey(StudentPlanLearn record);

	StudentPlanLearn getLearnTime(StudentPlanQuery studentPlanQuery);

	StudentPlanLearn accessLearningTime(Integer planId);

	int findByIsGrasp(Integer planId);
	
    //每个学生提取最新的学习计划
	StudentPlanLearn queryStudentPlanLearnsByStudentId(String studentId);

	StudentPlanLearn queryStudentPlanLearnsByPlan(StudentPlan studentPlan);
 
 
}