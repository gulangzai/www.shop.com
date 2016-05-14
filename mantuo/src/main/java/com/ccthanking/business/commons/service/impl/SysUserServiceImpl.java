/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    commons.service.SysUserService.java
 * 创建日期： 2015-10-26 下午 10:31:55
 * 功能：    接口：用户信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-26 下午 10:31:55  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.commons.service.impl;


import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.dbutils.DbUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.common.BusinessUtil;
import com.ccthanking.common.EventManager;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.common.vo.EventVO;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.coreapp.orgmanage.UserManager;
import com.ccthanking.framework.fileUpload.service.FileUploadUtilService;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.model.requestJson;

import com.ccthanking.business.commons.vo.SysUserVO;
import com.ccthanking.business.commons.dao.SysUserDao;
import com.ccthanking.business.commons.service.SysUserService;
import com.ccthanking.business.invite.dao.SysInviteDao;
import com.ccthanking.business.invite.service.SysInviteService;
import com.ccthanking.business.invite.vo.SysInviteVO;
import com.ccthanking.business.message.dao.BuUserMessageDao;
import com.ccthanking.business.message.service.BuUserMessageService;
import com.ccthanking.business.message.vo.BuUserMessageVO;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.ccthanking.framework.util.Encipher;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> SysUserService.java </p>
 * <p> 功能：用户信息 </p>
 *
 * <p><a href="SysUserService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-26
 * 
 */

@Service
public class SysUserServiceImpl extends Base1ServiceImpl<SysUserVO, String> implements SysUserService {

	private static Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);
	

    @Autowired
    private BuUserMessageDao buUserMessageDao;
    @Autowired
    BuUserMessageService buUserMessageService;
    
    @Autowired
    SysInviteService sysInviteService;
    @Autowired
    SysInviteDao sysInviteDao;
	@Autowired
    private SysUserDao sysUserDao;
    

    // @Override
    public String queryCondition(String userId) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = sysUserDao.queryCondition(userId, null, null);
        }catch (DaoException e) {
        	logger.error("用户信息{}", e.getMessage());
			SystemException.handleMessageException("用户信息查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {
		
        String resultVO = null;
        SysUserVO vo = new SysUserVO();
        Connection conn = DBUtil.getConnection();
        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj =(JSONObject) list.get(0);
            vo.setValueFromJson((JSONObject) list.get(0));
            String mima= vo.getPwd();
            String targetUid = obj.getString("target_uid");
            String pwd = "";
    		if (Constants.ENCODER_TYPE.equals("MD5")) {// md5加密
    			 pwd = DigestUtils.md5Hex(mima);
    		} else {
    			 pwd = Encipher.EncodePasswd(mima);
    		}
    		vo.setPwd(pwd);
            
    		/** 默认向 用户系统权限（不针对项目的权限）设置当前新增用户的权限？**/
			Long user_uid = BaseDAO.insert(conn, vo);
			String sql  = "INSERT INTO sys_user_auth (USER_UID,AUTHORITY_UID) VALUES ("+user_uid+",5)";
			DBUtil.exec(conn, sql);
            resultVO = vo.getRowJson();
            
            //带注册码的用户更新消息(bu_user_message)里的user_uid
            updateMessage(obj,String.valueOf(user_uid));
            //带注册码的用户更新sys_invite的inviter_user
            //updateInvite(obj,String.valueOf(user_uid));
            //更新附件信息
            if(null!=user_uid&&!"".equals(user_uid)){
            	 FileUploadUtilService. updateBytragetUid (targetUid,String.valueOf(user_uid));                 
            }
            
            
            
        } catch (DaoException e) {
            logger.error("用户信息{}", e.getMessage());
            SystemException.handleMessageException("用户信息新增失败,请联系相关人员处理");
        } finally {
        	DBUtil.closeConnetion(conn);
        }
        return resultVO;

    }
    
    private void updateInvite(JSONObject obj, String user_uid) {
		// TODO Auto-generated method stub
    	Connection conn = null;
    	try {
    		 conn = DBUtil.getConnection();
        	 String zcCode =  obj.getString("ZCCODE");
             if(null!=zcCode&&!zcCode.equals("")){
             	  String ssql="select *from sys_invite si  where INVITER_USER="+zcCode; 
                     BaseResultSet bs = DBUtil.query(conn, ssql, null);  
                     ResultSet rs = bs.getResultSet();
                     while(rs.next()){
                     	String INVITE_UID = rs.getString("INVITE_UID");
                        SysInviteVO sysInviteVo = sysInviteDao.get(SysInviteVO.class, INVITE_UID);
                        sysInviteVo.setInviter_user((String.valueOf(user_uid))); 
                     	//buUserMessageService.updateMessageVO(buUserMessageVo);
                     	sysInviteService.updateVo(sysInviteVo);
                     }
             }
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			DBUtil.closeConnetion(conn);
		} 
	}

    /**
     * 根据注册码zccode
     * 获取bu_user_message表里message_para4为注册码,USER_UID为0的记录
     * 更新此记录的user_uid
     * @param obj
     * @param user_uid
     */
	public void updateMessage(JSONObject obj,String user_uid){
    	Connection conn = null;
    	try {
    		 conn = DBUtil.getConnection();
        	 String zcCode =  obj.getString("ZCCODE");
             if(null!=zcCode&&!zcCode.equals("")){
             	  String ssql="select *from bu_user_message bum  where MESSAGE_PARA4='"+zcCode+"' and USER_UID=0"; 
                     BaseResultSet bs = DBUtil.query(conn, ssql, null);  
                     ResultSet rs = bs.getResultSet();
                     while(rs.next()){
                     	String USER_MESSAGE_UID = rs.getString("USER_MESSAGE_UID");
                     	BuUserMessageVO buUserMessageVo = buUserMessageDao.get(BuUserMessageVO.class, USER_MESSAGE_UID);
                     	buUserMessageVo.setUser_uid(String.valueOf(user_uid)); 
                     	buUserMessageVo.setMessage_para4("");
                     	buUserMessageService.updateMessageVO(buUserMessageVo); 
                     }
             }
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			DBUtil.closeConnetion(conn);
		} 
    }

    public String update(String json) throws Exception {
        User user = ActionContext.getCurrentUserInThread();
        String resultVO = null;
        SysUserVO vo = new SysUserVO();
        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);
            String user_uid = obj.getString("USER_UID");
            if("YES".equalsIgnoreCase(obj.getString("XGMIMA"))){//说明是修改密码 
            	String mima= vo.getPwd();
                String pwd = "";
        		if (Constants.ENCODER_TYPE.equals("MD5")) {// md5加密
        			 pwd = DigestUtils.md5Hex(mima);
        		} else {
        			 pwd = Encipher.EncodePasswd(mima);
        		}
        		
        		vo.setPwd(pwd);
        		 // 修改
                sysUserDao.update(vo);
                UserManager um = UserManager.getInstance();
                um.reBuildMemory();
            }else{
            	 // 修改
            	String targetUid = obj.getString("target_uid");
                sysUserDao.update(vo);
            	  //更新附件信息
                if(null!=user_uid&&!"".equals(user_uid)){
                	 FileUploadUtilService. updateBytragetUid (targetUid,String.valueOf(user_uid));                 
                }

            }
            
            resultVO = vo.getRowJson();
          
        } catch (DaoException e) {
            logger.error("用户信息{}", e.getMessage());
            SystemException.handleMessageException("用户信息修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		SysUserVO vo = new SysUserVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			sysUserDao.delete(SysUserVO.class, vo.getUser_uid());

			resultVo = vo.getRowJson();

		} catch (DaoException e) {
            logger.error("用户信息{}", e.getMessage());
            SystemException.handleMessageException("用户信息删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("sysUserDaoImpl")
	public void setSysUserDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}

	public boolean checkEmail(String param,String userId) {
		// TODO Auto-generated method stub
		return this.sysUserDao.checkEmail(param,userId);
	}

	public boolean checkName(String name,String userId) {
		return this.sysUserDao.checkName(name,userId);
	}

	public boolean checkPwd(String pwd, String userId) throws Exception {
		// TODO Auto-generated method stub
		return this.sysUserDao.checkPwd(pwd,userId);
	}

	public boolean queryAuthority(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return this.sysUserDao.queryAuthority(map);
	}

	public boolean checkDirAuthority(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return this.sysUserDao.checkDirAuthority(map);
	}
	
	public SysUserVO getById(String user_uid){
		return sysUserDao.get(SysUserVO.class,user_uid);
	}

	public List<Map<String, String>> getSysUserByLoginName(String loginName) {
		// TODO Auto-generated method stub
		return sysUserDao.getSysUserByLoginName(loginName);
	} 
	
	public requestJson resetPassword(String msg) {
		// TODO Auto-generated method stub
		requestJson j = new requestJson();
		JSONObject json =JSONObject.fromObject(msg);
		JSONArray array = json.getJSONObject("response").getJSONArray("data"); 
		JSONObject obj = array.getJSONObject(0);
		String user_uid = (String) obj.get("USER_UID");
		String timestamp = obj.getString("timestamp");
		String pwd = (String)obj.get("PWD");
		Long i_timestamp = Long.valueOf(timestamp);
		Date date = new Date();
		long now_date = date.getTime();
		if(i_timestamp>now_date){
			SysUserVO sysUserVO = sysUserDao.get(SysUserVO.class,user_uid);
			sysUserVO.setPwd(DigestUtils.md5Hex(pwd));
			sysUserDao.update(sysUserVO);
			j.setSuccess(true);
			j.setMsg("密码重置成功");
		}else{
			j.setSuccess(false);
			j.setMsg("重置请求过期了");
		} 
		return j;
	}
    
}
