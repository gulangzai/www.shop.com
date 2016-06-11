package com.jiuji.cn.business.tbstandard.service;

import java.util.List; 
import com.jiuji.cn.business.spcontact.model.SpContact;
import com.jiuji.cn.business.tbstandard.model.TbStandard; 

public interface TbStandardService {
 
	
	public void save(TbStandard tbStandard);

	public List<TbStandard> queryByProductId(TbStandard tbStandard);

}
