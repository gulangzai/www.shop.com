/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
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
package  com.ccthanking.business.jkjc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.ccthanking.business.jkjc.vo.JcJcDataVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> JcJcDataDao.java </p>
 * <p> 功能接口：监测数据 </p>
 *
 * <p><a href="JcJcDataDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-30
 * 
 */

public interface JcJcDataDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, JcJcDataVO vo, Map map);

	public Map<String,Object> queryLineChart(HashMap<String, Object> map);

	public Map<String,Object> queryBarChart(HashMap<String, Object> map);

	public Map<String,Object> queryTab(HashMap<String, Object> map);

	public List<Map<String, String>> getTypeByCode(HashMap<String, Object> map);


	// 可在此加入业务独特的服务接口
}
