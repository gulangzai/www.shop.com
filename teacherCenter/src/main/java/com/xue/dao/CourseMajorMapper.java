/*
 * CourseMajorMapper.java
 * Copyright(C) 2015-2018 ѧ��ɭ
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.dao;

import java.util.List;

import com.xue.dto.CourseMajorShopToday;
import com.xue.model.CourseMajor;
import com.xue.model.CourseMajorShop;
import com.xue.model.StudentPlan; 

public interface CourseMajorMapper {
    int deleteByPrimaryKey(String majorId);

    int insert(CourseMajor record);

    int insertSelective(CourseMajor record);

    CourseMajor selectByPrimaryKey(String majorId);

    int updateByPrimaryKeySelective(CourseMajor record);

    int updateByPrimaryKey(CourseMajor record);

	List<CourseMajorShop> findMajor(String studentId);

	List<CourseMajor> findCourseMajor();

	List<CourseMajorShopToday> findZhiboCourse(String studentId);

	List<CourseMajorShop> findByFreeMajorId(StudentPlan studentPlan);

	List<CourseMajorShop> findFreeLesson();

	List<CourseMajorShop> findByMajorIdForSubject(StudentPlan studentPlan);

	List<CourseMajor> findBySubjectType(String subjectType);
}