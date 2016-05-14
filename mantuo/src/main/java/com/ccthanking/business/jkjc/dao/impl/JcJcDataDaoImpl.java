/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.jkjc.JcJcDataDao.java
 * 创建日期： 2015-10-30 上午 09:30:58
 * 功能：   监测数据
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-30 上午 09:30:58  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.jkjc.dao.impl;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ccthanking.business.jkjc.dao.JcJcDataDao;
import com.ccthanking.business.jkjc.vo.JcJcDataVO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.MobileUtil;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.LineType;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Line;

/**
 * <p>
 * JcJcDataDao.java
 * </p>
 * <p>
 * 功能：监测数据
 * </p>
 * 
 * <p>
 * <a href="JcJcDataDao.java.html"><i>查看源代码</i></a>
 * </p>
 * 
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-30
 * 
 */

@Component
public class JcJcDataDaoImpl extends BsBaseDaoTJdbc implements JcJcDataDao {

	public String queryCondition(String json, JcJcDataVO vo, Map map) {

		User user = ActionContext.getCurrentUserInThread();

		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {

			// 组织查询条件
			PageManager page = RequestUtil.getPageManager(json);
			String condition = RequestUtil.getConditionList(json)
					.getConditionWhere();
			String orderFilter = RequestUtil.getOrderFilter(json);
			condition += orderFilter;
			if (page == null)
				page = new PageManager();
			page.setFilter(condition);

			String sql = "SELECT * FROM " + "JC_JC_DATA t";
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
	
	

	/**
	 * 组织折线图
	 */
	public Map<String,Object> queryLineChart(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection();
		DecimalFormat df = new DecimalFormat("#.00");
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		Map<String,Object> map1 = new HashMap<String, Object>();
		Option option = new Option();
		String tstr = "";
		String ustr = "";
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT DATE_FORMAT(d.REPORT_DATE,'%c/%e') as REPORT_DATE ,d.VERTICAL_VALUE,d.HORIZONTAL_VALUE  FROM jc_jc_data d where 1=1 ");
			sql.append(" and PRJ_POINTS_UID = '"+map.get("code")+"'");
			sql.append(" and d.REPORT_DATE BETWEEN '"+map.get("time1")+"' and '"+map.get("time2")+"'");
			list = DBUtil.queryReturnList(conn, sql.toString());
			if(list.size()>0){
				// 横轴为值轴
				option.yAxis(new ValueAxis());
				
				// 创建类目轴
				CategoryAxis yAxis = new CategoryAxis();
			
				Line line = new Line(); 
				Double warn1 = 0d;
				Double warn2 = 0d;
				if(null!=list&&list.size()>0){
					StringBuffer warnsql = new StringBuffer();
					warnsql.append(" SELECT  ");
					warnsql.append(" IFNULL(pp.SINGLE_WARN,pji.SINGLE_WARN) as SINGLE_WARN ,");
					warnsql.append(" IFNULL(pp.TOTAL_WARN1,pji.TOTAL_WARN1) as TOTAL_WARN1,");
					warnsql.append(" IFNULL(pp.TOTAL_WARN2,pji.TOTAL_WARN2) as TOTAL_WARN2");
					warnsql.append("  FROM jc_prj_point_item ppi ");
					warnsql.append(" LEFT JOIN jc_prj_jc_item pji ");
					warnsql.append(" ON ppi.JC_PRJ_ITEM_UID = pji.JC_PRJ_ITEM_UID ");
					warnsql.append(" LEFT JOIN jc_point_property pp ");
					warnsql.append(" ON ppi.PRJ_POINT_ITEM_UID = pp.PRJ_POINT_ITEM_UID ");
					warnsql.append(" WHERE ppi.PRJ_POINTS_UID = '"+map.get("code")+"' ");
					warnsql.append(" AND pji.PROJECT_UID = '"+map.get("projectUid")+"' ");
					
					List<Map<String, String>>  warnlist = DBUtil.queryReturnList(conn, warnsql.toString());
					warn1 = Double.valueOf(warnlist.get(0).get("TOTAL_WARN1"));
					warn2 = Double.valueOf(warnlist.get(0).get("TOTAL_WARN2"));
					line.markLine().itemStyle().normal().lineStyle().color("red");
					line.markLine().itemStyle().normal().lineStyle().type(LineType.solid);
					List<Object> data1 = new ArrayList<Object>();
					List<Object> data2 = new ArrayList<Object>();
					Map<String, Object> m = new HashMap<String, Object>();
					m.put("value", warn1);
					m.put("xAxis", -1);
					m.put("yAxis", warn1);
					data1.add(m);
					Map<String, Object> m2 = new HashMap<String, Object>();
					m2.put("xAxis", 1000);
					m2.put("yAxis", warn1);
					data1.add(m2);
					Map<String, Object> m3 = new HashMap<String, Object>();
					m3.put("value", warn2);
					m3.put("xAxis", -1);
					m3.put("yAxis", warn2);
					data2.add(m3);
					Map<String, Object> m4 = new HashMap<String, Object>();
					m4.put("xAxis", 1000);
					m4.put("yAxis", warn2);
					data2.add(m4);
					ArrayList<Object> o = new ArrayList<Object>();
					o.add(data1);
					o.add(data2);
					
					//o.add(data);
					line.markLine().setData(o);  
					
					//line.markPoint();
					
				}
				List<Map<String ,String>> mmap = getTypeByCode(map);
				if(null != mmap ){
					tstr = mmap.get(0).get("JC_TYPE_NAME");
					ustr = mmap.get(0).get("UNIT");
				}
				
				option.title("累计"+tstr+"值").tooltip(Trigger.axis).legend("累计"+tstr+"值("+ustr+")");

				
		        option.toolbox().show(true).feature(
		                Tool.saveAsImage).padding(20,50,0,0);
				//line.markLine().itemStyle().normal().lineStyle().shadowColor("rgba(100%, 0%, 0%, 1)");

		        List<Object> data = new ArrayList<Object>();
		       // ArrayList<Object> o = new ArrayList<Object>();
				// 循环数据
				for (Map<String, String> objectMap : list) {
					Map<String, Object> m = new HashMap<String, Object>();
					// 设置类目
					yAxis.data(objectMap.get("REPORT_DATE"));
					// 类目对应的折线图
					if("1".equals(map.get("type"))){
						if(Double.valueOf(objectMap.get("HORIZONTAL_VALUE"))>Double.valueOf(warn2)||Double.valueOf(objectMap.get("HORIZONTAL_VALUE"))<Double.valueOf(warn1)){
							m.put("yAxis", objectMap.get("HORIZONTAL_VALUE"));
							m.put("xAxis", objectMap.get("REPORT_DATE"));
							m.put("value", df.format(Double.valueOf(objectMap.get("HORIZONTAL_VALUE"))));
							m.put("name", "报警值");
							data.add(m);
						}
						line.data(objectMap.get("HORIZONTAL_VALUE"));
						
					}else{
						if(Double.valueOf(objectMap.get("VERTICAL_VALUE"))>Double.valueOf(warn2)||Double.valueOf(objectMap.get("VERTICAL_VALUE"))<Double.valueOf(warn1)){
							m.put("yAxis", objectMap.get("VERTICAL_VALUE"));
							m.put("xAxis", objectMap.get("REPORT_DATE"));
							m.put("value", df.format(Double.valueOf(objectMap.get("VERTICAL_VALUE"))));
							m.put("name", "报警值");
							data.add(m);
						}
						line.data(objectMap.get("VERTICAL_VALUE"));
						
					}
					line.markPoint().setData(data);
					line.markPoint().itemStyle().normal().lineStyle().color("red");
					//o.add(data);
				}
				
				// 设置类目轴
				option.xAxis(yAxis);
				
				// 设置数据
				option.series(line);
				map1.put("option", option);
				map1.put("time1", map.get("time1"));
				map1.put("time2", map.get("time2"));
				
			}else{
				Map<String,String> map2 = getDateMap(map.get("code")+"");
				sql = new StringBuffer();
				sql.append("SELECT DATE_FORMAT(d.REPORT_DATE,'%c/%e') as REPORT_DATE ,d.VERTICAL_VALUE,d.HORIZONTAL_VALUE  FROM jc_jc_data d where 1=1 ");
				sql.append(" and PRJ_POINTS_UID = '"+map.get("code")+"'");
				sql.append(" and d.REPORT_DATE BETWEEN '"+map2.get("time1")+"' and '"+map2.get("time2")+"'");
				list = DBUtil.queryReturnList(conn, sql.toString());
				// 横轴为值轴
				option.yAxis(new ValueAxis());
				
				// 创建类目轴
				CategoryAxis yAxis = new CategoryAxis();
			
				Line line = new Line(); 
				Double warn1 = 0d;
				Double warn2 = 0d;
				if(null!=list&&list.size()>0){
					StringBuffer warnsql = new StringBuffer();
					warnsql.append(" SELECT  ");
					warnsql.append(" IFNULL(pp.SINGLE_WARN,pji.SINGLE_WARN) as SINGLE_WARN ,");
					warnsql.append(" IFNULL(pp.TOTAL_WARN1,pji.TOTAL_WARN1) as TOTAL_WARN1,");
					warnsql.append(" IFNULL(pp.TOTAL_WARN2,pji.TOTAL_WARN2) as TOTAL_WARN2");
					warnsql.append("  FROM jc_prj_point_item ppi ");
					warnsql.append(" LEFT JOIN jc_prj_jc_item pji ");
					warnsql.append(" ON ppi.JC_PRJ_ITEM_UID = pji.JC_PRJ_ITEM_UID ");
					warnsql.append(" LEFT JOIN jc_point_property pp ");
					warnsql.append(" ON ppi.PRJ_POINT_ITEM_UID = pp.PRJ_POINT_ITEM_UID ");
					warnsql.append(" WHERE ppi.PRJ_POINTS_UID = '"+map.get("code")+"' ");
					warnsql.append(" AND pji.PROJECT_UID = '"+map.get("projectUid")+"' ");
					List<Map<String, String>>  warnlist = DBUtil.queryReturnList(conn, warnsql.toString());
					warn1 = Double.valueOf(warnlist.get(0).get("TOTAL_WARN1"));
					warn2 = Double.valueOf(warnlist.get(0).get("TOTAL_WARN2"));
					line.markLine().itemStyle().normal().lineStyle().color("red");
					line.markLine().itemStyle().normal().lineStyle().type(LineType.solid);
					List<Object> data1 = new ArrayList<Object>();
					List<Object> data2 = new ArrayList<Object>();
					Map<String, Object> m = new HashMap<String, Object>();
					m.put("value", warn1);
					m.put("xAxis", -1);
					m.put("yAxis", warn1);
					data1.add(m);
					Map<String, Object> m2 = new HashMap<String, Object>();
					m2.put("xAxis", 1000);
					m2.put("yAxis", warn1);
					data1.add(m2);
					Map<String, Object> m3 = new HashMap<String, Object>();
					m3.put("value", warn2);
					m3.put("xAxis", -1);
					m3.put("yAxis", warn2);
					data2.add(m3);
					Map<String, Object> m4 = new HashMap<String, Object>();
					m4.put("xAxis", 1000);
					m4.put("yAxis", warn2);
					data2.add(m4);
					ArrayList<Object> o = new ArrayList<Object>();
					o.add(data1);
					o.add(data2);
					line.markLine().setData(o);
		
				}
				List<Map<String ,String>> mmap = getTypeByCode(map);
				if(null != mmap ){
					tstr = mmap.get(0).get("JC_TYPE_NAME");
					ustr = mmap.get(0).get("UNIT");
				}
				
				option.title("累计"+tstr+"值").tooltip(Trigger.axis).legend("累计"+tstr+"值("+ustr+")");

				
		        option.toolbox().show(true).feature(
		                Tool.saveAsImage).padding(20,50,0,0);

		        List<Object> data = new ArrayList<Object>();
				// 循环数据
				for (Map<String, String> objectMap : list) {
					Map<String, Object> m = new HashMap<String, Object>();
					// 设置类目
					yAxis.data(objectMap.get("REPORT_DATE"));
					// 类目对应的折线图
					if("1".equals(map.get("type"))){
						if(Double.valueOf(objectMap.get("HORIZONTAL_VALUE"))>Double.valueOf(warn2)||Double.valueOf(objectMap.get("HORIZONTAL_VALUE"))<Double.valueOf(warn1)){
							m.put("yAxis", objectMap.get("HORIZONTAL_VALUE"));
							m.put("xAxis", objectMap.get("REPORT_DATE"));
							m.put("value", df.format(Double.valueOf(objectMap.get("HORIZONTAL_VALUE"))));
							m.put("name", "报警值");
							data.add(m);
						}
						line.data(objectMap.get("HORIZONTAL_VALUE"));
						
					}else{
						if(Double.valueOf(objectMap.get("VERTICAL_VALUE"))>Double.valueOf(warn2)||Double.valueOf(objectMap.get("VERTICAL_VALUE"))<Double.valueOf(warn1)){
							m.put("yAxis", objectMap.get("VERTICAL_VALUE"));
							m.put("xAxis", objectMap.get("REPORT_DATE"));
							m.put("value", df.format(Double.valueOf(objectMap.get("VERTICAL_VALUE"))));
							m.put("name", "报警值");
							data.add(m);
						}
						line.data(objectMap.get("VERTICAL_VALUE"));
						
					}
					line.markPoint().setData(data);
					line.markPoint().itemStyle().normal().barBorderColor("#87cefa");
				}

				// 设置类目轴
				option.xAxis(yAxis);
				
				// 设置数据
				option.series(line);
				map1.put("option", option);
				map1.put("time1", map2.get("time1"));
				map1.put("time2", map2.get("time2"));
			}

	
	
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn); 
		}

		return map1;
	}

	/**
	 * 组织柱状图
	 */
	public Map<String,Object> queryBarChart(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection(); 
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		Map<String,Object> map1 = new HashMap<String, Object>();
		Option option = new Option();
		String tstr = "";
		String ustr = "";
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT DATE_FORMAT(d.REPORT_DATE,'%c/%e') as REPORT_DATE ,d.VERTICAL_VALUE_DIFF,d.HORIZONTAL_VALUE_DIFF  FROM jc_jc_data d where 1=1 ");
			sql.append(" and  PRJ_POINTS_UID = '"+map.get("code")+"'");
			sql.append(" and d.REPORT_DATE BETWEEN '"+map.get("time1")+"' and '"+map.get("time2")+"'");
			list = DBUtil.queryReturnList(conn, sql.toString());
			if(list.size()>0){
				List<Map<String ,String>> mmap = getTypeByCode(map);
				//String vtype = mmap.get(0).get("JC_TYPE_UID");
				if(null != mmap ){
					tstr = mmap.get(0).get("JC_TYPE_NAME");
					ustr = mmap.get(0).get("UNIT");

				}
				option.title(""+tstr+"变化值").tooltip(Trigger.axis).legend(""+tstr+"变化值("+ustr+")");
				// 横轴为值轴
				option.yAxis(new ValueAxis());
				
				// 创建类目轴
				CategoryAxis yAxis = new CategoryAxis();
			
				Bar bar = new Bar();
				
		        option.toolbox().show(true).feature(
		                Tool.saveAsImage).padding(20,50,0,0);
				// 循环数据
				for (Map<String, String> objectMap : list) {
					
					// 设置类目
					yAxis.data(objectMap.get("REPORT_DATE"));
					if("1".equals(map.get("type"))){
						// 类目对应的柱状图
						bar.data(objectMap.get("HORIZONTAL_VALUE_DIFF"));
					}else{
						bar.data(objectMap.get("VERTICAL_VALUE_DIFF"));
					}

				}
				// 设置类目轴
				option.xAxis(yAxis);
				
				// 设置数据
				option.series(bar);
				map1.put("option", option);
				map1.put("time1", map.get("time1"));
				map1.put("time2", map.get("time2"));
			}else{
				Map<String,String> map2 = getDateMap(map.get("code")+"");
				sql = new StringBuffer();
				sql.append("SELECT DATE_FORMAT(d.REPORT_DATE,'%c/%e') as REPORT_DATE ,d.VERTICAL_VALUE_DIFF,d.HORIZONTAL_VALUE_DIFF  FROM jc_jc_data d where 1=1 ");
				sql.append(" and  PRJ_POINTS_UID = '"+map.get("code")+"'");
				sql.append(" and d.REPORT_DATE BETWEEN '"+map2.get("time1")+"' and '"+map2.get("time2")+"'");
				list = DBUtil.queryReturnList(conn, sql.toString());
				List<Map<String ,String>> mmap = getTypeByCode(map);
				//String vtype = mmap.get(0).get("JC_TYPE_UID");
				if(null != mmap ){
					tstr = mmap.get(0).get("JC_TYPE_NAME");
					ustr = mmap.get(0).get("UNIT");

				}
				option.title(""+tstr+"变化值").tooltip(Trigger.axis).legend(""+tstr+"变化值("+ustr+")");
				// 横轴为值轴
				option.yAxis(new ValueAxis());
				
				// 创建类目轴
				CategoryAxis yAxis = new CategoryAxis();
			
				Bar bar = new Bar();
				
		        option.toolbox().show(true).feature(
		                Tool.saveAsImage).padding(20,50,0,0);
				// 循环数据
				for (Map<String, String> objectMap : list) {
					
					// 设置类目
					yAxis.data(objectMap.get("REPORT_DATE"));
					if("1".equals(map.get("type"))){
						// 类目对应的柱状图
						bar.data(objectMap.get("HORIZONTAL_VALUE_DIFF"));
					}else{
						bar.data(objectMap.get("VERTICAL_VALUE_DIFF"));
					}

				}
				// 设置类目轴
				option.xAxis(yAxis);
				
				// 设置数据
				option.series(bar);
				map1.put("option", option);
				map1.put("time1", map2.get("time1"));
				map1.put("time2", map2.get("time2"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return map1;
	}



	public Map<String,Object> queryTab(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection();
		Map<String,Object> retmap = new HashMap<String,Object>();
		List<Map<String,String>> list = null;
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT d.JC_DATA_UID,DATE_FORMAT(REPORT_DATE,'%Y-%m-%d') as REPORT_DATE");
			if("1".equals(map.get("type"))){
				sql.append(",d.HORIZONTAL_VALUE as V,d.HORIZONTAL_VALUE_DIFF as B");
			}else{
				sql.append(",d.VERTICAL_VALUE as V,d.VERTICAL_VALUE_DIFF as B");
			}
			
			sql.append(" FROM jc_jc_data d where 1=1 ");
			sql.append(" and  PRJ_POINTS_UID = '"+map.get("code")+"'");
			sql.append(" and d.REPORT_DATE BETWEEN '"+map.get("time1")+"' and '"+map.get("time2")+"'");
			
			list = DBUtil.queryReturnList(conn, sql.toString());
			if(list.size()>0){
				retmap.put("data", list);
				retmap.put("time1", map.get("time1"));
				retmap.put("time2", map.get("time2"));
			}else{
				Map<String,String> map1 = getDateMap(map.get("code")+"");
				sql = new StringBuffer();
				sql.append("SELECT d.JC_DATA_UID,DATE_FORMAT(REPORT_DATE,'%Y-%m-%d') as REPORT_DATE");
				if("1".equals(map.get("type"))){
					sql.append(",d.HORIZONTAL_VALUE as V,d.HORIZONTAL_VALUE_DIFF as B");
				}else{
					sql.append(",d.VERTICAL_VALUE as V,d.VERTICAL_VALUE_DIFF as B");
				}
				
				sql.append(" FROM jc_jc_data d where 1=1 ");
				sql.append(" and  PRJ_POINTS_UID = '"+map.get("code")+"'");
				sql.append(" and d.REPORT_DATE BETWEEN '"+map1.get("time1")+"' and '"+map1.get("time2")+"'");
				
				list = DBUtil.queryReturnList(conn, sql.toString());
				retmap.put("data", list);
				retmap.put("time1", map1.get("time1"));
				retmap.put("time2", map1.get("time2"));
			}


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
			csql.append(" SELECT DISTINCT(jt.JC_TYPE_NAME),ji.UNIT,ji.JC_TYPE_UID FROM jc_jc_data jd ");
			csql.append(" LEFT JOIN jc_prj_point_item ppi ");
			csql.append(" ON jd.PRJ_POINTS_UID = ppi.PRJ_POINTS_UID ");
			csql.append(" LEFT JOIN jc_prj_jc_item ji ");
			csql.append(" ON ppi.JC_PRJ_ITEM_UID = ji.JC_PRJ_ITEM_UID ");
			csql.append(" LEFT JOIN bu_jc_type jt ");
			csql.append(" ON ji.JC_TYPE_UID = jt.JC_TYPE_UID ");
			csql.append(" WHERE jd.PRJ_POINTS_UID = '"+map.get("code")+"'");
			csql.append(" and ji.JC_TYPE_UID = '"+map.get("type")+"'");
			
			list = DBUtil.queryReturnList(conn, csql.toString());
			

		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return list;
	}



	public JSONObject queryJcData(HashMap<String, String> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT DATE_FORMAT(REPORT_DATE,'%c/%e') as REPORT_DATE  ,VERTICAL_VALUE as VERTICAL_VALUE,VERTICAL_VALUE_DIFF as VERTICAL_VALUE_DIFF  FROM jc_jc_data d where 1=1 ");
			sql.append(" and  PRJ_POINTS_UID = '"+map.get("code")+"'");
			sql.append(" and  REPORT_DATE BETWEEN '"+map.get("time1")+"' and '"+map.get("time2")+"'");
			List<Map<String, String>> list = DBUtil.queryReturnList(conn, sql.toString());
			obj.put("state", true);
			obj.put("message", "查询结果成功");
			obj.put("data", JSONArray.toJSONString(list));

			
		} catch (Exception e) {
			obj.put("state", false);
			obj.put("message", "查询结果失败");
			DaoException.handleMessageException("*********查询出错!*********");
		}finally{
			DBUtil.closeConnetion(conn);
		}

		return obj;
		
	}

	/**
	 * 移动端数据查询工况和天气
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public JSONObject queryJcWorkAndWeather(HashMap<String, String> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();

		if (map.get("token").equals(MobileUtil.TOKEN)) {
			try {
				StringBuffer sql = new StringBuffer();
				sql.append(" SELECT wea.WEATHER_DESC,wea.MAX_TEMPERATURE,wea.MIN_TEMPERATURE,gk.JINDU FROM bu_project pro ");
				sql.append(" LEFT JOIN sys_region reg ON reg.REGION_CODE = pro.DISTRICT ");
				sql.append(" LEFT JOIN sys_weather wea on wea.CITY_CODE = reg.WEATHER_CODE ");
				sql.append(" LEFT JOIN pm_gongkuang gk on gk.PROJECT_UID = pro.PROJECT_UID ");
				sql.append(" WHERE pro.PROJECT_UID = '"+map.get("projectid")+"' ");
				sql.append(" AND wea.REPORT_DATE = (SELECT MAX(REPORT_DATE) FROM sys_weather WHERE sys_weather.CITY_CODE = reg.WEATHER_CODE) ");
				sql.append(" AND gk.CURRENT_Y = 'Y' ");
				List<Map<String, String>> list = DBUtil.queryReturnList(conn, sql.toString());
				obj = MobileUtil.getRightMessage(obj);
//				obj.put("data", JSONArray.toJSONString(list));
				Map<String,String> m = list.get(0);
				obj.put("condition", m.get("JINDU"));
				obj.put("weather", m.get("WEATHER_DESC"));
				obj.put("temperature", m.get("MIN_TEMPERATURE")+"~"+m.get("MAX_TEMPERATURE")+"℃");

			} catch (Exception e) {
				obj = MobileUtil.getErrorToken(obj);
				DaoException.handleMessageException("*********查询出错!*********");
			} finally {
				DBUtil.closeConnetion(conn);
			}

			return obj;

		} else {
			return MobileUtil.getErrorToken(obj);
		}
	}
	
	/**
	 * 移动端数据查询正常点
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public JSONObject queryJcNormalPoint(HashMap<String, String> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		if (map.get("token").equals(MobileUtil.TOKEN)) {
			try {
				
				if("all".equals(map.get("type"))){
					list = queryAll(conn,map);
				}else{
					list  = queryFocus(conn,map);
				}
				obj = MobileUtil.getRightMessage(obj);
				obj.put("pointList", list);

			} catch (Exception e) {
				obj = MobileUtil.getErrorToken(obj);
				e.printStackTrace();
				DaoException.handleMessageException("*********查询出错!*********");
			} finally {
				DBUtil.closeConnetion(conn);
			}

			return obj;

		} else {
			return MobileUtil.getErrorToken(obj);
		}
	}
	
	/**
	 * 移动端数据查询测点数据
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public JSONObject queryJcPointData(HashMap<String, String> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();

		if (map.get("token").equals(MobileUtil.TOKEN)) {
			try {
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT d.JC_DATA_UID,DATE_FORMAT(REPORT_DATE,'%Y-%m-%d') as testTime");
				if("1".equals(map.get("type"))){
					sql.append(",d.HORIZONTAL_VALUE as singleChange,d.HORIZONTAL_VALUE_DIFF as totalVariation");
				}else{
					sql.append(",d.VERTICAL_VALUE as singleChange,d.VERTICAL_VALUE_DIFF as totalVariation");
				}
				
				sql.append(" FROM jc_jc_data d  where 1=1 ");
				sql.append(" and d.PRJ_POINTS_UID = '"+map.get("pointId")+"'");
				sql.append(" and d.REPORT_DATE BETWEEN '"+map.get("startDate")+"' and '"+map.get("endDate")+"'");

				List<Map<String, String>> list = DBUtil.queryReturnList(conn, sql.toString());
				obj = MobileUtil.getRightMessage(obj);
				obj.put("pointDataList", list);

			} catch (Exception e) {
				obj = MobileUtil.getErrorToken(obj);
				DaoException.handleMessageException("*********查询出错!*********");
			} finally {
				DBUtil.closeConnetion(conn);
			}

			return obj;

		} else {
			return MobileUtil.getErrorToken(obj);
		}
	}
	
	/**
	 * 移动端数据查询测点折线图和柱状图数据
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public JSONObject queryJcCurveData(HashMap<String, String> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();

		if (map.get("token").equals(MobileUtil.TOKEN)) {
			try {
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT d.PRJ_POINTS_UID,d.JC_DATA_UID,DATE_FORMAT(REPORT_DATE,'%Y-%m-%d') as testTime");
				if("1".equals(map.get("type"))){
					sql.append(",d.HORIZONTAL_VALUE as singleChange,d.HORIZONTAL_VALUE_DIFF as totalVariation");
				}else{
					sql.append(",d.VERTICAL_VALUE as singleChange,d.VERTICAL_VALUE_DIFF as totalVariation");
				}
				
				sql.append(" FROM jc_jc_data d where 1=1 ");
				sql.append(" and  d.PRJ_POINTS_UID = '"+map.get("pointId")+"'");
				sql.append(" and  d.REPORT_DATE BETWEEN '"+map.get("startDate")+"' and '"+map.get("endDate")+"'");
				List<Map<String, String>> list = DBUtil.queryReturnList(conn, sql.toString());
				
				obj = MobileUtil.getRightMessage(obj);
				for (Map<String, String> map2 : list) {
					Boolean itemwarn = false;
					String singleChange = map2.get("singleChange").length()>0?map2.get("singleChange"):"0";
					String totalVariation = map2.get("totalVariation").length()>0?map2.get("totalVariation"):"0";
					List<Map<String, String>> list2 =  queryBj(map2.get("PRJ_POINTS_UID"),map.get("type"),map.get("projectId"));
					if(list2.size()>0){
						Map<String, String> map3 = list2.get(0);
						String singlewarn =  map3.get("SINGLE_WARN").length()>0?map3.get("SINGLE_WARN"):"0";	
						String totalwarn1 =  map3.get("TOTAL_WARN1").length()>0?map3.get("TOTAL_WARN1"):"0";	
						String totalwarn2 =  map3.get("TOTAL_WARN2").length()>0?map3.get("TOTAL_WARN2"):"0";	
						if("1".equals(map.get("type"))){

							if (Math.abs(Float.valueOf(singleChange)) > Float.valueOf(singlewarn)){
								itemwarn = true;
							}
							if (Float.valueOf(totalVariation) < Float.valueOf(totalwarn1) || Float.valueOf(totalVariation) > Float.valueOf(totalwarn2)){
								itemwarn = true;
									 
							}
						}else{

							if (Math.abs(Float.valueOf(singleChange)) > Float.valueOf(singlewarn)){
								itemwarn = true;
							}
							if (Float.valueOf(totalVariation) < Float.valueOf(totalwarn1) || Float.valueOf(totalVariation) > Float.valueOf(totalwarn2)){
								itemwarn = true;
							}	
						}
						map2.put("itemwarn", String.valueOf(itemwarn));
					}

					
				}

				obj.put("curveDataList", list);

			} catch (Exception e) {
				obj = MobileUtil.getErrorToken(obj);
				e.printStackTrace();
				DaoException.handleMessageException("*********查询出错!*********");
			} finally {
				DBUtil.closeConnetion(conn);
			}

			return obj;

		} else {
			return MobileUtil.getErrorToken(obj);
		}
	}
	
	/**
	 * 移动端数据查询测点信息
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public JSONObject queryJcPointInfo(HashMap<String, String> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();

		if (map.get("token").equals(MobileUtil.TOKEN)) {
			try {
				StringBuffer sql = new StringBuffer();
				sql.append(" SELECT pp.POINT_CODE,ji.SHORT_NAME as number,jo.OBJECT_NAME as monitorObject ,jt.JC_TYPE_NAME as monitorType, ");
				sql.append(" ji.JC_NAME as monitorProjectName,JI.UNIT as company, ");
				sql.append("  CASE WHEN jpp.SINGLE_WARN IS NOT NULL THEN jpp.SINGLE_WARN  ");
				sql.append(" WHEN jpp.SINGLE_WARN IS  NULL THEN ji.SINGLE_WARN END AS singleWarningValue, ");
				sql.append("  CASE WHEN jpp.TOTAL_WARN1 IS NOT NULL THEN jPP.TOTAL_WARN1   ");
				sql.append(" WHEN jpp.TOTAL_WARN1 IS NULL THEN ji.TOTAL_WARN1  END AS allWarningValue, ");
				sql.append(" CASE WHEN jpp.TOTAL_WARN2 IS NOT NULL THEN jPP.TOTAL_WARN2    ");
				sql.append(" WHEN jpp.TOTAL_WARN2 IS NULL THEN ji.TOTAL_WARN2  END AS TOTAL_WARN2 , ");
				sql.append(" pp.INIT_HEIGHT as altitude,pp.EQUIPMENT_UID as pumpDeviceModel,ji.JC_TYPE_UID ");
				sql.append(" FROM jc_prj_points pp LEFT JOIN jc_prj_point_item ppi ");
				sql.append(" ON ppi.PRJ_POINTS_UID = pp.PRJ_POINTS_UID ");
				sql.append(" LEFT JOIN jc_prj_jc_item ji ON ji.JC_PRJ_ITEM_UID = ppi.JC_PRJ_ITEM_UID ");
				sql.append(" LEFT JOIN bu_jc_object jo ON ji.JC_OBJECT_UID = jo.JC_OBJECT_UID ");
				sql.append(" LEFT JOIN bu_jc_type jt ON ji.JC_TYPE_UID = jt.JC_TYPE_UID ");
				sql.append(" LEFT JOIN jc_point_property jpp  ");
				sql.append("    ON jpp.PRJ_POINT_ITEM_UID = ppi.JC_PRJ_ITEM_UID  ");
				sql.append(" WHERE pp.POINT_CODE = '"+map.get("number")+"'");
				sql.append(" AND ji.JC_TYPE_UID = '"+map.get("typeUid")+"'");
				sql.append(" and pp.PROJECT_UID = '"+map.get("projectId")+"'");
				sql.append(" ORDER BY ji.JC_PRJ_ITEM_UID ASC ");

				List<Map<String, String>> list = DBUtil.queryReturnList(conn, sql.toString());
				obj = MobileUtil.getRightMessage(obj);
				obj.put("pointInfoList", list);

			} catch (Exception e) {
				obj = MobileUtil.getErrorToken(obj);
				DaoException.handleMessageException("*********查询出错!*********");
			} finally {
				DBUtil.closeConnetion(conn);
			}

			return obj;

		} else {
			return MobileUtil.getErrorToken(obj);
		}
	}


	/**
	 * 移动端获取项目列表
	 */
	public JSONObject queryProjectList(HashMap<String, String> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();

		if (map.get("token").equals(MobileUtil.TOKEN)) {
			try {
				StringBuffer sql = new StringBuffer();
				sql.append(" SELECT  ");
				sql.append(" 	p.PROJECT_UID as projectId,p.PROJECT_NAME as projectName,p.PROJECT_ADDRESS as projectPosition,p.BEGIN_DATE as projectDate,p.JIANZHU_MIANJI as projectArea, ");
				sql.append(" (SELECT a.FILE_PATH FROM bu_file f LEFT JOIN bu_attachment a ON f.ATTACHMENT_UID = a.ATTACHMENT_UID ");
				sql.append(" 	WHERE f.ENABLED = '1' AND f.FILE_TYPE = '10001'  ");
				sql.append(" 	AND f.TARGET_UID  =  p.PROJECT_UID  ORDER BY f.CREATE_DATE desc ");
				sql.append("  LIMIT 1 ");
				sql.append(" ) as photoUrl ");
				sql.append("  FROM bu_project p ");
				sql.append(" LEFT JOIN bu_project_user u ");
				sql.append(" ON p.PROJECT_UID = u.PROJECT_UID  ");
				sql.append(" WHERE u.USER_UID = '"+map.get("userId")+"' ");

				List<Map<String, String>> list = DBUtil.queryReturnList(conn, sql.toString());
				obj = MobileUtil.getRightMessage(obj);
				obj.put("project", list);

			} catch (Exception e) {
				obj = MobileUtil.getErrorToken(obj);
				DaoException.handleMessageException("*********查询出错!*********");
			} finally {
				DBUtil.closeConnetion(conn);
			}

			return obj;

		} else {
			return MobileUtil.getErrorToken(obj);
		}
	}


	// 在此可加入其它方法
	
	private  List<Map<String, String>> queryNewestDataByPointId_cx(String PRJ_POINTS_UID) {
	    Connection conn = DBUtil.getConnection();
	    List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	    try {
	    	StringBuffer sql = new StringBuffer();
	    	sql.append(" SELECT * FROM jc_cx_data d ");
	    	sql.append(" WHERE d.PRJ_POINTS_UID = '"+PRJ_POINTS_UID+"' ");
	    	sql.append(" AND d.REPORT_DATE = (SELECT MAX(REPORT_DATE) FROM jc_cx_data AS d1 WHERE d1.PRJ_POINTS_UID = '"+PRJ_POINTS_UID+"') ");
	    	sql.append(" ORDER BY d.DEEP ");
	       list =  DBUtil.queryReturnList(conn,sql.toString());
	       
	    } catch (Exception e) {
	        DaoException.handleMessageException("*********查询测斜测点最新数据出错!*********");
	    } finally {
	        DBUtil.closeConnetion(conn);
	    }
	    return list;
	
	}
	
	private List<Map<String, String>> queryNewestDataByPointId_jc(String PRJ_POINTS_UID) {
	    Connection conn = DBUtil.getConnection();
	    List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	    try {
	    	StringBuffer sql = new StringBuffer();
	    	sql.append(" SELECT * FROM jc_jc_data d ");
	    	sql.append(" WHERE d.PRJ_POINTS_UID = '"+PRJ_POINTS_UID+"' ");
	    	sql.append(" AND d.REPORT_DATE = (SELECT MAX(REPORT_DATE) FROM jc_jc_data AS d1 WHERE d1.PRJ_POINTS_UID = '"+PRJ_POINTS_UID+"') ");
	       list =  DBUtil.queryReturnList(conn,sql.toString());
	       
	    } catch (Exception e) {
	        DaoException.handleMessageException("*********查询沉降测点最新数据出错!*********");
	    } finally {
	        DBUtil.closeConnetion(conn);
	    }
	    return list;
	
	}
	
	/**
	 * 查询报警
	 * @param PRJ_POINTS_UID
	 * @return
	 */
	private List<Map<String,String>> queryBj(String PRJ_POINTS_UID,String type,String projectId){
		
	    Connection conn = DBUtil.getConnection();
	    List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	    try {
	    	StringBuffer sql = new StringBuffer();
	    	sql.append("  SELECT p.PRJ_POINTS_UID,p.POINT_CODE AS number ,i.JC_NAME,i.SHORT_NAME AS pointType, ");
	    	sql.append("  i.JC_TYPE_UID,ppi.PRJ_POINT_ITEM_UID, ");
	    	sql.append("  CASE WHEN pp.SINGLE_WARN IS NOT NULL THEN pp.SINGLE_WARN  ");
	    	sql.append(" WHEN pp.SINGLE_WARN IS  NULL THEN i.SINGLE_WARN END AS SINGLE_WARN, ");
	    	sql.append("  CASE WHEN pp.TOTAL_WARN1 IS NOT NULL THEN PP.TOTAL_WARN1   ");
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
	    	sql.append(" where p.POINT_CODE = '"+PRJ_POINTS_UID+"'");
	    	sql.append(" AND i.JC_TYPE_UID =  '"+type+"'");
	    	sql.append(" and p.PROJECT_UID = '"+projectId+"'");
	       list =  DBUtil.queryReturnList(conn,sql.toString());
	       
	    } catch (Exception e) {
	        DaoException.handleMessageException("*********查询沉降测点最新数据出错!*********");
	    } finally {
	        DBUtil.closeConnetion(conn);
	    }
	    return list;
		
	}
	
	
	private Map<String,String> getDateMap(String PRJ_POINTS_UID){
		Connection conn = DBUtil.getConnection();
		Map<String,String> map = null;
		try {
			String maxdateSql = "SELECT DATE_FORMAT(date_add(max(REPORT_DATE), interval -1 week),'%Y-%m-%d') as time1,DATE_FORMAT(max(REPORT_DATE),'%Y-%m-%d') as time2 FROM jc_jc_data d where PRJ_POINTS_UID = '"+PRJ_POINTS_UID+"'";
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




	public JSONObject querySurveyDataList(HashMap<String, String> map) {
		Connection conn = DBUtil.getConnection();
		List<Map<String,String>> list = null;
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.TOKEN)) {
			try {
				
				StringBuffer sql = new StringBuffer();
				list = getDates(map);
	
				if(list.size()>0){
					if(list.size()==1){
						Map<String,String> map2 = list.get(0);
						sql = new StringBuffer();
						sql.append(" SELECT DISTINCT(DEEP) as deep,a.HORIZONTAL_VALUE as firstValue,DATE_FORMAT(REPORT_DATE,'%Y-%m-%d') AS firstTime FROM jc_cx_data a ");
						sql.append(" where PRJ_POINTS_UID = '"+map.get("code")+"' ");
						sql.append(" and REPORT_DATE = '"+map2.get("REPORT_DATE")+"'");
					}else{
						sql = new StringBuffer();
						sql.append(" SELECT DISTINCT(a.DEEP) as deep,a.HORIZONTAL_VALUE as firstValue,b.HORIZONTAL_VALUE as lastValue, ");
						sql.append(" a.REPORT_DATE AS firstTime,b.REPORT_DATE AS lastTime FROM ");
						sql.append(" (SELECT DEEP ,d.HORIZONTAL_VALUE,DATE_FORMAT(REPORT_DATE,'%Y-%m-%d') as REPORT_DATE  FROM jc_cx_data d where 1=1  ");
						sql.append(" and PRJ_POINTS_UID = '"+map.get("code")+"' ");
						sql.append(" and d.REPORT_DATE =  ");
						sql.append(" (SELECT DATE_FORMAT(MIN(REPORT_DATE),'%Y-%m-%d')  FROM jc_cx_data d  ");
						sql.append(" WHERE d.REPORT_DATE >= '"+list.get(0).get("REPORT_DATE")+"')) a , ");
						sql.append(" (SELECT DEEP ,d.HORIZONTAL_VALUE,DATE_FORMAT(REPORT_DATE,'%Y-%m-%d') as REPORT_DATE   FROM jc_cx_data d where 1=1  ");
						sql.append(" and PRJ_POINTS_UID = '"+map.get("code")+"' ");
						sql.append(" and d.REPORT_DATE =  ");
						sql.append(" (SELECT DATE_FORMAT(MAX(REPORT_DATE),'%Y-%m-%d')  FROM jc_cx_data d  ");
						sql.append(" WHERE d.REPORT_DATE <= '"+list.get(list.size()-1).get("REPORT_DATE")+"'))b ");
						sql.append(" WHERE a.DEEP = b.DEEP ");
					}
					
					
				}else{
					Map<String,String> map2 = getDateMap(map.get("code")+"");
					map2.put("code", map.get("code")+"");
					list = getDates(map2);
					if(list.size()==1){
						Map<String,String> map3 = list.get(0);
						sql = new StringBuffer();
						sql.append(" SELECT DISTINCT(DEEP) as deep,a.HORIZONTAL_VALUE as firstValue,DATE_FORMAT(REPORT_DATE,'%Y-%m-%d') AS firstTime FROM jc_cx_data a ");
						sql.append(" where PRJ_POINTS_UID = '"+map.get("code")+"' ");
						sql.append(" and REPORT_DATE = '"+map3.get("REPORT_DATE")+"'");
					}else{
						sql = new StringBuffer();
						sql.append(" SELECT DISTINCT(a.DEEP) as deep,a.HORIZONTAL_VALUE as firstValue,b.HORIZONTAL_VALUE as lastValue, ");
						sql.append(" a.REPORT_DATE AS firstTime,b.REPORT_DATE AS lastTime FROM ");
						sql.append(" (SELECT DEEP ,d.HORIZONTAL_VALUE,DATE_FORMAT(REPORT_DATE,'%Y-%m-%d') as REPORT_DATE  FROM jc_cx_data d where 1=1  ");
						sql.append(" and PRJ_POINTS_UID = '"+map.get("code")+"' ");
						sql.append(" and d.REPORT_DATE =  ");
						sql.append(" (SELECT DATE_FORMAT(MIN(REPORT_DATE),'%Y-%m-%d')  FROM jc_cx_data d  ");
						sql.append(" WHERE d.REPORT_DATE >= '"+list.get(0).get("REPORT_DATE")+"')) a , ");
						sql.append(" (SELECT DEEP ,d.HORIZONTAL_VALUE,DATE_FORMAT(REPORT_DATE,'%Y-%m-%d') as REPORT_DATE   FROM jc_cx_data d where 1=1  ");
						sql.append(" and PRJ_POINTS_UID = '"+map.get("code")+"' ");
						sql.append(" and d.REPORT_DATE =  ");
						sql.append(" (SELECT DATE_FORMAT(MAX(REPORT_DATE),'%Y-%m-%d')  FROM jc_cx_data d  ");
						sql.append(" WHERE d.REPORT_DATE <= '"+list.get(list.size()-1).get("REPORT_DATE")+"'))b ");
						sql.append(" WHERE a.DEEP = b.DEEP ");
					}
	
					
				}
				list = DBUtil.queryReturnList(conn, sql.toString());
				obj = MobileUtil.getRightMessage(obj);
				obj.put("surveyDataList", list);
	
			} catch (Exception e) {
				obj = MobileUtil.getErrorToken(obj);
				DaoException.handleMessageException("*********查询出错!*********");
			} finally {
				DBUtil.closeConnetion(conn);
			}
	
			return obj;

		} else {
			return MobileUtil.getErrorToken(obj);
		}
	}
	
	/**
	 * 查询所有点
	 * @param conn
	 * @param map
	 * @return
	 * @throws Exception
	 */
	private List<Map<String,String>> queryAll(Connection conn,Map<String,String> map) throws Exception{
		
		StringBuffer sql = new StringBuffer();
		sql.append("  SELECT p.PRJ_POINTS_UID,p.POINT_CODE as number,i.JC_NAME,i.SHORT_NAME as pointType, ");
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
		sql.append("    where p.PROJECT_UID = '"+map.get("project_uid")+"' ");
		List<Map<String, String>> list = DBUtil.queryReturnList(conn, sql.toString());
		
		
		for (Map<String, String> map2 : list) {
			Boolean itemwarn = false;
			String alarmValue = "";
			String alarmType = "";
			String singleChange = "";
			String accumulate = "";
			List<Object> diff = new ArrayList<Object>();
			List<Object> single = new ArrayList<Object>();
			if(map2.get("JC_NAME").indexOf("测斜") >= 0 || map2.get("pointType").indexOf("测斜") >= 0){
				List<Map<String, String>> list2 = queryNewestDataByPointId_cx(map2.get("PRJ_POINTS_UID"));
				for (Map<String, String> map3 : list2) {
					if (Math.abs(Float.valueOf(map3.get("HORIZONTAL_VALUE_DIFF"))) > Float.valueOf(map2.get("SINGLE_WARN"))){
							itemwarn = true;
							alarmValue = map2.get("SINGLE_WARN");
							alarmType = "单次预警值";
					}
					if (Float.valueOf(map3.get("HORIZONTAL_VALUE")) < Float.valueOf(map2.get("TOTAL_WARN1")) || Float.valueOf(map3.get("HORIZONTAL_VALUE")) > Float.valueOf(map2.get("TOTAL_WARN2"))){
							itemwarn = true;
							if(Float.valueOf(map3.get("HORIZONTAL_VALUE")) < Float.valueOf(map2.get("TOTAL_WARN1"))){
								alarmValue = map2.get("TOTAL_WARN1");
								alarmType = "累计值(正方向变化值)";
							}else{
								alarmValue = map2.get("TOTAL_WARN2");
								alarmType = "累计值(负方向变化值)";
							}
							
							
					}
					diff.add(map3.get("HORIZONTAL_VALUE_DIFF"));
					single.add(map3.get("HORIZONTAL_VALUE"));
					
				}
			}else{
				List<Map<String, String>> list2 =  queryNewestDataByPointId_jc(map2.get("PRJ_POINTS_UID"));
				if(list2.size()>0){
					Map<String, String> map3 = list2.get(0);
					
					if(map2.get("JC_NAME").indexOf("水平") >= 0 || map2.get("pointType").indexOf("水平") >= 0){

						String h_value_diff = map3.get("HORIZONTAL_VALUE").length()>0?map3.get("HORIZONTAL_VALUE"):"0";
						String h_value = map3.get("HORIZONTAL_VALUE_DIFF").length()>0?map3.get("HORIZONTAL_VALUE_DIFF"):"0";
						
						if (Math.abs(Float.valueOf(h_value_diff)) > Float.valueOf(map2.get("SINGLE_WARN"))){
							itemwarn = true;
							alarmValue = map2.get("SINGLE_WARN");
							alarmType = "单次预警值";
						}
						if (Float.valueOf(h_value) < Float.valueOf(map2.get("TOTAL_WARN1")) || Float.valueOf(h_value) > Float.valueOf(map2.get("TOTAL_WARN2"))){
							itemwarn = true;
							if(Float.valueOf(h_value) < Float.valueOf(map2.get("TOTAL_WARN1")) ){
								alarmValue = map2.get("TOTAL_WARN1");
								alarmType = "累计值(正方向变化值)";
							}else{
								alarmValue = map2.get("TOTAL_WARN2");
								alarmType = "累计值(负方向变化值)";
							}
							 
						}
						singleChange = h_value_diff;
						accumulate = h_value;
					}else{
						String v_value = map3.get("VERTICAL_VALUE").length()>0?map3.get("VERTICAL_VALUE"):"0";
						String v_value_diff = map3.get("VERTICAL_VALUE_DIFF").length()>0?map3.get("VERTICAL_VALUE_DIFF"):"0";

						if (Math.abs(Float.valueOf(v_value_diff)) > Float.valueOf(map2.get("SINGLE_WARN"))){
							itemwarn = true;
							alarmValue = map2.get("SINGLE_WARN");
							alarmType = "单次预警值";
						}
						if (Float.valueOf(v_value) < Float.valueOf(map2.get("TOTAL_WARN1")) || Float.valueOf(v_value) > Float.valueOf(map2.get("TOTAL_WARN2"))){
							itemwarn = true;
							if(Float.valueOf(v_value) < Float.valueOf(map2.get("TOTAL_WARN1"))){
								alarmValue = map2.get("TOTAL_WARN1");
								alarmType = "累计值(正方向变化值)";
							}else{
								alarmValue = map2.get("TOTAL_WARN2");
								alarmType = "累计值(负方向变化值)";
							}
							
						}	
						singleChange = v_value_diff;
						accumulate = v_value;
					}
				}


			}
			map2.put("itemwarn", String.valueOf(itemwarn));
			map2.put("alarmValue", alarmValue);
			map2.put("alarmType",alarmType);
			map2.put("singleChange", singleChange);
			map2.put("accumulate", accumulate);
			map2.put("cxDiff", JSONObject.toJSONString(diff));
			map2.put("cxSingle", JSONObject.toJSONString(single));
		}
		return list;
	}
	
	/**
	 * 查询关注点
	 * @param conn
	 * @param map
	 * @return
	 * @throws Exception
	 */
	private List<Map<String,String>> queryFocus(Connection conn,Map<String,String> map) throws Exception{
		StringBuffer sql = new StringBuffer();
/*		sql.append("  SELECT p.PRJ_POINTS_UID,p.POINT_CODE as number,i.JC_NAME,i.SHORT_NAME as pointType, ");
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
		sql.append("    LEFT JOIN jc_user_focus_point f ");
		sql.append("    ON p.PRJ_POINTS_UID = f.PRJ_POINTS_UID ");
		sql.append("    where p.PROJECT_UID = '"+map.get("project_uid")+"' ");
		sql.append(" and f.USER_UID = '"+map.get("userId")+"'");*/
		sql.append(" SELECT a.PRJ_POINTS_UID,a.POINT_CODE as number,b.JC_NAME, ");
		sql.append(" b.SHORT_NAME as pointType,  ");
		sql.append("  b.JC_TYPE_UID,b.PRJ_POINT_ITEM_UID, ");
		sql.append(" SINGLE_WARN,TOTAL_WARN1,TOTAL_WARN2 ");
		sql.append("  FROM jc_prj_points a LEFT JOIN ");
		sql.append(" (SELECT ppi.PRJ_POINTS_UID,ppi.PRJ_POINT_ITEM_UID,pji.JC_NAME, ");
		sql.append(" pji.SHORT_NAME,pji.JC_TYPE_UID,  ");
		sql.append("  IFNULL(pp.SINGLE_WARN,pji.SINGLE_WARN) as SINGLE_WARN , ");
		sql.append("  IFNULL(pp.TOTAL_WARN1,pji.TOTAL_WARN1) as TOTAL_WARN1, ");
		sql.append("  IFNULL(pp.TOTAL_WARN2,pji.TOTAL_WARN2) as TOTAL_WARN2 ");
		sql.append("   FROM jc_prj_point_item ppi  ");
		sql.append("  LEFT JOIN jc_prj_jc_item pji  ");
		sql.append("  ON ppi.JC_PRJ_ITEM_UID = pji.JC_PRJ_ITEM_UID  ");
		sql.append("  LEFT JOIN jc_point_property pp  ");
		sql.append("  ON ppi.PRJ_POINT_ITEM_UID = pp.PRJ_POINT_ITEM_UID  ");
		sql.append("  WHERE  pji.PROJECT_UID = '"+map.get("project_uid")+"' ) b ");
		sql.append(" ON a.PRJ_POINTS_UID = b.PRJ_POINTS_UID ");
		sql.append(" LEFT JOIN jc_user_focus_point f ");
		sql.append(" ON a.PRJ_POINTS_UID = f.PRJ_POINTS_UID ");
		sql.append(" WHERE a.PROJECT_UID = '"+map.get("project_uid")+"' ");
		sql.append(" AND f.USER_UID = '"+map.get("userId")+"' ");
		List<Map<String, String>> list = DBUtil.queryReturnList(conn, sql.toString());
		
		for (Map<String, String> map2 : list) {
			Boolean itemwarn = false;
			String alarmValue = "";
			String alarmType = "";
			String singleChange = "";
			String accumulate = "";
			List<Object> diff = new ArrayList<Object>();
			List<Object> single = new ArrayList<Object>();
			if(map2.get("JC_NAME").indexOf("测斜") >= 0 || map2.get("pointType").indexOf("测斜") >= 0){
				List<Map<String, String>> list2 = queryNewestDataByPointId_cx(map2.get("PRJ_POINTS_UID"));
				for (Map<String, String> map3 : list2) {
					if (Math.abs(Float.valueOf(map3.get("HORIZONTAL_VALUE_DIFF"))) > Float.valueOf(map2.get("SINGLE_WARN"))){
							itemwarn = true;
							alarmValue = map2.get("SINGLE_WARN");
							alarmType = "单次预警值";
					}
					if (Float.valueOf(map3.get("HORIZONTAL_VALUE")) < Float.valueOf(map2.get("TOTAL_WARN1")) || Float.valueOf(map3.get("HORIZONTAL_VALUE")) > Float.valueOf(map2.get("TOTAL_WARN2"))){
							itemwarn = true;
							if(Float.valueOf(map3.get("HORIZONTAL_VALUE")) < Float.valueOf(map2.get("TOTAL_WARN1"))){
								alarmValue = map2.get("TOTAL_WARN1");
								alarmType = "累计值(正方向变化值)";
							}else{
								alarmValue = map2.get("TOTAL_WARN2");
								alarmType = "累计值(负方向变化值)";
							}
							
							
					}
					diff.add(map3.get("HORIZONTAL_VALUE_DIFF"));
					single.add(map3.get("HORIZONTAL_VALUE"));
					
				}
			}else{
				List<Map<String, String>> list2 =  queryNewestDataByPointId_jc(map2.get("PRJ_POINTS_UID"));
				if(list2.size()>0){
					Map<String, String> map3 = list2.get(0);
					
					if(map2.get("JC_NAME").indexOf("水平") >= 0 || map2.get("pointType").indexOf("水平") >= 0){

						String h_value_diff = map3.get("HORIZONTAL_VALUE").length()>0?map3.get("HORIZONTAL_VALUE"):"0";
						String h_value = map3.get("HORIZONTAL_VALUE_DIFF").length()>0?map3.get("HORIZONTAL_VALUE_DIFF"):"0";
						
						if (Math.abs(Float.valueOf(h_value_diff)) > Float.valueOf(map2.get("SINGLE_WARN"))){
							itemwarn = true;
							alarmValue = map2.get("SINGLE_WARN");
							alarmType = "单次预警值";
						}
						if (Float.valueOf(h_value) < Float.valueOf(map2.get("TOTAL_WARN1")) || Float.valueOf(h_value) > Float.valueOf(map2.get("TOTAL_WARN2"))){
							itemwarn = true;
							if(Float.valueOf(h_value) < Float.valueOf(map2.get("TOTAL_WARN1")) ){
								alarmValue = map2.get("TOTAL_WARN1");
								alarmType = "累计值(正方向变化值)";
							}else{
								alarmValue = map2.get("TOTAL_WARN2");
								alarmType = "累计值(负方向变化值)";
							}
							 
						}
						singleChange = h_value_diff;
						accumulate = h_value;
					}else{
						String v_value = map3.get("VERTICAL_VALUE").length()>0?map3.get("VERTICAL_VALUE"):"0";
						String v_value_diff = map3.get("VERTICAL_VALUE_DIFF").length()>0?map3.get("VERTICAL_VALUE_DIFF"):"0";

						if (Math.abs(Float.valueOf(v_value_diff)) > Float.valueOf(map2.get("SINGLE_WARN"))){
							itemwarn = true;
							alarmValue = map2.get("SINGLE_WARN");
							alarmType = "单次预警值";
						}
						if (Float.valueOf(v_value) < Float.valueOf(map2.get("TOTAL_WARN1")) || Float.valueOf(v_value) > Float.valueOf(map2.get("TOTAL_WARN2"))){
							itemwarn = true;
							if(Float.valueOf(v_value) < Float.valueOf(map2.get("TOTAL_WARN1"))){
								alarmValue = map2.get("TOTAL_WARN1");
								alarmType = "累计值(正方向变化值)";
							}else{
								alarmValue = map2.get("TOTAL_WARN2");
								alarmType = "累计值(负方向变化值)";
							}
							
						}	
						singleChange = v_value_diff;
						accumulate = v_value;
					}
				}


			}
			map2.put("itemwarn", String.valueOf(itemwarn));
			map2.put("alarmValue", alarmValue);
			map2.put("alarmType",alarmType);
			map2.put("singleChange", singleChange);
			map2.put("accumulate", accumulate);
			map2.put("cxDiff", JSONObject.toJSONString(diff));
			map2.put("cxSingle", JSONObject.toJSONString(single));
		}
		return list;
	}
	
	

}
