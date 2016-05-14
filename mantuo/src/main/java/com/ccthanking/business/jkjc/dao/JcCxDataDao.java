/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
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
package  com.ccthanking.business.jkjc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccthanking.business.jkjc.vo.JcCxDataVO;
import com.ccthanking.framework.dao.BsBaseDaoable;
import com.github.abel533.echarts.Option;


/**
 * <p> JcCxDataDao.java </p>
 * <p> 功能接口：测斜数据 </p>
 *
 * <p><a href="JcCxDataDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-12-01
 * 
 */

public interface JcCxDataDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, JcCxDataVO vo, Map map);

	public Option queryBarChart(HashMap<String, Object> map);

	public Option queryLineChart(HashMap<String, Object> map);

	public Map<String,Object>  queryTab(HashMap<String, String> map);

	public Option queryLineChart2(HashMap<String, Object> map);

	public Option queryBarChart2(HashMap<String, Object> map);

	// 可在此加入业务独特的服务接口
}
