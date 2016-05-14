/*
 * BsUserNoticSetMapper.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2016-01-17 Created
 */
package com.xue.dao;

import java.util.List;

import com.xue.model.BsUserNoticSet;
import com.xue.model.BsUserNoticSetKey;

public interface BsUserNoticSetMapper {
    int deleteByPrimaryKey(BsUserNoticSetKey key);

    int insert(BsUserNoticSet record);

    int insertSelective(BsUserNoticSet record);

    BsUserNoticSet selectByPrimaryKey(BsUserNoticSetKey key);

    int updateByPrimaryKeySelective(BsUserNoticSet record);

    int updateByPrimaryKey(BsUserNoticSet record);

	List<BsUserNoticSet> findNoticeByUserId();

	void batchInsert(List<BsUserNoticSet> noticSets);

	int readNotice();

	List<BsUserNoticSet> findNoticeInUserId(List<Integer> list);

	int readNoNotice();
}