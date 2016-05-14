/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.jkjc.JcPointPropertyDao.java
 * 创建日期： 2015-10-30 上午 09:38:07
 * 功能：   监测点属性值
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-30 上午 09:38:07  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.jkjc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccthanking.business.jkjc.vo.JcPointPropertyVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> JcPointPropertyDao.java </p>
 * <p> 功能接口：监测点属性值 </p>
 *
 * <p><a href="JcPointPropertyDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-30
 * 
 */

public interface JcPointPropertyDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, JcPointPropertyVO vo, Map map);

	public String querybyTypeId(String itemId, String typeId);

	public String querybyPoint_Item_Id(String pointitemId);
	
	public String queryTypenameByTypeid(String typeId);
	
	public String update(HashMap<String, String> map);
	
	// 可在此加入业务独特的服务接口
}
