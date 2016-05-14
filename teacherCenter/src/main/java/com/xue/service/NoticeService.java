package com.xue.service;

import java.util.List;
 
import com.xue.dto.SelfNoticeQuery;
import com.xue.model.Notice;

public interface NoticeService {

	List<Notice> getSelfNotice(SelfNoticeQuery selfNotice);

}
