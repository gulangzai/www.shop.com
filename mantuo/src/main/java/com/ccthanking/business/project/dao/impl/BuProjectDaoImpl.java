/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.project.BuProjectDao.java
 * 创建日期： 2015-10-17 上午 08:22:57
 * 功能：   项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-17 上午 08:22:57  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.project.dao.impl;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.ccthanking.business.project.dao.BuProjectDao;
import com.ccthanking.business.project.vo.BuProjectVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.Mytimerservice;
import com.ccthanking.framework.util.RequestUtil;
import com.ccthanking.framework.util.getNongliWeather;
import com.copj.modules.utils.exception.DaoException;
import com.github.abel533.echarts.Label;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.SeriesType;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.code.Y;
import com.github.abel533.echarts.data.Data;
import com.github.abel533.echarts.data.PieData;
import com.github.abel533.echarts.series.Pie;
import com.github.abel533.echarts.style.ItemStyle;


/**
 * <p> BuProjectDao.java </p>
 * <p> 功能：项目 </p>
 *
 * <p><a href="BuProjectDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-17
 * 
 */

@Component
public class BuProjectDaoImpl  extends BsBaseDaoTJdbc implements BuProjectDao {

    public String queryCondition(String json, BuProjectVO vo, Map map){
    
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

            StringBuffer sql  = new StringBuffer();
            sql.append(" SELECT bp.*,bpu.PROJECT_USER_UID FROM bu_project bp ");
            sql.append(" LEFT JOIN bu_project_user bpu ");
            sql.append(" ON bp.PROJECT_UID = bpu.PROJECT_UID ");
            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);
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

    //查询单个项目
	public String queryProject(String projectUid) {
        Connection conn = DBUtil.getConnection();
        String projectName = "";
        String projectUid_ = "";
        String projectUserId = "";
        String rs = "";
        try {

            String sql = "SELECT PROJECT_NAME,PROJECT_UID FROM " + "BU_PROJECT t where t.PROJECT_UID = '"+projectUid+"'";
            
            String[][] bs = DBUtil.querySql(conn, sql);
            if(bs != null && bs.length!=0){
            	projectName = bs[0][0];
            	projectUid_ = bs[0][1];
            }
            JSONObject obj = new JSONObject();
            obj.put("projectName", projectName);
            obj.put("projectUid", projectUid_);
            rs = obj.toString();
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        
        return rs;
	}

	//查询项目列表
	public List<?> queryProjectList(String userUid,String projectName) {
        Connection conn = DBUtil.getConnection();
        List<?> list = new ArrayList();
        try {

            StringBuffer sql  = new StringBuffer();
            sql.append(" SELECT bp.*,bpu.PROJECT_USER_UID FROM bu_project bp ");
            sql.append(" LEFT JOIN bu_project_user bpu ");
            sql.append(" ON bp.PROJECT_UID = bpu.PROJECT_UID ");
            //sql.append(" LEFT JOIN file_view fv ");
            //sql.append(" ON bp.PROJECT_UID = fv.TARGET_UID");
            //sql.append(" WHERE fv.TARGET_TABLE = 'BU_PROJECT'");
            sql.append(" WHERE bpu.USER_UID = '"+userUid+"'");
            if(null!=projectName&&!"".equals(projectName)){
            	sql.append(" AND bp.PROJECT_NAME LIKE '%"+projectName+"%'");
            }
            list = DBUtil.queryReturnList(conn, sql.toString());

        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        
        return list;
	}

	public List<?> queryProjectList(String projectUid) {
        Connection conn = DBUtil.getConnection();
        List<?> list = new ArrayList();
        try {

            StringBuffer sql  = new StringBuffer();
            sql.append(" SELECT bp.* FROM bu_project bp ");
            //sql.append(" LEFT JOIN bu_project_user bpu ");
            //sql.append(" ON bp.PROJECT_UID = bpu.PROJECT_UID ");
           // sql.append(" LEFT JOIN file_view fv ");
           // sql.append(" ON bp.PROJECT_UID = fv.TARGET_UID");
            //sql.append(" WHERE fv.TARGET_TABLE = 'BU_PROJECT'");
            sql.append(" WHERE bp.PROJECT_UID = '"+projectUid+"'");
            list = DBUtil.queryReturnList(conn, sql.toString());

        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        
        return list;
	}
  /***
   * 项目详细信息查询 
   */
	public List<?> queryDetailById(String projectUid) {
		Connection conn = DBUtil.getConnection();
        List<?> list = new ArrayList();
        try {
            StringBuffer sql  = new StringBuffer();
            sql.append(" SELECT bp.*,u.USER_NAME FROM bu_project bp ");
            sql.append(" LEFT JOIN sys_user u  ON bp.CREATED_USER = u.USER_UID ");
            sql.append(" WHERE bp.PROJECT_UID = '"+projectUid+"'");
            list = DBUtil.queryReturnList(conn, sql.toString());

        } catch (Exception e) {
            DaoException.handleMessageException("*********查询项目详细信息出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        
        return list;
	}

public List<?> queryCity(String msg) {
	Connection conn = DBUtil.getConnection();
    List<?> list = new ArrayList();
    try {
        StringBuffer sql  = new StringBuffer();
        sql.append(" SELECT s.* FROM sys_region AS s ");
       // sql.append(" LEFT JOIN sys_user u  ON bp.CREATED_USER = u.USER_UID ");
        sql.append(" WHERE s.P_REGION_CODE IS null");
        list = DBUtil.queryReturnList(conn, sql.toString());

    } catch (Exception e) {
        DaoException.handleMessageException("*********查询省出错!*********");
    } finally {
        DBUtil.closeConnetion(conn);
    }
    
    return list;
}
    // 在此可加入其它方法

public List<?> queryCityorControyBycode(String code) {
	Connection conn = DBUtil.getConnection();
    List<?> list = new ArrayList();
    try {
        StringBuffer sql  = new StringBuffer();
        sql.append(" SELECT s.* FROM sys_region AS s ");
        sql.append(" WHERE s.P_REGION_CODE ="+code+" ");
        //sql.append(" AND s.REGION_CODE ="+code+"");
        list = DBUtil.queryReturnList(conn, sql.toString());

    } catch (Exception e) {
        DaoException.handleMessageException("*********查询市区(县)出错!*********");
    } finally {
        DBUtil.closeConnetion(conn);
    }
    
    return list;
}

/**
 * 查询最新的 天气信息 
 */
public List<?> queryWeather(String projectUid) {

	Connection conn = DBUtil.getConnection();
    List<?> list = new ArrayList();
    
    try {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String sysDate = sdf.format(new Date());
    	String sysTime = sysDate.replaceAll(" ","");  
    	StringBuffer sql  = new StringBuffer();
        sql.append(" SELECT MAX(w.REPORT_DATE) W_DATE,w.* FROM SYS_WEATHER w WHERE w.CITY_CODE = ");
        sql.append(" (SELECT DISTINCT s.WEATHER_CODE FROM bu_project AS p   ");
        sql.append(" left join sys_region AS s on p.DISTRICT = s.REGION_CODE ");
        sql.append(" WHERE p.PROJECT_UID = '"+projectUid+"'  )  and w.GET_TIME ='"+sysTime+"'  ORDER BY W.REPORT_DATE DESC LIMIT 0,1 ");
        
        String sqlzt = " SELECT	w.* FROM SYS_WEATHER w WHERE w.CITY_CODE = ( SELECT DISTINCT s.WEATHER_CODE "+
		               " FROM bu_project AS p  LEFT JOIN sys_region AS s ON p.DISTRICT = s.REGION_CODE  "+
		               " WHERE p.PROJECT_UID = '1' ) ORDER BY w.GET_TIME DESC LIMIT 1 ";
        list = DBUtil.queryReturnList(conn, sql.toString());
      
    	Map map  = null;
        if(list.size()!=0){
        	map = (Map) list.get(0);
        	java.util.Iterator it = map.entrySet().iterator();
        	String mapValue = "";
        	while(it.hasNext()){
        		java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
        		Object oo = entry.getKey();//返回与此项对应的键
        		mapValue = (String) entry.getValue();// 返回与此项对应的值
        		
        		}
        	if(mapValue.equals("") || mapValue.equals(null)){//返回为空 说明 今天的天气预报 还没有更新 就获取昨天 的天气预报状况
    			
    	        list = DBUtil.queryReturnList(conn, sqlzt.toString());
    	       
    		}
        	
        }
      
        
    } catch (Exception e) {
        DaoException.handleMessageException("*********查询天气出错!*********");
    } finally {
        DBUtil.closeConnetion(conn);
    }
    
    return list;
   }

/**
 * 查询当前勾选的  工况信息（即是 最新的）  同时查询出当前时间的 农历时间
 */
public String queryGKdetail(String projectUid) {
	 SimpleDateFormat chineseDateFormat = new SimpleDateFormat(
     "yyyy年MM月dd日");
        Connection conn = DBUtil.getConnection();
        String JINDU = "";
        String PROJECT_ADDRESS = "";
        String rs = "";
        try {

            String sql = "SELECT gk.JINDU,p.PROJECT_ADDRESS FROM pm_gongkuang  gk " +
                         " RIGHT JOIN BU_PROJECT p on gk.PROJECT_UID = p.PROJECT_UID " +
            		     " where gk.PROJECT_UID = '"+projectUid+"'  AND gk.CURRENT_Y ='Y' ";
            String[][] bs = DBUtil.querySql(conn, sql);
            if(bs != null && bs.length !=0 ){
            	JINDU = bs[0][0];
            	PROJECT_ADDRESS = bs[0][1];
            	
            }
            Calendar today = Calendar.getInstance();
 	        today.setTime(chineseDateFormat.parse(new  SimpleDateFormat("yyyy年MM月dd日").format(new Date())));
 	        getNongliWeather nldate = new getNongliWeather(today);
             JSONObject obj = new JSONObject();
             obj.put("NLDATE", nldate.toString());
             obj.put("DESCRIPTION", JINDU);
             obj.put("PROJECT_ADDRESS", PROJECT_ADDRESS);
             rs = obj.toString();
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询最新的工况信息出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        
        return rs;
	}

public String queryDataJSON(String msg) {
	User user = ActionContext.getCurrentUserInThread();
    
    Connection conn = DBUtil.getConnection();
    String domresult = "";
    try {

        // 组织查询条件
        PageManager page = RequestUtil.getPageManager(msg);
        String condition = RequestUtil.getConditionList(msg).getConditionWhere();
        String orderFilter = RequestUtil.getOrderFilter(msg);
        condition += orderFilter;
        if (page == null)
            page = new PageManager();
        page.setFilter(condition);

        StringBuffer sql  = new StringBuffer();
        sql.append(" SELECT bp.* ,u.USER_NAME FROM bu_project bp ");
        sql.append(" LEFT JOIN sys_user u ");
        sql.append(" ON bp.CREATED_USER = u.USER_UID ");
        BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);
     
        domresult = bs.getJson();
        
    } catch (Exception e) {
        DaoException.handleMessageException("*********查询出错!*********");
    } finally {
        DBUtil.closeConnetion(conn);
    }
    return domresult;

}

public boolean quertTianQi(String contry) {
		Connection conn = DBUtil.getConnection();
		String weather_code = "";
		boolean flag= false;
    try {
    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	 /**查询的是当前城市当天的天气是否存在于 天气表中 不存在就insert 一条数据 **/
    	 String sql =" select w.* from sys_weather w where w.CITY_CODE = ( "+
					" select s.WEATHER_CODE from sys_region s where s.REGION_CODE = "+contry+ ") AND w.GET_TIME='"+sdf.format(new Date())+"'";
    	 
    	 /** 查询当前城市的 城市代码编号 有可能为空（sys_region表中的城市code不全） */
         String sqlqy = "select s.WEATHER_CODE from sys_region s where s.REGION_CODE = "+contry;
    	 String[][] bs = DBUtil.query(conn, sql.toString());
         if(bs != null && bs.length !=0){//说明存在 不再添加数据
        	 flag = true;
         }else{ //说明天气预报的情况在天气表中不存在 就 insert一条数据
        	 String[][] dataqy = DBUtil.query(conn, sqlqy.toString()); 
        	 if(dataqy != null && dataqy[0][0]!= "" ){
	        	 weather_code = dataqy[0][0];
	        	 String url = "http://apistore.baidu.com/microservice/weather?cityid="+weather_code;
	        	 flag =  Mytimerservice.getReportData(Mytimerservice.request(url));
        	 }else{
        		 flag =false;
        	 }
         }
		
	} catch (Exception e) {
	    e.printStackTrace();
	} finally{
		DBUtil.closeConnetion(conn);
	}
	return flag; 
	
}

/**查询我的关注点**/
public Map<String, Object> queryMyCare(HashMap<String, Object> map) {
	User user = ActionContext.getCurrentUserInThread();
	Connection conn = DBUtil.getConnection();
	List<Map<String, String>> list = new ArrayList<Map<String,String>>();
	Map<String,Object> map1 = new HashMap<String, Object>();
	Option option = new Option();
	String type = "";
	String pointName = "";
	try {
		StringBuffer sql = new StringBuffer();
		sql.append("  SELECT p.PRJ_POINTS_UID,p.POINT_CODE as numCode,i.JC_NAME,i.SHORT_NAME as pointName,count(i.JC_TYPE_UID) as number,i.JC_TYPE_UID,ppi.PRJ_POINT_ITEM_UID,i.UNIT as danwei ");
		sql.append("  FROM jc_prj_points  p   LEFT JOIN jc_prj_point_item ppi    ON p.PRJ_POINTS_UID = ppi.PRJ_POINTS_UID  ");
		sql.append("  LEFT JOIN jc_prj_jc_item i  ON ppi.JC_PRJ_ITEM_UID = i.JC_PRJ_ITEM_UID  ");
		sql.append("  LEFT JOIN jc_user_focus_point f   ON p.PRJ_POINTS_UID = f.PRJ_POINTS_UID ");
		sql.append("   where p.PROJECT_UID = '"+map.get("puid")+"'  and f.USER_UID = '"+user.getIdCard()+"'  GROUP BY i.JC_TYPE_UID");
		list = DBUtil.queryReturnList(conn, sql.toString());
		
		if(list.size() !=0){
	         option.title().text("我的关注")
             .subtext("测点类型")
             .x(X.center)
             .y(Y.top)//Y.top
             .itemGap(150)
             .textStyle().color("#353736")
             .fontFamily("Open Sans,Helvetica Neue,Helvetica,Arial,sans-serif")
             .fontSize(18);
	          //控制 Tooptip()换行显示
		      option.tooltip().show(true).formatter("{a}<br/>{b} : {c}<br/>({d}%)");
		      option.calculable(false);//饼图外环去除
		      Pie pie= new Pie();
        for(int i=0;i<list.size();i++){
        	Map<String, String> mapList = list.get(i);
        	pie.name("我关注的测点");
            pie.center("50%","50%").radius("40%"); 
            type=mapList.get("JC_TYPE_UID");
            if("1".equals(type)){
            	pointName = "水平位移";
            }else if("2".equals(type)){
            	pointName = "竖向位移(沉降)";
            }else if("3".equals(type)){
            	pointName = "测斜";
            }else if("5".equals(type)){
            	pointName = "降水";
            }else{//6
            	pointName = "轴力";
            }
        	pie.data(new PieData(pointName+"\n(单位:"+mapList.get("danwei").toString()+")\n",mapList.get("number")));
        	
        	
        }
        
        option.series(pie);
       
        map1.put("option", option);
    }
	} catch (Exception e) {
		e.printStackTrace();
	} finally{
		DBUtil.closeConnetion(conn);
		
	}
	
	return map1;
}
/***
 * 查询 报警的和正常的监测点 
 */

public Map<String, Object> queryWarning(HashMap<String, Object> map) {
	User user = ActionContext.getCurrentUserInThread();
	Connection conn = DBUtil.getConnection();
	List<Map<String, String>> list = new ArrayList<Map<String,String>>();
	List<Map<String, String>> list1 = new ArrayList<Map<String,String>>();
	List<Map<String, String>> list2 = new ArrayList<Map<String,String>>();
	Map<String,Object> map1 = new HashMap<String, Object>();
	Option option = new Option();
	float singleValue = 0F;//单次预警值
	float total1 = 0F;//累计值（正方向变化值）
	float total2 = 0F;//累计值（负方向变化值）
	float value_diff = 0F;//本次水平变化值
	float value_jc = 0F;//水平监测值
	String typeName="";//类型名称
	int count = 0;
	int num  = 0;
	String name = "";
	try {
		//查询出我的关注点
		String sql="SELECT p.PRJ_POINTS_UID as PRJ_POINTS_UID, p.POINT_CODE AS number,i.JC_NAME,i.SHORT_NAME as shortName ,i.JC_TYPE_UID,ppi.PRJ_POINT_ITEM_UID, "+
					" CASE WHEN pp.SINGLE_WARN IS NOT NULL THEN pp.SINGLE_WARN WHEN pp.SINGLE_WARN IS NULL THEN 	i.SINGLE_WARN 	END AS SINGLE_WARN, " +
				    " CASE	WHEN pp.TOTAL_WARN1 IS NOT NULL THEN PP.TOTAL_WARN1 	WHEN pp.TOTAL_WARN1 IS NULL THEN 	i.TOTAL_WARN1 END AS TOTAL_WARN1," +
				    " CASE WHEN pp.TOTAL_WARN2 IS NOT NULL THEN PP.TOTAL_WARN2 WHEN pp.TOTAL_WARN2 IS NULL THEN 	I.TOTAL_WARN2 END AS TOTAL_WARN2 " +
				    " FROM " +
					" jc_prj_points p " +
					" LEFT JOIN jc_prj_point_item ppi ON p.PRJ_POINTS_UID = ppi.PRJ_POINTS_UID " +
					" LEFT JOIN jc_prj_jc_item i ON ppi.JC_PRJ_ITEM_UID = i.JC_PRJ_ITEM_UID " + 
					" LEFT JOIN jc_point_property pp ON pp.PRJ_POINT_ITEM_UID = ppi.JC_PRJ_ITEM_UID " +
					" LEFT JOIN jc_user_focus_point f ON p.PRJ_POINTS_UID = f.PRJ_POINTS_UID " +
					" WHERE " +
					" p.PROJECT_UID = '"+map.get("puid")+"'  AND f.USER_UID = '"+user.getIdCard()+"' ";
					
		list = DBUtil.queryReturnList(conn, sql.toString());
		
		//报警判断：
		if(list.size() !=0){
         option.title().text("报警情况")
                .subtext("报警状态")
                .x(X.center)
                .y(Y.top)
                .itemGap(150)
                .textStyle().color("#353736")
                .fontFamily("Open Sans,Helvetica Neue,Helvetica,Arial,sans-serif")
                .fontSize(18);
         option.tooltip().show(true).formatter("{a} <br/>{b} : {c} ({d}%)");
         option.calculable(false);//饼图外环去除
         Pie pie= new Pie();
        for(int i=0;i<list.size();i++){
        	Map<String, String> mapList = list.get(i);
        	typeName  =mapList.get("shortName");
        	total1 = Float.parseFloat(mapList.get("TOTAL_WARN1"));
        	total2 = Float.parseFloat(mapList.get("TOTAL_WARN2"));
        	singleValue = Float.parseFloat(mapList.get("SINGLE_WARN"));
        	StringBuffer sqltp = new StringBuffer();
        	if(typeName.indexOf("测斜") > -1){//说明是测斜表数据
        		sqltp.append(" SELECT d.PRJ_POINTS_UID, d.HORIZONTAL_VALUE_DIFF, d.HORIZONTAL_VALUE,d.REPORT_DATE");
        		sqltp.append(" FROM jc_cx_data AS d LEFT JOIN jc_prj_points jp ");
        		sqltp.append(" on d.PRJ_POINTS_UID = jp.PRJ_POINTS_UID WHERE");
        		sqltp.append(" d.PRJ_POINTS_UID = '"+mapList.get("PRJ_POINTS_UID")+"' and jp.project_uid = '"+map.get("puid")+"' ORDER BY d.REPORT_DATE DESC LIMIT 0,1");
    	       
        	}else{
	        	sqltp.append(" SELECT d.PRJ_POINTS_UID, d.HORIZONTAL_VALUE_DIFF, d.HORIZONTAL_VALUE,d.VERTICAL_VALUE,d.VERTICAL_VALUE_DIFF,d.REPORT_DATE");
	    		sqltp.append(" FROM jc_jc_data AS d LEFT JOIN jc_prj_points jp ");
	    		sqltp.append(" on d.PRJ_POINTS_UID = jp.PRJ_POINTS_UID WHERE");
	    		sqltp.append(" d.PRJ_POINTS_UID = '"+mapList.get("PRJ_POINTS_UID")+"' and jp.project_uid = '"+map.get("puid")+"' ORDER BY d.REPORT_DATE DESC LIMIT 0,1");
		     
        	}
        	  list1 =  DBUtil.queryReturnList(conn,sqltp.toString());
		    
	       if(list1.size() !=0){
        		   Map<String,String> map2 = list1.get(0);
        		   if(typeName.indexOf("测斜") != -1 || typeName.indexOf("水平") != -1){//当前测点类型是 测斜 就如下比较
        		   if(!"".equals(map2.get("HORIZONTAL_VALUE")) && !"".equals(map2.get("HORIZONTAL_VALUE_DIFF")) ){
        		   value_jc = Float.parseFloat(map2.get("HORIZONTAL_VALUE"));
        		   value_diff = Float.parseFloat(map2.get("HORIZONTAL_VALUE_DIFF"));
        		  
        		   /** 报警：本次变化值的绝对值 > singleValue 值 或 total2<value_jc <total1 **/
        		   if(Math.abs(value_diff) >singleValue || total2<value_jc || value_jc<total1 ){
        			   ++count; 
        		   }else{
        			   ++num;
        		   }
        		   }else if(!"".equals(map2.get("HORIZONTAL_VALUE_DIFF"))){
        			   value_diff = Float.parseFloat(map2.get("HORIZONTAL_VALUE_DIFF"));
        			   if(Math.abs(value_diff) >singleValue ){
            			   ++count;
            		   }else{
            			   ++num;
            		   }
        		   }else if(!"".equals(map2.get("HORIZONTAL_VALUE"))){
        			   value_jc = Float.parseFloat(map2.get("HORIZONTAL_VALUE"));
        			   if(total2<value_jc || value_jc<total1 ){
            			   ++count;
            		   }else{
            			   ++num;
            		   }
        		   }else{//都为空 正常
        			   ++count;
        		   }
        	   }else{//沉降 轴力 水位
        		   if(!"".equals(map2.get("VERTICAL_VALUE")) && !"".equals(map2.get("VERTICAL_VALUE_DIFF")) ){
            		   value_jc = Float.parseFloat(map2.get("VERTICAL_VALUE"));
            		   value_diff = Float.parseFloat(map2.get("VERTICAL_VALUE_DIFF"));
            		  
            		   /** 报警：本次变化值的绝对值 > singleValue 值 或 total2<value_jc <total1 **/
            		   if(Math.abs(value_diff) >singleValue || total2<value_jc || value_jc<total1 ){
            			   ++count; 
            		   }else{
            			   ++num;
            		   }
            		   }else if(!"".equals(map2.get("VERTICAL_VALUE_DIFF"))){
            			   value_diff = Float.parseFloat(map2.get("VERTICAL_VALUE_DIFF"));
            			   if(Math.abs(value_diff) >singleValue ){
                			   ++count;
                		   }else{
                			   ++num;
                		   }
            		   }else if(!"".equals(map2.get("VERTICAL_VALUE"))){
            			   value_jc = Float.parseFloat(map2.get("VERTICAL_VALUE"));
            			   if(total2<value_jc || value_jc<total1 ){
                			   ++count;
                		   }else{
                			   ++num;
                		   }
            		   }else{//都为空报警
            			   ++num;
            		   }
        		   
        		   
        	   }
        	   
            }
        }
        
				        pie.name("点的报警状态");
				        pie.center("50%","50%").radius("40%");  
				    	pie.data(new PieData("报警",count));
				    	pie.data(new PieData("正常",num));
				        option.series(pie);
				        map1.put("option", option);
				        map1.put("number",count);
    }
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally{
		DBUtil.closeConnetion(conn);
		
	}
	
	return map1;
}

/**
 * 查询 出所有 测点中正常的 和报警的对比
 * 
 */
public Map<String, Object> queryAllPoint(HashMap<String, Object> map) {
	User user = ActionContext.getCurrentUserInThread();
	Connection conn = DBUtil.getConnection();
	List<Map<String, String>> list = new ArrayList<Map<String,String>>();
	List<Map<String, String>> list1 = new ArrayList<Map<String,String>>();
	Map<String, Object> map1 = new HashMap<String, Object>();
	int count = 0; //统计报警的总数
	int num  = 0;//未报警的总数
	String name = "";String name1 = "";String name2 = "";String name3 = "";String name4 = "";
	int count1 = 0;int count2 = 0;int count3 = 0;int count4 = 0;int count5 = 0;
	int num1 = 0;int num2 = 0;int num3 = 0;int num4 = 0;int num5 = 0;
	try {
		
		//查询出我的关注点
		/*String sql="SELECT p.PRJ_POINTS_UID as PRJ_POINTS_UID, p.POINT_CODE AS number,i.JC_NAME,i.SHORT_NAME as shortName ,i.JC_TYPE_UID AS JC_TYPE_ID,ppi.PRJ_POINT_ITEM_UID, "+
					" CASE WHEN pp.SINGLE_WARN IS NOT NULL THEN pp.SINGLE_WARN WHEN pp.SINGLE_WARN IS NULL THEN 	i.SINGLE_WARN 	END AS SINGLE_WARN, " +
				    " CASE	WHEN pp.TOTAL_WARN1 IS NOT NULL THEN PP.TOTAL_WARN1 	WHEN pp.TOTAL_WARN1 IS NULL THEN 	i.TOTAL_WARN1 END AS TOTAL_WARN1," +
				    " CASE WHEN pp.TOTAL_WARN2 IS NOT NULL THEN PP.TOTAL_WARN2 WHEN pp.TOTAL_WARN2 IS NULL THEN 	i.TOTAL_WARN2 END AS TOTAL_WARN2 " +
				    " FROM " +
					" jc_prj_points p " +
					" RIGHT JOIN jc_prj_point_item ppi ON p.PRJ_POINTS_UID = ppi.PRJ_POINTS_UID " +
					" LEFT JOIN jc_prj_jc_item i ON ppi.JC_PRJ_ITEM_UID = i.JC_PRJ_ITEM_UID " + 
					" LEFT JOIN jc_point_property pp ON pp.PRJ_POINT_ITEM_UID = ppi.JC_PRJ_ITEM_UID " +
					" WHERE " +
					" p.PROJECT_UID = '"+map.get("puid")+"' ";*/
		
					StringBuffer sql = new StringBuffer();
					sql.append(" SELECT jpp.*,i.SHORT_NAME as shortName,i.JC_TYPE_UID AS JC_TYPE_ID,jp.PRJ_POINTS_UID  FROM jc_point_property jpp ");
			    	sql.append(" LEFT JOIN jc_prj_point_item jpi ");
			    	sql.append(" ON jpp.PRJ_POINT_ITEM_UID = jpi.PRJ_POINT_ITEM_UID ");
			    	sql.append(" LEFT JOIN jc_prj_jc_item i ON jpi.JC_PRJ_ITEM_UID = i.JC_PRJ_ITEM_UID ");
			    	sql.append(" LEFT JOIN jc_prj_points jp ");
			    	sql.append(" ON jp.PRJ_POINTS_UID = jpi.PRJ_POINTS_UID ");
					sql.append(" WHERE jp.PROJECT_UID = '"+map.get("puid")+"'");
				   list = DBUtil.queryReturnList(conn, sql.toString());
		//报警判断：
		if(list.size() !=0){
			 Option option1 = new Option();
			 Option option2 = new Option();
			 Option option3 = new Option();
			 Option option4 = new Option();
			 Option option5 = new Option();
			 Pie pie= new Pie();
			 Pie pie1= new Pie("1");
			 Pie pie2= new Pie("2");
			 Pie pie3= new Pie("3");
			 Pie pie4= new Pie("4");
        for(int i=0;i<list.size();i++){
		        	Map<String, String> mapList = list.get(i);
		        	//typeName  =mapList.get("shortName");
		        	String type_uid = mapList.get("JC_TYPE_ID");
		        	StringBuffer sqltp = new StringBuffer();
		        	StringBuffer sqlother = new StringBuffer();
		        	//除了测斜之外 所有的类型 sql 都去jc_jc_data表查数据判断是否报警
		        	sqlother.append(" SELECT d.PRJ_POINTS_UID, d.HORIZONTAL_VALUE_DIFF,d.VERTICAL_VALUE,d.VERTICAL_VALUE_DIFF, d.HORIZONTAL_VALUE,d.REPORT_DATE");
		        	sqlother.append(" FROM jc_jc_data AS d LEFT JOIN jc_prj_points jp ");
		        	sqlother.append(" on d.PRJ_POINTS_UID = jp.PRJ_POINTS_UID WHERE");
		        	sqlother.append(" d.PRJ_POINTS_UID = '"+mapList.get("PRJ_POINTS_UID")+"' and jp.project_uid = '"+map.get("puid")+"' ORDER BY d.REPORT_DATE DESC LIMIT 0,1");
	        	
	        	
		        	/*sqlother.append(" SELECT da.* FROM ( ");
		        	sqlother.append(" SELECT a.* FROM ");
			    	sqlother.append(" (SELECT jd.* FROM jc_jc_data jd LEFT JOIN jc_prj_points jp ON jd.PRJ_POINTS_UID = jp.PRJ_POINTS_UID WHERE jp.PROJECT_UID = '"+map.get("puid")+"') a, ");
			    	sqlother.append(" (SELECT jd.PRJ_POINTS_UID, MAX(jd.REPORT_DATE) REPORT_DATE_MAX FROM jc_jc_data jd GROUP BY jd.PRJ_POINTS_UID) b ");
			    	sqlother.append(" WHERE a.PRJ_POINTS_UID = b.PRJ_POINTS_UID AND a.REPORT_DATE = b.REPORT_DATE_MAX ) da where da.PRJ_POINTS_UID ='"+mapList.get("PRJ_POINTS_UID")+"' LIMIT 0,1");*/
	        	
        	if("3".equals(type_uid)){//说明是测斜
        		    name = "测斜";
	        		sqltp.append(" SELECT d.PRJ_POINTS_UID, d.HORIZONTAL_VALUE_DIFF, d.HORIZONTAL_VALUE,d.REPORT_DATE");
	        		sqltp.append(" FROM jc_cx_data AS d LEFT JOIN jc_prj_points jp ");
	        		sqltp.append(" on d.PRJ_POINTS_UID = jp.PRJ_POINTS_UID WHERE");
	        		sqltp.append(" d.PRJ_POINTS_UID = '"+mapList.get("PRJ_POINTS_UID")+"' and jp.project_uid = '"+map.get("puid")+"' ORDER BY d.REPORT_DATE DESC LIMIT 0,1");
	        		
        		    /*sqltp.append(" SELECT da.* FROM ( ");
        		    sqltp.append(" SELECT a.* FROM ");
        	    	sqltp.append(" (SELECT jd.* FROM jc_cx_data jd LEFT JOIN jc_prj_points jp ON jd.PRJ_POINTS_UID = jp.PRJ_POINTS_UID WHERE jp.PROJECT_UID = '"+map.get("puid")+"') a, ");
        	    	sqltp.append(" (SELECT jd.PRJ_POINTS_UID, MAX(jd.REPORT_DATE) REPORT_DATE_MAX FROM jc_cx_data jd GROUP BY jd.PRJ_POINTS_UID) b ");
        	    	sqltp.append(" WHERE a.PRJ_POINTS_UID = b.PRJ_POINTS_UID AND a.REPORT_DATE = b.REPORT_DATE_MAX ");
        	    	sqltp.append(" ORDER BY PRJ_POINTS_UID, DEEP) da where da.PRJ_POINTS_UID ='"+mapList.get("PRJ_POINTS_UID")+"' LIMIT 0,1");*/
	    	        list1 =  DBUtil.queryReturnList(conn,sqltp.toString());
	    	        int[] arr = getCount(list1,mapList);
	        		count1 +=arr[0];//报警的数
	        		num1 += arr[1];
        		
        	}else if("1".equals(type_uid)){//说是水平位移
        		 name1 = "水平位移";
        		 list1 =  DBUtil.queryReturnList(conn,sqlother.toString());
        		 int[] arr = getCount(list1,mapList);
        		 count2 +=arr[0];//报警的数
        		 num2 += arr[1];
        		
        	}else if("2".equals(type_uid)){//竖向位移（沉降）
        		 name2 = "竖向位移（沉降）";
        		 list1 =  DBUtil.queryReturnList(conn,sqlother.toString());
        		 int[] arr = getCount(list1,mapList);
        		 count3 +=arr[0];
        		 num3 +=arr[1];
        		
        	}else if("5".equals(type_uid)){//降水
        		 name3 = "降水";
        		 list1 =  DBUtil.queryReturnList(conn,sqlother.toString());
        		 int[] arr = getCount(list1,mapList);
        		 count4 +=arr[0];
        		 num4 +=arr[1];
        		
        	}else if("6".equals(type_uid)){//轴力
        		 name4 = "轴力";
        		 list1 =  DBUtil.queryReturnList(conn,sqlother.toString());
        		 int[] arr = getCount(list1,mapList);
        		 count5 +=arr[0];
        		 num5 +=arr[1];
        		
        	} 	
        }
        
        option1.title().text(name)
		     .subtext("报警状态")
		     .x(X.center)
		     .y(Y.top)
		     .itemGap(245)
		     .textStyle().color("#353736")
		     .fontFamily("Open Sans,Helvetica Neue,Helvetica,Arial,sans-serif")
		     .fontSize(18);
  		 option1.tooltip().show(true).formatter("{a} <br/>{b} : {c} ({d}%)");
  		 option1.calculable(false);//饼图外环去除
  		 
  		 option2.title().text(name1)
		     .subtext("报警状态")
		     .x(X.center)
		     .y(Y.top)
		     .itemGap(245)
		     .textStyle().color("#353736")
		     .fontFamily("Open Sans,Helvetica Neue,Helvetica,Arial,sans-serif")
		     .fontSize(18);
   		 option2.tooltip().show(true).formatter("{a} <br/>{b} : {c} ({d}%)");
   		 option2.calculable(false);//饼图外环去除
   		 
  		 option3.title().text(name2)
		     .subtext("报警状态")
		     .x(X.center)
		     .y(Y.top)
		     .itemGap(245)
		     .textStyle().color("#353736")
		     .fontFamily("Open Sans,Helvetica Neue,Helvetica,Arial,sans-serif")
		     .fontSize(18);
   		 option3.tooltip().show(true).formatter("{a} <br/>{b} : {c} ({d}%)");
   		 option3.calculable(false);//饼图外环去除
        	
         option5.title().text(name4)
  		    .subtext("报警状态")
  		     .x(X.center)
  		     .y(Y.top)
  		     .itemGap(245)
  		     .textStyle().color("#353736")
  		     .fontFamily("Open Sans,Helvetica Neue,Helvetica,Arial,sans-serif")
  		     .fontSize(18);
	   	 option5.tooltip().show(true).formatter("{a} <br/>{b} : {c} ({d}%)");
	   	 option5.calculable(false);//饼图外环去除
	   	 
	   	 option4.title().text(name3)
		    .subtext("报警状态")
		     .x(X.center)
		     .y(Y.top)
		     .itemGap(245)
		     .textStyle().color("#353736")
		     .fontFamily("Open Sans,Helvetica Neue,Helvetica,Arial,sans-serif")
		     .fontSize(18);
   		 option4.tooltip().show(true).formatter("{a} <br/>{b} : {c} ({d}%)");
   		 option4.calculable(false);//饼图外环去除
        
		pie.name(name);
		pie.center("50%","50%").radius("30%");  
		pie.data(new PieData("报警",count1));
		pie.data(new PieData("正常",num1));
		
		pie1.name(name1);
		pie1.center("50%","50%").radius("30%");  
		pie1.data(new PieData("报警",count2));
		pie1.data(new PieData("正常",num2));
		
		pie2.name(name2);
		pie2.center("50%","50%").radius("30%");  
		pie2.data(new PieData("报警",count3));
		pie2.data(new PieData("正常",num3));
		
		pie3.name(name3);
		pie3.center("50%","50%").radius("30%");  
		pie3.data(new PieData("报警",count4));
		pie3.data(new PieData("正常",num4));
		
		pie4.name(name4);
		pie4.center("50%","50%").radius("30%");  
		pie4.data(new PieData("报警",count5));
		pie4.data(new PieData("正常",num5));
		
		
		count = count1+count2+count3+count4+count5;
		//int nu = num1+num2+num3+num4+num5;
		option1.series(pie);
		option2.series(pie1);
		option3.series(pie2);
		option4.series(pie3);
		option5.series(pie4);
        map1.put("option1",option1);
        map1.put("option2",option2);
        map1.put("option3",option3);
        map1.put("option4",option4);
        map1.put("option5",option5);
        
        map1.put("sumTotal",count);
    }
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally{
		DBUtil.closeConnetion(conn);
	}
	
	return map1;

}
/** 该方法 主要是 判断当前测点是否报警 返回的是 统计的结果**/
private int[] getCount (List<Map<String, String>> list1,Map<String, String> mapList){
	int[] arr = new int[2];
	int count=0;int num = 0;
	
	float total1 = Float.parseFloat(mapList.get("TOTAL_WARN1"));//0.0
	float total2 = Float.parseFloat(mapList.get("TOTAL_WARN2"));//5.0
	float singleValue = Float.parseFloat(mapList.get("SINGLE_WARN"));
    float value_jc = 0f;
    float value_diff = 0f;
	String typeName = mapList.get("shortName");
	 if(list1.size() !=0){
  		   Map<String,String> map2 = list1.get(0);
  		if(typeName.indexOf("测斜") != -1 || typeName.indexOf("水平") != -1){//当前测点类型是 测斜 就如下比较
  		   if(!"".equals(map2.get("HORIZONTAL_VALUE")) && !"".equals(map2.get("HORIZONTAL_VALUE_DIFF")) ){
	  		   value_jc = Float.parseFloat(map2.get("HORIZONTAL_VALUE"));//5.0
	  		   value_diff = Float.parseFloat(map2.get("HORIZONTAL_VALUE_DIFF"));//0.0
  		  
  		   /** 报警：本次变化值的绝对值 > singleValue 值 或 total2<value_jc <total1 **/
  		   if(Math.abs(value_diff) >singleValue || total2<value_jc || value_jc<total1){
  			   ++count; 
  		   }else{
  			   ++num;
  		   }
  		   }else if(!"".equals(map2.get("HORIZONTAL_VALUE_DIFF"))){
  			   value_diff = Float.parseFloat(map2.get("HORIZONTAL_VALUE_DIFF"));
  			   if(Math.abs(value_diff) >singleValue ){
      			   ++count;
      		   }else{
      			   ++num;
      		   }
  		   }else if(!"".equals(map2.get("HORIZONTAL_VALUE"))){
  			   value_jc = Float.parseFloat(map2.get("HORIZONTAL_VALUE"));
  			   if(total2<value_jc || value_jc<total1 ){
      			   ++count;
      		   }else{
      			   ++num;
      		   }
  		   }else{//表示正常
  			   ++count;
  		   }
  	  }else{//沉降 轴力 水位
		   if(!"".equals(map2.get("VERTICAL_VALUE")) && !"".equals(map2.get("VERTICAL_VALUE_DIFF")) ){
    		   value_jc = Float.parseFloat(map2.get("VERTICAL_VALUE"));
    		   value_diff = Float.parseFloat(map2.get("VERTICAL_VALUE_DIFF"));
    		  
    		   /** 报警：本次变化值的绝对值 > singleValue 值 或 total2<value_jc <total1 **/
    		   if(Math.abs(value_diff) >singleValue || total2<value_jc || value_jc<total1 ){
    			   ++count; 
    		   }else{
    			   ++num;
    		   }
    		   }else if(!"".equals(map2.get("VERTICAL_VALUE_DIFF"))){
    			   value_diff = Float.parseFloat(map2.get("VERTICAL_VALUE_DIFF"));
    			   if(Math.abs(value_diff) >singleValue ){
        			   ++count;
        		   }else{
        			   ++num;
        		   }
    		   }else if(!"".equals(map2.get("VERTICAL_VALUE"))){
    			   value_jc = Float.parseFloat(map2.get("VERTICAL_VALUE"));
    			   if(total2<value_jc || value_jc<total1 ){
        			   ++count;
        		   }else{
        			   ++num;
        		   }
    		   }else{//都为空 正常
    			   ++count;
    		   }
  	  }
     }else{
    	 ++num;
     }
	
	 arr[0] = count;
	 arr[1] = num;
	return arr;
}
/**
 * 查询 市县的名称
 */
public List<?> queryCityName(String code) {
		Connection conn = DBUtil.getConnection();
	    List<?> list = new ArrayList();
	 try {
	        StringBuffer sql  = new StringBuffer();
	        sql.append(" SELECT s.* FROM sys_region AS s ");
	        sql.append(" WHERE s.REGION_CODE ="+code+" ");
	        //sql.append(" AND s.REGION_CODE ="+code+"");
	        list = DBUtil.queryReturnList(conn, sql.toString());

	    } catch (Exception e) {
	        DaoException.handleMessageException("*********查询市区(县)出错!*********");
	    } finally {
	        DBUtil.closeConnetion(conn);
	    }
	    
	    return list;


}

public String queryid(String projectuid) {
	//User user = ActionContext.getCurrentUserInThread(); 
    Connection conn = DBUtil.getConnection();
    String domresult = "";
    try { 
        // 组织查询条件 
        String  sql = "SELECT * FROM bu_project WHERE PROJECT_UID='"+projectuid+"'";

        BaseResultSet bs = DBUtil.query(conn, sql, null); 
        domresult = bs.getJson();  
    } catch (Exception e) {
        DaoException.handleMessageException("*********查询出错!*********");
    } finally {
        DBUtil.closeConnetion(conn);
    }
	return domresult;
}

}
