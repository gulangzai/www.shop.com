/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.jkjc.JcPointPropertyDao.java
 * 创建日期： 2015-10-30 上午 09:38:07
 * 功能：   监测点属性值
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-30 上午 09:38:07  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.jkjc.dao.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import sun.util.logging.resources.logging;

import com.ccthanking.business.jkjc.dao.JcPointPropertyDao;
import com.ccthanking.business.jkjc.vo.JcPointPropertyVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.log;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> JcPointPropertyDao.java </p>
 * <p> 功能：监测点属性值 </p>
 *
 * <p><a href="JcPointPropertyDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-30
 * 
 */

@Component
public class JcPointPropertyDaoImpl  extends BsBaseDaoTJdbc implements JcPointPropertyDao {

    public String queryCondition(String json, JcPointPropertyVO vo, Map map){
    
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

            String sql = "SELECT * FROM " + "JC_POINT_PROPERTY t";
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
    /** 监测点的种类的 名称查询**/
	public String querybyTypeId(String itemId, String typeId) {
		Connection conn = DBUtil.getConnection();
		String result="";
		try {
			  StringBuffer sql  = new StringBuffer();
			  sql.append("SELECT pro.TOTAL_WARN2,pro.TOTAL_WARN1,pro.SINGLE_WARN,");
			  sql.append("pro.PRJ_POINT_ITEM_UID,t.JC_TYPE_UID,t.JC_TYPE_NAME,point.JC_PRJ_ITEM_UID ");
			  sql.append("FROM jc_point_property AS pro ");
			  sql.append("LEFT JOIN jc_prj_point_item AS point ON pro.PRJ_POINT_ITEM_UID=point.PRJ_POINT_ITEM_UID ");
			  sql.append("LEFT JOIN jc_prj_jc_item AS item ON point.JC_PRJ_ITEM_UID = item.JC_PRJ_ITEM_UID ");
			  sql.append("LEFT JOIN bu_jc_type AS t ON item.JC_TYPE_UID = t.JC_TYPE_UID ");
			  sql.append("WHERE point.PRJ_POINT_ITEM_UID = '"+itemId+"' and item.JC_TYPE_UID = '"+typeId+"'");


	          BaseResultSet bs = DBUtil.query(conn, sql.toString(),null);
			  result = bs.getJson();
		} catch (Exception e) {
			DaoException.handleMessageException("*********查询当前的 监测点的 种类名称出错!*********");
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return result;
	}
    
	public String querybyPoint_Item_Id(String pointitemId) {
		Connection conn = DBUtil.getConnection();
		String result="";
		try {
			  StringBuffer sql  = new StringBuffer();
			  sql.append(" SELECT pp.SINGLE_WARN,pp.TOTAL_WARN1,pp.TOTAL_WARN2 FROM jc_point_property pp ");
			  sql.append(" WHERE pp.PRJ_POINT_ITEM_UID = '"+pointitemId+"'");

	          BaseResultSet bs = DBUtil.query(conn, sql.toString(),null);
			  result = bs.getJson();
		} catch (Exception e) {
			DaoException.handleMessageException("*********查询预警值出错!*********");
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return result;
	}
	
	public String queryTypenameByTypeid(String typeId) {
		Connection conn = DBUtil.getConnection();
		String result="";
		try {
			  StringBuffer sql  = new StringBuffer();
			  sql.append(" SELECT jt.JC_TYPE_NAME FROM bu_jc_type jt ");
			  sql.append(" WHERE jt.JC_TYPE_UID = '"+typeId+"'");

	          BaseResultSet bs = DBUtil.query(conn, sql.toString(),null);
			  result = bs.getJson();
		} catch (Exception e) {
			DaoException.handleMessageException("*********查询类型名称出错!*********");
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return result;
	}
	
	public String update(HashMap<String, String> map) {
		Connection conn = DBUtil.getConnection();
		String result="failed";
		try {
			
			  StringBuffer sql  = new StringBuffer();
			  if("all".equals(map.get("zyfw"))){
				  StringBuffer querySql = new StringBuffer();
				  querySql.append(" SELECT i.PRJ_POINT_ITEM_UID FROM jc_prj_point_item i  ");
				  querySql.append(" LEFT JOIN jc_prj_jc_item j ");
				  querySql.append(" ON i.JC_PRJ_ITEM_UID = j.JC_PRJ_ITEM_UID ");
				  querySql.append(" LEFT JOIN jc_prj_points p ");
				  querySql.append(" ON p.PRJ_POINTS_UID = i.PRJ_POINTS_UID ");
				  querySql.append(" WHERE i.JC_PRJ_ITEM_UID = '"+map.get("jcitemUid")+"' AND p.PROJECT_UID = '"+map.get("project_uid")+"' ");
				  List<Map<String, String>> list = DBUtil.queryReturnList(conn, querySql.toString());
				  for (int i = 0; i < list.size(); i++) {
					  sql = new StringBuffer();
					  HashMap<String,String> qmap = (HashMap<String, String>) list.get(i);
					  sql.append(" UPDATE jc_point_property  SET SINGLE_WARN = '"+map.get("SINGLE_WARN")+"', TOTAL_WARN1 = '"+map.get("TOTAL_WARN1")+"', TOTAL_WARN2 = '"+map.get("TOTAL_WARN2")+"' ");
					  sql.append(" WHERE PRJ_POINT_ITEM_UID = '"+qmap.get("PRJ_POINT_ITEM_UID")+"'");
					  System.out.println(sql);
					  DBUtil.exec(conn, sql.toString());
				  }

			  }else{
				  sql.append(" UPDATE jc_point_property  SET SINGLE_WARN = '"+map.get("SINGLE_WARN")+"', TOTAL_WARN1 = '"+map.get("TOTAL_WARN1")+"', TOTAL_WARN2 = '"+map.get("TOTAL_WARN2")+"' ");
				  sql.append(" WHERE PRJ_POINT_ITEM_UID = '"+map.get("PRJ_POINT_ITEM_UID")+"'");
				   DBUtil.exec(conn, sql.toString());
			  }
	          
	        
			 result = "successed";
	          
		} catch (Exception e) {
			DaoException.handleMessageException("*********更新测点预警值出错!*********");
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return result;
	}
	
	// 在此可加入其它方法

}
