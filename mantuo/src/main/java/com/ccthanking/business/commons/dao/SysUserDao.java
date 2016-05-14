/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.commons.SysUserDao.java
 * 创建日期： 2015-10-26 下午 10:31:55
 * 功能：   用户信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-26 下午 10:31:55  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.commons.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccthanking.business.commons.vo.SysUserVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> SysUserDao.java </p>
 * <p> 功能接口：用户信息 </p>
 *
 * <p><a href="SysUserDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-26
 * 
 */

public interface SysUserDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, SysUserVO vo, Map map);

	public boolean checkEmail(String param,String userId);

	public boolean checkName(String name,String userId);

	public boolean checkPwd(String pwd, String userId);

	public boolean queryAuthority(HashMap<String, String> map);

	public boolean checkDirAuthority(HashMap<String, String> map);

	public List<Map<String, String>> getSysUserByLoginName(String loginName);

	// 可在此加入业务独特的服务接口
}
