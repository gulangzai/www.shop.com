/*
 * StudentPlanLogMapper.java
 * Copyright(C) 2015-2018 ѧ��ɭ
 * All rights reserved.
 * ------------------------------------------------
 * 2015-09-23 Created
 */
package com.xue.dao;

import java.util.List;

import com.xue.model.StudentPlanLog;
import com.xue.vo.query.StudentPlanLogQuery;

public interface StudentPlanLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StudentPlanLog record);

    int insertSelective(StudentPlanLog record);

    StudentPlanLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StudentPlanLog record);

    int updateByPrimaryKey(StudentPlanLog record);

	List<StudentPlanLog> getStudentPlanLog(
			StudentPlanLogQuery studentPlanLogQuery);

}