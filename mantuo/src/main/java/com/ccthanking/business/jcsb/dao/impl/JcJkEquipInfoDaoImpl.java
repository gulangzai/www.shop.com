/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.jcsb.JcJkEquipInfoDao.java
 * 创建日期： 2015-10-27 下午 03:52:34
 * 功能：   项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-27 下午 03:52:34  luhonggang   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.jcsb.dao.impl;

import java.sql.Connection;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.jcsb.dao.JcJkEquipInfoDao;
import com.ccthanking.business.jcsb.vo.JcJkEquipInfoVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> JcJkEquipInfoDao.java </p>
 * <p> 功能：项目 </p>
 *
 * <p><a href="JcJkEquipInfoDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhongang@163.com">luhonggang</a>
 * @version 0.1
 * @since 2015-10-27
 * 
 */

@Component
public class JcJkEquipInfoDaoImpl  extends BsBaseDaoTJdbc implements JcJkEquipInfoDao {

    public String queryCondition(String json, JcJkEquipInfoVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
          
            condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "SELECT j.* FROM bu_project t "+
            		     " RIGHT JOIN jc_equipment j ON j.PROJECT_UID = t.PROJECT_UID ";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
        	
            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询设施设备信息出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
    //项目设备维护初始化 sql 查询当前项目下的 设施设备
	public String query(String msg, Object object, Object object2) {
		User user = ActionContext.getCurrentUserInThread();
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(msg);
            String condition = RequestUtil.getConditionList(msg).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(msg);
           // condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);
            
            String sql = "SELECT t.* FROM jc_equipment t "+
       		     " LEFT JOIN   bu_project b ON t.PROJECT_UID = b.PROJECT_UID ";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
        	
            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询项目设备维护出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}
    
    // 在此可加入其它方法

}
