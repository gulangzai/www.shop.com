package com.xue.dao;

import java.util.List;

import com.xue.dto.SelfNoticeQuery;
import com.xue.model.Notice;

public interface NoticeMapper {

	List<Notice> getSelfNotice(SelfNoticeQuery selfNoticeQuery);

}
