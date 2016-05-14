/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.pmjiancha.PmXunjianContentDao.java
 * 创建日期： 2016-03-02 下午 02:21:23
 * 功能：   巡检检查内容
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-02 下午 02:21:23  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.pmjiancha.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.pmjiancha.dao.PmXunjianContentDao;
import com.ccthanking.business.pmjiancha.vo.PmXunjianContentVO;
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
 * <p> PmXunjianContentDao.java </p>
 * <p> 功能：巡检检查内容 </p>
 *
 * <p><a href="PmXunjianContentDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-02
 * 
 */

@Component
public class PmXunjianContentDaoImpl  extends BsBaseDaoTJdbc implements PmXunjianContentDao {

    public String queryCondition(String json, PmXunjianContentVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition += BusinessUtil.getSJYXCondition(null);
            condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "SELECT * FROM " + "PM_XUNJIAN_CONTENT t";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
            // 合同表
            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
            // 项目下达库
            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
            // 标段表
            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");

            // 设置字典

            // 设置查询条件
            // bs.setFieldDateFormat("JLRQ", "yyyy-MM");// 计量月份
            // bs.setFieldThousand("DYJLSDZ");

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }

	public List<Map<String, String>> queryByXunjianUid(String xunjianUid) {
	    
        Connection conn = DBUtil.getConnection();
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        try {
        	StringBuffer sql = new StringBuffer();
        	
//        	sql.append(" SELECT t.XUNJIAN_CONTENT_UID,t.XUNJIAN_UID, ");
//        	sql.append(" t.BZGF_UID,t.CONTENT, ");
//        	sql.append(" case t.WEIGUI_LEVEL ");
//        	sql.append(" WHEN '1' THEN '一般' ");
//        	sql.append(" WHEN '2' THEN '较严重' ");
//        	sql.append(" WHEN '3' THEN '严重' ");
//        	sql.append(" END AS WEIGUI_LEVEL_NAME,t.WEIGUI_LEVEL, ");
//        	sql.append(" t.DESCRIPTION ,");
//        	sql.append(" (SELECT NODE_CONTENT FROM pm_bzgf p WHERE p.BZGF_UID =  (SELECT P_BZGF_UID FROM pm_bzgf b WHERE b.BZGF_UID = t.BZGF_UID) ) as P_BZGF");
//        	sql.append(" FROM pm_xunjian_content t ");
//        	sql.append(" WHERE t.XUNJIAN_UID = '"+xunjianUid+"'");
        	
        	sql.append(" SELECT *,(SELECT NODE_CONTENT FROM pm_bzgf p WHERE p.BZGF_UID = tab.P_BZGF) AS BZGF FROM ( ");
        	sql.append(" SELECT t.XUNJIAN_CONTENT_UID,t.XUNJIAN_UID,  ");
        	sql.append(" t.BZGF_UID,t.CONTENT,  ");
        	sql.append(" case t.WEIGUI_LEVEL  ");
        	sql.append(" WHEN '1' THEN '一般'  ");
        	sql.append(" WHEN '2' THEN '较严重'  ");
        	sql.append(" WHEN '3' THEN '严重'  ");
        	sql.append(" END AS WEIGUI_LEVEL_NAME,t.WEIGUI_LEVEL,  ");
        	sql.append(" t.DESCRIPTION , ");
        	sql.append(" (SELECT P_BZGF_UID FROM pm_bzgf p WHERE p.BZGF_UID =  (SELECT P_BZGF_UID FROM pm_bzgf b WHERE b.BZGF_UID = t.BZGF_UID) ) as P_BZGF ");
        	sql.append(" FROM pm_xunjian_content t  ");
        	sql.append(" WHERE t.XUNJIAN_UID = '"+xunjianUid+"') tab  ");
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
