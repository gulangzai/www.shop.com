/*
 * BsUserPayInfoMapper.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2016-01-17 Created
 */
package com.xue.dao;

import java.util.List;

import com.xue.model.BsUserPayInfo;

public interface BsUserPayInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BsUserPayInfo record);

    int insertSelective(BsUserPayInfo record);

    BsUserPayInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BsUserPayInfo record);

    int updateByPrimaryKey(BsUserPayInfo record);

	List<BsUserPayInfo> findUserPayInfo(String userId);

	Integer findAnnualConsumption(String userId);
}