package com.fh.controller.information.product;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.information.pictures.ProductService;
import com.fh.util.PageData;

import cn.lanbao.com.common.ResultGrid;
import cn.lanbao.com.jopo.User;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value="/productCtrl")
public class ProductCtrl extends BaseController {
	
	@Resource(name="productService")
	private ProductService productService;
	
	@RequestMapping(value="/getProductList")
	@ResponseBody
	public List<PageData> getProductList(Page page,HttpServletRequest req, HttpServletResponse res){  
		PageData pd = this.getPageData();   
		page.setCurrentPage(Integer.parseInt(pd.getString("page")));
		page.setShowCount(Integer.parseInt( pd.getString("rows")));
		System.out.println(page.getCurrentPage()+"---"+page.getShowCount());
		page.setPd(pd); 
		List<PageData> list = null;
		try {
			list = productService.list(page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
	}
}
