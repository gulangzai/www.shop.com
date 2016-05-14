/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.bucompany.BuCompanyDao.java
 * 创建日期： 2016-04-28 上午 11:14:13
 * 功能：   监测项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-04-28 上午 11:14:13  hushujie   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.bucompany.dao.impl;

import java.sql.Connection;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.bucompany.dao.BuCompanyDao;
import com.ccthanking.business.bucompany.vo.BuCompanyVO;
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
 * <p> BuCompanyDao.java </p>
 * <p> 功能：监测项目 </p>
 *
 * <p><a href="BuCompanyDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:">hushujie</a>
 * @version 0.1
 * @since 2016-04-28
 * 
 */

@Component
public class BuCompanyDaoImpl  extends BsBaseDaoTJdbc implements BuCompanyDao {

    public String queryCondition(String json, BuCompanyVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            //condition += BusinessUtil.getSJYXCondition(null);
            condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "SELECT * FROM " + "BU_COMPANY t";
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

	public boolean deleteid(String companyuid) {
		Connection conn = DBUtil.getConnection();
		boolean flag = false;
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append(" DELETE FROM BU_COMPANY WHERE COMPANY_UID = '"+companyuid+"'");

			flag  = DBUtil.exec(conn, sql.toString());

		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		
		return flag;
	}

	public String queryid(String companyuid) {
		
		User user = ActionContext.getCurrentUserInThread(); 
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try { 
            // 组织查询条件 
            String  sql = "SELECT * from bu_company where COMPANY_UID='"+companyuid+"'";
            BaseResultSet bs = DBUtil.query(conn, sql, null); 
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
