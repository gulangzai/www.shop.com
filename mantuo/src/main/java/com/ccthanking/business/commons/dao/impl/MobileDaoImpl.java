/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.commons.SysUserDao.java
 * 创建日期： 2015-10-26 下午 10:31:55
 * 功能：   用户信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-26 下午 10:31:55  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.commons.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ccthanking.business.commons.dao.MobileDao;
import com.ccthanking.business.loguserread.vo.LogUserReadVO;
import com.ccthanking.business.pmcailiao.vo.PmCailiaoVO;
import com.ccthanking.business.pmjiancha.vo.PmXunjianStrucVO;
import com.ccthanking.business.pmjiancha.vo.PmXunjianVO;
import com.ccthanking.business.pmxianchang.vo.PmXianchangDafuVO;
import com.ccthanking.business.pmxianchang.vo.PmXianchangVO;
import com.ccthanking.business.pmyanshou.vo.PmYanshouVO;
import com.ccthanking.business.project.vo.PmBiangengVO;
import com.ccthanking.business.projectlog.vo.PmProjectLogStrucVO;
import com.ccthanking.business.projectlog.vo.PmProjectLogVO;
import com.ccthanking.business.projectlog.vo.PmProjectTopicReplyVO;
import com.ccthanking.business.projectlog.vo.PmProjectTopicVO;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.util.MobileFileUpload;
import com.ccthanking.framework.util.MobileUtil;
import com.copj.modules.utils.exception.DaoException;

/**
 * <p>
 * MobileDaoImpl.java
 * </p>
 * <p>
 * 功能：用户信息
 * </p>
 *
 * <p>
 * <a href="MobileDaoImpl.java.html"><i>查看源代码</i></a>
 * </p>
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-26
 * 
 */

@Component
public class MobileDaoImpl extends BsBaseDaoTJdbc implements MobileDao {
	public JSONObject queryJcData(HashMap<String, String> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(
					"SELECT DATE_FORMAT(REPORT_DATE,'%c/%e') as REPORT_DATE  ,VERTICAL_VALUE as VERTICAL_VALUE,VERTICAL_VALUE_DIFF as VERTICAL_VALUE_DIFF  FROM jc_jc_data d where 1=1 ");
			sql.append(" and  PRJ_POINTS_UID = '" + map.get("code") + "'");
			sql.append(" and  REPORT_DATE BETWEEN '" + map.get("time1") + "' and '" + map.get("time2") + "'");
			List<Map<String, String>> list = DBUtil.queryReturnList(conn, sql.toString());
			obj.put("state", true);
			obj.put("message", "查询结果成功");
			obj.put("data", JSONArray.toJSONString(list));

		} catch (Exception e) {
			obj.put("state", false);
			obj.put("message", "查询结果失败");
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
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
				sql.append(
						" SELECT wea.WEATHER_DESC,wea.MAX_TEMPERATURE,wea.MIN_TEMPERATURE,gk.JINDU FROM bu_project pro ");
				sql.append(" LEFT JOIN sys_region reg ON reg.REGION_CODE = pro.DISTRICT ");
				sql.append(" LEFT JOIN sys_weather wea on wea.CITY_CODE = reg.WEATHER_CODE ");
				sql.append(" LEFT JOIN pm_gongkuang gk on gk.PROJECT_UID = pro.PROJECT_UID ");
				sql.append(" WHERE pro.PROJECT_UID = '" + map.get("projectid") + "' ");
				sql.append(
						" AND wea.REPORT_DATE = (SELECT MAX(REPORT_DATE) FROM sys_weather WHERE sys_weather.CITY_CODE = reg.WEATHER_CODE) ");
				sql.append(" AND gk.CURRENT_Y = 'Y' ");
				List<Map<String, String>> list = DBUtil.queryReturnList(conn, sql.toString());
				obj = MobileUtil.getRightMessage(obj);
				// obj.put("data", JSONArray.toJSONString(list));
				if(list.size()>0){
					Map<String, String> m = list.get(0);
					obj.put("condition", m.get("JINDU"));
					obj.put("weather", m.get("WEATHER_DESC"));
					obj.put("temperature",
							m.get("MIN_TEMPERATURE") + "~" + m.get("MAX_TEMPERATURE") + "℃");
				}
				

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
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		if (map.get("token").equals(MobileUtil.TOKEN)) {
			try {

				if ("all".equals(map.get("type"))) {
					list = queryAll(conn, map);
				} else {
					list = queryFocus(conn, map);
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
				if ("1".equals(map.get("type"))) {
					sql.append(",d.HORIZONTAL_VALUE as singleChange,d.HORIZONTAL_VALUE_DIFF as totalVariation");
				} else {
					sql.append(",d.VERTICAL_VALUE as singleChange,d.VERTICAL_VALUE_DIFF as totalVariation");
				}

				sql.append(" FROM jc_jc_data d  where 1=1 ");
				sql.append(" and d.PRJ_POINTS_UID = '" + map.get("pointId") + "'");
				sql.append(
						" and d.REPORT_DATE BETWEEN '" + map.get("startDate") + "' and '" + map.get("endDate") + "'");

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
				if ("1".equals(map.get("type"))) {
					sql.append(",d.HORIZONTAL_VALUE as singleChange,d.HORIZONTAL_VALUE_DIFF as totalVariation");
				} else {
					sql.append(",d.VERTICAL_VALUE as singleChange,d.VERTICAL_VALUE_DIFF as totalVariation");
				}

				sql.append(" FROM jc_jc_data d where 1=1 ");
				sql.append(" and  d.PRJ_POINTS_UID = '" + map.get("pointId") + "'");
				sql.append(
						" and  d.REPORT_DATE BETWEEN '" + map.get("startDate") + "' and '" + map.get("endDate") + "'");
				List<Map<String, String>> list = DBUtil.queryReturnList(conn, sql.toString());

				obj = MobileUtil.getRightMessage(obj);
				for (Map<String, String> map2 : list) {
					Boolean itemwarn = false;
					String singleChange = map2.get("singleChange").length() > 0 ? map2.get("singleChange") : "0";
					String totalVariation = map2.get("totalVariation").length() > 0 ? map2.get("totalVariation") : "0";
					List<Map<String, String>> list2 = queryBj(map2.get("PRJ_POINTS_UID"), map.get("type"),
							map.get("projectId"));
					if (list2.size() > 0) {
						Map<String, String> map3 = list2.get(0);
						String singlewarn = map3.get("SINGLE_WARN").length() > 0 ? map3.get("SINGLE_WARN") : "0";
						String totalwarn1 = map3.get("TOTAL_WARN1").length() > 0 ? map3.get("TOTAL_WARN1") : "0";
						String totalwarn2 = map3.get("TOTAL_WARN2").length() > 0 ? map3.get("TOTAL_WARN2") : "0";
						if ("1".equals(map.get("type"))) {

							if (Math.abs(Float.valueOf(singleChange)) > Float.valueOf(singlewarn)) {
								itemwarn = true;
							}
							if (Float.valueOf(totalVariation) < Float.valueOf(totalwarn1)
									|| Float.valueOf(totalVariation) > Float.valueOf(totalwarn2)) {
								itemwarn = true;

							}
						} else {

							if (Math.abs(Float.valueOf(singleChange)) > Float.valueOf(singlewarn)) {
								itemwarn = true;
							}
							if (Float.valueOf(totalVariation) < Float.valueOf(totalwarn1)
									|| Float.valueOf(totalVariation) > Float.valueOf(totalwarn2)) {
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
				sql.append(
						" SELECT pp.POINT_CODE,ji.SHORT_NAME as number,jo.OBJECT_NAME as monitorObject ,jt.JC_TYPE_NAME as monitorType, ");
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
				sql.append(" WHERE pp.POINT_CODE = '" + map.get("number") + "'");
				sql.append(" AND ji.JC_TYPE_UID = '" + map.get("typeUid") + "'");
				sql.append(" and pp.PROJECT_UID = '" + map.get("projectId") + "'");
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

		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			try {
				StringBuffer sql = new StringBuffer();
				sql.append(" SELECT  ");
				sql.append(
						" 	p.PROJECT_UID as projectId,p.PROJECT_NAME as projectName,p.PROJECT_ADDRESS as projectPosition,p.BEGIN_DATE as projectDate,p.JIANZHU_MIANJI as projectArea, ");
				sql.append(
						" (SELECT a.FILE_PATH FROM bu_file f LEFT JOIN bu_attachment a ON f.ATTACHMENT_UID = a.ATTACHMENT_UID ");
				sql.append(" 	WHERE f.ENABLED = '1' AND f.FILE_TYPE = '10001'  ");
				sql.append(" 	AND f.TARGET_UID  =  p.PROJECT_UID  ORDER BY f.CREATE_DATE desc ");
				sql.append("  LIMIT 1 ");
				sql.append(" ) as photoUrl ");
				sql.append("  FROM bu_project p ");
				sql.append(" LEFT JOIN bu_project_user u ");
				sql.append(" ON p.PROJECT_UID = u.PROJECT_UID  ");
				sql.append(" WHERE u.USER_UID = '" + map.get("userId") + "' ");

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

	/**
	 * 查询所有点
	 * 
	 * @param conn
	 * @param map
	 * @return
	 * @throws Exception
	 */
	private List<Map<String, String>> queryAll(Connection conn, Map<String, String> map) throws Exception {

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
		sql.append("    where p.PROJECT_UID = '" + map.get("project_uid") + "' ");*/
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
		sql.append("  WHERE  pji.PROJECT_UID = '" + map.get("project_uid") + "' ) b ");
		sql.append(" ON a.PRJ_POINTS_UID = b.PRJ_POINTS_UID ");
		sql.append(" WHERE a.PROJECT_UID = '" + map.get("project_uid") + "' ");
		List<Map<String, String>> list = DBUtil.queryReturnList(conn, sql.toString());

		for (Map<String, String> map2 : list) {
			Boolean itemwarn = false;
			String alarmValue = "";
			String alarmType = "";
			String singleChange = "";
			String accumulate = "";
			List<Object> diff = new ArrayList<Object>();
			List<Object> single = new ArrayList<Object>();
			if (map2.get("JC_NAME").indexOf("测斜") >= 0 || map2.get("pointType").indexOf("测斜") >= 0) {
				List<Map<String, String>> list2 = queryNewestDataByPointId_cx(map2.get("PRJ_POINTS_UID"));
				for (Map<String, String> map3 : list2) {
					if (Math.abs(Float.valueOf(map3.get("HORIZONTAL_VALUE_DIFF"))) > Float
							.valueOf(map2.get("SINGLE_WARN"))) {
						itemwarn = true;
						alarmValue = map2.get("SINGLE_WARN");
						alarmType = "单次预警值";
					}
					if (Float.valueOf(map3.get("HORIZONTAL_VALUE")) < Float.valueOf(map2.get("TOTAL_WARN1"))
							|| Float.valueOf(map3.get("HORIZONTAL_VALUE")) > Float.valueOf(map2.get("TOTAL_WARN2"))) {
						itemwarn = true;
						if (Float.valueOf(map3.get("HORIZONTAL_VALUE")) < Float.valueOf(map2.get("TOTAL_WARN1"))) {
							alarmValue = map2.get("TOTAL_WARN1");
							alarmType = "累计值(正方向变化值)";
						} else {
							alarmValue = map2.get("TOTAL_WARN2");
							alarmType = "累计值(负方向变化值)";
						}

					}
					diff.add(map3.get("HORIZONTAL_VALUE_DIFF"));
					single.add(map3.get("HORIZONTAL_VALUE"));

				}
			} else {
				List<Map<String, String>> list2 = queryNewestDataByPointId_jc(map2.get("PRJ_POINTS_UID"));
				if (list2.size() > 0) {
					Map<String, String> map3 = list2.get(0);

					if (map2.get("JC_NAME").indexOf("水平") >= 0 || map2.get("pointType").indexOf("水平") >= 0) {

						String h_value_diff = map3.get("HORIZONTAL_VALUE").length() > 0 ? map3.get("HORIZONTAL_VALUE")
								: "0";
						String h_value = map3.get("HORIZONTAL_VALUE_DIFF").length() > 0
								? map3.get("HORIZONTAL_VALUE_DIFF") : "0";

						if (Math.abs(Float.valueOf(h_value_diff)) > Float.valueOf(map2.get("SINGLE_WARN"))) {
							itemwarn = true;
							alarmValue = map2.get("SINGLE_WARN");
							alarmType = "单次预警值";
						}
						if (Float.valueOf(h_value) < Float.valueOf(map2.get("TOTAL_WARN1"))
								|| Float.valueOf(h_value) > Float.valueOf(map2.get("TOTAL_WARN2"))) {
							itemwarn = true;
							if (Float.valueOf(h_value) < Float.valueOf(map2.get("TOTAL_WARN1"))) {
								alarmValue = map2.get("TOTAL_WARN1");
								alarmType = "累计值(正方向变化值)";
							} else {
								alarmValue = map2.get("TOTAL_WARN2");
								alarmType = "累计值(负方向变化值)";
							}

						}
						singleChange = h_value_diff;
						accumulate = h_value;
					} else {
						String v_value = map3.get("VERTICAL_VALUE").length() > 0 ? map3.get("VERTICAL_VALUE") : "0";
						String v_value_diff = map3.get("VERTICAL_VALUE_DIFF").length() > 0
								? map3.get("VERTICAL_VALUE_DIFF") : "0";

						if (Math.abs(Float.valueOf(v_value_diff)) > Float.valueOf(map2.get("SINGLE_WARN"))) {
							itemwarn = true;
							alarmValue = map2.get("SINGLE_WARN");
							alarmType = "单次预警值";
						}
						if (Float.valueOf(v_value) < Float.valueOf(map2.get("TOTAL_WARN1"))
								|| Float.valueOf(v_value) > Float.valueOf(map2.get("TOTAL_WARN2"))) {
							itemwarn = true;
							if (Float.valueOf(v_value) < Float.valueOf(map2.get("TOTAL_WARN1"))) {
								alarmValue = map2.get("TOTAL_WARN1");
								alarmType = "累计值(正方向变化值)";
							} else {
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
			map2.put("alarmType", alarmType);
			map2.put("singleChange", singleChange);
			map2.put("accumulate", accumulate);
			map2.put("cxDiff", JSONObject.toJSONString(diff));
			map2.put("cxSingle", JSONObject.toJSONString(single));
		}
		//System.out.println(list);
		return list;
	}

	/**
	 * 查询关注点
	 * 
	 * @param conn
	 * @param map
	 * @return
	 * @throws Exception
	 */
	private List<Map<String, String>> queryFocus(Connection conn, Map<String, String> map) throws Exception {
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
		sql.append("    where p.PROJECT_UID = '" + map.get("project_uid") + "' ");
		sql.append(" and f.USER_UID = '" + map.get("userId") + "'");*/
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
			if (map2.get("JC_NAME").indexOf("测斜") >= 0 || map2.get("pointType").indexOf("测斜") >= 0) {
				List<Map<String, String>> list2 = queryNewestDataByPointId_cx(map2.get("PRJ_POINTS_UID"));
				for (Map<String, String> map3 : list2) {
					if (Math.abs(Float.valueOf(map3.get("HORIZONTAL_VALUE_DIFF"))) > Float
							.valueOf(map2.get("SINGLE_WARN"))) {
						itemwarn = true;
						alarmValue = map2.get("SINGLE_WARN");
						alarmType = "单次预警值";
					}
					if (Float.valueOf(map3.get("HORIZONTAL_VALUE")) < Float.valueOf(map2.get("TOTAL_WARN1"))
							|| Float.valueOf(map3.get("HORIZONTAL_VALUE")) > Float.valueOf(map2.get("TOTAL_WARN2"))) {
						itemwarn = true;
						if (Float.valueOf(map3.get("HORIZONTAL_VALUE")) < Float.valueOf(map2.get("TOTAL_WARN1"))) {
							alarmValue = map2.get("TOTAL_WARN1");
							alarmType = "累计值(正方向变化值)";
						} else {
							alarmValue = map2.get("TOTAL_WARN2");
							alarmType = "累计值(负方向变化值)";
						}

					}
					diff.add(map3.get("HORIZONTAL_VALUE_DIFF"));
					single.add(map3.get("HORIZONTAL_VALUE"));

				}
			} else {
				List<Map<String, String>> list2 = queryNewestDataByPointId_jc(map2.get("PRJ_POINTS_UID"));
				if (list2.size() > 0) {
					Map<String, String> map3 = list2.get(0);

					if (map2.get("JC_NAME").indexOf("水平") >= 0 || map2.get("pointType").indexOf("水平") >= 0) {

						String h_value_diff = map3.get("HORIZONTAL_VALUE").length() > 0 ? map3.get("HORIZONTAL_VALUE")
								: "0";
						String h_value = map3.get("HORIZONTAL_VALUE_DIFF").length() > 0
								? map3.get("HORIZONTAL_VALUE_DIFF") : "0";

						if (Math.abs(Float.valueOf(h_value_diff)) > Float.valueOf(map2.get("SINGLE_WARN"))) {
							itemwarn = true;
							alarmValue = map2.get("SINGLE_WARN");
							alarmType = "单次预警值";
						}
						if (Float.valueOf(h_value) < Float.valueOf(map2.get("TOTAL_WARN1"))
								|| Float.valueOf(h_value) > Float.valueOf(map2.get("TOTAL_WARN2"))) {
							itemwarn = true;
							if (Float.valueOf(h_value) < Float.valueOf(map2.get("TOTAL_WARN1"))) {
								alarmValue = map2.get("TOTAL_WARN1");
								alarmType = "累计值(正方向变化值)";
							} else {
								alarmValue = map2.get("TOTAL_WARN2");
								alarmType = "累计值(负方向变化值)";
							}

						}
						singleChange = h_value_diff;
						accumulate = h_value;
					} else {
						String v_value = map3.get("VERTICAL_VALUE").length() > 0 ? map3.get("VERTICAL_VALUE") : "0";
						String v_value_diff = map3.get("VERTICAL_VALUE_DIFF").length() > 0
								? map3.get("VERTICAL_VALUE_DIFF") : "0";

						if (Math.abs(Float.valueOf(v_value_diff)) > Float.valueOf(map2.get("SINGLE_WARN"))) {
							itemwarn = true;
							alarmValue = map2.get("SINGLE_WARN");
							alarmType = "单次预警值";
						}
						if (Float.valueOf(v_value) < Float.valueOf(map2.get("TOTAL_WARN1"))
								|| Float.valueOf(v_value) > Float.valueOf(map2.get("TOTAL_WARN2"))) {
							itemwarn = true;
							if (Float.valueOf(v_value) < Float.valueOf(map2.get("TOTAL_WARN1"))) {
								alarmValue = map2.get("TOTAL_WARN1");
								alarmType = "累计值(正方向变化值)";
							} else {
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
			map2.put("alarmType", alarmType);
			map2.put("singleChange", singleChange);
			map2.put("accumulate", accumulate);
			map2.put("cxDiff", JSONObject.toJSONString(diff));
			map2.put("cxSingle", JSONObject.toJSONString(single));
		}
		return list;
	}

	private List<Map<String, String>> queryNewestDataByPointId_jc(String PRJ_POINTS_UID) {
		Connection conn = DBUtil.getConnection();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT * FROM jc_jc_data d ");
			sql.append(" WHERE d.PRJ_POINTS_UID = '" + PRJ_POINTS_UID + "' ");
			sql.append(" AND d.REPORT_DATE = (SELECT MAX(REPORT_DATE) FROM jc_jc_data AS d1 WHERE d1.PRJ_POINTS_UID = '"
					+ PRJ_POINTS_UID + "') ");
			list = DBUtil.queryReturnList(conn, sql.toString());

		} catch (Exception e) {
			DaoException.handleMessageException("*********查询沉降测点最新数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return list;

	}

	private List<Map<String, String>> queryNewestDataByPointId_cx(String PRJ_POINTS_UID) {
		Connection conn = DBUtil.getConnection();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT * FROM jc_cx_data d ");
			sql.append(" WHERE d.PRJ_POINTS_UID = '" + PRJ_POINTS_UID + "' ");
			sql.append(" AND d.REPORT_DATE = (SELECT MAX(REPORT_DATE) FROM jc_cx_data AS d1 WHERE d1.PRJ_POINTS_UID = '"
					+ PRJ_POINTS_UID + "') ");
			sql.append(" ORDER BY d.DEEP ");
			list = DBUtil.queryReturnList(conn, sql.toString());

		} catch (Exception e) {
			DaoException.handleMessageException("*********查询测斜测点最新数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return list;

	}

	/**
	 * 查询报警
	 * 
	 * @param PRJ_POINTS_UID
	 * @return
	 */
	private List<Map<String, String>> queryBj(String PRJ_POINTS_UID, String type, String projectId) {

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
			sql.append(" where p.POINT_CODE = '" + PRJ_POINTS_UID + "'");
			sql.append(" AND i.JC_TYPE_UID =  '" + type + "'");
			sql.append(" and p.PROJECT_UID = '" + projectId + "'");
			list = DBUtil.queryReturnList(conn, sql.toString());

		} catch (Exception e) {
			DaoException.handleMessageException("*********查询沉降测点最新数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return list;

	}

	public JSONObject querySurveyDataList(HashMap<String, String> map) {
		Connection conn = DBUtil.getConnection();
		List<Map<String, String>> list = null;
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.TOKEN)) {
			try {

				StringBuffer sql = new StringBuffer();
				list = getDates(map);

				if (list.size() > 0) {
					if (list.size() == 1) {
						Map<String, String> map2 = list.get(0);
						sql = new StringBuffer();
						sql.append(
								" SELECT DISTINCT(DEEP) as deep,a.HORIZONTAL_VALUE as firstValue,DATE_FORMAT(REPORT_DATE,'%Y-%m-%d') AS firstTime FROM jc_cx_data a ");
						sql.append(" where PRJ_POINTS_UID = '" + map.get("code") + "' ");
						sql.append(" and REPORT_DATE = '" + map2.get("REPORT_DATE") + "'");
					} else {
						sql = new StringBuffer();
						sql.append(
								" SELECT DISTINCT(a.DEEP) as deep,a.HORIZONTAL_VALUE as firstValue,b.HORIZONTAL_VALUE as lastValue, ");
						sql.append(" a.REPORT_DATE AS firstTime,b.REPORT_DATE AS lastTime FROM ");
						sql.append(
								" (SELECT DEEP ,d.HORIZONTAL_VALUE,DATE_FORMAT(REPORT_DATE,'%Y-%m-%d') as REPORT_DATE  FROM jc_cx_data d where 1=1  ");
						sql.append(" and PRJ_POINTS_UID = '" + map.get("code") + "' ");
						sql.append(" and d.REPORT_DATE =  ");
						sql.append(" (SELECT DATE_FORMAT(MIN(REPORT_DATE),'%Y-%m-%d')  FROM jc_cx_data d  ");
						sql.append(" WHERE d.REPORT_DATE >= '" + list.get(0).get("REPORT_DATE") + "')) a , ");
						sql.append(
								" (SELECT DEEP ,d.HORIZONTAL_VALUE,DATE_FORMAT(REPORT_DATE,'%Y-%m-%d') as REPORT_DATE   FROM jc_cx_data d where 1=1  ");
						sql.append(" and PRJ_POINTS_UID = '" + map.get("code") + "' ");
						sql.append(" and d.REPORT_DATE =  ");
						sql.append(" (SELECT DATE_FORMAT(MAX(REPORT_DATE),'%Y-%m-%d')  FROM jc_cx_data d  ");
						sql.append(
								" WHERE d.REPORT_DATE <= '" + list.get(list.size() - 1).get("REPORT_DATE") + "'))b ");
						sql.append(" WHERE a.DEEP = b.DEEP ");
					}

				} else {
					Map<String, String> map2 = getDateMap(map.get("code") + "");
					map2.put("code", map.get("code") + "");
					list = getDates(map2);
					if (list.size() == 1) {
						Map<String, String> map3 = list.get(0);
						sql = new StringBuffer();
						sql.append(
								" SELECT DISTINCT(DEEP) as deep,a.HORIZONTAL_VALUE as firstValue,DATE_FORMAT(REPORT_DATE,'%Y-%m-%d') AS firstTime FROM jc_cx_data a ");
						sql.append(" where PRJ_POINTS_UID = '" + map.get("code") + "' ");
						sql.append(" and REPORT_DATE = '" + map3.get("REPORT_DATE") + "'");
					} else {
						sql = new StringBuffer();
						sql.append(
								" SELECT DISTINCT(a.DEEP) as deep,a.HORIZONTAL_VALUE as firstValue,b.HORIZONTAL_VALUE as lastValue, ");
						sql.append(" a.REPORT_DATE AS firstTime,b.REPORT_DATE AS lastTime FROM ");
						sql.append(
								" (SELECT DEEP ,d.HORIZONTAL_VALUE,DATE_FORMAT(REPORT_DATE,'%Y-%m-%d') as REPORT_DATE  FROM jc_cx_data d where 1=1  ");
						sql.append(" and PRJ_POINTS_UID = '" + map.get("code") + "' ");
						sql.append(" and d.REPORT_DATE =  ");
						sql.append(" (SELECT DATE_FORMAT(MIN(REPORT_DATE),'%Y-%m-%d')  FROM jc_cx_data d  ");
						sql.append(" WHERE d.REPORT_DATE >= '" + list.get(0).get("REPORT_DATE") + "')) a , ");
						sql.append(
								" (SELECT DEEP ,d.HORIZONTAL_VALUE,DATE_FORMAT(REPORT_DATE,'%Y-%m-%d') as REPORT_DATE   FROM jc_cx_data d where 1=1  ");
						sql.append(" and PRJ_POINTS_UID = '" + map.get("code") + "' ");
						sql.append(" and d.REPORT_DATE =  ");
						sql.append(" (SELECT DATE_FORMAT(MAX(REPORT_DATE),'%Y-%m-%d')  FROM jc_cx_data d  ");
						sql.append(
								" WHERE d.REPORT_DATE <= '" + list.get(list.size() - 1).get("REPORT_DATE") + "'))b ");
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

	private List<Map<String, String>> getDates(Map<String, String> map) {
		Connection conn = DBUtil.getConnection();
		List<Map<String, String>> list = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT DISTINCT(d.REPORT_DATE) as REPORT_DATE FROM jc_cx_data d WHERE d.REPORT_DATE between ");
			sql.append("'" + map.get("time1") + "'");
			sql.append(" and ");
			sql.append("'" + map.get("time2") + "'");
			sql.append(" and PRJ_POINTS_UID = '" + map.get("code") + "' ");
			list = DBUtil.queryReturnList(conn, sql.toString());

		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return list;
	}

	private Map<String, String> getDateMap(String PRJ_POINTS_UID) {
		Connection conn = DBUtil.getConnection();
		Map<String, String> map = null;
		try {
			String maxdateSql = "SELECT DATE_FORMAT(date_add(max(REPORT_DATE), interval -1 week),'%Y-%m-%d') as time1,DATE_FORMAT(max(REPORT_DATE),'%Y-%m-%d') as time2 FROM jc_jc_data d where PRJ_POINTS_UID = '"
					+ PRJ_POINTS_UID + "'";
			List<Map<String, String>> maxdateList = DBUtil.queryReturnList(conn, maxdateSql);
			if (maxdateList.size() > 0) {
				map = maxdateList.get(0);
			}

		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return map;
	}

	// 变更签证入库
	public JSONObject insertChange(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {
			Long id = BaseDAO.insert(conn, (PmBiangengVO) map.get("changevo"));
			String projectChangeUid = String.valueOf(id);
			if (null != map.get("json")) {
				org.json.JSONArray array = (org.json.JSONArray) map.get("json");
				MobileFileUpload.insertTable(array, projectChangeUid);
			}
			obj = MobileUtil.getMessageInfo(obj);
		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********新增数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	// 获取变更签证
	public JSONObject getChange(HashMap<String, String> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT(select s.user_name from sys_user s where s.user_uid = b.create_user) as username,");
			sql.append(" (select s.user_name from sys_user s where s.user_uid = b.update_user) as UPDATEBY,");
			sql.append(" biangeng_uid as changeId, ");
			sql.append(" biangeng_date as time,CREATE_DATE as createTime , ");
			sql.append(" case TAGS");
			sql.append(" when 'BG' then '变更'");
			sql.append(" when 'QZ' then '签证' ");
			sql.append(" when '' then ''  ");
			sql.append(" end as changeType,  ");
			sql.append(" case BIANGENG_TYPE ");
			sql.append(" when 'HTBCXY' then '合同补充协议'");
			sql.append(" when 'XH' then'信函'");
			sql.append(" when 'GCBGZL' then '工程变更指令'");
			sql.append(" when 'CLCJ' then'材料差价' ");
			sql.append(" when 'XCQZ'then '现场签证' ");
			sql.append(" when 'SJBG' then '设计变更'");
			sql.append(" when 'HYJY' then '会议纪要' ");
			sql.append(" when 'JSBG' then'结算变更' ");
			sql.append(" when '' then '' ");
			sql.append(" end AS type,STATUS as state, ");
			sql.append(" biangeng_company as unit, ");
			sql.append(" reason as reason, ");
			sql.append(" amount as amount, ");
			sql.append(" important as major, ");
			sql.append(" (SELECT a.FILE_PATH FROM bu_attachment a  ");
			sql.append(" LEFT JOIN bu_file f  ");
			sql.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID  ");
			sql.append(" WHERE f.FILE_TYPE = '10008'  ");
			sql.append(" AND b.CREATE_USER = f.TARGET_UID  ");
			sql.append(" ORDER BY f.CREATE_DATE DESC LIMIT 1)as userImage ");
			sql.append(" from pm_biangeng b ");
			sql.append(" where PROJECT_UID = '" + map.get("projectId") + "' ");
			sql = MobileUtil.isMyPublish(sql, map);

			List<Map<String, String>> list = DBUtil.queryReturnList(conn, sql.toString());
			for (int i = 0; i < list.size(); i++) {
				Map changemap = list.get(i);
				StringBuffer sql2 = new StringBuffer();
				sql2.append(" SELECT f.FILE_UID,a.FILE_EXT_NAME,a.FILE_PATH,f.TARGET_UID,a.FILE_NAME  ");
				sql2.append(" FROM bu_attachment a LEFT JOIN bu_file f ");
				sql2.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID ");
				sql2.append(" WHERE f.FILE_TYPE = '10401' AND f.ENABLED = '1' AND f.TARGET_UID = '"
						+ changemap.get("changeId") + "'  ");
				List<Map<String, String>> changeFileList = DBUtil.queryReturnList(conn, sql2.toString());
				changemap.put("appendix", changeFileList);//// 回复附件
			}
			obj = MobileUtil.getMessageInfo(obj);
			obj.put("changeList", list);

		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	/**
	 * 设备材料进场入库
	 */
	public JSONObject insertMaterial(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {
			Long id = BaseDAO.insert(conn, (PmCailiaoVO) map.get("materialvo"));
			String materialUid = String.valueOf(id);
			if (null != map.get("json")) {
				org.json.JSONArray array = (org.json.JSONArray) map.get("json");
				MobileFileUpload.insertTable(array, materialUid);
			}
			obj = MobileUtil.getMessageInfo(obj);
		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********新增数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	/**
	 * 设备材料进场查询
	 */

	public JSONObject getMaterial(HashMap<String, String> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" select (select s.user_name from sys_user s where s.user_uid = b.create_user) as username,");
			sql.append(" (select s.user_name from sys_user s where s.user_uid = b.update_user) as UPDATEBY,");
			sql.append(" cailiao_uid as materialId, ");
			sql.append(" cailiao_name as name, ");
			sql.append(" cailiao_pinpai as brand,");
			sql.append(" cailiao_changshang as producer,");
			sql.append(" cailiao_chandi as originPlace, ");
			sql.append(" cailiao_xinghao as model, ");
			sql.append(" cailiao_unit as unit,  ");
			sql.append(" cailiao_nums as number, ");
			sql.append(" cailiao_price as price, ");
			sql.append(" cailiao_jcrq as date,CREATE_DATE as createTime , ");
			sql.append(" yanshou_ren as acceptor, ");
			sql.append(" description as note, ");
			sql.append(" (SELECT a.FILE_PATH FROM bu_attachment a  ");
			sql.append(" LEFT JOIN bu_file f  ");
			sql.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID  ");
			sql.append(" WHERE f.FILE_TYPE = '10008'  ");
			sql.append(" AND b.CREATE_USER = f.TARGET_UID  ");
			sql.append(" ORDER BY f.CREATE_DATE DESC LIMIT 1)as userImage ");
			sql.append(" from pm_cailiao b ");
			sql.append(" where PROJECT_UID = '" + map.get("projectId") + "' ");
			sql = MobileUtil.isMyPublish(sql, map);
			
			List<Map<String, String>> list = DBUtil.queryReturnList(conn, sql.toString());
			for (int i = 0; i < list.size(); i++) {
				Map materialmap = list.get(i);
				StringBuffer sql2 = new StringBuffer();
				sql2.append(" SELECT f.FILE_UID,a.FILE_EXT_NAME,a.FILE_PATH,f.TARGET_UID,a.FILE_NAME  ");
				sql2.append(" FROM bu_attachment a LEFT JOIN bu_file f ");
				sql2.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID ");
				sql2.append(" WHERE f.FILE_TYPE = '10401' AND f.ENABLED = '1' AND f.TARGET_UID = '"
						+ materialmap.get("materialId") + "'  ");
				List<Map<String, String>> materialFileList = DBUtil.queryReturnList(conn, sql2.toString());
				materialmap.put("appendix", materialFileList);//// 回复附件
			}
			obj = MobileUtil.getMessageInfo(obj);
			obj.put("materialList", list);

		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	/**
	 * 现场检查入库
	 */

	public JSONObject insertCheck(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {
			Long id = BaseDAO.insert(conn, (PmXunjianVO) map.get("checkvo"));
			PmXunjianStrucVO svo = (PmXunjianStrucVO) map.get("svo");
			svo.setXunjian_uid(String.valueOf(id));
			BaseDAO.insert(conn, svo);
			String checkUid = String.valueOf(id);
			if (null != map.get("json")) {
				org.json.JSONArray array = (org.json.JSONArray) map.get("json");
				MobileFileUpload.insertTable(array, checkUid);
			}
			obj = MobileUtil.getMessageInfo(obj);
		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********新增数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	/**
	 * 获取现场检查
	 */
	public JSONObject getCheck(HashMap<String, String> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT (select s.user_name from sys_user s where s.user_uid = b.create_user) as username, ");
			sql.append(" (select s.user_name from sys_user s where s.user_uid = b.update_user) as UPDATEBY, ");
			sql.append(" xunjian_uid  checkId, ");
			sql.append(
					" case xunjian_type when 'ZJ' then '质量检查' when 'AJ' then '安全检查' when 'WJ' then '文明施工检查' end as type, ");
			sql.append(
					" (select level_name from sys_jiancha_level where JIANCHA_LEVEL_UID= b.jiancha_level_uid) as level, ");
			sql.append(" jierun as conclusion, ");
			sql.append(" canjian as person, STATUS as state, ");
			sql.append(" xunjian_date as time, CREATE_DATE as createTime , ");
			sql.append(" (SELECT a.FILE_PATH FROM bu_attachment a  ");
			sql.append(" LEFT JOIN bu_file f  ");
			sql.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID  ");
			sql.append(" WHERE f.FILE_TYPE = '10008'  ");
			sql.append(" AND b.CREATE_USER = f.TARGET_UID  ");
			sql.append(" ORDER BY f.CREATE_DATE DESC LIMIT 1)as userImage ");
			sql.append(" FROM pm_xunjian b ");
			sql.append(" where project_uid = '" + map.get("projectId") + "' ");
			sql = MobileUtil.isMyPublish(sql, map);
			
			List<Map<String, String>> list = DBUtil.queryReturnList(conn, sql.toString());
			for (int i = 0; i < list.size(); i++) {
				Map checkmap = list.get(i);
				StringBuffer sql2 = new StringBuffer();
				sql2.append(
						" SELECT xuhao,(select node_content from sys_bzgf where bzgf_uid = a.bzgf_uid) bzContent, ");
				sql2.append(" content as content, ");
				sql2.append(" case weigui_level when 1 then '一般' when 2 then '较严重' when 3 then '严重' end as dengji ");
				sql2.append("from pm_xunjian_content a ");
				sql2.append("where xunjian_uid = " + checkmap.get("xunjian_uid"));
				List<Map<String, String>> contentList = DBUtil.queryReturnList(conn, sql2.toString());
				checkmap.put("lllegalContentList", contentList);//// 违规内容

				StringBuffer sqlf = new StringBuffer();
				sqlf.append(" SELECT f.FILE_UID,a.FILE_EXT_NAME,a.FILE_PATH,f.TARGET_UID,a.FILE_NAME  ");
				sqlf.append(" FROM bu_attachment a LEFT JOIN bu_file f ");
				sqlf.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID ");
				sqlf.append(" WHERE f.FILE_TYPE = '10601' AND f.ENABLED = '1' AND f.TARGET_UID = '"
						+ checkmap.get("checkId") + "'  ");
				List<Map<String, String>> xunjianFileList = DBUtil.queryReturnList(conn, sqlf.toString());
				checkmap.put("appendix", xunjianFileList);//// 回复附件
			}

			obj = MobileUtil.getMessageInfo(obj);
			obj.put("checkList", list);

		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	/**
	 * 工程验收入库
	 */
	public JSONObject insertAcceptance(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {
			Long id = BaseDAO.insert(conn, (PmYanshouVO) map.get("acceptancevo"));
			String acceptanceUid = String.valueOf(id);
			if (null != map.get("json")) {
				org.json.JSONArray array = (org.json.JSONArray) map.get("json");
				MobileFileUpload.insertTable(array, acceptanceUid);
			}
			obj = MobileUtil.getMessageInfo(obj);
		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********新增数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;

	}

	/**
	 * 获取工程验收
	 */
	public JSONObject getAcceptance(HashMap<String, String> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select (select s.user_name from sys_user s where s.user_uid = b.create_user) as username, ");
			sql.append(" (select s.user_name from sys_user s where s.user_uid = b.update_user) as UPDATEBY, ");
			sql.append(
					" case yanshou_type when 'FB' then '分部工程验收' when 'YB' then '隐蔽工程验收' when 'FX' then '专项工程验收' end as type, ");
			sql.append(" fbfxgc_name as name, YANSHOU_UID as acceptanceId, ");
			sql.append(" sgdw as constructionUnit, ");
			sql.append(" sgdw_jsfzr as constructionUnitPerson, ");
			sql.append(" sgdw_zlfzr as constructionUnitQualityPerson, ");
			sql.append(" fbdw as subpackageUnit, ");
			sql.append(" fbdw_fzr as subpackageUnitPerson, ");
			sql.append(" fbdw_jsfzr as subpackageUnitQualityPerson, ");
			sql.append(" yb_ysbw as concealedAcceptanceLocation, ");
			sql.append(" yb_ysyj as concealedAcceptanceBasis, ");
			sql.append(" yb_ysnr as concealedAcceptanceContent, ");
			sql.append(" zx_kgrq as specialProjectStart, ");
			sql.append(" zx_wcrq as specialProjectEnd, ");
			sql.append(" zx_gcnr as specialProjectAcceptanceContent, ");
			sql.append(" zx_yszl as specialProjectAcceptanceData, ");
			sql.append(" jcjg as constructionUnitResult, ");
			sql.append(" ysjl as conclusion, ");
			sql.append(" ysrq as time, CREATE_DATE as createTime ,");
			sql.append(" (SELECT a.FILE_PATH FROM bu_attachment a  ");
			sql.append(" LEFT JOIN bu_file f  ");
			sql.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID  ");
			sql.append(" WHERE f.FILE_TYPE = '10008'  ");
			sql.append(" AND b.CREATE_USER = f.TARGET_UID  ");
			sql.append(" ORDER BY f.CREATE_DATE DESC LIMIT 1)as userImage ");
			sql.append(" from pm_yanshou b ");
			/*
			 * sql.append(
			 * "  LEFT JOIN (SELECT a.FILE_PATH,f.TARGET_UID FROM bu_attachment a  "
			 * ); sql.append(
			 * "  LEFT JOIN bu_file f ON a.ATTACHMENT_UID = f.ATTACHMENT_UID  "
			 * ); sql.append(
			 * "  WHERE f.FILE_TYPE = '10008' ORDER BY f.CREATE_DATE DESC LIMIT 1) f  "
			 * ); sql.append("  ON f.TARGET_UID = b.CREATE_USER ");
			 */
			sql.append(" where project_uid = '" + map.get("projectId") + "' ");
			List<Map<String, String>> list = DBUtil.queryReturnList(conn, sql.toString());
			for (int i = 0; i < list.size(); i++) {
				Map acceptancemap = list.get(i);
				StringBuffer sql2 = new StringBuffer();
				sql2.append(" SELECT f.FILE_UID,a.FILE_EXT_NAME,a.FILE_PATH,f.TARGET_UID,a.FILE_NAME  ");
				sql2.append(" FROM bu_attachment a LEFT JOIN bu_file f ");
				sql2.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID ");
				sql2.append(" WHERE f.FILE_TYPE = '10701' AND f.ENABLED = '1' AND f.TARGET_UID = '"
						+ acceptancemap.get("acceptanceId") + "'  ");
				List<Map<String, String>> acceptanceFileList = DBUtil.queryReturnList(conn, sql2.toString());
				acceptancemap.put("appendix", acceptanceFileList);//// 回复附件
			}
			obj = MobileUtil.getMessageInfo(obj);
			obj.put("acceptanceList", list);

		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	// 项目日志入库
	public JSONObject insertProjetLog(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {
			Long id = BaseDAO.insert(conn, (PmProjectLogVO) map.get("logvo"));
			PmProjectLogStrucVO svo = (PmProjectLogStrucVO) map.get("svo");
			svo.setProject_log_uid(String.valueOf(id));
			BaseDAO.insert(conn, svo);
			String projectLogUid = String.valueOf(id);
			if (null != map.get("json")) {
				org.json.JSONArray array = (org.json.JSONArray) map.get("json");
				MobileFileUpload.insertTable(array, projectLogUid);
			}
			obj = MobileUtil.getMessageInfo(obj);
		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********新增数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;

	}

	// 获取日志列表
	public JSONObject getProjetLogs(HashMap<String, String> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(
					" SELECT u.USER_NAME as username,l.LOG_TYPE as logType,l.GONGZUO_INFO as work,l.CQQK_INFO as problem, ");
			sql.append(" l.LOG_DATE as date,s.STRUC_NAME as location,l.TUFA as event,l.YZLOG_TITLE as title,l.YZLOG_CONTENT as content, ");
			sql.append(" l.SHENGCHAN_INFO as production,l.ZLAQ_INFO as safety,l.PROJECT_LOG_UID as logId,l.CREATE_DATE as createTime , ");
			
			sql.append(" (SELECT a.FILE_PATH FROM bu_attachment a  ");
			sql.append(" LEFT JOIN bu_file f  ");
			sql.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID  ");
			sql.append(" WHERE f.FILE_TYPE = '10008'  ");
			sql.append(" AND l.CREATE_USER = f.TARGET_UID  ");
			sql.append(" ORDER BY f.CREATE_DATE DESC LIMIT 1)as userImage ");
			sql.append("  FROM pm_project_log l ");
			sql.append(" LEFT JOIN sys_user u ");
			sql.append(" ON l.CREATE_USER = u.USER_UID ");
			sql.append(" LEFT JOIN pm_project_log_struc ls ");
			sql.append(" ON l.PROJECT_LOG_UID = ls.PROJECT_LOG_UID ");
			sql.append(" LEFT JOIN pm_prj_struc s ");
			sql.append(" ON ls.PRJ_STRUC_UID = s.PRJ_STRUC_UID ");
			if(map.get("type").equals("my")){
				sql.append("WHERE l.CREATE_USER = '"+map.get("userId")+"'");
			}
	
			List<Map<String, String>> list = DBUtil.queryReturnList(conn, sql.toString());
			for (int i = 0; i < list.size(); i++) {
				Map logmap = list.get(i);
				StringBuffer sql2 = new StringBuffer();
				sql2.append(" SELECT f.FILE_UID,a.FILE_EXT_NAME,a.FILE_PATH,f.TARGET_UID,a.FILE_NAME  ");
				sql2.append(" FROM bu_attachment a LEFT JOIN bu_file f ");
				sql2.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID ");
				sql2.append(" WHERE f.FILE_TYPE = '10109' AND f.ENABLED = '1' AND f.TARGET_UID = '"
						+ logmap.get("logId") + "'  ");
				List<Map<String, String>> logFileList = DBUtil.queryReturnList(conn, sql2.toString());
				logmap.put("appendix", logFileList);//// 回复附件
			}
			obj = MobileUtil.getMessageInfo(obj);
			obj.put("logList", list);

		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	// 问题讨论入库
	public JSONObject insertTopic(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {
			String topicUid = String.valueOf(BaseDAO.insert(conn, (PmProjectTopicVO) map.get("topic")));
			if (null != map.get("json")) {
				org.json.JSONArray array = (org.json.JSONArray) map.get("json");
				MobileFileUpload.insertTable(array, topicUid);
			}

			obj = MobileUtil.getMessageInfo(obj);
		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********新增数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	// 获取问题讨论列表
	public JSONObject getTopics(HashMap<String, String> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT t.PROJECT_TOPIC_UID as topicId,t.`SUBJECT` as subject,t.CONTENT as content,  ");
			sql.append("  DATE_FORMAT(t.CREATE_DATE,'%Y-%m-%d %H:%i')  as date,u.USER_NAME as username,u.LOGON_NAME as account,t.STATUS as state, ");
			sql.append(
					" (SELECT count(*) FROM pm_project_topic_reply r WHERE r.PROJECT_TOPIC_UID = t.PROJECT_TOPIC_UID) as comment ,");
			sql.append(" (SELECT a.FILE_PATH FROM bu_attachment a  ");
			sql.append(" LEFT JOIN bu_file f  ");
			sql.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID  ");
			sql.append(" WHERE f.FILE_TYPE = '10008'  ");
			sql.append(" AND t.CREATE_USER = f.TARGET_UID  ");
			sql.append(" ORDER BY f.CREATE_DATE DESC LIMIT 1)as userImage ");
			sql.append("  FROM PM_PROJECT_TOPIC t  ");
			sql.append(" LEFT JOIN sys_user u ");
			sql.append(" ON t.CREATE_USER = u.USER_UID ");
			if(map.get("type").equals("my")){
				sql.append("WHERE t.CREATE_USER = '"+map.get("userId")+"'");
			}
			List<Map<String, String>> list = DBUtil.queryReturnList(conn, sql.toString());
			for (int i = 0; i < list.size(); i++) {
				Map logmap = list.get(i);
				StringBuffer sql2 = new StringBuffer();
				sql2.append(" SELECT f.FILE_UID,a.FILE_EXT_NAME,a.FILE_PATH,f.TARGET_UID,a.FILE_NAME  ");
				sql2.append(" FROM bu_attachment a LEFT JOIN bu_file f ");
				sql2.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID ");
				sql2.append(" WHERE f.FILE_TYPE = '10301' AND f.ENABLED = '1' AND f.TARGET_UID = '"
						+ logmap.get("topicId") + "'  ");
				List<Map<String, String>> topicsFileList = DBUtil.queryReturnList(conn, sql2.toString());
				logmap.put("appendix", topicsFileList);//// 回复附件
			}
			obj = MobileUtil.getMessageInfo(obj);
			obj.put("topicList", list);

		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	/**
	 * 问题讨论回复入库
	 */
	public JSONObject insertReply(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {
			PmProjectTopicReplyVO vo = (PmProjectTopicReplyVO) map.get("reply");
			vo.setXuhao(getMaxCode(vo.getProject_topic_uid()));
			String replyUid = String.valueOf(BaseDAO.insert(conn, vo));
			if (null != map.get("json")) {
				org.json.JSONArray array = (org.json.JSONArray) map.get("json");
				MobileFileUpload.insertTable(array, replyUid);
			}
			obj = MobileUtil.getMessageInfo(obj);
		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********新增数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	/**
	 * 现场答复入库
	 */
	public JSONObject insertXianchang(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {
			PmXianchangDafuVO vo = (PmXianchangDafuVO) map.get("xianchangReply");
			vo.setXuhao(getXuhao(vo.getXianchang_uid()));
			vo.setDafu_type(getDftype(vo.getXianchang_uid(), vo.getCreate_user()));
			long ruid = BaseDAO.insert(conn, vo);
			if (null != map.get("json")) {
				org.json.JSONArray array = (org.json.JSONArray) map.get("json");
				MobileFileUpload.insertTable(array, String.valueOf(ruid));
			}
			obj = MobileUtil.getMessageInfo(obj);
		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********新增数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	// 获取问题讨论回复序号
	public String getMaxCode(String topicUid) {
		Connection conn = DBUtil.getConnection();
		String domresult = "";
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			String sql = "SELECT MAX(t.XUHAO) XUHAO FROM pm_project_topic_reply t WHERE t.PROJECT_TOPIC_UID ='"
					+ topicUid + "'";
			list = DBUtil.queryReturnList(conn, sql);
			if (list.size() > 0) {
				Map<String, String> map = list.get(0);
				if ("".equals(map.get("XUHAO"))) {
					domresult = "1";
				} else {
					domresult = String.valueOf(Long.valueOf(map.get("XUHAO")) + 1);
				}
			}
		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return domresult;

	}

	// 获取现场回复序号
	public String getXuhao(String xcUid) {
		Connection conn = DBUtil.getConnection();
		String domresult = "";
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			String sql = "SELECT MAX(t.XUHAO) XUHAO FROM pm_xianchang_dafu t WHERE t.XIANCHANG_UID ='" + xcUid + "'";
			list = DBUtil.queryReturnList(conn, sql);
			if (list.size() > 0) {
				Map<String, String> map = list.get(0);
				if ("".equals(map.get("XUHAO"))) {
					domresult = "1";
				} else {
					domresult = String.valueOf(Long.valueOf(map.get("XUHAO")) + 1);
				}
			}
		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return domresult;

	}

	// 获取现场回复类型 1－答复；0－发布人的回复
	public String getDftype(String xcUid, String userId) {
		Connection conn = DBUtil.getConnection();
		String domresult = "";
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			String sql = "select CREATE_USER from pm_xianchang where XIANCHANG_UID='" + xcUid + "'";
			list = DBUtil.queryReturnList(conn, sql);
			if (list.size() > 0) {
				Map<String, String> map = list.get(0);
				if (userId.equals(map.get("CREATE_USER"))) {
					domresult = "0";
				} else {
					domresult = "1";
				}
			}
		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return domresult;

	}

	// 获取某条问题讨论下的所有回复
	public JSONObject getReplyByTopicUid(HashMap<String, String> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT t.PROJECT_TOPIC_REPLY_UID as commentId,t.CONTENT as content,   ");
			sql.append("  DATE_FORMAT(t.CREATE_DATE,'%Y-%m-%d %H:%i')  as time,u.USER_NAME as username,t.CREATE_USER as userId, ");
			sql.append(" (SELECT a.FILE_PATH FROM bu_attachment a  ");
			sql.append(" LEFT JOIN bu_file f  ");
			sql.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID  ");
			sql.append(" WHERE f.FILE_TYPE = '10008'  ");
			sql.append(" AND t.CREATE_USER = f.TARGET_UID  ");
			sql.append(" ORDER BY f.CREATE_DATE DESC LIMIT 1)as userImage ");
			sql.append("  FROM pm_project_topic_reply t   ");
			sql.append(" LEFT JOIN sys_user u  ");
			sql.append(" ON t.CREATE_USER = u.USER_UID  ");
		
			sql.append(" WHERE t.PROJECT_TOPIC_UID = '" + map.get("topicUid") + "' ");
			List<Map<String, String>> list = DBUtil.queryReturnList(conn, sql.toString());

			for (int i = 0; i < list.size(); i++) {
				StringBuffer sql2 = new StringBuffer();
				Map rmap = list.get(i);
				sql2.append(" SELECT f.FILE_UID,a.FILE_EXT_NAME,a.FILE_PATH,f.TARGET_UID,a.FILE_NAME  ");
				sql2.append(" FROM bu_attachment a LEFT JOIN bu_file f ");
				sql2.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID ");
				sql2.append(" WHERE f.FILE_TYPE = '10302' AND f.ENABLED = '1' AND f.TARGET_UID = '"
						+ rmap.get("commentId") + "'  ");
				List<Map<String, String>> replyFileList = DBUtil.queryReturnList(conn, sql2.toString());
				rmap.put("appendix", replyFileList);//// 回复附件
			}

			obj = MobileUtil.getMessageInfo(obj);
			obj.put("commentList", list);

		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	// 获取现场状况列表
	public JSONObject getInfos(HashMap<String, String> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(
					" SELECT `xc`.DESCRIPTION AS conditions,`xc`.XIANCHANG_UID as xc_uid,`xc`.JINDU AS progress,`xc`.`STATUS` AS state,sc.STRUC_NAME AS location,count(an.XIANCHANG_UID) AS comment,");
			sql.append(
					" DATE_FORMAT(`xc`.CREATE_DATE, '%Y-%m-%d %H:%i') AS date,u.USER_NAME AS username,u.LOGON_NAME as account,xc.CREATE_USER as userId,xc.GONGKUANG_TYPE as type, xc.STATUS as state,");
			sql.append("xc.FINISH_DATE as finishDate,xc.SERIOUSNESS as severity , ");
			sql.append(" (SELECT a.FILE_PATH FROM bu_attachment a  ");
			sql.append(" LEFT JOIN bu_file f  ");
			sql.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID  ");
			sql.append(" WHERE f.FILE_TYPE = '10008'  ");
			sql.append(" AND `xc`.CREATE_USER = f.TARGET_UID  ");
			sql.append(" ORDER BY f.CREATE_DATE DESC LIMIT 1)as userImage, ");
			sql.append(" (SELECT count(*) from log_user_read WHERE TARGET_UID = xc.XIANCHANG_UID AND USER_UID = '"+map.get("userId")+"') as see ");
			sql.append(
					" FROM PM_XIANCHANG  AS `xc` LEFT JOIN PM_XIANCHANG_STRUC xs ON xc.XIANCHANG_UID = xs.XIANCHANG_UID ");
			sql.append(" LEFT JOIN pm_xianchang_dafu an ON an.XIANCHANG_UID = xc.XIANCHANG_UID ");
			sql.append("LEFT JOIN PM_PRJ_STRUC sc ON xs.PRJ_STRUC_UID = sc.PRJ_STRUC_UID  ");
			sql.append(" LEFT JOIN sys_user u  ON xc.CREATE_USER = u.USER_UID ");
		
			sql.append(" WHERE xc.PROJECT_UID = '" + map.get("projectId")+ "' "); 
			
			if(map.get("type").equals("my")){
				sql.append("AND xc.CREATE_USER = '"+map.get("userId")+"'");
			}
			
			sql.append("GROUP BY xc.XIANCHANG_UID ORDER BY xc.XIANCHANG_UID ");
			

			List<Map<String, String>> list = DBUtil.queryReturnList(conn, sql.toString());

			for (int i = 0; i < list.size(); i++) {
				StringBuffer sql2 = new StringBuffer();
				Map rmap = list.get(i);
				sql2.append(" SELECT f.FILE_UID,a.FILE_EXT_NAME,a.FILE_PATH,f.TARGET_UID,a.FILE_NAME,f.DESCRIPTION  ");
				sql2.append(" FROM bu_attachment a LEFT JOIN bu_file f ");
				sql2.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID ");
				sql2.append(" WHERE  f.ENABLED = '1' AND f.TARGET_UID = '" + rmap.get("xc_uid") + "'  ");
				if ("ZL".equals(rmap.get("type"))) {
					sql2.append(" AND f.FILE_TYPE = '10010' ");
				} else if ("AQ".equals(rmap.get("type"))) {
					sql2.append(" AND f.FILE_TYPE = '10011' ");
				} else if ("JD".equals(rmap.get("type"))) {
					sql2.append(" AND f.FILE_TYPE = '10009' ");
				}
				List<Map<String, String>> replyFileList = DBUtil.queryReturnList(conn, sql2.toString());
				rmap.put("appendix", replyFileList);//// 回复附件
			}

			obj = MobileUtil.getMessageInfo(obj);
			obj.put("conditionList", list);

		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********查询现场状况出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	/** 获取 现场状况回复的 信息 **/
	public JSONObject getResponse(HashMap<String, String> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {

			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT df.XIANCHANG_DAFU_UID as commentId,df.CONTENT as content,u.USER_NAME as username,df.CREATE_USER as userId, ");
			sql.append("  DATE_FORMAT(df.CREATE_DATE, '%Y-%m-%d %H:%i') AS time,  ");
			sql.append(" (SELECT a.FILE_PATH FROM bu_attachment a  ");
			sql.append(" LEFT JOIN bu_file f  ");
			sql.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID  ");
			sql.append(" WHERE f.FILE_TYPE = '10008'  ");
			sql.append(" AND df.CREATE_USER = f.TARGET_UID  ");
			sql.append(" ORDER BY f.CREATE_DATE DESC LIMIT 1)as userImage ");
			sql.append("  FROM pm_xianchang_dafu AS df LEFT JOIN sys_user u  ON df.CREATE_USER = u.USER_UID  ");

			sql.append(" where df.XIANCHANG_UID = '" + map.get("xc_uid") + "'   ");

			/*
			 * StringBuffer sql3 = new StringBuffer(); sql3.append(
			 * " SELECT a.FILE_EXT_NAME,a.FILE_PATH as IMG_PATH,f.TARGET_UID,a.FILE_NAME  "
			 * ); sql3.append(" FROM bu_attachment a LEFT JOIN bu_file f ");
			 * sql3.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID ");
			 * sql3.append(
			 * " WHERE f.FILE_TYPE = '10008' AND f.ENABLED = '1' AND f.TARGET_UID = '"
			 * +user.getIdCard()+"'  ");
			 * 
			 * List<Map<String,String>> userxx = DBUtil.queryReturnList(conn,
			 * sql3.toString());
			 */

			List<Map<String, String>> list = DBUtil.queryReturnList(conn, sql.toString());
			for (int i = 0; i < list.size(); i++) {
				StringBuffer sql2 = new StringBuffer();
				Map rmap = list.get(i);
				sql2.append(" SELECT f.FILE_UID,a.FILE_EXT_NAME,a.FILE_PATH,f.TARGET_UID,a.FILE_NAME  ");
				sql2.append(" FROM bu_attachment a LEFT JOIN bu_file f ");
				sql2.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID ");
				sql2.append(" WHERE  f.ENABLED = '1' AND f.TARGET_UID = '" + rmap.get("commentId") + "'  ");
				if ("ZL".equals(rmap.get("type"))) {
					sql2.append(" AND f.FILE_TYPE = '10010' ");
				} else if ("AQ".equals(rmap.get("type"))) {
					sql2.append(" AND f.FILE_TYPE = '10011' ");
				} else if ("JD".equals(rmap.get("type"))) {
					sql2.append(" AND f.FILE_TYPE = '10009' ");
				}
				List<Map<String, String>> replyFileList = DBUtil.queryReturnList(conn, sql2.toString());
				rmap.put("appendix", replyFileList);//// 回复附件
			}
			obj = MobileUtil.getMessageInfo(obj);
			obj.put("commentList", list);

		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********查询现场状况回复的信息出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	// 修改图像
	public JSONObject modifyImage(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {

			if (null != map.get("json")) {
				org.json.JSONArray array = (org.json.JSONArray) map.get("json");
				MobileFileUpload.insertTable(array, map.get("userId").toString());
			}
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT a.FILE_PATH,f.TARGET_UID FROM bu_attachment a  ");
			sql.append(" LEFT JOIN bu_file f ON a.ATTACHMENT_UID = f.ATTACHMENT_UID   ");
			sql.append(" WHERE f.FILE_TYPE = '10008'  ");
			sql.append(" AND f.TARGET_UID = '" + map.get("userId") + "'  ");
			sql.append(" ORDER BY f.CREATE_DATE DESC LIMIT 1 ");
			List<Map<String, String>> FileList = DBUtil.queryReturnList(conn, sql.toString());
			obj = MobileUtil.getMessageInfo(obj);
			obj.put("imageUrl", FileList);

		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********新增数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	//修改问题讨论
	public JSONObject updateTopic(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {
			PmProjectTopicVO vo  = (PmProjectTopicVO) map.get("topic");
			BaseDAO.update(conn, vo);
			if (null != map.get("json")) {
				org.json.JSONArray array = (org.json.JSONArray) map.get("json");
				MobileFileUpload.insertTable(array, vo.getProject_topic_uid());
			}

			obj = MobileUtil.getMessageInfo(obj);
		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********新增数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	//删除问题讨论及回复
	public JSONObject deleteTopic(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {

			DBUtil.exec(conn, "DELETE FROM pm_project_topic WHERE PROJECT_TOPIC_UID = '"+map.get("topicUid")+"'");

			DBUtil.exec(conn, "DELETE FROM pm_project_topic_reply WHERE PROJECT_TOPIC_UID = '"+map.get("topicUid")+"'");
			
			obj = MobileUtil.getMessageInfo(obj);

		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********新增数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	/**
	 * 删除附件公共方法
	 */
	public JSONObject deleteFiles(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {

			DBUtil.exec(conn, "DELETE FROM bu_file WHERE TARGET_TABLE = '"+map.get("table_name")+"' AND TARGET_UID = '"+map.get("target_uid")+"'");

			obj = MobileUtil.getMessageInfo(obj);

		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********新增数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	//修改变更
	public JSONObject updateChange(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {
			PmBiangengVO vo  = (PmBiangengVO) map.get("changevo");
			BaseDAO.update(conn, vo);
			if (null != map.get("json")) {
				org.json.JSONArray array = (org.json.JSONArray) map.get("json");
				MobileFileUpload.insertTable(array, vo.getBiangeng_uid());
			}

			obj = MobileUtil.getMessageInfo(obj);
		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********新增数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	//删除变更
	public JSONObject deleteChange(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {

			DBUtil.exec(conn, "DELETE FROM pm_biangeng WHERE BIANGENG_UID = '"+map.get("changeId")+"'");

			obj = MobileUtil.getMessageInfo(obj);

		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********新增数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	//修改材料
	public JSONObject updateMaterial(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {
			PmCailiaoVO vo  = (PmCailiaoVO) map.get("materialvo");
			BaseDAO.update(conn, vo);
			if (null != map.get("json")) {
				org.json.JSONArray array = (org.json.JSONArray) map.get("json");
				MobileFileUpload.insertTable(array, vo.getCailiao_uid());
			}

			obj = MobileUtil.getMessageInfo(obj);
		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********新增数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	//删除材料
	public JSONObject deleteMaterial(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {

			DBUtil.exec(conn, "DELETE FROM pm_cailiao WHERE CAILIAO_UID = '"+map.get("materialId")+"'");

			obj = MobileUtil.getMessageInfo(obj);

		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********新增数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	//修改检查
	public JSONObject updateCheck(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {
			PmXunjianVO vo  = (PmXunjianVO) map.get("checkvo");
			BaseDAO.update(conn, vo);
			if (null != map.get("json")) {
				org.json.JSONArray array = (org.json.JSONArray) map.get("json");
				MobileFileUpload.insertTable(array, vo.getXunjian_uid());
			}

			obj = MobileUtil.getMessageInfo(obj);
		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********修改数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	//删除检查
	public JSONObject deleteCheck(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {

			DBUtil.exec(conn, "DELETE FROM pm_xunjian WHERE XUNJIAN_UID = '"+map.get("checkId")+"'");

			obj = MobileUtil.getMessageInfo(obj);

		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********删除数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	//修改验收
	public JSONObject updateAcceptance(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {
			PmYanshouVO vo  = (PmYanshouVO) map.get("acceptancevo");
			BaseDAO.update(conn, vo);
			if (null != map.get("json")) {
				org.json.JSONArray array = (org.json.JSONArray) map.get("json");
				MobileFileUpload.insertTable(array, vo.getYanshou_uid());
			}

			obj = MobileUtil.getMessageInfo(obj);
		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********修改数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	//删除验收
	public JSONObject deleteAcceptance(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {

			DBUtil.exec(conn, "DELETE FROM pm_yanshou WHERE YANSHOU_UID = '"+map.get("acceptanceId")+"'");

			obj = MobileUtil.getMessageInfo(obj);

		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********删除数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	//修改日志
	public JSONObject updateProjectLog(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {
			PmProjectLogVO vo  = (PmProjectLogVO) map.get("logvo");
			BaseDAO.update(conn, vo);
			if (null != map.get("json")) {
				org.json.JSONArray array = (org.json.JSONArray) map.get("json");
				MobileFileUpload.insertTable(array, vo.getProject_log_uid());
			}

			obj = MobileUtil.getMessageInfo(obj);
		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********修改数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	//删除日志
	public JSONObject deleteProjectLog(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {

			DBUtil.exec(conn, "DELETE FROM pm_project_log WHERE PROJECT_LOG_UID = '"+map.get("logId")+"'");

			obj = MobileUtil.getMessageInfo(obj);

		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********删除数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	//修改现场工况
	public JSONObject updateXcZkInfo(HashMap<String, Object> map,
			PmXianchangVO vo) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {
			BaseDAO.update(conn, vo);
			if (null != map.get("json")) {
				org.json.JSONArray array = (org.json.JSONArray) map.get("json");
				MobileFileUpload.insertTable(array, vo.getXianchang_uid());
			}

			obj = MobileUtil.getMessageInfo(obj);
		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********修改数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	//删除现场工况
	public JSONObject deleteXcZkInfo(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {

			DBUtil.exec(conn, "DELETE FROM pm_xianchang WHERE XIANCHANG_UID = '"+map.get("xc_uid")+"'");

			obj = MobileUtil.getMessageInfo(obj);

		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********删除数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	public JSONObject deleteFile(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {

			DBUtil.exec(conn, "DELETE FROM bu_file WHERE FILE_UID = '"+map.get("file_uid")+"'");

			obj = MobileUtil.getMessageInfo(obj);

		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********删除数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	public JSONObject getFiles(HashMap<String, String> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT * FROM ( ");
			sql.append(" SELECT d.PROJECT_DOCS_UID as dataId,d.P_DOCS_UID as higher,d.NODE_NAME as name,d.NODE_TYPE as type, ");
			sql.append(" date_format(d.UPDATE_DATE,'%Y-%m-%d %H:%i') as time  ");
			sql.append(" FROM pm_project_docs d  ");
			sql.append(" WHERE d.PROJECT_UID = '"+map.get("projectId")+"' ");
			if("".equals(map.get("P_DOCS_UID"))){
				sql.append(" AND d.P_DOCS_UID is null");
			}else{
				sql.append(" AND d.P_DOCS_UID = '"+map.get("P_DOCS_UID")+"' ");
			}
			sql.append(" ) a ");
			sql.append(" LEFT JOIN ");
			sql.append(" (SELECT f.TARGET_UID,a.FILE_EXT_NAME,a.FILE_PATH, ");
			sql.append(" a.FILE_SIZE as size ");
			sql.append("  FROM bu_file f ");
			sql.append(" LEFT JOIN bu_attachment a ");
			sql.append(" ON f.ATTACHMENT_UID = a.ATTACHMENT_UID ");
			sql.append(" WHERE f.FILE_TYPE = '10801' ");
			sql.append(" AND f.ENABLED = '1' ");
			sql.append(" ) b ");
			sql.append(" ON a.dataId = b.TARGET_UID ");
			List<Map<String, String>> FileList = DBUtil.queryReturnList(conn, sql.toString());
			obj = MobileUtil.getMessageInfo(obj);
			obj.put("dataList", FileList);
		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********新增数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	public JSONObject queryUserInfo(Map<String, String> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT u.USER_UID as userId,u.EMAIL as email, ");
			sql.append(" u.MOBILE as phone,u.USER_NAME as name, ");
			sql.append(" u.LOGON_NAME as username   ");
			sql.append(" FROM sys_user u  ");
			sql.append(" WHERE u.USER_UID = '"+map.get("userId")+"' ");
			List<Map<String, String>> userList = DBUtil.queryReturnList(conn, sql.toString());
			if(userList.size()>0){
				Map<String, String> umap = userList.get(0);
				obj.put("email", umap.get("email"));
				obj.put("phone", umap.get("phone"));
				obj.put("name", umap.get("name"));
				obj.put("userId", umap.get("userId"));
				obj.put("username", umap.get("username"));
			}
			obj = MobileUtil.getMessageInfo(obj);
		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********新增数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	
	public JSONObject getTopicById(HashMap<String, String> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT t.PROJECT_TOPIC_UID as topicId,t.`SUBJECT` as subject,t.CONTENT as content,  ");
			sql.append("  DATE_FORMAT(t.CREATE_DATE,'%Y-%m-%d %H:%i')  as date,u.USER_NAME as username,u.LOGON_NAME as account,t.STATUS as state, ");
			sql.append(
					" (SELECT count(*) FROM pm_project_topic_reply r WHERE r.PROJECT_TOPIC_UID = t.PROJECT_TOPIC_UID) as comment ,");
			sql.append(" (SELECT a.FILE_PATH FROM bu_attachment a  ");
			sql.append(" LEFT JOIN bu_file f  ");
			sql.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID  ");
			sql.append(" WHERE f.FILE_TYPE = '10008'  ");
			sql.append(" AND t.CREATE_USER = f.TARGET_UID  ");
			sql.append(" ORDER BY f.CREATE_DATE DESC LIMIT 1)as userImage ");
			sql.append("  FROM PM_PROJECT_TOPIC t  ");
			sql.append(" LEFT JOIN sys_user u ");
			sql.append(" ON t.CREATE_USER = u.USER_UID ");
			sql.append(" WHERE PROJECT_TOPIC_UID = '"+map.get("topicId")+"'");

			List<Map<String, String>> list = DBUtil.queryReturnList(conn, sql.toString());
			for (int i = 0; i < list.size(); i++) {
				Map logmap = list.get(i);
				StringBuffer sql2 = new StringBuffer();
				sql2.append(" SELECT f.FILE_UID,a.FILE_EXT_NAME,a.FILE_PATH,f.TARGET_UID,a.FILE_NAME  ");
				sql2.append(" FROM bu_attachment a LEFT JOIN bu_file f ");
				sql2.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID ");
				sql2.append(" WHERE f.FILE_TYPE = '10301' AND f.ENABLED = '1' AND f.TARGET_UID = '"
						+ logmap.get("topicId") + "'  ");
				List<Map<String, String>> topicsFileList = DBUtil.queryReturnList(conn, sql2.toString());
				logmap.put("appendix", topicsFileList);//// 回复附件
			}
			obj = MobileUtil.getMessageInfo(obj);
			obj.put("topicList", list);
			/*if(list.size()>0){
				Map<String, String> umap = list.get(0);
				obj.put("subject", umap.get("subject"));
				obj.put("content", umap.get("content"));
				obj.put("date", umap.get("date"));
				obj.put("username", umap.get("username"));
				obj.put("account", umap.get("account"));
				obj.put("state", umap.get("state"));
				obj.put("comment", umap.get("comment"));
				obj.put("userImage", umap.get("userImage"));
			}*/

		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}


	public JSONObject getXcInfoById(HashMap<String, String> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(
					" SELECT `xc`.DESCRIPTION AS conditions,`xc`.XIANCHANG_UID as xc_uid,`xc`.JINDU AS progress,`xc`.`STATUS` AS state,sc.STRUC_NAME AS location,count(an.XIANCHANG_UID) AS comment,");
			sql.append(
					" DATE_FORMAT(`xc`.CREATE_DATE, '%Y-%m-%d %H:%i') AS date,u.USER_NAME AS username,u.LOGON_NAME as account,xc.CREATE_USER as userId,xc.GONGKUANG_TYPE as type, xc.STATUS as state,");
			sql.append("xc.FINISH_DATE as finishDate,xc.SERIOUSNESS as severity , ");
			sql.append(" (SELECT a.FILE_PATH FROM bu_attachment a  ");
			sql.append(" LEFT JOIN bu_file f  ");
			sql.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID  ");
			sql.append(" WHERE f.FILE_TYPE = '10008'  ");
			sql.append(" AND `xc`.CREATE_USER = f.TARGET_UID  ");
			sql.append(" ORDER BY f.CREATE_DATE DESC LIMIT 1)as userImage ,");
			sql.append(" (SELECT count(*) from log_user_read WHERE TARGET_UID = xc.XIANCHANG_UID AND USER_UID = '3') as see ");
			sql.append(" FROM PM_XIANCHANG  AS `xc` LEFT JOIN PM_XIANCHANG_STRUC xs ON xc.XIANCHANG_UID = xs.XIANCHANG_UID ");
			sql.append(" LEFT JOIN pm_xianchang_dafu an ON an.XIANCHANG_UID = xc.XIANCHANG_UID ");
			sql.append("LEFT JOIN PM_PRJ_STRUC sc ON xs.PRJ_STRUC_UID = sc.PRJ_STRUC_UID  ");
			sql.append(" LEFT JOIN sys_user u  ON xc.CREATE_USER = u.USER_UID ");
		
			sql.append(" WHERE xc.XIANCHANG_UID = '" + map.get("xc_uid")+ "' "); 

			List<Map<String, String>> list = DBUtil.queryReturnList(conn, sql.toString());
			for (int i = 0; i < list.size(); i++) {
				StringBuffer sql2 = new StringBuffer();
				Map rmap = list.get(i);
				sql2.append(" SELECT f.FILE_UID,a.FILE_EXT_NAME,a.FILE_PATH,f.TARGET_UID,a.FILE_NAME,f.DESCRIPTION  ");
				sql2.append(" FROM bu_attachment a LEFT JOIN bu_file f ");
				sql2.append(" ON a.ATTACHMENT_UID = f.ATTACHMENT_UID ");
				sql2.append(" WHERE  f.ENABLED = '1' AND f.TARGET_UID = '" + rmap.get("xc_uid") + "'  ");
				if ("ZL".equals(rmap.get("type"))) {
					sql2.append(" AND f.FILE_TYPE = '10010' ");
				} else if ("AQ".equals(rmap.get("type"))) {
					sql2.append(" AND f.FILE_TYPE = '10011' ");
				} else if ("JD".equals(rmap.get("type"))) {
					sql2.append(" AND f.FILE_TYPE = '10009' ");
				}
				List<Map<String, String>> replyFileList = DBUtil.queryReturnList(conn, sql2.toString());
				rmap.put("appendix", replyFileList);//// 回复附件
			}

			obj = MobileUtil.getMessageInfo(obj);
			obj.put("conditionList", list);

		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********查询现场状况出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	/**
	 * 添加阅读信息
	 */
	public JSONObject insertReadInfo(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {
			LogUserReadVO vo = (LogUserReadVO) map.get("vo");
			
			BaseDAO.insert(conn, vo);
			
			obj = MobileUtil.getMessageInfo(obj);
		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********新增数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	
	public JSONObject deleteXianchang(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {

			DBUtil.exec(conn, "DELETE FROM pm_xianchang_dafu WHERE XIANCHANG_DAFU_UID = '"+map.get("commentId")+"'");

			obj = MobileUtil.getMessageInfo(obj);

		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********删除数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	
	public JSONObject deleteReply(HashMap<String, Object> map) {
		Connection conn = DBUtil.getConnection();
		JSONObject obj = new JSONObject();
		try {

			DBUtil.exec(conn, "DELETE FROM pm_project_topic_reply WHERE PROJECT_TOPIC_REPLY_UID = '"+map.get("commentId")+"'");

			obj = MobileUtil.getMessageInfo(obj);

		} catch (Exception e) {
			obj = MobileUtil.getMessageError(obj);
			DaoException.handleMessageException("*********删除数据出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return obj;
	}

	
	public JSONObject checkBtnAuthority(HashMap<String, String> map) {
	    Connection conn = DBUtil.getConnection();
        boolean flag = false;
        JSONObject obj = new JSONObject();
        try {
        	StringBuffer sql = new StringBuffer();
        	sql.append(" SELECT a.* FROM (SELECT * FROM bu_project_user u WHERE  ");
        	sql.append("  u.PROJECT_UID = '"+map.get("projectId")+"' AND u.PROJECT_USER_UID ='"+map.get("projectUserId")+"' ) AS u ");
        	sql.append(" LEFT JOIN bu_project_role_user ru ");
        	sql.append(" ON u.PROJECT_USER_UID = ru.PROJECT_USER_UID ");
        	sql.append(" LEFT JOIN bu_project_role r ");
        	sql.append(" ON ru.PROJECT_ROLE_UID = r.PROJECT_ROLE_UID ");
        	sql.append(" LEFT JOIN bu_project_role_auth a ");
        	sql.append(" ON r.PROJECT_ROLE_UID = a.PROJECT_ROLE_UID ");
        	sql.append(" LEFT JOIN sys_authority t ");
        	sql.append(" ON t.authority_uid = a.authority_uid ");
        	sql.append(" WHERE t.AUTHORITY_CODE = '"+map.get("authorityCode")+"' ");
        	List<Map<String,String>> list = DBUtil.queryReturnList(conn, sql.toString());
        	if(list.size()>0){
        		flag = true;
        	}
        	obj = MobileUtil.getMessageInfo(obj);
        } catch (Exception e) {
        	obj = MobileUtil.getMessageError(obj);
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        obj.put("flag", flag);
        return obj;
	}

}
