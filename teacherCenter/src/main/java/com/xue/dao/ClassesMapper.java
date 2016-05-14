package com.xue.dao;

import java.util.List;

import javacommon.base.CommonMapper;

import com.xue.model.Classes;

public interface ClassesMapper{

	List<Classes> findClassByDetailId(String detailId);

	 Classes  selectByPrimaryKey(String classesId);
  
	List<Classes> findAllClassBySubjectId(String subjectId);

	List<Classes> getClassesByMajor(String majorId);
}