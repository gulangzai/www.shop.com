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
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.ccthanking.business.pmxianchang.vo.PmXianchangVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> MobileDao.java </p>
 * <p> 功能接口：用户信息 </p>
 *
 * <p><a href="MobileDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-26
 * 
 */

public interface MobileDao extends BsBaseDaoable {
	public JSONObject queryJcData(HashMap<String, String> map);
	
	public JSONObject queryJcWorkAndWeather(HashMap<String, String> map);
	
	public JSONObject queryJcNormalPoint(HashMap<String, String> map);
	
	public JSONObject queryJcPointData(HashMap<String, String> map);
	
	public JSONObject queryJcCurveData(HashMap<String, String> map);
	
	public JSONObject queryJcPointInfo(HashMap<String, String> map);

	public JSONObject queryProjectList(HashMap<String, String> map);

	public JSONObject querySurveyDataList(HashMap<String, String> map);
	
	public JSONObject insertChange(HashMap<String, Object> map);
	
	public JSONObject getChange(HashMap<String, String> map);
	
	public JSONObject insertMaterial(HashMap<String, Object> map);
	
	public JSONObject getMaterial(HashMap<String, String> map);
	
	public JSONObject insertCheck(HashMap<String, Object> map);
	
	public JSONObject getCheck(HashMap<String, String> map);

	public JSONObject insertAcceptance(HashMap<String, Object> map);

	public JSONObject getAcceptance(HashMap<String, String> map);

	public JSONObject insertProjetLog(HashMap<String, Object> map);

	public JSONObject getProjetLogs(HashMap<String, String> map);

	public JSONObject insertTopic(HashMap<String, Object> map);

	public JSONObject getTopics(HashMap<String, String> map);

	public JSONObject insertReply(HashMap<String, Object> map);
	
	public JSONObject insertXianchang(HashMap<String, Object> map);

	public JSONObject getReplyByTopicUid(HashMap<String, String> map);

	public JSONObject getInfos(HashMap<String, String> map);

	public JSONObject getResponse(HashMap<String, String> map);

	public JSONObject modifyImage(HashMap<String, Object> map);

	public JSONObject updateTopic(HashMap<String, Object> map);

	public JSONObject deleteTopic(HashMap<String, Object> map);

	public JSONObject deleteFiles(HashMap<String, Object> map);

	public JSONObject updateChange(HashMap<String, Object> map);

	public JSONObject deleteChange(HashMap<String, Object> map);

	public JSONObject updateMaterial(HashMap<String, Object> map);

	public JSONObject deleteMaterial(HashMap<String, Object> map);

	public JSONObject updateCheck(HashMap<String, Object> map);

	public JSONObject deleteCheck(HashMap<String, Object> map);

	public JSONObject updateAcceptance(HashMap<String, Object> map);

	public JSONObject deleteAcceptance(HashMap<String, Object> map);

	public JSONObject updateProjectLog(HashMap<String, Object> map);

	public JSONObject deleteProjectLog(HashMap<String, Object> map);

	public JSONObject updateXcZkInfo(HashMap<String, Object> map,
			PmXianchangVO vo);

	public JSONObject deleteXcZkInfo(HashMap<String, Object> map);

	public JSONObject deleteFile(HashMap<String, Object> map);

	public JSONObject getFiles(HashMap<String, String> map);

	public JSONObject queryUserInfo(Map<String, String> map);

	public JSONObject getTopicById(HashMap<String, String> map);

	public JSONObject getXcInfoById(HashMap<String, String> map);

	public JSONObject insertReadInfo(HashMap<String, Object> map);

	public JSONObject deleteXianchang(HashMap<String, Object> map);

	public JSONObject deleteReply(HashMap<String, Object> map);

	public JSONObject checkBtnAuthority(HashMap<String, String> map);

}
