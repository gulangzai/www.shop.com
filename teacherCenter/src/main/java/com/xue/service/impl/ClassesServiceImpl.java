package com.xue.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xue.dao.ClassesMapper;
import com.xue.dao.StudentInfoMapper; 
import com.xue.model.Classes;
import com.xue.model.MyCollect;
import com.xue.model.MyCollectDetailPointSet; 
import com.xue.model.StudentInfo;
import com.xue.model.StudentMyCollectSet;
import com.xue.model.SysCollect;
import com.xue.service.ClassesService; 
import com.xue.service.StudentInfoService; 

@Service
public class ClassesServiceImpl implements ClassesService {
	@Autowired
	private ClassesMapper classesMapper;

	@Override
	public List<Classes> findClassByDetailId(String detailId) {
		return classesMapper.findClassByDetailId(detailId);
	}

	@Override
	public  Classes selectByPrimaryKey(String classesId) {
		// TODO Auto-generated method stub
		return classesMapper.selectByPrimaryKey(classesId);
	}

	@Override
	public List<Classes> findAllClassBySubjectId(String subjectId) {
		// TODO Auto-generated method stub
		return classesMapper.findAllClassBySubjectId(subjectId);
	}

	@Override
	public List<Classes> getClassesByMajor(String majorId) {
		// TODO Auto-generated method stub
		return classesMapper.getClassesByMajor(majorId);
	}
 
}
