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
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.project.dao.ProjectBiangengDao;
import com.ccthanking.business.project.vo.PmBiangengVO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
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
public class ProjectBiangengDaoImpl  extends BsBaseDaoTJdbc implements ProjectBiangengDao {

    public String queryCondition(String json, PmBiangengVO vo, Map map){
    
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
            String sql = "select b.* ,(select s.user_name from sys_user s where s.user_uid = b.create_user) as CREATBY,(select s.user_name from sys_user s where s.user_uid = b.update_user) as UPDATEBY from pm_biangeng b";
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
