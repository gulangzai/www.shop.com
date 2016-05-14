package com.xue.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.xue.model.PaperInfo;
import com.xue.model.StudentExamInfo;
import com.xue.model.StudentExamPapeInfo;
import com.xue.model.StudentHighScore;
import com.xue.model.StudentInfo;
import com.xue.vo.query.StudentExamInfoQuery;


public interface StudentExamInfoService {

	/**
	 * @Description:根据用户信息寻找我的做试卷历史
	 * @param studentInfo
	 * @return
	 * Page<StudentExamInfo>
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2015年9月9日 上午11:09:51
	 */
	Page<StudentExamInfo> findPage(StudentInfo studentInfo);

	/**
	 * @Description:根据paperId和examId以及用户id 来查询用户考试结果
	 * @param query
	 * @return
	 * StudentExamPapeInfo
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2015年9月9日 下午1:35:19
	 */
	StudentExamPapeInfo findByExamId(StudentExamInfoQuery query);

	/**
	 * @Description:根据用户信息寻找我的做试卷历史
	 * @param studentExamInfoQuery
	 * @return
	 * Page<StudentExamInfo>
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2015年9月9日 下午3:16:10
	 */
	Page<StudentExamInfo> findPage(StudentExamInfoQuery studentExamInfoQuery);

	/**
	 * @Description:获取用户考试
	 * @param studentInfo
	 * @return
	 * List<StudentExamInfo>
	 * @exception:
	 * @author: Mr.Ruan
	 * @param subjectId 
	 * @param majorId 
	 * @time:2015年9月23日 下午3:30:44
	 */
	List<StudentHighScore> getStudentExamInfo(StudentInfo studentInfo, String majorId, String subjectId);

	/**
	 * 查询所有数据
	 * @return
	 */
	List<StudentExamInfo> selectEntityPage();

	int insert(StudentExamInfo examinfo);

	/**
	 * @Description:判断用户有考试记录
	 * @param paperId
	 * @return
	 * List<StudentExamInfo>
	 * @exception:
	 * @author: Mr.Ruan
	 * @param stu 
	 * @time:2015年12月8日 下午2:59:27
	 */
	List<StudentExamInfo> whetherPerformance(Integer paperId, StudentInfo stu);

	/**
	 * @Description:根据月份寻找
	 * @param studentInfo
	 * @param paperInfo
	 * @return
	 * List<Integer>
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2015年12月10日 下午3:18:35
	 */
	List<Float> findExamChange(StudentInfo studentInfo, PaperInfo paperInfo);

	/**
	 * @Description:根据用户id和paperId去寻找最近的考试记录
	 * @param studentInfo
	 * @param paperId
	 * @return
	 * StudentExamInfo
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2015年12月11日 上午11:21:37
	 */
	StudentExamInfo findByStudentId(StudentInfo studentInfo, Integer paperId);

}
