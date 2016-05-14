package com.xue.dao;

import java.util.List;

import com.xue.dto.OpenCourseRecodeQuery;
import com.xue.dto.OpenCourseRecodeResult;
import com.xue.model.BsUserShopSet;
import com.xue.model.StudentHighScore;
import com.xue.vo.query.BsUserShopSetQuery;

public interface BsUserShopSetMapper {

	List<OpenCourseRecodeResult> queryAllStudentShop(OpenCourseRecodeQuery openCourseRecode);
	
	void insert(BsUserShopSet bsUserShopSet);

	int getOpenCourseRecodeCount(OpenCourseRecodeQuery openCourseRecode);

	List<BsUserShopSet> findAllMajor(String userId);

	List<BsUserShopSet> findMajor(String userId);


	List<BsUserShopSet> queryBsUserShopSet(BsUserShopSet bsUserShopSet);

	List<StudentHighScore> getAchivementInfo(BsUserShopSetQuery bsUserShopSetQuery);

	List<StudentHighScore> getAchivementInfoByClassId(BsUserShopSetQuery bsUserShopSetQuery);

	List<StudentHighScore> getAchivementInfoByMonth();

	List<StudentHighScore> getMouthAchivementInfo(BsUserShopSetQuery bsUserShopSetQuery);

	List<StudentHighScore> getMouthAchivementInfoByClassId(BsUserShopSetQuery bsUserShopSetQuery);

	List<BsUserShopSet> findShowMajor(String userId);

	Integer findAllPayInfo(String userId);
}
