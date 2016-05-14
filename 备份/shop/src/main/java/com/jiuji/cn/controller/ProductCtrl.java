package com.jiuji.cn.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiuji.cn.model.TPicture;
import com.jiuji.cn.model.TProduct;
import com.jiuji.cn.result.ProjectPictureResult;
import com.jiuji.cn.service.PictureService;
import com.jiuji.cn.service.ProductService;
import com.jiuji.cn.service.UserService;

import javacommon.base.ResultAction;
import javacommon.base.Suggestion;
import javacommon.base.Vo;

@Controller
@RequestMapping("/productCtrl")
public class ProductCtrl {
	
	@Autowired
	ProductService productService;
	@Autowired
	PictureService pictureService;
	
	@RequestMapping("/toSingleProduct") 
	public String toSingleProduct(String f_ProductId,Model model,HttpServletRequest request,HttpServletResponse response,HttpSession session){  
		//String message =  userService.login(username,password,checkboxmark,model,request,response,session);  
		TProduct tproduct = productService.queryById(f_ProductId);
		List<TPicture> tPictures = pictureService.queryByProduct(tproduct);  
		model.addAttribute("singleProduct", tproduct);
		model.addAttribute("t_pictures",tPictures);
		return "/modules/product/singleProduct";
	}
	
	@RequestMapping("/toProjectListPage") 
	public String toProjectListPage(String f_clsId,Model model,HttpServletRequest request,HttpServletResponse response,HttpSession session){  
		//String message =  userService.login(username,password,checkboxmark,model,request,response,session);  
		List<TProduct> tproducts = productService.queryByClsId(f_clsId);
		List<ProjectPictureResult> projectPictureResults = new ArrayList<ProjectPictureResult>();
		for(TProduct tproduct:tproducts){
			List<TPicture> tPictures = pictureService.queryByProduct(tproduct);  
			ProjectPictureResult projectPictureResult = new ProjectPictureResult(tproduct,tPictures);
			projectPictureResults.add(projectPictureResult);
		} 
		model.addAttribute("projectPictureResults", projectPictureResults); 
		return "/modules/product/projectList";
	}
	
 
	@RequestMapping("/testCode")
	@ResponseBody
    public Vo testCode(){ 
		 Vo ra = new Vo(); 
		List suggestions = new ArrayList();
		//Map suggestions = new HashMap();
		Suggestion sug1= new Suggestion("好好学习1",null);
		Suggestion sug2= new Suggestion("好好学习2",null);
		suggestions.add(sug1);
		suggestions.add(sug2);
		ra.setSuggestions(suggestions);
		return ra;
	}
}
