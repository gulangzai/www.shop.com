/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2016
 * 文件：   com.ccthanking.business.pmxianchang.PmPrjStrucDao.java
 * 创建日期： 2016-01-25 下午 04:12:17
 * 功能：   项目结构
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-01-25 下午 04:12:17  卢红冈   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.pmxianchang.dao;

import java.util.Map;

import com.ccthanking.business.pmxianchang.vo.PmPrjStrucVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> PmPrjStrucDao.java </p>
 * <p> 功能接口：项目结构 </p>
 *
 * <p><a href="PmPrjStrucDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggang@kzcpm.com ">卢红冈</a>
 * @version 0.1
 * @since 2016-01-25
 * 
 */

public interface PmPrjStrucDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, PmPrjStrucVO vo, Map map);

	// 可在此加入业务独特的服务接口
}
