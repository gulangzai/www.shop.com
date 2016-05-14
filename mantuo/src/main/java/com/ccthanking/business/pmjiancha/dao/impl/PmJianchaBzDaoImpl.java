/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.pmjiancha.PmJianchaBzDao.java
 * 创建日期： 2016-02-23 上午 09:54:56
 * 功能：   项目检查内容
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-02-23 上午 09:54:56  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.pmjiancha.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.pmjiancha.dao.PmJianchaBzDao;
import com.ccthanking.business.pmjiancha.vo.PmJianchaBzVO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> PmJianchaBzDao.java </p>
 * <p> 功能：项目检查内容 </p>
 *
 * <p><a href="PmJianchaBzDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-02-23
 * 
 */

@Component
public class PmJianchaBzDaoImpl  extends BsBaseDaoTJdbc implements PmJianchaBzDao {

    public String queryCondition(String json, PmJianchaBzVO vo, Map map){
    
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

            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT t.JIANCHA_BZ_UID,t.JIANCHA_LEVEL_UID,  ");
            sql.append(" j.LEVEL_NAME,t.CONTENT ,t.BZGF_UID,b.NODE_CONTENT ");
            sql.append(" FROM PM_JIANCHA_BZ t    ");
            sql.append(" LEFT JOIN sys_jiancha_level j   ");
            sql.append(" ON t.JIANCHA_LEVEL_UID = j.JIANCHA_LEVEL_UID   ");
            sql.append(" LEFT JOIN pm_bzgf b ");
            sql.append(" ON t.BZGF_UID = b.BZGF_UID ");

            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }

	public List<Map<String, String>> queryJcjb() {
        Connection conn = DBUtil.getConnection();
        List<Map<String, String>> list = new ArrayList<Map<String,String>>();
        try {
            String sql = "SELECT JIANCHA_LEVEL_UID,LEVEL_NAME FROM SYS_JIANCHA_LEVEL order by JIANCHA_LEVEL_UID";
            list = DBUtil.queryReturnList(conn, sql);
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return list;
	}
	
	public String getMaxCode() {
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        try {
        	String sql = "SELECT MAX(t.XUHAO) XUHAO FROM pm_jiancha_bz t ";
            list = DBUtil.queryReturnList(conn, sql);
            if(list.size()>0){
            	Map<String,String> map = list.get(0);
            	if("".equals(map.get("XUHAO"))){
            		domresult = "1";
            	}else{
            		domresult = String.valueOf(Long.valueOf(map.get("XUHAO"))+1);
            	}
            }
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

	}

	public String queryBz(String msg) {
    	User user = ActionContext.getCurrentUserInThread();
        
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(msg);
            String condition = RequestUtil.getConditionList(msg).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(msg);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT t.JIANCHA_BZ_UID,t.JIANCHA_LEVEL_UID, ");
            sql.append(" j.LEVEL_NAME,t.CONTENT ");
            sql.append(" FROM PM_JIANCHA_BZ t   ");
            sql.append(" LEFT JOIN sys_jiancha_level j  ");
            sql.append(" ON t.JIANCHA_LEVEL_UID = j.JIANCHA_LEVEL_UID  ");
            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}

	public List<Map<String, String>> queryWgnr(String guifan_uid) {
        Connection conn = DBUtil.getConnection();
        List<Map<String, String>> list = new ArrayList<Map<String,String>>();
        try {
        	StringBuffer sql = new StringBuffer();
//        	sql.append(" SELECT b.BZGF_UID,b.P_BZGF_UID, ");
//        	sql.append(" case b.WEIGUI_LEVEL ");
//        	sql.append(" WHEN '1' THEN '一般' ");
//        	sql.append(" WHEN '2' THEN '较严重' ");
//        	sql.append(" WHEN '3' THEN '严重' ");
//        	sql.append(" END AS WEIGUI_LEVEL_NAME,b.WEIGUI_LEVEL, ");
//        	sql.append(" b.NODE_CONTENT, ");
//        	sql.append(" (SELECT NODE_CONTENT FROM pm_bzgf t WHERE t.BZGF_UID = b.P_BZGF_UID ) as P_BZGF");
//        	sql.append("  FROM pm_bzgf b ");
//          sql.append( " WHERE b.BZGF_UID IN ("+guifan_uid+")");
            
            
            sql.append(" SELECT *,(SELECT NODE_CONTENT FROM pm_bzgf t WHERE t.BZGF_UID = tab.P_BZGF ) as BZGF FROM ( ");
            sql.append(" SELECT b.BZGF_UID,b.P_BZGF_UID, ");
            sql.append(" case b.WEIGUI_LEVEL ");
            sql.append(" WHEN '1' THEN '一般' ");
            sql.append(" WHEN '2' THEN '较严重' ");
            sql.append(" WHEN '3' THEN '严重' ");
            sql.append(" END AS WEIGUI_LEVEL_NAME,b.WEIGUI_LEVEL, ");
            sql.append(" b.NODE_CONTENT, ");
            sql.append(" (SELECT P_BZGF_UID FROM pm_bzgf t WHERE t.BZGF_UID = b.P_BZGF_UID ) as P_BZGF ");
            sql.append("  FROM pm_bzgf b ");
            sql.append("  WHERE P_BZGF_UID IS NOT NULL AND b.BZGF_UID IN ("+guifan_uid+") ) tab ");
            sql.append(" WHERE tab.P_BZGF IS NOT NULL ");
            list = DBUtil.queryReturnList(conn, sql.toString());
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return list;
	}
    
    // 在此可加入其它方法

}
