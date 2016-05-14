package com.ccthanking.framework.service.impl;

import java.sql.Connection;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ccthanking.common.YwlxManager;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.UserVO;
import com.ccthanking.framework.coreapp.orgmanage.UserManager;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.service.UserService;
import com.ccthanking.framework.util.Encipher;
import com.ccthanking.framework.util.QueryConditionList;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;

@Service
public class UserServiceImpl extends Base1ServiceImpl<UserVO, String> implements UserService {

	private static String MODULE = UserServiceImpl.class.getName();

	private static Logger logger = LoggerFactory.getLogger(MODULE);

	public String queryUser(String json) throws Exception {
		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {
			QueryConditionList list = RequestUtil.getConditionList(json);
			String orderFilter = RequestUtil.getOrderFilter(json);
			String condition = list == null ? "" : list.getConditionWhere();
			condition += " AND FLAG = '1' " + orderFilter;

			PageManager page = RequestUtil.getPageManager(json);
			page.setFilter(condition);

			String sql = "SELECT ACCOUNT, PASSWORD, NAME, SEX, DEPARTMENT, PARENT, PERSON_KIND, "
					+ "USER_SN, LEVEL_NAME, SECRET_LEVEL, FLAG, IDCARD, CERTCODE, SMTP, MAILFROM, "
					+ "MAILNAME, MAILPSW, USERTEMPLATE, JWQ, ZRQ, SJHM,SORT FROM FS_ORG_PERSON";
			BaseResultSet bs = DBUtil.query(conn, sql, page);
			bs.setFieldDic("SEX", "XB");
			bs.setFieldDic("PERSON_KIND", "YHLB");
			bs.setFieldOrgDept("DEPARTMENT");

			domresult = bs.getJson();

		} catch (Exception e) {
			e.printStackTrace(System.out);

		} finally {
			DBUtil.closeConnetion(conn);
		}
		return domresult;
	}

	public String[][] queryRole(String project_uid) {
		/*String sql = "SELECT R.ROLE_ID RID, RP.ROLE_ID RPID, R.NAME RNAME,R.ROLETYPE,R.PARENTROLEID "
				+ "FROM (SELECT ROLE_ID,NAME,SFYX,ROLETYPE,PARENTROLEID FROM FS_ORG_ROLE WHERE SFYX='1') R LEFT JOIN FS_ORG_ROLE_PSN_MAP RP "
				+ "ON R.ROLE_ID = RP.ROLE_ID AND RP.PERSON_ACCOUNT='" + account + "' AND R.SFYX = '1' ";*/
		StringBuffer sql = new StringBuffer();
/*		sql.append(" SELECT r.* FROM bu_project_role_user t ");
		sql.append(" LEFT JOIN bu_project_role r ");
		sql.append(" ON t.PROJECT_ROLE_UID = r.PROJECT_ROLE_UID ");
		sql.append(" WHERE t.PROJECT_USER_UID = '1' ");*/
		String[][] roleNameArray = DBUtil.query(sql.toString());
		return roleNameArray;
	}

	public String[][] queryPersonRole(String id) {
		String sql = "select t.account,d.person_account,t.name,(select e.dept_name from FS_ORG_DEPT e where e.row_id = t.department) as dept_name "
				+ " from v_org_person t,fs_org_role_psn_map d "
				+ " where t.account = d.person_account(+) and d.role_id(+) = '"
				+ id
				+ "' order by t.department ,to_number(t.sort) asc";
		String[][] personArray = DBUtil.query(sql);
		return personArray;
	}

	public void awardRoleToUser(String account, String[] roleNameAndId, User user) throws Exception {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			String deleteRoleByAccountSql = "DELETE FROM bu_project_role_user WHERE PROJECT_USER_UID='" + account + "'";
			boolean bDel = DBUtil.exec(conn, deleteRoleByAccountSql);
			if (bDel) {
				for (int i = 0; i < roleNameAndId.length; i++) {
					String roleId = roleNameAndId[i].split("=")[0];
					String addRoleToUserSql = "INSERT INTO bu_project_role_user (PROJECT_ROLE_UID,PROJECT_USER_UID) VALUES ('"+roleId+"','"+account+"') ";
					DBUtil.execSql(conn, addRoleToUserSql);
				}
			}
			conn.commit();
			//LogManager.writeUserLog(user.getAccount(), YwlxManager.SYSTEM_USER, Globals.OPERATION_TYPE_INSERT,
			//		LogManager.RESULT_SUCCESS, user.getName() + "为用户分配角色成功",
			//		user, "", "");
		} catch (Exception e) {
			DBUtil.rollbackConnetion(conn);
			e.printStackTrace();
			//LogManager.writeUserLog(user.getAccount(), YwlxManager.SYSTEM_USER, Globals.OPERATION_TYPE_INSERT,
			//		LogManager.RESULT_SUCCESS, user.getName() + "为用户分配角色失败",
			//		user, "", "");
		} finally {
			DBUtil.closeConnetion(conn);
		}
	}

	public String resetPw(String account) throws Exception {
		Connection conn = null;
		String domresult = "";
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sqlQuery = "select t.password, fr from v_org_person t where upper(t.account)='"
					+ account.toUpperCase() + "'";

			String[][] res = DBUtil.query(conn, sqlQuery);
			if (res != null) {

				String newPass = Encipher.EncodePasswd(account.toUpperCase() + "Kcit");
				String newPass_md5 = account.toUpperCase() + "Kcit";
				if (Constants.ENCODER_TYPE.equals("MD5")) {// md5加密
					newPass_md5 = DigestUtils.md5Hex(newPass_md5);
				} else {
					newPass_md5 = Encipher.EncodePasswd(newPass_md5);
				}

				String sql = "";
				if (res[0][1].equals("1")) {
					// 从表fs_org_person来
					sql = "update fs_org_person t set t.password='" + newPass_md5 + "' where  upper(t.account)='"
							+ account.toUpperCase() + "'";
				} else if (res[0][1].equals("2")) {
					sql = "update sg_enterprise_library t set t.pwd='" + newPass_md5 + "', MIMA='" + newPass
							+ "' where  upper(t.denglu_code)='" + account.toUpperCase() + "' and status='1'";
				}

				DBUtil.exec(conn, sql);
				conn.commit();
			}

		} catch (Exception e) {
			DaoException.handleException("***重置密码出错***");
			DBUtil.rollbackConnetion(conn);
			throw e;
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return domresult;
	}

	public User getUserByUsernameAndPassword(String username, String password) throws Exception {

		//logger.debug("curr sql =[ {} ]", SqlStrHelper.getSql());

		User user = null;
		user = (User) UserManager.getInstance().getUserByLoginName(username);

		if (user == null) {
			logger.info(" {} 用户不存在!", username);
			return null;

		}
		String pass1 = user.getPassWord();
		if (pass1 == null)
			pass1 = "";
		if (password == null)
			password = "";
		//password = password.toUpperCase();// 密码不区分大小写

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
	
	public User getUserByName(String username) throws Exception {
		
		User user = null;
		user = (User) UserManager.getInstance().getUserByLoginName(username);
		
		if (user == null) {
			logger.info(" {} 用户不存在!", username);
			return null;
			
		}
		
		return user;
	}

	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
