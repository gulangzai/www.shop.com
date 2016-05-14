/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.jkjc.JcPrjJcItemDao.java
 * 创建日期： 2015-12-08 下午 07:52:25
 * 功能：   监测项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-12-08 下午 07:52:25  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.jkjc.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.jkjc.vo.JcPrjJcItemVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> JcPrjJcItemDao.java </p>
 * <p> 功能接口：监测项目 </p>
 *
 * <p><a href="JcPrjJcItemDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-12-08
 * 
 */

public interface JcPrjJcItemDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, JcPrjJcItemVO vo, Map map);

	public String queryById(String jCPRJITEMUID);

	public List<Map<String, String>> queryJcObject();

	public List<Map<String, String>> queryJcType();

	public Boolean removeData(String jCPRJITEMUID);

	// 可在此加入业务独特的服务接口
}
