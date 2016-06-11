package com.jiuji.cn.business.tbstandard.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiuji.cn.business.tbstandard.dao.TbStandardMapper;
import com.jiuji.cn.business.tbstandard.model.TbStandard;
import com.jiuji.cn.business.tbstandard.service.TbStandardService; 

@Service
public class TbStandardServiceImpl implements TbStandardService {

	@Autowired
	TbStandardMapper tbStandardMapper;
	  
	@Override
	public void save(TbStandard tbStandard) {
		// TODO Auto-generated method stub
		tbStandardMapper.insert(tbStandard); 
	}

	@Override
	public List<TbStandard> queryByProductId(TbStandard tbStandard) {
		// TODO Auto-generated method stub
		return tbStandardMapper.queryByProductId(tbStandard);
	}
 

}
