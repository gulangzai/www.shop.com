package com.xue.service;

import java.util.List;

import com.xue.dto.OpenCourseRecodeQuery;
import com.xue.dto.OpenCourseRecodeResult;
import com.xue.model.BsUserShopSet;

public interface BsUserShopSetService {

	List<OpenCourseRecodeResult> getOpenCourseRecode(OpenCourseRecodeQuery openCourseRecode);

	/*开通课程*/
	void openCourse(BsUserShopSet bsUserShopSet);

	int getOpenCourseRecodeCount(OpenCourseRecodeQuery openCourseRecode);

	List<BsUserShopSet> findAllMajor(String string);

	/**
	 * @Description:根据userID查询这个年度消费的金额
	 * @param userId
	 * @return
	 * Integer
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2016年1月27日 下午1:17:00
	 */
	Integer findAllPayInfo(String userId);

}
