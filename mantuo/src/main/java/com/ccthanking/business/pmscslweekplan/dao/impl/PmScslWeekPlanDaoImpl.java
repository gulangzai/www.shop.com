/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.pmscslweekplan.PmScslWeekPlanDao.java
 * 创建日期： 2016-05-12 上午 10:02:05
 * 功能：   监测项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-05-12 上午 10:02:05  hushujie   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.pmscslweekplan.dao.impl;

import java.sql.Connection;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.ccthanking.business.pmscslweekplan.dao.PmScslWeekPlanDao;
import com.ccthanking.business.pmscslweekplan.vo.PmScslWeekPlanVO;
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
 * <p> PmScslWeekPlanDao.java </p>
 * <p> 功能：监测项目 </p>
 *
 * <p><a href="PmScslWeekPlanDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:">hushujie</a>
 * @version 0.1
 * @since 2016-05-12
 * 
 */

@Component
public class PmScslWeekPlanDaoImpl  extends BsBaseDaoTJdbc implements PmScslWeekPlanDao {

    public String queryCondition(String json, PmScslWeekPlanVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            
//            JSONObject obj =  JSONObject.fromObject(json);
//            JSONObject obj1 = (JSONObject) obj.get("querycondition");
//            JSONArray obj2 =  (JSONArray) obj1.getJSONArray("conditions");
//            String name= obj2.getString(1);
//            
//            String names = name.substring(10, 11);
           // String condition ="";
           String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            // condition += BusinessUtil.getSJYXCondition(null);
            condition += BusinessUtil.getCommonCondition(user, null);
//            String  sql1=" YEARWEEK(date_format(PLAN_WEEK, '%Y-%m-%d')) = YEARWEEK(now())";//查询本周
//            String  sql2=" YEARWEEK(date_format(PLAN_WEEK, '%Y-%m-%d')) = YEARWEEK(now())-1";//查询上周
//            String  sql3=" YEARWEEK(date_format(PLAN_WEEK, '%Y-%m-%d')) = YEARWEEK(now())+1";//查询下周
//            if(names.equals("0")){
//            	 condition += sql1; 	
//            }else if(names.equals("1")){
//            	 condition += sql2;
//            }else if(names.equals("2")){
//            	 condition += sql3;
//            }
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);
             
            String sql = "SELECT * FROM pm_scsl_week_plan ";
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

	public Boolean deleteById(String SCSLWEEKPLANUID) {
		Connection conn = DBUtil.getConnection();
		boolean flag = false;
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append(" DELETE FROM pm_scsl_week_plan WHERE SCSL_WEEK_PLAN_UID = '"+SCSLWEEKPLANUID+"'");

			flag  = DBUtil.exec(conn, sql.toString());

		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		
		return flag;
	}

	public String queryId(String SCSLWEEKPLANUID) {
		User user = ActionContext.getCurrentUserInThread(); 
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try { 
            // 组织查询条件 
            String  sql = "SELECT * from pm_scsl_week_plan where SCSL_WEEK_PLAN_UID='"+SCSLWEEKPLANUID+"'";
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
