/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
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
package  com.ccthanking.business.jkjc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ccthanking.business.jkjc.vo.JcPrjPointsVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> JcPrjPointsDao.java </p>
 * <p> 功能接口：项目监测点位 </p>
 *
 * <p><a href="JcPrjPointsDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-30
 * 
 */

public interface JcPrjPointsDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, JcPrjPointsVO vo, Map map);

	
	/**
	 * 查询 监测点的 类型 
	 * @param msg
	 * @param object
	 * @param object2
	 * @return
	 */
	public String queryPointForType(String msg, HashMap<String, String> map);

	public List<?> queryList(Map<String, String> map);

	public List<Map<String, String>> queryU3DElementUids(
			HashMap<String, String> map);

   /***
    * 查询 U3D图标显示 并获取测点的 预警值 并判断 是否 预警
    * 来显示不同 的 图标
    * @param eleId
    * @return
    */
	public List<Map<String, String>> queryIconById(String eleId);

	public List<Map<String, String>> queryPoints(
			HashMap<String, String> map);

	public List<Map<String, String>> queryItems(
			HashMap<String, String> map);

	public List<Map<String, String>> queryItemTypes(
			HashMap<String, String> map);

	public List<Map<String, String>> queryPoint_Item(
			HashMap<String, String> map);

	public List<Map<String, String>> queryPointProperty(
			HashMap<String, String> map);
	
	public List<Map<String, String>> queryPointData_jc(
			HashMap<String, String> map);
	
	public List<Map<String, String>> queryPointData_cx(
			HashMap<String, String> map);

	public List<Map<String, String>> queryUser_Focus_Point(
			HashMap<String, String> map);
	
	public String queryById(String pRJPOINTSUID);
	
	public List<Map<String, String>> queryNewestDataByPointId_jc(
			HashMap<String, String> map);
	
	public List<Map<String, String>> queryNewestDataByPointId_cx(
			HashMap<String, String> map);

	// 可在此加入业务独特的服务接口
}
