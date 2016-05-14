/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.JcPerson.JcPersonInfoDao.java
 * 创建日期： 2015-10-29 上午 10:54:59
 * 功能：   负责人联络方式
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-29 上午 10:54:59  luhonggng   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.JcPerson.dao.impl;

import java.sql.Connection;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.JcPerson.dao.JcPersonInfoDao;
import com.ccthanking.business.JcPerson.vo.JcPersonInfoVO;
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
 * <p> JcPersonInfoDao.java </p>
 * <p> 功能：负责人联络方式 </p>
 *
 * <p><a href="JcPersonInfoDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggang@163.com">luhonggng</a>
 * @version 0.1
 * @since 2015-10-29
 * 
 */

@Component
public class JcPersonInfoDaoImpl  extends BsBaseDaoTJdbc implements JcPersonInfoDao {

    public String queryCondition(String json, JcPersonInfoVO vo, Map map){
    
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

            String sql = "SELECT t.* FROM JC_PERSON_INFO t  left join BU_PROJECT b "+
                         " on t.PROJECT_UID = b.PROJECT_UID ";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
           
            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询负责人联络方式出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }

    //抢修单位 联系人
	public String query(String msg, Object object, Object object2) {
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

            String sql = "SELECT t.* FROM " + "JC_PERSON_INFO t  left join BU_PROJECT b "+
                         " on t.PROJECT_UID = b.PROJECT_UID ";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
           
            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询负责人联络方式出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}
    
    // 在此可加入其它方法

}
