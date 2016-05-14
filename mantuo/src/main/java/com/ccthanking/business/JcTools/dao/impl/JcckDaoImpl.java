/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.JcTools.JcckDao.java
 * 创建日期： 2015-10-29 下午 02:32:36
 * 功能：   仓库备品
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-29 下午 02:32:36  luhonggng   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.JcTools.dao.impl;

import java.sql.Connection;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.JcTools.dao.JcckDao;
import com.ccthanking.business.jcck.vo.JcToolsVO;
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
 * <p> JcckDao.java </p>
 * <p> 功能：仓库备品 </p>
 *
 * <p><a href="JcckDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggang@163.com">luhonggng</a>
 * @version 0.1
 * @since 2015-10-29
 * 
 */

@Component
public class JcckDaoImpl  extends BsBaseDaoTJdbc implements JcckDao {

    public String queryCondition(String json, JcToolsVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition += orderFilter;
            //"t.TOOLS_TYPE ='JK' "
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "SELECT t.* FROM  JC_TOOLS t left join BU_PROJECT b " +
            		     " on t.PROJECT_UID = b.PROJECT_UID ";
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
