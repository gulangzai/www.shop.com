/*
 * BsNoticeMapper.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2016-01-17 Created
 */
package com.xue.dao;

import java.util.List;

import com.xue.model.BsNotice;

public interface BsNoticeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BsNotice record);

    int insertSelective(BsNotice record);

    BsNotice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BsNotice record);

    int updateByPrimaryKey(BsNotice record);

	List<BsNotice> findNotice();

	Integer findNoticeCount();

	List<BsNotice> readPageNotice();

	List<BsNotice> readNoPageNotice();
}