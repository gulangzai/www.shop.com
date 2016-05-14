/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.project.BuProjectUserDao.java
 * 创建日期： 2015-10-20 下午 05:09:42
 * 功能：   项目用户
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-20 下午 05:09:42  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.project.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.project.dao.BuProjectUserDao;
import com.ccthanking.business.project.vo.BuProjectUserVO;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.DateUtil;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> BuProjectUserDao.java </p>
 * <p> 功能：项目用户 </p>
 *
 * <p><a href="BuProjectUserDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-20
 * 
 */

@Component
public class BuProjectUserDaoImpl  extends BsBaseDaoTJdbc implements BuProjectUserDao {


	
	 
	public boolean getByCondition(BuProjectUserVO vo) {
		// TODO Auto-generated method stub
		Connection conn = null; 
		BaseResultSet brs = null;
		boolean flag = true;
		try {
			conn = DBUtil.getConnection();
			 StringBuffer sb = new StringBuffer();
			 sb.append("select *from bu_project_user where project_uid="+vo.getProject_uid()+" and USER_UID="+vo.getUser_uid());
			 brs = DBUtil.query(conn, sb.toString(), null); 
			 ResultSet rs = brs.getResultSet();
			 flag = rs.next();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return flag;
	}
	
    public String queryCondition(String json, BuProjectUserVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            //String sql = "SELECT * FROM " + "BU_PROJECT_USER t";
            StringBuffer sql  = new StringBuffer();
            sql.append(" SELECT u.USER_NAME,u.LOGON_NAME,u.EMAIL,u.MOBILE,i.EXPIRED_DATE,i.JOIN_DATE, ");
            sql.append(" CASE   ");
            //sql.append(" when i.JOIN_DATE IS NULL THEN '未邀请' ");
            sql.append(" WHEN i.JOIN_DATE IS NULL AND TIMESTAMPDIFF(HOUR,i.EXPIRED_DATE,SYSDATE()) >= 0 THEN  '过期邀请' ");
            sql.append(" WHEN i.JOIN_DATE IS NULL AND TIMESTAMPDIFF(HOUR,i.EXPIRED_DATE,SYSDATE()) < 0 THEN  '已邀请,待确认' ");
            sql.append(" WHEN i.JOIN_DATE IS NOT NULL THEN '完成邀请' ");
            sql.append(" END ");
            sql.append(" AS STATUS ");
            sql.append(" FROM sys_invite i  ");
            sql.append(" LEFT JOIN ");
            sql.append(" sys_user u ");
            sql.append(" ON  ");
            sql.append(" i.INVITER_USER = u.USER_UID ");

            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }

    //验证用户是否存在
	public Map<String,String> checkEmail(String email) {
		Map<String,String> map  = new HashMap<String, String>();
		String uuid = "";
		String project_uid = "";
        Connection conn = DBUtil.getConnection();
        try {
        	
        	StringBuffer sql = new StringBuffer();
        	sql.append(" SELECT su.USER_UID,bpu.PROJECT_UID FROM sys_user  su ");
        	sql.append(" LEFT JOIN bu_project_user bpu ");
        	sql.append(" ON su.USER_UID = bpu.USER_UID ");
        	sql.append(" WHERE EMAIL = '"+email+"'");
        	
			String[][] res = DBUtil.query(conn, sql.toString());
			
			if (res != null && !"".equals(res)) {
				uuid =  res[0][0];
				project_uid = res[0][1];
				map.put("uuid", uuid);
				map.put("project_uid", project_uid);
			}
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return map;
	}

	//验证是否是有效邀请
	public Map<String,String> checkTime(String uuid) {
	       Map<String,String> map = new HashMap<String,String>();
	       Connection conn = DBUtil.getConnection();
	       try {
	        	String sql = "SELECT * FROM sys_invite WHERE INVITE_UID = '"+uuid+"'";
	            
				String[][] res = DBUtil.query(conn, sql);
				if (null != res) {
					if (DateUtil.isOverdue(res[0][5])) {
						map.put("flag", "false");
						map.put("msg","邀请已过期！");
					}else if(!"".equals(res[0][6])){
						map.put("flag", "false");
						map.put("msg","邀请已确认，请勿重复操作！");
					}else{
						map.put("flag", "true");
					}
				}else{
					map.put("flag", "false");
					map.put("msg","邀请错误！");					
				}
	            
	        } catch (Exception e) {
	            DaoException.handleMessageException("*********查询出错!*********");
	        } finally {
	            DBUtil.closeConnetion(conn);
	        }
	        return map;
	}
	
	public Map<String, String> userConfirm(String uuid) {
	       Map<String,String> map = new HashMap<String,String>();
	       Connection conn = DBUtil.getConnection();
	       try {
	        	String sql = "UPDATE sys_invite set JOIN_DATE = SYSDATE() WHERE INVITE_UID = '"+uuid+"'";
	            DBUtil.exec(conn, sql);
	            String insertSql = "INSERT INTO bu_project_user (PROJECT_UID,USER_UID,CREATED_DATE)"+
	            				" SELECT PROJECT_UID,INVITER_USER,SYSDATE() FROM sys_invite WHERE INVITE_UID = '"+uuid+"'";
				DBUtil.exec(conn, insertSql);
				map.put("flag", "true");
	            map.put("msg", "邀请已确认");
	        } catch (Exception e) {
				map.put("flag", "false");
	            map.put("msg", "确认出错，联系管理员");
	            DaoException.handleMessageException("*********查询出错!*********");
	        } finally {
	            DBUtil.closeConnetion(conn);
	        }
	        return map;
	}

	public String queryList(String json) {
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            //String sql = "SELECT * FROM " + "BU_PROJECT_USER t";
            StringBuffer sql  = new StringBuffer();
            sql.append(" SELECT s.*,u.PROJECT_USER_UID FROM bu_project_user u ");
            sql.append(" LEFT JOIN sys_user s ");
            sql.append(" ON u.USER_UID = s.USER_UID ");
            //sql.append(" WHERE u.PROJECT_UID = '1' ");

            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}




    
    // 在此可加入其它方法

}
