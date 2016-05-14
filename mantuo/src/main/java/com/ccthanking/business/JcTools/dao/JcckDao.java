/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.JcTools.JcckDao.java
 * 创建日期： 2015-10-29 下午 02:32:36
 * 功能：   仓库备品
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-29 下午 02:32:36  luhonggng   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.JcTools.dao;

import java.util.Map;

import com.ccthanking.business.jcck.vo.JcToolsVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> JcckDao.java </p>
 * <p> 功能接口：仓库备品 </p>
 *
 * <p><a href="JcckDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggang@163.com">luhonggng</a>
 * @version 0.1
 * @since 2015-10-29
 * 
 */

public interface JcckDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, JcToolsVO vo, Map map);

	// 可在此加入业务独特的服务接口
}
