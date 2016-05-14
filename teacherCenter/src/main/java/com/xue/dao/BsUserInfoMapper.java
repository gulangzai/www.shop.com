/*
 * BsUserInfoMapper.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2016-01-17 Created
 */
package com.xue.dao;

import com.xue.model.BsUserInfo;

public interface BsUserInfoMapper {
    int deleteByPrimaryKey(String userId);

    int insert(BsUserInfo record);

    int insertSelective(BsUserInfo record);

    BsUserInfo selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(BsUserInfo record);

    int updateByPrimaryKey(BsUserInfo record);
}