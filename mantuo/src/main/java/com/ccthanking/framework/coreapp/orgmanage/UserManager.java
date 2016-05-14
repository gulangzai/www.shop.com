package com.ccthanking.framework.coreapp.orgmanage;

import java.sql.Connection;
import java.util.Map;

import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.Role;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.UserVO;
import com.ccthanking.framework.common.cache.Cache;
import com.ccthanking.framework.common.cache.CacheManager;
import com.ccthanking.framework.util.Pub;
import com.google.common.collect.Maps;

public class UserManager implements Cache {

	// private Hashtable usertable;
	private Map<String, User> usertable;

	static private UserManager instance;
	static private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger("UserManager");

	private UserManager() throws Exception {
		init();
	}

	public void reBuildMemory() throws Exception {
		if (usertable != null) {
			usertable.clear();
			usertable = null;
		}
		init();
	}

	public void synchronize(String userName, int action) throws Exception {

		User user = this.loadUser(userName, true);
		User old = this.getUserByLoginName(userName);
		if (action == CacheManager.ADD) {
			this.usertable.put(user.getAccount(), user);
		} else if (action == CacheManager.UPDATE) {
			old.setCertCode(user.getCertCode());
			old.setDepartment(user.getDepartment());
			old.setFlag(user.getFlag());
			old.setIdCard(user.getIdCard());
			old.setIP(user.getIP());
			old.setLevelName(user.getLevelName());
			old.setParent(user.getParent());
			old.setPassWord(user.getPassWord());
			old.setName(user.getName());
			old.setSex(user.getSex());
			old.setPersonKind(user.getPersonKind());
			old.setScretLevel(user.getScretLevel());
			old.setUserSN(user.getUserSN());
			old.setIdCard(user.getIdCard());
			old.setCertCode(user.getCertCode());
			old.setSmtp(user.getSmtp());
			old.setMailFrom(user.getMailFrom());
			old.setMailName(user.getMailName());
			old.setMailPsw(user.getMailPsw());
			old.setUserTemplate(user.getUserTemplate());
			old.setJwqdm(user.getJwqdm());
			old.setZrqdm(user.getZrqdm());
			old.setYhbh(user.getYhbh());
		} else if (action == CacheManager.DELETE) {
			usertable.remove(user.getAccount());
		}
		if (action == CacheManager.MAP_CHANGEED || action == CacheManager.DELETE) {
			Role[] roles = old.getRoles();
			if (roles != null)
				for (int i = 0; i < roles.length; i++) {
					roles[i].setUsers(null);
				}
			old.setRoles(null);
			old.setAllowedMenus(null);
			roles = user.getRoles();
			if (roles != null) {
				for (int i = 0; i < roles.length; i++) {
					roles[i].setUsers(null);
				}
			}
		}

	}

	private void init() throws Exception {

		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT DISTINCT(u.USER_UID) idcard,pwd,USER_NAME,LOGON_NAME,p.PROJECT_USER_UID FROM sys_user u ");
			sql.append(" LEFT JOIN bu_project_user p ");
			sql.append(" ON u.USER_UID = p.USER_UID ");
			sql.append(" where ENABLED='1'");
			//String querySql = "select LOGON_NAME,PWD,USER_NAME,EMAIL,MOBILE,COMPANY_UID,USER_UID"
			//		 +" from sys_user where ENABLED='1'";
			String[][] list = DBUtil.query(conn, sql.toString());
			if (list != null) {

				// usertable = new Hashtable(list.length);
				usertable = Maps.newConcurrentMap();

				for (int i = 0; i < list.length; i++) {
					User user = new UserVO();
					//user.setAccount(list[i][0]);
					// user.setPassWord(Encipher.DecodePasswd(list[i][1]));
					//user.setPassWord(list[i][1]);
					//user.setName(list[i][2]);
					//user.setSex(list[i][3]);
					//user.setDepartment(list[i][4]);
					///user.setParent(list[i][5]);
					//user.setPersonKind(list[i][6]);
					//user.setUserSN(list[i][7]);
					///user.setLevelName(list[i][8]);
					//user.setScretLevel(list[i][9]);
					//user.setFlag(list[i][10]);
					//user.setIdCard(list[i][6]);
					//user.setCertCode(list[i][12]);
					//user.setSmtp(list[i][13]);
					////user.setMailFrom(list[i][14]);
					//user.setMailName(list[i][15]);
					//user.setMailPsw(list[i][16]);
					//user.setYhbh(user.getIdCard());// --新增用户编号,对应中心库用户表的用户编号字段
					//if (Pub.empty(list[i][17])) {
					//	user.setUserTemplate("blueTemplate");
					//} else {
						//user.setUserTemplate(list[i][17]);
					//}
						
					user.setIdCard(list[i][0]);			
					user.setAccount(list[i][3]);
					user.setPassWord(list[i][1]);
					user.setName(list[i][2]);
					user.setParent(list[i][4]);	
					
					usertable.put(list[i][0], user);
				}
			}
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace(System.out);
		} finally {
			DBUtil.closeConnetion(conn);
		}
	}

	public User getUserFromDB(String account, boolean flag) throws Exception {
		return loadUser(account, flag);
	}
	
	public void removeUser(User user)throws Exception{
		if(user!=null){
			if(usertable.containsKey(user.getAccount())){
				usertable.remove(user.getAccount());
			    log.info("剔除【"+user.getAccount()+"】用户");
			}
		}
			
		
	}

	private User loadUser(String account, boolean flag) throws Exception {

		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			// 登录名不区分大小写
/*			String querySql = "select USERS_UID idcard,pwd,user_name,'' sex,user_type department,"
					+ " '' user_sn,admin_y PERSON_KIND," + " '1' flag, logon_name" + " from users "
					// +
					// "usertemplate,jwq,zrq from fs_org_person where account='"
					// + account + "'";
					+ "  where logon_name='" + account + "' ";
			if (flag) {
				querySql += " and USE_Y='Y'";
			}*/
			
			//查询用户基本信息
			StringBuffer querySql = new StringBuffer();			
			querySql.append(" SELECT DISTINCT(u.USER_UID) idcard,pwd,USER_NAME,LOGON_NAME,EMail,p.PROJECT_USER_UID FROM sys_user u ");
			querySql.append(" LEFT JOIN bu_project_user p ");
			querySql.append(" ON u.USER_UID = p.USER_UID ");
			querySql.append( "  where LOGON_NAME='" + account + "' or EMAIL='"+account+"' ");
			if(flag){
				querySql.append(" and ENABLED = 1");
			}
			String[][] list = DBUtil.query(conn, querySql.toString());
			if (list != null) {
				for (int i = 0; i < list.length;i++) {
					User user = new UserVO();
					user.setIdCard(list[i][0]);			
					// user.setPassWord(Encipher.DecodePasswd(list[i][1]));
					user.setAccount(list[i][3]);
					// user.setPassWord(Encipher.DecodePasswd(list[i][1]));
					user.setPassWord(list[i][1]);
					user.setName(list[i][2]);
					user.setParent(list[i][4]);
					user.setUserTemplate("blueTemplate");
					return user;
				}
			}
		} catch (Exception e) {
			log.error("", e);
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return null;
	}

	public User getUserByCertCode(String certCode) throws Exception {
		Connection conn = null;
		try {
			if (Pub.empty(certCode))
				return null;
			conn = DBUtil.getConnection();
			String querySql = "select account,password,name,sex,department,"
					+ "parent,person_kind,user_sn,level_name,secret_level,"
					+ "flag,idcard,certcode,smtp,mailfrom,mailname,mailpsw,"
					// +
					// "usertemplate,jwq,zrq from fs_org_person where certcode='"
					// + certCode + "'";
					+ "usertemplate,jwq,zrq from fs_org_person where certcode='" + certCode + "'";
			String[][] list = DBUtil.query(conn, querySql);
			if (list != null) {
				for (int i = 0; i < list.length; i++) {
					User user = new UserVO();
					user.setAccount(list[i][0]);
					// user.setPassWord(Encipher.DecodePasswd(list[i][1]));
					user.setPassWord(list[i][1]);
					user.setName(list[i][2]);
					user.setSex(list[i][3]);
					user.setDepartment(list[i][4]);
					user.setParent(list[i][5]);
					user.setPersonKind(list[i][6]);
					user.setUserSN(list[i][7]);
					user.setLevelName(list[i][8]);
					user.setScretLevel(list[i][9]);
					user.setFlag(list[i][10]);
					user.setIdCard(list[i][11]);
					user.setCertCode(list[i][12]);
					user.setSmtp(list[i][13]);
					user.setMailFrom(list[i][14]);
					user.setMailName(list[i][15]);
					user.setMailPsw(list[i][16]);// user.setMailPsw(Encipher.DecodePasswd(list[i][16]));
					user.setYhbh(user.getIdCard());
					if (Pub.empty(list[i][17])) {
						user.setUserTemplate("blueTemplate");
					} else {
						user.setUserTemplate(list[i][17]);
					}
					return user;
				}
			}
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace(System.out);
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return null;
	}

	public User getUserByLoginName(String loginName) throws Exception {
		User user = getUserByLoginNameFromNc(loginName);
		if (user == null) {
			user = getUserFromDB(loginName, true);// 从数据库中读取User信息
			if (user != null) {
				usertable.put(user.getAccount(), user);
			}	
		}
		return user;
	}

	public User getUserByLoginNameFromNc(String loginName) throws Exception {
		User user = (User) usertable.get(loginName);// -从内存中读取User信息
		return user;
	}

	public String getUserLastLoginTime(String loginName) throws Exception {
		String lastLoginTime = "";
		String[][] s = DBUtil
				.query("select * from (select to_char(logintime,'yyyy\"年\"fmmm\"月\"dd\"日\"') from fs_log_userlogin t where t.userid = '"
						+ loginName + "' order by t.logintime desc ) where rownum<=1");
		if (s != null && s.length > 0) {
			lastLoginTime = s[0][0];
		}
		return lastLoginTime;
	}

	// public Hashtable getUserTable() {
	public Map<String, User> getUserTable() {
		return this.usertable;
	}

	static synchronized public UserManager getInstance() throws Exception {
		if (instance == null) {
			instance = new UserManager();
			CacheManager.register(CacheManager.CACHE_USER, instance);
		}
		return instance;
	}
	
	/**根据id查询用户信息*/
	public User getUserMessage(String userId) {
		  Connection conn = null;
		  try {
			  conn = DBUtil.getConnection();
			  /** 查询当前 用户的 信息**/
			  StringBuffer sql = new StringBuffer();
			  sql.append("SELECT  USER_UID,PWD,LOGON_NAME FROM SYS_USER ");
			  sql.append("WHERE USER_UID = '"+userId+"'");
			  sql.append(" and ENABLED = 1 ");
			  String[][] list = DBUtil.query(conn,sql.toString());
			  if (list != null) {
					for (int i = 0; i < list.length;i++) {
						User user = new UserVO();
						user.setIdCard(list[i][0]);			
						user.setPassWord(list[i][1]);
						user.setAccount(list[i][2]);			
						user.setUserTemplate("blueTemplate");
						return user;
					}
				}
		 }catch (Exception e) {
			e.printStackTrace();
		 }finally{
			 if(conn !=null){
				 DBUtil.closeConnetion(conn);
			 }
		 }
		  
		return null;
	}
}