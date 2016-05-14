package com.xue.dao;

import java.util.List;

import com.xue.model.CourseMajorSubject; 
import com.xue.vo.query.CourseMajorSubjectQuery;

public interface CourseMajorSubjectMapper {
    int deleteByPrimaryKey(String subjectId);

    int insert(CourseMajorSubject record);

    int insertSelective(CourseMajorSubject record);

    CourseMajorSubject selectByPrimaryKey(String subjectId);

    int updateByPrimaryKeySelective(CourseMajorSubject record);

    int updateByPrimaryKey(CourseMajorSubject record); 
  
	List<CourseMajorSubject> findAllByMajorQuery(CourseMajorSubjectQuery courseMajorSubjectQuery);

	List<CourseMajorSubject> findCourseMajorSubjectListByStudentId(String studentId);
}