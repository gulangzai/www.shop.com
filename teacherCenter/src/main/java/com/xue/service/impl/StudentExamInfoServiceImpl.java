package com.xue.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xue.dao.StudentExamInfoMapper;
import com.xue.dao.StudentInfoMapper;
import com.xue.model.PaperInfo;
import com.xue.model.StudentExamInfo;
import com.xue.model.StudentExamPapeInfo;
import com.xue.model.StudentHighScore;
import com.xue.model.StudentInfo;
import com.xue.service.StudentExamInfoService;
import com.xue.service.StudentInfoService;
import com.xue.vo.query.PaperInfoQuery;
import com.xue.vo.query.StudentExamInfoQuery;

@Service
public class StudentExamInfoServiceImpl implements StudentExamInfoService {
	@Autowired
	private StudentExamInfoMapper studentExamInfoMapper;

	@Override
	@Transactional(readOnly=true)
	public Page<StudentExamInfo> findPage(StudentInfo studentInfo) {
		StudentExamInfoQuery query = new StudentExamInfoQuery();
		query.setStudentId(studentInfo.getStudentId());
		query.setSourceType(1);
		PageHelper.startPage(1, 10);
		Page<StudentExamInfo> studentExamInfos=studentExamInfoMapper.findPage(query);
		return studentExamInfos;
	}

	@Override
	@Transactional(readOnly=true)
	public StudentExamPapeInfo findByExamId(StudentExamInfoQuery query) {
		return studentExamInfoMapper.findByExamId(query);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<StudentExamInfo> findPage(
			StudentExamInfoQuery studentExamInfoQuery) {
		PageHelper.startPage(studentExamInfoQuery.getPageNumber(), studentExamInfoQuery.getPageSize());
		Page<StudentExamInfo> studentExamInfos=studentExamInfoMapper.findPage(studentExamInfoQuery);
		return studentExamInfos;
	}

	@Override
	public List<StudentHighScore> getStudentExamInfo(StudentInfo studentInfo,String majorId, String subjectId) {
		StudentExamInfoQuery studentExamInfoQuery = new StudentExamInfoQuery();
		studentExamInfoQuery.setStudentId(studentInfo.getStudentId());
		studentExamInfoQuery.setExamResult(majorId);
		studentExamInfoQuery.setExamSubject(subjectId);
		return studentExamInfoMapper.getStudentExamInfo(studentExamInfoQuery);
	}
	
	@Override
	public List<StudentExamInfo> selectEntityPage() {
		List<StudentExamInfo> listep = new ArrayList<StudentExamInfo>();
		List<StudentExamInfo> list = studentExamInfoMapper.selectEntityPage();
		for (int i = 0; i < list.size(); i++) {
			StudentExamInfo epa =list.get(i);
			Integer ranking=epa.getRanking();
			Integer allScore=epa.getAllScore();
			Integer questNum=epa.getQuestNum();
			Integer rightNum=epa.getRightNum();
			Float score=epa.getScore();
			if (ranking == null)
				ranking = 0;

			if (allScore == null)
				allScore = 0;

			if (questNum == null)
				questNum = 0;

			if (rightNum == null)
				rightNum = 0;

			if (score == null)
				score = 0F;
			epa.setRanking(ranking);
			epa.setAllScore(allScore);
			epa.setQuestNum(questNum);
			epa.setRightNum(rightNum);
			epa.setScore(score);
			listep.add(epa);
		}
		return listep;
	}

	@Override
	public int insert(StudentExamInfo examinfo) {
		return studentExamInfoMapper.insert(examinfo);
	}

	@Override
	public List<StudentExamInfo> whetherPerformance(Integer paperId,StudentInfo stu) {
		StudentExamInfoQuery examInfoQuery = new StudentExamInfoQuery();
		examInfoQuery.setPaperId(paperId);
		examInfoQuery.setStudentId(stu.getStudentId());
		return studentExamInfoMapper.whetherPerformance(examInfoQuery);
	}

	@Override
	public List<Float> findExamChange(StudentInfo studentInfo,
			PaperInfo paperInfo) {
		StudentExamInfoQuery paperInfoQuery = new StudentExamInfoQuery();
		paperInfoQuery.setPaperId(paperInfo.getPaperid());
		paperInfoQuery.setStudentId(studentInfo.getStudentId());
		return studentExamInfoMapper.findExamChange(paperInfoQuery);
	}

	@Override
	@Transactional(readOnly=true)
	public StudentExamInfo findByStudentId(StudentInfo studentInfo,
			Integer paperId) {
		StudentExamInfoQuery studentExamInfoQuery = new StudentExamInfoQuery();
		studentExamInfoQuery.setStudentId(studentInfo.getStudentId());
		studentExamInfoQuery.setPaperId(paperId);
		return studentExamInfoMapper.findByStudentId(studentExamInfoQuery);
	}

}
