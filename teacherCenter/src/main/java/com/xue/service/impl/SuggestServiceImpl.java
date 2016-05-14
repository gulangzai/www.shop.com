package com.xue.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xue.dao.SuggestMapper;
import com.xue.model.Suggest;
import com.xue.service.SuggestService;

@Service
public class SuggestServiceImpl implements SuggestService {

	@Autowired
	SuggestMapper suggestMapper;
	
	@Override
	public List<Suggest> getAllSuggest() {
		// TODO Auto-generated method stub
		return suggestMapper.getAllSuggest();
	}

}
