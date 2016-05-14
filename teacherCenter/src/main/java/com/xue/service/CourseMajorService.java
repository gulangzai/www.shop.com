package com.xue.service;

import java.util.List;

import com.xue.dto.CourseMajorShopToday;
import com.xue.model.CourseMajor;
import com.xue.model.CourseMajorShop;
import com.xue.model.ExperienceMoive;
import com.xue.model.StudentPlan; 

public interface CourseMajorService {

	/**
	 * @Description:根据用户购买的课程来查询专业
	 * @param studentId
	 * @return
	 * List<CourseMajor>
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2015年9月16日 下午5:23:06
	 */
	List<CourseMajorShop> findMajor(String studentId);

	/**
	 * @Description:首页查询专业
	 * @return
	 * List<CourseMajor>
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2015年10月10日 下午3:38:24
	 */
	List<CourseMajor> findCourseMajor();

	
	/**
	 * @Description:根据专业id查询专业
	 * @param majorId
	 * @return
	 * CourseMajor
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2015年10月12日 上午10:27:24
	 */
	CourseMajor selectByPrimaryKey(String majorId);

	/**
	 * @Description:根据专业ID查询试听视频
	 * @param majorId
	 * @return
	 * List<ExperienceMoive>
	 * @exception:
	 * @author: Mr.Ruan
	 * @param experienceOrder 
	 * @param experiencePhase 
	 * @time:2015年10月13日 下午12:33:00
	 */
	ExperienceMoive findExperienceMoive(String majorId, Integer experiencePhase, Integer experienceOrder);

	void insert(ExperienceMoive experienceMoive);

	/**
	 * @Description:根据专业查询试听视频
	 * @param majorId
	 * @return
	 * List<ExperienceMoive>
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2015年10月14日 上午9:40:11
	 */
	List<ExperienceMoive> findAllByMajorId(String majorId);

	/**
	 * @Description:根据专业查询科目
	 * @param studentPlan
	 * @return
	 * List<CourseMajorShop>
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2015年11月23日 下午2:11:43
	 */
	List<CourseMajorShop> findByMajorId(StudentPlan studentPlan);

	/**
	 * @Description:根据类型查询科目
	 * @param subjectType
	 * @return
	 * List<CourseMajor>
	 * @exception:
	 * @author: Liyi
	 * @time:2015年12月20日 下午2:11:43
	 */
	List<CourseMajor> findBySubjectType(String subjectType);

	/**
	 * @Description:查找免费的计划
	 * @return
	 * List<CourseMajorShop>
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2015年12月22日 下午1:27:41
	 */
	List<CourseMajorShop> findFreeLesson();

	List<CourseMajorShop> findByFreeMajorId(StudentPlan studentPlan);
	/**
	 * @Description:根据学号查找直播课程
	 * @return
	 * List<CourseMajorShopToday>
	 * @exception:
	 * @author: Liyi
	 * @time:2015年12月24日 
	 */
	List<CourseMajorShopToday> findZhiboCourse(String studentId);	
}
