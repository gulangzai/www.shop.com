/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.pmbzjc.PmBzjcDao.java
 * 创建日期： 2016-05-11 上午 09:12:48
 * 功能：   项目标准检查
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-05-11 上午 09:12:48  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.pmbzjc.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.ccthanking.business.pmbzjc.dao.PmBzjcDao;
import com.ccthanking.business.pmbzjc.vo.PmBzjcVO;
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
 * <p> PmBzjcDao.java </p>
 * <p> 功能：项目标准检查 </p>
 *
 * <p><a href="PmBzjcDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-05-11
 * 
 */

@Component
public class PmBzjcDaoImpl  extends BsBaseDaoTJdbc implements PmBzjcDao {

	public String queryTreeCondition(String json,Map map) {
		String resultjson  = "{\"response\":";

        JSONArray list = new JSONArray(); 
		User user = ActionContext.getCurrentUserInThread();   	
		List<Map<String, String>> data= new ArrayList<Map<String,String>>();
    	Connection conn = null;
		 try {
			    // 组织查询条件
	            PageManager page = RequestUtil.getPageManager(json);
	            System.out.println("currentPage"+page.getCurrentPage()+"pageRows"+page.getPageRows());
			 	String status=(String)map.get("STATUS");
			 	String jc_type=(String)map.get("JC_TYPE");
			 	
			 	//String sql="select (select a.projects_uid from pm_bzjc a where a.P_BZJC_UID = v.BZJC_UID and v.JC_TYPE='FX' ) XMFQ_UID,v.* from pm_bzjc v where 1=1 ";//只显示通过
			    String sql="select v.* from pm_bzjc v where 1=1 AND  v.JC_TYPE='FX'";//只显示通过
              
	            conn = DBUtil.getConnection(); 
	            data=  DBUtil.queryReturnList(conn, "{call getPmBzjcTree(0,\""+jc_type+"\")}");
	            //getList(conn,data,0);
	          
	            Map mapj =null; //(无分期)项目XMFQ_Y为0    子分期项目map数据
	    		if(data!=null&&data.size()>0){
	    			for(int i=0;i<data.size();i++){
	    				Map maptemp=(Map)data.get(i);
	    				JSONObject obj1 = new JSONObject();			 
	    				//String wwc=(String)maptemp.get("WWC");
	    				//String ywc=(String)maptemp.get("YWC");
	    				String parent=(String)maptemp.get("P_BZJC_UID");
	    				if("".equals(parent)){	//父节点 
	    					obj1.put("isLeaf", false);//有子节点
	    					obj1.put("expanded", true);   
	    				}else{
	    					obj1.put("isLeaf", true);//无子节点
	    					obj1.put("expanded", true);
	    				}								
	    				
	    				obj1.put("loaded", true); 
	    				obj1.put("BZJC_UID", maptemp.get("BZJC_UID"));
	    		        obj1.put("id", maptemp.get("BZJC_UID"));    	
	    		        obj1.put("level", maptemp.get("LEVEL"));
	    		        obj1.put("parent", maptemp.get("P_BZJC_UID")); 
//	    		        obj1.put("PARENT", maptemp.get("PARENT"));
	    		        obj1.put("PROJECT_UID", maptemp.get("PROJECT_UID"));
	    		        obj1.put("XUHAO", maptemp.get("XUHAO"));
	    		        obj1.put("JC_TYPE", maptemp.get("JC_TYPE"));
	    		        obj1.put("BZJC_NAME", maptemp.get("BZJC_NAME"));
	    		        obj1.put("PLAN_JC_DATE", maptemp.get("PLAN_JC_DATE"));
	    		        obj1.put("JC_ZHIBIAO", maptemp.get("JC_ZHIBIAO"));
	    		        obj1.put("JC_SSRY", maptemp.get("JC_SSRY"));
	    		        obj1.put("JC_BIAOZHUN", maptemp.get("JC_BIAOZHUN"));
	    		        obj1.put("JC_DZMS", maptemp.get("JC_DZMS"));
	    		        obj1.put("JC_CHENGGUO", maptemp.get("JC_CHENGGUO"));	
	    		        obj1.put("DESCRIPTION", maptemp.get("DESCRIPTION"));
	    		        obj1.put("JC_END_DATE", maptemp.get("JC_END_DATE"));
	    		        obj1.put("JC_RESULT", maptemp.get("JC_RESULT"));
	    		        obj1.put("JC_STATUS", maptemp.get("JC_STATUS"));
	    		        obj1.put("CREATE_DATE", maptemp.get("CREATE_DATE"));
	    		        obj1.put("CREATE_USER", maptemp.get("CREATE_USER"));
	    		        obj1.put("UPDATE_DATE", maptemp.get("UPDATE_DATE"));
	    		        obj1.put("UPDATE_USER", maptemp.get("UPDATE_USER")); 
	    			    list.add(obj1); 
	    			}
	    		} 
	    		if (page == null) {
	    			page = new PageManager();
	    			page.setPageRows(100);
	    		}
	    	 String strSqlCount="select count(*) from pm_bzjc v where 1=1 AND  v.JC_TYPE='FX' and P_BZJC_UID is null ";
		        
	    	 int  iCountRows = 0;
	    	 PreparedStatement	psCount = conn.prepareStatement(strSqlCount);
	    	 ResultSet	rsCount = psCount.executeQuery();
				if (rsCount.next()) {
					iCountRows = rsCount.getInt(1);
				} 
	    		page.setCountRows(iCountRows);
	    		int cp = page.getCurrentPage();
	    		int pr = page.getPageRows();
	    		if (pr <= 0)
	    			pr = 100;
	    		int countP = page.getCountPage(); 
	    		if (countP <= 0) { 
	    			if (iCountRows > 0) {
	    				countP = iCountRows / pr;
	    				int mod = iCountRows % pr;
	    				if (mod > 0)
	    					countP++;
	    				if (cp > countP)
	    					cp = countP;
	    				if (cp < 1)
	    					cp = 1;
	    			} 
	    		}
	    		page.setCurrentPage(cp);
	    		page.setCountPage(countP);
	    		page.setCountRows(iCountRows);
	    		page.setPageRows(pr);
	    	
    	        resultjson +="{\"data\":";
    	        resultjson += list.toString();
    	        resultjson += "},"; 
    	        resultjson +="pages:{";
    	        resultjson +="currentpagenum:"+String.valueOf(page.getCurrentPage())+",";
    	        resultjson +="recordsperpage:"+String.valueOf(page.getPageRows())+",";
    	        resultjson +="totalpage:"+String.valueOf(page.getCountPage())+",";
    	        resultjson +="countrows:"+String.valueOf(page.getCountRows())+"}}";
    	         
	            
	        } catch (Exception e) {
	        	e.printStackTrace();
	            DaoException.handleMessageException("*********查询出错!*********");
	        } finally {
	            DBUtil.closeConnetion(conn);
	        }	
		return list.toString();
	}
	
	public void getList(Connection conn,List<Map> data,int id){ 
		List list;
		String sql = "select v.* from pm_bzjc v where 1=1 AND  v.JC_TYPE='FX' AND P_BZJC_UID="+id; 
    	if(id==0){
           sql  = "select v.* from pm_bzjc v where 1=1 AND  v.JC_TYPE='FX' AND P_BZJC_UID is NULL"; 
        } 
		try {
			list = DBUtil.queryReturnList(conn, sql);
			for (Object object : list) {
            	Map map = (Map) object;
            	data.add(map);
				getList(conn,data,Integer.parseInt((String) map.get("BZJC_UID")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
    public String queryCondition(String json, PmBzjcVO vo, Map map){
    
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

            String sql = "SELECT * FROM " + "PM_BZJC t";
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
 
	public String queryNewContent(String bzjc_uid, PmBzjcVO vo, Object object) {
		// TODO Auto-generated method stub
		User user = ActionContext.getCurrentUserInThread(); 
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try { 
            // 组织查询条件 
            String  sql = "select  * from pm_bzjc pb where  pb.BZJC_UID='"+bzjc_uid+"' AND pb.PROJECT_UID='"+vo.getProject_uid()+"'";
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
