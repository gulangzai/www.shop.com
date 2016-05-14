/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    commons.service.SysUserService.java
 * 创建日期： 2015-10-26 下午 10:31:55
 * 功能：    接口：用户信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-26 下午 10:31:55  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.commons.service;



import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.ccthanking.business.pmxianchang.vo.PmXianchangVO;
import com.ccthanking.framework.base.BaseVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> MobileService.java </p>
 * <p> 功能：用户信息 </p>
 *
 * <p><a href="MobileService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-26
 * 
 */
public interface MobileService extends IBaseService<BaseVO, String> {
	
	JSONObject queryJcData(HashMap<String, String> map);
	
	JSONObject queryJcWorkAndWeather(HashMap<String, String> map);

	JSONObject queryJcNormalPoint(HashMap<String, String> map);
	
	JSONObject queryJcPointData(HashMap<String, String> map);
	
	JSONObject queryJcCurveData(HashMap<String, String> map);

	JSONObject queryJcPointInfo(HashMap<String, String> map);

	JSONObject queryProjectList(HashMap<String, String> map);

	JSONObject querySurveyDataList(HashMap<String, String> map);
	
	JSONObject insertChange(HashMap<String, Object> map);
	
	JSONObject getChange(HashMap<String, String> map);
	
	JSONObject insertMaterial(HashMap<String, Object> map);
	
	JSONObject getMaterial(HashMap<String, String> map);
	
	JSONObject insertCheck(HashMap<String, Object> map);
	
	JSONObject getCheck(HashMap<String, String> map);

	JSONObject insertAcceptance(HashMap<String, Object> map);
	
	JSONObject getAcceptance(HashMap<String, String> map);

	JSONObject insertProjetLog(HashMap<String, Object> map);

	JSONObject getProjetLogs(HashMap<String, String> map);

	JSONObject insertTopic(HashMap<String, Object> map);

	JSONObject getTopics(HashMap<String, String> map);

	JSONObject insertReply(HashMap<String, Object> map);
	
	JSONObject insertXianchang(HashMap<String, Object> map);

	JSONObject getReplyByTopicUid(HashMap<String, String> map);

	JSONObject getXcInfos(HashMap<String, String> map);

	JSONObject getXcResponse(HashMap<String, String> map);

	JSONObject insertXczkInfo(HashMap<String, Object> map,PmXianchangVO vo);

	JSONObject modifyImage(HashMap<String, Object> map);

	JSONObject updateTopic(HashMap<String, Object> map);

	JSONObject deleteTopic(HashMap<String, Object> map);

	JSONObject deleteFiles(HashMap<String, Object> map);

	JSONObject updateChange(HashMap<String, Object> map);

	JSONObject deleteChange(HashMap<String, Object> map);

	JSONObject updateMaterial(HashMap<String, Object> map);

	JSONObject deleteMaterial(HashMap<String, Object> map);

	JSONObject updateCheck(HashMap<String, Object> map);

	JSONObject deleteCheck(HashMap<String, Object> map);

	JSONObject updateAcceptance(HashMap<String, Object> map);

	JSONObject deleteAcceptance(HashMap<String, Object> map);

	JSONObject updateProjectLog(HashMap<String, Object> map);

	JSONObject deleteProjectLog(HashMap<String, Object> map);

	JSONObject updateXcZkInfo(HashMap<String, Object> map, PmXianchangVO vo);

	JSONObject deleteXcZkInfo(HashMap<String, Object> map);

	JSONObject deleteFile(HashMap<String, Object> map);

	JSONObject getFiles(HashMap<String, String> map);

	JSONObject queryUserInfo(Map<String, String> map);

	JSONObject getTopicById(HashMap<String, String> map);

	JSONObject getXcInfoById(HashMap<String, String> map);

	JSONObject insertReadInfo(HashMap<String, Object> map);

	JSONObject deleteReply(HashMap<String, Object> map);

	JSONObject deleteXianchang(HashMap<String, Object> map);

	JSONObject checkBtnAuthority(HashMap<String, String> map);

}
