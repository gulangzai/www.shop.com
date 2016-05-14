package com.xue.service;

import java.util.List;

import com.xue.model.CourseMajorSubject; 
import com.xue.model.SysCollect;


public interface CourseMajorSubjectService {

	/**
	 * @Description:根据专业查找科目
	 * @param majorId
	 * @return
	 * List<CourseMajorSubject>
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2015年9月15日 下午4:05:54
	 */ 
	
	List<CourseMajorSubject> findAllSubjectByMajorId(String majorId);
	
	CourseMajorSubject selectByPrimaryKey(String subjectId);

    List<CourseMajorSubject> findCourseMajorSubjectListByStudentId(String studentId);
}
