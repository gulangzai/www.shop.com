/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.jcsb.JcJkEquipInfoDao.java
 * 创建日期： 2015-10-27 下午 03:52:34
 * 功能：   项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-27 下午 03:52:34  luhonggang   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.jcsb.dao;

import java.util.Map;

import com.ccthanking.business.jcsb.vo.JcJkEquipInfoVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> JcJkEquipInfoDao.java </p>
 * <p> 功能接口：项目 </p>
 *
 * <p><a href="JcJkEquipInfoDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhongang@163.com">luhonggang</a>
 * @version 0.1
 * @since 2015-10-27
 * 
 */

public interface JcJkEquipInfoDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, JcJkEquipInfoVO vo, Map map);

	public String query(String msg, Object object, Object object2);

	// 可在此加入业务独特的服务接口
}
