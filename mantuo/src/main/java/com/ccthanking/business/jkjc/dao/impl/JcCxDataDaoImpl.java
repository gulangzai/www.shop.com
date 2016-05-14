/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.jkjc.JcCxDataDao.java
 * 创建日期： 2015-12-01 上午 10:44:12
 * 功能：   测斜数据
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-12-01 上午 10:44:12  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.jkjc.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.jkjc.dao.JcCxDataDao;
import com.ccthanking.business.jkjc.vo.JcCxDataVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.Toolbox;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.AxisType;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Position;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.feature.Feature;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.MarkLine;
import com.github.abel533.echarts.series.Series;


/**
 * <p> JcCxDataDao.java </p>
 * <p> 功能：测斜数据 </p>
 *
 * <p><a href="JcCxDataDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-12-01
 * 
 */

@Component
public class JcCxDataDaoImpl  extends BsBaseDaoTJdbc implements JcCxDataDao {

    public String queryCondition(String json, JcCxDataVO vo, Map map){
    
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

            String sql = "SELECT * FROM " + "JC_CX_DATA t";
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

	public Option queryBarChart(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection(); 
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		Option option = new Option();
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT tab1.DEEP,tab1.HORIZONTAL_VALUE,tab1.REPORT_DATE as date1,tab2.HORIZONTAL_VALUE,tab2.REPORT_DATE as date2,(tab2.HORIZONTAL_VALUE-tab1.HORIZONTAL_VALUE) as diff FROM (SELECT DISTINCT(DEEP) as DEEP ,d.HORIZONTAL_VALUE,date_format(REPORT_DATE,'%Y-%m-%d') as REPORT_DATE  FROM jc_cx_data d where 1=1  ");
			sql.append("  and PRJ_POINTS_UID = '"+map.get("code")+"' ");
			sql.append("  and d.REPORT_DATE = (SELECT DATE_FORMAT(MIN(REPORT_DATE),'%Y-%m-%d')  FROM jc_cx_data d   ");
			sql.append(" WHERE d.REPORT_DATE >= '"+map.get("time1")+"') ) tab1 ");
			sql.append(" LEFT JOIN   ");
			sql.append(" (SELECT DISTINCT(DEEP) as DEEP ,d.HORIZONTAL_VALUE,date_format(REPORT_DATE,'%Y-%m-%d') as REPORT_DATE  FROM jc_cx_data d where 1=1  ");
			sql.append("  and PRJ_POINTS_UID = '"+map.get("code")+"' ");
			sql.append("  and d.REPORT_DATE = (SELECT DATE_FORMAT(MAX(REPORT_DATE),'%Y-%m-%d')  FROM jc_cx_data d   ");
			sql.append(" WHERE d.REPORT_DATE <= '"+map.get("time2")+"')  ) tab2 ");
			sql.append(" ON tab1.DEEP = tab2.DEEP ");
			list = DBUtil.queryReturnList(conn, sql.toString());
			Map<String, String> map1 = new HashMap<String, String>();
			if(list.size()>0){
				map1 = list.get(0);
			}
			 
			option.title(map1.get("date1")+"~"+map1.get("date2")+"变化值").tooltip(Trigger.axis);
			// 横轴为值轴
			ValueAxis valueAxis = new ValueAxis();
			valueAxis.axisLabel().formatter("{value} mm");
				
			// 创建类目轴
			CategoryAxis categoryAxis = new CategoryAxis();
			categoryAxis.axisLabel().formatter("{value} m");
			
			Bar bar = new Bar();
				
		    option.toolbox().show(true).feature(
		                Tool.saveAsImage).padding(20,50,0,0);
			// 循环数据
			for (Map<String, String> objectMap : list) {
				// 设置类目
				categoryAxis.data(objectMap.get("DEEP"));
				// 类目对应的柱状图
				bar.data(objectMap.get("diff"));
			}
			// 设置类目轴
			option.xAxis(categoryAxis);
			option.yAxis(valueAxis);
			// 设置数据
			option.series(bar);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return option;
	}
	
	public Option queryBarChart2(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection(); 
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		Option option = new Option();
		String tstr = "";
		String ustr = "";
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT tab1.DEEP,tab1.HORIZONTAL_VALUE,tab1.REPORT_DATE as date1,tab2.HORIZONTAL_VALUE,tab2.REPORT_DATE as date2,(tab2.HORIZONTAL_VALUE-tab1.HORIZONTAL_VALUE) as diff FROM (SELECT DISTINCT(DEEP) as DEEP ,d.HORIZONTAL_VALUE,date_format(REPORT_DATE,'%Y-%m-%d') as REPORT_DATE  FROM jc_cx_data d where 1=1  ");
			sql.append("  and PRJ_POINTS_UID = '"+map.get("code")+"' ");
			sql.append("  and d.REPORT_DATE = (SELECT DATE_FORMAT(MIN(REPORT_DATE),'%Y-%m-%d')  FROM jc_cx_data d )  ");
			sql.append("  ) tab1 ");
			sql.append(" LEFT JOIN   ");
			sql.append(" (SELECT DISTINCT(DEEP) as DEEP ,d.HORIZONTAL_VALUE,date_format(REPORT_DATE,'%Y-%m-%d') as REPORT_DATE  FROM jc_cx_data d where 1=1  ");
			sql.append("  and PRJ_POINTS_UID = '"+map.get("code")+"' ");
			sql.append("  and d.REPORT_DATE = (SELECT DATE_FORMAT(MAX(REPORT_DATE),'%Y-%m-%d')  FROM jc_cx_data d   ");
			sql.append(" WHERE d.REPORT_DATE <= '"+map.get("time2")+"')  ) tab2 ");
			sql.append(" ON tab1.DEEP = tab2.DEEP ");
			list = DBUtil.queryReturnList(conn, sql.toString());
			Map<String, String> map1 = new HashMap<String, String>();
			if(list.size()>0){
				map1 = list.get(0);
			}
			 
			option.title(map1.get("date1")+"~"+map1.get("date2")+"变化值").tooltip(Trigger.axis);
			// 横轴为值轴
			ValueAxis valueAxis = new ValueAxis();
			valueAxis.axisLabel().formatter("{value} mm");	
			// 创建类目轴
			CategoryAxis categoryAxis = new CategoryAxis();
			categoryAxis.axisLabel().formatter("{value} m");
			
			Bar bar = new Bar();
				
		    option.toolbox().show(true).feature(
		                Tool.saveAsImage).padding(20,50,0,0);
			// 循环数据
			for (Map<String, String> objectMap : list) {
				// 设置类目
				categoryAxis.data(objectMap.get("DEEP"));
				// 类目对应的柱状图
				bar.data(objectMap.get("diff"));
			}
			// 设置类目轴
			option.xAxis(categoryAxis);
			option.yAxis(valueAxis);
			// 设置数据
			option.series(bar);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return option;
	}

	public Option queryLineChart(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection();
		Option option = new Option();
		String tstr = "";
		String cstr = "";
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT DEEP ,d.HORIZONTAL_VALUE,date_format(REPORT_DATE,'%Y-%m-%d') as REPORT_DATE  FROM jc_cx_data d where 1=1 ");
			sql.append(" and PRJ_POINTS_UID = '"+map.get("code")+"'");
			sql.append(" and d.REPORT_DATE = ");
			sql.append(" (SELECT DATE_FORMAT(MIN(REPORT_DATE),'%Y-%m-%d')  FROM jc_cx_data d ");
			sql.append(" WHERE d.REPORT_DATE >= '"+map.get("time1")+"')  ORDER BY DEEP ");
			List<Map<String, String>> list = DBUtil.queryReturnList(conn, sql.toString());
			
			StringBuffer sql2 = new StringBuffer();
			sql2.append("SELECT DEEP ,d.HORIZONTAL_VALUE,date_format(REPORT_DATE,'%Y-%m-%d') as REPORT_DATE  FROM jc_cx_data d where 1=1 ");
			sql2.append(" and PRJ_POINTS_UID = '"+map.get("code")+"'");
			sql2.append(" and d.REPORT_DATE = ");
			sql2.append(" (SELECT DATE_FORMAT(MAX(REPORT_DATE),'%Y-%m-%d')  FROM jc_cx_data d ");
			sql2.append(" WHERE d.REPORT_DATE <= '"+map.get("time2")+"') ORDER BY DEEP ");
			List<Map<String, String>> list2 = DBUtil.queryReturnList(conn, sql2.toString());
			List<Map<String ,String>> mmap = getTypeByCode(map);
			if(null != mmap ){
				tstr = mmap.get(0).get("SHORT_NAME");
				cstr = mmap.get(0).get("REPORT_CODE");
			}
			
			//option.title(tstr+cstr+"孔变化曲线").tooltip(Trigger.axis);
			option.title(map.get("time1")+"~"+map.get("time2")+"变化曲线").tooltip(Trigger.axis);
			//option.tooltip().trigger(Trigger.axis).formatter("位移量: <br/>{b}m : {c}mm");
		
			
			Line line = new Line(list.size()>0?list.get(0).get("REPORT_DATE"):"");
			Line line2 = new Line(list2.size()>0?list2.get(0).get("REPORT_DATE"):"");
		
			
	        option.toolbox().show(true).feature(
	                //Tool.mark,
	                //Tool.dataView,
	                //new MagicType(Magic.line, Magic.bar),
	                //Tool.restore,
	                Tool.saveAsImage
	                ).padding(20,50,0,0);
	        ValueAxis valueAxis = new ValueAxis();
	        valueAxis.axisLabel().formatter("{value} mm");
	        valueAxis.position(Position.top);
	        option.xAxis(valueAxis);
	        
	        CategoryAxis categoryAxis = new CategoryAxis();
	       // categoryAxis.axisLine().onZero(false);
	        categoryAxis.axisLabel().formatter("{value} m");
	        //categoryAxis.boundaryGap(false);
	        //categoryAxis.setType(AxisType.value);
	        
	        if(list.size()>0){
		        for(Map<String, String> objectMap : list){
		        	 categoryAxis.data(Double.valueOf(objectMap.get("DEEP")));
		        }
	        	//categoryAxis.data("0","-5","-10","-15","-20","-25");
		        option.yAxis(categoryAxis);     
				// 循环数据
				for (Map<String, String> objectMap : list) {
					// 类目对应的柱状图
				    line.data(objectMap.get("HORIZONTAL_VALUE"));
				}
				for (Map<String, String> objectMap : list2) {
					// 类目对应的柱状图
					line2.data(objectMap.get("HORIZONTAL_VALUE"));
				}
	        }
	       
			// 设置数据
			option.series(line,line2);


		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn); 
		}

		return option;
	}
	
	
	public Option queryLineChart2(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection();
		Option option = new Option();
		String tstr = "";
		String cstr = "";
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT DISTINCT(DEEP) as DEEP ,d.HORIZONTAL_VALUE,date_format(REPORT_DATE,'%Y-%m-%d') as REPORT_DATE  FROM jc_cx_data d where 1=1 ");
			sql.append(" and PRJ_POINTS_UID = '"+map.get("code")+"'");
			sql.append(" and d.REPORT_DATE = ");
			sql.append(" (SELECT DATE_FORMAT(MIN(REPORT_DATE),'%Y-%m-%d')  FROM jc_cx_data d )");
			sql.append(" ORDER BY DEEP ");
			List<Map<String, String>> list = DBUtil.queryReturnList(conn, sql.toString());
			
			StringBuffer sql2 = new StringBuffer();
			sql2.append("SELECT DISTINCT(DEEP) as DEEP ,d.HORIZONTAL_VALUE,date_format(REPORT_DATE,'%Y-%m-%d') as REPORT_DATE  FROM jc_cx_data d where 1=1 ");
			sql2.append(" and PRJ_POINTS_UID = '"+map.get("code")+"'");
			sql2.append(" and d.REPORT_DATE = ");
			sql2.append(" (SELECT DATE_FORMAT(MAX(REPORT_DATE),'%Y-%m-%d')  FROM jc_cx_data d ");
			sql2.append(" WHERE d.REPORT_DATE <= '"+map.get("time2")+"') ORDER BY DEEP ");
			List<Map<String, String>> list2 = DBUtil.queryReturnList(conn, sql2.toString());
			List<Map<String ,String>> mmap = getTypeByCode(map);
			if(null != mmap ){
				tstr = mmap.get(0).get("SHORT_NAME");
				cstr = mmap.get(0).get("REPORT_CODE");
			}
			
			//option.title(tstr+cstr+"孔变化曲线").tooltip(Trigger.axis);
			option.title(list.get(0).get("REPORT_DATE")+"~"+map.get("time2")+"变化曲线").tooltip(Trigger.axis);
			//option.tooltip().trigger(Trigger.axis).formatter("位移量: <br/>{b}m : {c}mm");
		
			
			Line line = new Line(list.size()>0?list.get(0).get("REPORT_DATE"):"");
			Line line2 = new Line(list2.size()>0?list2.get(0).get("REPORT_DATE"):"");
		
			
	        option.toolbox().show(true).feature(
	                //Tool.mark,
	                //Tool.dataView,
	                //new MagicType(Magic.line, Magic.bar),
	                //Tool.restore,
	                Tool.saveAsImage
	                ).padding(20,50,0,0);
	        ValueAxis valueAxis = new ValueAxis();
	        valueAxis.axisLabel().formatter("{value} mm");
	        valueAxis.position(Position.top);
	        option.xAxis(valueAxis);
	        
	        CategoryAxis categoryAxis = new CategoryAxis();
	       // categoryAxis.axisLine().onZero(false);
	        categoryAxis.axisLabel().formatter("{value} m");
	        //categoryAxis.boundaryGap(false);
	        //categoryAxis.setType(AxisType.value);
	        
	        if(list.size()>0){
		        for(Map<String, String> objectMap : list){
		        	 categoryAxis.data(Double.valueOf(objectMap.get("DEEP")));
		        }
	        	//categoryAxis.data("0","-5","-10","-15","-20","-25");
		        option.yAxis(categoryAxis);     
				// 循环数据
				for (Map<String, String> objectMap : list) {
					// 类目对应的柱状图
				    line.data(objectMap.get("HORIZONTAL_VALUE"));
				}
				for (Map<String, String> objectMap : list2) {
					// 类目对应的柱状图
					line2.data(objectMap.get("HORIZONTAL_VALUE"));
				}
	        }
	       
			// 设置数据
			option.series(line,line2);


		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn); 
		}

		return option;
	}

	public Map<String,Object>  queryTab(HashMap<String, String> map) {
		Connection conn = DBUtil.getConnection();
		List<Map<String,String>> list = null;
		Map<String,Object> retmap = new HashMap<String,Object>();
		try {
			
			StringBuffer sql = new StringBuffer();
			list = getDates(map);
			
/*			sql.append(" SELECT DISTINCT(a.DEEP),a.HORIZONTAL_VALUE as num1,b.HORIZONTAL_VALUE as num2, ");
			sql.append(" a.REPORT_DATE AS REPORT_DATE1,b.REPORT_DATE AS REPORT_DATE2 FROM ");
			sql.append(" (SELECT DEEP ,d.HORIZONTAL_VALUE,DATE_FORMAT(REPORT_DATE,'%Y-%m-%d') as REPORT_DATE  FROM jc_cx_data d where 1=1  ");
			sql.append(" and PRJ_POINTS_UID = '"+map.get("code")+"' ");
			sql.append(" and d.REPORT_DATE =  ");
			sql.append(" (SELECT DATE_FORMAT(MIN(REPORT_DATE),'%Y-%m-%d')  FROM jc_cx_data d  ");
			sql.append(" WHERE d.REPORT_DATE >= '"+map.get("time1")+"')) a , ");
			sql.append(" (SELECT DEEP ,d.HORIZONTAL_VALUE,DATE_FORMAT(REPORT_DATE,'%Y-%m-%d') as REPORT_DATE   FROM jc_cx_data d where 1=1  ");
			sql.append(" and PRJ_POINTS_UID = '"+map.get("code")+"' ");
			sql.append(" and d.REPORT_DATE =  ");
			sql.append(" (SELECT DATE_FORMAT(MAX(REPORT_DATE),'%Y-%m-%d')  FROM jc_cx_data d  ");
			sql.append(" WHERE d.REPORT_DATE <= '"+map.get("time2")+"'))b ");
			sql.append(" WHERE a.DEEP = b.DEEP ");*/
			
			
			//list = DBUtil.queryReturnList(conn, sql.toString());
			int name = 97;
			if(list.size()>0){
				if(list.size()==1){
					Map<String,String> map2 = list.get(0);
					sql = new StringBuffer();
					sql.append(" SELECT DISTINCT(DEEP),a.HORIZONTAL_VALUE as num0,DATE_FORMAT(REPORT_DATE,'%Y-%m-%d') AS REPORT_DATE0 FROM jc_cx_data a ");
					sql.append(" where PRJ_POINTS_UID = '"+map.get("code")+"' ");
					sql.append(" and REPORT_DATE = '"+map2.get("REPORT_DATE")+"'");
				}else{
					sql = new StringBuffer();
					sql.append("SELECT DISTINCT(a.DEEP)");
					for(int i = 0;i<list.size();i++){
						sql.append(","+(char)name+".HORIZONTAL_VALUE as num"+i+", "+(char)name+".REPORT_DATE AS REPORT_DATE"+i+"");
						name = name+1;
					}
					name = 97;
					sql.append(" from ");
					for(int i = 0;i<list.size();i++){
						Map<String,String> map2 = list.get(i);
						sql.append(" (SELECT DEEP,HORIZONTAL_VALUE,DATE_FORMAT(REPORT_DATE,'%Y-%m-%d') as REPORT_DATE  FROM jc_cx_data  where 1=1  ");
						sql.append(" and PRJ_POINTS_UID = '"+map.get("code")+"' ");
						sql.append(" and REPORT_DATE =  '"+map2.get("REPORT_DATE")+"') "+(char)name+",");
						name = name+1;
						
					}
					sql.append(" WHERE 1=1 ");
					name = 97;
					for (int i = 0; i < list.size()-1; i++) {
						name = name+1;
						sql.append(" and a.DEEP = "+(char)name+".DEEP ");
						
					}
					sql.deleteCharAt(sql.lastIndexOf(","));
				}
				retmap.put("time1", map.get("time1"));
				retmap.put("time2", map.get("time2"));
				
				
			}else{
				Map<String,String> map2 = getDateMap(map.get("code")+"");
				map2.put("code", map.get("code")+"");
				list = getDates(map2);
				if(list.size()==1){
					Map<String,String> map3 = list.get(0);
					sql = new StringBuffer();
					sql.append(" SELECT DISTINCT(a.DEEP),a.HORIZONTAL_VALUE as num0,DATE_FORMAT(REPORT_DATE,'%Y-%m-%d') AS REPORT_DATE0 FROM jc_cx_data a ");
					sql.append(" where PRJ_POINTS_UID = '"+map.get("code")+"' ");
					sql.append(" and REPORT_DATE = '"+map3.get("REPORT_DATE")+"'");
				}else{
					sql = new StringBuffer();
					sql.append("SELECT DISTINCT(a.DEEP)");
					for(int i = 0;i<list.size();i++){
						sql.append(","+(char)name+".HORIZONTAL_VALUE as num"+i+", "+(char)name+".REPORT_DATE AS REPORT_DATE"+i+"");
						name = name+1;
					}
					name = 97;
					sql.append(" from ");
					for(int i = 0;i<list.size();i++){
						Map<String,String> map3 = list.get(i);
						sql.append(" (SELECT DEEP,HORIZONTAL_VALUE,DATE_FORMAT(REPORT_DATE,'%Y-%m-%d') as REPORT_DATE  FROM jc_cx_data  where 1=1  ");
						sql.append(" and PRJ_POINTS_UID = '"+map.get("code")+"' ");
						sql.append(" and REPORT_DATE =  '"+map3.get("REPORT_DATE")+"') "+(char)name+",");
						name = name+1;
						
					}
					sql.append(" WHERE 1=1 ");
					name = 97;
					for (int i = 0; i < list.size()-1; i++) {
						name = name+1;
						sql.append(" and a.DEEP = "+(char)name+".DEEP ");
						
					}
					sql.deleteCharAt(sql.lastIndexOf(","));
				}
				
				retmap.put("time1", map2.get("time1"));
				retmap.put("time2", map2.get("time2"));
				
			}
			list = DBUtil.queryReturnList(conn, sql.toString());
			retmap.put("data", list);
			
		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return retmap;
	}
	
	//得到点的类型
	public List<Map<String,String>>  getTypeByCode(HashMap<String, Object> map){
		Connection conn = DBUtil.getConnection();
		List<Map<String,String>> list = null;
		try {
			
			StringBuffer csql = new StringBuffer();
			csql.append(" SELECT DISTINCT(ji.SHORT_NAME) as SHORT_NAME,jd.REPORT_CODE,ji.UNIT,ji.JC_TYPE_UID FROM jc_cx_data jd ");
			csql.append(" LEFT JOIN jc_prj_point_item ppi ");
			csql.append(" ON jd.PRJ_POINTS_UID = ppi.PRJ_POINTS_UID ");
			csql.append(" LEFT JOIN jc_prj_jc_item ji ");
			csql.append(" ON ppi.JC_PRJ_ITEM_UID = ji.JC_PRJ_ITEM_UID ");
			csql.append(" LEFT JOIN bu_jc_type jt ");
			csql.append(" ON ji.JC_TYPE_UID = jt.JC_TYPE_UID ");
			csql.append(" WHERE jd.PRJ_POINTS_UID = '"+map.get("code")+"'");
			csql.append(" and jt.JC_TYPE_UID = '"+map.get("type")+"'");
			
			list = DBUtil.queryReturnList(conn, csql.toString());

			

		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return list;
	}
    
    // 在此可加入其它方法
	
	private Map<String,String> getDateMap(String PRJ_POINTS_UID){
		Connection conn = DBUtil.getConnection();
		Map<String,String> map = null;
		try {
			String maxdateSql = "SELECT DATE_FORMAT(date_add(max(REPORT_DATE), interval -1 week),'%Y-%m-%d') as time1,DATE_FORMAT(max(REPORT_DATE),'%Y-%m-%d') as time2 FROM jc_cx_data d where PRJ_POINTS_UID = '"+PRJ_POINTS_UID+"'";
			List<Map<String,String>> maxdateList = DBUtil.queryReturnList(conn, maxdateSql);
			if(maxdateList.size()>0){
				map = maxdateList.get(0);	
			}

		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return map;
	}
	
	
	private List<Map<String,String>> getDates(Map<String,String> map){
		Connection conn = DBUtil.getConnection();
		List<Map<String,String>> list = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT DISTINCT(d.REPORT_DATE) as REPORT_DATE FROM jc_cx_data d WHERE d.REPORT_DATE between ");
			sql.append("'"+map.get("time1")+"'");
			sql.append(" and ");
			sql.append("'"+map.get("time2")+"'");
			sql.append(" and PRJ_POINTS_UID = '"+map.get("code")+"' ");
			list = DBUtil.queryReturnList(conn, sql.toString());
		

		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return list;
	}

}
