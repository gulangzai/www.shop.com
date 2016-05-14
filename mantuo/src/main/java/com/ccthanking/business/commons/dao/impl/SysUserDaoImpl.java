/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.commons.SysUserDao.java
 * 创建日期： 2015-10-26 下午 10:31:55
 * 功能：   用户信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-26 下午 10:31:55  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.commons.dao.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import com.ccthanking.business.commons.dao.SysUserDao;
import com.ccthanking.business.commons.vo.SysUserVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.Encipher;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> SysUserDao.java </p>
 * <p> 功能：用户信息 </p>
 *
 * <p><a href="SysUserDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-26
 * 
 */

@Component
public class SysUserDaoImpl  extends BsBaseDaoTJdbc implements SysUserDao {

    public String queryCondition(String userId, SysUserVO vo, Map map){
    	User user = ActionContext.getCurrentUserInThread();
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            String sql = "SELECT * FROM  SYS_USER s WHERE s.USER_UID ="+userId;
            BaseResultSet bs = DBUtil.query(conn, sql, null);
            domresult = bs.getJson();
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询用户信息出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }

	public boolean checkEmail(String param,String userId) {
        Connection conn = DBUtil.getConnection();
        boolean flag = true;
        String sql = "";
        try {
        	if("uidIsNull".equalsIgnoreCase(userId)){//说明是 insert
        		sql = "SELECT * FROM sys_user WHERE EMAIL = '"+param+"'";
        	}else{
        		sql = "SELECT count(*) FROM SYS_USER s WHERE s.EMAIL = '"+param+"' and s.USER_UID<>"+userId;
        	}
        	
			String[][] res = DBUtil.query(conn, sql);
			if (res != null) {
				if (!"0".equals(res[0][0])) {
					flag = false;
				}
			}
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return flag;
	}

	public boolean checkName(String name,String userId) {
		   Connection conn = DBUtil.getConnection();
	        boolean flag = true;
	        String sql ="";
	        try {
	        	if("uidIsNull".equalsIgnoreCase(userId)){//说明是 insert
	        		sql = "SELECT * FROM sys_user WHERE LOGON_NAME = '"+name+"'";
	        	}else{
	        		sql = "SELECT * FROM SYS_USER s WHERE s.LOGON_NAME = '"+name+"' and s.USER_UID<>"+userId;
	        	}
	        	
				String[][] res = DBUtil.query(conn, sql);
				if (res != null) {
					if (!"0".equals(res[0][0])) {
						flag = false;
					}
				}
	            
	        } catch (Exception e) {
	            DaoException.handleMessageException("*********查询登录名出错!*********");
	        } finally {
	            DBUtil.closeConnetion(conn);
	        } 
	        return flag;
		}
	
	public List<Map<String,String>> getSysUserByLoginName(String login_name) {
		   Connection conn = DBUtil.getConnection();
		   List<Map<String,String>> sysUserVOs = null;
	        boolean flag = true;
	        String sql ="";
	        try { 
	        	sql = "SELECT * FROM sys_user WHERE LOGON_NAME = '"+login_name+"'"; 
	            sysUserVOs = DBUtil.queryReturnList(conn, sql);  
	        } catch (Exception e) {
	            DaoException.handleMessageException("*********查询登录名出错!*********");
	        } finally {
	            DBUtil.closeConnetion(conn);
	        } 
	        return sysUserVOs;
 }

	public boolean checkPwd(String pwd, String userId) {
		    Connection conn = DBUtil.getConnection();
			User user = ActionContext.getCurrentUserInThread();
	        boolean flag = true;
	        String sql ="";
	        String oldPwd = "";
	        String dbPwd = "";
	        try {
	        	sql = "select PWD from sys_user where USER_UID = "+userId;
	        	String[][] res = DBUtil.query(conn,sql);
	        	if(res !=null){
	        		dbPwd = res[0][0];
	        	}
	        	if (Constants.ENCODER_TYPE.equals("MD5")) {// md5加密
	        		oldPwd = DigestUtils.md5Hex(pwd);
		   		 } else {
		   			oldPwd = Encipher.EncodePasswd(pwd);
		   		}
	        	/**说明输入的密码和 数据库的原始密码不正确**/
	        	if(!dbPwd.equalsIgnoreCase(oldPwd)){
	        		flag = false;
	        	} 
	        } catch (Exception e) {
	            DaoException.handleMessageException("*********原始密码业务判断出错!*********");
	        } finally {
	            DBUtil.closeConnetion(conn);
	        }
	        
	        return flag;
		}

	/**
	 * 检查权限
	 */
	public boolean queryAuthority(HashMap<String, String> map) {
	    Connection conn = DBUtil.getConnection();
        boolean flag = false;
        try {
        	StringBuffer sql = new StringBuffer();
        	sql.append(" SELECT a.* FROM (SELECT * FROM bu_project_user u WHERE  ");
        	sql.append("  u.PROJECT_UID = '"+map.get("projectId")+"' AND u.PROJECT_USER_UID ='"+map.get("projectUserId")+"' ) AS u ");
        	sql.append(" LEFT JOIN bu_project_role_user ru ");
        	sql.append(" ON u.PROJECT_USER_UID = ru.PROJECT_USER_UID ");
        	sql.append(" LEFT JOIN bu_project_role r ");
        	sql.append(" ON ru.PROJECT_ROLE_UID = r.PROJECT_ROLE_UID ");
        	sql.append(" LEFT JOIN bu_project_role_auth a ");
        	sql.append(" ON r.PROJECT_ROLE_UID = a.PROJECT_ROLE_UID ");
        	sql.append(" LEFT JOIN sys_authority t ");
        	sql.append(" ON t.authority_uid = a.authority_uid ");
        	sql.append(" WHERE t.AUTHORITY_CODE = '"+map.get("authorityCode")+"' ");
        	List<Map<String,String>> list = DBUtil.queryReturnList(conn, sql.toString());
        	if(list.size()>0){
        		flag = true;
        	}
        } catch (Exception e) {
            DaoException.handleMessageException("*********原始密码业务判断出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        
        return flag;
	}

	public boolean checkDirAuthority(HashMap<String, String> map) {
	    Connection conn = DBUtil.getConnection();
        boolean flag = false;
        try {
        	StringBuffer sql = new StringBuffer();
        	sql.append(" SELECT d.AUTH_LEVEL FROM bu_project_user u ");
        	sql.append("  LEFT JOIN bu_project_role_user r ");
        	sql.append(" ON u.PROJECT_USER_UID = r.PROJECT_USER_UID ");
        	sql.append(" LEFT JOIN bu_project_role b ");
        	sql.append(" ON r.PROJECT_ROLE_UID = b.PROJECT_ROLE_UID ");
        	sql.append(" LEFT JOIN pm_project_docs_auth d ");
        	sql.append(" ON b.PROJECT_ROLE_UID = d.PROJECT_ROLE_UID ");
        	sql.append(" WHERE u.PROJECT_UID = '"+map.get("projectId")+"' ");
        	sql.append(" AND u.PROJECT_USER_UID = '"+map.get("projectUserId")+"' ");
        	sql.append(" AND d.PROJECT_DOCS_UID = '"+map.get("dirUid")+"' ");

        	List<Map<String,String>> list = DBUtil.queryReturnList(conn, sql.toString());
        	if(list.size()>0){
        		Map<String, String> vmap = list.get(0);
        		if("1".equals(vmap.get("AUTH_LEVEL"))){
        			flag = true;
        		}
        	}
        } catch (Exception e) {
            DaoException.handleMessageException("*********原始密码业务判断出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        
        return flag;
	}

    
}
