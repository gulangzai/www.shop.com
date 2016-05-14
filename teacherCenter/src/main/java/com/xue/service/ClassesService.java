package com.xue.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.xue.model.Classes;
import com.xue.model.MyCollect; 



public interface ClassesService{

	List<Classes> findClassByDetailId(String detailId);

	Classes selectByPrimaryKey(String classesId);
  
	List<Classes> findAllClassBySubjectId(String subjectId);
  
	List<Classes> getClassesByMajor(String majorId);

}
