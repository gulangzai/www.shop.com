/*
 * BsuserOpinionMapper.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2016-01-27 Created
 */
package com.xue.dao;

import java.util.List;

import com.xue.model.BsuserOpinion;

public interface BsuserOpinionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BsuserOpinion record);

    int insertSelective(BsuserOpinion record);

    BsuserOpinion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BsuserOpinion record);

    int updateByPrimaryKey(BsuserOpinion record);

	List<BsuserOpinion> findOpinion(String userId);
}