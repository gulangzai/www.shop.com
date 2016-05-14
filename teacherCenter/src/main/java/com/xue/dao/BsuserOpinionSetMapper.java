/*
 * BsuserOpinionSetMapper.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2016-01-27 Created
 */
package com.xue.dao;

import com.xue.model.BsuserOpinionSet;

public interface BsuserOpinionSetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BsuserOpinionSet record);

    int insertSelective(BsuserOpinionSet record);

    BsuserOpinionSet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BsuserOpinionSet record);

    int updateByPrimaryKey(BsuserOpinionSet record);
}