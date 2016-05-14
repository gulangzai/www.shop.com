package com.xue.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javacommon.util.DateUtils;
import javacommon.util.SafeUtils;
import javacommon.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import sun.java2d.pipe.SpanShapeRenderer.Simple;
  
import com.xue.dao.StudentInfoMapper;
import com.xue.dao.StudentPlanLogMapper;
import com.xue.dao.StudentPlanMapper; 
import com.xue.model.KnowledgePoint;
import com.xue.model.KnowledgePointPhase;
import com.xue.model.Shop;
import com.xue.model.StudentInfo;
import com.xue.model.StudentPlan;
import com.xue.model.StudentPlanLearn;
import com.xue.model.StudentPlanLearnSet;
import com.xue.model.StudentPlanLog;
import com.xue.model.StudentPlanPhase;
import com.xue.model.StudentPlanPhaseSet;
import com.xue.model.StudentPlanSubjcetPhase;
import com.xue.service.StudentInfoService;
import com.xue.service.StudentPlanService;
import com.xue.vo.query.ShopQuery;
import com.xue.vo.query.StudentPlanLogQuery;
import com.xue.vo.query.StudentPlanQuery;

@Service
public class StudentPlanServiceImpl implements StudentPlanService {
	@Autowired
	private StudentPlanMapper studentPlanMapper;
	@Autowired
	private StudentPlanLogMapper studentPlanLogMapper;
	
	@Override
	public Integer insert(StudentPlan studentPlan) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public StudentPlan findByQuery(StudentPlanQuery studentPlanQuery) {
		// TODO Auto-generated method stub
		return studentPlanMapper.findByQuery(studentPlanQuery);
	}
	@Override
	public List<StudentPlanLog> getStudentPlanLog(StudentInfo studentInfo, Integer planId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public StudentPlanLearn accessLearningTime(Integer planId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public StudentPlan updateStudentPlan(StudentPlan studentPlan, StudentInfo studentInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void calculateSuccessRate(StudentPlan studentPlan, StudentPlanLearn studentPlanLearn, Model model) {
		// TODO Auto-generated method stub
		long needLearnDay=SafeUtils.getDaySub(studentPlan.getStartDate(),new Date());
		float learnDay=(studentPlanLearn.getOneLearnTime()+studentPlanLearn.getTwoLearnTime())/3600;
		int studentDayTime=studentPlan.getDayTime();
		float learningTimeRate = 0;
		if(needLearnDay>0){
			learningTimeRate = (learnDay/needLearnDay);
			learningTimeRate = learningTimeRate/studentDayTime;
		}else{
			learningTimeRate = learnDay/studentDayTime;
		}
		float residueLearnDay=SafeUtils.getOtherDaySub(new Date() ,studentPlan.getEndDate());
		model.addAttribute("learningTimeRate", (float)Math.round(learningTimeRate*100));
		if(residueLearnDay<0){
			residueLearnDay=0;
		}
		model.addAttribute("residueLearnDay", residueLearnDay);
	
	}
	@Override
	public StudentPlan findByFreeQuery(StudentPlanQuery studentPlanQuery) {
		// TODO Auto-generated method stub
		return studentPlanMapper.findByFreeQuery(studentPlanQuery);
	}
	@Override
	public Integer insertFreeLesson(StudentPlan studentPlan) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<StudentPlanLog> findByPlanId(Integer planId, StudentInfo studentInfo) {
		// TODO Auto-generated method stub
		StudentPlanLogQuery studentPlanLog = new StudentPlanLogQuery();
		studentPlanLog.setPlanId(planId);
		studentPlanLog.setStudentId(studentInfo.getStudentId());
		return studentPlanLogMapper.getStudentPlanLog(studentPlanLog);
	
	}
	@Override
	public StudentPlan findByStudent(StudentPlanQuery studentPlanQuery) {
		// TODO Auto-generated method stub
		return studentPlanMapper.findByStudent(studentPlanQuery);
	}
	@Override
	public List<StudentPlan> findByStudentId(StudentPlanQuery studentPlanQuery) {
		// TODO Auto-generated method stub
		return studentPlanMapper.findByStudentId(studentPlanQuery);
	} 
}
