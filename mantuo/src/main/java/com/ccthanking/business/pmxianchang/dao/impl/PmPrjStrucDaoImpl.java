/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.pmxianchang.PmPrjStrucDao.java
 * 创建日期： 2016-01-25 下午 04:12:17
 * 功能：   项目结构
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-01-25 下午 04:12:17  卢红冈   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.pmxianchang.dao.impl;

import java.sql.Connection;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.pmxianchang.dao.PmPrjStrucDao;
import com.ccthanking.business.pmxianchang.vo.PmPrjStrucVO;
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
 * <p> PmPrjStrucDao.java </p>
 * <p> 功能：项目结构 </p>
 *
 * <p><a href="PmPrjStrucDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggang@kzcpm.com ">卢红冈</a>
 * @version 0.1
 * @since 2016-01-25
 * 
 */

@Component
public class PmPrjStrucDaoImpl  extends BsBaseDaoTJdbc implements PmPrjStrucDao {

    public String queryCondition(String json, PmPrjStrucVO vo, Map map){
    
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

            String sql = "SELECT * FROM " + "PM_PRJ_STRUC xm";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
    
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
