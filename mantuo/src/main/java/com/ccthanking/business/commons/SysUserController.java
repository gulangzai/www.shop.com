/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    commons.service.SysUserController.java
 * 创建日期： 2015-10-26 下午 10:31:55
 * 功能：    服务控制类：用户信息
 * 所含类:   SysUserService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-26 下午 10:31:55  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.commons;


import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.commons.service.SysUserService;
import com.ccthanking.business.commons.vo.SysUserVO;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;
import com.ccthanking.framework.util.DateUtil;
import com.ccthanking.framework.util.MailSendMail;


/**
 * <p> SysUserController.java </p>
 * <p> 功能：用户信息 </p>
 *
 * <p><a href="SysUserController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-26
 * 
 */

@Controller
@RequestMapping("/commons/SysUserController")
public class SysUserController {

	private static Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private SysUserService sysUserService;

    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "query")
    @ResponseBody
    public requestJson queryCondition(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【用户信息查询】",user.getName());
        String userId = request.getParameter("userId");
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.sysUserService.queryCondition(userId);
        if("".equals(domresult) && domresult != null){
        	 j.setSuccess(true);
        }
        j.setMsg(domresult);
        return j;

    }
    
    /**
     * 查询json
     * 权限检查
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryAuthority")
    @ResponseBody
    public requestJson queryAuthority(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【用户信息查询】",user.getName());
    	String projectUserId = request.getParameter("projectUserId");
    	String projectId = request.getParameter("projectId");
    	String  authorityCode = request.getParameter("authorityCode");
    	HashMap<String, String> map = new HashMap<String, String>();
    	map.put("projectId", projectId);
    	map.put("projectUserId", projectUserId);
    	map.put("authorityCode", authorityCode);
    	
    	requestJson j = new requestJson();
    	boolean flag = this.sysUserService.queryAuthority(map);
    	j.setSuccess(flag);
    	return j;
    	
    }
    
    /**
     * 查询json
     * 权限检查
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "checkDirAuthority")
    @ResponseBody
    public requestJson checkDirAuthority(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【用户信息查询】",user.getName());
    	String projectUserId = request.getParameter("projectUserId");
    	String projectId = request.getParameter("projectId");
    	String  dirUid = request.getParameter("dirUid");
    	HashMap<String, String> map = new HashMap<String, String>();
    	map.put("projectId", projectId);
    	map.put("projectUserId", projectUserId);
    	map.put("dirUid", dirUid);
    	
    	requestJson j = new requestJson();
    	boolean flag = this.sysUserService.checkDirAuthority(map);
    	j.setSuccess(flag);
    	return j;
    	
    }
    
    /**
     * 检查登陆名是否重复
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "checkName")
    @ResponseBody
    public void checkName(final HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String name = request.getParameter("param");
    	String userId= request.getParameter("USER_UID");
    	boolean domresult = false;//
	    if(userId !=null && !"".equals(userId)){//说明是 修改页面的 请求
    	    domresult = this.sysUserService.checkName(name,userId);
    	}else{
    	    domresult = this.sysUserService.checkName(name,"uidIsNull");
    	}
		response.setCharacterEncoding("GBK");
		response.setContentType("application/x-json");
		PrintWriter out = response.getWriter();
		Map<String, String> c = new HashMap<String, String>();
		if (domresult) {
				c.put("info", "该登录名可以使用！");
				c.put("status", "y");
		} else {
				c.put("info", "该登陆名已被使用，请重新输入一个登陆名！");
				c.put("status", "n");
		}
		
		/**将一个map转换为json 并输出**/
		JSONObject object = JSONObject.fromObject(c);
		out.print(object.toString());
    	
    }
    

    /**
     * 忘记密码时发送邮件
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "sendResetPasswordMail")
    @ResponseBody
    protected requestJson sendResetPasswordMail(final HttpServletRequest request, requestJson json) throws Exception {
        logger.info("<{}>执行操作【重置密码时发送邮件邀请】");
    	boolean flag = false;
    	requestJson j = new requestJson(); 
    	String loginName = request.getParameter("username"); 
    	List<Map<String,String>> sysUserVos =  sysUserService.getSysUserByLoginName(loginName); 
        //时间戳为当前时间的下一天
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date next_date = calendar.getTime();
    	//如果存在该用户
    	if(sysUserVos.size()!=0){
    		Map sysUserVo = sysUserVos.get(0);
    		String email = (String) sysUserVo.get("EMAIL");
    		String subject = "重置密码验证";
        	String content = "  尊敬的曼拓用户"+sysUserVo.get("USER_NAME")+":\r\n   您好!";
        	String Url  = "\r\n"+"  您可以点击如下链接来重置密码  http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/jsp/business/user/user-reset.jsp?user_uid="+sysUserVo.get("USER_UID")+"&timestamp="+next_date.getTime();
        	flag= MailSendMail.sendMail(subject,content+Url,email);
        	j.setSuccess(flag); 
        	j.setMsg("邮箱验证发送成功");
      } else{
    	    //不存在该用户
    	     flag=false;
    		 j.setSuccess(flag);
    		 j.setMsg("不存在该用户");
    	} 
    	return j;
    }
    
    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "check")
    @ResponseBody
    public void check(final HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String param = request.getParameter("param");
    	String userId= request.getParameter("USER_UID");
    	boolean domresult = false;
	    if(userId !=null && !"".equals(userId)){//说明是 修改页面的 请求
    	    domresult = this.sysUserService.checkEmail(param,userId);
    	}else{
    	    domresult = this.sysUserService.checkEmail(param,"uidIsNull");
    	}
    	
		response.setCharacterEncoding("GBK");
		response.setContentType("application/x-json");
		PrintWriter out = response.getWriter();
		Map<String, String> c = new HashMap<String, String>();
		if (domresult) {
				c.put("info", "该邮箱可以使用！");
				c.put("status", "y");
		} else {
				c.put("info", "该邮箱已注册，请换一个邮箱！");
				c.put("status", "n");
		}
		JSONObject object = JSONObject.fromObject(c);
		out.print(object.toString());
    	
    }
    

    /**
     * 检查登陆名是否重复
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "checkPwd")
    @ResponseBody
    public void checkPwd(final HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String pwd = request.getParameter("param");
    	String userId= request.getParameter("USER_UID");
    	boolean domresult = this.sysUserService.checkPwd(pwd,userId);
		response.setCharacterEncoding("GBK");
		response.setContentType("application/x-json");
		PrintWriter out = response.getWriter();
		Map<String, String> c = new HashMap<String, String>();
		if (domresult) {
				c.put("info", "密码正确");
				c.put("status", "y");
		} else {
				c.put("info", "原密码有误，请重新输入。");
				c.put("status", "n");
		}
		
		/**将一个map转换为json 并输出**/
		JSONObject object = JSONObject.fromObject(c);
		out.print(object.toString());
    	
    }
    /**
     * 保存数据json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params ="insert")
    @ResponseBody
    protected requestJson insert(final HttpServletRequest request, requestJson json,HttpSession session) throws Exception {
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sysUserService.insert(json.getMsg());
        j.setMsg(resultVO); 
        
        //session里removeuserId
        session.removeAttribute("userId");
        return j;
    }
    
    @RequestMapping(params="resetPassword")
    @ResponseBody
    protected requestJson resetPassword(final HttpServletRequest request, requestJson json) throws Exception {
    	 requestJson j = this.sysUserService.resetPassword(json.getMsg());
         return j;
    }

    /**
     * 修改记录.
     * 
     * @param request
     * @param json
     * @return
     * @throws Exception
     * @since v1.00
     */
    @RequestMapping(params = "update")
    @ResponseBody
    protected requestJson update(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【用户信息修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sysUserService.update(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

    /**
     * 删除记录.
     * 
     * @param request
     * @param json
     * @return
     * @throws Exception
     * @since v1.00
     */
    @RequestMapping(params = "delete")
    @ResponseBody
    public requestJson delete(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【用户信息删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sysUserService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
