package com.xue.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xue.dao.NoticeMapper;
import com.xue.dto.SelfNoticeQuery;
import com.xue.model.Notice;
import com.xue.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	NoticeMapper noticeMapper;
	
	@Override
	public List<Notice> getSelfNotice(SelfNoticeQuery selfNoticeQuery) {
		// TODO Auto-generated method stub
		return noticeMapper.getSelfNotice(selfNoticeQuery);
	}

}
