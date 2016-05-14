package com.xue.service.impl;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern; 
import javacommon.util.MD5Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*import com.xue.dao.QuestionInfoMapper;*/
import com.xue.dao.StudentInfoMapper;
import com.xue.dao.StudentPlanLearnMapper;
import com.xue.model.QuestionInfo;
import com.xue.model.StudentInfo;
import com.xue.model.StudentPlan;
import com.xue.model.StudentPlanLearn;
import com.xue.model.StudentPlanSubjectPoint;
import com.xue.service.StudentInfoService;
import com.xue.service.StudentPlanLearnService;
import com.xue.vo.query.StudentPlanQuery;

@Service
public class StudentPlanLearnServiceImpl implements StudentPlanLearnService {
	@Autowired
	private StudentPlanLearnMapper studentPlanLearnMapper;
	/*@Autowired
	private QuestionInfoMapper questionInfoMapper;*/
	
	@Override
	public StudentPlanLearn getLearnTime(Integer planId) {
		StudentPlanQuery studentPlanQuery = new StudentPlanQuery();
		studentPlanQuery.setPlanId(planId);
		return studentPlanLearnMapper.getLearnTime(studentPlanQuery);
	}

	@Override
	public StudentPlanLearn findByPlanId(Integer planId) {
		return studentPlanLearnMapper.accessLearningTime(planId);
	}

	@Override
	public List<QuestionInfo> findByPointId(Integer pointId) {
		//return questionInfoMapper.findByPointId(pointId);
		return null;
	}

	@Override
	public QuestionInfo findByQuestionId(Integer questionid) {
		//return questionInfoMapper.selectByPrimaryKey(questionid);
		return null;
	}

	@Override
	@Transactional
	public void updateStudentPlanLearn(StudentPlanLearn studentPlanLearn,Float sparkPlayerDuration,StudentPlanSubjectPoint studentPlanSubjectPoint) {
		StudentPlanLearn upStudentPlanLearn = new StudentPlanLearn();
		if(studentPlanSubjectPoint.getIsGrasp()==2||studentPlanSubjectPoint.getIsGrasp()==1){
			Float studentLength = studentPlanLearn.getTwoLearnTime();
			Float total = studentLength+sparkPlayerDuration;
			upStudentPlanLearn.setPlanLearnId(studentPlanLearn.getPlanLearnId());
			upStudentPlanLearn.setTwoLearnTime(total);
			upStudentPlanLearn.setReviewRate(studentPlanLearn.getReviewRate()+1);
			studentPlanLearnMapper.updateByPrimaryKeySelective(upStudentPlanLearn);
		}else{
			Float studentLength = studentPlanLearn.getOneLearnTime();
			Float total = studentLength+sparkPlayerDuration;
			upStudentPlanLearn.setPlanLearnId(studentPlanLearn.getPlanLearnId());
			upStudentPlanLearn.setOneLearnTime(total);
			studentPlanLearnMapper.updateByPrimaryKeySelective(upStudentPlanLearn);
		}	
			
	}

	@Override
	@Transactional
	public void updateStudentPlanLearn(StudentPlanLearn upStudentPlanLearn,StudentPlanSubjectPoint studentPlanSubjectPoint)throws Exception {
		studentPlanLearnMapper.updateByPrimaryKeySelective(upStudentPlanLearn);
	}

	@Override
	public int findByIsGrasp(Integer planId) {
		return studentPlanLearnMapper.findByIsGrasp(planId);
	}

	@Override
	public StudentPlanLearn queryStudentPlanLearnsByPlan(StudentPlan studentPlan) {
		// TODO Auto-generated method stub
		return studentPlanLearnMapper.queryStudentPlanLearnsByPlan(studentPlan);
	}
}
