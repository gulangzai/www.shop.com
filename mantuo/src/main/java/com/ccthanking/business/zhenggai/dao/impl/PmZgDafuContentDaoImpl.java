/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.zhenggai.PmZgDafuContentDao.java
 * 创建日期： 2016-03-30 上午 10:43:36
 * 功能：   工程整改答复内容
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-30 上午 10:43:36  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.zhenggai.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.ccthanking.business.zhenggai.dao.PmZgDafuContentDao;
import com.ccthanking.business.zhenggai.vo.PmZgDafuContentVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.base.BaseVO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.QueryConditionList;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> PmZgDafuContentDao.java </p>
 * <p> 功能：工程整改答复内容 </p>
 *
 * <p><a href="PmZgDafuContentDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-30
 * 
 */

@Component
public class PmZgDafuContentDaoImpl  extends BsBaseDaoTJdbc implements PmZgDafuContentDao {

    public String queryCondition(String json, PmZgDafuContentVO vo, Map map){
    
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

            String sql = "SELECT * FROM " + "PM_ZG_DAFU_CONTENT t";
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
    
    // 在此可加入其它方法 
    public List querySon(String json, Object vo, Object map,String ZG_DAFU_UID){ 
    	User user = ActionContext.getCurrentUserInThread(); 
        Connection conn = DBUtil.getConnection();
        List list  = null;   
        try {   
           //PageManager page = RequestUtil.getPageManager(json);
        	//RequestUtil.getConditionList(json).
            //String condition = RequestUtil.getConditionList(json).getConditionWhere();
        	//String ZG_DAFU_UID=null;
           //String orderFilter = RequestUtil.getOrderFilter(json);
            //condition += BusinessUtil.getSJYXCondition(null);
            //condition += BusinessUtil.getCommonCondition(user, null);
            //condition += orderFilter;
            //if (page == null)
            //    page = new PageManager();
            // page.setFilter(condition);  
            //String sql="select pzc.XUHAO,pzc.BZGF_UID,pb.NODE_CONTENT,pzc.WEIGUI_LEVEL,pzc.CONTENT,pzc.DESCRIPTION, pzdc.DAFU_CONTENT from pm_zhenggai_content pzc  inner join pm_zg_dafu_content pzdc on  pzc.ZHENGGAI_CONTENT_UID = pzdc.ZHENGGAI_CONTENT_UID INNER JOIN pm_bzgf pb on pzc.BZGF_UID = pb.BZGF_UID";

            String sql="select pzc.XUHAO,pzc.BZGF_UID,pb.NODE_CONTENT,pzc.WEIGUI_LEVEL,pzc.CONTENT,pzc.DESCRIPTION, pzdc.DAFU_CONTENT from pm_zhenggai_content pzc  inner join pm_zg_dafu_content pzdc on  pzc.ZHENGGAI_CONTENT_UID = pzdc.ZHENGGAI_CONTENT_UID INNER JOIN pm_bzgf pb on pzc.BZGF_UID = pb.BZGF_UID where pzdc.ZG_DAFU_UID='"+ZG_DAFU_UID+"'";
            list = DBUtil.queryReturnList(conn, sql); 
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
            //domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return list;

    }
 
}
