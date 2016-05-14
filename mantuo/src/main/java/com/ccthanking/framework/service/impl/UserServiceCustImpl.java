package com.ccthanking.framework.service.impl;


import java.sql.Connection;
import java.util.HashMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import com.alibaba.fastjson.JSONObject;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.UserVO;
import com.ccthanking.framework.coreapp.orgmanage.UserManager;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.framework.service.UserServiceCust;
import com.ccthanking.framework.util.Encipher;
import com.ccthanking.framework.util.MobileUtil;
import com.copj.modules.utils.exception.DaoException;

@Service
public class UserServiceCustImpl extends Base1ServiceImpl<UserVO, String> implements UserServiceCust {
	private static String MODULE = UserServiceCustImpl.class.getName();

	private static Logger logger = LoggerFactory.getLogger(MODULE);
	// @Override
	public void awardRoleToPerson(String roleid, String rolename, String[] personNameAndId, User user) throws Exception {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			String deleteRoleByAccountSql = "DELETE FROM bu_project_role_user WHERE PROJECT_ROLE_UID='" + roleid + "'";
			boolean bDel = DBUtil.exec(conn, deleteRoleByAccountSql);
			if (bDel) {
				for (int i = 0; i < personNameAndId.length; i++) {
					String account = personNameAndId[i].split("=")[0];
					// String roleName = personNameAndId[i].split("=")[1];
					String addRoleToUserSql = "INSERT INTO bu_project_role_user (PROJECT_ROLE_UID,PROJECT_USER_UID) VALUES ('"+roleid+"','"+account+"') ";
					DBUtil.execSql(conn, addRoleToUserSql);
				}
			}
			conn.commit();
			
			//LogManager.writeUserLog(user.getAccount(), YwlxManager.SYSTEM_USER, Globals.OPERATION_TYPE_INSERT,
			//		LogManager.RESULT_SUCCESS, user.getDepartment() + " " + user.getName() + "将角色授予用户成功",
			//		user, "", "");
		} catch (Exception e) {
			DaoException.handleException("***把角色授予用户失败***");
			DBUtil.rollbackConnetion(conn);
			throw e;
		} finally {
			DBUtil.closeConnetion(conn);
		}
	}
	
	public User getUserByUsernameAndPasswordm(String username, String password) throws Exception {
		
		//logger.debug("curr sql =[ {} ]", SqlStrHelper.getSql());
		
		User user = null;
		user = (User) UserManager.getInstance().getUserFromDB(username, true);// 从数据库中读取User信息;
		
		if (user == null) {
			logger.info(" {} 用户不存在!", username);
			return null;
			
		}
		String pass1 = user.getPassWord();
		if (pass1 == null)
			pass1 = "";
		if (password == null)
			password = "";
		password = password.toUpperCase();// 密码不区分大小写
		
		if (Constants.ENCODER_TYPE.equals("MD5")) {// md5加密
			password = DigestUtils.md5Hex(password);
		} else {
			password = Encipher.EncodePasswd(password);
		}
		
		if (!pass1.equals(password)) {
			logger.info(" {} 密码不正确!", username);
			return null;
			
		}
		
		return user;
	}
	
	public User getUserByUsernameAndPassword(String username, String password) throws Exception{
		User user = null;
		user = (User) UserManager.getInstance().getUserFromDB(username, true);// 从数据库中读取User信息;
		if (user == null) {
			logger.info(" {} 用户不存在!", username);
			return null;
		}
		
		String pass1 = user.getPassWord();
		if (pass1 == null)
			pass1 = "";
		if (password == null)
			password = "";
		password = password.toUpperCase();
		
		/*if (Constants.ENCODER_TYPE.equals("MD5")) {// md5加密
			password = DigestUtils.md5Hex(password);
		} else {
			password = Encipher.EncodePasswd(password);
		}
		*/
		/**不区分大小写*/
		if (!pass1.equalsIgnoreCase(password)) {
			logger.info(" {} 密码不正确!", username);
			return null;
			
		}
		
		return user;
		
	}
	
	public String updatePassWord(String userId,String oldPwd,String newPwd) throws Exception{
		
		String message = "";
		User user = (User)UserManager.getInstance().getUserMessage(userId);// 从数据库中读取User信息;
		
		if(user == null){
			message ="bad";
		}
	    //获取 当前用户的数据库密码
		String dbPwd = user.getPassWord();
		if(dbPwd == null){
			dbPwd = "";
		}else{
			if(!dbPwd.equalsIgnoreCase(oldPwd)){
				message = "no";
			}else{//说明输入的密码和原始密码相同 即可 更该改当前的密码
			    boolean bs = updatePassWordById(userId,newPwd);
			    message = String.valueOf(bs);
			}
		}
		
		return message;
		
	}
	
	/** 修改密码 by id**/
	private boolean updatePassWordById(String userId, String newPwd) {
		Connection conn = null;
		boolean flag = false;
		try {
			conn = DBUtil.getConnection();
			StringBuffer sql = new StringBuffer();
			  sql.append(" UPDATE SYS_USER ");
			  sql.append("SET PWD = '"+newPwd+"' ");
			  sql.append("WHERE USER_UID="+userId+" and ENABLED = 1 ");
			  flag= DBUtil.exec(conn, sql.toString());
			  // UPDATE SYS_USER SET PWD = '202cb962ac59075b964b07152d234b70'WHERE USER_UID=3 and ENABLED = 1 
			 
		} catch (Exception e) {
		    e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return flag;
	}
	
	public JSONObject getUserXinXi(String userId) {
		Connection conn = null;
		JSONObject obj = new JSONObject();
		boolean flag =false;
		try {
			conn = DBUtil.getConnection();
			StringBuffer sql = new StringBuffer();
			  sql.append("SELECT s.USER_NAME as name,s.MOBILE as phone,s.EMAIL as email,");
			  sql.append(" (SELECT a.FILE_PATH FROM bu_file f LEFT JOIN bu_attachment a ON f.ATTACHMENT_UID = a.ATTACHMENT_UID ");
			  sql.append(" 	WHERE f.ENABLED = '1' AND f.FILE_TYPE = '10008'  ");
			  sql.append(" 	AND f.TARGET_UID  =  s.USER_UID  ORDER BY f.CREATE_DATE desc ");
			  sql.append("  LIMIT 1 ");
			  sql.append(" ) as imageUrl ");
			  sql.append("  from SYS_USER  s");
			  sql.append("  WHERE s.USER_UID='"+userId+"' and s.ENABLED = '1' ");
			  System.out.println(sql);
			  String[][] bs = DBUtil.querySql(conn, sql.toString());
	            if(bs != null && bs.length!=0){
	            	flag = true;
				    MobileUtil.getUserXinXi(obj,flag);
	            	obj.put("name", bs[0][0]);
	            	obj.put("phone", bs[0][1]);
	            	obj.put("email", bs[0][2]);
	            	obj.put("imageUrl", bs[0][3]);
	            
	            }
		} catch (Exception e) {
		    e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
		
		return obj;
	
}
	
	public String[][] queryRole(HashMap<String, String> map) {
		/*String sql = "SELECT R.ROLE_ID RID, RP.ROLE_ID RPID, R.NAME RNAME,R.ROLETYPE,R.PARENTROLEID "
				+ "FROM (SELECT ROLE_ID,NAME,SFYX,ROLETYPE,PARENTROLEID FROM FS_ORG_ROLE WHERE SFYX='1') R LEFT JOIN FS_ORG_ROLE_PSN_MAP RP "
				+ "ON R.ROLE_ID = RP.ROLE_ID AND RP.PERSON_ACCOUNT='" + account + "' AND R.SFYX = '1' ";*/
		StringBuffer sql = new StringBuffer();
/*		sql.append(" SELECT r.* FROM bu_project_role_user t ");
		sql.append(" LEFT JOIN bu_project_role r ");
		sql.append(" ON t.PROJECT_ROLE_UID = r.PROJECT_ROLE_UID ");
		sql.append(" WHERE t.PROJECT_USER_UID = '1' ");*/
		
		sql.append(" SELECT  ");
		sql.append(" r.PROJECT_ROLE_UID, ");
		sql.append(" t.PROJECT_ROLE_UID AS PPROJECT_ROLE_UID, ");
		sql.append(" r.ROLE_NAME  ");
		sql.append(" FROM bu_project_role  r  ");
		sql.append(" LEFT JOIN (SELECT PROJECT_ROLE_UID,PROJECT_USER_UID ");
		sql.append("  FROM bu_project_role_user WHERE PROJECT_USER_UID = '"+map.get("ryid")+"') t  ");
		sql.append(" ON t.PROJECT_ROLE_UID = r.PROJECT_ROLE_UID  ");
		sql.append(" WHERE r.PROJECT_UID = '"+map.get("project_uid")+"' ");

		String[][] roleNameArray = DBUtil.query(sql.toString());
		return roleNameArray;
	}
	
	public String[][] queryPersonRole(HashMap<String, String> map) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  ");
		sql.append(" r.PROJECT_USER_UID, ");
		sql.append(" t.PROJECT_USER_UID AS PPROJECT_USER_UID, ");
		sql.append(" r.USER_NAME ");
		sql.append(" FROM (SELECT u.USER_NAME,p.PROJECT_USER_UID,p.PROJECT_UID  ");
		sql.append(" FROM bu_project_user p LEFT JOIN sys_user u ON p.USER_UID = u.USER_UID ) r  ");
		sql.append(" LEFT JOIN (SELECT PROJECT_ROLE_UID,PROJECT_USER_UID ");
		sql.append("  FROM bu_project_role_user WHERE PROJECT_ROLE_UID = '"+map.get("role_uid")+"') t  ");
		sql.append(" ON t.PROJECT_USER_UID = r.PROJECT_USER_UID  ");
		sql.append(" WHERE r.PROJECT_UID = '"+map.get("project_uid")+"' ");
		String[][] personArray = DBUtil.query(sql.toString());
		return personArray;
	}
	
}

