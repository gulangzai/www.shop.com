/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.pmxianchang.PmXianchangDao.java
 * 创建日期： 2016-01-22 上午 10:45:08
 * 功能：   进度质量安全
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-01-22 上午 10:45:08  卢红冈   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.pmxianchang.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.ccthanking.business.pmxianchang.dao.PmXianchangDao;
import com.ccthanking.business.pmxianchang.vo.PmXianchangVO;
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
 * <p> PmXianchangDao.java </p>
 * <p> 功能：进度质量安全 </p>
 *
 * <p><a href="PmXianchangDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggang@kzcpm.com ">卢红冈</a>
 * @version 0.1
 * @since 2016-01-22
 * 
 */

@Component
public class PmXianchangDaoImpl  extends BsBaseDaoTJdbc implements PmXianchangDao {

    public String queryCondition(String json, PmXianchangVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition += BusinessUtil.getCommonCondition(user, null);
            condition += " GROUP BY xc.XIANCHANG_UID" +orderFilter;
            /** 判断 选择的复选框的值  双选忽略并去除STATUS**/
            int s = condition.indexOf("STATUS");
            /**判断 文件的类型 质量 10010 进度 10009 安全 10011**/
            String file_type = "";
            String type=condition.substring(condition.indexOf("=",20)+2,48);
            if(type.indexOf("JD") != -1){
            	file_type = "10009";
            }else if(type.indexOf("ZL") != -1){
            	file_type = "10010";
            }else{
            	file_type = "10011";
            }
           
            if(s!=-1){
            String con =condition.substring(s,67);
            if(con.indexOf("1") !=-1 && con.indexOf("0") !=-1){
            	 String condition1 = condition.substring(68);
            	 condition = condition.substring(0,49);
            	 condition += condition1;
            }
            }
          
            
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            //count(an.XIANCHANG_UID) 
            String sql =" SELECT xc.*,u.USER_NAME,p.PROJECT_NAME,COUNT(an.XIANCHANG_UID) AS SUMANSWER,xs.XIANCHANG_STRUC_UID," +
                        " (SELECT MAX(f.TARGET_UID) FROM bu_file f   WHERE xc.XIANCHANG_UID = f.TARGET_UID and f.ENABLED='1' and FILE_TYPE='"+file_type+"' ) AS TARGET_UID,"+
            		    " (select u.USER_NAME  from SYS_USER u where u.USER_UID = an.CREATE_USER ) AS AN_PERSON," +
            		    " (select MAX(an.UPDATE_DATE)  from pm_xianchang_dafu an where an.CREATE_USER = u.USER_UID ) AS AN_DATE" +
            		    " FROM PM_XIANCHANG xc "+
                        " LEFT JOIN pm_xianchang_dafu an ON an.XIANCHANG_UID = xc.XIANCHANG_UID "+
                        " LEFT JOIN pm_xianchang_struc xs ON xc.XIANCHANG_UID = xs.XIANCHANG_UID "+
	            		" LEFT JOIN BU_PROJECT p ON xc.PROJECT_UID = p.PROJECT_UID "+
	                    " LEFT JOIN SYS_USER u ON xc.CREATE_USER  = u.USER_UID" ;
	                   
            BaseResultSet bs = DBUtil.query(conn, sql, page);
        
            // 设置查询条件
            //DATE_FORMAT(MAX(an.UPDATE_DATE),'%Y-%c-%d\t%h:%i') 也可
            bs.setFieldDateFormat("AN_DATE", "yyyy-MM-dd\tHH:mm");// 计量月份
            bs.setFieldDateFormat("CREATE_DATE","yyyy-MM-dd\tHH:mm");
            //获取当前登录用户的id
            String  uid = user.getIdCard();
          
            domresult = bs.getJson();
            //没有数据不处理
            if(!"0".equals(domresult)){
                JSONObject response = new JSONObject(domresult).getJSONObject("response");
                JSONArray array = response.getJSONArray("data");
                JSONObject pages = new JSONObject(domresult).getJSONObject("pages");
                for (int i = 0; i < array.length(); i++) {
                	JSONObject obj = (JSONObject) array.get(i);
    				//System.out.println(obj.getString("XIANCHANG_UID"));
                	//判断其他用户的草稿不显示
                	if(!obj.getString("CREATE_USER").equals(uid)&&obj.getString("STATUS").equals("-1")){
                	    array.remove(i);
                  	 }
                	 
                	        String sql1 = "SELECT a.FILE_PATH,f.DESCRIPTION "+
    		                " FROM bu_attachment a LEFT JOIN bu_file f "+
    		                " ON a.ATTACHMENT_UID = f.ATTACHMENT_UID "+
    		               " WHERE f.FILE_TYPE = '10009' AND f.ENABLED = '1' AND f.TARGET_UID = '"+obj.getString("XIANCHANG_UID")+"' ";
                  
                	 List<Map<String ,String >> list = DBUtil.queryReturnList(conn, sql1);
    				obj.put("list", list);
                    
    			}
                response.put("data", array);
                JSONObject o = new JSONObject();
                o.put("response", response);
                o.put("pages", pages);
                domresult = o.toString();
            }

    
        } catch (Exception e) {
        	e.printStackTrace();
            DaoException.handleMessageException("*********现场状况信息查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }
    
    /** 查询出 当前数据的 所有的信息 包含 用户信息 现场状况的信息  结构信息 **/
	public Map<String, List<?>> queryXcQK(String xcUid,String fileType) {
		    Connection conn = DBUtil.getConnection();
	        List<Map<String, String>> listxx = new ArrayList<Map<String,String>>();
	        List<Map<String, String>> allFile = new ArrayList<Map<String,String>>();
	        List<Map<String, String>> userPic = new ArrayList<Map<String,String>>();
	        Map<String, List<?>> map = new HashMap<String, List<?>>();
	        try {
	            StringBuffer sql = new StringBuffer();
	            sql.append(" SELECT`xc`.DESCRIPTION,`xc`.JINDU,`xc`.SERIOUSNESS,`xc`.FINISH_DATE,`xc`.`STATUS` as STATUS ,`xc`.ENABLED,`xc`.CREATE_DATE,`xc`.CREATE_USER,sc.STRUC_NAME,sc.STRUC_TYPE,sc.PRJ_STRUC_UID, ");
	            sql.append(" DATE_FORMAT(`xc`.CLOSE_DATE,'%Y-%m-%d') AS CLOSE_DATE,DATE_FORMAT(`xc`.CREATE_DATE, '%Y-%c-%d %h:%i') AS FB_DATE,u.USER_NAME,u.USER_UID ");
	            sql.append(" FROM PM_XIANCHANG  AS `xc` LEFT JOIN PM_XIANCHANG_STRUC xs ON xc.XIANCHANG_UID = xs.XIANCHANG_UID ");
	            sql.append( "LEFT JOIN PM_PRJ_STRUC sc ON xs.PRJ_STRUC_UID = sc.PRJ_STRUC_UID  ");
	            sql.append(" LEFT JOIN sys_user u  ON xc.CREATE_USER = u.USER_UID");
	            sql.append(" where xc.XIANCHANG_UID = '"+xcUid+"'");
	            listxx = DBUtil.queryReturnList(conn, sql.toString());

	            
	            /**查询发布者 的头像**/
	            if(listxx.size() != 0){
	            		Map rmap = (Map)listxx.get(0);
	                    StringBuffer sql3 = new StringBuffer();
	                    sql3.append(" SELECT a.FILE_EXT_NAME,a.FILE_PATH as IMG_PATH,f.TARGET_UID,a.FILE_NAME  ");
	                    sql3.append(" FROM bu_attachment a LEFT JOIN bu_file f ");
	                    sql3.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID ");
	                    sql3.append(" WHERE f.FILE_TYPE = '10008' AND f.ENABLED = '1' AND f.TARGET_UID = '"+rmap.get("USER_UID")+"' ORDER BY f.CREATE_DATE DESC LIMIT 1 ");
	                    
	                    userPic = DBUtil.queryReturnList(conn, sql3.toString());
	            	
	            }
	            
	            
			            /** 查询当前现场状况的  附件信息 **/
			            StringBuffer sql2 = new StringBuffer();
			            sql2.append(" SELECT a.FILE_EXT_NAME,a.FILE_PATH,f.TARGET_UID,a.FILE_NAME  ");
		                sql2.append(" FROM bu_attachment a LEFT JOIN bu_file f ");
		                sql2.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID ");
		                sql2.append(" WHERE f.FILE_TYPE = '"+fileType+"' AND f.ENABLED = '1' AND f.TARGET_UID = '"+xcUid+"' ");
		                allFile = DBUtil.queryReturnList(conn, sql2.toString());
                
		            map.put("userPic",userPic);
		            map.put("allFile",allFile);
		            map.put("listxx",listxx);
                
	        } catch (Exception e) {
	        	e.printStackTrace();
	            DaoException.handleMessageException("*********查询现场状况信息(用户信息及现场发布信息)出错!*********");
	        } finally {
	            DBUtil.closeConnetion(conn);
	        }
	        
	        return map;
		
	}


}
