package com.xue.service;

import java.util.List;

import com.xue.model.BsCallbackInfo;
import com.xue.vo.query.BsCallbackInfoQuery;

public interface BsCallbackInfoService {

	List<BsCallbackInfo> selectByTeacherAndStudent(BsCallbackInfoQuery bsCallbackInfoQuery);

	void addCallbackInfo(BsCallbackInfo bsCallbackInfo);

	int findNoticeCount(BsCallbackInfoQuery bsCallbackInfoQuery);

}
