package cn.lanbao.com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.fh.controller.base.BaseController;

import cn.lanbao.com.common.ResultAction;
import cn.lanbao.com.common.ResultGrid;
import cn.lanbao.com.jopo.BaseJopo;
import cn.lanbao.com.jopo.User;
import cn.lanbao.com.service.MyStudentService;
import cn.lanbao.com.service.MyUserService; 
import cn.lanbao.com.util.CommonUtil;
import cn.lanbao.com.util.Json;
import cn.lanbao.com.util.TranscodUtil;

@Controller
@RequestMapping(value="/MyuserCtrl")
public class MyUserController extends BaseController{

	@Autowired(required=true)
	private MyUserService userService;
 
  
	public ModelAndView save(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		String name =  req.getParameter("name");
		String age  = req.getParameter("age");
	    User user = new User(name,Integer.parseInt(age));
	     //userService.save(user);
	    ModelAndView modelAndView = new ModelAndView("redirect:/success.jsp");
		return modelAndView;
	}
	
	public void updateUser(HttpServletRequest req,HttpServletResponse res)throws Exception{
		String str = "{\"status\":\"ok\",\"message\":\"更新失败\"}";  
		/*String name =  req.getParameter("name");
		String age  = req.getParameter("age");
		String id = req.getParameter("id");
	    User user = new User(name,Integer.parseInt(age));
	    user.setId(Integer.parseInt(id));
	   */
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=utf-8");
		try {
			 User user = (User)CommonUtil.objectFromReq(User.class, req);
			 userService.updateUser(user);
			 str = "{\"status\":\"ok\",\"message\":\"更新成功\"}";  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 	res.getWriter().write(str); 
	}
	
	public void saveUser(HttpServletRequest req,HttpServletResponse res)throws Exception{
		String str = "{\"status\":\"ok\",\"message\":\"添加成功\"}"; 
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=utf-8");
		try { 
			/*String name =  req.getParameter("name");
			String age  = req.getParameter("age");
			String sex = req.getParameter("sex");*/
			String birthday = req.getParameter("birthday");
			Date birthdayd = TranscodUtil.stringToDate(req,birthday);
			
			User user =  (User) CommonUtil.objectFromReq(User.class, req);
			
			userService.save(user); 
			res.getWriter().write(str);
			System.out.println("str:"+str);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			str = "{\"status\":\"ok\",\"message\":\"添加失败\"}"; 
			res.getWriter().write(str);
		}
	}
	
	public void deleteUser(HttpServletRequest req,HttpServletResponse res)throws Exception{
		String str = "{\"status\":\"ok\",\"message\":\"ɾ���û��ɹ�\"}"; 
		String id =  req.getParameter("id");  
		if(id!=null){
			  userService.deleteUserById(Integer.parseInt(id));
				res.setCharacterEncoding("UTF-8");
				res.setContentType("text/html;charset=utf-8");
				res.getWriter().write(str);
				System.out.println("str:"+str);
		} 
	}
	
	
	
	public void getPageUserList(HttpServletRequest req,
			HttpServletResponse res)throws Exception{   
		
		int curpage = 1;
		int pageSize = 10; 
		String page = req.getParameter("page");
		String rows = req.getParameter("rows");
		 
		
		if(StringUtils.isNotEmpty(page))
			curpage = Integer.parseInt(page);
		
		if(StringUtils.isNotEmpty(rows))
			pageSize =Integer.parseInt(rows);
	
		BaseJopo baseJopo = (BaseJopo) CommonUtil.objectFromReq(BaseJopo.class, req);
		
System.out.println("UserController"+page+" rows:"+rows);

		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		List<User> list  =  userService.getPageUserLists(curpage,pageSize); 
		String str = Json.praseObject(userService.getPageCount(),list); 
		System.out.println("str:"+str);
		try {
			res.getWriter().write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//检索用户
	public void searchUser(HttpServletRequest req,
			HttpServletResponse res){ 
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=utf-8");
		try{ 
		    User user = (User) CommonUtil.objectFromReq(User.class,req); 
		    List<User> list = userService.searchUser(user); 
			String str =Json.praseObject(list.size(), list);  
			res.getWriter().write(str);
			System.out.println("str:"+str);
		}catch(Exception e){
			e.printStackTrace();
		} 
	} 
}
