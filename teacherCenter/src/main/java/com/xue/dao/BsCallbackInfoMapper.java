/*
 * BsCallbackInfoMapper.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2016-01-17 Created
 */
package com.xue.dao;

import java.util.List;

import com.xue.model.BsCallbackInfo;
import com.xue.vo.query.BsCallbackInfoQuery;

public interface BsCallbackInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BsCallbackInfo record);

    int insertSelective(BsCallbackInfo record);

    BsCallbackInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BsCallbackInfo record);

    int updateByPrimaryKey(BsCallbackInfo record);

	List<BsCallbackInfo> findBsCallbackInfo();

	List<BsCallbackInfo> selectByTeacherAndStudent(BsCallbackInfoQuery bsCallbackInfoQuery);

	int findNoticeCount(BsCallbackInfoQuery bsCallbackInfoQuery);
}