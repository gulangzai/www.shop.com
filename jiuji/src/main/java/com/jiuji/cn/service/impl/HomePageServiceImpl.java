package com.jiuji.cn.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.jiuji.cn.dao.TCarouselMapper;
import com.jiuji.cn.dao.TClassMapper;
import com.jiuji.cn.dao.TProductMapper;
import com.jiuji.cn.model.TCarousel;
import com.jiuji.cn.model.TClass;
import com.jiuji.cn.model.TProduct;
import com.jiuji.cn.model.TProductDto;
import com.jiuji.cn.service.HomePageService;

import java.util.List;

@Service
public class HomePageServiceImpl implements HomePageService {

	@Autowired
	TCarouselMapper tcarouselMapper;
	@Autowired
	TClassMapper tclassMapper;
	@Autowired
	TProductMapper tproductMapper;
	
	
	@Override
	public void queryInfomation(Model model, HttpSession session) {
		// TODO Auto-generated method stub
		//轮播图片
		List<TCarousel> tCarousels = tcarouselMapper.queryAll();
		//类目
		List<TClass> tclasses = tclassMapper.queryAll();
		for (TClass tClass : tclasses) {
			tClass.setSonTClasses(tclassMapper.queryAllson(tClass));
		}
		model.addAttribute("tCarousels", tCarousels);
		//相关类目
		model.addAttribute("tclasses",tclasses);
		session.setAttribute("tclasses", tclasses);
		
		
		//获取最新发布的5个商品
		List<TProductDto> tproducts = tproductMapper.findNewGood();
		//获取热门发布的5个商品
		List<TProductDto> tproductHots = tproductMapper.findNewHot();
		//获取特卖发布的5个商品
		List<TProductDto> tproductSpecials = tproductMapper.findNewSpecial();
		
		model.addAttribute("tproducts",tproducts);
		model.addAttribute("tproductHots",tproductHots);
		model.addAttribute("tproductSpecials",tproductSpecials);
	}
}
