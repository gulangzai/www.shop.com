/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.pmxianchang.PmXianchangDafuDao.java
 * 创建日期： 2016-01-27 下午 06:45:28
 * 功能：   现场状况答复
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-01-27 下午 06:45:28  卢红冈   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.pmxianchang.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.pmxianchang.dao.PmXianchangDafuDao;
import com.ccthanking.business.pmxianchang.vo.PmXianchangDafuVO;
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
 * <p> PmXianchangDafuDao.java </p>
 * <p> 功能：现场状况答复 </p>
 *
 * <p><a href="PmXianchangDafuDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggang@kzcpm.com ">卢红冈</a>
 * @version 0.1
 * @since 2016-01-27
 * 
 */

@Component
public class PmXianchangDafuDaoImpl  extends BsBaseDaoTJdbc implements PmXianchangDafuDao {

    public String queryCondition(String json, PmXianchangDafuVO vo, Map map){
    
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

            String sql = "SELECT * FROM " + "PM_XIANCHANG_DAFU t";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
          

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
      /**将现场 答复的所有内容查询出来**/
	public Map<String, List<?>> queryDetails(String xcUid,String fileType) {
		    Connection conn = DBUtil.getConnection();
		    User user = ActionContext.getCurrentUserInThread();
	        List<Map<String, String>> allAnswer = new ArrayList<Map<String,String>>();
	        List<Map<String, String>> userxx = new ArrayList<Map<String,String>>();
	        Map<String, List<?>> map = new HashMap<String, List<?>>();
	       
	        try {

	            StringBuffer sql = new StringBuffer();
	            /*sql.append(" SELECT df.XIANCHANG_DAFU_UID,df.XUHAO,df.DAFU_TYPE,df.CONTENT,df.CLOSE_Y,u.USER_NAME,u.USER_UID, ");
	            sql.append(" DATE_FORMAT(df.UPDATE_DATE, '%Y-%c-%d %h:%i') AS UPDATE_DATE,a.FILE_EXT_NAME,a.FILE_PATH AS IMG_PATH,f.TARGET_UID,a.FILE_NAME,");
	            sql.append(" DATE_FORMAT(df.CREATE_DATE, '%Y-%c-%d %h:%i') AS CREATE_DATE");
	            sql.append(" FROM pm_xianchang_dafu AS df LEFT JOIN sys_user u  ON df.CREATE_USER = u.USER_UID");
	            sql.append(" LEFT JOIN bu_file f ON f.TARGET_UID = u.USER_UID ");
	            sql.append(" LEFT JOIN bu_attachment a ON a.ATTACHMENT_UID = f.ATTACHMENT_UID ");
	            sql.append(" where df.XIANCHANG_UID = '"+xcUid+"' AND f.FILE_TYPE='10008' ORDER BY f.CREATE_DATE DESC LIMIT 1 ");
	            */
				sql.append(" SELECT df.XIANCHANG_DAFU_UID,df.XUHAO,df.DAFU_TYPE,df.CONTENT,df.CLOSE_Y,u.USER_NAME,u.USER_UID, DATE_FORMAT(df.UPDATE_DATE,'%Y-%c-%d %h:%i') AS UPDATE_DATE, ");
				sql.append("  DATE_FORMAT(df.CREATE_DATE, '%Y-%m-%d %k:%i') AS CREATE_DATE,  ");
				sql.append(" (SELECT a.FILE_PATH FROM bu_attachment a  ");
				sql.append(" LEFT JOIN bu_file f  ");
				sql.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID  ");
				sql.append(" WHERE f.FILE_TYPE = '10008'  ");
				sql.append(" AND df.CREATE_USER = f.TARGET_UID  ");
				sql.append(" ORDER BY f.CREATE_DATE DESC LIMIT 1)as IMG_PATH ");
				sql.append("  FROM pm_xianchang_dafu AS df LEFT JOIN sys_user u  ON df.CREATE_USER = u.USER_UID  ");

				sql.append(" where df.XIANCHANG_UID = '" + xcUid + "'   ");
	            allAnswer = DBUtil.queryReturnList(conn, sql.toString());
	           
	            
	            List<Map<String, String>> allFileList =new ArrayList<Map<String,String>>();
	            List<String> userlist = new ArrayList<String>();
	            //当前  回复的 附件分类型
	            if(allAnswer.size() !=0){
	            	for(int i=0;i<allAnswer.size();i++){
	            		Map rmap = (Map)allAnswer.get(i);
	                    StringBuffer sql2 = new StringBuffer();
	                    sql2.append(" SELECT a.FILE_EXT_NAME,a.FILE_PATH,f.TARGET_UID,a.FILE_NAME  ");
	                    sql2.append(" FROM bu_attachment a LEFT JOIN bu_file f ");
	                    sql2.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID ");
	                    sql2.append(" WHERE f.FILE_TYPE = '"+fileType+"' AND f.ENABLED = '1' AND f.TARGET_UID = '"+rmap.get("XIANCHANG_DAFU_UID")+"'  ");
	                    
	                    allFileList = DBUtil.queryReturnList(conn, sql2.toString());
	                    rmap.put("allFileList", allFileList);//所有的现场状况回复附件
	                    
	                 
	            	}
	            }
	            
	            //** 有过回复的用户 的头像  查询出来是一个list*//  
	           /* StringBuffer sql3 = new StringBuffer();
                sql3.append(" SELECT a.FILE_EXT_NAME,a.FILE_PATH AS IMG_PATH,f.TARGET_UID,a.FILE_NAME ");
                sql3.append(" FROM bu_attachment a LEFT JOIN bu_file f ");
                sql3.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID ");
                sql3.append(" LEFT JOIN pm_xianchang_dafu df ON df.CREATE_USER = f.TARGET_UID ");
                sql3.append(" WHERE f.FILE_TYPE = '10008' AND f.ENABLED = '1' AND df.XIANCHANG_UID = '"+xcUid+"' ");
                userxx = DBUtil.queryReturnList(conn, sql3.toString());*/
                
      		
	            //map.put("userxx", userxx);
	            map.put("allAnswer", allAnswer);
	            //map.put("allFileList", allFileList);
	        } catch (Exception e) {
	        	e.printStackTrace();
	            DaoException.handleMessageException("*********查询现场状况回复的内容 出错!*********");
	        } finally {
	            DBUtil.closeConnetion(conn);
	        }
	        
	        return map;
		
	}
	
	 /** 查询当前 最新的现场状况信息*/
	public Map<String, List<?>>queryNewAnswer(String xcUid,String fileType) {
		    Connection conn = DBUtil.getConnection();
		    Map<String, List<?>> map = new HashMap<String, List<?>>();
	        List<Map<String, String>> list = new ArrayList<Map<String,String>>();
	        try {

	        	//查询的是最新的回复消息
	            StringBuffer sql = new StringBuffer();
	            sql.append(" SELECT df.XIANCHANG_DAFU_UID,df.CONTENT,df.XIANCHANG_UID,df.DAFU_TYPE, u.USER_NAME,u.USER_UID,");
	            sql.append(" DATE_FORMAT(df.CREATE_DATE, '%Y-%c-%d %h:%i') AS CREATE_DATE, ");
	            sql.append(" DATE_FORMAT(df.UPDATE_DATE, '%Y-%c-%d %h:%i') AS UPDATE_DATE ");
	            sql.append(" FROM  pm_xianchang_dafu as df LEFT JOIN sys_user u  ON df.CREATE_USER = u.USER_UID");
	            sql.append(" WHERE  df.XIANCHANG_UID = '"+xcUid+"'");
	            sql.append(" AND df.CREATE_DATE = (select MAX(d.CREATE_DATE) FROM pm_xianchang_dafu d where d.XIANCHANG_UID = '"+xcUid+"')");
	            list = DBUtil.queryReturnList(conn, sql.toString()); 
	            
	            
	            List<Map<String, String>> queryFileList =new ArrayList<Map<String,String>>();
	            List<Map<String, String>> userxx = new ArrayList<Map<String,String>>();
	            
	            //当前  回复的 附件信息 分类型
	            if(list.size() !=0){
	            	for(int i=0;i<list.size();i++){
	            		Map rmap = (Map)list.get(i);
	                    StringBuffer sql2 = new StringBuffer();
	                    sql2.append(" SELECT a.FILE_EXT_NAME,a.FILE_PATH,f.TARGET_UID,a.FILE_NAME  ");
	                    sql2.append(" FROM bu_attachment a LEFT JOIN bu_file f ");
	                    sql2.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID ");
	                    sql2.append(" WHERE f.FILE_TYPE = '"+fileType+"' AND f.ENABLED = '1' AND f.TARGET_UID = '"+rmap.get("XIANCHANG_DAFU_UID")+"'  ");
	                    
	                    queryFileList = DBUtil.queryReturnList(conn, sql2.toString());
	                   
	                  
	            	}
	            }
	            
	            /** 当前用户的头像 */
	            Map mapu = (Map)list.get(0);
	            StringBuffer sql3 = new StringBuffer();
                sql3.append(" SELECT a.FILE_EXT_NAME,a.FILE_PATH as IMG_PATH,f.TARGET_UID,a.FILE_NAME  ");
                sql3.append(" FROM bu_attachment a LEFT JOIN bu_file f ");
                sql3.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID ");
                sql3.append(" WHERE f.FILE_TYPE = '10008' AND f.ENABLED = '1' AND f.TARGET_UID = '"+mapu.get("USER_UID")+"' ORDER BY f.CREATE_DATE DESC LIMIT 1  ");
                userxx = DBUtil.queryReturnList(conn, sql3.toString());
                
                map.put("userxx", userxx);
	            map.put("listxx", list);
	            map.put("queryFileList", queryFileList);
	            
	        } catch (Exception e) {
	        	e.printStackTrace();
	            DaoException.handleMessageException("*********查询现场最新的回复信息出错!*********");
	        } finally {
	            DBUtil.closeConnetion(conn);
	        }
	        
	        return map;
	    }

	
	   // 在此可加入其它方法
	
	}
