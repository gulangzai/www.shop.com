/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    project.service.BuProjectUserController.java
 * 创建日期： 2015-10-20 下午 05:09:42
 * 功能：    服务控制类：项目用户
 * 所含类:   BuProjectUserService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-20 下午 05:09:42  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.project;


import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.invite.service.SysInviteService;
import com.ccthanking.business.invite.vo.SysInviteVO;
import com.ccthanking.business.message.service.BuUserMessageService;
import com.ccthanking.business.message.vo.BuUserMessageVO;
import com.ccthanking.business.project.service.BuProjectUserService;
import com.ccthanking.business.project.vo.BuProjectRoleVO;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;
import com.ccthanking.framework.service.impl.UserServiceImpl;
import com.ccthanking.framework.util.DBUtil;
import com.ccthanking.framework.util.DesUtil;
import com.ccthanking.framework.util.MailSendMail;


/**
 * <p> BuProjectUserController.java </p>
 * <p> 功能：项目用户 </p>
 *
 * <p><a href="BuProjectUserController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-20
 * 
 */

@Controller
@RequestMapping("/project/buProjectUserController")
public class BuProjectUserController {

	private static Logger logger = LoggerFactory.getLogger(BuProjectUserController.class);

    @Autowired
    private BuProjectUserService buProjectUserService;
    
    @Autowired
    private BuUserMessageService buUserMessageService;
    
    @Autowired
    private SysInviteService sysInviteService;
    
  
    
    

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
        logger.info("<{}>执行操作【项目用户查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.buProjectUserService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    /**
     * 查询 某个项目下的所有用户
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryList")
    @ResponseBody
    public requestJson queryList(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目用户查询】",user.getName());
    	requestJson j = new requestJson();
    	String domresult = "";
    	domresult = this.buProjectUserService.queryList(json.getMsg());
    	j.setMsg(domresult);
    	return j;
    	
    }
    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "checkEmail")
    @ResponseBody
    public requestJson checkEmail(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目用户查询】",user.getName());
    	requestJson j = new requestJson();
    	String email = request.getParameter("email");
    	String project_uid = request.getParameter("project_uid");
    	Map<String,String> map  = this.buProjectUserService.checkEmail(email);
    	
    	if(null != map.get("uuid") && !"".equals(map.get("uuid")) ){
    		if(user.getIdCard().equals(map.get("uuid"))){
        		j.setSuccess(false);
        		j.setMsg("您已经是本项目管理员啦！");
        	}else if(project_uid.equals(map.get("project_uid"))){
        		j.setSuccess(false);
        		j.setMsg("该用户已经是本项目成员啦！");
        	}else{
        		j.setSuccess(true);
        	}
    		
    	}else{
    		j.setSuccess(false);
    		j.setMsg("用户不存在!");
    	}
    	
    	return j; 
    }
    
    @RequestMapping(params = "checkProjectEmail")
    @ResponseBody
    public requestJson checkProjectEmail(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目用户查询】",user.getName());
    	requestJson j = new requestJson();
    	String email = request.getParameter("email");
    	String project_uid = request.getParameter("project_uid");
    	Map<String,String> map  = this.buProjectUserService.checkEmail(email);
    	
    	if(null != map.get("uuid") && !"".equals(map.get("uuid")) ){
    		if(user.getIdCard().equals(map.get("uuid"))){
        		j.setSuccess(false);
        		j.setMsg("您已经是本项目管理员啦！");
        	}else if(project_uid.equals(map.get("project_uid"))){
        		j.setSuccess(false);
        		j.setMsg("该用户已经是本项目成员啦！");
        	}else{
        		j.setSuccess(true);
        	}
    		
    	}else{
    		j.setSuccess(true);
    		//j.setMsg("用户不存在!");
    	} 
    	return j; 
    }
    

    /**
     * 保存数据json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "insert")
    @ResponseBody
    protected requestJson insert(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【项目用户新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buProjectUserService.insert(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    @RequestMapping(params="getRoleByProject")
    @ResponseBody
    protected List getRoleByProject(HttpServletRequest request)throws Exception{
    	String project_uid = request.getParameter("project_uid");
    	List buProjectRoles = buProjectUserService.getRoleByProject(project_uid); 
    	return buProjectRoles;
    }
    /**
     * sys_invite  判断邀请时间，是否加入,没过期，更新加入时间
     * 同意加入项目时,操作bu_project_user, 项目与用户对应
     * bu_project_role_user, 角色跟项目用户对应 
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(params="join")
    @ResponseBody
    protected requestJson join(final HttpServletRequest request)throws Exception{
    	User user = RestContext.getCurrentUser();
    	String userId = user.getIdCard();
    	requestJson j = new requestJson();
    	String status = request.getParameter("STATUS"); 
    	String invite_uid = request.getParameter("invite_uid");
    	String  user_message_uid= request.getParameter("USER_MESSAGE_UID");
    	//是否过期
    	boolean flag = sysInviteService.isOutDate(userId);
    	buUserMessageService.updateById(user_message_uid,status);
    	if(!flag){
    		String project_uid = request.getParameter("PROJECT_UID");
    		String project_role_uid = request.getParameter("project_role_uid"); 
    		//绑定该用户到项目
        	j = this.buProjectUserService.save(userId,project_uid,project_role_uid,user_message_uid);
        	//更新sys_invite加入时间join_date 
        	sysInviteService.updateStatusAndJoinDate(invite_uid,status); 
        	//反馈信息给发起人
        	String content=user.getUsername()+"同意加入你发起的项目";
        	buUserMessageService.insertVo(invite_uid,content); 
    	}else{
    		j.setSuccess(false);
        	j.setMsg("邀请过期了");
    	} 
       return j;
    }
    
    
    
    @RequestMapping(params="noJoin")
    @ResponseBody
    protected requestJson noJoin(final HttpServletRequest request)throws Exception{
    	User user = RestContext.getCurrentUser();
    	requestJson j =  new requestJson();
    	String userId = user.getIdCard();
    	String project_uid = request.getParameter("PROJECT_UID");
    	String  user_message_uid= request.getParameter("USER_MESSAGE_UID");
    	String invite_uid = request.getParameter("invite_uid");
    	String status = request.getParameter("STATUS");
    	//不同意加入邀请
    	sysInviteService.updateStatus(invite_uid,status); 
    	 
    	//反馈信息给发起人
    	String content=user.getUsername()+"不同意加入你邀请的项目";
    	buUserMessageService.insertVo(invite_uid,content); 
        j.setSuccess(true);
        return j;
    }
    
    
    
    /**
     * 发送邮件邀请
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "sendInvite")
    @ResponseBody
    protected requestJson sendInvite(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【发送邮件邀请】",user.getName());
    	requestJson j = new requestJson();
    	
    	String project_uid = request.getParameter("project_uid");
    	String email =  request.getParameter("email");
    	String userId = user.getIdCard();
    	String project_name = request.getParameter("project_name");
    	String role = request.getParameter("role");
    	String roleName = request.getParameter("roleName");
    	String project_role_uid = request.getParameter("project_role_uid");
    	
    	Map<String,String> map = new HashMap<String,String>();
    	map.put("create_user",userId);
    	map.put("project_uid", project_uid);
    	map.put("INVITER_USER",userId);
    	map.put("email", email);
    	
    	Map<String,String> rmap = this.buProjectUserService.insertInvite(map);
    	
    	JSONObject obj = new JSONObject();
    	obj.put("uuid", rmap.get("uuid"));
    	obj.put("project_uid", project_uid);
    	obj.put("userUid", rmap.get("userUid"));
    	obj.put("login", rmap.get("login")); 
    	String status = rmap.get("status");
    	String zcCode = rmap.get("zcCode"); 
    	String userUid = rmap.get("userUid");
    	//uuid="+ DesUtil.encrypt(obj.toString())
    	//如果存在该用户
    	if("true".equals(status)){
    		String subject = project_name+"项目邀请函";
        	String content = "尊敬的"+rmap.get("userName")+":\r\n您好! \r\n用户 "+rmap.get("yqName")+" 邀请您以"+roleName+"角色加入 "+project_name +" 项目  \r\n";
        	String Url  = "请点击链接确认邀请,24小时内有效 ,否则需重新邀请,链接  http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/index.jsp";
        	boolean flag = MailSendMail.sendMail(subject,content+Url,email);
        	j.setSuccess(flag);
        	
        	//存入用户确定信息
        	BuUserMessageVO buUserMessageVo = new BuUserMessageVO();
        	buUserMessageVo.setMessage_content(project_name+"项目邀请你加入");
        	buUserMessageVo.setMessage_time(new Date());
        	buUserMessageVo.setUser_uid(userUid);
        	buUserMessageVo.setStatus("0");
        	buUserMessageVo.setMessage_para1("?invite_uid="+obj.getString("uuid"));
        	buUserMessageVo.setMessage_para2("&project_uid="+project_uid+"&projectName="+project_name+"&role="+role+"&userName="+user.getName());
        	buUserMessageVo.setMessage_para3("&roleName="+roleName+"&project_role_uid="+project_role_uid);
        	buUserMessageVo.setMessage_para4("");
        	buUserMessageVo.setMessage_url("/invite/joinProject.jsp"); 
        	buUserMessageVo.setMessage_type("O");
        	buUserMessageService.save(buUserMessageVo);  
    	} else{
    	    //不存在该用户
    		String subject = project_name+"项目邀请函";
        	String content = "您好! \r\n"+rmap.get("yqName")+" 邀请您加入 "+project_name +" 项目  \r\n";
        	String Url  = "请点击链接确认邀请,24小时内有效 ,否则需重新邀请,链接  http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/userController/toRegister.do?inviteCode="+rmap.get("invite_code");
        	
         	//此用户没有注册前发message。将注册码记录到para4里。
        	BuUserMessageVO buUserMessageVo = new BuUserMessageVO();
        	buUserMessageVo.setMessage_content(project_name+"项目邀请你加入");
        	buUserMessageVo.setMessage_time(new Date());
        	buUserMessageVo.setUser_uid("0");
        	buUserMessageVo.setStatus("0");
        	buUserMessageVo.setMessage_para1("?invite_uid="+obj.getString("uuid"));
        	buUserMessageVo.setMessage_para2("&project_uid="+project_uid+"&projectName="+project_name+"&role="+role+"&userName="+user.getName());
        	buUserMessageVo.setMessage_para3("&roleName="+roleName+"&project_role_uid="+project_role_uid);
        	buUserMessageVo.setMessage_para4(rmap.get("invite_code"));
        	buUserMessageVo.setMessage_url("/invite/joinProject.jsp"); 
        	buUserMessageVo.setMessage_type("O");
        	buUserMessageService.save(buUserMessageVo);  
        	
        	boolean flag = MailSendMail.sendMail(subject,content+Url,email);
        	j.setSuccess(flag);
    	} 
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
        logger.info("<{}>执行操作【项目用户修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buProjectUserService.update(json.getMsg());
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
        logger.info("<{}>执行操作【项目用户删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buProjectUserService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    /**
     * 保存数据json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "userConfirm")
    @ResponseBody
    protected requestJson userConfirm(final HttpServletRequest request, requestJson json) throws Exception {
        //User user = RestContext.getCurrentUser();
        //logger.info("<{}>执行操作【项目用户新增】",user.getName());
        requestJson j = new requestJson();
        Map<String,String> map = new HashMap<String, String>();
        String jsonstr = request.getParameter("uuid");
        String uuid = "";
        String username = "";
        if(null!=jsonstr&&!"".equals(jsonstr)){
        	JSONObject obj = JSONObject.fromObject(DesUtil.decrypt(jsonstr));
        	uuid = obj.getString("uuid");
        	username = obj.getString("login");
        }
        map = this.buProjectUserService.checkTime(uuid);

    	
        if("true".equals(map.get("flag"))){
        	map  = this.buProjectUserService.userConfirm(uuid);
        	j.setMsg(map.get("msg"));
        	j.setObj(map.get("flag"));
        	User user = new UserServiceImpl().getUserByName(username);
        	if(user != null) {
    			// 设置threadLocal信息
    			RestContext.setCurrentUserInThread(user);
    			RestContext.setCurrentSession(request.getSession());

    			request.getSession().setAttribute("userId", user.getAccount());
    		}	
        }else{
        	j.setMsg(map.get("msg"));
        	j.setObj(map.get("flag"));
        }
        return j;
    }

}
