/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2016
 * 文件：   com.ccthanking.business.pmxianchang.PmXianchangDao.java
 * 创建日期： 2016-01-22 上午 10:45:08
 * 功能：   进度质量安全
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-01-22 上午 10:45:08  卢红冈   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.pmxianchang.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.pmxianchang.vo.PmXianchangVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> PmXianchangDao.java </p>
 * <p> 功能接口：进度质量安全 </p>
 *
 * <p><a href="PmXianchangDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggang@kzcpm.com ">卢红冈</a>
 * @version 0.1
 * @since 2016-01-22
 * 
 */

public interface PmXianchangDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, PmXianchangVO vo, Map map);

	public Map<String, List<?>> queryXcQK(String xcUid, String fileType);

	// 可在此加入业务独特的服务接口
}
