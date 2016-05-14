package com.xue.service;

import java.util.List;

import com.xue.model.QuestionInfo;
import com.xue.model.StudentInfo;
import com.xue.model.StudentPlan;
import com.xue.model.StudentPlanLearn;
import com.xue.model.StudentPlanSubjectPoint;
import com.xue.vo.query.StudentPlanQuery;


public interface StudentPlanLearnService {

	/**
	 * @Description:获取学习时长
	 * @param studentInfo
	 * @param planId
	 * @return
	 * StudentPlanLearn
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2015年9月22日 下午2:19:24
	 */
	StudentPlanLearn getLearnTime(Integer planId);

	/**
	 * @Description:获取学习时长和做题
	 * @param planId
	 * @return
	 * StudentPlanLearn
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2015年10月19日 上午11:08:13
	 */
	StudentPlanLearn findByPlanId(Integer planId);

	/**
	 * @Description:获取知识点对应的问题
	 * @param pointId
	 * @return
	 * List<QuestionInfo>
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2015年10月27日 上午10:45:47
	 */
	List<QuestionInfo> findByPointId(Integer pointId);

	/**
	 * @Description:根据ID获取问题
	 * @param questionid
	 * @return
	 * QuestionInfo
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2015年10月27日 下午1:16:02
	 */
	QuestionInfo findByQuestionId(Integer questionid);

	/**
	 * @Description:更新学习时长
	 * @param studentPlanLearn
	 * void
	 * @exception:
	 * @author: Mr.Ruan
	 * @param sparkPlayerDuration 
	 * @param studentPlanSubjectPoint 
	 * @time:2015年10月30日 下午3:50:31
	 */
	void updateStudentPlanLearn(StudentPlanLearn studentPlanLearn, Float sparkPlayerDuration, StudentPlanSubjectPoint studentPlanSubjectPoint);

	/**
	 * @Description:更新答题数量
	 * @param upStudentPlanLearn
	 * void
	 * @exception:
	 * @author: Mr.Ruan
	 * @param studentPlanSubjectPoint 
	 * @throws Exception 
	 * @time:2015年11月2日 上午11:32:37
	 */
	void updateStudentPlanLearn(StudentPlanLearn upStudentPlanLearn, StudentPlanSubjectPoint studentPlanSubjectPoint) throws Exception;

	/**
	 * @Description:未掌握和掌握的知识点总数
	 * @param planId
	 * @return
	 * int
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2015年11月19日 下午2:43:08
	 */
	int findByIsGrasp(Integer planId);

	/**
	 * @Description:通过学习计划获得(我的计划学习数据)
	 * @param studentPlan
	 * @return  StudentPlanLearn
	 * @exception:
	 * @author: liyi
	 * @time:2015年11月19日 下午2:43:08
	 */ 
	StudentPlanLearn queryStudentPlanLearnsByPlan(StudentPlan aStudentPlan);
}
