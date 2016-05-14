package com.xue.dao;

import java.util.List;

import com.github.pagehelper.Page;
import com.xue.model.StudentExamInfo;
import com.xue.model.StudentExamPapeInfo;
import com.xue.model.StudentHighScore;
import com.xue.vo.query.PaperInfoQuery;
import com.xue.vo.query.StudentExamInfoQuery;

public interface StudentExamInfoMapper {
    int deleteByPrimaryKey(String examId);

    int insert(StudentExamInfo record);

    int insertSelective(StudentExamInfo record);

    StudentExamInfo selectByPrimaryKey(String examId);

    int updateByPrimaryKeySelective(StudentExamInfo record);

    int updateByPrimaryKey(StudentExamInfo record);

	Page<StudentExamInfo> findPage(StudentExamInfoQuery query);

	StudentExamPapeInfo findByExamId(StudentExamInfoQuery query);

	List<StudentHighScore> getStudentExamInfo(
			StudentExamInfoQuery studentExamInfoQuery);

	List<StudentExamInfo> selectEntityPage();

	List<StudentExamInfo> whetherPerformance(StudentExamInfoQuery examInfoQuery);

	List<Float> findExamChange(StudentExamInfoQuery paperInfoQuery);

	StudentExamInfo findByStudentId(StudentExamInfoQuery studentExamInfoQuery);
}