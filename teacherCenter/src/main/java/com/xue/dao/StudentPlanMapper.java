/*
 * StudentPlanMapper.java
 * Copyright(C) 2015-2018 ѧ��ɭ
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.dao;

import java.util.List;

import com.xue.model.StudentPlan;
import com.xue.vo.query.StudentPlanQuery;

public interface StudentPlanMapper {
    int deleteByPrimaryKey(Integer planId);

    int insert(StudentPlan record);

    int insertSelective(StudentPlan record);

    StudentPlan selectByPrimaryKey(Integer planId);

    int updateByPrimaryKeySelective(StudentPlan record);

    int updateByPrimaryKey(StudentPlan record);
    
    StudentPlan findByQuery(StudentPlanQuery studentPlanQuery);

	StudentPlan findByFreeQuery(StudentPlanQuery studentPlanQuery);

	StudentPlan findByStudent(StudentPlanQuery studentPlanQuery);
     
	/*
	 * 通过学生获取该学生的学习计划
	 * */
	List<StudentPlan> findByStudentId(StudentPlanQuery studentPlanQuery);
}