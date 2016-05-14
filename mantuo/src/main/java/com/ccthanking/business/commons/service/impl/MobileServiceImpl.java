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
package com.ccthanking.business.commons.service.impl;


import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ccthanking.business.commons.dao.MobileDao;
import com.ccthanking.business.commons.service.MobileService;
import com.ccthanking.business.pmxianchang.vo.PmXianchangStrucVO;
import com.ccthanking.business.pmxianchang.vo.PmXianchangVO;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.base.BaseVO;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.ccthanking.framework.util.MobileFileUpload;
import com.ccthanking.framework.util.MobileUtil;



/**
 * <p> MobileServiceImpl.java </p>
 * <p> 功能：用户信息 </p>
 *
 * <p><a href="MobileServiceImpl.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-26
 * 
 */

@Service
public class MobileServiceImpl extends Base1ServiceImpl<BaseVO, String> implements MobileService {

    private MobileDao mobileDao;


	@Autowired
	@Qualifier("mobileDaoImpl")
	public void setMobileDao(MobileDao mobileDao) {
		this.mobileDao = mobileDao;
	}

	public com.alibaba.fastjson.JSONObject queryJcData(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return this.mobileDao.queryJcData(map);
	}
	
	public com.alibaba.fastjson.JSONObject queryJcWorkAndWeather(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return this.mobileDao.queryJcWorkAndWeather(map);
	}

	
	public com.alibaba.fastjson.JSONObject queryJcNormalPoint(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return this.mobileDao.queryJcNormalPoint(map);
	}
	
	
	public com.alibaba.fastjson.JSONObject queryJcPointData(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return this.mobileDao.queryJcPointData(map);
	}
	
	
	public com.alibaba.fastjson.JSONObject queryJcCurveData(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return this.mobileDao.queryJcCurveData(map);
	}
	
	
	public com.alibaba.fastjson.JSONObject queryJcPointInfo(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return this.mobileDao.queryJcPointInfo(map);
	}

	public com.alibaba.fastjson.JSONObject queryProjectList(
			HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return this.mobileDao.queryProjectList(map);
	}

	public com.alibaba.fastjson.JSONObject querySurveyDataList(
			HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return this.mobileDao.querySurveyDataList(map);
	}
	
	public JSONObject insertChange(HashMap<String, Object> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.insertChange(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}
	
	public JSONObject getChange(HashMap<String, String> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.getChange(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}
	

	
	public JSONObject insertMaterial(HashMap<String, Object> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.insertMaterial(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}
	
	
	public JSONObject getMaterial(HashMap<String, String> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.getMaterial(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}
	
	
	public JSONObject insertCheck(HashMap<String, Object> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.insertCheck(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}
	
	
	public JSONObject getCheck(HashMap<String, String> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.getCheck(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}
	
	
	public JSONObject insertAcceptance(HashMap<String, Object> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.insertAcceptance(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}

	
	public JSONObject getAcceptance(HashMap<String, String> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.getAcceptance(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}
	
	public JSONObject insertProjetLog(HashMap<String, Object> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.insertProjetLog(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}

	public JSONObject getProjetLogs(HashMap<String, String> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.getProjetLogs(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}

	public JSONObject insertTopic(HashMap<String, Object> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.insertTopic(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}

	public JSONObject getTopics(HashMap<String, String> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.getTopics(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}

	public JSONObject insertReply(HashMap<String, Object> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.insertReply(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}
	
	public JSONObject insertXianchang(HashMap<String, Object> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.insertXianchang(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}

	public JSONObject getReplyByTopicUid(HashMap<String, String> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.getReplyByTopicUid(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}

	public JSONObject getXcInfos(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return this.mobileDao.getInfos(map);
	}

	public JSONObject getXcResponse(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return this.mobileDao.getResponse(map);
	}

	public JSONObject insertXczkInfo(HashMap<String, Object> map,PmXianchangVO vo) {
		JSONObject obj = new JSONObject();
		Connection conn = DBUtil.getConnection();
		try {
			if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			 long xc_uid= BaseDAO.insert(conn,vo );
			 if(!"".equals(map.get("locationId")) && xc_uid != 0){//结构表的主键id和现场主键id不为空
				 PmXianchangStrucVO pvo = new PmXianchangStrucVO();
				 pvo.setXianchang_uid(String.valueOf(xc_uid));
				 pvo.setPrj_struc_uid(String.valueOf(map.get("locationId")));
				 BaseDAO.insert(conn,pvo); 
			 }
			 if(null!=map.get("json")){
					org.json.JSONArray array = (org.json.JSONArray) map.get("json");
					MobileFileUpload.insertTable(array,String.valueOf(xc_uid));	
			 }
			 obj = MobileUtil.getMessageInfo(obj);	
			}else{
				MobileUtil.getErrorToken(obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtil.closeConnetion(conn);
		}
		
		
		return obj;
	}

	public JSONObject modifyImage(HashMap<String, Object> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.modifyImage(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}

	public JSONObject updateTopic(HashMap<String, Object> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.updateTopic(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}

	public JSONObject deleteTopic(HashMap<String, Object> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.deleteTopic(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}

	public JSONObject deleteFiles(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.deleteFiles(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}

	public JSONObject updateChange(HashMap<String, Object> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.updateChange(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}

	public JSONObject deleteChange(HashMap<String, Object> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.deleteChange(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}

	public JSONObject updateMaterial(HashMap<String, Object> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.updateMaterial(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}

	public JSONObject deleteMaterial(HashMap<String, Object> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.deleteMaterial(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}

	public JSONObject updateCheck(HashMap<String, Object> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.updateCheck(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}

	public JSONObject deleteCheck(HashMap<String, Object> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.deleteCheck(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}

	public JSONObject updateAcceptance(HashMap<String, Object> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.updateAcceptance(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}

	public JSONObject deleteAcceptance(HashMap<String, Object> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.deleteAcceptance(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}

	public JSONObject updateProjectLog(HashMap<String, Object> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.updateProjectLog(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}

	public JSONObject deleteProjectLog(HashMap<String, Object> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.deleteProjectLog(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}

	public JSONObject updateXcZkInfo(HashMap<String, Object> map,
			PmXianchangVO vo) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.updateXcZkInfo(map,vo);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}

	public JSONObject deleteXcZkInfo(HashMap<String, Object> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.deleteXcZkInfo(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}

	public JSONObject deleteFile(HashMap<String, Object> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.deleteFile(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}

	public JSONObject getFiles(HashMap<String, String> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.getFiles(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}

	public JSONObject queryUserInfo(Map<String, String> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.queryUserInfo(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}


	public JSONObject getTopicById(HashMap<String, String> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.getTopicById(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}


	public JSONObject getXcInfoById(HashMap<String, String> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.getXcInfoById(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}

	
	public JSONObject insertReadInfo(HashMap<String, Object> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.insertReadInfo(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}

	
	public JSONObject deleteReply(HashMap<String, Object> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.deleteReply(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}

	
	public JSONObject deleteXianchang(HashMap<String, Object> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.deleteXianchang(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}


	public JSONObject checkBtnAuthority(HashMap<String, String> map) {
		JSONObject obj = new JSONObject();
		if (map.get("token").equals(MobileUtil.WXCTOKEN)) {
			obj = this.mobileDao.checkBtnAuthority(map);
		}else{
			MobileUtil.getErrorToken(obj);
		}
		return obj;
	}
}
