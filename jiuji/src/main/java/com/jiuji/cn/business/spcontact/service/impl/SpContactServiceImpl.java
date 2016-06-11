package com.jiuji.cn.business.spcontact.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiuji.cn.business.spcontact.model.SpContact;
import com.jiuji.cn.business.spcontact.service.SpContactService;  
import com.jiuji.cn.business.spcontact.dao.SpContactMapper;

@Service
public class SpContactServiceImpl implements SpContactService {

	@Autowired
	SpContactMapper spContactMapper;
	  
	@Override
	public void save(SpContact spContact) {
		// TODO Auto-generated method stub
		spContactMapper.insert(spContact); 
	}
 

}
