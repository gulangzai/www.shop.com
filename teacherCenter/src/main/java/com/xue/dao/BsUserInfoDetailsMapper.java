/*
 * BsUserInfoDetailsMapper.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2016-01-17 Created
 */
package com.xue.dao;

import com.xue.dto.BsUserInfoDetail;
import com.xue.model.BsUserInfo;
import com.xue.model.BsUserInfoDetails;
import com.xue.vo.query.BsUserInfoQuery;

public interface BsUserInfoDetailsMapper {
    int deleteByPrimaryKey(String userId);

    int insert(BsUserInfoDetails record);

    int insertSelective(BsUserInfoDetails record);

    BsUserInfoDetails selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(BsUserInfoDetails record);

    int updateByPrimaryKey(BsUserInfoDetails record);

	BsUserInfoDetail findByUserId(String userId);

	BsUserInfoDetail login(BsUserInfoQuery bsUserInfoQuery);
}