/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2016
 * 文件：   com.ccthanking.business.bucompany.BuCompanyDao.java
 * 创建日期： 2016-04-28 上午 11:14:13
 * 功能：   监测项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-04-28 上午 11:14:13  hushujie   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.bucompany.dao;

import java.util.Map;

import com.ccthanking.business.bucompany.vo.BuCompanyVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuCompanyDao.java </p>
 * <p> 功能接口：监测项目 </p>
 *
 * <p><a href="BuCompanyDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:">hushujie</a>
 * @version 0.1
 * @since 2016-04-28
 * 
 */

public interface BuCompanyDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, BuCompanyVO vo, Map map);
    public boolean deleteid(String companyuid);  
    public String  queryid(String companyuid);
	// 可在此加入业务独特的服务接口
}
