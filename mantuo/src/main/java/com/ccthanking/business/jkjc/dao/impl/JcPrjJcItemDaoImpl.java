/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.jkjc.JcPrjJcItemDao.java
 * 创建日期： 2015-12-08 下午 07:52:25
 * 功能：   监测项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-12-08 下午 07:52:25  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.jkjc.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.jkjc.dao.JcPrjJcItemDao;
import com.ccthanking.business.jkjc.vo.JcPrjJcItemVO;
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
 * <p> JcPrjJcItemDao.java </p>
 * <p> 功能：监测项目 </p>
 *
 * <p><a href="JcPrjJcItemDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-12-08
 * 
 */

@Component
public class JcPrjJcItemDaoImpl  extends BsBaseDaoTJdbc implements JcPrjJcItemDao {

    public String queryCondition(String json, JcPrjJcItemVO vo, Map map){
    
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
            sql.append(" SELECT ji.*,jo.OBJECT_NAME,jt.JC_TYPE_NAME FROM jc_prj_jc_item ji ");
            sql.append(" LEFT JOIN bu_jc_object jo  ");
            sql.append(" ON ji.JC_OBJECT_UID = jo.JC_OBJECT_UID ");
            sql.append(" LEFT JOIN bu_jc_type jt ");
            sql.append(" ON ji.JC_TYPE_UID = jt.JC_TYPE_UID ");
            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }

	public String queryById(String jCPRJITEMUID) {
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = null;
            String condition = "";
            String orderFilter = "";
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM jc_prj_jc_item where JC_PRJ_ITEM_UID = '"+jCPRJITEMUID+"'");
            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);
           

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}

	//查询监测对象
	public List<Map<String, String>> queryJcObject() {
        Connection conn = DBUtil.getConnection();
        List<Map<String, String>> domresult = new ArrayList<Map<String,String>>();
        try {

            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT o.JC_OBJECT_UID,o.OBJECT_NAME FROM bu_jc_object o");
            domresult = DBUtil.queryReturnList(conn, sql.toString());
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}

	//查询检测值类型
	public List<Map<String, String>> queryJcType() {
        Connection conn = DBUtil.getConnection();
        List<Map<String, String>> domresult = new ArrayList<Map<String,String>>();
        try {

            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT t.JC_TYPE_UID,t.JC_TYPE_NAME FROM bu_jc_type t");
            domresult = DBUtil.queryReturnList(conn, sql.toString());
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}

	public Boolean removeData(String jCPRJITEMUID) {
        Connection conn = DBUtil.getConnection();
       	Boolean flag = false;
        try {

            StringBuffer sql = new StringBuffer();
            sql.append(" DELETE FROM jc_prj_jc_item WHERE JC_PRJ_ITEM_UID = '"+jCPRJITEMUID+"'");
            flag = DBUtil.exec(conn, sql.toString());
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return flag;
	}
    
    // 在此可加入其它方法

}
