/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.dizhi.PmDizhiVODao.java
 * 创建日期： 2015-12-17 下午 02:12:38
 * 功能：   项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-12-17 下午 02:12:38  卢红冈   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.dizhi.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.dizhi.vo.PmDizhiVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> PmDizhiVODao.java </p>
 * <p> 功能接口：项目 </p>
 *
 * <p><a href="PmDizhiVODao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggnag@163.com">卢红冈</a>
 * @version 0.1
 * @since 2015-12-17
 * 
 */

public interface PmDizhiDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, PmDizhiVO vo, Map map);

	public List<?> queryDxsById(String dxsUid);

	// 可在此加入业务独特的服务接口
}
