/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.jkjc.JcPrjPointsDao.java
 * 创建日期： 2015-10-30 上午 09:48:59
 * 功能：   项目监测点位
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-30 上午 09:48:59  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.jkjc.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.ccthanking.business.jkjc.dao.JcPrjPointsDao;
import com.ccthanking.business.jkjc.vo.JcPrjPointsVO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> JcPrjPointsDao.java </p>
 * <p> 功能：项目监测点位 </p>
 *
 * <p><a href="JcPrjPointsDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-30
 * 
 */

@Component
public class JcPrjPointsDaoImpl  extends BsBaseDaoTJdbc implements JcPrjPointsDao {

    public String queryCondition(String json, JcPrjPointsVO vo, Map map){
    
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
            sql.append(" SELECT pp.* FROM jc_prj_points pp ");
            sql.append(" LEFT JOIN jc_prj_point_item pi ");
            sql.append(" ON pp.PRJ_POINTS_UID = pi.PRJ_POINTS_UID ");
            sql.append(" LEFT JOIN jc_prj_jc_item ji ");
            sql.append(" ON pi.JC_PRJ_ITEM_UID = ji.JC_PRJ_ITEM_UID ");
            //sql.append(" WHERE ji.ITEM_TYPE = '降水' ");
            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);
           

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }

    /** 监测点 类型图片 查询 */
	public String queryPointForType(String msg,HashMap<String, String> map) {
		
	    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(msg);
            String condition = RequestUtil.getConditionList(msg).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(msg);
            if(null!=map.get("type")){
            	condition += "  and J.ITEM_TYPE = '"+map.get("type")+"'";
            }
            //j.JKJC_TYPE
            condition += " AND i.JC_PRJ_ITEM_UID IS NOT NULL GROUP BY j.SHORT_NAME " +orderFilter;
           // condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);
 /*
            String sql = "SELECT 	jc_prj_points.U3D_ELEMENT_ID  FROM  jc_prj_points "+
                         " INNER JOIN bu_project p ON jc_prj_points.PROJECT_UID = p.PROJECT_UID";
					*/
			
           String sql="SELECT j.SHORT_NAME,j.JC_PRJ_ITEM_UID,j.ICON_FILE,j.PRE_CODE,points.U3D_POINT_ID,points.U3D_ELEMENT_ID"+
					  " FROM  jc_prj_jc_item j "+
					  " LEFT  JOIN jc_prj_point_item  i ON j.JC_PRJ_ITEM_UID = i.JC_PRJ_ITEM_UID "+
					  " LEFT JOIN jc_prj_points points ON i.PRJ_POINTS_UID = points.PRJ_POINTS_UID "+
					  " LEFT JOIN bu_project p ON j.PROJECT_UID = p.PROJECT_UID AND points.PROJECT_UID = p.PROJECT_UID ";
            BaseResultSet bs = DBUtil.query(conn, sql, page);

            domresult = bs.getJson();
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询检测点类型 出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

	}

	public List<?> queryList(Map<String, String> map) {
        Connection conn = DBUtil.getConnection();
        User user = ActionContext.getCurrentUserInThread();
        List<?> list = new ArrayList();
        try {

            StringBuffer sql  = new StringBuffer();
			sql.append("  SELECT p.PRJ_POINTS_UID,p.POINT_CODE,i.JC_NAME,i.SHORT_NAME, ");
			sql.append("  i.JC_TYPE_UID,ppi.PRJ_POINT_ITEM_UID, ");
			sql.append("  CASE WHEN pp.SINGLE_WARN IS NOT NULL THEN pp.SINGLE_WARN  ");
			sql.append(" WHEN pp.SINGLE_WARN IS  NULL THEN i.SINGLE_WARN END AS SINGLE_WARN, ");
			sql.append(" CASE WHEN pp.TOTAL_WARN1 IS NOT NULL THEN PP.TOTAL_WARN1   ");
			sql.append(" WHEN pp.TOTAL_WARN1 IS NULL THEN i.TOTAL_WARN1  END AS TOTAL_WARN1, ");
			sql.append(" CASE WHEN pp.TOTAL_WARN2 IS NOT NULL THEN PP.TOTAL_WARN2    ");
			sql.append(" WHEN pp.TOTAL_WARN2 IS NULL THEN I.TOTAL_WARN2  END AS TOTAL_WARN2  ");
			sql.append(" FROM jc_prj_points  p  ");
			sql.append("    LEFT JOIN jc_prj_point_item ppi  ");
			sql.append("    ON p.PRJ_POINTS_UID = ppi.PRJ_POINTS_UID  ");
			sql.append("    LEFT JOIN jc_prj_jc_item i  ");
			sql.append("    ON ppi.JC_PRJ_ITEM_UID = i.JC_PRJ_ITEM_UID  ");
			sql.append("    LEFT JOIN jc_point_property pp  ");
			sql.append("    ON pp.PRJ_POINT_ITEM_UID = ppi.JC_PRJ_ITEM_UID  ");

            sql.append(" where p.PROJECT_UID = '");
            sql.append(map.get("project_uid"));
            sql.append("'");
            
           
            if(null!= map.get("type")&&"bj".equals(map.get("type"))){
            	sql.append("");
            }
            if(null!=map.get("lx")&&!"".equals(map.get("lx"))){
            	sql.append(" and i.JC_PRJ_ITEM_UID in ("+map.get("lx")+")");
            }
            
/*            if(null!=map.get("wdgz")&&"1".equals(map.get("wdgz"))){
            	sql.append(" and f.USER_UID = '");
            	sql.append(user.getIdCard());
            	sql.append("'");
            }*/
            sql.append(" ORDER BY p.POINT_CODE");
            list = DBUtil.queryReturnList(conn, sql.toString());

        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        
        return list;
	}
 /**
  * 通过 项目监测点位表 的主键 PRJ_POINTS_UID去 监测点监测项目表 中 
  * 查找 监测项目表 的主键 JC_PRJ_ITEM_UID
  * 从而获取 当前测点所属类型所对应的图标  并获取到当前点最新的监测数据（日期最大的数据）
  */
	public List<Map<String, String>> queryIconById(String eleId) {
	    User user = ActionContext.getCurrentUserInThread();
        Connection conn = DBUtil.getConnection();
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        try {
         

          /*  String sql = "SELECT points.U3D_ELEMENT_ID, points.PRJ_POINTS_UID, i.JC_TYPE_UID, "+
						 " j.PRJ_POINTS_UID PRJ_POINTS_UID_ITEM, j.JC_PRJ_ITEM_UID, "+
						" i.JC_PRJ_ITEM_UID JC_PRJ_ITEM_UID_ITEM, i.SHORT_NAME,i.ICON_FILE "+
						" FROM jc_prj_points AS points "+
						" LEFT JOIN jc_prj_point_item AS j ON j.PRJ_POINTS_UID = points.PRJ_POINTS_UID "+
						" LEFT JOIN jc_prj_jc_item AS i ON j.JC_PRJ_ITEM_UID = i.JC_PRJ_ITEM_UID "+
						" LEFT  JOIN bu_project AS p ON points.PROJECT_UID = p.PROJECT_UID AND i.PROJECT_UID = p.PROJECT_UID "+
						" WHERE points.U3D_ELEMENT_ID ="+eleId;*/
         
          String sql="SELECT points.U3D_ELEMENT_ID,points.PRJ_POINTS_UID,"+
					 " item.PRJ_POINT_ITEM_UID AS ITEM_P_UID, "+
					 " 	d.PRJ_POINTS_UID AS DATA_PP_UID,d.VERTICAL_VALUE, "+
					 " 	d.VERTICAL_VALUE_DIFF,d.REPORT_DATE, "+
					 " 	j.JC_PRJ_ITEM_UID AS J_P_ITEM_UID,j.SHORT_NAME,j.ICON_FILE, "+
					 " 	p.PRJ_POINT_ITEM_UID,item.JC_PRJ_ITEM_UID, "+
					 " 	p.SINGLE_WARN,p.TOTAL_WARN1,p.TOTAL_WARN2 "+
					 " 	FROM "+
					 " 	jc_prj_points AS points "+
					 " 	LEFT JOIN jc_prj_point_item AS item ON item.PRJ_POINTS_UID = points.PRJ_POINTS_UID "+
					 " 	LEFT JOIN jc_jc_data AS d ON d.PRJ_POINTS_UID = points.PRJ_POINTS_UID "+
					 " 	LEFT JOIN jc_prj_jc_item AS j ON item.JC_PRJ_ITEM_UID = j.JC_PRJ_ITEM_UID "+
					 " 	LEFT JOIN jc_point_property AS p ON p.PRJ_POINT_ITEM_UID = item.PRJ_POINT_ITEM_UID "+
					 " 	WHERE points.U3D_ELEMENT_ID = '"+eleId+"' "+
					 " 	ORDER BY d.REPORT_DATE DESC LIMIT 0,1 ";
						
						
           list =  DBUtil.queryReturnList(conn,sql.toString());
           
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询U3D测点 图标出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return list;

	}
	
	
	/**
	 * 
	 */
	public List<Map<String, String>> queryU3DElementUids(HashMap<String, String> map) {
	    Connection conn = DBUtil.getConnection();
	    List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	    try {
	    	StringBuffer sql = new StringBuffer();
	    	sql.append(" SELECT jp.U3D_ELEMENT_ID FROM jc_prj_points jp ");
	    	sql.append(" LEFT JOIN jc_prj_point_item jpi ");
	    	sql.append(" ON jp.PRJ_POINTS_UID = jpi.PRJ_POINTS_UID ");
	    	sql.append(" LEFT JOIN jc_prj_jc_item j ");
	    	sql.append(" ON jpi.JC_PRJ_ITEM_UID = j.JC_PRJ_ITEM_UID ");
			sql.append(" WHERE jp.PROJECT_UID = '"+map.get("puid")+"'");
			if(!"".equals(map.get("jpiuid"))){
				sql.append(" AND jpi.JC_PRJ_ITEM_UID in ("+map.get("jpiuid")+")");
			}
			sql.append(" and j.ITEM_TYPE = '"+map.get("type")+"'");
	       list =  DBUtil.queryReturnList(conn,sql.toString());
	       
	    } catch (Exception e) {
	        DaoException.handleMessageException("*********查询测点 图标出错!*********");
	    } finally {
	        DBUtil.closeConnetion(conn);
	    }
	    return list;
	
	}
    
	/**
	 * 
	 */
	public List<Map<String, String>> queryPoints(HashMap<String, String> map) {
	    Connection conn = DBUtil.getConnection();
	    List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	    try {
	    	StringBuffer sql = new StringBuffer();
	    	sql.append(" SELECT jp.* FROM jc_prj_points jp ");
			sql.append(" WHERE jp.PROJECT_UID = '"+map.get("puid")+"'");
	       list =  DBUtil.queryReturnList(conn,sql.toString());
	       
	    } catch (Exception e) {
	        DaoException.handleMessageException("*********查询测点出错!*********");
	    } finally {
	        DBUtil.closeConnetion(conn);
	    }
	    return list;
	
	}

	/**
	 * 
	 */
	public List<Map<String, String>> queryItems(HashMap<String, String> map) {
	    Connection conn = DBUtil.getConnection();
	    List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	    try {
	    	StringBuffer sql = new StringBuffer();
	    	sql.append(" SELECT ji.* FROM jc_prj_jc_item ji ");
			sql.append(" WHERE ji.PROJECT_UID = '"+map.get("puid")+"'");
	       list =  DBUtil.queryReturnList(conn,sql.toString());
	       
	    } catch (Exception e) {
	        DaoException.handleMessageException("*********查询检测项目出错!*********");
	    } finally {
	        DBUtil.closeConnetion(conn);
	    }
	    return list;
	
	}
	
	/**
	 * 
	 */
	public List<Map<String, String>> queryItemTypes(HashMap<String, String> map) {
	    Connection conn = DBUtil.getConnection();
	    List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	    try {
	    	StringBuffer sql = new StringBuffer();
	    	sql.append(" SELECT * FROM bu_jc_type ");
	       list =  DBUtil.queryReturnList(conn,sql.toString());
	       
	    } catch (Exception e) {
	        DaoException.handleMessageException("*********查询检测项目监测类型出错!*********");
	    } finally {
	        DBUtil.closeConnetion(conn);
	    }
	    return list;
	
	}

	/**
	 * 
	 */
	public List<Map<String, String>> queryPoint_Item(HashMap<String, String> map) {
	    Connection conn = DBUtil.getConnection();
	    List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	    try {
	    	StringBuffer sql = new StringBuffer();
	    	sql.append(" SELECT jci.* FROM jc_prj_point_item jci ");
	    	sql.append(" LEFT JOIN jc_prj_points jp ");
	    	sql.append(" ON jci.PRJ_POINTS_UID = jp.PRJ_POINTS_UID ");
			sql.append(" WHERE jp.PROJECT_UID = '"+map.get("puid")+"'");
			sql.append(" ORDER BY jp.POINT_CODE ");
	       list =  DBUtil.queryReturnList(conn,sql.toString());
	       
	    } catch (Exception e) {
	        DaoException.handleMessageException("*********查询测点监测项出错!*********");
	    } finally {
	        DBUtil.closeConnetion(conn);
	    }
	    return list;
	
	}

	/**
	 * 
	 */
	public List<Map<String, String>> queryPointProperty(HashMap<String, String> map) {
	    Connection conn = DBUtil.getConnection();
	    List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	    try {
	    	StringBuffer sql = new StringBuffer();
	    	sql.append(" SELECT jpp.* FROM jc_point_property jpp ");
	    	sql.append(" LEFT JOIN jc_prj_point_item jpi ");
	    	sql.append(" ON jpp.PRJ_POINT_ITEM_UID = jpi.PRJ_POINT_ITEM_UID ");
	    	sql.append(" LEFT JOIN jc_prj_points jp ");
	    	sql.append(" ON jp.PRJ_POINTS_UID = jpi.PRJ_POINTS_UID ");
			sql.append(" WHERE jp.PROJECT_UID = '"+map.get("puid")+"'");
	       list =  DBUtil.queryReturnList(conn,sql.toString());
	       
	    } catch (Exception e) {
	        DaoException.handleMessageException("*********查询测点预警值出错!*********");
	    } finally {
	        DBUtil.closeConnetion(conn);
	    }
	    return list;
	
	}

	/**
	 * 
	 */
	public List<Map<String, String>> queryPointData_jc(HashMap<String, String> map) {
	    Connection conn = DBUtil.getConnection();
	    List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	    try {
	    	StringBuffer sql = new StringBuffer();
	    	sql.append(" SELECT a.* FROM ");
	    	sql.append(" (SELECT jd.* FROM jc_jc_data jd LEFT JOIN jc_prj_points jp ON jd.PRJ_POINTS_UID = jp.PRJ_POINTS_UID WHERE jp.PROJECT_UID = '"+map.get("puid")+"') a, ");
	    	sql.append(" (SELECT jd.PRJ_POINTS_UID, MAX(jd.REPORT_DATE) REPORT_DATE_MAX FROM jc_jc_data jd GROUP BY jd.PRJ_POINTS_UID) b ");
	    	sql.append(" WHERE a.PRJ_POINTS_UID = b.PRJ_POINTS_UID AND a.REPORT_DATE = b.REPORT_DATE_MAX ");
	       list =  DBUtil.queryReturnList(conn,sql.toString());
	       
	    } catch (Exception e) {
	        DaoException.handleMessageException("*********查询沉降测点最新数据出错!*********");
	    } finally {
	        DBUtil.closeConnetion(conn);
	    }
	    return list;
	
	}
	
	/**
	 * 
	 */
	public List<Map<String, String>> queryPointData_cx(HashMap<String, String> map) {
	    Connection conn = DBUtil.getConnection();
	    List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	    try {
	    	StringBuffer sql = new StringBuffer();
	    	sql.append(" SELECT a.* FROM ");
	    	sql.append(" (SELECT jd.* FROM jc_cx_data jd LEFT JOIN jc_prj_points jp ON jd.PRJ_POINTS_UID = jp.PRJ_POINTS_UID WHERE jp.PROJECT_UID = '"+map.get("puid")+"') a, ");
	    	sql.append(" (SELECT jd.PRJ_POINTS_UID, MAX(jd.REPORT_DATE) REPORT_DATE_MAX FROM jc_cx_data jd GROUP BY jd.PRJ_POINTS_UID) b ");
	    	sql.append(" WHERE a.PRJ_POINTS_UID = b.PRJ_POINTS_UID AND a.REPORT_DATE = b.REPORT_DATE_MAX ");
	    	sql.append(" ORDER BY PRJ_POINTS_UID, DEEP ");
	       list =  DBUtil.queryReturnList(conn,sql.toString());
	       
	    } catch (Exception e) {
	        DaoException.handleMessageException("*********查询测斜测点最新数据出错!*********");
	    } finally {
	        DBUtil.closeConnetion(conn);
	    }
	    return list;
	
	}

	public String queryById(String pRJPOINTSUID) {
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
            sql.append(" SELECT * FROM jc_prj_points where PRJ_POINTS_UID = '"+pRJPOINTSUID+"'");
            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);
           

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}
	
	/**
	 * 
	 */
	public List<Map<String, String>> queryNewestDataByPointId_jc(HashMap<String, String> map) {
	    Connection conn = DBUtil.getConnection();
	    List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	    try {
	    	StringBuffer sql = new StringBuffer();
	    	sql.append(" SELECT * FROM jc_jc_data d ");
	    	sql.append(" WHERE d.PRJ_POINTS_UID = '"+map.get("PRJ_POINTS_UID")+"' ");
	    	sql.append(" AND d.REPORT_DATE = (SELECT MAX(REPORT_DATE) FROM jc_jc_data AS d1 WHERE d1.PRJ_POINTS_UID = '"+map.get("PRJ_POINTS_UID")+"') ");
	       list =  DBUtil.queryReturnList(conn,sql.toString());
	       
	    } catch (Exception e) {
	        DaoException.handleMessageException("*********查询沉降测点最新数据出错!*********");
	    } finally {
	        DBUtil.closeConnetion(conn);
	    }
	    return list;
	
	}
	
	/**
	 * 
	 */
	public List<Map<String, String>> queryNewestDataByPointId_cx(HashMap<String, String> map) {
	    Connection conn = DBUtil.getConnection();
	    List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	    try {
	    	StringBuffer sql = new StringBuffer();
	    	sql.append(" SELECT * FROM jc_cx_data d ");
	    	sql.append(" WHERE d.PRJ_POINTS_UID = '"+map.get("PRJ_POINTS_UID")+"' ");
	    	sql.append(" AND d.REPORT_DATE = (SELECT MAX(REPORT_DATE) FROM jc_cx_data AS d1 WHERE d1.PRJ_POINTS_UID = '"+map.get("PRJ_POINTS_UID")+"') ");
	    	sql.append(" ORDER BY d.DEEP ");
	       list =  DBUtil.queryReturnList(conn,sql.toString());
	       
	    } catch (Exception e) {
	        DaoException.handleMessageException("*********查询测斜测点最新数据出错!*********");
	    } finally {
	        DBUtil.closeConnetion(conn);
	    }
	    return list;
	
	}
    
	/**
	 * 
	 */
	public List<Map<String, String>> queryUser_Focus_Point(HashMap<String, String> map) {
	    Connection conn = DBUtil.getConnection();
	    List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	    try {
	    	StringBuffer sql = new StringBuffer();
	    	sql.append(" SELECT * FROM jc_user_focus_point ufp ");
	    	sql.append(" WHERE ufp.USER_UID = '"+map.get("USER_UID")+"' ");
	       list =  DBUtil.queryReturnList(conn,sql.toString());
	       
	    } catch (Exception e) {
	        DaoException.handleMessageException("*********查询用户关注点数据出错!*********");
	    } finally {
	        DBUtil.closeConnetion(conn);
	    }
	    return list;
	
	}
	
	// 在此可加入其它方法

}
