/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.zhenggai.PmZhengGaiContentDao.java
 * 创建日期： 2016-03-30 上午 10:35:43
 * 功能：   工程整改内容
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-30 上午 10:35:43  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.zhenggai.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.zhenggai.dao.PmZhengGaiContentDao;
import com.ccthanking.business.zhenggai.vo.PmZhengGaiContentVO;
import com.ccthanking.business.zhenggai.vo.PmZhengGaiVO;
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
 * <p> PmZhengGaiContentDao.java </p>
 * <p> 功能：工程整改内容 </p>
 *
 * <p><a href="PmZhengGaiContentDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-30
 * 
 */

@Component
public class PmZhengGaiContentDaoImpl  extends BsBaseDaoTJdbc implements PmZhengGaiContentDao {

	public String queryNewContent(String zhenggai_uid,PmZhengGaiVO vo,
			Object object2) {
		User user = ActionContext.getCurrentUserInThread(); 
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try { 
            // 组织查询条件 
            String  sql = "select pz.*,pzd.ZG_DAFU_UID,pzd.DAFU_CODE,pzd.DAFU_DATE,pzd.DAFU_ZG_DATE as ZG_DAFU_ZG_DATE,pzd.DAFU_USER,pzd.FUCHA_USER,pzd.FUCHA_DATE,pzd.FUCHA_DESC,pzd.FUCHA_JIEGUO,pzd.CXZG_DONE_DATE from pm_zhenggai pz left JOIN (SELECT *FROM pm_zg_dafu WHERE  NEW_Y='Y' )pzd on pz.ZHENGGAI_UID = pzd.ZHENGGAI_UID WHERE pz.ZHENGGAI_UID='"+vo.getZhenggai_uid()+"' AND pz.PROJECT_UID='"+vo.getProject_uid()+"'";
            BaseResultSet bs = DBUtil.query(conn, sql, null); 
            domresult = bs.getJson();  
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}
	
	//@Override
	public String queryConditionContent(String zhenggai_uid, Object object,
			Object object2) {
		User user = ActionContext.getCurrentUserInThread(); 
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try { 
            // 组织查询条件 
            String sql = "SELECT t.BZGF_UID,t.WEIGUI_LEVEL,t.XUHAO,t.CONTENT,t.DESCRIPTION,t.ZHENGGAI_CONTENT_UID,PB.NODE_CONTENT from (SELECT * FROM PM_ZHENGGAI_CONTENT WHERE ZHENGGAI_UID='"+zhenggai_uid+"') t inner JOIN PM_BZGF PB ON t.BZGF_UID = PB.BZGF_UID";
            
            BaseResultSet bs = DBUtil.query(conn, sql, null); 
            domresult = bs.getJson();  
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}
	 
	public List queryContentList(String zhenggai_uid, Object vo, Object object){ 
    	User user = ActionContext.getCurrentUserInThread(); 
        Connection conn = DBUtil.getConnection();
        List list  = null;   
        try { 
        	StringBuffer sql = new StringBuffer();
        	sql.append("SELECT t.ZHENGGAI_CONTENT_UID,t.BZGF_UID,t.WEIGUI_LEVEL,t.XUHAO,t.CONTENT,t.DESCRIPTION,t.ZHENGGAI_CONTENT_UID,PB.NODE_CONTENT ,");
        	sql.append("(SELECT NODE_CONTENT FROM pm_bzgf pmbzgf WHERE pmbzgf.BZGF_UID = PB.P_BZGF_UID ) as P_BZGF ");
        	sql.append("from (SELECT * FROM PM_ZHENGGAI_CONTENT WHERE ZHENGGAI_UID='"+zhenggai_uid+"') t ,PM_BZGF PB where t.BZGF_UID = PB.BZGF_UID;");
           // String sql = "SELECT t.ZHENGGAI_CONTENT_UID,t.BZGF_UID,t.WEIGUI_LEVEL,t.XUHAO,t.CONTENT,t.DESCRIPTION,t.ZHENGGAI_CONTENT_UID,PB.NODE_CONTENT from (SELECT * FROM PM_ZHENGGAI_CONTENT WHERE ZHENGGAI_UID='"+zhenggai_uid+"') t inner JOIN PM_BZGF PB ON t.BZGF_UID = PB.BZGF_UID";
            list = DBUtil.queryReturnList(conn, sql.toString());  
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return list; 
    }
	
    
    // 在此可加入其它方法 
    public String queryCondition(String json, PmZhengGaiContentVO vo, Map map){ 
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

            String sql = "SELECT * FROM " + "PM_ZHENGGAI_CONTENT t";
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



}
