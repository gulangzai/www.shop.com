package com.xue.service;

import java.util.List;

import org.springframework.ui.Model;

import com.xue.model.StudentInfo;
import com.xue.model.StudentPlan;
import com.xue.model.StudentPlanLearn;
import com.xue.model.StudentPlanLog;
import com.xue.vo.query.StudentPlanQuery;


public interface StudentPlanService {

	  public Integer insert(StudentPlan studentPlan);

	 /**
	 * @Description:根据query查询学员工作计划
	 * @param studentPlanQuery
	 * @return
	 * StudentPlan
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2015年9月17日 上午9:30:58
	 */
	 
	public StudentPlan findByQuery(StudentPlanQuery studentPlanQuery);

	 /**
	 * @Description:根据用户id和计划id 查询 ,创建时间倒序
	 * @param studentInfo
	 * @param planId
	 * @return
	 * List<StudentPlanLog>
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2015年9月23日 下午2:47:41
	 */
	public List<StudentPlanLog> getStudentPlanLog(StudentInfo studentInfo,
			Integer planId);

	 /**
	 * @Description:根据计划id获取学员学习时长
	 * @param planId
	 * @return
	 * StudentPlanLearn
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2015年10月13日 下午1:40:33
	 */
	public StudentPlanLearn accessLearningTime(Integer planId);

	/**
	 * @Description:根据用户计划ID查询出用户操作日志表
	 * @param planId
	 * @return
	 * List<StudentPlanLog>
	 * @exception:
	 * @author: Mr.Ruan
	 * @param studentInfo 
	 * @time:2015年10月19日 下午1:14:15
	 *//*
	public List<StudentPlanLog> findByPlanId(Integer planId, StudentInfo studentInfo);

	*//**
	 * @Description:根据用户计划ID修改内容
	 * @param studentPlan
	 * void
	 * @exception:
	 * @author: Mr.Ruan
	 * @param studentInfo 
	 * @return 
	 * @time:2015年10月19日 下午5:01:20
	 */
	public StudentPlan updateStudentPlan(StudentPlan studentPlan, StudentInfo studentInfo);

	/**
	 * @Description:计算达标率
	 * @param studentPlan
	 * @param studentPlanLearn
	 * @return
	 * Long
	 * @exception:
	 * @author: Mr.Ruan
	 * @param model 
	 * @time:2015年11月11日 上午10:20:58
	 */
	public void calculateSuccessRate(StudentPlan studentPlan,
			StudentPlanLearn studentPlanLearn, Model model);

	/**
	 * @Description:获取免费计划
	 * @param studentPlanQuery
	 * @return
	 * StudentPlan
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2015年12月22日 下午1:57:23
	 */
	public StudentPlan findByFreeQuery(StudentPlanQuery studentPlanQuery);

	/**
	 * @Description:插入自由学习
	 * @param studentPlan
	 * @return
	 * Integer
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2015年12月22日 下午2:31:56
	 */
	public Integer insertFreeLesson(StudentPlan studentPlan);

	public List<StudentPlanLog> findByPlanId(Integer planId, StudentInfo studentInfo);

	public StudentPlan findByStudent(StudentPlanQuery studentPlanQuery);

	public List<StudentPlan> findByStudentId(StudentPlanQuery studentPlanQuery);

}