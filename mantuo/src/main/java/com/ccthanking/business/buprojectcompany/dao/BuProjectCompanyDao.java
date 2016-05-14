/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2016
 * 文件：   com.ccthanking.business.buprojectcompany.BuProjectCompanyDao.java
 * 创建日期： 2016-04-29 下午 01:42:29
 * 功能：   监测项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-04-29 下午 01:42:29  hushujie   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.buprojectcompany.dao;

import java.util.Map;

import com.ccthanking.business.buprojectcompany.vo.BuProjectCompanyVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuProjectCompanyDao.java </p>
 * <p> 功能接口：监测项目 </p>
 *
 * <p><a href="BuProjectCompanyDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:">hushujie</a>
 * @version 0.1
 * @since 2016-04-29
 * 
 */

public interface BuProjectCompanyDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, BuProjectCompanyVO vo, Map map);
	public boolean deleteid(String projectcompanyid);
    public String queryid(String projectcompanyid);
	// 可在此加入业务独特的服务接口
}
