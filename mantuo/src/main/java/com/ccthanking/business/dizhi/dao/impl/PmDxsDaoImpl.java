/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.dizhi.PmDxsDao.java
 * 创建日期： 2015-12-17 下午 02:19:59
 * 功能：   项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-12-17 下午 02:19:59  卢红冈   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.dizhi.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.dizhi.dao.PmDxsDao;
import com.ccthanking.business.dizhi.vo.PmDxsVO;
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
 * <p> PmDxsDao.java </p>
 * <p> 功能：项目 </p>
 *
 * <p><a href="PmDxsDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggnag@163.com">卢红冈</a>
 * @version 0.1
 * @since 2015-12-17
 * 
 */

@Component
public class PmDxsDaoImpl  extends BsBaseDaoTJdbc implements PmDxsDao {

    public String queryCondition(String json, PmDxsVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {


            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
           // condition += BusinessUtil.getSJYXCondition(null);
            condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "select d.*,u.USER_NAME from pm_dxs d LEFT JOIN sys_user u  ON d.CREATE_USER = u.USER_UID ";
                       
            BaseResultSet bs = DBUtil.query(conn, sql, page);
            // 设置查询条件
           //  bs.setFieldDateFormat("CREATE_DATE", "yyyy-MM-dd ");// 计量月份
            
            domresult = bs.getJson();
            
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
  
	public List<?> queryById(String dxsUid) {
		Connection conn = DBUtil.getConnection();
        List<?> list = new ArrayList();
        try {
            StringBuffer sql  = new StringBuffer();
            sql.append(" SELECT d.* FROM  pm_dxs d ");
            sql.append(" WHERE d.DXS_UID = '"+dxsUid+"'");
            list = DBUtil.queryReturnList(conn, sql.toString());

        } catch (Exception e) {
            DaoException.handleMessageException("*********查询地下水详细信息出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        
        return list;
	}
    
    // 在此可加入其它方法

}
