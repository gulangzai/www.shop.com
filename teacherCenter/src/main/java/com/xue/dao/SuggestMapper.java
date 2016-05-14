package com.xue.dao;

import java.util.List;

import com.xue.dto.SelfNoticeQuery;
import com.xue.model.Notice;
import com.xue.model.Suggest;

public interface SuggestMapper {
 
	List<Suggest> getAllSuggest();

}
