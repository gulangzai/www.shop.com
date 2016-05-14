/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.projectlog.PmProjectTopicDao.java
 * 创建日期： 2016-01-19 下午 04:30:30
 * 功能：   问题讨论
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-01-19 下午 04:30:30  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.projectlog.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.projectlog.dao.PmProjectTopicDao;
import com.ccthanking.business.projectlog.vo.PmProjectTopicVO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> PmProjectTopicDao.java </p>
 * <p> 功能：问题讨论 </p>
 *
 * <p><a href="PmProjectTopicDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-01-19
 * 
 */

@Component
public class PmProjectTopicDaoImpl  extends BsBaseDaoTJdbc implements PmProjectTopicDao {

    public String queryCondition(String json, PmProjectTopicVO vo, Map map){
    
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {
        	//String j = "{\"pages\": {\"recordsperpage\": 10,\"currentpagenum\": 1,\"totalpage\": 9,\"countrows\": 84}}";
            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            if (page == null){
                page = new PageManager();
            }
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT t.PROJECT_TOPIC_UID,t.`SUBJECT`,t.STATUS,t.CONTENT, u.USER_NAME ,t.CREATE_USER,");
            sql.append(" DATE_FORMAT(t.CREATE_DATE,'%Y-%m-%d %k:%i') as CREATE_DATE, ");
            sql.append(" (SELECT count(*) FROM pm_project_topic_reply r WHERE r.PROJECT_TOPIC_UID = t.PROJECT_TOPIC_UID) as NUM ");
            sql.append("  FROM PM_PROJECT_TOPIC t ");
			sql.append(" LEFT JOIN sys_user u ");
			sql.append(" ON t.CREATE_USER = u.USER_UID where 1=1 ");
			if(null!= map.get("project_uid")){
				sql.append(" and t.project_uid ='"+map.get("project_uid")+"'");
			}
            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }

	public List<Map<String, String>> queryList() {
        Connection conn = DBUtil.getConnection();
        List<Map<String, String>> list = new ArrayList<Map<String,String>>();
        try {

            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT t.PROJECT_TOPIC_UID,t.`SUBJECT`,t.CONTENT, u.USER_NAME ,t.CREATE_USER,");
            sql.append(" DATE_FORMAT(t.CREATE_DATE,'%Y-%m-%d %k:%i') as CREATE_DATE, ");
            sql.append(" (SELECT count(*) FROM pm_project_topic_reply r WHERE r.PROJECT_TOPIC_UID = t.PROJECT_TOPIC_UID) as NUM ");
            sql.append("  FROM PM_PROJECT_TOPIC t ");
			sql.append(" LEFT JOIN sys_user u ");
			sql.append(" ON t.CREATE_USER = u.USER_UID");
            list = DBUtil.queryReturnList(conn, sql.toString());

        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return list;
	}

	public Map<String, List<?>> queryDetails(String topicUid) {
        Connection conn = DBUtil.getConnection();
        Map<String, List<?>> map = new HashMap<String, List<?>>();
        try {

            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT t.PROJECT_TOPIC_UID,t.`SUBJECT`,t.STATUS,t.CONTENT,u.USER_NAME,f.FILE_PATH,t.CREATE_USER, ");
            sql.append(" DATE_FORMAT(t.CREATE_DATE,'%Y-%m-%d %k:%i') as CREATE_DATE ");
            sql.append("  FROM PM_PROJECT_TOPIC t ");
            sql.append(" LEFT JOIN sys_user u ");
            sql.append(" ON u.USER_UID = t.CREATE_USER ");
            sql.append(" LEFT JOIN ");
			sql.append(" (SELECT a.FILE_PATH,f.TARGET_UID FROM bu_attachment a ");
			sql.append(" LEFT JOIN bu_file f ");
			sql.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID ");
			sql.append(" WHERE f.FILE_TYPE = '10008' ORDER BY f.CREATE_DATE DESC LIMIT 1 ) f ");
			sql.append(" ON u.USER_UID = f.TARGET_UID ");
            sql.append("where t.PROJECT_TOPIC_UID = '"+topicUid+"' ORDER BY CREATE_DATE DESC");
            List<Map<String, String>> topiclist = DBUtil.queryReturnList(conn, sql.toString());
            StringBuffer sql1 = new StringBuffer();
            sql1.append(" SELECT t.PROJECT_TOPIC_REPLY_UID,t.CONTENT,u.USER_NAME,f.FILE_PATH,t.CREATE_USER, ");
            sql1.append(" DATE_FORMAT(t.CREATE_DATE,'%Y-%m-%d %k:%i') as CREATE_DATE ");
            sql1.append("  FROM pm_project_topic_reply t ");
            sql1.append(" LEFT JOIN sys_user u ");
            sql1.append(" ON u.USER_UID = t.CREATE_USER ");
            sql1.append(" LEFT JOIN ");
			sql1.append(" (SELECT a.FILE_PATH,f.TARGET_UID FROM bu_attachment a ");
			sql1.append(" LEFT JOIN bu_file f ");
			sql1.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID ");
			sql1.append(" WHERE f.FILE_TYPE = '10008' ORDER BY f.CREATE_DATE DESC LIMIT 1) f ");
			sql1.append(" ON u.USER_UID = f.TARGET_UID ");
            sql1.append(" WHERE t.PROJECT_TOPIC_UID = '"+topicUid+"' ORDER BY XUHAO ");
            List<?> replylist = DBUtil.queryReturnList(conn, sql1.toString());
            
            StringBuffer sql2 = new StringBuffer();
            sql2.append(" SELECT f.FILE_UID,a.FILE_EXT_NAME,a.FILE_PATH,f.TARGET_UID,a.FILE_NAME  ");
            sql2.append(" FROM bu_attachment a LEFT JOIN bu_file f ");
            sql2.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID ");
            sql2.append(" WHERE f.FILE_TYPE = '10301' AND f.ENABLED = '1'  AND f.TARGET_UID = '"+topicUid+"'  ");

            List<Map<String, String>> topicFileList = DBUtil.queryReturnList(conn, sql2.toString());
            
            for (int i = 0; i < replylist.size(); i++) {
				Map rmap = (Map) replylist.get(i);
                StringBuffer sql3 = new StringBuffer();
                sql3.append(" SELECT f.FILE_UID,a.FILE_EXT_NAME,a.FILE_PATH,f.TARGET_UID,a.FILE_NAME  ");
                sql3.append(" FROM bu_attachment a LEFT JOIN bu_file f ");
                sql3.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID ");
                sql3.append(" WHERE f.FILE_TYPE = '10302' AND f.ENABLED = '1' AND f.TARGET_UID = '"+rmap.get("PROJECT_TOPIC_REPLY_UID")+"'  ");
                
                List<Map<String, String>> replyFileList = DBUtil.queryReturnList(conn, sql3.toString());
                rmap.put("replyFileList", replyFileList);////回复附件
			}

            map.put("topiclist", topiclist);
            map.put("replylist", replylist);
            map.put("topicFileList", topicFileList); //问题附件
            

        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return map;
	}

	public boolean deleteById(String topicUid) {
		Connection conn = DBUtil.getConnection();
		boolean flag = false;
		try {
			
			conn.setAutoCommit(false);
			StringBuffer sql = new StringBuffer();
			sql.append(" DELETE FROM pm_project_topic_reply WHERE PROJECT_TOPIC_UID = '"+topicUid+"'");
			DBUtil.exec(conn, sql.toString());
			StringBuffer sql1 = new StringBuffer();
			sql1.append(" DELETE FROM pm_project_topic WHERE PROJECT_TOPIC_UID = '"+topicUid+"'" );
			DBUtil.exec(conn,sql1.toString());
			conn.commit();
			flag = true;
		} catch (Exception e) {
			 DBUtil.rollbackConnetion(conn);
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		
		return flag;
	}

	public boolean shutDownById(String topic_uid) {
		Connection conn = DBUtil.getConnection();
		boolean flag = false;
		try {
			
			conn.setAutoCommit(false);
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE pm_project_topic SET `STATUS` = '1' WHERE PROJECT_TOPIC_UID = '"+topic_uid+"'");
			DBUtil.exec(conn, sql.toString());
			conn.commit();
			flag = true;
		} catch (Exception e) {
			 DBUtil.rollbackConnetion(conn);
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		
		return flag;
	}
    
    // 在此可加入其它方法

}
