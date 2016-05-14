/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
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
package  com.ccthanking.business.project.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.ccthanking.business.project.vo.BuProjectVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuProjectDao.java </p>
 * <p> 功能接口：项目 </p>
 *
 * <p><a href="BuProjectDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-17
 * 
 */

public interface BuProjectDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, BuProjectVO vo, Map map);

	public String queryProject(String projectUid);

	public List<?> queryProjectList(String userUid,String projectName);

	public List<?> queryProjectList(String projectUid);

	public List<?> queryDetailById(String projectUid);

	public List<?> queryCity(String msg);

	public List<?> queryCityorControyBycode(String code);

	public List<?> queryWeather(String projectUid);

	public String queryGKdetail(String projectUid);

	public String queryDataJSON(String msg);

	/**据region_code 查询城市的code 并判断其天气是否存在于天气表中**/
	public boolean quertTianQi(String contry);

	public Map<String, Object> queryMyCare(HashMap<String, Object> map);

	public Map<String, Object> queryWarning(HashMap<String, Object> map);

	public Map<String, Object> queryAllPoint(HashMap<String, Object> map);

	public List<?> queryCityName(String code);

	public  String queryid(String projectuid);
	// 可在此加入业务独特的服务接口
}
