/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.projectlog.PmProjectLogDao.java
 * 创建日期： 2016-01-14 下午 02:44:26
 * 功能：   项目日志
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-01-14 下午 02:44:26  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.projectlog.dao.impl;

import java.sql.Connection;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.projectlog.dao.PmProjectLogDao;
import com.ccthanking.business.projectlog.vo.PmProjectLogVO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> PmProjectLogDao.java </p>
 * <p> 功能：项目日志 </p>
 *
 * <p><a href="PmProjectLogDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-01-14
 * 
 */

@Component
public class PmProjectLogDaoImpl  extends BsBaseDaoTJdbc implements PmProjectLogDao {

    public String queryCondition(String json, PmProjectLogVO vo, Map map){
    
    
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

            //String sql = "SELECT * FROM " + "PM_PROJECT_LOG l";
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT l.*,u.USER_NAME,p.PROJECT_NAME FROM pm_project_log l ");
            sql.append(" LEFT JOIN bu_project p ");
            sql.append(" ON l.PROJECT_UID = p.PROJECT_UID ");
            sql.append(" LEFT JOIN sys_user u ");
            sql.append(" ON l.CREATE_USER = u.USER_UID ");
            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }

	public boolean deleteById(String logUid) {
		Connection conn = DBUtil.getConnection();
		boolean flag = false;
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append(" DELETE FROM PM_PROJECT_LOG  WHERE PROJECT_LOG_UID = '"+logUid+"'");

			flag  = DBUtil.exec(conn, sql.toString());

		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		
		return flag;
	}
    
    // 在此可加入其它方法

}
