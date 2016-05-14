package com.fh.service.information.pictures;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;

import cn.lanbao.com.jopo.User;

@Service("productService")
public class ProductService {
	
	public ProductService(){}
	
	@Resource(name="daoSupport")
	private DaoSupport dao;
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ProductMapper.datalistPage", page);
	}
  
}
